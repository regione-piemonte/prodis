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

import it.csi.prodis.prodisweb.ejb.entity.ProDParametri;
import it.csi.prodis.prodisweb.lib.dto.prospetto.Parametri;
import it.csi.prodis.prodisweb.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Parametri and ProDParametri
 */
@Mapper
public interface ParametriMapper extends BaseMapperInterface<Parametri, ProDParametri> {

	@Override
	// @Mapping(source = "entityName", target = "modelName")
	Parametri toModel(ProDParametri entity);

	@Override
	@IterableMapping(elementTargetType = Parametri.class)
	List<Parametri> toModels(Collection<ProDParametri> entities);

	@Override
	@IterableMapping(elementTargetType = ProDParametri.class)
	List<ProDParametri> toEntities(Collection<Parametri> models);

}
