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
package it.csi.prodis.prodisweb.ejb.business.be.service.request.compensazioni;


import it.csi.prodis.prodisweb.ejb.business.be.service.request.base.BaseRequest;

public class ConfermaTornaRiepilogoRequest implements BaseRequest {

	private final Long idProspetto;

	/**
	 * Constructor
	 * @param id the id
	 */
	public ConfermaTornaRiepilogoRequest(Long idProspetto) {
		this.idProspetto = idProspetto;
	}

	/**
	 * @return the id
	 */
	public Long getIdProspetto() {
		return idProspetto;
	}
}
