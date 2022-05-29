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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.csi.prodis.prodisweb.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProTStatiEsteriDao;
import it.csi.prodis.prodisweb.ejb.entity.ProTStatiEsteri;
import it.csi.prodis.prodisweb.ejb.util.jpa.JpaQueryHelper;
import it.csi.prodis.prodisweb.lib.dto.common.DecodificaGenerica;

/**
 * Data Access Object implementor for the entity ProTStatiEsteri
 */
@ApplicationScoped
public class ProTStatiEsteriDaoImpl extends BaseEntityDaoImpl<Long, ProTStatiEsteri> implements ProTStatiEsteriDao {

	@Override
	public List<ProTStatiEsteri> findByField(String field) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProTStatiEsteri t ");
		jpql.append(" WHERE 1=1 ");

		JpaQueryHelper.andFieldEquals(jpql, params, "t.field", "field", field);

		jpql.append(" ORDER BY t.idTStatiEsteri ");

		TypedQuery<ProTStatiEsteri> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DecodificaGenerica> findByFilter(DecodificaGenerica filtro) {
		Map<String, Object> params = new HashMap<String, Object>();

		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT  DISTINCT  ID_T_STATI_ESTERI id_dec, COD_NAZIONE_MIN cod_dec, DS_STATI_ESTERI des_dec, SIGLA_NAZIONE sigla_naz ");
        sql.append(" FROM  PRO_T_STATI_ESTERI  WHERE 1=1 ");
		if (filtro.getIdDecodifica() != null) {
	    	 params.put("idDecod", filtro.getIdDecodifica().toString());
	    	 sql.append(" AND ID_T_STATI_ESTERI = :idDecod");
		} else {
			if (filtro.getCodDecodifica() != null) {
	    		params.put("codDecod",  "%" + filtro.getCodDecodifica().toUpperCase() + "%");
	    		sql.append(" AND upper(COD_NAZIONE_MIN) like :codDecod");
			}
			if (filtro.getDsDecodifica() != null) {
	    		params.put("dsDecod", "%" + filtro.getDsDecodifica().toUpperCase() + "%");
	    		sql.append(" AND upper(DS_STATI_ESTERI) like :dsDecod");
			}
			if (filtro.getFlgStatiUe() != null) {
				params.put("flgUE", filtro.getFlgStatiUe());
		    	sql.append(" AND FLG_UE = :flgUE");
			}
		}
		if (filtro.getDataValidita() != null) {
			Date dataV = filtro.getDataValidita();
			params.put("now", dataV);
			sql.append(" AND (dt_Inizio IS NULL OR (dt_Inizio IS NOT NULL and dt_Inizio <= :now ) ) ");
			sql.append(" AND (dt_Fine   IS NULL OR (dt_Fine   IS NOT NULL and dt_Fine   >= :now ) ) ");
		} else if (!filtro.isFlgAncheNonValidi()) {
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
			if (obj[1] != null) {
				dec.setCodDecodifica((String) obj[1]);
			}
			if (obj[2] != null) {
				dec.setDsDecodifica((String) obj[2]);
			}
			if (obj[3] != null) {
				dec.setSiglaNazione((String) obj[3]);
			}
			decList.add(dec);
		}
		return decList;
	}

	@Override
	public List<ProTStatiEsteri> find(String codNazioneMin, String dsStatiEsteri, Date data) {
		Map<String, Object> params = new HashMap<String, Object>();
		Date now = new Date();
		// elenco nazione valida alla data di nascita
		if (data != null) {
			now = data;
		}
		params.put("now", now);

		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProTStatiEsteri t ");
		jpql.append(" WHERE 1=1 ");

		JpaQueryHelper.andStringLike(jpql, params, "t.codNazioneMin", "codNazioneMin", codNazioneMin);
		JpaQueryHelper.andStringLike(jpql, params, "t.dsStatiEsteri", "dsStatiEsteri", dsStatiEsteri);

		// Elenco comuni validi alla data
		jpql.append("    AND (t.dtInizio IS NULL OR (t.dtInizio IS NOT NULL and t.dtInizio <= :now ) ) ");
		jpql.append("    AND (t.dtFine   IS NULL OR (t.dtFine   IS NOT NULL and t.dtFine   >= :now ) ) ");

		jpql.append(" ORDER BY t.dsStatiEsteri ");

		TypedQuery<ProTStatiEsteri> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public Optional<ProTStatiEsteri> findByCodice(String codiceStatoEstero) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProTStatiEsteri t ");
		jpql.append(" WHERE 1=1 ");
		
		JpaQueryHelper.andStringLike(jpql, params, "t.codNazioneMin", "codNazioneMin", codiceStatoEstero);
		
		TypedQuery<ProTStatiEsteri> query = composeTypedQuery(jpql, params);
		
		if (!query.getResultList().isEmpty()) {
			return Optional.of(query.getResultList().get(0));
		} else {
			return null;
		}
		
	}
}
