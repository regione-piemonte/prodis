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
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProDCategorieEscluseDao;
import it.csi.prodis.prodissrv.ejb.entity.ProDCategorieEscluse;
import it.csi.prodis.prodissrv.ejb.util.jpa.JpaQueryHelper;
import it.csi.prodis.prodissrv.ejb.util.jpa.Page;

/**
 * Data Access Object implementor for the entity ProDCategorieEscluse
 */
@ApplicationScoped
public class ProDCategorieEscluseDaoImpl extends BaseAuditedEntityDaoImpl<Long, ProDCategorieEscluse> implements ProDCategorieEscluseDao {

	@Override
	public List<ProDCategorieEscluse> findByField(String field) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProDCategorieEscluse t ");
		jpql.append(" WHERE 1=1 ");

		JpaQueryHelper.andFieldEquals(jpql, params, "t.field", "field", field);

		//jpql.append(" ORDER BY t.idCategorieEscluse ");

		TypedQuery<ProDCategorieEscluse> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public Page<ProDCategorieEscluse> findPaginatedByIdProspettoProv(int page, int size, String sortField,
			String sortDirection, Long idProspettoProv) {
		Map<String, Object> params = new HashMap<>();

		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProDCategorieEscluse t ");
		jpql.append(" WHERE 1=1 ");

		composeQueryRicerca(idProspettoProv, params, jpql);

		if (sortField != null && sortDirection != null) {
			//jpql.append(" ORDER BY ").append(sortField).append(" ").append(sortDirection); sortField errato devo ancora scoprire perchè
		}

		if (sortField == null) {
			jpql.append(" ORDER BY t.dAggiorn DESC ");
		}

		return getPagedResult(jpql, params, page, size);
	}
	
	private void composeQueryRicerca(Long idProspettoProv, Map<String, Object> params,
			StringBuilder jpql) {
		if (idProspettoProv != null) {
				jpql.append(" AND t.proDDatiProvinciali.id = :idProspettoProv ");
				params.put("idProspettoProv", idProspettoProv);
			}

		}

	@Override
	public List<ProDCategorieEscluse> findByIdProspettoProv(Long idProspettoProv) {
		Map<String, Object> params = new HashMap<>();

		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProDCategorieEscluse t ");
		jpql.append(" WHERE 1=1 ");

		 
		composeQueryRicerca(idProspettoProv, params, jpql);
		
			jpql.append(" ORDER BY t.dAggiorn DESC ");
	
			TypedQuery<ProDCategorieEscluse> query = composeTypedQuery(jpql, params);
			return query.getResultList();
	}
}
