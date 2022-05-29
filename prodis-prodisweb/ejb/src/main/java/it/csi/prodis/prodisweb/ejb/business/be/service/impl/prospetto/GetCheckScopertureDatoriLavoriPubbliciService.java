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

import java.util.List;

import it.csi.prodis.prodisweb.ejb.business.be.dad.ProspettoDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto.GetCheckScopertureDatoriLavoriPubbliciRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto.GetCheckScopertureDatoriLavoriPubbliciResponse;
import it.csi.prodis.prodisweb.ejb.entity.ProDRiepilogoNazionale;
import it.csi.prodis.prodisweb.ejb.entity.ProDRiepilogoProvinciale;
import it.csi.prodis.prodisweb.ejb.util.ProdisSrvUtil;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.dto.error.MsgProdis;

public class GetCheckScopertureDatoriLavoriPubbliciService extends
		BaseProspettoService<GetCheckScopertureDatoriLavoriPubbliciRequest, GetCheckScopertureDatoriLavoriPubbliciResponse> {

	public GetCheckScopertureDatoriLavoriPubbliciService(ConfigurationHelper configurationHelper,
			ProspettoDad prospettoDad) {
		super(configurationHelper, prospettoDad);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getIdProspetto(), "idProspetto");
	}

	@Override
	protected void execute() {
		String esito = "";
		if (ProdisSrvUtil.isNotVoid(request.getIdProspetto())) {
			esito = this.checkWarningPerScoperture(request.getIdProspetto());
		}
		response.setResultCheck(esito);
	}

	public String checkWarningPerScoperture(Long idProspetto) {
		ProDRiepilogoNazionale riepilogoNazionale = prospettoDad.caricaRiepilogoNazionalePerPdf(idProspetto);
		List<ProDRiepilogoProvinciale> riepilogoProvinciale = prospettoDad
				.caricaElencoRiepilogoProvincialeByIdProspetto(idProspetto);
		long totaleScopertureCategorieProtetteProvinciali = 0;
		long totaleScopertureDisabiliProvinciali = 0;
		long numScopertureDisabiliNazionali = 0;
		long numScopertureCategorieProtetteNazionali = 0;
		for (ProDRiepilogoProvinciale prov : riepilogoProvinciale) {
			totaleScopertureCategorieProtetteProvinciali += prov.getNumScopertureCatProt().longValue();
			totaleScopertureDisabiliProvinciali += prov.getNumScopertureDisabili().longValue();
		}
		numScopertureCategorieProtetteNazionali = riepilogoNazionale.getNumScopertCategorieProtette().longValue();
		numScopertureDisabiliNazionali = riepilogoNazionale.getNumScopertDisabili().longValue();

		if (totaleScopertureDisabiliProvinciali != numScopertureDisabiliNazionali
				|| totaleScopertureCategorieProtetteProvinciali != numScopertureCategorieProtetteNazionali) {
			return MsgProdis.PRORIEE0023.getMessage();
		}
		return null;
	}

}
