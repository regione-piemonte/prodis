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

import it.csi.prodis.prodisweb.ejb.entity.ProTCpi;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Cpi;
import it.csi.prodis.prodisweb.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Cpi and ProTCpi
 */
@Mapper
public interface CpiMapper extends BaseMapperInterface<Cpi, ProTCpi> {

	@Override
	// @Mapping(source = "entityName", target = "modelName")
	Cpi toModel(ProTCpi entity);

	@Override
	@IterableMapping(elementTargetType = Cpi.class)
	List<Cpi> toModels(Collection<ProTCpi> entities);

	@Override
	@IterableMapping(elementTargetType = ProTCpi.class)
	List<ProTCpi> toEntities(Collection<Cpi> models);

}
