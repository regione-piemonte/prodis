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
package it.csi.prodis.prodisweb.ejb.business.be.service.request.decodifica;

import it.csi.prodis.prodisweb.ejb.business.be.service.request.base.BaseRequest;
import it.csi.prodis.prodisweb.lib.dto.common.DecodificaGenerica;

/**
 * The Class GetCcnlRequest.
 */
public class PostSoggettiDecodificaRequest implements BaseRequest {

	private final DecodificaGenerica filtro;

	public PostSoggettiDecodificaRequest(DecodificaGenerica filtro) {
		this.filtro = filtro;
	}

	/**
	 * @return the ricercaProspetto
	 */
	public DecodificaGenerica getFiltro() {
		return filtro;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PostSoggettiDecodificaRequest []");
		return builder.toString();
	}
}
