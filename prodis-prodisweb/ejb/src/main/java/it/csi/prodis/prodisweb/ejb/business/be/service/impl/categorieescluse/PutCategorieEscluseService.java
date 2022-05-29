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
package it.csi.prodis.prodisweb.ejb.business.be.service.impl.categorieescluse;

import java.util.ArrayList;
import java.util.List;

import it.csi.prodis.prodisweb.ejb.business.be.dad.CategorieEscluseDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.categorieescluse.PutCategorieEscluseRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.categorieescluse.PutCategorieEscluseResponse;
import it.csi.prodis.prodisweb.ejb.exception.NotFoundException;
import it.csi.prodis.prodisweb.ejb.util.ValidatorCategorieEscluse;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.dto.ApiError;
import it.csi.prodis.prodisweb.lib.dto.prospetto.CategorieEscluse;

public class PutCategorieEscluseService extends BaseCategorieEscluseService<PutCategorieEscluseRequest, PutCategorieEscluseResponse> {
	/**
	 * Constructor
	 * 
	 * @param configurationHelper the helper for the configuration
	 * @param datiProvincialiDad        the DAD for the datiProvinciali
	 */
	public PutCategorieEscluseService(ConfigurationHelper configurationHelper, CategorieEscluseDad categorieEscluseDad) {
		super(configurationHelper, categorieEscluseDad);
	}

	@Override
	protected void execute() {
		CategorieEscluse categorieEscluse = request.getCategorieEscluse();
		List<ApiError> apiErrors = new ArrayList<ApiError>();
		ValidatorCategorieEscluse valida = new ValidatorCategorieEscluse(categorieEscluseDad);
		valida.valida(categorieEscluse, apiErrors);
		
		if (apiErrors == null || apiErrors.size() == 0) {
			if (categorieEscluse.getId()!=null) {
				categorieEscluse = categorieEscluseDad.updateSingleCategorieEscluse(categorieEscluse, categorieEscluse.getId());
			} else {
				throw new NotFoundException("CategorieEscluse");
			}
		}
		
		if (apiErrors != null && apiErrors.size() > 0) {
			response.setApiErrors(apiErrors);
		}
		response.setCategorieEscluse(categorieEscluse);
	}
}
