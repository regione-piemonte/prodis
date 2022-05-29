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
package it.csi.prodis.prodisweb.ejb.business.be.service.impl.postilavorodisp;

import it.csi.prodis.prodisweb.ejb.business.be.dad.PostiLavoroDispDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.postilavorodisp.DeletePostiLavoroDispRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.postilavorodisp.DeletePostiLavoroDispResponse;
import it.csi.prodis.prodisweb.ejb.entity.ProDPostiLavoroDisp;
import it.csi.prodis.prodisweb.ejb.exception.NotFoundException;
import it.csi.prodis.prodisweb.ejb.mapper.ProdisMappers;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;

public class DeletePostiLavoroDispService extends BasePostiLavoroDispService<DeletePostiLavoroDispRequest, DeletePostiLavoroDispResponse> {
	/**
	 * Constructor
	 * 
	 * @param configurationHelper the helper for the configuration
	 * @param postiLavoroDispDad        the DAD for the postiLavoroDisp
	 */
	public DeletePostiLavoroDispService(ConfigurationHelper configurationHelper, PostiLavoroDispDad postiLavoroDispDad) {
		super(configurationHelper, postiLavoroDispDad);
	}

	@Override
	protected void execute() {
		
		Long idPostiLavoroDisp = request.getIdPostiLavoroDisp();
		
		ProDPostiLavoroDisp postiLavoroDispDeleted = null;
		if (idPostiLavoroDisp!=null) {
			postiLavoroDispDeleted = postiLavoroDispDad.deletePostiLavoroDisp(idPostiLavoroDisp);
		} else {
			throw new NotFoundException("PostiLavoroDisp");
		}

		response.setPostiLavoroDisp(ProdisMappers.POSTI_LAVORO_DISP.toModel(postiLavoroDispDeleted));
	}
}
