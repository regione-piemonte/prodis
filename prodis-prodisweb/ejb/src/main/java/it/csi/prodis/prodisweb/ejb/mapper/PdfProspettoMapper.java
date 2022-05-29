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

import it.csi.prodis.prodisweb.ejb.entity.ProDPdfProspetto;
import it.csi.prodis.prodisweb.lib.dto.prospetto.PdfProspetto;
import it.csi.prodis.prodisweb.lib.mapper.BaseMapperInterface;

/**
 * Mapper between PdfProspetto and ProDPdfProspetto
 */
@Mapper
public interface PdfProspettoMapper extends BaseMapperInterface<PdfProspetto, ProDPdfProspetto> {

	@Override
	// @Mapping(source = "entityName", target = "modelName")
	PdfProspetto toModel(ProDPdfProspetto entity);

	@Override
	@IterableMapping(elementTargetType = PdfProspetto.class)
	List<PdfProspetto> toModels(Collection<ProDPdfProspetto> entities);

	@Override
	@IterableMapping(elementTargetType = ProDPdfProspetto.class)
	List<ProDPdfProspetto> toEntities(Collection<PdfProspetto> models);

}
