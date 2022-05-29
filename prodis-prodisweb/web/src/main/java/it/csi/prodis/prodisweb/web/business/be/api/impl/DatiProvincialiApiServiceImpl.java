/*-
 * ========================LICENSE_START=================================
 * PRODIS BackEnd - WAR submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodisweb.web.business.be.api.impl;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.prodis.prodisweb.ejb.business.be.facade.DatiProvincialiFacade;
import it.csi.prodis.prodisweb.ejb.util.ConstantsProdis;
import it.csi.prodis.prodisweb.lib.dto.prospetto.DatiProvinciali;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProspettoProvincia;
import it.csi.prodis.prodisweb.lib.util.ProdisClassUtils;
import it.csi.prodis.prodisweb.web.business.be.api.DatiProvincialiApi;
import it.csi.util.performance.StopWatch;

public class DatiProvincialiApiServiceImpl extends BaseRestServiceImpl implements DatiProvincialiApi {

	@EJB
	private DatiProvincialiFacade datiProvincialiFacade;

	@Override
	public Response getDatiProvincialiByIdProspettoProv(Long idProspettoProv, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[getDatiProvincialiByIdProspettoProv]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			return invoke(() -> datiProvincialiFacade.getDatiProvincialiByIdProspettoProv(idProspettoProv));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "getDatiProvincialiByIdProspettoProv()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass())
							+ ".getDatiProvincialiByIdProspettoProv",
					"");
			watcher.stop();
		}

	}

	@Override
	public Response getElencoProvinceQ2ByIdProspetto(Long idProspetto, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[getElencoProvinceQ2ByIdProspetto]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			return invoke(() -> datiProvincialiFacade.getElencoProvQ2ByIdProspetto(idProspetto));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "getElencoProvinceQ2ByIdProspetto()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass())
							+ ".getElencoProvinceQ2ByIdProspetto",
					"");
			watcher.stop();
		}

	}

	@Override
	public Response getRiepilogoByIdProspetto(Long idProspetto, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[getRiepilogoByIdProspetto]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			return invoke(() -> datiProvincialiFacade.getProspettoProvinciaByIdProspetto(idProspetto));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "getRiepilogoByIdProspetto()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".getRiepilogoByIdProspetto",
					"");
			watcher.stop();
		}

	}

	@Override
	public Response getElencoScopertureByIdProspetto(Long idProspetto, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[getElencoScopertureByIdProspetto]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			return invoke(() -> datiProvincialiFacade.getElencoScoperture(idProspetto));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "getElencoScopertureByIdProspetto()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass())
							+ ".getElencoScopertureByIdProspetto",
					"");
			watcher.stop();
		}

	}

	@Override
	public Response postDatiProvinciali(ProspettoProvincia prospettoProvincia, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[postDatiProvinciali]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			return invoke(() -> datiProvincialiFacade.postDatiProvinciali(prospettoProvincia));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "postDatiProvinciali()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".postDatiProvinciali", "");
			watcher.stop();
		}
	}

	@Override
	public Response putDatiProvinciali(Long idProspettoProv, Boolean flagBozza, DatiProvinciali datiProvinciali,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		log.info(ProdisClassUtils.truncClassName(getClass()), "[putDatiProvinciali]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			// qui trovi il flag bozza utilizzabile per fare i controlli necessari
			// lato backend dovrebbe bastare ma non c'è un campo sul record del database
			// in cui salvarlo

			datiProvinciali.setProspettoProvincia(new ProspettoProvincia());
			datiProvinciali.getProspettoProvincia().setId(idProspettoProv.intValue());

			return invoke(() -> datiProvincialiFacade.putDatiProvinciali(datiProvinciali, flagBozza));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "putDatiProvinciali()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".putDatiProvinciali", "");
			watcher.stop();
		}

	}

	@Override
	public Response getCategorieEscluseByIdProspettoProv(Long idProspettoProv, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[getCategorieEscluseByIdProspettoProv]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			return invoke(() -> datiProvincialiFacade.getCategorieEscluse(idProspettoProv));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "getCategorieEscluseByIdProspettoProv()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass())
							+ ".getCategorieEscluseByIdProspettoProv",
					"");
			watcher.stop();
		}

	}

	@Override
	public Response getProvIntermittentiByIdProspettoProv(Long idProspettoProv, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[getProvIntermittentiByIdProspettoProv]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			return invoke(() -> datiProvincialiFacade.getProvIntermittenti(idProspettoProv));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "getProvIntermittentiByIdProspettoProv()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass())
							+ ".getProvIntermittentiByIdProspettoProv",
					"");
			watcher.stop();
		}

	}

	@Override
	public Response getPartTimeByIdProspettoProv(Long idProspettoProv, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[getPartTimeByIdProspettoProv]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			return invoke(() -> datiProvincialiFacade.getPartTime(idProspettoProv));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "getPartTimeByIdProspettoProv()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".getPartTimeByIdProspettoProv",
					"");
			watcher.stop();
		}

	}

	@Override
	public Response getLavoratoriInForzaByIdProspettoProv(Long idProspettoProv, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[getLavoratoriInForzaByIdProspettoProv]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			return invoke(() -> datiProvincialiFacade.getLavoratoriInForza(idProspettoProv));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "getLavoratoriInForzaByIdProspettoProv()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass())
							+ ".getLavoratoriInForzaByIdProspettoProv",
					"");
			watcher.stop();
		}

	}

	@Override
	public Response getConfermaProvince(Long idProspetto, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[getConfermaProvince]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			return invoke(() -> datiProvincialiFacade.getConfermaProvince(idProspetto));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "getConfermaProvince()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".getConfermaProvince", "");
			watcher.stop();
		}

	}

	@Override
	public Response deleteDatiProvinciali(Long idProspettoProv, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[deleteDatiProvinciali]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			return invoke(() -> datiProvincialiFacade.deleteDatiProvinciali(idProspettoProv));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "deleteDatiProvinciali()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".deleteDatiProvinciali", "");
			watcher.stop();
		}

	}

}
