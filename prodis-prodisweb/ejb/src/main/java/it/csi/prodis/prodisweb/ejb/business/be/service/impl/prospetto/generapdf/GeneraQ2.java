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
package it.csi.prodis.prodisweb.ejb.business.be.service.impl.prospetto.generapdf;

import java.text.ParseException;
import java.util.List;

import it.csi.prodis.prodisweb.ejb.business.be.dad.ProspettoDad;
import it.csi.prodis.prodisweb.ejb.entity.ProDCategorieEscluse;
import it.csi.prodis.prodisweb.ejb.entity.ProDDatiProvinciali;
import it.csi.prodis.prodisweb.ejb.entity.ProDLavoratoriInForza;
import it.csi.prodis.prodisweb.ejb.entity.ProDPartTime;
import it.csi.prodis.prodisweb.ejb.entity.ProDPostiLavoroDisp;
import it.csi.prodis.prodisweb.ejb.entity.ProDProvCompensazioni;
import it.csi.prodis.prodisweb.ejb.entity.ProDProvConvenzione;
import it.csi.prodis.prodisweb.ejb.entity.ProDProvEsonero;
import it.csi.prodis.prodisweb.ejb.entity.ProDProvEsoneroAutocert;
import it.csi.prodis.prodisweb.ejb.entity.ProDProvGradualita;
import it.csi.prodis.prodisweb.ejb.entity.ProDProvIntermittenti;
import it.csi.prodis.prodisweb.ejb.entity.ProDProvSospensione;
import it.csi.prodis.prodisweb.ejb.entity.ProRProspettoProvincia;

public class GeneraQ2 {

	public static StringBuilder getGenerazioneQ2(ProspettoDad prospettoDad, Long idProspetto,
			List<ProRProspettoProvincia> prospettoProvincia) throws ParseException {

		StringBuilder sb = new StringBuilder();

		// QUADRO 2
        
        sb.append(GeneraPdfUtilities.createCapitolo("Quadro 2"));

        if (!prospettoProvincia.isEmpty()) {
        	
        	for (int i=0; i<prospettoProvincia.size(); i++) {
        		
        		sb.append(GeneraPdfUtilities.createParagrafoDati("Dati Provinciali: "+(i+1)));
        		
        		if (prospettoProvincia.get(i).getProTProvincia()!=null 
        				&& prospettoProvincia.get(i).getProTProvincia().getCodProvinciaMin()!=null
        				&& prospettoProvincia.get(i).getProTProvincia().getDsProTProvincia()!=null){
        			sb.append(GeneraPdfUtilities.createRow("Codice Provincia",
        					prospettoProvincia.get(i).getProTProvincia().getCodProvinciaMin() 
        					+ prospettoProvincia.get(i).getProTProvincia().getDsProTProvincia()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Codice Provincia",""));
        		}
        		
        		ProDDatiProvinciali datiProvinciali = prospettoProvincia.get(i).getProDDatiProvinciali();

        		if (datiProvinciali.getProDProspettoProvSede()!=null && 
        				datiProvinciali.getProDProspettoProvSede().getProTComune()!=null &&
        				datiProvinciali.getProDProspettoProvSede().getProTComune().getDsProTComune()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Comune sede riferimento",datiProvinciali.getProDProspettoProvSede().getProTComune().getDsProTComune()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Comune sede riferimento",""));
        		}
        		if (datiProvinciali.getProDProspettoProvSede()!=null && datiProvinciali.getProDProspettoProvSede().getCap()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("CAP sede di riferimento",datiProvinciali.getProDProspettoProvSede().getCap()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("CAP sede di riferimento",""));
        		}
        		if (datiProvinciali.getProDProspettoProvSede()!=null && datiProvinciali.getProDProspettoProvSede().getIndirizzo()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Indirizzo sede di riferimento",GeneraPdfUtilities.escapeHtml(datiProvinciali.getProDProspettoProvSede().getIndirizzo())));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Indirizzo sede di riferimento",""));
        		}
        		if (datiProvinciali.getProDProspettoProvSede()!=null && datiProvinciali.getProDProspettoProvSede().getTelefono()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Telefono sede di riferimento",datiProvinciali.getProDProspettoProvSede().getTelefono()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Telefono sede di riferimento",""));
        		}
        		if (datiProvinciali.getProDProspettoProvSede()!=null && datiProvinciali.getProDProspettoProvSede().getFax()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Fax sede di riferimento",datiProvinciali.getProDProspettoProvSede().getFax()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Fax sede di riferimento",""));
        		}
        		if (datiProvinciali.getProDProspettoProvSede()!=null && datiProvinciali.getProDProspettoProvSede().getEmail()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("E-mail sede di riferimento",GeneraPdfUtilities.escapeHtml(datiProvinciali.getProDProspettoProvSede().getEmail())));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("E-mail sede di riferimento",""));
        		}
        		if (datiProvinciali.getnPostiPrevCentraliNonved()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Num.posti previsti per centralinisti non vedenti (113/85)",datiProvinciali.getnPostiPrevCentraliNonved().toString()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Num.posti previsti per centralinisti non vedenti (113/85)",""));
        		}
        		if (datiProvinciali.getnPostiPrevMassofisNonved()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Num.posti previsti per massofisioterapisti non vedenti (403/71)",datiProvinciali.getnPostiPrevMassofisNonved().toString()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Num.posti previsti per massofisioterapisti non vedenti (403/71)",""));
        		}
        		
        		sb.append(GeneraPdfUtilities.chiudiParagrafo());
        		
        		sb.append(GeneraPdfUtilities.createParagrafo("Dati relativi al personale dipendente"));
        		
        		if (datiProvinciali.getnTotaleLavoratDipendenti()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Num. Totale lavoratori dipendenti",datiProvinciali.getnTotaleLavoratDipendenti().toString()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Num. Totale lavoratori dipendenti",""));
        		}
        		if (datiProvinciali.getnCateProtForza()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Num.Categorie Protette in forza (L.68.99 Art.18 limitatamente all'1%)",datiProvinciali.getnCateProtForza().toString()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Num.Categorie Protette in forza (L.68.99 Art.18 limitatamente all'1%)",""));
        		}
        		if (datiProvinciali.getnDisabiliInForza()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Num.Disabili (L.68.99) in forza a tempo pieno ",datiProvinciali.getnDisabiliInForza().toString()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Num.Disabili (L.68.99) in forza a tempo pieno ",""));
        		}
        		if (datiProvinciali.getnCentralTelefoNonvedenti()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Num. centralinisti telefonici non vedenti a tempo pieno",datiProvinciali.getnCentralTelefoNonvedenti().toString()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Num. centralinisti telefonici non vedenti a tempo pieno",""));
        		}
        		if (datiProvinciali.getnTerariabMassofisNonved()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Num.terapisti della riabilitazione e massofisioterapisti non vedenti  a tempo pieno (L.29/94)",datiProvinciali.getnTerariabMassofisNonved().toString()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Num.terapisti della riabilitazione e massofisioterapisti non vedenti  a tempo pieno (L.29/94)",""));
        		}
        		if (datiProvinciali.getnCateProtForzaA17012000()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Num. categorie protette in forza al 17/01/2000",datiProvinciali.getnCateProtForzaA17012000().toString()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Num. categorie protette in forza al 17/01/2000",""));
        		}
        		
        		sb.append(GeneraPdfUtilities.chiudiParagrafo());
        		
        		sb.append(GeneraPdfUtilities.createParagrafo("Dati Relativi al personale non dipendente computabile nella quota di riserva"));
        		
        		if (datiProvinciali.getnSomministratiFt()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Num. disabili somministrati a tempo pieno",datiProvinciali.getnSomministratiFt().toString()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Num. disabili somministrati a tempo pieno",""));
        		}
        		if (datiProvinciali.getnConvenzioni12bis14Ft()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Num. disabili in convenzione art. 12-bis e 14 a tempo pieno",datiProvinciali.getnConvenzioni12bis14Ft().toString()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Num. disabili in convenzione art. 12-bis e 14 a tempo pieno",""));
        		}
        		
        		sb.append(GeneraPdfUtilities.chiudiParagrafo());
        		
        		List<ProDCategorieEscluse> categorieEscluse = prospettoDad.caricaCategorieEsclusePerPdf(prospettoProvincia.get(i).getIdProspettoProv());
        		
        		if (!categorieEscluse.isEmpty() || datiProvinciali.getnTelelavoroFt()!=null) {
        			
        			sb.append(GeneraPdfUtilities.createParagrafo("Categorie escluse"));
        			
            		sb.append("<tr class=\"ptIntest\">"
        					+ "<td class=\"ptCol\">Categoria esclusa dal computo</td>"
        					+ "<td class=\"ptCol\">Num. lavoratori appartenenti alla categoria</td>"
        				+ "</tr>");
            		
            		if (datiProvinciali.getnTelelavoroFt()!=null) {
            			
            			sb.append("<tr class=\"ptRow\">"
            					+ "<td class=\"ptCell\">Telelavoro a tempo pieno (art.23,D.Lgs.80/2015)</td>"
            					+ "<td class=\"ptCell\">"+datiProvinciali.getnTelelavoroFt()+"</td>"
            					+ "</tr>");
            			
            		}
            		
            		for (ProDCategorieEscluse ce : categorieEscluse) {
            			
            			if (ce.getProTCategoriaEscluse()!=null) {
            				
            				ce.getProTCategoriaEscluse().setDesCategoriaEscluse(ce.getProTCategoriaEscluse().getDesCategoriaEscluse().replace(">", "&gt;"));
            				ce.getProTCategoriaEscluse().setDesCategoriaEscluse(ce.getProTCategoriaEscluse().getDesCategoriaEscluse().replace("<", "&lt;"));
            				
            				sb.append("<tr class=\"ptRow\">"
                					+ "<td class=\"ptCell\">"+ce.getProTCategoriaEscluse().getDesCategoriaEscluse()+"</td>"
                					+ "<td class=\"ptCell\">"+ce.getnLavAppartartCategoria()+"</td>"
                					+ "</tr>");
            				
            			}

            		}

            		sb.append(GeneraPdfUtilities.chiudiParagrafo());

        		}
        		
        		List<ProDPartTime> partTime = prospettoDad.caricaPartTimePerPdf(prospettoProvincia.get(i).getIdProspettoProv());
        		List<ProDProvIntermittenti> intermittenti = prospettoDad.caricaIntermittentiPerPdf(prospettoProvincia.get(i).getIdProspettoProv());
        		
        		if (!partTime.isEmpty() || !intermittenti.isEmpty()) {
        			
        			sb.append(GeneraPdfUtilities.createParagrafo("Part time e intermittenti"));
        			
        			if (!partTime.isEmpty()) {
            			
            			sb.append("<tr class=\"ptIntest\">"
        					+ "<td class=\"ptCol\">Tipologia lavoratore</td>"
        					+ "<td class=\"ptCol\">Num. part-time</td>"
        					+ "<td class=\"ptCol\">Orario settimanale contrattuale</td>"
        					+ "<td class=\"ptCol\">Orario settimanale svolto</td>"
        				+ "</tr>");
            			
            			for (ProDPartTime pt : partTime) {
                			
                			sb.append("<tr class=\"ptRow\">"
                					+ "<td class=\"ptCell\">"+pt.getProTTipoRipropPt().getDsTipoRipropPt()+"</td>"
                					+ "<td class=\"ptCell\">"+pt.getnPartTime()+"</td>"
                					+ "<td class=\"ptCell\">"+GeneraPdfUtilities.fromMinutesToHHmm(pt.getOrarioSettContrattualeMin().intValue())+"</td>"
                					+ "<td class=\"ptCell\">"+GeneraPdfUtilities.fromMinutesToHHmm(pt.getOrarioSettPartTimeMin().intValue())+"</td>"
                					+ "</tr>");
                		}
            			
            			sb.append(GeneraPdfUtilities.chiudiParagrafo());
            			
            		}

        			if (!intermittenti.isEmpty()) {
        				
        				if (!partTime.isEmpty()) {
        					sb.append("<table class=\"tabellaDati\">\n");
        				}

                		if (!intermittenti.isEmpty()) {
                		sb.append("<tr class=\"ptIntest\">"
            					+ "<td class=\"ptCol\">Tipologia lavoratore</td>"
            					+ "<td class=\"ptCol\">Num. intermittenti</td>"
            					+ "<td class=\"ptCol\">Orario settimanale contrattuale</td>"
            					+ "<td class=\"ptCol\">Orario settimanale svolto</td>"
            				+ "</tr>");
                		}
                		
                		for (ProDProvIntermittenti interm : intermittenti) {
                			
                			sb.append("<tr class=\"ptRow\">"
                					+ "<td class=\"ptCell\">Intermittenti</td>"
                					+ "<td class=\"ptCell\">"+interm.getnIntermittenti()+"</td>"
                					+ "<td class=\"ptCell\">"+GeneraPdfUtilities.fromMinutesToHHmm(interm.getOrarioSettimanaleContrattual().intValue())+"</td>"
                					+ "<td class=\"ptCell\">"+GeneraPdfUtilities.fromMinutesToHHmm(interm.getOrarioSettimanaleSvolto().intValue())+"</td>"
                					+ "</tr>");
                			
                		}
                		
                		sb.append(GeneraPdfUtilities.chiudiParagrafo());
        				
        			}
        			
        		}
        		
        		sb.append(GeneraPdfUtilities.createParagrafo("Sospensione"));
                
        		ProDProvSospensione sospensione = datiProvinciali.getProDProvSospensione();
        		
        		if (sospensione!=null && sospensione.getProTStatoConcessione()!=null && sospensione.getProTStatoConcessione().getDescStatoConcessione()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Stato",sospensione.getProTStatoConcessione().getDescStatoConcessione()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Stato",""));
        		}
        		if (sospensione!=null && sospensione.getProTCausaSospensione()!=null && sospensione.getProTCausaSospensione().getDesCausaSospensione()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Causa",sospensione.getProTCausaSospensione().getDesCausaSospensione()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Causa",""));
        		}
        		if (sospensione!=null && sospensione.getDataFineSospensioneQ2()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Data fine sospensione",GeneraPdfUtilities.removeTime(sospensione.getDataFineSospensioneQ2())));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Data fine sospensione",""));
        		}
        		if (sospensione!=null && sospensione.getnLavoratori()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Numero Lavoratori",sospensione.getnLavoratori().toString()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Numero Lavoratori",""));
        		}
        
        		sb.append(GeneraPdfUtilities.chiudiParagrafo());
        		
        		sb.append(GeneraPdfUtilities.createParagrafo("Gradualita"));
        		
        		ProDProvGradualita gradualita = datiProvinciali.getProDProvGradualita();
        		
        		if (gradualita!=null && gradualita.getnAssunzioniEffDopoTrasf()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Num. assunzioni dopo la trasformazione",gradualita.getnAssunzioniEffDopoTrasf().toString()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Num. assunzioni dopo la trasformazione",""));
        		}
        		
        		sb.append(GeneraPdfUtilities.chiudiParagrafo());
        		
        		sb.append(GeneraPdfUtilities.createParagrafo("Esonero parziale autorizzato"));
        		
        		ProDProvEsonero esonero = datiProvinciali.getProDProvEsonero();

        		if (esonero!=null && esonero.getProTStatoConcessione()!=null && esonero.getProTStatoConcessione().getDescStatoConcessione()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Stato",esonero.getProTStatoConcessione().getDescStatoConcessione()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Stato",""));
        		}
        		if (esonero!=null && esonero.getDataAtto()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Data Atto",GeneraPdfUtilities.removeTime(esonero.getDataAtto())));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Data Atto",""));
        		}
        		if (esonero!=null && esonero.getEstremiAtto()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Estremi Atto",GeneraPdfUtilities.escapeHtml(esonero.getEstremiAtto())));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Estremi Atto",""));
        		}
        		if (esonero!=null && esonero.getDataAttoFinoAl()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Fino al",GeneraPdfUtilities.removeTime(esonero.getDataAttoFinoAl())));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Fino al",""));
        		}
        		if (esonero!=null && esonero.getPercentuale()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Percentuale",esonero.getPercentuale().toString()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Percentuale",""));
        		}
        		if (esonero!=null && esonero.getNumLavoratoriEsonero()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Num. Lavoratori in esonero",esonero.getNumLavoratoriEsonero().toString()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Num. Lavoratori in esonero",""));
        		}
        		
        		sb.append(GeneraPdfUtilities.chiudiParagrafo());
        		
        		sb.append(GeneraPdfUtilities.createParagrafo("Esonero parziale autocertificato"));
        		
        		ProDProvEsoneroAutocert esoneroAutocert = datiProvinciali.getProDProvEsoneroAutocert();;

        		if (esoneroAutocert!=null && esoneroAutocert.getDataAutocert()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Data autocertificazione",GeneraPdfUtilities.removeTime(esoneroAutocert.getDataAutocert())));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Data autocertificazione",""));
        		}
        		if (esoneroAutocert!=null && esoneroAutocert.getnLav60x1000()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Num. Lavoratori 60 per mille",esoneroAutocert.getnLav60x1000().toString()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Num. Lavoratori 60 per mille",""));
        		}
        		if (esoneroAutocert!=null && esoneroAutocert.getPercentualeEsAutocert()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Percentuale",esoneroAutocert.getPercentualeEsAutocert().toString()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Percentuale",""));
        		}
        		if (esoneroAutocert!=null && esoneroAutocert.getnLavEsoneroAutocert()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Num. Lavoratori in esonero",esoneroAutocert.getnLavEsoneroAutocert().toString()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Num. Lavoratori in esonero",""));
        		}
        		
        		sb.append(GeneraPdfUtilities.chiudiParagrafo());
        		
        		sb.append(GeneraPdfUtilities.createParagrafo("Convenzione"));
        		
        		ProDProvConvenzione convenzione = datiProvinciali.getProDProvConvenzione();

        		if (convenzione!=null && convenzione.getProTStatoConcessione()!=null && convenzione.getProTStatoConcessione().getDescStatoConcessione()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Stato",convenzione.getProTStatoConcessione().getDescStatoConcessione()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Stato",""));
        		}
        		if (convenzione!=null && convenzione.getDataAtto()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Data Atto",GeneraPdfUtilities.removeTime(convenzione.getDataAtto())));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Data Atto",""));
        		}
        		if (convenzione!=null && convenzione.getEstremiAtto()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Estremi Atto",GeneraPdfUtilities.escapeHtml(convenzione.getEstremiAtto())));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Estremi Atto",""));
        		}
        		if (convenzione!=null && convenzione.getProTAssunzioneProtetta()!=null && convenzione.getProTAssunzioneProtetta().getDescAssunzioneProtetta()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Tipologia di Convenzione",convenzione.getProTAssunzioneProtetta().getDescAssunzioneProtetta().toString()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Tipologia di Convenzione",""));
        		}
        		if (convenzione!=null && convenzione.getNumLavPrevConvQ2()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Numero di lavoratori previsti",convenzione.getNumLavPrevConvQ2().toString()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Numero di lavoratori previsti",""));
        		}
        		if (convenzione!=null && convenzione.getDataStipula()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Data stipula",GeneraPdfUtilities.removeTime(convenzione.getDataStipula())));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Data stipula",""));
        		}
        		if (convenzione!=null && convenzione.getDataScadenza()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Data scadenza",GeneraPdfUtilities.removeTime(convenzione.getDataScadenza())));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Data scadenza",""));
        		}
        		
        		sb.append(GeneraPdfUtilities.chiudiParagrafo());
        		
        		sb.append(GeneraPdfUtilities.createParagrafo("Note"));
                
        		if (datiProvinciali!=null && datiProvinciali.getNote()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Note",GeneraPdfUtilities.escapeHtml(datiProvinciali.getNote())));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Note",""));
        		}
                
                sb.append(GeneraPdfUtilities.chiudiParagrafo());
        		
        		List<ProDLavoratoriInForza> insiemeLavoratori = prospettoDad.caricaLavoratoriInForzaPerPdf(prospettoProvincia.get(i).getIdProspettoProv());

        		sb.append(GeneraPdfUtilities.createParagrafo("Elenco lavoratori in forza"));
        		sb.append("</table>");
                
                if (!insiemeLavoratori.isEmpty()) {
                	
                	for (ProDLavoratoriInForza lav : insiemeLavoratori) {
                		
                		sb.append(GeneraPdfUtilities.createParagrafo("Lavoratore"));
                		
                		if (lav.getCodiceFiscale()!=null) {
                			sb.append(GeneraPdfUtilities.createRow("Codice fiscale",lav.getCodiceFiscale()));
                		} else {
                			sb.append(GeneraPdfUtilities.createRow("Codice fiscale",""));
                		}
                		if (lav.getCognome()!=null) {
                			sb.append(GeneraPdfUtilities.createRow("Cognome",GeneraPdfUtilities.escapeHtml(lav.getCognome())));
                		} else {
                			sb.append(GeneraPdfUtilities.createRow("Cognome",""));
                		}
                		if (lav.getNome()!=null) {
                			sb.append(GeneraPdfUtilities.createRow("Nome",GeneraPdfUtilities.escapeHtml(lav.getNome())));
                		} else {
                			sb.append(GeneraPdfUtilities.createRow("Nome",""));
                		}
                		if (lav.getSesso()!=null) {
                			sb.append(GeneraPdfUtilities.createRow("Sesso",lav.getSesso()));
                		} else {
                			sb.append(GeneraPdfUtilities.createRow("Sesso",""));
                		}
                		
                		if (lav.getProTComune()!=null && lav.getProTComune().getDsProTComune()!=null) {
                			sb.append(GeneraPdfUtilities.createRow("Comune di nascita",lav.getProTComune().getDsProTComune()));
                		} else if (lav.getProTStatiEsteri()!=null && lav.getProTStatiEsteri().getDsStatiEsteri()!=null) {
                			sb.append(GeneraPdfUtilities.createRow("Stato estero di nascita",lav.getProTStatiEsteri().getDsStatiEsteri()));
                		} else {
                			sb.append(GeneraPdfUtilities.createRow("Comune / Stati Esteri",""));
                		}
                		
                		if (lav.getDataNascita()!=null) {
                			sb.append(GeneraPdfUtilities.createRow("Data Nascita",GeneraPdfUtilities.removeTime(lav.getDataNascita())));
                		} else {
                			sb.append(GeneraPdfUtilities.createRow("Data Nascita",""));
                		}
                		if (lav.getDataInizioRapporto()!=null) {
                			sb.append(GeneraPdfUtilities.createRow("Data Inizio Rapporto",GeneraPdfUtilities.removeTime(lav.getDataInizioRapporto())));
                		} else {
                			sb.append(GeneraPdfUtilities.createRow("Data Inizio Rapporto",""));
                		}
                		if (lav.getProTContratti()!=null && lav.getProTContratti().getDsContratto()!=null) {
                			sb.append(GeneraPdfUtilities.createRow("Tipologia contrattuale",lav.getProTContratti().getDsContratto()));
                		} else {
                			sb.append(GeneraPdfUtilities.createRow("Tipologia contrattuale",""));
                		}
                		if (lav.getDataFineRapporto()!=null) {
                			sb.append(GeneraPdfUtilities.createRow("Data Fine Rapporto",GeneraPdfUtilities.removeTime(lav.getDataFineRapporto())));
                		} else {
                			sb.append(GeneraPdfUtilities.createRow("Data Fine Rapporto",""));
                		}
                		if (lav.getProTIstat2001livello5()!=null && lav.getProTIstat2001livello5().getDsComIstat2001livello5()!=null) {
                			sb.append(GeneraPdfUtilities.createRow("Qualifica professionale ISTAT",lav.getProTIstat2001livello5().getDsComIstat2001livello5()));
                		} else {
                			sb.append(GeneraPdfUtilities.createRow("Qualifica professionale ISTAT",""));
                		}
                		if (lav.getProTAssunzioneProtetta()!=null && lav.getProTAssunzioneProtetta().getDescAssunzioneProtetta()!=null) {
                			sb.append(GeneraPdfUtilities.createRow("Tipo assunzione protetta",lav.getProTAssunzioneProtetta().getDescAssunzioneProtetta()));
                		} else {
                			sb.append(GeneraPdfUtilities.createRow("Tipo assunzione protetta",""));
                		}
                		if (lav.getOrarioSettContrattualeMin()!=null) {
                			sb.append(GeneraPdfUtilities.createRow("Orario settimanale contrattuale",
                					GeneraPdfUtilities.fromMinutesToHHmm(lav.getOrarioSettContrattualeMin().intValue())));
                		} else {
                			sb.append(GeneraPdfUtilities.createRow("Orario settimanale contrattuale",""));
                		}
                		if (lav.getOrarioSettPartTimeMin()!=null) {
                			sb.append(GeneraPdfUtilities.createRow("Orario settimanale part time",
                					GeneraPdfUtilities.fromMinutesToHHmm(lav.getOrarioSettPartTimeMin().intValue())));
                		} else {
                			sb.append(GeneraPdfUtilities.createRow("Orario settimanale part time",""));
                		}
                		if (lav.getCategoriaSoggetto()!=null) {
                			if (lav.getCategoriaSoggetto().equals("D")) {
                				sb.append(GeneraPdfUtilities.createRow("Categoria soggetto","Disabile"));
                			} else if (lav.getCategoriaSoggetto().equals("C")) {
                				sb.append(GeneraPdfUtilities.createRow("Categoria soggetto","Categoria Protetta"));
                			} else {
                				sb.append(GeneraPdfUtilities.createRow("Categoria soggetto",""));
                			}
                		} else {
                			sb.append(GeneraPdfUtilities.createRow("Categoria soggetto",""));
                		}
                		if (lav.getCategoriaAssunzione()!=null) {
                			if (lav.getCategoriaAssunzione().equals("NO")) {
                				sb.append(GeneraPdfUtilities.createRow("Categoria di Assunzione","Assunzione Nominativa"));
                			} else if (lav.getCategoriaAssunzione().equals("NU")) {
                				sb.append(GeneraPdfUtilities.createRow("Categoria di Assunzione","Assunzione Numerica"));
                			} else {
                				sb.append(GeneraPdfUtilities.createRow("Categoria di Assunzione",""));
                			}
                		} else {
                			sb.append(GeneraPdfUtilities.createRow("Categoria di Assunzione",""));
                		}
                		
                		sb.append(GeneraPdfUtilities.chiudiParagrafo());
                		
                	}
                	
                }
                
                sb.append(GeneraPdfUtilities.createParagrafo("Elenco posti di lavoro disponibili"));

        		List<ProDPostiLavoroDisp> postiLavoroDispList = prospettoDad.caricaPostiLavoroDispPerPdf(prospettoProvincia.get(i).getIdProspettoProv());
        		
        		if (!postiLavoroDispList.isEmpty()) {
        			
        			for (ProDPostiLavoroDisp postiLavoroDisp : postiLavoroDispList) {
        				
        				if (postiLavoroDisp!=null && postiLavoroDisp.getProTIstat2001livello5()!=null 
                				&& postiLavoroDisp.getProTIstat2001livello5().getDsComIstat2001livello5()!=null) {
                			sb.append(GeneraPdfUtilities.createRow("Qualifica professionale ISTAT",postiLavoroDisp.getProTIstat2001livello5().getDsComIstat2001livello5()));
                		} else {
                			sb.append(GeneraPdfUtilities.createRow("Qualifica professionale ISTAT",""));
                		}
                		if (postiLavoroDisp!=null && postiLavoroDisp.getDescMansione()!=null) {
                			sb.append(GeneraPdfUtilities.createRow("Mansione / Descrizione compiti",GeneraPdfUtilities.escapeHtml(postiLavoroDisp.getDescMansione())));
                		} else {
                			sb.append(GeneraPdfUtilities.createRow("Mansione / Descrizione compiti",""));
                		}
                		if (postiLavoroDisp!=null && postiLavoroDisp.getnPosti()!=null) {
                			sb.append(GeneraPdfUtilities.createRow("Num. Posti",postiLavoroDisp.getnPosti().toString()));
                		} else {
                			sb.append(GeneraPdfUtilities.createRow("Num. Posti",""));
                		}
                		if (postiLavoroDisp!=null && postiLavoroDisp.getCategoriaSoggetto()!=null) {
                			if (postiLavoroDisp.getCategoriaSoggetto().equals("D")) {
                				sb.append(GeneraPdfUtilities.createRow("Categoria soggetto","Disabile"));
                			} else if (postiLavoroDisp.getCategoriaSoggetto().equals("C"))  {
                				sb.append(GeneraPdfUtilities.createRow("Categoria soggetto","Categoria Protetta"));
                			} else {
                				sb.append(GeneraPdfUtilities.createRow("Categoria soggetto",""));
                			}
                		} else {
                			sb.append(GeneraPdfUtilities.createRow("Categoria soggetto",""));
                		}
                		if (postiLavoroDisp!=null && postiLavoroDisp.getProTComune()!=null && postiLavoroDisp.getProTComune().getDsProTComune()!=null) {
                			sb.append(GeneraPdfUtilities.createRow("Comune di assunzione",postiLavoroDisp.getProTComune().getDsProTComune()));
                		} else {
                			sb.append(GeneraPdfUtilities.createRow("Comune di assunzione",""));
                		}
                		if (postiLavoroDisp!=null && postiLavoroDisp.getDescCapacitaRichiesteContr()!=null) {
                			sb.append(GeneraPdfUtilities.createRow("Capacità richieste / controindicazioni",GeneraPdfUtilities.escapeHtml(postiLavoroDisp.getDescCapacitaRichiesteContr())));
                		} else {
                			sb.append(GeneraPdfUtilities.createRow("Capacità richieste / controindicazioni",""));
                		}
                		if (postiLavoroDisp!=null && postiLavoroDisp.getFlgPresenzaBarriereArchite()!=null) {
                			sb.append(GeneraPdfUtilities.createRow("Presenza di barriere architettoniche",postiLavoroDisp.getFlgPresenzaBarriereArchite()));
                		} else {
                			sb.append(GeneraPdfUtilities.createRow("Presenza di barriere architettoniche",""));
                		}
                		if (postiLavoroDisp!=null && postiLavoroDisp.getFlgTurniNotturni()!=null) {
                			sb.append(GeneraPdfUtilities.createRow("Turni notturni",postiLavoroDisp.getFlgTurniNotturni()));
                		} else {
                			sb.append(GeneraPdfUtilities.createRow("Turni notturni",""));
                		}
                		if (postiLavoroDisp!=null && postiLavoroDisp.getFlgRaggiungibMezziPubblici()!=null) {
                			sb.append(GeneraPdfUtilities.createRow("Raggiungibilità mezzi pubblici",postiLavoroDisp.getFlgRaggiungibMezziPubblici()));
                		} else {
                			sb.append(GeneraPdfUtilities.createRow("Raggiungibilità mezzi pubblici",""));
                		}
                		if (postiLavoroDisp!=null && postiLavoroDisp.getCategoriaAssunzione()!=null) {
                			if (postiLavoroDisp.getCategoriaAssunzione().equals("NU")) {
                				sb.append(GeneraPdfUtilities.createRow("Categoria assunzione","Assunzione Numerica"));
                			} else if (postiLavoroDisp.getCategoriaAssunzione().equals("NO")) {
                				sb.append(GeneraPdfUtilities.createRow("Categoria assunzione","Assunzione Nominativa"));
                			} else {
                				sb.append(GeneraPdfUtilities.createRow("Categoria assunzione",""));
                			}
                		} else {
                			sb.append(GeneraPdfUtilities.createRow("Categoria assunzione",""));
                		}
                		
                		sb.append("<br></br>");
        				
        			}
        			
        		}
        		
        		sb.append(GeneraPdfUtilities.chiudiParagrafo());
        		
        		sb.append(GeneraPdfUtilities.createParagrafo("Elenco compensazioni territoriali"));
        		
        		List<ProDProvCompensazioni> compensazioniList = prospettoDad.caricaCompensazioniPerPdf(prospettoProvincia.get(i).getIdProspettoProv());

        		if (!compensazioniList.isEmpty()) {
        			
        			for (ProDProvCompensazioni compensazioni : compensazioniList) {
        				
        				if (compensazioni!=null && compensazioni.getProTProvincia()!=null && compensazioni.getProTProvincia().getDsProTProvincia()!=null) {
                			sb.append(GeneraPdfUtilities.createRow("Provincia compensazione",compensazioni.getProTProvincia().getDsProTProvincia()));
                		} else {
                			sb.append(GeneraPdfUtilities.createRow("Provincia compensazione",""));
                		}
                		if (compensazioni!=null && compensazioni.getCategoriaCompensazione()!=null) {
                			if (compensazioni.getCategoriaCompensazione().equals("E")) {
                				sb.append(GeneraPdfUtilities.createRow("Categoria compensazione","Eccedenza"));
                			} else if (compensazioni.getCategoriaCompensazione().equals("R")){
                				sb.append(GeneraPdfUtilities.createRow("Categoria compensazione","Riduzione"));
                			} else {
                				sb.append(GeneraPdfUtilities.createRow("Categoria compensazione",""));
                			}
                		} else {
                			sb.append(GeneraPdfUtilities.createRow("Categoria compensazione",""));
                		}
                		if (compensazioni!=null && compensazioni.getnLavoratori()!=null) {
                			sb.append(GeneraPdfUtilities.createRow("Numero lavoratori",compensazioni.getnLavoratori().toString()));
                		} else {
                			sb.append(GeneraPdfUtilities.createRow("Numero lavoratori",""));
                		}
                		if (compensazioni!=null && compensazioni.getCategoriaSoggetto()!=null) {
                			if (compensazioni.getCategoriaSoggetto().equals("D")) {
                				sb.append(GeneraPdfUtilities.createRow("Categoria soggetto","Disabile"));
                			} else if (compensazioni.getCategoriaSoggetto().equals("C"))  {
                				sb.append(GeneraPdfUtilities.createRow("Categoria soggetto","Categoria Protetta"));
                			} else {
                				sb.append(GeneraPdfUtilities.createRow("Categoria soggetto",""));
                			}
                		} else {
                			sb.append(GeneraPdfUtilities.createRow("Categoria soggetto",""));
                		}
                		if (compensazioni!=null && compensazioni.getCfAziendaAppartenAlGruppo()!=null) {
                			sb.append(GeneraPdfUtilities.createRow("Codice fiscale azienda del gruppo",compensazioni.getCfAziendaAppartenAlGruppo()));
                		} else {
                			sb.append(GeneraPdfUtilities.createRow("Codice fiscale azienda del gruppo",""));
                		}
                		
                		sb.append("<br></br>");
        				
        			}

        		}
        		
        		sb.append(GeneraPdfUtilities.chiudiParagrafo());

        	}
        	
        }
        
		return sb;

	}

}
