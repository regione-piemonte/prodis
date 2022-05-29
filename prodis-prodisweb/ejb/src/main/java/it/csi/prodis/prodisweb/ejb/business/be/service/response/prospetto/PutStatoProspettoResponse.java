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
package it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto;

import it.csi.prodis.prodisweb.ejb.business.be.service.response.base.BasePutResponse;
import it.csi.prodis.prodisweb.lib.dto.prospetto.Prospetto;

public class PutStatoProspettoResponse extends BasePutResponse<Long, Prospetto> {

	private Prospetto prospetto = new Prospetto();

	public Prospetto getProspetto() {
		return prospetto;
	}

	public void setProspetto(Prospetto prospetto) {
		this.prospetto = prospetto;
	}

	@Override
	protected Prospetto getEntity() {
		return prospetto;
	}

	@Override
	protected String getBaseUri() {
		return "intervento";
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PutStatoProspettoResponse [prospetto=").append(prospetto).append(", apiErrors=")
				.append(getApiErrors()).append("]");
		return builder.toString();
	}

}
