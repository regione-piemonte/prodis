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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 * API interface for /silp path
 */
@Path("silp")
@Produces({ MediaType.APPLICATION_JSON })
public interface SilpApi {

	@GET
	@Path("azienda/{codiceFiscale}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAzienda(@PathParam("codiceFiscale") String codiceFiscale,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("lavoratore/{codiceFiscale}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getLavoratore(@PathParam("codiceFiscale") String codiceFiscale,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("sedi/{idAzienda}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getSedi(@PathParam("idAzienda") String idAzienda, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

}
