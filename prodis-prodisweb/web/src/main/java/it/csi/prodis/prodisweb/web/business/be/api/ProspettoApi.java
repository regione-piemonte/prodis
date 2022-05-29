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

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.prodis.prodisweb.ejb.util.mime.MimeTypeContainer.MimeType;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ConfermaRiepilogoProspetto;
import it.csi.prodis.prodisweb.lib.dto.prospetto.Prospetto;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ReinviaProspetto;
import it.csi.prodis.prodisweb.lib.dto.prospetto.RicercaProspetto;

/**
 * API interface for /prospetto path
 */
@Path("prospetto")
public interface ProspettoApi {

	@PUT
	@Path("genera-pdf/{idProspetto}")
	@Produces({ MimeType.PDF })
	public Response generaPdf(@PathParam("idProspetto") Long idProspetto, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@PUT
	@Path("rettifica/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response rettificaProspetto(@PathParam("id") Long id, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@PUT
	@Path("annulla/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response annullaProspetto(@PathParam("id") Long id, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@PUT
	@Path("duplica/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response duplicaProspetto(@PathParam("id") Long id, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@POST
	@Path("confermaRiepilogo/{idProspetto}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response confermaRiepilogo(ConfermaRiepilogoProspetto confermaRiepilogoProspetto,
			@PathParam("idProspetto") Long idProspetto, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@POST
	@Path("salvaBozzaRiepilogo/{idProspetto}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response salvaBozzaRiepilogo(Prospetto prospetto, @PathParam("idProspetto") Long idProspetto,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("ricerca")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getRicercaProspetti(@Min(0) @QueryParam("offset") Integer page,
			@Min(1) @Max(100) @QueryParam("limit") Integer limit, @QueryParam("sort") @DefaultValue("") String sort,
			@QueryParam("direction") @DefaultValue("asc") String direction, RicercaProspetto ricercaProspetto,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Posts a Prospetto
	 * 
	 * @param prospetto
	 * @param securityContext
	 * @param httpHeaders
	 * @param httpRequest
	 * @return
	 */
	@POST
	@Path("{flagBozza}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response postProspetto(@PathParam("flagBozza") Boolean flagBozza, Prospetto prospetto,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Put a Prospetto for update
	 * 
	 * @param id              del prospetto du cui fare l'update (path param)
	 * @param prospetto
	 * @param securityContext
	 * @param httpHeaders
	 * @param httpRequest
	 * @return
	 */
	@PUT
	@Path("update/{id}/{flagBozza}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response putProspettoUpdate(@PathParam("id") Long id, Prospetto prospetto,
			@PathParam("flagBozza") Boolean flagBozza, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	/**
	 * Gets a Prospetto by its id
	 * 
	 * @param id              the id
	 * @param securityContext the security context
	 * @param httpHeaders     the HTTP headers
	 * @param httpRequest     the HTTP request
	 * @return the response
	 */
	@GET
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getProspettoById(@PathParam("id") Long id, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@GET
	@Path("assunzioniPubbliche/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAssunzioniPubblicheByIdProspetto(@PathParam("id") Long id,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("checkCodiceFiscale/{codiceFiscale}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getCheckCodiceFiscale(@PathParam("codiceFiscale") String codiceFiscale,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Delete a Prospetto
	 * 
	 * @param id              del prospetto du cui fare l'update (path param)
	 * @param prospetto
	 * @param securityContext
	 * @param httpHeaders
	 * @param httpRequest
	 * @return
	 */
	@DELETE
	@Path("delete/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response deleteProspetto(@PathParam("id") Long id, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@PUT
	@Path("stato-prospetto/{idStatoProspetto}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response putStatoProspettoUpdate(Prospetto prospetto, @PathParam("idStatoProspetto") Long idStatoProspetto,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("checkPassaggioQ3/{idProspetto}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response checkPassaggioQ3(@PathParam("idProspetto") Long idProspetto,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("storeProcedure/{idProspetto}/{cfUenteAggiornamento}/{soloScoperture}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response storeProcedureEseguiCalcoli(@PathParam("idProspetto") Long idProspetto,
			@PathParam("cfUenteAggiornamento") String cfUenteAggiornamento,
			@PathParam("soloScoperture") boolean soloScoperture, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@PUT
	@Path("reinvia")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response reinviaProspetto(ReinviaProspetto reinviaProspetto,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@GET
	@Path("checkScopertureDatoriLavoriPubblici/{idProspetto}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getCheckScopertureDatoriLavoriPubblici(@PathParam("idProspetto") Long idProspetto,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	
	@PUT
	@Path("salva-pdf")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response salvaPdf(List<String> idProspettos, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

}
