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
package it.csi.prodis.prodissrv.ejb.business.be.service.response.prospetto;

import java.util.ArrayList;
import java.util.List;

import it.csi.prodis.prodissrv.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.prodis.prodissrv.lib.dto.prospetto.TestataProspetto;

/**
 * The Class PostFilterTestataProspettoResponse.
 */
public class PostFilterTestataProspettoResponse extends BaseGetResponse<List<TestataProspetto>> {

	/** The model. */
	private List<TestataProspetto> testataProspettos = new ArrayList<>();

	public List<TestataProspetto> getProspettos() {
		return testataProspettos;
	}

	public void setProspettos(List<TestataProspetto> testataProspettos) {
		this.testataProspettos = testataProspettos;
	}

	@Override
	protected List<TestataProspetto> getEntity() {
		return testataProspettos;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getSimpleName() + " [testataProspettos=").append(testataProspettos)
				.append(", apiErrors=").append(getApiErrors()).append("]");
		return builder.toString();
	}

}
