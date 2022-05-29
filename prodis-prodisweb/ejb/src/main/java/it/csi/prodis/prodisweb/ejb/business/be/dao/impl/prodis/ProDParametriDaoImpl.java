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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.csi.prodis.prodisweb.ejb.business.be.dao.impl.BaseEntityDaoImpl;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDParametriDao;
import it.csi.prodis.prodisweb.ejb.entity.ProDParametri;
import it.csi.prodis.prodisweb.ejb.util.ProdisSrvUtil;
import it.csi.prodis.prodisweb.ejb.util.jpa.JpaQueryHelper;
import it.csi.prodis.prodisweb.lib.dto.common.DecodificaGenerica;
import it.csi.prodis.prodisweb.lib.util.ProdisClassUtils;

/**
 * Data Access Object implementor for the entity ProDParametri
 */
@ApplicationScoped
public class ProDParametriDaoImpl extends BaseEntityDaoImpl<String, ProDParametri> implements ProDParametriDao {

	@Override
	public List<ProDParametri> findByField(String field) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProDParametri t ");
		jpql.append(" WHERE 1=1 ");

		JpaQueryHelper.andFieldEquals(jpql, params, "t.dsNome", "dsNome", field);

		jpql.append(" ORDER BY 1 ");

		TypedQuery<ProDParametri> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public DecodificaGenerica findByNome(String field) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder();

		sql.append(" select DS_VALORE, DS_DESCRIZIONE from PRO_D_PARAMETRI WHERE DS_NOME  = '");
		sql.append(field);
		sql.append("' ");
		try {
			Query query = composeNativeQuery(sql.toString(), params);
			List<Object[]> elenco = query.getResultList();
			List<DecodificaGenerica> decList = new ArrayList<DecodificaGenerica>();
			for (Object[] obj : elenco) {
				DecodificaGenerica dec = new DecodificaGenerica();
				if (obj[0] != null) {
					dec.setCodDecodifica((String) obj[0]);
				}
				if (obj[1] != null) {
					dec.setDsDecodifica((String) obj[1]);
				}
				decList.add(dec);
			}
			if (ProdisSrvUtil.isNotVoid(decList)) {
				return decList.get(0);
			} else {
				return new DecodificaGenerica();
			}
		} catch (Exception e) {
			log.error(ProdisClassUtils.truncClassName(getClass()) + " Eccezione !!", e);
			throw e;
		}

	}

	@Override
	public Optional<ProDParametri> getOneParametro(String parametroFirma) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProDParametri t ");
		jpql.append(" WHERE 1=1 ");
		jpql.append(" AND DS_NOME = '"+parametroFirma+"'");
		jpql.append(" ORDER BY 1 ");

		TypedQuery<ProDParametri> query = composeTypedQuery(jpql, params);
		
		return query.getResultList().stream().findFirst();
	}

	@Override
	public List<ProDParametri> getLikeParametri(String parametroFirmaLike) {
	
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProDParametri ");
		jpql.append(" WHERE DS_NOME LIKE :nome");		

		TypedQuery<ProDParametri> query = entityManager.createQuery(jpql.toString(), ProDParametri.class) ;
		query.setParameter("nome", parametroFirmaLike);
		
		return query.getResultList();
	}

}
