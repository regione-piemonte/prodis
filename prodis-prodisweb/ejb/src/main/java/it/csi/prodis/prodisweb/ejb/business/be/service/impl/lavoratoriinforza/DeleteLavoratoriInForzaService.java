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
package it.csi.prodis.prodisweb.ejb.business.be.service.impl.lavoratoriinforza;

import it.csi.prodis.prodisweb.ejb.business.be.dad.LavoratoriInForzaDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.lavoratoriinforza.DeleteLavoratoriInForzaRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.lavoratoriinforza.DeleteLavoratoriInForzaResponse;
import it.csi.prodis.prodisweb.ejb.exception.NotFoundException;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.dto.prospetto.LavoratoriInForza;

public class DeleteLavoratoriInForzaService extends BaseLavoratoriInForzaService<DeleteLavoratoriInForzaRequest, DeleteLavoratoriInForzaResponse> {
	/**
	 * Constructor
	 * 
	 * @param configurationHelper the helper for the configuration
	 * @param datiProvincialiDad        the DAD for the datiProvinciali
	 */
	public DeleteLavoratoriInForzaService(ConfigurationHelper configurationHelper, LavoratoriInForzaDad lavoratoriInForzaDad) {
		super(configurationHelper, lavoratoriInForzaDad);
	}

	@Override
	protected void execute() {
		
		Long idLavoratoriInForza = request.getIdLavoratoriInForza();
		
		LavoratoriInForza lavoratoriDeleted = null;
		if (idLavoratoriInForza!=null) {
			lavoratoriDeleted = lavoratoriInForzaDad.deleteSingleLavoratoriInForza(idLavoratoriInForza);
		} else {
			throw new NotFoundException("LavoratoriInForza");
		}

		response.setLavoratoriInForza(lavoratoriDeleted);
	}
}
