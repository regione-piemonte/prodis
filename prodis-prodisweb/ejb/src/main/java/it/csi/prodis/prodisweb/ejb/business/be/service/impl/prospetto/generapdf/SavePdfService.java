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
package it.csi.prodis.prodisweb.ejb.business.be.service.impl.prospetto.generapdf;

import it.csi.prodis.prodisweb.ejb.business.be.dad.ProspettoDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.prospetto.BaseProspettoService;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto.SavePdfRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto.SavePdfResponse;
import it.csi.prodis.prodisweb.ejb.exception.NotFoundException;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;

public class SavePdfService extends BaseProspettoService<SavePdfRequest, SavePdfResponse> {

	public SavePdfService(ConfigurationHelper configurationHelper, ProspettoDad prospettoDad) {
		super(configurationHelper, prospettoDad);
	}

	@Override
	protected void execute() {
		final String methodName = "SavePdfService - execute";

		Long idProspetto = request.getIdProspetto();
		if (idProspetto == null) {
			throw new NotFoundException("Prospetto");
		}

		try {
			byte[] bytes = request.getBytes();
			log.info(methodName, "idProspetto: " + idProspetto + " - bytes: " + bytes.length);
			String operazione = prospettoDad.inserisciPdfInDatabase(idProspetto, bytes);
			log.info(methodName, "operazione: " + operazione);

		} catch (Exception e) {
			log.error(methodName, "idProspetto: " + idProspetto + " - " + e.getMessage(), e);
		} finally {
			log.info(methodName, "idProspetto: " + idProspetto + " - fine");
		}
	}

}
