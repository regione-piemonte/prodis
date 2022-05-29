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
package it.csi.prodis.prodisweb.ejb.business.be.service.response.riepilogonazionale;

import it.csi.prodis.prodisweb.ejb.business.be.service.response.base.BasePostResponse;
import it.csi.prodis.prodisweb.lib.dto.prospetto.RiepilogoNazionale;

/**
 * The Class PostProspettoResponse.
 */
public class PutRiepilogoNazionaleResponse extends BasePostResponse<Integer, RiepilogoNazionale> {

	/** The model. */
	private RiepilogoNazionale riepilogoNazionale = new RiepilogoNazionale();


	public RiepilogoNazionale getRiepilogoNazionale() {
		return riepilogoNazionale;
	}

	public void setRiepilogoNazionale(RiepilogoNazionale riepilogoNazionale) {
		this.riepilogoNazionale = riepilogoNazionale;
	}

	@Override
	protected String getBaseUri() {
		return "intervento";
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PutRiepilogoNazionaleResponse [riepilogoNazionale=").append(riepilogoNazionale).append(", apiErrors=")
				.append(getApiErrors()).append("]");
		return builder.toString();
	}

	@Override
	protected RiepilogoNazionale getEntity() {
		return riepilogoNazionale;
	}

}
