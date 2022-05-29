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
package it.csi.prodis.prodisweb.ejb.business.be.service.impl.decodifica;

import it.csi.prodis.prodisweb.ejb.business.be.dad.DecodificaDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.decodifica.GetCcnlRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.decodifica.GetCcnlResponse;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;

/**
 * Gets the Provincias
 */
public class GetCcnlService extends BaseDecodificaService<GetCcnlRequest, GetCcnlResponse> {

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the helper for the configuration
	 * @param decodificaDad       the DAD for the decodifica
	 */
	public GetCcnlService(ConfigurationHelper configurationHelper, DecodificaDad decodificaDad) {
		super(configurationHelper, decodificaDad);
	}

	@Override
	protected void execute() {
		response.setCcnls(decodificaDad.getCcnl(null));
	}

}
