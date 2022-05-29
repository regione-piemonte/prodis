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
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.csi.prodis.prodissrv.lib.dto.ApiError;
import it.csi.prodis.prodissrv.lib.dto.prospetto.FilterServiziProdis;
import it.csi.prodis.prodissrv.lib.dto.prospetto.PdfProspetto;
import it.csi.prodis.prodissrv.lib.dto.prospetto.Prospetto;
import it.csi.prodis.prodissrv.lib.dto.prospetto.TestataProspetto;
import it.csi.spicom.dto.ComunicazioneProspettoDisabiliDTO;

/**
 * API interface for /servizi path
 */
@Path("servizi")
@Produces({ MediaType.APPLICATION_JSON })
@Api(value = "Servizi", description = "APIs for working with users")
public interface ServiziApi {

	@ApiOperation(value = "Ricerca prospetti")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = TestataProspetto.class, responseContainer = "List"),
			@ApiResponse(code = 500, message = "OK", response = ApiError.class) })
	@POST
	@Path("findByFilterTestataProspetto")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findByFilterTestataProspetto(FilterServiziProdis filterTestataProspetto,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@ApiOperation(value = "Dettaglio Prospetto")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Prospetto.class),
			@ApiResponse(code = 500, message = "OK", response = ApiError.class) })
	@POST
	@Path("getDettaglioProspettoCompleto")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getDettaglioProspettoCompleto(FilterServiziProdis filterTestataProspetto,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@ApiOperation(value = "PDF Prospetto")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = PdfProspetto.class),
			@ApiResponse(code = 500, message = "OK", response = ApiError.class) })
	@POST
	@Path("findByPkPdfProspetto")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findByPkPdfProspetto(FilterServiziProdis filterTestataProspetto,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("riceviProspettoDaSpicom")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response riceviProspettoDaSpicom(ComunicazioneProspettoDisabiliDTO comunicazioneSpicom,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

}
