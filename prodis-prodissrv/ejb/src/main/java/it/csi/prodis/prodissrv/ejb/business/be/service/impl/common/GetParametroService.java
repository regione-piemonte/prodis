/*-
 * ========================LICENSE_START=================================
 * PRODIS Servizi - EJB submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodissrv.ejb.business.be.service.impl.common;

import it.csi.prodis.prodissrv.ejb.business.be.dad.CommonDad;
import it.csi.prodis.prodissrv.ejb.business.be.service.request.common.GetParametroRequest;
import it.csi.prodis.prodissrv.ejb.business.be.service.response.common.GetParametroResponse;
import it.csi.prodis.prodissrv.ejb.util.conf.ConfigurationHelper;

/**
 * Retrieves an testataOrdine by its id
 */
public class GetParametroService extends BaseCommonService<GetParametroRequest, GetParametroResponse> {

	public GetParametroService(ConfigurationHelper configurationHelper, CommonDad commonDad) {
		super(configurationHelper, commonDad);
	}

	@Override
	protected void execute() {
		String ilParametro = commonDad.getParametroByNome(request.getNomeParametro());
		response.setParametro(ilParametro);

	}

}
