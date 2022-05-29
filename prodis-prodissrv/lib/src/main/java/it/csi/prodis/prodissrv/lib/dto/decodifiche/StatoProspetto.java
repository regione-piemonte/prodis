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
import java.util.List;

import it.csi.prodis.prodissrv.lib.dto.BaseDto;
import it.csi.prodis.prodissrv.lib.dto.prospetto.Prospetto;

/**
 * The Class StatoProspetto.
 */
public class StatoProspetto extends BaseDto<Long> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String descrizione;
	private List<Prospetto> prospettos;

	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}
	
	/**
	 * @param descrizione the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
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
