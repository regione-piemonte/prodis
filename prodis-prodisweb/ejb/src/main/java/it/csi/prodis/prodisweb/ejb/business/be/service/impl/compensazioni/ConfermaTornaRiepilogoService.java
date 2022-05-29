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
import it.csi.prodis.prodisweb.ejb.business.be.service.request.compensazioni.ConfermaTornaRiepilogoRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.compensazioni.ConfermaTornaRiepilogoResponse;
import it.csi.prodis.prodisweb.ejb.util.ValidatorCompensazioni;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.dto.ApiError;



/**
 * Retrieves an testataOrdine by its id
 */
public class ConfermaTornaRiepilogoService extends BaseCompensazioniService<ConfermaTornaRiepilogoRequest, ConfermaTornaRiepilogoResponse> {

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad    the testataOrdine DAD
	 */
	public ConfermaTornaRiepilogoService(ConfigurationHelper configurationHelper, CompensazioniDad compensazioniDad) {
		super(configurationHelper, compensazioniDad);
	}

	@Override
	protected void execute() {
		
		Long idProspetto= request.getIdProspetto();

		List<ApiError> apiErrors = new ArrayList<ApiError>();
		ValidatorCompensazioni valida = new ValidatorCompensazioni(compensazioniDad);
		valida.validaConsistenzaDati(idProspetto, apiErrors);
		
		if (apiErrors != null && apiErrors.size() > 0) {
			response.setApiErrors(apiErrors);
			response.setConfermato(false);
		} else {
			response.setConfermato(true);
		}
		
	}

	

}
