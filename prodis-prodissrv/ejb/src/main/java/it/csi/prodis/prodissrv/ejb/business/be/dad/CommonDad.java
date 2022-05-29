/*-
 * ========================LICENSE_START=================================
 * PRODIS Servizi - EJB submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodissrv.ejb.business.be.dad;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProDParametriDao;

@ApplicationScoped
public class CommonDad extends BaseDad {

	@Inject
	private ProDParametriDao proDParametriDao;

	public String getParametroByNome(String nomeParam) {
		String ilParametro = proDParametriDao.findByNome(nomeParam);
		return ilParametro;
	}

}
