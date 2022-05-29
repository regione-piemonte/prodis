/*-
 * ========================LICENSE_START=================================
 * PRODIS BackEnd - INTEGRATION SILP submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodisweb.lib.external.impl.silp;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;

import org.apache.commons.lang3.StringUtils;

import it.csi.prodis.prodisweb.lib.dto.decodifiche.Comune;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.StatiEsteri;
import it.csi.prodis.prodisweb.lib.dto.prospetto.LavoratoriSilp;
import it.csi.prodis.prodisweb.lib.util.log.LogUtil;
import it.csi.silpsv.silpsval.cxfclient.AnagraficaLavoratore;
import it.csi.silpsv.silpsval.cxfclient.EstrazioneAnagraficaLavoratore;
import it.csi.silpsv.silpsval.cxfclient.ParamRicercaLavoratoreFilter;
import it.csi.silpsv.silpsval.cxfclient.ResultRicercaLavoratore;
import it.csi.silpsv.silpsval.cxfclient.ServiziSilpException_Exception;

public class LavoratoreHelperImpl {
	protected final LogUtil log = new LogUtil(getClass());
	private Service s = null;

	public LavoratoriSilp ricercaPerCodiceFiscale(String codiceFiscale) {
		final String methodName = "ricercaPerCodiceFiscale";
		LavoratoriSilp lavoratoriSilp = new LavoratoriSilp();

		try {
			EstrazioneAnagraficaLavoratore estrazioneAnagraficaLavoratore = getService();

			String caller = "PRODIS"; // "MORE"; // configurato da Sacchi
			ParamRicercaLavoratoreFilter paramRicercaLavoratoreFilter = new ParamRicercaLavoratoreFilter();
			paramRicercaLavoratoreFilter.setCodiceFiscale(codiceFiscale);
			paramRicercaLavoratoreFilter.setMaxNumeroRecord(10);

			List<ResultRicercaLavoratore> resultRicercaLavoratores = estrazioneAnagraficaLavoratore
					.ricercaLavoratorePerDatiAnagrafici(caller, paramRicercaLavoratoreFilter);
			for (Iterator iterator = resultRicercaLavoratores.iterator(); iterator.hasNext();) {
				ResultRicercaLavoratore resultRicercaLavoratore = (ResultRicercaLavoratore) iterator.next();

				// controllo errore
				if (!resultRicercaLavoratore.getCodErrore().equalsIgnoreCase("OK000001")) {
					log.error(methodName, "codErrore: " + resultRicercaLavoratore.getCodErrore());
					log.error(methodName, "descrizioneErrore: " + resultRicercaLavoratore.getDescrizioneErrore());
					return null;
				}
			}

			// Se il servizio restituisce un solo lavoratore il sistema richiama il servizio
			// SEAL: Servizio dettaglio lavoratore per idlavoratore.
			if (resultRicercaLavoratores != null && resultRicercaLavoratores.size() > 0) {
				ResultRicercaLavoratore resultRicercaLavoratore = resultRicercaLavoratores.get(0);

				AnagraficaLavoratore anagraficaLavoratore = estrazioneAnagraficaLavoratore
						.visualizzaDettaglioLavoratore(caller, resultRicercaLavoratore.getIdLavoratore());

				// Il sistema compila: nome, cognome, genere, data di nascita, comune o stato
				// estero di nascita (codStatoNascita, descrStatoNascita,codComuneNascita,
				// descrComuneNascita)
				lavoratoriSilp.setNome(anagraficaLavoratore.getNome());
				lavoratoriSilp.setCognome(anagraficaLavoratore.getCognome());
				lavoratoriSilp.setSesso(anagraficaLavoratore.getGenere());

				try {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date date = sdf.parse(anagraficaLavoratore.getDataNascita());
					lavoratoriSilp.setDataNascita(date);
				} catch (Exception e) {
					log.error(methodName, e.getMessage(), e);
				}

				Comune comune = new Comune();
				comune.setCodComuneMin(anagraficaLavoratore.getCodComuneNascita());
				comune.setDsProTComune(anagraficaLavoratore.getDescrComuneNascita());
				lavoratoriSilp.setComune(comune);

				// stato estero di nascita
				StatiEsteri statiEsteri  =new StatiEsteri();
				statiEsteri.setCodNazioneMin(anagraficaLavoratore.getCodStatoNascita());
				statiEsteri.setDsStatiEsteri(anagraficaLavoratore.getDescrStatoNascita());
				lavoratoriSilp.setStatiEsteri(statiEsteri);
			}

		} catch (ServiziSilpException_Exception e) {
			log.error(methodName, e);
		} catch (IOException e) {
			log.error(methodName, e);
		}

		return lavoratoriSilp;
	}

	public EstrazioneAnagraficaLavoratore getService() throws IOException {
		if (s == null) {
			// log.info("[AdapterSilpsvalWSFactory::getService] Look up to
			// SILPSV.silpsval....");
			s = Service.create(getClass().getResource("/silpsv.silpsval.wsdl"),
					new QName("urn:silpsval", "EstrazioneAnagraficaLavoratoreService"));
		}
		EstrazioneAnagraficaLavoratore service = s.getPort(EstrazioneAnagraficaLavoratore.class);
		BindingProvider bp = (BindingProvider) service;
		Map<String, Object> context = bp.getRequestContext();
		context.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, getEndpoint());

//	        org.apache.cxf.endpoint.Client client = ClientProxy.getClient(service);
//	        HTTPConduit conduit = (HTTPConduit) client.getConduit();
//	        HTTPClientPolicy policy = conduit.getClient();
//	        policy.setConnectionTimeout(30000);
//	        policy.setReceiveTimeout(30000);
//	        log.info("[AdapterSilpsvalWSFactory::getService] Look up to SILPSV.silpsval completed to endpoint " + getEndpoint());

		return service;
	}

	public String getEndpoint() throws IOException {
		final String SILP_RESOURCE = "/silp.properties"; // AAA TODO creare file e update file ambiente
		InputStream is = this.getClass().getResourceAsStream(SILP_RESOURCE);
		Properties properties = new Properties();
		properties.load(is);
		String endpoint = properties.getProperty("url_al");
		return endpoint;
	}

}
