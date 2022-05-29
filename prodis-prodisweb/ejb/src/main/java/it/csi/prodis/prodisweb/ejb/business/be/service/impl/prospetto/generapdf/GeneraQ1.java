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

import java.util.List;

import it.csi.prodis.prodisweb.ejb.business.be.dad.ProspettoDad;
import it.csi.prodis.prodisweb.ejb.entity.ProDAssPubbliche;
import it.csi.prodis.prodisweb.ejb.entity.ProDDatiAzienda;
import it.csi.prodis.prodisweb.ejb.entity.ProDProspetto;
import it.csi.prodis.prodisweb.ejb.entity.ProDProspettoGradualita;
import it.csi.prodis.prodisweb.ejb.entity.ProDSedeLegale;

public class GeneraQ1 {
	
	public static StringBuilder getGenerazioneQ1(ProspettoDad prospettoDad, Long idProspetto) {
		
		StringBuilder sb = new StringBuilder();
		
		// QUADRO 1
        
        ProDProspetto prospetto = prospettoDad.caricaProspettoPerPdf(idProspetto);
        
        sb.append(GeneraPdfUtilities.createCapitolo("Quadro 1"));
        
        sb.append(GeneraPdfUtilities.createParagrafo("Dati Prospetto"));     

        if (prospetto.getDataRiferimentoProspetto()!=null){
        	sb.append(GeneraPdfUtilities.createRow("Data riferimento prospetto",GeneraPdfUtilities.removeTime(prospetto.getDataRiferimentoProspetto())));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("Data riferimento prospetto",""));
        }
        if (prospetto.getNumLavorInForzaNazionale()!=null){
        	sb.append(GeneraPdfUtilities.createRow("Numero Lavoratori in forza nazionale",prospetto.getNumLavorInForzaNazionale().toString()));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("Numero Lavoratori in forza nazionale",""));
        }
        
        if (prospetto.getProTCategoriaAzienda()!=null && prospetto.getProTCategoriaAzienda().getDesCategoriaAzienda()!=null){
        	sb.append(GeneraPdfUtilities.createRow("Categoria azienda  L.68/99 Art.3 c.1",prospetto.getProTCategoriaAzienda().getDesCategoriaAzienda()));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("Categoria azienda  L.68/99 Art.3 c.1",""));
        }
        if (prospetto.getFlgNessunaAssunzioneAggiun()!=null){
        	sb.append(GeneraPdfUtilities.createRow("Nessuna assunzione aggiuntiva",prospetto.getFlgNessunaAssunzioneAggiun()));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("Nessuna assunzione aggiuntiva",""));
        }
        if (prospetto.getDataPrimaAssunzione()!=null){
        	sb.append(GeneraPdfUtilities.createRow("Data prima assunzione (DPR.333/2000)",GeneraPdfUtilities.removeTime(prospetto.getDataPrimaAssunzione())));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("Data prima assunzione (DPR.333/2000)",""));
        }
        if (prospetto.getDataSecondaAssunzione()!=null){
        	sb.append(GeneraPdfUtilities.createRow("Data seconda assunzione (DPR.333/2000)",GeneraPdfUtilities.removeTime(prospetto.getDataSecondaAssunzione())));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("Data seconda assunzione (DPR.333/2000)",""));
        }

        sb.append(GeneraPdfUtilities.chiudiParagrafo());
        
        // DATI AZIENDA
        
        ProDDatiAzienda datiAzienda = prospetto.getProDDatiAzienda();
        
        sb.append(GeneraPdfUtilities.createParagrafo("Dati Aziendali"));

        if (datiAzienda.getProTDichiarante()!=null && datiAzienda.getProTDichiarante().getDescDichiarante()!=null){
        	sb.append(GeneraPdfUtilities.createRow("Tipologia del dichiarante",datiAzienda.getProTDichiarante().getDescDichiarante()));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("Tipologia del dichiarante",""));
        }
        if (datiAzienda.getCfAzienda()!=null){
        	sb.append(GeneraPdfUtilities.createRow("Codice Fiscale",datiAzienda.getCfAzienda()));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("Codice Fiscale",""));
        }
        if (datiAzienda.getDenominazioneDatoreLavoro()!=null){
        	sb.append(GeneraPdfUtilities.createRow("Denominazione datore di lavoro",GeneraPdfUtilities.escapeHtml(datiAzienda.getDenominazioneDatoreLavoro())));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("Denominazione datore di lavoro",""));
        }
        if (datiAzienda.getProTAtecofin()!=null && datiAzienda.getProTAtecofin().getDsProTAtecofin()!=null){
        	sb.append(GeneraPdfUtilities.createRow("Settore",datiAzienda.getProTAtecofin().getDsProTAtecofin()));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("Settore",""));
        }
        if (datiAzienda.getProTCcnl()!=null && datiAzienda.getProTCcnl().getDsCcnl()!=null){
        	sb.append(GeneraPdfUtilities.createRow("Contratto collettivo applicato",datiAzienda.getProTCcnl().getDsCcnl()));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("Contratto collettivo applicato",""));
        }
        if (datiAzienda.getFlgProspettoDaCapogruppo()!=null){
        	sb.append(GeneraPdfUtilities.createRow("Prospetto presentato da capogruppo",datiAzienda.getFlgProspettoDaCapogruppo()));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("Prospetto presentato da capogruppo",""));
        }
        if (datiAzienda.getFlgCapogruppoEstero()!=null){
        	sb.append(GeneraPdfUtilities.createRow("Prospetto presentato da capogruppo estero",datiAzienda.getFlgCapogruppoEstero()));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("Prospetto presentato da capogruppo estero",""));
        }
        if (datiAzienda.getCfCapogruppo()!=null){
        	sb.append(GeneraPdfUtilities.createRow("Codice fiscale azienda capogruppo",datiAzienda.getCfCapogruppo()));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("Codice fiscale azienda capogruppo",""));
        }
        
        // SEDE LEGALE
        
        ProDSedeLegale sedeLegale = datiAzienda.getProDSedeLegale();
        
        if (sedeLegale.getProTComune()!=null && sedeLegale.getProTComune().getDsProTComune()!=null){
        	sb.append(GeneraPdfUtilities.createRow("Comune sede legale",sedeLegale.getProTComune().getDsProTComune()));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("Comune sede legale",""));
        }
        if (sedeLegale.getCapSede()!=null){
        	sb.append(GeneraPdfUtilities.createRow("CAP sede legale",sedeLegale.getCapSede()));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("CAP sede legale",""));
        }
        if (sedeLegale.getIndirizzo()!=null){
        	sb.append(GeneraPdfUtilities.createRow("Indirizzo sede legale",GeneraPdfUtilities.escapeHtml(sedeLegale.getIndirizzo())));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("Indirizzo sede legale",""));
        }
        if (sedeLegale.getTelefono()!=null){
        	sb.append(GeneraPdfUtilities.createRow("Telefono sede legale",sedeLegale.getTelefono()));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("Telefono sede legale",""));
        }
        if (sedeLegale.getFax()!=null){
        	sb.append(GeneraPdfUtilities.createRow("Fax sede legale",sedeLegale.getFax()));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("Fax sede legale",""));
        }
        if (sedeLegale.getEmail()!=null){
        	sb.append(GeneraPdfUtilities.createRow("PEC sede legale",GeneraPdfUtilities.escapeHtml(sedeLegale.getEmail())));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("PEC sede legale",""));
        }
        
        if (datiAzienda.getCfReferente()!=null){
        	sb.append(GeneraPdfUtilities.createRow("Codice fiscale referente",datiAzienda.getCfReferente()));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("Codice fiscale referente",""));
        }
        if (datiAzienda.getCognomeReferente()!=null){
        	sb.append(GeneraPdfUtilities.createRow("Cognome referente",GeneraPdfUtilities.escapeHtml(datiAzienda.getCognomeReferente())));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("Cognome referente",""));
        }
        if (datiAzienda.getNomeReferente()!=null){
        	sb.append(GeneraPdfUtilities.createRow("Nome referente",GeneraPdfUtilities.escapeHtml(datiAzienda.getNomeReferente())));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("Nome referente",""));
        }
        if (datiAzienda.getIndirizzoReferente()!=null){
        	sb.append(GeneraPdfUtilities.createRow("Indirizzo referente",GeneraPdfUtilities.escapeHtml(datiAzienda.getIndirizzoReferente())));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("Indirizzo referente",""));
        }
        if (datiAzienda.getProTComune()!=null && datiAzienda.getProTComune().getDsProTComune()!=null){
        	sb.append(GeneraPdfUtilities.createRow("Comune referente",datiAzienda.getProTComune().getDsProTComune()));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("Comune referente",""));
        }
        if (datiAzienda.getCapReferente()!=null){
        	sb.append(GeneraPdfUtilities.createRow("CAP referente",datiAzienda.getCapReferente()));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("CAP referente",""));
        }
        if (datiAzienda.getTelefonoReferente()!=null){
        	sb.append(GeneraPdfUtilities.createRow("Telefono referente",datiAzienda.getTelefonoReferente()));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("Telefono referente",""));
        }
        if (datiAzienda.getFaxReferente()!=null){
        	sb.append(GeneraPdfUtilities.createRow("Fax referente",datiAzienda.getFaxReferente()));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("Fax referente",""));
        }
        if (datiAzienda.getEmailReferente()!=null){
        	sb.append(GeneraPdfUtilities.createRow("E-mail referente",GeneraPdfUtilities.escapeHtml(datiAzienda.getEmailReferente())));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("E-mail referente",""));
        }
        
        sb.append(GeneraPdfUtilities.chiudiParagrafo());
        
        // GRADUALITA
        
        ProDProspettoGradualita gradualita = prospetto.getProDProspettoGradualita();
        
        sb.append(GeneraPdfUtilities.createParagrafo("Gradualita' - Sezione obbligatoria per aziende in gradualita'"));
        
        if (gradualita!=null && gradualita.getDataAtto()!=null){
        	sb.append(GeneraPdfUtilities.createRow("Data atto",GeneraPdfUtilities.removeTime(gradualita.getDataAtto())));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("Data atto",""));
        }
        if (gradualita!=null && gradualita.getEstremiAtto()!=null){
        	sb.append(GeneraPdfUtilities.createRow("Estremi atto",GeneraPdfUtilities.escapeHtml(gradualita.getEstremiAtto())));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("Estremi atto",""));
        }
        if (gradualita!=null && gradualita.getnAssunzioniLavPreTrasf()!=null){
        	sb.append(GeneraPdfUtilities.createRow("Num. assunzioni di lavoratori non disabili effettuate prima della trasformazione",gradualita.getnAssunzioniLavPreTrasf().toString()));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("Num. assunzioni di lavoratori non disabili effettuate prima della trasformazione",""));
        }
        if (gradualita!=null && gradualita.getDataTrasformazione()!=null){
        	sb.append(GeneraPdfUtilities.createRow("Data trasformazione",GeneraPdfUtilities.removeTime(gradualita.getDataTrasformazione())));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("Data trasformazione",""));
        }
        if (gradualita!=null && gradualita.getPercentuale()!=null){
        	sb.append(GeneraPdfUtilities.createRow("Percentuale",gradualita.getPercentuale().toString()));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("Percentuale",""));
        }
        
        sb.append(GeneraPdfUtilities.chiudiParagrafo());
        
        sb.append(GeneraPdfUtilities.createParagrafo("Sospensioni a carattere nazionale"));
        
        if (prospetto.getFlgSospensionePerMobilita()!=null){
        	sb.append(GeneraPdfUtilities.createRow("Sospensione per mobilita'",prospetto.getFlgSospensionePerMobilita()));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("Sospensione per mobilita'",""));
        }
        if (prospetto.getdFineSospensioneQ1()!=null){
        	sb.append(GeneraPdfUtilities.createRow("Data fine sospensione",GeneraPdfUtilities.removeTime(prospetto.getdFineSospensioneQ1())));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("Data fine sospensione",""));
        }
        
        sb.append(GeneraPdfUtilities.chiudiParagrafo());
        
        // ASS PUBBLICHE
        
        List<ProDAssPubbliche> assPubbliche = prospettoDad.caricaAssPubblichePerPdf(idProspetto);
        
        if (!assPubbliche.isEmpty()) {
        	
        	sb.append(GeneraPdfUtilities.createParagrafo("Ass pubbliche"));
        	
        	for (int i=0; i<assPubbliche.size(); i++) {
        		
        		sb.append(GeneraPdfUtilities.createParagrafo("Ass pubblica: "+(i+1)));
        		
        		if (assPubbliche.get(i).getSaldoDisabili()!=null){
        			sb.append(GeneraPdfUtilities.createRow("Saldo disabili",assPubbliche.get(i).getSaldoDisabili().toString()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Saldo disabili",""));
        		}
        		if (assPubbliche.get(i).getSaldoExArt18()!=null){
        			sb.append(GeneraPdfUtilities.createRow("Saldo ex art 18",assPubbliche.get(i).getSaldoExArt18().toString()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Saldo ex art 18",""));
        		}
        		if (assPubbliche.get(i).getCodUserInserim()!=null){
        			sb.append(GeneraPdfUtilities.createRow("Codice user inserimento",assPubbliche.get(i).getCodUserInserim()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Codice user inserimento",""));
        		}
        		if (assPubbliche.get(i).getDInserim()!=null){
        			sb.append(GeneraPdfUtilities.createRow("Data inserimento",GeneraPdfUtilities.removeTime(assPubbliche.get(i).getDInserim())));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Data inserimento",""));
        		}
        		if (assPubbliche.get(i).getCodUserAggiorn()!=null){
        			sb.append(GeneraPdfUtilities.createRow("Codice user aggiornamento",assPubbliche.get(i).getCodUserAggiorn()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Codice user aggiornamento",""));
        		}
        		if (assPubbliche.get(i).getDAggiorn()!=null){
        			sb.append(GeneraPdfUtilities.createRow("Data aggiornamento",GeneraPdfUtilities.removeTime(assPubbliche.get(i).getDAggiorn())));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Data aggiornamento",""));
        		}
        		if (assPubbliche.get(i).getDsNote()!=null){
        			sb.append(GeneraPdfUtilities.createRow("Note",GeneraPdfUtilities.escapeHtml(assPubbliche.get(i).getDsNote())));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Note",""));
        		}
        		
        		sb.append(GeneraPdfUtilities.chiudiParagrafo());
        		
        	}
        	
        	sb.append(GeneraPdfUtilities.chiudiParagrafo());
        	
        }
        
        return sb;
        
	}

}
