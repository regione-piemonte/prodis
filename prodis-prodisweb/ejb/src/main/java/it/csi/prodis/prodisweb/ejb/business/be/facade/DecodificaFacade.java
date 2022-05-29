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

import java.util.Date;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import it.csi.prodis.prodisweb.ejb.business.be.dad.DecodificaDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.decodifica.GetAtecofinService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.decodifica.GetCcnlService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.decodifica.GetComuneService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.decodifica.GetProvinciaService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.decodifica.GetStatoProspettoService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.decodifica.PostAssunzioneProtettaDecodificaService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.decodifica.PostAtecofinDecodificaService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.decodifica.PostCategoriaEscluseDecodificaService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.decodifica.PostCausaSospensioneDecodificaService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.decodifica.PostCcnlDecodificaService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.decodifica.PostComuneDecodificaService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.decodifica.PostContrattiDecodificaService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.decodifica.PostDichiaranteDecodificaService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.decodifica.PostQualificaDecodificaService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.decodifica.PostRegioneDecodificaService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.decodifica.PostSoggettiDecodificaService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.decodifica.PostStatiEsteriDecodificaService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.decodifica.PostStatoConcessioneDecodificaService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.decodifica.PostTipologiaLavoratoreDecodificaService;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.decodifica.GetAtecofinRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.decodifica.GetCcnlRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.decodifica.GetComuneRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.decodifica.GetProvinciaRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.decodifica.GetStatoProspettoRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.decodifica.PostAssunzioneProtettaDecodificaRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.decodifica.PostAtecofinDecodificaRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.decodifica.PostCategoriaEscluseDecodificaRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.decodifica.PostCausaSospensioneDecodificaRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.decodifica.PostCcnlDecodificaRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.decodifica.PostComuneDecodificaRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.decodifica.PostContrattiDecodificaRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.decodifica.PostDichiaranteDecodificaRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.decodifica.PostQualificaDecodificaRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.decodifica.PostRegioneDecodificaRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.decodifica.PostSoggettiDecodificaRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.decodifica.PostStatiEsteriDecodificaRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.decodifica.PostStatoConcessioneDecodificaRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.decodifica.PostTipologiaLavoratoreDecodificaRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.decodifica.GetAtecofinResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.decodifica.GetCcnlResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.decodifica.GetComuneResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.decodifica.GetProvinciaResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.decodifica.GetStatoProspettoResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.decodifica.PostDecodificaGenericaResponse;
import it.csi.prodis.prodisweb.lib.dto.common.DecodificaGenerica;

/**
 * Facade for the /decodifica path
 */
@Stateless
public class DecodificaFacade extends BaseFacade {
	// Injection point
	@Inject
	private DecodificaDad decodificaDad;

	/**
	 * Gets the Comuni
	 * 
	 * @return the comuni
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public GetComuneResponse getComune(Long idProvincia, String codComuneMin, String dsProTComune, Date data) {
		return executeService(new GetComuneRequest(idProvincia, codComuneMin, dsProTComune, data),
				new GetComuneService(configurationHelper, decodificaDad));
	}

	/**
	 * Gets the Province
	 * 
	 * @return the province
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public GetProvinciaResponse getProvincia() {
		return executeService(new GetProvinciaRequest(), new GetProvinciaService(configurationHelper, decodificaDad));
	}

	/**
	 * Gets the state prospects
	 * 
	 * @return the state prospects
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public GetStatoProspettoResponse getStatoProspetto() {
		return executeService(new GetStatoProspettoRequest(),
				new GetStatoProspettoService(configurationHelper, decodificaDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public GetCcnlResponse getCcnl() {
		return executeService(new GetCcnlRequest(), new GetCcnlService(configurationHelper, decodificaDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public GetAtecofinResponse getAtecofin() {
		return executeService(new GetAtecofinRequest(), new GetAtecofinService(configurationHelper, decodificaDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PostDecodificaGenericaResponse postCcnlDecodifica(DecodificaGenerica filtro) {
		return executeService(new PostCcnlDecodificaRequest(filtro),
				new PostCcnlDecodificaService(configurationHelper, decodificaDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PostDecodificaGenericaResponse postAtecofinDecodifica(DecodificaGenerica filtro) {
		return executeService(new PostAtecofinDecodificaRequest(filtro),
				new PostAtecofinDecodificaService(configurationHelper, decodificaDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PostDecodificaGenericaResponse postStatiEsteriDecodifica(DecodificaGenerica filtro) {
		return executeService(new PostStatiEsteriDecodificaRequest(filtro),
				new PostStatiEsteriDecodificaService(configurationHelper, decodificaDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PostDecodificaGenericaResponse postContrattiDecodifica(DecodificaGenerica filtro) {
		return executeService(new PostContrattiDecodificaRequest(filtro),
				new PostContrattiDecodificaService(configurationHelper, decodificaDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PostDecodificaGenericaResponse postAssunzioneProtettaDecodifica(DecodificaGenerica filtro) {
		return executeService(new PostAssunzioneProtettaDecodificaRequest(filtro),
				new PostAssunzioneProtettaDecodificaService(configurationHelper, decodificaDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PostDecodificaGenericaResponse postDichiaranteDecodifica(DecodificaGenerica filtro) {
		return executeService(new PostDichiaranteDecodificaRequest(filtro),
				new PostDichiaranteDecodificaService(configurationHelper, decodificaDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PostDecodificaGenericaResponse postCategoriaEscluseDecodifica(DecodificaGenerica filtro) {
		return executeService(new PostCategoriaEscluseDecodificaRequest(filtro),
				new PostCategoriaEscluseDecodificaService(configurationHelper, decodificaDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PostDecodificaGenericaResponse postCausaSospensioneDecodifica(DecodificaGenerica filtro) {
		return executeService(new PostCausaSospensioneDecodificaRequest(filtro),
				new PostCausaSospensioneDecodificaService(configurationHelper, decodificaDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PostDecodificaGenericaResponse postQualificaDecodifica(DecodificaGenerica filtro) {
		return executeService(new PostQualificaDecodificaRequest(filtro),
				new PostQualificaDecodificaService(configurationHelper, decodificaDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PostDecodificaGenericaResponse postTipologiaLavoratoreDecodifica(DecodificaGenerica filtro) {
		return executeService(new PostTipologiaLavoratoreDecodificaRequest(filtro),
				new PostTipologiaLavoratoreDecodificaService(configurationHelper, decodificaDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PostDecodificaGenericaResponse postComuneDecodifica(DecodificaGenerica filtro) {
		return executeService(new PostComuneDecodificaRequest(filtro),
				new PostComuneDecodificaService(configurationHelper, decodificaDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PostDecodificaGenericaResponse postRegioneDecodifica(DecodificaGenerica filtro) {
		return executeService(new PostRegioneDecodificaRequest(filtro),
				new PostRegioneDecodificaService(configurationHelper, decodificaDad));
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PostDecodificaGenericaResponse postSoggettiDecodifica(DecodificaGenerica filtro) {
		return executeService(new PostSoggettiDecodificaRequest(filtro),
				new PostSoggettiDecodificaService(configurationHelper, decodificaDad));
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PostDecodificaGenericaResponse postStatoConcessioneDecodifica(DecodificaGenerica filtro) {
		return executeService(new PostStatoConcessioneDecodificaRequest(filtro),
				new PostStatoConcessioneDecodificaService(configurationHelper, decodificaDad));
	}
}
