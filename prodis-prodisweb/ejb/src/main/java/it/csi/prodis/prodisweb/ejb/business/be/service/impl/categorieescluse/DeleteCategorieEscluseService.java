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
import it.csi.prodis.prodisweb.ejb.business.be.service.request.categorieescluse.DeleteCategorieEscluseRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.categorieescluse.DeleteCategorieEscluseResponse;
import it.csi.prodis.prodisweb.ejb.exception.NotFoundException;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.dto.prospetto.CategorieEscluse;

public class DeleteCategorieEscluseService extends BaseCategorieEscluseService<DeleteCategorieEscluseRequest, DeleteCategorieEscluseResponse> {
	/**
	 * Constructor
	 * 
	 * @param configurationHelper the helper for the configuration
	 * @param datiProvincialiDad        the DAD for the datiProvinciali
	 */
	public DeleteCategorieEscluseService(ConfigurationHelper configurationHelper, CategorieEscluseDad categorieEscluseDad) {
		super(configurationHelper, categorieEscluseDad);
	}

	@Override
	protected void execute() {
		
		Long idCategorieEscluse = request.getIdCategorieEscluse();
		
		CategorieEscluse catDeleted = null;
		if (idCategorieEscluse!=null) {
			catDeleted = categorieEscluseDad.deleteSingleCategorieEscluse(idCategorieEscluse);
		} else {
			throw new NotFoundException("CategorieEscluse");
		}

		response.setCategorieEscluse(catDeleted);
	}
}
