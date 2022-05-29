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
package it.csi.prodis.prodisweb.web.util.rest.exception;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import it.csi.prodis.prodisweb.ejb.exception.ParamValidationException;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.dto.error.CoreError;

/**
 * Exception mapper for the ParamValidationException
 */
@Provider
public class ParamValidationExceptionMapper implements ExceptionMapper<ParamValidationException> {

	/** The configuration helper */
	@Inject protected ConfigurationHelper configurationHelper;
	/** The servlet response */
	@Context protected HttpServletResponse httpServletResponse;

	@Override
	public Response toResponse(ParamValidationException exception) {
		ExceptionMapperHelper emh = new ExceptionMapperHelper(configurationHelper, httpServletResponse, 400);
		return emh.toResponse(CoreError.REQUIRED_PARAMETER_OMITTED_EMPTY.getError());
	}

}
