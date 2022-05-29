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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import it.csi.prodis.prodisweb.ejb.business.be.dad.DatiProvincialiDad;
import it.csi.prodis.prodisweb.ejb.business.be.dad.ProspettoDad;
import it.csi.prodis.prodisweb.ejb.entity.ProDDatiProvinciali;
import it.csi.prodis.prodisweb.ejb.entity.ProDProvGradualita;
import it.csi.prodis.prodisweb.ejb.entity.ProRProspettoProvincia;
import it.csi.prodis.prodisweb.ejb.entity.ProTAtecofin;
import it.csi.prodis.prodisweb.ejb.entity.ProTCcnl;
import it.csi.prodis.prodisweb.ejb.entity.ProTComune;
import it.csi.prodis.prodisweb.lib.dto.ApiError;
import it.csi.prodis.prodisweb.lib.dto.common.DecodificaGenerica;
import it.csi.prodis.prodisweb.lib.dto.error.MsgProdis;
import it.csi.prodis.prodisweb.lib.dto.prospetto.AssPubbliche;
import it.csi.prodis.prodisweb.lib.dto.prospetto.Prospetto;

public class ValidatorProspetto extends ValidatorUtil {

	ProspettoDad prospettoDad;
	DatiProvincialiDad datiProvincialiDad;

	public ValidatorProspetto(ProspettoDad prospettoDad, DatiProvincialiDad datiProvincialiDad) {
		super();
		this.prospettoDad = prospettoDad;
		this.datiProvincialiDad = datiProvincialiDad;
	}

	public ValidatorProspetto(ProspettoDad prospettoDad) {
		super();
		this.prospettoDad = prospettoDad;
	}

	public boolean checkComuneValorizzato(String codice, String descrizione) {

		if (codice == null || "".equalsIgnoreCase(codice) || descrizione == null || "".equalsIgnoreCase(descrizione)) {
			return false;
		}
		return true;
	}

	private Long recuperaIdComuneDaDecodifica(String codice, String descrizione) {

		Long id = null;

		List<DecodificaGenerica> decode = prospettoDad.getComune(codice, descrizione);
		if (decode != null && decode.size() == 1) {
			id = decode.get(0).getIdDecodifica();
		}

		return id;
	}

	private Long recuperaIdAtecofinDaDecodifica(String codice, String descrizione) {

		Long id = null;

		List<DecodificaGenerica> decode = prospettoDad.getAtecofin(codice, descrizione);
		if (decode != null && decode.size() == 1) {
			id = decode.get(0).getIdDecodifica();
		}

		return id;
	}

	private Long recuperaIdCcnlDaDecodifica(String codice, String descrizione) {

		Long id = null;

		List<DecodificaGenerica> decode = prospettoDad.getCcnl(codice, descrizione);
		if (decode != null && decode.size() == 1) {
			id = decode.get(0).getIdDecodifica();
		}

		return id;
	}

	public boolean checkCongruenzaComune(String codice, String descrizione, Long id) {

		ProTComune comune = prospettoDad.getComuneById(id);

		if (comune.getCodComuneMin().equalsIgnoreCase(codice)
				&& comune.getDsProTComune().equalsIgnoreCase(descrizione)) {
			return true;
		}
		return false;
	}

	public boolean checkScadenzaComune(Long id) {

		ProTComune comune = prospettoDad.getComuneById(id);

		if (comune.getDtFine() == null || comune.getDtFine().getTime() > new Date().getTime()) {
			return true;
		}
		return false;
	}

	////////////
	public boolean checkAtecofinValorizzato(String codice, String descrizione) {

		if (codice == null || "".equalsIgnoreCase(codice) || descrizione == null || "".equalsIgnoreCase(descrizione)) {
			return false;
		}
		return true;
	}

	public boolean checkCongruenzaAtecofin(String codice, String descrizione, Long id) {

		ProTAtecofin atecofin = prospettoDad.getAtecofinById(id);

		if (atecofin.getCodAtecofinMin().equalsIgnoreCase(codice)
				&& atecofin.getDsProTAtecofin().equalsIgnoreCase(descrizione)) {
			return true;
		}
		return false;
	}

	public boolean checkScadenzaAtecofin(Long id) {

		ProTAtecofin atecofin = prospettoDad.getAtecofinById(id);

		if (atecofin.getDtFine() == null || atecofin.getDtFine().getTime() > new Date().getTime()) {
			return true;
		}
		return false;
	}

	////////////
	public boolean checkCcnlValorizzato(String codice, String descrizione) {

		if (codice == null || "".equalsIgnoreCase(codice) || descrizione == null || "".equalsIgnoreCase(descrizione)) {
			return false;
		}
		return true;
	}

	public boolean checkCongruenzaCcnl(String codice, String descrizione, Long id) {

		ProTCcnl ccnl = prospettoDad.getCcnlById(id);

		if (ccnl.getCodCcnlMin().equalsIgnoreCase(codice) && ccnl.getDsCcnl().equalsIgnoreCase(descrizione)) {
			return true;
		}
		return false;
	}

	public boolean checkScadenzaCcnl(Long id) {

		ProTCcnl ccnl = prospettoDad.getCcnlById(id);

		if (ccnl.getDtFine() == null || ccnl.getDtFine().getTime() > new Date().getTime()) {
			return true;
		}
		return false;
	}

	////////////////////////

	public void validaInBozza(Prospetto prospetto, List<ApiError> apiErrors) {

		controlliFormali(prospetto, apiErrors);

		if (apiErrors == null || apiErrors.size() == 0) {
			obbligatorietaMinima(prospetto, apiErrors);
		}
		if (apiErrors == null || apiErrors.size() == 0) {
			controlliMinimiCampiValorizzati(prospetto, apiErrors);
		}
	}

	private void controlliMinimiCampiValorizzati(Prospetto prospetto, List<ApiError> apiErrors) {
		if (prospetto.getDatiAzienda() != null && prospetto.getDatiAzienda().getCfAzienda() != null
				&& (prospetto.getDatiAzienda().getCfAzienda().length() != 11
						&& prospetto.getDatiAzienda().getCfAzienda().length() != 16)) {
			apiErrors.add(MsgProdis.PROPROE0035.getError());
		}
		if (prospetto.getDatiAzienda() != null && prospetto.getDatiAzienda().getAtecofin() != null
				&& prospetto.getDatiAzienda().getAtecofin().getCodAtecofinMin() != null
				&& !"".equalsIgnoreCase(prospetto.getDatiAzienda().getAtecofin().getCodAtecofinMin())
				&& (prospetto.getDatiAzienda().getAtecofin().getDsProTAtecofin() == null
						|| "".equalsIgnoreCase(prospetto.getDatiAzienda().getAtecofin().getDsProTAtecofin()))) {
			apiErrors.add(MsgProdis.PROPROE0036.getError());
		}
		if (prospetto.getDatiAzienda() != null && prospetto.getDatiAzienda().getAtecofin() != null
				&& (prospetto.getDatiAzienda().getAtecofin().getCodAtecofinMin() == null
						|| "".equalsIgnoreCase(prospetto.getDatiAzienda().getAtecofin().getCodAtecofinMin()))
				&& prospetto.getDatiAzienda().getAtecofin().getDsProTAtecofin() != null
				&& !"".equalsIgnoreCase(prospetto.getDatiAzienda().getAtecofin().getDsProTAtecofin())) {
			apiErrors.add(MsgProdis.PROPROE0037.getError());
		}
		if (prospetto.getDatiAzienda() != null && prospetto.getDatiAzienda().getAtecofin() != null
				&& prospetto.getDatiAzienda().getAtecofin().getId() == null
				&& prospetto.getDatiAzienda().getAtecofin().getCodAtecofinMin() != null
				&& !"".equalsIgnoreCase(prospetto.getDatiAzienda().getAtecofin().getCodAtecofinMin())
				&& prospetto.getDatiAzienda().getAtecofin().getDsProTAtecofin() != null
				&& !"".equalsIgnoreCase(prospetto.getDatiAzienda().getAtecofin().getDsProTAtecofin())) {
			Long id = recuperaIdAtecofinDaDecodifica(prospetto.getDatiAzienda().getAtecofin().getCodAtecofinMin(),
					prospetto.getDatiAzienda().getAtecofin().getDsProTAtecofin());
			if (id != null) {
				prospetto.getDatiAzienda().getAtecofin().setId(id);
			} else {
				apiErrors.add(MsgProdis.PROPROE0083.getError());
			}
		}
		if (prospetto.getDatiAzienda() != null && prospetto.getDatiAzienda().getCcnl() != null
				&& prospetto.getDatiAzienda().getCcnl().getCodCcnlMin() != null
				&& !"".equalsIgnoreCase(prospetto.getDatiAzienda().getCcnl().getCodCcnlMin())
				&& (prospetto.getDatiAzienda().getCcnl().getDsCcnl() == null
						|| "".equalsIgnoreCase(prospetto.getDatiAzienda().getCcnl().getDsCcnl()))) {
			apiErrors.add(MsgProdis.PROPROE0038.getError());
		}
		if (prospetto.getDatiAzienda() != null && prospetto.getDatiAzienda().getCcnl() != null
				&& (prospetto.getDatiAzienda().getCcnl().getCodCcnlMin() == null
						|| "".equalsIgnoreCase(prospetto.getDatiAzienda().getCcnl().getCodCcnlMin()))
				&& prospetto.getDatiAzienda().getCcnl().getDsCcnl() != null
				&& !"".equalsIgnoreCase(prospetto.getDatiAzienda().getCcnl().getDsCcnl())) {
			apiErrors.add(MsgProdis.PROPROE0039.getError());
		}
		if (prospetto.getDatiAzienda() != null && prospetto.getDatiAzienda().getCcnl() != null
				&& prospetto.getDatiAzienda().getCcnl().getId() == null
				&& prospetto.getDatiAzienda().getCcnl().getCodCcnlMin() != null
				&& !"".equalsIgnoreCase(prospetto.getDatiAzienda().getCcnl().getCodCcnlMin())
				&& prospetto.getDatiAzienda().getCcnl().getDsCcnl() != null
				&& !"".equalsIgnoreCase(prospetto.getDatiAzienda().getCcnl().getDsCcnl())) {
			Long id = recuperaIdCcnlDaDecodifica(prospetto.getDatiAzienda().getCcnl().getCodCcnlMin(),
					prospetto.getDatiAzienda().getCcnl().getDsCcnl());
			if (id != null) {
				prospetto.getDatiAzienda().getCcnl().setId(id);
			} else {
				apiErrors.add(MsgProdis.PROPROE0084.getError());
			}
		}
		// sede legale
		if (prospetto.getDatiAzienda() != null && prospetto.getDatiAzienda().getSedeLegale() != null
				&& prospetto.getDatiAzienda().getSedeLegale().getEmail() != null
				&& !"".equals(prospetto.getDatiAzienda().getSedeLegale().getEmail())) {

			String regexMail = prospettoDad.getParametroByNome(ConstantsProdis.PARAMETRO_REGEX_EMAIL);
			if (!ProdisSrvUtil.checkValore(prospetto.getDatiAzienda().getSedeLegale().getEmail(), regexMail)) {
				apiErrors.add(MsgProdis.PROPROE0057.getError());
			}
		}

		if (prospetto.getDatiAzienda() != null && prospetto.getDatiAzienda().getSedeLegale() != null
				&& prospetto.getDatiAzienda().getSedeLegale().getComune() != null
				&& prospetto.getDatiAzienda().getSedeLegale().getComune().getCodComuneMin() != null
				&& !"".equalsIgnoreCase(prospetto.getDatiAzienda().getSedeLegale().getComune().getCodComuneMin())
				&& (prospetto.getDatiAzienda().getSedeLegale().getComune().getDsProTComune() == null || ""
						.equalsIgnoreCase(prospetto.getDatiAzienda().getSedeLegale().getComune().getDsProTComune()))) {
			apiErrors.add(MsgProdis.PROPROE0040.getError());
		}
		if (prospetto.getDatiAzienda() != null && prospetto.getDatiAzienda().getSedeLegale() != null
				&& prospetto.getDatiAzienda().getSedeLegale().getComune() != null
				&& (prospetto.getDatiAzienda().getSedeLegale().getComune().getCodComuneMin() == null || ""
						.equalsIgnoreCase(prospetto.getDatiAzienda().getSedeLegale().getComune().getCodComuneMin()))
				&& prospetto.getDatiAzienda().getSedeLegale().getComune().getDsProTComune() != null
				&& !"".equalsIgnoreCase(prospetto.getDatiAzienda().getSedeLegale().getComune().getDsProTComune())) {
			apiErrors.add(MsgProdis.PROPROE0041.getError());
		}
		if (prospetto.getDatiAzienda() != null && prospetto.getDatiAzienda().getSedeLegale() != null
				&& prospetto.getDatiAzienda().getSedeLegale().getComune() != null
				&& prospetto.getDatiAzienda().getSedeLegale().getComune().getId() == null
				&& prospetto.getDatiAzienda().getSedeLegale().getComune().getCodComuneMin() != null
				&& !"".equalsIgnoreCase(prospetto.getDatiAzienda().getSedeLegale().getComune().getCodComuneMin())
				&& prospetto.getDatiAzienda().getSedeLegale().getComune().getDsProTComune() != null
				&& !"".equalsIgnoreCase(prospetto.getDatiAzienda().getSedeLegale().getComune().getDsProTComune())) {
			Long id = recuperaIdComuneDaDecodifica(
					prospetto.getDatiAzienda().getSedeLegale().getComune().getCodComuneMin(),
					prospetto.getDatiAzienda().getSedeLegale().getComune().getDsProTComune());
			if (id != null) {
				prospetto.getDatiAzienda().getSedeLegale().getComune().setId(id);
			} else {
				apiErrors.add(MsgProdis.PROPROE0082.getError());
			}
		}
		// dati referente
		if (prospetto.getDatiAzienda() != null && prospetto.getDatiAzienda().getCfReferente() != null
				&& prospetto.getDatiAzienda().getCfReferente().length() != 16) {
			apiErrors.add(MsgProdis.PROPROE0042.getError());
		}
		if (prospetto.getDatiAzienda() != null && prospetto.getDatiAzienda().getCfReferente() != null
				&& !ProdisSrvUtil.controllaCF(prospetto.getDatiAzienda().getCfReferente())) {
			apiErrors.add(MsgProdis.PROPROE0043.getError());
		}

		if (prospetto.getDatiAzienda() != null && prospetto.getDatiAzienda().getEmailReferente() != null
				&& !"".equals(prospetto.getDatiAzienda().getEmailReferente())) {

			String regexMail = prospettoDad.getParametroByNome(ConstantsProdis.PARAMETRO_REGEX_EMAIL);
			if (!ProdisSrvUtil.checkValore(prospetto.getDatiAzienda().getEmailReferente(), regexMail)) {
				apiErrors.add(MsgProdis.PROPROE0058.getError());
			}
		}
		if (prospetto.getDatiAzienda() != null && prospetto.getDatiAzienda().getComune() != null
				&& prospetto.getDatiAzienda().getComune().getId() == null
				&& prospetto.getDatiAzienda().getComune().getCodComuneMin() != null
				&& !"".equalsIgnoreCase(prospetto.getDatiAzienda().getComune().getCodComuneMin())
				&& prospetto.getDatiAzienda().getComune().getDsProTComune() != null
				&& !"".equalsIgnoreCase(prospetto.getDatiAzienda().getComune().getDsProTComune())) {
			Long id = recuperaIdComuneDaDecodifica(prospetto.getDatiAzienda().getComune().getCodComuneMin(),
					prospetto.getDatiAzienda().getComune().getDsProTComune());
			if (id != null) {
				prospetto.getDatiAzienda().getComune().setId(id);
			} else {
				apiErrors.add(MsgProdis.PROPROE0081.getError());
			}
		}
		if (prospetto.getDatiAzienda() != null && prospetto.getDatiAzienda().getComune() != null
				&& prospetto.getDatiAzienda().getComune().getCodComuneMin() != null
				&& (prospetto.getDatiAzienda().getComune().getDsProTComune() == null
						|| "".equalsIgnoreCase(prospetto.getDatiAzienda().getComune().getDsProTComune()))) {
			apiErrors.add(MsgProdis.PROPROE0044.getError());
		}
		if (prospetto.getDatiAzienda() != null && prospetto.getDatiAzienda().getComune() != null
				&& (prospetto.getDatiAzienda().getComune().getCodComuneMin() == null
						|| "".equalsIgnoreCase(prospetto.getDatiAzienda().getComune().getCodComuneMin()))
				&& prospetto.getDatiAzienda().getComune().getDsProTComune() != null) {
			apiErrors.add(MsgProdis.PROPROE0045.getError());
		}

		String codiceFiscaleAziendaCapoGruppo = null;
		if (prospetto.getDatiAzienda() != null && prospetto.getDatiAzienda().getCfCapogruppo() != null) {
			codiceFiscaleAziendaCapoGruppo = prospetto.getDatiAzienda().getCfCapogruppo();
		}

		String siglaNazione = null;
		if (prospetto.getDatiAzienda() != null && prospetto.getDatiAzienda().getStatiEsteri() != null
				&& prospetto.getDatiAzienda().getStatiEsteri().getSiglaNazione() != null) {
			siglaNazione = prospetto.getDatiAzienda().getStatiEsteri().getSiglaNazione();
		}

		if (prospetto.getDatiAzienda() != null && prospetto.getDatiAzienda().getDichiarante() != null
				&& prospetto.getDatiAzienda().getDichiarante().getId() != null) {

			if (!prospetto.getDatiAzienda().getDichiarante().getId().equals(ConstantsProdis.COD_DICHIARANTE_D)) {

				if (codiceFiscaleAziendaCapoGruppo != null) {
					apiErrors.add(MsgProdis.PROPROE0046.getError());
				} else {
					if (prospetto.getDatiAzienda().getFlgProspettoDaCapogruppo() != null
							&& "S".equalsIgnoreCase(prospetto.getDatiAzienda().getFlgProspettoDaCapogruppo())) {
						apiErrors.add(MsgProdis.PROPROE0047.getError());
					}
				}
			} else {
				if (codiceFiscaleAziendaCapoGruppo == null || "".equalsIgnoreCase(codiceFiscaleAziendaCapoGruppo)) {
					apiErrors.add(MsgProdis.PROPROE0048.getError());
				} else {
					if (prospetto.getDatiAzienda().getFlgProspettoDaCapogruppo() != null
							&& "S".equalsIgnoreCase(prospetto.getDatiAzienda().getFlgProspettoDaCapogruppo())) {
						if (siglaNazione != null && !siglaNazione.equals(ConstantsProdis.SIGLA_ITALIANA)) {
							apiErrors.add(MsgProdis.PROPROE0049.getError());
						} else {
							if (prospetto.getDatiAzienda().getCfAzienda() != null && !prospetto.getDatiAzienda()
									.getCfAzienda().equalsIgnoreCase(codiceFiscaleAziendaCapoGruppo)) {
								apiErrors.add(MsgProdis.PROPROE0050.getError());
							}
						}
					} else {
						if (prospetto.getDatiAzienda().getCfAzienda() != null && prospetto.getDatiAzienda()
								.getCfAzienda().equalsIgnoreCase(codiceFiscaleAziendaCapoGruppo)) {
							apiErrors.add(MsgProdis.PROPROE0051.getError());
						}
					}
				}
			}

		}
		if (codiceFiscaleAziendaCapoGruppo != null && (siglaNazione == null || "".equalsIgnoreCase(siglaNazione))
				|| (codiceFiscaleAziendaCapoGruppo == null || "".equalsIgnoreCase(codiceFiscaleAziendaCapoGruppo))
						&& siglaNazione != null) {
			apiErrors.add(MsgProdis.PROPROE0052.getError());
		}
		if (codiceFiscaleAziendaCapoGruppo != null) {
			if (siglaNazione != null) {
				if (ConstantsProdis.SIGLA_ITALIANA.equalsIgnoreCase(siglaNazione)) {
					if (!ProdisSrvUtil.controllaPartitaIVA(codiceFiscaleAziendaCapoGruppo)
							&& !ProdisSrvUtil.controllaCF(codiceFiscaleAziendaCapoGruppo)) {
						apiErrors.add(MsgProdis.PROPROE0053.getError());
					}
				} else {
					String codiceFiscaleStraniero = siglaNazione + codiceFiscaleAziendaCapoGruppo;
					String regexCodiceFiscaleStraniero = prospettoDad
							.getParametroByNome(ConstantsProdis.PARAMETRO_REGEX_AZIENDA_CODICEFISCALE_STRANIERO);

					if (!ProdisSrvUtil.checkValore(codiceFiscaleStraniero, regexCodiceFiscaleStraniero)) {
						apiErrors.add(MsgProdis.PROPROE0053.getError());
					}
				}
			}
		}
		// sezione dati prospetto
		String dataAtto = null;
		if (prospetto.getProspettoGradualita() != null && prospetto.getProspettoGradualita().getDataAtto() != null) {
			dataAtto = ProdisSrvUtil.convertiDataInStringa(prospetto.getProspettoGradualita().getDataAtto());
		}
		String dataRiferimento = null;
		if (prospetto.getDataRiferimentoProspetto() != null) {
			dataRiferimento = ProdisSrvUtil.convertiDataInStringa(prospetto.getDataRiferimentoProspetto());
		}
		String dataTrasformazione = null;
		if (prospetto.getProspettoGradualita() != null
				&& prospetto.getProspettoGradualita().getDataTrasformazione() != null) {
			dataTrasformazione = ProdisSrvUtil
					.convertiDataInStringa(prospetto.getProspettoGradualita().getDataTrasformazione());
		}

		if (dataRiferimento != null) {

			// verifiche sulla data di riferimento
			/*
			 * se data odierna <= data parametro/anno in corso ---------------------------->
			 * la data prospetto deve essere 31/12/anno in corso -1 -----------------------
			 * se data odierna > data parametro/anno in corso----------------------------->
			 * la data prospetto dovrà essere: data parametro/anno in corso < la data
			 * prospetto <= data odierna
			 */

			Date dataProspetto = ProdisSrvUtil.convertiStringaInData(dataRiferimento);

			Date dataOdierna = new Date();

			Date dataParametroAnnoInCorso = ProdisSrvUtil.convertiStringaInData(
					prospettoDad.getParametroByNome(ConstantsProdis.PARAMETRO_DATA_RIFERIMENTO_TERMINE_PROSPETTO) + "/"
							+ ProdisSrvUtil.getAnnoCorrenteStringa());

			Date dataUltimaPrec = ProdisSrvUtil
					.convertiStringaInData("31/12/" + (Integer.parseInt(ProdisSrvUtil.getAnnoCorrenteStringa()) - 1));

			if (!prospetto.getComunicazione().getId()
					.equals(Long.valueOf(ConstantsProdis.TIPO_COMUNICAZIONE_PROSPETTO_RETTIFICA))) {
				// PROVA

				/*
				 * EVOUTIVA PRODIS 10/02/2022 1.1 Gestione valore e controlli per data di
				 * riferimento – Modifica valorizzazione nel periodo di dichiarazione NON
				 * ufficiale
				 * 
				 * La data di riferimento deve essere compresa tra 01/01/<anno in corso> e data
				 * corrente
				 * 
				 */

				if (ProdisSrvUtil.removeTime(dataOdierna).before(ProdisSrvUtil.removeTime(dataParametroAnnoInCorso))
						|| ProdisSrvUtil.removeTime(dataOdierna)
								.equals(ProdisSrvUtil.removeTime(dataParametroAnnoInCorso))) {
					/*
					 * PERIODO UFFICIALE
					 */
					if (!ProdisSrvUtil.removeTime(dataProspetto).equals(ProdisSrvUtil.removeTime(dataUltimaPrec))) {
						apiErrors.add(new ApiError("PRO-PRO-E-0079", "La data prospetto deve essere uguale a "
								+ ProdisSrvUtil.getStringDate(dataUltimaPrec)));
					}
				} else {
					/*
					 * PERIODO NON UFFICIALE
					 */

					/*
					 * EVOUTIVA PRODIS 10/02/2022 1.1 Gestione valore e controlli per data di
					 * riferimento – Modifica valorizzazione nel periodo di dichiarazione NON
					 * ufficiale
					 * 
					 * La data di riferimento deve essere compresa tra 01/01/<anno in corso> e data
					 * corrente
					 * 
					 */
//				if (ProdisSrvUtil.removeTime(dataParametroAnnoInCorso).before(ProdisSrvUtil.removeTime(dataProspetto))
//						&& (ProdisSrvUtil.removeTime(dataProspetto).before(ProdisSrvUtil.removeTime(dataOdierna))
//								|| ProdisSrvUtil.removeTime(dataProspetto)
//										.equals(ProdisSrvUtil.removeTime(dataOdierna)))) {
					if (ProdisSrvUtil.removeTime(dataUltimaPrec).before(ProdisSrvUtil.removeTime(dataProspetto))
							&& (ProdisSrvUtil.removeTime(dataProspetto).before(ProdisSrvUtil.removeTime(dataOdierna))
									|| ProdisSrvUtil.removeTime(dataProspetto)
											.equals(ProdisSrvUtil.removeTime(dataOdierna)))) {
						// DO NOTHING
					} else {
						Date data_01_01_annoCorrente = ProdisSrvUtil.convertiStringaInData(
								"01/01/" + (Integer.parseInt(ProdisSrvUtil.getAnnoCorrenteStringa())));
						apiErrors.add(new ApiError("PRO-PRO-E-0079",
								"La data prospetto deve essere compresa tra "
										+ ProdisSrvUtil.getStringDate(ProdisSrvUtil.removeTime(data_01_01_annoCorrente))
										+ " e la data odierna"));
					}
				}
			}

			if (dataAtto != null) {
				if (ProdisSrvUtil.confrontaData1MaggioreData2(dataAtto, dataRiferimento)) {
					apiErrors.add(MsgProdis.PROPROE0054.getError());
				}
			}
			if (dataTrasformazione != null) {
				if (ProdisSrvUtil.confrontaData1MaggioreData2(dataTrasformazione, dataRiferimento)) {
					apiErrors.add(MsgProdis.PROPROE0055.getError());
				}
			}

		}

	}

	private void obbligatorietaMinima(Prospetto prospetto, List<ApiError> apiErrors) {
		if (prospetto.getDatiAzienda() != null && (prospetto.getDatiAzienda().getCfAzienda() == null
				|| "".equalsIgnoreCase(prospetto.getDatiAzienda().getCfAzienda()))) {
			apiErrors.add(MsgProdis.PROPROE0033.getError());
		}
		if (prospetto.getDatiAzienda() != null && (prospetto.getDatiAzienda().getDenominazioneDatoreLavoro() == null
				|| "".equalsIgnoreCase(prospetto.getDatiAzienda().getDenominazioneDatoreLavoro()))) {
			apiErrors.add(MsgProdis.PROPROE0034.getError());
		}
	}

	private void controlliFormali(Prospetto prospetto, List<ApiError> apiErrors) {
		if (prospetto.getDatiAzienda() == null) {
			apiErrors.add(MsgProdis.PROPROE0003.getError());
			return;
		}
		// sede legale
		if (prospetto.getDatiAzienda().getSedeLegale() != null
				&& prospetto.getDatiAzienda().getSedeLegale().getCapSede() != null
				&& !"".equalsIgnoreCase(prospetto.getDatiAzienda().getSedeLegale().getCapSede())
				&& !ProdisSrvUtil.controllaCAP(prospetto.getDatiAzienda().getSedeLegale().getCapSede())) {
			apiErrors.add(MsgProdis.PROPROE0021.getError());
		}
		if (prospetto.getDatiAzienda().getSedeLegale() != null
				&& prospetto.getDatiAzienda().getSedeLegale().getTelefono() != null
				&& !"".equalsIgnoreCase(prospetto.getDatiAzienda().getSedeLegale().getTelefono())
				&& !ProdisSrvUtil.controllaTel(prospetto.getDatiAzienda().getSedeLegale().getTelefono())) {
			apiErrors.add(MsgProdis.PROPROE0022.getError());
		}
		if (prospetto.getDatiAzienda().getSedeLegale() != null
				&& prospetto.getDatiAzienda().getSedeLegale().getFax() != null
				&& !"".equalsIgnoreCase(prospetto.getDatiAzienda().getSedeLegale().getFax())
				&& !ProdisSrvUtil.controllaTel(prospetto.getDatiAzienda().getSedeLegale().getFax())) {
			apiErrors.add(MsgProdis.PROPROE0023.getError());
		}
		// referente
		if (prospetto.getDatiAzienda().getCfReferente() != null
				&& prospetto.getDatiAzienda().getCfReferente().length() != 16
				&& prospetto.getDatiAzienda().getCfReferente().length() != 11) {
			apiErrors.add(MsgProdis.PROPROE0024.getError());
		} else if (prospetto.getDatiAzienda().getCfReferente() != null
				&& prospetto.getDatiAzienda().getCfReferente().length() == 16) {
			/////////////////
			String esito = prospettoDad.getCheckCongruenzaCodiceFiscale(prospetto.getDatiAzienda().getCfReferente(),
					prospetto.getDatiAzienda().getCognomeReferente(), prospetto.getDatiAzienda().getNomeReferente(),
					null, null, null);
			if ("0".equalsIgnoreCase(esito)) {
				apiErrors.add(MsgProdis.PROPROE0078.getError());
			}
		}
		if (prospetto.getDatiAzienda().getCapReferente() != null
				&& !"".equalsIgnoreCase(prospetto.getDatiAzienda().getCapReferente())
				&& !ProdisSrvUtil.controllaCAP(prospetto.getDatiAzienda().getCapReferente())) {
			apiErrors.add(MsgProdis.PROPROE0025.getError());
		}
		if (prospetto.getDatiAzienda().getTelefonoReferente() != null
				&& !"".equalsIgnoreCase(prospetto.getDatiAzienda().getTelefonoReferente())
				&& !ProdisSrvUtil.controllaTel(prospetto.getDatiAzienda().getTelefonoReferente())) {
			apiErrors.add(MsgProdis.PROPROE0026.getError());
		}
		if (prospetto.getDatiAzienda().getFaxReferente() != null
				&& !"".equalsIgnoreCase(prospetto.getDatiAzienda().getFaxReferente())
				&& !ProdisSrvUtil.controllaTel(prospetto.getDatiAzienda().getFaxReferente())) {
			apiErrors.add(MsgProdis.PROPROE0027.getError());
		}

		//////////////// dati prospetto
		if (prospetto.getDataRiferimentoProspetto() != null
				&& !ProdisSrvUtil.controllaData(prospetto.getDataRiferimentoProspetto())) {
			apiErrors.add(MsgProdis.PROPROE0028.getError());
		}
		if (prospetto.getProspettoGradualita() != null && prospetto.getProspettoGradualita().getDataAtto() != null
				&& !ProdisSrvUtil.controllaData(prospetto.getProspettoGradualita().getDataAtto())) {
			apiErrors.add(MsgProdis.PROPROE0029.getError());
		}
		if (prospetto.getProspettoGradualita() != null
				&& prospetto.getProspettoGradualita().getDataTrasformazione() != null
				&& !ProdisSrvUtil.controllaData(prospetto.getProspettoGradualita().getDataTrasformazione())) {
			apiErrors.add(MsgProdis.PROPROE0030.getError());
		}
		if (prospetto.getdFineSospensioneQ1() != null
				&& !ProdisSrvUtil.controllaData(prospetto.getdFineSospensioneQ1())) {
			apiErrors.add(MsgProdis.PROPROE0031.getError());
		}
		if (prospetto.getProspettoGradualita() != null && prospetto.getProspettoGradualita().getPercentuale() != null
				&& !ProdisSrvUtil
						.controllaPercentuale(prospetto.getProspettoGradualita().getPercentuale().intValue())) {
			apiErrors.add(MsgProdis.PROPROE0032.getError());
		}
		////// Ass pubbliche
		// controllo su unicità di una riga per regione
		if (prospetto.getAssPubbliche() != null && prospetto.getAssPubbliche().size() > 0) {
			for (AssPubbliche assPubb1 : prospetto.getAssPubbliche()) {

				Long idRegione1 = assPubb1.getRegione().getId();
				int quanti = 0;

				for (AssPubbliche assPubb2 : prospetto.getAssPubbliche()) {
					Long idRegione2 = assPubb2.getRegione().getId();

					if (idRegione1 == idRegione2) {
						quanti++;
					}
				}
				if (quanti > 1) {
					apiErrors.add(MsgProdis.PROPROE0077.getError());
					break;
				}
			}
		}
	}

	//////////////////////
	public void validaConfermaEProsegui(Prospetto prospetto, List<ApiError> apiErrors, List<ApiError> apiWarnings) {

		if (prospetto.getProspettoGradualita() != null) {

			List<ProRProspettoProvincia> ppFinded = new ArrayList<ProRProspettoProvincia>();
			if (prospetto.getId() != null) {
				ppFinded = prospettoDad.getProspettoProvinciaPerProspetto(prospetto.getId());
			}

			Boolean flagW = false;

			for (ProRProspettoProvincia pp : ppFinded) {

				Optional<ProDDatiProvinciali> dp = prospettoDad.getDatiProvinciali(pp.getIdProspettoProv());

				if (dp.isPresent()) {

					if (dp.get().getProDProvGradualita() == null) {

						flagW = true;

						pp.setFlgConfermatoQ2("N");

						datiProvincialiDad.modificaFlagConfermatoQ2(dp.get().getId(), true, false);

					} else if (dp.get().getProDProvGradualita().getnAssunzioniEffDopoTrasf() == null
							|| dp.get().getProDProvGradualita().getnAssunzioniEffDopoTrasf().intValue() == 0) {

						flagW = true;

						pp.setFlgConfermatoQ2("N");

						datiProvincialiDad.modificaFlagConfermatoQ2(dp.get().getId(), true, false);

					}

				}

			}

			if (flagW) {
				apiWarnings.add(MsgProdis.PRODPRW0101.getError());
			}

		}

		if (ProdisSrvUtil.isNotVoid(prospetto) && ProdisSrvUtil.isNotVoid(prospetto.getId())) {
			List<ProRProspettoProvincia> ppFinded = prospettoDad.getProspettoProvinciaPerProspetto(prospetto.getId());

			Boolean flagMob = false;
			for (ProRProspettoProvincia p : ppFinded) {

				ProDDatiProvinciali d = p.getProDDatiProvinciali();

				if (d.getProDProvSospensione() != null && d.getProDProvSospensione().getProTStatoConcessione() != null
						&& d.getProDProvSospensione().getProTStatoConcessione().getId() == 1
						&& d.getProDProvSospensione().getProTCausaSospensione() != null
						&& d.getProDProvSospensione().getProTCausaSospensione().getId() == 2) {
					flagMob = true;
					break;
				}

			}

			if (prospetto.getFlgSospensionePerMobilita() != null && prospetto.getFlgSospensionePerMobilita().equals("N")
					&& flagMob) {
				apiErrors.add(MsgProdis.PRODPRE0069.getError());
			}
		}

		obbligatorietaAggiuntiva(prospetto, apiErrors);

		if (apiErrors == null || apiErrors.size() == 0) {
			controlliAggiuntivi(prospetto, apiErrors);
		}
	}

	public void controlliAggiuntivi(Prospetto prospetto, List<ApiError> apiErrors) {
		// start - questi controlli erano sul server. li aggiungo qui
		if (prospetto.getDatiAzienda() != null && prospetto.getDatiAzienda().getComune() != null) {

			if (prospetto.getDatiAzienda().getComune().getId() != null) {
				if (!checkCongruenzaComune(prospetto.getDatiAzienda().getComune().getCodComuneMin(),
						prospetto.getDatiAzienda().getComune().getDsProTComune(),
						prospetto.getDatiAzienda().getComune().getId())) {
					apiErrors.add(MsgProdis.PROPROE0008.getError());
				}
				if (!checkScadenzaComune(prospetto.getDatiAzienda().getComune().getId())) {
					apiErrors.add(MsgProdis.PROPROE0010.getError());
				}
			}
		}
		if (prospetto.getDatiAzienda() != null && prospetto.getDatiAzienda().getSedeLegale() != null
				&& prospetto.getDatiAzienda().getSedeLegale().getComune() != null) {

			if (prospetto.getDatiAzienda().getSedeLegale().getComune().getId() != null) {
				if (!checkCongruenzaComune(prospetto.getDatiAzienda().getSedeLegale().getComune().getCodComuneMin(),
						prospetto.getDatiAzienda().getSedeLegale().getComune().getDsProTComune(),
						prospetto.getDatiAzienda().getSedeLegale().getComune().getId())) {
					apiErrors.add(MsgProdis.PROPROE0009.getError());
				}
				if (!checkScadenzaComune(prospetto.getDatiAzienda().getSedeLegale().getComune().getId())) {
					apiErrors.add(MsgProdis.PROPROE0011.getError());
				}
			}

		}
		if (prospetto.getDatiAzienda() != null && prospetto.getDatiAzienda().getAtecofin() != null) {

			if (prospetto.getDatiAzienda().getAtecofin().getId() != null) {
				if (!checkCongruenzaAtecofin(prospetto.getDatiAzienda().getAtecofin().getCodAtecofinMin(),
						prospetto.getDatiAzienda().getAtecofin().getDsProTAtecofin(),
						prospetto.getDatiAzienda().getAtecofin().getId())) {
					apiErrors.add(MsgProdis.PROPROE0013.getError());
				}
				if (!checkScadenzaAtecofin(prospetto.getDatiAzienda().getAtecofin().getId())) {
					apiErrors.add(MsgProdis.PROPROE0014.getError());
				}
			}

		}
		if (prospetto.getDatiAzienda() != null && prospetto.getDatiAzienda().getCcnl() != null) {

			if (prospetto.getDatiAzienda().getCcnl().getId() != null) {
				if (!checkCongruenzaCcnl(prospetto.getDatiAzienda().getCcnl().getCodCcnlMin(),
						prospetto.getDatiAzienda().getCcnl().getDsCcnl(),
						prospetto.getDatiAzienda().getCcnl().getId())) {
					apiErrors.add(MsgProdis.PROPROE0016.getError());
				}
				if (!checkScadenzaCcnl(prospetto.getDatiAzienda().getCcnl().getId())) {
					apiErrors.add(MsgProdis.PROPROE0017.getError());
				}
			}

		}
		// end
		if (prospetto.getDatiAzienda() != null && prospetto.getDatiAzienda().getDichiarante() != null) {
			if (prospetto.getDatiAzienda().getDichiarante().getId() == ConstantsProdis.COD_DICHIARANTE_C) {
				if (prospetto.getProspettoGradualita() != null
						&& ProdisSrvUtil.isNotVoid(prospetto.getProspettoGradualita().getDataAtto())) {
					apiErrors.add(MsgProdis.PROPROE0076.getError());
				}
			}
		}

		// 71862 - PRODIS-545
		if (prospetto.getId() != null) {

			// carico i dati prospettoProvincia
			List<ProRProspettoProvincia> elencoProspettoProvincia = prospettoDad
					.caricaProspettoProvinciaPerPdf(prospetto.getId());
			if (elencoProspettoProvincia != null && elencoProspettoProvincia.size() > 0) {
				for (ProRProspettoProvincia proProv : elencoProspettoProvincia) {
					ProDProvGradualita provGradualita = prospettoDad
							.caricaProvGradualitaPerPdf(proProv.getIdProspettoProv());

					if (provGradualita != null && !isGradualitaPresente(prospetto)
							&& provGradualita.getnAssunzioniEffDopoTrasf() != null) {
						apiErrors.add(MsgProdis.PROPROE0080.getError());
					}
				}

			}

		}
	}

	public void obbligatorietaAggiuntiva(Prospetto prospetto, List<ApiError> apiErrors) {
		if (prospetto.getDatiAzienda() != null) {
			if (prospetto.getDatiAzienda().getAtecofin() == null
					|| !checkAtecofinValorizzato(prospetto.getDatiAzienda().getAtecofin().getCodAtecofinMin(),
							prospetto.getDatiAzienda().getAtecofin().getDsProTAtecofin())) {
				apiErrors.add(MsgProdis.PROPROE0012.getError());
			}
			if (prospetto.getDatiAzienda().getCcnl() == null
					|| !checkAtecofinValorizzato(prospetto.getDatiAzienda().getCcnl().getCodCcnlMin(),
							prospetto.getDatiAzienda().getCcnl().getDsCcnl())) {
				apiErrors.add(MsgProdis.PROPROE0015.getError());
			}
			if (prospetto.getDatiAzienda().getDichiarante() == null
					|| ProdisSrvUtil.isVoid(prospetto.getDatiAzienda().getDichiarante().getCodDichiarante())) {
				apiErrors.add(MsgProdis.PROPROE0059.getError());
			}

			// sede legale
			if (prospetto.getDatiAzienda().getSedeLegale() == null
					|| prospetto.getDatiAzienda().getSedeLegale().getComune() == null
					|| !checkComuneValorizzato(prospetto.getDatiAzienda().getSedeLegale().getComune().getCodComuneMin(),
							prospetto.getDatiAzienda().getSedeLegale().getComune().getDsProTComune())) {
				apiErrors.add(MsgProdis.PROPROE0007.getError());
			}
			if (prospetto.getDatiAzienda().getSedeLegale() == null
					|| ProdisSrvUtil.isVoid(prospetto.getDatiAzienda().getSedeLegale().getEmail())) {
				apiErrors.add(MsgProdis.PROPROE0060.getError());
			}
			if (prospetto.getDatiAzienda().getSedeLegale() == null
					|| ProdisSrvUtil.isVoid(prospetto.getDatiAzienda().getSedeLegale().getCapSede())) {
				apiErrors.add(MsgProdis.PROPROE0061.getError());
			}
			if (prospetto.getDatiAzienda().getSedeLegale() == null
					|| ProdisSrvUtil.isVoid(prospetto.getDatiAzienda().getSedeLegale().getIndirizzo())) {
				apiErrors.add(MsgProdis.PROPROE0062.getError());
			}
			if (prospetto.getDatiAzienda().getSedeLegale() == null
					|| (ProdisSrvUtil.isVoid(prospetto.getDatiAzienda().getSedeLegale().getTelefono())
							&& ProdisSrvUtil.isVoid(prospetto.getDatiAzienda().getSedeLegale().getFax()))) {
				apiErrors.add(MsgProdis.PROPROE0063.getError());
			}

			// dati referente
			if (ProdisSrvUtil.isVoid(prospetto.getDatiAzienda().getCfReferente())) {
				apiErrors.add(MsgProdis.PROPROE0064.getError());
			}
			if (ProdisSrvUtil.isVoid(prospetto.getDatiAzienda().getCognomeReferente())) {
				apiErrors.add(MsgProdis.PROPROE0065.getError());
			}
			if (ProdisSrvUtil.isVoid(prospetto.getDatiAzienda().getNomeReferente())) {
				apiErrors.add(MsgProdis.PROPROE0066.getError());
			}
			if (ProdisSrvUtil.isVoid(prospetto.getDatiAzienda().getCapReferente())) {
				apiErrors.add(MsgProdis.PROPROE0067.getError());
			}
			if (ProdisSrvUtil.isVoid(prospetto.getDatiAzienda().getIndirizzoReferente())) {
				apiErrors.add(MsgProdis.PROPROE0068.getError());
			}

			if (prospetto.getDatiAzienda().getComune() == null
					|| !checkComuneValorizzato(prospetto.getDatiAzienda().getComune().getCodComuneMin(),
							prospetto.getDatiAzienda().getComune().getDsProTComune())) {
				apiErrors.add(MsgProdis.PROPROE0006.getError());
			}
			if (ProdisSrvUtil.isVoid(prospetto.getDatiAzienda().getTelefonoReferente())
					&& ProdisSrvUtil.isVoid(prospetto.getDatiAzienda().getFaxReferente())
					&& ProdisSrvUtil.isVoid(prospetto.getDatiAzienda().getEmailReferente())) {
				apiErrors.add(MsgProdis.PROPROE0069.getError());
			}
		}
		// DATI PROSPETTO
		if (ProdisSrvUtil.isVoid(prospetto.getDataRiferimentoProspetto())) {
			apiErrors.add(MsgProdis.PROPROE0070.getError());
		}
		if ((prospetto.getProspettoGradualita() != null
				&& ProdisSrvUtil.isNotVoid(prospetto.getProspettoGradualita().getDataAtto()))
				|| (prospetto.getProspettoGradualita() != null
						&& ProdisSrvUtil.isNotVoid(prospetto.getProspettoGradualita().getEstremiAtto()))
				|| (prospetto.getProspettoGradualita() != null
						&& prospetto.getProspettoGradualita().getnAssunzioniLavPreTrasf() != null
						&& prospetto.getProspettoGradualita().getnAssunzioniLavPreTrasf().intValue() != 0)
				|| (prospetto.getProspettoGradualita() != null
						&& ProdisSrvUtil.isNotVoid(prospetto.getProspettoGradualita().getDataTrasformazione()))
				|| (prospetto.getProspettoGradualita() != null
						&& prospetto.getProspettoGradualita().getPercentuale() != null
						&& prospetto.getProspettoGradualita().getPercentuale().intValue() != 0)) {

			if (ProdisSrvUtil.isVoid(prospetto.getProspettoGradualita().getDataAtto())
					|| ProdisSrvUtil.isVoid(prospetto.getProspettoGradualita().getEstremiAtto())
					|| (prospetto.getProspettoGradualita().getnAssunzioniLavPreTrasf() == null
							|| prospetto.getProspettoGradualita().getnAssunzioniLavPreTrasf().intValue() == 0)
					|| ProdisSrvUtil.isVoid(prospetto.getProspettoGradualita().getDataTrasformazione())
					|| (prospetto.getProspettoGradualita().getPercentuale() == null
							|| prospetto.getProspettoGradualita().getPercentuale().intValue() == 0)) {
				apiErrors.add(MsgProdis.PROPROE0071.getError());
			}
		}
		if (ProdisSrvUtil.isVoid(prospetto.getFlgSospensionePerMobilita())) {
			apiErrors.add(MsgProdis.PROPROE0072.getError());
		}
		if (ProdisSrvUtil.isVoid(prospetto.getdFineSospensioneQ1())
				&& ProdisSrvUtil.isNotVoid(prospetto.getFlgSospensionePerMobilita())
				&& "S".equalsIgnoreCase(prospetto.getFlgSospensionePerMobilita())) {
			apiErrors.add(MsgProdis.PROPROE0073.getError());
		} else if (ProdisSrvUtil.isNotVoid(prospetto.getdFineSospensioneQ1())
				&& ProdisSrvUtil.isNotVoid(prospetto.getFlgSospensionePerMobilita())
				&& "N".equalsIgnoreCase(prospetto.getFlgSospensionePerMobilita())) {
			apiErrors.add(MsgProdis.PROPROE0074.getError());
		}

		// sezione assunzioni pubbl selezione
		// *****************************************************************

		if (prospetto.getAssPubbliche() != null && prospetto.getAssPubbliche().size() > 0) {
			for (AssPubbliche ass : prospetto.getAssPubbliche()) {
				if (ass.getRegione() == null
						|| (ass.getRegione() != null && ProdisSrvUtil.isVoid(ass.getRegione().getId()))
						|| ProdisSrvUtil.isVoid(ass.getSaldoDisabili())
						|| ProdisSrvUtil.isVoid(ass.getSaldoExArt18())) {
					apiErrors.add(MsgProdis.PROPROE0075.getError());
				}
			}
		}
	}
}
