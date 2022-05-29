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

import it.csi.prodis.prodisweb.ejb.entity.ProTCcnl;
import it.csi.prodis.prodisweb.ejb.mapper.IdMapper.EmptyIdToNull;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Ccnl;
import it.csi.prodis.prodisweb.lib.mapper.BaseMapperInterface;

/**
 * Mapper between Ccnl and ProTCcnl
 */
@Mapper(uses = { IdMapper.class })
public interface CcnlMapper extends BaseMapperInterface<Ccnl, ProTCcnl> {

	@Override
	@Mapping(source = "id", target = "id", qualifiedBy = EmptyIdToNull.class)
	Ccnl toModel(ProTCcnl entity);

	@Override
	@IterableMapping(elementTargetType = Ccnl.class)
	List<Ccnl> toModels(Collection<ProTCcnl> entities);

	@Override
	@IterableMapping(elementTargetType = ProTCcnl.class)
	List<ProTCcnl> toEntities(Collection<Ccnl> models);

	@Override
	ProTCcnl toEntityExisting(Ccnl model, @MappingTarget ProTCcnl entity);

}
