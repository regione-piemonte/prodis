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
package it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto;

import it.csi.prodis.prodisweb.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.prodis.prodisweb.lib.dto.prospetto.Prospetto;
import it.csi.prodis.prodisweb.lib.util.pagination.PagedList;
import it.csi.prodis.prodisweb.lib.util.pagination.PagedListImpl;

/**
 * The Class PostRicercaProspettoResponse.
 */
public class PostRicercaProspettoResponse extends BaseGetResponse<PagedList<Prospetto>> {

	/** The model. */
	private PagedList<Prospetto> prospettos = new PagedListImpl<>();

	public PagedList<Prospetto> getProspettos() {
		return prospettos;
	}

	public void setProspettos(PagedList<Prospetto> prospettos) {
		this.prospettos = prospettos;
	}

	@Override
	protected PagedList<Prospetto> getEntity() {
		return prospettos;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getSimpleName() + " [prospettos=").append(prospettos).append(", apiErrors=")
				.append(getApiErrors()).append("]");
		return builder.toString();
	}

}
