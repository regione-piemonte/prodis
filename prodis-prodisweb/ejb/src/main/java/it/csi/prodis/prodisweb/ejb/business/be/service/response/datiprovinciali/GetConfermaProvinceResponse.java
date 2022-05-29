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
package it.csi.prodis.prodisweb.ejb.business.be.service.response.datiprovinciali;



import it.csi.prodis.prodisweb.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.prodis.prodisweb.ejb.entity.RitornoPassaggioQ3;

public class GetConfermaProvinceResponse extends BaseGetResponse<RitornoPassaggioQ3> {

	private RitornoPassaggioQ3 rit;

	@Override
	protected RitornoPassaggioQ3 getEntity() {
		return rit;
	}

	public RitornoPassaggioQ3 getRit() {
		return rit;
	}

	public void setRit(RitornoPassaggioQ3 rit) {
		this.rit = rit;
	}

}
