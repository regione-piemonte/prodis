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
package it.csi.prodis.prodisweb.ejb.business.be.service.impl.compensazioni;


import it.csi.prodis.prodisweb.ejb.business.be.dad.CompensazioniDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.base.BaseService;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.base.BaseRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.base.BaseResponse;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;

/**
 * Base service implementation for the compensazioni
 * 
 * @param <Q> the request type
 * @param <R> the response type
 */
public abstract class BaseCompensazioniService<Q extends BaseRequest, R extends BaseResponse> extends BaseService<Q, R> {

	/** Data Access Delegate for Compensazioni  */
	protected final CompensazioniDad compensazioniDad;
	/**
	 * Constructor
	 * 
	 * @param configurationHelper the helper for the configuration
	 * @param compensazioniDad       the DAD for the compensazioni
	 */
	protected BaseCompensazioniService(ConfigurationHelper configurationHelper,  CompensazioniDad compensazioniDad) {
		super(configurationHelper);
		this.compensazioniDad = compensazioniDad;
	}

}
