/*-
 * ========================LICENSE_START=================================
 * PRODIS Servizi - EJB submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodissrv.ejb.business.be.service.response.prospetto;

import it.csi.prodis.prodissrv.ejb.business.be.service.response.base.BaseGetResponse;

public class InitRicezioneProspettoDaSpicomResponse extends BaseGetResponse<Integer> {
	private Integer id;
	

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	@Override
	protected Integer getEntity() {
		// TODO Auto-generated method stub
		return id;
	}

}
