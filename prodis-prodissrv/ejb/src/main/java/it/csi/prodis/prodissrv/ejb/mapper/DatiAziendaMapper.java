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

import it.csi.prodis.prodissrv.ejb.entity.ProDDatiAzienda;
import it.csi.prodis.prodissrv.lib.dto.prospetto.DatiAzienda;
import it.csi.prodis.prodissrv.lib.mapper.BaseMapperInterface;

/**
 * Mapper between DatiAzienda and ProDDatiAzienda
 */
@Mapper(uses = { AtecofinMapper.class, CcnlMapper.class, ComuneMapper.class, DichiaranteMapper.class,
		StatiEsteriMapper.class, SedeLegaleMapper.class })
public interface DatiAziendaMapper extends BaseMapperInterface<DatiAzienda, ProDDatiAzienda> {

	@Override
	@Mapping(source = "proTAtecofin", target = "atecofin")
	@Mapping(source = "proTCcnl", target = "ccnl")
	@Mapping(source = "proTComune", target = "comune")
	@Mapping(source = "proTDichiarante", target = "dichiarante")
	@Mapping(source = "proTStatiEsteri", target = "statiEsteri")
	@Mapping(source = "proDSedeLegale", target = "sedeLegale")
	DatiAzienda toModel(ProDDatiAzienda entity);

	@Override
	@IterableMapping(elementTargetType = DatiAzienda.class)
	List<DatiAzienda> toModels(Collection<ProDDatiAzienda> entities);

	@Override
	@IterableMapping(elementTargetType = ProDDatiAzienda.class)
	List<ProDDatiAzienda> toEntities(Collection<DatiAzienda> models);
	    
	

}
