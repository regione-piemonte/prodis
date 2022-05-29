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

import java.util.Date;
import java.util.List;
import java.util.Optional;

import it.csi.prodis.prodisweb.ejb.business.be.dao.BaseEntityDao;
import it.csi.prodis.prodisweb.ejb.entity.ProTComune;
import it.csi.prodis.prodisweb.lib.dto.common.DecodificaGenerica;

/**
 * Data Access Object interface for the entity ProTComune
 */
public interface ProTComuneDao extends BaseEntityDao<Long, ProTComune> {

	List<ProTComune> find(Long idProvincia, String codComuneMin, String dsProTComune, Date data);
	
	List<DecodificaGenerica> findByFilter(DecodificaGenerica filtro);

	Optional<ProTComune> findByCodice(String codiceComune);
}
