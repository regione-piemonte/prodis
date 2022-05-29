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
package it.csi.prodis.prodisweb.ejb.business.be.service.request.datiprovinciali;

import it.csi.prodis.prodisweb.ejb.business.be.service.request.base.BaseRequest;



/**
 * Request for reading the DatiProvinciali by its idProspettoProv
 */
public class GetProspettoProvinciaByIdProspettoRequest implements BaseRequest {

	private final Long idProspetto;

	/**
	 * Constructor
	 * @param idProspetto the Prospetto's id
	 */
	public GetProspettoProvinciaByIdProspettoRequest(Long idProspetto) {
		this.idProspetto = idProspetto;
	}


	/**
	 * @return the idProspetto
	 */
	
	public Long getIdProspetto() {
		return idProspetto;
	}
	
}
