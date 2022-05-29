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

import it.csi.prodis.prodisweb.ejb.entity.ProTIstat2001livello5;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Istat2001livello5;
import it.csi.prodis.prodisweb.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Istat2001livello5 and ProTIstat2001livello5
 */
@Mapper
public interface Istat2001livello5Mapper extends BaseMapperInterface<Istat2001livello5, ProTIstat2001livello5> {

	@Override
	// @Mapping(source = "entityName", target = "modelName")
	Istat2001livello5 toModel(ProTIstat2001livello5 entity);

	@Override
	@IterableMapping(elementTargetType = Istat2001livello5.class)
	List<Istat2001livello5> toModels(Collection<ProTIstat2001livello5> entities);

	@Override
	@IterableMapping(elementTargetType = ProTIstat2001livello5.class)
	List<ProTIstat2001livello5> toEntities(Collection<Istat2001livello5> models);
	
	@Override
	ProTIstat2001livello5 toEntityExisting(Istat2001livello5 model, @MappingTarget ProTIstat2001livello5 entity);

}
