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
package it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto;

import it.csi.prodis.prodisweb.ejb.business.be.service.response.base.BaseGetResponse;

/**
 * Response for reading Prospetto by its id.
 */
public class CheckPassaggioQ3Response extends BaseGetResponse<Boolean> {

	private Boolean successo;

	public Boolean isSuccesso() {
		return successo;
	}

	public void setSuccesso(Boolean successo) {
		this.successo = successo;
	}

	@Override
	protected Boolean getEntity() {
		return successo;
	}

}
