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
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProDProvIntermittentiDao;
import it.csi.prodis.prodissrv.ejb.entity.ProDProvIntermittenti;
import it.csi.prodis.prodissrv.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity ProDProvIntermittenti
 */
@ApplicationScoped
public class ProDProvIntermittentiDaoImpl extends BaseAuditedEntityDaoImpl<Long, ProDProvIntermittenti> implements ProDProvIntermittentiDao {

	@Override
	public List<ProDProvIntermittenti> findByField(String field) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProDProvIntermittenti t ");
		jpql.append(" WHERE 1=1 ");

		JpaQueryHelper.andFieldEquals(jpql, params, "t.field", "field", field);

		jpql.append(" ORDER BY t.idIntermittenti ");

		TypedQuery<ProDProvIntermittenti> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}
	
	private void composeQueryRicerca(Long idProspettoProv, Map<String, Object> params,
			StringBuilder jpql) {
		if (idProspettoProv != null) {
				jpql.append(" AND t.proDDatiProvinciali.id = :idProspettoProv ");
				params.put("idProspettoProv", idProspettoProv);
			}

		}

	@Override
	public List<ProDProvIntermittenti> findByIdProspettoProv(Long idProspettoProv) {
		Map<String, Object> params = new HashMap<>();

		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProDProvIntermittenti t ");
		jpql.append(" WHERE 1=1 ");

		 
		composeQueryRicerca(idProspettoProv, params, jpql);
		
			jpql.append(" ORDER BY t.dAggiorn DESC ");
	
			TypedQuery<ProDProvIntermittenti> query = composeTypedQuery(jpql, params);
			return query.getResultList();
	}

}
