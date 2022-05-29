/*-
 * ========================LICENSE_START=================================
 * PRODIS BackEnd - INTEGRATION AAEP, IRIDE, COMONL submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodisweb.profilazione.helper;

import java.util.List;

import it.csi.iride2.policy.entity.Identita;
import it.csi.prodis.prodisweb.lib.util.log.LogUtil;
import it.csi.prodis.prodisweb.profilazione.dto.ImpresaInfoc;
import it.csi.prodis.prodisweb.profilazione.dto.ProfileComonl;
import it.csi.prodis.prodisweb.profilazione.dto.ProfiloUtente;
import it.csi.prodis.prodisweb.profilazione.dto.ProfiloUtenteProdis;
import it.csi.prodis.prodisweb.profilazione.dto.RuoloIrideListaCasiUso;

/**
 * Classe di utilita' per il reperimento del profilo.
 * 
 */
public class ProfilazioneHelper {

//  private static final Logger logger = Logger.getLogger(Constants.LOGGER);
	protected final LogUtil logger = new LogUtil(getClass());

	/**
	 * Recupera il profilo a partire dall'identita' restituita da Shibboleth.
	 * Richiama i servizi di AAEP, COMONL e IRIDE2 per ottenere i dati del profilo.
	 * La logica di costruzione del profilo e' delegata a ProfileParser.
	 * 
	 * @param identitaIride un'identita' Iride2
	 * @return
	 * @throws Exception
	 */
	public ProfiloUtenteProdis ricercaProfiloUtente(Identita identitaIride) throws Exception {
		logger.info("[ProfilazioneHelper::ricercaProfiloUtente] " + identitaIride, "");
		ProfiloUtente profiloUtente = new ProfiloUtente();

		List<ImpresaInfoc> listaAziendeAAEP = AdapterAAEP.getInstance()
				.cercaElencoAziende(identitaIride.getCodFiscale());
		profiloUtente.setListaAziendeAAEP(listaAziendeAAEP);

		List<RuoloIrideListaCasiUso> ruoliIride = AdapterIride.getInstance().getListRuoliIride(identitaIride);
		profiloUtente.setListRuoliIride(ruoliIride);

		List<ProfileComonl> listaProfiliComonl = AdapterComonlWSImpl.getInstance()
				.cercaProfili(identitaIride.getCodFiscale());
		profiloUtente.setListaProfiliComonl(listaProfiliComonl);

		ProfileParser parser = new ProfileParser();
		ProfiloUtenteProdis r = parser.creaProfilo(profiloUtente, identitaIride);
		logger.info("[ProfilazioneHelper::ricercaProfiloUtente] Profilo=" + r, "");
		return r;

	}

	/**
	 * Consente di testare un'estrazione, per il reperimento di un token valido di
	 * test si puo' utilizzare
	 * http://tst-www.sistemapiemonte.it/routingconf-cons/identita.do
	 * 
	 * Nel caso si riceva l'eccezione
	 * it.csi.iride2.policy.exceptions.IdentitaNonAutenticaException e' necessario
	 * ricalcolare il token
	 * 
	 * @param args nessun parametro
	 * @throws Exception
	 */

//	public static void main(String[] args) throws Exception {
//
////	it.csi.csi.wrapper.UserException
//
//		String demo20 = "";
//		String demo21 = "AAAAAA00A11B000J/CSI PIEMONTE/DEMO 21/ACTALIS_EU/20210907162743/16/D1260M6fRQTEzxKrGJuqmg==";
//		String demo22 = "";
//		String demo24 = "AAAAAA00A11E000M/CSI PIEMONTE/DEMO 24/ACTALIS_EU/20210805103020/16/GKXmnA1S5Z4mLHia2LgZuw==";
//		String demo25 = "";
//		String demo26 = "";
//		String demo27 = "";
//		String demo28 = "";
//		String token = demo21;
//		ProfilazioneHelper helper = new ProfilazioneHelper();
//		ProfiloUtenteProdis p = helper.ricercaProfiloUtente(new Identita(token));
//		for (Profilo profilo : p.getListaRuoliAmmessi()) {
//			logger.info("[ProfilazioneHelper::main] " + profilo.getDenominazioneAzienda() + " - " + profilo.getRuolo()
//					+ " - " + profilo.getListaCasiUso(), "");
//		}
//		logger.info("[ProfilazioneHelper::main] " + p.getListaRuoliAmmessi(), "");
//	}
}
