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
package it.csi.prodis.prodisweb.ejb.business.be.service.impl.parttime;

import java.util.ArrayList;
import java.util.List;

import it.csi.prodis.prodisweb.ejb.business.be.dad.IntermittentiDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.parttime.PostIntermittentiRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.parttime.PostIntermittentiResponse;
import it.csi.prodis.prodisweb.ejb.exception.NotFoundException;
import it.csi.prodis.prodisweb.ejb.util.ValidatorIntermittenti;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.dto.ApiError;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProvIntermittenti;

public class PostIntermittentiService extends BaseIntermittentiService<PostIntermittentiRequest, PostIntermittentiResponse> {
	/**
	 * Constructor
	 * 
	 * @param configurationHelper the helper for the configuration
	 * @param intermittentiDad        the DAD for the intermittenti
	 */
	public PostIntermittentiService(ConfigurationHelper configurationHelper, IntermittentiDad intermittentiDad) {
		super(configurationHelper, intermittentiDad);
	}

	@Override
	protected void execute() {
		
		ProvIntermittenti intermittenti = request.getIntermittenti();
		Long idPartTime = request.getIdPartTime();

		List<ApiError> apiErrors = new ArrayList<ApiError>();
		ValidatorIntermittenti valida = new ValidatorIntermittenti(intermittentiDad);
		valida.valida(intermittenti, apiErrors);
				
		if (apiErrors == null || apiErrors.size() == 0) {
			if (intermittenti.getId()==null) {
				intermittenti = intermittentiDad.insertSingleIntermittenti(intermittenti, intermittenti.getIdProspettoProv(), idPartTime);
			} else {
				throw new NotFoundException("ProvIntermittenti");
			}
		}
		if (apiErrors != null && apiErrors.size() > 0) {
			response.setApiErrors(apiErrors);
		}
		response.setIntermittenti(intermittenti);
	}
}
