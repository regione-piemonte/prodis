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

import it.csi.prodis.prodisweb.ejb.business.be.facade.PostiLavoroDispFacade;
import it.csi.prodis.prodisweb.ejb.util.ConstantsProdis;
import it.csi.prodis.prodisweb.lib.dto.prospetto.PostiLavoroDisp;
import it.csi.prodis.prodisweb.lib.util.ProdisClassUtils;
import it.csi.prodis.prodisweb.web.business.be.api.PostiLavoroDispApi;
import it.csi.util.performance.StopWatch;

public class PostiLavoroDispApiServiceImpl extends BaseRestServiceImpl implements PostiLavoroDispApi {

	@EJB
	private PostiLavoroDispFacade postiLavoroDispFacade;

	@Override
	public Response postPostiLavoroDisp(Long idDatiProvinciali, PostiLavoroDisp postiLavoroDisp,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[postPostiLavoroDisp]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			postiLavoroDisp.setIdProspettoProv(idDatiProvinciali);
			return invoke(() -> postiLavoroDispFacade.postPostiLavoroDisp(postiLavoroDisp));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "postPostiLavoroDisp()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".postPostiLavoroDisp", "");
			watcher.stop();
		}

	}

	@Override
	public Response putPostiLavoroDisp(Long idDatiProvinciali, Long idPostiLavoroDisp, PostiLavoroDisp postiLavoroDisp,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		log.info(ProdisClassUtils.truncClassName(getClass()), "[putPostiLavoroDisp]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			postiLavoroDisp.setIdProspettoProv(idDatiProvinciali);
			postiLavoroDisp.setId(idPostiLavoroDisp.intValue());
			return invoke(() -> postiLavoroDispFacade.putPostiLavoroDisp(postiLavoroDisp));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "putPostiLavoroDisp()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".putPostiLavoroDisp", "");
			watcher.stop();
		}

	}

	@Override
	public Response deletePostiLavoroDisp(Long idPostiLavoroDisp, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[deletePostiLavoroDisp]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			return invoke(() -> postiLavoroDispFacade.deletePostiLavoroDisp(idPostiLavoroDisp));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "deletePostiLavoroDisp()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".deletePostiLavoroDisp", "");
			watcher.stop();
		}

	}

}
