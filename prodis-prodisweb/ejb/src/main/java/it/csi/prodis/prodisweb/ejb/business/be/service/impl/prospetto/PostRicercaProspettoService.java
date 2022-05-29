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

import it.csi.prodis.prodisweb.ejb.business.be.dad.CommonDad;
import it.csi.prodis.prodisweb.ejb.business.be.dad.ProspettoDad;
import it.csi.prodis.prodisweb.ejb.business.be.dad.RiepilogoNazionaleDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.prospetto.PostRicercaProspettoRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto.PostRicercaProspettoResponse;
import it.csi.prodis.prodisweb.ejb.util.ConstantsProdis;
import it.csi.prodis.prodisweb.ejb.util.ProdisSrvUtil;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.dto.common.DecodificaGenerica;
import it.csi.prodis.prodisweb.lib.dto.error.MsgProdis;
import it.csi.prodis.prodisweb.lib.dto.prospetto.Prospetto;
import it.csi.prodis.prodisweb.lib.dto.prospetto.RiepilogoNazionale;
import it.csi.prodis.prodisweb.lib.util.pagination.PagedList;

/**
 * PostRicercaProspetto
 */
public class PostRicercaProspettoService
		extends BaseProspettoCommonService<PostRicercaProspettoRequest, PostRicercaProspettoResponse> {
	
	RiepilogoNazionaleDad riepilogoNazionaleDad;

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the helper for the configuration
	 * @param prospettoDad        the DAD for the prospetto
	 */
	public PostRicercaProspettoService(ConfigurationHelper configurationHelper, ProspettoDad prospettoDad,
			CommonDad commonDad,RiepilogoNazionaleDad riepilogoNazionaleDad) {
		super(configurationHelper, prospettoDad, commonDad);
		this.riepilogoNazionaleDad = riepilogoNazionaleDad;
	}

	@Override
	protected void execute() {
		log.error("execute----------->", "Entro nel metodo execute");

		long count = prospettoDad.countRicerca(request.getRicercaProspetto());
		log.error("execute----------->", "valore di count -----> " + count);
		checkBusinessCondition(count > 0, MsgProdis.PROPROP0002.getError());
		DecodificaGenerica ilNumeroMassimoRecord = commonDad
				.getParametroByNome(ConstantsProdis.PARAM_PROSPETTI_NUMERO_MASSIMO);
		int ilNumeroEffettivo = 450;
		if (ProdisSrvUtil.isNotVoid(ilNumeroMassimoRecord)) {
			ilNumeroEffettivo = Integer.parseInt(ilNumeroMassimoRecord.getCodDecodifica());
		}
		 
		
		checkBusinessCondition(count <= ilNumeroEffettivo, MsgProdis.PROPROE0004.getError() );

		if (request.getSize() == 0 || request.getSize() > ilNumeroEffettivo) {
			request.setSize(ilNumeroEffettivo);
		}

		PagedList<Prospetto> prospettos = prospettoDad.getRicerca(request.getPage(), request.getSize(),
				request.getSort(), request.getRicercaProspetto());
		
		for(Prospetto p : prospettos) {
			RiepilogoNazionale rn = riepilogoNazionaleDad.getRiepilogoNazionaleByIdProspetto(p.getId());
			p.setRiepilogoNazionale(rn);
		}
		response.setProspettos(prospettos);
	}

}
