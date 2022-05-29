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
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDProvConvenzioneDao;
import it.csi.prodis.prodisweb.ejb.entity.ProDProvConvenzione;
import it.csi.prodis.prodisweb.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity ProDProvConvenzione
 */
@ApplicationScoped
public class ProDProvConvenzioneDaoImpl extends BaseEntityDaoImpl<Long, ProDProvConvenzione> implements ProDProvConvenzioneDao {

	@Override
	public List<ProDProvConvenzione> findByField(String field) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProDProvConvenzione t ");
		jpql.append(" WHERE 1=1 ");

		JpaQueryHelper.andFieldEquals(jpql, params, "t.field", "field", field);

		jpql.append(" ORDER BY t.idProspettoProv ");

		TypedQuery<ProDProvConvenzione> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

}
