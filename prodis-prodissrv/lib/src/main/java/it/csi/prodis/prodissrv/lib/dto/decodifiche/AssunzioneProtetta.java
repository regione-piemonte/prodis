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
import it.csi.prodis.prodissrv.lib.dto.prospetto.LavoratoriInForza;
import it.csi.prodis.prodissrv.lib.dto.prospetto.LavoratoriSilp;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProvConvenzione;

/**
 * The Class AssunzioneProtetta.
 */
public class AssunzioneProtetta extends BaseDto<Long> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String codAssunzioneProtetta;
	private Date dataFine;
	private Date dataInizio;
	private Date dataTmst;
	private String descAssunzioneProtetta;
	private String flgConvensione;
	private List<LavoratoriInForza> lavoratoriInForzas;
	private List<LavoratoriSilp> lavoratoriSilps;
	private List<ProvConvenzione> provConvenziones;

	/**
	 * @return the codAssunzioneProtetta
	 */
	public String getCodAssunzioneProtetta() {
		return codAssunzioneProtetta;
	}
	
	/**
	 * @param codAssunzioneProtetta the codAssunzioneProtetta to set
	 */
	public void setCodAssunzioneProtetta(String codAssunzioneProtetta) {
		this.codAssunzioneProtetta = codAssunzioneProtetta;
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
	 * @return the descAssunzioneProtetta
	 */
	public String getDescAssunzioneProtetta() {
		return descAssunzioneProtetta;
	}
	
	/**
	 * @param descAssunzioneProtetta the descAssunzioneProtetta to set
	 */
	public void setDescAssunzioneProtetta(String descAssunzioneProtetta) {
		this.descAssunzioneProtetta = descAssunzioneProtetta;
	}

	/**
	 * @return the flgConvensione
	 */
	public String getFlgConvensione() {
		return flgConvensione;
	}
	
	/**
	 * @param flgConvensione the flgConvensione to set
	 */
	public void setFlgConvensione(String flgConvensione) {
		this.flgConvensione = flgConvensione;
	}

	/**
	 * @return the lavoratoriInForzas
	 */
	public List<LavoratoriInForza> getLavoratoriInForzas() {
		return lavoratoriInForzas;
	}
	
	/**
	 * @param lavoratoriInForzas the lavoratoriInForzas to set
	 */
	public void setLavoratoriInForzas(List<LavoratoriInForza> lavoratoriInForzas) {
		this.lavoratoriInForzas = lavoratoriInForzas;
	}

	/**
	 * @return the lavoratoriSilps
	 */
	public List<LavoratoriSilp> getLavoratoriSilps() {
		return lavoratoriSilps;
	}
	
	/**
	 * @param lavoratoriSilps the lavoratoriSilps to set
	 */
	public void setLavoratoriSilps(List<LavoratoriSilp> lavoratoriSilps) {
		this.lavoratoriSilps = lavoratoriSilps;
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

}
