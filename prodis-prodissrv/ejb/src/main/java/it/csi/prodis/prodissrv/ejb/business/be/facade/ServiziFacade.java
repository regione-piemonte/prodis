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
package it.csi.prodis.prodissrv.ejb.business.be.facade;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.jboss.ejb3.annotation.TransactionTimeout;

import it.csi.prodis.prodissrv.ejb.business.be.dad.ProspettoDad;
import it.csi.prodis.prodissrv.ejb.business.be.service.impl.prospetto.InitRiceviProspettoDaSpicomService;
import it.csi.prodis.prodissrv.ejb.business.be.service.impl.prospetto.InserisciProspettoPrivateService;
import it.csi.prodis.prodissrv.ejb.business.be.service.impl.prospetto.PostDettaglioProspettoCompletoService;
import it.csi.prodis.prodissrv.ejb.business.be.service.impl.prospetto.PostFilterTestataProspettoService;
import it.csi.prodis.prodissrv.ejb.business.be.service.impl.prospetto.PostFindByPkPdfProspettoService;
import it.csi.prodis.prodissrv.ejb.business.be.service.impl.prospetto.RiceviProspettoDaSpicomService;
import it.csi.prodis.prodissrv.ejb.business.be.service.impl.prospetto.StoreProcedureEseguiCalcoliService;
import it.csi.prodis.prodissrv.ejb.business.be.service.request.prospetto.InserisciProspettoRequest;
import it.csi.prodis.prodissrv.ejb.business.be.service.request.prospetto.PostDettaglioProspettoCompletoRequest;
import it.csi.prodis.prodissrv.ejb.business.be.service.request.prospetto.PostFilterTestataProspettoRequest;
import it.csi.prodis.prodissrv.ejb.business.be.service.request.prospetto.PostFindByPkPdfProspettoRequest;
import it.csi.prodis.prodissrv.ejb.business.be.service.request.prospetto.RiceviProspettoDaSpicomRequest;
import it.csi.prodis.prodissrv.ejb.business.be.service.request.prospetto.StoreProcedureEseguiCalcoliRequest;
import it.csi.prodis.prodissrv.ejb.business.be.service.response.prospetto.InitRicezioneProspettoDaSpicomResponse;
import it.csi.prodis.prodissrv.ejb.business.be.service.response.prospetto.InserisciProspettoResponse;
import it.csi.prodis.prodissrv.ejb.business.be.service.response.prospetto.PostDettaglioProspettoCompletoResponse;
import it.csi.prodis.prodissrv.ejb.business.be.service.response.prospetto.PostFilterTestataProspettoResponse;
import it.csi.prodis.prodissrv.ejb.business.be.service.response.prospetto.PostFindByPkPdfProspettoResponse;
import it.csi.prodis.prodissrv.ejb.business.be.service.response.prospetto.RiceviProspettoDaSpicomResponse;
import it.csi.prodis.prodissrv.ejb.business.be.service.response.prospetto.StoreProcedureEseguiCalcoliResponse;
import it.csi.prodis.prodissrv.ejb.entity.ProDImportErrori;
import it.csi.prodis.prodissrv.ejb.util.ConstantsProdis;
import it.csi.prodis.prodissrv.lib.dto.prospetto.FilterServiziProdis;
import it.csi.prodis.prodissrv.lib.dto.prospetto.Prospetto;
import it.csi.spicom.dto.ComunicazioneProspettoDisabiliDTO;

/**
 * Facade for the /servizi path
 */
@Stateless
public class ServiziFacade extends BaseFacade {

	@Inject
	private ProspettoDad prospettoDad;

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public PostFilterTestataProspettoResponse findByFilterTestataProspetto(FilterServiziProdis filterTestataProspetto) {
		return executeService(new PostFilterTestataProspettoRequest(filterTestataProspetto),
				new PostFilterTestataProspettoService(configurationHelper, prospettoDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PostDettaglioProspettoCompletoResponse getDettaglioProspettoCompleto(
			FilterServiziProdis filterTestataProspetto) {
		return executeService(new PostDettaglioProspettoCompletoRequest(filterTestataProspetto),
				new PostDettaglioProspettoCompletoService(configurationHelper, prospettoDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PostFindByPkPdfProspettoResponse findByPkPdfProspetto(FilterServiziProdis filterTestataProspetto) {
		return executeService(new PostFindByPkPdfProspettoRequest(filterTestataProspetto),
				new PostFindByPkPdfProspettoService(configurationHelper, prospettoDad));
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RiceviProspettoDaSpicomResponse riceviProspettoDaSpicom(
			ComunicazioneProspettoDisabiliDTO comunicazioneProspettoSpicom) {
		return executeService(new RiceviProspettoDaSpicomRequest(comunicazioneProspettoSpicom),
				new RiceviProspettoDaSpicomService(configurationHelper, prospettoDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public InitRicezioneProspettoDaSpicomResponse initRicezioneComunicazione(ComunicazioneProspettoDisabiliDTO comunicazioneProspettoSpicom) {
		return executeService(new RiceviProspettoDaSpicomRequest(comunicazioneProspettoSpicom),
				new InitRiceviProspettoDaSpicomService(configurationHelper, prospettoDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@TransactionTimeout(value = 600, unit = TimeUnit.SECONDS)
	@Lock(LockType.WRITE)
	public InserisciProspettoResponse inserisciProspetto(
			Prospetto prospetto, List<ProDImportErrori> errors, String tipoComunicazione) {
		return executeService(new InserisciProspettoRequest(prospetto, errors, tipoComunicazione),
				new InserisciProspettoPrivateService(configurationHelper, prospettoDad));
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	@Lock(LockType.WRITE)
	public StoreProcedureEseguiCalcoliResponse eseguiCalcoliStoreProcedure(Long idProspetto) {
		return executeService(new StoreProcedureEseguiCalcoliRequest(idProspetto,
				ConstantsProdis.UTENTE_IMPORT_PROSPETTO_DA_SPICOM, true),
				new StoreProcedureEseguiCalcoliService(configurationHelper, prospettoDad));
	}

}
