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

import it.csi.prodis.prodisweb.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProTCategoriaAziendaDao;
import it.csi.prodis.prodisweb.ejb.entity.ProTCategoriaAzienda;
import it.csi.prodis.prodisweb.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity ProTCategoriaAzienda
 */
@ApplicationScoped
public class ProTCategoriaAziendaDaoImpl extends BaseEntityDaoImpl<Long, ProTCategoriaAzienda> implements ProTCategoriaAziendaDao {

	@Override
	public List<ProTCategoriaAzienda> findByField(String field) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProTCategoriaAzienda t ");
		jpql.append(" WHERE 1=1 ");

		JpaQueryHelper.andFieldEquals(jpql, params, "t.field", "field", field);

		jpql.append(" ORDER BY t.idTCategoriaAzienda ");

		TypedQuery<ProTCategoriaAzienda> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

}
