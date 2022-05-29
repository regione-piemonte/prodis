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

import it.csi.prodis.prodisweb.ejb.business.be.facade.IntermittentiFacade;
import it.csi.prodis.prodisweb.ejb.util.ConstantsProdis;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProvIntermittenti;
import it.csi.prodis.prodisweb.lib.util.ProdisClassUtils;
import it.csi.prodis.prodisweb.web.business.be.api.IntermittentiApi;
import it.csi.util.performance.StopWatch;

public class IntermittentiApiServiceImpl extends BaseRestServiceImpl implements IntermittentiApi {

	@EJB
	private IntermittentiFacade intermittentiFacade;

	@Override
	public Response postIntermittenti(Long idDatiProvinciali, Long idPartTime, ProvIntermittenti intermittenti,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[postIntermittenti]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			intermittenti.setIdProspettoProv(idDatiProvinciali);
			return invoke(() -> intermittentiFacade.postIntermittenti(intermittenti, idPartTime));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "postIntermittenti()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".postIntermittenti", "");
			watcher.stop();
		}

	}

	@Override
	public Response putIntermittenti(Long idDatiProvinciali, Long idIntermittenti, ProvIntermittenti intermittenti,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[putIntermittenti]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			intermittenti.setIdProspettoProv(idDatiProvinciali);
			intermittenti.setId(idIntermittenti.intValue());
			return invoke(() -> intermittentiFacade.putIntermittenti(intermittenti));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "putIntermittenti()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".putIntermittenti", "");
			watcher.stop();
		}

	}

	@Override
	public Response deleteIntermittenti(Long idIntermittenti,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[deleteIntermittenti]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			return invoke(() -> intermittentiFacade.deleteIntermittenti(idIntermittenti));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "deleteIntermittenti()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".deleteIntermittenti", "");
			watcher.stop();
		}

	}

}
