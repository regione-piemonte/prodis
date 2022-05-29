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
package it.csi.prodis.prodisweb.ejb.business.be.service.request.parttime;

import it.csi.prodis.prodisweb.ejb.business.be.service.request.base.BaseRequest;
import it.csi.prodis.prodisweb.lib.dto.prospetto.PartTime;

/**
 * The Class PostProspettoRequest.
 */
public class PostPartTimeRequest implements BaseRequest {

	private final PartTime partTime;
	private final Long idIntermittenti;

	/**
	 * Constructor
	 * 
	 * @param CategoriaEscluse
	 */
	public PostPartTimeRequest(PartTime partTime, Long idIntermittenti) {
		this.partTime = partTime;
		this.idIntermittenti = idIntermittenti;
	}

	public PartTime getPartTime() {
		return partTime;
	}
	
	public Long getIdIntermittenti() {
		return idIntermittenti;
	}

}
