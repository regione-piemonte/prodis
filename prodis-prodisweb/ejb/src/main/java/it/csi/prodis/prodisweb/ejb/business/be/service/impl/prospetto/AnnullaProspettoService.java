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
import it.csi.prodis.prodisweb.ejb.util.ValidatorAnnullamento;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.dto.ApiError;
import it.csi.prodis.prodisweb.lib.dto.error.MsgProdis;

public class AnnullaProspettoService extends BaseProspettoService<FunzProspettoRequest, PutProspettoResponse> {

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the helper for the configuration
	 * @param prospettoDad        the DAD for the prospetto
	 */
	public AnnullaProspettoService(ConfigurationHelper configurationHelper, ProspettoDad prospettoDad) {
		super(configurationHelper, prospettoDad);
	}

	@Override
	protected void execute() {

		Long idProspetto = request.getIdProspetto();

		List<ApiError> apiErrors = new ArrayList<ApiError>();
		ValidatorAnnullamento valida = new ValidatorAnnullamento(prospettoDad);
		valida.valida(idProspetto, apiErrors);

		if (apiErrors == null || apiErrors.size() == 0)  {
			RitornoEseguiOperazione annullato = null;
			if (idProspetto!=null) {
				annullato = prospettoDad.annullaProspetto(idProspetto);
			} else {
				throw new NotFoundException("Prospetto");
			}

			response.setProspetto(annullato.getNuovoProspetto());
			
			if(annullato.getEsito() != null && annullato.getEsito() < 0) {
				apiErrors.add(new ApiError(MsgProdis.PROANNE0001.getCode(), annullato.getMessaggio()));
			}
		}
		
		if (apiErrors != null && apiErrors.size() > 0) {
			response.setApiErrors(apiErrors);
		}

	}
}
