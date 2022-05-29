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

import it.csi.prodis.prodisweb.ejb.business.be.facade.CompensazioniFacade;
import it.csi.prodis.prodisweb.ejb.util.ConstantsProdis;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProvCompensazioni;
import it.csi.prodis.prodisweb.lib.util.ProdisClassUtils;
import it.csi.prodis.prodisweb.web.business.be.api.CompensazioniApi;
import it.csi.util.performance.StopWatch;

public class CompensazioniApiServiceImpl extends BaseRestServiceImpl implements CompensazioniApi {

	@EJB
	private CompensazioniFacade compensazioniFacade;

	@Override
	public Response postCompensazioni(Long idDatiProvinciali, ProvCompensazioni compensazioni,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[postCompensazioni]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			compensazioni.setIdProspettoProv(idDatiProvinciali);
			return invoke(() -> compensazioniFacade.postCompensazioni(compensazioni));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "postCompensazioni()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".postCompensazioni", "");
			watcher.stop();
		}

	}

	@Override
	public Response putCompensazioni(Long idDatiProvinciali, Long idCompensazioni, ProvCompensazioni compensazioni,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[putCompensazioni]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			compensazioni.setIdProspettoProv(idDatiProvinciali);
			compensazioni.setId(idCompensazioni.intValue());
			return invoke(() -> compensazioniFacade.putCompensazioni(compensazioni));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "putCompensazioni()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".putCompensazioni", "");
			watcher.stop();
		}

	}

	@Override
	public Response deleteCompensazioni(Long idCompensazioni, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[deleteCompensazioni]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			return invoke(() -> compensazioniFacade.deleteCompensazioni(idCompensazioni));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "deleteCompensazioni()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".deleteCompensazioni", "");
			watcher.stop();
		}

	}

	@Override
	public Response confermaTornaRiepilogo(Long idProspetto, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[confermaTornaRiepilogo]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			return invoke(() -> compensazioniFacade.confermaTornaRiepilogo(idProspetto));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "confermaTornaRiepilogo()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".confermaTornaRiepilogo", "");
			watcher.stop();
		}

	}

}
