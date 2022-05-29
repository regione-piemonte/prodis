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

import it.csi.prodis.prodissrv.ejb.entity.ProRProspettoProvincia;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProspettoProvincia;
import it.csi.prodis.prodissrv.lib.mapper.BaseMapperInterface;

/**
 * Mapper between ProspettoProvincia and ProRProspettoProvincia
 */
@Mapper(uses = { ProvinciaMapper.class, RiepilogoProvincialeMapper.class, DatiProvincialiMapper.class })
public interface ProspettoProvinciaMapper extends BaseMapperInterface<ProspettoProvincia, ProRProspettoProvincia> {

	@Override
	// @Mapping(source = "entityName", target = "modelName")
	@Mapping(source = "proTProvincia", target = "provincia")
	@Mapping(source = "proDRiepilogoProvinciale", target = "riepilogoProvinciale")
	@Mapping(source = "proDDatiProvinciali", target = "datiProvinciali")
	ProspettoProvincia toModel(ProRProspettoProvincia entity);

	@Override
	@IterableMapping(elementTargetType = ProspettoProvincia.class)
	List<ProspettoProvincia> toModels(Collection<ProRProspettoProvincia> entities);

	@Override
	@IterableMapping(elementTargetType = ProRProspettoProvincia.class)
	List<ProRProspettoProvincia> toEntities(Collection<ProspettoProvincia> models);

}
