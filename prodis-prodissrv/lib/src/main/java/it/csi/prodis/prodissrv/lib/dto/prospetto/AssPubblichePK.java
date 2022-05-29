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
 * The Class AssPubblichePK.
 */
public class AssPubblichePK extends BaseDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private long idProspetto;
	private long idTRegione;

	/**
	 * @return the idProspetto
	 */
	public long getIdProspetto() {
		return idProspetto;
	}
	
	/**
	 * @param idProspetto the idProspetto to set
	 */
	public void setIdProspetto(long idProspetto) {
		this.idProspetto = idProspetto;
	}

	/**
	 * @return the idTRegione
	 */
	public long getIdTRegione() {
		return idTRegione;
	}
	
	/**
	 * @param idTRegione the idTRegione to set
	 */
	public void setIdTRegione(long idTRegione) {
		this.idTRegione = idTRegione;
	}

}
