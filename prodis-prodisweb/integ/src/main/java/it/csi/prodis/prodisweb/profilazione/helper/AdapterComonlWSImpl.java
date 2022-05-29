/*-
 * ========================LICENSE_START=================================
 * PRODIS BackEnd - INTEGRATION AAEP, IRIDE, COMONL submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodisweb.profilazione.helper;

import java.util.ArrayList;
import java.util.List;

import it.csi.comonl.comprof.cxfclient.DatiAggiuntiviConsulenteDTO;
import it.csi.comonl.comprof.cxfclient.DatiAggiuntiviImpresaDTO;
import it.csi.comonl.comprof.cxfclient.ParamCallerDTO;
import it.csi.comonl.comprof.cxfclient.ProfileComonlDTO;
import it.csi.prodis.prodisweb.lib.util.log.LogUtil;
import it.csi.prodis.prodisweb.profilazione.Constants;
import it.csi.prodis.prodisweb.profilazione.dto.DatiAggiuntiviConsulente;
import it.csi.prodis.prodisweb.profilazione.dto.DatiAggiuntiviImpresa;
import it.csi.prodis.prodisweb.profilazione.dto.ProfileComonl;
import it.csi.util.performance.StopWatch;

/**
 * Recupero dei dati da COMONL.comprof
 */
public class AdapterComonlWSImpl {

//	private static final Logger logger = Logger.getLogger(Constants.LOGGER);
	protected final LogUtil logger = new LogUtil(getClass());

	public static final String ID_CHIAMANTE = "COMPROF.FRUITORE.ORCH_LAV_PROF";

	public AdapterComonlWSFactory factory = new AdapterComonlWSFactory();

	private static AdapterComonlWSImpl instance = new AdapterComonlWSImpl();

	public static AdapterComonlWSImpl getInstance() {
		return instance;
	}

	public List<ProfileComonl> cercaProfili(String codiceFiscale) throws Exception {
		logger.debug("[AdapterComonlWSImpl::cercaProfili] BEGIN codiceFiscale=" + codiceFiscale, "");

		StopWatch watcher = new StopWatch(Constants.LOGGER);
		watcher.start();

		List<ProfileComonl> result = new ArrayList<>();

		try {

			ParamCallerDTO parametri = new ParamCallerDTO();
			parametri.setCodiceFiscale(codiceFiscale);

			// questo utente chiamante deve essere censito sulla tavola comonl_parametri.
			// invece l'utente BEA � schiantato nella pd del chiamato (comprof, in
			// defpd_comprofsv.xml):
			// <property name="csi.auth.basic" value="FORMAZIONEWEB/ZX_32_67_21"/>
			parametri.setIdentificativoChiamante(ID_CHIAMANTE);
			List<ProfileComonlDTO> resultWS = factory.getService().cercaProfiloWS(parametri);
			if (resultWS != null) {
				for (ProfileComonlDTO pWS : resultWS) {
					result.add(converti(pWS));
				}
			}

			return result;
		} finally {
			watcher.dumpElapsed("AdapterComonlWSImpl", "cercaProfili", "invocazione servizio COMONL.comprof", "");
			watcher.stop();
		}
	}

	/**
	 * Converte i dati restituiti dal servizio di COMONL in DTO di prodis.
	 */
	private ProfileComonl converti(ProfileComonlDTO pWS) {
		ProfileComonl p = new ProfileComonl();
		p.setCapDomicilio(pWS.getCapDomicilio());
		p.setCapResidenza(pWS.getCapResidenza());
		p.setCodiceCittadinanza(pWS.getCodiceCittadinanza());
		p.setCodiceComuneDomicilio(pWS.getCodiceComuneDomicilio());
		p.setCodiceComuneNascita(pWS.getCodiceComuneNascita());
		p.setCodiceComuneResidenza(pWS.getCodiceComuneResidenza());
		p.setCodiceEnte(pWS.getCodiceEnte());
		p.setCodiceFiscaleInput(pWS.getCodiceFiscaleInput());
		p.setCodiceStatoEsteroDomicilio(pWS.getCodiceStatoEsteroDomicilio());
		p.setCodiceStatoEsteroNascita(pWS.getCodiceStatoEsteroNascita());
		p.setCodiceStatoEsteroResidenza(pWS.getCodiceStatoEsteroResidenza());
		p.setCognome(pWS.getCognome());
		p.setComuneDomicilio(pWS.getComuneDomicilio());
		p.setConsulente(converti(pWS.getConsulente()));
		p.setDataFineCarica(pWS.getDataFineCarica());
		p.setDataInsert(pWS.getDataInsert());
		p.setDataNascita(pWS.getDataNascita());
		p.setDataUltimaModifica(pWS.getDataUltimaModifica());
		if (pWS.getDatiAggiuntiviImpresa() != null) {
			List<DatiAggiuntiviImpresa> datiAggiuntiviImpresa = new ArrayList<>();
			for (DatiAggiuntiviImpresaDTO dWS : pWS.getArrayDatiAggiuntiviImpresaDTO().getItem()) {
				datiAggiuntiviImpresa.add(converti(dWS));
			}
			p.setDatiAggiuntiviImpresa(datiAggiuntiviImpresa);
		}
		p.setDelegaValida(pWS.getDelegaValida());
		p.setDescrizioneCittadinanza(pWS.getDescrizioneCittadinanza());
		p.setDescrizioneComuneDomicilio(pWS.getDescrizioneComuneDomicilio());
		p.setDescrizioneComuneNascita(pWS.getDescrizioneComuneNascita());
		p.setDescrizioneComuneResidenza(pWS.getDescrizioneComuneResidenza());
		p.setDescrizioneStatoEsteroDomicilio(pWS.getDescrizioneStatoEsteroDomicilio());
		p.setDescrizioneStatoEsteroNascita(pWS.getCodiceStatoEsteroNascita());
		p.setDescrizioneStatoEsteroResidenza(pWS.getDescrizioneStatoEsteroResidenza());
		p.setEmail(pWS.getEmail());
		p.setFax(pWS.getFax());
		p.setGenere(pWS.getGenere());
		p.setGruppoOperatori(pWS.getGruppoOperatori());
		if (pWS.getIdComDSoggettoAbilitato() != null)
			p.setIdComDSoggettoAbilitato(pWS.getIdComDSoggettoAbilitato().toString());
		p.setIdUserInsert(pWS.getIdUserInsert());
		p.setIdUserUltimaModifica(pWS.getIdUserUltimaModifica());
		p.setIndirizzoDomicilio(pWS.getIndirizzoDomicilio());
		p.setIndirizzoResidenza(pWS.getIndirizzoResidenza());
		p.setNome(pWS.getNome());
		p.setTelefono(pWS.getTelefono());
		p.setTipoAnagrafica(pWS.getTipoAnagrafica());
		return p;
	}

	/**
	 * Converte i dati restituiti dal servizio di COMONL in DTO di prodis.
	 */
	private DatiAggiuntiviConsulente converti(DatiAggiuntiviConsulenteDTO dWS) {
		if (dWS == null)
			return null;
		DatiAggiuntiviConsulente d = new DatiAggiuntiviConsulente();
		d.setCapStudioProfessionale(dWS.getCapStudioProfessionale());
		d.setCodiceComuneStudioProfessionale(dWS.getCodiceComuneStudioProfessionale());
		d.setCodiceFiscaleStudioProfessionale(dWS.getCodiceFiscaleStudioProfessionale());
		d.setCodMinSoggettoAbilitato(dWS.getCodMinSoggettoAbilitato());
		d.setDescrizioneComuneStudioProfessionale(dWS.getDescrizioneComuneStudioProfessionale());
		d.setDescrizioneStudioProfessionale(dWS.getDescrizioneStudioProfessionale());
		d.setFaxStudioProfessionale(dWS.getFaxStudioProfessionale());
		d.setFlgConsulenteAccentrato(dWS.getFlgConsulenteAccentrato());
		d.setIndirizzoStudioProfessionale(dWS.getIndirizzoStudioProfessionale());
		d.setMailStudioProfessionale(dWS.getMailStudioProfessionale());
		d.setPartitaIvaStudioProfessionale(dWS.getPartitaIvaStudioProfessionale());
		d.setTelefonoStudioProfessionale(dWS.getTelefonoStudioProfessionale());
		return d;
	}

	/**
	 * Converte i dati restituiti dal servizio di COMONL in DTO di prodis.
	 */
	private DatiAggiuntiviImpresa converti(DatiAggiuntiviImpresaDTO dWS) {
		DatiAggiuntiviImpresa d = new DatiAggiuntiviImpresa();
		d.setCodiceFiscaleImpresa(dWS.getCodiceFiscaleImpresa());
		d.setDenominazioneImpresaNoAAEP(dWS.getDenominazioneImpresaNoAAEP());
		d.setFlgImpresaAccentrata(dWS.getFlgImpresaAccentrata());
		d.setFlgScuolaPubblica(dWS.getFlgScuolaPubblica());
		return d;
	}

	/**
	 * Consente di testare un'estrazione da COMONL.comprof per uan verifica dei
	 * dati.
	 * 
	 * @param args nessuno
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		AdapterComonlWSImpl.getInstance().cercaProfili("AAAAAA00A11B000J");
	}

}
