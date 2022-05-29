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

import it.csi.prodis.prodisweb.ejb.business.be.dad.PartTimeDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.parttime.DeletePartTimeRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.parttime.DeletePartTimeResponse;
import it.csi.prodis.prodisweb.ejb.exception.NotFoundException;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.dto.prospetto.PartTime;

public class DeletePartTimeService extends BasePartTimeService<DeletePartTimeRequest, DeletePartTimeResponse> {
	/**
	 * Constructor
	 * 
	 * @param configurationHelper the helper for the configuration
	 * @param partTimeDad        the DAD for the partTime
	 */
	public DeletePartTimeService(ConfigurationHelper configurationHelper, PartTimeDad partTimeDad) {
		super(configurationHelper, partTimeDad);
	}

	@Override
	protected void execute() {
		
		Long idPartTime = request.getIdPartTime();
		
		PartTime ptDeleted = null;
		if (idPartTime!=null) {
			ptDeleted = partTimeDad.deleteSinglePartTime(idPartTime);
		} else {
			throw new NotFoundException("partTime");
		}

		response.setPartTime(ptDeleted);
	}
	
	
}
