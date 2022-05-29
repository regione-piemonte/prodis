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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import it.csi.prodis.prodisweb.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDAssPubblicheDao;
import it.csi.prodis.prodisweb.ejb.entity.ProDAssPubbliche;
import it.csi.prodis.prodisweb.ejb.entity.ProDAssPubblichePK;

/**
 * Data Access Object implementor for the entity ProDAssPubbliche
 */
@ApplicationScoped
public class ProDAssPubblicheDaoImpl extends BaseAuditedEntityDaoImpl<ProDAssPubblichePK, ProDAssPubbliche>
		implements ProDAssPubblicheDao {

	@Override
	public List<ProDAssPubbliche> findByField(Long idProspetto) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProDAssPubbliche t ");
		jpql.append(" WHERE 1=1 ");

//		ProDAssPubblichePK laPk = new ProDAssPubblichePK();
//		laPk.setIdProspetto(idProspetto);

//		JpaQueryHelper.andFieldEquals(jpql, params, "t.id", "id", laPk);
//		JpaQueryHelper.andFieldEquals(jpql, params, "t.idTRegione", "idTRegione", idTRegione);

//		jpql.append(" ORDER BY t.it.csi.prodis.prodisweb.ejb.entity ");
		jpql.append(" and t.id.idProspetto = " + idProspetto);

		TypedQuery<ProDAssPubbliche> query = composeTypedQuery(jpql, params);
		return query.getResultList();
		
	}

}
