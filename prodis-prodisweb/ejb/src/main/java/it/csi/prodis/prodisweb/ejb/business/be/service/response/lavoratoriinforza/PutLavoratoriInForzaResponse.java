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
package it.csi.prodis.prodisweb.ejb.business.be.service.response.lavoratoriinforza;

import it.csi.prodis.prodisweb.ejb.business.be.service.response.base.BasePutResponse;
import it.csi.prodis.prodisweb.lib.dto.prospetto.LavoratoriInForza;

public class PutLavoratoriInForzaResponse extends BasePutResponse<Integer, LavoratoriInForza> {

	/** The model. */
	private LavoratoriInForza lavoratoriInForza = new LavoratoriInForza();






	public LavoratoriInForza getLavoratoriInForza() {
		return lavoratoriInForza;
	}

	public void setLavoratoriInForza(LavoratoriInForza lavoratoriInForza) {
		this.lavoratoriInForza = lavoratoriInForza;
	}

	@Override
	protected String getBaseUri() {
		return "intervento";
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PutLavoratoriInForzaResponse [lavoratoriInForza=").append(lavoratoriInForza).append(", apiErrors=")
				.append(getApiErrors()).append("]");
		return builder.toString();
	}

	@Override
	protected LavoratoriInForza getEntity() {
		return lavoratoriInForza;
	}

}
