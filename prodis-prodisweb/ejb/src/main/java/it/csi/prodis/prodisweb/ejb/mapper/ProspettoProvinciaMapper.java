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
import org.mapstruct.Mapping;

import it.csi.prodis.prodisweb.ejb.entity.ProRProspettoProvincia;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProspettoProvincia;
import it.csi.prodis.prodisweb.lib.mapper.BaseMapperInterface;

/**
 * Mapper between ProspettoProvincia and ProRProspettoProvincia
 */
@Mapper(uses = { ProvinciaMapper.class, DatiProvincialiMapper.class })
public interface ProspettoProvinciaMapper extends BaseMapperInterface<ProspettoProvincia, ProRProspettoProvincia> {

	@Override
	@Mapping(source = "proTProvincia", target = "provincia")
	// @Mapping(source = "proDRiepilogoProvinciale", target = "riepilogoProvinciale")
	@Mapping(source = "proDDatiProvinciali", target = "datiProvinciali")
	// @Mapping(source = "proDProspetto", target = "prospetto")
	ProspettoProvincia toModel(ProRProspettoProvincia entity);

	@Override
	@IterableMapping(elementTargetType = ProspettoProvincia.class)
	List<ProspettoProvincia> toModels(Collection<ProRProspettoProvincia> entities);

	@Override
	@IterableMapping(elementTargetType = ProRProspettoProvincia.class)
	List<ProRProspettoProvincia> toEntities(Collection<ProspettoProvincia> models);

}
