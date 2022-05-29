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
import it.csi.prodis.prodisweb.lib.dto.prospetto.Prospetto;

/**
 * The Class Comunicazione.
 */
public class Comunicazione extends BaseDto<Long> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String codComunicazione;
	private Date dataFine;
	private Date dataInizio;
	private Date dataTmst;
	private String descComunicazione;
	private List<Prospetto> prospettos;

	/**
	 * @return the codComunicazione
	 */
	public String getCodComunicazione() {
		return codComunicazione;
	}
	
	/**
	 * @param codComunicazione the codComunicazione to set
	 */
	public void setCodComunicazione(String codComunicazione) {
		this.codComunicazione = codComunicazione;
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
	 * @return the descComunicazione
	 */
	public String getDescComunicazione() {
		return descComunicazione;
	}
	
	/**
	 * @param descComunicazione the descComunicazione to set
	 */
	public void setDescComunicazione(String descComunicazione) {
		this.descComunicazione = descComunicazione;
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
