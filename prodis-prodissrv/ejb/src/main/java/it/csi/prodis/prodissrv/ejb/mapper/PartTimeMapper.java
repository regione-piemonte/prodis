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

import it.csi.prodis.prodissrv.ejb.entity.ProDPartTime;
import it.csi.prodis.prodissrv.lib.dto.prospetto.PartTime;
import it.csi.prodis.prodissrv.lib.mapper.BaseMapperInterface;

/**
 * Mapper between PartTime and ProDPartTime
 */
@Mapper(uses = {TipoRipropPtMapper.class})
public interface PartTimeMapper extends BaseMapperInterface<PartTime, ProDPartTime> {

	@Override
	@Mapping(source = "proTTipoRipropPt", target = "tipoRipropPt")
	//@Mapping(source = "proDDatiProvinciali", target = "datiProvinciali")
	PartTime toModel(ProDPartTime entity);

	@Override
	@IterableMapping(elementTargetType = PartTime.class)
	List<PartTime> toModels(Collection<ProDPartTime> entities);

	@Override
	@IterableMapping(elementTargetType = ProDPartTime.class)
	List<ProDPartTime> toEntities(Collection<PartTime> models);

}
