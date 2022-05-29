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

import it.csi.prodis.prodissrv.ejb.entity.ProDPdfSilp;
import it.csi.prodis.prodissrv.lib.dto.prospetto.PdfSilp;
import it.csi.prodis.prodissrv.lib.mapper.BaseMapperInterface;

/**
 * Mapper between PdfSilp and ProDPdfSilp
 */
@Mapper
public interface PdfSilpMapper extends BaseMapperInterface<PdfSilp, ProDPdfSilp> {

	@Override
	// @Mapping(source = "entityName", target = "modelName")
	PdfSilp toModel(ProDPdfSilp entity);

	@Override
	@IterableMapping(elementTargetType = PdfSilp.class)
	List<PdfSilp> toModels(Collection<ProDPdfSilp> entities);

	@Override
	@IterableMapping(elementTargetType = ProDPdfSilp.class)
	List<ProDPdfSilp> toEntities(Collection<PdfSilp> models);

}
