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
package it.csi.prodis.prodisweb.ejb.business.be.service.impl.lavoratoriinforza;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import it.csi.prodis.prodisweb.ejb.business.be.dad.LavoratoriInForzaDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.lavoratoriinforza.excel.MassivoLavoratoriEsito;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.lavoratoriinforza.excel.MassivoLavoratoriManager;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.lavoratoriinforza.DownloadLavoratoriInForzaRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.lavoratoriinforza.DownloadLavoratoriInForzaResponse;
import it.csi.prodis.prodisweb.ejb.exception.NotFoundException;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.ejb.util.mime.MimeTypeContainer;
import it.csi.prodis.prodisweb.ejb.util.mime.MimeTypeContainer.Extension;
import it.csi.prodis.prodisweb.lib.dto.ApiError;
import it.csi.prodis.prodisweb.lib.dto.prospetto.Prospetto;

public class DownloadProspettoLavoratoriInForzaService 
extends BaseUploadDownloadLavoratoriInForzaService<DownloadLavoratoriInForzaRequest, DownloadLavoratoriInForzaResponse> {

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad    the testataOrdine DAD
	 */
	public DownloadProspettoLavoratoriInForzaService(ConfigurationHelper configurationHelper, LavoratoriInForzaDad lavoratoriInForzaDad) {
		super(configurationHelper, lavoratoriInForzaDad);
	}


	@Override
	protected void execute(){
		
		Long idProspetto = request.getId();
		
		if (idProspetto==null) {
			throw new NotFoundException("Prospetto");
		} 
		
		MassivoLavoratoriManager manager = new MassivoLavoratoriManager(lavoratoriInForzaDad);
			
		MassivoLavoratoriEsito esito = manager.downloadProspettoLavoratori(idProspetto);

		if(esito!=null && esito.isEsitoPositivo()) {
			
			response.setBytes(esito.getFileLavoratori().getContents());
			
			Prospetto prospetto = lavoratoriInForzaDad.getProspetto(idProspetto);
			Date dataOdierna = new Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			response.setFileNameTemplate("MassivoLavoratori_"+prospetto.getDatiAzienda().getCfAzienda()
					+"_ALL_"+dateFormat.format(dataOdierna));
			
			response.setMimeTypeContainer(MimeTypeContainer.byExtension(Extension.EXCEL_SPREADSHEET));
			
		} else {
			List<ApiError> errori = esito.getErrori();
			errori = new ArrayList<ApiError>(new HashSet<ApiError>(errori));
			response.setApiErrors(errori);
		}
		
		
		
	}

	

}
