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
package it.csi.prodis.prodissrv.ejb.business.be.service.request.prospetto;

import it.csi.prodis.prodissrv.ejb.business.be.service.request.base.BaseRequest;
import it.csi.spicom.dto.ComunicazioneProspettoDisabiliDTO;

public class RiceviProspettoDaSpicomRequest implements BaseRequest {
	
	public ComunicazioneProspettoDisabiliDTO comunicazioneProspettoSpicom;
	
	public RiceviProspettoDaSpicomRequest(ComunicazioneProspettoDisabiliDTO comunicazioneProspettoSpicom) {
		this.comunicazioneProspettoSpicom = comunicazioneProspettoSpicom;
	}

	public ComunicazioneProspettoDisabiliDTO getComunicazioneProspettoSpicom() {
		return comunicazioneProspettoSpicom;
	}

	public void setComunicazioneProspettoSpicom(ComunicazioneProspettoDisabiliDTO comunicazioneProspettoSpicom) {
		this.comunicazioneProspettoSpicom = comunicazioneProspettoSpicom;
	}
	

}
