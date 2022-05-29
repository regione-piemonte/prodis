/*-
 * ========================LICENSE_START=================================
 * PRODIS Servizi - EJB submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodissrv.ejb.business.be.service.impl.prospetto;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import it.csi.prodis.prodissrv.ejb.business.be.dad.ProspettoDad;
import it.csi.prodis.prodissrv.ejb.business.be.service.request.prospetto.PostFilterTestataProspettoRequest;
import it.csi.prodis.prodissrv.ejb.business.be.service.response.prospetto.PostFilterTestataProspettoResponse;
import it.csi.prodis.prodissrv.ejb.exception.BusinessException;
import it.csi.prodis.prodissrv.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodissrv.lib.dto.error.MsgProdis;
import it.csi.prodis.prodissrv.lib.dto.prospetto.Prospetto;
import it.csi.prodis.prodissrv.lib.dto.prospetto.TestataProspetto;
import it.csi.prodis.prodissrv.lib.util.pagination.PagedList;

/**
 * Retrieves an list of prospetto
 */
public class PostFilterTestataProspettoService
		extends BaseProspettoService<PostFilterTestataProspettoRequest, PostFilterTestataProspettoResponse> {

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the configuration helper
	 * @param testataOrdineDad    the testataOrdine DAD
	 */
	public PostFilterTestataProspettoService(ConfigurationHelper configurationHelper, ProspettoDad prospettoDad) {
		super(configurationHelper, prospettoDad);
	}

	@Override
	protected void checkServiceParams() {
		// checkNotNull(request.getId(), "id");
	}

	@Override
	protected void execute() {
		final String methodName = "execute";

		try {
			// verifica se utente è autorizzato
			String caller = request.getFilterTestataProspetto().getCaller();
			checkBusinessCondition(!StringUtils.isEmpty(caller), MsgProdis.SEC00001.getError());

			List<String> callers = Arrays.asList(getCallers());
			boolean bCallerAuthorized = callers.contains(caller);
			checkBusinessCondition(bCallerAuthorized, MsgProdis.SEC00001.getError());

			// verifica se è stata specificata la lista di CF azienda
			boolean bListaCfSpecificata = request.getFilterTestataProspetto().getListCodiceFiscaleAzienda() != null
					&& request.getFilterTestataProspetto().getListCodiceFiscaleAzienda().size() > 0;
			checkBusinessCondition(bListaCfSpecificata, MsgProdis.CR00001.getError());

			boolean bListaCfSpecificataMax = request.getFilterTestataProspetto().getListCodiceFiscaleAzienda()
					.size() <= 200;
			checkBusinessCondition(bListaCfSpecificataMax, MsgProdis.CR00002.getError());

			PagedList<Prospetto> prospettos = prospettoDad.getRicerca(0, 0, null, request.getFilterTestataProspetto());
			if (prospettos.getTotalElements() == 0) {
				checkBusinessCondition(false, MsgProdis.ANA0001.getError());
			}

			List<TestataProspetto> listTestataProspetto = new ArrayList<TestataProspetto>();
			for (Iterator iterator = prospettos.getList().iterator(); iterator.hasNext();) {
				Prospetto prospetto = (Prospetto) iterator.next();

				TestataProspetto testataProspetto = new TestataProspetto();

				if (prospetto.getAnnoProtocollo() != null) {
					testataProspetto.setAnnoProtocollo(prospetto.getAnnoProtocollo().longValueExact());
				}

				testataProspetto.setCfAzienda(prospetto.getDatiAzienda().getCfAzienda());
				testataProspetto.setCodiceRegionale(prospetto.getCodiceComunicazione());
				testataProspetto.setDataProtocollo(prospetto.getDataProtocollo());
				testataProspetto.setDataRiferimentoProspetto(prospetto.getDataRiferimentoProspetto());
				testataProspetto.setDataTimbroPostale(prospetto.getDataTimbroPostale());
				testataProspetto
						.setDenominazioneDatoreDiLavoro(prospetto.getDatiAzienda().getDenominazioneDatoreLavoro());
				testataProspetto.setDescrizioneComunicazione(prospetto.getComunicazione().getDescComunicazione());
				testataProspetto.setIdProspetto(prospetto.getId());

				String numProtRif = prospetto.getAnnoProtocollo() + "/" + prospetto.getNumeroProtocollo();
				testataProspetto.setNumeroProtocolloRiferimento(numProtRif);

				testataProspetto.setPivaAzienda(prospetto.getDatiAzienda().getCfAzienda());
				testataProspetto.setStatoProspettoDescrizione(prospetto.getStatoProspetto().getDescrizione());
				testataProspetto.setStatoProspettoId(prospetto.getStatoProspetto().getId());
				testataProspetto.setTipoProvenienza(prospetto.getTipoProvenienza());

				// Per ogni prospetto vado a recuperare l'elenco dei codici ministeriali delle
				// province del prospetto
				List<String> elencoCodProvince = prospettoDad.getCodiceMinProvinceByIdProspetto(prospetto.getId());
				testataProspetto.setElencoCodProvince(elencoCodProvince);

				listTestataProspetto.add(testataProspetto);
			}
//			response.addApiError(MsgProdis.OK000001.getError());
			response.setProspettos(listTestataProspetto);

		} catch (BusinessException e) {
			log.error(methodName, e, e);
			response.addApiError(MsgProdis.DBA00001.getError());
			throw e;
		} catch (Exception e) {
			log.error(methodName, e, e);
			response.addApiError(MsgProdis.DBA00001.getError());
		}
	}

	public String[] getCallers() throws IOException {
		final String CALLER_RESOURCE = "/caller.properties";
		InputStream is = this.getClass().getResourceAsStream(CALLER_RESOURCE);
		Properties properties = new Properties();
		properties.load(is);

		String caller = properties.getProperty("caller");
		String[] callers = caller.split(",");
		return callers;
	}

}
