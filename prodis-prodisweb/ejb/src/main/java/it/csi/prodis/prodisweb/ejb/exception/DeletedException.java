/*-
 * ========================LICENSE_START=================================
 * PRODIS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodisweb.ejb.exception;

import javax.ejb.ApplicationException;

/**
 * Exception signaling a deleted entity
 */
@ApplicationException(rollback = true)
public class DeletedException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3669781997904869102L;

	/**
	 * @see RuntimeException#RuntimeException(String)
	 */
	public DeletedException(String message) {
		super(message);
	}

	/**
	 * @see RuntimeException#RuntimeException(String, Throwable)
	 */
	public DeletedException(String message, Throwable cause) {
		super(message, cause);
	}

}
