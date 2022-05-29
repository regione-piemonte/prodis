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
package it.csi.prodis.prodisweb.ejb.business.be.service.request.categorieescluse;

import it.csi.prodis.prodisweb.ejb.business.be.service.request.base.BaseRequest;
import it.csi.prodis.prodisweb.lib.dto.prospetto.CategorieEscluse;

public class PutCategorieEscluseRequest implements BaseRequest {

	private final CategorieEscluse categorieEscluse;

	/**
	 * Constructor
	 * 
	 * @param CategoriaEscluse
	 */
	public PutCategorieEscluseRequest(CategorieEscluse categorieEscluse) {
		this.categorieEscluse = categorieEscluse;
	}

	public CategorieEscluse getCategorieEscluse() {
		return categorieEscluse;
	}

	
}
