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

public class GetCheckScopertureDatoriLavoriPubbliciRequest implements BaseRequest {

	private Long idProspetto;

	public GetCheckScopertureDatoriLavoriPubbliciRequest(Long idProspetto) {
		this.setIdProspetto(idProspetto);
	}

	public Long getIdProspetto() {
		return idProspetto;
	}

	public void setIdProspetto(Long idProspetto) {
		this.idProspetto = idProspetto;
	}

}
