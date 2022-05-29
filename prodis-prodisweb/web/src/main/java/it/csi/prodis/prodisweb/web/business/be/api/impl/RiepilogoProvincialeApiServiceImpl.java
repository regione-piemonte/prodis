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

import it.csi.prodis.prodisweb.ejb.business.be.facade.RiepilogoProvincialeFacade;
import it.csi.prodis.prodisweb.ejb.util.ConstantsProdis;
import it.csi.prodis.prodisweb.lib.dto.prospetto.RiepilogoProvinciale;
import it.csi.prodis.prodisweb.lib.util.ProdisClassUtils;
import it.csi.prodis.prodisweb.web.business.be.api.RiepilogoProvincialeApi;
import it.csi.util.performance.StopWatch;

public class RiepilogoProvincialeApiServiceImpl extends BaseRestServiceImpl implements RiepilogoProvincialeApi {

	@EJB
	private RiepilogoProvincialeFacade riepilogoProvincialeFacade;

	@Override
	public Response postRiepilogoProvinciale(RiepilogoProvinciale riepilogoProvinciale, Long idProspettoProv,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[postRiepilogoProvinciale]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			riepilogoProvinciale.setIdProspettoProv(idProspettoProv);
			return invoke(() -> riepilogoProvincialeFacade.postRiepilogoProvinciale(riepilogoProvinciale));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "postRiepilogoProvinciale()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".postRiepilogoProvinciale", "");
			watcher.stop();
		}

	}

	@Override
	public Response putRiepilogoProvinciale(RiepilogoProvinciale riepilogoProvinciale, Long idProspettoProv, Long id,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[putRiepilogoProvinciale]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			riepilogoProvinciale.setIdProspettoProv(idProspettoProv);
			riepilogoProvinciale.setId(id.intValue());
			return invoke(() -> riepilogoProvincialeFacade.putRiepilogoProvinciale(riepilogoProvinciale));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "putRiepilogoProvinciale()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".putRiepilogoProvinciale", "");
			watcher.stop();
		}

	}

}
