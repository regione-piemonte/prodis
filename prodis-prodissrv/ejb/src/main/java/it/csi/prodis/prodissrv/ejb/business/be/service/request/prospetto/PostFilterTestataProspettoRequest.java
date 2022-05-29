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
 * The Class PostFilterTestataProspettoRequest.
 */
public class PostFilterTestataProspettoRequest implements BaseRequest {

	private final FilterServiziProdis filterTestataProspetto;

	/**
	 * Constructor
	 */
	public PostFilterTestataProspettoRequest(FilterServiziProdis filterTestataProspetto) {
		this.filterTestataProspetto = filterTestataProspetto;
	}

	/**
	 * @return the ricercaProspetto
	 */
	public FilterServiziProdis getFilterTestataProspetto() {
		return filterTestataProspetto;
	}

}
