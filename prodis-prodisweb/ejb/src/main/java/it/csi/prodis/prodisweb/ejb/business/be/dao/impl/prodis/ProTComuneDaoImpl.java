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
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProTComuneDao;
import it.csi.prodis.prodisweb.ejb.entity.ProTComune;
import it.csi.prodis.prodisweb.ejb.util.jpa.JpaQueryHelper;
import it.csi.prodis.prodisweb.lib.dto.common.DecodificaGenerica;

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
		jpql.append(" AND (t.dtInizio IS NULL OR (t.dtInizio IS NOT NULL and t.dtInizio <= :now ) ) ");
		jpql.append(" AND (t.dtFine   IS NULL OR (t.dtFine   IS NOT NULL and t.dtFine   >= :now ) ) ");

		jpql.append(" ORDER BY t.dsProTComune ");

		TypedQuery<ProTComune> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DecodificaGenerica> findByFilter(DecodificaGenerica filtro) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder();
		 
		sql.append(" SELECT  DISTINCT   ID_T_COMUNE id_dec, COD_COMUNE_MIN cod_dec, DS_PRO_T_COMUNE des_dec, ID_PROVINCIA id_opt ");
	    sql.append(" FROM  Pro_T_Comune  WHERE 1=1 ");
	    if (filtro.getIdDecodifica() != null ) {
	    	 params.put("idDecod", filtro.getIdDecodifica().toString());
	    	 sql.append(" AND ID_T_COMUNE = :idDecod");
	    } else {
	    	if (filtro.getCodDecodifica() != null) {
	    		params.put("codDecod",  "%" + filtro.getCodDecodifica().toUpperCase() + "%");
	    		sql.append(" AND upper(COD_COMUNE_MIN) like :codDecod");
	    	}  
	    	if (filtro.getDsDecodifica() != null) {
	    		params.put("dsDecod", "%" + filtro.getDsDecodifica().toUpperCase() + "%");
	    		sql.append(" AND upper(DS_PRO_T_COMUNE) like :dsDecod");
	    	}
	    	if (filtro.getIdFiltroFacoltativo() != null) {
	    		params.put("idFiltroFac", filtro.getIdFiltroFacoltativo().toString());
	    		sql.append(" AND ID_PROVINCIA = :idFiltroFac");
	    	}
	    } 
	    
	    if(filtro.getDataValidita() != null) {
	    	Date dataV = filtro.getDataValidita();
			params.put("now", dataV);
		    sql.append(" AND (dt_Inizio IS NULL OR (dt_Inizio IS NOT NULL and dt_Inizio <= :now ) ) ");
		    sql.append(" AND (dt_Fine   IS NULL OR (dt_Fine   IS NOT NULL and dt_Fine   >= :now ) ) ");
	    }else if (!filtro.isFlgAncheNonValidi()) {
	    	Date now = new Date();
			params.put("now", now);
		    sql.append(" AND (dt_Inizio IS NULL OR (dt_Inizio IS NOT NULL and dt_Inizio <= :now ) ) ");
		    sql.append(" AND (dt_Fine   IS NULL OR (dt_Fine   IS NOT NULL and dt_Fine   >= :now ) ) ");
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
	    	if (obj[3] != null) {
	    		dec.setIdFiltroFacoltativo(((BigDecimal) obj[3]).longValue());
		    }
	    	decList.add(dec);
		}
		return decList;
	}

	@Override
	public Optional<ProTComune> findByCodice(String codiceComune) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProTComune t ");
		jpql.append(" WHERE 1=1 ");
		
		JpaQueryHelper.andStringLike(jpql, params, "t.codComuneMin", "codComuneMin", codiceComune);
		
		TypedQuery<ProTComune> query = composeTypedQuery(jpql, params);
		
		if (!query.getResultList().isEmpty()) {
			return Optional.of(query.getResultList().get(0));
		} else {
			return null;
		}
	
	}
}
