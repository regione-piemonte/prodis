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
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProTStatoVerificaDao;
import it.csi.prodis.prodissrv.ejb.entity.ProTStatoVerifica;
import it.csi.prodis.prodissrv.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity ProTStatoVerifica
 */
@ApplicationScoped
public class ProTStatoVerificaDaoImpl extends BaseEntityDaoImpl<Long, ProTStatoVerifica> implements ProTStatoVerificaDao {

	@Override
	public List<ProTStatoVerifica> findByField(String field) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProTStatoVerifica t ");
		jpql.append(" WHERE 1=1 ");

		JpaQueryHelper.andFieldEquals(jpql, params, "t.field", "field", field);

		jpql.append(" ORDER BY t.idTStatoVerifica ");

		TypedQuery<ProTStatoVerifica> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

}
