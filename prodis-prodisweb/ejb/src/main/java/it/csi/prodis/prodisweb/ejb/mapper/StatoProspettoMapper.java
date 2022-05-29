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

import it.csi.prodis.prodisweb.ejb.entity.ProTStatoProspetto;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.StatoProspetto;
import it.csi.prodis.prodisweb.lib.mapper.BaseMapperInterface;

/**
 * Mapper between StatoProspetto and ProTStatoProspetto
 */
@Mapper
public interface StatoProspettoMapper extends BaseMapperInterface<StatoProspetto, ProTStatoProspetto> {

	@Override
	// @Mapping(source = "entityName", target = "modelName")
	StatoProspetto toModel(ProTStatoProspetto entity);

	@Override
	@IterableMapping(elementTargetType = StatoProspetto.class)
	List<StatoProspetto> toModels(Collection<ProTStatoProspetto> entities);

	@Override
	@IterableMapping(elementTargetType = ProTStatoProspetto.class)
	List<ProTStatoProspetto> toEntities(Collection<StatoProspetto> models);
	
	@Override
	ProTStatoProspetto toEntityExisting(StatoProspetto model, @MappingTarget ProTStatoProspetto entity);

}
