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

public class GeneraPdfRequest implements BaseRequest {

	private final Long idProspetto;
	private final String htmlContent;

	/**
	 * Constructor
	 * 
	 * @param id the id
	 */
	public GeneraPdfRequest(Long idProspetto, String htmlContent) {
		this.idProspetto = idProspetto;
		this.htmlContent = htmlContent;
	}

	/**
	 * @return the id
	 */
	public Long getIdProspetto() {
		return idProspetto;
	}

	/**
	 * @return the htmlContent
	 */
	public String getHtmlContent() {
		return htmlContent;
	}

}
