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

import it.csi.prodis.prodissrv.ejb.entity.ProDProvEsonero;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProvEsonero;
import it.csi.prodis.prodissrv.lib.mapper.BaseMapperInterface;

/**
 * Mapper between ProvEsonero and ProDProvEsonero
 */
@Mapper(uses = { StatoConcessioneMapper.class })
public interface ProvEsoneroMapper extends BaseMapperInterface<ProvEsonero, ProDProvEsonero> {

	@Override
	@Mapping(source = "proTStatoConcessione", target = "statoConcessione")
	ProvEsonero toModel(ProDProvEsonero entity);

	@Override
	@IterableMapping(elementTargetType = ProvEsonero.class)
	List<ProvEsonero> toModels(Collection<ProDProvEsonero> entities);

	@Override
	@IterableMapping(elementTargetType = ProDProvEsonero.class)
	List<ProDProvEsonero> toEntities(Collection<ProvEsonero> models);

}
