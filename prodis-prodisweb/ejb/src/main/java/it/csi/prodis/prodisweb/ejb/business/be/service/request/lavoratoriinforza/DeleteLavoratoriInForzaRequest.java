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
package it.csi.prodis.prodisweb.ejb.business.be.service.request.lavoratoriinforza;

import it.csi.prodis.prodisweb.ejb.business.be.service.request.base.BaseRequest;

public class DeleteLavoratoriInForzaRequest implements BaseRequest {

	private final Long idLavoratoriInForza;

	/**
	 * Constructor
	 * 
	 * @param CategoriaEscluse
	 */
	public DeleteLavoratoriInForzaRequest(Long idLavoratoriInForza) {
		this.idLavoratoriInForza = idLavoratoriInForza;
	}

	public Long getIdLavoratoriInForza() {
		return idLavoratoriInForza;
	}


	
}
