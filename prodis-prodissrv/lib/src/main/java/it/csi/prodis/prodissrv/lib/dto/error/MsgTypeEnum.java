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
package it.csi.prodis.prodissrv.lib.dto.error;

public enum MsgTypeEnum {
	/** Represents an error */
	ERROR(),
	/** Represents a notice*/
	WARNING(),
	/** Represents an information */
	INFO();

	private final String costante;

	private MsgTypeEnum(String costante) {
		this.costante = costante;
	}

	private MsgTypeEnum() {
		this.costante = this.name();
	}

	/**
	 * @return the costante
	 */
	public String getCostante() {
		return costante;
	}
}
