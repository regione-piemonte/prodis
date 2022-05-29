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

import it.csi.prodis.prodisweb.ejb.entity.ProTStatoConcessione;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.StatoConcessione;
import it.csi.prodis.prodisweb.lib.mapper.BaseMapperInterface;

/**
 * Mapper between StatoConcessione and ProTStatoConcessione
 */
@Mapper
public interface StatoConcessioneMapper extends BaseMapperInterface<StatoConcessione, ProTStatoConcessione> {

	@Override
	// @Mapping(source = "entityName", target = "modelName")
	StatoConcessione toModel(ProTStatoConcessione entity);

	@Override
	@IterableMapping(elementTargetType = StatoConcessione.class)
	List<StatoConcessione> toModels(Collection<ProTStatoConcessione> entities);

	@Override
	@IterableMapping(elementTargetType = ProTStatoConcessione.class)
	List<ProTStatoConcessione> toEntities(Collection<StatoConcessione> models);

}
