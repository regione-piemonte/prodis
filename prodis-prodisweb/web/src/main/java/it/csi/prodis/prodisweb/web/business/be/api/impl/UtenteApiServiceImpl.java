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

import it.csi.prodis.prodisweb.ejb.business.be.facade.UtenteFacade;
import it.csi.prodis.prodisweb.ejb.util.ConstantsProdis;
import it.csi.prodis.prodisweb.lib.util.ProdisClassUtils;
import it.csi.prodis.prodisweb.web.business.be.api.UtenteApi;
import it.csi.prodis.prodisweb.web.util.annotation.Logged;
import it.csi.util.performance.StopWatch;

/**
 * Implementation for InterventoApi
 */
@Logged
public class UtenteApiServiceImpl extends BaseRestServiceImpl implements UtenteApi {

	@EJB
	private UtenteFacade utenteFacade;

	@Override
	public Response getUtenteSelf(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[getUtenteSelf]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			return invoke(() -> utenteFacade.getUtenteSelf());
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "getUtenteSelf()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".getUtenteSelf", "");
			watcher.stop();
		}
	}

}
