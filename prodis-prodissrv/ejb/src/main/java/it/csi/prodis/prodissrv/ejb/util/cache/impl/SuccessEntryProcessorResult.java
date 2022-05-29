/*-
 * ========================LICENSE_START=================================
 * PRODIS Servizi - EJB submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodissrv.ejb.util.cache.impl;

import javax.cache.processor.EntryProcessorException;
import javax.cache.processor.EntryProcessorResult;

/**
 * EntryProcessorResult for success values
 * @param <T> the result type
 */
public class SuccessEntryProcessorResult<T> implements EntryProcessorResult<T> {
	
	/** The result */
	private final T result;
	
	/**
	 * the constructor
	 * @param result the result
	 */
	public SuccessEntryProcessorResult(T result) {
		this.result = result;
	}
	@Override
	public T get() throws EntryProcessorException {
		return result;
	}

}
