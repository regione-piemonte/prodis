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

import it.csi.prodis.prodisweb.ejb.entity.ProDProspetto;
import it.csi.prodis.prodisweb.lib.dto.prospetto.Prospetto;
import it.csi.prodis.prodisweb.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Prospetto and ProDProspetto
 */
@Mapper(uses = { DatiAziendaMapper.class, CategoriaAziendaMapper.class, ComunicazioneMapper.class, SoggettiMapper.class,
		StatoProspettoMapper.class, StatoVerificaMapper.class})
public interface ProspettoMapper extends BaseMapperInterface<Prospetto, ProDProspetto> {

	@Override
	 
	@Mapping(source = "proDDatiAzienda", target = "datiAzienda")
	@Mapping(source = "proTCategoriaAzienda", target = "categoriaAzienda")
	@Mapping(source = "proTComunicazione", target = "comunicazione")
	@Mapping(source = "proTSoggetti", target = "soggetti")
	@Mapping(source = "proTStatoProspetto", target = "statoProspetto")
	@Mapping(source = "proTStatoVerifica", target = "statoVerifica")
	
	Prospetto toModel(ProDProspetto entity);

	@Override
	@IterableMapping(elementTargetType = Prospetto.class)
	List<Prospetto> toModels(Collection<ProDProspetto> entities);

	@Override
	@IterableMapping(elementTargetType = ProDProspetto.class)
	List<ProDProspetto> toEntities(Collection<Prospetto> models);
	
	@Override
	ProDProspetto toEntityExisting(Prospetto model, @MappingTarget ProDProspetto entity);
	

}
