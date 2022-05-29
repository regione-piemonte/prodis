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

import it.csi.prodis.prodisweb.ejb.business.be.service.response.base.BasePutResponse;
import it.csi.prodis.prodisweb.lib.dto.prospetto.DatiProvinciali;

public class PutDatiProvincialiResponse extends BasePutResponse<Integer, DatiProvinciali> {

	/** The model. */
	private DatiProvinciali datiProvinciali = new DatiProvinciali();

	public DatiProvinciali getDatiProvinciali() {
		return datiProvinciali;
	}

	public void setDatiProvinciali(DatiProvinciali datiProvinciali) {
		this.datiProvinciali = datiProvinciali;
	}

	@Override
	protected DatiProvinciali getEntity() {
		return datiProvinciali;
	}

	@Override
	protected String getBaseUri() {
		return "intervento";
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PutDatiProvincialiResponse [prospetto=").append(datiProvinciali).append(", apiErrors=")
				.append(getApiErrors()).append("]");
		return builder.toString();
	}

}
