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
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProDRiepilogoProvincialeDao;
import it.csi.prodis.prodissrv.ejb.entity.ProDRiepilogoProvinciale;

/**
 * Data Access Object implementor for the entity ProDRiepilogoProvinciale
 */
@ApplicationScoped
public class ProDRiepilogoProvincialeDaoImpl extends BaseEntityDaoImpl<Long, ProDRiepilogoProvinciale>
		implements ProDRiepilogoProvincialeDao {

	@Override
	public List<ProDRiepilogoProvinciale> findByIdProspetto(Long idProspetto) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProDRiepilogoProvinciale t ");
		jpql.append(" WHERE 1=1 ");

		// JpaQueryHelper.andFieldEquals(jpql, params, "t.field", "field", field);

		jpql.append(" AND EXISTS ( ");
		jpql.append("   FROM ProRProspettoProvincia pp ");
		jpql.append("   WHERE pp.idProspettoProv = t.idProspettoProv  ");
		jpql.append("   AND pp.proDProspetto.idProspetto = :idProspetto ");
		jpql.append(" ) ");

		params.put("idProspetto", idProspetto);

		jpql.append(" ORDER BY t.idProspettoProv ");

		TypedQuery<ProDRiepilogoProvinciale> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

}
