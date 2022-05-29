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
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Comune;

/**
 * The Class GetComuneResponse.
 */
public class GetComuneResponse extends BaseGetResponse<List<Comune>> {

	private List<Comune> comunes = new ArrayList<>();
	
	public List<Comune> getComunes() {
		return comunes;
	}

	public void setComunes(List<Comune> comunes) {
		this.comunes = comunes;
	}

	@Override
	protected List<Comune> getEntity() {
		return comunes;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GetComuneResponse [comunes=").append(comunes).append(", apiErrors=").append(getApiErrors()).append("]");
		return builder.toString();
	}



}
