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
package it.csi.prodis.prodisweb.ejb.business.be.service.response.common;

import it.csi.prodis.prodisweb.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.prodis.prodisweb.lib.dto.common.DecodificaGenerica;

/**
 * The Class GetRuoloResponse.
 */
public class GetParametroResponse extends BaseGetResponse<DecodificaGenerica> {

	/** The model. */
	private DecodificaGenerica parametro = null;

	public DecodificaGenerica getParametro() {
		return parametro;
	}

	public void setParametro(DecodificaGenerica parametro) {
		this.parametro = parametro;
	}

	@Override
	protected DecodificaGenerica getEntity() {
		return parametro;
	}

}
