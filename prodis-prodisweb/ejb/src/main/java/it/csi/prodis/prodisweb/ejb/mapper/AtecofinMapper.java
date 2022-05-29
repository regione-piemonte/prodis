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

import it.csi.prodis.prodisweb.ejb.entity.ProTAtecofin;
import it.csi.prodis.prodisweb.ejb.mapper.IdMapper.EmptyIdToNull;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Atecofin;
import it.csi.prodis.prodisweb.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Atecofin and ProTAtecofin
 */
@Mapper(uses = { IdMapper.class })
public interface AtecofinMapper extends BaseMapperInterface<Atecofin, ProTAtecofin> {

	@Override
	@Mapping(source = "id", target = "id", qualifiedBy = EmptyIdToNull.class)
	Atecofin toModel(ProTAtecofin entity);

	@Override
	@IterableMapping(elementTargetType = Atecofin.class)
	List<Atecofin> toModels(Collection<ProTAtecofin> entities);

	@Override
	@IterableMapping(elementTargetType = ProTAtecofin.class)
	List<ProTAtecofin> toEntities(Collection<Atecofin> models);

	@Override
	ProTAtecofin toEntityExisting(Atecofin model, @MappingTarget ProTAtecofin entity);

}
