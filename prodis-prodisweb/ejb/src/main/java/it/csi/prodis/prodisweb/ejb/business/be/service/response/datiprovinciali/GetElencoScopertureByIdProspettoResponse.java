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
import it.csi.prodis.prodisweb.lib.dto.prospetto.ElencoProvScoperture;

/**
 * Response for reading Elenco scoperture by its id Prospetto.
 */
public class GetElencoScopertureByIdProspettoResponse extends BaseGetResponse<List<ElencoProvScoperture>> {

	private List<ElencoProvScoperture> elencoProvScopertures;

	@Override
	protected List<ElencoProvScoperture> getEntity() {
		return elencoProvScopertures;
	}

	public List<ElencoProvScoperture> getElencoProvScopertures() {
		return elencoProvScopertures;
	}

	public void setElencoProvScopertures(List<ElencoProvScoperture> elencoProvScopertures) {
		this.elencoProvScopertures = elencoProvScopertures;
	}

}
