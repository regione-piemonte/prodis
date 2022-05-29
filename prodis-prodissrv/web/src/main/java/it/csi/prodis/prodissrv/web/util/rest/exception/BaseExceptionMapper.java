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
import javax.ws.rs.ext.ExceptionMapper;

import it.csi.prodis.prodissrv.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodissrv.lib.util.log.LogUtil;

/**
 * Base class for ExceptionMapping
 * @param <E> the exception type
 */
public abstract class BaseExceptionMapper<E extends Throwable> implements ExceptionMapper<E> {
	/** The configuration helper */
	@Inject protected ConfigurationHelper configurationHelper;
	/** The servlet response */
	@Context protected HttpServletResponse httpServletResponse;
	/** The logger */
	protected final LogUtil log = new LogUtil(getClass());

}
