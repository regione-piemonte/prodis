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

import it.csi.prodis.prodissrv.ejb.entity.ProDImportErrori;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ImportErrori;
import it.csi.prodis.prodissrv.lib.mapper.BaseMapperInterface;

/**
 * Mapper between ImportErrori and ProDImportErrori
 */
@Mapper
public interface ImportErroriMapper extends BaseMapperInterface<ImportErrori, ProDImportErrori> {

	@Override
	// @Mapping(source = "entityName", target = "modelName")
	ImportErrori toModel(ProDImportErrori entity);

	@Override
	@IterableMapping(elementTargetType = ImportErrori.class)
	List<ImportErrori> toModels(Collection<ProDImportErrori> entities);

	@Override
	@IterableMapping(elementTargetType = ProDImportErrori.class)
	List<ProDImportErrori> toEntities(Collection<ImportErrori> models);

}
