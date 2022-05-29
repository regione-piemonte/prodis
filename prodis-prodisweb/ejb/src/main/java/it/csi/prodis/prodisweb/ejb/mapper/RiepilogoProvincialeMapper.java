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

import it.csi.prodis.prodisweb.ejb.entity.ProDRiepilogoProvinciale;
import it.csi.prodis.prodisweb.lib.dto.prospetto.RiepilogoProvinciale;
import it.csi.prodis.prodisweb.lib.mapper.BaseMapperInterface;

/**
 * Mapper between RiepilogoProvinciale and ProDRiepilogoProvinciale
 */
@Mapper
public interface RiepilogoProvincialeMapper extends BaseMapperInterface<RiepilogoProvinciale, ProDRiepilogoProvinciale> {

	@Override
	// @Mapping(source = "entityName", target = "modelName")
	RiepilogoProvinciale toModel(ProDRiepilogoProvinciale entity);

	@Override
	@IterableMapping(elementTargetType = RiepilogoProvinciale.class)
	List<RiepilogoProvinciale> toModels(Collection<ProDRiepilogoProvinciale> entities);

	@Override
	@IterableMapping(elementTargetType = ProDRiepilogoProvinciale.class)
	List<ProDRiepilogoProvinciale> toEntities(Collection<RiepilogoProvinciale> models);

}
