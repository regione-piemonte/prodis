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
package it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto;

import it.csi.prodis.prodisweb.ejb.business.be.service.request.base.BaseRequest;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ReinviaProspetto;

public class ReinviaProspettoRequest implements BaseRequest {

	private final ReinviaProspetto reinviaProspetto;

	/**
	 * Constructor
	 * 
	 * @param list of ids
	 */
	public ReinviaProspettoRequest(ReinviaProspetto reinviaProspetto) {
		this.reinviaProspetto = reinviaProspetto;
	}

	/**
	 * @return the list of ids
	 */
	public ReinviaProspetto getReinviaProspetto() {
		return reinviaProspetto;
	}


}
