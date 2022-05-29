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
 * NULL auth adapter
 */
public class NullAuthAdapter implements AuthAdapter {

	@Override
	public Utente processAuth(boolean devMode, UriInfo uriInfo, ContainerRequestContext containerRequest) {
		return null;
	}

}
