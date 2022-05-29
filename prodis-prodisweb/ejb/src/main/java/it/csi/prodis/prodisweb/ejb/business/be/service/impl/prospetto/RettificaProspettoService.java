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

import java.util.ArrayList;
import java.util.List;

import it.csi.prodis.prodisweb.ejb.business.be.dad.ProspettoDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto.FunzProspettoRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto.PutProspettoResponse;
import it.csi.prodis.prodisweb.ejb.entity.RitornoEseguiOperazione;
import it.csi.prodis.prodisweb.ejb.exception.NotFoundException;
import it.csi.prodis.prodisweb.ejb.util.ValidatorRettifica;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.dto.ApiError;
import it.csi.prodis.prodisweb.lib.dto.error.MsgProdis;

public class RettificaProspettoService extends BaseProspettoService<FunzProspettoRequest, PutProspettoResponse> {

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the helper for the configuration
	 * @param prospettoDad        the DAD for the prospetto
	 */
	public RettificaProspettoService(ConfigurationHelper configurationHelper, ProspettoDad prospettoDad) {
		super(configurationHelper, prospettoDad);
	}

	@Override
	protected void execute() {
		
		Long idProspetto = request.getIdProspetto();

		List<ApiError> apiErrors = new ArrayList<ApiError>();
		ValidatorRettifica valida = new ValidatorRettifica(prospettoDad);
		valida.valida(idProspetto, apiErrors);
		
		if (apiErrors == null || apiErrors.size() == 0)  {
			RitornoEseguiOperazione  rettificato = null;
			if (idProspetto!=null) {
				rettificato = prospettoDad.rettificaProspetto(idProspetto);
			} else {
				throw new NotFoundException("Prospetto");
			}

			response.setProspetto(rettificato.getNuovoProspetto());
			if(rettificato.getEsito() != null && rettificato.getEsito() < 0) {
				apiErrors.add(new ApiError(MsgProdis.PRORETE0001.getCode(), rettificato.getMessaggio()));
			}
		}
		
		if (apiErrors != null && apiErrors.size() > 0) {
			response.setApiErrors(apiErrors);
		}
		
	}
}
