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

import it.csi.prodis.prodissrv.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProDDatiProvincialiDao;
import it.csi.prodis.prodissrv.ejb.entity.ProDDatiProvinciali;
import it.csi.prodis.prodissrv.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity ProDDatiProvinciali
 */
@ApplicationScoped
public class ProDDatiProvincialiDaoImpl extends BaseAuditedEntityDaoImpl<Long, ProDDatiProvinciali> implements ProDDatiProvincialiDao {

	@Override
	public List<ProDDatiProvinciali> findByField(String field) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProDDatiProvinciali t ");
		jpql.append(" WHERE 1=1 ");

		JpaQueryHelper.andFieldEquals(jpql, params, "t.field", "field", field);

		jpql.append(" ORDER BY t.idProspettoProv ");

		TypedQuery<ProDDatiProvinciali> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

}
