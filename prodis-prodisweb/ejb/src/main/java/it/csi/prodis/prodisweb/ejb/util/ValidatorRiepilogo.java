/*-
 * ========================LICENSE_START=================================
 * PRODIS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodisweb.ejb.util;

import java.util.Date;
import java.util.List;

import it.csi.prodis.prodisweb.ejb.business.be.dad.ProspettoDad;
import it.csi.prodis.prodisweb.ejb.entity.ProDDatiProvinciali;
import it.csi.prodis.prodisweb.ejb.entity.ProDProvCompensazioni;
import it.csi.prodis.prodisweb.ejb.entity.ProDRiepilogoNazionale;
import it.csi.prodis.prodisweb.ejb.entity.ProDRiepilogoProvinciale;
import it.csi.prodis.prodisweb.ejb.entity.ProRProspettoProvincia;
import it.csi.prodis.prodisweb.ejb.entity.ProTProvincia;
import it.csi.prodis.prodisweb.lib.dto.ApiError;
import it.csi.prodis.prodisweb.lib.dto.common.Ruolo;
import it.csi.prodis.prodisweb.lib.dto.error.MsgProdis;
import it.csi.prodis.prodisweb.lib.dto.prospetto.DatiAzienda;
import it.csi.prodis.prodisweb.lib.dto.prospetto.Prospetto;

public class ValidatorRiepilogo extends ValidatorUtil {

	ProspettoDad prospettoDad;
	Prospetto prospettoQuadro3;

	public ValidatorRiepilogo(ProspettoDad prospettoDad) {
		super();
		this.prospettoDad = prospettoDad;
	}

	public void validaInBozza(Prospetto prospetto, List<ApiError> apiErrors) {

		controlliMinimiCampiValorizzati(prospetto, apiErrors);

	}

	public void controlliMinimiCampiValorizzati(Prospetto prospetto, List<ApiError> apiErrors) {

		Date dataPrimaAssunzione = prospetto.getDataPrimaAssunzione();

		Date dataSecondaAssunzione = prospetto.getDataSecondaAssunzione();

		Date dataRiferimentoProspetto = prospetto.getDataRiferimentoProspetto();

		String flgNessunaAssunzioneAggiuntiva = prospetto.getFlgNessunaAssunzioneAggiun();

		Long idCategoriaAzienda = prospetto.getCategoriaAzienda() != null
				&& prospetto.getCategoriaAzienda().getId() != null ? prospetto.getCategoriaAzienda().getId()
						: Long.valueOf(ConstantsProdis.COD_CATEGORIA_ASSENTE);

		// CodCategoriaAzienda = C
		// DesCategoriaAzienda = "Da 15 a 35 dipendenti"
		if (idCategoriaAzienda == ConstantsProdis.COD_CATEGORIA_AZIENDA_C
				&& ProdisSrvUtil.isVoid(flgNessunaAssunzioneAggiuntiva)) {
			apiErrors.add(MsgProdis.PRORIEE0007.getError());
		}

		if (flgNessunaAssunzioneAggiuntiva != null && flgNessunaAssunzioneAggiuntiva.equals("N")) {

			// VALIDITA' DATA PRIMA ASSUNZIONE

			if (dataPrimaAssunzione != null) {
				if (!ProdisSrvUtil.controllaData(dataPrimaAssunzione)) {
					apiErrors.add(MsgProdis.PRORIEE0001.getError());
				} else if (ProdisSrvUtil.confrontaData1MaggioreData2(dataPrimaAssunzione, dataRiferimentoProspetto)) {
					apiErrors.add(MsgProdis.PRORIEE0002.getError());
				}
			} else {
				apiErrors.add(MsgProdis.PRORIEE0008.getError());
			}

			// VALIDITA' DATA SECONDA ASSUNZIONE

			if (dataSecondaAssunzione != null) {
				if (!ProdisSrvUtil.controllaData(dataSecondaAssunzione)) {
					apiErrors.add(MsgProdis.PRORIEE0003.getError());
				} else if (dataPrimaAssunzione == null) {
					apiErrors.add(MsgProdis.PRORIEE0004.getError());
				} else if (ProdisSrvUtil.confrontaData1MaggioreData2(dataPrimaAssunzione, dataSecondaAssunzione)) {
					apiErrors.add(MsgProdis.PRORIEE0005.getError());
				} else if (ProdisSrvUtil.confrontaData1MaggioreData2(dataSecondaAssunzione, dataRiferimentoProspetto)) {
					apiErrors.add(MsgProdis.PRORIEE0006.getError());
				}
			} else {
				// DO NOTHING - IN QUESTO CASO LA DATA SECONDA ASSUNZIONE E' FACOLTATIVA
			}

			if (idCategoriaAzienda.equals(Long.valueOf(ConstantsProdis.COD_CATEGORIA_AZIENDA_A))
					&& dataPrimaAssunzione != null) {
				apiErrors.add(MsgProdis.PRORIEE0011.getError());
			}

		} else if (flgNessunaAssunzioneAggiuntiva != null && flgNessunaAssunzioneAggiuntiva.equals("S")) {

			if (dataPrimaAssunzione != null) {
				apiErrors.add(MsgProdis.PRORIEE0009.getError());
			}

			if (dataSecondaAssunzione != null) {
				apiErrors.add(MsgProdis.PRORIEE0010.getError());
			}

		}

	}

	public void validaConfermaEProsegui(Prospetto prospetto, Ruolo ruolo, List<ApiError> apiErrors) {
		controlliMinimiCampiValorizzati(prospetto, apiErrors);
		if (apiErrors == null || apiErrors.size() == 0) {
			controlliAggiuntivi(prospetto, ruolo, apiErrors);

			if (apiErrors == null || apiErrors.size() == 0) {
				controlliDiCongruenza(prospetto, apiErrors);

				if (apiErrors == null || apiErrors.size() == 0) {
					checkBloccantePerScoperture(prospetto, apiErrors);
				}
			}
		}
	}

	public void checkBloccantePerScoperture(Prospetto prospetto, List<ApiError> apiErrors) {
		DatiAzienda datiAzienda = prospetto.getDatiAzienda() != null ? prospetto.getDatiAzienda() : null;
		int tipoDichiarante = datiAzienda.getDichiarante() != null ? datiAzienda.getDichiarante().getId().intValue()
				: 0;
		ProDRiepilogoNazionale riepilogoNazionale = prospettoDad.caricaRiepilogoNazionalePerPdf(prospetto.getId());
		List<ProDRiepilogoProvinciale> riepilogoProvinciale = prospettoDad
				.caricaElencoRiepilogoProvincialeByIdProspetto(prospetto.getId());

		boolean isOk = true;

		long totaleScopertureCategorieProtetteProvinciali = 0;
		long totaleScopertureDisabiliProvinciali = 0;

		long numScopertureDisabiliNazionali = 0;
		long numScopertureCategorieProtetteNazionali = 0;

		for (ProDRiepilogoProvinciale prov : riepilogoProvinciale) {
			totaleScopertureCategorieProtetteProvinciali += prov.getNumScopertureCatProt().longValue();
			totaleScopertureDisabiliProvinciali += prov.getNumScopertureDisabili().longValue();
		}
		numScopertureCategorieProtetteNazionali = riepilogoNazionale.getNumScopertCategorieProtette().longValue();
		numScopertureDisabiliNazionali = riepilogoNazionale.getNumScopertDisabili().longValue();

		if (totaleScopertureDisabiliProvinciali != numScopertureDisabiliNazionali
				|| totaleScopertureCategorieProtetteProvinciali != numScopertureCategorieProtetteNazionali) {

			isOk = false;
		}
		if (!isOk) {
			if (ConstantsProdis.COD_DICHIARANTE_C.intValue() == tipoDichiarante) {
//				apiErrors.add(MsgProdis.PRORIEE0023.getError());
			} else {
				apiErrors.add(MsgProdis.PRORIEE0024.getError());
			}
		}
	}

	public void controlliDiCongruenza(Prospetto prospetto, List<ApiError> apiErrors) {
		DatiAzienda datiAzienda = prospetto.getDatiAzienda() != null ? prospetto.getDatiAzienda() : null;
		int tipoDichiarante = datiAzienda.getDichiarante() != null ? datiAzienda.getDichiarante().getId().intValue()
				: 0;

		boolean esisteComConCodFisc = false;
		boolean esisteCompConProvFuoriRegione = false;
		boolean esisteCompDiEccedenzaSuProvinciaConEsonero = false;
		boolean esisteCompDiEccedenzaSuProvinciaConEsoneroAutocert = false;

		List<ProRProspettoProvincia> elencoProspettoProvincia = prospettoDad
				.caricaProspettoProvinciaPerPdf(prospetto.getId());
		if (elencoProspettoProvincia != null && elencoProspettoProvincia.size() > 0) {

			List<ProDProvCompensazioni> compensazioniDaProspetto = null;

			for (ProRProspettoProvincia prospettoProvincia : elencoProspettoProvincia) {
				compensazioniDaProspetto = prospettoDad
						.caricaCompensazioniPerPdf(prospettoProvincia.getIdProspettoProv());
			}

			if (compensazioniDaProspetto != null && compensazioniDaProspetto.size() > 0) {
				for (ProDProvCompensazioni comp : compensazioniDaProspetto) {
					if (comp.getCfAziendaAppartenAlGruppo() != null
							&& !"".equalsIgnoreCase(comp.getCfAziendaAppartenAlGruppo())) {
						esisteComConCodFisc = true;
					}

					ProTProvincia provincia = prospettoDad.getProvinciaById(comp.getProTProvincia().getIdTProvincia());
					if (provincia != null) {
						Long idRegione = provincia.getProTRegione().getId();
						Long idRegionePerCuiCompensare = null;
						Long idProvinciaDaElencoProspettoProvincia = null;
						for (ProRProspettoProvincia datiProv : elencoProspettoProvincia) {
							if (datiProv.getIdProspettoProv() == comp.getIdProspettoProv().longValue()) {
								idProvinciaDaElencoProspettoProvincia = datiProv.getProTProvincia().getIdTProvincia();
								break;
							}

						}
						if (idProvinciaDaElencoProspettoProvincia != null) {
							ProTProvincia provinciaCompensazione = prospettoDad
									.getProvinciaById(idProvinciaDaElencoProspettoProvincia);
							if (provinciaCompensazione != null) {
								idRegionePerCuiCompensare = provinciaCompensazione.getProTRegione().getId();
							}
						}
						if (!idRegione.equals(idRegionePerCuiCompensare)) {
							esisteCompConProvFuoriRegione = true;
						}
					}
					if (comp.getCategoriaCompensazione().equals(ConstantsProdis.CATEGORIA_COMPENSAZIONE_E)) {

						if (comp.getCategoriaSoggetto().equals(ConstantsProdis.CATEGORIA_SOGGETTO_D)) {

							for (ProRProspettoProvincia datiProv : elencoProspettoProvincia) {

								if (datiProv.getIdProspettoProv() == comp.getIdProspettoProv().longValue()) {

									ProDDatiProvinciali datiProvinciali = prospettoDad
											.caricaDatiProvincialiPerPdf(datiProv.getIdProspettoProv());

									if (datiProvinciali.getProDProvEsonero() != null
											&& datiProvinciali.getProDProvEsonero().getNumLavoratoriEsonero() != null
											&& datiProvinciali.getProDProvEsonero().getNumLavoratoriEsonero()
													.intValue() > 0) {
										esisteCompDiEccedenzaSuProvinciaConEsonero = true;
										break;
									}

									if (datiProvinciali.getProDProvEsoneroAutocert() != null
											&& datiProvinciali.getProDProvEsoneroAutocert()
													.getnLavEsoneroAutocert() != null
											&& datiProvinciali.getProDProvEsoneroAutocert().getnLavEsoneroAutocert()
													.intValue() > 0) {
										esisteCompDiEccedenzaSuProvinciaConEsoneroAutocert = true;
										break;
									}
								}
							}
						}
					}
				}
				if (compensazioniDaProspetto.size() > 0) {
					if (esisteComConCodFisc) {
						if (tipoDichiarante != ConstantsProdis.COD_DICHIARANTE_D) {
							apiErrors.add(MsgProdis.PRORIEE0020.getError());
						}
					}
				}
				if (tipoDichiarante == ConstantsProdis.COD_DICHIARANTE_C) {
					if (esisteCompConProvFuoriRegione) {
						apiErrors.add(MsgProdis.PRORIEE0021.getError());
					}

				}
				if (esisteCompDiEccedenzaSuProvinciaConEsonero || esisteCompDiEccedenzaSuProvinciaConEsoneroAutocert) {
					apiErrors.add(MsgProdis.PRORIEE0022.getError());
				}
				if (esisteCompDiEccedenzaSuProvinciaConEsoneroAutocert) {
					apiErrors.add(MsgProdis.PRORIEE0022.getError());
				}
			}
		}

	}

	public void controlliAggiuntivi(Prospetto prospetto, Ruolo ruolo, List<ApiError> apiErrors) {
		String codiceFiscaleSoggetto = prospetto.getCfStudioProfessionale();
		String eMailSoggetto = prospetto.getEmailSoggettoComunicazione();
		Date dataTmbroPostale = prospetto.getDataTimbroPostale();
		String eMailNotifica = prospetto.getEmailNotifica();
		// Long codiceAltroSoggetto = prospetto.getSoggetti() != null &&
		// prospetto.getSoggetti().getId() != null ? prospetto.getSoggetti().getId() :
		// null;

		if (ruolo != null && ruolo.isAmministratore()) {
			if (ProdisSrvUtil.isNotVoid(codiceFiscaleSoggetto) && 
					(!ProdisSrvUtil.controllaCF(codiceFiscaleSoggetto) && !ProdisSrvUtil.controllaPartitaIVA(codiceFiscaleSoggetto))) {
				apiErrors.add(MsgProdis.PRORIEE0014.getError());
			}

			if (eMailSoggetto == null || "".equalsIgnoreCase(eMailSoggetto)) {
				apiErrors.add(MsgProdis.PRORIEE0015.getError());
			} else {
				String regexMail = prospettoDad.getParametroByNome(ConstantsProdis.PARAMETRO_REGEX_EMAIL);
				if (!ProdisSrvUtil.checkValore(eMailSoggetto, regexMail)) {
					apiErrors.add(MsgProdis.PRORIEE0016.getError());
				}
			}
			if (dataTmbroPostale == null) {
				apiErrors.add(MsgProdis.PRORIEE0017.getError());
			}
		}
		if (eMailNotifica == null || "".equalsIgnoreCase(eMailNotifica)) {
			apiErrors.add(MsgProdis.PRORIEE0018.getError());
		} else {
			String regexMail = prospettoDad.getParametroByNome(ConstantsProdis.PARAMETRO_REGEX_EMAIL);
			if (!ProdisSrvUtil.checkValore(eMailNotifica, regexMail)) {
				apiErrors.add(MsgProdis.PRORIEE0019.getError());
			}
		}

	}
}
