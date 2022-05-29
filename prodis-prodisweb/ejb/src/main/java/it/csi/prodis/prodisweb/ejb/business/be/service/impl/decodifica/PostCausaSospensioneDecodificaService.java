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
import it.csi.prodis.prodisweb.ejb.business.be.service.request.decodifica.PostCausaSospensioneDecodificaRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.decodifica.PostDecodificaGenericaResponse;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;

/**
 * Gets the contratti decodificas
 */
public class PostCausaSospensioneDecodificaService extends BaseDecodificaService<PostCausaSospensioneDecodificaRequest, PostDecodificaGenericaResponse> {

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the helper for the configuration
	 * @param decodificaDad       the DAD for the decodifica
	 */
	public PostCausaSospensioneDecodificaService(ConfigurationHelper configurationHelper, DecodificaDad decodificaDad) {
		super(configurationHelper, decodificaDad);
	}

	@Override
	protected void execute() {
		
		response.setDecodificaGenericas( decodificaDad.getCausaSospensioneDecodifica(request.getFiltro() ));
	}

}
