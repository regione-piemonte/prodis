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
package it.csi.prodis.prodisweb.ejb.business.be.service.response.silp;

import it.csi.prodis.prodisweb.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.prodis.prodisweb.lib.dto.prospetto.LavoratoriSilp;

/**
 * The Class GetAziendaResponse.
 */
public class GetLavoratoreResponse extends BaseGetResponse<LavoratoriSilp> {

	/** The model. */
	private LavoratoriSilp lavoratoriSilp = new LavoratoriSilp();

	/**
	 * @return the lavoratoriSilp
	 */
	public LavoratoriSilp getLavoratoriSilp() {
		return lavoratoriSilp;
	}

	/**
	 * @param lavoratoriSilp the lavoratoriSilp to set
	 */
	public void setLavoratoriSilp(LavoratoriSilp lavoratoriSilp) {
		this.lavoratoriSilp = lavoratoriSilp;
	}

	@Override
	protected LavoratoriSilp getEntity() {
		return lavoratoriSilp;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GetAziendaResponse [lavoratoriSilp=").append(lavoratoriSilp).append(", apiErrors=")
				.append(getApiErrors()).append("]");
		return builder.toString();
	}

}
