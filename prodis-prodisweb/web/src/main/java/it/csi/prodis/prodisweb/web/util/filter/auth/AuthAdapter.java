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
package it.csi.prodis.prodisweb.web.util.filter.auth;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.UriInfo;

import it.csi.prodis.prodisweb.lib.dto.Utente;

/**
 * Autentication adapter
 */
public interface AuthAdapter {
	/**
	 * Processes the authentication
	 * @param devMode dev mode
	 * @param uriInfo the URI info
	 * @param containerRequest the container request
	 * @return the user associated with the context
	 */
	Utente processAuth(boolean devMode, UriInfo uriInfo, ContainerRequestContext containerRequest);
}
