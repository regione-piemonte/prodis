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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;

import org.apache.commons.lang3.StringUtils;

import it.csi.prodis.prodisweb.lib.dto.decodifiche.Atecofin;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Ccnl;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Comune;
import it.csi.prodis.prodisweb.lib.dto.prospetto.DatiAzienda;
import it.csi.prodis.prodisweb.lib.dto.prospetto.SedeLegale;
import it.csi.prodis.prodisweb.lib.util.log.LogUtil;
import it.csi.silpsv.silpsvaa.cxfclient.AnagraficaAziende;
import it.csi.silpsv.silpsvaa.cxfclient.AnagraficaSediAziende;
import it.csi.silpsv.silpsvaa.cxfclient.ElencoAziende;
import it.csi.silpsv.silpsvaa.cxfclient.ElencoSedi;
import it.csi.silpsv.silpsvaa.cxfclient.EstrazioneAnagraficaAziende;
import it.csi.silpsv.silpsvaa.cxfclient.ServiziSilpException_Exception;

public class AziendaHelperImpl {
	protected final LogUtil log = new LogUtil(getClass());
	private Service s = null;

	public DatiAzienda ricercaPerCodiceFiscale(String codiceFiscaleAzienda) {
		final String methodName = "ricercaPerCodiceFiscale";
		DatiAzienda datiAzienda = new DatiAzienda();

		try {
			EstrazioneAnagraficaAziende estrazioneAnagraficaAziende = getService();

			final String caller = "PRODIS"; // configurato da Sacchi
			ElencoAziende elencoAziendeFilter = new ElencoAziende();
			elencoAziendeFilter.setCodFiscale(codiceFiscaleAzienda);

			List<ElencoAziende> elencoAziendes = estrazioneAnagraficaAziende.ricercaElencoAziende(caller,
					elencoAziendeFilter);
			if (elencoAziendes == null || elencoAziendes.size() == 0) {
				log.error(methodName, "azienda non trovata: " + codiceFiscaleAzienda);
				return null;
			}

			ElencoAziende elencoAziende = null;
			if (elencoAziendes != null && elencoAziendes.size() == 1) {
				// prendo l'unica trovata
				elencoAziende = elencoAziendes.get(0);

			} else if (elencoAziendes != null && elencoAziendes.size() > 1) {
				// Se ci sono più aziende senza flag Master il sistema non carica e segnala che
				// non è stata trovata azienda.
				// Se ci sono più azienda con flag master ne carica una
				for (Iterator iterator = elencoAziendes.iterator(); iterator.hasNext();) {
					ElencoAziende elencoAziendeItem = (ElencoAziende) iterator.next();
					if (elencoAziendeItem.getFlgMaster() != null
							&& elencoAziendeItem.getFlgMaster().equalsIgnoreCase("S")) {
						elencoAziende = elencoAziendeItem;
					}
				}
			}

			if (elencoAziende == null) {
				log.error(methodName, "azienda master non trovata: " + codiceFiscaleAzienda);
				return null;
			}

			// denominazione, settore ateco, ccnl
			AnagraficaAziende anagraficaAziende = estrazioneAnagraficaAziende.visualizzaDettaglioAzienda(caller,
					elencoAziende.getIdAzienda());

			datiAzienda.setDenominazioneDatoreLavoro(anagraficaAziende.getRagioneSociale());

			Atecofin atecofin = new Atecofin();
			atecofin.setCodAtecofinMin(anagraficaAziende.getCodMinAteco());
			atecofin.setDsProTAtecofin(anagraficaAziende.getDescrAteco());
			datiAzienda.setAtecofin(atecofin);

			Ccnl ccnl = new Ccnl();
			ccnl.setCodCcnlMin(anagraficaAziende.getIdCcnlAzienda());
			ccnl.setDsCcnl(anagraficaAziende.getDescrCcnlAzienda());
			datiAzienda.setCcnl(ccnl);
			datiAzienda.setIdAziendaSilp(elencoAziende.getIdAzienda());

			ElencoSedi elencoSediFilter = new ElencoSedi();
			elencoSediFilter.setIdAzienda(elencoAziende.getIdAzienda());
			elencoSediFilter.setTipoSede("1"); // estrae la sede legale (tipoSede = 1)

			List<ElencoSedi> elencoSedis = estrazioneAnagraficaAziende.ricercaSediAzienda(caller, elencoSediFilter);
			if (elencoSedis == null || elencoSedis.size() == 0) {
				log.error(methodName, "sede non trovata per idAzienda: " + elencoAziende.getIdAzienda());

			} else {
				ElencoSedi elencoSedi = elencoSedis.get(0);

				// comune (descrComuneSede, idComuneSede)
				Comune comune = new Comune();
				comune.setDsProTComune(elencoSedi.getDescrComuneSede());
				comune.setCodComuneMin(elencoSedi.getIdComuneSede());

				SedeLegale sedeLegale = new SedeLegale();
				sedeLegale.setComune(comune);
				datiAzienda.setSedeLegale(sedeLegale);

				// indirizzo (descrToponimoSede + descrIndirizzoSede + descrNumCivicoSede)
				String indirizzo = "";
				if (!StringUtils.isBlank(elencoSedi.getDescrToponimoSede())) {
					indirizzo += elencoSedi.getDescrToponimoSede() + " ";
				}
				indirizzo += elencoSedi.getDescrIndirizzoSede() + " " + elencoSedi.getDescrNumCivicoSede();
				sedeLegale.setIndirizzo(indirizzo);

				// cap (capSede)
				sedeLegale.setCapSede(elencoSedi.getCapSede());

				AnagraficaSediAziende anagraficaSediAziende = estrazioneAnagraficaAziende
						.visualizzaDettaglioSedeAzienda(caller, elencoSedi.getIdSedeAzienda());
				// eMail, fax, telefono
				sedeLegale.setEmail(anagraficaSediAziende.getEMail());
				sedeLegale.setFax(anagraficaSediAziende.getFax());
				sedeLegale.setTelefono(anagraficaSediAziende.getTelefono());
			}

			// aggiungere chiamata al servizio per la ricerca sedi dell'azienda
			datiAzienda.setElencoSedi(this.elencoSediByIdAzienda(elencoAziende.getIdAzienda()));

		} catch (ServiziSilpException_Exception e) {
			log.error(methodName, e);
		} catch (IOException e) {
			log.error(methodName, e);
		}

		return datiAzienda;
	}

	public EstrazioneAnagraficaAziende getService() throws IOException {
		if (s == null) {
			// log.info("[AdapterSilpsvalWSFactory::getService] Look up to
			// SILPSV.silpsval....");
			s = Service.create(getClass().getResource("/silpsv.silpsvaa.wsdl"),
					new QName("urn:silpsvaa", "EstrazioneAnagraficaAziendeService"));
		}
		EstrazioneAnagraficaAziende service = s.getPort(EstrazioneAnagraficaAziende.class);
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
		String endpoint = properties.getProperty("url_aa");
		return endpoint;
	}

	public List<SedeLegale> elencoSediByIdAzienda(String idAzienda) {
		final String methodName = "elencoSediByIdAzienda";
		List<SedeLegale> elencoDelleSediDaVisualizzare = new ArrayList<SedeLegale>();

		try {
			EstrazioneAnagraficaAziende estrazioneAnagraficaAziende = getService();
			final String caller = "PRODIS"; // configurato da Sacchi

			ElencoSedi elencoSediFilter = new ElencoSedi();
			elencoSediFilter.setIdAzienda(idAzienda);

			List<ElencoSedi> elencoSedis = estrazioneAnagraficaAziende.ricercaSediAzienda(caller, elencoSediFilter);
			if (elencoSedis == null || elencoSedis.size() == 0) {
				log.error(methodName, "sede non trovata per idAzienda: " + idAzienda);
			} else {
				for (ElencoSedi laSede : elencoSedis) {
					// comune (descrComuneSede, idComuneSede)
					Comune comune = new Comune();
					comune.setDsProTComune(laSede.getDescrComuneSede());
					comune.setCodComuneMin(laSede.getIdComuneSede());
					SedeLegale sede = new SedeLegale();
					sede.setComune(comune);
					// indirizzo (descrToponimoSede + descrIndirizzoSede + descrNumCivicoSede)
					String indirizzo = "";
					if (!StringUtils.isBlank(laSede.getDescrToponimoSede())) {
						indirizzo += laSede.getDescrToponimoSede() + " ";
					}
					indirizzo += laSede.getDescrIndirizzoSede() + " " + laSede.getDescrNumCivicoSede();
					sede.setIndirizzo(indirizzo);

					// cap (capSede)
					sede.setCapSede(laSede.getCapSede());

					AnagraficaSediAziende anagraficaSediAziende = estrazioneAnagraficaAziende
							.visualizzaDettaglioSedeAzienda(caller, laSede.getIdSedeAzienda());
					// eMail, fax, telefono
					sede.setEmail(anagraficaSediAziende.getEMail());
					sede.setFax(anagraficaSediAziende.getFax());
					sede.setTelefono(anagraficaSediAziende.getTelefono());

					elencoDelleSediDaVisualizzare.add(sede);
				}
			}

		} catch (ServiziSilpException_Exception e) {
			log.error(methodName, e);
		} catch (IOException e) {
			log.error(methodName, e);
		}

		return elencoDelleSediDaVisualizzare;
	}

}
