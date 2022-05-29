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

import it.csi.prodis.prodisweb.lib.dto.prospetto.RiepilogoNazionale;

/**
 * API interface for /riepilogoNazionale path
 */
@Path("riepilogo-nazionale")
@Produces({ MediaType.APPLICATION_JSON })
public interface RiepilogoNazionaleApi {

	/**
	 * Posts a RiepilogoNazionale
	 * 
	 * @param riepilogoNazionale
	 * @param securityContext
	 * @param httpHeaders
	 * @param httpRequest
	 * @return
	 */
	@POST
	@Path("{idProspetto}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response postRiepilogoNazionale( RiepilogoNazionale riepilogoNazionale, 
			@PathParam("idProspetto") Long idProspetto, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	/**
	 * Put a RiepilogoNazionale for update
	 * 
	 * @param id             
	 * @param riepilogoNazionale
	 * @param securityContext
	 * @param httpHeaders
	 * @param httpRequest
	 * @return
	 */
	@PUT
	@Path("{idProspetto}/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response putRiepilogoNazionale(RiepilogoNazionale riepilogoNazionale,
			@PathParam("idProspetto") Long idProspetto,
			@PathParam("id") Long id,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);


}
