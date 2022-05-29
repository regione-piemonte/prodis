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
package it.csi.prodis.prodisweb.ejb.business.be.service.response.decodifica;

import java.util.ArrayList;
import java.util.List;

import it.csi.prodis.prodisweb.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.prodis.prodisweb.lib.dto.common.DecodificaGenerica;

/**
 * The Class PostDecodificaGenericaResponse.
 */
public class PostDecodificaGenericaResponse extends BaseGetResponse<List<DecodificaGenerica>  > {


	private List<DecodificaGenerica> decodificaGenericas = new ArrayList<>();
	 
	

	public List<DecodificaGenerica> getDecodificaGenericas() {
		return decodificaGenericas;
	}

	public void setDecodificaGenericas( List<DecodificaGenerica> decodificaGenericas) {
		this.decodificaGenericas = decodificaGenericas;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getSimpleName() + " [decodificaGenericas=").append(decodificaGenericas).append(", apiErrors=")
				.append(getApiErrors()).append("]");
		return builder.toString();
	}



	@Override
	protected List<DecodificaGenerica> getEntity() {

		return decodificaGenericas;
	}

	
}
