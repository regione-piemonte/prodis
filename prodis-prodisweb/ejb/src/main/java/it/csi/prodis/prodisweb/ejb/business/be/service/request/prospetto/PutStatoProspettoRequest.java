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

public class PutStatoProspettoRequest implements BaseRequest {

	private final Long idProspetto;
	private final Long idStatoProspetto;

	public PutStatoProspettoRequest(Long idProspetto, Long idStatoProspetto) {
		this.idProspetto = idProspetto;
		this.idStatoProspetto = idStatoProspetto;
	}

	public Long getIdStatoProspetto() {
		return idStatoProspetto;
	}

	public Long getIdProspetto() {
		return idProspetto;
	}

}
