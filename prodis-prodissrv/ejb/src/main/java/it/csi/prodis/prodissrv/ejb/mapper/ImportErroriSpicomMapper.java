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

import it.csi.prodis.prodissrv.ejb.entity.ProTImportErroriSpicom;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.ImportErroriSpicom;
import it.csi.prodis.prodissrv.lib.mapper.BaseMapperInterface;

/**
 * Mapper between ImportErroriSpicom and ProTImportErroriSpicom
 */
@Mapper
public interface ImportErroriSpicomMapper extends BaseMapperInterface<ImportErroriSpicom, ProTImportErroriSpicom> {

	@Override
	// @Mapping(source = "entityName", target = "modelName")
	ImportErroriSpicom toModel(ProTImportErroriSpicom entity);

	@Override
	@IterableMapping(elementTargetType = ImportErroriSpicom.class)
	List<ImportErroriSpicom> toModels(Collection<ProTImportErroriSpicom> entities);

	@Override
	@IterableMapping(elementTargetType = ProTImportErroriSpicom.class)
	List<ProTImportErroriSpicom> toEntities(Collection<ImportErroriSpicom> models);

}
