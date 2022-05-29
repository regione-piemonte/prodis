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
package it.csi.prodis.prodisweb.ejb.business.be.service.request.compensazioni;

import it.csi.prodis.prodisweb.ejb.business.be.service.request.base.BaseRequest;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProvCompensazioni;

public class PutCompensazioniRequest implements BaseRequest {

	private final ProvCompensazioni compensazioni;

	/**
	 * Constructor
	 * 
	 * @param Compensazioni
	 */
	public PutCompensazioniRequest(ProvCompensazioni compensazioni) {
		this.compensazioni = compensazioni;
	}

	public ProvCompensazioni getCompensazioni() {
		return compensazioni;
	}

	
}
