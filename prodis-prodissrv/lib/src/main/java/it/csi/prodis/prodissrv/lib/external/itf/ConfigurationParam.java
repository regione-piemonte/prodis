/*-
 * ========================LICENSE_START=================================
 * PRODIS Servizi - LIB submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodissrv.lib.external.itf;

//import it.csi.prodis.prodissrv.lib.dto.Parametro;

/**
 * Configuration parameter
 */
public interface ConfigurationParam {

	/**
	 * @return the param name
	 */
	String getParamName();
	
	/**
	 * Checks whether the given argument equals the configuration
	 * @param parametro the parametro to check
	 * @return whether the argument represents the configuration
	 */
//	default boolean isParametroEqual(Parametro parametro) {
//		return this.getParamName().equals(parametro.getChiave());
//	}
}
