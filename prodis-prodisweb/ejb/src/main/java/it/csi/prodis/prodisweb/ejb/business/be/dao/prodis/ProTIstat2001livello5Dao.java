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
import it.csi.prodis.prodisweb.ejb.entity.ProTIstat2001livello5;
import it.csi.prodis.prodisweb.lib.dto.common.DecodificaGenerica;

/**
 * Data Access Object interface for the entity ProTIstat2001livello5
 */
public interface ProTIstat2001livello5Dao extends BaseEntityDao<Long, ProTIstat2001livello5> {

	List<ProTIstat2001livello5> findByField(String field);

	List<DecodificaGenerica> findByFilter(DecodificaGenerica filtro);

	Optional<ProTIstat2001livello5> findByCodice(String codiceQualificaIstat);
}
