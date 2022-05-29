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
package it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto;

import java.util.List;

import it.csi.prodis.prodisweb.ejb.business.be.service.request.base.BaseRequest;

public class SalvaPdfRequest implements BaseRequest {

	private final List<Long> idProspettos;
	

	/**
	 * Constructor
	 * 
	 * @param id the id
	 */
	public SalvaPdfRequest(List<Long> idProspettos) {
		this.idProspettos = idProspettos;
	}


	public List<Long> getIdProspettos() {
		return idProspettos;
	}


}
