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
package it.csi.prodis.prodisweb.ejb.util.jpa;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * Query page
 * @param <T> the retrieved type
 */
public interface Page<T> {

	/**
	 * @return the total elements
	 */
	long getTotalElements();
	/**
	 * @return the content
	 */
	Collection<T> getContent();

	/**
	 * @return the stream
	 */
	Stream<T> stream();
	/**
	 * @return the parallel stream
	 */
	Stream<T> parallelStream();

}
