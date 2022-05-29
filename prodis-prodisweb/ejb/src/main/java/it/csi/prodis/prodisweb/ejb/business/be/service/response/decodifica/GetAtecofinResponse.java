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
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Atecofin;

/**
 * The Class GetAtecofinResponse.
 */
public class GetAtecofinResponse extends BaseGetResponse<List<Atecofin>> {

	private List<Atecofin> atecofins = new ArrayList<>();

	public List<Atecofin> getAtecos() {
		return atecofins;
	}

	public void setAtecos(List<Atecofin> atecos) {
		this.atecofins = atecos;
	}

	@Override
	protected List<Atecofin> getEntity() {
		return atecofins;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getSimpleName() + " [atecofin=").append(atecofins).append(", apiErrors=")
				.append(getApiErrors()).append("]");
		return builder.toString();
	}

}
