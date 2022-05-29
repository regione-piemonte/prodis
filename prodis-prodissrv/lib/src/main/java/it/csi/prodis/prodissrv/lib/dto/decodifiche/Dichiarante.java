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
import it.csi.prodis.prodissrv.lib.dto.prospetto.DatiAzienda;

/**
 * The Class Dichiarante.
 */
public class Dichiarante extends BaseDto<Long> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String codDichiarante;
	private Date dataFine;
	private Date dataInizio;
	private Date dataTmst;
	private String descDichiarante;
	private List<DatiAzienda> datiAziendas;

	/**
	 * @return the codDichiarante
	 */
	public String getCodDichiarante() {
		return codDichiarante;
	}
	
	/**
	 * @param codDichiarante the codDichiarante to set
	 */
	public void setCodDichiarante(String codDichiarante) {
		this.codDichiarante = codDichiarante;
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
	 * @return the descDichiarante
	 */
	public String getDescDichiarante() {
		return descDichiarante;
	}
	
	/**
	 * @param descDichiarante the descDichiarante to set
	 */
	public void setDescDichiarante(String descDichiarante) {
		this.descDichiarante = descDichiarante;
	}

	/**
	 * @return the datiAziendas
	 */
	public List<DatiAzienda> getDatiAziendas() {
		return datiAziendas;
	}
	
	/**
	 * @param datiAziendas the datiAziendas to set
	 */
	public void setDatiAziendas(List<DatiAzienda> datiAziendas) {
		this.datiAziendas = datiAziendas;
	}

}
