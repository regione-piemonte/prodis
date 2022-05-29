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

import java.util.List;

import it.csi.prodis.prodisweb.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProvIntermittenti;

/**
 * Response for reading provIntermittentis by its id Prospetto provinciale.
 */
public class GetProvIntermittentiByIdProspettoProvResponse extends BaseGetResponse<List<ProvIntermittenti>> {

	private List<ProvIntermittenti> provIntermittentis;

	@Override
	protected List<ProvIntermittenti> getEntity() {
		return provIntermittentis;
	}

	public List<ProvIntermittenti> getProvIntermittentis() {
		return provIntermittentis;
	}

	public void setProvIntermittentis(List<ProvIntermittenti> provIntermittentis) {
		this.provIntermittentis = provIntermittentis;
	}

}
