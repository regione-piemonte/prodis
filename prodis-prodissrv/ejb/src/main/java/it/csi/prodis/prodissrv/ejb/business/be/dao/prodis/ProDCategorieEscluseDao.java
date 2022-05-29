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
package it.csi.prodis.prodissrv.ejb.business.be.dao.prodis;

import java.util.List;

import it.csi.prodis.prodissrv.ejb.business.be.dao.BaseEntityDao;
import it.csi.prodis.prodissrv.ejb.entity.ProDCategorieEscluse;
import it.csi.prodis.prodissrv.ejb.util.jpa.Page;


/**
 * Data Access Object interface for the entity ProDCategorieEscluse
 */
public interface ProDCategorieEscluseDao extends BaseEntityDao<Long, ProDCategorieEscluse> {

	List<ProDCategorieEscluse> findByField(String field);
	
	public Page<ProDCategorieEscluse> findPaginatedByIdProspettoProv(int page, int size, String sortField, String sortDirection,
			Long idProspettoProv);

	List<ProDCategorieEscluse> findByIdProspettoProv(Long idProspettoProv);
}
