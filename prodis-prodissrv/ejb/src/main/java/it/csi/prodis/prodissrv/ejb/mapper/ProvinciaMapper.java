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

import it.csi.prodis.prodissrv.ejb.entity.ProTProvincia;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Provincia;
import it.csi.prodis.prodissrv.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Provincia and ProTProvincia
 */
@Mapper
public interface ProvinciaMapper extends BaseMapperInterface<Provincia, ProTProvincia> {

	@Override
	// @Mapping(source = "entityName", target = "modelName")
	Provincia toModel(ProTProvincia entity);

	@Override
	@IterableMapping(elementTargetType = Provincia.class)
	List<Provincia> toModels(Collection<ProTProvincia> entities);

	@Override
	@IterableMapping(elementTargetType = ProTProvincia.class)
	List<ProTProvincia> toEntities(Collection<Provincia> models);

}
