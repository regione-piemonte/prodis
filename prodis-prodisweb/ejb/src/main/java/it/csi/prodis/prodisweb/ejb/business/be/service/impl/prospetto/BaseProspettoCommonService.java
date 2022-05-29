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
package it.csi.prodis.prodisweb.ejb.business.be.service.impl.prospetto;

import it.csi.prodis.prodisweb.ejb.business.be.dad.CommonDad;
import it.csi.prodis.prodisweb.ejb.business.be.dad.ProspettoDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.base.BaseService;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.base.BaseRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.base.BaseResponse;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;

/**
 * Base service implementation for the prospetto
 * 
 * @param <Q> the request type
 * @param <R> the response type
 */
public abstract class BaseProspettoCommonService<Q extends BaseRequest, R extends BaseResponse> extends BaseService<Q, R> {

	/** Data Access Delegate for prospetto */
	protected final ProspettoDad prospettoDad;
	protected final CommonDad commonDad;

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the helper for the configuration
	 * @param prospettoDad        the DAD for the prospetto
	 */
	protected BaseProspettoCommonService(ConfigurationHelper configurationHelper, ProspettoDad prospettoDad,
			CommonDad commonDad) {
		super(configurationHelper);
		this.prospettoDad = prospettoDad;
		this.commonDad = commonDad;
	}

}
