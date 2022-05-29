/*-
 * ========================LICENSE_START=================================
 * PRODIS BackEnd - INTEGRATION AAEP, IRIDE, COMONL submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodisweb.profilazione.helper;

import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import it.csi.iride2.policy.entity.Identita;
import it.csi.prodis.prodisweb.lib.util.log.LogUtil;
import it.csi.prodis.prodisweb.profilazione.Constants;
import it.csi.prodis.prodisweb.profilazione.dto.DatiAggiuntiviConsulente;
import it.csi.prodis.prodisweb.profilazione.dto.DatiAggiuntiviImpresa;
import it.csi.prodis.prodisweb.profilazione.dto.ImpresaInfoc;
import it.csi.prodis.prodisweb.profilazione.dto.ProfileComonl;
import it.csi.prodis.prodisweb.profilazione.dto.Profilo;
import it.csi.prodis.prodisweb.profilazione.dto.ProfiloUtente;
import it.csi.prodis.prodisweb.profilazione.dto.ProfiloUtenteProdis;
import it.csi.prodis.prodisweb.profilazione.dto.RuoloIrideListaCasiUso;
import it.csi.servizioaaep2.vo.AziendaAAEP;

/**
 * Crea il profilo dell'utente con le informazioni reperite da - Iride2 - AAEP -
 * COMONL NOTA: questa costruzione del profilo e' stata importata da
 * AMINDER.orchlavprof
 */
public class ProfileParser {

//	private static final Logger logger = Logger.getLogger(Constants.LOGGER);
	protected final LogUtil logger = new LogUtil(getClass());

	public ProfiloUtenteProdis creaProfilo(ProfiloUtente listaProfili, Identita identitaIride) {
		ProfiloUtenteProdis profiloUtente = new ProfiloUtenteProdis(listaProfili);

		List<RuoloIrideListaCasiUso> listaRuoliIride2 = listaProfili.getListRuoliIride();

		// non � una Map, perch� dal lato orchestratore per ora gestisco solo la chiave
		// della mappa
		List<ImpresaInfoc> listaAziendeAAEP = listaProfili.getListaAziendeAAEP();

		List<ProfileComonl> listaProfiliComonl = listaProfili.getListaProfiliComonl();

		profiloUtente.inizializzaListaRuoli();

		if (listaAziendeAAEP != null) {
			// per motivi di prestazioni nel caso di alcuni utenti che sono associati
			// a molte aziende su AAEP come legali rappresentanti:
			// eliminato nel servizio IRIDE il caricamento del ruolo
			// LEGALE_RAPPRESENTANTE, perch� comunque recuperati da AAEP
			for (ImpresaInfoc profiloAAEP : listaAziendeAAEP) {
				if (profiloAAEP.getDescrFonte().equals("LR")) {
					Profilo profilo = new Profilo();
					profilo.setCfUtente(identitaIride.getCodFiscale());
					profilo.setDenominazioneAzienda(profiloAAEP.getRagioneSociale());
					profilo.setCodiceFiscale(profiloAAEP.getCodiceFiscale());
					profilo.setCognome(identitaIride.getCognome());
					profilo.setNome(identitaIride.getNome());

					for (RuoloIrideListaCasiUso r : listaRuoliIride2) {
						if (r.getActor() != null) {
							String attore = StringUtils.substringBetween(r.getActor().getId(), ",", "]");

							if (attore.equalsIgnoreCase(Constants.LEGALE_RAPPRESENTANTE_COMONL)
									&& r.getListaCasiUso() != null) {
								profilo.setRuolo(Constants.LEGALE_RAPPRESENTANTE_COMONL_DESC);
								System.out.println("..... OrchLavProfImpl size= " + r.getListaCasiUso().size());
								profilo.setListaCasiUso(r.getListaCasiUso());
								break;
							}
						}
					}

					profiloUtente.addRuoloInLista(profilo);
					logger.debug("[ProfileParser::creaProfilo] aggiunto ruolo, " + profiloAAEP.getRagioneSociale() + " "
							+ profiloAAEP.getCodiceFiscale(), "");
				}
			}
		}

		// -----------------------------------------------------------------------------
		// IRIDE
		// -----------------------------------------------------------------------------
		for (RuoloIrideListaCasiUso r : listaRuoliIride2) {
			// den azienda | ruolo | cf
			Profilo profilo = new Profilo();
			profilo.setCfUtente(identitaIride.getCodFiscale());
			profilo.setCodiceFiscale(identitaIride.getCodFiscale());
			profilo.setCognome(identitaIride.getCognome());
			profilo.setNome(identitaIride.getNome());
			if (r.getActor() != null) {

				/*
				 * esempi:
				 * 
				 * r.getCodiceDominio= UTENTI_IRIDE2 r.getCodiceRuolo= TITOLARE_CF
				 * r.getMnemonico= TITOLARE_CF@UTENTI_IRIDE2
				 *
				 * r.getCodiceDominio= ALI r.getCodiceRuolo= LEGALE_RAPPRESENTANTE
				 * r.getMnemonico= LEGALE_RAPPRESENTANTE@ALI
				 */

				/*
				 * esempi di actor.id:
				 * 
				 * Actor[Application[AMINDER],CONSULENTE_RESPONSABILE_COMONL]
				 * 
				 */
				String attore = StringUtils.substringBetween(r.getActor().getId(), ",", "]");
				if (attore.equalsIgnoreCase(Constants.TITOLARE_CF_GENERICO_COMONL)) {
					// non pu� entrare
					logger.debug("[ProfileParser::creaProfilo]trovato ruolo TITOLARE_CF --> non aggiungo in lista", "");
				} else if (attore.equalsIgnoreCase(Constants.PERSONA_CARICA_AZIENDALE_COMONL)) {
					logger.debug("[ProfileParser::creaProfilo] PERSONA_CARICA_AZIENDALE_COMONL", "");

					// -----------------------------------------------------------------------------
					// AAEP
					// -----------------------------------------------------------------------------

					if (listaAziendeAAEP != null) {

						for (int j = 0; j < listaAziendeAAEP.size(); j++) {
							ImpresaInfoc profiloAAEP = listaAziendeAAEP.get(j);

							if (profiloAAEP != null && !profiloAAEP.getDescrFonte().equals("LR")) {
								// in caso di attore = PERSONA_CARICA_AZIENDALE_COMONL si
								// prendono quelli diversi da LR

								profilo = new Profilo();
								profilo.setCfUtente(identitaIride.getCodFiscale());
								profilo.setDenominazioneAzienda(profiloAAEP.getRagioneSociale());
								profilo.setRuolo(determinaRuoloAttoreDaVisualizzare(attore));
								profilo.setCodiceFiscale(profiloAAEP.getCodiceFiscale());
								profilo.setCognome(identitaIride.getCognome());
								profilo.setNome(identitaIride.getNome());

								if (r.getListaCasiUso() != null) {
									logger.debug("[ProfileParser::creaProfilo] Lista Casi Uso size= "
											+ r.getListaCasiUso().size(), "");
									profilo.setListaCasiUso(r.getListaCasiUso());
								}

								profiloUtente.addRuoloInLista(profilo);
								logger.debug("[ProfileParser::creaProfilo] aggiunto ruolo "
										+ profiloAAEP.getRagioneSociale() + " " + profiloAAEP.getCodiceFiscale(), "");
							}
						}
					}

				}

				else if (attore.equalsIgnoreCase(Constants.PERS_AUTORIZZATA_COMONL) && (listaProfiliComonl != null)) {

					// -----------------------------------------------------------------------------
					// COMONL
					// -----------------------------------------------------------------------------
					for (int k = 0; k < listaProfiliComonl.size(); k++) {
						// den azienda | ruolo | cf

						ProfileComonl profiloComonl = listaProfiliComonl.get(k);
						if (profiloComonl.getTipoAnagrafica().equals(Constants.COMONL_PERSONA_AUTORIZZATA)
								&& profiloComonl.getDatiAggiuntiviImpresa() != null) {
							logger.debug("[ProfileParser::creaProfilo] profiloComonl.getTipoAnagrafica= "
									+ profiloComonl.getTipoAnagrafica(), "");

							boolean conDelega = isNotVoid(profiloComonl.getDelegaValida())
									&& profiloComonl.getDelegaValida().equalsIgnoreCase(Constants.FLAG_S) ? true
											: false;
							logger.debug("[ProfileParser::creaProfilo] conDelega= " + conDelega, "");

							logger.debug("[ProfileParser::creaProfilo] profiloComonl.getDatiAggiuntiviImpresa().size = "
									+ profiloComonl.getDatiAggiuntiviImpresa().size(), "");
							for (int j = 0; j < profiloComonl.getDatiAggiuntiviImpresa().size(); j++) {
								DatiAggiuntiviImpresa datiAgg = profiloComonl.getDatiAggiuntiviImpresa().get(j);

								boolean scuola = isNotVoid(datiAgg.getFlgScuolaPubblica())
										&& datiAgg.getFlgScuolaPubblica().equalsIgnoreCase(Constants.FLAG_S) ? true
												: false;

								if (!scuola && !conDelega) {
									// solo se flag scuola = N
									profilo = new Profilo();
									profilo.setCfUtente(identitaIride.getCodFiscale());

									String denom = Constants.DATO_NON_PERVENUTO;
									if (datiAgg.getDenominazioneImpresaNoAAEP() == null
											|| datiAgg.getDenominazioneImpresaNoAAEP().equals("")) {
										try {
											// Ricerco su AAEP la DESCRIZIONE
											DatiAggiuntiviImpresa temp = new DatiAggiuntiviImpresa();

											temp = getDescrizioneDaAAEP(datiAgg, identitaIride);
											denom = isNotVoid(temp.getDenominazioneImpresaNoAAEP())
													? temp.getDenominazioneImpresaNoAAEP()
													: denom;
										} catch (Exception e) {
										}
									} else if (isNotVoid(datiAgg.getDenominazioneImpresaNoAAEP())) {
										denom = datiAgg.getDenominazioneImpresaNoAAEP();
									}
									profilo.setDenominazioneAzienda(denom);

									profilo.setRuolo(determinaRuoloAttoreDaVisualizzare(attore));
									profilo.setCodiceFiscale(datiAgg != null ? datiAgg.getCodiceFiscaleImpresa()
											: Constants.DATO_NON_PERVENUTO);
									profilo.setCognome(identitaIride.getCognome());
									profilo.setNome(identitaIride.getNome());
									// TODO :: datiAgg.getFlgImpresaAccentrata();

									if (r.getListaCasiUso() != null) {
										logger.debug("[ProfileParser::creaProfilo] r.getListaCasiUso.size= "
												+ r.getListaCasiUso().size(), "");
										profilo.setListaCasiUso(r.getListaCasiUso());
									}

									profiloUtente.addRuoloInLista(profilo);
									logger.debug("[ProfileParser::creaProfilo] aggiunto ruolo: "
											+ profiloComonl.getTipoAnagrafica(), "");
								}
							}

						}
					}
				}

				else if (attore.equalsIgnoreCase(Constants.PERS_AUTORIZZATA_SCUOLA_COMONL)
						&& (listaProfiliComonl != null)) {

					// -----------------------------------------------------------------------------
					// COMONL
					// -----------------------------------------------------------------------------

					// System.out.println("..... AuthenticationInterceptor.intercept,
					// listaProfiliComonl.size= " + listaProfiliComonl.size());
					for (int k = 0; k < listaProfiliComonl.size(); k++) {
						// den azienda | ruolo | cf

						ProfileComonl profiloComonl = listaProfiliComonl.get(k);
						if (profiloComonl.getTipoAnagrafica().equals(Constants.COMONL_PERSONA_AUTORIZZATA)
								&& profiloComonl.getDatiAggiuntiviImpresa() != null) {

							logger.debug("[ProfileParser::creaProfilo] profiloComonl.getTipoAnagrafica= "
									+ profiloComonl.getTipoAnagrafica(), "");
							if (profiloComonl.getConsulente() != null) {
								logger.debug(
										"[ProfileParser::creaProfilo] profiloComonl.getTipoAnagrafica.studio= "
												+ profiloComonl.getConsulente().getDescrizioneStudioProfessionale(),
										"");
							}

							boolean conDelega = isNotVoid(profiloComonl.getDelegaValida())
									&& profiloComonl.getDelegaValida().equalsIgnoreCase(Constants.FLAG_S) ? true
											: false;

							for (int j = 0; j < profiloComonl.getDatiAggiuntiviImpresa().size(); j++) {
								DatiAggiuntiviImpresa datiAgg = profiloComonl.getDatiAggiuntiviImpresa().get(j);

								boolean scuola = isNotVoid(datiAgg.getFlgScuolaPubblica())
										&& datiAgg.getFlgScuolaPubblica().equalsIgnoreCase(Constants.FLAG_S) ? true
												: false;

								if (scuola && !conDelega) {
									// solo se flag scuola = S
									profilo = new Profilo();
									profilo.setCfUtente(identitaIride.getCodFiscale());
									profilo.setDenominazioneAzienda(isNotVoid(datiAgg.getDenominazioneImpresaNoAAEP())
											? datiAgg.getDenominazioneImpresaNoAAEP()
											: Constants.DATO_NON_PERVENUTO);
									profilo.setRuolo(determinaRuoloAttoreDaVisualizzare(attore));
									profilo.setCodiceFiscale(isNotVoid(datiAgg.getCodiceFiscaleImpresa())
											? datiAgg.getCodiceFiscaleImpresa()
											: Constants.DATO_NON_PERVENUTO);
									profilo.setCognome(identitaIride.getCognome());
									profilo.setNome(identitaIride.getNome());

									if (r.getListaCasiUso() != null) {
										logger.debug("[ProfileParser::creaProfilo] r.getListaCasiUso.size= "
												+ r.getListaCasiUso().size(), "");
										profilo.setListaCasiUso(r.getListaCasiUso());
									}

									profiloUtente.addRuoloInLista(profilo);
									logger.debug("[ProfileParser::creaProfilo] OrchLavProfImpl, aggiunto ruolo: "
											+ profiloComonl.getTipoAnagrafica(), "");
								}
							}

						}
					}
				}

				else if (attore.equalsIgnoreCase(Constants.CONSULENTE_RESPONSABILE_COMONL)
						&& (listaProfiliComonl != null)) {
					// -----------------------------------------------------------------------------
					// COMONL
					// -----------------------------------------------------------------------------

					for (int k = 0; k < listaProfiliComonl.size(); k++) {
						ProfileComonl profiloComonl = listaProfiliComonl.get(k);
						if (profiloComonl.getTipoAnagrafica().equals(Constants.COMONL_CONSULENTE)) {
							if (profiloComonl.getConsulente() != null) {
								logger.debug(
										"[ProfileParser::creaProfilo] profiloComonl.getTipoAnagrafica.studio= "
												+ profiloComonl.getConsulente().getDescrizioneStudioProfessionale(),
										"");
							}
							DatiAggiuntiviConsulente datiCons = isNotVoid(profiloComonl.getConsulente())
									? profiloComonl.getConsulente()
									: new DatiAggiuntiviConsulente();

							profilo = new Profilo();
							profilo.setCfUtente(identitaIride.getCodFiscale());
							profilo.setDenominazioneAzienda(isNotVoid(datiCons.getDescrizioneStudioProfessionale())
									? datiCons.getDescrizioneStudioProfessionale()
									: Constants.DATO_NON_PERVENUTO);
							profilo.setRuolo(determinaRuoloAttoreDaVisualizzare(attore));
							profilo.setCodiceFiscale(isNotVoid(datiCons.getCodiceFiscaleStudioProfessionale())
									? datiCons.getCodiceFiscaleStudioProfessionale()
									: Constants.DATO_NON_PERVENUTO);
							profilo.setCognome(identitaIride.getCognome());
							profilo.setNome(identitaIride.getNome());
							profilo.setCodMinSoggettoAbilitato(datiCons.getCodMinSoggettoAbilitato());
							profilo.setFlgConsulenteAccentrato(datiCons.getFlgConsulenteAccentrato());

							if (r.getListaCasiUso() != null) {
								logger.debug("[ProfileParser::creaProfilo] r.getListaCasiUso.size= "
										+ r.getListaCasiUso().size(), "");
								profilo.setListaCasiUso(r.getListaCasiUso());
							}

							profiloUtente.addRuoloInLista(profilo);
							logger.debug("[ProfileParser::creaProfilo] OrchLavProfImpl, aggiunto ruolo: "
									+ profiloComonl.getTipoAnagrafica(), "");
						}
					}
				}

				else if (attore.equalsIgnoreCase(Constants.DELEGATO_RESPONSABILE_COMONL)
						&& (listaProfiliComonl != null)) {
					// -----------------------------------------------------------------------------
					// COMONL
					// -----------------------------------------------------------------------------

					for (int k = 0; k < listaProfiliComonl.size(); k++) {
						// den azienda | ruolo | cf

						ProfileComonl profiloComonl = listaProfiliComonl.get(k);
						if (profiloComonl.getTipoAnagrafica().equals(Constants.COMONL_DELEGATO)
								&& profiloComonl.getDatiAggiuntiviImpresa() != null) {
							if (profiloComonl.getConsulente() != null) {
								logger.debug(
										"[ProfileParser::creaProfilo] profiloComonl.getTipoAnagrafica.studio= "
												+ profiloComonl.getConsulente().getDescrizioneStudioProfessionale(),
										"");
							}
							boolean conDelega = isNotVoid(profiloComonl.getDelegaValida())
									&& profiloComonl.getDelegaValida().equalsIgnoreCase(Constants.FLAG_S) ? true
											: false;
							for (int j = 0; j < profiloComonl.getDatiAggiuntiviImpresa().size(); j++) {
								DatiAggiuntiviImpresa datiAgg = profiloComonl.getDatiAggiuntiviImpresa().get(j);

								boolean scuola = isNotVoid(datiAgg.getFlgScuolaPubblica())
										&& datiAgg.getFlgScuolaPubblica().equalsIgnoreCase(Constants.FLAG_S) ? true
												: false;

								if (!scuola && conDelega) {
									// solo se flag scuola = N e c'� la delega
									profilo = new Profilo();
									profilo.setCfUtente(identitaIride.getCodFiscale());
									profilo.setDenominazioneAzienda(isNotVoid(datiAgg.getDenominazioneImpresaNoAAEP())
											? datiAgg.getDenominazioneImpresaNoAAEP()
											: Constants.DATO_NON_PERVENUTO);
									profilo.setRuolo(determinaRuoloAttoreDaVisualizzare(attore));
									profilo.setCodiceFiscale(isNotVoid(datiAgg.getCodiceFiscaleImpresa())
											? datiAgg.getCodiceFiscaleImpresa()
											: Constants.DATO_NON_PERVENUTO);
									profilo.setCognome(identitaIride.getCognome());
									profilo.setNome(identitaIride.getNome());

									if (r.getListaCasiUso() != null) {
										logger.debug("[ProfileParser::creaProfilo] r.getListaCasiUso.size= "
												+ r.getListaCasiUso().size(), "");
										profilo.setListaCasiUso(r.getListaCasiUso());
									}

									profiloUtente.addRuoloInLista(profilo);
									logger.debug("[ProfileParser::creaProfilo] OrchLavProfImpl, aggiunto ruolo: "
											+ profiloComonl.getTipoAnagrafica(), "");
								}
							}

						}
					}

				} else if (attore.equalsIgnoreCase(Constants.OPERATORE_PROV_PRODIS)
						|| attore.equalsIgnoreCase(Constants.AMMINISTRATORE_PRODIS)
						|| attore.equalsIgnoreCase(Constants.MONITORAGGIO_CSI)) {

					// TODO: recupero cf, cognome, nome da documento INFP fornito ad IRIDE
					profilo = new Profilo();
					profilo.setCfUtente(identitaIride.getCodFiscale());
					profilo.setDenominazioneAzienda(identitaIride.getCognome() + " " + identitaIride.getNome());
					profilo.setRuolo(determinaRuoloAttoreDaVisualizzare(attore));
					profilo.setCodiceFiscale(identitaIride.getCodFiscale());
					profilo.setCognome(identitaIride.getCognome());
					profilo.setNome(identitaIride.getNome());

					if (r.getListaCasiUso() != null) {
						logger.debug(
								"[ProfileParser::creaProfilo] r.getListaCasiUso.size= " + r.getListaCasiUso().size(),
								"");
						profilo.setListaCasiUso(r.getListaCasiUso());
					}

					profiloUtente.addRuoloInLista(profilo);
					logger.debug("[ProfileParser::creaProfilo] OrchLavProfImpl, aggiunto ruolo: " + attore, "");
				}
			}
		}
		return profiloUtente;
	}

	public DatiAggiuntiviImpresa getDescrizioneDaAAEP(DatiAggiuntiviImpresa datiAgg, Identita identita)
			throws Exception {

		// Azienda ris =
		// AdapterAAEP.getInstance().cercaPerCodiceFiscale(identita.getCodFiscale());
		AziendaAAEP ris = AdapterAAEP.getInstance().cercaPerCodiceFiscale(identita.getCodFiscale());
		if (ris != null) {
			// datiAgg.setDenominazioneImpresaNoAAEP(ris.getDenominazione());
			datiAgg.setDenominazioneImpresaNoAAEP(ris.getRagioneSociale());
		}
		return datiAgg;
	}

	private String determinaRuoloAttoreDaVisualizzare(String attore) {

		if (attore.equals(Constants.LEGALE_RAPPRESENTANTE_COMONL)) {
			return Constants.LEGALE_RAPPRESENTANTE_COMONL_DESC;
		} else if (attore.equals(Constants.AMMINISTRATORE_PRODIS)) {
			return Constants.AMMINISTRATORE_PRODIS_DESC;
		} else if (attore.equals(Constants.OPERATORE_PROV_PRODIS)) {
			return Constants.OPERATORE_PROVINCIALE_PRODIS_DESC;
		} else if (attore.equals(Constants.MONITORAGGIO_CSI)) {
			return Constants.MONITORAGGIO_CSI_DESC;
		} else if (attore.equals(Constants.PERS_AUTORIZZATA_COMONL)) {
			return Constants.PERS_AUTORIZZATA_COMONL_DESC;
		} else if (attore.equals(Constants.PERS_AUTORIZZATA_SCUOLA_COMONL)) {
			return Constants.PERS_AUTORIZZATA_SCUOLA_COMONL_DESC;
		} else if (attore.equals(Constants.PERSONA_CARICA_AZIENDALE_COMONL)) {
			return Constants.PERSONA_CARICA_AZIENDALE_COMONL_DESC;
		} else if (attore.equals(Constants.DELEGATO_RESPONSABILE_COMONL)) {
			return Constants.DELEGATO_RESPONSABILE_COMONL_DESC;
		} else if (attore.equals(Constants.CONSULENTE_RESPONSABILE_COMONL)) {
			return Constants.CONSULENTE_RESPONSABILE_COMONL_DESC;
		} else if (attore.equals(Constants.COMONL_CONSULENTE)) {
			return Constants.CONSULENTE_RESPONSABILE_COMONL_DESC;
		} else if (attore.equals(Constants.COMONL_DELEGATO)) {
			return Constants.DELEGATO_RESPONSABILE_COMONL_DESC;
		} else if (attore.equals(Constants.COMONL_PERSONA_AUTORIZZATA)) {
			return Constants.PERS_AUTORIZZATA_COMONL_DESC;
		} else
			return Constants.DATO_NON_PERVENUTO;
	}

	public static boolean isNotVoid(Object objIn) {
		return !isVoid(objIn);
	}

	public static boolean isVoid(Object objIn) {
		try {
			// oggetto nullo
			if (objIn == null) {
				return true;
			}
			// stringa vuota
			else if (objIn instanceof String && objIn.equals("")) {
				return true;
			}
			// Long
			else if (objIn instanceof Long && ((Long) objIn).longValue() == 0) {
				return true;
			}
			// Boolean: se non � nullo,
			else if (objIn instanceof Boolean) {
				try {
					@SuppressWarnings("unused")
					boolean b = ((Boolean) objIn).booleanValue();
					return false;
				} catch (Exception e) {
					return true;
				}
			}
			// collection nulla o vuota
			else if (objIn instanceof Collection && (objIn == null || ((Collection) objIn).size() == 0)) {
				return true;
			} else if (objIn instanceof List && (objIn == null || ((List) objIn).size() == 0)) {
				return true;
			}
			// java.util.date
			else if (objIn instanceof java.util.Date && ((java.util.Date) objIn).getTime() == 0) {
				return true;
			} else if (objIn instanceof java.sql.Date && ((java.sql.Date) objIn).getTime() == 0) {
				return true;
			}
			// gregoriaCal
			else if (objIn instanceof GregorianCalendar && ((GregorianCalendar) objIn).getTime() == null) {
				return true;
			}

			// }
			else {
				return false;
			}
		} catch (Exception e) {
			return true;
		}
	}

}
