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

import it.csi.prodis.prodissrv.ejb.entity.ProTRegione;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Regione;
import it.csi.prodis.prodissrv.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Regione and ProTRegione
 */
@Mapper
public interface RegioneMapper extends BaseMapperInterface<Regione, ProTRegione> {

	@Override
	// @Mapping(source = "entityName", target = "modelName")
	Regione toModel(ProTRegione entity);

	@Override
	@IterableMapping(elementTargetType = Regione.class)
	List<Regione> toModels(Collection<ProTRegione> entities);

	@Override
	@IterableMapping(elementTargetType = ProTRegione.class)
	List<ProTRegione> toEntities(Collection<Regione> models);

}
