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

import it.csi.prodis.prodissrv.ejb.entity.ProDProspettoProvSede;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProspettoProvSede;
import it.csi.prodis.prodissrv.lib.mapper.BaseMapperInterface;

/**
 * Mapper between ProspettoProvSede and ProDProspettoProvSede
 */
@Mapper(uses = {ComuneMapper.class})
public interface ProspettoProvSedeMapper extends BaseMapperInterface<ProspettoProvSede, ProDProspettoProvSede> {

	@Override
	@Mapping(source = "proTComune", target = "comune")
	ProspettoProvSede toModel(ProDProspettoProvSede entity);

	@Override
	@IterableMapping(elementTargetType = ProspettoProvSede.class)
	List<ProspettoProvSede> toModels(Collection<ProDProspettoProvSede> entities);

	@Override
	@IterableMapping(elementTargetType = ProDProspettoProvSede.class)
	List<ProDProspettoProvSede> toEntities(Collection<ProspettoProvSede> models);

}
