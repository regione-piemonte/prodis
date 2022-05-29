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
import it.csi.prodis.prodisweb.lib.dto.prospetto.LavoratoriInForza;

/**
 * The Class PostProspettoRequest.
 */
public class PostLavoratoriInForzaRequest implements BaseRequest {

	private final LavoratoriInForza lavoratoriInForza;
	private final Boolean flagWarning;

	/**
	 * Constructor
	 * 
	 * @param CategoriaEscluse
	 */
	public PostLavoratoriInForzaRequest(LavoratoriInForza lavoratoriInForza, Boolean flagWarning) {
		this.lavoratoriInForza = lavoratoriInForza;
		this.flagWarning = flagWarning;
	}

	public LavoratoriInForza getLavoratoriInForza() {
		return lavoratoriInForza;
	}
	
	public Boolean getFlagWarning() {
		return flagWarning;
	}

}
