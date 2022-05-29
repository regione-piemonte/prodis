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

import it.csi.prodis.prodisweb.ejb.business.be.dad.PartTimeDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.parttime.DeletePartTimeService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.parttime.PostPartTimeService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.parttime.PutPartTimeService;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.parttime.DeletePartTimeRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.parttime.PostPartTimeRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.parttime.PutPartTimeRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.parttime.DeletePartTimeResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.parttime.PostPartTimeResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.parttime.PutPartTimeResponse;
import it.csi.prodis.prodisweb.lib.dto.prospetto.PartTime;


/**
 * Facade for the /common path
 */
@Stateless
public class PartTimeFacade extends BaseFacade {

	@Inject
	private PartTimeDad partTimeDad;

	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PostPartTimeResponse postPartTime(PartTime partTime, Long idIntermittenti) {
		return executeService(new PostPartTimeRequest(partTime, idIntermittenti),
				new PostPartTimeService(configurationHelper, partTimeDad));
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PutPartTimeResponse putPartTime(PartTime partTime) {
		return executeService(new PutPartTimeRequest(partTime),
				new PutPartTimeService(configurationHelper, partTimeDad));
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public DeletePartTimeResponse deletePartTime(Long idPartTime) {
		return executeService(new DeletePartTimeRequest(idPartTime),
				new DeletePartTimeService(configurationHelper, partTimeDad));
	}

}
