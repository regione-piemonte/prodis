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
package it.csi.prodis.prodissrv.ejb.business.be.dao.prodis;

import java.util.List;

import it.csi.prodis.prodissrv.ejb.business.be.dao.BaseEntityDao;
import it.csi.prodis.prodissrv.ejb.entity.ProDProvEsonero;

/**
 * Data Access Object interface for the entity ProDProvEsonero
 */
public interface ProDProvEsoneroDao extends BaseEntityDao<Long, ProDProvEsonero> {

	List<ProDProvEsonero> findByField(String field);
}
