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
package it.csi.prodis.prodisweb.ejb.business.be.dad;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDParametriDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDUserAccessLogDao;
import it.csi.prodis.prodisweb.ejb.entity.ProDUserAccessLog;
import it.csi.prodis.prodisweb.ejb.mapper.ProdisMappers;
import it.csi.prodis.prodisweb.lib.dto.common.DecodificaGenerica;
import it.csi.prodis.prodisweb.lib.dto.prospetto.UserAccessLog;

@ApplicationScoped
public class CommonDad extends BaseDad {

	@Inject
	private ProDParametriDao proDParametriDao;

	@Inject
	private ProDUserAccessLogDao proDUserAccessLogDao;

	public DecodificaGenerica getParametroByNome(String nomeParam) {
		DecodificaGenerica ilParametro = proDParametriDao.findByNome(nomeParam);
		return ilParametro;
	}

	public UserAccessLog insertUserAccessLog(UserAccessLog loUser) {
		ProDUserAccessLog proDloUser = ProdisMappers.USER_ACCESS_LOG.toEntity(loUser);
		proDloUser = proDUserAccessLogDao.insert(proDloUser);
		loUser.setId(proDloUser.getId() != null ? proDloUser.getId().intValue() : null);
		return loUser;
	}

}
