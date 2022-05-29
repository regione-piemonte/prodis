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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.prodis.prodisweb.lib.dto.prospetto.PostiLavoroDisp;

@Path("posti-lavoro-disp")
@Produces({ MediaType.APPLICATION_JSON })
public interface PostiLavoroDispApi {

	@POST
	@Path("{idDatiProvinciali}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response postPostiLavoroDisp(@PathParam("idDatiProvinciali") Long idDatiProvinciali, 
			PostiLavoroDisp postiLavoroDisp, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@PUT
	@Path("{idDatiProvinciali}/{idPostiLavoroDisp}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response putPostiLavoroDisp(@PathParam("idDatiProvinciali") Long idDatiProvinciali, 
			@PathParam("idPostiLavoroDisp") Long idPostiLavoroDisp, 
			PostiLavoroDisp postiLavoroDisp, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@DELETE
	@Path("{idPostiLavoroDisp}")
	public Response deletePostiLavoroDisp(@PathParam("idPostiLavoroDisp") Long idPostiLavoroDisp, 
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	
}
