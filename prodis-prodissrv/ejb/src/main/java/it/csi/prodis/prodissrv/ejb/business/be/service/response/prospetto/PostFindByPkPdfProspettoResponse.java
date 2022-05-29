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
import it.csi.prodis.prodissrv.lib.dto.prospetto.PdfProspetto;

/**
 * Response for reading Prospetto by its id.
 */
public class PostFindByPkPdfProspettoResponse extends BaseGetResponse<PdfProspetto> {

	private PdfProspetto prospetto;

	public PdfProspetto getProspetto() {
		return prospetto;
	}

	public void setProspetto(PdfProspetto prospetto) {
		this.prospetto = prospetto;
	}

	@Override
	protected PdfProspetto getEntity() {
		return prospetto;
	}

}
