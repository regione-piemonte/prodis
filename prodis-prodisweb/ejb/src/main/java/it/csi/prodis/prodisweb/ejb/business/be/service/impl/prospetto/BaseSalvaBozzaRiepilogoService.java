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

import it.csi.prodis.prodisweb.ejb.business.be.dad.ProspettoDad;
import it.csi.prodis.prodisweb.ejb.business.be.dad.RiepilogoNazionaleDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.base.BaseService;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.base.BaseRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.base.BaseResponse;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;

public abstract class BaseSalvaBozzaRiepilogoService <Q extends BaseRequest, R extends BaseResponse> extends BaseService<Q, R> {

	/** Data Access Delegate for prospetto */
	protected final ProspettoDad prospettoDad;
	protected final RiepilogoNazionaleDad riepilogoNazionaleDad;

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the helper for the configuration
	 * @param prospettoDad        the DAD for the prospetto
	 */
	protected BaseSalvaBozzaRiepilogoService(ConfigurationHelper configurationHelper, ProspettoDad prospettoDad, RiepilogoNazionaleDad riepilogoNazionaleDad) {
		super(configurationHelper);
		this.prospettoDad = prospettoDad;
		this.riepilogoNazionaleDad = riepilogoNazionaleDad;
	}

}
