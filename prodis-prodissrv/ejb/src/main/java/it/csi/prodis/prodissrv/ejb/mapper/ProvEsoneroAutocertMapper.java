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

import it.csi.prodis.prodissrv.ejb.entity.ProDProvEsoneroAutocert;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProvEsoneroAutocert;
import it.csi.prodis.prodissrv.lib.mapper.BaseMapperInterface;

/**
 * Mapper between ProvEsoneroAutocert and ProDProvEsoneroAutocert
 */
@Mapper
public interface ProvEsoneroAutocertMapper extends BaseMapperInterface<ProvEsoneroAutocert, ProDProvEsoneroAutocert> {

	@Override
	// @Mapping(source = "entityName", target = "modelName")
	ProvEsoneroAutocert toModel(ProDProvEsoneroAutocert entity);

	@Override
	@IterableMapping(elementTargetType = ProvEsoneroAutocert.class)
	List<ProvEsoneroAutocert> toModels(Collection<ProDProvEsoneroAutocert> entities);

	@Override
	@IterableMapping(elementTargetType = ProDProvEsoneroAutocert.class)
	List<ProDProvEsoneroAutocert> toEntities(Collection<ProvEsoneroAutocert> models);

}
