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
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProRProspettoProvinciaDao;
import it.csi.prodis.prodissrv.ejb.entity.ProRProspettoProvincia;
import it.csi.prodis.prodissrv.ejb.util.jpa.JpaQueryHelper;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ElencoProvScoperture;

/**
 * Data Access Object implementor for the entity ProRProspettoProvincia
 */
@ApplicationScoped
public class ProRProspettoProvinciaDaoImpl extends BaseEntityDaoImpl<Long, ProRProspettoProvincia>
		implements ProRProspettoProvinciaDao {

	@Override
	public List<ProRProspettoProvincia> findByField(String field) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProRProspettoProvincia t ");
		jpql.append(" WHERE 1=1 ");

		JpaQueryHelper.andFieldEquals(jpql, params, "t.field", "field", field);

		jpql.append(" ORDER BY t.idProspettoProv ");

		TypedQuery<ProRProspettoProvincia> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public List<ProRProspettoProvincia> findByIdProspetto(Long idProspetto) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProRProspettoProvincia t ");
		jpql.append(" WHERE 1=1 ");

		JpaQueryHelper.andFieldEquals(jpql, params, "t.proDProspetto.idProspetto", "field", idProspetto);

		jpql.append(" ORDER BY t.proTProvincia.dsTarga ");

		TypedQuery<ProRProspettoProvincia> query = composeTypedQuery(jpql, params);
		return query.getResultList();
	}

	@Override
	public List<ElencoProvScoperture> findElencoScopertureByIdProspetto(Long idProspetto) {
		Map<String, Object> params = new HashMap<String, Object>();
		Date now = new Date();
		params.put("id", idProspetto);

		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT ");
		sql.append("  DISTINCT r.ID_PROSPETTO_PROV,  ");
		sql.append("  r.ID_T_PROVINCIA, ");
		sql.append("  t.DS_PRO_T_PROVINCIA, ");
		sql.append("  t.ds_targa,  ");
		sql.append(
				"  NVL(PCK_PRODIS_2012_COMP_UTILS.get_n_scopert_disab_pv(r.ID_PROSPETTO_PROV), '0') n_scopert_disab, ");
		sql.append("  CASE PCK_PRODIS_2012_COMP_UTILS.get_cat_compens_disab_pv(r.ID_PROSPETTO_PROV) "
				+ "WHEN 'E' THEN 'Eccedenza' WHEN  'R' THEN 'Riduzione' ELSE NULL END AS cat_compens_disab, ");
		sql.append(
				"  NVL(PCK_PRODIS_2012_COMP_UTILS.get_n_scopert_catprot_pv(r.ID_PROSPETTO_PROV), '0') n_scopert_catprot, ");
		sql.append(
				"  NVL(PCK_PRODIS_2012_COMP_UTILS.get_n_compen_disab_pv(r.ID_PROSPETTO_PROV), '0') n_compen_disab, ");
		sql.append("  CASE PCK_PRODIS_2012_COMP_UTILS.get_cat_compens_catprot_pv(r.ID_PROSPETTO_PROV) "
				+ "WHEN 'E' THEN 'Eccedenza' WHEN  'R' THEN 'Riduzione' ELSE NULL END AS cat_compens_catprot, ");
		sql.append(
				"  NVL(PCK_PRODIS_2012_COMP_UTILS.get_n_compen_catprot_pv(r.ID_PROSPETTO_PROV), '0') n_compen_catprot ");
		sql.append(" FROM ");
		sql.append("  PRO_D_PROSPETTO p, ");
		sql.append("  PRO_R_PROSPETTO_PROVINCIA r, ");
		sql.append("  PRO_T_PROVINCIA t ");
		sql.append(" WHERE ");
		sql.append("  r.ID_PROSPETTO = p.ID_PROSPETTO ");
		sql.append("  AND p.ID_PROSPETTO = :id ");
		sql.append("  AND t.id_t_provincia = r.id_t_provincia ");
		sql.append(" ORDER BY ");
		sql.append("  t.ds_targa ");

		Query query = composeNativeQuery(sql.toString(), params);

		List<Object[]> elenco = query.getResultList();
		List<ElencoProvScoperture> resList = new ArrayList<ElencoProvScoperture>();
		for (Object[] obj : elenco) {
			ElencoProvScoperture el = new ElencoProvScoperture();

			if (obj[0] != null) {
				el.setIdProspettoProv(((BigDecimal) obj[0]).longValue());
			}
			if (obj[1] != null) {
				el.setIdTProvincia(((BigDecimal) obj[1]).longValue());
			}
			if (obj[2] != null) {
				el.setDsProTProvincia((String) obj[2]);
			}
			if (obj[3] != null) {
				el.setDsTarga((String) obj[3]);
			}

			el.setNumScopertDisab((BigDecimal) obj[4]);
			el.setCatCompensDisab((String) obj[5]);
			el.setNumScopertCatprot((BigDecimal) obj[6]);
			el.setNumCompenDisab((BigDecimal) obj[7]);
			el.setCatCompensCatprot((String) obj[8]);
			el.setNumCompenCatprot((BigDecimal) obj[9]);
			resList.add(el);
		}

		return resList;
	}

}
