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
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.csi.prodis.prodissrv.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProDParametriDao;
import it.csi.prodis.prodissrv.ejb.entity.ProDParametri;
import it.csi.prodis.prodissrv.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity ProDParametri
 */
@ApplicationScoped
public class ProDParametriDaoImpl extends BaseEntityDaoImpl<String, ProDParametri> implements ProDParametriDao {

	@Override
	public List<ProDParametri> findByField(String field) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProDParametri t ");
		jpql.append(" WHERE 1=1 ");

		JpaQueryHelper.andFieldEquals(jpql, params, "t.dsNome", "dsNome", field);

		jpql.append(" ORDER BY 1 ");

		TypedQuery<ProDParametri> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public String findByNome(String field) {

		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder();

		sql.append(" select DS_VALORE from PRO_D_PARAMETRI WHERE DS_NOME  = '");
		sql.append(field);
		sql.append("' ");

		try {
			Query query = composeNativeQuery(sql.toString(), params);
			return query.getSingleResult().toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
