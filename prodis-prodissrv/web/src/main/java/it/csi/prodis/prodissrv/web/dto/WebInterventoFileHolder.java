/*-
 * ========================LICENSE_START=================================
 * PRODIS Servizi - WAR submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022  | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodissrv.web.dto;

import java.util.UUID;

import javax.ws.rs.FormParam;

import it.csi.prodis.prodissrv.lib.dto.FileHolder;

/**
 * File holder for intervento
 */
public class WebInterventoFileHolder extends WebFileHolder{

	@FormParam("annoProgramma")
	private String annoProgramma;
	@FormParam("versioneProgramma")
	private String versioneProgramma;
	@FormParam("utenteReferenteCf")
	private String utenteReferenteCf;
	@FormParam("attachment2")
	private byte[] attachment2;

	/**
	 * @return the annoProgramma
	 */
	public String getAnnoProgramma() {
		return annoProgramma;
	}

	/**
	 * @param annoProgramma the annoProgramma to set
	 */
	public void setAnnoProgramma(String annoProgramma) {
		this.annoProgramma = annoProgramma;
	}

	/**
	 * @return the versioneProgramma
	 */
	public String getVersioneProgramma() {
		return versioneProgramma;
	}

	/**
	 * @param versioneProgramma the versioneProgramma to set
	 */
	public void setVersioneProgramma(String versioneProgramma) {
		this.versioneProgramma = versioneProgramma;
	}

	/**
	 * @return the utenteReferenteCf
	 */
	public String getUTtenteReferenteCf() {
		return utenteReferenteCf;
	}

	/**
	 * @param utenteReferenteCf the utenteReferenteCf to set
	 */
	public void setUtenteReferenteCf(String utenteReferenteCf) {
		this.utenteReferenteCf = utenteReferenteCf;
	}
	/**
	 * @return the attachment2
	 */
	public byte[] getAttachment2() {
		return attachment2;
	}

	/**
	 * @param attachment2 the attachment2 to set
	 */
	public void setAttachment2(byte[] attachment2) {
		this.attachment2 = attachment2;
	}

	@Override
	public FileHolder toFileHolder() {
		FileHolder fh = new FileHolder();
		fh.setAttachment(super.getAttachment());
		fh.setAttachment2(attachment2);
		fh.setAnnoProgramma(annoProgramma);
		fh.setVersioneProgramma(versioneProgramma);
		fh.setUtenteReferenteCf(utenteReferenteCf);
		fh.setIdEnte(UUID.fromString(super.getIdEnte()));
		return fh;
	}

}
