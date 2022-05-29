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
import it.csi.prodis.prodissrv.ejb.entity.ProTIstat2001livello5;
import it.csi.prodis.prodissrv.lib.dto.common.DecodificaGenerica;

/**
 * Data Access Object interface for the entity ProTIstat2001livello5
 */
public interface ProTIstat2001livello5Dao extends BaseEntityDao<Long, ProTIstat2001livello5> {

	List<ProTIstat2001livello5> findByField(String field);

	List<DecodificaGenerica> findByFilter(DecodificaGenerica filtro);
}
