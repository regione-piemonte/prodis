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

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import it.csi.prodis.prodisweb.ejb.business.be.dad.CompensazioniDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.compensazioni.ConfermaTornaRiepilogoService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.compensazioni.DeleteCompensazioniService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.compensazioni.PostCompensazioniService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.compensazioni.PutCompensazioniService;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.compensazioni.ConfermaTornaRiepilogoRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.compensazioni.DeleteCompensazioniRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.compensazioni.PostCompensazioniRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.compensazioni.PutCompensazioniRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.compensazioni.ConfermaTornaRiepilogoResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.compensazioni.DeleteCompensazioniResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.compensazioni.PostCompensazioniResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.compensazioni.PutCompensazioniResponse;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProvCompensazioni;

/**
 * Facade for the /common path
 */
@Stateless
public class CompensazioniFacade extends BaseFacade {

	@Inject
	private CompensazioniDad compensazioniDad;

	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PostCompensazioniResponse postCompensazioni(ProvCompensazioni compensazioni) {
		return executeService(new PostCompensazioniRequest(compensazioni),
				new PostCompensazioniService(configurationHelper, compensazioniDad));
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PutCompensazioniResponse putCompensazioni(ProvCompensazioni compensazioni) {
		return executeService(new PutCompensazioniRequest(compensazioni),
				new PutCompensazioniService(configurationHelper, compensazioniDad));
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public DeleteCompensazioniResponse deleteCompensazioni(Long idCompensazioni) {
		return executeService(new DeleteCompensazioniRequest(idCompensazioni),
				new DeleteCompensazioniService(configurationHelper, compensazioniDad));
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public ConfermaTornaRiepilogoResponse confermaTornaRiepilogo(Long idProspetto) {
		return executeService(new ConfermaTornaRiepilogoRequest(idProspetto),
				new ConfermaTornaRiepilogoService(configurationHelper, compensazioniDad));
	}
	

}
