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
package it.csi.prodis.prodissrv.ejb.business.be.dao.impl.prodis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.prodis.prodissrv.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProTImportErroriSpicomDao;
import it.csi.prodis.prodissrv.ejb.entity.ProTImportErroriSpicom;
import it.csi.prodis.prodissrv.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity ProTImportErroriSpicom
 */
@ApplicationScoped
public class ProTImportErroriSpicomDaoImpl extends BaseEntityDaoImpl<Long, ProTImportErroriSpicom> implements ProTImportErroriSpicomDao {

	@Override
	public List<ProTImportErroriSpicom> findByField(String field) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProTImportErroriSpicom t ");
		jpql.append(" WHERE 1=1 ");

		JpaQueryHelper.andFieldEquals(jpql, params, "t.field", "field", field);

		jpql.append(" ORDER BY t.codErrore ");

		TypedQuery<ProTImportErroriSpicom> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

}
