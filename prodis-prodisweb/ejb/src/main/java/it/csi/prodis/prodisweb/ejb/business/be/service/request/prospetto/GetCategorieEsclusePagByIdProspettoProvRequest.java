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

import it.csi.prodis.prodisweb.ejb.business.be.service.request.base.BasePagedRequest;


/**
 * The Class PostRicercaProspettoRequest.
 */
public class GetCategorieEsclusePagByIdProspettoProvRequest extends BasePagedRequest {

	private final Long idProspettoProv;

	/**
	 * Constructor
	 */
	public GetCategorieEsclusePagByIdProspettoProvRequest(Integer page, Integer size, String sort, String direction,
			Long idProspettoProv) {
		super(size, page, sort, direction);
		this.idProspettoProv = idProspettoProv;
	}


	/**
	 * @return the idProspettoProv
	 */
	public Long getIdProspettoProv() {
		return idProspettoProv;
	}

}
