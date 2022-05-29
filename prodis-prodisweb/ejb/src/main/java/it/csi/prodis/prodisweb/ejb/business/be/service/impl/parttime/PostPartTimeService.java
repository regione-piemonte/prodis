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

import it.csi.prodis.prodisweb.ejb.business.be.dad.PartTimeDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.parttime.PostPartTimeRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.parttime.PostPartTimeResponse;
import it.csi.prodis.prodisweb.ejb.exception.NotFoundException;
import it.csi.prodis.prodisweb.ejb.util.ValidatorPartTime;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.dto.ApiError;
import it.csi.prodis.prodisweb.lib.dto.prospetto.PartTime;

public class PostPartTimeService extends BasePartTimeService<PostPartTimeRequest, PostPartTimeResponse> {
	/**
	 * Constructor
	 * 
	 * @param configurationHelper the helper for the configuration
	 * @param datiProvincialiDad        the DAD for the datiProvinciali
	 */
	public PostPartTimeService(ConfigurationHelper configurationHelper, PartTimeDad partTimeDad) {
		super(configurationHelper, partTimeDad);
	}

	@Override
	protected void execute() {
		
		PartTime partTime = request.getPartTime();
		Long idIntermittenti = request.getIdIntermittenti();
		List<ApiError> apiErrors = new ArrayList<ApiError>();
		ValidatorPartTime valida = new ValidatorPartTime(partTimeDad);
		valida.valida(partTime, apiErrors);
		
		if (apiErrors == null || apiErrors.size() == 0) {
			if (partTime.getId()==null) {
				partTime = partTimeDad.insertSinglePartTime(partTime, partTime.getIdProspettoProv().intValue(), idIntermittenti);
			} else {
				throw new NotFoundException("PartTime");
			}
		}
		if (apiErrors != null && apiErrors.size() > 0) {
			response.setApiErrors(apiErrors);
		}
		response.setPartTime(partTime);
		
	}

}
