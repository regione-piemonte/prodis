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

import javax.ws.rs.core.Response;

import it.csi.prodis.prodisweb.ejb.business.be.service.response.base.BaseResponse;

public class SavePdfResponse extends BaseResponse {

	protected Response composeOwnResponse() {
		return Response.noContent().build();
	}

}
