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

import it.csi.prodis.prodissrv.ejb.entity.ProDAssPubbliche;
import it.csi.prodis.prodissrv.lib.dto.prospetto.AssPubbliche;
import it.csi.prodis.prodissrv.lib.mapper.BaseMapperInterface;

/**
 * Mapper between AssPubbliche and ProDAssPubbliche
 */
@Mapper
public interface AssPubblicheMapper extends BaseMapperInterface<AssPubbliche, ProDAssPubbliche> {

	@Override
	@Mapping(source = "proDProspetto", target = "prospetto")
	@Mapping(source = "proTRegione", target = "regione")
	AssPubbliche toModel(ProDAssPubbliche entity);

	@Override
	@IterableMapping(elementTargetType = AssPubbliche.class)
	List<AssPubbliche> toModels(Collection<ProDAssPubbliche> entities);

	@Override
	@IterableMapping(elementTargetType = ProDAssPubbliche.class)
	List<ProDAssPubbliche> toEntities(Collection<AssPubbliche> models);

}
