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

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.prodis.prodisweb.lib.dto.common.DecodificaGenerica;

/**
 * API interface for /decodifica path
 */
@Path("decodifica")
@Produces({ MediaType.APPLICATION_JSON })
public interface DecodificaApi {

	/**
	 * Gets the comuni
	 * 
	 * @param securityContext the security context
	 * @param httpHeaders     the HTTP headers
	 * @param httpRequest     the HTTP request
	 * @return the response
	 */
	@GET
	@Path("comune")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getComune(@QueryParam("idProvincia") Long idProvincia,
			@QueryParam("codComuneMin") String codComuneMin, @QueryParam("dsProTComune") String dsProTComune,
			@QueryParam("data") Date data, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Gets the province
	 * 
	 * @param securityContext the security context
	 * @param httpHeaders     the HTTP headers
	 * @param httpRequest     the HTTP request
	 * @return the response
	 */
	@GET
	@Path("provincia")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getProvincia(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Gets the state prospects
	 * 
	 * @param securityContext the security context
	 * @param ht@Override     tpHeaders the HTTP headers
	 * @param httpRequest     the HTTP request
	 * @return the response
	 */
	@GET
	@Path("stato-prospetto")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getStatoProspetto(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

//	/**
//	 * Gets the regioni
//	 * 
//	 * @param securityContext the security context
//	 * @param httpHeaders     the HTTP headers
//	 * @param httpRequest     the HTTP request
//	 * @return the response
//	 */
//	@GET
//	@Path("regione")
//	@Produces({ MediaType.APPLICATION_JSON })
//	public Response getRegione(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
//			@Context HttpServletRequest httpRequest);

	/**
	 * Gets the ccnl
	 * 
	 * @param securityContext the security context
	 * @param httpHeaders     the HTTP headers
	 * @param httpRequest     the HTTP request
	 * @return the response
	 */
	@GET
	@Path("ccnl")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getCcnl(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Gets the atecofin
	 * 
	 * @param securityContext the security context
	 * @param httpHeaders     the HTTP headers
	 * @param httpRequest     the HTTP request
	 * @return the response
	 */
	@GET
	@Path("atecofin")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAtecofin(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Gets the ccnl
	 * 
	 * @param securityContext the security context
	 * @param httpHeaders     the HTTP headers
	 * @param httpRequest     the HTTP request
	 * @return the response
	 */
	@POST
	@Path("ccnlDecodifica")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response postCcnlDecodifica(DecodificaGenerica filtro, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	/**
	 * Gets the atecofin decodifica
	 * 
	 * @param securityContext the security context
	 * @param httpHeaders     the HTTP headers
	 * @param httpRequest     the HTTP request
	 * @return the response
	 */
	@POST
	@Path("atecofinDecodifica")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response postAtecofinDecodifica(DecodificaGenerica filtro, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	/**
	 * Gets the statiEsteri decodifica
	 * 
	 * @param securityContext the security context
	 * @param httpHeaders     the HTTP headers
	 * @param httpRequest     the HTTP request
	 * @return the response
	 */
	@POST
	@Path("statiEsteriDecodifica")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response postStatiEsteriDecodifica(DecodificaGenerica filtro, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	/**
	 * Gets the contratti decodifica
	 * 
	 * @param securityContext the security context
	 * @param httpHeaders     the HTTP headers
	 * @param httpRequest     the HTTP request
	 * @return the response
	 */
	@POST
	@Path("contrattiDecodifica")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response postContrattiDecodifica(DecodificaGenerica filtro, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	/**
	 * Gets the assunzioneProtetta decodifica
	 * 
	 * @param securityContext the security context
	 * @param httpHeaders     the HTTP headers
	 * @param httpRequest     the HTTP request
	 * @return the response
	 */
	@POST
	@Path("assunzioneProtettaDecodifica")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response postAssunzioneProtettaDecodifica(DecodificaGenerica filtro,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Gets the assunzioneProtetta decodifica
	 * 
	 * @param securityContext the security context
	 * @param httpHeaders     the HTTP headers
	 * @param httpRequest     the HTTP request
	 * @return the response
	 */
	@POST
	@Path("dichiaranteDecodifica")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response postDichiaranteDecodifica(DecodificaGenerica filtro, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	/**
	 * Gets the categoriaEscluse decodifica
	 * 
	 * @param securityContext the security context
	 * @param httpHeaders     the HTTP headers
	 * @param httpRequest     the HTTP request
	 * @return the response
	 */
	@POST
	@Path("categoriaEscluseDecodifica")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response postCategoriaEscluseDecodifica(DecodificaGenerica filtro, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	/**
	 * Gets the causaSospensione decodifica
	 * 
	 * @param securityContext the security context
	 * @param httpHeaders     the HTTP headers
	 * @param httpRequest     the HTTP request
	 * @return the response
	 */
	@POST
	@Path("causaSospensioneDecodifica")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response postCausaSospensioneDecodifica(DecodificaGenerica filtro, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	/**
	 * Gets the qualifica decodifica
	 * 
	 * @param securityContext the security context
	 * @param httpHeaders     the HTTP headers
	 * @param httpRequest     the HTTP request
	 * @return the response
	 */
	@POST
	@Path("qualificaDecodifica")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response postQualificaDecodifica(DecodificaGenerica filtro, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	/**
	 * Gets the tipologia lavoratore decodifica
	 * 
	 * @param securityContext the security context
	 * @param httpHeaders     the HTTP headers
	 * @param httpRequest     the HTTP request
	 * @return the response
	 */
	@POST
	@Path("tipologiaLavoratoreDecodifica")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response postTipologiaLavoratoreDecodifica(DecodificaGenerica filtro,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	/**
	 * Gets the comune decodifica
	 * 
	 * @param securityContext the security context
	 * @param httpHeaders     the HTTP headers
	 * @param httpRequest     the HTTP request
	 * @return the response
	 */
	@POST
	@Path("comuneDecodifica")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response postComuneDecodifica(DecodificaGenerica filtro, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	/**
	 * Gets the regione decodifica
	 * 
	 * @param securityContext the security context
	 * @param httpHeaders     the HTTP headers
	 * @param httpRequest     the HTTP request
	 * @return the response
	 */
	@POST
	@Path("regioneDecodifica")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response postRegioneDecodifica(DecodificaGenerica filtro, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	/**
	 * Gets the regione decodifica
	 * 
	 * @param securityContext the security context
	 * @param httpHeaders     the HTTP headers
	 * @param httpRequest     the HTTP request
	 * @return the response
	 */
	@POST
	@Path("soggettiDecodifica")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response postSoggettiDecodifica(DecodificaGenerica filtro, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	/**
	 * Gets the statoConcessione decodifica
	 * 
	 * @param securityContext the security context
	 * @param httpHeaders     the HTTP headers
	 * @param httpRequest     the HTTP request
	 * @return the response
	 */
	@POST
	@Path("statoConcessioneDecodifica")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response postStatoConcessioneDecodifica(DecodificaGenerica filtro, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

}
