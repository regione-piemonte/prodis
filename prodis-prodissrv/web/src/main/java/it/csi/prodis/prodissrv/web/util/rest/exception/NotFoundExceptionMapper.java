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
package it.csi.prodis.prodissrv.web.util.rest.exception;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import it.csi.prodis.prodissrv.ejb.exception.NotFoundException;
import it.csi.prodis.prodissrv.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodissrv.lib.dto.ApiError;
import it.csi.prodis.prodissrv.lib.dto.error.CoreError;

/**
 * Exception mapper for the BusinessException
 */
@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

	/** The configuration helper */
	@Inject protected ConfigurationHelper configurationHelper;
	/** The servlet response */
	@Context protected HttpServletResponse httpServletResponse;

	@Override
	public Response toResponse(NotFoundException exception) {
		ExceptionMapperHelper emh = new ExceptionMapperHelper(configurationHelper, httpServletResponse, 404);
		ApiError error = CoreError.UNMAPPED_ENTITY.getError("entity", exception.getMessage());
		return emh.toResponse(error);
	}

}
