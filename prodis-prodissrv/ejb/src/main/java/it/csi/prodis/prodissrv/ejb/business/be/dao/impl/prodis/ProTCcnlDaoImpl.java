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

import it.csi.prodis.prodissrv.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProTCcnlDao;
import it.csi.prodis.prodissrv.ejb.entity.ProTCcnl;
import it.csi.prodis.prodissrv.ejb.util.jpa.JpaQueryHelper;
import it.csi.prodis.prodissrv.lib.dto.common.DecodificaGenerica;

/**
 * Data Access Object implementor for the entity ProTCcnl
 */
@ApplicationScoped
public class ProTCcnlDaoImpl extends BaseEntityDaoImpl<Long, ProTCcnl> implements ProTCcnlDao {

	@Override
	public List<ProTCcnl> findByField(Long field) {
		Map<String, Object> params = new HashMap<String, Object>();
		Date now = new Date();
		params.put("now", now);

		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProTCcnl t ");
		jpql.append(" WHERE 1=1 ");

		JpaQueryHelper.andFieldEquals(jpql, params, "t.idTCcnl", "idTCcnl", field);

		jpql.append("    AND (t.dtInizio IS NULL OR (t.dtInizio IS NOT NULL and t.dtInizio <= :now ) ) ");
		jpql.append("    AND (t.dtFine   IS NULL OR (t.dtFine   IS NOT NULL and t.dtFine   >= :now ) ) ");

		jpql.append(" ORDER BY t.dsCcnl ");

		TypedQuery<ProTCcnl> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public List<DecodificaGenerica> findByFilter(DecodificaGenerica filtro) {
		Map<String, Object> params = new HashMap<String, Object>();

		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT ");
		sql.append(" DISTINCT ");
		sql.append("    ID_T_CCNL id_dec ");
		sql.append(" , COD_CCNL_MIN cod_dec  ");
		sql.append(" ,  DS_CCNL des_dec ");

		sql.append(" FROM ");
		sql.append("  PRO_T_CCNL ");
		sql.append(" WHERE 1=1 ");
		if (filtro.getIdDecodifica() != null) {
			sql.append(" AND ID_T_CCNL = ");
			sql.append(filtro.getIdDecodifica().toString());
		} else {
			if (filtro.getCodDecodifica() != null) {
				sql.append(" AND upper(COD_CCNL_MIN) like '%");
				sql.append(filtro.getCodDecodifica().toUpperCase());
				sql.append("%'");
			}
			if (filtro.getDsDecodifica() != null) {
				sql.append(" AND upper(DS_CCNL) like '%");
				sql.append(filtro.getDsDecodifica().toUpperCase());
				sql.append("%'");
			}
		}

		if (!filtro.isFlgAncheNonValidi()) {
			Date now = new Date();
			params.put("now", now);
			sql.append(" AND (dt_Inizio IS NULL OR (dt_Inizio IS NOT NULL and dt_Inizio <= :now ) ) ");
			sql.append(" AND (dt_Fine   IS NULL OR (dt_Fine   IS NOT NULL and dt_Fine   >= :now ) ) ");
		}

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
