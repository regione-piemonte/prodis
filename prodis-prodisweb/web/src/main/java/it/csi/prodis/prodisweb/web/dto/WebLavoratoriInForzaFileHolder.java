/*-
 * ========================LICENSE_START=================================
 * PRODIS BackEnd - WAR submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodisweb.web.dto;

import java.util.UUID;

import javax.ws.rs.FormParam;

import it.csi.prodis.prodisweb.lib.dto.FileHolder;

/**
 * File holder for Web
 */
public class WebLavoratoriInForzaFileHolder {

	@FormParam("attachment")
	private byte[] attachment;
	@FormParam("idProspettoProv")
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

	/**
	 * To a EJB file holder
	 * 
	 * @return the file holder
	 */
	public FileHolder toFileHolder() {
		FileHolder fh = new FileHolder();
		fh.setAttachment(attachment);
		fh.setIdProspettoProv(idProspettoProv);
		return fh;
	}

}
