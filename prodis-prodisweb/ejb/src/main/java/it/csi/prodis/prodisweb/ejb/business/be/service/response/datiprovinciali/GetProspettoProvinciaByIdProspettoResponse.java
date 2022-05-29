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
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProspettoProvincia;

/**
 * Response for reading Prospetto by its id.
 */
public class GetProspettoProvinciaByIdProspettoResponse extends BaseGetResponse<List<ProspettoProvincia>> {

	private List<ProspettoProvincia> prospettoProvincias;

	@Override
	protected List<ProspettoProvincia> getEntity() {
		return prospettoProvincias;
	}

	public List<ProspettoProvincia> getProspettoProvincias() {
		return prospettoProvincias;
	}

	public void setProspettoProvincias(List<ProspettoProvincia> prospettoProvincias) {
		this.prospettoProvincias = prospettoProvincias;
	}

}
