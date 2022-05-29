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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.csi.prodis.prodisweb.ejb.business.be.dad.ProspettoDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.prospetto.BaseProspettoService;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto.CreaHtmlRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto.CreaHtmlResponse;
import it.csi.prodis.prodisweb.ejb.entity.ProRProspettoProvincia;
import it.csi.prodis.prodisweb.ejb.exception.NotFoundException;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;

public class CreaHtmlService extends BaseProspettoService<CreaHtmlRequest, CreaHtmlResponse> {

	public CreaHtmlService(ConfigurationHelper configurationHelper, ProspettoDad prospettoDad) {
		super(configurationHelper, prospettoDad);
	}

	@Override
	protected void execute() {
		//final String methodName = "CreaHtmlService - execute";

		Long idProspetto = request.getIdProspetto();
		if (idProspetto == null) {
			throw new NotFoundException("Prospetto");
		}

		try {
			//log.info(methodName, "idProspetto: " + idProspetto + " - inizio");
			String htmlContent = GeneraPdfUtilities.getHtmlString();

			htmlContent = htmlContent.replace("{{quadro1}}",
					GeneraQ1.getGenerazioneQ1(prospettoDad, idProspetto).toString());

			List<ProRProspettoProvincia> prospettoProvincia = prospettoDad.caricaProspettoProvinciaPerPdf(idProspetto);

			htmlContent = htmlContent.replace("{{quadro2}}",
					GeneraQ2.getGenerazioneQ2(prospettoDad, idProspetto, prospettoProvincia).toString());

			htmlContent = htmlContent.replace("{{quadro3}}",
					GeneraQ3.getGenerazioneQ3(prospettoDad, idProspetto, prospettoProvincia).toString());

			//log.info(methodName, "idProspetto: " + idProspetto + " - htmlContent: " + htmlContent.length());
			
			//System.out.println(htmlContent);
			
			htmlContent = GeneraPdfUtilities.replaceSpecialChar(htmlContent);
			
			response.setHtmlContent(htmlContent);

		} catch (Exception e) {
			//log.error(methodName, "idProspetto: " + idProspetto + " - " + e.getMessage(), e);
			throw new RuntimeException(e);
		} finally {
			//log.info(methodName, "idProspetto: " + idProspetto + " - fine");
		}
	}

}
