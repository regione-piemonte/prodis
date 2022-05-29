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

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import it.csi.prodis.prodisweb.ejb.business.be.dad.ProspettoDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.base.BaseService;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto.PostConferemaRiepilogoProspettoRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto.PostConferemaRiepilogoProspettoResponse;
import it.csi.prodis.prodisweb.ejb.entity.ProDParametri;
import it.csi.prodis.prodisweb.ejb.util.ConstantsProdis;
import it.csi.prodis.prodisweb.ejb.util.ProdisSrvUtil;
import it.csi.prodis.prodisweb.ejb.util.ValidatorRiepilogo;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.ejb.util.mail.AddressMessageBean;
import it.csi.prodis.prodisweb.ejb.util.mail.GestioneMail;
import it.csi.prodis.prodisweb.ejb.util.mail.MailConstants;
import it.csi.prodis.prodisweb.lib.dto.ApiError;
import it.csi.prodis.prodisweb.lib.dto.common.Ruolo;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Cpi;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.StatoProspetto;
import it.csi.prodis.prodisweb.lib.dto.error.MsgProdis;
import it.csi.prodis.prodisweb.lib.dto.prospetto.Prospetto;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProspettoProvincia;
import it.csi.prodis.prodisweb.lib.dto.prospetto.RiepilogoProvinciale;
import it.csi.prodis.prodisweb.lib.exception.SpicomIntegrationException;
import it.csi.prodis.prodisweb.lib.util.ProdisDateUtils;
import it.csi.prodis.prodisweb.protocollo.hepler.AdapterIup;
import it.csi.prodis.prodisweb.spicom.ProfConstants;
import it.csi.prodis.prodisweb.spicom.helper.AdapterSpicom;

/**
 * PostProspetto
 */
public class ConfermaRiepilogoService
		extends BaseService<PostConferemaRiepilogoProspettoRequest, PostConferemaRiepilogoProspettoResponse> {

	protected final ProspettoDad prospettoDad;

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the helper for the configuration
	 * @param prospettoDad        the DAD for the prospetto
	 */
	public ConfermaRiepilogoService(ConfigurationHelper configurationHelper, ProspettoDad prospettoDad) {
		super(configurationHelper);
		this.prospettoDad = prospettoDad;
	}

	@Override
	protected void execute() {

		Date dataOdierna = new Date();

		Prospetto prospetto = request.getConferemaRiepilogoProspettoRequest().getProspetto();
		Long idProspetto = prospetto.getId();
		Ruolo ruolo = request.getConferemaRiepilogoProspettoRequest().getRuolo();

		if (ruolo == null) {
			response.addApiError(MsgProdis.PROCONE0001.getError());
			return;
		}

		ValidatorRiepilogo valida = new ValidatorRiepilogo(prospettoDad);
		List<ApiError> apiErrors = new ArrayList<ApiError>();
		valida.validaConfermaEProsegui(prospetto, ruolo, apiErrors);

		if (apiErrors.size() > 0) {
			response.addApiErrors(apiErrors);
			return;
		}

		/*
		 * 
		 * 1) protocollazione (su IUP) => se protocollo KO esce (con errore ***) - salva
		 * sul db STATO: PRESENTATO; PROTOCOLLO (ANNO, NUMERO, DATA PROT), DATA TIMBRO
		 * POSTALE (fa l'update sulla data timbro postale se la trova NULL),
		 * FLG_INVIO_MINISTERO = 'S' => se KO esce con errore GRAVE(*) - se è una
		 * rettifica o un annullamento salva sul prospetto precedente (estratto per
		 * ID_PROSPETTO_PRECEDENTE) lo stato: RETTIFICATO o ANNULLATO => se KO esce con
		 * errore GRAVE(*)
		 * 
		 * 2) invio a SPICOM: - se è una rettifica o un annullamento, verificare che il
		 * campo CODICE_COMUNICAZIONE_PRECED sia NOT NULL => se è NULL esce e va alla
		 * pagina di notifica invio prospetto indicando che c'è stato un problema
		 * durante invio, altrimenti prosegue - chiamata a SPICOM => se chiamata a
		 * SPICOM KO (es: spicom non disponibile, oppure oggettone non valido per
		 * spicom) va alla pagina di notifica invio prospetto indicando che il codice
		 * regionale verrà generato appena il Sistema è disponibile - salva sul db
		 * CODICE_COMUNICAZIONE = codice regionale ritornato da SPICOM, DATA_INVIO =
		 * sysdate se invece la chiamata è OK, fa update su prospetto:
		 * CODICE_COMUNICAZIONE = codice regionale ottenuto da SPICOM, DATA_INVIO =
		 * sysdate
		 * 
		 * 3) invio mail con indicazione del protocollo ottenuto ed eventualmente codice
		 * regionale ottenuto (l'indirizzo destinatario è
		 * PRO_D_PROSPETTO.EMAIL_NOTIFICA)
		 * 
		 * 4) presenta pagina "notifica invio prospetto" con indicazioni del protocollo
		 * ottenuto ed eventualmente codice regionale ottenuto (con eventuale
		 * segnalazioni di errore)
		 */

		// salvo il prospetto
		
		if(ruolo.getIdSoggetti() != null && ruolo.getIdSoggetti() > 0 ) {
			prospetto.setCfStudioProfessionale(ruolo.getCodiceFiscale());	
			
		} else {
			if(!ruolo.getRuolo().toUpperCase().startsWith(ConstantsProdis.RUOLO_AMMINISTRATORE_PRODIS.toUpperCase())){
				prospetto.setCfStudioProfessionale(null);
				if(prospetto.getSoggetti() != null) {
					prospetto.getSoggetti().setId(null);
				}
			}
		}
		
		prospettoDad.updateProspettoRiepilogo(prospetto);

		prospetto = prospettoDad.getProspettoCompleto(idProspetto);

		if (prospetto.getStatoProspetto().getId() != ConstantsProdis.PROSPETTO_STATO_BOZZA
				&& prospetto.getStatoProspetto().getId() != ConstantsProdis.PROSPETTO_STATO_DA_FIRMARE) {
			response.addApiError(MsgProdis.PROSPIE0006.getError());
			return;
		}

		AdapterIup iup = AdapterIup.getInstance();

		if (iup == null) {
			log.error("[ConfermaRiepilogoService::execute]", "ERRORE nella protocollazione");
			response.addApiError(MsgProdis.PROSPIE0003.getError());
			return;
		}

		List<ProDParametri> parametri = prospettoDad.getParametri();
		boolean spicomAbilitato = false;
		
		try {

			Properties properties = new Properties();

			for (ProDParametri param : parametri) {
				if (param == null)
					continue;

				if (ConstantsProdis.PARAMETRO_PROT_IUP_CODUT.equals(param.getDsNome())) {
					properties.setProperty("codiceUtente", param.getDsValore());

				} else if (ConstantsProdis.PARAMETRO_PROT_IUP_IDAOO.equals(param.getDsNome())) {
					properties.setProperty("idAOO", param.getDsValore());

				} else if (ConstantsProdis.PARAMETRO_PROT_IUP_DENAOO.equals(param.getDsNome())) {
					properties.setProperty("denominazioneAOO", param.getDsValore());

				} else if (ConstantsProdis.PARAMETRO_PROT_IUP_IDENTE.equals(param.getDsNome())) {
					properties.setProperty("idEnte", param.getDsValore());

				} else if (ConstantsProdis.PARAMETRO_PROT_IUP_CLASS.equals(param.getDsNome())) {
					properties.setProperty("indiceClassificazionePrincipale", param.getDsValore());

				} else if (ConstantsProdis.PARAMETRO_PROT_IUP_ABILITAZIONE.equals(param.getDsNome())) {
					properties.setProperty("abilitazione", param.getDsValore());

				} else if (ConstantsProdis.PARAMETRO_PROT_FINTO.equals(param.getDsNome())) {
					properties.setProperty("numeroProtocolloFinto", param.getDsValore());

				} else if (ConstantsProdis.PARAMETRO_SPICOM_ABILITATO.equals(param.getDsNome())) {
					if("S".equals(param.getDsValore())) {
						spicomAbilitato = true;
					}
				}
			}

			String cfAzienda = prospetto.getDatiAzienda().getCfAzienda();
			properties.setProperty("codFiscale", cfAzienda);
			properties.setProperty("partitaIVA", cfAzienda != null && cfAzienda.length() == 11 ? cfAzienda : "");
			properties.setProperty("denominazione", prospetto.getDatiAzienda().getDenominazioneDatoreLavoro());
			properties.setProperty("indirizzo", prospetto.getDatiAzienda().getSedeLegale().getIndirizzo());
			properties.setProperty("capComune", prospetto.getDatiAzienda().getSedeLegale().getCapSede());

			StringBuffer oggetto = new StringBuffer("Prospetto disabili per l'impresa: ");
			oggetto.append(prospetto.getDatiAzienda().getDenominazioneDatoreLavoro());
			oggetto.append(" alla data del ");
			oggetto.append(ProdisSrvUtil.getStringDate(prospetto.getDataRiferimentoProspetto()));

			properties.setProperty("oggetto", oggetto.toString());

			properties.setProperty("tipoDocumento", "");

			Properties protocollo = iup.staccaProtocollo(properties);
			String numeroProt = protocollo.getProperty("numero");
			String annoProt = protocollo.getProperty("anno");
			String dataProtocolloStr = protocollo.getProperty("dataProtocollo");

			if (numeroProt == null || numeroProt.trim().length() == 0) {
				log.error("[ConfermaRiepilogoService::execute] ERRORE nella protocollazione", "");
				response.addApiError(MsgProdis.PROSPIE0003.getError());
				return;
			}

			prospetto.setNumeroProtocollo(new BigDecimal(numeroProt));
			prospetto.setAnnoProtocollo(new BigDecimal(annoProt));
			prospetto.setDataProtocollo(ProdisSrvUtil.convertiStringaInData(dataProtocolloStr));
			
			if(prospetto.getDataTimbroPostale() == null) {
				prospetto.setDataTimbroPostale(dataOdierna);
			}

		} catch (Exception e) {
			log.error("[ConfermaRiepilogoService::execute] ERRORE nella protocollazione", e);
			response.addApiError(MsgProdis.PROSPIE0003.getError());
			return;
		}

		AdapterSpicom spicom = AdapterSpicom.getInstance();

		// invio del prospetto a SPICOM
		String codiceRegionale = null;

		if (spicomAbilitato) {
			try {
				
				codiceRegionale = spicom.inviaComunicazione(prospetto, ruolo);
	
				prospetto.setCodiceComunicazione(codiceRegionale);
				prospetto.setFlgInvioMinistero("S");
				prospetto.setDataInvio(dataOdierna);
	
			} catch (SpicomIntegrationException se) {
				log.error("[ConfermaRiepilogoService::execute] ERRORE nella chiamata a spicom", se);
			}
	
			if (codiceRegionale != null) {
				prospetto.setCodiceComunicazione(codiceRegionale);
	
			} else {
				log.error("[ConfermaRiepilogoService::execute]", " ERRORE nella chiamata a spicom");
				response.addWarnings(MsgProdis.PROSPIW0002.getError());
			}
		} else {
			log.info("[ConfermaRiepilogoService::execute]", " Cominicazione SPICOM disabilitata");
		}

		StatoProspetto statoPresentato = prospettoDad
				.getStatoProspetto(Long.valueOf(ConstantsProdis.PROSPETTO_STATO_PRESENTATA));

		prospetto.setStatoProspetto(statoPresentato);

		Prospetto prospettoConfermato = prospettoDad.updateConfermaRiepilogo(prospetto);

		boolean annullamento = false;
		if (prospetto.getIdProspettoPrecedente() != null) {
			Prospetto prospettoPrec = prospettoDad.getProspetto(prospetto.getIdProspettoPrecedente());

			if (prospettoPrec != null && prospettoPrec.getId() != null) {
				if (prospettoPrec.getStatoProspetto().getId() == ConstantsProdis.PROSPETTO_STATO_IN_RETTIFICA) {
					prospettoDad.updateStatoProspetto(prospettoPrec.getId(),
							Long.valueOf(ConstantsProdis.PROSPETTO_STATO_RETTIFICATA));

				} else if (prospettoPrec.getStatoProspetto()
						.getId() == ConstantsProdis.PROSPETTO_STATO_IN_ANNULLAMENTO) {
					annullamento = true;
					prospettoDad.updateStatoProspetto(prospettoPrec.getId(),
							Long.valueOf(ConstantsProdis.PROSPETTO_STATO_ANNULLATA));
				}
			}
		}

		if (prospettoConfermato == null) {
			response.addApiError(MsgProdis.PROSPIE0004.getError());
			return;
		}

		response.setProspetto(prospettoConfermato);

		// parametri configurazione mail
		Properties mailProp = new Properties();
		for (ProDParametri pr : parametri) {
			if (ConstantsProdis.PARAMETRO_MAIL.startsWith("mail")) {
				mailProp.setProperty(pr.getDsNome(), pr.getDsValore());
			}
		}

		if(annullamento) {			
			inviaMailReferente(prospettoConfermato, prospetto, mailProp);
		} else {
			inviaMail(prospettoConfermato, prospetto, mailProp);
		}
	}

	// nuova specifica 2021
	private void inviaMail(Prospetto prospettoConfermato, Prospetto prospetto, Properties mailProp) {
		log.debug("[ConfermaRiepilogoService::inviaMail]", "BEGIN");

		mailProp.setProperty(ConstantsProdis.PARAMETRO_MAIL_SUBJECT, MailConstants.MAIL_SUBJECT);

		boolean esistonoScopertureDisabili = false;
		boolean esistonoScopertureCatProt = false;

		List<AddressMessageBean> listaMail = new ArrayList<AddressMessageBean>();

		boolean fuoriTerminiChk = false;

		Date termineProspetto = prospettoDad.getDataTermineProspettoParametri();
		Date sysDate = new Date();
		//Controllo vecchio
		/*
		if (prospetto.getDataRiferimentoProspetto().after(termineProspetto)) {
			fuoriTerminiChk = true;
		}*/
		
		Date dataUltimaPrec = ProdisSrvUtil
				.convertiStringaInData("31/12/" + (Integer.parseInt(ProdisSrvUtil.getAnnoCorrenteStringa()) - 1));
		
		
		
		//Controllo nuovo
		if (sysDate.after(termineProspetto) && !ProdisSrvUtil.removeTime(prospetto.getDataRiferimentoProspetto()).equals(ProdisSrvUtil.removeTime(dataUltimaPrec))) {
			fuoriTerminiChk = true;
		}
		
		// costruisce messaggio per mail CPI sedi operative
		BigDecimal cpiSedeId = prospetto.getDatiAzienda().getSedeLegale().getComune().getIdTCpi();

		for (ProspettoProvincia prospettoProv : prospetto.getProspettoProvincias()) {

			RiepilogoProvinciale riepilogo = prospettoProv.getRiepilogoProvinciale();

			boolean esistonoScopertureDisabiliProv = false;
			boolean esistonoScopertureCatProtProv = false;

			if (riepilogo.getNumScopertureDisabili() != null
					&& riepilogo.getNumScopertureDisabili().compareTo(BigDecimal.ZERO) > 0) {
				esistonoScopertureDisabili = true;
				esistonoScopertureDisabiliProv = true;

			}
			if (riepilogo.getNumScopertureCatProt() != null
					&& riepilogo.getNumScopertureCatProt().compareTo(BigDecimal.ZERO) > 0) {
				esistonoScopertureCatProt = true;
				esistonoScopertureCatProtProv = true;
			}

			if ((esistonoScopertureDisabiliProv || esistonoScopertureCatProtProv) && fuoriTerminiChk) {
				BigDecimal cpiId = prospettoProv.getDatiProvinciali().getProspettoProvSede().getComune().getIdTCpi();

				if (cpiId != null && cpiId.compareTo(cpiSedeId) != 0) {
					Cpi cpiSede = prospettoDad.getCpi(cpiId.longValue());

					if (cpiSede.getDsMail() != null) {

						String messaggio = MessageFormat.format(MailConstants.SCOPERTURE_TEMPLATE,
								getMsgScoperture(esistonoScopertureDisabiliProv, esistonoScopertureCatProtProv));

						String testo = MessageFormat.format(MailConstants.MAIL_PROSPETTO_TEMPLATE,
								mailProp.get(ConstantsProdis.PARAMETRO_MAIL_SUBJECT),
								prospetto.getDatiAzienda().getCfAzienda(),
								prospetto.getDatiAzienda().getDenominazioneDatoreLavoro(),
								prospettoConfermato.getAnnoProtocollo().toString(),
								prospettoConfermato.getNumeroProtocollo().toString(),
								ProdisDateUtils.formatDate(prospettoConfermato.getDataProtocollo()),
								prospettoConfermato.getCodiceComunicazione() != null
										? prospettoConfermato.getCodiceComunicazione()
										: "Non presente.",
								messaggio, "");

						listaMail.add(new AddressMessageBean(cpiSede.getDsMail(), testo));
					}
				}
			}
		}

		String mesgScoperture = "";
		if ((esistonoScopertureDisabili || esistonoScopertureCatProt) && fuoriTerminiChk) {
			if (cpiSedeId != null) {
				mesgScoperture = MessageFormat.format(MailConstants.SCOPERTURE_TEMPLATE,
						getMsgScoperture(esistonoScopertureDisabili, esistonoScopertureCatProt));

				Cpi cpiSedeLegale = prospettoDad.getCpi(cpiSedeId.longValue());
				// solo se c'è la mail
				if (cpiSedeLegale.getDsMail() != null) {
					String testo = MessageFormat.format(MailConstants.MAIL_PROSPETTO_TEMPLATE,
							mailProp.get(ConstantsProdis.PARAMETRO_MAIL_SUBJECT),
							prospetto.getDatiAzienda().getCfAzienda(),
							prospetto.getDatiAzienda().getDenominazioneDatoreLavoro(),
							prospettoConfermato.getAnnoProtocollo().toString(),
							prospettoConfermato.getNumeroProtocollo().toString(),
							ProdisDateUtils.formatDate(prospettoConfermato.getDataProtocollo()),
							prospettoConfermato.getCodiceComunicazione() != null
									? prospettoConfermato.getCodiceComunicazione()
									: "Non presente.",
							mesgScoperture != null ? mesgScoperture : "", "");

					listaMail.add(new AddressMessageBean(cpiSedeLegale.getDsMail(), testo));
				}
			}
		}

		// messaggio per il referente aziendale
		mesgScoperture = "";
		if (esistonoScopertureDisabili || esistonoScopertureCatProt) {
			mesgScoperture = MessageFormat.format(MailConstants.SCOPERTURE_TEMPLATE,
					getMsgScoperture(esistonoScopertureDisabili, esistonoScopertureCatProt));
		}

		String testoTermini = "";
		if (fuoriTerminiChk) {
			testoTermini = "La dichiarazione fatta durante l’anno in corso non sostituisce la dichiarazione \"ufficiale\" da effettuare nel \n"
					+ "periodo definito dal Ministero, dato che è intercorsa una variazione di organico rispetto alla dichiarazione \n"
					+ "precedente.";
		}

		String testo = MessageFormat.format(MailConstants.MAIL_PROSPETTO_TEMPLATE,
				mailProp.get(ConstantsProdis.PARAMETRO_MAIL_SUBJECT), prospetto.getDatiAzienda().getCfAzienda(),
				prospetto.getDatiAzienda().getDenominazioneDatoreLavoro(),
				prospettoConfermato.getAnnoProtocollo().toString(),
				prospettoConfermato.getNumeroProtocollo().toString(),
				ProdisDateUtils.formatDate(prospettoConfermato.getDataProtocollo()),
				prospettoConfermato.getCodiceComunicazione() != null ? prospettoConfermato.getCodiceComunicazione()
						: "Non presente.",
				mesgScoperture != null ? mesgScoperture : "", testoTermini);

		listaMail.add(new AddressMessageBean(prospetto.getEmailNotifica(), testo));

		// invia le mail
		StringBuilder mailErrorList = null;
		for (AddressMessageBean mail : listaMail) {
			mailProp.setProperty(ConstantsProdis.PARAMETRO_MAIL_TO, mail.getIndirizzo());
			mailProp.setProperty("mail.text", mail.getMessaggio());

			GestioneMail mailMgr = new GestioneMail(mailProp);

			try {
				mailMgr.inviaMail();

			} catch (Exception e) {
				log.warn("[ConfermaRiepilogoService::inviaMail] ERRORE mail server", e);
				if (mailErrorList == null) {
					mailErrorList = new StringBuilder();
				}
				mailErrorList.append(mail.getIndirizzo()).append("  ");
			}
		}

		if (mailErrorList != null) {
			response.addApiError(MsgProdis.PROSPIW0005.getError());
		}
		log.debug("[ConfermaRiepilogoService::inviaMail]", "END");
	}

	private String getMsgScoperture(boolean esistonoScopertureDisabili, boolean esistonoScopertureCatProt) {
		String scoperture = null;
		if (esistonoScopertureDisabili && esistonoScopertureCatProt) {
			scoperture = "disabili e categorie protette";
		} else if (esistonoScopertureDisabili) {
			scoperture = "disabili";
		} else {
			scoperture = "categorie protette";
		}

		return scoperture;
	}
	
	private void inviaMailReferente(Prospetto prospettoConfermato, Prospetto prospetto, Properties mailProp) {
		log.debug("[inviaMailReferente::inviaMail]", "BEGIN");

		mailProp.setProperty(ConstantsProdis.PARAMETRO_MAIL_SUBJECT, MailConstants.MAIL_SUBJECT);

		List<AddressMessageBean> listaMail = new ArrayList<AddressMessageBean>();

		// messaggio per il referente aziendale
		String mesgScoperture = "";
		String testoTermini = "";

		String testo = MessageFormat.format(MailConstants.MAIL_PROSPETTO_TEMPLATE,
				mailProp.get(ConstantsProdis.PARAMETRO_MAIL_SUBJECT), prospetto.getDatiAzienda().getCfAzienda(),
				prospetto.getDatiAzienda().getDenominazioneDatoreLavoro(),
				prospettoConfermato.getAnnoProtocollo().toString(),
				prospettoConfermato.getNumeroProtocollo().toString(),
				ProdisDateUtils.formatDate(prospettoConfermato.getDataProtocollo()),
				prospettoConfermato.getCodiceComunicazione() != null ? prospettoConfermato.getCodiceComunicazione()
						: "Non presente.",
				mesgScoperture != null ? mesgScoperture : "", testoTermini);

		listaMail.add(new AddressMessageBean(prospetto.getEmailNotifica(), testo));

		// invia le mail
		StringBuilder mailErrorList = null;
		for (AddressMessageBean mail : listaMail) {
			mailProp.setProperty(ConstantsProdis.PARAMETRO_MAIL_TO, mail.getIndirizzo());
			mailProp.setProperty("mail.text", mail.getMessaggio());

			GestioneMail mailMgr = new GestioneMail(mailProp);

			try {
				mailMgr.inviaMail();

			} catch (Exception e) {
				log.warn("[inviaMailReferente::inviaMail] ERRORE mail server", e);
				if (mailErrorList == null) {
					mailErrorList = new StringBuilder();
				}
				mailErrorList.append(mail.getIndirizzo()).append("  ");
			}
		}

		if (mailErrorList != null) {
			response.addApiError(MsgProdis.PROSPIW0005.getError());
		}
		log.debug("[inviaMailReferente::inviaMail]", "END");		
	}
	
}
