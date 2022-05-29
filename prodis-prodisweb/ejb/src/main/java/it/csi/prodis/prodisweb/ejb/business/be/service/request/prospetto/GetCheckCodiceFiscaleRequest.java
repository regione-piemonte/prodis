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

public class GetCheckCodiceFiscaleRequest implements BaseRequest {

	private final String cf;

	public GetCheckCodiceFiscaleRequest(String cf) {
		this.cf = cf;
	}

	public String getCheckCodiceFiscale() {
		return cf;
	}
}
