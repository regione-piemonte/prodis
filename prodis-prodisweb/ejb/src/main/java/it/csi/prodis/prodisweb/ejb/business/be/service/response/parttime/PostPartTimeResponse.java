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

import it.csi.prodis.prodisweb.ejb.business.be.service.response.base.BasePostResponse;
import it.csi.prodis.prodisweb.lib.dto.prospetto.PartTime;

/**
 * The Class PostProspettoResponse.
 */
public class PostPartTimeResponse extends BasePostResponse<Integer, PartTime> {

	/** The model. */
	private PartTime partTime = new PartTime();


	public PartTime getPartTime() {
		return partTime;
	}

	public void setPartTime(PartTime partTime) {
		this.partTime = partTime;
	}

	@Override
	protected String getBaseUri() {
		return "intervento";
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PostPartTimeResponse [partTime=").append(partTime).append(", apiErrors=")
				.append(getApiErrors()).append("]");
		return builder.toString();
	}

	@Override
	protected PartTime getEntity() {
		return partTime;
	}

}
