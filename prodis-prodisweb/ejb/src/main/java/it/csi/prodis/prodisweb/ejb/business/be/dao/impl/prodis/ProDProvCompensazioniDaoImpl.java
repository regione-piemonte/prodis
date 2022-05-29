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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.prodis.prodisweb.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDProvCompensazioniDao;
import it.csi.prodis.prodisweb.ejb.entity.ProDProvCompensazioni;
import it.csi.prodis.prodisweb.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity ProDProvCompensazioni
 */
@ApplicationScoped
public class ProDProvCompensazioniDaoImpl extends BaseAuditedEntityDaoImpl<Long, ProDProvCompensazioni> implements ProDProvCompensazioniDao {

	@Override
	public List<ProDProvCompensazioni> findByField(String field) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProDProvCompensazioni t ");
		jpql.append(" WHERE 1=1 ");

		JpaQueryHelper.andFieldEquals(jpql, params, "t.field", "field", field);

		jpql.append(" ORDER BY t.idCompensazione ");

		TypedQuery<ProDProvCompensazioni> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}
	@Override
	public List<ProDProvCompensazioni> findByIdProspettoProv(Long idProspettoProv) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProDProvCompensazioni t ");
		jpql.append(" WHERE 1=1 ");

		jpql.append(" and t.idProspettoProv = " + idProspettoProv);

		TypedQuery<ProDProvCompensazioni> query = composeTypedQuery(jpql, params);
		return query.getResultList();
		
	}
}
