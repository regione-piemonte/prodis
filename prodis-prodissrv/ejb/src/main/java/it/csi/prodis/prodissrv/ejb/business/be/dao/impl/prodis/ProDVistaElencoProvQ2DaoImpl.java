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
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProDVistaElencoProvQ2Dao;
import it.csi.prodis.prodissrv.ejb.entity.ProDVistaElencoProvQ2;
import it.csi.prodis.prodissrv.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity ProDVistaElencoProvQ2
 */
@ApplicationScoped
public class ProDVistaElencoProvQ2DaoImpl extends BaseEntityDaoImpl<Long, ProDVistaElencoProvQ2> implements ProDVistaElencoProvQ2Dao {

	@Override
	public List<ProDVistaElencoProvQ2> findByField(String field) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProDVistaElencoProvQ2 t ");
		jpql.append(" WHERE 1=1 ");

		JpaQueryHelper.andFieldEquals(jpql, params, "t.field", "field", field);

		jpql.append(" ORDER BY t.idProspettoProv ");

		TypedQuery<ProDVistaElencoProvQ2> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public List<ProDVistaElencoProvQ2> findByIdProspetto(Long idProspetto) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProDVistaElencoProvQ2 t ");
		jpql.append(" WHERE 1=1 ");

		JpaQueryHelper.andFieldEquals(jpql, params, "t.idProspetto", "idProspetto", idProspetto);

		jpql.append(" ORDER BY t.idProspettoProv ");

		TypedQuery<ProDVistaElencoProvQ2> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

}
