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

import it.csi.prodis.prodisweb.lib.dto.BaseAuditedDto;

/**
 * The Class ProvIntermittenti.
 */
public class ProvIntermittenti extends BaseAuditedDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private BigDecimal nIntermittenti;
	private BigDecimal orarioSettimanaleContrattual;
	private BigDecimal orarioSettimanaleSvolto;
	private Long idProspettoProv;

	/**
	 * @return the nIntermittenti
	 */
	public BigDecimal getnIntermittenti() {
		return nIntermittenti;
	}
	
	/**
	 * @param nIntermittenti the nIntermittenti to set
	 */
	public void setnIntermittenti(BigDecimal nIntermittenti) {
		this.nIntermittenti = nIntermittenti;
	}

	/**
	 * @return the orarioSettimanaleContrattual
	 */
	public BigDecimal getOrarioSettimanaleContrattual() {
		return orarioSettimanaleContrattual;
	}
	
	/**
	 * @param orarioSettimanaleContrattual the orarioSettimanaleContrattual to set
	 */
	public void setOrarioSettimanaleContrattual(BigDecimal orarioSettimanaleContrattual) {
		this.orarioSettimanaleContrattual = orarioSettimanaleContrattual;
	}

	/**
	 * @return the orarioSettimanaleSvolto
	 */
	public BigDecimal getOrarioSettimanaleSvolto() {
		return orarioSettimanaleSvolto;
	}
	
	/**
	 * @param orarioSettimanaleSvolto the orarioSettimanaleSvolto to set
	 */
	public void setOrarioSettimanaleSvolto(BigDecimal orarioSettimanaleSvolto) {
		this.orarioSettimanaleSvolto = orarioSettimanaleSvolto;
	}

	public Long getIdProspettoProv() {
		return idProspettoProv;
	}

	public void setIdProspettoProv(Long idProspettoProv) {
		this.idProspettoProv = idProspettoProv;
	}

}
