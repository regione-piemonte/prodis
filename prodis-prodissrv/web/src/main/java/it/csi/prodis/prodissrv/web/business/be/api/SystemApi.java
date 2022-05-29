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
/**********************************************
 * CSI PIEMONTE
 **********************************************/
package it.csi.prodis.prodissrv.web.business.be.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 * API interface for /system path
 */
@Path("system")
@Produces({ MediaType.APPLICATION_JSON })
public interface SystemApi {

	/**
	 * Ping method
	 * @param securityContext the security context
	 * @param httpHeaders the HTTP headers
	 * @param httpRequest the HTTP request
	 * @return the response
	 */
	@GET
	@Path("ping")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response ping(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("access")
	// @RolesAllowed({"fruitore"})
	@Produces({ MediaType.APPLICATION_JSON })
	public Response access(
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
}
