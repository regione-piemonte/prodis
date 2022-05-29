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
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProTAssunzioneProtettaDao;
import it.csi.prodis.prodisweb.ejb.entity.ProTAssunzioneProtetta;
import it.csi.prodis.prodisweb.ejb.util.jpa.JpaQueryHelper;
import it.csi.prodis.prodisweb.lib.dto.common.DecodificaGenerica;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<DecodificaGenerica> findByFilter(DecodificaGenerica filtro) {
		Map<String, Object> params = new HashMap<String, Object>();
		Date now = new Date();
		params.put("now", now);
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT  DISTINCT   ID_T_ASSUNZIONE_PROTETTA id_dec, COD_ASSUNZIONE_PROTETTA cod_dec, DESC_ASSUNZIONE_PROTETTA des_dec ");
		sql.append(" FROM   PRO_T_ASSUNZIONE_PROTETTA  WHERE 1=1 ");
		if (filtro.getIdDecodifica() != null) {
			params.put("idDecod", filtro.getIdDecodifica().toString());
	    	 sql.append(" AND ID_T_ASSUNZIONE_PROTETTA = :idDecod");
		} else {
			if (filtro.getCodDecodifica() != null) {
	    		params.put("codDecod",  "%" + filtro.getCodDecodifica().toUpperCase() + "%");
	    		sql.append(" AND upper(COD_ASSUNZIONE_PROTETTA) like :codDecod");
			}
			if (filtro.getDsDecodifica() != null) {
	    		params.put("dsDecod", "%" + filtro.getDsDecodifica().toUpperCase() + "%");
	    		sql.append(" AND upper(DESC_ASSUNZIONE_PROTETTA) like :dsDecod");
			}
		}
		sql.append(" AND (data_inizio IS NULL OR (data_inizio IS NOT NULL and data_inizio <= :now ) ) ");
		sql.append(" AND (data_fine   IS NULL OR (data_fine   IS NOT NULL and data_fine   >= :now ) ) ");

		// sql.append(" ORDER BY des_dec ");
		// senza ordinamento mette "Nessuno dei precedenti" per ultimo come richiesto PRODIS.0135

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

	@Override
	public Optional<ProTAssunzioneProtetta> findByCodice(String codiceTipoAssunzioneProtetta) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProTAssunzioneProtetta t ");
		jpql.append(" WHERE 1=1 ");
		
		JpaQueryHelper.andStringLike(jpql, params, "t.codAssunzioneProtetta", "codAssunzioneProtetta", codiceTipoAssunzioneProtetta);

		TypedQuery<ProTAssunzioneProtetta> query = composeTypedQuery(jpql, params);
		
		if (!query.getResultList().isEmpty()) {
			return Optional.of(query.getResultList().get(0));
		} else {
			return null;
		}

	}
	
}
