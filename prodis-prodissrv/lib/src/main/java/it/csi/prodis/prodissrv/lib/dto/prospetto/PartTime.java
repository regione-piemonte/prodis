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

import it.csi.prodis.prodissrv.lib.dto.BaseAuditedDto;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.TipoRipropPt;

/**
 * The Class PartTime.
 */
public class PartTime extends BaseAuditedDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private BigDecimal nPartTime;
	private BigDecimal orarioSettContrattualeMin;
	private BigDecimal orarioSettPartTimeMin;
	private Long idProspettoProv;
	private TipoRipropPt tipoRipropPt;

	/**
	 * @return the nPartTime
	 */
	public BigDecimal getnPartTime() {
		return nPartTime;
	}
	
	/**
	 * @param nPartTime the nPartTime to set
	 */
	public void setnPartTime(BigDecimal nPartTime) {
		this.nPartTime = nPartTime;
	}

	/**
	 * @return the orarioSettContrattualeMin
	 */
	public BigDecimal getOrarioSettContrattualeMin() {
		return orarioSettContrattualeMin;
	}
	
	/**
	 * @param orarioSettContrattualeMin the orarioSettContrattualeMin to set
	 */
	public void setOrarioSettContrattualeMin(BigDecimal orarioSettContrattualeMin) {
		this.orarioSettContrattualeMin = orarioSettContrattualeMin;
	}

	/**
	 * @return the orarioSettPartTimeMin
	 */
	public BigDecimal getOrarioSettPartTimeMin() {
		return orarioSettPartTimeMin;
	}
	
	/**
	 * @param orarioSettPartTimeMin the orarioSettPartTimeMin to set
	 */
	public void setOrarioSettPartTimeMin(BigDecimal orarioSettPartTimeMin) {
		this.orarioSettPartTimeMin = orarioSettPartTimeMin;
	}

	/**
	 * @return the tipoRipropPt
	 */
	public TipoRipropPt getTipoRipropPt() {
		return tipoRipropPt;
	}
	
	/**
	 * @param tipoRipropPt the tipoRipropPt to set
	 */
	public void setTipoRipropPt(TipoRipropPt tipoRipropPt) {
		this.tipoRipropPt = tipoRipropPt;
	}

	public Long getIdProspettoProv() {
		return idProspettoProv;
	}

	public void setIdProspettoProv(Long idProspettoProv) {
		this.idProspettoProv = idProspettoProv;
	}

}
