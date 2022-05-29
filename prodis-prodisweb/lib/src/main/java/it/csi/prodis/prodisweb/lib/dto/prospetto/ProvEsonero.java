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

import it.csi.prodis.prodisweb.lib.dto.BaseAuditedDto;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.StatoConcessione;

/**
 * The Class ProvEsonero.
 */
public class ProvEsonero extends BaseAuditedDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Date dataAtto;
	private Date dataAttoFinoAl;
	private String estremiAtto;
	private BigDecimal nLavoratoriEsonero;
	private BigDecimal percentuale;
	private DatiProvinciali datiProvinciali;
	private StatoConcessione statoConcessione;


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
	 * @return the dataAttoFinoAl
	 */
	public Date getDataAttoFinoAl() {
		return dataAttoFinoAl;
	}
	
	/**
	 * @param dataAttoFinoAl the dataAttoFinoAl to set
	 */
	public void setDataAttoFinoAl(Date dataAttoFinoAl) {
		this.dataAttoFinoAl = dataAttoFinoAl;
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
	 * @return the nLavoratoriEsonero
	 */
	public BigDecimal getnLavoratoriEsonero() {
		return nLavoratoriEsonero;
	}
	
	/**
	 * @param nLavoratoriEsonero the nLavoratoriEsonero to set
	 */
	public void setnLavoratoriEsonero(BigDecimal nLavoratoriEsonero) {
		this.nLavoratoriEsonero = nLavoratoriEsonero;
	}

	/**
	 * @return the percentuale
	 */
	public BigDecimal getPercentuale() {
		return percentuale;
	}
	
	/**
	 * @param percentuale the percentuale to set
	 */
	public void setPercentuale(BigDecimal percentuale) {
		this.percentuale = percentuale;
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
