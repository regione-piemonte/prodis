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

import it.csi.prodis.prodissrv.ejb.entity.ProTSoggetti;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Soggetti;
import it.csi.prodis.prodissrv.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Soggetti and ProTSoggetti
 */
@Mapper
public interface SoggettiMapper extends BaseMapperInterface<Soggetti, ProTSoggetti> {

	@Override
	// @Mapping(source = "entityName", target = "modelName")
	Soggetti toModel(ProTSoggetti entity);

	@Override
	@IterableMapping(elementTargetType = Soggetti.class)
	List<Soggetti> toModels(Collection<ProTSoggetti> entities);

	@Override
	@IterableMapping(elementTargetType = ProTSoggetti.class)
	List<ProTSoggetti> toEntities(Collection<Soggetti> models);

}
