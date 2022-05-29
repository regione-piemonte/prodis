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
import org.mapstruct.MappingTarget;


import it.csi.prodis.prodisweb.ejb.entity.ProTCategoriaEscluse;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.CategoriaEscluse;
import it.csi.prodis.prodisweb.lib.mapper.BaseMapperInterface;

/**
 * Mapper between CategoriaEscluse and ProTCategoriaEscluse
 */
@Mapper
public interface CategoriaEscluseMapper extends BaseMapperInterface<CategoriaEscluse, ProTCategoriaEscluse> {

	@Override
	// @Mapping(source = "entityName", target = "modelName")
	CategoriaEscluse toModel(ProTCategoriaEscluse entity);

	@Override
	@IterableMapping(elementTargetType = CategoriaEscluse.class)
	List<CategoriaEscluse> toModels(Collection<ProTCategoriaEscluse> entities);

	@Override
	@IterableMapping(elementTargetType = ProTCategoriaEscluse.class)
	List<ProTCategoriaEscluse> toEntities(Collection<CategoriaEscluse> models);
	
	@Override
	ProTCategoriaEscluse toEntityExisting(CategoriaEscluse model, @MappingTarget ProTCategoriaEscluse entity);

}
