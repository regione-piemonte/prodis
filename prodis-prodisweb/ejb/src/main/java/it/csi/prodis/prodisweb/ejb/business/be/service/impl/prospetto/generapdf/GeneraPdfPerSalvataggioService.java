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
package it.csi.prodis.prodisweb.ejb.business.be.service.impl.prospetto.generapdf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import it.csi.prodis.prodisweb.ejb.business.be.service.impl.base.BaseService;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto.GeneraPdfRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto.GeneraPdfPerSalvataggioResponse;
import it.csi.prodis.prodisweb.ejb.exception.NotFoundException;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;


public class GeneraPdfPerSalvataggioService extends BaseService<GeneraPdfRequest, GeneraPdfPerSalvataggioResponse> {

	public GeneraPdfPerSalvataggioService(ConfigurationHelper configurationHelper) {
		super(configurationHelper);
	}

	@Override
	protected void execute() {
		//final String methodName = "GeneraPdfService - execute";

		Long idProspetto = request.getIdProspetto();
		if (idProspetto == null) {
			throw new NotFoundException("Prospetto");
		}

		ByteArrayOutputStream baosPdf = null;
		try {
			//log.info(methodName, "idProspetto: " + idProspetto + " - inizio");
			baosPdf = new ByteArrayOutputStream();

			PdfRendererBuilder builder = new PdfRendererBuilder();
			
			String stringaHtml = request.getHtmlContent();

			builder.withHtmlContent(stringaHtml, null);
			builder.toStream(baosPdf);
			builder.run(); 
			builder = null;

			baosPdf.close();

			byte[] bytes = baosPdf.toByteArray();
			//log.info(methodName, "idProspetto: " + idProspetto + " - bytes: " + bytes.length);

			response.setBytes(bytes);

		} catch (IOException e) {
			//log.error(methodName, "IOException---> idProspetto: " + idProspetto + " - " + e.getMessage(), e);
			throw new RuntimeException(e);
		} catch (Exception e) {
			//log.error(methodName, "Exception ---> idProspetto: " + idProspetto + " - " + e.getMessage(), e);
			throw new RuntimeException(e);
		} finally {
			//log.info(methodName, "idProspetto: " + idProspetto + " - fine");
		}
	}

}
