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
package it.csi.prodis.prodisweb.ejb.business.be.service.response.decodifica;

import java.util.ArrayList;
import java.util.List;

import it.csi.prodis.prodisweb.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Ccnl;

/**
 * The Class GetCcnlResponse.
 */
public class GetCcnlResponse extends BaseGetResponse<List<Ccnl>> {

	private List<Ccnl> ccnls = new ArrayList<>();

	public List<Ccnl> getCcnls() {
		return ccnls;
	}

	public void setCcnls(List<Ccnl> ccnls) {
		this.ccnls = ccnls;
	}

	@Override
	protected List<Ccnl> getEntity() {
		return ccnls;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getSimpleName() + " [ccnls=").append(ccnls).append(", apiErrors=")
				.append(getApiErrors()).append("]");
		return builder.toString();
	}

}
