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

import it.csi.prodis.prodisweb.ejb.business.be.dao.BaseAuditedEntityDao;
import it.csi.prodis.prodisweb.ejb.entity.ProRProspettoProvincia;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ElencoProvScoperture;

/**
 * Data Access Object interface for the entity ProRProspettoProvincia
 */
public interface ProRProspettoProvinciaDao extends BaseAuditedEntityDao<Long, ProRProspettoProvincia> {

	List<ProRProspettoProvincia> findByField(String field);

	List<ProRProspettoProvincia> findByIdProspetto(Long idProspetto);
	
	List<ElencoProvScoperture> findElencoScopertureByIdProspetto(Long idProspetto);
}
