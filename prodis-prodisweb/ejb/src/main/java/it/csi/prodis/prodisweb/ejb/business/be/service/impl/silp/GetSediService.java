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

import java.util.List;

import it.csi.prodis.prodisweb.ejb.business.be.service.impl.base.BaseService;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.silp.GetSediRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.silp.GetSediResponse;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.dto.prospetto.SedeLegale;
import it.csi.prodis.prodisweb.lib.external.impl.silp.AziendaHelperImpl;
import it.csi.prodis.prodisweb.lib.util.ProdisClassUtils;

public class GetSediService extends BaseService<GetSediRequest, GetSediResponse> {

	public GetSediService(ConfigurationHelper configurationHelper) {
		super(configurationHelper);
	}

	@Override
	protected void execute() {
		try {
			String idAzienda = request.getIdAzienda();
			AziendaHelperImpl aziendaHelperImpl = new AziendaHelperImpl();
			List<SedeLegale> leSedi = aziendaHelperImpl.elencoSediByIdAzienda(idAzienda);
			response.setLeSedi(leSedi);
		} catch (Exception e) {
			log.error(ProdisClassUtils.truncClassName(getClass()) + " Eccezione !!", e);
		}
	}

}
