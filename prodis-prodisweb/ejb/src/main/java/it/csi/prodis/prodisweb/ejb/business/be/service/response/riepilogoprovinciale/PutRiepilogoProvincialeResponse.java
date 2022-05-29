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
package it.csi.prodis.prodisweb.ejb.business.be.service.response.riepilogoprovinciale;

import it.csi.prodis.prodisweb.ejb.business.be.service.response.base.BasePostResponse;
import it.csi.prodis.prodisweb.lib.dto.prospetto.RiepilogoProvinciale;

/**
 * The Class PostProspettoResponse.
 */
public class PutRiepilogoProvincialeResponse extends BasePostResponse<Integer, RiepilogoProvinciale> {

	/** The model. */
	private RiepilogoProvinciale riepilogoProvinciale = new RiepilogoProvinciale();


	public RiepilogoProvinciale getRiepilogoProvinciale() {
		return riepilogoProvinciale;
	}

	public void setRiepilogoProvinciale(RiepilogoProvinciale riepilogoProvinciale) {
		this.riepilogoProvinciale = riepilogoProvinciale;
	}

	@Override
	protected String getBaseUri() {
		return "intervento";
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PutRiepilogoProvincialeResponse [riepilogoProvinciale=").append(riepilogoProvinciale).append(", apiErrors=")
				.append(getApiErrors()).append("]");
		return builder.toString();
	}

	@Override
	protected RiepilogoProvinciale getEntity() {
		return riepilogoProvinciale;
	}

}
