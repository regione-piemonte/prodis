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

import it.csi.prodis.prodisweb.ejb.business.be.dad.ProspettoDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto.FunzProspettoRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto.PutProspettoResponse;
import it.csi.prodis.prodisweb.ejb.entity.RitornoEseguiOperazione;
import it.csi.prodis.prodisweb.ejb.exception.NotFoundException;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.dto.ApiError;
import it.csi.prodis.prodisweb.lib.dto.error.MsgProdis;

public class DuplicaProspettoService extends BaseProspettoService<FunzProspettoRequest, PutProspettoResponse> {

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the helper for the configuration
	 * @param prospettoDad        the DAD for the prospetto
	 */
	public DuplicaProspettoService(ConfigurationHelper configurationHelper, ProspettoDad prospettoDad) {
		super(configurationHelper, prospettoDad);
	}

	@Override
	protected void execute() {
		
		Long idProspetto = request.getIdProspetto();
		
		RitornoEseguiOperazione duplica = null;
		if (idProspetto!=null) {
			duplica = prospettoDad.duplicaProspetto(idProspetto);
		} else {
			throw new NotFoundException("Prospetto");
		}

		response.setProspetto(duplica.getNuovoProspetto());
		
		if(duplica.getEsito() != null && duplica.getEsito() < 0) {
			response.addApiError(new ApiError( MsgProdis.PRODUPE0001.getCode(), duplica.getMessaggio()));
		}
	}
}
