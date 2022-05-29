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
package it.csi.prodis.prodisweb.ejb.business.be.dao.impl.prodis;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.prodis.prodisweb.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDPartTimeDao;
import it.csi.prodis.prodisweb.ejb.entity.ProDPartTime;
import it.csi.prodis.prodisweb.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity ProDPartTime
 */
@ApplicationScoped
public class ProDPartTimeDaoImpl extends BaseAuditedEntityDaoImpl<Long, ProDPartTime> implements ProDPartTimeDao {

	@Override
	public List<ProDPartTime> findByField(String field) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProDPartTime t ");
		jpql.append(" WHERE 1=1 ");

		JpaQueryHelper.andFieldEquals(jpql, params, "t.field", "field", field);

		jpql.append(" ORDER BY t.idPartTime ");

		TypedQuery<ProDPartTime> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}
	private void composeQueryRicerca(BigDecimal idProspettoProv, Map<String, Object> params,
			StringBuilder jpql) {
		if (idProspettoProv != null) {
				jpql.append(" AND t.idProspettoProv = :idProspettoProv ");
				params.put("idProspettoProv", idProspettoProv);
			}

		}

	@Override
	public List<ProDPartTime> findByIdProspettoProv(BigDecimal idProspettoProv) {
		Map<String, Object> params = new HashMap<>();

		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProDPartTime t ");
		jpql.append(" WHERE 1=1 ");

		 
		composeQueryRicerca(idProspettoProv, params, jpql);
		
			jpql.append(" ORDER BY t.dAggiorn DESC ");
	
			TypedQuery<ProDPartTime> query = composeTypedQuery(jpql, params);
			return query.getResultList();
	}
}
