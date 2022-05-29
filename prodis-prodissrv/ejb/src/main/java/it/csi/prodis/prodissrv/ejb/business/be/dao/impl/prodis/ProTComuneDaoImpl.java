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
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProTComuneDao;
import it.csi.prodis.prodissrv.ejb.entity.ProTComune;
import it.csi.prodis.prodissrv.ejb.util.jpa.JpaQueryHelper;
import it.csi.prodis.prodissrv.lib.dto.common.DecodificaGenerica;

/**
 * Data Access Object implementor for the entity ProTComune
 */
@ApplicationScoped
public class ProTComuneDaoImpl extends BaseEntityDaoImpl<Long, ProTComune> implements ProTComuneDao {

	@Override
	public List<ProTComune> find(Long idProvincia, String codComuneMin, String dsProTComune, Date data) {
		Map<String, Object> params = new HashMap<String, Object>();
		Date now = new Date();
		// elenco comuni valida alla data di nascita
		if (data != null) {
			now = data;
		}
		params.put("now", now);
		
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProTComune t ");
		jpql.append(" WHERE 1=1 ");

		JpaQueryHelper.andStringLike(jpql, params, "t.codComuneMin", "codComuneMin", codComuneMin);
		JpaQueryHelper.andStringLike(jpql, params, "t.dsProTComune", "dsProTComune", dsProTComune);
		JpaQueryHelper.andFieldEquals(jpql, params, "t.proTProvincia.idTProvincia", "idProvincia", idProvincia);
		
		//  Elenco comuni validi alla data
		jpql.append("    AND (t.dtInizio IS NULL OR (t.dtInizio IS NOT NULL and t.dtInizio <= :now ) ) ");
		jpql.append("    AND (t.dtFine   IS NULL OR (t.dtFine   IS NOT NULL and t.dtFine   >= :now ) ) ");

		jpql.append(" ORDER BY t.dsProTComune ");

		TypedQuery<ProTComune> query = composeTypedQuery(jpql, params);
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
		sql.append("  ID_T_COMUNE id_dec "); 
		sql.append(" , COD_COMUNE_MIN cod_dec  ");
		sql.append(" , DS_PRO_T_COMUNE des_dec ");
		sql.append(" , ID_PROVINCIA id_opt ");
	    
	    sql.append(" FROM ");
	    sql.append("  Pro_T_Comune "); 
	    sql.append(" WHERE 1=1 ");
	    if (filtro.getIdDecodifica() != null ) {
	    	 sql.append(" AND ID_T_COMUNE = ");
	    	 sql.append(filtro.getIdDecodifica().toString());
	    } else {
	    	if (filtro.getCodDecodifica() != null) {
	    		sql.append(" AND upper(COD_COMUNE_MIN) like '%");
		    	 sql.append(filtro.getCodDecodifica().toUpperCase());
		    	 sql.append("%'");
	    	}  
	    	if (filtro.getDsDecodifica() != null) {
	    		sql.append(" AND upper(DS_PRO_T_COMUNE) like '%");
		    	 sql.append(filtro.getDsDecodifica().toUpperCase());
		    	 sql.append("%'");
	    	}
	    	
	    	if (filtro.getIdFiltroFacoltativo() != null) {
	    		sql.append(" AND ID_PROVINCIA = ");
		    	 sql.append(filtro.getIdFiltroFacoltativo().toString());
	    	}
	    } 
	    sql.append(" AND (DT_INIZIO IS NULL OR (DT_INIZIO IS NOT NULL and DT_INIZIO <= :now ) ) ");
	    sql.append(" AND (DT_FINE   IS NULL OR (DT_FINE   IS NOT NULL and DT_FINE   >= :now ) ) ");
	    
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
	    	if (obj[3] != null) {
	    		dec.setIdFiltroFacoltativo(((BigDecimal) obj[3]).longValue());
		    }
	    	decList.add(dec);
		}
		return decList;
	}
}
