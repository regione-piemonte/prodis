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
import it.csi.prodis.prodisweb.ejb.business.be.service.request.parttime.PutPartTimeRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.parttime.PutPartTimeResponse;
import it.csi.prodis.prodisweb.ejb.exception.NotFoundException;
import it.csi.prodis.prodisweb.ejb.util.ValidatorPartTime;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.dto.ApiError;
import it.csi.prodis.prodisweb.lib.dto.prospetto.PartTime;

public class PutPartTimeService extends BasePartTimeService<PutPartTimeRequest, PutPartTimeResponse> {
	/**
	 * Constructor
	 * 
	 * @param configurationHelper the helper for the configuration
	 * @param partTimeDad        the DAD for the partTime
	 */
	public PutPartTimeService(ConfigurationHelper configurationHelper, PartTimeDad partTimeDad) {
		super(configurationHelper, partTimeDad);
	}

	@Override
	protected void execute() {
		
		PartTime partTime = request.getPartTime();
		List<ApiError> apiErrors = new ArrayList<ApiError>();
		ValidatorPartTime valida = new ValidatorPartTime(partTimeDad);
		valida.valida(partTime, apiErrors);
		if (apiErrors == null || apiErrors.size() == 0) {
			if (partTime.getId()!=null) {
				partTime = partTimeDad.updateSinglePartTime(partTime, partTime.getId());
			} else {
				throw new NotFoundException("partTime");
			}
		}
		if (apiErrors != null && apiErrors.size() > 0) {
			response.setApiErrors(apiErrors);
		}
		response.setPartTime(partTime);
	}
	
	
}
