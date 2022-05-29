/*-
 * ========================LICENSE_START=================================
 * PRODIS Servizi - LIB submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodissrv.lib.util.function;

/**
 * Supplier which may throw an Exception
 *
 * @param <T> the resulting type
 * @param <E> the exception type
 */
@FunctionalInterface
public interface SupplierWithException<T, E extends Exception> {
	/**
	 * Get the value
	 * @return the value
	 * @throws E the exception
	 */
	public T get() throws E;
}
