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
package it.csi.prodis.prodissrv.ejb.business.be.service.impl.prospetto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import it.csi.prodis.prodissrv.ejb.business.be.dad.ProspettoDad;
import it.csi.prodis.prodissrv.ejb.business.be.service.request.prospetto.RiceviProspettoDaSpicomRequest;
import it.csi.prodis.prodissrv.ejb.business.be.service.response.prospetto.RiceviProspettoDaSpicomResponse;
import it.csi.prodis.prodissrv.ejb.entity.ProDImportErrori;
import it.csi.prodis.prodissrv.ejb.entity.ProTImportErroriSpicom;
import it.csi.prodis.prodissrv.ejb.util.ConstantsProdis;
import it.csi.prodis.prodissrv.ejb.util.ProdisSrvUtil;
import it.csi.prodis.prodissrv.ejb.util.ProdisThreadLocalContainer;
import it.csi.prodis.prodissrv.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodissrv.lib.dto.Utente;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.AssunzioneProtetta;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Atecofin;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.CategoriaAzienda;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.CategoriaEscluse;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.CausaSospensione;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Ccnl;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Comune;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Comunicazione;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Contratti;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Dichiarante;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Istat2001livello5;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Provincia;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Regione;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Soggetti;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.StatiEsteri;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.StatoConcessione;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.StatoVerifica;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.TipoRipropPt;
import it.csi.prodis.prodissrv.lib.dto.error.MsgProdis;
import it.csi.prodis.prodissrv.lib.dto.prospetto.AssPubbliche;
import it.csi.prodis.prodissrv.lib.dto.prospetto.CategorieEscluse;
import it.csi.prodis.prodissrv.lib.dto.prospetto.DatiAzienda;
import it.csi.prodis.prodissrv.lib.dto.prospetto.DatiProvinciali;
import it.csi.prodis.prodissrv.lib.dto.prospetto.FilterServiziProdis;
import it.csi.prodis.prodissrv.lib.dto.prospetto.LavoratoriInForza;
import it.csi.prodis.prodissrv.lib.dto.prospetto.PartTime;
import it.csi.prodis.prodissrv.lib.dto.prospetto.PostiLavoroDisp;
import it.csi.prodis.prodissrv.lib.dto.prospetto.Prospetto;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProspettoGradualita;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProspettoProvSede;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProspettoProvincia;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProvCompensazioni;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProvConvenzione;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProvEsonero;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProvEsoneroAutocert;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProvGradualita;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProvIntermittenti;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProvSospensione;
import it.csi.prodis.prodissrv.lib.dto.prospetto.RiepilogoNazionale;
import it.csi.prodis.prodissrv.lib.dto.prospetto.RiepilogoProvinciale;
import it.csi.prodis.prodissrv.lib.dto.prospetto.SedeLegale;
import it.csi.spicom.dto.Assunzionipubblicaselezione;
import it.csi.spicom.dto.Categorieescluse;
import it.csi.spicom.dto.Compensazioni;
import it.csi.spicom.dto.ComunicazioneProspettoDisabiliDTO;
import it.csi.spicom.dto.Convenzione;
import it.csi.spicom.dto.DatiInvioDTO;
import it.csi.spicom.dto.Datiaziendali;
import it.csi.spicom.dto.Datiprospetto;
import it.csi.spicom.dto.Dettagliointermittenti;
import it.csi.spicom.dto.Dettaglioparttime;
import it.csi.spicom.dto.Elencoriepilogativo;
import it.csi.spicom.dto.Gradualita;
import it.csi.spicom.dto.Lavoratoricomputabili;
import it.csi.spicom.dto.Postilavoro;
import it.csi.spicom.dto.ProspettoGenerale;
import it.csi.spicom.dto.Quadro1;
import it.csi.spicom.dto.Quadro2;
import it.csi.spicom.dto.Riepilogonazionale;
import it.csi.spicom.dto.Sedelegale;

public class RiceviProspettoDaSpicomService
		extends BaseProspettoService<RiceviProspettoDaSpicomRequest, RiceviProspettoDaSpicomResponse> {

	public RiceviProspettoDaSpicomService(ConfigurationHelper configurationHelper, ProspettoDad prospettoDad) {
		super(configurationHelper, prospettoDad);
	}

	@Override
	protected void execute() {
		log.debug("[RiceviProspettoDaSpicomService::execute]", "BEGIN");

		// ricerca il prospetto per codice comunicazione
		// se lo trova esce
		// altrimenti
		// cancella i record relativi alla trasmissione su PRO_D_IMPORT_ERRORI
		// completa il prospetto con tutte le transcodifiche (da ministero a locale)
		// salva eventuali errori su PRO_D_IMPORT_ERRORI
		// se ci sono errori esce
		// altrimenti
		// valida prospetto come se fosse in bozza
		// esegue l'inserimento
		// esegue il ricalcolo delle scoperture
		// cerca se ci sono prospetti con codice comunicazione precedente
		// cambia lo stato del prospetto precedente

		Utente utente = getUtente();
		if (utente == null) {
			utente = new Utente();
			utente.setCodiceFiscale(ConstantsProdis.UTENTE_IMPORT_PROSPETTO_DA_SPICOM);
			ProdisThreadLocalContainer.UTENTE_CONNESSO.set(utente); // FIXME

		}

		ComunicazioneProspettoDisabiliDTO comunicazioneSpicom = request.getComunicazioneProspettoSpicom();
		
		if (comunicazioneSpicom.getIdTrasmissione() == null) {
			log.error("[RiceviProspettoDaSpicomService::execute]", " ERROR: comunicazione senza idTrasmissione");
			response.addApiError(MsgProdis.SP0002.getError());
			return;
		}

		FilterServiziProdis filter = new FilterServiziProdis();
		filter.setCodiceRegionale(comunicazioneSpicom.getDatiInvio().getCodiceComunicazione());

		long numProspetti = prospettoDad.countRicerca(filter);
		if (numProspetti > 0) {
			log.info("[RiceviProspettoDaSpicomService::execute]", " INFO: prospetto con codice = "
					+ comunicazioneSpicom.getDatiInvio().getCodiceComunicazione() + " gia presente in Prodis");
			response.addApiError(MsgProdis.SP0001.getError());
			return;
		}

		//prospettoDad.deleteImportErrori(comunicazioneSpicom.getIdTrasmissione());

		List<ProDImportErrori> errors = new ArrayList<ProDImportErrori>();
		Prospetto prospetto = spicomToProdis(comunicazioneSpicom, prospettoDad, errors);
		
		response.setProspetto(prospetto);
		response.setErrors(errors);
		response.setTipoComunicazione(comunicazioneSpicom.getDatiInvio().getTipoComunicazione());
		
		
		///////////////////////////////////////////////////////////////////////////77
//
//		if (errors.size() > 0) {
//			response.addApiError(MsgProdis.SP0003.getError());
//
//			for (ProDImportErrori error : errors) {
//				prospettoDad.insertImportErrori(error);
//			}
//			return;
//		}
//
//		Long idProspetto = prospettoDad.insertProspettoCompleto(prospetto);
//
//		if (prospetto.getCodiceComunicazionePreced() != null) {
//			prospettoDad.updateStatoProspettoPrecedente(prospetto,
//					comunicazioneSpicom.getDatiInvio().getTipoComunicazione());
//		}
//
//		response.setIdProspetto(idProspetto);
		log.debug("[RiceviProspettoDaSpicomService::execute]", "END");

	}

	private void addErrorProDImportErrori(List<ProDImportErrori> errors, String tableName, Long idSpiTrasmissione,
			String datoInput, Long codErrore, String descrErrore) {

		ProDImportErrori errorToInsert = new ProDImportErrori();
		errorToInsert.setTabellaDestinazione(tableName);
		errorToInsert.setIdSpiTrasmissione(
				idSpiTrasmissione == null ? BigDecimal.ZERO : new BigDecimal(idSpiTrasmissione.longValue()));
		errorToInsert.setDatoInput(datoInput);
		errorToInsert.setProTImportErroriSpicom(new ProTImportErroriSpicom());
		errorToInsert.getProTImportErroriSpicom().setCodErrore(codErrore);
		errorToInsert.setDataElab(new Date());
		errorToInsert.setDsErroreNonGestito(descrErrore);

//		prospettoDad.insertImportErrori(errorToInsert);
		errors.add(errorToInsert);
	}

	private Prospetto spicomToProdis(ComunicazioneProspettoDisabiliDTO comunicazioneSpicom, ProspettoDad prospettoDad,
			List<ProDImportErrori> errors) {
		Prospetto prospetto = new Prospetto();

		List<ProspettoProvincia> prospettoProvincias = new ArrayList<ProspettoProvincia>();

		ProspettoGenerale prospettoSpicom = comunicazioneSpicom.getProspetto();
		Date dataRif = prospettoSpicom.getQuadro1().getDatiprospetto().getDataRiferimento();
		prospetto.setDataRiferimentoProspetto(dataRif);

		prospetto.setCodiceComunicazione(comunicazioneSpicom.getDatiInvio().getCodiceComunicazione());
		prospetto.setCodiceComunicazionePreced(comunicazioneSpicom.getDatiInvio().getCodiceComunicazionePrecedente());
		prospetto.setDataInvio(comunicazioneSpicom.getDatiInvio().getDataInvio());
		prospetto.setCfStudioProfessionale(comunicazioneSpicom.getDatiInvio().getCodFiscaleSoggettoComunicante()); // FIXME
		prospetto.setEmailSoggettoComunicazione(prospettoSpicom.getEmailDelegato());

//		prospetto.setNumeroProtocollo(prospettoSpicom.getProtocollo() == null ? BigDecimal.ZERO : new BigDecimal(prospettoSpicom.getProtocollo())); // FIXME anno??

		if (prospettoSpicom.getTipocomunicazione() != null
				&& !"".equalsIgnoreCase(prospettoSpicom.getTipocomunicazione())) {
			Comunicazione comunicazione = (Comunicazione) prospettoDad.getTfromMin(Comunicazione.class,
					prospettoSpicom.getTipocomunicazione(), dataRif);
			prospetto.setComunicazione(comunicazione);
			if (comunicazione == null) {
				addErrorProDImportErrori(errors, ConstantsProdis.PRO_T_COMUNICAZIONE,
						comunicazioneSpicom.getIdTrasmissione(), prospettoSpicom.getTipocomunicazione(),
						ConstantsProdis.IMPORT_ERROR_TIPO_COMUNICAZIONE, "prospettoSpicom.getTipocomunicazione()");
			}
		}

		Datiprospetto datiProsSpicom = prospettoSpicom.getQuadro1().getDatiprospetto();
		if (datiProsSpicom.getCategoriaAzienda() != null
				&& !"".equalsIgnoreCase(datiProsSpicom.getCategoriaAzienda())) {
			CategoriaAzienda categoriaAzienda = (CategoriaAzienda) prospettoDad.getTfromMin(CategoriaAzienda.class,
					datiProsSpicom.getCategoriaAzienda(), dataRif);
			prospetto.setCategoriaAzienda(categoriaAzienda);
			if (categoriaAzienda == null) {
				addErrorProDImportErrori(errors, ConstantsProdis.PRO_T_CATEGORIA_AZIENDA,
						comunicazioneSpicom.getIdTrasmissione(), datiProsSpicom.getCategoriaAzienda(),
						ConstantsProdis.IMPORT_ERROR_CATEGORIA_AZIENDA,
						"prospettoSpicom.getQuadro1().getDatiprospetto().getCategoriaAzienda()");
			}
		}

		DatiAzienda datiAzienda = new DatiAzienda();

		datiAzienda.setFlgProspettoDaCapogruppo(datiProsSpicom.getCapogruppo());
		datiAzienda.setCfCapogruppo(datiProsSpicom.getCfcapogruppo());
		datiAzienda.setFlgCapogruppoEstero(datiProsSpicom.getFlgCapogruppoEstero());
		prospetto.setNumLavorInForzaNazionale(datiProsSpicom.getNlavoratoriNazionali() == null ? BigDecimal.ZERO
				: new BigDecimal(datiProsSpicom.getNlavoratoriNazionali()));
		prospetto.setFlgNessunaAssunzioneAggiun(datiProsSpicom.getNessunaAssunzioneAggiuntiva());
		prospetto.setDataPrimaAssunzione(datiProsSpicom.getDataPrimaAssunzione());
		prospetto.setDataSecondaAssunzione(datiProsSpicom.getDataSecondaAssunzione());

		Quadro1 quadro1Spicom = prospettoSpicom.getQuadro1();
		Datiaziendali datiAzSpicom = quadro1Spicom.getDatiaziendali();
		if (datiAzSpicom.getDichiarante().getCcnl() != null
				&& !"".equalsIgnoreCase(datiAzSpicom.getDichiarante().getCcnl())) {
			Ccnl ccnl = (Ccnl) prospettoDad.getTfromMin(Ccnl.class, datiAzSpicom.getDichiarante().getCcnl(), dataRif);
			datiAzienda.setCcnl(ccnl);
			if (ccnl == null) {
				addErrorProDImportErrori(errors, ConstantsProdis.PRO_T_CCNL, comunicazioneSpicom.getIdTrasmissione(),
						datiAzSpicom.getDichiarante().getCcnl(), ConstantsProdis.IMPORT_ERROR_CCNL,
						"datiAzSpicom.getDichiarante().getCcnl()");
			}
		}

		datiAzienda.setCfAzienda(datiAzSpicom.getDichiarante().getCodicefiscale());
		datiAzienda.setDenominazioneDatoreLavoro(datiAzSpicom.getDichiarante().getDenominazione());
		if (datiAzSpicom.getDichiarante().getSettore() != null
				&& !"".equalsIgnoreCase(datiAzSpicom.getDichiarante().getSettore())) {
			Atecofin atecofin = (Atecofin) prospettoDad.getTfromMin(Atecofin.class,
					datiAzSpicom.getDichiarante().getSettore(), dataRif);
			datiAzienda.setAtecofin(atecofin);
			if (atecofin == null) {
				addErrorProDImportErrori(errors, ConstantsProdis.PRO_T_ATECOFIN,
						comunicazioneSpicom.getIdTrasmissione(), datiAzSpicom.getDichiarante().getSettore(),
						ConstantsProdis.IMPORT_ERROR_SETTORE_ATECO, "datiAzSpicom.getDichiarante().getSettore()");
			}
		}
		if (datiAzSpicom.getDichiarante().getTipologiadichiarante() != null
				&& !"".equalsIgnoreCase(datiAzSpicom.getDichiarante().getTipologiadichiarante())) {
			Dichiarante dichiarante = (Dichiarante) prospettoDad.getTfromMin(Dichiarante.class,
					datiAzSpicom.getDichiarante().getTipologiadichiarante(), dataRif);
			datiAzienda.setDichiarante(dichiarante);
			if (dichiarante == null) {
				addErrorProDImportErrori(errors, ConstantsProdis.PRO_T_DICHIARANTE,
						comunicazioneSpicom.getIdTrasmissione(),
						datiAzSpicom.getDichiarante().getTipologiadichiarante(),
						ConstantsProdis.IMPORT_ERROR_TIPO_DICHIARANTE,
						"datiAzSpicom.getDichiarante().getTipologiadichiarante()");
			}
		}
		datiAzienda.setCapReferente(datiAzSpicom.getReferente().getCap());
		datiAzienda.setCfReferente(datiAzSpicom.getReferente().getCodicefiscale());
		datiAzienda.setCognomeReferente(datiAzSpicom.getReferente().getCognome());
		datiAzienda.setNomeReferente(datiAzSpicom.getReferente().getNome());
		if (datiAzSpicom.getReferente().getComune() != null
				&& !"".equalsIgnoreCase(datiAzSpicom.getReferente().getComune())) {
			Comune comuneReferente = (Comune) prospettoDad.getTfromMin(Comune.class,
					datiAzSpicom.getReferente().getComune(), dataRif);
			datiAzienda.setComune(comuneReferente);
			if (comuneReferente == null) {
				addErrorProDImportErrori(errors, ConstantsProdis.PRO_T_COMUNE, comunicazioneSpicom.getIdTrasmissione(),
						datiAzSpicom.getReferente().getComune(), ConstantsProdis.IMPORT_ERROR_COMUNE_STATO_ESTERO,
						"datiAzSpicom.getReferente().getComune()");
			}
		}
		datiAzienda.setEmailReferente(datiAzSpicom.getReferente().getEmail());
		datiAzienda.setFaxReferente(datiAzSpicom.getReferente().getFax());
		datiAzienda.setIndirizzoReferente(datiAzSpicom.getReferente().getIndirizzo());
		datiAzienda.setTelefonoReferente(datiAzSpicom.getReferente().getTelefono());
		prospetto.setDatiAzienda(datiAzienda);

		SedeLegale sedeLegale = new SedeLegale();
		Sedelegale sedeLegaleSpicom = datiAzSpicom.getSedelegale();
		sedeLegale.setCapSede(sedeLegaleSpicom.getCapsedelegale());
		if (sedeLegaleSpicom.getComunesedelegale() != null
				&& !"".equalsIgnoreCase(sedeLegaleSpicom.getComunesedelegale())) {
			Comune comuneSedeLegale = (Comune) prospettoDad.getTfromMin(Comune.class,
					sedeLegaleSpicom.getComunesedelegale(), dataRif);
			sedeLegale.setComune(comuneSedeLegale);
			if (comuneSedeLegale == null) {
				addErrorProDImportErrori(errors, ConstantsProdis.PRO_T_COMUNE, comunicazioneSpicom.getIdTrasmissione(),
						sedeLegaleSpicom.getComunesedelegale(), ConstantsProdis.IMPORT_ERROR_COMUNE_STATO_ESTERO,
						"sedeLegaleSpicom.getComunesedelegale()");
			}
		}
		sedeLegale.setEmail(sedeLegaleSpicom.getEmail());
		sedeLegale.setFax(sedeLegaleSpicom.getFax());
		sedeLegale.setIndirizzo(sedeLegaleSpicom.getIndirizzosedelegale());
		sedeLegale.setTelefono(sedeLegaleSpicom.getTelefono());
		prospetto.getDatiAzienda().setSedeLegale(sedeLegale);

		if (prospettoSpicom.getQuadro1().getGradualita() != null) {
			Gradualita gradualitaSpicom = prospettoSpicom.getQuadro1().getGradualita();
			ProspettoGradualita gradualita = new ProspettoGradualita();
			gradualita.setnAssunzioniLavPreTrasf(
					gradualitaSpicom.getAssunzioninondisabiliprimaditrasformazione() == null ? BigDecimal.ZERO
							: new BigDecimal(gradualitaSpicom.getAssunzioninondisabiliprimaditrasformazione()));
			gradualita.setDataAtto(gradualitaSpicom.getDataatto());
			gradualita.setDataTrasformazione(gradualitaSpicom.getDatatrasformazione());
			gradualita.setEstremiAtto(gradualitaSpicom.getEstremiatto());
			gradualita.setPercentuale(gradualitaSpicom.getPercentuale() == null ? BigDecimal.ZERO
					: new BigDecimal(gradualitaSpicom.getPercentuale()));
			prospetto.setProspettoGradualita(gradualita);
		}

		if (prospettoSpicom.getQuadro1().getAssunzionipubblicaselezione() != null
				&& prospettoSpicom.getQuadro1().getAssunzionipubblicaselezione().length > 0) {
			Assunzionipubblicaselezione[] assPubblicheSpicom = prospettoSpicom.getQuadro1()
					.getAssunzionipubblicaselezione();

			List<AssPubbliche> assPubbliche = new ArrayList<AssPubbliche>();
			for (Assunzionipubblicaselezione ass : assPubblicheSpicom) {
				AssPubbliche assPubblica = new AssPubbliche();
				if (ass.getRegione() != null && !"".equalsIgnoreCase(ass.getRegione())) {
					Regione regione = (Regione) prospettoDad.getTfromMin(Regione.class, ass.getRegione(), dataRif);
					assPubblica.setRegione(regione);
					if (regione == null) {
						addErrorProDImportErrori(errors, ConstantsProdis.PRO_T_REGIONE,
								comunicazioneSpicom.getIdTrasmissione(), ass.getRegione(),
								ConstantsProdis.IMPORT_ERROR_REGIONE, "assPubblicheSpicom.getRegione()");
					}
				}
				assPubblica.setSaldoDisabili(
						ass.getSaldodisabili() == null ? BigDecimal.ZERO : new BigDecimal(ass.getSaldodisabili()));
				assPubblica.setSaldoExArt18(
						ass.getSaldoexart18() == null ? BigDecimal.ZERO : new BigDecimal(ass.getSaldoexart18()));
				assPubblica.setDsNote(ass.getNote());
				assPubbliche.add(assPubblica);
			}
			prospetto.setAssPubbliche(assPubbliche);
		}

		if (prospettoSpicom.getQuadro1().getSospensionemobilita() != null) {
			prospetto.setFlgSospensionePerMobilita(
					prospettoSpicom.getQuadro1().getSospensionemobilita().getSospensionepermobilita());
			prospetto.setDFineSospensioneQ1(
					prospettoSpicom.getQuadro1().getSospensionemobilita().getDataFineSospensione());
		} else {
			prospetto.setFlgSospensionePerMobilita("N");
		}

		Quadro2[] quadro2 = prospettoSpicom.getQuadro2();
		Map<String, ProspettoProvincia> mapProspettoProvincia = new HashMap<String, ProspettoProvincia>();

		if (quadro2 != null && quadro2.length > 0) {

			for (Quadro2 quadro2Spicom : quadro2) { // for su quadro 2 spicom start

				DatiProvinciali datiProvinciali = new DatiProvinciali();
				ProspettoProvincia prospettoProvincia = new ProspettoProvincia();
				mapProspettoProvincia.put(quadro2Spicom.getDatiprovinciali().getProvincia(), prospettoProvincia);

				// Compensazioni
				Compensazioni[] compensazioniSpicom = quadro2Spicom.getCompensazioni();
				if (compensazioniSpicom != null && compensazioniSpicom.length > 0) {
					List<ProvCompensazioni> provCompensazionis = new ArrayList<ProvCompensazioni>();
					for (Compensazioni compensazioneSpicom : compensazioniSpicom) {
						ProvCompensazioni comp = new ProvCompensazioni();
						comp.setCfAziendaAppartenAlGruppo(compensazioneSpicom.getCodicefiscaleaziendagruppo());
						comp.setCategoriaCompensazione(compensazioneSpicom.getCategoriacomp());
						comp.setCategoriaSoggetto(compensazioneSpicom.getCategoriasogg());
						comp.setDataAtto(compensazioneSpicom.getDataatto());
						comp.setEstremiAtto(compensazioneSpicom.getEstremiatto());
						comp.setnLavoratori(compensazioneSpicom.getNumero() == null ? BigDecimal.ZERO
								: new BigDecimal(compensazioneSpicom.getNumero()));
						if (compensazioneSpicom.getProvincia() != null
								&& !"".equalsIgnoreCase(compensazioneSpicom.getProvincia())) {
							Provincia provincia = (Provincia) prospettoDad.getTfromMin(Provincia.class,
									compensazioneSpicom.getProvincia(), dataRif);
							comp.setProvincia(provincia);
							if (provincia == null) {
								addErrorProDImportErrori(errors, ConstantsProdis.PRO_T_PROVINCIA,
										comunicazioneSpicom.getIdTrasmissione(), compensazioneSpicom.getProvincia(),
										ConstantsProdis.IMPORT_ERROR_PROVINCIA, "compensazioneSpicom.getProvincia()");
							}
						}
						if (compensazioneSpicom.getStato() != null
								&& !"".equalsIgnoreCase(compensazioneSpicom.getStato())) {
							StatoConcessione statoConcessione = (StatoConcessione) prospettoDad
									.getTfromMin(StatoConcessione.class, compensazioneSpicom.getStato(), dataRif);
							comp.setStatoConcessione(statoConcessione);
							if (statoConcessione == null) {
								addErrorProDImportErrori(errors, ConstantsProdis.PRO_T_STATO_CONCESSIONE,
										comunicazioneSpicom.getIdTrasmissione(), compensazioneSpicom.getStato(),
										ConstantsProdis.IMPORT_ERROR_STATO_CONCESSIONE,
										"compensazioneSpicom.getStato()");
							}
						}
						provCompensazionis.add(comp);
					}
					datiProvinciali.setProvCompensazionis(provCompensazionis);
				}
				// end compensazioni

				// Convenzione
				if (quadro2Spicom.getConvenzione() != null) {
					Convenzione convSpicom = quadro2Spicom.getConvenzione();
					ProvConvenzione convenzione = new ProvConvenzione();
					convenzione.setDataAtto(convSpicom.getDataatto());
					convenzione.setDataStipula(convSpicom.getDatastipula());
					convenzione.setDataScadenza(convSpicom.getDataconcessione());
					convenzione.setEstremiAtto(convSpicom.getEstremiatto());
					if (convSpicom.getStato() != null && !"".equalsIgnoreCase(convSpicom.getStato())) {
						StatoConcessione statoConcessione = (StatoConcessione) prospettoDad
								.getTfromMin(StatoConcessione.class, convSpicom.getStato(), dataRif);
						convenzione.setStatoConcessione(statoConcessione);
						if (statoConcessione == null) {
							addErrorProDImportErrori(errors, ConstantsProdis.PRO_T_STATO_CONCESSIONE,
									comunicazioneSpicom.getIdTrasmissione(), convSpicom.getStato(),
									ConstantsProdis.IMPORT_ERROR_STATO_CONCESSIONE, "convenzione.getStato()");
						}
					}
					if (convSpicom.getTipoconvenzione() != null
							&& !"".equalsIgnoreCase(convSpicom.getTipoconvenzione())) {
						AssunzioneProtetta assunzioneProtetta = (AssunzioneProtetta) prospettoDad
								.getTfromMin(AssunzioneProtetta.class, convSpicom.getTipoconvenzione(), dataRif);
						convenzione.setAssunzioneProtetta(assunzioneProtetta);
						if (assunzioneProtetta == null) {
							addErrorProDImportErrori(errors, ConstantsProdis.PRO_T_ASSUNZIONE_PROTETTA,
									comunicazioneSpicom.getIdTrasmissione(), convSpicom.getTipoconvenzione(),
									ConstantsProdis.IMPORT_ERROR_TIPO_ASSSUNZIONE, "convenzione.getTipoconvenzione()");
						}
					}
					if (convSpicom.getNumLavoratoriPrevisti() != null
							&& !"0".equalsIgnoreCase(convSpicom.getNumLavoratoriPrevisti())) {
						convenzione.setNumLavPrevConvQ2(new BigDecimal(convSpicom.getNumLavoratoriPrevisti()));
					} else {
						convenzione.setNumLavPrevConvQ2(BigDecimal.ZERO);
					}
					datiProvinciali.setProvConvenzione(convenzione);
				}
				// end Convenzione
				datiProvinciali.setnPostiPrevCentraliNonved(
						quadro2Spicom.getDatiprovinciali().getNposticentralinisti() == null ? BigDecimal.ZERO
								: new BigDecimal(quadro2Spicom.getDatiprovinciali().getNposticentralinisti()));
				datiProvinciali.setnPostiPrevMassofisNonved(
						quadro2Spicom.getDatiprovinciali().getNpostimassofisioterapisti() == null ? BigDecimal.ZERO
								: new BigDecimal(quadro2Spicom.getDatiprovinciali().getNpostimassofisioterapisti()));
				// campi presenti in tabella , non valorizzatinel DTO di spicom ma che devono
				// essere settati 0 perchè poi calcolati dalla procedura
				datiProvinciali.setnCentralTelefoNonvedenti(BigDecimal.ZERO);
				datiProvinciali.setnPartimeRiproporzionati(BigDecimal.ZERO);
				datiProvinciali.setnCateProtForzaCntDis("0");
				datiProvinciali.setBaseComputo(BigDecimal.ZERO);
				datiProvinciali.setnIntermittentiRiproporziona(BigDecimal.ZERO);

				if (datiProvinciali.getProspettoProvincia() == null) {
					datiProvinciali.setProspettoProvincia(new ProspettoProvincia());
				}
				if (quadro2Spicom.getDatiprovinciali().getProvincia() != null
						&& !"".equalsIgnoreCase(quadro2Spicom.getDatiprovinciali().getProvincia())) {
					Provincia provincia = (Provincia) prospettoDad.getTfromMin(Provincia.class,
							quadro2Spicom.getDatiprovinciali().getProvincia(), dataRif);
					datiProvinciali.getProspettoProvincia().setProvincia(provincia);
					if (provincia == null) {
						addErrorProDImportErrori(errors, ConstantsProdis.PRO_T_PROVINCIA,
								comunicazioneSpicom.getIdTrasmissione(),
								quadro2Spicom.getDatiprovinciali().getProvincia(),
								ConstantsProdis.IMPORT_ERROR_PROVINCIA,
								"quadro2Spicom.getDatiprovinciali().getProvincia()");
					}
				}

				if (datiProvinciali.getProspettoProvSede() == null) {
					datiProvinciali.setProspettoProvSede(new ProspettoProvSede());
				}
				datiProvinciali.getProspettoProvSede()
						.setCognomeReferente(quadro2Spicom.getDatiprovinciali().getReferente().getCognome());
				datiProvinciali.getProspettoProvSede()
						.setNomeReferente(quadro2Spicom.getDatiprovinciali().getReferente().getNome());

				datiProvinciali.getProspettoProvSede()
						.setCap(quadro2Spicom.getDatiprovinciali().getSederiferimento().getCap());
				if (quadro2Spicom.getDatiprovinciali().getSederiferimento().getComune() != null
						&& !"".equalsIgnoreCase(quadro2Spicom.getDatiprovinciali().getSederiferimento().getComune())) {
					Comune comuneSedeRif = (Comune) prospettoDad.getTfromMin(Comune.class,
							quadro2Spicom.getDatiprovinciali().getSederiferimento().getComune(), dataRif);
					datiProvinciali.getProspettoProvSede().setComune(comuneSedeRif);
					if (comuneSedeRif == null) {
						addErrorProDImportErrori(errors, ConstantsProdis.PRO_T_COMUNE,
								comunicazioneSpicom.getIdTrasmissione(),
								quadro2Spicom.getDatiprovinciali().getSederiferimento().getComune(),
								ConstantsProdis.IMPORT_ERROR_COMUNE_STATO_ESTERO,
								"quadro2Spicom.getDatiprovinciali().getSederiferimento().getComune()");
					}
				}
				datiProvinciali.getProspettoProvSede()
						.setEmail(quadro2Spicom.getDatiprovinciali().getSederiferimento().getEmail());
				datiProvinciali.getProspettoProvSede()
						.setFax(quadro2Spicom.getDatiprovinciali().getSederiferimento().getFax());
				datiProvinciali.getProspettoProvSede()
						.setIndirizzo(quadro2Spicom.getDatiprovinciali().getSederiferimento().getIndirizzo());
				datiProvinciali.getProspettoProvSede()
						.setTelefono(quadro2Spicom.getDatiprovinciali().getSederiferimento().getTelefono());

				// Esonero
				if (quadro2Spicom.getEsoneri() != null) {
					ProvEsonero esonero = new ProvEsonero();
					esonero.setDataAtto(quadro2Spicom.getEsoneri().getDataatto());
					esonero.setEstremiAtto(quadro2Spicom.getEsoneri().getEstremiatto());
					esonero.setDataAttoFinoAl(quadro2Spicom.getEsoneri().getFinoal());
					esonero.setnLavoratoriEsonero(
							quadro2Spicom.getEsoneri().getNumerolavoratori() == null ? BigDecimal.ZERO
									: new BigDecimal(quadro2Spicom.getEsoneri().getNumerolavoratori()));
					esonero.setPercentuale(quadro2Spicom.getEsoneri().getPercentuale() == null ? BigDecimal.ZERO
							: new BigDecimal(quadro2Spicom.getEsoneri().getPercentuale()));
					if (quadro2Spicom.getEsoneri().getStato() != null
							&& !"".equalsIgnoreCase(quadro2Spicom.getEsoneri().getStato())) {
						StatoConcessione stConcess = (StatoConcessione) prospettoDad.getTfromMin(StatoConcessione.class,
								quadro2Spicom.getEsoneri().getStato(), dataRif);
						esonero.setStatoConcessione(stConcess);
						if (stConcess == null) {
							addErrorProDImportErrori(errors, ConstantsProdis.PRO_T_STATO_CONCESSIONE,
									comunicazioneSpicom.getIdTrasmissione(), quadro2Spicom.getEsoneri().getStato(),
									ConstantsProdis.IMPORT_ERROR_STATO_CONCESSIONE,
									"quadro2Spicom.getEsoneri().getStato()");
						}
					}
					datiProvinciali.setProvEsonero(esonero);
				}

				// Esonero Autocert
				if (quadro2Spicom.getEsoneri60PerMille() != null) {
					ProvEsoneroAutocert esoneroAutocert = new ProvEsoneroAutocert();
					esoneroAutocert.setDataAutocert(quadro2Spicom.getEsoneri60PerMille().getDataAutocertificazione());
					esoneroAutocert
							.setnLav60x1000(quadro2Spicom.getEsoneri60PerMille().getNumeroLavoratori60PerMille() == null
									? BigDecimal.ZERO
									: new BigDecimal(
											quadro2Spicom.getEsoneri60PerMille().getNumeroLavoratori60PerMille()));
					esoneroAutocert.setnLavEsoneroAutocert(
							quadro2Spicom.getEsoneri60PerMille().getNumeroLavoratoriEsonero() == null ? BigDecimal.ZERO
									: new BigDecimal(
											quadro2Spicom.getEsoneri60PerMille().getNumeroLavoratoriEsonero()));
					esoneroAutocert.setPercentualeEsAutocert(
							quadro2Spicom.getEsoneri60PerMille().getPercentuale() == null ? BigDecimal.ZERO
									: new BigDecimal(quadro2Spicom.getEsoneri60PerMille().getPercentuale()));
					datiProvinciali.setProvEsoneroAutocert(esoneroAutocert);
				}
				// Graduallita
				if (quadro2Spicom.getGradualita() != null) {
					ProvGradualita provGradualita = new ProvGradualita();
					provGradualita.setnAssunzioniEffDopoTrasf(
							quadro2Spicom.getGradualita().getNassunzioni() == null ? BigDecimal.ZERO
									: new BigDecimal(quadro2Spicom.getGradualita().getNassunzioni()));
					datiProvinciali.setProvGradualita(provGradualita);
				}

				// lavoratori
				if (quadro2Spicom.getLavoratoricomputabili() != null
						&& quadro2Spicom.getLavoratoricomputabili().length > 0) {
					List<LavoratoriInForza> lavoratoriInForzas = new ArrayList<LavoratoriInForza>();
					for (Lavoratoricomputabili lavSpicom : quadro2Spicom.getLavoratoricomputabili()) {
						LavoratoriInForza lavoratore = new LavoratoriInForza();

						lavoratore.setCodiceFiscale(lavSpicom.getLavoratore().getCodicefiscale());
						lavoratore.setCognome(lavSpicom.getLavoratore().getCognome());
						lavoratore.setNome(lavSpicom.getLavoratore().getNome());
						lavoratore.setDataNascita(lavSpicom.getLavoratore().getDatanascita());
						if (lavSpicom.getLavoratore().getComunenascita() != null
								&& !"".equalsIgnoreCase(lavSpicom.getLavoratore().getComunenascita())) {
							if (!lavSpicom.getLavoratore().getComunenascita().startsWith("Z")) {
								Comune comuneNascita = (Comune) prospettoDad.getTfromMin(Comune.class,
										lavSpicom.getLavoratore().getComunenascita(),
										lavSpicom.getLavoratore().getDatanascita());
								lavoratore.setComune(comuneNascita);
								if (comuneNascita == null) {
									addErrorProDImportErrori(errors, ConstantsProdis.PRO_T_COMUNE,
											comunicazioneSpicom.getIdTrasmissione(),
											lavSpicom.getLavoratore().getComunenascita(),
											ConstantsProdis.IMPORT_ERROR_COMUNE_STATO_ESTERO,
											"lavSpicom.getLavoratore().getComunenascita() CF "
													+ lavSpicom.getLavoratore().getCodicefiscale());
								}
							} else {
								StatiEsteri statoNascita = (StatiEsteri) prospettoDad.getTfromMin(StatiEsteri.class,
										lavSpicom.getLavoratore().getComunenascita(),
										lavSpicom.getLavoratore().getDatanascita());
								if (statoNascita != null) {
									lavoratore.setIdTStatoEsteroNascita(statoNascita.getId());
								} else {
									addErrorProDImportErrori(errors, ConstantsProdis.PRO_T_STATI_ESTERI,
											comunicazioneSpicom.getIdTrasmissione(),
											lavSpicom.getLavoratore().getComunenascita(),
											ConstantsProdis.IMPORT_ERROR_COMUNE_STATO_ESTERO,
											"lavSpicom.getLavoratore().getComunenascita() CF "
													+ lavSpicom.getLavoratore().getCodicefiscale());
								}

							}
						}
						lavoratore.setSesso(lavSpicom.getLavoratore().getSesso());
						lavoratore.setPercentualeDisabilita(
								lavSpicom.getLavoratore().getPercentualeDisabilita() == null ? null
										: new BigDecimal(lavSpicom.getLavoratore().getPercentualeDisabilita()));
						lavoratore.setCategoriaAssunzione(lavSpicom.getRapportolavoro().getCategoriaass());
						lavoratore.setCategoriaSoggetto(lavSpicom.getRapportolavoro().getCategoriasogg());
						lavoratore.setDataInizioRapporto(lavSpicom.getRapportolavoro().getDatainizio());
						lavoratore.setDataFineRapporto(lavSpicom.getRapportolavoro().getDatafine());
						lavoratore.setOrarioSettContrattualeMin(
								lavSpicom.getRapportolavoro().getOrariosettimanale() == null ? BigDecimal.ZERO
										: new BigDecimal(ProdisSrvUtil.oreMinutiToMinuti(
												lavSpicom.getRapportolavoro().getOrariosettimanale())));
						lavoratore.setOrarioSettPartTimeMin(
								lavSpicom.getRapportolavoro().getOrariosettimanaleparttime() == null ? BigDecimal.ZERO
										: new BigDecimal(ProdisSrvUtil.oreMinutiToMinuti(
												lavSpicom.getRapportolavoro().getOrariosettimanaleparttime())));
						if (lavSpicom.getRapportolavoro().getQualifica() != null
								&& !"".equalsIgnoreCase(lavSpicom.getRapportolavoro().getQualifica())) {
							Istat2001livello5 istat = (Istat2001livello5) prospettoDad.getTfromMin(
									Istat2001livello5.class, lavSpicom.getRapportolavoro().getQualifica(), dataRif); // FIXME
																														// è
																														// corretta
																														// la
																														// data
																														// riferimento
																														// o
																														// e'
																														// la
																														// data
																														// inizio
																														// rapporto
							lavoratore.setIstat2001livello5(istat);
							if (istat == null) {
								addErrorProDImportErrori(errors, ConstantsProdis.PRO_T_ISTAT2001LIVELLO5,
										comunicazioneSpicom.getIdTrasmissione(),
										lavSpicom.getRapportolavoro().getQualifica(),
										ConstantsProdis.IMPORT_ERROR_QUALIFICA,
										"lavSpicom.getRapportolavoro().getQualifica() CF "
												+ lavSpicom.getLavoratore().getCodicefiscale());
							}
						}
						if (lavSpicom.getRapportolavoro().getTipoassunzione() != null
								&& !"".equalsIgnoreCase(lavSpicom.getRapportolavoro().getTipoassunzione())) {
							AssunzioneProtetta assProtetta = (AssunzioneProtetta) prospettoDad.getTfromMin(
									AssunzioneProtetta.class, lavSpicom.getRapportolavoro().getTipoassunzione(),
									dataRif); // FIXME è corretta la data riferimento o e' la data inizio rapporto
							lavoratore.setAssunzioneProtetta(assProtetta);
							if (assProtetta == null) {
								addErrorProDImportErrori(errors, ConstantsProdis.PRO_T_ASSUNZIONE_PROTETTA,
										comunicazioneSpicom.getIdTrasmissione(),
										lavSpicom.getRapportolavoro().getTipoassunzione(),
										ConstantsProdis.IMPORT_ERROR_TIPO_ASSSUNZIONE,
										"lavSpicom.getRapportolavoro().getTipoassunzione() CF "
												+ lavSpicom.getLavoratore().getCodicefiscale());
							}
						}
						if (lavSpicom.getRapportolavoro().getTipologiacontr() != null
								&& !"".equalsIgnoreCase(lavSpicom.getRapportolavoro().getTipologiacontr())) {
							Contratti contratto = (Contratti) prospettoDad.getTfromMin(Contratti.class,
									lavSpicom.getRapportolavoro().getTipologiacontr(), dataRif); // FIXME è corretta la
																									// data riferimento
																									// o e' la data
																									// inizio rapporto
							lavoratore.setContratti(contratto);
							if (contratto == null) {
								addErrorProDImportErrori(errors, ConstantsProdis.PRO_T_CONTRATTI,
										comunicazioneSpicom.getIdTrasmissione(),
										lavSpicom.getRapportolavoro().getTipologiacontr(),
										ConstantsProdis.IMPORT_ERROR_CONTRATTO,
										"lavSpicom.getRapportolavoro().getTipologiacontr() CF "
												+ lavSpicom.getLavoratore().getCodicefiscale());
							}
						}
						lavoratoriInForzas.add(lavoratore);
					}
					datiProvinciali.setLavoratoriInForzas(lavoratoriInForzas);
				}
				datiProvinciali.setNote(quadro2Spicom.getNote());

				// categorie escluse
				if (quadro2Spicom.getPersonaledipendente() != null
						&& quadro2Spicom.getPersonaledipendente().getCategorieescluse() != null
						&& quadro2Spicom.getPersonaledipendente().getCategorieescluse().length > 0) {
					List<CategorieEscluse> categorieEscluses = new ArrayList<CategorieEscluse>();
					for (Categorieescluse catSpicom : quadro2Spicom.getPersonaledipendente().getCategorieescluse()) {
						CategorieEscluse categorieEscluse = new CategorieEscluse();
						if (catSpicom.getCategoria() != null && !"".equalsIgnoreCase(catSpicom.getCategoria())) {
							CategoriaEscluse categoria = (CategoriaEscluse) prospettoDad
									.getTfromMin(CategoriaEscluse.class, catSpicom.getCategoria(), dataRif);
							categorieEscluse.setCategoriaEscluse(categoria);
							if (categoria == null) {
								addErrorProDImportErrori(errors, ConstantsProdis.PRO_T_CATEGORIA_ESCLUSE,
										comunicazioneSpicom.getIdTrasmissione(), catSpicom.getCategoria(),
										ConstantsProdis.IMPORT_ERROR_CATEGORIA_ESCLUSA,
										"quadro2Spicom.getPersonaledipendente().getCategorieescluse().getCategoria()");
							}
						}
						categorieEscluse.setnLavAppartartCategoria(catSpicom.getNumero() == null ? BigDecimal.ZERO
								: new BigDecimal(catSpicom.getNumero()));
						categorieEscluses.add(categorieEscluse);
					}
					datiProvinciali.setCategorieEscluses(categorieEscluses);
				}
				List<PartTime> partTimes = new ArrayList<PartTime>();
				if (quadro2Spicom.getPersonaledipendente() != null) {
					datiProvinciali.setnCateProtForzaA17012000(
							quadro2Spicom.getPersonaledipendente().getDicuiinforzaal17012000() == null ? BigDecimal.ZERO
									: new BigDecimal(
											quadro2Spicom.getPersonaledipendente().getDicuiinforzaal17012000()));
					datiProvinciali.setnCateProtForza(
							quadro2Spicom.getPersonaledipendente().getCategorieprotette() == null ? BigDecimal.ZERO
									: new BigDecimal(quadro2Spicom.getPersonaledipendente().getCategorieprotette()));
					datiProvinciali.setnTotaleLavoratDipendenti(
							quadro2Spicom.getPersonaledipendente().getNlavoratori() == null ? BigDecimal.ZERO
									: new BigDecimal(quadro2Spicom.getPersonaledipendente().getNlavoratori()));

					if (quadro2Spicom.getPersonaledipendente().getCentralinisti() != null) {
						datiProvinciali.setnCentralTelefoNonvedenti(quadro2Spicom.getPersonaledipendente()
								.getCentralinisti().getNlavoratoritempopieno() == null ? BigDecimal.ZERO
										: new BigDecimal(quadro2Spicom.getPersonaledipendente().getCentralinisti()
												.getNlavoratoritempopieno()));
						if (quadro2Spicom.getPersonaledipendente().getCentralinisti().getParttime() != null
								&& quadro2Spicom.getPersonaledipendente().getCentralinisti().getParttime().size() > 0) {
							ArrayList<Dettaglioparttime> listaPt = new ArrayList<Dettaglioparttime>();
							for (int i = 0; i < quadro2Spicom.getPersonaledipendente().getCentralinisti().getParttime()
									.size(); i++) {
								LinkedHashMap<String, String> dpt = (LinkedHashMap<String, String>) quadro2Spicom
										.getPersonaledipendente().getCentralinisti().getParttime().get(i);
								String numero = dpt.get("numero");
								String orariosettimanalecontrattuale = dpt.get("orariosettimanalecontrattuale");
								String orariosettimanaleparttime = dpt.get("orariosettimanaleparttime");

								PartTime pt = new PartTime();
								pt.setnPartTime(numero == null ? BigDecimal.ZERO : new BigDecimal(numero));
								pt.setOrarioSettContrattualeMin(orariosettimanalecontrattuale == null ? BigDecimal.ZERO
										: new BigDecimal(
												ProdisSrvUtil.oreMinutiToMinuti(orariosettimanalecontrattuale)));
								pt.setOrarioSettPartTimeMin(orariosettimanaleparttime == null ? BigDecimal.ZERO
										: new BigDecimal(ProdisSrvUtil.oreMinutiToMinuti(orariosettimanaleparttime)));
								pt.setTipoRipropPt(new TipoRipropPt());
								pt.getTipoRipropPt().setId(ConstantsProdis.PT_CENTRALINISTI);
								partTimes.add(pt);
							}
						}
					}
					if (quadro2Spicom.getPersonaledipendente().getDisabili() != null) {
						datiProvinciali.setnDisabiliInForza(
								quadro2Spicom.getPersonaledipendente().getDisabili().getNlavoratoritempopieno() == null
										? BigDecimal.ZERO
										: new BigDecimal(quadro2Spicom.getPersonaledipendente().getDisabili()
												.getNlavoratoritempopieno()));
						if (quadro2Spicom.getPersonaledipendente().getDisabili().getParttime() != null
								&& quadro2Spicom.getPersonaledipendente().getDisabili().getParttime().size() > 0) {
							ArrayList<Dettaglioparttime> listaPt = new ArrayList<Dettaglioparttime>();
							for (int i = 0; i < quadro2Spicom.getPersonaledipendente().getDisabili().getParttime()
									.size(); i++) {
								LinkedHashMap<String, String> dpt = (LinkedHashMap<String, String>) quadro2Spicom
										.getPersonaledipendente().getDisabili().getParttime().get(i);
								String numero = dpt.get("numero");
								String orariosettimanalecontrattuale = dpt.get("orariosettimanalecontrattuale");
								String orariosettimanaleparttime = dpt.get("orariosettimanaleparttime");

								PartTime pt = new PartTime();
								pt.setnPartTime(numero == null ? BigDecimal.ZERO : new BigDecimal(numero));
								pt.setOrarioSettContrattualeMin(orariosettimanalecontrattuale == null ? BigDecimal.ZERO
										: new BigDecimal(
												ProdisSrvUtil.oreMinutiToMinuti(orariosettimanalecontrattuale)));
								pt.setOrarioSettPartTimeMin(orariosettimanaleparttime == null ? BigDecimal.ZERO
										: new BigDecimal(ProdisSrvUtil.oreMinutiToMinuti(orariosettimanaleparttime)));
								pt.setTipoRipropPt(new TipoRipropPt());
								pt.getTipoRipropPt().setId(ConstantsProdis.PT_DISABILI);
								partTimes.add(pt);
							}
						}
					}
					if (quadro2Spicom.getPersonaledipendente().getMassofisioterapisti() != null) {
						datiProvinciali.setnTerariabMassofisNonved(quadro2Spicom.getPersonaledipendente()
								.getMassofisioterapisti().getNlavoratoritempopieno() == null ? BigDecimal.ZERO
										: new BigDecimal(quadro2Spicom.getPersonaledipendente().getMassofisioterapisti()
												.getNlavoratoritempopieno()));
						if (quadro2Spicom.getPersonaledipendente().getMassofisioterapisti().getParttime() != null
								&& quadro2Spicom.getPersonaledipendente().getMassofisioterapisti().getParttime()
										.size() > 0) {
							ArrayList<Dettaglioparttime> listaPt = new ArrayList<Dettaglioparttime>();
							for (int i = 0; i < quadro2Spicom.getPersonaledipendente().getMassofisioterapisti()
									.getParttime().size(); i++) {
								LinkedHashMap<String, String> dpt = (LinkedHashMap<String, String>) quadro2Spicom
										.getPersonaledipendente().getMassofisioterapisti().getParttime().get(i);
								String numero = dpt.get("numero");
								String orariosettimanalecontrattuale = dpt.get("orariosettimanalecontrattuale");
								String orariosettimanaleparttime = dpt.get("orariosettimanaleparttime");

								PartTime pt = new PartTime();
								pt.setnPartTime(numero == null ? BigDecimal.ZERO : new BigDecimal(numero));
								pt.setOrarioSettContrattualeMin(orariosettimanalecontrattuale == null ? BigDecimal.ZERO
										: new BigDecimal(
												ProdisSrvUtil.oreMinutiToMinuti(orariosettimanalecontrattuale)));
								pt.setOrarioSettPartTimeMin(orariosettimanaleparttime == null ? BigDecimal.ZERO
										: new BigDecimal(ProdisSrvUtil.oreMinutiToMinuti(orariosettimanaleparttime)));
								pt.setTipoRipropPt(new TipoRipropPt());
								pt.getTipoRipropPt().setId(ConstantsProdis.PT_TERAPISTI_MASSOF_NONVEDENTI);
								partTimes.add(pt);
							}
						}
					}
					if (quadro2Spicom.getPersonaledipendente().getTelelavoro() != null) {
						datiProvinciali.setnTelelavoroFt(quadro2Spicom.getPersonaledipendente().getTelelavoro()
								.getNlavoratoritempopieno() == null ? BigDecimal.ZERO
										: new BigDecimal(quadro2Spicom.getPersonaledipendente().getTelelavoro()
												.getNlavoratoritempopieno()));
						if (quadro2Spicom.getPersonaledipendente().getTelelavoro().getParttime() != null
								&& quadro2Spicom.getPersonaledipendente().getTelelavoro().getParttime().size() > 0) {
							ArrayList<Dettaglioparttime> listaPt = new ArrayList<Dettaglioparttime>();
							for (int i = 0; i < quadro2Spicom.getPersonaledipendente().getTelelavoro().getParttime()
									.size(); i++) {
								LinkedHashMap<String, String> dpt = (LinkedHashMap<String, String>) quadro2Spicom
										.getPersonaledipendente().getTelelavoro().getParttime().get(i);
								String numero = dpt.get("numero");
								String orariosettimanalecontrattuale = dpt.get("orariosettimanalecontrattuale");
								String orariosettimanaleparttime = dpt.get("orariosettimanaleparttime");

								PartTime pt = new PartTime();
								pt.setnPartTime(numero == null ? BigDecimal.ZERO : new BigDecimal(numero));
								pt.setOrarioSettContrattualeMin(orariosettimanalecontrattuale == null ? BigDecimal.ZERO
										: new BigDecimal(
												ProdisSrvUtil.oreMinutiToMinuti(orariosettimanalecontrattuale)));
								pt.setOrarioSettPartTimeMin(orariosettimanaleparttime == null ? BigDecimal.ZERO
										: new BigDecimal(ProdisSrvUtil.oreMinutiToMinuti(orariosettimanaleparttime)));
								pt.setTipoRipropPt(new TipoRipropPt());
								pt.getTipoRipropPt().setId(ConstantsProdis.PT_TELELAVORO);
								partTimes.add(pt);
							}
						}
					}
				}
				if (quadro2Spicom.getPersonaleNonDipendente() != null
						&& quadro2Spicom.getPersonaleNonDipendente().getLavoratoriDisabiliSomministrati() != null) {
					datiProvinciali.setnSomministratiFt(quadro2Spicom.getPersonaleNonDipendente()
							.getLavoratoriDisabiliSomministrati().getNlavoratoritempopieno() == null ? BigDecimal.ZERO
									: new BigDecimal(quadro2Spicom.getPersonaleNonDipendente()
											.getLavoratoriDisabiliSomministrati().getNlavoratoritempopieno()));
					if (quadro2Spicom.getPersonaleNonDipendente().getLavoratoriDisabiliSomministrati()
							.getParttime() != null
							&& quadro2Spicom.getPersonaleNonDipendente().getLavoratoriDisabiliSomministrati()
									.getParttime().size() > 0) {
						ArrayList<Dettaglioparttime> listaPt = new ArrayList<Dettaglioparttime>();
						for (int i = 0; i < quadro2Spicom.getPersonaleNonDipendente()
								.getLavoratoriDisabiliSomministrati().getParttime().size(); i++) {
							LinkedHashMap<String, String> dpt = (LinkedHashMap<String, String>) quadro2Spicom
									.getPersonaleNonDipendente().getLavoratoriDisabiliSomministrati().getParttime()
									.get(i);
							String numero = dpt.get("numero");
							String orariosettimanalecontrattuale = dpt.get("orariosettimanalecontrattuale");
							String orariosettimanaleparttime = dpt.get("orariosettimanaleparttime");

							PartTime pt = new PartTime();
							pt.setnPartTime(numero == null ? BigDecimal.ZERO : new BigDecimal(numero));
							pt.setOrarioSettContrattualeMin(orariosettimanalecontrattuale == null ? BigDecimal.ZERO
									: new BigDecimal(ProdisSrvUtil.oreMinutiToMinuti(orariosettimanalecontrattuale)));
							pt.setOrarioSettPartTimeMin(orariosettimanaleparttime == null ? BigDecimal.ZERO
									: new BigDecimal(ProdisSrvUtil.oreMinutiToMinuti(orariosettimanaleparttime)));
							pt.setTipoRipropPt(new TipoRipropPt());
							pt.getTipoRipropPt().setId(ConstantsProdis.PT_DISABILI_SOMM);
							partTimes.add(pt);
						}
					}
				}
				if (quadro2Spicom.getPersonaleNonDipendente() != null
						&& quadro2Spicom.getPersonaleNonDipendente().getLavoratoriDisabiliConv12bis14() != null) {
					datiProvinciali.setnConvenzioni12bis14Ft(quadro2Spicom.getPersonaleNonDipendente()
							.getLavoratoriDisabiliConv12bis14().getNlavoratoritempopieno() == null ? BigDecimal.ZERO
									: new BigDecimal(quadro2Spicom.getPersonaleNonDipendente()
											.getLavoratoriDisabiliConv12bis14().getNlavoratoritempopieno()));
					if (quadro2Spicom.getPersonaleNonDipendente().getLavoratoriDisabiliConv12bis14()
							.getParttime() != null
							&& quadro2Spicom.getPersonaleNonDipendente().getLavoratoriDisabiliConv12bis14()
									.getParttime().size() > 0) {
						ArrayList<Dettaglioparttime> listaPt = new ArrayList<Dettaglioparttime>();
						for (int i = 0; i < quadro2Spicom.getPersonaleNonDipendente().getLavoratoriDisabiliConv12bis14()
								.getParttime().size(); i++) {
							LinkedHashMap<String, String> dpt = (LinkedHashMap<String, String>) quadro2Spicom
									.getPersonaleNonDipendente().getLavoratoriDisabiliConv12bis14().getParttime()
									.get(i);
							String numero = dpt.get("numero");
							String orariosettimanalecontrattuale = dpt.get("orariosettimanalecontrattuale");
							String orariosettimanaleparttime = dpt.get("orariosettimanaleparttime");

							PartTime pt = new PartTime();
							pt.setnPartTime(numero == null ? BigDecimal.ZERO : new BigDecimal(numero));
							pt.setOrarioSettContrattualeMin(orariosettimanalecontrattuale == null ? BigDecimal.ZERO
									: new BigDecimal(ProdisSrvUtil.oreMinutiToMinuti(orariosettimanalecontrattuale)));
							pt.setOrarioSettPartTimeMin(orariosettimanaleparttime == null ? BigDecimal.ZERO
									: new BigDecimal(ProdisSrvUtil.oreMinutiToMinuti(orariosettimanaleparttime)));
							pt.setTipoRipropPt(new TipoRipropPt());
							pt.getTipoRipropPt().setId(ConstantsProdis.PT_DISABILI_CONVENZIONE);
							partTimes.add(pt);
						}
					}
				}
				// part time
				if (quadro2Spicom.getPersonaledipendente() != null
						&& quadro2Spicom.getPersonaledipendente().getDettaglioparttime() != null
						&& quadro2Spicom.getPersonaledipendente().getDettaglioparttime().length > 0) {

					for (Dettaglioparttime spicomPt : quadro2Spicom.getPersonaledipendente().getDettaglioparttime()) {
						PartTime pt = new PartTime();

						pt.setnPartTime(
								spicomPt.getNumero() == null ? BigDecimal.ZERO : new BigDecimal(spicomPt.getNumero()));
						pt.setOrarioSettContrattualeMin(spicomPt.getOrariosettimanalecontrattuale() == null
								? BigDecimal.ZERO
								: new BigDecimal(
										ProdisSrvUtil.oreMinutiToMinuti(spicomPt.getOrariosettimanalecontrattuale())));
						pt.setOrarioSettPartTimeMin(spicomPt.getOrariosettimanaleparttime() == null ? BigDecimal.ZERO
								: new BigDecimal(
										ProdisSrvUtil.oreMinutiToMinuti(spicomPt.getOrariosettimanaleparttime())));
						pt.setTipoRipropPt(new TipoRipropPt());
						pt.getTipoRipropPt().setId(ConstantsProdis.PT_ALTRE_CATEGORIA);
						partTimes.add(pt);
					}
				}
				datiProvinciali.setPartTimes(partTimes);
				// INTERMITTENTI
				if (quadro2Spicom.getPersonaledipendente() != null
						&& quadro2Spicom.getPersonaledipendente().getDettagliointermittenti() != null
						&& quadro2Spicom.getPersonaledipendente().getDettagliointermittenti().length > 0) {
					List<ProvIntermittenti> provIntermittentis = new ArrayList<ProvIntermittenti>();

					for (Dettagliointermittenti intermSpicom : quadro2Spicom.getPersonaledipendente()
							.getDettagliointermittenti()) {
						ProvIntermittenti intermittenti = new ProvIntermittenti();
						intermittenti.setnIntermittenti(intermSpicom.getNumero() == null ? BigDecimal.ZERO
								: new BigDecimal(intermSpicom.getNumero()));
						intermittenti.setOrarioSettimanaleContrattual(
								intermSpicom.getOrariosettimanalecontrattuale() == null ? BigDecimal.ZERO
										: new BigDecimal(ProdisSrvUtil
												.oreMinutiToMinuti(intermSpicom.getOrariosettimanalecontrattuale())));
						intermittenti.setOrarioSettimanaleSvolto(intermSpicom.getOrariosettimanalesvolto() == null
								? BigDecimal.ZERO
								: new BigDecimal(
										ProdisSrvUtil.oreMinutiToMinuti(intermSpicom.getOrariosettimanalesvolto())));
						provIntermittentis.add(intermittenti);
					}
					datiProvinciali.setProvIntermittentis(provIntermittentis);
				}

				// POSTI DI LAVORO
				if (quadro2Spicom.getPostilavoro() != null && quadro2Spicom.getPostilavoro().length > 0) {
					List<PostiLavoroDisp> postiLavoroDisps = new ArrayList<PostiLavoroDisp>();

					for (Postilavoro postiSpicom : quadro2Spicom.getPostilavoro()) {
						PostiLavoroDisp posto = new PostiLavoroDisp();
						posto.setFlgPresenzaBarriereArchite(postiSpicom.getBarrierearchitettoniche());
						posto.setDescCapacitaRichiesteContr(postiSpicom.getCapacitacontroindicazioni());
						posto.setCategoriaAssunzione(postiSpicom.getCategoriaass());
						posto.setCategoriaSoggetto(postiSpicom.getCategoriasogg());
						if (postiSpicom.getComuneass() != null && !"".equalsIgnoreCase(postiSpicom.getComuneass())
								&& !postiSpicom.getComuneass().startsWith("Z")) {
							Comune comune = (Comune) prospettoDad.getTfromMin(Comune.class, postiSpicom.getComuneass(),
									dataRif);
							posto.setComune(comune);
							if (comune == null) {
								addErrorProDImportErrori(errors, ConstantsProdis.PRO_T_COMUNE,
										comunicazioneSpicom.getIdTrasmissione(), postiSpicom.getComuneass(),
										ConstantsProdis.IMPORT_ERROR_COMUNE_STATO_ESTERO,
										"quadro2Spicom.getPostilavoro().getComuneass()");
							}
						} else if (postiSpicom.getComuneass() != null
								&& !"".equalsIgnoreCase(postiSpicom.getComuneass())
								&& postiSpicom.getComuneass().startsWith("Z")) {
							StatiEsteri stato = (StatiEsteri) prospettoDad.getTfromMin(StatiEsteri.class,
									postiSpicom.getComuneass(), dataRif);
							posto.setStatiEsteri(stato);
							if (stato == null) {
								addErrorProDImportErrori(errors, ConstantsProdis.PRO_T_STATI_ESTERI,
										comunicazioneSpicom.getIdTrasmissione(), postiSpicom.getComuneass(),
										ConstantsProdis.IMPORT_ERROR_COMUNE_STATO_ESTERO,
										"quadro2Spicom.getPostilavoro().getComuneass()");
							}
						}
						posto.setDescMansione(postiSpicom.getMansione());
						posto.setFlgRaggiungibMezziPubblici(postiSpicom.getMezzipubblici());
						posto.setnPosti(postiSpicom.getNposti() == null ? BigDecimal.ZERO
								: new BigDecimal(postiSpicom.getNposti()));
						if (postiSpicom.getQualificaprof() != null
								&& !"".equalsIgnoreCase(postiSpicom.getQualificaprof())) {
							Istat2001livello5 istat = (Istat2001livello5) prospettoDad
									.getTfromMin(Istat2001livello5.class, postiSpicom.getQualificaprof(), dataRif);
							posto.setIstat2001livello5(istat);
							if (istat == null) {
								addErrorProDImportErrori(errors, ConstantsProdis.PRO_T_ISTAT2001LIVELLO5,
										comunicazioneSpicom.getIdTrasmissione(), postiSpicom.getQualificaprof(),
										ConstantsProdis.IMPORT_ERROR_QUALIFICA,
										"quadro2Spicom.getPostilavoro().getQualificaprof()");
							}
						}
						posto.setFlgTurniNotturni(postiSpicom.getTurninotturni());
						postiLavoroDisps.add(posto);
					}
					datiProvinciali.setPostiLavoroDisps(postiLavoroDisps);
				}

				// Sopensione
				if (quadro2Spicom.getSospensioni() != null) {
					ProvSospensione sospensione = new ProvSospensione();
					if (quadro2Spicom.getSospensioni().getCausale() != null
							&& !"".equalsIgnoreCase(quadro2Spicom.getSospensioni().getCausale())) {
						CausaSospensione causa = (CausaSospensione) prospettoDad.getTfromMin(CausaSospensione.class,
								quadro2Spicom.getSospensioni().getCausale(), dataRif);
						sospensione.setCausaSospensione(causa);
						if (causa == null) {
							addErrorProDImportErrori(errors, ConstantsProdis.PRO_T_CAUSA_SOSPENSIONE,
									comunicazioneSpicom.getIdTrasmissione(),
									quadro2Spicom.getSospensioni().getCausale(),
									ConstantsProdis.IMPORT_ERROR_CAUSA_SOSPENSIONE,
									"quadro2Spicom.getSospensioni().getCausale()");
						}
					}
					sospensione.setnLavoratori(
							quadro2Spicom.getSospensioni().getNumerolavoratori() == null ? BigDecimal.ZERO
									: new BigDecimal(quadro2Spicom.getSospensioni().getNumerolavoratori()));
					if (quadro2Spicom.getSospensioni().getStato() != null
							&& !"".equalsIgnoreCase(quadro2Spicom.getSospensioni().getStato())) {
						StatoConcessione concessione = (StatoConcessione) prospettoDad.getTfromMin(
								StatoConcessione.class, quadro2Spicom.getSospensioni().getStato(), dataRif);
						sospensione.setStatoConcessione(concessione);
						if (concessione == null) {
							addErrorProDImportErrori(errors, ConstantsProdis.PRO_T_STATO_CONCESSIONE,
									comunicazioneSpicom.getIdTrasmissione(), quadro2Spicom.getSospensioni().getStato(),
									ConstantsProdis.IMPORT_ERROR_STATO_CONCESSIONE,
									"quadro2Spicom.getSospensioni().getStato()");
						}
					}
					sospensione.setDFineSospensioneQ2(quadro2Spicom.getSospensioni().getDataFineSospensione());
					datiProvinciali.setProvSospensione(sospensione);
				}
				prospettoProvincia.setDatiProvinciali(datiProvinciali);
				prospettoProvincias.add(prospettoProvincia);
			} // for su quadro 2 spicom end
		}

		// QUADRO 3
		if (prospettoSpicom.getQuadro3() != null) {

			if (prospettoSpicom.getQuadro3().getElencoriepilogativoprovinciale() != null
					&& prospettoSpicom.getQuadro3().getElencoriepilogativoprovinciale().length > 0) {
				for (Elencoriepilogativo riepilogativoSpicom : prospettoSpicom.getQuadro3()
						.getElencoriepilogativoprovinciale()) {
					ProspettoProvincia prosProv = mapProspettoProvincia.get(riepilogativoSpicom.getProvincia());

					prosProv.setRiepilogoProvinciale(new RiepilogoProvinciale());

					prosProv.getRiepilogoProvinciale()
							.setBaseComputoArt3(riepilogativoSpicom.getNumLavBaseComputoArt3() == null ? BigDecimal.ZERO
									: new BigDecimal(riepilogativoSpicom.getNumLavBaseComputoArt3()));
					prosProv.getRiepilogoProvinciale().setBaseComputoArt18(
							riepilogativoSpicom.getNumLavBaseComputoArt18() == null ? BigDecimal.ZERO
									: new BigDecimal(riepilogativoSpicom.getNumLavBaseComputoArt18()));
					prosProv.getRiepilogoProvinciale()
							.setFlgSospensioniInCorso(riepilogativoSpicom.getSospensioniincorso());
					prosProv.getRiepilogoProvinciale()
							.setCatCompensazioneCateProt(riepilogativoSpicom.getCategoriacompcatprotette());
					prosProv.getRiepilogoProvinciale()
							.setCatCompensazioneDisabili(riepilogativoSpicom.getCategoriacompdisabili());
					prosProv.getRiepilogoProvinciale()
							.setNumCatProtInForza(riepilogativoSpicom.getCatprotette() == null ? BigDecimal.ZERO
									: new BigDecimal(riepilogativoSpicom.getCatprotette()));
					prosProv.getRiepilogoProvinciale()
							.setNumDisabiliInForza(riepilogativoSpicom.getDisabili() == null ? BigDecimal.ZERO
									: new BigDecimal(riepilogativoSpicom.getDisabili()));
					prosProv.getRiepilogoProvinciale()
							.setNumPosizioniEsonerate(riepilogativoSpicom.getEsonerati() == null ? BigDecimal.ZERO
									: new BigDecimal(riepilogativoSpicom.getEsonerati()));

					if (riepilogativoSpicom.getNumerocompcatprotette() != null
							&& !"0".equalsIgnoreCase(riepilogativoSpicom.getNumerocompcatprotette())) {
						prosProv.getRiepilogoProvinciale().setNumCompensazioniCateProt(
								new BigDecimal(riepilogativoSpicom.getNumerocompcatprotette()));
					} else {
						prosProv.getRiepilogoProvinciale().setNumCompensazioniCateProt(BigDecimal.ZERO);
					}
					if (riepilogativoSpicom.getNumerocompdisabili() != null
							&& !"0".equalsIgnoreCase(riepilogativoSpicom.getNumerocompdisabili())) {
						prosProv.getRiepilogoProvinciale().setNumCompensazioneDisabili(
								new BigDecimal(riepilogativoSpicom.getNumerocompdisabili()));
					} else {
						prosProv.getRiepilogoProvinciale().setNumCompensazioneDisabili(BigDecimal.ZERO);
					}
					if (riepilogativoSpicom.getProvincia() != null
							&& !"".equalsIgnoreCase(riepilogativoSpicom.getProvincia())) {
						Provincia provincia = (Provincia) prospettoDad.getTfromMin(Provincia.class,
								riepilogativoSpicom.getProvincia(), dataRif);
						prosProv.setProvincia(provincia);
						if (provincia == null) {
							addErrorProDImportErrori(errors, ConstantsProdis.PRO_T_PROVINCIA,
									comunicazioneSpicom.getIdTrasmissione(), riepilogativoSpicom.getProvincia(),
									ConstantsProdis.IMPORT_ERROR_PROVINCIA, "riepilogativoSpicom.getProvincia()");
						}
					}
					prosProv.getRiepilogoProvinciale().setQuotaRiservaArt18(
							riepilogativoSpicom.getQuotariservacatprotette() == null ? BigDecimal.ZERO
									: new BigDecimal(riepilogativoSpicom.getQuotariservacatprotette()));
					prosProv.getRiepilogoProvinciale().setQuotaRiservaDisabili(
							riepilogativoSpicom.getQuotariservadisabili() == null ? BigDecimal.ZERO
									: new BigDecimal(riepilogativoSpicom.getQuotariservadisabili()));
					prosProv.getRiepilogoProvinciale().setNumScopertureCatProt(
							riepilogativoSpicom.getScoperturecatprotette() == null ? BigDecimal.ZERO
									: new BigDecimal(riepilogativoSpicom.getScoperturecatprotette()));
					prosProv.getRiepilogoProvinciale().setNumScopertureDisabili(
							riepilogativoSpicom.getScoperturedisabili() == null ? BigDecimal.ZERO
									: new BigDecimal(riepilogativoSpicom.getScoperturedisabili()));
					// campi presenti in tabella , non valorizzatinel DTO di spicom ma che devono
					// essere settati 0 perchè poi calcolati dalla procedura
					prosProv.getRiepilogoProvinciale().setNumLavoratoriBaseComputo(BigDecimal.ZERO);
					prosProv.getRiepilogoProvinciale().setNumLavoratoriSospensione(BigDecimal.ZERO);
					prosProv.getRiepilogoProvinciale().setNumCatProtInForzaContDis(BigDecimal.ZERO);

				}
			}
			prospetto.setNote(prospettoSpicom.getQuadro3().getNote());

			if (prospettoSpicom.getQuadro3().getRiepilogonazionale() != null) {
				Riepilogonazionale riepilogoSpicom = prospettoSpicom.getQuadro3().getRiepilogonazionale();
				prospetto.setRiepilogoNazionale(new RiepilogoNazionale());
				prospetto.getRiepilogoNazionale()
						.setBaseComputoArt3(riepilogoSpicom.getNumLavBaseComputoArt3() == null ? BigDecimal.ZERO
								: new BigDecimal(riepilogoSpicom.getNumLavBaseComputoArt3()));
				prospetto.getRiepilogoNazionale()
						.setBaseComputoArt18(riepilogoSpicom.getNumLavBaseComputoArt18() == null ? BigDecimal.ZERO
								: new BigDecimal(riepilogoSpicom.getNumLavBaseComputoArt18()));
				prospetto.getRiepilogoNazionale().setFlgSospensioniInCorso(riepilogoSpicom.getSospensioniincorso());
				prospetto.getRiepilogoNazionale()
						.setNumCatProtInForza(riepilogoSpicom.getCatprotette() == null ? BigDecimal.ZERO
								: new BigDecimal(riepilogoSpicom.getCatprotette()));
				prospetto.getRiepilogoNazionale()
						.setNumDisabiliInForza(riepilogoSpicom.getDisabili() == null ? BigDecimal.ZERO
								: new BigDecimal(riepilogoSpicom.getDisabili()));
				prospetto.getRiepilogoNazionale()
						.setNumPosizioniEsonerate(riepilogoSpicom.getEsoneri() == null ? BigDecimal.ZERO
								: new BigDecimal(riepilogoSpicom.getEsoneri()));
				prospetto.getRiepilogoNazionale()
						.setQuotaRiservaArt18(riepilogoSpicom.getQuotariservacatprotette() == null ? BigDecimal.ZERO
								: new BigDecimal(riepilogoSpicom.getQuotariservacatprotette()));
				prospetto.getRiepilogoNazionale()
						.setQuotaRiservaDisabili(riepilogoSpicom.getQuotariservadisabili() == null ? BigDecimal.ZERO
								: new BigDecimal(riepilogoSpicom.getQuotariservadisabili()));
				prospetto.getRiepilogoNazionale().setNumScopertCategorieProtette(
						riepilogoSpicom.getScoperturecatprotette() == null ? BigDecimal.ZERO
								: new BigDecimal(riepilogoSpicom.getScoperturecatprotette()));
				prospetto.getRiepilogoNazionale()
						.setNumScopertDisabili(riepilogoSpicom.getScoperturedisabili() == null ? BigDecimal.ZERO
								: new BigDecimal(riepilogoSpicom.getScoperturedisabili()));
				prospetto.getRiepilogoNazionale()
						.setQuotaEsuberiArt18(riepilogoSpicom.getQuotaEsuberiArt18() == null ? BigDecimal.ZERO
								: new BigDecimal(riepilogoSpicom.getQuotaEsuberiArt18()));
				// campi presenti in tabella , non valorizzatinel DTO di spicom ma che devono
				// essere settati 0 perchè poi calcolati dalla procedura
				prospetto.getRiepilogoNazionale().setNumLavoratoriSospensione(BigDecimal.ZERO);
				prospetto.getRiepilogoNazionale().setNumCatProtInForzaCntDis(BigDecimal.ZERO);
				prospetto.getRiepilogoNazionale().setNumScopertCatProtReali(BigDecimal.ZERO);
				prospetto.getRiepilogoNazionale().setNumScopertDisabiliReali(BigDecimal.ZERO);
				prospetto.getRiepilogoNazionale().setNumLavoratoriBaseComputo(BigDecimal.ZERO);

			}
		}

		if (comunicazioneSpicom.getDatiInvio() != null) {
			DatiInvioDTO datiInvioSpicom = comunicazioneSpicom.getDatiInvio();
			if (datiInvioSpicom.getSoggettoComunicante() != null
					&& !"".equalsIgnoreCase(datiInvioSpicom.getSoggettoComunicante())) {
				Soggetti soggetti = (Soggetti) prospettoDad.getTfromMin(Soggetti.class,
						datiInvioSpicom.getSoggettoComunicante(), dataRif);
				prospetto.setSoggetti(soggetti);
				if (soggetti == null) {
					addErrorProDImportErrori(errors, ConstantsProdis.PRO_T_SOGGETTI,
							comunicazioneSpicom.getIdTrasmissione(), datiInvioSpicom.getSoggettoComunicante(),
							ConstantsProdis.IMPORT_ERROR_TIPO_SOGGETTI, "datiInvioSpicom.getSoggettoComunicante()");
				}
			}
			prospetto.setCfStudioProfessionale(datiInvioSpicom.getCodFiscaleSoggettoComunicante());
			prospetto.setDataTimbroPostale(datiInvioSpicom.getDataInvio());
			prospetto.setEmailSoggettoComunicazione(datiInvioSpicom.getEmailDelegato());
			StatoVerifica statoVerifica = new StatoVerifica();
			statoVerifica.setId(ConstantsProdis.STATO_VERIFICA_VERIFICATA);
			prospetto.setStatoVerifica(statoVerifica);
//			prospetto.setNumeroProtocollo(datiInvioSpicom.getProtocolloSistema() == null ? BigDecimal.ZERO : new BigDecimal(datiInvioSpicom.getProtocolloSistema()));
			if (datiInvioSpicom.getTipoComunicazione() != null
					&& !"".equalsIgnoreCase(datiInvioSpicom.getTipoComunicazione())) {
				Comunicazione com = (Comunicazione) prospettoDad.getTfromMin(Comunicazione.class,
						datiInvioSpicom.getTipoComunicazione(), dataRif);
				prospetto.setComunicazione(com);
				if (com == null) {
					addErrorProDImportErrori(errors, ConstantsProdis.PRO_T_COMUNICAZIONE,
							comunicazioneSpicom.getIdTrasmissione(), datiInvioSpicom.getTipoComunicazione(),
							ConstantsProdis.IMPORT_ERROR_TIPO_COMUNICAZIONE, "datiInvioSpicom.getTipoComunicazione()");
				}
			}
		}
		prospetto.setProspettoProvincias(prospettoProvincias);
		return prospetto;
	}

	private Utente getUtente() {
		Utente utente = null;
		if (ProdisThreadLocalContainer.UTENTE_CONNESSO.get() != null) {
			utente = ProdisThreadLocalContainer.UTENTE_CONNESSO.get();
		}
		return utente;
	}
}
