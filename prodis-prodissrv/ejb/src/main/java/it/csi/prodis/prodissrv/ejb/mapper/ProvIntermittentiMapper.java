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

import it.csi.prodis.prodissrv.ejb.entity.ProDProvIntermittenti;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProvIntermittenti;
import it.csi.prodis.prodissrv.lib.mapper.BaseMapperInterface;

/**
 * Mapper between ProvIntermittenti and ProDProvIntermittenti
 */
@Mapper(uses = {TipoRipropPtMapper.class})
public interface ProvIntermittentiMapper extends BaseMapperInterface<ProvIntermittenti, ProDProvIntermittenti> {

	@Override
	//@Mapping(source = "proDDatiProvinciali", target = "datiProvinciali")
	//@Mapping(source = "entityName", target = "modelName")
	ProvIntermittenti toModel(ProDProvIntermittenti entity);

	@Override
	@IterableMapping(elementTargetType = ProvIntermittenti.class)
	List<ProvIntermittenti> toModels(Collection<ProDProvIntermittenti> entities);

	@Override
	@IterableMapping(elementTargetType = ProDProvIntermittenti.class)
	List<ProDProvIntermittenti> toEntities(Collection<ProvIntermittenti> models);

}
