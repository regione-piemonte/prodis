/*-
 * ========================LICENSE_START=================================
 * PRODIS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodisweb.lib.dto;

/**
 * File holder
 */
public class FileHolder {

	private byte[] attachment;
	private String idProspettoProv;

	/**
	 * @return the attachment
	 */
	public byte[] getAttachment() {
		return attachment;
	}

	/**
	 * @param attachment the attachment to set
	 */
	public void setAttachment(byte[] attachment) {
		this.attachment = attachment;
	}

	/**
	 * @return the idProspettoProv
	 */
	public String getIdProspettoProv() {
		return idProspettoProv;
	}

	/**
	 * @param idProspettoProv the idProspettoProv to set
	 */
	public void setIdProspettoProv(String idProspettoProv) {
		this.idProspettoProv = idProspettoProv;
	}

}
