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

import it.csi.prodis.prodisweb.ejb.business.be.facade.RiepilogoNazionaleFacade;
import it.csi.prodis.prodisweb.ejb.util.ConstantsProdis;
import it.csi.prodis.prodisweb.lib.dto.prospetto.RiepilogoNazionale;
import it.csi.prodis.prodisweb.lib.util.ProdisClassUtils;
import it.csi.prodis.prodisweb.web.business.be.api.RiepilogoNazionaleApi;
import it.csi.util.performance.StopWatch;

public class RiepilogoNazionaleApiServiceImpl extends BaseRestServiceImpl implements RiepilogoNazionaleApi {

	@EJB
	private RiepilogoNazionaleFacade riepilogoNazionaleFacade;

	@Override
	public Response postRiepilogoNazionale(RiepilogoNazionale riepilogoNazionale, Long idProspetto,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[postRiepilogoNazionale]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			riepilogoNazionale.setIdProspetto(idProspetto);
			return invoke(() -> riepilogoNazionaleFacade.postRiepilogoNazionale(riepilogoNazionale));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "postRiepilogoNazionale()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".postRiepilogoNazionale", "");
			watcher.stop();
		}

	}

	@Override
	public Response putRiepilogoNazionale(RiepilogoNazionale riepilogoNazionale, Long idProspetto, Long id,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[putRiepilogoNazionale]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			riepilogoNazionale.setIdProspetto(idProspetto);
			riepilogoNazionale.setId(id.intValue());
			return invoke(() -> riepilogoNazionaleFacade.putRiepilogoNazionale(riepilogoNazionale));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "putRiepilogoNazionale()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".putRiepilogoNazionale", "");
			watcher.stop();
		}

	}

}
