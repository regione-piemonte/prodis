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
package it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto;

import javax.ws.rs.core.Response;

import it.csi.prodis.prodisweb.ejb.business.be.service.response.base.BaseResponse;
import it.csi.prodis.prodisweb.ejb.util.mime.MimeTypeContainer;

public class GeneraPdfResponse extends BaseResponse {

	private byte[] bytes;
	private String fileNameTemplate;
	private MimeTypeContainer mimeTypeContainer;

	/**
	 * @return the bytes
	 */
	public byte[] getBytes() {
		return bytes;
	}

	/**
	 * @param bytes the bytes to set
	 */
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	/**
	 * @return the fileNameTemplate
	 */
	public String getFileNameTemplate() {
		return fileNameTemplate;
	}

	/**
	 * @param fileNameTemplate the fileNameTemplate to set
	 */
	public void setFileNameTemplate(String fileNameTemplate) {
		this.fileNameTemplate = fileNameTemplate;
	}

	/**
	 * @return the mimeTypeContainer
	 */
	public MimeTypeContainer getMimeTypeContainer() {
		return mimeTypeContainer;
	}

	/**
	 * @param mimeTypeContainer the mimeTypeContainer to set
	 */
	public void setMimeTypeContainer(MimeTypeContainer mimeTypeContainer) {
		this.mimeTypeContainer = mimeTypeContainer;
	}

	@Override
	protected Response composeOwnResponse() {
		return Response.ok(bytes, getMimeTypeContainer().getMimeType())
				.header("Content-Disposition",
						"attachment;filename=" + fileNameTemplate + "." + getMimeTypeContainer().getExtension())
				.build();
	}

}
