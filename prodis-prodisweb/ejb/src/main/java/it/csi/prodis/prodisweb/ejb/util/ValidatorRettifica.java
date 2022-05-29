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

public class ValidatorRettifica extends ValidatorUtil {

	ProspettoDad prospettoDad;
	Prospetto  prospetto;
	public ValidatorRettifica(ProspettoDad prospettoDad) {
		super();
		this.prospettoDad = prospettoDad;
	}

	public void valida(Long idProspetto, List<ApiError> apiErrors) {
		prospetto = prospettoDad.getProspetto(idProspetto);

		String valoreTabParam = prospettoDad.getParametroByNome(ConstantsProdis.TERMINE_RETTIFICA);
		int intervalloAmmesso = Integer.valueOf(valoreTabParam);

		Date dataTimbroPostale = prospetto.getDataTimbroPostale();
		String tipoProvenienza = prospetto.getTipoProvenienza();

		Long idStatoProspetto = prospetto.getStatoProspetto() != null && prospetto.getStatoProspetto().getId() != null ?  prospetto.getStatoProspetto().getId() : 0L;;
		String  desComunicazione = prospetto.getComunicazione() != null && prospetto.getComunicazione().getDescComunicazione() != null && !"".equalsIgnoreCase(prospetto.getComunicazione().getDescComunicazione()) ? prospetto.getComunicazione().getDescComunicazione(): "";


		if (idStatoProspetto.intValue() != ConstantsProdis.PROSPETTO_STATO_PRESENTATA
				|| desComunicazione.equalsIgnoreCase(ConstantsProdis.TIPO_COMUNICAZIONE_PROSPETTO_ANNULLAMENTO_DESC)
				|| isProvenienzaSilp(ProdisSrvUtil.convertiDataInStringa(dataTimbroPostale))
				|| isProvenienzaMinistero(tipoProvenienza)
				|| isRettificaNonAmmessa(dataTimbroPostale, intervalloAmmesso)) {
			apiErrors.add(MsgProdis.PRORETE0001.getError());
		}
	}

}
