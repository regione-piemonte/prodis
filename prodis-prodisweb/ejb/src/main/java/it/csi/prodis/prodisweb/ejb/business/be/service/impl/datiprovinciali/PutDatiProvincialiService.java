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

import java.util.ArrayList;
import java.util.List;

import it.csi.prodis.prodisweb.ejb.business.be.dad.DatiProvincialiDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.datiprovinciali.PutDatiProvincialiRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.datiprovinciali.PutDatiProvincialiResponse;
import it.csi.prodis.prodisweb.ejb.util.ValidatorDatiProvinciali;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.dto.ApiError;
import it.csi.prodis.prodisweb.lib.dto.prospetto.DatiProvinciali;

public class PutDatiProvincialiService
		extends BaseDatiProvincialiService<PutDatiProvincialiRequest, PutDatiProvincialiResponse> {
	/**
	 * Constructor
	 * 
	 * @param configurationHelper the helper for the configuration
	 * @param datiProvincialiDad  the DAD for the datiProvinciali
	 */
	public PutDatiProvincialiService(ConfigurationHelper configurationHelper, DatiProvincialiDad datiProvincialiDad) {
		super(configurationHelper, datiProvincialiDad);
	}

	@Override
	protected void execute() {

		DatiProvinciali datiProvinciali = request.getDatiProvinciali();

		if (datiProvinciali.getnCateProtForzaCntDis() == null
				|| "".equalsIgnoreCase(datiProvinciali.getnCateProtForzaCntDis())) {
			datiProvinciali.setnCateProtForzaCntDis("0");
		}
		if (datiProvinciali.getnCateProtForzaEsubero() == null
				|| "".equalsIgnoreCase(datiProvinciali.getnCateProtForzaEsubero())) {
			datiProvinciali.setnCateProtForzaEsubero("0");
		}
		boolean flagBozza = request.isFlagBozza();

		List<ApiError> apiErrors = new ArrayList<>();
		ValidatorDatiProvinciali valida = new ValidatorDatiProvinciali(datiProvincialiDad);
		valida.validaInBozza(datiProvinciali, apiErrors);

		if (!flagBozza) {
			apiErrors = valida.validaConfermaEProsegui(datiProvinciali, apiErrors);
		}

		boolean flagValidazione = false;
		if (!flagBozza) {
			flagValidazione = true;
		}
		if (apiErrors == null || apiErrors.isEmpty()) {
			datiProvincialiDad.modificaFlagConfermatoQ2(datiProvinciali.getId().longValue(), flagBozza,
					flagValidazione);
			datiProvinciali = datiProvincialiDad.insertQuadro2Completo(datiProvinciali, flagBozza);

		} else {
			datiProvincialiDad.modificaFlagConfermatoQ2(datiProvinciali.getId().longValue(), flagBozza, false);
			response.setApiErrors(apiErrors);
		}

		response.setDatiProvinciali(datiProvinciali);
	}
}
