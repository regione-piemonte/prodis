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
package it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto;


import it.csi.prodis.prodisweb.ejb.business.be.service.response.base.BaseGetResponse;


/**
 * Response for reading Prospetto by its id.
 */
public class SalvaPdfResponse extends BaseGetResponse<String> {

	private String res;
	
	
	

	public String getRes() {
		return res;
	}




	public void setRes(String res) {
		this.res = res;
	}




	@Override
	protected String getEntity() {
		return res;
	}



	
}
