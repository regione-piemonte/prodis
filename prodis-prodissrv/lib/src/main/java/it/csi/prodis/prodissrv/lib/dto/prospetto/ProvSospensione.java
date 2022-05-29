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
import java.util.Date;

import it.csi.prodis.prodissrv.lib.dto.BaseAuditedDto;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.CausaSospensione;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.StatoConcessione;

/**
 * The Class ProvSospensione.
 */
public class ProvSospensione extends BaseAuditedDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Date dFineSospensioneQ2;
	private BigDecimal nLavoratori;
	private DatiProvinciali datiProvinciali;
	private CausaSospensione causaSospensione;
	private StatoConcessione statoConcessione;

	/**
	 * @return the dFineSospensioneQ2
	 */
	public Date getDFineSospensioneQ2() {
		return dFineSospensioneQ2;
	}
	
	/**
	 * @param dFineSospensioneQ2 the dFineSospensioneQ2 to set
	 */
	public void setDFineSospensioneQ2(Date dFineSospensioneQ2) {
		this.dFineSospensioneQ2 = dFineSospensioneQ2;
	}

	/**
	 * @return the nLavoratori
	 */
	public BigDecimal getnLavoratori() {
		return nLavoratori;
	}
	
	/**
	 * @param nLavoratori the nLavoratori to set
	 */
	public void setnLavoratori(BigDecimal nLavoratori) {
		this.nLavoratori = nLavoratori;
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
	 * @return the causaSospensione
	 */
	public CausaSospensione getCausaSospensione() {
		return causaSospensione;
	}
	
	/**
	 * @param causaSospensione the causaSospensione to set
	 */
	public void setCausaSospensione(CausaSospensione causaSospensione) {
		this.causaSospensione = causaSospensione;
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
