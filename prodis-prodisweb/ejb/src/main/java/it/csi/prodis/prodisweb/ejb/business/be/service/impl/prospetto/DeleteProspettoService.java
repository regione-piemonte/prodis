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
import it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto.DeleteProspettoRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto.DeleteProspettoResponse;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;

public class DeleteProspettoService extends BaseProspettoService<DeleteProspettoRequest, DeleteProspettoResponse> {

	public DeleteProspettoService(ConfigurationHelper configurationHelper, ProspettoDad prospettoDad) {
		super(configurationHelper, prospettoDad);
	}

	@Override
	protected void execute() {
		Long idProspetto = request.getId();
		prospettoDad.deleteProspetto(idProspetto);
	}
}
