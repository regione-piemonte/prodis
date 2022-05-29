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
import org.mapstruct.MappingTarget;

import it.csi.prodis.prodisweb.ejb.entity.ProDCategorieEscluse;
import it.csi.prodis.prodisweb.lib.dto.prospetto.CategorieEscluse;
import it.csi.prodis.prodisweb.lib.mapper.BaseMapperInterface;

/**
 * Mapper between CategorieEscluse and ProDCategorieEscluse
 */
@Mapper(uses = {CategoriaEscluseMapper.class, DatiProvincialiMapper.class})
public interface CategorieEscluseMapper extends BaseMapperInterface<CategorieEscluse, ProDCategorieEscluse> {

	@Override
	@Mapping(source = "proTCategoriaEscluse", target = "categoriaEscluse")
	@Mapping(source = "idProspettoProv", target = "idProspettoProv")
	CategorieEscluse toModel(ProDCategorieEscluse entity);

	@Override
	@IterableMapping(elementTargetType = CategorieEscluse.class)
	List<CategorieEscluse> toModels(Collection<ProDCategorieEscluse> entities);

	@Override
	@IterableMapping(elementTargetType = ProDCategorieEscluse.class)
	List<ProDCategorieEscluse> toEntities(Collection<CategorieEscluse> models);
	
	@Override
	ProDCategorieEscluse toEntityExisting(CategorieEscluse model, @MappingTarget ProDCategorieEscluse entity);

}
