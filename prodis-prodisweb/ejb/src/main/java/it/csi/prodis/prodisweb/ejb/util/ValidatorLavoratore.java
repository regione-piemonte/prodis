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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import it.csi.prodis.prodisweb.ejb.business.be.dad.LavoratoriInForzaDad;
import it.csi.prodis.prodisweb.ejb.entity.ProTComune;
import it.csi.prodis.prodisweb.ejb.entity.ProTStatiEsteri;
import it.csi.prodis.prodisweb.ejb.mapper.ProdisMappers;
import it.csi.prodis.prodisweb.lib.dto.ApiError;
import it.csi.prodis.prodisweb.lib.dto.common.DecodificaGenerica;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.AssunzioneProtetta;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Contratti;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Istat2001livello5;
import it.csi.prodis.prodisweb.lib.dto.error.MsgProdis;
import it.csi.prodis.prodisweb.lib.dto.prospetto.LavoratoriInForza;
import it.csi.prodis.prodisweb.lib.dto.prospetto.Prospetto;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProspettoProvincia;
import it.csi.prodis.prodisweb.lib.util.ProdisUtility;

public class ValidatorLavoratore extends ValidatorUtil {

	private LavoratoriInForzaDad lavoratoriInForzaDad;
	private Prospetto prospettoQuadro1 = new Prospetto();
	private List<ProspettoProvincia> prospettoProvincias = null;
	private List<LavoratoriInForza> listaLavoratoriPerProvincia = null;
	private boolean prefetch = false;

	public ValidatorLavoratore(LavoratoriInForzaDad lavoratoriInForzaDad) {
		super();
		this.lavoratoriInForzaDad = lavoratoriInForzaDad;
	}
	
	public ValidatorLavoratore(LavoratoriInForzaDad lavoratoriInForzaDad, Prospetto prospetto,
			List<ProspettoProvincia> prospettoProvincias, List<LavoratoriInForza> listaLavoratoriPerProvincia) {
		super();
		this.lavoratoriInForzaDad = lavoratoriInForzaDad;
		this.prospettoQuadro1 = prospetto;
		this.prospettoProvincias = prospettoProvincias;
		this.listaLavoratoriPerProvincia = listaLavoratoriPerProvincia;
		this.prefetch=true;
	}

	public boolean checkComuneValorizzato(String codice, String descrizione) {

		if (codice == null || "".equalsIgnoreCase(codice) || descrizione == null || "".equalsIgnoreCase(descrizione)) {
			return false;
		}
		return true;
	}

	private Long recuperaIdComuneDaDecodifica(String codice, String descrizione) {

		Long id = null;

		List<DecodificaGenerica> decode = lavoratoriInForzaDad.getComuneValido(codice, descrizione);
		if (decode != null && decode.size() == 1) {
			id = decode.get(0).getIdDecodifica();
		}

		return id;
	}

	private Long recuperaIdStatoEsteroDaDecodifica(String codice, String descrizione) {

		Long id = null;

		List<DecodificaGenerica> decode = lavoratoriInForzaDad.getStatoEsteroValido(codice, descrizione);
		if (decode != null && decode.size() == 1) {
			id = decode.get(0).getIdDecodifica();
		}

		return id;
	}

	private Long recuperaIdQualificaIstatDaDecodifica(String codice, String descrizione) {

		Long id = null;

		List<DecodificaGenerica> decode = lavoratoriInForzaDad.getIstatValidi(codice, descrizione);
		if (decode != null && decode.size() == 1) {
			id = decode.get(0).getIdDecodifica();
		}

		return id;
	}

	public void validaLavoratore(LavoratoriInForza lavoratoriInForza, List<ApiError> apiErrors) {

		/*
		 * a questo punto eseguendo updoal del file gli arriva
		 * lavoratoriInForza.getIdProspettoProv() e' null quindi si scassa. Bisogna
		 * eventualmente passargli anche l'id del prospetto come seconda scelta
		 */

		ProspettoProvincia prospettoProvincia = getProspettoProvincia(lavoratoriInForza.getIdProspettoProv());
		
		if (!prefetch) {
			prospettoQuadro1 = lavoratoriInForzaDad.getProspetto(prospettoProvincia.getIdProspetto());
		} 
		
		checkDatiAnagraficiObbligatori(lavoratoriInForza, apiErrors);

		if (apiErrors == null || apiErrors.size() == 0) {

			controlliDatiNascita(lavoratoriInForza, apiErrors);

			controlliCheckCF(lavoratoriInForza, apiErrors);

			controlliDatiRapportoDiLavoro(lavoratoriInForza, apiErrors);

			controlliQualificaIstat(lavoratoriInForza, apiErrors);

			controlliOrarioSettimanale(lavoratoriInForza, apiErrors);

			controlliCategoriaAssunzione(lavoratoriInForza, apiErrors);

			controlliAdeguamenti2016(lavoratoriInForza, apiErrors);

			controllaEtaLavoratoreConMessaggioWarning(lavoratoriInForza, apiErrors);

			controlliCategoriaSoggetto(lavoratoriInForza, apiErrors);

			if (lavoratoriInForza.getCategoriaSoggetto() != null && lavoratoriInForza.getCategoriaSoggetto().equals("C")
					&& lavoratoriInForza.getPercentualeDisabilita() == null) {
				return;
			} else if (lavoratoriInForza.getCategoriaSoggetto() != null
					&& lavoratoriInForza.getCategoriaSoggetto().equals("C")
					&& lavoratoriInForza.getPercentualeDisabilita() != null) {
				apiErrors.add(MsgProdis.PROLAVE0037.getError());
				return;
			}

			if ((apiErrors == null || apiErrors.size() == 0) && !prefetch) {
				controlliCoerenzaDatiGiaInseriti(lavoratoriInForza, apiErrors, prospettoQuadro1.getId());
			}
		}
	}
	
	private ProspettoProvincia getProspettoProvincia (Long idProspettoProv) {
		if (prefetch) {
			for (ProspettoProvincia pp : prospettoProvincias) {
				if (pp.getId().longValue()==idProspettoProv) {
					return pp;
				}
			}
			return null;
		} else {
			return lavoratoriInForzaDad.getProspettoProvinciaById(idProspettoProv);
		}
	}

	private void controlliCoerenzaDatiGiaInseriti(LavoratoriInForza lavoratoriInForza, List<ApiError> apiErrors, Long idProspetto) {
		boolean lavoratoreGiaPresente = false;

		if (lavoratoriInForza.getId() == null || lavoratoriInForza.getId() == 0) {
			
			if (!prefetch) {
				listaLavoratoriPerProvincia = ProdisMappers.LAVORATORI_IN_FORZA.toModels(lavoratoriInForzaDad
						.getListaLavoratoriByIdProspettoProv(lavoratoriInForza.getIdProspettoProv()));
			}
			
			if (listaLavoratoriPerProvincia != null && listaLavoratoriPerProvincia.size() > 0) {

				for (LavoratoriInForza lav : listaLavoratoriPerProvincia) {
					if (lav.getCodiceFiscale().equalsIgnoreCase(lavoratoriInForza.getCodiceFiscale())) {
						lavoratoreGiaPresente = true;
						String error = "E' gia' stato inserito un lavoratore con codice fiscale "
								+ lav.getCodiceFiscale() + ".";
						apiErrors.add(new ApiError("PRO-LAV-E-00042", error));
						break;
					}
				}
			}
			if (!lavoratoreGiaPresente) {
				
				if (!prefetch) {
					prospettoProvincias = ProdisMappers.PROSPETTO_PROVINCIA.toModels(
							lavoratoriInForzaDad.getProspettoProvinciaByIdProspetto(idProspetto)
					);
				}
				
				for (ProspettoProvincia prov : prospettoProvincias) {
					
					if (!prefetch) {
						listaLavoratoriPerProvincia = ProdisMappers.LAVORATORI_IN_FORZA.toModels(
								lavoratoriInForzaDad.getListaLavoratoriByIdProspettoProv(prov.getId().longValue())
						);
					}
					
					if (listaLavoratoriPerProvincia != null) {
						for (LavoratoriInForza lav : listaLavoratoriPerProvincia) {
							if (lav.getCodiceFiscale().equalsIgnoreCase(lavoratoriInForza.getCodiceFiscale())) {
								String error = "E' gia' stato inserito un lavoratore con codice fiscale "
										+ lav.getCodiceFiscale() + " in un'altra provincia.";
								apiErrors.add(new ApiError("PRO-LAV-E-00043", error));
								break;
							}
						}
					}
				}
			}
		}
	}

	private void controlliAdeguamenti2016(LavoratoriInForza lavoratoriInForza, List<ApiError> apiErrors) {
		if (lavoratoriInForza.getAssunzioneProtetta() != null
				&& lavoratoriInForza.getAssunzioneProtetta().getId() != null) {
			AssunzioneProtetta tipoAssunzioneProtetta = lavoratoriInForza.getAssunzioneProtetta();
			Date dataInizioRapporto = lavoratoriInForza.getDataInizioRapporto();
			Date dataFineRapporto = lavoratoriInForza.getDataFineRapporto();
			if (tipoAssunzioneProtetta != null && tipoAssunzioneProtetta.getId() != null && dataInizioRapporto != null
					&& dataFineRapporto != null) {
				if (ConstantsProdis.TIPO_ASSUNZIONE_PROTETTA_CODICE_M
						.equalsIgnoreCase(tipoAssunzioneProtetta.getCodAssunzioneProtetta())
						&& !(dateInizioAndFineCoerentiPerTipoAssunzioneProtetta(dataInizioRapporto,
								dataFineRapporto))) {
					apiErrors.add(MsgProdis.PROLAVE0040.getError());
				}
			}
			if (tipoAssunzioneProtetta != null && ProdisSrvUtil.isNotVoid(lavoratoriInForza.getCategoriaSoggetto())) {
				if (isAssunzioneProtettaSomministratiOrConvenzioni(tipoAssunzioneProtetta.getCodAssunzioneProtetta())
						&& !ConstantsProdis.CATEGORIA_SOGGETTO_D.equals(lavoratoriInForza.getCategoriaSoggetto())) {

				}
			}

		}
	}

	private void controlliCategoriaAssunzione(LavoratoriInForza lavoratoriInForza, List<ApiError> apiErrors) {
		if (lavoratoriInForza.getCategoriaAssunzione() == null
				|| "".equals(lavoratoriInForza.getCategoriaAssunzione())) {
			apiErrors.add(MsgProdis.PROLAVE0039.getError());
		}
	}

	private void controlliCategoriaSoggetto(LavoratoriInForza lavoratoriInForza, List<ApiError> apiErrors) {

		if (lavoratoriInForza.getCategoriaSoggetto() == null || "".equals(lavoratoriInForza.getCategoriaSoggetto())) {
			apiErrors.add(MsgProdis.PROLAVE0034.getError());
		} else {

			if (lavoratoriInForza.getAssunzioneProtetta() != null
					&& lavoratoriInForza.getAssunzioneProtetta().getDescAssunzioneProtetta() != null
					&& (lavoratoriInForza.getAssunzioneProtetta().getDescAssunzioneProtetta()
							.contains("Convenzione art. 14")
							|| lavoratoriInForza.getAssunzioneProtetta().getDescAssunzioneProtetta()
									.contains("Convenzione art. 12")
							|| lavoratoriInForza.getAssunzioneProtetta().getDescAssunzioneProtetta()
									.contains("Somministrazione"))
					&& lavoratoriInForza.getCategoriaSoggetto().equals("C")) {
				apiErrors.add(MsgProdis.PROLAVE0049.getError());
			} else if (lavoratoriInForza.getCategoriaSoggetto()
					.equalsIgnoreCase(ConstantsProdis.CATEGORIA_SOGGETTO_D)) {
				if (lavoratoriInForza.getPercentualeDisabilita() == null) {
					apiErrors.add(MsgProdis.PROLAVE0036.getError());
				}
			} else {
				if (lavoratoriInForza.getCategoriaSoggetto().equals("C")
						&& lavoratoriInForza.getPercentualeDisabilita() == null) {
					// NON DEVE RESTITUIRE NESSUN ERRORE
				} else if (lavoratoriInForza.getPercentualeDisabilita() == null) {
					apiErrors.add(MsgProdis.PROLAVE0035.getError());
				} else {

					if (lavoratoriInForza.getPercentualeDisabilita().compareTo(new BigDecimal(100)) == 1) {
						apiErrors.add(MsgProdis.PROLAVE0037.getError());
					} else if (lavoratoriInForza.getPercentualeDisabilita().compareTo(new BigDecimal(0)) == -1) {
						apiErrors.add(MsgProdis.PROLAVE0038.getError());
					}
				}
			}
		}

	}

	private void controlliOrarioSettimanale(LavoratoriInForza lavoratoriInForza, List<ApiError> apiErrors) {
		if (lavoratoriInForza.getOrarioSettContrattualeMin() == null) {
			apiErrors.add(MsgProdis.PROLAVE0030.getError());
		}
		// else {
		// if(!lavoratoriInForza.getOrarioSettContrattualeMin().toString().matches(ConstantsProdis.DEFAULT_HHMM_MENSILE_FORMAT)){
		// apiErrors.add(MsgProdis.PROLAVE0031.getError());
		// }
		// }
		if (lavoratoriInForza.getOrarioSettPartTimeMin() == null) {
			apiErrors.add(MsgProdis.PROLAVE0032.getError());
		}
		// else {
		// if(!lavoratoriInForza.getOrarioSettPartTimeMin().toString().matches(ConstantsProdis.DEFAULT_HHMM_MENSILE_FORMAT)){
		// apiErrors.add(MsgProdis.PROLAVE0033.getError());
		// }
		// }

	}

	private void controlliQualificaIstat(LavoratoriInForza lavoratoriInForza, List<ApiError> apiErrors) {

		if (lavoratoriInForza.getIstat2001livello5() != null
				&& ProdisSrvUtil.isNotVoid(lavoratoriInForza.getIstat2001livello5().getCodIstat2001livello5Min())
				&& ProdisSrvUtil.isNotVoid(lavoratoriInForza.getIstat2001livello5().getDsComIstat2001livello5())) {

			if (lavoratoriInForza.getIstat2001livello5().getId() == null) {
				Long id = recuperaIdQualificaIstatDaDecodifica(
						lavoratoriInForza.getIstat2001livello5().getCodIstat2001livello5Min(),
						lavoratoriInForza.getIstat2001livello5().getDsComIstat2001livello5());
				if (id != null) {
					lavoratoriInForza.getIstat2001livello5().setId(id);
				} else {
					apiErrors.add(MsgProdis.PROLAVE0048.getError());
				}
			}

			List<DecodificaGenerica> elencoDecodifica = lavoratoriInForzaDad.getIstat(
					lavoratoriInForza.getIstat2001livello5().getCodIstat2001livello5Min(),
					lavoratoriInForza.getIstat2001livello5().getDsComIstat2001livello5());
			if (elencoDecodifica == null || elencoDecodifica.size() == 0) {
				apiErrors.add(MsgProdis.PROLAVE0027.getError());
			} else {

				ArrayList<Istat2001livello5> elencoIstat = new ArrayList<Istat2001livello5>();
				for (DecodificaGenerica deco : elencoDecodifica) {
					if (deco.getCodDecodifica()
							.equalsIgnoreCase(lavoratoriInForza.getIstat2001livello5().getCodIstat2001livello5Min())) {
						Istat2001livello5 istat = lavoratoriInForzaDad.getIstatById(deco.getIdDecodifica());
						elencoIstat.add(istat);
					}
				}
				if (elencoIstat == null || elencoIstat.size() == 0) {
					apiErrors.add(MsgProdis.PROLAVE0027.getError());
				}
				if (elencoIstat != null && elencoIstat.size() > 1) {
					apiErrors.add(MsgProdis.PROLAVE0028.getError());
				}
				if (elencoIstat != null && elencoIstat.size() == 1) {
					Istat2001livello5 trovato = elencoIstat.get(0);
					if (!ProdisSrvUtil.isDecodificaValida(new Date(), trovato.getDtInizio(), trovato.getDtFine())) {
						apiErrors.add(MsgProdis.PROLAVE0029.getError());
					}
				}
			}
		} else {
			apiErrors.add(MsgProdis.PROLAVE0026.getError());
		}

	}

	private void controlliDatiRapportoDiLavoro(LavoratoriInForza lavoratoriInForza, List<ApiError> apiErrors) {
		if (lavoratoriInForza.getDataInizioRapporto() == null) {
			apiErrors.add(MsgProdis.PROLAVE0014.getError());
		} else {
			if (!ProdisSrvUtil.controllaData(lavoratoriInForza.getDataInizioRapporto())) {
				apiErrors.add(MsgProdis.PROLAVE0015.getError());
			}
			if (prospettoQuadro1.getDataRiferimentoProspetto() != null) {
				if (!ValidatorUtil.checkCoerenzaDtRifProspettoAndDtInizioRapporto(
						prospettoQuadro1.getDataRiferimentoProspetto(), lavoratoriInForza.getDataInizioRapporto())) {
					apiErrors.add(MsgProdis.PROLAVE0016.getError());
				}
			}
			if (!ValidatorUtil.checkCoerenzaDtNascitaAndDtInizioRapporto(lavoratoriInForza.getDataInizioRapporto(),
					lavoratoriInForza.getDataNascita())) {
				apiErrors.add(MsgProdis.PROLAVE0017.getError());
			}

			String codiceMinisterialeAssunzioneProtetta = "";
			Contratti tipologiaContrattuale = null;
			String flgFormaContrattualeAssunzioneProtetta = null;
			String flgFormaContrattualeTipologiaContratto = null;

			boolean isTipoAssunzioneProtettaValorizzata = true;
			if (lavoratoriInForza.getAssunzioneProtetta() == null) {
				apiErrors.add(MsgProdis.PROLAVE0018.getError());
				isTipoAssunzioneProtettaValorizzata = false;
			}

			boolean isTipologiaContrattualeValorizzata = lavoratoriInForza.getContratti() != null
					&& lavoratoriInForza.getContratti().getId() != null ? true : false;

			if (isTipoAssunzioneProtettaValorizzata) {
				AssunzioneProtetta assunzioneProtetta = lavoratoriInForzaDad
						.getAssunzioneProtettaById(lavoratoriInForza.getAssunzioneProtetta().getId());
				codiceMinisterialeAssunzioneProtetta = assunzioneProtetta.getCodAssunzioneProtetta();
				flgFormaContrattualeAssunzioneProtetta = determinaFlgFormaContrattualeByAssunzioneProtetta(
						codiceMinisterialeAssunzioneProtetta);
			}

			if (isTipologiaContrattualeValorizzata) {
				tipologiaContrattuale = lavoratoriInForzaDad
						.getTipologiaContrattualeById(lavoratoriInForza.getContratti().getId());
				if (ValidatorUtil.getTipologiaContrattualeValida(tipologiaContrattuale,
						lavoratoriInForza.getContratti().getId())) {
					flgFormaContrattualeTipologiaContratto = tipologiaContrattuale.getFlgForma();
					isTipologiaContrattualeValorizzata = true;
				} else {
					isTipologiaContrattualeValorizzata = false;
					apiErrors.add(MsgProdis.PROLAVE0019.getError());
				}

			}

			if (isTipoAssunzioneProtettaValorizzata && isTipologiaContrattualeValorizzata
					&& isAssunzioneProtettaSomministrati(codiceMinisterialeAssunzioneProtetta)) {

				apiErrors.add(MsgProdis.PROLAVE0020.getError());
			} else {

				// Se siamo in caso di somministrati:
				if (isAssunzioneProtettaSomministrati(codiceMinisterialeAssunzioneProtetta)) {

					String errore = ValidatorUtil.controlloObbligatorietaAndCoerenzaFlgForma(lavoratoriInForza,
							flgFormaContrattualeAssunzioneProtetta);
					if (errore != null) {
						apiErrors.add(new ApiError("PRO-LAV-E-00021", errore));
					}
					errore = ValidatorUtil.controlliDataFineRaporto(lavoratoriInForza,
							flgFormaContrattualeAssunzioneProtetta);
					if (errore != null) {
						apiErrors.add(new ApiError("PRO-LAV-E-00022", errore));
					}

				} else {
					// SE TIPO ASSUNZIONE PROTETTA NON (M,N) --> DEVE essere valorizzata la
					// Tipologia Contrattuale
					if (lavoratoriInForza.getContratti() == null || lavoratoriInForza.getContratti().getId() == null) {
						isTipologiaContrattualeValorizzata = false;
						apiErrors.add(MsgProdis.PROLAVE0023.getError());
					}
					String errore = ValidatorUtil.controlliDataFineRaporto(lavoratoriInForza,
							flgFormaContrattualeTipologiaContratto);
					if (errore != null) {
						apiErrors.add(new ApiError("PRO-LAV-E-00022", errore));
					}
				}
			}
			// ------------ VERIFICA SU CONGRUENZA DATA PROSPETTO / DATA FINE RAPPORTO SE
			// VALORIZZATA
			if (lavoratoriInForza.getDataFineRapporto() != null) {
				if (!ValidatorUtil.checkCoerenzaDtRifProspettoAndDtFineRapporto(prospettoQuadro1, lavoratoriInForza)) {
					apiErrors.add(MsgProdis.PROLAVE0024.getError());
				}
			}

			// ------------ 4-VERIFICA SU CONGRUENZA CONTRATTO TIROCINIO - ok
			if (isTipoAssunzioneProtettaValorizzata && isTipologiaContrattualeValorizzata
					&& ProdisSrvUtil.isNotVoid(codiceMinisterialeAssunzioneProtetta) && tipologiaContrattuale != null) {

				String elencoTirocini = lavoratoriInForzaDad
						.getParametroByNome(ConstantsProdis.PARAMETRO_ELENCO_CODICI_CONTRATTI_TIROCINIO);
				ArrayList<String> listaTirocini = null;
				StringTokenizer tokenizer = new StringTokenizer(elencoTirocini, ",");
				while (tokenizer.hasMoreTokens()) {
					if (listaTirocini == null) {
						listaTirocini = new ArrayList<String>();
					}
					listaTirocini.add(tokenizer.nextToken());
				}
				if (!ValidatorUtil.checkCongruenzaAssunzioneProtettaContrattoTirocinio(
						codiceMinisterialeAssunzioneProtetta, lavoratoriInForza, listaTirocini)) {
					apiErrors.add(MsgProdis.PROLAVE0025.getError());
				}
			}
		}
	}

	private void controlliDatiNascita(LavoratoriInForza lavoratoriInForza, List<ApiError> apiErrors) {
		if (lavoratoriInForza.getDataNascita() != null
				&& !ProdisSrvUtil.controllaData(lavoratoriInForza.getDataNascita())) {
			apiErrors.add(MsgProdis.PROLAVE0009.getError());
		}

		if (lavoratoriInForza.getDataInizioRapporto() != null && lavoratoriInForza.getDataNascita() != null
				&& !ProdisSrvUtil.removeTime(lavoratoriInForza.getDataInizioRapporto())
						.after(ProdisSrvUtil.removeTime(lavoratoriInForza.getDataNascita()))) {
			apiErrors.add(MsgProdis.PROLAVE0017.getError());
		}

		if (lavoratoriInForza.getDataNascita() != null && lavoratoriInForza.getDataInizioRapporto() != null
				&& getDiffYears(ProdisSrvUtil.removeTime(lavoratoriInForza.getDataNascita()),
						ProdisSrvUtil.removeTime(lavoratoriInForza.getDataInizioRapporto())) < 16) {
			apiErrors.add(MsgProdis.PROLAVW0044.getError());
		}

		if (lavoratoriInForza.getComune() != null && lavoratoriInForza.getStatiEsteri() != null
				&& lavoratoriInForza.getComune().getCodComuneMin() != null
				&& lavoratoriInForza.getComune().getDsProTComune() != null
				&& lavoratoriInForza.getStatiEsteri().getCodNazioneMin() != null
				&& lavoratoriInForza.getStatiEsteri().getDsStatiEsteri() != null) {
			apiErrors.add(MsgProdis.PROLAVE0010.getError());
		} else {
			// è valorizzato il comune
			if (lavoratoriInForza.getComune() != null && lavoratoriInForza.getComune().getCodComuneMin() != null
					&& !"".equals(lavoratoriInForza.getComune().getCodComuneMin())
					&& lavoratoriInForza.getComune().getDsProTComune() != null
					&& !"".equals(lavoratoriInForza.getComune().getDsProTComune())) {

				if (lavoratoriInForza.getComune().getId() == null) {
					Long id = recuperaIdComuneDaDecodifica(lavoratoriInForza.getComune().getCodComuneMin(),
							lavoratoriInForza.getComune().getDsProTComune());
					if (id != null) {
						lavoratoriInForza.getComune().setId(id);
					} else {
						apiErrors.add(MsgProdis.PROLAVE0046.getError());
					}
				}

				List<ProTComune> listaComuni = lavoratoriInForzaDad.getComuni(null,
						lavoratoriInForza.getComune().getCodComuneMin(),
						lavoratoriInForza.getComune().getDsProTComune(), lavoratoriInForza.getDataNascita());
				if (listaComuni == null || listaComuni.size() == 0) {
					apiErrors.add(MsgProdis.PROLAVE0011.getError());
				}

			} else {

				if (lavoratoriInForza.getStatiEsteri().getId() == null) {
					Long id = recuperaIdStatoEsteroDaDecodifica(lavoratoriInForza.getStatiEsteri().getCodNazioneMin(),
							lavoratoriInForza.getStatiEsteri().getDsStatiEsteri());
					if (id != null) {
						lavoratoriInForza.getStatiEsteri().setId(id);
					} else {
						apiErrors.add(MsgProdis.PROLAVE0047.getError());
					}
				}

				List<ProTStatiEsteri> listaStati = lavoratoriInForzaDad.getStati(
						lavoratoriInForza.getStatiEsteri().getCodNazioneMin(),
						lavoratoriInForza.getStatiEsteri().getDsStatiEsteri(), lavoratoriInForza.getDataNascita());
				if (listaStati == null || listaStati.size() == 0) {
					apiErrors.add(MsgProdis.PROLAVE0013.getError());
				}
			}
		}
	}

	private void checkDatiAnagraficiObbligatori(LavoratoriInForza lavoratoriInForza, List<ApiError> apiErrors) {
		if (lavoratoriInForza.getCodiceFiscale() == null || "".equals(lavoratoriInForza.getCodiceFiscale())) {
			apiErrors.add(MsgProdis.PROLAVE0001.getError());
		}
		if (lavoratoriInForza.getCognome() == null || "".equals(lavoratoriInForza.getCognome())) {
			apiErrors.add(MsgProdis.PROLAVE0004.getError());
		}
		if (lavoratoriInForza.getNome() == null || "".equals(lavoratoriInForza.getNome())) {
			apiErrors.add(MsgProdis.PROLAVE0005.getError());
		}
		if (lavoratoriInForza.getSesso() == null || "".equals(lavoratoriInForza.getSesso())) {
			apiErrors.add(MsgProdis.PROLAVE0006.getError());
		}
		if (lavoratoriInForza.getDataNascita() == null) {
			apiErrors.add(MsgProdis.PROLAVE0008.getError());
		}

		if ((lavoratoriInForza.getComune() == null
				|| (lavoratoriInForza.getComune().getCodComuneMin() == null
						&& lavoratoriInForza.getComune().getDsProTComune() == null)
				|| ("".equals(lavoratoriInForza.getComune().getCodComuneMin())
						&& "".equals(lavoratoriInForza.getComune().getDsProTComune())))
				&& (lavoratoriInForza.getStatiEsteri() == null
						|| (lavoratoriInForza.getStatiEsteri().getCodNazioneMin() == null
								&& lavoratoriInForza.getStatiEsteri().getDsStatiEsteri() == null)
						|| ("".equals(lavoratoriInForza.getStatiEsteri().getCodNazioneMin())
								&& "".equals(lavoratoriInForza.getStatiEsteri().getDsStatiEsteri())))) {
			apiErrors.add(MsgProdis.PROLAVE0007.getError());
		}

	}

	private void controlliCheckCF(LavoratoriInForza lavoratoriInForza, List<ApiError> apiErrors) {

		if (lavoratoriInForza.getCodiceFiscale() == null || "".equals(lavoratoriInForza.getCodiceFiscale())) {
			apiErrors.add(MsgProdis.PROLAVE0001.getError());
		} else {
			String regexCodiceFiscale = lavoratoriInForzaDad.getParametroByNome(ConstantsProdis.PARAMETRO_REGEX_CF_PERSONAFISICA);
			if (!ProdisSrvUtil.checkValore(lavoratoriInForza.getCodiceFiscale(), regexCodiceFiscale)) {
				apiErrors.add(MsgProdis.PROLAVE0002.getError());
			} else {
				String codiceComuneStatoEsteroNascita = null;
				if (lavoratoriInForza.getComune() != null && lavoratoriInForza.getComune().getCodComuneMin() != null) {
					codiceComuneStatoEsteroNascita = lavoratoriInForza.getComune().getCodComuneMin();
				} else if (lavoratoriInForza.getStatiEsteri() != null
						&& lavoratoriInForza.getStatiEsteri().getCodNazioneMin() != null
						&& lavoratoriInForza.getStatiEsteri().getDsStatiEsteri() != null) {
					codiceComuneStatoEsteroNascita = lavoratoriInForza.getStatiEsteri().getCodNazioneMin();
				}

				String esito = lavoratoriInForzaDad.getCheckCongruenzaCodiceFiscale(
						lavoratoriInForza.getCodiceFiscale(), lavoratoriInForza.getCognome(),
						lavoratoriInForza.getNome(), lavoratoriInForza.getSesso(),
						ProdisSrvUtil.convertiDataInStringa(lavoratoriInForza.getDataNascita()),
						codiceComuneStatoEsteroNascita);
				
				if ("0".equalsIgnoreCase(esito)) {
					if (ProdisUtility.isCFConOmocodia(lavoratoriInForza.getCodiceFiscale()) ){
						if(!ProdisUtility.controllaCFConOmocodia(lavoratoriInForza.getCodiceFiscale())) {
							apiErrors.add(MsgProdis.PRODLAV0003.getError());
						}
					} else {
						apiErrors.add(MsgProdis.PRODLAV0003.getError());
					}
				}
			}
		}
	}

	private void controllaEtaLavoratoreConMessaggioWarning(LavoratoriInForza lavoratoriInForza,
			List<ApiError> apiErrors) {
		// String etaMinimaPrevista =
		// lavoratoriInForzaDad.getParametroByNome(ConstantsProdis.PARAMETRO_LAVORATORI_ETA_PREVISTA_MINIMA);
		String etaMassimaPrevista = lavoratoriInForzaDad
				.getParametroByNome(ConstantsProdis.PARAMETRO_LAVORATORI_ETA_PREVISTA_MASSIMA);

		if (lavoratoriInForza.getDataNascita() != null && lavoratoriInForza.getDataInizioRapporto() != null
				&& (ProdisSrvUtil.removeTime(lavoratoriInForza.getDataInizioRapporto())
						.before(ProdisSrvUtil.removeTime(lavoratoriInForza.getDataNascita()))
						|| ProdisSrvUtil.removeTime(lavoratoriInForza.getDataNascita())
								.equals(ProdisSrvUtil.removeTime(lavoratoriInForza.getDataInizioRapporto())))) {

			apiErrors.add(MsgProdis.PROLAVW0044.getError());

		}
		if (lavoratoriInForza.getDataNascita() != null
				&& ProdisSrvUtil.controllaData(lavoratoriInForza.getDataNascita())
				&& lavoratoriInForza.getDataInizioRapporto() != null
				&& ProdisSrvUtil.controllaData(lavoratoriInForza.getDataInizioRapporto())) {
			if (ProdisSrvUtil.differenzaInAnniFraDueDate(lavoratoriInForza.getDataNascita(),
					lavoratoriInForza.getDataInizioRapporto()) > Integer.valueOf(etaMassimaPrevista)) {
				apiErrors.add(MsgProdis.PROLAVW0045.getError());
			}
		}
	}

	public static int getDiffYears(Date first, Date last) {
		Calendar a = getCalendar(first);
		Calendar b = getCalendar(last);
		int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
		if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH)
				|| (a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
			diff--;
		}
		return diff;
	}

	public static Calendar getCalendar(Date date) {
		Calendar cal = Calendar.getInstance(Locale.US);
		cal.setTime(date);
		return cal;
	}

}
