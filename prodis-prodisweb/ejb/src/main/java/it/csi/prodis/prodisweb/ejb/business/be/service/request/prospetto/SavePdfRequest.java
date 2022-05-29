/*-
 * ========================LICENSE_START=================================
 * PRODIS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto;

import it.csi.prodis.prodisweb.ejb.business.be.service.request.base.BaseRequest;

public class SavePdfRequest implements BaseRequest {

	private final Long idProspetto;
	private final byte[] bytes;

	/**
	 * Constructor
	 * 
	 * @param id the id
	 */
	public SavePdfRequest(Long idProspetto, byte[] bytes) {
		this.idProspetto = idProspetto;
		this.bytes = bytes;
	}

	/**
	 * @return the id
	 */
	public Long getIdProspetto() {
		return idProspetto;
	}

	/**
	 * @return the bytes
	 */
	public byte[] getBytes() {
		return bytes;
	}

}
