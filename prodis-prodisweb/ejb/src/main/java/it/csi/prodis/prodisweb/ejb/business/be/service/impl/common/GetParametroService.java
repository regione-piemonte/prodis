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
package it.csi.prodis.prodisweb.ejb.business.be.service.impl.common;

import it.csi.prodis.prodisweb.ejb.business.be.dad.CommonDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.common.GetParametroRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.common.GetParametroResponse;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.dto.common.DecodificaGenerica;

/**
 * Retrieves an testataOrdine by its id
 */
public class GetParametroService extends BaseCommonService<GetParametroRequest, GetParametroResponse> {

	public GetParametroService(ConfigurationHelper configurationHelper, CommonDad commonDad) {
		super(configurationHelper, commonDad);
	}

	@Override
	protected void execute() {
		DecodificaGenerica ilParametro = commonDad.getParametroByNome(request.getNomeParametro());
		response.setParametro(ilParametro);

	}

}
