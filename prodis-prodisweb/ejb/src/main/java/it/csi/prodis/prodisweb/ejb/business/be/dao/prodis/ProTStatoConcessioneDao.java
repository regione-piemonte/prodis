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
import it.csi.prodis.prodisweb.ejb.entity.ProTStatoConcessione;
import it.csi.prodis.prodisweb.lib.dto.common.DecodificaGenerica;

/**
 * Data Access Object interface for the entity ProTStatoConcessione
 */
public interface ProTStatoConcessioneDao extends BaseEntityDao<Long, ProTStatoConcessione> {

	List<ProTStatoConcessione> findByField(String field);
	
	List<DecodificaGenerica> findByFilter(DecodificaGenerica filtro);
}
