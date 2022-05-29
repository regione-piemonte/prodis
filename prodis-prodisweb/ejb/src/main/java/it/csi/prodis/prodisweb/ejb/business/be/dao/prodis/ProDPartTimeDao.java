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

import java.math.BigDecimal;
import java.util.List;

import it.csi.prodis.prodisweb.ejb.business.be.dao.BaseAuditedEntityDao;
import it.csi.prodis.prodisweb.ejb.entity.ProDPartTime;

/**
 * Data Access Object interface for the entity ProDPartTime
 */
public interface ProDPartTimeDao extends BaseAuditedEntityDao<Long, ProDPartTime> {

	List<ProDPartTime> findByField(String field);
	
	List<ProDPartTime> findByIdProspettoProv(BigDecimal idProspettoProv);
}
