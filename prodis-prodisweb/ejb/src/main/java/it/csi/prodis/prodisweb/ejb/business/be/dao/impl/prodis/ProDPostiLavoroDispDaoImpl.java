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
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDPostiLavoroDispDao;
import it.csi.prodis.prodisweb.ejb.entity.ProDPostiLavoroDisp;

/**
 * Data Access Object implementor for the entity ProDPostiLavoroDisp
 */
@ApplicationScoped
public class ProDPostiLavoroDispDaoImpl extends BaseAuditedEntityDaoImpl<Long, ProDPostiLavoroDisp> implements ProDPostiLavoroDispDao {

	@Override
	public List<ProDPostiLavoroDisp> findByField(Long field) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProDPostiLavoroDisp t ");
		jpql.append(" WHERE 1=1 ");
		jpql.append(" AND t.idProspettoProv = "+field);
		jpql.append(" ORDER BY t.idPostoLavoroDisp ");

		TypedQuery<ProDPostiLavoroDisp> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

}
