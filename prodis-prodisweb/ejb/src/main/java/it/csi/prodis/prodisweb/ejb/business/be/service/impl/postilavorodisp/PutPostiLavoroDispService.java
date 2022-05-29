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

import java.util.ArrayList;
import java.util.List;

import it.csi.prodis.prodisweb.ejb.business.be.dad.PostiLavoroDispDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.postilavorodisp.PutPostiLavoroDispRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.postilavorodisp.PutPostiLavoroDispResponse;
import it.csi.prodis.prodisweb.ejb.exception.NotFoundException;
import it.csi.prodis.prodisweb.ejb.util.ValidatorPostiLavoroDisponibili;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.dto.ApiError;
import it.csi.prodis.prodisweb.lib.dto.prospetto.PostiLavoroDisp;

public class PutPostiLavoroDispService extends BasePostiLavoroDispService<PutPostiLavoroDispRequest, PutPostiLavoroDispResponse> {
	/**
	 * Constructor
	 * 
	 * @param configurationHelper the helper for the configuration
	 * @param postiLavoroDispDad        the DAD for the postiLavoroDisp
	 */
	public PutPostiLavoroDispService(ConfigurationHelper configurationHelper, PostiLavoroDispDad postiLavoroDispDad) {
		super(configurationHelper, postiLavoroDispDad);
	}

	@Override
	protected void execute() {
		PostiLavoroDisp postiLavoroDisp = request.getPostiLavoroDisp();
		List<ApiError> apiErrors = new ArrayList<ApiError>();
		ValidatorPostiLavoroDisponibili valida = new ValidatorPostiLavoroDisponibili(postiLavoroDispDad);
		valida.valida(postiLavoroDisp, apiErrors);
		
		if (apiErrors == null || apiErrors.size() == 0) {
			if (postiLavoroDisp.getId()!=null) {
				postiLavoroDisp = postiLavoroDispDad.updatePostiLavoroDisp(postiLavoroDisp, postiLavoroDisp.getId().longValue());
			} else {
				throw new NotFoundException("PostiLavoroDisp");
			}
		}
		if (apiErrors != null && apiErrors.size() > 0) {
			response.setApiErrors(apiErrors);
		}
		response.setPostiLavoroDisp(postiLavoroDisp);
	}
}
