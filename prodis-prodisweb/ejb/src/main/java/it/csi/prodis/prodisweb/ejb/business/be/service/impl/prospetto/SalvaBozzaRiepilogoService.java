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

import it.csi.prodis.prodisweb.ejb.business.be.dad.ProspettoDad;
import it.csi.prodis.prodisweb.ejb.business.be.dad.RiepilogoNazionaleDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto.PostProspettoRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto.PostProspettoResponse;
import it.csi.prodis.prodisweb.ejb.util.ValidatorRiepilogo;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.dto.ApiError;
import it.csi.prodis.prodisweb.lib.dto.prospetto.Prospetto;
import it.csi.prodis.prodisweb.lib.dto.prospetto.RiepilogoNazionale;

/**
 * PostProspetto
 */
public class SalvaBozzaRiepilogoService extends BaseSalvaBozzaRiepilogoService<PostProspettoRequest, PostProspettoResponse> {

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the helper for the configuration
	 * @param prospettoDad        the DAD for the prospetto
	 */
	public SalvaBozzaRiepilogoService(ConfigurationHelper configurationHelper, ProspettoDad prospettoDad, RiepilogoNazionaleDad riepilogoNazionaleDad) {
		super(configurationHelper, prospettoDad, riepilogoNazionaleDad);
	}

	@Override
	protected void execute() {
		
		Prospetto prospetto = request.getProspetto();
		ValidatorRiepilogo valida = new ValidatorRiepilogo(prospettoDad);
		List<ApiError> apiErrors = new ArrayList<ApiError>();
		valida.validaInBozza(prospetto, apiErrors);
		
		if (apiErrors != null && apiErrors.size() > 0) {
			response.setApiErrors(apiErrors);
		} else {
			
			RiepilogoNazionale riepilogo = prospetto.getRiepilogoNazionale();

			prospetto.setRiepilogoNazionale(null);
			
			prospetto = prospettoDad.updateProspetto(prospetto);
			
			if (riepilogo.getId()==null) {
				riepilogo = riepilogoNazionaleDad.insertRiepilogoNazionale(riepilogo, prospetto.getId());
			} else {
				riepilogo = riepilogoNazionaleDad.updateRiepilogoNazionale(riepilogo, prospetto.getId(), riepilogo.getId().longValue());
			}
			
			prospetto.setRiepilogoNazionale(riepilogo);
			
			response.setProspetto(prospetto);
			
		}
	}
}
