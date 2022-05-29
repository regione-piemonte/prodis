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
import javax.ws.rs.GET;
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

import it.csi.prodis.prodisweb.lib.dto.prospetto.DatiProvinciali;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProspettoProvincia;

/**
 * API interface for /prospetto path
 */
@Path("dati-provinciali")
@Produces({ MediaType.APPLICATION_JSON })
public interface DatiProvincialiApi {
	
	@GET
	@Path("{idProspettoProv}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getDatiProvincialiByIdProspettoProv(@PathParam("idProspettoProv") Long idProspettoProv,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	
	@GET
	@Path("elenco-province-by-prospetto/{idProspetto}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getElencoProvinceQ2ByIdProspetto(@PathParam("idProspetto") Long idProspetto,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("riepilogo/{idProspetto}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getRiepilogoByIdProspetto(@PathParam("idProspetto") Long idProspetto,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("elenco-scoperture/{idProspetto}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getElencoScopertureByIdProspetto(@PathParam("idProspetto") Long idProspetto,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response postDatiProvinciali(ProspettoProvincia prospettoProvincia, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	

	@PUT
	@Path("{idProspettoProv}/{flagBozza}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response putDatiProvinciali(@PathParam("idProspettoProv") Long idProspettoProv, 
			@PathParam("flagBozza") Boolean flagBozza, 
			DatiProvinciali datiProvinciali, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@GET
	@Path("elenco-categorie-escluse/{idProspettoProv}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getCategorieEscluseByIdProspettoProv(@PathParam("idProspettoProv") Long idProspettoProv,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("elenco-prov-intermittenti/{idProspettoProv}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getProvIntermittentiByIdProspettoProv(@PathParam("idProspettoProv") Long idProspettoProv,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("elenco-part-time/{idProspettoProv}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getPartTimeByIdProspettoProv(@PathParam("idProspettoProv") Long idProspettoProv,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("elenco-lavoratori-in-forza/{idProspettoProv}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getLavoratoriInForzaByIdProspettoProv(@PathParam("idProspettoProv") Long idProspettoProv,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("conferma/{idProspetto}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getConfermaProvince(@PathParam("idProspetto") Long idProspetto,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@DELETE
	@Path("{idProspettoProv}")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response deleteDatiProvinciali(@PathParam("idProspettoProv") Long idProspettoProv,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
}
