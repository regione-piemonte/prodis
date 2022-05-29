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

import it.csi.prodis.prodissrv.ejb.entity.ProTAtecofin;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Atecofin;
import it.csi.prodis.prodissrv.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Atecofin and ProTAtecofin
 */
@Mapper
public interface AtecofinMapper extends BaseMapperInterface<Atecofin, ProTAtecofin> {

	@Override
	// @Mapping(source = "entityName", target = "modelName")
	Atecofin toModel(ProTAtecofin entity);

	@Override
	@IterableMapping(elementTargetType = Atecofin.class)
	List<Atecofin> toModels(Collection<ProTAtecofin> entities);

	@Override
	@IterableMapping(elementTargetType = ProTAtecofin.class)
	List<ProTAtecofin> toEntities(Collection<Atecofin> models);

}
