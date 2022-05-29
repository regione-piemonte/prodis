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
import it.csi.prodis.prodisweb.ejb.business.be.service.request.datiprovinciali.PostProspettoProvinciaRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.datiprovinciali.PostProspettoProvinciaResponse;
import it.csi.prodis.prodisweb.ejb.entity.ProRProspettoProvincia;
import it.csi.prodis.prodisweb.ejb.exception.NotFoundException;
import it.csi.prodis.prodisweb.ejb.mapper.ProdisMappers;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProspettoProvincia;

/**
 * PostProspetto
 */
public class PostProspettoProvinciaService extends BaseDatiProvincialiService<PostProspettoProvinciaRequest, PostProspettoProvinciaResponse> {

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the helper for the configuration
	 * @param datiProvincialiDad        the DAD for the datiProvinciali
	 */
	public PostProspettoProvinciaService(ConfigurationHelper configurationHelper, DatiProvincialiDad datiProvincialiDad) {
		super(configurationHelper, datiProvincialiDad);
	}

	@Override
	protected void execute() {
		
		ProspettoProvincia prospettoProvincia = request.getProspettoProvincia();
		
		Long idProspetto = prospettoProvincia.getIdProspetto();
		Long idProvincia = prospettoProvincia.getProvincia().getId();
		
		ProRProspettoProvincia dummyPP = new ProRProspettoProvincia();
		if (prospettoProvincia.getId()==null) {
			dummyPP = datiProvincialiDad.insertProspettoProvincia(prospettoProvincia, idProspetto, idProvincia);
		} else {
			throw new NotFoundException("ProspettoProvincia");
		}

		response.setProspettoProvincia(ProdisMappers.PROSPETTO_PROVINCIA.toModel(dummyPP));
		
	}

}
