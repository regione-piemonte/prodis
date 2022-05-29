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

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import it.csi.prodis.prodisweb.ejb.util.mime.MimeTypeContainer.MimeType;
import it.csi.prodis.prodisweb.lib.dto.prospetto.LavoratoriInForza;
import it.csi.prodis.prodisweb.web.dto.WebLavoratoriInForzaFileHolder;

@Path("lavoratori-in-forza")
public interface LavoratoriInForzaApi {

	@POST
	@Path("insert/{idDatiProvinciali}/{flagWarning}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response postLavoratoriInForza(@PathParam("idDatiProvinciali") Long idDatiProvinciali,
			@PathParam("flagWarning") Boolean flagWarning,
			LavoratoriInForza lavoratoriInForza, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@POST
	@Path("excel/upload/prospetto/{idProspetto}")
	@Produces({MimeType.EXCEL_SPREADSHEET})
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadProspettoLavoratoriInForza(
			@PathParam("idProspetto") Long idProspetto,
			@MultipartForm WebLavoratoriInForzaFileHolder webLavoratoriInForzaFileHolder,		
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@POST
	@Path("excel/upload/provincia/{idProspettoProv}")
	@Produces({MimeType.EXCEL_SPREADSHEET})
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadProvinciaLavoratoriInForza(
			@PathParam("idProspettoProv") Long idProspettoProv,
			@MultipartForm WebLavoratoriInForzaFileHolder webLavoratoriInForzaFileHolder,		
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@PUT
	@Path("{idDatiProvinciali}/{idLavoratoriInForza}/{flagWarning}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response putLavoratoriInForza(@PathParam("idDatiProvinciali") Long idDatiProvinciali,
			@PathParam("idLavoratoriInForza") Long idLavoratoriInForza, 
			@PathParam("flagWarning") Boolean flagWarning,
			LavoratoriInForza lavoratoriInForza, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@DELETE
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("{idLavoratoriInForza}")
	public Response deleteLavoratoriInForza(@PathParam("idLavoratoriInForza") Long idLavoratoriInForza, 
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@GET
	@Produces({MimeType.EXCEL_SPREADSHEET})
	@Path("excel/download/provincia/{idProspettoProv}")
	public Response downloadProvinciaLavoratoriInForza(@PathParam("idProspettoProv") Long idProspettoProv,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@GET
	//@Produces({MimeType.EXCEL_SPREADSHEET})
	@Produces({MediaType.APPLICATION_JSON,MimeType.EXCEL_SPREADSHEET })
	@Path("excel/download/prospetto/{idProspetto}")
	public Response downloadProspettoLavoratoriInForza(@PathParam("idProspetto") Long idProspetto,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) throws Exception;
	
}
