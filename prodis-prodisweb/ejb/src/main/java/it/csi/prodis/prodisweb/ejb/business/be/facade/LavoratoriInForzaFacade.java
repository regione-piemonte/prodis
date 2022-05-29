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

import it.csi.prodis.prodisweb.ejb.business.be.dad.LavoratoriInForzaDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.lavoratoriinforza.DeleteLavoratoriInForzaService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.lavoratoriinforza.DownloadProspettoLavoratoriInForzaService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.lavoratoriinforza.DownloadProvinciaLavoratoriInForzaService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.lavoratoriinforza.PostLavoratoriInForzaService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.lavoratoriinforza.PutLavoratoriInForzaService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.lavoratoriinforza.UploadProspettoLavoratoriInForzaService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.lavoratoriinforza.UploadProvinciaLavoratoriInForzaService;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.lavoratoriinforza.DeleteLavoratoriInForzaRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.lavoratoriinforza.DownloadLavoratoriInForzaRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.lavoratoriinforza.PostLavoratoriInForzaRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.lavoratoriinforza.PutLavoratoriInForzaRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.lavoratoriinforza.UploadLavoratoriInForzaRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.lavoratoriinforza.DeleteLavoratoriInForzaResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.lavoratoriinforza.DownloadLavoratoriInForzaResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.lavoratoriinforza.PostLavoratoriInForzaResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.lavoratoriinforza.PutLavoratoriInForzaResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.lavoratoriinforza.UploadLavoratoriInForzaResponse;
import it.csi.prodis.prodisweb.lib.dto.FileHolder;
import it.csi.prodis.prodisweb.lib.dto.prospetto.LavoratoriInForza;


/**
 * Facade for the /common path
 */
@Stateless
public class LavoratoriInForzaFacade extends BaseFacade {

	@Inject
	private LavoratoriInForzaDad lavoratoriInForzaDad;

	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PostLavoratoriInForzaResponse postLavoratoriInForza(LavoratoriInForza lavoratoriInForza, Boolean flagWarning) {
		return executeService(new PostLavoratoriInForzaRequest(lavoratoriInForza, flagWarning),
				new PostLavoratoriInForzaService(configurationHelper, lavoratoriInForzaDad));
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PutLavoratoriInForzaResponse putLavoratoriInForza(LavoratoriInForza lavoratoriInForza, Boolean flagWarning) {
		return executeService(new PutLavoratoriInForzaRequest(lavoratoriInForza, flagWarning),
				new PutLavoratoriInForzaService(configurationHelper, lavoratoriInForzaDad));
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public DeleteLavoratoriInForzaResponse deleteLavoratoriInForza(Long idLavoratoriInForza) {
		return executeService(new DeleteLavoratoriInForzaRequest(idLavoratoriInForza),
				new DeleteLavoratoriInForzaService(configurationHelper, lavoratoriInForzaDad));
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public UploadLavoratoriInForzaResponse uploadProspettoLavoratoriInForza(Long idProspetto, FileHolder fileHolder) {
		return executeService(new UploadLavoratoriInForzaRequest(idProspetto, fileHolder),
				new UploadProspettoLavoratoriInForzaService(configurationHelper, lavoratoriInForzaDad));
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public UploadLavoratoriInForzaResponse uploadProvinciaLavoratoriInForza(Long idProspettoProv, FileHolder fileHolder) {
		return executeService(new UploadLavoratoriInForzaRequest(idProspettoProv, fileHolder),
				new UploadProvinciaLavoratoriInForzaService(configurationHelper, lavoratoriInForzaDad));
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public DownloadLavoratoriInForzaResponse downloadProspettoLavoratoriInForza(Long idProspetto) {
		return executeService(new DownloadLavoratoriInForzaRequest(idProspetto),
				new DownloadProspettoLavoratoriInForzaService(configurationHelper, lavoratoriInForzaDad));
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public DownloadLavoratoriInForzaResponse downloadProvinciaLavoratoriInForza(Long idProspettoProv) {
		return executeService(new DownloadLavoratoriInForzaRequest(idProspettoProv),
				new DownloadProvinciaLavoratoriInForzaService(configurationHelper, lavoratoriInForzaDad));
	}

}
