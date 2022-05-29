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

import it.csi.prodis.prodissrv.lib.dto.BaseDto;

/**
 * The Class Parametri.
 */
public class Parametri extends BaseDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String dsDescrizione;
	private String dsNome;
	private String dsValore;

	/**
	 * @return the dsDescrizione
	 */
	public String getDsDescrizione() {
		return dsDescrizione;
	}
	
	/**
	 * @param dsDescrizione the dsDescrizione to set
	 */
	public void setDsDescrizione(String dsDescrizione) {
		this.dsDescrizione = dsDescrizione;
	}

	/**
	 * @return the dsNome
	 */
	public String getDsNome() {
		return dsNome;
	}
	
	/**
	 * @param dsNome the dsNome to set
	 */
	public void setDsNome(String dsNome) {
		this.dsNome = dsNome;
	}

	/**
	 * @return the dsValore
	 */
	public String getDsValore() {
		return dsValore;
	}
	
	/**
	 * @param dsValore the dsValore to set
	 */
	public void setDsValore(String dsValore) {
		this.dsValore = dsValore;
	}

}
