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

public class DeleteCategorieEscluseRequest implements BaseRequest {

	private final Long idCategorieEscluse;

	/**
	 * Constructor
	 * 
	 * @param CategoriaEscluse
	 */
	public DeleteCategorieEscluseRequest(Long idCategorieEscluse) {
		this.idCategorieEscluse = idCategorieEscluse;
	}

	public Long getIdCategorieEscluse() {
		return idCategorieEscluse;
	}

	
}
