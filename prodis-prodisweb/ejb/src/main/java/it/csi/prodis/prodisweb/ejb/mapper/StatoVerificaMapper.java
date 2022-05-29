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

import it.csi.prodis.prodisweb.ejb.entity.ProTStatoVerifica;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.StatoVerifica;
import it.csi.prodis.prodisweb.lib.mapper.BaseMapperInterface;

/**
 * Mapper between StatoVerifica and ProTStatoVerifica
 */
@Mapper
public interface StatoVerificaMapper extends BaseMapperInterface<StatoVerifica, ProTStatoVerifica> {

	@Override
	// @Mapping(source = "entityName", target = "modelName")
	StatoVerifica toModel(ProTStatoVerifica entity);

	@Override
	@IterableMapping(elementTargetType = StatoVerifica.class)
	List<StatoVerifica> toModels(Collection<ProTStatoVerifica> entities);

	@Override
	@IterableMapping(elementTargetType = ProTStatoVerifica.class)
	List<ProTStatoVerifica> toEntities(Collection<StatoVerifica> models);

}
