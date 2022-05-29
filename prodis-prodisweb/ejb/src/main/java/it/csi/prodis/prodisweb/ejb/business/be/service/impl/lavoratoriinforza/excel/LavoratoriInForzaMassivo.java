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
package it.csi.prodis.prodisweb.ejb.business.be.service.impl.lavoratoriinforza.excel;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;

import org.apache.commons.beanutils.BeanUtils;

import it.csi.prodis.prodisweb.lib.dto.prospetto.LavoratoriInForza;

public class LavoratoriInForzaMassivo extends LavoratoriInForza {

	private static final long serialVersionUID = 1L;
	
	public LavoratoriInForzaMassivo(){}
	
	public LavoratoriInForzaMassivo(LavoratoriInForza lavoratore) {
		try {
			BeanUtils.copyProperties(this, lavoratore);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String siglaProvincia;
	
	private String codiceTipoAssunzioneProtetta;
	private String codiceComune;
	private String codiceStatoEstero;
	private String codiceTipologiaContrattuale;
	private String codiceQualificaIstat;
	
	private String orarioSettimanale;
	private String orarioSettimanalePartTime;
	
	private String flgForma;

	public String getOrarioSettimanale() {
		return orarioSettimanale;
	}

	public void setOrarioSettimanale(String orarioSettimanale) {
		this.orarioSettimanale = orarioSettimanale;
	}

	public String getOrarioSettimanalePartTime() {
		return orarioSettimanalePartTime;
	}

	public void setOrarioSettimanalePartTime(String orarioSettimanalePartTime) {
		this.orarioSettimanalePartTime = orarioSettimanalePartTime;
	}

	public String getSiglaProvincia() {return siglaProvincia;}
	public void setSiglaProvincia(String siglaProvincia) {this.siglaProvincia = siglaProvincia;}
	
	
	public String getCodiceTipoAssunzioneProtetta() {
		return codiceTipoAssunzioneProtetta;
	}

	public void setCodiceTipoAssunzioneProtetta(String codiceTipoAssunzioneProtetta) {
		this.codiceTipoAssunzioneProtetta = codiceTipoAssunzioneProtetta;
	}

	public String getCodiceComune() {
		return codiceComune;
	}

	public void setCodiceComune(String codiceComune) {
		this.codiceComune = codiceComune;
	}

	public String getCodiceStatoEstero() {
		return codiceStatoEstero;
	}

	public void setCodiceStatoEstero(String codiceStatoEstero) {
		this.codiceStatoEstero = codiceStatoEstero;
	}

	public String getCodiceTipologiaContrattuale() {
		return codiceTipologiaContrattuale;
	}

	public void setCodiceTipologiaContrattuale(String codiceTipologiaContrattuale) {
		this.codiceTipologiaContrattuale = codiceTipologiaContrattuale;
	}

	public String getCodiceQualificaIstat() {
		return codiceQualificaIstat;
	}

	public void setCodiceQualificaIstat(String codiceQualificaIstat) {
		this.codiceQualificaIstat = codiceQualificaIstat;
	}

	public boolean equals4Massivo(LavoratoriInForza obj, LinkedHashMap<String, CampoCella> mappaCampi) {
		
		LavoratoriInForza other = (LavoratoriInForza) obj;
		
		//categoria assunzione
		if (mappaCampi.get(NomiCampi.CategoriaAssunzione.toString())!=null
				&& mappaCampi.get(NomiCampi.CategoriaAssunzione.toString()).getComparabile().isComparabile()) {
			if (getCategoriaAssunzione()==null){
				if (other.getCategoriaAssunzione()!=null) {
					return false;
				}
			} else if (!getCategoriaAssunzione().equalsIgnoreCase(other.getCategoriaAssunzione())) {
				return false;
			}
		}
		
		
		//categoria soggetto
		if (mappaCampi.get(NomiCampi.CategoriaSoggetto.toString())!=null
				&& mappaCampi.get(NomiCampi.CategoriaSoggetto.toString()).getComparabile().isComparabile()) {
			if (getCategoriaSoggetto()==null) {
				if (other.getCategoriaSoggetto()!=null) {
					return false;
				}
			} else if (!getCategoriaSoggetto().equalsIgnoreCase(other.getCategoriaSoggetto())) {
				return false;
			}
		}
		
		//codice fiscale
		if (mappaCampi.get(NomiCampi.CodiceFiscale.toString())!=null
				&& mappaCampi.get(NomiCampi.CodiceFiscale.toString()).getComparabile().isComparabile()) {
			if (getCodiceFiscale()==null) {
				if (other.getCodiceFiscale()!=null) {
					return false;
				}
			} else if (!getCodiceFiscale().equalsIgnoreCase(other.getCodiceFiscale())) {
				return false;
			}
		}
		
		//data fine rapporto
		if (mappaCampi.get(NomiCampi.DataFineRapporto.toString())!=null
				&& mappaCampi.get(NomiCampi.DataFineRapporto.toString()).getComparabile().isComparabile()) {
			if (getDataFineRapporto()==null) {
				if (other.getDataFineRapporto()!=null) {
					return false;
				}
			} else if (other.getDataFineRapporto()!=null) {
				if (!(removeTime(getDataFineRapporto()).compareTo(removeTime(other.getDataFineRapporto()))==0)) {
					return false;
				}
			}
		}
		
		//data inizio rapporto
		if (mappaCampi.get(NomiCampi.DataInizioRapporto.toString())!=null
				&& mappaCampi.get(NomiCampi.DataInizioRapporto.toString()).getComparabile().isComparabile()) {
			if (getDataInizioRapporto()==null) {
				if (other.getDataInizioRapporto()!=null) {
					return false;
				}
			} else if (!(removeTime(getDataInizioRapporto()).compareTo(removeTime(other.getDataInizioRapporto()))==0)) {
				return false;
			}
		}
		
		//data nascita
		if (mappaCampi.get(NomiCampi.DataNascita.toString())!=null
				&& mappaCampi.get(NomiCampi.DataNascita.toString()).getComparabile().isComparabile()) {
			if (getDataNascita()==null) {
				if (other.getDataNascita()!=null) {
					return false;
				}
			} else if (!(removeTime(getDataNascita()).compareTo(removeTime(other.getDataNascita()))==0)) {
				return false;
			}
		}
		
		//comune
		if (mappaCampi.get(NomiCampi.CodiceComune.toString())!=null
				&& mappaCampi.get(NomiCampi.CodiceComune.toString()).getComparabile().isComparabile()) {
			if (getComune()==null || getComune().getId()==null) {
				if (other.getComune()!=null && other.getComune().getId()!=null) {
					return false;
				}
			} else if (!getComune().getId().equals(other.getComune().getId())) {
				return false;
			}
		}
		
		//qualifica istat
		if (mappaCampi.get(NomiCampi.CodiceQualificaIstat.toString())!=null
				&& mappaCampi.get(NomiCampi.CodiceQualificaIstat.toString()).getComparabile().isComparabile()) {
			if (getIstat2001livello5()==null || getIstat2001livello5().getId()==null) {
				if (other.getIstat2001livello5()!=null && other.getIstat2001livello5().getId()!=null) {
					return false;
				}
			} else if (!getIstat2001livello5().getId().equals(other.getIstat2001livello5().getId())) {
				return false;
			}
		}
		
		//stato estero
		if (mappaCampi.get(NomiCampi.CodiceStatoEstero.toString())!=null
				&& mappaCampi.get(NomiCampi.CodiceStatoEstero.toString()).getComparabile().isComparabile()) {
			if (getStatiEsteri()==null || getStatiEsteri().getId()==null) {
				if (other.getStatiEsteri()!=null && other.getStatiEsteri().getId()!=null) {
					return false;
				}
			} else if (!getStatiEsteri().getId().equals(other.getStatiEsteri().getId())) {
				return false;
			}
		}
		
		//tipo assunzione protetta
		if (mappaCampi.get(NomiCampi.CodiceTipoAssunzioneProtetta.toString())!=null
				&& mappaCampi.get(NomiCampi.CodiceTipoAssunzioneProtetta.toString()).getComparabile().isComparabile()) {
			if (getAssunzioneProtetta()==null || getAssunzioneProtetta().getId()==null) {
				if (other.getAssunzioneProtetta()!=null && other.getAssunzioneProtetta().getId()!=null) {
					return false;
				}
			} else if (!getAssunzioneProtetta().getId()
					.equals(other.getAssunzioneProtetta().getId())) {
				return false;
			}
		}
		
		//tipologia contrattuale
		if (mappaCampi.get(NomiCampi.CodiceTipologiaContrattuale.toString())!=null
				&& mappaCampi.get(NomiCampi.CodiceTipologiaContrattuale.toString()).getComparabile().isComparabile()) {
			if (getContratti()==null || getContratti().getTipo()==null) {
				if (other.getContratti()!=null && other.getContratti().getTipo()!=null) {
					return false;
				}
			} else if (!getContratti().getTipo()
					.equals(other.getContratti().getTipo())) {
				return false;
			}
		}
		
		//nome
		if (mappaCampi.get(NomiCampi.Nome.toString())!=null
				&& mappaCampi.get(NomiCampi.Nome.toString()).getComparabile().isComparabile()) {
			if (getNome()==null) {
				if (other.getNome()!=null) {
					return false;
				}
			} else if (!getNome().equalsIgnoreCase(other.getNome())) {
				return false;
			}
		}
		
		//cognome
		if (mappaCampi.get(NomiCampi.Cognome.toString())!=null
				&& mappaCampi.get(NomiCampi.Cognome.toString()).getComparabile().isComparabile()) {
			if (getCognome()==null) {
				if (other.getCognome()!=null) {
					return false;
				}
			} else if (!getCognome().equalsIgnoreCase(other.getCognome())) {
				return false; 
			}
		}
		
		//orario settimanale
		if (mappaCampi.get(NomiCampi.OrarioSettimanale.toString())!=null
				&& mappaCampi.get(NomiCampi.OrarioSettimanale.toString()).getComparabile().isComparabile()) {
			if (getOrarioSettContrattualeMin()==null) {
				if (other.getOrarioSettContrattualeMin()!=null) {
					return false;
				}
			} else if (!getOrarioSettContrattualeMin().equals(other.getOrarioSettContrattualeMin())) {
				return false;
			}
		}
		
		//orario settimanale parttime
		if (mappaCampi.get(NomiCampi.OrarioSettimanalePartTime.toString())!=null
				&& mappaCampi.get(NomiCampi.OrarioSettimanalePartTime.toString()).getComparabile().isComparabile()) {
			if (getOrarioSettPartTimeMin()==null) {
				if (other.getOrarioSettPartTimeMin()!=null) {
					return false;
				}
			} else if (!getOrarioSettPartTimeMin()
					.equals(other.getOrarioSettPartTimeMin())) {
				return false;
			}
		}
		
		//percentuale disabilita
		if (mappaCampi.get(NomiCampi.PercentualeDisabilita.toString())!=null
				&& mappaCampi.get(NomiCampi.PercentualeDisabilita.toString()).getComparabile().isComparabile()) {
			if (getPercentualeDisabilita()==null) {
				if (other.getPercentualeDisabilita()!=null) {
					return false;
				}
			} else if (!getPercentualeDisabilita().equals(other.getPercentualeDisabilita())) {
				return false;
			}
		}
		
		//sesso
		if (mappaCampi.get(NomiCampi.Sesso.toString())!=null
				&& mappaCampi.get(NomiCampi.Sesso.toString()).getComparabile().isComparabile()) {
			if (getSesso()==null) {
				if (other.getSesso()!=null) {
					return false;
				}
			} else if (!getSesso().equalsIgnoreCase(other.getSesso())) {
				return false;
			}
		}
		
		return true;
	}
	
	public static Date removeTime(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.set(Calendar.HOUR_OF_DAY, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    cal.set(Calendar.MILLISECOND, 0);
	    return cal.getTime();
	}

	public String getFlgForma() {
		return flgForma;
	}

	public void setFlgForma(String flgForma) {
		this.flgForma = flgForma;
	}
	
}
