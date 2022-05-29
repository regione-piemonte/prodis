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
package it.csi.prodis.prodisweb.ejb.business.be.service.response.base;

import java.util.Optional;

/**
 * Base response for GET methods, with Optional results
 * @param <E> the returned type
 */
public abstract class BaseOptionalGetResponse<E> extends BaseGetResponse<E> {

	/**
	 * @return the optional value
	 */
	protected abstract Optional<E> getOptional();

	@Override
	protected boolean isPresent() {
		return getOptional().isPresent();
	}

	@Override
	protected E getEntity() {
		return getOptional().orElse(null);
	}

}
