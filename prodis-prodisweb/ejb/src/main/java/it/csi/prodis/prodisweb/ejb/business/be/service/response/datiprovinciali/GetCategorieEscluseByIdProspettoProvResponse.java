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
package it.csi.prodis.prodisweb.ejb.business.be.service.response.datiprovinciali;

import java.util.List;

import it.csi.prodis.prodisweb.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.prodis.prodisweb.lib.dto.prospetto.CategorieEscluse;

/**
 * Response for reading categorieEscluses by its id Prospetto provinciale.
 */
public class GetCategorieEscluseByIdProspettoProvResponse extends BaseGetResponse<List<CategorieEscluse>> {

	private List<CategorieEscluse> categorieEscluses;

	@Override
	protected List<CategorieEscluse> getEntity() {
		return categorieEscluses;
	}

	public List<CategorieEscluse> getCategorieEscluses() {
		return categorieEscluses;
	}

	public void setCategorieEscluses(List<CategorieEscluse> categorieEscluses) {
		this.categorieEscluses = categorieEscluses;
	}

}
