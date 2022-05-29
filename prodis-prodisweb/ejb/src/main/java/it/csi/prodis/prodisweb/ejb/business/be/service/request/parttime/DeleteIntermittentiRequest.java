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
package it.csi.prodis.prodisweb.ejb.business.be.service.request.parttime;

import it.csi.prodis.prodisweb.ejb.business.be.service.request.base.BaseRequest;

public class DeleteIntermittentiRequest implements BaseRequest {

	private final Long idIntermittenti;

	public DeleteIntermittentiRequest(Long idIntermittenti) {
		this.idIntermittenti = idIntermittenti;
	}

	public Long getIdIntermittenti() {
		return idIntermittenti;
	}
	
}
