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


import it.csi.prodis.prodisweb.ejb.business.be.dad.RiepilogoNazionaleDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.riepilogonazionale.PostRiepilogoNazionaleService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.riepilogonazionale.PutRiepilogoNazionaleService;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.riepilogonazionale.PostRiepilogoNazionaleRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.riepilogonazionale.PutRiepilogoNazionaleRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.riepilogonazionale.PostRiepilogoNazionaleResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.riepilogonazionale.PutRiepilogoNazionaleResponse;
import it.csi.prodis.prodisweb.lib.dto.prospetto.RiepilogoNazionale;

@Stateless
public class RiepilogoNazionaleFacade extends BaseFacade {

	@Inject
	private RiepilogoNazionaleDad riepilogoNazionaleDad;

	/**
	 * Posts an riepilogoNazionale
	 * 
	 * @param riepilogoNazionale
	 * @return the riepilogoNazionale
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PostRiepilogoNazionaleResponse postRiepilogoNazionale(RiepilogoNazionale riepilogoNazionale) {
		return executeService(new PostRiepilogoNazionaleRequest(riepilogoNazionale),
				new PostRiepilogoNazionaleService(configurationHelper, riepilogoNazionaleDad));
	}
	
	/**
	 * Put an RiepilogoNazionale
	 * 
	 * @param riepilogoNazionale
	 * @return the riepilogoNazionale
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PutRiepilogoNazionaleResponse putRiepilogoNazionale(RiepilogoNazionale riepilogoNazionale) {
		return executeService(new PutRiepilogoNazionaleRequest(riepilogoNazionale),
				new PutRiepilogoNazionaleService(configurationHelper, riepilogoNazionaleDad));
	}


}
