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

import java.math.BigDecimal;
import java.util.List;

import it.csi.prodis.prodisweb.ejb.business.be.dad.IntermittentiDad;
import it.csi.prodis.prodisweb.ejb.entity.ProDProvIntermittenti;
import it.csi.prodis.prodisweb.lib.dto.ApiError;
import it.csi.prodis.prodisweb.lib.dto.error.MsgProdis;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProvIntermittenti;

public class ValidatorIntermittenti extends ValidatorUtil{


	IntermittentiDad intermittentiDad;

	public ValidatorIntermittenti(IntermittentiDad intermittentiDad) {
		super();
		this.intermittentiDad = intermittentiDad;
	}
	public void valida(ProvIntermittenti intermittenti, List<ApiError> apiErrors) {
		boolean orarioInserito = true;

		if (intermittenti.getOrarioSettimanaleContrattual() == null) {
			apiErrors.add(MsgProdis.PROINTE0002.getError());
			orarioInserito = false;
		} else if (intermittenti.getOrarioSettimanaleContrattual().equals(new BigDecimal(0))) {
			apiErrors.add(MsgProdis.PROINTE0003.getError());
			orarioInserito = false;
		}
		if (intermittenti.getOrarioSettimanaleSvolto() == null) {
			apiErrors.add(MsgProdis.PROINTE0004.getError());
			orarioInserito = false;
		} else if (intermittenti.getOrarioSettimanaleSvolto().equals(new BigDecimal(0))) {
			apiErrors.add(MsgProdis.PROINTE0005.getError());
			orarioInserito = false;
		}
		if (intermittenti.getnIntermittenti() == null) {
			apiErrors.add(MsgProdis.PROINTE0006.getError());
		} else {
			if (intermittenti.getnIntermittenti().compareTo(new BigDecimal(0))  != 1 ){
				apiErrors.add(MsgProdis.PROINTE0007.getError());
			}
		}

		if (orarioInserito) {
			int orarioSettContr = intermittenti.getOrarioSettimanaleContrattual().intValue();
			int orarioSettPt = intermittenti.getOrarioSettimanaleSvolto().intValue();

			if(orarioSettContr <= orarioSettPt)  {
				apiErrors.add(MsgProdis.PROINTE0008.getError());

			} else {
				List<ProDProvIntermittenti> listaAltri = intermittentiDad.getListaIntermittentiByIdProspettoProvincia(intermittenti.getIdProspettoProv());
				if (listaAltri != null && listaAltri.size() > 0) {
					for (ProDProvIntermittenti inter : listaAltri) {
						if (intermittenti.getId() == null || intermittenti.getId().longValue() != inter.getIdIntermittenti() ) {
							int aOrarioSettContr = inter.getOrarioSettimanaleContrattual().intValue();
							int aOrarioSettPt    = inter.getOrarioSettimanaleSvolto().intValue();
							if (aOrarioSettContr == orarioSettContr
									&& aOrarioSettPt == orarioSettPt) {
								apiErrors.add(MsgProdis.PROINTE0009.getError());
							}
						}
					}
				}
			}
		}
	}
}
