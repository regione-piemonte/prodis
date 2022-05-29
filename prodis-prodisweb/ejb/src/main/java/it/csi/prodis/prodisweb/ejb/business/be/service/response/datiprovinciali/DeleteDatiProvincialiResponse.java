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

import it.csi.prodis.prodisweb.ejb.business.be.service.response.base.BaseGetResponse;


public class DeleteDatiProvincialiResponse extends BaseGetResponse<Boolean> {

	private Boolean cancellato;

	@Override
	protected Boolean getEntity() {
		return cancellato;
	}

	public Boolean isCancellato() {
		return cancellato;
	}

	public void setCancellato(Boolean cancellato) {
		this.cancellato = cancellato;
	}

}
