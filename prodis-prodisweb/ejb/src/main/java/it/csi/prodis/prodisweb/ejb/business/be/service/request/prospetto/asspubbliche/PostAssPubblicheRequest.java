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
package it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto.asspubbliche;

import java.util.List;

import it.csi.prodis.prodisweb.ejb.business.be.service.request.base.BaseRequest;
import it.csi.prodis.prodisweb.lib.dto.prospetto.AssPubbliche;

public class PostAssPubblicheRequest implements BaseRequest {

	private final List<AssPubbliche> assPubbliche;

	/**
	 * Constructor
	 * 
	 * @param prospetto the prospetto
	 */
	public PostAssPubblicheRequest(List<AssPubbliche> assPubbliche) {
		this.assPubbliche = assPubbliche;
	}

	/**
	 * @return the prospetto
	 */
	public List<AssPubbliche> getAssPubbliche() {
		return assPubbliche;
	}

}
