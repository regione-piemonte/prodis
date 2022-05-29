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
package it.csi.prodis.prodisweb.ejb.business.be.facade;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import it.csi.prodis.prodisweb.ejb.business.be.dad.ProspettoDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.silp.GetAziendaService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.silp.GetLavoratoreService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.silp.GetSediService;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.silp.GetAziendaRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.silp.GetLavoratoreRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.silp.GetSediRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.silp.GetAziendaResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.silp.GetLavoratoreResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.silp.GetSediResponse;

/**
 * Facade for the /silp path
 */
@Stateless
public class SilpFacade extends BaseFacade {

	@Inject
	private ProspettoDad prospettoDad;

	/**
	 * Gets the Azienda
	 * 
	 * @return the azienda
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public GetAziendaResponse getAzienda(String codiceFiscale) {
		return executeService(new GetAziendaRequest(codiceFiscale), new GetAziendaService(configurationHelper));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public GetLavoratoreResponse getLavoratore(String codiceFiscale) {
		return executeService(new GetLavoratoreRequest(codiceFiscale),
				new GetLavoratoreService(configurationHelper, prospettoDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public GetSediResponse getSedi(String idAzienda) {
		return executeService(new GetSediRequest(idAzienda), new GetSediService(configurationHelper));
	}

}
