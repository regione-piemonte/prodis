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
import it.csi.prodis.prodisweb.ejb.entity.ProDVistaElencoProvQ2;

/**
 * Data Access Object interface for the entity ProDVistaElencoProvQ2
 */
public interface ProDVistaElencoProvQ2Dao extends BaseEntityDao<Long, ProDVistaElencoProvQ2> {

	List<ProDVistaElencoProvQ2> findByField(String field);
	
	List<ProDVistaElencoProvQ2> findByIdProspetto(Long idProspetto);
}
