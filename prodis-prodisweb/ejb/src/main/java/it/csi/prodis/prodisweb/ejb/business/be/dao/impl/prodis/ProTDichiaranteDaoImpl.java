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

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.csi.prodis.prodisweb.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProTDichiaranteDao;
import it.csi.prodis.prodisweb.ejb.entity.ProTDichiarante;
import it.csi.prodis.prodisweb.ejb.util.jpa.JpaQueryHelper;
import it.csi.prodis.prodisweb.lib.dto.common.DecodificaGenerica;

/**
 * Data Access Object implementor for the entity ProTDichiarante
 */
@ApplicationScoped
public class ProTDichiaranteDaoImpl extends BaseEntityDaoImpl<Long, ProTDichiarante> implements ProTDichiaranteDao {

	@Override
	public List<ProTDichiarante> findByField(String field) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProTDichiarante t ");
		jpql.append(" WHERE 1=1 ");

		JpaQueryHelper.andFieldEquals(jpql, params, "t.idTDichiarante", "idTDichiarante", field);

		jpql.append(" ORDER BY t.idTDichiarante ");

		TypedQuery<ProTDichiarante> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DecodificaGenerica> findByFilter(DecodificaGenerica filtro) {
		Map<String, Object> params = new HashMap<String, Object>();
		Date now = new Date();
		params.put("now", now);
		StringBuilder sql = new StringBuilder();
		 
		sql.append(" SELECT  DISTINCT  ID_T_DICHIARANTE id_dec, COD_DICHIARANTE cod_dec,  DESC_DICHIARANTE des_dec "); 
	    sql.append(" FROM  PRO_T_DICHIARANTE  WHERE 1=1 ");
	    if (filtro.getIdDecodifica() != null ) {
	    	 params.put("idDecod", filtro.getIdDecodifica().toString());
	    	 sql.append(" AND ID_T_DICHIARANTE = :idDecod");
	    } else {
	    	if (filtro.getCodDecodifica() != null) {
	    		params.put("codDecod",  "%" + filtro.getCodDecodifica().toUpperCase() + "%");
	    		sql.append(" AND upper(COD_DICHIARANTE) like :codDecod");
	    	}  
	    	if (filtro.getDsDecodifica() != null) {
	    		params.put("dsDecod", "%" + filtro.getDsDecodifica().toUpperCase() + "%");
	    		sql.append(" AND upper(DESC_DICHIARANTE) like :dsDecod");
	    	}   
	    } 
	    if (!filtro.isFlgAncheNonValidi()) {
		    sql.append(" AND (DATA_INIZIO IS NULL OR (DATA_INIZIO IS NOT NULL and DATA_INIZIO <= :now ) ) ");
		    sql.append(" AND (DATA_FINE   IS NULL OR (DATA_FINE   IS NOT NULL and DATA_FINE   >= :now ) ) ");
	    }
	    sql.append(" ORDER BY des_dec ");
	    
	    Query query = composeNativeQuery(sql.toString(), params);
	    List<Object []> elenco = query.getResultList();
	    List<DecodificaGenerica> decList = new ArrayList<DecodificaGenerica>();
	    
	    for(Object []  obj : elenco) {
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
