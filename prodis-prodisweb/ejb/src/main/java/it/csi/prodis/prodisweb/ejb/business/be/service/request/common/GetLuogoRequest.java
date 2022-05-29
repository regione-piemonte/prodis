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

/**
 * The Class GetLuogoRequest.
 */
public class GetLuogoRequest implements BaseRequest {

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GetLuogoRequest []");
		return builder.toString();
	}
}
