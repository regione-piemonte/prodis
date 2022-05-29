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

import it.csi.prodis.prodissrv.ejb.entity.ProTTipoRipropPt;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.TipoRipropPt;
import it.csi.prodis.prodissrv.lib.mapper.BaseMapperInterface;

/**
 * Mapper between TipoRipropPt and ProTTipoRipropPt
 */
@Mapper
public interface TipoRipropPtMapper extends BaseMapperInterface<TipoRipropPt, ProTTipoRipropPt> {

	@Override
	// @Mapping(source = "entityName", target = "modelName")
	TipoRipropPt toModel(ProTTipoRipropPt entity);

	@Override
	@IterableMapping(elementTargetType = TipoRipropPt.class)
	List<TipoRipropPt> toModels(Collection<ProTTipoRipropPt> entities);

	@Override
	@IterableMapping(elementTargetType = ProTTipoRipropPt.class)
	List<ProTTipoRipropPt> toEntities(Collection<TipoRipropPt> models);

}
