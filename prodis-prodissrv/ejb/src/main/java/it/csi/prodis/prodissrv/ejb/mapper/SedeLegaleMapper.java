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
package it.csi.prodis.prodissrv.ejb.mapper;

import java.util.Collection;
import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.csi.prodis.prodissrv.ejb.entity.ProDSedeLegale;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Comune;
import it.csi.prodis.prodissrv.lib.dto.prospetto.SedeLegale;
import it.csi.prodis.prodissrv.lib.mapper.BaseMapperInterface;

/**
 * Mapper between SedeLegale and ProDSedeLegale
 */
@Mapper(uses = { Comune.class })
public interface SedeLegaleMapper extends BaseMapperInterface<SedeLegale, ProDSedeLegale> {

	@Override
	@Mapping(source = "proTComune", target = "comune")
	SedeLegale toModel(ProDSedeLegale entity);

	@Override
	@IterableMapping(elementTargetType = SedeLegale.class)
	List<SedeLegale> toModels(Collection<ProDSedeLegale> entities);

	@Override
	@IterableMapping(elementTargetType = ProDSedeLegale.class)
	List<ProDSedeLegale> toEntities(Collection<SedeLegale> models);

}
