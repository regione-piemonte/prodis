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
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

import it.csi.prodis.prodisweb.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDDatiProvincialiDao;
import it.csi.prodis.prodisweb.ejb.entity.ProDDatiProvinciali;
import it.csi.prodis.prodisweb.ejb.entity.RitornoPassaggioQ3;
import it.csi.prodis.prodisweb.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity ProDDatiProvinciali
 */
@ApplicationScoped
public class ProDDatiProvincialiDaoImpl extends BaseAuditedEntityDaoImpl<Long, ProDDatiProvinciali> implements ProDDatiProvincialiDao {

	@Override
	public List<ProDDatiProvinciali> findByField(String field) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProDDatiProvinciali t ");
		jpql.append(" WHERE 1=1 ");

		JpaQueryHelper.andFieldEquals(jpql, params, "t.field", "field", field);

		jpql.append(" ORDER BY t.idProspettoProv ");

		TypedQuery<ProDDatiProvinciali> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	//
	@Override
	public RitornoPassaggioQ3 passaggioQ3(Long idProspetto, String cfOperatore, Boolean soloScoperture) {
		
		RitornoPassaggioQ3 rit = new RitornoPassaggioQ3();
		
		rit.setFlagConferma(true);
		
		StoredProcedureQuery query = entityManager
				.createStoredProcedureQuery("PCK_PRODIS_2012.esegui_calcoli")
				.registerStoredProcedureParameter(
				    1,
				    Long.class,
				    ParameterMode.IN
				)
				.registerStoredProcedureParameter(
				    2,
				    String.class,
				    ParameterMode.IN
				)
				.registerStoredProcedureParameter(
					3,
					Boolean.class,
					ParameterMode.IN
				)
				.registerStoredProcedureParameter(
					4,
					Long.class,
					ParameterMode.OUT
				)
				.registerStoredProcedureParameter(
					5,
					String.class,
					ParameterMode.OUT
				)
				.setParameter(1, idProspetto)
				.setParameter(2, cfOperatore)
				.setParameter(3, soloScoperture);
				 
		query.execute();
				 
		Long esito = (Long) query.getOutputParameterValue(4);
		
		String messaggio = (String) query.getOutputParameterValue(5);
		
		rit.setEsito(esito);
		rit.setMessaggio(messaggio);
		
		return rit;
	}

}
