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

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.prodis.prodisweb.ejb.business.be.facade.ProspettoFacade;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto.CreaHtmlResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto.GeneraPdfPerSalvataggioResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto.GeneraPdfResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto.SalvaPdfResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto.SavePdfResponse;
import it.csi.prodis.prodisweb.ejb.util.ConstantsProdis;
import it.csi.prodis.prodisweb.lib.dto.ApiError;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ConfermaRiepilogoProspetto;
import it.csi.prodis.prodisweb.lib.dto.prospetto.Prospetto;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ReinviaProspetto;
import it.csi.prodis.prodisweb.lib.dto.prospetto.RicercaProspetto;
import it.csi.prodis.prodisweb.lib.util.ProdisClassUtils;
import it.csi.prodis.prodisweb.web.business.be.api.ProspettoApi;
import it.csi.prodis.prodisweb.web.util.annotation.Logged;
import it.csi.util.performance.StopWatch;

/**
 * Implementation for CommonApi
 */
@Logged
public class ProspettoApiServiceImpl extends BaseRestServiceImpl implements ProspettoApi {

	@EJB
	private ProspettoFacade prospettoFacade;

	@Override
	public Response getRicercaProspetti(@Min(0) Integer page, @Min(1) @Max(100) Integer limit, String sort,
			String direction, RicercaProspetto ricercaProspetto, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[getRicercaProspetti]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			return invoke(() -> prospettoFacade.getRicercaProspetti(page, limit, sort, direction, ricercaProspetto));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "getRicercaProspetti()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".getRicercaProspetti", "");
			watcher.stop();
		}

	}

	@Override
	public Response postProspetto(Boolean flagBozza, Prospetto prospetto, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		log.info(ProdisClassUtils.truncClassName(getClass()), "[postProspetto]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			prospetto.getStatoProspetto().setId(1L);
			prospetto.getStatoProspetto().setDescrizione("BOZZA");

			return invoke(() -> prospettoFacade.postProspetto(prospetto, flagBozza));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "postProspetto()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".postProspetto", "");
			watcher.stop();
		}

	}

	@Override
	public Response getProspettoById(Long idProspetto, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[getProspettoById]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			return invoke(() -> prospettoFacade.getProspettoById(idProspetto));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "getProspettoById()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".getProspettoById", "");
			watcher.stop();
		}

	}

	@Override
	public Response getAssunzioniPubblicheByIdProspetto(Long idProspetto, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[getAssunzioniPubblicheByIdProspetto]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			return invoke(() -> prospettoFacade.getAssunzioniPubblicheByIdProspetto(idProspetto));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "getAssunzioniPubblicheByIdProspetto()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass())
							+ ".getAssunzioniPubblicheByIdProspetto",
					"");
			watcher.stop();
		}

	}

	@Override
	public Response putProspettoUpdate(Long id, Prospetto prospetto, Boolean flagBozza, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		log.info(ProdisClassUtils.truncClassName(getClass()), "[putProspettoUpdate]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			prospetto.setId(id);
			return invoke(() -> prospettoFacade.putProspetto(prospetto, flagBozza));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "putProspettoUpdate()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".putProspettoUpdate", "");
			watcher.stop();
		}

	};

	@Override
	public Response deleteProspetto(Long id, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		log.info(ProdisClassUtils.truncClassName(getClass()), "[deleteProspetto]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			return invoke(() -> prospettoFacade.deleteProspetto(id));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "deleteProspetto()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".deleteProspetto", "");
			watcher.stop();
		}

	}

	@Override
	public Response getCheckCodiceFiscale(String codiceFiscale, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[getCheckCodiceFiscale]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			return invoke(() -> prospettoFacade.getCheckCodiceFiscale(codiceFiscale));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "getCheckCodiceFiscale()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".getCheckCodiceFiscale", "");
			watcher.stop();
		}
	}

	@Override
	public Response generaPdf(Long idProspetto, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[generaPdf]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			// return invoke(() -> prospettoFacade.generaPdf(idProspetto));

			CreaHtmlResponse creaHtmlResponse = prospettoFacade.creaHtml(idProspetto);
			if (creaHtmlResponse == null) {
				return Response.noContent().build();
			}

			GeneraPdfResponse generaPdfResponse = prospettoFacade.generaPdf(idProspetto,
					creaHtmlResponse.getHtmlContent());
			if (generaPdfResponse == null) {
				return Response.noContent().build();
			}

			// come da richiesta si annulla il salvataggio del pdf in fase di generazione
//			SavePdfResponse savePdfResponse = prospettoFacade.savePdf(idProspetto, generaPdfResponse.getBytes());

			return generaPdfResponse.composeResponse();

		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;

		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "generaPdf()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".generaPdf", "");
			watcher.stop();
		}
	}

	@Override
	public Response rettificaProspetto(Long id, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		log.info(ProdisClassUtils.truncClassName(getClass()), "[rettificaProspetto]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			return invoke(() -> prospettoFacade.rettificaProspetto(id));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "rettificaProspetto()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".rettificaProspetto", "");
			watcher.stop();
		}

	}

	@Override
	public Response annullaProspetto(Long id, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[annullaProspetto]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			return invoke(() -> prospettoFacade.annullaProspetto(id));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "annullaProspetto()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".annullaProspetto", "");
			watcher.stop();
		}

	};

	@Override
	public Response duplicaProspetto(Long id, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[duplicaProspetto]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			return invoke(() -> prospettoFacade.duplicaProspetto(id));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "duplicaProspetto()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".duplicaProspetto", "");
			watcher.stop();
		}

	};

	@Override
	public Response putStatoProspettoUpdate(Prospetto prospetto, Long idStatoProspetto, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		log.info(ProdisClassUtils.truncClassName(getClass()), "[putStatoProspettoUpdate]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			return invoke(() -> prospettoFacade.putStatoProspettoUpdate(prospetto.getId(), idStatoProspetto));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "putStatoProspettoUpdate()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".putStatoProspettoUpdate", "");
			watcher.stop();
		}
	}

	@Override
	public Response confermaRiepilogo(ConfermaRiepilogoProspetto confermaRiepilogoProspetto, Long idProspetto,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[confermaRiepilogo]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			confermaRiepilogoProspetto.getProspetto().setId(idProspetto);
			return invoke(() -> prospettoFacade.confermaRiepilogo(confermaRiepilogoProspetto));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "confermaRiepilogo()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".confermaRiepilogo", "");
			watcher.stop();
		}

	}

	@Override
	public Response salvaBozzaRiepilogo(Prospetto prospetto, Long idProspetto, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[salvaBozzaRiepilogo]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			prospetto.setId(idProspetto);
			return invoke(() -> prospettoFacade.salvaBozzaRiepilogo(prospetto));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "salvaBozzaRiepilogo()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".salvaBozzaRiepilogo", "");
			watcher.stop();
		}

	}

	@Override
	public Response checkPassaggioQ3(Long idProspetto, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> prospettoFacade.checkPassaggioQ3(idProspetto));
	};

	@Override
	public Response storeProcedureEseguiCalcoli(Long idProspetto, String cfUtenteAggiornamento, boolean soloScoperture,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(
				() -> prospettoFacade.storeProcedureEseguiCalcoli(idProspetto, cfUtenteAggiornamento, soloScoperture));
	}

	@Override
	public Response reinviaProspetto(ReinviaProspetto reinviaProspetto, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> prospettoFacade.reinviaProspetto(reinviaProspetto));
	};

	@Override
	public Response getCheckScopertureDatoriLavoriPubblici(Long idProspetto, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[getCheckScopertureDatoriLavoriPubblici]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			return invoke(() -> prospettoFacade.getCheckScopertureDatoriLavoriPubblici(idProspetto));
		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;
		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "getCheckScopertureDatoriLavoriPubblici()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass())
							+ ".getCheckScopertureDatoriLavoriPubblici",
					"");
			watcher.stop();
		}
	}
	
	
	@Override
	public Response salvaPdf(List<String> idProspettos, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		log.info(ProdisClassUtils.truncClassName(getClass()), "[generaPdf]");
		StopWatch watcher = new StopWatch(ConstantsProdis.COMPONENT_NAME);
		watcher.start();
		try {
			// return invoke(() -> prospettoFacade.generaPdf(idProspetto));
			ApiError listErrors = new ApiError();
			StringBuilder stbErrors = new StringBuilder();
			for(String idProspetto: idProspettos) {
				Long id = Long.parseLong(idProspetto);
				CreaHtmlResponse creaHtmlResponse = prospettoFacade.creaHtml(id);
				if (creaHtmlResponse != null) {
					
					
					GeneraPdfPerSalvataggioResponse generaPdfPersalvataggioResponse = prospettoFacade.generaPdfPerSalvataggio(id,
							creaHtmlResponse.getHtmlContent());
					if (generaPdfPersalvataggioResponse != null && generaPdfPersalvataggioResponse.getBytes() != null) {
						// come da richiesta si annulla il salvataggio del pdf in fase di generazione
						SavePdfResponse savePdfResponse = prospettoFacade.savePdf(id, generaPdfPersalvataggioResponse.getBytes());
					}else {
						stbErrors.append(idProspetto.toString()+"; ");
					}

					
				}else {
					stbErrors.append(idProspetto.toString()+"; ");
				}

			}
			
			SalvaPdfResponse salvaPdfResponse = new SalvaPdfResponse();
			
			if(stbErrors.length() > 0) {
				listErrors.setErrorMessage(stbErrors.toString());
				salvaPdfResponse.addApiError(listErrors);
			}else {
				salvaPdfResponse.setRes("SUCCESS");
			}
			
			
			
			return salvaPdfResponse.composeResponse();

		} catch (Exception ex) {
			log.error(ProdisClassUtils.truncClassName(getClass()), ex);
			throw ex;

		} finally {
			watcher.dumpElapsed(ProdisClassUtils.truncClassName(getClass()), "generaPdfPerSalvataggio()",
					"invocazione API " + ProdisClassUtils.truncClassName(getClass()) + ".generaPdfPerSalvataggio", "");
			watcher.stop();
		}
	}

}
