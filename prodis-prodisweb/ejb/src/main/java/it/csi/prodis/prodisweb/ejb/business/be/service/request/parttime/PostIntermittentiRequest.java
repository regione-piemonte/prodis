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

/**
 * The Class PostIntermittentiRequest.
 */
public class PostIntermittentiRequest implements BaseRequest {

	private final ProvIntermittenti intermittenti;
	private final Long idPartTime;

	/**
	 * Constructor
	 * 
	 * @param ProvIntermittenti
	 */
	public PostIntermittentiRequest(ProvIntermittenti intermittenti, Long idPartTime) {
		this.intermittenti = intermittenti;
		this.idPartTime = idPartTime;
	}

	public ProvIntermittenti getIntermittenti() {
		return intermittenti;
	}
	
	public Long getIdPartTime() {
		return idPartTime;
	}


}
