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
import it.csi.prodis.prodisweb.ejb.business.be.dad.DatiProvincialiDad;
import it.csi.prodis.prodisweb.ejb.business.be.dad.PostiLavoroDispDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.datiprovinciali.DeleteDatiProvincialiService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.datiprovinciali.GetCategorieEscluseByIdProspettoProvService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.datiprovinciali.GetConfermaProvinceService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.datiprovinciali.GetDatiProvincialiByIdProspettoProvService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.datiprovinciali.GetElencoProvQ2ByIdProspettoService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.datiprovinciali.GetElencoScopertureByIdProspettoService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.datiprovinciali.GetLavoratoriInForzaByIdProspettoProvService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.datiprovinciali.GetPartTimeByIdProspettoProvService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.datiprovinciali.GetProspettoProvinciaByIdProspettoService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.datiprovinciali.GetProvIntermittentiByIdProspettoProvService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.datiprovinciali.PostProspettoProvinciaService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.datiprovinciali.PutDatiProvincialiService;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.datiprovinciali.DeleteDatiProvincialiRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.datiprovinciali.GetCategorieEscluseByIdProspettoProvRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.datiprovinciali.GetConfermaProvinceRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.datiprovinciali.GetDatiProvincialiByIdProspettoProvRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.datiprovinciali.GetElencoProvQ2ByIdProspettoRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.datiprovinciali.GetElencoScopertureByIdProspettoRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.datiprovinciali.GetLavoratoriInForzaByIdProspettoProvRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.datiprovinciali.GetPartTimeByIdProspettoProvRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.datiprovinciali.GetProspettoProvinciaByIdProspettoRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.datiprovinciali.GetProvIntermittentiByIdProspettoProvRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.datiprovinciali.PostProspettoProvinciaRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.datiprovinciali.PutDatiProvincialiRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.datiprovinciali.DeleteDatiProvincialiResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.datiprovinciali.GetCategorieEscluseByIdProspettoProvResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.datiprovinciali.GetConfermaProvinceResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.datiprovinciali.GetDatiProvincialiByIdProspettoProvResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.datiprovinciali.GetElencoProvQ2ByIdProspettoResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.datiprovinciali.GetElencoScopertureByIdProspettoResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.datiprovinciali.GetLavoratoriInForzaByIdProspettoProvResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.datiprovinciali.GetPartTimeByIdProspettoProvResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.datiprovinciali.GetProspettoProvinciaByIdProspettoResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.datiprovinciali.GetProvIntermittentiByIdProspettoProvResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.datiprovinciali.PostProspettoProvinciaResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.datiprovinciali.PutDatiProvincialiResponse;
import it.csi.prodis.prodisweb.lib.dto.prospetto.DatiProvinciali;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProspettoProvincia;

/**
 * Facade for the /common path
 */
@Stateless
public class DatiProvincialiFacade extends BaseFacade {

	@Inject
	private DatiProvincialiDad datiProvincialiDad;

	@Inject
	private CompensazioniDad compensazioniDad;

	@Inject
	private PostiLavoroDispDad postiLavoroDispDad;

	/**
	 * get dati provinciali by idProspettoProv
	 * 
	 * @param idProspettoProv
	 * @return the datiProvinciali
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public GetDatiProvincialiByIdProspettoProvResponse getDatiProvincialiByIdProspettoProv(Long idProspettoProv) {
		return executeService(new GetDatiProvincialiByIdProspettoProvRequest(idProspettoProv),
				new GetDatiProvincialiByIdProspettoProvService(configurationHelper, datiProvincialiDad));
	}

	/**
	 * get dati VistaElencoQ2 by idProspetto
	 * 
	 * @param idProspetto
	 * @return the list of VistaElencoQ2
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public GetElencoProvQ2ByIdProspettoResponse getElencoProvQ2ByIdProspetto(Long idProspetto) {
		return executeService(new GetElencoProvQ2ByIdProspettoRequest(idProspetto),
				new GetElencoProvQ2ByIdProspettoService(configurationHelper, datiProvincialiDad));
	}

	/**
	 * get dati ProspettoProvincia by idProspetto
	 * 
	 * @param idProspetto
	 * @return the list of ProspettoProvincia
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public GetProspettoProvinciaByIdProspettoResponse getProspettoProvinciaByIdProspetto(Long idProspetto) {
		return executeService(new GetProspettoProvinciaByIdProspettoRequest(idProspetto),
				new GetProspettoProvinciaByIdProspettoService(configurationHelper, datiProvincialiDad, compensazioniDad,
						postiLavoroDispDad));
	}

	/**
	 * get dati ElencoScoperture by idProspetto
	 * 
	 * @param idProspetto
	 * @return the list of ElencoScoperture
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public GetElencoScopertureByIdProspettoResponse getElencoScoperture(Long idProspetto) {
		return executeService(new GetElencoScopertureByIdProspettoRequest(idProspetto),
				new GetElencoScopertureByIdProspettoService(configurationHelper, datiProvincialiDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PostProspettoProvinciaResponse postDatiProvinciali(ProspettoProvincia prospettoProvincia) {
		return executeService(new PostProspettoProvinciaRequest(prospettoProvincia),
				new PostProspettoProvinciaService(configurationHelper, datiProvincialiDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PutDatiProvincialiResponse putDatiProvinciali(DatiProvinciali datiProvinciali, boolean flagBozza) {
		return executeService(new PutDatiProvincialiRequest(datiProvinciali, flagBozza),
				new PutDatiProvincialiService(configurationHelper, datiProvincialiDad));
	}

	/**
	 * get dati CategorieEscluse by idProspettoProv
	 * 
	 * @param idProspettoprov
	 * @return the list of CategorieEscluse
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public GetCategorieEscluseByIdProspettoProvResponse getCategorieEscluse(Long idProspettoProv) {
		return executeService(new GetCategorieEscluseByIdProspettoProvRequest(idProspettoProv),
				new GetCategorieEscluseByIdProspettoProvService(configurationHelper, datiProvincialiDad));
	}

	/**
	 * get dati Elenco ProvIntermittenti by idProspettoprov
	 * 
	 * @param idProspettoProv
	 * @return the list of ProvIntermittenti
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public GetProvIntermittentiByIdProspettoProvResponse getProvIntermittenti(Long idProspettoProv) {
		return executeService(new GetProvIntermittentiByIdProspettoProvRequest(idProspettoProv),
				new GetProvIntermittentiByIdProspettoProvService(configurationHelper, datiProvincialiDad));
	}

	/**
	 * get dati Elenco PartTime by idProspettoprov
	 * 
	 * @param idProspettoProv
	 * @return the list of PartTime
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public GetPartTimeByIdProspettoProvResponse getPartTime(Long idProspettoProv) {
		return executeService(new GetPartTimeByIdProspettoProvRequest(idProspettoProv),
				new GetPartTimeByIdProspettoProvService(configurationHelper, datiProvincialiDad));
	}

	/**
	 * get dati Elenco Lavoratori In Forza by idProspettoprov
	 * 
	 * @param idProspettoProv
	 * @return the list of LavoratoriInForza
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public GetLavoratoriInForzaByIdProspettoProvResponse getLavoratoriInForza(Long idProspettoProv) {
		return executeService(new GetLavoratoriInForzaByIdProspettoProvRequest(idProspettoProv),
				new GetLavoratoriInForzaByIdProspettoProvService(configurationHelper, datiProvincialiDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public GetConfermaProvinceResponse getConfermaProvince(Long idProspetto) {
		return executeService(new GetConfermaProvinceRequest(idProspetto),
				new GetConfermaProvinceService(configurationHelper, datiProvincialiDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Lock(LockType.WRITE)
	public DeleteDatiProvincialiResponse deleteDatiProvinciali(Long idProspettoProv) {
		return executeService(new DeleteDatiProvincialiRequest(idProspettoProv),
				new DeleteDatiProvincialiService(configurationHelper, datiProvincialiDad));
	}

}
