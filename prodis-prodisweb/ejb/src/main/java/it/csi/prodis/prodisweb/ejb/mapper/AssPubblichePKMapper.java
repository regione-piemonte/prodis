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

import it.csi.prodis.prodisweb.ejb.entity.ProDAssPubblichePK;
import it.csi.prodis.prodisweb.lib.dto.prospetto.AssPubblichePK;
import it.csi.prodis.prodisweb.lib.mapper.BaseMapperInterface;

/**
 * Mapper between AssPubblichePK and ProDAssPubblichePK
 */
@Mapper
public interface AssPubblichePKMapper extends BaseMapperInterface<AssPubblichePK, ProDAssPubblichePK> {

	@Override
	// @Mapping(source = "entityName", target = "modelName")
	AssPubblichePK toModel(ProDAssPubblichePK entity);

	@Override
	@IterableMapping(elementTargetType = AssPubblichePK.class)
	List<AssPubblichePK> toModels(Collection<ProDAssPubblichePK> entities);

	@Override
	@IterableMapping(elementTargetType = ProDAssPubblichePK.class)
	List<ProDAssPubblichePK> toEntities(Collection<AssPubblichePK> models);

}
