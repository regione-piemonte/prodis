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
package it.csi.prodis.prodissrv.web.business.be.api.impl;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import it.csi.prodis.prodissrv.lib.util.serialization.JsonUtility;
import it.csi.prodis.prodissrv.web.business.be.api.SystemApi;
import it.csi.prodis.prodissrv.web.util.annotation.Logged;

/**
 * Implementation for SystemApiServiceImpl
 */
@Logged
public class SystemApiServiceImpl extends BaseRestServiceImpl implements SystemApi {

	@Override
	public Response ping(SecurityContext securityContext, HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) {
		String message = "ping versione allineata al 2021/11/15 ore 15.06";
		System.out.println(message);
		log.info("ping", message);
		return Response.ok().build();
	}

	@Override
	public Response access(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		String message = "test access";
		System.out.println(message);
		log.info("access", message);
		
		boolean bCheck = securityContext.isUserInRole("fruitore");
		log.info("bCheck", bCheck);

		String serialized = JsonUtility.serialize(bCheck);

		return Response.status(Status.OK).entity(serialized).build();
		// return Response.ok().build();
	}

}
