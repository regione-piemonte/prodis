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
package it.csi.prodis.prodisweb.ejb.business.be.service.response.datiprovinciali;


import java.util.List;

import it.csi.prodis.prodisweb.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.prodis.prodisweb.lib.dto.prospetto.VistaElencoProvQ2;


/**
 * Response for reading Prospetto by its id.
 */
public class GetElencoProvQ2ByIdProspettoResponse extends BaseGetResponse<List<VistaElencoProvQ2>> {

	private List<VistaElencoProvQ2> vistaElencoProvQ2;

	
	

	public List<VistaElencoProvQ2> getVistaElencoProvQ2() {
		return vistaElencoProvQ2;
	}




	public void setVistaElencoProvQ2(List<VistaElencoProvQ2> vistaElencoProvQ2) {
		this.vistaElencoProvQ2 = vistaElencoProvQ2;
	}




	@Override
	protected List<VistaElencoProvQ2> getEntity() {
		return vistaElencoProvQ2;
	}

}
