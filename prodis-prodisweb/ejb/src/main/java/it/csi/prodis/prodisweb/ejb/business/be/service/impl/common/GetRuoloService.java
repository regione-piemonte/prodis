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
package it.csi.prodis.prodisweb.ejb.business.be.service.impl.common;

import java.util.ArrayList;
import java.util.List;

import it.csi.iride2.policy.entity.Identita;
import it.csi.prodis.prodisweb.ejb.business.be.dad.CommonDad;
import it.csi.prodis.prodisweb.ejb.business.be.dad.DecodificaDad;
import it.csi.prodis.prodisweb.ejb.business.be.service.request.common.GetRuoloRequest;
import it.csi.prodis.prodisweb.ejb.business.be.service.response.common.GetRuoloResponse;
import it.csi.prodis.prodisweb.ejb.util.ProdisThreadLocalContainer;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;
import it.csi.prodis.prodisweb.lib.dto.common.Ruolo;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Soggetti;
import it.csi.prodis.prodisweb.lib.util.ProdisClassUtils;
import it.csi.prodis.prodisweb.profilazione.Constants;
import it.csi.prodis.prodisweb.profilazione.dto.Profilo;
import it.csi.prodis.prodisweb.profilazione.dto.ProfiloUtenteProdis;
import it.csi.prodis.prodisweb.profilazione.helper.ProfilazioneHelper;

// import org.apache.cxf.jaxws.EndpointImpl;
// import org.apache.cxf.interceptor.InterceptorProvider;

/**
 * GetRuolo
 */
public class GetRuoloService extends BaseCommonService<GetRuoloRequest, GetRuoloResponse> {

	DecodificaDad decodificaDad;

	/**
	 * Constructor
	 * 
	 * @param configurationHelper the helper for the configuration
	 * @param ruoloDad            the DAD for the ruolo
	 */
	public GetRuoloService(ConfigurationHelper configurationHelper, CommonDad commonDad, DecodificaDad decodificaDad) {
		super(configurationHelper, commonDad);
		this.decodificaDad = decodificaDad;
	}

	@Override
	protected void execute() {
		try {
			List<Ruolo> ruolos = new ArrayList<>();
			Identita identita = ProdisThreadLocalContainer.IDENTITA.get();
			ProfilazioneHelper helper = new ProfilazioneHelper();
			ProfiloUtenteProdis p = helper.ricercaProfiloUtente(identita);
			for (Profilo profilo : p.getListaRuoliAmmessi()) {
				Ruolo ruolo = new Ruolo();
				ruolo.setDenominazioneAzienda(profilo.getDenominazioneAzienda());
				ruolo.setCodiceFiscale(profilo.getCodiceFiscale());
				ruolo.setCfUtente(profilo.getCfUtente());
				ruolo.setRuolo(profilo.getRuolo());
				ruolo.setCodMinSoggettoAbilitato(profilo.getCodMinSoggettoAbilitato());
				if (ruolo.getCodMinSoggettoAbilitato() != null) {
					Soggetti s = decodificaDad.getSoggettoByCodice(profilo.getCodMinSoggettoAbilitato());
					if (s != null) {
						ruolo.setIdSoggetti(s.getId());
					}
				}
				ruolo.setListaCasiUso(profilo.getListaCasiUso());
				determinaRuoloUtente(profilo.getRuolo(), ruolo);
				ruolos.add(ruolo);
			}

			response.setRuolos(ruolos);
		} catch (Exception e) {
			log.error(ProdisClassUtils.truncClassName(getClass()) + " Eccezione !!", e);
			throw new RuntimeException(e);
		}
	}

	private void determinaRuoloUtente(String descrizioneRuolo, Ruolo ilRuolo) {
		if (descrizioneRuolo.equals(Constants.LEGALE_RAPPRESENTANTE_COMONL_DESC)) {
			ilRuolo.setLegaleRappresentante(true);
		} else if (descrizioneRuolo.equals(Constants.AMMINISTRATORE_PRODIS_DESC)) {
			ilRuolo.setAmministratore(true);
		} else if (descrizioneRuolo.equals(Constants.OPERATORE_PROVINCIALE_PRODIS_DESC)) {
			ilRuolo.setOperatoreProvinciale(true);
		} else if (descrizioneRuolo.equals(Constants.MONITORAGGIO_CSI_DESC)) {
			ilRuolo.setMonitoraggio(true);
		} else if (descrizioneRuolo.equals(Constants.PERS_AUTORIZZATA_COMONL_DESC)) {
			ilRuolo.setPersonaAutorizzata(true);
		} else if (descrizioneRuolo.equals(Constants.PERS_AUTORIZZATA_SCUOLA_COMONL_DESC)) {
			ilRuolo.setPersonaAutorizzataScuola(true);
		} else if (descrizioneRuolo.equals(Constants.PERSONA_CARICA_AZIENDALE_COMONL_DESC)) {
			ilRuolo.setPersonaCaricaAziendale(true);
		} else if (descrizioneRuolo.equals(Constants.DELEGATO_RESPONSABILE_COMONL_DESC)) {
			ilRuolo.setDelegatoRespo(true);
		} else if (descrizioneRuolo.equals(Constants.CONSULENTE_RESPONSABILE_COMONL_DESC)) {
			ilRuolo.setConsulenteRespo(true);
		}
	}
}
