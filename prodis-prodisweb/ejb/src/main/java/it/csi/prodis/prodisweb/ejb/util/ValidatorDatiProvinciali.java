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
/*
 * 
 */
package it.csi.prodis.prodisweb.ejb.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import it.csi.prodis.prodisweb.ejb.business.be.dad.DatiProvincialiDad;
import it.csi.prodis.prodisweb.ejb.entity.ProTComune;
import it.csi.prodis.prodisweb.lib.dto.ApiError;
import it.csi.prodis.prodisweb.lib.dto.common.DecodificaGenerica;
import it.csi.prodis.prodisweb.lib.dto.error.MsgProdis;
import it.csi.prodis.prodisweb.lib.dto.prospetto.DatiProvinciali;
import it.csi.prodis.prodisweb.lib.dto.prospetto.LavoratoriInForza;
import it.csi.prodis.prodisweb.lib.dto.prospetto.PartTime;
import it.csi.prodis.prodisweb.lib.dto.prospetto.Prospetto;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProspettoProvincia;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProvIntermittenti;

public class ValidatorDatiProvinciali extends ValidatorUtil {

	DatiProvincialiDad datiProvincialiDad;

	public ValidatorDatiProvinciali(DatiProvincialiDad datiProvincialiDad) {

		super();

		this.datiProvincialiDad = datiProvincialiDad;

	}

	private Long recuperaIdComuneDaDecodifica(String codice, String descrizione) {

		Long id = null;

		List<DecodificaGenerica> decode = datiProvincialiDad.getComuneValido(codice, descrizione);
		if (decode != null && decode.size() == 1) {
			id = decode.get(0).getIdDecodifica();
		}

		return id;
	}

	public boolean checkComuneValorizzato(String codice, String descrizione) {

		if (codice == null || "".equalsIgnoreCase(codice) || descrizione == null || "".equalsIgnoreCase(descrizione)) {
			return false;
		}
		return true;
	}

	public boolean checkScadenzaComune(BigDecimal id) {

		ProTComune comune = datiProvincialiDad.getComuneById(id.longValue());

		if (comune.getDtFine() == null || comune.getDtFine().getTime() > new Date().getTime()) {
			return true;
		}
		return false;
	}

	public void validaInBozza(DatiProvinciali datiProvinciali, List<ApiError> apiErrors) {

		obbligatorietaMinima(datiProvinciali, apiErrors);

		controlliFormali(datiProvinciali, apiErrors);

		if (apiErrors == null || apiErrors.size() == 0) {
			controlliMinimiCampiValorizzati(datiProvinciali, apiErrors);
		}

		/*
		 * Nel salva in bozza non bisogna fare controlli su : - Convenzione - Esonero
		 * autorizzato - Esonero autocertificato - Gradualita'
		 */
//		if (datiProvinciali.getProvConvenzione() != null
//				&& datiProvinciali.getProvConvenzione().getStatoConcessione() != null
//				&& datiProvinciali.getProvConvenzione().getAssunzioneProtetta() == null) {
//
//			apiErrors.add(MsgProdis.PRODPRE0054.getError());
//
//		}
//		if (datiProvinciali.getProvConvenzione() != null
//				&& (datiProvinciali.getProvConvenzione().getNumLavPrevConvQ2() == null
//				|| datiProvinciali.getProvConvenzione().getNumLavPrevConvQ2().intValue() <= 0)) {
//			
//			apiErrors.add(MsgProdis.PRODPRE0058.getError());
//			
//		}
//		if (datiProvinciali.getProvEsoneroAutocert() != null
//				&& (datiProvinciali.getProvEsoneroAutocert().getnLavEsoneroAutocert() == null
//						|| datiProvinciali.getProvEsoneroAutocert().getnLavEsoneroAutocert().intValue() <= 0)) {
//
//			apiErrors.add(MsgProdis.PRODPRE0044.getError());
//
//		}

		/* questo controllo viene effettuato da un'altra e non nel salva in bozza */
//		ProspettoProvincia prospettoProvincia = datiProvincialiDad.getProspettoProvinciaById(datiProvinciali.getId());
//
//		Prospetto prospetto = datiProvincialiDad.getProspetto(prospettoProvincia.getIdProspetto());
//
//		if (prospetto.getFlgSospensionePerMobilita() != null && prospetto.getFlgSospensionePerMobilita().equals("N")
//				&& datiProvinciali.getProvSospensione() != null
//				&& datiProvinciali.getProvSospensione().getStatoConcessione() != null
//				&& datiProvinciali.getProvSospensione().getStatoConcessione().getDescStatoConcessione() != null
//				&& datiProvinciali.getProvSospensione().getStatoConcessione().getDescStatoConcessione()
//						.equals("Approvata/concessa")
//				&& datiProvinciali.getProvSospensione().getCausaSospensione() != null
//				&& datiProvinciali.getProvSospensione().getCausaSospensione().getDesCausaSospensione() != null
//				&& datiProvinciali.getProvSospensione().getCausaSospensione().getDesCausaSospensione()
//						.equals("Mobilità")) {
//			apiErrors.add(MsgProdis.PRODPRE0064.getError());
//			apiErrors.add(MsgProdis.PRODPRE0069.getError());
//		}

	}

	public List<ApiError> validaConfermaEProsegui(DatiProvinciali datiProvinciali, List<ApiError> apiErrors) {

		if ((datiProvinciali.getProvConvenzione() != null
				&& datiProvinciali.getProvConvenzione().getStatoConcessione() != null
				&& datiProvinciali.getProvConvenzione().getStatoConcessione().getId() == 2L)
				|| (datiProvinciali.getProvEsonero() != null
						&& datiProvinciali.getProvEsonero().getStatoConcessione() != null
						&& datiProvinciali.getProvEsonero().getStatoConcessione().getId() == 2L)) {

			/*
			 * TEST di VULNERABILITA'.... NON TOCCARE ....
			 */
//			apiErrors = new ArrayList<ApiError>();

			if (datiProvinciali.getProvConvenzione() != null
					&& datiProvinciali.getProvConvenzione().getStatoConcessione() != null
					&& datiProvinciali.getProvConvenzione().getStatoConcessione().getCodStatoConcessione() != null
					&& datiProvinciali.getProvConvenzione().getStatoConcessione().getCodStatoConcessione().equals("F")
					&& datiProvinciali.getProvConvenzione().getAssunzioneProtetta() == null) {

				apiErrors.add(MsgProdis.PRODPRE0054.getError());

			}

		}

		if (datiProvinciali.getProvSospensione() != null
				&& datiProvinciali.getProvSospensione().getCausaSospensione() != null
				&& datiProvinciali.getProvSospensione().getCausaSospensione().getCodCausaSospensione() != null
				&& datiProvinciali.getProvSospensione().getCausaSospensione().getCodCausaSospensione().equals("B")
				&& datiProvinciali.getProvSospensione().getStatoConcessione() != null
				&& datiProvinciali.getProvSospensione().getStatoConcessione().getCodStatoConcessione() != null
				&& datiProvinciali.getProvSospensione().getStatoConcessione().getCodStatoConcessione().equals("E")) {

			ProspettoProvincia pp = datiProvincialiDad.getProspettoProvinciaById(datiProvinciali.getId());

			Prospetto pr = datiProvincialiDad.getProspetto(pp.getIdProspetto());

			if (pr.getFlgSospensionePerMobilita().equals("N")) {

				apiErrors.add(MsgProdis.PRODPRE0064.getError());

			}

		}

		if (apiErrors == null || apiErrors.size() == 0) {

			obbligatorietaAggiuntiva(datiProvinciali, apiErrors);

			controlliAggiuntivi(datiProvinciali, apiErrors);

		}

		return apiErrors;

	}

	private void controlliAggiuntivi(DatiProvinciali datiProvinciali, List<ApiError> apiErrors) {

		if (datiProvinciali.getProspettoProvSede() != null) {

			if (datiProvinciali.getProspettoProvSede().getComune() == null
					|| (!checkComuneValorizzato(datiProvinciali.getProspettoProvSede().getComune().getCodComuneMin(),
							datiProvinciali.getProspettoProvSede().getComune().getDsProTComune()))) {

				apiErrors.add(MsgProdis.PROPROE0018.getError());

			}

			if (datiProvinciali.getProspettoProvincia() != null
					&& datiProvinciali.getProspettoProvincia().getId() != null) {

				ProspettoProvincia prospettoProvincia = datiProvincialiDad
						.getProspettoProvinciaById(datiProvinciali.getProspettoProvincia().getId());

				Long idProvincia = prospettoProvincia.getProvincia().getId();

				if (datiProvinciali.getProspettoProvSede() != null
						&& datiProvinciali.getProspettoProvSede().getComune() != null
						&& datiProvinciali.getProspettoProvSede().getComune().getCodComuneMin() != null
						&& datiProvinciali.getProspettoProvSede().getComune().getDsProTComune() != null) {

					List<ProTComune> comuniDb = datiProvincialiDad.getComuni(idProvincia,
							datiProvinciali.getProspettoProvSede().getComune().getCodComuneMin(),
							datiProvinciali.getProspettoProvSede().getComune().getDsProTComune(), new Date());

					if (comuniDb == null || comuniDb.size() == 0) {

						String errore = "Nessun comune trovato nella provincia di "
								+ prospettoProvincia.getProvincia().getDsProTProvincia()
								+ " per codice e/o descrizione inseriti.";

						apiErrors.add(new ApiError("PRO-DPR-E-0069", errore));

					}

					List<DecodificaGenerica> comuneDecode = datiProvincialiDad.getComuneValido(
							datiProvinciali.getProspettoProvSede().getComune().getCodComuneMin(),
							datiProvinciali.getProspettoProvSede().getComune().getDsProTComune());

					if (comuneDecode == null || comuneDecode.size() == 0) {

						apiErrors.add(MsgProdis.PRODPRE0068.getError());

					}

					if (comuneDecode != null && comuneDecode.size() > 1) {

						apiErrors.add(MsgProdis.PRODPRE0067.getError());

					}

				}

			}

			if (datiProvinciali.getProspettoProvSede().getCap() == null) {

				apiErrors.add(MsgProdis.PRODPRE0070.getError());

			}

			if (datiProvinciali.getProspettoProvSede().getIndirizzo() == null) {

				apiErrors.add(MsgProdis.PRODPRE0071.getError());

			}

			if (datiProvinciali.getProspettoProvSede().getTelefono() == null
					&& datiProvinciali.getProspettoProvSede().getFax() == null
					&& datiProvinciali.getProspettoProvSede().getEmail() == null) {

				apiErrors.add(MsgProdis.PRODPRE0072.getError());

			}

		}

		//////////////////////

		int numLavoratoriComputabiliCatD = 0;

		int numLavoratoriComputabiliCatD_Centralinisti = 0;

		int numLavoratoriComputabiliCatD_Terapisti = 0;

		int numLavoratoriComputabiliCatDSomministrati = 0;

		int numLavoratoriComputabiliCatDConvenzione = 0;

		boolean findCodiceMinAssunzioneProtetta = true;

		List<LavoratoriInForza> lavoratori = datiProvincialiDad
				.getLavoratoriInForzaByIdProspettoProv(datiProvinciali.getId().longValue());

		// LAVORATORI DISABILI

		if (lavoratori != null && lavoratori.size() > 0) {

			for (LavoratoriInForza lav : lavoratori) {

				if (lav.getAssunzioneProtetta() != null) {

					String codiceMinisterialeAssunzioneProtetta = lav.getAssunzioneProtetta()
							.getCodAssunzioneProtetta();

					if (lav.getCategoriaSoggetto() != null
							&& lav.getCategoriaSoggetto().equalsIgnoreCase(ConstantsProdis.CATEGORIA_SOGGETTO_D)
							&& !ValidatorUtil.isAssunzioneProtettaSomministratiOrConvenzioni(
									codiceMinisterialeAssunzioneProtetta)) {

						numLavoratoriComputabiliCatD++;

					}

					if (lav.getCategoriaSoggetto() != null
							&& lav.getCategoriaSoggetto().equalsIgnoreCase(ConstantsProdis.CATEGORIA_SOGGETTO_D)
							&& ValidatorUtil.isAssunzioneProtettaCentralinisti(codiceMinisterialeAssunzioneProtetta)) {

						numLavoratoriComputabiliCatD_Centralinisti++;

					}

					if (lav.getCategoriaSoggetto() != null
							&& lav.getCategoriaSoggetto().equalsIgnoreCase(ConstantsProdis.CATEGORIA_SOGGETTO_D)
							&& ValidatorUtil.isAssunzioneProtettaTerapisti(codiceMinisterialeAssunzioneProtetta)) {

						numLavoratoriComputabiliCatD_Terapisti++;

					}

					if (lav.getCategoriaSoggetto() != null
							&& lav.getCategoriaSoggetto().equalsIgnoreCase(ConstantsProdis.CATEGORIA_SOGGETTO_D)
							&& ValidatorUtil.isAssunzioneProtettaConvenzione(codiceMinisterialeAssunzioneProtetta)) {

						numLavoratoriComputabiliCatDConvenzione++;

					}

					if (lav.getCategoriaSoggetto() != null
							&& lav.getCategoriaSoggetto().equalsIgnoreCase(ConstantsProdis.CATEGORIA_SOGGETTO_D)
							&& ValidatorUtil.isAssunzioneProtettaSomministrati(codiceMinisterialeAssunzioneProtetta)) {

						numLavoratoriComputabiliCatDSomministrati++;

					}

				} else {

					findCodiceMinAssunzioneProtetta = false;

					break;

				}

			}

		}

		// PART TIME e INTERMITTENTI

		if (findCodiceMinAssunzioneProtetta) {

			List<PartTime> partTime = datiProvincialiDad
					.getPartTimeByIdProspettoProv(datiProvinciali.getId().longValue());

			List<ProvIntermittenti> intermittenti = datiProvincialiDad
					.getProvIntermittentiByIdProspettoProv(datiProvinciali.getId().longValue());

			int sommaDisabiliFullTimeAndPartTime = (datiProvinciali.getnDisabiliInForza() != null
					&& datiProvinciali.getnDisabiliInForza().intValue() != 0)
							? datiProvinciali.getnDisabiliInForza().intValue()
									+ ValidatorUtil.getPTNumberByTipologiaLavoratore(partTime, intermittenti,
											ConstantsProdis.TIPOLOGIA_LAVORATORE_PT_DISABILI)
							: 0 + ValidatorUtil.getPTNumberByTipologiaLavoratore(partTime, intermittenti,
									ConstantsProdis.TIPOLOGIA_LAVORATORE_PT_DISABILI);

			int sommaCentralinistiFullTimeAndPartTime = (datiProvinciali.getnCentralTelefoNonvedenti() != null
					&& datiProvinciali.getnCentralTelefoNonvedenti().intValue() != 0)
							? datiProvinciali.getnCentralTelefoNonvedenti().intValue()
									+ ValidatorUtil.getPTNumberByTipologiaLavoratore(partTime, intermittenti,
											ConstantsProdis.TIPOLOGIA_LAVORATORE_PT_OPERATORI)
							: 0 + ValidatorUtil.getPTNumberByTipologiaLavoratore(partTime, intermittenti,
									ConstantsProdis.TIPOLOGIA_LAVORATORE_PT_OPERATORI);

			int sommaTerapistiFullTimeAndPartTime = (datiProvinciali.getnTerariabMassofisNonved() != null
					&& datiProvinciali.getnTerariabMassofisNonved().intValue() != 0)
							? datiProvinciali.getnTerariabMassofisNonved().intValue()
									+ ValidatorUtil.getPTNumberByTipologiaLavoratore(partTime, intermittenti,
											ConstantsProdis.TIPOLOGIA_LAVORATORE_PT_TERAPISTI)
							: 0 + ValidatorUtil.getPTNumberByTipologiaLavoratore(partTime, intermittenti,
									ConstantsProdis.TIPOLOGIA_LAVORATORE_PT_TERAPISTI);

			int sommaDisabiliFullTimeAndPartTimeSomministrati = (datiProvinciali.getnSomministratiFt() != null
					&& datiProvinciali.getnSomministratiFt().intValue() != 0)
							? datiProvinciali.getnSomministratiFt().intValue()
									+ ValidatorUtil.getPTNumberByTipologiaLavoratore(partTime, intermittenti,
											ConstantsProdis.TIPOLOGIA_LAVORATORE_PT_DISABILI_SOMMINISTRATI)
							: 0 + ValidatorUtil.getPTNumberByTipologiaLavoratore(partTime, intermittenti,
									ConstantsProdis.TIPOLOGIA_LAVORATORE_PT_DISABILI_SOMMINISTRATI);

			int sommaDisabiliFullTimeAndPartTimeConvenzione = (datiProvinciali.getnConvenzioni12bis14Ft() != null
					&& datiProvinciali.getnConvenzioni12bis14Ft().intValue() != 0)
							? datiProvinciali.getnConvenzioni12bis14Ft().intValue()
									+ ValidatorUtil.getPTNumberByTipologiaLavoratore(partTime, intermittenti,
											ConstantsProdis.TIPOLOGIA_LAVORATORE_PT_DISABILI_CONVENZIONE)
							: 0 + ValidatorUtil.getPTNumberByTipologiaLavoratore(partTime, intermittenti,
									ConstantsProdis.TIPOLOGIA_LAVORATORE_PT_DISABILI_CONVENZIONE);

			if (numLavoratoriComputabiliCatD < (sommaDisabiliFullTimeAndPartTime + sommaCentralinistiFullTimeAndPartTime
					+ sommaTerapistiFullTimeAndPartTime)) {

				apiErrors.add(MsgProdis.PRODPRE0081.getError());

			}

			if (numLavoratoriComputabiliCatD_Terapisti < sommaTerapistiFullTimeAndPartTime) {

				apiErrors.add(MsgProdis.PRODPRE0082.getError());

			}

			if (numLavoratoriComputabiliCatD_Centralinisti < sommaCentralinistiFullTimeAndPartTime) {

				apiErrors.add(MsgProdis.PRODPRE0083.getError());

			}

			if (numLavoratoriComputabiliCatDSomministrati < sommaDisabiliFullTimeAndPartTimeSomministrati) {

				apiErrors.add(MsgProdis.PRODPRE0084.getError());

			}

			if (numLavoratoriComputabiliCatDConvenzione < sommaDisabiliFullTimeAndPartTimeConvenzione) {

				apiErrors.add(MsgProdis.PRODPRE0085.getError());

			}

		} else {

			apiErrors.add(MsgProdis.PRODPRE0080.getError());

		}

		// LAVORATORI CATEGORIE PROTETTE

		int numLavL68CatC = 0;

		for (LavoratoriInForza lav : lavoratori) {

			if (lav.getCategoriaSoggetto().equals(ConstantsProdis.CATEGORIA_SOGGETTO_C)) {

				numLavL68CatC++;

			}

		}

		int numCatProtette = datiProvinciali.getnCateProtForza() != null
				? datiProvinciali.getnCateProtForza().intValue()
				: 0;

		int numCatDisabiliL68 = datiProvinciali.getnCateProtForzaCntDis() != null
				? Integer.valueOf(datiProvinciali.getnCateProtForzaCntDis())
				: 0;

		if (numLavL68CatC < numCatProtette + numCatDisabiliL68) {

			apiErrors.add(MsgProdis.PRODPRE0086.getError());

		}

		int numeroCategorieProtette17_01_2000 = datiProvinciali.getnCateProtForzaA17012000() != null
				? datiProvinciali.getnCateProtForzaA17012000().intValue()
				: 0;

		if (numCatProtette < numeroCategorieProtette17_01_2000) {

			apiErrors.add(MsgProdis.PRODPRE0087.getError());

		}

		int numeroCategorieProtetteEsubero = datiProvinciali.getnCateProtForzaEsubero() != null
				? Integer.parseInt(datiProvinciali.getnCateProtForzaEsubero())
				: 0;

		if (numeroCategorieProtetteEsubero < numCatDisabiliL68) {

			apiErrors.add(MsgProdis.PRODPRE0088.getError());

		}

		int numLavL68CatCPrima17012000 = 0;

		for (LavoratoriInForza lav : lavoratori) {
			if (lav.getCategoriaSoggetto() != null
					&& lav.getCategoriaSoggetto().equals(ConstantsProdis.CATEGORIA_SOGGETTO_C)
					&& lav.getDataInizioRapporto() != null && lav.getDataInizioRapporto().getTime() < ProdisSrvUtil
							.convertiStringaInData(ConstantsProdis.DATA_17_01_2000).getTime()) {
				numLavL68CatCPrima17012000++;
			}
		}

		if (numLavL68CatCPrima17012000 < numeroCategorieProtette17_01_2000) {

			apiErrors.add(MsgProdis.PRODPRE0089.getError());

		}

		// CONCLUSIONE LAVORATORI

		ProspettoProvincia prospettoProvincia = datiProvincialiDad.getProspettoProvinciaById(datiProvinciali.getId());

		Prospetto prospetto = datiProvincialiDad.getProspetto(prospettoProvincia.getIdProspetto());

		for (LavoratoriInForza lav : lavoratori) {

			if ("N".equalsIgnoreCase(lav.getFlgCompletato())) {

				apiErrors.add(MsgProdis.PRODPRE0090.getError());

				break;

			}

			if (lav.getDataFineRapporto() != null && prospetto.getDataRiferimentoProspetto() != null) {

				DateTime dataFineRapportoLavoratore = new DateTime(lav.getDataFineRapporto());

				DateTime dataRiferimentoProspetto = new DateTime(prospetto.getDataRiferimentoProspetto());

				if (dataFineRapportoLavoratore.isBefore(dataRiferimentoProspetto)) {

					lav.setFlgCompletato("N");

					StringBuffer message = new StringBuffer();
					message.append("Il lavoratore ");
					message.append(lav.getCognome()).append(" ");
					message.append(lav.getNome()).append(" (cf:");
					message.append(lav.getCodiceFiscale());
					message.append(") ");
					message.append("presenta una data fine rapporto inferiore alla data riferimento del prospetto. ");

					apiErrors.add(new ApiError("PRO-DPR-E-0091", message.toString()));

				}

			}

		}

	}

	private void obbligatorietaAggiuntiva(DatiProvinciali datiProvinciali, List<ApiError> apiErrors) {

		ProspettoProvincia prospettoProvincia = datiProvincialiDad.getProspettoProvinciaById(datiProvinciali.getId());

		Prospetto prospetto = datiProvincialiDad.getProspetto(prospettoProvincia.getIdProspetto());

		if (datiProvinciali.getProspettoProvSede() != null) {
			if (datiProvinciali.getProspettoProvSede().getComune() == null
					|| (!checkComuneValorizzato(datiProvinciali.getProspettoProvSede().getComune().getCodComuneMin(),
							datiProvinciali.getProspettoProvSede().getComune().getDsProTComune()))) {
				apiErrors.add(MsgProdis.PROPROE0018.getError());
			}
			if (datiProvinciali.getProspettoProvSede().getComune() != null
					&& datiProvinciali.getProspettoProvSede().getComune().getId() != null && !checkScadenzaComune(
							new BigDecimal(datiProvinciali.getProspettoProvSede().getComune().getId()))) {
				apiErrors.add(MsgProdis.PROPROE0019.getError());
			}
			if (datiProvinciali.getProspettoProvSede().getCap() == null
					|| "".equalsIgnoreCase(datiProvinciali.getProspettoProvSede().getCap())) {
				apiErrors.add(MsgProdis.PRODPRE0047.getError());
			}
			if (datiProvinciali.getProspettoProvSede().getIndirizzo() == null
					|| "".equalsIgnoreCase(datiProvinciali.getProspettoProvSede().getIndirizzo())) {
				apiErrors.add(MsgProdis.PRODPRE0048.getError());
			}
			if ((datiProvinciali.getProspettoProvSede().getTelefono() == null
					|| "".equalsIgnoreCase(datiProvinciali.getProspettoProvSede().getTelefono()))
					&& (datiProvinciali.getProspettoProvSede().getFax() == null
							|| "".equalsIgnoreCase(datiProvinciali.getProspettoProvSede().getFax()))
					&& (datiProvinciali.getProspettoProvSede().getEmail() == null
							|| "".equalsIgnoreCase(datiProvinciali.getProspettoProvSede().getEmail()))) {
				apiErrors.add(MsgProdis.PRODPRE0049.getError());
			}
			if (datiProvinciali.getProspettoProvSede().getCognomeReferente() == null
					|| "".equalsIgnoreCase(datiProvinciali.getProspettoProvSede().getCognomeReferente())) {
				apiErrors.add(MsgProdis.PRODPRE0050.getError());
			}
			if (datiProvinciali.getProspettoProvSede().getNomeReferente() == null
					|| "".equalsIgnoreCase(datiProvinciali.getProspettoProvSede().getNomeReferente())) {
				apiErrors.add(MsgProdis.PRODPRE0051.getError());
			}
		}
		if (datiProvinciali.getProvConvenzione() != null) {
			if ((datiProvinciali.getProvConvenzione().getStatoConcessione() == null
					|| datiProvinciali.getProvConvenzione().getStatoConcessione().getId() == null)
					&& datiProvinciali.getProvConvenzione().getAssunzioneProtetta() != null
					&& datiProvinciali.getProvConvenzione().getAssunzioneProtetta().getId() != null) {
				apiErrors.add(MsgProdis.PRODPRE0052.getError());
			}
			if (datiProvinciali.getProvConvenzione().getEstremiAtto() == null
					&& datiProvinciali.getProvConvenzione().getStatoConcessione() != null
					&& datiProvinciali.getProvConvenzione().getStatoConcessione().getId() != null
					&& datiProvinciali.getProvConvenzione().getStatoConcessione().getId()
							.equals(ConstantsProdis.STATO_CONCESSIONE_APPROVATA_CONCESSA)) {
				apiErrors.add(MsgProdis.PRODPRE0053.getError());
			}
			if (datiProvinciali.getProvConvenzione().getDataAtto() == null
					&& datiProvinciali.getProvConvenzione().getStatoConcessione() != null
					&& datiProvinciali.getProvConvenzione().getStatoConcessione().getId() != null
					&& datiProvinciali.getProvConvenzione().getStatoConcessione()
							.getId() == ConstantsProdis.STATO_CONCESSIONE_APPROVATA_CONCESSA) {
				apiErrors.add(MsgProdis.PRODPRE0055.getError());
			}
			if ((datiProvinciali.getProvConvenzione().getStatoConcessione() != null
					&& datiProvinciali.getProvConvenzione().getStatoConcessione().getId() != null)
					&& (datiProvinciali.getProvConvenzione().getAssunzioneProtetta() == null
							|| datiProvinciali.getProvConvenzione().getAssunzioneProtetta().getId() == null)) {
				apiErrors.add(MsgProdis.PRODPRE0054.getError());
			}
			if (datiProvinciali.getProvConvenzione().getDataStipula() == null
					&& (datiProvinciali.getProvConvenzione().getStatoConcessione() != null
							&& datiProvinciali.getProvConvenzione().getStatoConcessione().getId() != null
							&& datiProvinciali.getProvConvenzione().getStatoConcessione()
									.getId() == ConstantsProdis.STATO_CONCESSIONE_APPROVATA_CONCESSA)) {
				apiErrors.add(MsgProdis.PRODPRE0056.getError());
			}
			if (datiProvinciali.getProvConvenzione().getDataScadenza() == null
					&& (datiProvinciali.getProvConvenzione().getStatoConcessione() != null
							&& datiProvinciali.getProvConvenzione().getStatoConcessione().getId() != null
							&& datiProvinciali.getProvConvenzione().getStatoConcessione()
									.getId() == ConstantsProdis.STATO_CONCESSIONE_APPROVATA_CONCESSA)) {
				apiErrors.add(MsgProdis.PRODPRE0057.getError());
			}
			if (datiProvinciali.getProvConvenzione().getStatoConcessione() != null
					&& datiProvinciali.getProvConvenzione().getStatoConcessione().getCodStatoConcessione() != null
					&& datiProvinciali.getProvConvenzione().getStatoConcessione().getCodStatoConcessione()
							.equalsIgnoreCase(ConstantsProdis.STATO_CONCESSIONE_E)
					&& datiProvinciali.getProvConvenzione().getNumLavPrevConvQ2() == null) {
				apiErrors.add(MsgProdis.PRODPRE0058.getError());
			}
		}
		if (datiProvinciali.getProvEsonero() != null) {
			if (datiProvinciali.getProvEsonero().getStatoConcessione() != null
					&& datiProvinciali.getProvEsonero().getStatoConcessione().getId() != null
					&& datiProvinciali.getProvEsonero().getStatoConcessione().getCodStatoConcessione()
							.equalsIgnoreCase(ConstantsProdis.STATO_CONCESSIONE_E)) {

				if (datiProvinciali.getProvEsonero().getDataAtto() == null) {
					apiErrors.add(MsgProdis.PRODPRE0059.getError());
				}
				if (ProdisSrvUtil.isVoid(datiProvinciali.getProvEsonero().getEstremiAtto())) {
					apiErrors.add(MsgProdis.PRODPRE0053.getError());
				}
				if (datiProvinciali.getProvEsonero().getDataAttoFinoAl() == null) {
					apiErrors.add(MsgProdis.PRODPRE0061.getError());
				}
				if (datiProvinciali.getProvEsonero().getPercentuale() == null) {
					apiErrors.add(MsgProdis.PRODPRE0062.getError());
				}
				if (datiProvinciali.getProvEsonero().getnLavoratoriEsonero() == null) {
					apiErrors.add(MsgProdis.PRODPRE0063.getError());
				}
			}
		}

		if (datiProvinciali.getProvSospensione() != null) {

			if (datiProvinciali.getProvSospensione().getnLavoratori() != null
					&& datiProvinciali.getProvSospensione().getnLavoratori().intValue() == 0) {
				apiErrors.add(MsgProdis.PRODPRE0065.getError());
			} else if ((datiProvinciali.getProvSospensione().getStatoConcessione() != null
					&& datiProvinciali.getProvSospensione().getStatoConcessione().getId() != null)
					|| (datiProvinciali.getProvSospensione().getCausaSospensione() != null
							&& datiProvinciali.getProvSospensione().getCausaSospensione().getId() != null)
					|| datiProvinciali.getProvSospensione().getnLavoratori() != null) {
				if ((datiProvinciali.getProvSospensione().getStatoConcessione() != null
						&& datiProvinciali.getProvSospensione().getStatoConcessione().getId() != null)
						&& (datiProvinciali.getProvSospensione().getCausaSospensione() != null
								&& datiProvinciali.getProvSospensione().getCausaSospensione().getId() != null)
						&& datiProvinciali.getProvSospensione().getnLavoratori() != null) {
					if (datiProvinciali.getProvSospensione().getCausaSospensione().getId()
							.intValue() == ConstantsProdis.CAUSA_SOSPENSIONE_B_MOBILITA
							&& datiProvinciali.getProvSospensione().getCausaSospensione().getId()
									.intValue() == ConstantsProdis.STATO_SOSPENSIONE_APPROVATA_CONCESSA) {
						if ("".equalsIgnoreCase(prospetto.getFlgSospensionePerMobilita())
								|| "N".equalsIgnoreCase(prospetto.getFlgSospensionePerMobilita())) {
							apiErrors.add(MsgProdis.PRODPRE0064.getError());
						}
					}
				} else {
					apiErrors.add(MsgProdis.PRODPRE0065.getError());
				}

			}
			if (datiProvinciali.getProvSospensione().getDataFineSospensioneQ2() == null
					&& (datiProvinciali.getProvSospensione().getStatoConcessione() != null
							&& datiProvinciali.getProvSospensione().getStatoConcessione().getId() != null)
					&& (datiProvinciali.getProvSospensione().getCausaSospensione() != null
							&& datiProvinciali.getProvSospensione().getCausaSospensione().getId() != null)
					&& datiProvinciali.getProvSospensione().getnLavoratori() != null) {
				apiErrors.add(MsgProdis.PRODPRE0066.getError());
			}
		}

		if (!isGradualitaPresente(prospetto)) {
			if (datiProvinciali.getProvGradualita() != null
					&& datiProvinciali.getProvGradualita().getnAssunzioniEffDopoTrasf() != null) {
				apiErrors.add(MsgProdis.PRODPRE0073.getError());
			}
		} else {
			if (datiProvinciali.getProvGradualita() == null || (datiProvinciali.getProvGradualita() != null
					&& datiProvinciali.getProvGradualita().getnAssunzioniEffDopoTrasf() == null)) {
				apiErrors.add(MsgProdis.PRODPRE0074.getError());
			}

		}

	}

	private void controlliMinimiCampiValorizzati(DatiProvinciali datiProvinciali, List<ApiError> apiErrors) {

		ProspettoProvincia prospettoProvincia = datiProvincialiDad.getProspettoProvinciaById(datiProvinciali.getId());

		Prospetto prospetto = datiProvincialiDad.getProspetto(prospettoProvincia.getIdProspetto());

		if (datiProvinciali.getProspettoProvSede() != null && datiProvinciali.getProspettoProvSede().getEmail() != null
				&& !"".equals(datiProvinciali.getProspettoProvSede().getEmail())) {

			String regexMail = datiProvincialiDad.getParametroByNome(ConstantsProdis.PARAMETRO_REGEX_EMAIL);
			if (!ProdisSrvUtil.checkValore(datiProvinciali.getProspettoProvSede().getEmail(), regexMail)) {
				apiErrors.add(MsgProdis.PRODPRE0045.getError());
			}
		}
		if (datiProvinciali.getProspettoProvSede() != null && datiProvinciali.getProspettoProvSede().getComune() != null
				&& datiProvinciali.getProspettoProvSede().getComune().getId() == null
				&& datiProvinciali.getProspettoProvSede().getComune().getCodComuneMin() != null
				&& !"".equalsIgnoreCase(datiProvinciali.getProspettoProvSede().getComune().getCodComuneMin())
				&& datiProvinciali.getProspettoProvSede().getComune().getDsProTComune() != null
				&& !"".equalsIgnoreCase(datiProvinciali.getProspettoProvSede().getComune().getDsProTComune())) {
			Long id = recuperaIdComuneDaDecodifica(datiProvinciali.getProspettoProvSede().getComune().getCodComuneMin(),
					datiProvinciali.getProspettoProvSede().getComune().getDsProTComune());
			if (id != null) {
				datiProvinciali.getProspettoProvSede().getComune().setId(id);
			} else {
				apiErrors.add(MsgProdis.PRODPRE0099.getError());
			}
		}
		Date dataRiferimentoProspetto = prospetto.getDataRiferimentoProspetto();
//			Date dataRif = getDataRiferimento();
		if (datiProvinciali.getProvConvenzione() != null && datiProvinciali.getProvConvenzione().getDataAtto() != null
				&& ProdisSrvUtil.removeTime(datiProvinciali.getProvConvenzione().getDataAtto())
						.after(dataRiferimentoProspetto)) {
			apiErrors.add(MsgProdis.PRODPRE0075.getError());
		}

		if (datiProvinciali.getProvConvenzione() != null
				&& datiProvinciali.getProvConvenzione().getDataStipula() != null && dataRiferimentoProspetto != null) {
			if (ProdisSrvUtil.removeTime(datiProvinciali.getProvConvenzione().getDataStipula())
					.after(dataRiferimentoProspetto)) {
				apiErrors.add(MsgProdis.PRODPRE0076.getError());
			}

			if (datiProvinciali.getProvConvenzione().getDataScadenza() != null
					&& !ProdisSrvUtil.removeTime(datiProvinciali.getProvConvenzione().getDataScadenza())
							.after(datiProvinciali.getProvConvenzione().getDataStipula())) {
				apiErrors.add(MsgProdis.PRODPRE0046.getError());
			}
		}

//		Date dataRif = getDataRiferimento();
		if (datiProvinciali.getProvEsonero() != null && datiProvinciali.getProvEsonero().getDataAtto() != null
				&& ProdisSrvUtil.removeTime(datiProvinciali.getProvEsonero().getDataAtto())
						.after(dataRiferimentoProspetto)) {
			apiErrors.add(MsgProdis.PRODPRE0077.getError());
		}
		if (prospetto.getDatiAzienda() != null
				&& prospetto.getDatiAzienda().getDichiarante().getId() == ConstantsProdis.COD_DICHIARANTE_C
				&& (datiProvinciali.getProvEsoneroAutocert() != null
						&& datiProvinciali.getProvEsoneroAutocert().getDataAutocert() != null
						&& datiProvinciali.getProvEsoneroAutocert().getnLav60x1000() != null
						&& datiProvinciali.getProvEsoneroAutocert().getPercentualeEsAutocert() != null
						&& datiProvinciali.getProvEsoneroAutocert().getnLavEsoneroAutocert() != null)) {
			apiErrors.add(MsgProdis.PRODPRE0078.getError());
		}

//		Date dataRif = getDataRiferimento();
		if (datiProvinciali.getProvEsoneroAutocert() != null
				&& datiProvinciali.getProvEsoneroAutocert().getDataAutocert() != null) {
			String dataPros = ProdisSrvUtil.convertiDataInStringa(dataRiferimentoProspetto);
			if (ProdisSrvUtil.removeTime(datiProvinciali.getProvEsoneroAutocert().getDataAutocert())
					.after(dataRiferimentoProspetto)) {
				apiErrors.add(MsgProdis.PRODPRE0079.getError());
//				String errore = "Data autocertificazione deve essere minore o uguale al 31/12/"
//						+ ProdisSrvUtil.getAnnoFromDateString(ProdisSrvUtil.convertiStringaInData(dataPros));
//				apiErrors.add(new ApiError("PRO-DPR-E-0079", errore));

			}
		}
	}

//	private Date getDataRiferimento() {
//		Date dataRif = null;
//		try {
//			dataRif = new SimpleDateFormat("dd/MM/yyyy")
//					.parse("31/12/" + (Calendar.getInstance().get(Calendar.YEAR) - 1));
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		return dataRif;
//	}

	private void obbligatorietaMinima(DatiProvinciali datiProvinciali, List<ApiError> apiErrors) {

		if (datiProvinciali.getnTotaleLavoratDipendenti() == null) {
			apiErrors.add(MsgProdis.PRODPRE0032.getError());
		}
		if (datiProvinciali.getnDisabiliInForza() == null) {
			apiErrors.add(MsgProdis.PRODPRE0033.getError());
		}
		if (datiProvinciali.getnCentralTelefoNonvedenti() == null) {
			apiErrors.add(MsgProdis.PRODPRE0034.getError());
		}
		if (datiProvinciali.getnPostiPrevCentraliNonved() == null) {
			apiErrors.add(MsgProdis.PRODPRE0035.getError());
		}
		if (datiProvinciali.getnTerariabMassofisNonved() == null) {
			apiErrors.add(MsgProdis.PRODPRE0036.getError());
		}
		if (datiProvinciali.getnPostiPrevMassofisNonved() == null) {
			apiErrors.add(MsgProdis.PRODPRE0037.getError());
		}
		if (datiProvinciali.getnCateProtForza() == null) {
			apiErrors.add(MsgProdis.PRODPRE0038.getError());
		}
		if (datiProvinciali.getnCateProtForzaCntDis() == null
				|| "".equalsIgnoreCase(datiProvinciali.getnCateProtForzaCntDis())) {
			apiErrors.add(MsgProdis.PRODPRE0039.getError());
		}
		if (datiProvinciali.getnCateProtForzaEsubero() == null
				|| "".equalsIgnoreCase(datiProvinciali.getnCateProtForzaEsubero())) {
			apiErrors.add(MsgProdis.PRODPRE0040.getError());
		}
		// altre concessioni - esonero
		if (datiProvinciali.getProvEsonero() != null) {
			if (datiProvinciali.getProvEsonero().getStatoConcessione() == null
					|| (datiProvinciali.getProvEsonero().getStatoConcessione() != null
							&& datiProvinciali.getProvEsonero().getStatoConcessione().getId() == null)) {
				if (ProdisSrvUtil.isNotVoid(datiProvinciali.getProvEsonero().getDataAtto())
						|| ProdisSrvUtil.isNotVoid(datiProvinciali.getProvEsonero().getEstremiAtto())
						|| ProdisSrvUtil.isNotVoid(datiProvinciali.getProvEsonero().getDataAttoFinoAl())
						|| (datiProvinciali.getProvEsonero().getPercentuale() != null
								&& !datiProvinciali.getProvEsonero().getPercentuale().equals(new BigDecimal(0)))
						|| (datiProvinciali.getProvEsonero().getnLavoratoriEsonero() != null && !datiProvinciali
								.getProvEsonero().getnLavoratoriEsonero().equals(new BigDecimal(0)))) {
					apiErrors.add(MsgProdis.PRODPRE0041.getError());
				}
			}
		}
		// altre concessioni - esonero autocertificato
		if (datiProvinciali.getProvEsoneroAutocert() != null) {
			if (ProdisSrvUtil.isVoid(datiProvinciali.getProvEsoneroAutocert().getDataAutocert())
					&& (datiProvinciali.getProvEsoneroAutocert().getnLav60x1000() != null
							|| datiProvinciali.getProvEsoneroAutocert().getPercentualeEsAutocert() != null
							|| datiProvinciali.getProvEsoneroAutocert().getnLavEsoneroAutocert() != null)) {
				apiErrors.add(MsgProdis.PRODPRE0042.getError());
			}
			if (ProdisSrvUtil.isNotVoid(datiProvinciali.getProvEsoneroAutocert().getDataAutocert())) {
				if (datiProvinciali.getProvEsoneroAutocert().getnLav60x1000() == null
						|| datiProvinciali.getProvEsoneroAutocert().getnLav60x1000().equals(new BigDecimal(0))) {
					apiErrors.add(MsgProdis.PRODPRE0043.getError());
				}
				if (datiProvinciali.getProvEsoneroAutocert().getnLavEsoneroAutocert() == null || datiProvinciali
						.getProvEsoneroAutocert().getnLavEsoneroAutocert().equals(new BigDecimal(0))) {
					apiErrors.add(MsgProdis.PRODPRE0044.getError());
				}
			}
		}
		/* CONTROLLI SU CONVENZIONI */
		if (ProdisSrvUtil.isNotVoid(datiProvinciali.getProvConvenzione())
				&& (ProdisSrvUtil.isVoid(datiProvinciali.getProvConvenzione().getStatoConcessione())
						|| ProdisSrvUtil.isVoid(datiProvinciali.getProvConvenzione().getStatoConcessione().getId()))) {
			if (ProdisSrvUtil.isNotVoid(datiProvinciali.getProvConvenzione().getDataAtto())
					|| ProdisSrvUtil.isNotVoid(datiProvinciali.getProvConvenzione().getEstremiAtto())
					|| (ProdisSrvUtil.isNotVoid(datiProvinciali.getProvConvenzione().getAssunzioneProtetta())
							&& ProdisSrvUtil
									.isNotVoid(datiProvinciali.getProvConvenzione().getAssunzioneProtetta().getId()))
					|| ProdisSrvUtil.isNotVoid(datiProvinciali.getProvConvenzione().getDataStipula())
					|| ProdisSrvUtil.isNotVoid(datiProvinciali.getProvConvenzione().getDataScadenza())) {
				apiErrors.add(MsgProdis.PRODPRE0091.getError());
			}
		}

		/* CONTROLLI SU SOSPENSIONI */
		if (ProdisSrvUtil.isNotVoid(datiProvinciali.getProvSospensione())) {
			if (((ProdisSrvUtil.isNotVoid(datiProvinciali.getProvSospensione().getStatoConcessione())
					&& ProdisSrvUtil.isNotVoid(datiProvinciali.getProvSospensione().getStatoConcessione().getId()))
					|| (ProdisSrvUtil.isNotVoid(datiProvinciali.getProvSospensione().getCausaSospensione())
							&& ProdisSrvUtil
									.isNotVoid(datiProvinciali.getProvSospensione().getCausaSospensione().getId()))
					|| ProdisSrvUtil.isNotVoid(datiProvinciali.getProvSospensione().getDataFineSospensioneQ2()))
					&& ProdisSrvUtil.isVoid(datiProvinciali.getProvSospensione().getnLavoratori())) {
				apiErrors.add(MsgProdis.PRODPRE0092.getError());
			}
		}
	}

	private void controlliFormali(DatiProvinciali datiProvinciali, List<ApiError> apiErrors) {
		if (datiProvinciali == null) {
			apiErrors.add(MsgProdis.PROPROE0003.getError());
			return;
		}

		//////////////
		if (datiProvinciali.getnTotaleLavoratDipendenti() != null
				&& !datiProvinciali.getnTotaleLavoratDipendenti().equals(new BigDecimal(0))
				&& !ProdisSrvUtil.controllaNumero(datiProvinciali.getnTotaleLavoratDipendenti().intValue())) {
			apiErrors.add(MsgProdis.PRODPRE0001.getError());
		}

		if (datiProvinciali.getProspettoProvSede() != null) {
			if (ProdisSrvUtil.isNotVoid(datiProvinciali.getProspettoProvSede().getCap())
					&& !ProdisSrvUtil.controllaCAP(datiProvinciali.getProspettoProvSede().getCap())) {
				apiErrors.add(MsgProdis.PRODPRE0002.getError());

			}
			if (ProdisSrvUtil.isNotVoid(datiProvinciali.getProspettoProvSede().getTelefono())
					&& !ProdisSrvUtil.controllaTel(datiProvinciali.getProspettoProvSede().getTelefono())) {
				apiErrors.add(MsgProdis.PRODPRE0003.getError());

			}
			if (ProdisSrvUtil.isNotVoid(datiProvinciali.getProspettoProvSede().getFax())
					&& !ProdisSrvUtil.controllaTel(datiProvinciali.getProspettoProvSede().getFax())) {
				apiErrors.add(MsgProdis.PRODPRE0004.getError());

			}

		}

		// sezione telelavoro
		if (ProdisSrvUtil.isNotVoid(datiProvinciali.getnTelelavoroFt())
				&& !ProdisSrvUtil.controllaNumero(datiProvinciali.getnTelelavoroFt().intValue())) {
			apiErrors.add(MsgProdis.PRODPRE0005.getError());
		}
		// sezione disabili e cat. protette
		if (ProdisSrvUtil.isNotVoid(datiProvinciali.getnDisabiliInForza())
				&& !ProdisSrvUtil.controllaNumero(datiProvinciali.getnDisabiliInForza().intValue())) {
			apiErrors.add(MsgProdis.PRODPRE0006.getError());
		}
		if (ProdisSrvUtil.isNotVoid(datiProvinciali.getnCentralTelefoNonvedenti())
				&& !ProdisSrvUtil.controllaNumero(datiProvinciali.getnCentralTelefoNonvedenti().intValue())) {
			apiErrors.add(MsgProdis.PRODPRE0007.getError());
		}
		if (ProdisSrvUtil.isNotVoid(datiProvinciali.getnPostiPrevCentraliNonved())
				&& !ProdisSrvUtil.controllaNumero(datiProvinciali.getnPostiPrevCentraliNonved().intValue())) {
			apiErrors.add(MsgProdis.PRODPRE0008.getError());
		}
		if (ProdisSrvUtil.isNotVoid(datiProvinciali.getnTerariabMassofisNonved())
				&& !ProdisSrvUtil.controllaNumero(datiProvinciali.getnTerariabMassofisNonved().intValue())) {
			apiErrors.add(MsgProdis.PRODPRE0009.getError());
		}
		if (ProdisSrvUtil.isNotVoid(datiProvinciali.getnPostiPrevMassofisNonved())
				&& !ProdisSrvUtil.controllaNumero(datiProvinciali.getnPostiPrevMassofisNonved().intValue())) {
			apiErrors.add(MsgProdis.PRODPRE0010.getError());
		}
		if (ProdisSrvUtil.isNotVoid(datiProvinciali.getnCateProtForza())
				&& !ProdisSrvUtil.controllaNumero(datiProvinciali.getnCateProtForza().intValue())) {
			apiErrors.add(MsgProdis.PRODPRE0011.getError());
		}
		if (ProdisSrvUtil.isNotVoid(datiProvinciali.getnCateProtForzaA17012000())
				&& !ProdisSrvUtil.controllaNumero(datiProvinciali.getnCateProtForzaA17012000().intValue())) {
			apiErrors.add(MsgProdis.PRODPRE0012.getError());
		}
		if (ProdisSrvUtil.isNotVoid(datiProvinciali.getnSomministratiFt())
				&& !ProdisSrvUtil.controllaNumero(datiProvinciali.getnSomministratiFt().intValue())) {
			apiErrors.add(MsgProdis.PRODPRE0013.getError());
		}
		if (ProdisSrvUtil.isNotVoid(datiProvinciali.getnConvenzioni12bis14Ft())
				&& !ProdisSrvUtil.controllaNumero(datiProvinciali.getnConvenzioni12bis14Ft().intValue())) {
			apiErrors.add(MsgProdis.PRODPRE0014.getError());
		}
		// sezione altre concessioni
		// convenzione
		if (datiProvinciali.getProvConvenzione() != null) {
			if (ProdisSrvUtil.isNotVoid(datiProvinciali.getProvConvenzione().getDataAtto())
					&& !ProdisSrvUtil.controllaData(datiProvinciali.getProvConvenzione().getDataAtto())) {
				apiErrors.add(MsgProdis.PRODPRE0015.getError());
			}
			if (ProdisSrvUtil.isNotVoid(datiProvinciali.getProvConvenzione().getDataStipula())
					&& !ProdisSrvUtil.controllaData(datiProvinciali.getProvConvenzione().getDataStipula())) {
				apiErrors.add(MsgProdis.PRODPRE0016.getError());
			}
			if (ProdisSrvUtil.isNotVoid(datiProvinciali.getProvConvenzione().getDataScadenza())
					&& !ProdisSrvUtil.controllaData(datiProvinciali.getProvConvenzione().getDataScadenza())) {
				apiErrors.add(MsgProdis.PRODPRE0017.getError());
			}
			if (ProdisSrvUtil.isNotVoid(datiProvinciali.getProvConvenzione().getNumLavPrevConvQ2()) && !ProdisSrvUtil
					.controllaNumero(datiProvinciali.getProvConvenzione().getNumLavPrevConvQ2().intValue())) {
				apiErrors.add(MsgProdis.PRODPRE0025.getError());
			}
		}
		// esonero
		if (datiProvinciali.getProvEsonero() != null) {
			if (ProdisSrvUtil.isNotVoid(datiProvinciali.getProvEsonero().getDataAtto())
					&& !ProdisSrvUtil.controllaData(datiProvinciali.getProvEsonero().getDataAtto())) {
				apiErrors.add(MsgProdis.PRODPRE0018.getError());
			}
			if (ProdisSrvUtil.isNotVoid(datiProvinciali.getProvEsonero().getDataAttoFinoAl())
					&& !ProdisSrvUtil.controllaData(datiProvinciali.getProvEsonero().getDataAttoFinoAl())) {
				apiErrors.add(MsgProdis.PRODPRE0019.getError());
			}
			if (ProdisSrvUtil.isNotVoid(datiProvinciali.getProvEsonero().getPercentuale()) && !ProdisSrvUtil
					.controllaPercentuale(datiProvinciali.getProvEsonero().getPercentuale().intValue())) {
				apiErrors.add(MsgProdis.PRODPRE0020.getError());
			}
			if (ProdisSrvUtil.isNotVoid(datiProvinciali.getProvEsonero().getnLavoratoriEsonero()) && !ProdisSrvUtil
					.controllaNumero(datiProvinciali.getProvEsonero().getnLavoratoriEsonero().intValue())) {
				apiErrors.add(MsgProdis.PRODPRE0021.getError());
			}

		}
		// sospensione
		if (datiProvinciali.getProvSospensione() != null) {
			if (ProdisSrvUtil.isNotVoid(datiProvinciali.getProvSospensione().getnLavoratori()) && !ProdisSrvUtil
					.controllaNumero(datiProvinciali.getProvSospensione().getnLavoratori().intValue())) {
				apiErrors.add(MsgProdis.PRODPRE0022.getError());
			}
			if (ProdisSrvUtil.isNotVoid(datiProvinciali.getProvSospensione().getDataFineSospensioneQ2())
					&& !ProdisSrvUtil.controllaData(datiProvinciali.getProvSospensione().getDataFineSospensioneQ2())) {
				apiErrors.add(MsgProdis.PRODPRE0026.getError());
			}

		}
		// gradualita
		if (datiProvinciali.getProvGradualita() != null
				&& ProdisSrvUtil.isNotVoid(datiProvinciali.getProvGradualita().getnAssunzioniEffDopoTrasf())
				&& !ProdisSrvUtil
						.controllaNumero(datiProvinciali.getProvGradualita().getnAssunzioniEffDopoTrasf().intValue())) {
			apiErrors.add(MsgProdis.PRODPRE0023.getError());
		}
		// note
		if (ProdisSrvUtil.isNotVoid(datiProvinciali.getNote()) && datiProvinciali.getNote().length() > 2000) {
			apiErrors.add(MsgProdis.PRODPRE0024.getError());
		}

		// convenzione
		if (datiProvinciali.getProvConvenzione() != null
				&& ProdisSrvUtil.isNotVoid(datiProvinciali.getProvConvenzione().getNumLavPrevConvQ2())) {
			String esito = datiProvincialiDad.getParametroByNome(
					ConstantsProdis.PARAMETRO_QUADRO2_ALTRECONCESSIONI_CONTROLLO_NUMERO_LAVORATORI_PREVISTI_ABILITATO);
			if (ProdisSrvUtil.isControlloAbilitato(esito)) {
				if (datiProvinciali.getProvConvenzione().getNumLavPrevConvQ2().intValue() > 99) {
					apiErrors.add(MsgProdis.PRODPRE0027.getError());
				}
			}

		}
		// esonero parziale autocertificato
		if (datiProvinciali.getProvEsoneroAutocert() != null) {
			if (ProdisSrvUtil.isNotVoid(datiProvinciali.getProvEsoneroAutocert().getDataAutocert())
					&& !ProdisSrvUtil.controllaData(datiProvinciali.getProvEsoneroAutocert().getDataAutocert())) {
				apiErrors.add(MsgProdis.PRODPRE0028.getError());
			}
			if (ProdisSrvUtil.isNotVoid(datiProvinciali.getProvEsoneroAutocert().getnLav60x1000()) && !ProdisSrvUtil
					.controllaNumero(datiProvinciali.getProvEsoneroAutocert().getnLav60x1000().intValue())) {
				apiErrors.add(MsgProdis.PRODPRE0029.getError());
			}
			if (ProdisSrvUtil.isNotVoid(datiProvinciali.getProvEsoneroAutocert().getnLavEsoneroAutocert())
					&& !ProdisSrvUtil.controllaNumero(
							datiProvinciali.getProvEsoneroAutocert().getnLavEsoneroAutocert().intValue())) {
				apiErrors.add(MsgProdis.PRODPRE0030.getError());
			}
			if (ProdisSrvUtil.isNotVoid(datiProvinciali.getProvEsoneroAutocert().getPercentualeEsAutocert())
					&& !ProdisSrvUtil.controllaPercentuale(
							datiProvinciali.getProvEsoneroAutocert().getPercentualeEsAutocert().intValue())) {
				apiErrors.add(MsgProdis.PRODPRE0031.getError());
			}

		}
	}

}
