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

import it.csi.prodis.prodisweb.ejb.entity.ProTDichiarante;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Dichiarante;
import it.csi.prodis.prodisweb.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Dichiarante and ProTDichiarante
 */
@Mapper
public interface DichiaranteMapper extends BaseMapperInterface<Dichiarante, ProTDichiarante> {

	@Override
	// @Mapping(source = "entityName", target = "modelName")
	Dichiarante toModel(ProTDichiarante entity);

	@Override
	@IterableMapping(elementTargetType = Dichiarante.class)
	List<Dichiarante> toModels(Collection<ProTDichiarante> entities);

	@Override
	@IterableMapping(elementTargetType = ProTDichiarante.class)
	List<ProTDichiarante> toEntities(Collection<Dichiarante> models);

}
