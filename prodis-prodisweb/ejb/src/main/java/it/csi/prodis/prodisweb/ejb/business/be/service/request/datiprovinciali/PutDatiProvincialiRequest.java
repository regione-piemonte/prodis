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
import it.csi.prodis.prodisweb.lib.dto.prospetto.DatiProvinciali;

public class PutDatiProvincialiRequest implements BaseRequest {

	private final DatiProvinciali datiProvinciali;
	private final boolean flagBozza;
	/**
	 * Constructor
	 * 
	 * @param prospetto the prospetto
	 */
	public PutDatiProvincialiRequest(DatiProvinciali datiProvinciali, boolean flagBozza) {
		this.datiProvinciali = datiProvinciali;
		this.flagBozza = flagBozza;
	}

	public DatiProvinciali getDatiProvinciali() {
		return datiProvinciali;
	}

	public boolean isFlagBozza() {
		return flagBozza;
	}
	



}
