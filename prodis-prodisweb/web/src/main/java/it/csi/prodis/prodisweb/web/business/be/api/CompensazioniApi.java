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
package it.csi.prodis.prodisweb.web.business.be.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.prodis.prodisweb.lib.dto.prospetto.ProvCompensazioni;

@Path("compensazioni")
public interface CompensazioniApi {

	@POST
	@Path("{idDatiProvinciali}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response postCompensazioni(@PathParam("idDatiProvinciali") Long idDatiProvinciali,
			ProvCompensazioni compensazioni, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@PUT
	@Path("{idDatiProvinciali}/{idCompensazioni}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response putCompensazioni(@PathParam("idDatiProvinciali") Long idDatiProvinciali,
			@PathParam("idCompensazioni") Long idCompensazioni, 
			ProvCompensazioni compensazioni, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@DELETE
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("{idCompensazioni}")
	public Response deleteCompensazioni(@PathParam("idCompensazioni") Long idCompensazioni, 
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON})
	@Path("confermaTornaRiepilogo/{idProspetto}")
	public Response confermaTornaRiepilogo(@PathParam("idProspetto") Long idProspetto,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
}
