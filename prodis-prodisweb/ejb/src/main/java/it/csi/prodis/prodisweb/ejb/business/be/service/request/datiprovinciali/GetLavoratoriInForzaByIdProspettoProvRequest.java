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
package it.csi.prodis.prodisweb.ejb.business.be.service.request.datiprovinciali;

import it.csi.prodis.prodisweb.ejb.business.be.service.request.base.BaseRequest;



/**
 * Request for reading the categorieEscluse from DatiProvinciali by its idProspettoProv
 */
public class GetLavoratoriInForzaByIdProspettoProvRequest implements BaseRequest {

	private final Long idProspettoProv;

	/**
	 * Constructor
	 * @param idProspettoProv the ProspettoProvinciale's id
	 */
	public GetLavoratoriInForzaByIdProspettoProvRequest(Long idProspettoProv) {
		this.idProspettoProv = idProspettoProv;
	}


	/**
	 * @return the idProspetto
	 */
	
	public Long getIdProspettoProv() {
		return idProspettoProv;
	}
	
}
