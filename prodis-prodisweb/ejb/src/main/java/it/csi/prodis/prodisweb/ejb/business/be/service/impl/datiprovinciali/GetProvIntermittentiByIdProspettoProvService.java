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
package it.csi.prodis.prodisweb.ejb.business.be.service.impl.datiprovinciali;

import java.util.List;

import it.csi.prodis.prodisweb.ejb.business.be.dad.DatiProvincialiDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.datiprovinciali.GetProvIntermittentiByIdProspettoProvRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.datiprovinciali.GetProvIntermittentiByIdProspettoProvResponse;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProvIntermittenti;


/**
 * Retrieves an elenco compensazioni by its idprospetto
 */
public class GetProvIntermittentiByIdProspettoProvService
		extends BaseDatiProvincialiService<GetProvIntermittentiByIdProspettoProvRequest, GetProvIntermittentiByIdProspettoProvResponse> {

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the configuration helper
	 * @param datiProvincialiDad    the dati provinciali DAD
	 */
	public GetProvIntermittentiByIdProspettoProvService(ConfigurationHelper configurationHelper,
			DatiProvincialiDad datiProvincialiDad) {
		super(configurationHelper, datiProvincialiDad);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getIdProspettoProv(), "idProspettoProv");
	}

	@Override
	protected void execute() {
		List<ProvIntermittenti> list = datiProvincialiDad.getProvIntermittentiByIdProspettoProv(request.getIdProspettoProv());
		response.setProvIntermittentis(list);
	}

}
