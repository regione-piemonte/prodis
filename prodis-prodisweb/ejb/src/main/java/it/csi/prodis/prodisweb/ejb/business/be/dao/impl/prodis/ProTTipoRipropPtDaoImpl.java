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
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProTTipoRipropPtDao;
import it.csi.prodis.prodisweb.ejb.entity.ProTTipoRipropPt;
import it.csi.prodis.prodisweb.ejb.util.jpa.JpaQueryHelper;
import it.csi.prodis.prodisweb.lib.dto.common.DecodificaGenerica;

/**
 * Data Access Object implementor for the entity ProTTipoRipropPt
 */
@ApplicationScoped
public class ProTTipoRipropPtDaoImpl extends BaseEntityDaoImpl<Long, ProTTipoRipropPt> implements ProTTipoRipropPtDao {

	@Override
	public List<ProTTipoRipropPt> findByField(String field) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProTTipoRipropPt t ");
		jpql.append(" WHERE 1=1 ");

		JpaQueryHelper.andFieldEquals(jpql, params, "t.field", "field", field);

		jpql.append(" ORDER BY t.idTipoRipropPt ");

		TypedQuery<ProTTipoRipropPt> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DecodificaGenerica> findByFilter(DecodificaGenerica filtro) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		StringBuilder sql = new StringBuilder();
		 
		sql.append(" SELECT  DISTINCT  ID_TIPO_RIPROP_PT id_dec, ID_TIPO_RIPROP_PT cod_dec, DS_TIPO_RIPROP_PT des_dec, AMBITO_RIPROP ambito "); 
	    sql.append(" FROM  PRO_T_TIPO_RIPROP_PT  WHERE 1=1 ");
	    if (filtro.getIdDecodifica() != null ) {
	    	 params.put("idDecod", filtro.getIdDecodifica().toString());
	    	 sql.append(" AND ID_TIPO_RIPROP_PT = :idDecod");
	    } else {
	    	if (filtro.getCodDecodifica() != null) {
	    		params.put("codDecod",  "%" + filtro.getCodDecodifica().toUpperCase() + "%");
	    		sql.append(" AND upper(ID_TIPO_RIPROP_PT) like :codDecod");
	    	}  
	    	if (filtro.getDsDecodifica() != null) {
	    		params.put("dsDecod", "%" + filtro.getDsDecodifica().toUpperCase() + "%");
	    		sql.append(" AND upper(DS_TIPO_RIPROP_PT) like :dsDecod");
	    	}  
	    	if (filtro.getAmbitoDecodifica() != null) {
	    		params.put("ambito", filtro.getAmbitoDecodifica().toUpperCase());
		    	sql.append(" AND upper(AMBITO_RIPROP) = :ambito");
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
	    	   dec.setCodDecodifica(((BigDecimal) obj[0]).toString());
	    	}
	    	if (obj[3] != null) {
		       	   dec.setAmbitoDecodifica((String) obj[3]);
		    	}
	    	decList.add(dec);
		}
		return decList;
	}

}
