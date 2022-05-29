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

import it.csi.prodis.prodisweb.lib.dto.prospetto.CategorieEscluse;

@Path("categorie-escluse")
@Produces({ MediaType.APPLICATION_JSON })
public interface CategorieEscluseApi {
	
	@GET
	@Path("prospetto-prov/{idProspettoProv}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getCategorieEsclusePagByIdProspettoProv(@Min(0) @QueryParam("offset") Integer page,
			@Min(1) @Max(100) @QueryParam("limit") Integer limit, @QueryParam("sort") @DefaultValue("") String sort,
			@QueryParam("direction") @DefaultValue("asc") String direction,@PathParam("idProspettoProv") Long idProspettoProv,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	
	@POST
	@Path("{idDatiProvinciali}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response postCategorieEscluse(@PathParam("idDatiProvinciali") Long idDatiProvinciali,
			CategorieEscluse categorieEscluse, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@PUT
	@Path("{idDatiProvinciali}/{idCategorieEscluse}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response putCategorieEscluse(@PathParam("idDatiProvinciali") Long idDatiProvinciali,
			@PathParam("idCategorieEscluse") Long idCategorieEscluse, 
			CategorieEscluse categorieEscluse, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@DELETE
	@Path("{idCategorieEscluse}")
	public Response deleteCategorieEscluse(@PathParam("idCategorieEscluse") Long idCategorieEscluse, 
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
}
