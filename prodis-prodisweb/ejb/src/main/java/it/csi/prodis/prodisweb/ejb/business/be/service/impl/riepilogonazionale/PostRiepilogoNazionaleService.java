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
package it.csi.prodis.prodisweb.ejb.business.be.service.impl.riepilogonazionale;

import javax.ws.rs.NotFoundException;

import it.csi.prodis.prodisweb.ejb.business.be.dad.RiepilogoNazionaleDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.riepilogonazionale.PostRiepilogoNazionaleRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.riepilogonazionale.PostRiepilogoNazionaleResponse;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.dto.prospetto.RiepilogoNazionale;

public class PostRiepilogoNazionaleService extends BaseRiepilogoNazionaleService<PostRiepilogoNazionaleRequest, PostRiepilogoNazionaleResponse> {
	/**
	 * Constructor
	 * 
	 * @param configurationHelper the helper for the configuration
	 * @param datiProvincialiDad        the DAD for the datiProvinciali
	 */
	public PostRiepilogoNazionaleService(ConfigurationHelper configurationHelper, RiepilogoNazionaleDad riepilogoNazionaleDad) {
		super(configurationHelper, riepilogoNazionaleDad);
	}

	@Override
	protected void execute() {
		RiepilogoNazionale riepilogoNazionale = request.getRiepilogoNazionale();
		
		
		if (riepilogoNazionale.getId()==null) {
			riepilogoNazionale = riepilogoNazionaleDad.insertRiepilogoNazionale(riepilogoNazionale, 1L);
		} else {
			throw new NotFoundException("RiepilogoNazionale");
		}

		response.setRiepilogoNazionale(riepilogoNazionale);
	}
}
