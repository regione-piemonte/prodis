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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import it.csi.prodis.prodisweb.ejb.business.be.dad.ProspettoDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto.ReinviaProspettoRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto.ReinviaProspettoResponse;
import it.csi.prodis.prodisweb.ejb.entity.ProDParametri;
import it.csi.prodis.prodisweb.ejb.exception.NotFoundException;
import it.csi.prodis.prodisweb.ejb.util.ConstantsProdis;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.ejb.util.mail.AddressMessageBean;
import it.csi.prodis.prodisweb.ejb.util.mail.GestioneMail;
import it.csi.prodis.prodisweb.ejb.util.mail.MailConstants;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.StatoProspetto;
import it.csi.prodis.prodisweb.lib.dto.prospetto.Prospetto;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ReinviaProspetto;
import it.csi.prodis.prodisweb.lib.exception.SpicomIntegrationException;
import it.csi.prodis.prodisweb.lib.util.ProdisDateUtils;
import it.csi.prodis.prodisweb.spicom.helper.AdapterSpicom;

public class ReinviaProspettoService extends BaseProspettoService<ReinviaProspettoRequest, ReinviaProspettoResponse> {

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the helper for the configuration
	 * @param prospettoDad        the DAD for the prospetto
	 */
	public ReinviaProspettoService(ConfigurationHelper configurationHelper, ProspettoDad prospettoDad) {
		super(configurationHelper, prospettoDad);
	}

	@Override
	protected void execute() {
		
		Long successi = 0L;
		Long fallimenti = 0L;
		
		ReinviaProspetto reinviaProspetto = request.getReinviaProspetto();
		
		if (reinviaProspetto==null) {
			throw new NotFoundException("ReinviaProspetto");
		}
		
		if (reinviaProspetto.getIdsProspetti()==null || reinviaProspetto.getIdsProspetti().isEmpty()) {
			throw new NotFoundException("Prospetto");
		}
		
		List<ProDParametri> parametri = prospettoDad.getParametri();
		
		Date dataOdierna = new Date();
		
		for (String idString : reinviaProspetto.getIdsProspetti()) {
			
			Long id = Long.parseLong(idString);
			
			Prospetto p = prospettoDad.getProspettoCompleto(id);
			
			if (p.getStatoProspetto().getId() != 3L) {
				//response.addApiError(MsgProdis.PROSPIE0006.getError());
				fallimenti++;
				continue;
			}
			
			AdapterSpicom spicom = AdapterSpicom.getInstance();

			// invio del prospetto a SPICOM
			String codiceRegionale = null;

			try {

				codiceRegionale = spicom.inviaComunicazione(p, reinviaProspetto.getRuolo());

				p.setCodiceComunicazione(codiceRegionale);
				p.setFlgInvioMinistero("S");
				p.setDataInvio(dataOdierna);

			} catch (SpicomIntegrationException se) {
				log.error("[ConfermaRiepilogoService::execute] ERRORE nella chiamata a spicom", se);
				fallimenti++;
				continue;
			}

			if (codiceRegionale != null) {
				p.setCodiceComunicazione(codiceRegionale);

			} else {
				log.error("[ConfermaRiepilogoService::execute]", " ERRORE nella chiamata a spicom");
				fallimenti++;
				continue;
			}

			StatoProspetto statoPresentato = prospettoDad
					.getStatoProspetto(Long.valueOf(ConstantsProdis.PROSPETTO_STATO_PRESENTATA));

			p.setStatoProspetto(statoPresentato);

			Prospetto prospettoConfermato = prospettoDad.updateConfermaRiepilogo(p);
			
			if (prospettoConfermato == null) {
				//response.addApiError(MsgProdis.PROSPIE0004.getError());
				fallimenti++;
				continue;
			}

			// parametri configurazione mail
			Properties mailProp = new Properties();
			for (ProDParametri pr : parametri) {
				if (ConstantsProdis.PARAMETRO_MAIL.startsWith("mail")) {
					mailProp.setProperty(pr.getDsNome(), pr.getDsValore());
				}
			}
		
			if (inviaMailReferente(prospettoConfermato, p, mailProp)) {
				// DO NOTHING
			} else {
				fallimenti++;
				continue;
			}
			
			successi++;

		}

		response.setEsito("Sono stati trasmessi "+successi+" prospetti positivamente. Non sono trasmessi "+fallimenti+" prospetti");
		
	}
	
	private Boolean inviaMailReferente(Prospetto prospettoConfermato, Prospetto prospetto, Properties mailProp) {
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
			//response.addApiError(MsgProdis.PROSPIW0005.getError());
			return false;
		}
		log.debug("[inviaMailReferente::inviaMail]", "END");	
		return true;
	}
	
}
