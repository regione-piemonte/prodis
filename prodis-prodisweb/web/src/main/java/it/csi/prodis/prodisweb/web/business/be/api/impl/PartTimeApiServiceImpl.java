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
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.prodis.prodisweb.ejb.business.be.facade.PartTimeFacade;
import it.csi.prodis.prodisweb.ejb.util.ConstantsProdis;
import it.csi.prodis.prodisweb.lib.dto.prospetto.PartTime;
import it.csi.prodis.prodisweb.lib.util.ProdisClassUtils;
import it.csi.prodis.prodisweb.web.business.be.api.PartTimeApi;
import it.csi.util.performance.StopWatch;

public class PartTimeApiServiceImpl extends BaseRestServiceImpl implements PartTimeApi {

	@EJB
	private PartTimeFacade partTimeFacade;

	@Override
	public Response postPartTime(Long idDatiProvinciali, Long idIntermittenti, PartTime partTime, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[postPartTime]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			partTime.setIdProspettoProv(idDatiProvinciali);
			return invoke(() -> partTimeFacade.postPartTime(partTime, idIntermittenti));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "postPartTime()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".postPartTime", "");
			watcher.stop();
		}

	}

	@Override
	public Response putPartTime(Long idDatiProvinciali, Long idPartTime, PartTime partTime,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[putPartTime]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			partTime.setIdProspettoProv(idDatiProvinciali);
			partTime.setId(idPartTime.intValue());
			return invoke(() -> partTimeFacade.putPartTime(partTime));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "putPartTime()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".putPartTime", "");
			watcher.stop();
		}

	}

	@Override
	public Response deletePartTime(Long idPartTime,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[deletePartTime]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			return invoke(() -> partTimeFacade.deletePartTime(idPartTime));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "deletePartTime()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".deletePartTime", "");
			watcher.stop();
		}
	}

}
