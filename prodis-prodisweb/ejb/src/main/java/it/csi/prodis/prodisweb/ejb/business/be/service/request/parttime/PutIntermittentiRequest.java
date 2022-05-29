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
package it.csi.prodis.prodisweb.ejb.business.be.service.request.parttime;

import it.csi.prodis.prodisweb.ejb.business.be.service.request.base.BaseRequest;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProvIntermittenti;

public class PutIntermittentiRequest implements BaseRequest {

	private final ProvIntermittenti intermittenti;

	/**
	 * Constructor
	 * 
	 * @param CategoriaEscluse
	 */
	public PutIntermittentiRequest(ProvIntermittenti intermittenti) {
		this.intermittenti = intermittenti;
	}

	public ProvIntermittenti getIntermittenti() {
		return intermittenti;
	}



	
}
