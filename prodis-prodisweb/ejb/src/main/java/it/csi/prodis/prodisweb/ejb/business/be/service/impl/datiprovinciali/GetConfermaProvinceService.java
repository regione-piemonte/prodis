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
package it.csi.prodis.prodisweb.ejb.business.be.service.impl.datiprovinciali;


import java.util.ArrayList;
import java.util.List;

import it.csi.prodis.prodisweb.ejb.business.be.dad.DatiProvincialiDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.datiprovinciali.GetConfermaProvinceRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.datiprovinciali.GetConfermaProvinceResponse;
import it.csi.prodis.prodisweb.ejb.entity.RitornoPassaggioQ3;
import it.csi.prodis.prodisweb.ejb.util.ValidatorConfermaDatiProvinciali;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.dto.ApiError;


/**
 * Retrieves an elenco compensazioni by its idprospetto
 */
public class GetConfermaProvinceService
		extends BaseDatiProvincialiService<GetConfermaProvinceRequest, GetConfermaProvinceResponse> {

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the configuration helper
	 * @param datiProvincialiDad    the dati provinciali DAD
	 */
	public GetConfermaProvinceService(ConfigurationHelper configurationHelper,
			DatiProvincialiDad datiProvincialiDad) {
		super(configurationHelper, datiProvincialiDad);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getIdProspetto(), "idProspetto");
	}

	@Override
	protected void execute() {
		
		List<ApiError> apiErrors = new ArrayList<ApiError>();
		
		ValidatorConfermaDatiProvinciali valida = new ValidatorConfermaDatiProvinciali(datiProvincialiDad);
		
		valida.validaConfermaEProsegui(request.getIdProspetto(), apiErrors);
		
		if (apiErrors == null || apiErrors.size() == 0) {
			
			RitornoPassaggioQ3 rit = datiProvincialiDad.confermaProvince(request.getIdProspetto());
			
			response.setRit(rit);
			
			if (rit.getMessaggio() == null || "".equalsIgnoreCase(rit.getMessaggio())) {
				
//				Prospetto prospetto = datiProvincialiDad.getProspetto(request.getIdProspetto());
//				
//				List<VistaElencoProvQ2> elencoProvince =  datiProvincialiDad.getElencoProvQ2ByIdProspetto(request.getIdProspetto());
//				
//				valida.validaMessaggioAvviso( prospetto, apiErrors, elencoProvince);
//
//				valida.checkWarningPerScoperture( prospetto, elencoProvince, apiErrors);
				
				if (apiErrors != null || apiErrors.size() > 0) {
					
					response.setApiErrors(apiErrors);
					
				}
				
			}
			
		} else {
			
			response.setApiErrors(apiErrors);
			
		}
		
	}

}
