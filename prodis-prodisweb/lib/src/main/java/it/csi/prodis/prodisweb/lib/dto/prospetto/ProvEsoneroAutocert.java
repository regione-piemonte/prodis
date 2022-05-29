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

/**
 * The Class ProvEsoneroAutocert.
 */
public class ProvEsoneroAutocert extends BaseAuditedDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Date dataAutocert;
	private BigDecimal nLav60x1000;
	private BigDecimal nLavEsoneroAutocert;
	private BigDecimal percentualeEsAutocert;
	private DatiProvinciali datiProvinciali;

	/**
	 * @return the dataAutocert
	 */
	public Date getDataAutocert() {
		return dataAutocert;
	}
	
	/**
	 * @param dataAutocert the dataAutocert to set
	 */
	public void setDataAutocert(Date dataAutocert) {
		this.dataAutocert = dataAutocert;
	}

	/**
	 * @return the nLav60x1000
	 */
	public BigDecimal getnLav60x1000() {
		return nLav60x1000;
	}
	
	/**
	 * @param nLav60x1000 the nLav60x1000 to set
	 */
	public void setnLav60x1000(BigDecimal nLav60x1000) {
		this.nLav60x1000 = nLav60x1000;
	}

	/**
	 * @return the nLavEsoneroAutocert
	 */
	public BigDecimal getnLavEsoneroAutocert() {
		return nLavEsoneroAutocert;
	}
	
	/**
	 * @param nLavEsoneroAutocert the nLavEsoneroAutocert to set
	 */
	public void setnLavEsoneroAutocert(BigDecimal nLavEsoneroAutocert) {
		this.nLavEsoneroAutocert = nLavEsoneroAutocert;
	}

	/**
	 * @return the percentualeEsAutocert
	 */
	public BigDecimal getPercentualeEsAutocert() {
		return percentualeEsAutocert;
	}
	
	/**
	 * @param percentualeEsAutocert the percentualeEsAutocert to set
	 */
	public void setPercentualeEsAutocert(BigDecimal percentualeEsAutocert) {
		this.percentualeEsAutocert = percentualeEsAutocert;
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

}
