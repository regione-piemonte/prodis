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
import it.csi.prodis.prodisweb.lib.dto.decodifiche.StatoProspetto;

/**
 * The Class GetProvinciaResponse.
 */
public class GetStatoProspettoResponse extends BaseGetResponse<List<StatoProspetto>> {

	private List<StatoProspetto> statoProspettos = new ArrayList<>();

	public List<StatoProspetto> getStatoProspettos() {
		return statoProspettos;
	}

	public void setStatoProspettos(List<StatoProspetto> statoProspettos) {
		this.statoProspettos = statoProspettos;
	}

	@Override
	protected List<StatoProspetto> getEntity() {
		return statoProspettos;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getSimpleName() + " [statoProspettos=").append(statoProspettos).append(", apiErrors=").append(getApiErrors())
				.append("]");
		return builder.toString();
	}

}
