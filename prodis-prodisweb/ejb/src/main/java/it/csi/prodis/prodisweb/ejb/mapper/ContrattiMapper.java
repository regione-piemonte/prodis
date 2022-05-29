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
import org.mapstruct.MappingTarget;

import it.csi.prodis.prodisweb.ejb.entity.ProTContratti;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Contratti;
import it.csi.prodis.prodisweb.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Contratti and ProTContratti
 */
@Mapper
public interface ContrattiMapper extends BaseMapperInterface<Contratti, ProTContratti> {

	@Override
	// @Mapping(source = "entityName", target = "modelName")
	Contratti toModel(ProTContratti entity);

	@Override
	@IterableMapping(elementTargetType = Contratti.class)
	List<Contratti> toModels(Collection<ProTContratti> entities);

	@Override
	@IterableMapping(elementTargetType = ProTContratti.class)
	List<ProTContratti> toEntities(Collection<Contratti> models);
	
	@Override
	ProTContratti toEntityExisting(Contratti model, @MappingTarget ProTContratti entity);

}
