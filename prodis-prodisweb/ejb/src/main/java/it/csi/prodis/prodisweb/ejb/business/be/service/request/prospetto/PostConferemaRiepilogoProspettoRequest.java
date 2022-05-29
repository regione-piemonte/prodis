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
package it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto;

import it.csi.prodis.prodisweb.ejb.business.be.service.request.base.BaseRequest;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ConfermaRiepilogoProspetto;

/**
 * The Class PostProspettoRequest.
 */
public class PostConferemaRiepilogoProspettoRequest implements BaseRequest {

	private final ConfermaRiepilogoProspetto conferemaRiepilogoProspettoRequest;

	/**
	 * Constructor
	 * 
	 * @param prospetto the prospetto
	 */
	public PostConferemaRiepilogoProspettoRequest(ConfermaRiepilogoProspetto conferemaRiepilogoProspettoRequest) {
		this.conferemaRiepilogoProspettoRequest = conferemaRiepilogoProspettoRequest;
	}

	public ConfermaRiepilogoProspetto getConferemaRiepilogoProspettoRequest() {
		return conferemaRiepilogoProspettoRequest;
	}


}
