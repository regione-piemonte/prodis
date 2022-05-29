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
/**********************************************
 * CSI PIEMONTE
 **********************************************/
package it.csi.prodis.prodisweb.web.business.be.api;


import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.prodis.prodisweb.lib.dto.prospetto.RiepilogoProvinciale;

/**
 * API interface for /riepilogoProvinciale path
 */
@Path("riepilogo-provinciale")
@Produces({ MediaType.APPLICATION_JSON })
public interface RiepilogoProvincialeApi {

	/**
	 * Posts a RiepilogoProvinciale
	 * 
	 * @param riepilogoProvinciale
	 * @param securityContext
	 * @param httpHeaders
	 * @param httpRequest
	 * @return
	 */
	@POST
	@Path("{idProspettoProv}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response postRiepilogoProvinciale(RiepilogoProvinciale riepilogoProvinciale,
			@PathParam("idProspettoProv") Long idProspettoProv, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	/**
	 * Put a RiepilogoProvinciale for update
	 * 
	 * @param id             
	 * @param riepilogoProvinciale
	 * @param securityContext
	 * @param httpHeaders
	 * @param httpRequest
	 * @return
	 */
	@PUT
	@Path("{idProspettoProv}/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response putRiepilogoProvinciale(RiepilogoProvinciale riepilogoProvinciale,
			@PathParam("idProspettoProv") Long idProspettoProv,
			@PathParam("id") Long id, 
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);


}
