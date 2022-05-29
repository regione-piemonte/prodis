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

import it.csi.prodis.prodisweb.ejb.entity.ProTAssunzioneProtetta;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.AssunzioneProtetta;
import it.csi.prodis.prodisweb.lib.mapper.BaseMapperInterface;

/**
 * Mapper between AssunzioneProtetta and ProTAssunzioneProtetta
 */
@Mapper
public interface AssunzioneProtettaMapper extends BaseMapperInterface<AssunzioneProtetta, ProTAssunzioneProtetta> {

	@Override
	// @Mapping(source = "entityName", target = "modelName")
	AssunzioneProtetta toModel(ProTAssunzioneProtetta entity);

	@Override
	@IterableMapping(elementTargetType = AssunzioneProtetta.class)
	List<AssunzioneProtetta> toModels(Collection<ProTAssunzioneProtetta> entities);

	@Override
	@IterableMapping(elementTargetType = ProTAssunzioneProtetta.class)
	List<ProTAssunzioneProtetta> toEntities(Collection<AssunzioneProtetta> models);

}
