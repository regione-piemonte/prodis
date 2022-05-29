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
package it.csi.prodis.prodissrv.ejb.business.be.service.response.base;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.csi.prodis.prodissrv.lib.util.serialization.JsonUtility;

/**
 * Base response for GET methods
 * @param <E> the returned type
 */
public abstract class BaseGetResponse<E> extends BaseResponse {

	/**
	 * @return whether the resulting value is present
	 */
	protected boolean isPresent() {
		return getEntity() != null;
	}

	/**
	 * @return the entity
	 */
	protected abstract E getEntity();

	@Override
	protected Response composeOwnResponse() {
		final String methodName = "composeOwnResponse";
		if(isPresent()) {
			String serialized = JsonUtility.serialize(getEntity());
			final int MAX_LENGTH = 10 * 1024;
			if (serialized != null && serialized.length() > MAX_LENGTH) {
				String logSerialized = serialized.substring(0, MAX_LENGTH) + "...";
				log.debug(methodName, "JSON response: " + logSerialized);
			} else {
				log.debug(methodName, "JSON response: " + serialized);
			}
			
			Response response =  Response
				.ok(serialized, MediaType.APPLICATION_JSON)
				// fv - il contenuto viene alle volte troncato - es. /prodis/api/v1/utente/settore/09ce7d4a-1c97-52de-9893-8eb23821df3d/modulo
				//.header("Content-Length", Integer.valueOf(StringHelper.getStringLengthUTF8(serialized)))
				.build();
			return response;
		}
		return Response.noContent().build();
	}

	/**
	 * Initializes a list
	 * @param <T> the generic type of the list
	 * @param value the value
	 * @return the list
	 */
	protected <T> List<T> initList(List<T> value) {
		return value != null ? value : new ArrayList<>();
	}

}
