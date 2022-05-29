/*-
 * ========================LICENSE_START=================================
 * PRODIS Servizi - LIB submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodissrv.lib.dto.prospetto;

import java.io.Serializable;
import java.math.BigDecimal;

import it.csi.prodis.prodissrv.lib.dto.BaseDto;

/**
 * The Class ElencoProvScoperture.
 */
public class ElencoProvScoperture extends BaseDto<Long> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Long idProspettoProv;

	private Long idTProvincia;
	private String dsProTProvincia;
	private String dsTarga;

	private BigDecimal numScopertDisab;
	private String catCompensDisab;
	private BigDecimal numScopertCatprot;
	private BigDecimal numCompenDisab;
	private String catCompensCatprot;
	private BigDecimal numCompenCatprot;

	/**
	 * @return the idProspettoProv
	 */
	public Long getIdProspettoProv() {
		return idProspettoProv;
	}

	/**
	 * @param idProspettoProv the idProspettoProv to set
	 */
	public void setIdProspettoProv(Long idProspettoProv) {
		this.idProspettoProv = idProspettoProv;
	}

	/**
	 * @return the idTProvincia
	 */
	public Long getIdTProvincia() {
		return idTProvincia;
	}

	/**
	 * @param idTProvincia the idTProvincia to set
	 */
	public void setIdTProvincia(Long idTProvincia) {
		this.idTProvincia = idTProvincia;
	}

	/**
	 * @return the dsProTProvincia
	 */
	public String getDsProTProvincia() {
		return dsProTProvincia;
	}

	/**
	 * @param dsProTProvincia the dsProTProvincia to set
	 */
	public void setDsProTProvincia(String dsProTProvincia) {
		this.dsProTProvincia = dsProTProvincia;
	}

	/**
	 * @return the dsTarga
	 */
	public String getDsTarga() {
		return dsTarga;
	}

	/**
	 * @param dsTarga the dsTarga to set
	 */
	public void setDsTarga(String dsTarga) {
		this.dsTarga = dsTarga;
	}

	/**
	 * @return the numScopertDisab
	 */
	public BigDecimal getNumScopertDisab() {
		return numScopertDisab;
	}

	/**
	 * @param numScopertDisab the numScopertDisab to set
	 */
	public void setNumScopertDisab(BigDecimal numScopertDisab) {
		this.numScopertDisab = numScopertDisab;
	}

	/**
	 * @return the numScopertCatprot
	 */
	public BigDecimal getNumScopertCatprot() {
		return numScopertCatprot;
	}

	/**
	 * @param numScopertCatprot the numScopertCatprot to set
	 */
	public void setNumScopertCatprot(BigDecimal numScopertCatprot) {
		this.numScopertCatprot = numScopertCatprot;
	}

	/**
	 * @return the numCompenDisab
	 */
	public BigDecimal getNumCompenDisab() {
		return numCompenDisab;
	}

	/**
	 * @param numCompenDisab the numCompenDisab to set
	 */
	public void setNumCompenDisab(BigDecimal numCompenDisab) {
		this.numCompenDisab = numCompenDisab;
	}

	/**
	 * @return the numCompenCatprot
	 */
	public BigDecimal getNumCompenCatprot() {
		return numCompenCatprot;
	}

	/**
	 * @param numCompenCatprot the numCompenCatprot to set
	 */
	public void setNumCompenCatprot(BigDecimal numCompenCatprot) {
		this.numCompenCatprot = numCompenCatprot;
	}

	public String getCatCompensDisab() {
		return catCompensDisab;
	}

	public void setCatCompensDisab(String catCompensDisab) {
		this.catCompensDisab = catCompensDisab;
	}

	public String getCatCompensCatprot() {
		return catCompensCatprot;
	}

	public void setCatCompensCatprot(String catCompensCatprot) {
		this.catCompensCatprot = catCompensCatprot;
	}

}
