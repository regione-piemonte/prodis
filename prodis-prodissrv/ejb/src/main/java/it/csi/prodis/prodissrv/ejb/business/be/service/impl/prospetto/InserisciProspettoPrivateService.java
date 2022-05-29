/*-
 * ========================LICENSE_START=================================
 * PRODIS Servizi - EJB submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodissrv.ejb.business.be.service.impl.prospetto;

import java.util.List;

import it.csi.prodis.prodissrv.ejb.business.be.dad.ProspettoDad;
import it.csi.prodis.prodissrv.ejb.business.be.service.request.prospetto.InserisciProspettoRequest;
import it.csi.prodis.prodissrv.ejb.business.be.service.response.prospetto.InserisciProspettoResponse;
import it.csi.prodis.prodissrv.ejb.entity.ProDImportErrori;
import it.csi.prodis.prodissrv.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodissrv.lib.dto.error.MsgProdis;
import it.csi.prodis.prodissrv.lib.dto.prospetto.Prospetto;

public class InserisciProspettoPrivateService extends BaseProspettoService<InserisciProspettoRequest, InserisciProspettoResponse> {
	
	public InserisciProspettoPrivateService(ConfigurationHelper configurationHelper, ProspettoDad prospettoDad) {
		super(configurationHelper, prospettoDad);
	}

	@Override
	protected void execute() {
		log.debug("[InserisciProspettoPrivateService::execute]", "BEGIN");
		
		List<ProDImportErrori> errors = request.getErrors();
		Prospetto prospetto = request.getProspetto();

		if (errors.size() > 0) {
			response.addApiError(MsgProdis.SP0003.getError());

			for (ProDImportErrori error : errors) {
				prospettoDad.insertImportErrori(error);
			}
			return;
		}

		Long idProspetto = prospettoDad.insertProspettoCompleto(prospetto);

		if (prospetto.getCodiceComunicazionePreced() != null) {
			prospettoDad.updateStatoProspettoPrecedente(prospetto, request.getTipoComunicazione());
		}

		response.setIdProspetto(idProspetto);
		log.debug("[RiceviProspettoDaSpicomService::execute]", "END");
	}

}
