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

import it.csi.prodis.prodissrv.ejb.business.be.dad.ProspettoDad;
import it.csi.prodis.prodissrv.ejb.business.be.service.request.prospetto.RiceviProspettoDaSpicomRequest;
import it.csi.prodis.prodissrv.ejb.business.be.service.response.prospetto.InitRicezioneProspettoDaSpicomResponse;
import it.csi.prodis.prodissrv.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodissrv.lib.dto.error.MsgProdis;
import it.csi.prodis.prodissrv.lib.dto.prospetto.FilterServiziProdis;
import it.csi.spicom.dto.ComunicazioneProspettoDisabiliDTO;

public class InitRiceviProspettoDaSpicomService extends BaseProspettoService<RiceviProspettoDaSpicomRequest, InitRicezioneProspettoDaSpicomResponse> {

	public InitRiceviProspettoDaSpicomService(ConfigurationHelper configurationHelper, ProspettoDad prospettoDad) {
		super(configurationHelper, prospettoDad);

	}

	@Override
	protected void execute() {

		ComunicazioneProspettoDisabiliDTO comunicazioneSpicom = request.getComunicazioneProspettoSpicom();
		if (comunicazioneSpicom.getIdTrasmissione() == null) {
			log.error("[InitRiceviProspettoDaSpicomService::execute]", " ERROR: comunicazione senza idTrasmissione");
			response.addApiError(MsgProdis.SP0002.getError());
			return;
		}

		FilterServiziProdis filter = new FilterServiziProdis();
		filter.setCodiceRegionale(comunicazioneSpicom.getDatiInvio().getCodiceComunicazione());

		long numProspetti = prospettoDad.countRicerca(filter);
		if (numProspetti > 0) {
			log.info("[InitRiceviProspettoDaSpicomService::execute]", " INFO: prospetto con codice = "
					+ comunicazioneSpicom.getDatiInvio().getCodiceComunicazione() + " gia presente in Prodis");
			response.addApiError(MsgProdis.SP0001.getError());
			return;
		}

		prospettoDad.deleteImportErrori(comunicazioneSpicom.getIdTrasmissione());
		
		response.setId(0);
		
	}

}
