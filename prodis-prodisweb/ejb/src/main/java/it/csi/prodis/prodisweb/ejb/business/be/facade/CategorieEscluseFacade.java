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
package it.csi.prodis.prodisweb.ejb.business.be.facade;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import it.csi.prodis.prodisweb.ejb.business.be.dad.CategorieEscluseDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.categorieescluse.DeleteCategorieEscluseService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.categorieescluse.GetCategorieEsclusePagByIdProspettoProvService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.categorieescluse.PostCategorieEscluseService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.categorieescluse.PutCategorieEscluseService;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.categorieescluse.DeleteCategorieEscluseRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.categorieescluse.PostCategorieEscluseRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.categorieescluse.PutCategorieEscluseRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto.GetCategorieEsclusePagByIdProspettoProvRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.categorieescluse.DeleteCategorieEscluseResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.categorieescluse.PostCategorieEscluseResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.categorieescluse.PutCategorieEscluseResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto.GetCategorieEsclusePagByIdProspettoProvResponse;
import it.csi.prodis.prodisweb.lib.dto.prospetto.CategorieEscluse;


/**
 * Facade for the /common path
 */
@Stateless
public class CategorieEscluseFacade extends BaseFacade {

	@Inject
	private CategorieEscluseDad categorieEscluseDad;

	
	/**
	 * Gets an AssunzioniPubbliche
	 * 
	 * @param prospetto
	 * @return the assunzioniPubbliche
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public GetCategorieEsclusePagByIdProspettoProvResponse getCategorieEsclusePagByIdProspettoProv(Integer page, Integer limit, String sort, String direction, Long idProspettoProv) {
		return executeService(new GetCategorieEsclusePagByIdProspettoProvRequest(page, limit, sort, direction,idProspettoProv),
				new GetCategorieEsclusePagByIdProspettoProvService(configurationHelper, categorieEscluseDad));
			
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PostCategorieEscluseResponse postCategorieEscluse(CategorieEscluse categorieEscluse) {
		return executeService(new PostCategorieEscluseRequest(categorieEscluse),
				new PostCategorieEscluseService(configurationHelper, categorieEscluseDad));
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PutCategorieEscluseResponse putCategorieEscluse(CategorieEscluse categorieEscluse) {
		return executeService(new PutCategorieEscluseRequest(categorieEscluse),
				new PutCategorieEscluseService(configurationHelper, categorieEscluseDad));
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public DeleteCategorieEscluseResponse deleteCategorieEscluse(Long idCategorieEscluse) {
		return executeService(new DeleteCategorieEscluseRequest(idCategorieEscluse),
				new DeleteCategorieEscluseService(configurationHelper, categorieEscluseDad));
	}

}
