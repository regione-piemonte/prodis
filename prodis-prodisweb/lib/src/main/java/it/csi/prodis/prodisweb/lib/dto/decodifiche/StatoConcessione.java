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
package it.csi.prodis.prodisweb.lib.dto.decodifiche;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import it.csi.prodis.prodisweb.lib.dto.BaseDto;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProvCompensazioni;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProvConvenzione;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProvEsonero;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProvSospensione;

/**
 * The Class StatoConcessione.
 */
public class StatoConcessione extends BaseDto<Long> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String codStatoConcessione;
	private Date dataFine;
	private Date dataInizio;
	private Date dataTmst;
	private String descStatoConcessione;
	private List<ProvCompensazioni> provCompensazionis;
	private List<ProvConvenzione> provConvenziones;
	private List<ProvEsonero> provEsoneros;
	private List<ProvSospensione> provSospensiones;

	/**
	 * @return the codStatoConcessione
	 */
	public String getCodStatoConcessione() {
		return codStatoConcessione;
	}
	
	/**
	 * @param codStatoConcessione the codStatoConcessione to set
	 */
	public void setCodStatoConcessione(String codStatoConcessione) {
		this.codStatoConcessione = codStatoConcessione;
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
	 * @return the descStatoConcessione
	 */
	public String getDescStatoConcessione() {
		return descStatoConcessione;
	}
	
	/**
	 * @param descStatoConcessione the descStatoConcessione to set
	 */
	public void setDescStatoConcessione(String descStatoConcessione) {
		this.descStatoConcessione = descStatoConcessione;
	}

	/**
	 * @return the provCompensazionis
	 */
	public List<ProvCompensazioni> getProvCompensazionis() {
		return provCompensazionis;
	}
	
	/**
	 * @param provCompensazionis the provCompensazionis to set
	 */
	public void setProvCompensazionis(List<ProvCompensazioni> provCompensazionis) {
		this.provCompensazionis = provCompensazionis;
	}

	/**
	 * @return the provConvenziones
	 */
	public List<ProvConvenzione> getProvConvenziones() {
		return provConvenziones;
	}
	
	/**
	 * @param provConvenziones the provConvenziones to set
	 */
	public void setProvConvenziones(List<ProvConvenzione> provConvenziones) {
		this.provConvenziones = provConvenziones;
	}

	/**
	 * @return the provEsoneros
	 */
	public List<ProvEsonero> getProvEsoneros() {
		return provEsoneros;
	}
	
	/**
	 * @param provEsoneros the provEsoneros to set
	 */
	public void setProvEsoneros(List<ProvEsonero> provEsoneros) {
		this.provEsoneros = provEsoneros;
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
