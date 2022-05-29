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
package it.csi.prodis.prodisweb.ejb.business.be.service.request.common;

import it.csi.prodis.prodisweb.ejb.business.be.service.request.base.BaseRequest;

public class GetParametroRequest implements BaseRequest {

	private final String nomeParametro;

	public GetParametroRequest(String nomeParametro) {
		this.nomeParametro = nomeParametro;
	}

	public String getNomeParametro() {
		return nomeParametro;
	}
}
