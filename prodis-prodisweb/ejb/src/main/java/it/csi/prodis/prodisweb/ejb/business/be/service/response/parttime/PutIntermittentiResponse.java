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
package it.csi.prodis.prodisweb.ejb.business.be.service.response.parttime;

import it.csi.prodis.prodisweb.ejb.business.be.service.response.base.BasePutResponse;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProvIntermittenti;

public class PutIntermittentiResponse extends BasePutResponse<Integer, ProvIntermittenti> {

	/** The model. */
	private ProvIntermittenti intermittenti = new ProvIntermittenti();


	public ProvIntermittenti getIntermittenti() {
		return intermittenti;
	}

	public void setIntermittenti(ProvIntermittenti intermittenti) {
		this.intermittenti = intermittenti;
	}

	@Override
	protected String getBaseUri() {
		return "intervento";
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PutIntermittentiResponse [intermittenti=").append(intermittenti).append(", apiErrors=")
				.append(getApiErrors()).append("]");
		return builder.toString();
	}

	@Override
	protected ProvIntermittenti getEntity() {
		return intermittenti;
	}

}
