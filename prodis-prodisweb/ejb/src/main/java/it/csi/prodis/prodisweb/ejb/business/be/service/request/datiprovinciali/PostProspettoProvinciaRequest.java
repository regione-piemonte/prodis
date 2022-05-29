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
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProspettoProvincia;

/**
 * The Class PostProspettoRequest.
 */
public class PostProspettoProvinciaRequest implements BaseRequest {

	private final ProspettoProvincia prospettoProvincia;

	/**
	 * Constructor
	 * 
	 * @param prospetto the prospetto
	 */
	public PostProspettoProvinciaRequest(ProspettoProvincia prospettoProvincia) {
		this.prospettoProvincia = prospettoProvincia;
	}

	public ProspettoProvincia getProspettoProvincia() {
		return prospettoProvincia;
	}


}
