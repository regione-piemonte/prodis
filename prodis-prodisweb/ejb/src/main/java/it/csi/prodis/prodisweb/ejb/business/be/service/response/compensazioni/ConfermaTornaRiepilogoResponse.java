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
package it.csi.prodis.prodisweb.ejb.business.be.service.response.compensazioni;


import it.csi.prodis.prodisweb.ejb.business.be.service.response.base.BaseGetResponse;

public class ConfermaTornaRiepilogoResponse extends BaseGetResponse<Boolean> {

	private Boolean confermato;

	public Boolean isConfermato() {
		return confermato;
	}

	public void setConfermato(Boolean confermato) {
		this.confermato = confermato;
	}

	@Override
	protected Boolean getEntity() {
		return confermato;
	}

}
