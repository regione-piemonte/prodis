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

import java.util.List;

import it.csi.prodis.prodisweb.ejb.business.be.dad.CompensazioniDad;
import it.csi.prodis.prodisweb.ejb.business.be.dad.DatiProvincialiDad;
import it.csi.prodis.prodisweb.ejb.business.be.dad.PostiLavoroDispDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.datiprovinciali.GetProspettoProvinciaByIdProspettoRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.datiprovinciali.GetProspettoProvinciaByIdProspettoResponse;
import it.csi.prodis.prodisweb.ejb.entity.ProDPostiLavoroDisp;
import it.csi.prodis.prodisweb.ejb.entity.ProDProvCompensazioni;
import it.csi.prodis.prodisweb.ejb.mapper.ProdisMappers;
import it.csi.prodis.prodisweb.ejb.util.ProdisSrvUtil;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.dto.prospetto.DatiProvinciali;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProspettoProvincia;
import it.csi.prodis.prodisweb.lib.dto.prospetto.RiepilogoProvinciale;

/**
 * Retrieves an testataOrdine by its id
 */
public class GetProspettoProvinciaByIdProspettoService extends
		BaseDatiProvincialiService<GetProspettoProvinciaByIdProspettoRequest, GetProspettoProvinciaByIdProspettoResponse> {

	private CompensazioniDad compensazioniDad;
	private PostiLavoroDispDad postiLavoroDispDad;

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad    the testataOrdine DAD
	 */
	public GetProspettoProvinciaByIdProspettoService(ConfigurationHelper configurationHelper,
			DatiProvincialiDad datiProvincialiDad, CompensazioniDad compensazioniDad,
			PostiLavoroDispDad postiLavoroDispDad) {
		super(configurationHelper, datiProvincialiDad);
		this.compensazioniDad = compensazioniDad;
		this.postiLavoroDispDad = postiLavoroDispDad;
	}

	@Override
	protected void checkServiceParams() {
		checkNotNull(request.getIdProspetto(), "idProspetto");
	}

	@Override
	protected void execute() {
		List<ProspettoProvincia> list = datiProvincialiDad.getProspettoProvinciaByIdProspetto(request.getIdProspetto());

		/*
		 * Da forzare il caricamento delle compensazioni e dei posti di lavoro disp
		 * perche' con il Transient non vengono più mappati nell'oggetto
		 */
		for (ProspettoProvincia ilProspettoProvincia : list) {
			if (ProdisSrvUtil.isNotVoid(ilProspettoProvincia.getDatiProvinciali())) {
				DatiProvinciali ilDatoProvinciale = ilProspettoProvincia.getDatiProvinciali();
				List<ProDProvCompensazioni> elencoCompensazioni = compensazioniDad
						.getElencoCompensazioniByIdProspettoProv(ilDatoProvinciale.getId().longValue());
				if (!elencoCompensazioni.isEmpty()) {
					ilDatoProvinciale
							.setProvCompensazionis(ProdisMappers.PROV_COMPENSAZIONI.toModels(elencoCompensazioni));
				}
				List<ProDPostiLavoroDisp> elencoPostiDispo = postiLavoroDispDad
						.getElencoPostiLavoroDispByIdProspettoProv(ilDatoProvinciale.getId().longValue());
				if (!elencoPostiDispo.isEmpty()) {
					ilDatoProvinciale.setPostiLavoroDisps(ProdisMappers.POSTI_LAVORO_DISP.toModels(elencoPostiDispo));
				}

			}
			/* Forzo il caricamento del riepilogo provinciale */
			RiepilogoProvinciale rp = compensazioniDad
					.getRiepilogoProvincialeByidProspettoProv(Long.valueOf(ilProspettoProvincia.getId()));
			if (ProdisSrvUtil.isNotVoid(rp)) {
				ilProspettoProvincia.setRiepilogoProvinciale(rp);
			}
		}

		response.setProspettoProvincias(list);
	}

}
