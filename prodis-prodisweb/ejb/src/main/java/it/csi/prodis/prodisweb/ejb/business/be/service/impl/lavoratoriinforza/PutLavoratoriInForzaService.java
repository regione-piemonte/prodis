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
package it.csi.prodis.prodisweb.ejb.business.be.service.impl.lavoratoriinforza;

import java.util.ArrayList;
import java.util.List;

import it.csi.prodis.prodisweb.ejb.business.be.dad.LavoratoriInForzaDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.lavoratoriinforza.PutLavoratoriInForzaRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.lavoratoriinforza.PutLavoratoriInForzaResponse;
import it.csi.prodis.prodisweb.ejb.exception.NotFoundException;
import it.csi.prodis.prodisweb.ejb.util.ValidatorLavoratore;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.dto.ApiError;
import it.csi.prodis.prodisweb.lib.dto.prospetto.LavoratoriInForza;

public class PutLavoratoriInForzaService extends BaseLavoratoriInForzaService<PutLavoratoriInForzaRequest, PutLavoratoriInForzaResponse> {
	/**
	 * Constructor
	 * 
	 * @param configurationHelper the helper for the configuration
	 * @param datiProvincialiDad        the DAD for the datiProvinciali
	 */
	public PutLavoratoriInForzaService(ConfigurationHelper configurationHelper, LavoratoriInForzaDad lavoratoriInForzaDad) {
		super(configurationHelper, lavoratoriInForzaDad);
	}

	@Override
	protected void execute() {
		LavoratoriInForza lavoratoriInForza = request.getLavoratoriInForza();
		
		List<ApiError> apiErrors = new ArrayList<ApiError>();
		ValidatorLavoratore valida = new ValidatorLavoratore(lavoratoriInForzaDad);
		valida.validaLavoratore(lavoratoriInForza, apiErrors);
		
		List<ApiError> warningsDaEliminare = new ArrayList<ApiError>();
		if (request.getFlagWarning()) {
			for (int i=0; i<apiErrors.size(); i++) {
				if (apiErrors.get(i).getCode().contains("W")) {
					warningsDaEliminare.add(apiErrors.get(i));
				}
			}
		}
		for (int i=0; i<warningsDaEliminare.size(); i++) {
			apiErrors.remove(warningsDaEliminare.get(i));
		}
		
		if (apiErrors == null || apiErrors.size() == 0) {
			if (lavoratoriInForza.getId()!=null) {
				lavoratoriInForza = lavoratoriInForzaDad.updateSingleLavoratoriInForza(
						lavoratoriInForza, lavoratoriInForza.getId(), "S");
			} else {
				throw new NotFoundException("LavoratoriInForza");
			}
		}
		if (apiErrors != null && apiErrors.size() > 0) {
			response.setApiErrors(apiErrors);
		}
		response.setLavoratoriInForza(lavoratoriInForza);
	}
}
