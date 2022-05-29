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
package it.csi.prodis.prodisweb.ejb.business.be.service.impl.prospetto;

import org.apache.commons.lang.StringUtils;

import it.csi.prodis.prodisweb.ejb.business.be.dad.ProspettoDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto.GetCheckCodiceFiscaleRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto.GetCheckCodiceFiscaleResponse;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.util.ProdisUtility;

public class GetCheckCodiceFiscaleService
		extends BaseProspettoService<GetCheckCodiceFiscaleRequest, GetCheckCodiceFiscaleResponse> {

	public GetCheckCodiceFiscaleService(ConfigurationHelper configurationHelper, ProspettoDad prospettoDad) {
		super(configurationHelper, prospettoDad);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getCheckCodiceFiscale(), "codiceFiscale");
	}

	@Override
	protected void execute() {
		String esito = "";
		if (StringUtils.isNotEmpty(request.getCheckCodiceFiscale())) {
			if (request.getCheckCodiceFiscale().length() == 11) {
				esito = ProdisUtility.validateCodiceFiscaleOPartitaIva(request.getCheckCodiceFiscale());
			} else if (request.getCheckCodiceFiscale().length() == 16) {
				esito = prospettoDad.getCheckCodiceFiscale(request.getCheckCodiceFiscale());
			}
		}
		/*
		 * RESTITUISCE - 0 se errato - 1 se corretto
		 */
		response.setResultCheck(esito);
	}
}
