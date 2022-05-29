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
package it.csi.prodis.prodisweb.ejb.business.be.facade;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import it.csi.prodis.prodisweb.ejb.business.be.dad.RiepilogoProvincialeDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.riepilogoprovinciale.PostRiepilogoProvincialeService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.riepilogoprovinciale.PutRiepilogoProvincialeService;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.riepilogoprovinciale.PostRiepilogoProvincialeRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.riepilogoprovinciale.PutRiepilogoProvincialeRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.riepilogoprovinciale.PostRiepilogoProvincialeResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.riepilogoprovinciale.PutRiepilogoProvincialeResponse;
import it.csi.prodis.prodisweb.lib.dto.prospetto.RiepilogoProvinciale;

@Stateless
public class RiepilogoProvincialeFacade extends BaseFacade {

	@Inject
	private RiepilogoProvincialeDad riepilogoProvincialeDad;

	/**
	 * Posts an riepilogoProvinciale
	 * 
	 * @param riepilogoProvinciale
	 * @return the riepilogoProvinciale
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PostRiepilogoProvincialeResponse postRiepilogoProvinciale(RiepilogoProvinciale riepilogoProvinciale) {
		return executeService(new PostRiepilogoProvincialeRequest(riepilogoProvinciale),
				new PostRiepilogoProvincialeService(configurationHelper, riepilogoProvincialeDad));
	}

	/**
	 * Put an RiepilogoProvinciale
	 * 
	 * @param riepilogoProvinciale
	 * @return the riepilogoProvinciale
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PutRiepilogoProvincialeResponse putRiepilogoProvinciale(RiepilogoProvinciale riepilogoProvinciale) {
		return executeService(new PutRiepilogoProvincialeRequest(riepilogoProvinciale),
				new PutRiepilogoProvincialeService(configurationHelper, riepilogoProvincialeDad));
	}

}
