/*-
 * ========================LICENSE_START=================================
 * PRODIS BackEnd - INTEGRATION SILP submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodisweb.lib.mapper;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;

import it.csi.prodis.prodisweb.lib.mapper.annotation.TrimmedString;

/**
 * Mapper between Strings
 */
@Mapper
public interface StringMapper {

	/**
	 * Maps a string
	 * 
	 * @param input the input string
	 * @return the mapped value
	 */
	@TrimmedString
	default String mapString(String input) {
		return StringUtils.trimToNull(input);
	}

}
