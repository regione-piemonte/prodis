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
package it.csi.prodis.prodisweb.ejb.business.be.service.response.categorieescluse;

import it.csi.prodis.prodisweb.ejb.business.be.service.response.base.BasePostResponse;
import it.csi.prodis.prodisweb.lib.dto.prospetto.CategorieEscluse;

public class DeleteCategorieEscluseResponse extends BasePostResponse<Integer, CategorieEscluse> {

	/** The model. */
	private CategorieEscluse categorieEscluse = new CategorieEscluse();

	public CategorieEscluse getCategorieEscluse() {
		return categorieEscluse;
	}

	public void setCategorieEscluse(CategorieEscluse categorieEscluse) {
		this.categorieEscluse = categorieEscluse;
	}

	@Override
	protected String getBaseUri() {
		return "intervento";
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DeleteCategorieEscluseResponse [categorieEscluse=").append(categorieEscluse).append(", apiErrors=")
				.append(getApiErrors()).append("]");
		return builder.toString();
	}

	@Override
	protected CategorieEscluse getEntity() {
		return categorieEscluse;
	}

}
