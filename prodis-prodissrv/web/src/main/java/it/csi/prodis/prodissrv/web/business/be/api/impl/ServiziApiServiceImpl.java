/*-
 * ========================LICENSE_START=================================
 * PRODIS Servizi - WAR submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022  | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodissrv.web.business.be.api.impl;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.prodis.prodissrv.ejb.business.be.facade.ServiziFacade;
import it.csi.prodis.prodissrv.ejb.business.be.service.response.prospetto.InitRicezioneProspettoDaSpicomResponse;
import it.csi.prodis.prodissrv.ejb.business.be.service.response.prospetto.InserisciProspettoResponse;
import it.csi.prodis.prodissrv.ejb.business.be.service.response.prospetto.RiceviProspettoDaSpicomResponse;
import it.csi.prodis.prodissrv.ejb.business.be.service.response.prospetto.StoreProcedureEseguiCalcoliResponse;
import it.csi.prodis.prodissrv.ejb.util.ConstantsProdis;
import it.csi.prodis.prodissrv.lib.dto.prospetto.FilterServiziProdis;
import it.csi.prodis.prodissrv.lib.util.ProdisClassUtils;
import it.csi.prodis.prodissrv.web.business.be.api.ServiziApi;
import it.csi.prodis.prodissrv.web.util.annotation.Logged;
import it.csi.spicom.dto.ComunicazioneProspettoDisabiliDTO;
import it.csi.util.performance.StopWatch;

/**
 * Implementation for ServiziApi
 */
@Logged
public class ServiziApiServiceImpl extends BaseRestServiceImpl implements ServiziApi {

	@EJB
	private ServiziFacade serviziFacade;

	@Override
	public Response findByFilterTestataProspetto(FilterServiziProdis filterTestataProspetto,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[findByFilterTestataProspetto]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			return invoke(() -> serviziFacade.findByFilterTestataProspetto(filterTestataProspetto));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "findByFilterTestataProspetto()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".findByFilterTestataProspetto",
					"");
			watcher.stop();
		}
	}

	@Override
	public Response getDettaglioProspettoCompleto(FilterServiziProdis filterTestataProspetto,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[getDettaglioProspettoCompleto]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			return invoke(() -> serviziFacade.getDettaglioProspettoCompleto(filterTestataProspetto));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "getDettaglioProspettoCompleto()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".getDettaglioProspettoCompleto",
					"");
			watcher.stop();
		}

	}

	@Override
	public Response findByPkPdfProspetto(FilterServiziProdis filterTestataProspetto, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[findByPkPdfProspetto]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			return invoke(() -> serviziFacade.findByPkPdfProspetto(filterTestataProspetto));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "findByPkPdfProspetto()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".findByPkPdfProspetto", "");
			watcher.stop();
		}

	}
	
	@Override
	public Response riceviProspettoDaSpicom(ComunicazioneProspettoDisabiliDTO comunicazioneSpicom, 
			SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		
		log.info(ProdisClassUtils.truncClassName(getClass()), "[riceviProspettoDaSpicom]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			
			InitRicezioneProspettoDaSpicomResponse initResponse = serviziFacade.initRicezioneComunicazione(comunicazioneSpicom);  //transazione
			 if(initResponse.getApiErrors()!=null && initResponse.getApiErrors().size() > 0) {
				 InserisciProspettoResponse response = new InserisciProspettoResponse();
				 response.setApiErrors(initResponse.getApiErrors());
				 return response.composeResponse();
			 }			
			
			 RiceviProspettoDaSpicomResponse responseSpicom = serviziFacade.riceviProspettoDaSpicom(comunicazioneSpicom); // non transazione
			 if(responseSpicom.getApiErrors()!=null && responseSpicom.getApiErrors().size() > 0) {
				 InserisciProspettoResponse response = new InserisciProspettoResponse();
				 response.setApiErrors(responseSpicom.getApiErrors());
				 return response.composeResponse();
			 }
			 // transazione
			 InserisciProspettoResponse responseInserimento = serviziFacade.inserisciProspetto(responseSpicom.getProspetto(), responseSpicom.getErrors(), responseSpicom.getTipoComunicazione());
			 if(responseInserimento.getApiErrors()!=null && responseInserimento.getApiErrors().size() > 0) {
				 return responseInserimento.composeResponse();
			 }
			 // transazione
			 StoreProcedureEseguiCalcoliResponse spResponse = serviziFacade.eseguiCalcoliStoreProcedure(responseInserimento.getIdProspetto());
			 if (spResponse.getApiErrors()!=null) {
				 responseInserimento.setApiErrors(responseSpicom.getApiErrors());
			 }
			 return responseInserimento.composeResponse();
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "comunicazioneSpicom()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".comunicazioneSpicom", "");
			watcher.stop();
		}

	}

}
