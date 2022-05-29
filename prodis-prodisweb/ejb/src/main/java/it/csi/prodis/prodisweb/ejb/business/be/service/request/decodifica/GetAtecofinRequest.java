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

/**
 * The Class GetAtecofinRequest.
 */
public class GetAtecofinRequest implements BaseRequest {

	public GetAtecofinRequest() {
		super();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GetAtecofinRequest []");
		return builder.toString();
	}
}
