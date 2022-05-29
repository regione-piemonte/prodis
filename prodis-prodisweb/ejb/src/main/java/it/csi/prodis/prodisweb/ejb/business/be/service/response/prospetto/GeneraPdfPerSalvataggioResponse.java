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

public class GeneraPdfPerSalvataggioResponse extends BaseResponse {

	private byte[] bytes;

	

	public byte[] getBytes() {
		return bytes;
	}



	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}


	@Override
	protected Response composeOwnResponse() {
		return Response.ok().build();
	}

}
