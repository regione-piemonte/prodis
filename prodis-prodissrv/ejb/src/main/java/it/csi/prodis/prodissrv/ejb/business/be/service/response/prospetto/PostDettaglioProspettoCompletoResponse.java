/*-
 * ========================LICENSE_START=================================
 * PRODIS Servizi - EJB submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodissrv.ejb.business.be.service.response.prospetto;

import it.csi.prodis.prodissrv.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.prodis.prodissrv.lib.dto.prospetto.Prospetto;

/**
 * Response for reading Prospetto by its id.
 */
public class PostDettaglioProspettoCompletoResponse extends BaseGetResponse<Prospetto> {

	private Prospetto prospetto;

	public Prospetto getProspetto() {
		return prospetto;
	}

	public void setProspetto(Prospetto prospetto) {
		this.prospetto = prospetto;
	}

	@Override
	protected Prospetto getEntity() {
		return prospetto;
	}

}
