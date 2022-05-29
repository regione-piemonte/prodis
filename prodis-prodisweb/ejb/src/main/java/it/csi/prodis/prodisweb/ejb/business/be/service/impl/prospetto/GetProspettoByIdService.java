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

import it.csi.prodis.prodisweb.ejb.business.be.dad.ProspettoDad;
import it.csi.prodis.prodisweb.ejb.business.be.dad.RiepilogoNazionaleDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto.GetProspettoByIdRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto.GetPropspettoByIdResponse;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.dto.prospetto.Prospetto;
import it.csi.prodis.prodisweb.lib.dto.prospetto.RiepilogoNazionale;



/**
 * Retrieves an testataOrdine by its id
 */
public class GetProspettoByIdService extends BaseProspettoService<GetProspettoByIdRequest, GetPropspettoByIdResponse> {
	
	RiepilogoNazionaleDad riepilogoNazionaleDad;

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad    the testataOrdine DAD
	 */
	public GetProspettoByIdService(ConfigurationHelper configurationHelper, ProspettoDad prospettoDad, RiepilogoNazionaleDad riepilogoNazionaleDad) {
		super(configurationHelper, prospettoDad);
		this.riepilogoNazionaleDad = riepilogoNazionaleDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getId(), "id");
	}

	@Override
	protected void execute() {
		Prospetto prospetto = prospettoDad.getProspetto(request.getId());
		RiepilogoNazionale rn = riepilogoNazionaleDad.getRiepilogoNazionaleByIdProspetto(prospetto.getId());
		prospetto.setRiepilogoNazionale(rn);
		response.setProspetto(prospetto);
	}

	

}
