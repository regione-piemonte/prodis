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
package it.csi.prodis.prodisweb.ejb.business.be.service.request.silp;

import it.csi.prodis.prodisweb.ejb.business.be.service.request.base.BaseRequest;

/**
 * The Class GetAziendaRequest.
 */
public class GetLavoratoreRequest implements BaseRequest {

	private String codiceFiscale;

	public GetLavoratoreRequest(String codiceFiscale) {
		super();
		this.codiceFiscale = codiceFiscale;
	}

	/**
	 * @return the codiceFiscale
	 */
	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GetAziendaRequest []");
		return builder.toString();
	}

}
