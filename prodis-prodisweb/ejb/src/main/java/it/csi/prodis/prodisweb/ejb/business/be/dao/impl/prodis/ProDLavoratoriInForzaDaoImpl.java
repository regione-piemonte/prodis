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

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.csi.prodis.prodisweb.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDLavoratoriInForzaDao;
import it.csi.prodis.prodisweb.ejb.entity.ProDLavoratoriInForza;
import it.csi.prodis.prodisweb.ejb.util.ProdisSrvUtil;
import it.csi.prodis.prodisweb.ejb.util.jpa.JpaQueryHelper;

/**
 * Data Access Object implementor for the entity ProDLavoratoriInForza
 */
@ApplicationScoped
public class ProDLavoratoriInForzaDaoImpl extends BaseAuditedEntityDaoImpl<Long, ProDLavoratoriInForza>
		implements ProDLavoratoriInForzaDao {

	@Override
	public List<ProDLavoratoriInForza> findByField(String field) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProDLavoratoriInForza t ");
		jpql.append(" WHERE 1=1 ");

		JpaQueryHelper.andFieldEquals(jpql, params, "t.field", "field", field);

		jpql.append(" ORDER BY t.idLavoratoriInForza ");

		TypedQuery<ProDLavoratoriInForza> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	private void composeQueryRicerca(BigDecimal idProspettoProv, Map<String, Object> params, StringBuilder jpql) {
		if (idProspettoProv != null) {
			jpql.append(" AND t.idProspettoProv = :idProspettoProv ");
			params.put("idProspettoProv", idProspettoProv);
		}

	}

	@Override
	public List<ProDLavoratoriInForza> findByIdProspettoProv(BigDecimal idProspettoProv) {
		Map<String, Object> params = new HashMap<>();

		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProDLavoratoriInForza t ");
		jpql.append(" WHERE 1=1 ");

		composeQueryRicerca(idProspettoProv, params, jpql);

		jpql.append(" ORDER BY t.codiceFiscale ASC ");

		TypedQuery<ProDLavoratoriInForza> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	// raffaella controlli lavoratore
	@Override
	public String checkCongruenzaCodiceFiscale(String cf, String cognome, String nome, String sesso, String dataNascita,
			String codiceComuneStatoEsteroNascita) {

		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder();
		if (ProdisSrvUtil.isVoid(cognome)) {
			cognome = "NULL";
		}
		if (ProdisSrvUtil.isVoid(nome)) {
			nome = "NULL";
		}
		if (ProdisSrvUtil.isVoid(sesso)) {
			sesso = "NULL";
		}
		if (ProdisSrvUtil.isVoid(dataNascita)) {
			dataNascita = "NULL";
		}
		if (ProdisSrvUtil.isVoid(codiceComuneStatoEsteroNascita)) {
			codiceComuneStatoEsteroNascita = "NULL";
		}

		sql.append(" select PCK_PRODIS_2012_UTILS.controlla_codice_fiscale('");
		sql.append(cf);
		sql.append("',");
		sql.append(!cognome.equalsIgnoreCase("NULL") ? "'" : "");
		sql.append(cognome);
		sql.append(!cognome.equalsIgnoreCase("NULL") ? "'" : "");
		sql.append(",");
		sql.append(!nome.equalsIgnoreCase("NULL") ? "'" : "");
		sql.append(nome);
		sql.append(!nome.equalsIgnoreCase("NULL") ? "'" : "");
		sql.append(",");
		sql.append(!sesso.equalsIgnoreCase("NULL") ? "'" : "");
		sql.append(sesso);
		sql.append(!sesso.equalsIgnoreCase("NULL") ? "'" : "");
		sql.append(",");
		sql.append(!dataNascita.equalsIgnoreCase("NULL") ? "'" : "");
		sql.append(dataNascita);
		sql.append(!sesso.equalsIgnoreCase("NULL") ? "'" : "");
		sql.append(",");
		sql.append(!codiceComuneStatoEsteroNascita.equalsIgnoreCase("NULL") ? "'" : "");
		sql.append(codiceComuneStatoEsteroNascita);
		sql.append(!codiceComuneStatoEsteroNascita.equalsIgnoreCase("NULL") ? "'" : "");
		sql.append(" ) FROM DUAL ");
		System.out.println(sql);

		Query query = composeNativeQuery(sql.toString(), params);
		long resultCheck = ((Number) query.getSingleResult()).longValue();

		return Long.toString(resultCheck);
	}

	@Override
	public Long contaLavoratoriProspettoProv(Long idProspettoProv) {

		Map<String, Object> params = new HashMap<>();

		StringBuilder jpql = new StringBuilder();
		jpql.append(" select COUNT(t.idLavoratoriInForza) ");
		jpql.append(" FROM ProDLavoratoriInForza t ");
		jpql.append(" WHERE 1=1 ");

		JpaQueryHelper.andFieldEquals(jpql, params, "t.idProspettoProv", "idProspettoProv",
				new BigDecimal(idProspettoProv));

		Query query = composeQuery(jpql, params);

		return (Long) query.getSingleResult();

	}
}
