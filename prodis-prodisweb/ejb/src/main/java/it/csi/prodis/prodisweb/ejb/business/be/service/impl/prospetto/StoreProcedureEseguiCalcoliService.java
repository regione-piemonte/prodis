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
import it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto.StoreProcedureEseguiCalcoliRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto.StoreProcedureEseguiCalcoliResponse;
import it.csi.prodis.prodisweb.ejb.entity.EsitoStoreProcedure;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;

public class StoreProcedureEseguiCalcoliService
		extends BaseProspettoService<StoreProcedureEseguiCalcoliRequest, StoreProcedureEseguiCalcoliResponse> {

	public StoreProcedureEseguiCalcoliService(ConfigurationHelper configurationHelper, ProspettoDad prospettoDad) {
		super(configurationHelper, prospettoDad);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getIdProspetto(), "idProspetto");
		checkNotNull(request.getCfUtenteAggiornamento(), "cfUtenteAggiornamento");
	}

	@Override
	protected void execute() {
		EsitoStoreProcedure loEsito = prospettoDad.storeProcedureEseguiCalcoli(request.getIdProspetto(),
				request.getCfUtenteAggiornamento(), request.isSoloScoperture());
		response.setEsito(loEsito);
	}

}
