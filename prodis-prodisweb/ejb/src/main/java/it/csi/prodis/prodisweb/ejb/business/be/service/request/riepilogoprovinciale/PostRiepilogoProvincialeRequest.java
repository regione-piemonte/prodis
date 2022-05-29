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
package it.csi.prodis.prodisweb.ejb.business.be.service.request.riepilogoprovinciale;

import it.csi.prodis.prodisweb.ejb.business.be.service.request.base.BaseRequest;
import it.csi.prodis.prodisweb.lib.dto.prospetto.RiepilogoProvinciale;

/**
 * The Class PostRiepilogoNazionaleRequest.
 */
public class PostRiepilogoProvincialeRequest implements BaseRequest {

	private final RiepilogoProvinciale riepilogoProvinciale;

	/**
	 * Constructor
	 * 
	 * @param RiepilogoProvinciale
	 */
	public PostRiepilogoProvincialeRequest(RiepilogoProvinciale riepilogoProvinciale) {
		this.riepilogoProvinciale = riepilogoProvinciale;
	}

	public RiepilogoProvinciale getRiepilogoProvinciale() {
		return riepilogoProvinciale;
	}

}
