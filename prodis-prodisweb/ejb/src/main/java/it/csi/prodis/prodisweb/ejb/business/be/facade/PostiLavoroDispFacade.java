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

import it.csi.prodis.prodisweb.ejb.business.be.dad.PostiLavoroDispDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.postilavorodisp.DeletePostiLavoroDispService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.postilavorodisp.PostPostiLavoroDispService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.postilavorodisp.PutPostiLavoroDispService;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.postilavorodisp.DeletePostiLavoroDispRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.postilavorodisp.PostPostiLavoroDispRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.postilavorodisp.PutPostiLavoroDispRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.postilavorodisp.DeletePostiLavoroDispResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.postilavorodisp.PostPostiLavoroDispResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.postilavorodisp.PutPostiLavoroDispResponse;
import it.csi.prodis.prodisweb.lib.dto.prospetto.PostiLavoroDisp;


/**
 * Facade for the /common path
 */
@Stateless
public class PostiLavoroDispFacade extends BaseFacade {

	@Inject
	private PostiLavoroDispDad postiLavoroDispDad;

	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PostPostiLavoroDispResponse postPostiLavoroDisp(PostiLavoroDisp postiLavoroDisp) {
		return executeService(new PostPostiLavoroDispRequest(postiLavoroDisp),
				new PostPostiLavoroDispService(configurationHelper, postiLavoroDispDad));
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public PutPostiLavoroDispResponse putPostiLavoroDisp(PostiLavoroDisp postiLavoroDisp) {
		return executeService(new PutPostiLavoroDispRequest(postiLavoroDisp),
				new PutPostiLavoroDispService(configurationHelper, postiLavoroDispDad));
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public DeletePostiLavoroDispResponse deletePostiLavoroDisp(Long idPostiLavoroDisp) {
		return executeService(new DeletePostiLavoroDispRequest(idPostiLavoroDisp),
				new DeletePostiLavoroDispService(configurationHelper, postiLavoroDispDad));
	}

}
