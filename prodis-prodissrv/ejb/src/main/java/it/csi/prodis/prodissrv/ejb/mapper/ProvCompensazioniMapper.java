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
import org.mapstruct.Mapping;

import it.csi.prodis.prodissrv.ejb.entity.ProDProvCompensazioni;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProvCompensazioni;
import it.csi.prodis.prodissrv.lib.mapper.BaseMapperInterface;

/**
 * Mapper between ProvCompensazioni and ProDProvCompensazioni
 */
@Mapper(uses = { ProvinciaMapper.class, StatoConcessioneMapper.class })
public interface ProvCompensazioniMapper extends BaseMapperInterface<ProvCompensazioni, ProDProvCompensazioni> {

	@Override
	// @Mapping(source = "entityName", target = "modelName")
	@Mapping(source = "proTProvincia", target = "provincia")
	@Mapping(source = "proTStatoConcessione", target = "statoConcessione")
	ProvCompensazioni toModel(ProDProvCompensazioni entity);

	@Override
	@IterableMapping(elementTargetType = ProvCompensazioni.class)
	List<ProvCompensazioni> toModels(Collection<ProDProvCompensazioni> entities);

	@Override
	@IterableMapping(elementTargetType = ProDProvCompensazioni.class)
	List<ProDProvCompensazioni> toEntities(Collection<ProvCompensazioni> models);

}
