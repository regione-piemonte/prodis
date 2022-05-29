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
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 * API interface for /utente path
 */
@Path("utente")
@Produces({ MediaType.APPLICATION_JSON })
public interface UtenteApi {

	/**
	 * Gets the Utente
	 * 
	 * @param securityContext the security context
	 * @param httpHeaders     the HTTP headers
	 * @param httpRequest     the HTTP request
	 * @return the response
	 */
	@GET
	@Path("self")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getUtenteSelf(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	

}
