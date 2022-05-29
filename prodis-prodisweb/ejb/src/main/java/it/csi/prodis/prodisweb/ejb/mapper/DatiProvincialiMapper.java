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

import it.csi.prodis.prodisweb.ejb.entity.ProDDatiProvinciali;
import it.csi.prodis.prodisweb.lib.dto.prospetto.DatiProvinciali;
import it.csi.prodis.prodisweb.lib.mapper.BaseMapperInterface;

/**
 * Mapper between DatiProvinciali and ProDDatiProvinciali
 */
@Mapper(uses = { ProspettoProvSedeMapper.class, ProvConvenzioneMapper.class, ProvEsoneroAutocertMapper.class,
		ProvSospensioneMapper.class, ProvGradualitaMapper.class, ProvEsoneroMapper.class })
public interface DatiProvincialiMapper extends BaseMapperInterface<DatiProvinciali, ProDDatiProvinciali> {

	@Override
	@Mapping(source = "proDProspettoProvSede", target = "prospettoProvSede")
	@Mapping(source = "proDProvConvenzione", target = "provConvenzione")
	@Mapping(source = "proDProvEsonero", target = "provEsonero")
	@Mapping(source = "proDProvEsoneroAutocert", target = "provEsoneroAutocert")
	@Mapping(source = "proDProvSospensione", target = "provSospensione")
	@Mapping(source = "proDProvGradualita", target = "provGradualita")
	DatiProvinciali toModel(ProDDatiProvinciali entity);

	@Override
	@IterableMapping(elementTargetType = DatiProvinciali.class)
	List<DatiProvinciali> toModels(Collection<ProDDatiProvinciali> entities);

	@Override
	@IterableMapping(elementTargetType = ProDDatiProvinciali.class)
	List<ProDDatiProvinciali> toEntities(Collection<DatiProvinciali> models);

}
