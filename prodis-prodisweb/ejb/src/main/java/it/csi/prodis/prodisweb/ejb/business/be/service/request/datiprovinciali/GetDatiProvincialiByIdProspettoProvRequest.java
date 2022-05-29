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
public class GetDatiProvincialiByIdProspettoProvRequest implements BaseRequest {

	private final Long id;

	/**
	 * Constructor
	 * @param id the id
	 */
	public GetDatiProvincialiByIdProspettoProvRequest(Long id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
}
