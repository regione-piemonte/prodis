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
package it.csi.prodis.prodisweb.ejb.business.be.service.response.datiprovinciali;

import it.csi.prodis.prodisweb.ejb.business.be.service.response.base.BasePostResponse;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProspettoProvincia;

/**
 * The Class PostProspettoResponse.
 */
public class PostProspettoProvinciaResponse extends BasePostResponse<Integer, ProspettoProvincia> {

	/** The model. */
	private ProspettoProvincia prospettoProvincia = new ProspettoProvincia();



	public ProspettoProvincia getProspettoProvincia() {
		return prospettoProvincia;
	}

	public void setProspettoProvincia(ProspettoProvincia prospettoProvincia) {
		this.prospettoProvincia = prospettoProvincia;
	}

	@Override
	protected String getBaseUri() {
		return "prospettoProvincia";
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PostProspettoProvinciaResponse [prospettoProvincia=").append(prospettoProvincia).append(", apiErrors=")
				.append(getApiErrors()).append("]");
		return builder.toString();
	}

	@Override
	protected ProspettoProvincia getEntity() {
		return prospettoProvincia;
	}

}
