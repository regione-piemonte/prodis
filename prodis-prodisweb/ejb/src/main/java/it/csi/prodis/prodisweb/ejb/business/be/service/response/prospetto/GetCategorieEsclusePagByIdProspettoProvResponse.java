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
import it.csi.prodis.prodisweb.lib.dto.prospetto.CategorieEscluse;
import it.csi.prodis.prodisweb.lib.util.pagination.PagedList;

/**
 * Response for reading Prospetto by its id.
 */
public class GetCategorieEsclusePagByIdProspettoProvResponse extends BaseGetResponse<PagedList<CategorieEscluse>> {

	private PagedList<CategorieEscluse> categorieEsluses;

	

	public PagedList<CategorieEscluse> getCategorieEsluses() {
		return categorieEsluses;
	}



	public void setCategorieEsluses(PagedList<CategorieEscluse> categorieEsluses) {
		this.categorieEsluses = categorieEsluses;
	}



	@Override
	protected PagedList<CategorieEscluse> getEntity() {
		return categorieEsluses;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getSimpleName() + " [categorieEsluses=").append(categorieEsluses).append(", apiErrors=")
				.append(getApiErrors()).append("]");
		return builder.toString();
	}

}
