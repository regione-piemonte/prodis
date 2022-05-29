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

import java.util.List;

import it.csi.prodis.prodisweb.ejb.business.be.dao.BaseEntityDao;
import it.csi.prodis.prodisweb.ejb.entity.ProDDatiProvinciali;
import it.csi.prodis.prodisweb.ejb.entity.RitornoPassaggioQ3;

/**
 * Data Access Object interface for the entity ProDDatiProvinciali
 */
public interface ProDDatiProvincialiDao extends BaseEntityDao<Long, ProDDatiProvinciali> {

	List<ProDDatiProvinciali> findByField(String field);

	RitornoPassaggioQ3 passaggioQ3(Long idProspetto, String cfOperatore, Boolean soloScoperture);
	
}
