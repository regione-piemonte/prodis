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
package it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto;


import it.csi.prodis.prodisweb.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.prodis.prodisweb.lib.dto.prospetto.Prospetto;

/**
 * Response for reading Prospetto by its id.
 */
public class GetPropspettoByIdResponse extends BaseGetResponse<Prospetto> {

	private Prospetto prospetto;

	/**
	 * @return the prospetto
	 */
	public Prospetto getProspetto() {
		return prospetto;
	}

	/**
	 * @param prospetto the prospetto to set
	 */
	public void setProspetto(Prospetto prospetto) {
		this.prospetto = prospetto;
	}

	@Override
	protected Prospetto getEntity() {
		return prospetto;
	}

}
