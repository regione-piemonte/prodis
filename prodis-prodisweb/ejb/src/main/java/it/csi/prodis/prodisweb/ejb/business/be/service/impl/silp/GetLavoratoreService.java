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
package it.csi.prodis.prodisweb.ejb.business.be.service.impl.silp;

import it.csi.prodis.prodisweb.ejb.business.be.dad.ProspettoDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.prospetto.BaseProspettoService;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.silp.GetLavoratoreRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.silp.GetLavoratoreResponse;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.dto.error.MsgProdis;
import it.csi.prodis.prodisweb.lib.dto.prospetto.LavoratoriSilp;
import it.csi.prodis.prodisweb.lib.external.impl.silp.LavoratoreHelperImpl;
import it.csi.prodis.prodisweb.lib.util.ProdisClassUtils;
import it.csi.prodis.prodisweb.lib.util.ProdisUtility;

/**
 * GetLavoratore
 */
public class GetLavoratoreService extends BaseProspettoService<GetLavoratoreRequest, GetLavoratoreResponse> {

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the helper for the configuration
	 * @param ruoloDad            the DAD for the ruolo
	 */
	public GetLavoratoreService(ConfigurationHelper configurationHelper, ProspettoDad prospettoDad) {
		super(configurationHelper, prospettoDad);
	}

	@Override
	protected void execute() {
		try {
			String codiceFiscale = request.getCodiceFiscale();
			checkBusinessCondition(ProdisUtility.controllaCFConOmocodia(codiceFiscale),
					MsgProdis.PROSLPE0002.getError());

			LavoratoreHelperImpl lavoratoreHelperImpl = new LavoratoreHelperImpl();
			LavoratoriSilp lavoratoriSilp = lavoratoreHelperImpl.ricercaPerCodiceFiscale(request.getCodiceFiscale());

			response.setLavoratoriSilp(lavoratoriSilp);
		} catch (Exception e) {
			log.error(ProdisClassUtils.truncClassName(getClass()) + " Eccezione !!", e);
		}
	}

}
