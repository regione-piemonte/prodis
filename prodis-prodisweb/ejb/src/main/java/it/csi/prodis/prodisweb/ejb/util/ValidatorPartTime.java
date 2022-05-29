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

import it.csi.prodis.prodisweb.ejb.business.be.dad.PartTimeDad;
import it.csi.prodis.prodisweb.ejb.entity.ProDPartTime;
import it.csi.prodis.prodisweb.lib.dto.ApiError;
import it.csi.prodis.prodisweb.lib.dto.error.MsgProdis;
import it.csi.prodis.prodisweb.lib.dto.prospetto.PartTime;

public class ValidatorPartTime extends ValidatorUtil{


	PartTimeDad partTimeDad;

	public ValidatorPartTime(PartTimeDad partTimeDad) {
		super();
		this.partTimeDad = partTimeDad;
	}
	public void valida(PartTime partTime, List<ApiError> apiErrors) {
		if (partTime.getTipoRipropPt() == null || partTime.getTipoRipropPt().getId() == null) {
			apiErrors.add(MsgProdis.PROPTME0001.getError());
		}
		boolean orarioInserito = true;

		if (partTime.getOrarioSettContrattualeMin() == null) {
			apiErrors.add(MsgProdis.PROPTME0002.getError());
			orarioInserito = false;
		} else if (partTime.getOrarioSettContrattualeMin().equals(new BigDecimal(0))) {
			apiErrors.add(MsgProdis.PROPTME0003.getError());
			orarioInserito = false;
		}
		if (partTime.getOrarioSettPartTimeMin() == null) {
			apiErrors.add(MsgProdis.PROPTME0004.getError());
			orarioInserito = false;
		} else if (partTime.getOrarioSettPartTimeMin().equals(new BigDecimal(0))) {
			apiErrors.add(MsgProdis.PROPTME0005.getError());
			orarioInserito = false;
		}
		if (partTime.getnPartTime() == null) {
			apiErrors.add(MsgProdis.PROPTME0006.getError());
		} else {
			if (partTime.getnPartTime().compareTo(new BigDecimal(0))  != 1 ){
				apiErrors.add(MsgProdis.PROPTME0007.getError());
			}
		}

		if (orarioInserito) {
			int orarioSettContr = partTime.getOrarioSettContrattualeMin().intValue();
			int orarioSettPt = partTime.getOrarioSettPartTimeMin().intValue();
			
			if(orarioSettContr <= orarioSettPt)  {
				apiErrors.add(MsgProdis.PROPTME0008.getError());

			} else {
				List<ProDPartTime> listaAltri = partTimeDad.getListaPartTimeByIdProspettoProvincia(partTime.getIdProspettoProv());
				if (listaAltri != null && listaAltri.size() > 0) {
					for (ProDPartTime pt : listaAltri) {
						if (partTime.getId() == null || partTime.getId().longValue() != pt.getIdPartTime() ) {
							if (partTime.getTipoRipropPt() != null && partTime.getTipoRipropPt().getId() != null) {
								if (partTime.getTipoRipropPt().getId().equals(pt.getProTTipoRipropPt().getId())) {
									int aOrarioSettContr = pt.getOrarioSettContrattualeMin().intValue();
									int aOrarioSettPt    = pt.getOrarioSettPartTimeMin().intValue();
									if (aOrarioSettContr == orarioSettContr
											&& aOrarioSettPt == orarioSettPt) {
										apiErrors.add(MsgProdis.PROPTME0009.getError());
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
