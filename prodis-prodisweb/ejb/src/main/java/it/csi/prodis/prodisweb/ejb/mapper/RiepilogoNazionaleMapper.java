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

import it.csi.prodis.prodisweb.ejb.entity.ProDRiepilogoNazionale;
import it.csi.prodis.prodisweb.lib.dto.prospetto.RiepilogoNazionale;
import it.csi.prodis.prodisweb.lib.mapper.BaseMapperInterface;

/**
 * Mapper between RiepilogoNazionale and ProDRiepilogoNazionale
 */
@Mapper
public interface RiepilogoNazionaleMapper extends BaseMapperInterface<RiepilogoNazionale, ProDRiepilogoNazionale> {

	@Override
	// @Mapping(source = "entityName", target = "modelName")
	RiepilogoNazionale toModel(ProDRiepilogoNazionale entity);

	@Override
	@IterableMapping(elementTargetType = RiepilogoNazionale.class)
	List<RiepilogoNazionale> toModels(Collection<ProDRiepilogoNazionale> entities);

	@Override
	@IterableMapping(elementTargetType = ProDRiepilogoNazionale.class)
	List<ProDRiepilogoNazionale> toEntities(Collection<RiepilogoNazionale> models);

}
