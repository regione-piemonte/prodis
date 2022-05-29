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

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.csi.prodis.prodissrv.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProDImportErroriDao;
import it.csi.prodis.prodissrv.ejb.entity.ProDImportErrori;
import it.csi.prodis.prodissrv.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity ProDImportErrori
 */
@ApplicationScoped
public class ProDImportErroriDaoImpl extends BaseEntityDaoImpl<Long, ProDImportErrori> implements ProDImportErroriDao {

	@Override
	public List<ProDImportErrori> findByField(String field) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProDImportErrori t ");
		jpql.append(" WHERE 1=1 ");

		JpaQueryHelper.andFieldEquals(jpql, params, "t.field", "field", field);

		jpql.append(" ORDER BY t.idErrore ");

		TypedQuery<ProDImportErrori> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}
	
	
	@Override
	public void deleteErroriComunicazione(Long idSpiTrasmissione) {
		StringBuilder sb = new StringBuilder()
				.append("DELETE FROM ")
				.append(clazz.getName())
				.append(" WHERE ")
				.append("idSpiTrasmissione")
				.append(" = :id");
		Map<String, Object> params = new HashMap<>();
		params.put("id", new BigDecimal(idSpiTrasmissione));
		Query query = composeQuery(sb, params);
		query.executeUpdate();
	}
	
	

}
