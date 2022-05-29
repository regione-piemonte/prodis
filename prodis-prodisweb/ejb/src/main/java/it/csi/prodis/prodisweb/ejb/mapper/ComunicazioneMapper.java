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
import org.mapstruct.MappingTarget;

import it.csi.prodis.prodisweb.ejb.entity.ProTComunicazione;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Comunicazione;
import it.csi.prodis.prodisweb.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Comunicazione and ProTComunicazione
 */
@Mapper
public interface ComunicazioneMapper extends BaseMapperInterface<Comunicazione, ProTComunicazione> {

	@Override
	// @Mapping(source = "entityName", target = "modelName")
	Comunicazione toModel(ProTComunicazione entity);

	@Override
	@IterableMapping(elementTargetType = Comunicazione.class)
	List<Comunicazione> toModels(Collection<ProTComunicazione> entities);

	@Override
	@IterableMapping(elementTargetType = ProTComunicazione.class)
	List<ProTComunicazione> toEntities(Collection<Comunicazione> models);
	
	@Override
	ProTComunicazione toEntityExisting(Comunicazione model, @MappingTarget ProTComunicazione entity);

}
