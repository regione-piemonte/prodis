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
package it.csi.prodis.prodissrv.ejb.entity.base;

import java.util.UUID;

/**
 * Marker interface for an entity with Optlock
 */
public interface OptlockEntity {

	/**
	 * @return the id
	 */
	UUID getOptlock();
	/**
	 * @param id the id to set
	 */
	void setOptlock(UUID id);
	/**
	 * Generates a new Optlock
	 */
	void generateNewOptlock();
}
