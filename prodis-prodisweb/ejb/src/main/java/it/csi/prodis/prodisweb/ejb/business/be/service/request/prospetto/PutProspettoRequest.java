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
import it.csi.prodis.prodisweb.lib.dto.prospetto.Prospetto;

public class PutProspettoRequest implements BaseRequest {

	private final Prospetto prospetto;
	private final boolean flagBozza;

	/**
	 * Constructor
	 * 
	 * @param prospetto the prospetto
	 */
	public PutProspettoRequest(Prospetto prospetto, boolean flagBozza) {
		this.prospetto = prospetto;
		this.flagBozza = flagBozza;
	}

	/**
	 * @return the prospetto
	 */
	public Prospetto getProspetto() {
		return prospetto;
	}

	public boolean isFlagBozza() {
		return flagBozza;
	}

}
