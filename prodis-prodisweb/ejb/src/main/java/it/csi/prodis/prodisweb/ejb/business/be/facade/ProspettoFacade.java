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

import java.util.List;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import it.csi.prodis.prodisweb.ejb.business.be.dad.CommonDad;
import it.csi.prodis.prodisweb.ejb.business.be.dad.DatiProvincialiDad;
import it.csi.prodis.prodisweb.ejb.business.be.dad.ProspettoDad;
import it.csi.prodis.prodisweb.ejb.business.be.dad.RiepilogoNazionaleDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.prospetto.AnnullaProspettoService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.prospetto.CheckPassaggioQ3Service;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.prospetto.ConfermaRiepilogoService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.prospetto.DeleteProspettoService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.prospetto.DuplicaProspettoService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.prospetto.GetAssunzioniPubblicheService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.prospetto.GetCheckCodiceFiscaleService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.prospetto.GetCheckScopertureDatoriLavoriPubbliciService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.prospetto.GetProspettoByIdService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.prospetto.PostProspettoService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.prospetto.PostRicercaProspettoService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.prospetto.PutProspettoService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.prospetto.PutStatoProspettoService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.prospetto.ReinviaProspettoService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.prospetto.RettificaProspettoService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.prospetto.SalvaBozzaRiepilogoService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.prospetto.StoreProcedureEseguiCalcoliService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.prospetto.generapdf.CreaHtmlService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.prospetto.generapdf.GeneraPdfPerSalvataggioService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.prospetto.generapdf.GeneraPdfService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.prospetto.generapdf.SavePdfService;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto.CheckPassaggioQ3Request;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto.CreaHtmlRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto.DeleteProspettoRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto.FunzProspettoRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto.GeneraPdfRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto.GetAssunzioniPubblicheRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto.GetCheckCodiceFiscaleRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto.GetCheckScopertureDatoriLavoriPubbliciRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto.GetProspettoByIdRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto.PostConferemaRiepilogoProspettoRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto.PostProspettoRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto.PostRicercaProspettoRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto.PutProspettoRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto.PutStatoProspettoRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto.ReinviaProspettoRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto.SalvaPdfRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto.SavePdfRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto.StoreProcedureEseguiCalcoliRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto.CheckPassaggioQ3Response;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto.CreaHtmlResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto.DeleteProspettoResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto.GeneraPdfPerSalvataggioResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto.GeneraPdfResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto.GetAssunzioniPubblicheResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto.GetCheckCodiceFiscaleResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto.GetCheckScopertureDatoriLavoriPubbliciResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto.GetPropspettoByIdResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto.PostConferemaRiepilogoProspettoResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto.PostProspettoResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto.PostRicercaProspettoResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto.PutProspettoResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto.PutStatoProspettoResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto.ReinviaProspettoResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto.SavePdfResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto.StoreProcedureEseguiCalcoliResponse;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ConfermaRiepilogoProspetto;
import it.csi.prodis.prodisweb.lib.dto.prospetto.Prospetto;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ReinviaProspetto;
import it.csi.prodis.prodisweb.lib.dto.prospetto.RicercaProspetto;

/**
 * Facade for the /common path
 */
@Stateless
public class ProspettoFacade extends BaseFacade {

	@Inject
	private ProspettoDad prospettoDad;
	@Inject
	private DatiProvincialiDad datiProvincialiDad;
	@Inject
	private CommonDad commonDad;

	@Inject
	private RiepilogoNazionaleDad riepilogoNazionaleDad;

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public PostRicercaProspettoResponse getRicercaProspetti(Integer page, Integer limit, String sort, String direction,
			RicercaProspetto ricercaProspetto) {
		return executeService(new PostRicercaProspettoRequest(page, limit, sort, direction, ricercaProspetto),
				new PostRicercaProspettoService(configurationHelper, prospettoDad, commonDad, riepilogoNazionaleDad));
	}

	/**
	 * Posts an Prospetto
	 * 
	 * @param prospetto
	 * @return the prospetto
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PostProspettoResponse postProspetto(Prospetto prospetto, boolean flagBozza) {
		return executeService(new PostProspettoRequest(prospetto, flagBozza),
				new PostProspettoService(configurationHelper, prospettoDad, datiProvincialiDad));
	}

	/**
	 * Put an Prospetto
	 * 
	 * @param prospetto
	 * @return the prospetto
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PutProspettoResponse putProspetto(Prospetto prospetto, boolean flagBozza) {
		if (prospetto.getDatiAzienda().getCognomeReferente() != null
				&& prospetto.getDatiAzienda().getCognomeReferente().endsWith("'")) {
			prospetto.getDatiAzienda().setCognomeReferente(prospetto.getDatiAzienda().getCognomeReferente() + "'");
		}
		return executeService(new PutProspettoRequest(prospetto, flagBozza),
				new PutProspettoService(configurationHelper, prospettoDad, datiProvincialiDad));
	}

	/**
	 * Posts an Prospetto
	 * 
	 * @param prospetto
	 * @return the prospetto
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public GetPropspettoByIdResponse getProspettoById(Long idProspetto) {
		return executeService(new GetProspettoByIdRequest(idProspetto),
				new GetProspettoByIdService(configurationHelper, prospettoDad, riepilogoNazionaleDad));
	}

	/**
	 * Posts an AssunzioniPubbliche
	 * 
	 * @param prospetto
	 * @return the assunzioniPubbliche
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public GetAssunzioniPubblicheResponse getAssunzioniPubblicheByIdProspetto(Long idProspetto) {
		return executeService(new GetAssunzioniPubblicheRequest(idProspetto),
				new GetAssunzioniPubblicheService(configurationHelper, prospettoDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public GetCheckCodiceFiscaleResponse getCheckCodiceFiscale(String codiceFiscale) {
		return executeService(new GetCheckCodiceFiscaleRequest(codiceFiscale),
				new GetCheckCodiceFiscaleService(configurationHelper, prospettoDad));
	}

	/**
	 * Crea l'html per la successiva generazione del Pdf Non deve esserci
	 * transazione, operazioni di sola lettura. Altrimenti se l'operazione impiega
	 * troppo tempo la transazione viene rollbackata e interrotta
	 * 
	 * @param idProspetto
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public CreaHtmlResponse creaHtml(Long idProspetto) {
		return executeService(new CreaHtmlRequest(idProspetto), new CreaHtmlService(configurationHelper, prospettoDad));
	}

	/**
	 * Genera il Pdf a partire dall'html passato come parametro
	 * 
	 * @param idProspetto
	 * @param htmlContent
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public GeneraPdfResponse generaPdf(Long idProspetto, String htmlContent) {
		return executeService(new GeneraPdfRequest(idProspetto, htmlContent),
				new GeneraPdfService(configurationHelper));
	}
	
	
	/**
	 * Salva il Pdf su database
	 * 
	 * @param idProspetto
	 * @param bytes
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public SavePdfResponse savePdf(Long idProspetto, byte[] bytes) {
		return executeService(new SavePdfRequest(idProspetto, bytes),
				new SavePdfService(configurationHelper, prospettoDad));
	}

	/**
	 * Delete an Prospetto
	 * 
	 * @param prospetto
	 * @return the prospetto
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public DeleteProspettoResponse deleteProspetto(Long idProspetto) {
		return executeService(new DeleteProspettoRequest(idProspetto),
				new DeleteProspettoService(configurationHelper, prospettoDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PutProspettoResponse rettificaProspetto(Long idProspetto) {
		return executeService(new FunzProspettoRequest(idProspetto),
				new RettificaProspettoService(configurationHelper, prospettoDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PutProspettoResponse annullaProspetto(Long idProspetto) {
		return executeService(new FunzProspettoRequest(idProspetto),
				new AnnullaProspettoService(configurationHelper, prospettoDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PutProspettoResponse duplicaProspetto(Long idProspetto) {
		return executeService(new FunzProspettoRequest(idProspetto),
				new DuplicaProspettoService(configurationHelper, prospettoDad));
	}

	/**
	 * Put an Prospetto
	 * 
	 * @param prospetto
	 * @return the prospetto
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PutStatoProspettoResponse putStatoProspettoUpdate(Long idProspetto, Long idStatoProspetto) {
		return executeService(new PutStatoProspettoRequest(idProspetto, idStatoProspetto),
				new PutStatoProspettoService(configurationHelper, prospettoDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PostConferemaRiepilogoProspettoResponse confermaRiepilogo(
			ConfermaRiepilogoProspetto confermaRiepilogoProspetto) {
		return executeService(new PostConferemaRiepilogoProspettoRequest(confermaRiepilogoProspetto),
				new ConfermaRiepilogoService(configurationHelper, prospettoDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PostProspettoResponse salvaBozzaRiepilogo(Prospetto prospetto) {
		return executeService(new PostProspettoRequest(prospetto, true),
				new SalvaBozzaRiepilogoService(configurationHelper, prospettoDad, riepilogoNazionaleDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public CheckPassaggioQ3Response checkPassaggioQ3(Long idProspetto) {
		return executeService(new CheckPassaggioQ3Request(idProspetto),
				new CheckPassaggioQ3Service(configurationHelper, datiProvincialiDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public StoreProcedureEseguiCalcoliResponse storeProcedureEseguiCalcoli(Long idProspetto,
			String cfUtenteAggiornamento, boolean soloScoperture) {
		return executeService(
				new StoreProcedureEseguiCalcoliRequest(idProspetto, cfUtenteAggiornamento, soloScoperture),
				new StoreProcedureEseguiCalcoliService(configurationHelper, prospettoDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public GetCheckScopertureDatoriLavoriPubbliciResponse getCheckScopertureDatoriLavoriPubblici(Long idProspetto) {
		return executeService(new GetCheckScopertureDatoriLavoriPubbliciRequest(idProspetto),
				new GetCheckScopertureDatoriLavoriPubbliciService(configurationHelper, prospettoDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public ReinviaProspettoResponse reinviaProspetto(ReinviaProspetto reinviaProspetto) {
		return executeService(new ReinviaProspettoRequest(reinviaProspetto),
				new ReinviaProspettoService(configurationHelper, prospettoDad));
	}
	
	
	/**
	 * Genera il Pdf a partire dall'html passato come parametro
	 * 
	 * @param idProspetto
	 * @param htmlContent
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public GeneraPdfPerSalvataggioResponse generaPdfPerSalvataggio(Long idProspetto, String htmlContent) {
		return executeService(new GeneraPdfRequest(idProspetto, htmlContent),
				new GeneraPdfPerSalvataggioService(configurationHelper));
	}
}
