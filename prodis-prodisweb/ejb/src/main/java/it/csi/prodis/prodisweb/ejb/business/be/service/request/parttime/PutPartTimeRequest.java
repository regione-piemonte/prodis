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
import it.csi.prodis.prodisweb.lib.dto.prospetto.PartTime;

public class PutPartTimeRequest implements BaseRequest {

	private final PartTime partTime;

	/**
	 * Constructor
	 * 
	 * @param CategoriaEscluse
	 */
	public PutPartTimeRequest(PartTime partTime) {
		this.partTime = partTime;
	}

	public PartTime getPartTime() {
		return partTime;
	}



	
}
