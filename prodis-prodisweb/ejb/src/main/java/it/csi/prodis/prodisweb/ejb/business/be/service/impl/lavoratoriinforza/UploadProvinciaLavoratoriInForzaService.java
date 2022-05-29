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

import it.csi.prodis.prodisweb.ejb.business.be.dad.LavoratoriInForzaDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.lavoratoriinforza.excel.MassivoLavoratoriEsito;
import it.csi.prodis.prodisweb.ejb.business.be.service.impl.lavoratoriinforza.excel.MassivoLavoratoriManager;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.lavoratoriinforza.UploadLavoratoriInForzaRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.lavoratoriinforza.UploadLavoratoriInForzaResponse;
import it.csi.prodis.prodisweb.ejb.exception.NotFoundException;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.ejb.util.mime.MimeTypeContainer;
import it.csi.prodis.prodisweb.ejb.util.mime.MimeTypeContainer.Extension;
import it.csi.prodis.prodisweb.lib.dto.prospetto.Prospetto;

public class UploadProvinciaLavoratoriInForzaService extends 
BaseUploadDownloadLavoratoriInForzaService<UploadLavoratoriInForzaRequest, UploadLavoratoriInForzaResponse> {
	/**
	 * Constructor
	 * 
	 * @param configurationHelper the helper for the configuration
	 * @param datiProvincialiDad        the DAD for the datiProvinciali
	 */
	public UploadProvinciaLavoratoriInForzaService(ConfigurationHelper configurationHelper, LavoratoriInForzaDad lavoratoriInForzaDad) {
		super(configurationHelper, lavoratoriInForzaDad);
	}

	@Override
	protected void execute() {
		
		Long idProspettoProv = request.getId();
		byte[] fileInByte = request.getFileInByte();
		
		if (idProspettoProv==null) {
			throw new NotFoundException("Provincia");
		}
		
		if (fileInByte.length==0 || fileInByte==null) {
			throw new NotFoundException("File passato vuoto");
		}
		
		MassivoLavoratoriManager manager = new MassivoLavoratoriManager(lavoratoriInForzaDad);
		
		Prospetto prospetto = lavoratoriInForzaDad.getProspettoByProvincia(idProspettoProv);
		
		MassivoLavoratoriEsito esito = manager.uploadProvinciaLavoratori(prospetto, idProspettoProv, fileInByte, prospetto.getCodUserAggiorn());
		
		byte[] fileDiEsito = manager.creaFileEsito(esito.getMessaggi(), esito.getErrori(), 
				esito.getLavoratoriBuoni(), esito.getLavoratoriCattivi());
		
		response.setBytes(fileDiEsito);
		response.setFileNameTemplate("Esiti-" + idProspettoProv);
		response.setMimeTypeContainer(MimeTypeContainer.byExtension(Extension.EXCEL_SPREADSHEET));
		
		response.setApiErrors(null);

	}
	
}
