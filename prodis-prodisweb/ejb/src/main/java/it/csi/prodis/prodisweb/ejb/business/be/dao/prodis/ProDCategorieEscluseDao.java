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
package it.csi.prodis.prodisweb.ejb.business.be.dao.prodis;

import java.math.BigDecimal;
import java.util.List;

import it.csi.prodis.prodisweb.ejb.business.be.dao.BaseAuditedEntityDao;
import it.csi.prodis.prodisweb.ejb.entity.ProDCategorieEscluse;
import it.csi.prodis.prodisweb.ejb.util.jpa.Page;


/**
 * Data Access Object interface for the entity ProDCategorieEscluse
 */
public interface ProDCategorieEscluseDao extends BaseAuditedEntityDao<Long, ProDCategorieEscluse> {

	List<ProDCategorieEscluse> findByField(String field);
	
	public Page<ProDCategorieEscluse> findPaginatedByIdProspettoProv(int page, int size, String sortField, String sortDirection,
			BigDecimal idProspettoProv);

	List<ProDCategorieEscluse> findByIdProspettoProv(BigDecimal idProspettoProv);
}
