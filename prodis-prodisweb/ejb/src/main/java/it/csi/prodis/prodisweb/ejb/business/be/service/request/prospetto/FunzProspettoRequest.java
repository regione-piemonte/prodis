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
package it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto;

import it.csi.prodis.prodisweb.ejb.business.be.service.request.base.BaseRequest;

public class FunzProspettoRequest implements BaseRequest {

	private final Long idProspetto;

	public FunzProspettoRequest(Long idProspetto) {
		this.idProspetto = idProspetto;
	}

	public Long getIdProspetto() {
		return idProspetto;
	}

}
