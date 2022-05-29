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
package it.csi.prodis.prodisweb.ejb.business.be.service.impl.common;

import java.util.Date;

import it.csi.prodis.prodisweb.ejb.business.be.dad.CommonDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.common.InsertUserAccessLogRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.common.InsertUserAccessLogResponse;
import it.csi.prodis.prodisweb.ejb.util.ProdisSrvUtil;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.dto.prospetto.UserAccessLog;

public class InsertUserAccessLogService
		extends BaseCommonService<InsertUserAccessLogRequest, InsertUserAccessLogResponse> {

	public InsertUserAccessLogService(ConfigurationHelper configurationHelper, CommonDad commonDad) {
		super(configurationHelper, commonDad);
	}

	@Override
	protected void execute() {
		UserAccessLog loUser = request.getUser();
		loUser.setDtEvento(new Date());
		String dsCognome = "";
		if (ProdisSrvUtil.isNotVoid(loUser.getDsCognome()) && loUser.getDsCognome().length() > 40) {
			dsCognome = loUser.getDsCognome().substring(0, 40);
		} else {
			dsCognome = loUser.getDsCognome();
		}
		loUser.setDsCognome(dsCognome);
		String dsNome = "";
		if (ProdisSrvUtil.isNotVoid(loUser.getDsCognome()) && loUser.getDsCognome().length() > 40) {
			dsNome = loUser.getDsNome().substring(0, 40);
		} else {
			dsNome = loUser.getDsNome();
		}
		loUser.setDsNome(dsNome);
		loUser = commonDad.insertUserAccessLog(loUser);
		response.setUser(loUser);
	}
}
