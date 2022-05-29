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
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.prodis.prodisweb.lib.dto.prospetto.UserAccessLog;

/**
 * API interface for /common path
 */
@Path("common")
@Produces({ MediaType.APPLICATION_JSON })
public interface CommonApi {

	@GET
	@Path("ruolo")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getRuolo(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("getParametro/{nomeParametro}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getParametro(@PathParam("nomeParametro") String nomeParametro,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("userAccessLog")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response insertUserAccessLog(UserAccessLog loUserLog, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@GET
	@Path("getDataCalcolataConGiorniLavorativi/{dataInput}/{numeroGiorniLavorativi}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getDataCalcolataConGiorniLavorativi(@PathParam("dataInput") String dataInput,
			@PathParam("numeroGiorniLavorativi") Long numeroGiorniLavorativi, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

}
