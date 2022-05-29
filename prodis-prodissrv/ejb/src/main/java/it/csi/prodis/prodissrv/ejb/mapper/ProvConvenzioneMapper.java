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

import it.csi.prodis.prodissrv.ejb.entity.ProDProvConvenzione;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProvConvenzione;
import it.csi.prodis.prodissrv.lib.mapper.BaseMapperInterface;

/**
 * Mapper between ProvConvenzione and ProDProvConvenzione
 */
@Mapper(uses = { StatoConcessioneMapper.class, AssunzioneProtettaMapper.class} )
public interface ProvConvenzioneMapper extends BaseMapperInterface<ProvConvenzione, ProDProvConvenzione> {

	@Override
	@Mapping(source = "proTStatoConcessione", target = "statoConcessione")
	@Mapping(source = "proTAssunzioneProtetta", target = "assunzioneProtetta")
	ProvConvenzione toModel(ProDProvConvenzione entity);

	@Override
	@IterableMapping(elementTargetType = ProvConvenzione.class)
	List<ProvConvenzione> toModels(Collection<ProDProvConvenzione> entities);

	@Override
	@IterableMapping(elementTargetType = ProDProvConvenzione.class)
	List<ProDProvConvenzione> toEntities(Collection<ProvConvenzione> models);

}
