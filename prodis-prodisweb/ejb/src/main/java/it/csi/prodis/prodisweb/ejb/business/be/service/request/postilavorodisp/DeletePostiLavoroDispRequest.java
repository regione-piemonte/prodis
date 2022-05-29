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
package it.csi.prodis.prodisweb.ejb.business.be.service.request.postilavorodisp;

import it.csi.prodis.prodisweb.ejb.business.be.service.request.base.BaseRequest;

public class DeletePostiLavoroDispRequest implements BaseRequest {

	private final Long idPostiLavoroDisp;

	/**
	 * Constructor
	 * 
	 * @param PostiLavoroDisp
	 */
	public DeletePostiLavoroDispRequest(Long idPostiLavoroDisp) {
		this.idPostiLavoroDisp = idPostiLavoroDisp;
	}

	public Long getIdPostiLavoroDisp() {
		return idPostiLavoroDisp;
	}

	
}
