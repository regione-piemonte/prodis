/*-
 * ========================LICENSE_START=================================
 * PRODIS BackEnd - INTEGRATION SPICOM submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodisweb.spicom.helper;

import java.io.InputStream;

import it.csi.csi.porte.InfoPortaDelegata;
import it.csi.csi.porte.proxy.PDProxy;
import it.csi.csi.util.xml.PDConfigReader;
import it.csi.prodis.prodisweb.lib.dto.common.Ruolo;
import it.csi.prodis.prodisweb.lib.dto.prospetto.Prospetto;
import it.csi.prodis.prodisweb.lib.exception.SpicomIntegrationException;
import it.csi.prodis.prodisweb.lib.mapper.ProdisIntegMappers;
import it.csi.prodis.prodisweb.lib.util.log.LogUtil;
import it.csi.prodis.prodisweb.spicom.AppConstants;
import it.csi.spicom.dto.ComunicazioneProspettoDisabiliDTO;
import it.csi.spicom.interf.EsitoInvioComunicazione;
import it.csi.spicom.interf.ServizioInvioComunicazioni;
import it.csi.spicom.util.serialization.ObjectSerializer;
import it.csi.util.performance.StopWatch;

public class AdapterSpicom {
	
	protected final LogUtil logger = new LogUtil(getClass());

	private static final AdapterSpicom instance = new AdapterSpicom();

	private static final String PD_SERVICE = "/defpd_spicom_soap.xml";

	private static ServizioInvioComunicazioni service;

	public static AdapterSpicom getInstance() {
		return instance;
	}

	/**
	 * Restituisce l'interfaccia della Porta Delegata SOAP
	 */
	private ServizioInvioComunicazioni getService() throws Exception {
		logger.debug("[AdapterSpicom::getService]","START");
		if (service == null) {

			InputStream pd = this.getClass().getResourceAsStream(PD_SERVICE);
			InfoPortaDelegata info = PDConfigReader.read(pd);
			service = (ServizioInvioComunicazioni) PDProxy.newInstance(info);
		}
		logger.debug("[AdapterSpicom::getService]","END");
		return service;
	}
	
	// restituisce il codice regionale
	public String inviaComunicazione(Prospetto prospetto, Ruolo ruolo) throws SpicomIntegrationException {
		logger.debug("[AdapterSpicom::inviaComunicazione]","START");
		StopWatch watcher = new StopWatch(AppConstants.LOGGER);
		watcher.start();

		String ret = null;
		try {
			logger.debug("[AdapterSpicom::inviaComunicazione] invio prospetto id ", prospetto.getId());
			logger.debug("[AdapterSpicom::inviaComunicazione] ruolo", ruolo.getRuolo());
			
			ComunicazioneProspettoDisabiliDTO comunicazioneProDis = ProdisIntegMappers.toComunicazioneSpicom(prospetto, ruolo);
			
			String xml = ObjectSerializer.objectToXml(comunicazioneProDis).replaceAll("java.sql.Date", "java.util.Date");
			
			logger.debug("[AdapterSpicom::inviaComunicazione]", xml);
			
			EsitoInvioComunicazione esito = getService().inviaComunicazioneProspettoXML(xml);
			//EsitoInvioComunicazione esito = getService().inviaComunicazione(comunicazioneProDis);
			
			ret = esito.getIdentificativoRegionale();
			
			logger.info("[AdapterSpicom::inviaComunicazione] codice regionale ", esito.getIdentificativoRegionale());
			logger.info("[AdapterSpicom::inviaComunicazione] codice spicom ", esito.getIdentificativoSpicom());		
			
		} catch (Exception e) {
			logger.error("[AdapterSpicom::inviaComunicazione] ERRORE " + e.getMessage(), e);		
			throw new SpicomIntegrationException(e);
			
		} finally {
			watcher.dumpElapsed(getClass().getName(), "inviaComunicazione",
					"Invio comunicazione a Spicom " + getClass().getName() + ".inviaComunicazione",
					"");
			watcher.stop();
		}
		
		logger.debug("[AdapterSpicom::inviaComunicazione]","END");

		return ret;
	}

}
