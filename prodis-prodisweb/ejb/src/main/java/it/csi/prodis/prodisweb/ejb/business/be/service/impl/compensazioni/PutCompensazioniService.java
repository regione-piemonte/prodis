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
package it.csi.prodis.prodisweb.ejb.business.be.service.impl.compensazioni;

import java.util.ArrayList;
import java.util.List;

import it.csi.prodis.prodisweb.ejb.business.be.dad.CompensazioniDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.compensazioni.PutCompensazioniRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.compensazioni.PutCompensazioniResponse;
import it.csi.prodis.prodisweb.ejb.exception.NotFoundException;
import it.csi.prodis.prodisweb.ejb.util.ValidatorCompensazioni;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.dto.ApiError;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProvCompensazioni;

public class PutCompensazioniService extends BaseCompensazioniService<PutCompensazioniRequest, PutCompensazioniResponse> {
	/**
	 * Constructor
	 * 
	 * @param configurationHelper the helper for the configuration
	 * @param compensazioniDad        the DAD for the compensazioni
	 */
	public PutCompensazioniService(ConfigurationHelper configurationHelper, CompensazioniDad compensazioniDad) {
		super(configurationHelper, compensazioniDad);
	}

	@Override
	protected void execute() {
		ProvCompensazioni compensazioni = request.getCompensazioni();

		List<ApiError> apiErrors = new ArrayList<ApiError>();
		ValidatorCompensazioni valida = new ValidatorCompensazioni(compensazioniDad);
		valida.valida(compensazioni, apiErrors);

		if (apiErrors == null || apiErrors.size() == 0) {
			if (compensazioni.getId()!=null) {
				compensazioni = compensazioniDad.updateCompensazioni(compensazioni, compensazioni.getId().longValue());
			} else {
				throw new NotFoundException("Compensazioni");
			}
		}
		response.setApiErrors(apiErrors);
		response.setCompensazioni(compensazioni);
	}
}
