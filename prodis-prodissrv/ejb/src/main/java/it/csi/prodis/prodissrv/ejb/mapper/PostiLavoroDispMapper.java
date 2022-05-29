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

import it.csi.prodis.prodissrv.ejb.entity.ProDPostiLavoroDisp;
import it.csi.prodis.prodissrv.lib.dto.prospetto.PostiLavoroDisp;
import it.csi.prodis.prodissrv.lib.mapper.BaseMapperInterface;

/**
 * Mapper between PostiLavoroDisp and ProDPostiLavoroDisp
 */
@Mapper(uses = { Istat2001livello5Mapper.class, ComuneMapper.class })
public interface PostiLavoroDispMapper extends BaseMapperInterface<PostiLavoroDisp, ProDPostiLavoroDisp> {

	@Override
	// @Mapping(source = "entityName", target = "modelName")
	@Mapping(source = "proTIstat2001livello5", target = "istat2001livello5")
	@Mapping(source = "proTComune", target = "comune")
	PostiLavoroDisp toModel(ProDPostiLavoroDisp entity);

	@Override
	@IterableMapping(elementTargetType = PostiLavoroDisp.class)
	List<PostiLavoroDisp> toModels(Collection<ProDPostiLavoroDisp> entities);

	@Override
	@IterableMapping(elementTargetType = ProDPostiLavoroDisp.class)
	List<ProDPostiLavoroDisp> toEntities(Collection<PostiLavoroDisp> models);

}
