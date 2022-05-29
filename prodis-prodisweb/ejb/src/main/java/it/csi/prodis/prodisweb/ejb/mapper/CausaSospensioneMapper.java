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

import it.csi.prodis.prodisweb.ejb.entity.ProTCausaSospensione;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.CausaSospensione;
import it.csi.prodis.prodisweb.lib.mapper.BaseMapperInterface;

/**
 * Mapper between CausaSospensione and ProTCausaSospensione
 */
@Mapper
public interface CausaSospensioneMapper extends BaseMapperInterface<CausaSospensione, ProTCausaSospensione> {

	@Override
	// @Mapping(source = "entityName", target = "modelName")
	CausaSospensione toModel(ProTCausaSospensione entity);

	@Override
	@IterableMapping(elementTargetType = CausaSospensione.class)
	List<CausaSospensione> toModels(Collection<ProTCausaSospensione> entities);

	@Override
	@IterableMapping(elementTargetType = ProTCausaSospensione.class)
	List<ProTCausaSospensione> toEntities(Collection<CausaSospensione> models);

}
