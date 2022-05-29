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

import it.csi.prodis.prodisweb.ejb.entity.ProDUserAccessLog;
import it.csi.prodis.prodisweb.lib.dto.prospetto.UserAccessLog;
import it.csi.prodis.prodisweb.lib.mapper.BaseMapperInterface;

/**
 * Mapper between UserAccessLog and ProDUserAccessLog
 */
@Mapper
public interface UserAccessLogMapper extends BaseMapperInterface<UserAccessLog, ProDUserAccessLog> {

	@Override
	// @Mapping(source = "entityName", target = "modelName")
	UserAccessLog toModel(ProDUserAccessLog entity);

	@Override
	@IterableMapping(elementTargetType = UserAccessLog.class)
	List<UserAccessLog> toModels(Collection<ProDUserAccessLog> entities);

	@Override
	@IterableMapping(elementTargetType = ProDUserAccessLog.class)
	List<ProDUserAccessLog> toEntities(Collection<UserAccessLog> models);

}
