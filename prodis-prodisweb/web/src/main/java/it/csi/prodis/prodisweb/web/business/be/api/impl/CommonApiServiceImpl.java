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

import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.prodis.prodisweb.ejb.business.be.facade.CommonFacade;
import it.csi.prodis.prodisweb.ejb.util.ConstantsProdis;
import it.csi.prodis.prodisweb.lib.dto.prospetto.UserAccessLog;
import it.csi.prodis.prodisweb.lib.util.ProdisClassUtils;
import it.csi.prodis.prodisweb.web.business.be.api.CommonApi;
import it.csi.prodis.prodisweb.web.util.annotation.Logged;
import it.csi.util.performance.StopWatch;

/**
 * Implementation for CommonApi
 */
@Logged
public class CommonApiServiceImpl extends BaseRestServiceImpl implements CommonApi {

	@EJB
	private CommonFacade commonFacade;

	@Override
	public Response getRuolo(SecurityContext securityContext, HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[getRuolo]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			return invoke(() -> commonFacade.getRuolo());
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "getRuolo()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".getRuolo", "");
			watcher.stop();
		}

	}

	@Override
	public Response getParametro(String nomeParametro, SecurityContext securityContext, HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[getParametro]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			return invoke(() -> commonFacade.getParametroByNome(nomeParametro));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "getParametro()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".getParametro", "");
			watcher.stop();
		}

	}

	@Override
	public Response insertUserAccessLog(UserAccessLog loUserLog, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[insertUserAccessLog]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			return invoke(() -> commonFacade.insertUserAccessLog(loUserLog));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "insertUserAccessLog()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".insertUserAccessLog", "");
			watcher.stop();
		}

	}

	@Override
	public Response getDataCalcolataConGiorniLavorativi(String dataInput, Long numeroGiorniLavorativi,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[getDataCalcolataConGiorniLavorativi]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			Date laDataInput = new Date(dataInput);

			return invoke(() -> commonFacade.getDataCalcolataConGiorniLavorativi(laDataInput, numeroGiorniLavorativi));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "getDataCalcolataConGiorniLavorativi()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass())
							+ ".getDataCalcolataConGiorniLavorativi",
					"");
			watcher.stop();
		}

	}

}
