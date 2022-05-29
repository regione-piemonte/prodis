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

import java.util.Date;

import it.csi.prodis.prodisweb.ejb.business.be.service.request.base.BaseRequest;

public class GetDataCalcolataConGiorniLavorativiRequest implements BaseRequest {

	private final Date dataInput;
	private final Long numeroGiorniLavorativi;

	public GetDataCalcolataConGiorniLavorativiRequest(Date dataInput, Long numeroGiorniLavorativi) {
		this.dataInput = dataInput;
		this.numeroGiorniLavorativi = numeroGiorniLavorativi;
	}

	public Date getDataInput() {
		return dataInput;
	}

	public Long getNumeroGiorniLavorativi() {
		return numeroGiorniLavorativi;
	}
}
