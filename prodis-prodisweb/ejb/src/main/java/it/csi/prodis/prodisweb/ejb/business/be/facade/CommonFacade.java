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

import java.util.Date;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import it.csi.prodis.prodisweb.ejb.business.be.dad.CommonDad;
import it.csi.prodis.prodisweb.ejb.business.be.dad.DecodificaDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.common.GetDataCalcolataConGiorniLavorativiService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.common.GetParametroService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.common.GetRuoloService;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.common.InsertUserAccessLogService;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.common.GetDataCalcolataConGiorniLavorativiRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.common.GetParametroRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.common.GetRuoloRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.common.InsertUserAccessLogRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.common.GetDataCalcolataConGiorniLavorativiResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.common.GetParametroResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.common.GetRuoloResponse;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.common.InsertUserAccessLogResponse;
import it.csi.prodis.prodisweb.lib.dto.prospetto.UserAccessLog;

/**
 * Facade for the /common path
 */
@Stateless
public class CommonFacade extends BaseFacade {

	@Inject
	private CommonDad commonDad;
	@Inject
	private DecodificaDad decodificaDad;

	/**
	 * Gets the Ruolos
	 * 
	 * @return the ruolos
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public GetRuoloResponse getRuolo() {
		return executeService(new GetRuoloRequest(),
				new GetRuoloService(configurationHelper, commonDad, decodificaDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public GetParametroResponse getParametroByNome(String nomeParam) {
		return executeService(new GetParametroRequest(nomeParam),
				new GetParametroService(configurationHelper, commonDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Lock(LockType.WRITE)
	public InsertUserAccessLogResponse insertUserAccessLog(UserAccessLog loUserLog) {
		return executeService(new InsertUserAccessLogRequest(loUserLog),
				new InsertUserAccessLogService(configurationHelper, commonDad));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public GetDataCalcolataConGiorniLavorativiResponse getDataCalcolataConGiorniLavorativi(Date dataInput,
			Long numeroGiorniLavorativi) {
		return executeService(new GetDataCalcolataConGiorniLavorativiRequest(dataInput, numeroGiorniLavorativi),
				new GetDataCalcolataConGiorniLavorativiService(configurationHelper, commonDad));
	}

}
