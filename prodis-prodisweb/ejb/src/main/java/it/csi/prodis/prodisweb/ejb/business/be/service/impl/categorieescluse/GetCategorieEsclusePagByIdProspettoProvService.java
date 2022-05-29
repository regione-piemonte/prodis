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

import it.csi.prodis.prodisweb.ejb.business.be.dad.CategorieEscluseDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto.GetCategorieEsclusePagByIdProspettoProvRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto.GetCategorieEsclusePagByIdProspettoProvResponse;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.dto.prospetto.CategorieEscluse;
import it.csi.prodis.prodisweb.lib.util.pagination.PagedList;

/**
 * PostRicercaProspetto
 */
public class GetCategorieEsclusePagByIdProspettoProvService
		extends BaseCategorieEscluseService<GetCategorieEsclusePagByIdProspettoProvRequest,GetCategorieEsclusePagByIdProspettoProvResponse> {

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the helper for the configuration
	 * @param prospettoDad        the DAD for the prospetto
	 */
	public GetCategorieEsclusePagByIdProspettoProvService(ConfigurationHelper configurationHelper, CategorieEscluseDad categorieEscluseDad) {
		super(configurationHelper, categorieEscluseDad);
	}

	@Override
	protected void execute() {
		
		PagedList<CategorieEscluse> categorieEscluses = categorieEscluseDad.getCategorieEsclusePagByIdProspettoProv(request.getPage(),  request.getSize(), request.getSort(), request.getIdProspettoProv());
		response.setCategorieEsluses(categorieEscluses);
	}

}
