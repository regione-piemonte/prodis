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

import it.csi.prodis.prodissrv.ejb.entity.ProTComune;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Comune;
import it.csi.prodis.prodissrv.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Comune and ProTComune
 */
@Mapper
public interface ComuneMapper extends BaseMapperInterface<Comune, ProTComune> {

	@Override
	// @Mapping(source = "entityName", target = "modelName")
	Comune toModel(ProTComune entity);

	@Override
	@IterableMapping(elementTargetType = Comune.class)
	List<Comune> toModels(Collection<ProTComune> entities);

	@Override
	@IterableMapping(elementTargetType = ProTComune.class)
	List<ProTComune> toEntities(Collection<Comune> models);

}
