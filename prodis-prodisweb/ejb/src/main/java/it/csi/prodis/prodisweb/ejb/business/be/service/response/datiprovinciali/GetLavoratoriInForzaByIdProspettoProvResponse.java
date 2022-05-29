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
import it.csi.prodis.prodisweb.lib.dto.prospetto.LavoratoriInForza;

/**
 * Response for reading lavoratoriInForzas by its id Prospetto provinciale.
 */
public class GetLavoratoriInForzaByIdProspettoProvResponse extends BaseGetResponse<List<LavoratoriInForza>> {

	private List<LavoratoriInForza> lavoratoriInForzas;

	@Override
	protected List<LavoratoriInForza> getEntity() {
		return lavoratoriInForzas;
	}

	public List<LavoratoriInForza> getLavoratoriInForzas() {
		return lavoratoriInForzas;
	}

	public void setLavoratoriInForzas(List<LavoratoriInForza> lavoratoriInForzas) {
		this.lavoratoriInForzas = lavoratoriInForzas;
	}

}
