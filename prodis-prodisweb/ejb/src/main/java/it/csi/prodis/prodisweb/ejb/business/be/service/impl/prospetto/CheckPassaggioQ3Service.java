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


import java.util.ArrayList;
import java.util.List;

import it.csi.prodis.prodisweb.ejb.business.be.dad.DatiProvincialiDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.datiprovinciali.BaseDatiProvincialiService;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto.CheckPassaggioQ3Request;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto.CheckPassaggioQ3Response;
import it.csi.prodis.prodisweb.ejb.util.ValidatorConfermaDatiProvinciali;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.dto.ApiError;
import it.csi.prodis.prodisweb.lib.dto.prospetto.Prospetto;
import it.csi.prodis.prodisweb.lib.dto.prospetto.VistaElencoProvQ2;

public class CheckPassaggioQ3Service
		extends BaseDatiProvincialiService<CheckPassaggioQ3Request, CheckPassaggioQ3Response> {

	public CheckPassaggioQ3Service(ConfigurationHelper configurationHelper, DatiProvincialiDad datiProvincialiDad) {
		super(configurationHelper, datiProvincialiDad);
	}

	@Override
	protected void execute() {
		
		Long idProspetto = request.getIdProspetto();
		
		ValidatorConfermaDatiProvinciali valida = new ValidatorConfermaDatiProvinciali(datiProvincialiDad);
		List<ApiError> apiErrors = new ArrayList<ApiError>();
		
		Prospetto prospetto = datiProvincialiDad.getProspetto(idProspetto);
		
		List<VistaElencoProvQ2> elencoProvince =  datiProvincialiDad.getElencoProvQ2ByIdProspetto(request.getIdProspetto());
		valida.validaMessaggioAvviso( prospetto , apiErrors, elencoProvince);
		valida.checkWarningPerScoperture( prospetto, elencoProvince, apiErrors);
		
		if (apiErrors == null || apiErrors.size() == 0) {
			response.setSuccesso(true);
		} else {
			response.setApiErrors(apiErrors);
			response.setSuccesso(false);
		}
		
	}
}
