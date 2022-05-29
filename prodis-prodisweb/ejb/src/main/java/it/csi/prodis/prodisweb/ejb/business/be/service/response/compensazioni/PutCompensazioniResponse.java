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
package it.csi.prodis.prodisweb.ejb.business.be.service.response.compensazioni;

import it.csi.prodis.prodisweb.ejb.business.be.service.response.base.BasePutResponse;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProvCompensazioni;

public class PutCompensazioniResponse extends BasePutResponse<Integer, ProvCompensazioni> {

	/** The model. */
	private ProvCompensazioni compensazioni = new ProvCompensazioni();

	public ProvCompensazioni getCompensazioni() {
		return compensazioni;
	}

	public void setCompensazioni(ProvCompensazioni compensazioni) {
		this.compensazioni = compensazioni;
	}

	@Override
	protected String getBaseUri() {
		return "intervento";
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PostCompensazioniResponse [compensazioni=").append(compensazioni).append(", apiErrors=")
				.append(getApiErrors()).append("]");
		return builder.toString();
	}

	@Override
	protected ProvCompensazioni getEntity() {
		return compensazioni;
	}

}
