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

import it.csi.prodis.prodisweb.ejb.entity.ProDLavoratoriInForza;
import it.csi.prodis.prodisweb.lib.dto.prospetto.LavoratoriInForza;
import it.csi.prodis.prodisweb.lib.mapper.BaseMapperInterface;

/**
 * Mapper between LavoratoriInForza and ProDLavoratoriInForza
 */
@Mapper(uses = { ComuneMapper.class, ContrattiMapper.class, Istat2001livello5Mapper.class})
public interface LavoratoriInForzaMapper extends BaseMapperInterface<LavoratoriInForza, ProDLavoratoriInForza> {

	@Override
	@Mapping(source = "proTComune", target = "comune")
	@Mapping(source = "proTContratti", target = "contratti")
	@Mapping(source = "proTIstat2001livello5", target = "istat2001livello5")
	@Mapping(source = "proTAssunzioneProtetta", target = "assunzioneProtetta")
	@Mapping(source = "proTStatiEsteri", target = "statiEsteri")
	LavoratoriInForza toModel(ProDLavoratoriInForza entity);

	@Override
	@IterableMapping(elementTargetType = LavoratoriInForza.class)
	List<LavoratoriInForza> toModels(Collection<ProDLavoratoriInForza> entities);

	@Override
	@IterableMapping(elementTargetType = ProDLavoratoriInForza.class)
	List<ProDLavoratoriInForza> toEntities(Collection<LavoratoriInForza> models);

}
