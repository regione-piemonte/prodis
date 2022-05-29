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

import it.csi.prodis.prodisweb.ejb.entity.ProDProvIntermittenti;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProvIntermittenti;
import it.csi.prodis.prodisweb.lib.mapper.BaseMapperInterface;

/**
 * Mapper between ProvIntermittenti and ProDProvIntermittenti
 */
@Mapper(uses = {TipoRipropPtMapper.class, DatiProvincialiMapper.class})
public interface ProvIntermittentiMapper extends BaseMapperInterface<ProvIntermittenti, ProDProvIntermittenti> {

	@Override
	@IterableMapping(elementTargetType = ProvIntermittenti.class)
	List<ProvIntermittenti> toModels(Collection<ProDProvIntermittenti> entities);

	@Override
	@IterableMapping(elementTargetType = ProDProvIntermittenti.class)
	List<ProDProvIntermittenti> toEntities(Collection<ProvIntermittenti> models);
	
	@Override
	ProDProvIntermittenti toEntityExisting(ProvIntermittenti model, @MappingTarget ProDProvIntermittenti entity);

}
