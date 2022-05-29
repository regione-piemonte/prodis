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

import it.csi.prodis.prodisweb.ejb.entity.ProDPartTime;
import it.csi.prodis.prodisweb.lib.dto.prospetto.PartTime;
import it.csi.prodis.prodisweb.lib.mapper.BaseMapperInterface;

/**
 * Mapper between PartTime and ProDPartTime
 */
@Mapper(uses = {TipoRipropPtMapper.class, DatiProvincialiMapper.class})
public interface PartTimeMapper extends BaseMapperInterface<PartTime, ProDPartTime> {

	@Override
	@Mapping(source = "proTTipoRipropPt", target = "tipoRipropPt")
	@Mapping(source = "idProspettoProv", target = "idProspettoProv")
	PartTime toModel(ProDPartTime entity);

	@Override
	@IterableMapping(elementTargetType = PartTime.class)
	List<PartTime> toModels(Collection<ProDPartTime> entities);

	@Override
	@IterableMapping(elementTargetType = ProDPartTime.class)
	List<ProDPartTime> toEntities(Collection<PartTime> models);
	
	@Override
	ProDPartTime toEntityExisting(PartTime model, @MappingTarget ProDPartTime entity);

}
