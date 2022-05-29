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

import it.csi.prodis.prodisweb.ejb.entity.ProDProvGradualita;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProvGradualita;
import it.csi.prodis.prodisweb.lib.mapper.BaseMapperInterface;

/**
 * Mapper between ProvGradualita and ProDProvGradualita
 */
@Mapper
public interface ProvGradualitaMapper extends BaseMapperInterface<ProvGradualita, ProDProvGradualita> {

	@Override
	ProvGradualita toModel(ProDProvGradualita entity);

	@Override
	@IterableMapping(elementTargetType = ProvGradualita.class)
	List<ProvGradualita> toModels(Collection<ProDProvGradualita> entities);

	@Override
	@IterableMapping(elementTargetType = ProDProvGradualita.class)
	List<ProDProvGradualita> toEntities(Collection<ProvGradualita> models);

}
