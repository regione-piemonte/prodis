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
import it.csi.prodis.prodisweb.ejb.business.be.service.request.datiprovinciali.DeleteDatiProvincialiRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.datiprovinciali.DeleteDatiProvincialiResponse;
import it.csi.prodis.prodisweb.ejb.exception.NotFoundException;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;

public class DeleteDatiProvincialiService extends BaseDatiProvincialiService<DeleteDatiProvincialiRequest, DeleteDatiProvincialiResponse> {

	public DeleteDatiProvincialiService(ConfigurationHelper configurationHelper, DatiProvincialiDad datiProvincialiDad) {
		super(configurationHelper, datiProvincialiDad);
	}

	@Override
	protected void execute() {
		
		Long idProspettoProv = request.getIdProspettoProv();
		
		Boolean cancellato = false;
		if (idProspettoProv!=null) {
			cancellato = datiProvincialiDad.deleteDatiProvinciali(idProspettoProv);
		} else {
			throw new NotFoundException("ProspettoProvincia");
		}
		
		response.setCancellato(cancellato);

	}

}
