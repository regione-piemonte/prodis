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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.mapstruct.Mapper;
import org.mapstruct.Qualifier;

@Mapper
public interface IdMapper {

	@Qualifier
	@java.lang.annotation.Target(ElementType.METHOD)
	@Retention(RetentionPolicy.CLASS)
	public @interface EmptyIdToNull {
	}

	@EmptyIdToNull
	default Long emptyIdToNull(Long s) {
		return s == null || Long.valueOf(0).equals(s) ? null : s;
	}

}
