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

import it.csi.prodis.prodisweb.ejb.entity.ProDLavoratoriSilp;
import it.csi.prodis.prodisweb.lib.dto.prospetto.LavoratoriSilp;
import it.csi.prodis.prodisweb.lib.mapper.BaseMapperInterface;

/**
 * Mapper between LavoratoriSilp and ProDLavoratoriSilp
 */
@Mapper
public interface LavoratoriSilpMapper extends BaseMapperInterface<LavoratoriSilp, ProDLavoratoriSilp> {

	@Override
	// @Mapping(source = "entityName", target = "modelName")
	LavoratoriSilp toModel(ProDLavoratoriSilp entity);

	@Override
	@IterableMapping(elementTargetType = LavoratoriSilp.class)
	List<LavoratoriSilp> toModels(Collection<ProDLavoratoriSilp> entities);

	@Override
	@IterableMapping(elementTargetType = ProDLavoratoriSilp.class)
	List<ProDLavoratoriSilp> toEntities(Collection<LavoratoriSilp> models);

}
