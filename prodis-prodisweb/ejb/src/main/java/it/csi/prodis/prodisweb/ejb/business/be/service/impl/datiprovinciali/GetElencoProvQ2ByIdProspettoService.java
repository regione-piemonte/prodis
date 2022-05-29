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
package it.csi.prodis.prodisweb.ejb.business.be.service.impl.datiprovinciali;

import java.math.BigDecimal;
import java.util.List;

import it.csi.prodis.prodisweb.ejb.business.be.dad.DatiProvincialiDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.datiprovinciali.GetElencoProvQ2ByIdProspettoRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.datiprovinciali.GetElencoProvQ2ByIdProspettoResponse;
import it.csi.prodis.prodisweb.ejb.util.ProdisSrvUtil;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.dto.prospetto.VistaElencoProvQ2;

/**
 * Retrieves an testataOrdine by its id
 */
public class GetElencoProvQ2ByIdProspettoService
		extends BaseDatiProvincialiService<GetElencoProvQ2ByIdProspettoRequest, GetElencoProvQ2ByIdProspettoResponse> {

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad    the testataOrdine DAD
	 */
	public GetElencoProvQ2ByIdProspettoService(ConfigurationHelper configurationHelper,
			DatiProvincialiDad datiProvincialiDad) {
		super(configurationHelper, datiProvincialiDad);
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getIdProspetto(), "idProspetto");
	}

	@Override
	protected void execute() {
		List<VistaElencoProvQ2> list = datiProvincialiDad.getElencoProvQ2ByIdProspetto(request.getIdProspetto());
		if (list != null && list.size() > 0) {
			for (VistaElencoProvQ2 v : list) {
				if (ProdisSrvUtil.isVoid(v.getNumPartTime())) {
					v.setNumPartTime(BigDecimal.ZERO);
				}
				if (ProdisSrvUtil.isVoid(v.getNumIntermittenti())) {
					v.setNumIntermittenti(BigDecimal.ZERO);
				}

				v.setPtIntermittenti(v.getNumPartTime().add(v.getNumIntermittenti()));

				if (ProdisSrvUtil.isVoid(v.getNumCateProtForza())) {
					v.setNumCateProtForza(BigDecimal.ZERO);
				}
				if (ProdisSrvUtil.isVoid(v.getNumCentralTelefoNonvedenti())) {
					v.setNumCentralTelefoNonvedenti(BigDecimal.ZERO);
				}
				if (ProdisSrvUtil.isVoid(v.getNumTerariabMassofisNonved())) {
					v.setNumTerariabMassofisNonved(BigDecimal.ZERO);
				}
				if (ProdisSrvUtil.isVoid(v.getNumSomministratiFt())) {
					v.setNumSomministratiFt(BigDecimal.ZERO);
				}
				if (ProdisSrvUtil.isVoid(v.getNumConvenzioni12bis14Ft())) {
					v.setNumConvenzioni12bis14Ft(BigDecimal.ZERO);
				}
				
				if (ProdisSrvUtil.isVoid(v.getNumDisabiliInForza())) {
					v.setNumDisabiliInForza(BigDecimal.ZERO);
				}
				
				if (ProdisSrvUtil.isVoid(v.getNumCentralTelefoNonvedenti())) {
					v.setNumCentralTelefoNonvedenti(BigDecimal.ZERO);
				}
				
				v.setDisabiliTempoPienoFt(v.getNumDisabiliInForza().add(v.getNumCentralTelefoNonvedenti())
						.add(v.getNumTerariabMassofisNonved()).add(v.getNumSomministratiFt())
						.add(v.getNumConvenzioni12bis14Ft()));

				if (ProdisSrvUtil.isVoid(v.getNumTelelavoroFt())) {
					v.setNumTelelavoroFt(BigDecimal.ZERO);
				}
				if (ProdisSrvUtil.isVoid(v.getNumLavAppartartCategoria())) {
					v.setNumLavAppartartCategoria(BigDecimal.ZERO);
				}
				if (ProdisSrvUtil.isVoid(v.getNumPostiDisp())) {
					v.setNumPostiDisp(BigDecimal.ZERO);
				}
				if (ProdisSrvUtil.isVoid(v.getNumLavoratoriEsonero())) {
					v.setNumLavoratoriEsonero(BigDecimal.ZERO);
				}
				if (ProdisSrvUtil.isVoid(v.getNumAssunzioniEffDopoTrasf())) {
					v.setNumAssunzioniEffDopoTrasf(BigDecimal.ZERO);
				}
				
				if (ProdisSrvUtil.isVoid(v.getNumTelelavoroPt())) {
					v.setNumTelelavoroPt(BigDecimal.ZERO);
				}

				v.setTeleLavCatEscluseDisab(
						v.getNumTelelavoroFt().add(v.getNumLavAppartartCategoria()).add(v.getNumTelelavoroPt()));
			}
		}
		response.setVistaElencoProvQ2(list);
	}

}
