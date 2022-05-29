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

import it.csi.prodis.prodisweb.ejb.business.be.dao.BaseAuditedEntityDao;
import it.csi.prodis.prodisweb.ejb.entity.ProDProspettoGradualita;

/**
 * Data Access Object interface for the entity ProDProspettoGradualita
 */
public interface ProDProspettoGradualitaDao extends BaseAuditedEntityDao<Long, ProDProspettoGradualita> {

	List<ProDProspettoGradualita> findByField(String field);
}
