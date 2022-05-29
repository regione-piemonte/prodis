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
package it.csi.prodis.prodisweb.ejb.util.exception;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import it.csi.prodis.prodisweb.lib.dto.ApiError;

/**
 * Marks a class as being aware of a BusinessException check
 */
public interface BusinessExceptionAware {

	/**
	 * Checks for business condition
	 * @param condition the condition to check
	 * @param message the message to supply to the exception
	 */
	void checkBusinessCondition(boolean condition, ApiError message);
	/**
	 * Checks for business condition
	 * @param condition supplier for the condition to check
	 * @param message the message to supply to the exception
	 */
	void checkBusinessCondition(BooleanSupplier condition, ApiError message);

	/**
	 * Checks for business condition
	 * @param condition the condition to check
	 * @param message the message to supply to the exception
	 */
	void checkBusinessCondition(boolean condition, Supplier<ApiError> message);
	/**
	 * Checks for business condition
	 * @param condition supplier for the condition to check
	 * @param message the message to supply to the exception
	 */
	void checkBusinessCondition(BooleanSupplier condition, Supplier<ApiError> message);
}
