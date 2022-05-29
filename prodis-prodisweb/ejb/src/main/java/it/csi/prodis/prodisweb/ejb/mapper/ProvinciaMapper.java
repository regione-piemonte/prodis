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

import it.csi.prodis.prodisweb.ejb.entity.ProTProvincia;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Provincia;
import it.csi.prodis.prodisweb.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Provincia and ProTProvincia
 */
@Mapper
public interface ProvinciaMapper extends BaseMapperInterface<Provincia, ProTProvincia> {

	@Override
	// @Mapping(source = "entityName", target = "modelName")
	@Mapping(source = "proTRegione", target = "regione")
	Provincia toModel(ProTProvincia entity);

	@Override
	@IterableMapping(elementTargetType = Provincia.class)
	List<Provincia> toModels(Collection<ProTProvincia> entities);

	@Override
	@IterableMapping(elementTargetType = ProTProvincia.class)
	List<ProTProvincia> toEntities(Collection<Provincia> models);

}
