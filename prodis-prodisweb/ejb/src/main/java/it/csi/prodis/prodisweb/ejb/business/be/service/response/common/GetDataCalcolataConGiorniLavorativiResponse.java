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
package it.csi.prodis.prodisweb.ejb.business.be.service.response.common;

import java.util.Date;

import it.csi.prodis.prodisweb.ejb.business.be.service.response.base.BaseGetResponse;

/**
 * The Class GetRuoloResponse.
 */
public class GetDataCalcolataConGiorniLavorativiResponse extends BaseGetResponse<Date> {

	/** The model. */
	private Date laDataCalcolataOutput = null;

	@Override
	protected Date getEntity() {
		return laDataCalcolataOutput;
	}

	public Date getLaDataCalcolataOutput() {
		return laDataCalcolataOutput;
	}

	public void setLaDataCalcolataOutput(Date laDataCalcolataOutput) {
		this.laDataCalcolataOutput = laDataCalcolataOutput;
	}

}
