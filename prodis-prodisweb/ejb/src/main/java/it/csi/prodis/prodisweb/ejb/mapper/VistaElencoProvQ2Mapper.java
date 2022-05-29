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

import it.csi.prodis.prodisweb.ejb.entity.ProDVistaElencoProvQ2;
import it.csi.prodis.prodisweb.lib.dto.prospetto.VistaElencoProvQ2;
import it.csi.prodis.prodisweb.lib.mapper.BaseMapperInterface;

/**
 * Mapper between VistaElencoProvQ2 and ProDVistaElencoProvQ2
 */
@Mapper
public interface VistaElencoProvQ2Mapper extends BaseMapperInterface<VistaElencoProvQ2, ProDVistaElencoProvQ2> {

	@Override
	// @Mapping(source = "entityName", target = "modelName")
	VistaElencoProvQ2 toModel(ProDVistaElencoProvQ2 entity);

	@Override
	@IterableMapping(elementTargetType = VistaElencoProvQ2.class)
	List<VistaElencoProvQ2> toModels(Collection<ProDVistaElencoProvQ2> entities);

	@Override
	@IterableMapping(elementTargetType = ProDVistaElencoProvQ2.class)
	List<ProDVistaElencoProvQ2> toEntities(Collection<VistaElencoProvQ2> models);

}
