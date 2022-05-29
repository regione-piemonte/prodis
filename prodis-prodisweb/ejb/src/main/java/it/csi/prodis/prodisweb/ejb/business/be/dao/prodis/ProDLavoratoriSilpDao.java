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
package it.csi.prodis.prodisweb.ejb.business.be.dao.prodis;

import java.util.List;

import it.csi.prodis.prodisweb.ejb.business.be.dao.BaseEntityDao;
import it.csi.prodis.prodisweb.ejb.entity.ProDLavoratoriSilp;
import it.csi.prodis.prodisweb.ejb.entity.ProDLavoratoriSilpPK;

/**
 * Data Access Object interface for the entity ProDLavoratoriSilp
 */
public interface ProDLavoratoriSilpDao extends BaseEntityDao<ProDLavoratoriSilpPK, ProDLavoratoriSilp> {

	List<ProDLavoratoriSilp> findByField(String field);
}
