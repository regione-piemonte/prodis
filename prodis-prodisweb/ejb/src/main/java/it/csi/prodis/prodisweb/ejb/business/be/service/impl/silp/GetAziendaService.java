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

import it.csi.prodis.prodisweb.ejb.business.be.service.impl.base.BaseService;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.silp.GetAziendaRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.silp.GetAziendaResponse;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.dto.error.MsgProdis;
import it.csi.prodis.prodisweb.lib.dto.prospetto.DatiAzienda;
import it.csi.prodis.prodisweb.lib.external.impl.silp.AziendaHelperImpl;
import it.csi.prodis.prodisweb.lib.util.ProdisClassUtils;
import it.csi.prodis.prodisweb.lib.util.ProdisUtility;

/**
 * GetAzienda
 */
public class GetAziendaService extends BaseService<GetAziendaRequest, GetAziendaResponse> {

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the helper for the configuration
	 * @param ruoloDad            the DAD for the ruolo
	 */
	public GetAziendaService(ConfigurationHelper configurationHelper) {
		super(configurationHelper);
	}

	@Override
	protected void execute() {
		try {
			String codiceFiscale = request.getCodiceFiscale();
			if (ProdisUtility.validateCodiceFiscaleOPartitaIva(codiceFiscale).equals("0")) {
				checkBusinessCondition(false, MsgProdis.PROSLPE0001.getError());
			}

			AziendaHelperImpl aziendaHelperImpl = new AziendaHelperImpl();
			DatiAzienda datiAzienda = aziendaHelperImpl.ricercaPerCodiceFiscale(codiceFiscale);

			response.setDatiAzienda(datiAzienda);
		} catch (Exception e) {
			log.error(ProdisClassUtils.truncClassName(getClass()) + " Eccezione !!", e);
//			throw new RuntimeException(e);
		}
	}

}
