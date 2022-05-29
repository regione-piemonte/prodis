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

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.csi.prodis.prodisweb.ejb.business.be.facade.LavoratoriInForzaFacade;
import it.csi.prodis.prodisweb.ejb.util.ConstantsProdis;
import it.csi.prodis.prodisweb.lib.dto.ApiError;
import it.csi.prodis.prodisweb.lib.dto.prospetto.LavoratoriInForza;
import it.csi.prodis.prodisweb.lib.util.ProdisClassUtils;
import it.csi.prodis.prodisweb.lib.util.serialization.JsonUtility;
import it.csi.prodis.prodisweb.web.business.be.api.LavoratoriInForzaApi;
import it.csi.prodis.prodisweb.web.dto.WebLavoratoriInForzaFileHolder;
import it.csi.util.performance.StopWatch;

public class LavoratoriInForzaApiServiceImpl extends BaseRestServiceImpl implements LavoratoriInForzaApi {

	@EJB
	private LavoratoriInForzaFacade lavoratoriInForzaFacade;

	@Override
	public Response postLavoratoriInForza(Long idDatiProvinciali, Boolean flagWarning, LavoratoriInForza lavoratoriInForza,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[postLavoratoriInForza]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			lavoratoriInForza.setIdProspettoProv(idDatiProvinciali);
			return invoke(() -> lavoratoriInForzaFacade.postLavoratoriInForza(lavoratoriInForza,flagWarning));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "postLavoratoriInForza()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".postLavoratoriInForza", "");
			watcher.stop();
		}

	}

	@Override
	public Response putLavoratoriInForza(Long idDatiProvinciali, Long idLavoratoriInForza,
			Boolean flagWarning, LavoratoriInForza lavoratoriInForza, 
			SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[putLavoratoriInForza]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			lavoratoriInForza.setId(idLavoratoriInForza.intValue());
			lavoratoriInForza.setIdProspettoProv(idDatiProvinciali);
			return invoke(() -> lavoratoriInForzaFacade.putLavoratoriInForza(lavoratoriInForza, flagWarning));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "putLavoratoriInForza()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".putLavoratoriInForza", "");
			watcher.stop();
		}

	}

	@Override
	public Response deleteLavoratoriInForza(Long idLavoratoriInForza, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[deleteLavoratoriInForza]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			return invoke(() -> lavoratoriInForzaFacade.deleteLavoratoriInForza(idLavoratoriInForza));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "deleteLavoratoriInForza()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".deleteLavoratoriInForza", "");
			watcher.stop();
		}

	}

	@Override
	public Response downloadProspettoLavoratoriInForza(Long idProspetto, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws Exception {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[downloadProspettoLavoratoriInForza]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			Response response = invoke(() -> lavoratoriInForzaFacade.downloadProspettoLavoratoriInForza(idProspetto));
			if (response.getEntity().toString().contains("PRO-UPD-E-")) {
				
				String errorString = response.getEntity().toString().replace("[", "");
				
				ApiError ap = new ObjectMapper().readValue(errorString, ApiError.class);
				
				//DownloadLavoratoriInForzaResponse br = new DownloadLavoratoriInForzaResponse();
				List<ApiError> newListErrors = new ArrayList<ApiError>();
				newListErrors.add(ap);
				//br.setApiErrors(newListErrors);
				String serialized = JsonUtility.serialize(newListErrors);
				return Response.status(Status.BAD_REQUEST).entity(serialized).build();  //(br, MediaType.APPLICATION_JSON).build();
			}
			return response;
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "downloadProspettoLavoratoriInForza()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".downloadProspettoLavoratoriInForza",
					"");
			watcher.stop();
		}
	}
	
	@Override
	public Response downloadProvinciaLavoratoriInForza(Long idProspettoProv, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[downloadProvinciaLavoratoriInForza]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			return invoke(() -> lavoratoriInForzaFacade.downloadProvinciaLavoratoriInForza(idProspettoProv));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "downloadProvinciaLavoratoriInForza()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".downloadProvinciaLavoratoriInForza",
					"");
			watcher.stop();
		}

	}

	
	@Override
	public Response uploadProspettoLavoratoriInForza(
			Long idProspetto,
			@MultipartForm WebLavoratoriInForzaFileHolder webLavoratoriInForzaFileHolder,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		log.info(ProdisClassUtils.truncClassName(getClass()), "[uploadProspettoLavoratoriInForza]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			return invoke(() -> lavoratoriInForzaFacade.uploadProspettoLavoratoriInForza(idProspetto, webLavoratoriInForzaFileHolder.toFileHolder()));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "uploadProspettoLavoratoriInForza()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".uploadProspettoLavoratoriInForza", "");
			watcher.stop();
		}

	}
	
	@Override
	public Response uploadProvinciaLavoratoriInForza(
			Long idProspettoProv,
			@MultipartForm WebLavoratoriInForzaFileHolder webLavoratoriInForzaFileHolder,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		log.info(ProdisClassUtils.truncClassName(getClass()), "[uploadProvinciaLavoratoriInForza]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			return invoke(() -> lavoratoriInForzaFacade.uploadProvinciaLavoratoriInForza(idProspettoProv, webLavoratoriInForzaFileHolder.toFileHolder()));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "uploadProvinciaLavoratoriInForza()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".uploadProvinciaLavoratoriInForza", "");
			watcher.stop();
		}

	}

}
