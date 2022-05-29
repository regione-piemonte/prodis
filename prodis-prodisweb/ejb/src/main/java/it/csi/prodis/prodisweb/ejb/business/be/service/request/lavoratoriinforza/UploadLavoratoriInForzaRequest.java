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
package it.csi.prodis.prodisweb.ejb.business.be.service.request.lavoratoriinforza;

import it.csi.prodis.prodisweb.ejb.business.be.service.request.base.BaseRequest;
import it.csi.prodis.prodisweb.lib.dto.FileHolder;

public class UploadLavoratoriInForzaRequest implements BaseRequest {

	private final Long id;
	private final byte[] fileInByte;

	public UploadLavoratoriInForzaRequest(Long id, FileHolder fileHolder) {
		this.id = id;
		this.fileInByte = fileHolder.getAttachment();		
	}

	public Long getId() {
		return id;
	}

	public byte[] getFileInByte() {
		return fileInByte;
	}

	
}
