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

import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.prodis.prodisweb.ejb.business.be.facade.DecodificaFacade;
import it.csi.prodis.prodisweb.lib.dto.common.DecodificaGenerica;
import it.csi.prodis.prodisweb.web.business.be.api.DecodificaApi;
import it.csi.prodis.prodisweb.web.util.annotation.Logged;

/**
 * Implementation for DecodificaApi
 */
@Logged
public class DecodificaApiServiceImpl extends BaseRestServiceImpl implements DecodificaApi {

	@EJB
	private DecodificaFacade decodificaFacade;

	@Override
	public Response getProvincia(SecurityContext securityContext, HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) {
		return invoke(() -> decodificaFacade.getProvincia());
	}

	@Override
	public Response getComune(Long idProvincia, String codComuneMin, String dsProTComune, Date data,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> decodificaFacade.getComune(idProvincia, codComuneMin, dsProTComune, data));
	}

	@Override
	public Response getStatoProspetto(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> decodificaFacade.getStatoProspetto());
	}

	@Override
	public Response getCcnl(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return invoke(() -> decodificaFacade.getCcnl());
	}

	@Override
	public Response getAtecofin(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return invoke(() -> decodificaFacade.getAtecofin());
	}

	@Override
	public Response postCcnlDecodifica(DecodificaGenerica filtro, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		return invoke(() -> decodificaFacade.postCcnlDecodifica(filtro));
	}

	@Override
	public Response postAtecofinDecodifica(DecodificaGenerica filtro, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		return invoke(() -> decodificaFacade.postAtecofinDecodifica(filtro));
	}

	@Override
	public Response postStatiEsteriDecodifica(DecodificaGenerica filtro, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		return invoke(() -> decodificaFacade.postStatiEsteriDecodifica(filtro));
	}

	@Override
	public Response postContrattiDecodifica(DecodificaGenerica filtro, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		return invoke(() -> decodificaFacade.postContrattiDecodifica(filtro));
	}

	@Override
	public Response postAssunzioneProtettaDecodifica(DecodificaGenerica filtro, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		return invoke(() -> decodificaFacade.postAssunzioneProtettaDecodifica(filtro));
	}

	@Override
	public Response postDichiaranteDecodifica(DecodificaGenerica filtro, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		return invoke(() -> decodificaFacade.postDichiaranteDecodifica(filtro));
	}

	@Override
	public Response postCategoriaEscluseDecodifica(DecodificaGenerica filtro, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		return invoke(() -> decodificaFacade.postCategoriaEscluseDecodifica(filtro));
	}

	@Override
	public Response postCausaSospensioneDecodifica(DecodificaGenerica filtro, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		return invoke(() -> decodificaFacade.postCausaSospensioneDecodifica(filtro));
	}

	@Override
	public Response postQualificaDecodifica(DecodificaGenerica filtro, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		return invoke(() -> decodificaFacade.postQualificaDecodifica(filtro));
	}

	@Override
	public Response postTipologiaLavoratoreDecodifica(DecodificaGenerica filtro, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		return invoke(() -> decodificaFacade.postTipologiaLavoratoreDecodifica(filtro));
	}

	@Override
	public Response postComuneDecodifica(DecodificaGenerica filtro, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		return invoke(() -> decodificaFacade.postComuneDecodifica(filtro));
	}

	@Override
	public Response postRegioneDecodifica(DecodificaGenerica filtro, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		return invoke(() -> decodificaFacade.postRegioneDecodifica(filtro));
	}

	@Override
	public Response postSoggettiDecodifica(DecodificaGenerica filtro, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		return invoke(() -> decodificaFacade.postSoggettiDecodifica(filtro));
	}

	@Override
	public Response postStatoConcessioneDecodifica(DecodificaGenerica filtro, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		return invoke(() -> decodificaFacade.postStatoConcessioneDecodifica(filtro));
	}
}
