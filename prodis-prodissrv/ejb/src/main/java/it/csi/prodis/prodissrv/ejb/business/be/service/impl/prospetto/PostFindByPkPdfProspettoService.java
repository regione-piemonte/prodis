/*-
 * ========================LICENSE_START=================================
 * PRODIS Servizi - EJB submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodissrv.ejb.business.be.service.impl.prospetto;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import it.csi.prodis.prodissrv.ejb.business.be.dad.ProspettoDad;
import it.csi.prodis.prodissrv.ejb.business.be.service.request.prospetto.PostFindByPkPdfProspettoRequest;
import it.csi.prodis.prodissrv.ejb.business.be.service.response.prospetto.PostFindByPkPdfProspettoResponse;
import it.csi.prodis.prodissrv.ejb.exception.NotFoundException;
import it.csi.prodis.prodissrv.ejb.util.ProdisSrvUtil;
import it.csi.prodis.prodissrv.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodissrv.lib.dto.error.MsgProdis;
import it.csi.prodis.prodissrv.lib.dto.prospetto.PdfProspetto;

/**
 * Retrieves an testataOrdine by its id
 */
public class PostFindByPkPdfProspettoService
		extends BaseProspettoService<PostFindByPkPdfProspettoRequest, PostFindByPkPdfProspettoResponse> {

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad    the testataOrdine DAD
	 */
	public PostFindByPkPdfProspettoService(ConfigurationHelper configurationHelper, ProspettoDad prospettoDad) {
		super(configurationHelper, prospettoDad);
	}

	@Override
	protected void execute() {
		final String methodName = "execute";

		// verifica se utente è autorizzato
		String caller = request.getFilterServiziProdis().getCaller();
		checkBusinessCondition(!StringUtils.isEmpty(caller), MsgProdis.SEC00001.getError());

		List<String> callers;
		try {
			callers = Arrays.asList(getCallers());
			boolean bCallerAuthorized = callers.contains(caller);
			checkBusinessCondition(bCallerAuthorized, MsgProdis.SEC00001.getError());
			checkBusinessCondition(!ProdisSrvUtil.isVoid(request.getFilterServiziProdis()),
					MsgProdis.CR00001.getError());
			checkBusinessCondition(!ProdisSrvUtil.isVoid(request.getFilterServiziProdis().getIdProspetto()),
					MsgProdis.CR00001.getError());

			PdfProspetto pdfProspetto = prospettoDad.getPdfProspetto(request.getFilterServiziProdis().getIdProspetto());
			response.setProspetto(pdfProspetto);

		} catch (NotFoundException e) {
			log.error(methodName, e, e);
			response.addApiError(MsgProdis.ANA0001.getError());
			e.printStackTrace();
		} catch (IOException e) {
			log.error(methodName, e, e);
			response.addApiError(MsgProdis.DBA00001.getError());
			e.printStackTrace();
		} catch (Exception e) {
			log.error(methodName, e, e);
			response.addApiError(MsgProdis.DBA00001.getError());
			e.printStackTrace();
		}
	}

	public String[] getCallers() throws IOException {
		final String CALLER_RESOURCE = "/caller.properties";
		InputStream is = this.getClass().getResourceAsStream(CALLER_RESOURCE);
		Properties properties = new Properties();
		properties.load(is);

		String caller = properties.getProperty("caller");
		String[] callers = caller.split(",");
		return callers;
	}

}
