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
import java.util.Optional;

import it.csi.prodis.prodisweb.ejb.business.be.dao.BaseEntityDao;
import it.csi.prodis.prodisweb.ejb.entity.ProDParametri;
import it.csi.prodis.prodisweb.lib.dto.common.DecodificaGenerica;

/**
 * Data Access Object interface for the entity ProDParametri
 */
public interface ProDParametriDao extends BaseEntityDao<String, ProDParametri> {

	List<ProDParametri> findByField(String field);

	DecodificaGenerica findByNome(String field);

	Optional<ProDParametri> getOneParametro(String parametroFirma);

	List<ProDParametri> getLikeParametri(String parametroFirmaLike);
}
