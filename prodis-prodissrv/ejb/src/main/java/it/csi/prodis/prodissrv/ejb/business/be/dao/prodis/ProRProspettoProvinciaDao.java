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
import it.csi.prodis.prodissrv.ejb.entity.ProRProspettoProvincia;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ElencoProvScoperture;

/**
 * Data Access Object interface for the entity ProRProspettoProvincia
 */
public interface ProRProspettoProvinciaDao extends BaseEntityDao<Long, ProRProspettoProvincia> {

	List<ProRProspettoProvincia> findByField(String field);

	List<ProRProspettoProvincia> findByIdProspetto(Long idProspetto);
	
	List<ElencoProvScoperture> findElencoScopertureByIdProspetto(Long idProspetto);
}
