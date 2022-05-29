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
package it.csi.prodis.prodisweb.ejb.business.be.service.impl.prospetto;

import it.csi.prodis.prodisweb.ejb.business.be.dad.ProspettoDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto.PutStatoProspettoRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto.PutStatoProspettoResponse;
import it.csi.prodis.prodisweb.ejb.util.ConstantsProdis;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Comunicazione;
import it.csi.prodis.prodisweb.lib.dto.prospetto.Prospetto;

public class PutStatoProspettoService
		extends BaseProspettoService<PutStatoProspettoRequest, PutStatoProspettoResponse> {
	
	
	public PutStatoProspettoService(ConfigurationHelper configurationHelper, ProspettoDad prospettoDad) {
		super(configurationHelper, prospettoDad);
	}
	
	
	@Override
	protected void checkServiceParams() {
		 checkNotNull(request.getIdProspetto(), "idProspetto", true);
		 checkNotNull(request.getIdStatoProspetto(), "idStatoProspetto", true);
	}
	
	
	@Override
	protected void execute() {
		Long idProspetto = request.getIdProspetto();
		Prospetto prospetto = prospettoDad.getProspetto(idProspetto);
		
		Comunicazione comunicazione = prospetto.getComunicazione();
		
		if(comunicazione != null) {
			String codComunicazione = comunicazione.getCodComunicazione();
			if(codComunicazione != null) {
				boolean isRettifica = codComunicazione.equals(ConstantsProdis.TIPO_COMUNICAZIONE_PROSPETTO_RETTIFICA_COD);
				if(isRettifica) {
					Long idProspettoPrecedente = prospetto.getIdProspettoPrecedente();
					prospettoDad.updateStatoProspetto(idProspettoPrecedente,ConstantsProdis.PROSPETTO_STATO_PRESENTATA_ID);
					
				}
				boolean isAnnullo = codComunicazione.equals(ConstantsProdis.TIPO_COMUNICAZIONE_PROSPETTO_ANNULLAMENTO_COD);
				if(isAnnullo) {
					Long idProspettoPrecedente = prospetto.getIdProspettoPrecedente();
					prospettoDad.updateStatoProspetto(idProspettoPrecedente,ConstantsProdis.PROSPETTO_STATO_PRESENTATA_ID);
					
				}
			}
		}
		
		prospettoDad.updateStatoProspetto(request.getIdProspetto(), request.getIdStatoProspetto());
	}
}
