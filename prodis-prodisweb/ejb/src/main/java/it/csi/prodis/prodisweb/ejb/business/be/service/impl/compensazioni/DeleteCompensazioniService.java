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
package it.csi.prodis.prodisweb.ejb.business.be.service.impl.compensazioni;

import it.csi.prodis.prodisweb.ejb.business.be.dad.CompensazioniDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.compensazioni.DeleteCompensazioniRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.compensazioni.DeleteCompensazioniResponse;
import it.csi.prodis.prodisweb.ejb.entity.ProDProvCompensazioni;
import it.csi.prodis.prodisweb.ejb.exception.NotFoundException;
import it.csi.prodis.prodisweb.ejb.mapper.ProdisMappers;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;

public class DeleteCompensazioniService extends BaseCompensazioniService<DeleteCompensazioniRequest, DeleteCompensazioniResponse> {
	/**
	 * Constructor
	 * 
	 * @param configurationHelper the helper for the configuration
	 * @param compensazioniDad        the DAD for the compensazioni
	 */
	public DeleteCompensazioniService(ConfigurationHelper configurationHelper, CompensazioniDad compensazioniDad) {
		super(configurationHelper, compensazioniDad);
	}

	@Override
	protected void execute() {
		
		Long idCompensazioni = request.getIdCompensazioni();
		
		ProDProvCompensazioni compensazioniDeleted = null;
		if (idCompensazioni!=null) {
			compensazioniDeleted = compensazioniDad.deleteCompensazioni(idCompensazioni);
		} else {
			throw new NotFoundException("Compensazioni");
		}

		response.setCompensazioni(ProdisMappers.PROV_COMPENSAZIONI.toModel(compensazioniDeleted));
	}
}
