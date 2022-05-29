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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang.StringEscapeUtils;

import it.csi.prodis.prodissrv.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProTAssunzioneProtettaDao;
import it.csi.prodis.prodissrv.ejb.entity.ProTAssunzioneProtetta;
import it.csi.prodis.prodissrv.ejb.util.jpa.JpaQueryHelper;
import it.csi.prodis.prodissrv.lib.dto.common.DecodificaGenerica;

/**
 * Data Access Object implementor for the entity ProTAssunzioneProtetta
 */
@ApplicationScoped
public class ProTAssunzioneProtettaDaoImpl extends BaseEntityDaoImpl<Long, ProTAssunzioneProtetta>
		implements ProTAssunzioneProtettaDao {

	@Override
	public List<ProTAssunzioneProtetta> findByField(String field) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProTAssunzioneProtetta t ");
		jpql.append(" WHERE 1=1 ");

		JpaQueryHelper.andFieldEquals(jpql, params, "t.field", "field", field);

		jpql.append(" ORDER BY t.idTAssunzioneProtetta ");

		TypedQuery<ProTAssunzioneProtetta> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public List<DecodificaGenerica> findByFilter(DecodificaGenerica filtro) {
		Map<String, Object> params = new HashMap<String, Object>();
		Date now = new Date();
		params.put("now", now);
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT ");
		sql.append(" DISTINCT ");
		sql.append("  ID_T_ASSUNZIONE_PROTETTA id_dec ");
		sql.append(" , COD_ASSUNZIONE_PROTETTA cod_dec  ");
		sql.append(" , DESC_ASSUNZIONE_PROTETTA des_dec ");

		sql.append(" FROM ");
		sql.append("  PRO_T_ASSUNZIONE_PROTETTA ");
		sql.append(" WHERE 1=1 ");
		if (filtro.getIdDecodifica() != null) {
			sql.append(" AND ID_T_ASSUNZIONE_PROTETTA = ");
			sql.append(filtro.getIdDecodifica().toString());
		} else {
			if (filtro.getCodDecodifica() != null) {
				sql.append(" AND upper(COD_ASSUNZIONE_PROTETTA) like '%");
				sql.append(filtro.getCodDecodifica().toUpperCase());
				sql.append("%'");
			}
			if (filtro.getDsDecodifica() != null) {
				sql.append(" AND upper(DESC_ASSUNZIONE_PROTETTA) like '%");
				sql.append(StringEscapeUtils.escapeSql(filtro.getDsDecodifica().toUpperCase()));
				sql.append("%'");
			}
		}
		sql.append(" AND (data_inizio IS NULL OR (data_inizio IS NOT NULL and data_inizio <= :now ) ) ");
		sql.append(" AND (data_fine   IS NULL OR (data_fine   IS NOT NULL and data_fine   >= :now ) ) ");

		sql.append(" ORDER BY des_dec ");

		Query query = composeNativeQuery(sql.toString(), params);
		List<Object[]> elenco = query.getResultList();
		List<DecodificaGenerica> decList = new ArrayList<DecodificaGenerica>();

		for (Object[] obj : elenco) {
			DecodificaGenerica dec = new DecodificaGenerica();
			if (obj[0] != null) {
				dec.setIdDecodifica(((BigDecimal) obj[0]).longValue());
			}
			if (obj[2] != null) {
				dec.setDsDecodifica((String) obj[2]);
			}
			if (obj[1] != null) {
				dec.setCodDecodifica((String) obj[1]);
			}
			decList.add(dec);
		}
		return decList;
	}
}
