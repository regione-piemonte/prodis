/*-
 * ========================LICENSE_START=================================
 * PRODIS Servizi - EJB submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodissrv.ejb.business.be.service.response.common;

import it.csi.prodis.prodissrv.ejb.business.be.service.response.base.BaseGetResponse;

/**
 * The Class GetRuoloResponse.
 */
public class GetParametroResponse extends BaseGetResponse<String> {

	/** The model. */
	private String parametro = null;

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	@Override
	protected String getEntity() {
		// TODO Auto-generated method stub
		return parametro;
	}

}
