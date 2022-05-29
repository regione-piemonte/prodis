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
import it.csi.prodis.prodisweb.ejb.entity.ProTStatiEsteri;
import it.csi.prodis.prodisweb.lib.dto.common.DecodificaGenerica;

/**
 * Data Access Object interface for the entity ProTStatiEsteri
 */
public interface ProTStatiEsteriDao extends BaseEntityDao<Long, ProTStatiEsteri> {

	List<ProTStatiEsteri> findByField(String field);

	List<DecodificaGenerica> findByFilter(DecodificaGenerica filtro);
	
	List<ProTStatiEsteri> find(String codNazioneMin, String dsStatiEsteri, Date data);

	Optional<ProTStatiEsteri> findByCodice(String codiceStatoEstero);
}
