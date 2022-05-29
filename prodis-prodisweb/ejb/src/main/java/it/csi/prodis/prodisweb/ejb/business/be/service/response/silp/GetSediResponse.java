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

import java.util.ArrayList;
import java.util.List;

import it.csi.prodis.prodisweb.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.prodis.prodisweb.lib.dto.prospetto.SedeLegale;

public class GetSediResponse extends BaseGetResponse<List<SedeLegale>> {

	/** The model. */
	private List<SedeLegale> leSedi = new ArrayList<SedeLegale>();

	@Override
	protected List<SedeLegale> getEntity() {
		return leSedi;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		return builder.toString();
	}

	public List<SedeLegale> getLeSedi() {
		return leSedi;
	}

	public void setLeSedi(List<SedeLegale> leSedi) {
		this.leSedi = leSedi;
	}

}
