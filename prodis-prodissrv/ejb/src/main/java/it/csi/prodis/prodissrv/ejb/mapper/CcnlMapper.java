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

import it.csi.prodis.prodissrv.ejb.entity.ProTCcnl;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Ccnl;
import it.csi.prodis.prodissrv.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Ccnl and ProTCcnl
 */
@Mapper
public interface CcnlMapper extends BaseMapperInterface<Ccnl, ProTCcnl> {

	@Override
	// @Mapping(source = "entityName", target = "modelName")
	Ccnl toModel(ProTCcnl entity);

	@Override
	@IterableMapping(elementTargetType = Ccnl.class)
	List<Ccnl> toModels(Collection<ProTCcnl> entities);

	@Override
	@IterableMapping(elementTargetType = ProTCcnl.class)
	List<ProTCcnl> toEntities(Collection<Ccnl> models);

}
