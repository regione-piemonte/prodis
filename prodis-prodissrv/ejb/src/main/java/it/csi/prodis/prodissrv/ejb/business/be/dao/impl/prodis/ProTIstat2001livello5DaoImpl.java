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
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProTIstat2001livello5Dao;
import it.csi.prodis.prodissrv.ejb.entity.ProTIstat2001livello5;
import it.csi.prodis.prodissrv.ejb.util.jpa.JpaQueryHelper;
import it.csi.prodis.prodissrv.lib.dto.common.DecodificaGenerica;

/**
 * Data Access Object implementor for the entity ProTIstat2001livello5
 */
@ApplicationScoped
public class ProTIstat2001livello5DaoImpl extends BaseEntityDaoImpl<Long, ProTIstat2001livello5> implements ProTIstat2001livello5Dao {

	@Override
	public List<ProTIstat2001livello5> findByField(String field) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProTIstat2001livello5 t ");
		jpql.append(" WHERE 1=1 ");

		JpaQueryHelper.andFieldEquals(jpql, params, "t.field", "field", field);

		jpql.append(" ORDER BY t.idTIstat2001livello5 ");

		TypedQuery<ProTIstat2001livello5> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public List<DecodificaGenerica> findByFilter(DecodificaGenerica filtro) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		StringBuilder sql = new StringBuilder();
		 
		sql.append(" SELECT ");
		sql.append(" DISTINCT ");
		sql.append("   ID_T_ISTAT2001LIVELLO5 id_dec "); 
		sql.append(" , COD_ISTAT2001LIVELLO5_MIN cod_dec  ");
		sql.append(" , DS_COM_ISTAT2001LIVELLO5 des_dec "); 
		
	    
	    sql.append(" FROM ");
	    sql.append("  PRO_T_ISTAT2001LIVELLO5 "); 
	    sql.append(" WHERE 1=1 ");
	    if (filtro.getIdDecodifica() != null ) {
	    	 sql.append(" AND ID_T_ISTAT2001LIVELLO5 = ");
	    	 sql.append(filtro.getIdDecodifica().toString());
	    } else {
	    	if (filtro.getCodDecodifica() != null) {
	    		sql.append(" AND upper(COD_ISTAT2001LIVELLO5_MIN) like '%");
		    	 sql.append(filtro.getCodDecodifica().toUpperCase());
		    	 sql.append("%'");
	    	}  
	    	if (filtro.getDsDecodifica() != null) {
	    		sql.append(" AND upper(DS_COM_ISTAT2001LIVELLO5) like '%");
		    	 sql.append(filtro.getDsDecodifica().toUpperCase());
		    	 sql.append("%'");
	    	}  
  
	    } 
	    if (!filtro.isFlgAncheNonValidi()) {
	    	Date now = new Date();
			params.put("now", now);
	       sql.append(" AND (DT_INIZIO IS NULL OR (DT_INIZIO IS NOT NULL and DT_INIZIO <= :now ) ) ");
	       sql.append(" AND (DT_FINE   IS NULL OR (DT_FINE   IS NOT NULL and DT_FINE   >= :now ) ) ");
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
