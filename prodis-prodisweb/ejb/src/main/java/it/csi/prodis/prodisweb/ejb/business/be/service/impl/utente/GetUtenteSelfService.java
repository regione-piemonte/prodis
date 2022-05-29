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
package it.csi.prodis.prodisweb.ejb.business.be.service.impl.utente;

import it.csi.prodis.prodisweb.ejb.business.be.service.impl.base.BaseService;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.utente.GetUtenteSelfRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.utente.GetUtenteSelfResponse;
import it.csi.prodis.prodisweb.ejb.util.ProdisThreadLocalContainer;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.dto.Utente;

/**
 * Retrieves an utente by its id
 */
public class GetUtenteSelfService extends BaseService<GetUtenteSelfRequest, GetUtenteSelfResponse> {

	/**
	 * Constructor
	 * @param configurationHelper the configuration helper
	 * @param utenteDad the utente DAD
	 */
	public GetUtenteSelfService(ConfigurationHelper configurationHelper) {
		super(configurationHelper);
	}

	@Override
	protected void execute() {
		Utente utente = ProdisThreadLocalContainer.UTENTE_CONNESSO.get();
		// Read from DB?
		response.setUtente(utente);
	}

}
