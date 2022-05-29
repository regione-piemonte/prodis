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

import it.csi.prodis.prodisweb.ejb.business.be.service.request.base.BasePagedRequest;
import it.csi.prodis.prodisweb.lib.dto.prospetto.RicercaProspetto;

/**
 * The Class PostRicercaProspettoRequest.
 */
public class PostRicercaProspettoRequest extends BasePagedRequest {

	private final RicercaProspetto ricercaProspetto;

	/**
	 * Constructor
	 */
	public PostRicercaProspettoRequest(Integer page, Integer size, String sort, String direction,
			RicercaProspetto ricercaProspetto) {
		super(size, page, sort, direction);
		this.ricercaProspetto = ricercaProspetto;
	}

	/**
	 * @return the ricercaProspetto
	 */
	public RicercaProspetto getRicercaProspetto() {
		return ricercaProspetto;
	}

}
