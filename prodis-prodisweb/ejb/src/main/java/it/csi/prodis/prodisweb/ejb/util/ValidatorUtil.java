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
import java.util.Date;
import java.util.List;

import it.csi.prodis.prodisweb.lib.dto.decodifiche.Contratti;
import it.csi.prodis.prodisweb.lib.dto.prospetto.LavoratoriInForza;
import it.csi.prodis.prodisweb.lib.dto.prospetto.PartTime;
import it.csi.prodis.prodisweb.lib.dto.prospetto.Prospetto;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProvIntermittenti;

public class ValidatorUtil {


	public static boolean isAssunzioneProtettaSomministratiOrConvenzioni (String tipoAssunzione) {

		if (ConstantsProdis.TIPO_ASSUNZIONE_PROTETTA_CODICE_N.equalsIgnoreCase(tipoAssunzione) 
				|| ConstantsProdis.TIPO_ASSUNZIONE_PROTETTA_CODICE_M.equalsIgnoreCase(tipoAssunzione) 
				|| ConstantsProdis.TIPO_ASSUNZIONE_PROTETTA_CODICE_I.equalsIgnoreCase(tipoAssunzione) 
				|| ConstantsProdis.TIPO_ASSUNZIONE_PROTETTA_CODICE_H.equalsIgnoreCase(tipoAssunzione) 
				){
			return true;
		}
		return false;
	}

	public static boolean isAssunzioneProtettaCentralinisti (String tipoAssunzione) {

		if (ConstantsProdis.TIPO_ASSUNZIONE_PROTETTA_CODICE_D.equalsIgnoreCase(tipoAssunzione)){
			return true;
		}
		return false;
	}
	public static boolean isAssunzioneProtettaTerapisti (String tipoAssunzione) {

		if (ConstantsProdis.TIPO_ASSUNZIONE_PROTETTA_CODICE_E.equalsIgnoreCase(tipoAssunzione)
				|| ConstantsProdis.TIPO_ASSUNZIONE_PROTETTA_CODICE_O.equalsIgnoreCase(tipoAssunzione)){
			return true;
		}
		return false;
	}
	public static boolean isAssunzioneProtettaConvenzione(String tipoAssunzione) {

		if (ConstantsProdis.TIPO_ASSUNZIONE_PROTETTA_CODICE_H.equalsIgnoreCase(tipoAssunzione) 
				|| ConstantsProdis.TIPO_ASSUNZIONE_PROTETTA_CODICE_I.equalsIgnoreCase(tipoAssunzione) 
				){
			return true;
		}
		return false;
	}
	public static int getPTNumberByTipologiaLavoratore(List<PartTime> partTime,List<ProvIntermittenti> intermittenti, Long tipologiaDaConteggiare) {
		int count = 0;
		if(partTime != null && partTime.size() > 0) {
			for(PartTime det : partTime){
				if (det.getTipoRipropPt() != null && det.getTipoRipropPt().getId() != null) {
					if(tipologiaDaConteggiare == det.getTipoRipropPt().getId()) {
						count = count + det.getnPartTime().intValue();
					}
				}
			}
		}
//		if(intermittenti != null && intermittenti.size() > 0) {
//			for(ProvIntermittenti det : intermittenti){
//				count = count + det.getnIntermittenti().intValue();
//			}
//		}
		return count;
	} 
	public static boolean checkCoerenzaDtRifProspettoAndDtInizioRapporto(Date dataRiferimentoProspetto, Date dataInizioRapporto)   {

		if (ProdisSrvUtil.confrontaData1MaggioreData2(dataInizioRapporto, dataRiferimentoProspetto)){
			return false;
		}
		return true;
	}
	public static  boolean checkCoerenzaDtNascitaAndDtInizioRapporto(Date dataInizioRapporto, Date dataDiNascita) {
		if (dataDiNascita != null
				&& ProdisSrvUtil.controllaData(dataDiNascita)
				&& dataInizioRapporto != null
				&& ProdisSrvUtil.controllaData(dataInizioRapporto)
				&& ProdisSrvUtil.confrontaData1MaggioreUgualeData2(dataDiNascita, dataInizioRapporto)) {

			return false;
		}
		return true;
	}
	public static String determinaFlgFormaContrattualeByAssunzioneProtetta(String codiceMinisterialeAssunzioneProtetta) {

		if (ConstantsProdis.TIPO_ASSUNZIONE_PROTETTA_CODICE_M.equals(codiceMinisterialeAssunzioneProtetta)) {
			return ConstantsProdis.CONTRATTO_FORMA_DETERMINATO;

		} else if (ConstantsProdis.TIPO_ASSUNZIONE_PROTETTA_CODICE_N.equals(codiceMinisterialeAssunzioneProtetta)) {
			return ConstantsProdis.CONTRATTO_FORMA_INDETERMINATO;
		} else {
			return null;
		}
	}
	public static final boolean getTipologiaContrattualeValida(Contratti contratto,Long idTipologiaContrattuale) {

		Date dataInizio = contratto.getDtInizio();
		Date dataFine = contratto.getDtFine();

		if (ProdisSrvUtil.isDecodificaValida(new Date(), dataInizio, dataFine)) {
			return true;
		} 
		return false;

	}
	public static boolean isAssunzioneProtettaSomministrati(String tipoAssunzione) {

		if (ConstantsProdis.TIPO_ASSUNZIONE_PROTETTA_CODICE_N.equalsIgnoreCase(tipoAssunzione) 
				|| ConstantsProdis.TIPO_ASSUNZIONE_PROTETTA_CODICE_M.equalsIgnoreCase(tipoAssunzione) 
				){
			return true;
		}
		return false;
	}
	public static String controlloObbligatorietaAndCoerenzaFlgForma(
			LavoratoriInForza lavoratoriInForza, String flgFormaContrattualeAssunzioneProtetta) {

		if (lavoratoriInForza.getContratti()!=null) {
			if (ProdisSrvUtil.isVoid(lavoratoriInForza.getContratti().getFlgForma())) {
				return "Il campo Forma contratto e' obbligatorio.";
			} else {
				if (ConstantsProdis.CONTRATTO_FORMA_INDETERMINATO.equalsIgnoreCase(flgFormaContrattualeAssunzioneProtetta) 
						&& ConstantsProdis.CONTRATTO_FORMA_DETERMINATO.equalsIgnoreCase(lavoratoriInForza.getContratti().getFlgForma())){

					return "Il campo Forma contratto non e' coerente con il contratto / tipo assunzione protetta a tempo indeterminato.";

				} else if (ConstantsProdis.CONTRATTO_FORMA_DETERMINATO.equalsIgnoreCase(flgFormaContrattualeAssunzioneProtetta) 
						&& ConstantsProdis.CONTRATTO_FORMA_INDETERMINATO.equalsIgnoreCase(lavoratoriInForza.getContratti().getFlgForma())) {

					return "Il campo Forma contratto non e' coerente con il contratto / tipo assunzione protetta  a tempo determinato.";

				}
			}
		}
		
		return null;
	}

	public static final String controlliDataFineRaporto(LavoratoriInForza lavoratoriInForza, String flgFormaContrattualeTipologiaContratto) {

		if(ConstantsProdis.CONTRATTO_FORMA_INDETERMINATO.equalsIgnoreCase(flgFormaContrattualeTipologiaContratto)){

			if(lavoratoriInForza.getDataFineRapporto() != null ){
				return "La data di fine rapporto non deve essere valorizzata per contratti  / tipo assunzione protetta a tempo indeterminato.";	    				
			}
		} else if(ConstantsProdis.CONTRATTO_FORMA_DETERMINATO.equalsIgnoreCase(flgFormaContrattualeTipologiaContratto)){

			if (lavoratoriInForza.getDataFineRapporto() == null) {
				return "La data di fine rapporto obbligatoria per contratti  / tipo assunzione protetta a tempo determinato.";
			} else if (!ProdisSrvUtil.controllaData(lavoratoriInForza.getDataFineRapporto())) {
				return "Formato data di fine rapporto non valida.";
			} else {
				if (lavoratoriInForza.getDataInizioRapporto() != null 
						&& lavoratoriInForza.getDataFineRapporto() != null
						&& !lavoratoriInForza.getDataFineRapporto().after(lavoratoriInForza.getDataInizioRapporto())) {
					return "La data di fine rapporto deve essere posteriore a quella di inizio rapporto.";
				}
			}

		} else if(ConstantsProdis.CONTRATTO_FORMA_ENTRAMBE.equalsIgnoreCase(flgFormaContrattualeTipologiaContratto)){

			if (lavoratoriInForza.getContratti() != null && lavoratoriInForza.getContratti().getFlgForma() == null) {

				return 		"Il campo Forma contratto e' obbligatorio.";
			} else {
				if (lavoratoriInForza.getContratti() != null && lavoratoriInForza.getContratti().getFlgForma() != null 
						&& ConstantsProdis.CONTRATTO_FORMA_DETERMINATO.equalsIgnoreCase(lavoratoriInForza.getContratti().getFlgForma())
						&& lavoratoriInForza.getDataFineRapporto() == null ) {

					return "La Data fine rapporto e' obbligatoria per la tipologia e la forma contrattuali selezionate.";
				} else if (lavoratoriInForza.getContratti() != null && lavoratoriInForza.getContratti().getFlgForma() != null
						&& ConstantsProdis.CONTRATTO_FORMA_INDETERMINATO.equalsIgnoreCase(lavoratoriInForza.getContratti().getFlgForma())
						&& lavoratoriInForza.getDataFineRapporto() != null  ) {

					return "La Data fine rapporto non deve essere valorizzata per la tipologia e la forma contrattuali selezionate.";
				}
			}
		}
		return null;
	}
	public static final boolean checkCoerenzaDtRifProspettoAndDtFineRapporto(Prospetto prospetto, LavoratoriInForza lavoratoreInForza) {
		Date dataRiferimento = prospetto.getDataRiferimentoProspetto();

		if (ProdisSrvUtil.confrontaData1MaggioreData2(dataRiferimento, lavoratoreInForza.getDataFineRapporto())){
			return false;
		}
		return true;
	}
	public static final boolean checkCongruenzaAssunzioneProtettaContrattoTirocinio( String codiceMinisterialeAssunzioneProtetta,LavoratoriInForza lavoratoriInForza, ArrayList<String> listaTirocini) {

		if (lavoratoriInForza.getContratti()!=null && lavoratoriInForza.getContratti().getCodTipoContrattoMin()!=null){
			if(listaTirocini.contains(lavoratoriInForza.getContratti().getCodTipoContrattoMin()) 
					&& !ConstantsProdis.TIPO_ASSUNZIONE_PROTETTA_CODICE_A.equalsIgnoreCase(codiceMinisterialeAssunzioneProtetta)){

				return false;	    				
			}	
		}
		return true;
	}
	public static boolean dateInizioAndFineCoerentiPerTipoAssunzioneProtetta (Date dataInizioRapporto, Date dataFineRapporto) {
		Date dtInizio = dataInizioRapporto;
		Date dtFineMenoUnAnno = ProdisSrvUtil.dateAddDays(ProdisSrvUtil.dateAddYear(dataFineRapporto, -1), 1)   ;

		if (ProdisSrvUtil.confrontaData1MaggioreData2(dtInizio, dtFineMenoUnAnno)){
			return false;
		}
		return true;
	}

	public static boolean checkFormatoCfoPiva(
			String codiceFiscale , 
			String regexCfAziendaAlfanumerico, 
			String regexCfAziendaNumerico ) {


		if(codiceFiscale != null){


			if(! (codiceFiscale.toUpperCase().matches(regexCfAziendaAlfanumerico) ||    
					codiceFiscale.toUpperCase().matches(regexCfAziendaNumerico))) {

				return false;
			}
		}

		return true;

	}
	public static int nvl(Integer source){
		if(source == null) return 0;
		return source.intValue();
	}
	public static boolean isGradualitaPresente (Prospetto prospetto)  {
		if(prospetto != null ) {

			if (prospetto.getProspettoGradualita() == null) {
				return false;
			}
			if (prospetto.getProspettoGradualita().getDataAtto() != null
					&& (prospetto.getProspettoGradualita().getEstremiAtto() != null || !"".equals(prospetto.getProspettoGradualita().getEstremiAtto())) 
					&& (prospetto.getProspettoGradualita().getnAssunzioniLavPreTrasf() != null || !prospetto.getProspettoGradualita().getnAssunzioniLavPreTrasf().equals(new BigDecimal(0)))
					&& prospetto.getProspettoGradualita().getDataTrasformazione() != null
					&& (prospetto.getProspettoGradualita().getPercentuale() != null || !prospetto.getProspettoGradualita().getPercentuale().equals(new BigDecimal(0))) ) {

				return true;
			}


		} 

		return false;

	} 

	public static boolean isProvenienzaSilp(String dataTimbroPostale) {
		return ProdisSrvUtil.isVoid(dataTimbroPostale);
	}

	public static  boolean isProvenienzaMinistero(String tipoProvenienza) {
		return ConstantsProdis.TIPO_PROVENIENZA_MINISTERO.equalsIgnoreCase(tipoProvenienza);
	}
	public static boolean isRettificaNonAmmessa(Date dataTimbroPostale, int intervalloAmmesso){

		Date dataCorrente = new Date();
		Date dataTermine = ProdisSrvUtil.dateAddDays(dataTimbroPostale, intervalloAmmesso);

		while (ProdisSrvUtil.checkFestivita(dataTermine)) {
			dataTermine = ProdisSrvUtil.dateAddDays(dataTermine, 1);
		}

		return isDataCorrenteOltreDataTermine(dataTermine, dataCorrente);
	}
	public static boolean isDataCorrenteOltreDataTermine(Date dataTermine,
			Date dataCorrente) {

		if (ProdisSrvUtil.confrontaDate(dataTermine, dataCorrente) == 1) {
			return true;
		} else {
			return false;
		}
	}
	public static boolean isAnnullamentoNonAmmesso(Date dataTermine) {
		Date dataCorrente = new Date();
		return isDataCorrenteOltreDataTermine(dataTermine, dataCorrente);

	}
	public static boolean isProspettoAnniPrecedenti(String dataDiRiferimento) {

		return !ProdisSrvUtil.verificaDataConAnnoUgualeAnnoPrecedente(dataDiRiferimento);

	}
}
