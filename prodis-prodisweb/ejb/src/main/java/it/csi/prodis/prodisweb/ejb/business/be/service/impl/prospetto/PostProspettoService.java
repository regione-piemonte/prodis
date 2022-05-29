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

import java.util.ArrayList;
import java.util.List;

import it.csi.prodis.prodisweb.ejb.business.be.dad.DatiProvincialiDad;
import it.csi.prodis.prodisweb.ejb.business.be.dad.ProspettoDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto.PostProspettoRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto.PostProspettoResponse;
import it.csi.prodis.prodisweb.ejb.exception.NotFoundException;
import it.csi.prodis.prodisweb.ejb.util.ValidatorProspetto;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.dto.ApiError;
import it.csi.prodis.prodisweb.lib.dto.prospetto.Prospetto;

/**
 * PostProspetto
 */
public class PostProspettoService extends BaseInsProspettoService<PostProspettoRequest, PostProspettoResponse> {

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the helper for the configuration
	 * @param prospettoDad        the DAD for the prospetto
	 */
	public PostProspettoService(ConfigurationHelper configurationHelper, ProspettoDad prospettoDad, DatiProvincialiDad datiProvincialiDad) {
		super(configurationHelper, prospettoDad, datiProvincialiDad);
	}

	@Override
	protected void execute() {
		
		Prospetto prospetto = request.getProspetto();
		
		boolean flagBozza = request.isFlagBozza();
		ValidatorProspetto valida = new ValidatorProspetto(prospettoDad, datiProvincialiDad);
		
		List<ApiError> apiErrors = new ArrayList<ApiError>();
		List<ApiError> apiWarnings = new ArrayList<ApiError>();
		valida.validaInBozza(prospetto, apiErrors);

		if (apiErrors == null || apiErrors.size() == 0) {
			if (!flagBozza) {
				valida.validaConfermaEProsegui(prospetto, apiErrors, apiWarnings);
				if (apiWarnings != null && apiWarnings.size() != 0) {
					response.setWarnings(apiWarnings);
				}
			}
		}

		Prospetto newProspetto = null;
		
		if (apiErrors == null || apiErrors.size() == 0) {
			if (prospetto.getId()==null) {
				if (!flagBozza) {
					prospetto.setFlgConfermatoQ1("S");
				} else {
					prospetto.setFlgConfermatoQ1("N");
				}
				newProspetto = prospettoDad.insertProspetto(prospetto);
			} else {
				throw new NotFoundException("Prospetto");
			}
		} else {
			response.setApiErrors(apiErrors);
		}
		
		response.setProspetto(newProspetto);
	}

}
