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

import it.csi.prodis.prodissrv.ejb.exception.BusinessException;
import it.csi.prodis.prodissrv.ejb.util.conf.ConfigurationHelper;

/**
 * Exception mapper for the BusinessException
 */
@Provider
public class BusinessExceptionMapper implements ExceptionMapper<BusinessException> {

	/** The configuration helper */
	@Inject protected ConfigurationHelper configurationHelper;
	/** The servlet response */
	@Context protected HttpServletResponse httpServletResponse;

	@Override
	public Response toResponse(BusinessException exception) {
		ExceptionMapperHelper emh = new ExceptionMapperHelper(configurationHelper, httpServletResponse, 400);
		// return emh.toResponse(exception.getError());
		return emh.toResponse(exception.getErrors());
	}

}
