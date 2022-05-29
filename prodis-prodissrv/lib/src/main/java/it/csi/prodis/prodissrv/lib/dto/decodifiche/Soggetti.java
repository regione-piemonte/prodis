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
package it.csi.prodis.prodissrv.lib.dto.decodifiche;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import it.csi.prodis.prodissrv.lib.dto.BaseDto;
import it.csi.prodis.prodissrv.lib.dto.prospetto.Prospetto;

/**
 * The Class Soggetti.
 */
public class Soggetti extends BaseDto<Long> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String codSoggetti;
	private Date dataFine;
	private Date dataInizio;
	private Date dataTmst;
	private String descSoggetti;
	private List<Prospetto> prospettos;

	/**
	 * @return the codSoggetti
	 */
	public String getCodSoggetti() {
		return codSoggetti;
	}
	
	/**
	 * @param codSoggetti the codSoggetti to set
	 */
	public void setCodSoggetti(String codSoggetti) {
		this.codSoggetti = codSoggetti;
	}

	/**
	 * @return the dataFine
	 */
	public Date getDataFine() {
		return dataFine;
	}
	
	/**
	 * @param dataFine the dataFine to set
	 */
	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	/**
	 * @return the dataInizio
	 */
	public Date getDataInizio() {
		return dataInizio;
	}
	
	/**
	 * @param dataInizio the dataInizio to set
	 */
	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	/**
	 * @return the dataTmst
	 */
	public Date getDataTmst() {
		return dataTmst;
	}
	
	/**
	 * @param dataTmst the dataTmst to set
	 */
	public void setDataTmst(Date dataTmst) {
		this.dataTmst = dataTmst;
	}

	/**
	 * @return the descSoggetti
	 */
	public String getDescSoggetti() {
		return descSoggetti;
	}
	
	/**
	 * @param descSoggetti the descSoggetti to set
	 */
	public void setDescSoggetti(String descSoggetti) {
		this.descSoggetti = descSoggetti;
	}

	/**
	 * @return the prospettos
	 */
	public List<Prospetto> getProspettos() {
		return prospettos;
	}
	
	/**
	 * @param prospettos the prospettos to set
	 */
	public void setProspettos(List<Prospetto> prospettos) {
		this.prospettos = prospettos;
	}

}
