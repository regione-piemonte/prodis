/*-
 * ========================LICENSE_START=================================
 * PRODIS BackEnd - WAR submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodisweb.web.business.be.api.impl;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.prodis.prodisweb.ejb.business.be.facade.CategorieEscluseFacade;
import it.csi.prodis.prodisweb.ejb.util.ConstantsProdis;
import it.csi.prodis.prodisweb.lib.dto.prospetto.CategorieEscluse;
import it.csi.prodis.prodisweb.lib.util.ProdisClassUtils;
import it.csi.prodis.prodisweb.web.business.be.api.CategorieEscluseApi;
import it.csi.util.performance.StopWatch;

public class CategorieEscluseApiServiceImpl extends BaseRestServiceImpl implements CategorieEscluseApi {

	@EJB
	private CategorieEscluseFacade categorieEscluseFacade;

	@Override
	public Response getCategorieEsclusePagByIdProspettoProv(@Min(0) Integer page, @Min(1) @Max(100) Integer limit,
			String sort, String direction, Long idProspettoProv, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[getCategorieEsclusePagByIdProspettoProv]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			return invoke(() -> categorieEscluseFacade.getCategorieEsclusePagByIdProspettoProv(page, limit, sort,
					direction, idProspettoProv));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()),
					"getCategorieEsclusePagByIdProspettoProv()", "invocazione API "
							+ ProdisClassUtils.truncClassName(getClass()) + ".getCategorieEsclusePagByIdProspettoProv",
					"");
			watcher.stop();
		}

	}

	@Override
	public Response postCategorieEscluse(Long idDatiProvinciali, CategorieEscluse categorieEscluse,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		categorieEscluse.setIdProspettoProv(idDatiProvinciali);
		log.info(ProdisClassUtils.truncClassName(getClass()), "[postCategorieEscluse]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			return invoke(() -> categorieEscluseFacade.postCategorieEscluse(categorieEscluse));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "postCategorieEscluse()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".postCategorieEscluse", "");
			watcher.stop();
		}
	}

	@Override
	public Response putCategorieEscluse(Long idDatiProvinciali, Long idCategorieEscluse,
			CategorieEscluse categorieEscluse, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		categorieEscluse.setIdProspettoProv(idDatiProvinciali);
		categorieEscluse.setId(idCategorieEscluse.intValue());
		log.info(ProdisClassUtils.truncClassName(getClass()), "[putCategorieEscluse]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			return invoke(() -> categorieEscluseFacade.putCategorieEscluse(categorieEscluse));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "putCategorieEscluse()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".putCategorieEscluse", "");
			watcher.stop();
		}
	}

	@Override
	public Response deleteCategorieEscluse(Long idCategorieEscluse, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		log.info(ProdisClassUtils.truncClassName(getClass()), "[deleteCategorieEscluse]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			return invoke(() -> categorieEscluseFacade.deleteCategorieEscluse(idCategorieEscluse));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "deleteCategorieEscluse()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".deleteCategorieEscluse", "");
			watcher.stop();
		}
	}

}
