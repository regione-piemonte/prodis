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
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProvSospensione;

/**
 * The Class CausaSospensione.
 */
public class CausaSospensione extends BaseDto<Long> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String codCausaSospensione;
	private Date dataFine;
	private Date dataInizio;
	private Date dataTmst;
	private String desCausaSospensione;
	private List<ProvSospensione> provSospensiones;

	/**
	 * @return the codCausaSospensione
	 */
	public String getCodCausaSospensione() {
		return codCausaSospensione;
	}
	
	/**
	 * @param codCausaSospensione the codCausaSospensione to set
	 */
	public void setCodCausaSospensione(String codCausaSospensione) {
		this.codCausaSospensione = codCausaSospensione;
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
	 * @return the desCausaSospensione
	 */
	public String getDesCausaSospensione() {
		return desCausaSospensione;
	}
	
	/**
	 * @param desCausaSospensione the desCausaSospensione to set
	 */
	public void setDesCausaSospensione(String desCausaSospensione) {
		this.desCausaSospensione = desCausaSospensione;
	}

	/**
	 * @return the provSospensiones
	 */
	public List<ProvSospensione> getProvSospensiones() {
		return provSospensiones;
	}
	
	/**
	 * @param provSospensiones the provSospensiones to set
	 */
	public void setProvSospensiones(List<ProvSospensione> provSospensiones) {
		this.provSospensiones = provSospensiones;
	}

}
