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
package it.csi.prodis.prodisweb.ejb.mapper;

import java.util.Collection;
import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import it.csi.prodis.prodisweb.ejb.entity.ProTComune;
import it.csi.prodis.prodisweb.ejb.mapper.IdMapper.EmptyIdToNull;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Comune;
import it.csi.prodis.prodisweb.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Comune and ProTComune
 */
@Mapper(uses = { IdMapper.class })
public interface ComuneMapper extends BaseMapperInterface<Comune, ProTComune> {

	@Override
	@Mapping(source = "id", target = "id", qualifiedBy = EmptyIdToNull.class)
	// @Mapping(target="idTCpi", source="proTCpi.id")
	Comune toModel(ProTComune entity);

	@Override
	@IterableMapping(elementTargetType = Comune.class)
	List<Comune> toModels(Collection<ProTComune> entities);

	@Override
	@IterableMapping(elementTargetType = ProTComune.class)
	List<ProTComune> toEntities(Collection<Comune> models);

	@Override
	ProTComune toEntityExisting(Comune model, @MappingTarget ProTComune entity);

}
