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

import it.csi.prodis.prodisweb.ejb.entity.ProDLavoratoriSilpPK;
import it.csi.prodis.prodisweb.lib.dto.prospetto.LavoratoriSilpPK;
import it.csi.prodis.prodisweb.lib.mapper.BaseMapperInterface;

/**
 * Mapper between LavoratoriSilpPK and ProDLavoratoriSilpPK
 */
@Mapper
public interface LavoratoriSilpPKMapper extends BaseMapperInterface<LavoratoriSilpPK, ProDLavoratoriSilpPK> {

	@Override
	// @Mapping(source = "entityName", target = "modelName")
	LavoratoriSilpPK toModel(ProDLavoratoriSilpPK entity);

	@Override
	@IterableMapping(elementTargetType = LavoratoriSilpPK.class)
	List<LavoratoriSilpPK> toModels(Collection<ProDLavoratoriSilpPK> entities);

	@Override
	@IterableMapping(elementTargetType = ProDLavoratoriSilpPK.class)
	List<ProDLavoratoriSilpPK> toEntities(Collection<LavoratoriSilpPK> models);

}
