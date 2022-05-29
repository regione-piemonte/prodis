/*-
 * ========================LICENSE_START=================================
 * PRODIS Servizi - EJB submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodissrv.ejb.mapper;

import java.util.Collection;
import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import it.csi.prodis.prodissrv.ejb.entity.ProTStatiEsteri;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.StatiEsteri;
import it.csi.prodis.prodissrv.lib.mapper.BaseMapperInterface;

/**
 * Mapper between StatiEsteri and ProTStatiEsteri
 */
@Mapper
public interface StatiEsteriMapper extends BaseMapperInterface<StatiEsteri, ProTStatiEsteri> {

	@Override
	// @Mapping(source = "entityName", target = "modelName")
	StatiEsteri toModel(ProTStatiEsteri entity);

	@Override
	@IterableMapping(elementTargetType = StatiEsteri.class)
	List<StatiEsteri> toModels(Collection<ProTStatiEsteri> entities);

	@Override
	@IterableMapping(elementTargetType = ProTStatiEsteri.class)
	List<ProTStatiEsteri> toEntities(Collection<StatiEsteri> models);

}
