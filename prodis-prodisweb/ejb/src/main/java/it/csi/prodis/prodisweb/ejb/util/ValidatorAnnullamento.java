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
package it.csi.prodis.prodisweb.ejb.util;

import java.util.Date;
import java.util.List;

import it.csi.prodis.prodisweb.ejb.business.be.dad.ProspettoDad;
import it.csi.prodis.prodisweb.lib.dto.ApiError;
import it.csi.prodis.prodisweb.lib.dto.error.MsgProdis;
import it.csi.prodis.prodisweb.lib.dto.prospetto.Prospetto;

public class ValidatorAnnullamento extends ValidatorUtil {

	ProspettoDad prospettoDad;
	Prospetto  prospetto;
	public ValidatorAnnullamento(ProspettoDad prospettoDad) {
		super();
		this.prospettoDad = prospettoDad;
	}

	public void valida(Long idProspetto, List<ApiError> apiErrors) {
		prospetto = prospettoDad.getProspetto(idProspetto);

		String valoreTabParam = prospettoDad.getParametroByNome(ConstantsProdis.TERMINE_ANNULLAMENTO);
		Date dataTermine = ProdisSrvUtil.convertiStringaInData(valoreTabParam);

		Date dataTimbroPostale = prospetto.getDataTimbroPostale();
		Date dataRiferimentoProspetto = prospetto.getDataRiferimentoProspetto();
		String tipoProvenienza = prospetto.getTipoProvenienza();

		Long idStatoProspetto = prospetto.getStatoProspetto() != null && prospetto.getStatoProspetto().getId() != null ?  prospetto.getStatoProspetto().getId() : 0L;;


		if (idStatoProspetto.intValue() != ConstantsProdis.PROSPETTO_STATO_PRESENTATA
				|| isProvenienzaSilp(ProdisSrvUtil.convertiDataInStringa(dataTimbroPostale))
				|| isProvenienzaMinistero(tipoProvenienza)
				|| isAnnullamentoNonAmmesso(dataTermine)
				|| isProspettoAnniPrecedenti(ProdisSrvUtil.convertiDataInStringa(dataRiferimentoProspetto))) {
			apiErrors.add(MsgProdis.PROANNE0001.getError());
		}
	}

}
