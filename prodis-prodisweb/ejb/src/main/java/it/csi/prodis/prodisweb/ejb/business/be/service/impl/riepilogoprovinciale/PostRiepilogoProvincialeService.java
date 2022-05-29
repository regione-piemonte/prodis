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
package it.csi.prodis.prodisweb.ejb.business.be.service.impl.riepilogoprovinciale;

import javax.ws.rs.NotFoundException;

import it.csi.prodis.prodisweb.ejb.business.be.dad.RiepilogoProvincialeDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.riepilogoprovinciale.PostRiepilogoProvincialeRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.riepilogoprovinciale.PostRiepilogoProvincialeResponse;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.dto.prospetto.RiepilogoProvinciale;

public class PostRiepilogoProvincialeService extends BaseRiepilogoProvincialeService<PostRiepilogoProvincialeRequest, PostRiepilogoProvincialeResponse> {
	/**
	 * Constructor
	 * 
	 * @param configurationHelper the helper for the configuration
	 * @param riepilogoProvincialeDad        the DAD for the riepilogoProvinciale
	 */
	public PostRiepilogoProvincialeService(ConfigurationHelper configurationHelper, RiepilogoProvincialeDad riepilogoProvincialeDad) {
		super(configurationHelper, riepilogoProvincialeDad);
	}

	@Override
	protected void execute() {
		RiepilogoProvinciale riepilogoProvinciale = request.getRiepilogoProvinciale();
		
		
		if (riepilogoProvinciale.getId()==null) {
			riepilogoProvinciale = riepilogoProvincialeDad.insertRiepilogoProvinciale(riepilogoProvinciale, 1L);
		} else {
			throw new NotFoundException("RiepilogoProvinciale");
		}

		response.setRiepilogoProvinciale(riepilogoProvinciale);
	}
}
