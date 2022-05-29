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
import org.mapstruct.Mapping;

import it.csi.prodis.prodisweb.ejb.entity.ProDProvSospensione;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProvSospensione;
import it.csi.prodis.prodisweb.lib.mapper.BaseMapperInterface;

/**
 * Mapper between ProvSospensione and ProDProvSospensione
 */
@Mapper(uses = { CausaSospensioneMapper.class })
public interface ProvSospensioneMapper extends BaseMapperInterface<ProvSospensione, ProDProvSospensione> {

	@Override
	@Mapping(source = "proTCausaSospensione", target = "causaSospensione")
	@Mapping(source = "proTStatoConcessione", target = "statoConcessione")
	ProvSospensione toModel(ProDProvSospensione entity);

	@Override
	@IterableMapping(elementTargetType = ProvSospensione.class)
	List<ProvSospensione> toModels(Collection<ProDProvSospensione> entities);

	@Override
	@IterableMapping(elementTargetType = ProDProvSospensione.class)
	List<ProDProvSospensione> toEntities(Collection<ProvSospensione> models);

}
