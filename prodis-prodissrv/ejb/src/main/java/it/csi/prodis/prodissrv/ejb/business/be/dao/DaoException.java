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
//classe generata automaticamente da generator.strategy.DaoExceptionStrategy
package it.csi.prodis.prodissrv.ejb.business.be.dao;

public class DaoException extends RuntimeException {

	public DaoException(String message, Exception e) {
		e.printStackTrace();
	}

}
