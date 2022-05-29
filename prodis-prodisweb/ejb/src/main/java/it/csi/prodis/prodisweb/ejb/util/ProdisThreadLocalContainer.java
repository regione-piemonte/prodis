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
package it.csi.prodis.prodisweb.ejb.util;

import it.csi.iride2.policy.entity.Identita;
import it.csi.prodis.prodisweb.lib.dto.Utente;

/**
 * Thead local container
 */
public class ProdisThreadLocalContainer {
	/** Contains the connected user */
	public static final ThreadLocal<Utente> UTENTE_CONNESSO = new ThreadLocal<>();
	public static final ThreadLocal<Identita> IDENTITA = new ThreadLocal<>();
	
	/** Private constructor */
	private ProdisThreadLocalContainer() {
		// Prevent instantiation
	}

	/**
	 * Cleanup of the thread locals
	 */
	public static void cleanup() {
		UTENTE_CONNESSO.remove();
		IDENTITA.remove();
	}

}
