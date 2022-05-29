/*-
 * ========================LICENSE_START=================================
 * PRODIS Servizi - EJB submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodissrv.ejb.business.be.service.request.prospetto;

import it.csi.prodis.prodissrv.ejb.business.be.service.request.base.BaseRequest;
import it.csi.prodis.prodissrv.lib.dto.prospetto.FilterServiziProdis;

/**
 * Request for reading the Prospetto by its id
 */
public class PostDettaglioProspettoCompletoRequest implements BaseRequest {

	private final FilterServiziProdis filterServiziProdis;

	/**
	 * Constructor
	 */
	public PostDettaglioProspettoCompletoRequest(FilterServiziProdis filterServiziProdis) {
		this.filterServiziProdis = filterServiziProdis;
	}

	/**
	 * @return the ricercaProspetto
	 */
	public FilterServiziProdis getFilterServiziProdis() {
		return filterServiziProdis;
	}

}
