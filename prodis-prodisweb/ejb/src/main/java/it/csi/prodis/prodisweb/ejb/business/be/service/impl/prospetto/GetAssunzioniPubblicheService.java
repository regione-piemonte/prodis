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

import java.util.List;

import it.csi.prodis.prodisweb.ejb.business.be.dad.ProspettoDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto.GetAssunzioniPubblicheRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto.GetAssunzioniPubblicheResponse;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.dto.prospetto.AssPubbliche;

/**
 * Retrieves an testataOrdine by its id
 */
public class GetAssunzioniPubblicheService
		extends BaseProspettoService<GetAssunzioniPubblicheRequest, GetAssunzioniPubblicheResponse> {

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad    the testataOrdine DAD
	 */
	public GetAssunzioniPubblicheService(ConfigurationHelper configurationHelper, ProspettoDad prospettoDad) {
		super(configurationHelper, prospettoDad);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getId(), "id");
	}

	@Override
	protected void execute() {
		List<AssPubbliche> listAssunzioniPubblicheDelProspetto = prospettoDad.getAssunzioniPubbliche(request.getId());
		response.setProspetto(listAssunzioniPubblicheDelProspetto);
	}

}
