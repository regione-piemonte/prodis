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
package it.csi.prodis.prodisweb.ejb.business.be.dad.sort;

/**
 * Sort mapper for Prospetto.
 */
public enum ProspettoSort implements JpaSort {

	ID("id", "t.idProspetto"), 
	ANNO("anno", "t.annoProtocollo");

	/** The model name. */
	private final String modelName;

	/** The query name. */
	private final String queryName;

	/**
	 * Constructor.
	 *
	 * @param modelName the model name
	 * @param queryName the query name
	 */
	private ProspettoSort(String modelName, String queryName) {
		this.modelName = modelName;
		this.queryName = queryName;
	}

	@Override
	public String getQueryName() {
		return queryName;
	}

	@Override
	public String getModelName() {
		return modelName;
	}

	/**
	 * Retrieves the Sort by its model name.
	 *
	 * @param modelName the model name
	 * @return the sort
	 */
	public static ProspettoSort byModelName(String modelName) {
		for (ProspettoSort is : ProspettoSort.values()) {
			if (is.modelName.equalsIgnoreCase(modelName)) {
				return is;
			}
		}
		return ProspettoSort.ID;
	}

}
