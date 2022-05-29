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
import org.mapstruct.MappingTarget;

import it.csi.prodis.prodisweb.ejb.entity.ProDDatiAzienda;
import it.csi.prodis.prodisweb.ejb.mapper.IdMapper.EmptyIdToNull;
import it.csi.prodis.prodisweb.lib.dto.prospetto.DatiAzienda;
import it.csi.prodis.prodisweb.lib.mapper.BaseMapperInterface;

/**
 * Mapper between DatiAzienda and ProDDatiAzienda
 */
@Mapper(uses = { AtecofinMapper.class, CcnlMapper.class, ComuneMapper.class, DichiaranteMapper.class,
		StatiEsteriMapper.class, SedeLegaleMapper.class, IdMapper.class })
public interface DatiAziendaMapper extends BaseMapperInterface<DatiAzienda, ProDDatiAzienda> {

	@Override
	@Mapping(source = "proTAtecofin", target = "atecofin")
	@Mapping(source = "proTCcnl", target = "ccnl")
	@Mapping(source = "proTComune", target = "comune")
	@Mapping(source = "proTDichiarante", target = "dichiarante")
	@Mapping(source = "proTStatiEsteri", target = "statiEsteri")
	@Mapping(source = "proDSedeLegale", target = "sedeLegale")
	@Mapping(source = "id", target = "id", qualifiedBy = EmptyIdToNull.class)
	DatiAzienda toModel(ProDDatiAzienda entity);

	@Override
	@IterableMapping(elementTargetType = DatiAzienda.class)
	List<DatiAzienda> toModels(Collection<ProDDatiAzienda> entities);

	@Override
	@IterableMapping(elementTargetType = ProDDatiAzienda.class)
	List<ProDDatiAzienda> toEntities(Collection<DatiAzienda> models);

	@Override
	ProDDatiAzienda toEntityExisting(DatiAzienda model, @MappingTarget ProDDatiAzienda entity);

}
