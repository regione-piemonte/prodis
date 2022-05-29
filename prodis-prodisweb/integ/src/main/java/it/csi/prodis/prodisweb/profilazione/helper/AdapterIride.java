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

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.csi.csi.porte.InfoPortaDelegata;
import it.csi.csi.porte.proxy.PDProxy;
import it.csi.csi.util.xml.PDConfigReader;
import it.csi.iride2.policy.entity.Actor;
import it.csi.iride2.policy.entity.Application;
import it.csi.iride2.policy.entity.Identita;
import it.csi.iride2.policy.entity.UseCase;
import it.csi.iride2.policy.interfaces.PolicyEnforcerBaseService;
import it.csi.prodis.prodisweb.lib.util.log.LogUtil;
import it.csi.prodis.prodisweb.profilazione.Constants;
import it.csi.prodis.prodisweb.profilazione.dto.RuoloIrideListaCasiUso;
import it.csi.util.performance.StopWatch;

/**
 * Recupero dei dati da IRIDE2.pep (PD/PA SOPA)
 */
public class AdapterIride {

//	private static final Logger logger = Logger.getLogger(Constants.LOGGER);
	protected final LogUtil logger = new LogUtil(getClass());

	private static final AdapterIride instance = new AdapterIride();

	private static final String PD_SERVICE = "/iride2_pep_defPD_soap.xml";

	private static PolicyEnforcerBaseService service;

	public static AdapterIride getInstance() {
		return instance;
	}

	/**
	 * Restituisce l'interfaccia della Porta Delegata SOAP
	 */
	private PolicyEnforcerBaseService getService() throws Exception {
		if (service == null) {
			InputStream pd = this.getClass().getResourceAsStream(PD_SERVICE);
			InfoPortaDelegata info = PDConfigReader.read(pd);
			service = (PolicyEnforcerBaseService) PDProxy.newInstance(info);
		}
		return service;
	}

	/**
	 * Reperisce da Iride una lista di attori con i casi d'uso associati a ciascun
	 * attore.
	 * 
	 * @param identita Identita Iride restituita da Shibboleth
	 * @return
	 */
	public List<RuoloIrideListaCasiUso> getListRuoliIride(Identita identita) throws Exception {
		logger.debug("[AdapterIride::getListRuoliIride] BEGIN identita=" + identita, "");

		StopWatch watcher = new StopWatch(Constants.LOGGER);
		watcher.start();

		List<RuoloIrideListaCasiUso> result = new ArrayList<>();

		try {

			Application application = new Application(Constants.APPLICATION_NAME_IRIDE);

			Map<String, Set<String>> mapActorsUseCases = new HashMap<>();
			UseCase[] casiDUso = getService().findUseCasesForPersonaInApplication(identita, application);
			if (casiDUso != null) {
				for (UseCase uc : casiDUso) {
					Actor[] elencoAttori = getService().findActorsForPersonaInUseCase(identita, uc);
					for (Actor r : elencoAttori) {
						String actorString = r.toString();
						Set<String> useCases = mapActorsUseCases.get(actorString);
						if (useCases == null) {
							useCases = new HashSet<>();
							mapActorsUseCases.put(actorString, useCases);
						}
						useCases.add(uc.getId());
					}
				}
			}

			List<String> actorKeys = new ArrayList<>(mapActorsUseCases.keySet());
			Collections.sort(actorKeys);
			int indice = 0;
			String actors = "";
			for (String actorKey : actorKeys) {
				indice++;
				RuoloIrideListaCasiUso v = new RuoloIrideListaCasiUso();
				v.setActor(new Actor(application, actorKey));
				v.setIndice(indice);
				v.setListaCasiUso(new ArrayList<>(mapActorsUseCases.get(actorKey)));
				result.add(v);
				actors += actorKey + ",";
			}
			logger.debug("[AdapterIride::getListRuoliIride] actors=" + actors, "");

		} catch (Exception e) {
			logger.error("Exception---->>>> getListRuoliIride()", e);

		} finally {
			watcher.dumpElapsed("AdapterIride", "getListRuoliIride", "invocazione servizio IRIDE", "");
			watcher.stop();

			logger.debug("[AdapterIride::getListRuoliIride] END", "");
		}
		return result;
	}

}
