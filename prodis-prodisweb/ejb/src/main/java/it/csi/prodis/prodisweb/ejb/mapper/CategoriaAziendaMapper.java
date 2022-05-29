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

import it.csi.prodis.prodisweb.ejb.entity.ProTCategoriaAzienda;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.CategoriaAzienda;
import it.csi.prodis.prodisweb.lib.mapper.BaseMapperInterface;

/**
 * Mapper between CategoriaAzienda and ProTCategoriaAzienda
 */
@Mapper
public interface CategoriaAziendaMapper extends BaseMapperInterface<CategoriaAzienda, ProTCategoriaAzienda> {

	@Override
	// @Mapping(source = "entityName", target = "modelName")
	CategoriaAzienda toModel(ProTCategoriaAzienda entity);

	@Override
	@IterableMapping(elementTargetType = CategoriaAzienda.class)
	List<CategoriaAzienda> toModels(Collection<ProTCategoriaAzienda> entities);

	@Override
	@IterableMapping(elementTargetType = ProTCategoriaAzienda.class)
	List<ProTCategoriaAzienda> toEntities(Collection<CategoriaAzienda> models);

}
