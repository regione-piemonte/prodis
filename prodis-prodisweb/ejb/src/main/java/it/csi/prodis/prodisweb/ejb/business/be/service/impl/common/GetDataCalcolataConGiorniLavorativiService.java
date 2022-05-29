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
package it.csi.prodis.prodisweb.ejb.business.be.service.impl.common;

import java.util.Date;

import it.csi.prodis.prodisweb.ejb.business.be.dad.CommonDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.common.GetDataCalcolataConGiorniLavorativiRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.common.GetDataCalcolataConGiorniLavorativiResponse;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.util.ProdisDateUtils;
import it.csi.prodis.prodisweb.lib.util.ProdisUtility;

/**
 * Retrieves an testataOrdine by its id
 */
public class GetDataCalcolataConGiorniLavorativiService extends
		BaseCommonService<GetDataCalcolataConGiorniLavorativiRequest, GetDataCalcolataConGiorniLavorativiResponse> {

	public GetDataCalcolataConGiorniLavorativiService(ConfigurationHelper configurationHelper, CommonDad commonDad) {
		super(configurationHelper, commonDad);
	}

	@Override
	protected void execute() {
		if (ProdisUtility.isNotVoid(request.getDataInput())
				&& ProdisUtility.isNotVoid(request.getNumeroGiorniLavorativi())) {
			Long numeroGiorniDaSommare = request.getNumeroGiorniLavorativi();
			Date dataCalcolata = request.getDataInput();
			for (int i = 0; i <= numeroGiorniDaSommare; i++) {
				dataCalcolata = ProdisDateUtils.aggiungiGiorniAData(dataCalcolata, 1);
				if (ProdisUtility.isGiornoFestivo(dataCalcolata)) {
					// devo incrementare la somma di 1 per recuperare il giorno festivo
					numeroGiorniDaSommare++;
				}
			}
			response.setLaDataCalcolataOutput(dataCalcolata);
		}
	}

}
