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
package it.csi.prodis.prodisweb.ejb.business.be.service.impl.parttime;

import java.math.BigDecimal;

import it.csi.prodis.prodisweb.ejb.business.be.dad.IntermittentiDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.parttime.DeleteIntermittentiRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.parttime.DeleteIntermittentiResponse;
import it.csi.prodis.prodisweb.ejb.exception.NotFoundException;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.dto.error.MsgProdis;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProvIntermittenti;

public class DeleteIntermittentiService extends BaseIntermittentiService<DeleteIntermittentiRequest, DeleteIntermittentiResponse> {
	/**
	 * Constructor
	 * 
	 * @param configurationHelper the helper for the configuration
	 * @param intermittentiDad        the DAD for the intermittenti
	 */
	public DeleteIntermittentiService(ConfigurationHelper configurationHelper, IntermittentiDad intermittentiDad) {
		super(configurationHelper, intermittentiDad);
	}

	@Override
	protected void execute() {
		
		Long idIntermittenti = request.getIdIntermittenti();
		
		ProvIntermittenti intermittentiPerControllo = intermittentiDad.cercaIntermittentiPerControllo(idIntermittenti);
		
		checkBusinessCondition(validaIntermittenti(intermittentiPerControllo.getnIntermittenti(), 
				intermittentiPerControllo.getOrarioSettimanaleContrattual(),
				intermittentiPerControllo.getOrarioSettimanaleSvolto()),MsgProdis.PROPROE0020.getError());
		
		
		ProvIntermittenti intermittentiDeleted = null;
		if (idIntermittenti!=null) {
			intermittentiDeleted = intermittentiDad.deleteSingleIntermittenti(idIntermittenti);
		} else {
			throw new NotFoundException("Intermittenti");
		}

		response.setIntermittenti(intermittentiDeleted);
		
	}
	private boolean validaIntermittenti (BigDecimal numero, BigDecimal orarioSettimanaleContrattuale, BigDecimal orarioSettimanaleSvolto) {
		if (numero.equals(new BigDecimal(0))
				&& orarioSettimanaleContrattuale.equals(new BigDecimal(0))
				&& orarioSettimanaleSvolto.equals(new BigDecimal(0))) {
			return true;
		} 
		if (numero.equals(new BigDecimal(0))
				|| orarioSettimanaleContrattuale.equals(new BigDecimal(0))
				|| orarioSettimanaleSvolto.equals(new BigDecimal(0))) {
			//se qualcuno dei dati non è valorizzato do eccezione
			return false;
		}
		return true;
	}
}
