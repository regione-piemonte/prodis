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
import it.csi.prodis.prodisweb.ejb.entity.ProDProspetto;
import it.csi.prodis.prodisweb.ejb.entity.ProDRiepilogoNazionale;
import it.csi.prodis.prodisweb.ejb.entity.ProDRiepilogoProvinciale;
import it.csi.prodis.prodisweb.ejb.entity.ProRProspettoProvincia;

public class GeneraQ3 {

	public static StringBuilder getGenerazioneQ3(ProspettoDad prospettoDad, Long idProspetto, List<ProRProspettoProvincia> prospettoProvincia) {
		
		StringBuilder sb = new StringBuilder();
		
		// QUADRO 3
		
		sb.append(GeneraPdfUtilities.createCapitolo("Quadro 3"));
		
		if (!prospettoProvincia.isEmpty()) {
        	
        	for (int i=0; i<prospettoProvincia.size(); i++) {

        		sb.append(GeneraPdfUtilities.createParagrafo("Elenco riepilogativo provinciale"));
        		
        		ProDRiepilogoProvinciale riepilogoProvinciale = prospettoDad.caricaRiepilogoProvincialePerPdf(prospettoProvincia.get(i).getIdProspettoProv());
                
        		if (prospettoProvincia.get(i).getProTProvincia()!=null && prospettoProvincia.get(i).getProTProvincia().getDsTarga()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Provincia",prospettoProvincia.get(i).getProTProvincia().getDsTarga()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Provincia",""));
        		}
        		if (riepilogoProvinciale!=null && riepilogoProvinciale.getBaseComputoArt3()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Numero lavoratori (base computo art. 3)",riepilogoProvinciale.getBaseComputoArt3().toString()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Numero lavoratori (base computo art. 3)",""));
        		}
        		if (riepilogoProvinciale!=null && riepilogoProvinciale.getBaseComputoArt18()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Numero lavoratori (base computo art. 18)",riepilogoProvinciale.getBaseComputoArt18().toString()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Numero lavoratori (base computo art. 18)",""));
        		}
        		if (riepilogoProvinciale!=null && riepilogoProvinciale.getFlgSospensioniInCorso()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Flag sospensioni in corso",riepilogoProvinciale.getFlgSospensioniInCorso()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Flag sospensioni in corso",""));
        		}
        		if (riepilogoProvinciale!=null && riepilogoProvinciale.getCatCompensazioneDisabili()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Categoria compensazione disabili",riepilogoProvinciale.getCatCompensazioneDisabili()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Categoria compensazione disabili",""));
        		}
        		if (riepilogoProvinciale!=null && riepilogoProvinciale.getNumCompensazioneDisabili()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Num compensazione disabili",riepilogoProvinciale.getNumCompensazioneDisabili().toString()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Num compensazione disabili",""));
        		}
        		if (riepilogoProvinciale!=null && riepilogoProvinciale.getCatCompensazioneCateProt()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Categoria compensazione categorie protette",riepilogoProvinciale.getCatCompensazioneCateProt()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Categoria compensazione cateogorie protette",""));
        		}
        		if (riepilogoProvinciale!=null && riepilogoProvinciale.getNumCompensazioniCateProt()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Num compensazione cateogorie protette",riepilogoProvinciale.getNumCompensazioniCateProt().toString()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Num compensazione cateogorie protette",""));
        		}
        		if (riepilogoProvinciale!=null && riepilogoProvinciale.getNumDisabiliInForza()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Num.disabili in forza (L.68/99 Art.3)",riepilogoProvinciale.getNumDisabiliInForza().toString()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Num.disabili in forza (L.68/99 Art.3)",""));
        		}
        		if (riepilogoProvinciale!=null && riepilogoProvinciale.getNumCatProtInForza()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Num.categorie protette in forza (L.68/99 Art.18)",riepilogoProvinciale.getNumCatProtInForza().toString()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Num.categorie protette in forza (L.68/99 Art.18)",""));
        		}
        		if (riepilogoProvinciale!=null && riepilogoProvinciale.getQuotaRiservaDisabili()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Quota riserva disabili",riepilogoProvinciale.getQuotaRiservaDisabili().toString()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Quota riserva disabili",""));
        		}
        		if (riepilogoProvinciale!=null && riepilogoProvinciale.getQuotaRiservaArt18()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Quota riserva art.18",riepilogoProvinciale.getQuotaRiservaArt18().toString()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Quota riserva art.18",""));
        		}
        		if (riepilogoProvinciale!=null && riepilogoProvinciale.getNumPosizioniEsonerate()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Num posizioni esonerate",riepilogoProvinciale.getNumPosizioniEsonerate().toString()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Num posizioni esonerate",""));
        		}
        		if (riepilogoProvinciale!=null && riepilogoProvinciale.getNumScopertureDisabili()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Num.scoperture disabili (L.68/99 Art.3)",riepilogoProvinciale.getNumScopertureDisabili().toString()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Num.scoperture disabili (L.68/99 Art.3)",""));
        		}
        		if (riepilogoProvinciale!=null && riepilogoProvinciale.getNumScopertureCatProt()!=null) {
        			sb.append(GeneraPdfUtilities.createRow("Num.scoperture categorie protette (L.68/99 Art.18)",riepilogoProvinciale.getNumScopertureCatProt().toString()));
        		} else {
        			sb.append(GeneraPdfUtilities.createRow("Num.scoperture categorie protette (L.68/99 Art.18)",""));
        		}
                
                sb.append(GeneraPdfUtilities.chiudiParagrafo());
		
        	}
        	
		}

        sb.append(GeneraPdfUtilities.createParagrafo("Riepilogo Nazionale"));
        
        ProDRiepilogoNazionale riepilogoNazionale = prospettoDad.caricaRiepilogoNazionalePerPdf(idProspetto);
        
        if (riepilogoNazionale!=null && riepilogoNazionale.getBaseComputoArt3()!=null) {
			sb.append(GeneraPdfUtilities.createRow("Numero lavoratori (base computo art. 3)",riepilogoNazionale.getBaseComputoArt3().toString()));
		} else {
			sb.append(GeneraPdfUtilities.createRow("Numero lavoratori (base computo art. 3)",""));
		}
        if (riepilogoNazionale!=null && riepilogoNazionale.getBaseComputoArt18()!=null) {
			sb.append(GeneraPdfUtilities.createRow("Numero lavoratori (base computo art. 18)",riepilogoNazionale.getBaseComputoArt18().toString()));
		} else {
			sb.append(GeneraPdfUtilities.createRow("Numero lavoratori (base computo art. 18)",""));
		}
        if (riepilogoNazionale!=null && riepilogoNazionale.getQuotaRiservaDisabili()!=null) {
			sb.append(GeneraPdfUtilities.createRow("Quota riserva disabili",riepilogoNazionale.getQuotaRiservaDisabili().toString()));
		} else {
			sb.append(GeneraPdfUtilities.createRow("Quota riserva disabili",""));
		}
        if (riepilogoNazionale!=null && riepilogoNazionale.getQuotaRiservaArt18()!=null) {
			sb.append(GeneraPdfUtilities.createRow("Quota di riserva Art.18",riepilogoNazionale.getQuotaRiservaArt18().toString()));
		} else {
			sb.append(GeneraPdfUtilities.createRow("Quota di riserva Art.18",""));
		}
        if (riepilogoNazionale!=null && riepilogoNazionale.getFlgSospensioniInCorso()!=null) {
			sb.append(GeneraPdfUtilities.createRow("Flag sospensioni in corso",riepilogoNazionale.getFlgSospensioniInCorso().toString()));
		} else {
			sb.append(GeneraPdfUtilities.createRow("Flag sospensioni in corso",""));
		}
        if (riepilogoNazionale!=null && riepilogoNazionale.getNumPosizioniEsonerate()!=null) {
			sb.append(GeneraPdfUtilities.createRow("Num Posizioni Esonerate",riepilogoNazionale.getNumPosizioniEsonerate().toString()));
		} else {
			sb.append(GeneraPdfUtilities.createRow("Num Posizioni Esonerate",""));
		}
        if (riepilogoNazionale!=null && riepilogoNazionale.getNumDisabiliInForza()!=null) {
			sb.append(GeneraPdfUtilities.createRow("Num.disabili in forza (L.68/99 Art.3)",riepilogoNazionale.getNumDisabiliInForza().toString()));
		} else {
			sb.append(GeneraPdfUtilities.createRow("Num.disabili in forza (L.68/99 Art.3)",""));
		}
        if (riepilogoNazionale!=null && riepilogoNazionale.getNumCatProtInForza()!=null) {
			sb.append(GeneraPdfUtilities.createRow("Num.Categorie protette in forza (L.68/99 Art.18)",riepilogoNazionale.getNumCatProtInForza().toString()));
		} else {
			sb.append(GeneraPdfUtilities.createRow("Num.Categorie protette in forza (L.68/99 Art.18)",""));
		}
        if (riepilogoNazionale!=null && riepilogoNazionale.getQuotaEsuberiArt18()!=null) {
			sb.append(GeneraPdfUtilities.createRow("Quota esuberi art.18",riepilogoNazionale.getQuotaEsuberiArt18().toString()));
		} else {
			sb.append(GeneraPdfUtilities.createRow("Quota esuberi art.18",""));
		}
        if (riepilogoNazionale!=null && riepilogoNazionale.getNumScopertDisabili()!=null) {
			sb.append(GeneraPdfUtilities.createRow("Num.Scoperture Disabili (L.68/99 Art.3)",riepilogoNazionale.getNumScopertDisabili().toString()));
		} else {
			sb.append(GeneraPdfUtilities.createRow("Num.Scoperture Disabili (L.68/99 Art.3)",""));
		}
        if (riepilogoNazionale!=null && riepilogoNazionale.getNumScopertCategorieProtette()!=null) {
			sb.append(GeneraPdfUtilities.createRow("Num.Scoperture Categorie protette (L.68/99 Art.18)",riepilogoNazionale.getNumScopertCategorieProtette().toString()));
		} else {
			sb.append(GeneraPdfUtilities.createRow("Num.Scoperture Categorie protette (L.68/99 Art.18)",""));
		}
        
        sb.append(GeneraPdfUtilities.chiudiParagrafo());
        
        ProDProspetto prospetto = prospettoDad.caricaProspettoPerPdf(idProspetto);
        
        sb.append(GeneraPdfUtilities.createParagrafo("Note"));

		if (prospetto!=null && prospetto.getNote()!=null) {
			sb.append(GeneraPdfUtilities.createRow("Note",GeneraPdfUtilities.escapeHtml(prospetto.getNote())));
		} else {
			sb.append(GeneraPdfUtilities.createRow("Note",""));
		}
        
        sb.append(GeneraPdfUtilities.chiudiParagrafo());
        
        sb.append(GeneraPdfUtilities.createParagrafo("Dati Invio"));
        
        if (prospetto!=null && prospetto.getProTSoggetti()!=null && prospetto.getProTSoggetti().getDescSoggetti()!=null) {
        	sb.append(GeneraPdfUtilities.createRow("Soggetto che effettua la comunicazione se diverso dall'azienda di cui\r\n" + 
        			"                                                                si sta effettuando la dichiarazione", prospetto.getProTSoggetti().getDescSoggetti()));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("Soggetto che effettua la comunicazione se diverso dall'azienda di cui\r\n" + 
        			"                                                                si sta effettuando la dichiarazione", ""));
        }
        if (prospetto!=null && prospetto.getCfStudioProfessionale()!=null) {
        	sb.append(GeneraPdfUtilities.createRow("Codice fiscale del soggetto che effettua la comunicazione se diverso\r\n" + 
        			"                                                                              dall'azienda di cui si sta effettuando la dichiarazione", prospetto.getCfStudioProfessionale()));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("Codice fiscale del soggetto che effettua la comunicazione se diverso\r\n" + 
        			"                                                                              dall'azienda di cui si sta effettuando la dichiarazione", ""));
        }
        if (prospetto!=null && prospetto.getEmailSoggettoComunicazione()!=null) {
        	sb.append(GeneraPdfUtilities.createRow("E-mail del soggetto che effettua la comunicazione",GeneraPdfUtilities.escapeHtml(prospetto.getEmailSoggettoComunicazione())));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("E-mail del soggetto che effettua la comunicazione", ""));
        }
        if (prospetto!=null && prospetto.getCodiceComunicazionePreced()!=null) {
        	sb.append(GeneraPdfUtilities.createRow("Codice comunicazione precedente", prospetto.getCodiceComunicazionePreced()));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("Codice comunicazione precedente", ""));
        }
        if (prospetto!=null && prospetto.getProTComunicazione()!=null && prospetto.getProTComunicazione().getDescComunicazione()!=null) {
        	sb.append(GeneraPdfUtilities.createRow("Tipo comunicazione", prospetto.getProTComunicazione().getDescComunicazione().toString()));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("Tipo comunicazione", ""));
        }
        if (prospetto!=null && prospetto.getCodiceComunicazione()!=null) {
        	sb.append(GeneraPdfUtilities.createRow("Codice comunicazione", prospetto.getCodiceComunicazione().toString()));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("Codice comunicazione", ""));
        }
        if (prospetto!=null && prospetto.getNumeroProtocollo()!=null && prospetto.getAnnoProtocollo()!=null) {
        	sb.append(GeneraPdfUtilities.createRow("Numero protocollo", 
        			prospetto.getAnnoProtocollo().toString() + "/" + prospetto.getNumeroProtocollo().toString()));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("Numero protocollo", ""));
        }
        if (prospetto!=null && prospetto.getDataTimbroPostale()!=null) {
        	sb.append(GeneraPdfUtilities.createRow("Data timbro postale",GeneraPdfUtilities.removeTime(prospetto.getDataTimbroPostale())));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("Data timbro postale", ""));
        }
        if (prospetto!=null && prospetto.getDataInvio()!=null) {
        	sb.append(GeneraPdfUtilities.createRow("Data invio",GeneraPdfUtilities.removeTime(prospetto.getDataInvio())));
        } else {
        	sb.append(GeneraPdfUtilities.createRow("Data invio", ""));
        }

        sb.append(GeneraPdfUtilities.chiudiParagrafo());
		
		return sb;
		
	}

}
