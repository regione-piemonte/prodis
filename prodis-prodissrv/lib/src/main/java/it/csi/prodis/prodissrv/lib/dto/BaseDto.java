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
package it.csi.prodis.prodissrv.lib.dto;

import java.util.Objects;

/**
 * Base model class
 * @param <K> the key type
 */
public abstract class BaseDto<K> {

	/** The uid */
	protected K id;

	/** Base JavaBean contructor */
	protected BaseDto() {
		this(null);
	}

	/**
	 * Constructor
	 * @param id the id
	 */
	protected BaseDto(K id) {
		this.id = id;
	}

	/**
	 * @return the uuid
	 */
	public K getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(K id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof BaseDto)) {
			return false;
		}
		@SuppressWarnings("unchecked")
		BaseDto<K> other = (BaseDto<K>) obj;
		return Objects.equals(id, other.id);
	}

}
