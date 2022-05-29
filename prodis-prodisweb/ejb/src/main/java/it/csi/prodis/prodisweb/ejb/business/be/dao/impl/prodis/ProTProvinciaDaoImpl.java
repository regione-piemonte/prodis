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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.csi.prodis.prodisweb.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProTProvinciaDao;
import it.csi.prodis.prodisweb.ejb.entity.ProTProvincia;
import it.csi.prodis.prodisweb.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity ProTProvincia
 */
@ApplicationScoped
public class ProTProvinciaDaoImpl extends BaseEntityDaoImpl<Long, ProTProvincia> implements ProTProvinciaDao {

	@Override
	public List<ProTProvincia> findByRegione(Long idRegione) {
		Map<String, Object> params = new HashMap<String, Object>();
		Date now = new Date();
		params.put("now", now);
		
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProTProvincia t ");
		jpql.append(" WHERE 1=1 ");

		JpaQueryHelper.andFieldEquals(jpql, params, "t.proTRegione.idTRegione", "idRegione", idRegione);
		
		jpql.append("    AND (t.dtInizio IS NULL OR (t.dtInizio IS NOT NULL and t.dtInizio <= :now ) ) ");
		jpql.append("    AND (t.dtFine   IS NULL OR (t.dtFine   IS NOT NULL and t.dtFine   >= :now ) ) ");

		jpql.append(" ORDER BY t.dsProTProvincia ");

		TypedQuery<ProTProvincia> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public Long findIdBySigla(String siglaProvincia) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		StringBuilder jpql = new StringBuilder();
		jpql.append(" select t.idTProvincia ");
		jpql.append(" FROM ProTProvincia t ");
		jpql.append(" WHERE 1=1 ");

		JpaQueryHelper.andFieldEquals(jpql, params, "t.dsTarga", "dsTarga", siglaProvincia);

		Query query = composeTypedQuery(jpql, params);
		
		return (Long) query.getResultList().get(0);
	}

}
