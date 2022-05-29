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

import it.csi.prodis.prodisweb.ejb.entity.ProDProspettoGradualita;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProspettoGradualita;
import it.csi.prodis.prodisweb.lib.mapper.BaseMapperInterface;

/**
 * Mapper between ProspettoGradualita and ProDProspettoGradualita
 */
@Mapper
public interface ProspettoGradualitaMapper extends BaseMapperInterface<ProspettoGradualita, ProDProspettoGradualita> {

	@Override
	// @Mapping(source = "entityName", target = "modelName")
	ProspettoGradualita toModel(ProDProspettoGradualita entity);

	@Override
	@IterableMapping(elementTargetType = ProspettoGradualita.class)
	List<ProspettoGradualita> toModels(Collection<ProDProspettoGradualita> entities);

	@Override
	@IterableMapping(elementTargetType = ProDProspettoGradualita.class)
	List<ProDProspettoGradualita> toEntities(Collection<ProspettoGradualita> models);
	
	@Override
	ProDProspettoGradualita toEntityExisting(ProspettoGradualita model, @MappingTarget ProDProspettoGradualita entity);

}
