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

import it.csi.prodis.prodisweb.ejb.business.be.service.impl.parttime.DeleteIntermittentiService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.parttime.PostIntermittentiService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.parttime.PutIntermittentiService;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.parttime.DeleteIntermittentiRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.parttime.PostIntermittentiRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.parttime.PutIntermittentiRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.parttime.DeleteIntermittentiResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.parttime.PostIntermittentiResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.parttime.PutIntermittentiResponse;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProvIntermittenti;
import it.csi.prodis.prodisweb.ejb.business.be.dad.IntermittentiDad;


/**
 * Facade for the /common path
 */
@Stateless
public class IntermittentiFacade extends BaseFacade {

	@Inject
	private IntermittentiDad intermittentiDad;

	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PostIntermittentiResponse postIntermittenti(ProvIntermittenti intermittenti, Long idPartTime) {
		return executeService(new PostIntermittentiRequest(intermittenti, idPartTime),
				new PostIntermittentiService(configurationHelper, intermittentiDad));
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PutIntermittentiResponse putIntermittenti(ProvIntermittenti intermittenti) {
		return executeService(new PutIntermittentiRequest(intermittenti),
				new PutIntermittentiService(configurationHelper, intermittentiDad));
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public DeleteIntermittentiResponse deleteIntermittenti(Long idIntermittenti) {
		return executeService(new DeleteIntermittentiRequest(idIntermittenti),
				new DeleteIntermittentiService(configurationHelper, intermittentiDad));
	}

}
