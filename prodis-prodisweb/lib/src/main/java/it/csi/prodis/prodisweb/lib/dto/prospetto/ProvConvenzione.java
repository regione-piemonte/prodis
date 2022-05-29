/*-
 * ========================LICENSE_START=================================
 * PRODIS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodisweb.lib.dto.prospetto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import it.csi.prodis.prodisweb.lib.dto.BaseDto;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.AssunzioneProtetta;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.StatoConcessione;

/**
 * The Class ProvConvenzione.
 */
public class ProvConvenzione extends BaseDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String codUserAggiorn;
	private String codUserInserim;
	private Date dAggiorn;
	private Date dInserim;
	private Date dataAtto;
	private Date dataScadenza;
	private Date dataStipula;
	private String estremiAtto;
	private BigDecimal numLavPrevConvQ2;
	private DatiProvinciali datiProvinciali;
	private AssunzioneProtetta assunzioneProtetta;
	private StatoConcessione statoConcessione;

	/**
	 * @return the codUserAggiorn
	 */
	public String getCodUserAggiorn() {
		return codUserAggiorn;
	}
	
	/**
	 * @param codUserAggiorn the codUserAggiorn to set
	 */
	public void setCodUserAggiorn(String codUserAggiorn) {
		this.codUserAggiorn = codUserAggiorn;
	}

	/**
	 * @return the codUserInserim
	 */
	public String getCodUserInserim() {
		return codUserInserim;
	}
	
	/**
	 * @param codUserInserim the codUserInserim to set
	 */
	public void setCodUserInserim(String codUserInserim) {
		this.codUserInserim = codUserInserim;
	}

	/**
	 * @return the dAggiorn
	 */
	public Date getDAggiorn() {
		return dAggiorn;
	}
	
	/**
	 * @param dAggiorn the dAggiorn to set
	 */
	public void setDAggiorn(Date dAggiorn) {
		this.dAggiorn = dAggiorn;
	}

	/**
	 * @return the dInserim
	 */
	public Date getDInserim() {
		return dInserim;
	}
	
	/**
	 * @param dInserim the dInserim to set
	 */
	public void setDInserim(Date dInserim) {
		this.dInserim = dInserim;
	}

	/**
	 * @return the dataAtto
	 */
	public Date getDataAtto() {
		return dataAtto;
	}
	
	/**
	 * @param dataAtto the dataAtto to set
	 */
	public void setDataAtto(Date dataAtto) {
		this.dataAtto = dataAtto;
	}

	/**
	 * @return the dataScadenza
	 */
	public Date getDataScadenza() {
		return dataScadenza;
	}
	
	/**
	 * @param dataScadenza the dataScadenza to set
	 */
	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	/**
	 * @return the dataStipula
	 */
	public Date getDataStipula() {
		return dataStipula;
	}
	
	/**
	 * @param dataStipula the dataStipula to set
	 */
	public void setDataStipula(Date dataStipula) {
		this.dataStipula = dataStipula;
	}

	/**
	 * @return the estremiAtto
	 */
	public String getEstremiAtto() {
		return estremiAtto;
	}
	
	/**
	 * @param estremiAtto the estremiAtto to set
	 */
	public void setEstremiAtto(String estremiAtto) {
		this.estremiAtto = estremiAtto;
	}

	/**
	 * @return the numLavPrevConvQ2
	 */
	public BigDecimal getNumLavPrevConvQ2() {
		return numLavPrevConvQ2;
	}
	
	/**
	 * @param numLavPrevConvQ2 the numLavPrevConvQ2 to set
	 */
	public void setNumLavPrevConvQ2(BigDecimal numLavPrevConvQ2) {
		this.numLavPrevConvQ2 = numLavPrevConvQ2;
	}

	/**
	 * @return the datiProvinciali
	 */
	public DatiProvinciali getDatiProvinciali() {
		return datiProvinciali;
	}
	
	/**
	 * @param datiProvinciali the datiProvinciali to set
	 */
	public void setDatiProvinciali(DatiProvinciali datiProvinciali) {
		this.datiProvinciali = datiProvinciali;
	}

	/**
	 * @return the assunzioneProtetta
	 */
	public AssunzioneProtetta getAssunzioneProtetta() {
		return assunzioneProtetta;
	}
	
	/**
	 * @param assunzioneProtetta the assunzioneProtetta to set
	 */
	public void setAssunzioneProtetta(AssunzioneProtetta assunzioneProtetta) {
		this.assunzioneProtetta = assunzioneProtetta;
	}

	/**
	 * @return the statoConcessione
	 */
	public StatoConcessione getStatoConcessione() {
		return statoConcessione;
	}
	
	/**
	 * @param statoConcessione the statoConcessione to set
	 */
	public void setStatoConcessione(StatoConcessione statoConcessione) {
		this.statoConcessione = statoConcessione;
	}

}
