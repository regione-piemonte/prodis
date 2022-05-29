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
package it.csi.prodis.prodisweb.ejb.business.be.service.request.riepilogonazionale;

import it.csi.prodis.prodisweb.ejb.business.be.service.request.base.BaseRequest;
import it.csi.prodis.prodisweb.lib.dto.prospetto.RiepilogoNazionale;

/**
 * The Class PostRiepilogoNazionaleRequest.
 */
public class PostRiepilogoNazionaleRequest implements BaseRequest {

	private final RiepilogoNazionale riepilogoNazionale;

	/**
	 * Constructor
	 * 
	 * @param CategoriaEscluse
	 */
	public PostRiepilogoNazionaleRequest(RiepilogoNazionale riepilogoNazionale) {
		this.riepilogoNazionale = riepilogoNazionale;
	}

	public RiepilogoNazionale getRiepilogoNazionale() {
		return riepilogoNazionale;
	}

}
