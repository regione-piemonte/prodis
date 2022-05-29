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
 * The Class ProvGradualita.
 */
public class ProvGradualita extends BaseAuditedDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private BigDecimal nAssunzioniEffDopoTrasf;
	private DatiProvinciali datiProvinciali;


	/**
	 * @return the nAssunzioniEffDopoTrasf
	 */
	public BigDecimal getnAssunzioniEffDopoTrasf() {
		return nAssunzioniEffDopoTrasf;
	}
	
	/**
	 * @param nAssunzioniEffDopoTrasf the nAssunzioniEffDopoTrasf to set
	 */
	public void setnAssunzioniEffDopoTrasf(BigDecimal nAssunzioniEffDopoTrasf) {
		this.nAssunzioniEffDopoTrasf = nAssunzioniEffDopoTrasf;
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
