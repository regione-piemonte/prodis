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
import org.mapstruct.MappingTarget;

import it.csi.prodis.prodissrv.ejb.entity.ProDProspetto;
import it.csi.prodis.prodissrv.lib.dto.prospetto.Prospetto;
import it.csi.prodis.prodissrv.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Prospetto and ProDProspetto
 */
@Mapper(uses = { DatiAziendaMapper.class, CategoriaAziendaMapper.class, ComunicazioneMapper.class, SoggettiMapper.class,
		StatoProspettoMapper.class, StatoVerificaMapper.class, RiepilogoNazionaleMapper.class,
		ProspettoGradualitaMapper.class, CategoriaAziendaMapper.class })
public interface ProspettoMapper extends BaseMapperInterface<Prospetto, ProDProspetto> {

	@Override

	@Mapping(source = "proDDatiAzienda", target = "datiAzienda")
	@Mapping(source = "proTCategoriaAzienda", target = "categoriaAzienda")
	@Mapping(source = "proTComunicazione", target = "comunicazione")
//	@Mapping(source = "proTSoggetti", target = "soggetti")
	@Mapping(source = "proTStatoProspetto", target = "statoProspetto")
//	@Mapping(source = "proTStatoVerifica", target = "statoVerifica")
//	@Mapping(source = "proDRiepilogoNazionale", target = "riepilogoNazionale")
	// @Mapping(source = "proDAssPubbliches", target = "assPubbliche")
//	@Mapping(source = "proDProspettoGradualita", target = "prospettoGradualita")

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
