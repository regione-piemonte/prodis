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
package it.csi.prodis.prodisweb.ejb.business.be.service.response.postilavorodisp;

import it.csi.prodis.prodisweb.ejb.business.be.service.response.base.BasePostResponse;
import it.csi.prodis.prodisweb.lib.dto.prospetto.PostiLavoroDisp;

public class DeletePostiLavoroDispResponse extends BasePostResponse<Integer, PostiLavoroDisp> {

	/** The model. */
	private PostiLavoroDisp postiLavoroDisp = new PostiLavoroDisp();





	public PostiLavoroDisp getPostiLavoroDisp() {
		return postiLavoroDisp;
	}

	public void setPostiLavoroDisp(PostiLavoroDisp postiLavoroDisp) {
		this.postiLavoroDisp = postiLavoroDisp;
	}

	@Override
	protected String getBaseUri() {
		return "intervento";
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DeletePostiLavoroDispResponse [postiLavoroDisp=").append(postiLavoroDisp).append(", apiErrors=")
				.append(getApiErrors()).append("]");
		return builder.toString();
	}

	@Override
	protected PostiLavoroDisp getEntity() {
		return postiLavoroDisp;
	}

}
