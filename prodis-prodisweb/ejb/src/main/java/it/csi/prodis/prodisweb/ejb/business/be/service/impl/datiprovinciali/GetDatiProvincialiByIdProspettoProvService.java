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



import it.csi.prodis.prodisweb.ejb.business.be.dad.DatiProvincialiDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.datiprovinciali.GetDatiProvincialiByIdProspettoProvRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.datiprovinciali.GetDatiProvincialiByIdProspettoProvResponse;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.dto.prospetto.DatiProvinciali;




/**
 * Retrieves an testataOrdine by its id
 */
public class GetDatiProvincialiByIdProspettoProvService extends BaseDatiProvincialiService<GetDatiProvincialiByIdProspettoProvRequest, GetDatiProvincialiByIdProspettoProvResponse> {

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad    the testataOrdine DAD
	 */
	public GetDatiProvincialiByIdProspettoProvService(ConfigurationHelper configurationHelper, DatiProvincialiDad datiProvincialiDad) {
		super(configurationHelper, datiProvincialiDad);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getId(), "idProspettoProv");
	}

	@Override
	protected void execute() {
		DatiProvinciali datiProvinciali = datiProvincialiDad.getDatiProvincialiByIdProspettoProv(request.getId());
		response.setDatiProvinciali(datiProvinciali);
	}

	

}
