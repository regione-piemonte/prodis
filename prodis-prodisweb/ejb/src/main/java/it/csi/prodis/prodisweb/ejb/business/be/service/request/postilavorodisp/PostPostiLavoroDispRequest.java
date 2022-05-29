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
import it.csi.prodis.prodisweb.lib.dto.prospetto.PostiLavoroDisp;

/**
 * The Class PostProspettoRequest.
 */
public class PostPostiLavoroDispRequest implements BaseRequest {

	private final PostiLavoroDisp postiLavoroDisp;

	/**
	 * Constructor
	 * 
	 * @param PostiLavoroDisp
	 */
	public PostPostiLavoroDispRequest(PostiLavoroDisp postiLavoroDisp) {
		this.postiLavoroDisp = postiLavoroDisp;
	}

	public PostiLavoroDisp getPostiLavoroDisp() {
		return postiLavoroDisp;
	}

	
}
