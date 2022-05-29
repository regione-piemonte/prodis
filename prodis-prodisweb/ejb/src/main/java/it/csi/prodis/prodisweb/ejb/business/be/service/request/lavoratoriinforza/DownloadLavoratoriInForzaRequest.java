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

public class DownloadLavoratoriInForzaRequest implements BaseRequest {

	private final Long id;

	public DownloadLavoratoriInForzaRequest(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}


	
}
