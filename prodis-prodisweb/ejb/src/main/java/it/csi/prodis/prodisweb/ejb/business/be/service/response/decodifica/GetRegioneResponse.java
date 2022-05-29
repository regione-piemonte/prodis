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
package it.csi.prodis.prodisweb.ejb.business.be.service.response.decodifica;

import java.util.ArrayList;
import java.util.List;

import it.csi.prodis.prodisweb.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Regione;

/**
 * The Class GetRegioneResponse.
 */
public class GetRegioneResponse extends BaseGetResponse<List<Regione>> {

	private List<Regione> regiones = new ArrayList<>();
	
	
	
	public List<Regione> getRegiones() {
		return regiones;
	}

	public void setRegiones(List<Regione> regiones) {
		this.regiones = regiones;
	}

	@Override
	protected List<Regione> getEntity() {
		return regiones;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getSimpleName() + " [regiones=").append(regiones).append(", apiErrors=").append(getApiErrors())
				.append("]");
		return builder.toString();
	}

}
