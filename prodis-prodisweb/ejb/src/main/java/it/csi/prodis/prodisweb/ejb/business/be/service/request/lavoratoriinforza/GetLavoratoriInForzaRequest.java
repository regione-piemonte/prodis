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
package it.csi.prodis.prodisweb.ejb.business.be.service.request.lavoratoriinforza;

import it.csi.prodis.prodisweb.ejb.business.be.service.request.base.BaseRequest;

public class GetLavoratoriInForzaRequest implements BaseRequest {

	private final Integer idLavoratore;

	public GetLavoratoriInForzaRequest(Integer idLavoratore) {
		this.idLavoratore = idLavoratore;
	}

	public Integer getIdLavoratore() {
		return idLavoratore;
	}


	
}
