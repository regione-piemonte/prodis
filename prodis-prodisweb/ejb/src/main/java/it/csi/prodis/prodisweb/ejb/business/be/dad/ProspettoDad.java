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
package it.csi.prodis.prodisweb.ejb.business.be.dad;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import it.csi.prodis.prodisweb.ejb.business.be.dad.sort.ProspettoSort;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDAssPubblicheDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDCategorieEscluseDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDDatiAziendaDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDDatiProvincialiDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDLavoratoriInForzaDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDParametriDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDPartTimeDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDPdfProspettoDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDPostiLavoroDispDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDProspettoDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDProspettoGradualitaDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDProvCompensazioniDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDProvConvenzioneDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDProvEsoneroAutocertDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDProvEsoneroDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDProvGradualitaDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDProvIntermittentiDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDProvSospensioneDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDRiepilogoNazionaleDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDRiepilogoProvincialeDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDSedeLegaleDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProRProspettoProvinciaDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProTAtecofinDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProTCcnlDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProTComuneDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProTCpiDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProTProvinciaDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProTRegioneDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProTStatoProspettoDao;
import it.csi.prodis.prodisweb.ejb.entity.EsitoStoreProcedure;
import it.csi.prodis.prodisweb.ejb.entity.ProDAssPubbliche;
import it.csi.prodis.prodisweb.ejb.entity.ProDAssPubblichePK;
import it.csi.prodis.prodisweb.ejb.entity.ProDCategorieEscluse;
import it.csi.prodis.prodisweb.ejb.entity.ProDDatiAzienda;
import it.csi.prodis.prodisweb.ejb.entity.ProDDatiProvinciali;
import it.csi.prodis.prodisweb.ejb.entity.ProDLavoratoriInForza;
import it.csi.prodis.prodisweb.ejb.entity.ProDParametri;
import it.csi.prodis.prodisweb.ejb.entity.ProDPartTime;
import it.csi.prodis.prodisweb.ejb.entity.ProDPdfProspetto;
import it.csi.prodis.prodisweb.ejb.entity.ProDPostiLavoroDisp;
import it.csi.prodis.prodisweb.ejb.entity.ProDProspetto;
import it.csi.prodis.prodisweb.ejb.entity.ProDProspettoGradualita;
import it.csi.prodis.prodisweb.ejb.entity.ProDProvCompensazioni;
import it.csi.prodis.prodisweb.ejb.entity.ProDProvConvenzione;
import it.csi.prodis.prodisweb.ejb.entity.ProDProvEsonero;
import it.csi.prodis.prodisweb.ejb.entity.ProDProvEsoneroAutocert;
import it.csi.prodis.prodisweb.ejb.entity.ProDProvGradualita;
import it.csi.prodis.prodisweb.ejb.entity.ProDProvIntermittenti;
import it.csi.prodis.prodisweb.ejb.entity.ProDProvSospensione;
import it.csi.prodis.prodisweb.ejb.entity.ProDRiepilogoNazionale;
import it.csi.prodis.prodisweb.ejb.entity.ProDRiepilogoProvinciale;
import it.csi.prodis.prodisweb.ejb.entity.ProDSedeLegale;
import it.csi.prodis.prodisweb.ejb.entity.ProRProspettoProvincia;
import it.csi.prodis.prodisweb.ejb.entity.ProTAtecofin;
import it.csi.prodis.prodisweb.ejb.entity.ProTCcnl;
import it.csi.prodis.prodisweb.ejb.entity.ProTComune;
import it.csi.prodis.prodisweb.ejb.entity.ProTCpi;
import it.csi.prodis.prodisweb.ejb.entity.ProTProvincia;
import it.csi.prodis.prodisweb.ejb.entity.ProTRegione;
import it.csi.prodis.prodisweb.ejb.entity.ProTStatoProspetto;
import it.csi.prodis.prodisweb.ejb.entity.RitornoEseguiOperazione;
import it.csi.prodis.prodisweb.ejb.exception.NotFoundException;
import it.csi.prodis.prodisweb.ejb.mapper.ProdisMappers;
import it.csi.prodis.prodisweb.ejb.util.ConstantsProdis;
import it.csi.prodis.prodisweb.ejb.util.ProdisSrvUtil;
import it.csi.prodis.prodisweb.ejb.util.jpa.Page;
import it.csi.prodis.prodisweb.lib.dto.common.DecodificaGenerica;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Cpi;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Provincia;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.StatoProspetto;
import it.csi.prodis.prodisweb.lib.dto.prospetto.AssPubbliche;
import it.csi.prodis.prodisweb.lib.dto.prospetto.AssPubblichePK;
import it.csi.prodis.prodisweb.lib.dto.prospetto.DatiAzienda;
import it.csi.prodis.prodisweb.lib.dto.prospetto.DatiProvinciali;
import it.csi.prodis.prodisweb.lib.dto.prospetto.Prospetto;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProspettoGradualita;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProspettoProvincia;
import it.csi.prodis.prodisweb.lib.dto.prospetto.RicercaProspetto;
import it.csi.prodis.prodisweb.lib.dto.prospetto.SedeLegale;
import it.csi.prodis.prodisweb.lib.util.pagination.PagedList;
import it.csi.prodis.prodisweb.lib.util.pagination.Sort;

/**
 * Data Access Delegate for prospetto
 */
@ApplicationScoped
public class ProspettoDad extends BaseDad {

	@Inject
	private ProDProspettoDao proDProspettoDao;

	@Inject
	private ProDAssPubblicheDao proDAssPubblicheDao;

	@Inject
	private ProTRegioneDao proTRegioneDao;

	@Inject
	private ProDDatiAziendaDao proDDatiAziendaDao;

	@Inject
	private ProDSedeLegaleDao proDSedeLegaleDao;

	@Inject
	private ProDProspettoGradualitaDao proDProspettoGradualitaDao;

	@Inject
	private ProTComuneDao proTComuneDao;

	@Inject
	private ProTAtecofinDao proTAtecofinDao;

	@Inject
	private ProTCcnlDao proTCcnlDao;

	@Inject
	private ProTStatoProspettoDao proTStatoProspettoDao;

	@Inject
	private ProDParametriDao proDParametriDao;

	@Inject
	private ProRProspettoProvinciaDao proRProspettoProvinciaDao;

	@Inject
	private ProDDatiProvincialiDao proDDatiProvincialiDao;

	@Inject
	private ProDCategorieEscluseDao proDCategorieEscluseDao;

	@Inject
	private ProDPartTimeDao proDPartTimeDao;

	@Inject
	private ProDProvIntermittentiDao proDIntermittentiDao;

	@Inject
	private ProDLavoratoriInForzaDao proDLavoratoriInForzaDao;

	@Inject
	private ProDProvSospensioneDao proDSospensioneDao;

	@Inject
	private ProDProvGradualitaDao proDProvGradualitaDao;

	@Inject
	private ProDProvEsoneroAutocertDao proDProvEsoneroAutocertDao;

	@Inject
	private ProDProvEsoneroDao proDProvEsoneroDao;

	@Inject
	private ProDProvConvenzioneDao proDProvConvenzioneDao;

	@Inject
	private ProDRiepilogoNazionaleDao proDRiepilogoNazionaleDao;

	@Inject
	private ProDPostiLavoroDispDao proDPostiLavoroDispDao;

	@Inject
	private ProDProvCompensazioniDao proDProvCompensazioniDao;

	@Inject
	private ProDRiepilogoProvincialeDao proDRiepilogoProvincialeDao;

	@Inject
	private ProDPdfProspettoDao proDPdfProspettoDao;

	@Inject
	private ProTProvinciaDao proTProvinciaDao;

	@Inject
	private ProTCpiDao proTCpiDao;

	public ProDParametri getParametro(String parametroFirma) {

		Optional<ProDParametri> parametriFinded = proDParametriDao.getOneParametro(parametroFirma);

		if (parametriFinded.isPresent()) {
			return parametriFinded.get();
		} else {
			throw new NotFoundException("Parametri");
		}

	}

	public List<ProDParametri> getParametri() {
		List<ProDParametri> listParametriFinded = proDParametriDao.findAll();

		return listParametriFinded;

	}

	/**
	 * Find by id
	 * 
	 * @param uuid the uuid
	 * @return the model instance
	 */
	public Prospetto getProspetto(Long id) {

		Optional<Prospetto> optionalProspettoModel = proDProspettoDao.findOne(id).map(ProdisMappers.PROSPETTO::toModel);

		if (!optionalProspettoModel.isPresent()) {
			throw new NotFoundException("Prospetto");
		} else {

			Optional<DatiAzienda> optionalDatiAziendaModel = proDDatiAziendaDao.findOne(id)
					.map(ProdisMappers.DATI_AZIENDA::toModel);
			if (optionalDatiAziendaModel.isPresent()) {
				optionalProspettoModel.get().setDatiAzienda(optionalDatiAziendaModel.get());
			}

			List<ProDAssPubbliche> assPubblicheModel = proDAssPubblicheDao.findByField(id);
			if (assPubblicheModel != null) {
				optionalProspettoModel.get().setAssPubbliche(new ArrayList<AssPubbliche>());
				for (ProDAssPubbliche ap : assPubblicheModel) {
					AssPubbliche apModel = ProdisMappers.ASS_PUBBLICHE.toModel(ap);
					optionalProspettoModel.get().getAssPubbliche().add(apModel);
				}
			}

			Optional<ProspettoGradualita> optionalProspettoGradualitaModel = proDProspettoGradualitaDao.findOne(id)
					.map(ProdisMappers.PROSPETTO_GRADUALITA::toModel);
			if (optionalProspettoGradualitaModel.isPresent()) {
				optionalProspettoModel.get().setProspettoGradualita(optionalProspettoGradualitaModel.get());
			}
		}

		return optionalProspettoModel.get();
	}

	public Cpi getCpi(Long id) {
		Optional<ProTCpi> cpiEnt = proTCpiDao.findOne(id);

		return ProdisMappers.CPI.toModel(cpiEnt.get());
	}

	/**
	 * Returns the Prospetto
	 * 
	 * @return the Prospetto
	 */
	public List<Prospetto> getProspettos() {
		List<ProDProspetto> list = proDProspettoDao.findAll();
		return ProdisMappers.PROSPETTO.toModels(list);
	}

	public Prospetto modificaFlagConfermatoQ1(Prospetto prospetto, boolean flagBozza, boolean flagValidazione) {
		Optional<ProDProspetto> prospettoFinded = proDProspettoDao.findById(prospetto.getId());
		if (prospettoFinded.isPresent()) {
			if (!flagBozza && flagValidazione) {
				prospetto.setFlgConfermatoQ1("S");
			} else {
				prospetto.setFlgConfermatoQ1("N");
			}
			return prospetto;
		} else {
			throw new NotFoundException("Prospetto");
		}
	}

	/**
	 * Inserts the prospetto
	 * 
	 * @param prospetto the prospetto
	 * @return the model instance
	 */
	@Consumes(MediaType.APPLICATION_JSON)
	public Prospetto insertProspetto(Prospetto prospetto) {

		Date actualDate = new Date();

		// ANNULLO PROSPETTO SUCCESSIVO

		prospetto.setIdProspettoPrecedente(null);

		// FLAG VISITA ISPETTIVA DI DEFAULT

		if (prospetto.getFlgVisitaIspettiva() == null) {
			prospetto.setFlgVisitaIspettiva("N");
		}

		// PROVENZIENZA DI DEFAULT

		if (prospetto.getTipoProvenienza() == null) {
			prospetto.setTipoProvenienza("P");
		}

		ProDProspetto newProspetto = ProdisMappers.PROSPETTO.toEntity(prospetto);

		// STACCARE CAMPI

		// stacco i dati azienda
		newProspetto.setProDDatiAzienda(null);

		// stacco gradualita
		if (newProspetto.getProDProspettoGradualita() != null) {
			newProspetto.setProDProspettoGradualita(null);
		}

		// azienda
		ProDDatiAzienda newAzienda = ProdisMappers.DATI_AZIENDA.toEntity(prospetto.getDatiAzienda());

		// DATE DI INSERIMENTO E AGGIORNAMENTO PER AZIENDA

		newAzienda.setdAggiorn(actualDate);
		newAzienda.setdInserim(actualDate);

		// GESTIONE CAPOGRUPPO

		if (newAzienda.getProTStatiEsteri() != null) {

			if (newAzienda.getProTStatiEsteri().getSiglaNazione().equals("IT")) {

				newAzienda.setFlgCapogruppoEstero("N");
				newAzienda.setCfCapogruppo(newAzienda.getCfCapogruppo());

			} else if (newAzienda.getProTStatiEsteri().getSiglaNazione() == null) {

				newAzienda.setFlgCapogruppoEstero(null);
				newAzienda.setCfCapogruppo(null);

			} else {

				newAzienda.setFlgCapogruppoEstero("S");
				newAzienda.setCfCapogruppo(
						newAzienda.getProTStatiEsteri().getSiglaNazione() + newAzienda.getCfCapogruppo());
			}

		} else {

			newAzienda.setFlgCapogruppoEstero(null);

		}

		/* Pezzo per gestire gli oggetti con id = null o id = 0 */
		if (newAzienda.getProTAtecofin() != null && newAzienda.getProTAtecofin().getIdTAtecofin() == null) {
			if (newAzienda.getProTAtecofin().getCodAtecofinMin() == null
					&& newAzienda.getProTAtecofin().getDsProTAtecofin() == null) {
				newAzienda.setProTAtecofin(null);
			}
		}
		if (newAzienda.getProTCcnl() != null && newAzienda.getProTCcnl().getIdTCcnl() == null) {
			if (newAzienda.getProTCcnl().getCodCcnlMin() == null && newAzienda.getProTCcnl().getDsCcnl() == null) {
				newAzienda.setProTCcnl(null);
			}
		}
		if (newAzienda.getProTComune() != null && newAzienda.getProTComune().getIdTComune() == null) {
			if (newAzienda.getProTComune().getCodComuneMin() == null
					&& newAzienda.getProTComune().getDsProTComune() == null) {
				newAzienda.setProTComune(null);
			}

		}

		// sede (è dentro l'azienda)
		ProDSedeLegale newSede = newAzienda.getProDSedeLegale();

		// DATE DI SEDE LEGALE

		if (newSede != null) {
			newSede.setDAggiorn(actualDate);
			newSede.setdAggiorn(actualDate);
			newSede.setDInserim(actualDate);
			newSede.setdInserim(actualDate);
		}

		if (newSede != null && newSede.getProTComune() != null && newSede.getProTComune().getIdTComune() == null) {
			if (newSede.getProTComune().getCodComuneMin() == null
					&& newSede.getProTComune().getDsProTComune() == null) {
				newSede.setProTComune(null);
			}
		}

		newAzienda.setProDSedeLegale(null);

		// ass pubbliche (se esistono)
		List<ProDAssPubbliche> newAssPubbliche = null;
		if (prospetto.getAssPubbliche() != null) {

			newAssPubbliche = ProdisMappers.ASS_PUBBLICHE.toEntities(prospetto.getAssPubbliche());

		}

		// gradualita (se esiste)
		ProDProspettoGradualita newGrad = null;
		if (prospetto.getProspettoGradualita() != null) {

			newGrad = ProdisMappers.PROSPETTO_GRADUALITA.toEntity(prospetto.getProspettoGradualita());

		} else {
			prospetto.setProspettoGradualita(null);
		}

		// INSERIMENTO

		newProspetto = proDProspettoDao.insert(newProspetto);

		String utente = newProspetto.getCodUserInserim();

		// solo dopo l'inserimento ho l'ID
		final Long prospettoId = newProspetto.getId();

		// azienda
		newAzienda.setIdProspetto(prospettoId);
		newAzienda.setProTStatiEsteri(null);
		proDDatiAziendaDao.insert(newAzienda);

		// sede
		newSede.setId(prospettoId);
		newSede.setCodUserAggiorn(utente);
		newSede.setCodUserInserim(utente);
		proDSedeLegaleDao.insert(newSede);

		// ass pubbliche
		if (newAssPubbliche != null) {

			for (ProDAssPubbliche ap : newAssPubbliche) {
				ProDAssPubblichePK pk = new ProDAssPubblichePK();
				pk.setIdTRegione(ap.getProTRegione().getId());
				pk.setIdProspetto(prospettoId);
				ap.setId(pk);
				ap.setDAggiorn(actualDate);
				ap.setDInserim(actualDate);
				proDAssPubblicheDao.insert(ap);
			}

		}

		// gradualita
		if (newGrad != null) {

			newGrad.setId(prospettoId);
			proDProspettoGradualitaDao.insert(newGrad);

		}

		// set id del prospetto
		prospetto.setId(prospettoId);

		if (newSede != null) {
			newAzienda.setProDSedeLegale(newSede);
		}
		
		ProspettoGradualita prospettoGradualitaTmp = null;
		
		if (newGrad != null) {
			newProspetto.setProDProspettoGradualita(newGrad);
			prospettoGradualitaTmp = ProdisMappers.PROSPETTO_GRADUALITA.toModel(newGrad);
		}
		newProspetto.setProDDatiAzienda(newAzienda);
		
		/*in ProspettoMapper la mappatura della gradualità è commentata*/
		prospetto = ProdisMappers.PROSPETTO.toModel(newProspetto);
		
		prospetto.setProspettoGradualita(prospettoGradualitaTmp);

		prospetto.setAssPubbliche(ProdisMappers.ASS_PUBBLICHE.toModels(newAssPubbliche));

		return prospetto;

	}

	/**
	 * Updates only the prospetto entity
	 * 
	 * @param prospetto the prospetto
	 * @return the model instance
	 */
	public Prospetto updateProspettoRiepilogo(Prospetto prospetto) {
		Optional<ProDProspetto> prospettoOpt = proDProspettoDao.findById(prospetto.getId());
		if (prospettoOpt.isEmpty()) {
			return null;
		}

		ProDProspetto prospettoEnt = prospettoOpt.get();

		if (prospettoEnt.getProTCategoriaAzienda() == null || prospettoEnt.getProTCategoriaAzienda().getId() == null) {

			prospettoEnt.setFlgNessunaAssunzioneAggiun(null);
			prospettoEnt.setDataPrimaAssunzione(null);
			prospettoEnt.setDataSecondaAssunzione(null);

		} else {
			prospettoEnt.setFlgNessunaAssunzioneAggiun(prospetto.getFlgNessunaAssunzioneAggiun());
			prospettoEnt.setDataPrimaAssunzione(prospetto.getDataPrimaAssunzione());
			prospettoEnt.setDataSecondaAssunzione(prospetto.getDataSecondaAssunzione());

		}

		prospettoEnt.setNote(prospetto.getNote());
		prospettoEnt.setFlgVisitaIspettiva(prospetto.getFlgVisitaIspettiva());

		if (prospetto.getSoggetti() != null && ProdisSrvUtil.isNotVoid(prospetto.getSoggetti().getId())) {
			prospettoEnt.setProTSoggetti(ProdisMappers.SOGGETTI.toEntity(prospetto.getSoggetti()));
		} else {
			prospettoEnt.setProTSoggetti(null);
		}

		prospettoEnt.setDataTimbroPostale(prospetto.getDataTimbroPostale());

		prospettoEnt.setEmailNotifica(prospetto.getEmailNotifica());
		if (prospetto.getEmailSoggettoComunicazione() == null) {
			prospettoEnt.setEmailSoggettoComunicazione(prospetto.getEmailNotifica());
		} else {
			prospettoEnt.setEmailSoggettoComunicazione(prospetto.getEmailSoggettoComunicazione());
		}

		prospettoEnt.setCfStudioProfessionale(prospetto.getCfStudioProfessionale());
		prospettoEnt.setCodUserAggiorn(prospetto.getCodUserAggiorn());

		ProDProspetto prospettoUpdated = proDProspettoDao.update(prospettoEnt);

		return ProdisMappers.PROSPETTO.toModel(prospettoUpdated);
	}

	public Prospetto updateConfermaRiepilogo(Prospetto prospetto) {
		Optional<ProDProspetto> prospettoOpt = proDProspettoDao.findById(prospetto.getId());
		if (prospettoOpt.isEmpty()) {
			return null;
		}

		ProDProspetto prospettoEnt = prospettoOpt.get();

		prospettoEnt.setNumeroProtocollo(prospetto.getNumeroProtocollo());
		prospettoEnt.setAnnoProtocollo(prospetto.getAnnoProtocollo());
		prospettoEnt.setDataProtocollo(prospetto.getDataProtocollo());
		prospettoEnt.setCodiceComunicazione(prospetto.getCodiceComunicazione());
		prospettoEnt.setFlgInvioMinistero(prospetto.getFlgInvioMinistero());
		prospettoEnt.setDataInvio(prospetto.getDataInvio());
		prospettoEnt.setProTStatoProspetto(ProdisMappers.STATO_PROSPETTO.toEntity(prospetto.getStatoProspetto()));
		prospettoEnt.setDataTimbroPostale(prospetto.getDataTimbroPostale());

		ProDProspetto prospettoUpdated = proDProspettoDao.update(prospettoEnt);

		return ProdisMappers.PROSPETTO.toModel(prospettoUpdated);
	}

	/**
	 * Updates the prospetto
	 * 
	 * @param prospetto the prospetto
	 * @return the model instance
	 */
	public Prospetto updateProspetto(Prospetto prospetto) {

		// CHECK ANNULLAMENTO SOGGETTI

		if (prospetto.getSoggetti() != null && prospetto.getSoggetti().getId() == null) {
			prospetto.setSoggetti(null);
		}

		// CHECK SE IL COGNOME FINISCE CON ACCENTO
		if (prospetto.getDatiAzienda().getCognomeReferente() != null
				&& prospetto.getDatiAzienda().getCognomeReferente().endsWith("''")) {
			prospetto.getDatiAzienda().setCognomeReferente(prospetto.getDatiAzienda().getCognomeReferente().substring(0,
					prospetto.getDatiAzienda().getCognomeReferente().length() - 1));
		}

		// DATA DI AGGIORNAMENTO DA INSERIRE IN TUTTI I DAggiorn/dAggiorn
		Date dataAggiornamento = new Date();

		prospetto.setdAggiorn(dataAggiornamento);

		// PROVENIENZA DI DEFAULT
		if (prospetto.getTipoProvenienza() == null) {
			prospetto.setTipoProvenienza("P");
		}

		Optional<ProDProspetto> prospettoFinded = proDProspettoDao.findById(prospetto.getId());

		ProDProspetto newProspetto = null;
		ProDDatiAzienda newAzienda = null;
		ProDSedeLegale newSedeLegale = null;
		ProDProspettoGradualita newGradualita = null;

		if (prospettoFinded.isEmpty()) {
			throw new NotFoundException("Prospetto");
		} else {

			String utenteInserim = prospettoFinded.get().getCodUserInserim();
			String utenteAggiornamento = prospetto.getCodUserAggiorn();

			Date dataInserimento = prospettoFinded.get().getdInserim();

			prospetto.setdInserim(dataInserimento);

			// SEDE LEGALE

			if (prospetto.getDatiAzienda().getSedeLegale() != null
					&& proDSedeLegaleDao.findOne(prospetto.getId()).isPresent()) {
				SedeLegale sedeLegale = prospetto.getDatiAzienda().getSedeLegale();

				if (sedeLegale.getComune() != null && sedeLegale.getComune().getId() == null) {
					if (sedeLegale.getComune().getCodComuneMin() == null
							&& sedeLegale.getComune().getDsProTComune() == null) {
						sedeLegale.setComune(null);
					} else if (sedeLegale.getComune().getCodComuneMin().equals("")
							&& sedeLegale.getComune().getDsProTComune().equals("")) {
						sedeLegale.setComune(null);
					}
				}
				sedeLegale.setDAggiorn(dataAggiornamento);
				sedeLegale.setDInserim(dataInserimento);
				sedeLegale.setCodUserInserim(utenteInserim);
				sedeLegale.setCodUserAggiorn(utenteAggiornamento);
				sedeLegale.setId(prospetto.getId().intValue());
				newSedeLegale = ProdisMappers.SEDE_LEGALE.toEntity(sedeLegale);
				proDSedeLegaleDao.update(newSedeLegale);
			}

			// DATI AZIENDA

			DatiAzienda datiAzienda = prospetto.getDatiAzienda();

			datiAzienda.setdAggiorn(dataAggiornamento);
			datiAzienda.setdInserim(dataInserimento);
			datiAzienda.setId(prospetto.getId().intValue());

			newAzienda = ProdisMappers.DATI_AZIENDA.toEntity(datiAzienda);

			newAzienda.setCodUserInserim(utenteInserim);
			newAzienda.setCodUserAggiorn(utenteAggiornamento);

			// GESTIONE CAPOGRUPPO

			if (newAzienda.getProTStatiEsteri() != null) {

				if (newAzienda.getProTStatiEsteri().getSiglaNazione().equals("IT")) {

					newAzienda.setFlgCapogruppoEstero("N");
					newAzienda.setCfCapogruppo(newAzienda.getCfCapogruppo());

				} else if (newAzienda.getProTStatiEsteri().getSiglaNazione() == null) {

					newAzienda.setFlgCapogruppoEstero(null);
					newAzienda.setCfCapogruppo(null);

				} else {

					newAzienda.setFlgCapogruppoEstero("S");
					newAzienda.setCfCapogruppo(
							newAzienda.getProTStatiEsteri().getSiglaNazione() + newAzienda.getCfCapogruppo());
				}

			} else {

				newAzienda.setFlgCapogruppoEstero(null);

			}

			// ANNULLO GLI STATI ESTERI

			newAzienda.setProTStatiEsteri(null);

			/* Pezzo per gestire gli oggetti con id = null o id = 0 */
			if (newAzienda.getProTAtecofin() != null && newAzienda.getProTAtecofin().getIdTAtecofin() == null) {
				if (newAzienda.getProTAtecofin().getCodAtecofinMin() == null
						&& newAzienda.getProTAtecofin().getDsProTAtecofin() == null) {
					newAzienda.setProTAtecofin(null);
				} else if (newAzienda.getProTAtecofin().getCodAtecofinMin().equals("")
						&& newAzienda.getProTAtecofin().getDsProTAtecofin().equals("")) {
					newAzienda.setProTAtecofin(null);
				}
			}
			if (newAzienda.getProTCcnl() != null && newAzienda.getProTCcnl().getIdTCcnl() == null) {
				if (newAzienda.getProTCcnl().getCodCcnlMin() == null && newAzienda.getProTCcnl().getDsCcnl() == null) {
					newAzienda.setProTCcnl(null);
				} else if (newAzienda.getProTCcnl().getCodCcnlMin().equals("")
						&& newAzienda.getProTCcnl().getDsCcnl().equals("")) {
					newAzienda.setProTCcnl(null);
				}
			}
			if (newAzienda.getProTComune() != null && newAzienda.getProTComune().getIdTComune() == null) {
				if (newAzienda.getProTComune().getCodComuneMin() == null
						&& newAzienda.getProTComune().getDsProTComune() == null) {
					newAzienda.setProTComune(null);
				} else if (newAzienda.getProTComune().getCodComuneMin().equals("")
						&& newAzienda.getProTComune().getDsProTComune().equals("")) {
					newAzienda.setProTComune(null);
				}
			}
			proDDatiAziendaDao.update(newAzienda);

			// ASS PUBBLICHE
			List<ProDAssPubbliche> oldAssPubbliche = proDAssPubblicheDao.findByField(prospetto.getId());

			if (prospetto.getAssPubbliche() != null) {

				List<AssPubbliche> assPubbliche = prospetto.getAssPubbliche();

				for (AssPubbliche newAs : assPubbliche) {

					newAs.setId(new AssPubblichePK());
					newAs.getId().setIdProspetto(prospetto.getId());
					newAs.getId().setIdTRegione(newAs.getRegione().getId());
					newAs.setDAggiorn(dataAggiornamento);
					newAs.setDInserim(dataInserimento);

					boolean flagFinded = false;

					for (ProDAssPubbliche oldAs : oldAssPubbliche) {

						if (newAs.getId().getIdTRegione() == oldAs.getId().getIdTRegione()) {

							// UPDATE

							flagFinded = true;
							newAs.setCodUserInserim(oldAs.getCodUserInserim());

							proDAssPubblicheDao.update(ProdisMappers.ASS_PUBBLICHE.toEntity(newAs));

						}

					}

					if (!flagFinded) {

						// INSERT

						proDAssPubblicheDao.insert(ProdisMappers.ASS_PUBBLICHE.toEntity(newAs));

					}

				}

				// DELETE

				for (ProDAssPubbliche oldAs : oldAssPubbliche) {

					boolean flagFinded = false;

					for (AssPubbliche newAs : assPubbliche) {

						if (newAs.getId().getIdTRegione() == oldAs.getId().getIdTRegione()) {

							flagFinded = true;

						}

					}

					if (!flagFinded) {

						proDAssPubblicheDao.delete(oldAs.getId());

					}

				}

			} else {

				// se la lista è vuota, cancello tutto

				for (ProDAssPubbliche oldAs : oldAssPubbliche) {

					proDAssPubblicheDao.delete(oldAs.getId());

				}

			}

			// GRADUALITA

			if (prospetto.getProspettoGradualita() != null) {

				if (prospetto.getProspettoGradualita().getDataAtto() != null
						|| prospetto.getProspettoGradualita().getEstremiAtto() != null
						|| prospetto.getProspettoGradualita().getnAssunzioniLavPreTrasf() != null
						|| prospetto.getProspettoGradualita().getDataTrasformazione() != null
						|| prospetto.getProspettoGradualita().getPercentuale() != null) {
					prospetto.getProspettoGradualita().setId(prospetto.getId().intValue());
				}
				if (prospetto.getProspettoGradualita().getDataAtto() == null
						&& (prospetto.getProspettoGradualita().getEstremiAtto() == null
								|| prospetto.getProspettoGradualita().getEstremiAtto().equals(""))
						&& prospetto.getProspettoGradualita().getDataTrasformazione() == null) {

					prospetto.setProspettoGradualita(null);
				}

			}

			Optional<ProDProspettoGradualita> oldGradualita = proDProspettoGradualitaDao.findOne(prospetto.getId());

			if (oldGradualita.isPresent()) {

				// UPDATE e DELETE

				if (prospetto.getProspettoGradualita() == null || prospetto.getProspettoGradualita().getId() == null) {

					// DELETE

					proDProspettoGradualitaDao.delete(prospetto.getId());

					prospetto.setProspettoGradualita(null);

				} else {

					// UPDATE

					ProspettoGradualita gradualita = prospetto.getProspettoGradualita();
					gradualita.setId(prospetto.getId().intValue());
					newGradualita = ProdisMappers.PROSPETTO_GRADUALITA.toEntity(gradualita);
					newGradualita.setId(prospetto.getId());
					newGradualita.setdAggiorn(dataAggiornamento);
					newGradualita.setdInserim(oldGradualita.get().getdInserim());
					newGradualita.setCodUserInserim(oldGradualita.get().getCodUserInserim());
					proDProspettoGradualitaDao.update(newGradualita);

				}

			} else {

				// INSERIMENTO

				if (prospetto.getProspettoGradualita() != null) {
					ProspettoGradualita gradualita = prospetto.getProspettoGradualita();
					gradualita.setId(prospetto.getId().intValue());
					newGradualita = ProdisMappers.PROSPETTO_GRADUALITA.toEntity(gradualita);
					newGradualita.setId(prospetto.getId());
					newGradualita.setdAggiorn(dataAggiornamento);
					newGradualita.setdInserim(dataAggiornamento);
					proDProspettoGradualitaDao.insert(newGradualita);
				}

			}

			// EFFETTIVO PROSPETTO

			newProspetto = ProdisMappers.PROSPETTO.toEntity(prospetto);

			newProspetto.setCodUserInserim(utenteInserim);
			newProspetto.setCodUserAggiorn(utenteAggiornamento);

			proDProspettoDao.update(newProspetto);

		}

		prospetto = ProdisMappers.PROSPETTO.toModel(newProspetto);

		if (newAzienda != null) {
			prospetto.setDatiAzienda(ProdisMappers.DATI_AZIENDA.toModel(newAzienda));
		}
		if (newSedeLegale != null) {
			prospetto.getDatiAzienda().setSedeLegale(ProdisMappers.SEDE_LEGALE.toModel(newSedeLegale));
		}
		if (newGradualita != null) {
			prospetto.setProspettoGradualita(ProdisMappers.PROSPETTO_GRADUALITA.toModel(newGradualita));
		}

		List<ProDAssPubbliche> newAssPubbliche = proDAssPubblicheDao.findByField(prospetto.getId());

		prospetto.setAssPubbliche(ProdisMappers.ASS_PUBBLICHE.toModels(newAssPubbliche));

		return prospetto;
	}

	public long countRicerca(RicercaProspetto ricercaProspetto) {
		log.error("countRicerca----------->", "Entro nel metodo countRicerca");
		long count = proDProspettoDao.countRicerca(ricercaProspetto);
		log.error("countRicerca----------->", "ESCO DAl metodo countRicerca--->" + count);
		return count;
	}

	public PagedList<Prospetto> getRicerca(int page, int size, Sort sort, RicercaProspetto ricercaProspetto) {
		String sortField = null;
		String sortDirection = null;
		if (sort != null) {
			if (ProspettoSort.byModelName(sort.getField()) != null) {
				sortField = ProspettoSort.byModelName(sort.getField()).getQueryName();
			}
			sortDirection = sort.getOrder().getSortDirection();
		}

		Page<ProDProspetto> prodisDProspettos = proDProspettoDao.findPaginated(page, size, sortField, sortDirection,
				ricercaProspetto);

		PagedList<Prospetto> pagedList = toPagedList(prodisDProspettos, page, size, ProdisMappers.PROSPETTO::toModel);
		return pagedList;
	}

	public List<AssPubbliche> getAssunzioniPubbliche(Long idProspetto) {
		List<ProDAssPubbliche> list = proDAssPubblicheDao.findByField(idProspetto);

		for (ProDAssPubbliche proDAssPubbliche : list) {
			DecodificaGenerica ilFilter = new DecodificaGenerica();
			ilFilter.setIdDecodifica(proDAssPubbliche.getId().getIdTRegione());
			List<DecodificaGenerica> lista = proTRegioneDao.findByFilter(ilFilter);
			if (lista.size() > 0) {
				ProTRegione proTRegione = new ProTRegione();
				DecodificaGenerica laDecoDellaRegione = lista.get(0);
				proTRegione.setId(laDecoDellaRegione.getIdDecodifica());
				proTRegione.setDsProTRegione(laDecoDellaRegione.getDsDecodifica());
				proTRegione.setCodRegioneMin(laDecoDellaRegione.getCodDecodifica());
				proDAssPubbliche.setProTRegione(proTRegione);
			}
		}

		return ProdisMappers.ASS_PUBBLICHE.toModels(list);
	}

	public String getCheckCodiceFiscale(String cf) {
		return proDProspettoDao.checkCodiceFiscale(cf);
	}

	public String getCheckCongruenzaCodiceFiscale(String cf, String cognome, String nome, String sesso,
			String dataNascita, String codiceComuneStatoEsteroNascita) {
		if (ProdisSrvUtil.isNotVoid(cognome)) {
			cognome = cognome.replace("'", "");
			cognome = cognome.replace("-", "");
		}
		if (ProdisSrvUtil.isNotVoid(nome)) {
			nome = nome.replace("'", "");
			nome = nome.replace("-", "");
		}
		return proDProspettoDao.checkCongruenzaCodiceFiscale(cf, cognome, nome, sesso, dataNascita,
				codiceComuneStatoEsteroNascita);
	}

	////////////////////// Metodi per la validazione dei dati del prospetto
	public ProTComune getComuneById(Long id) {
		Optional<ProTComune> optional = proTComuneDao.findOne(id);
		if (!optional.isPresent()) {
			throw new NotFoundException("Comune");
		}
		return optional.get();
	}

	public ProTAtecofin getAtecofinById(Long id) {
		Optional<ProTAtecofin> optional = proTAtecofinDao.findOne(id);
		if (!optional.isPresent()) {
			throw new NotFoundException("Atecofin");
		}
		return optional.get();
	}

	public ProTCcnl getCcnlById(Long id) {
		Optional<ProTCcnl> optional = proTCcnlDao.findOne(id);
		if (!optional.isPresent()) {
			throw new NotFoundException("CCNL");
		}
		return optional.get();
	}

	public void updateAssPubbliche(List<AssPubbliche> assPubbliche) {

		Optional<ProDProspetto> prospettoFinded = proDProspettoDao
				.findById(assPubbliche.get(0).getId().getIdProspetto());

		if (prospettoFinded.isEmpty()) {
			throw new NotFoundException("Prospetto");
		}

		List<ProDAssPubbliche> assPubblicheFinded = proDAssPubblicheDao
				.findByField(assPubbliche.get(0).getId().getIdProspetto());

		if (assPubblicheFinded.isEmpty()) {
			throw new NotFoundException("AssPubbliche");
		} else {

			for (int i = 0; i < assPubblicheFinded.size(); i++) {
				ProdisMappers.ASS_PUBBLICHE.toEntityExisting(assPubbliche.get(i), assPubblicheFinded.get(i));
				proDAssPubblicheDao.update(assPubblicheFinded.get(i));
			}

		}

	}

	public void insertAssPubbliche(List<AssPubbliche> assPubbliche) {

		Optional<ProDProspetto> prospettoFinded = proDProspettoDao
				.findById(assPubbliche.get(0).getId().getIdProspetto());

		if (prospettoFinded.isEmpty()) {
			throw new NotFoundException("Prospetto");
		}

		List<ProDAssPubbliche> newAssPubbliche = ProdisMappers.ASS_PUBBLICHE.toEntities(assPubbliche);

		// ass pubbliche
		if (newAssPubbliche != null) {

			for (ProDAssPubbliche ap : newAssPubbliche) {
				ProDAssPubblichePK pk = new ProDAssPubblichePK();
				pk.setIdTRegione(ap.getProTRegione().getId());
				pk.setIdProspetto(assPubbliche.get(0).getId().getIdProspetto());
				ap.setId(pk);
				proDAssPubblicheDao.insert(ap);
			}

		}

	}

	public String getParametroByNome(String parametro) {
		DecodificaGenerica valore = proDParametriDao.findByNome(parametro);
		if (ProdisSrvUtil.isVoid(valore)) {
			throw new NotFoundException("parametro");
		}
		return valore.getCodDecodifica();
	}

	public void deleteProspetto(Long idProspetto) {

		Optional<ProDProspetto> prospettoFinded = proDProspettoDao.findById(idProspetto);

		if (prospettoFinded.isEmpty()) {
			throw new NotFoundException("Prospetto");
		} else {

			List<ProRProspettoProvincia> ppFinded = proRProspettoProvinciaDao.findByIdProspetto(idProspetto);

			if (!ppFinded.isEmpty()) {
				throw new NotFoundException("Il prospetto che si vuole eliminare, ha legati dei dati provinciali");
			}

			if (prospettoFinded.get().getProTStatoProspetto() != null
					&& prospettoFinded.get().getProTStatoProspetto().getDescrizione() != null
					&& prospettoFinded.get().getProTStatoProspetto().getDescrizione().equals("BOZZA")) {

				if (prospettoFinded.get().getProTComunicazione() != null
						&& prospettoFinded.get().getProTComunicazione().getIdTComunicazione() == 2L) {

					Optional<ProDProspetto> vecchioProspettoRettificato = proDProspettoDao
							.findById(prospettoFinded.get().getIdProspettoPrecedente());

					if (vecchioProspettoRettificato.isPresent()) {

						ProTStatoProspetto ilNuovoStato = new ProTStatoProspetto();

						ilNuovoStato.setId(3L);
						vecchioProspettoRettificato.get().setProTStatoProspetto(ilNuovoStato);
						proDProspettoDao.update(vecchioProspettoRettificato.get());

					} else {
						throw new NotFoundException("Prospetto");
					}

				}

			}

			prospettoFinded.get().setIdProspettoPrecedente(null);
			proDProspettoDao.update(prospettoFinded.get());

			if (!proDDatiAziendaDao.findOne(idProspetto).isEmpty()) {

				if (!proDSedeLegaleDao.findOne(idProspetto).isEmpty()) {

					proDSedeLegaleDao.delete(idProspetto);

				}

				proDDatiAziendaDao.delete(idProspetto);

			}

			List<ProDAssPubbliche> listAssPubbliche = proDAssPubblicheDao.findByField(idProspetto);
			if (!listAssPubbliche.isEmpty()) {

				for (ProDAssPubbliche ap : listAssPubbliche) {

					proDAssPubblicheDao.delete(ap.getId());

				}

			}

			if (!proDProspettoGradualitaDao.findOne(idProspetto).isEmpty()) {

				proDProspettoGradualitaDao.delete(idProspetto);

			}

			proDProspettoDao.delete(idProspetto);

		}

	}

	public RitornoEseguiOperazione rettificaProspetto(Long idProspetto) {

		Optional<ProDProspetto> prospettoFinded = proDProspettoDao.findById(idProspetto);

		if (!prospettoFinded.isPresent()) {
			throw new NotFoundException("Prospetto");
		}

		RitornoEseguiOperazione ritornoRettificato = proDProspettoDao.rettifica(prospettoFinded.get());

		Optional<ProDProspetto> prospettoRettificatoFinded = proDProspettoDao.findById(ritornoRettificato.getNewId());

		if (prospettoRettificatoFinded.isPresent()) {
			ritornoRettificato.setNuovoProspetto(ProdisMappers.PROSPETTO.toModel(prospettoRettificatoFinded.get()));
			return ritornoRettificato;
		} else {
			throw new NotFoundException("Prospetto");
		}

	}

	public RitornoEseguiOperazione annullaProspetto(Long idProspetto) {

		Optional<ProDProspetto> prospettoFinded = proDProspettoDao.findById(idProspetto);

		if (!prospettoFinded.isPresent()) {
			throw new NotFoundException("Prospetto");
		}

		RitornoEseguiOperazione ritornoAnnullato = proDProspettoDao.annulla(prospettoFinded.get());

		Optional<ProDProspetto> prospettoAnnullatoFinded = proDProspettoDao.findById(ritornoAnnullato.getNewId());

		if (prospettoAnnullatoFinded.isPresent()) {
			ritornoAnnullato.setNuovoProspetto(ProdisMappers.PROSPETTO.toModel(prospettoAnnullatoFinded.get()));
			return ritornoAnnullato;
		} else {
			throw new NotFoundException("Prospetto");
		}

	}

	public RitornoEseguiOperazione duplicaProspetto(Long idProspetto) {

		Optional<ProDProspetto> prospettoFinded = proDProspettoDao.findById(idProspetto);

		if (!prospettoFinded.isPresent()) {
			throw new NotFoundException("Prospetto");
		}

		RitornoEseguiOperazione ritornoDuplicato = proDProspettoDao.duplica(prospettoFinded.get());

		Optional<ProDProspetto> prospettoDuplicatoFinded = proDProspettoDao.findById(ritornoDuplicato.getNewId());

		if (prospettoDuplicatoFinded.isPresent()) {
			ritornoDuplicato.setNuovoProspetto(ProdisMappers.PROSPETTO.toModel(prospettoDuplicatoFinded.get()));
			return ritornoDuplicato;
		} else {
			throw new NotFoundException("Prospetto");
		}

	}

	public void updateStatoProspetto(Long idProspetto, Long idStatoProspetto) {

		Optional<ProDProspetto> newProspetto = proDProspettoDao.findById(idProspetto);
		if (newProspetto.isPresent()) {
			ProTStatoProspetto ilNuovoStato = new ProTStatoProspetto();
			ilNuovoStato.setId(idStatoProspetto);
			newProspetto.get().setProTStatoProspetto(ilNuovoStato);
			proDProspettoDao.update(newProspetto.get());
		} else {
			throw new NotFoundException("Prospetto");
		}

	}

	public Date getDataTermineProspettoParametri() {

		long MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;

		Optional<ProDParametri> parametriFinded = proDParametriDao
				.getOneParametro(ConstantsProdis.PARAMETRO_DATA_RIFERIMENTO_TERMINE_PROSPETTO);

		if (parametriFinded.isPresent()) {

			String valoreData = parametriFinded.get().getDsValore() + "/" + LocalDate.now().getYear();

			Date ritorno = null;
			try {
				ritorno = new SimpleDateFormat("dd/MM/yyyy").parse(valoreData);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			return new Date(ritorno.getTime() + MILLIS_IN_A_DAY);

		} else {
			throw new NotFoundException("Parametri");
		}

	}

	// CARICAMENTO PER PDF

	public ProDProspetto caricaProspettoPerPdf(Long idProspetto) {

		Optional<ProDProspetto> prospettoFinded = proDProspettoDao.findById(idProspetto);

		if (prospettoFinded.isPresent()) {
			return prospettoFinded.get();
		} else {
			return null;
		}

	}

	public List<ProDAssPubbliche> caricaAssPubblichePerPdf(Long idProspetto) {

		List<ProDAssPubbliche> assPubblicheFinded = proDAssPubblicheDao.findByField(idProspetto);

		return assPubblicheFinded;

	}

	public List<ProRProspettoProvincia> caricaProspettoProvinciaPerPdf(Long idProspetto) {

		List<ProRProspettoProvincia> prospettoProvinciaFinded = proRProspettoProvinciaDao
				.findByIdProspetto(idProspetto);

		return prospettoProvinciaFinded;

	}

	public ProDDatiProvinciali caricaDatiProvincialiPerPdf(Long idProspettoProv) {

		Optional<ProDDatiProvinciali> datiProvincialiFinded = proDDatiProvincialiDao.findOne(idProspettoProv);

		if (datiProvincialiFinded.isPresent()) {
			return datiProvincialiFinded.get();
		} else {
			return null;
		}
	}

	public List<ProDPartTime> caricaPartTimePerPdf(long idProspettoProv) {

		List<ProDPartTime> partTimeFinded = proDPartTimeDao.findByIdProspettoProv(new BigDecimal(idProspettoProv));

		return partTimeFinded;

	}

	public List<ProDProvIntermittenti> caricaIntermittentiPerPdf(long idProspettoProv) {

		List<ProDProvIntermittenti> intermittentiFinded = proDIntermittentiDao
				.findByIdProspettoProv(new BigDecimal(idProspettoProv));

		return intermittentiFinded;

	}

	public List<ProDLavoratoriInForza> caricaLavoratoriInForzaPerPdf(long idProspettoProv) {

		List<ProDLavoratoriInForza> lavoratoriInForzaFinded = proDLavoratoriInForzaDao
				.findByIdProspettoProv(new BigDecimal(idProspettoProv));

		return lavoratoriInForzaFinded;

	}

	public List<ProDCategorieEscluse> caricaCategorieEsclusePerPdf(long idProspettoProv) {

		List<ProDCategorieEscluse> categorieEscluseFinded = proDCategorieEscluseDao
				.findByIdProspettoProv(new BigDecimal(idProspettoProv));

		return categorieEscluseFinded;

	}

	public ProDProvSospensione caricaSospensionePerPdf(long idProspettoProv) {

		Optional<ProDProvSospensione> sospensioneFinded = proDSospensioneDao.findOne(idProspettoProv);

		if (sospensioneFinded.isPresent()) {
			return sospensioneFinded.get();
		} else {
			return null;
		}

	}

	public ProDProvGradualita caricaProvGradualitaPerPdf(long idProspettoProv) {

		Optional<ProDProvGradualita> gradualitaFinded = proDProvGradualitaDao.findOne(idProspettoProv);

		if (gradualitaFinded.isPresent()) {
			return gradualitaFinded.get();
		} else {
			return null;
		}

	}

	public ProDProvEsoneroAutocert caricaProvEsoneroAutocertPerPdf(long idProspettoProv) {

		Optional<ProDProvEsoneroAutocert> esoneroAutocertFinded = proDProvEsoneroAutocertDao.findOne(idProspettoProv);

		if (esoneroAutocertFinded.isPresent()) {
			return esoneroAutocertFinded.get();
		} else {
			return null;
		}

	}

	public ProDProvEsonero caricaProvEsoneroPerPdf(long idProspettoProv) {

		Optional<ProDProvEsonero> esoneroFinded = proDProvEsoneroDao.findOne(idProspettoProv);

		if (esoneroFinded.isPresent()) {
			return esoneroFinded.get();
		} else {
			return null;
		}

	}

	public ProDProvConvenzione caricaProvConvenzionePerPdf(long idProspettoProv) {

		Optional<ProDProvConvenzione> convenzioneFinded = proDProvConvenzioneDao.findOne(idProspettoProv);

		if (convenzioneFinded.isPresent()) {
			return convenzioneFinded.get();
		} else {
			return null;
		}

	}

	public ProDRiepilogoNazionale caricaRiepilogoNazionalePerPdf(Long idProspetto) {

		Optional<ProDRiepilogoNazionale> riepilogoNazionaleFinded = proDRiepilogoNazionaleDao.findOne(idProspetto);

		if (riepilogoNazionaleFinded.isPresent()) {
			return riepilogoNazionaleFinded.get();
		} else {
			return null;
		}

	}

	public List<ProDPostiLavoroDisp> caricaPostiLavoroDispPerPdf(long idProspettoProv) {

		List<ProDPostiLavoroDisp> postiLavoroDispFinded = proDPostiLavoroDispDao.findByField(idProspettoProv);

		return postiLavoroDispFinded;

	}

	public List<ProDProvCompensazioni> caricaCompensazioniPerPdf(long idProspettoProv) {

		List<ProDProvCompensazioni> compensazioniFinded = proDProvCompensazioniDao
				.findByIdProspettoProv(idProspettoProv);

		return compensazioniFinded;

	}

	public ProDRiepilogoProvinciale caricaRiepilogoProvincialePerPdf(long idProspettoProv) {

		Optional<ProDRiepilogoProvinciale> riepilogoProvincialeFinded = proDRiepilogoProvincialeDao
				.findOne(idProspettoProv);

		if (riepilogoProvincialeFinded.isPresent()) {
			return riepilogoProvincialeFinded.get();
		} else {
			return null;
		}
	}

	public String inserisciPdfInDatabase(Long idProspetto, byte[] os) {

		String operazione = null;

		Optional<ProDPdfProspetto> pdfFinded = proDPdfProspettoDao.findOne(idProspetto);

		ProDPdfProspetto newPdf = new ProDPdfProspetto();
		// Modificato la destinazione del pdf su cui salvare.
//		newPdf.setPdfProspettoFirmato(os);
		newPdf.setPdfProspettoDaFirmare(os);
		newPdf.setId(idProspetto);
		newPdf.setProDProspetto(proDProspettoDao.findById(idProspetto).get());

		// ATTENZIONE! Questo IF sostituisce anche il vecchio pdf nel db
		if (!pdfFinded.isPresent()) {
			proDPdfProspettoDao.insert(newPdf);
			operazione = "inserito";
		} else {
			BigDecimal big2022 = new BigDecimal(2022);
			if (newPdf.getProDProspetto().getAnnoProtocollo().compareTo(big2022) >= 0) {
				proDPdfProspettoDao.update(newPdf);
				operazione = "modificato";
			}
		}

		return operazione;

	}

	public ProTProvincia getProvinciaById(Long id) {
		Optional<ProTProvincia> optional = proTProvinciaDao.findOne(id.longValue());
		if (!optional.isPresent()) {
			throw new NotFoundException("Provincia");
		}
		return optional.get();
	}

	public List<ProDRiepilogoProvinciale> caricaElencoRiepilogoProvincialeByIdProspetto(Long idProspetto) {
		return proDRiepilogoProvincialeDao.findByIdProspetto(idProspetto);
	}

	public List<DecodificaGenerica> getComune(String codiceMinisteriale, String descrizioneMin) {
		DecodificaGenerica filtro = new DecodificaGenerica();
		filtro.setCodDecodifica(codiceMinisteriale);
		filtro.setDsDecodifica(descrizioneMin);
		filtro.setFlgAncheNonValidi(false);
		List<DecodificaGenerica> lista = proTComuneDao.findByFilter(filtro);
		return lista;
	}

	public List<DecodificaGenerica> getAtecofin(String codiceMinisteriale, String descrizioneMin) {
		DecodificaGenerica filtro = new DecodificaGenerica();
		filtro.setCodDecodifica(codiceMinisteriale);
		filtro.setDsDecodifica(descrizioneMin);
		filtro.setFlgAncheNonValidi(false);
		List<DecodificaGenerica> lista = proTAtecofinDao.findByFilter(filtro);
		return lista;
	}

	public List<DecodificaGenerica> getCcnl(String codiceMinisteriale, String descrizioneMin) {
		DecodificaGenerica filtro = new DecodificaGenerica();
		filtro.setCodDecodifica(codiceMinisteriale);
		filtro.setDsDecodifica(descrizioneMin);
		filtro.setFlgAncheNonValidi(false);
		List<DecodificaGenerica> lista = proTCcnlDao.findByFilter(filtro);
		return lista;
	}

	public List<ProRProspettoProvincia> getProspettoProvinciaPerProspetto(Long id) {

		return proRProspettoProvinciaDao.findByIdProspetto(id);

	}

	// carica il prospetto completo
	public Prospetto getProspettoCompleto(Long id) {

		Prospetto prospetto = getProspetto(id);

		Optional<ProTComune> comuneEnt = proTComuneDao.findOne(prospetto.getDatiAzienda().getComune().getId());
		Provincia provincia = ProdisMappers.PROVINCIA.toModel(comuneEnt.get().getProTProvincia());
		prospetto.getDatiAzienda().getComune().setProvincia(provincia);
		if (comuneEnt.get().getIdTCpi() != null) {
			prospetto.getDatiAzienda().getComune().setIdTCpi(new BigDecimal(comuneEnt.get().getIdTCpi()));
		}

		Optional<ProTComune> comuneSeleLegEnt = proTComuneDao
				.findOne(prospetto.getDatiAzienda().getSedeLegale().getComune().getId());
		if (comuneSeleLegEnt.get().getIdTCpi() != null) {
			prospetto.getDatiAzienda().getSedeLegale().getComune()
					.setIdTCpi(new BigDecimal(comuneSeleLegEnt.get().getIdTCpi()));
		}

		List<ProDAssPubbliche> assPubblicheEntites = caricaAssPubblichePerPdf(id);
		if (assPubblicheEntites != null && assPubblicheEntites.size() > 0) {
			List<AssPubbliche> assPubbliche = new ArrayList<AssPubbliche>();
			prospetto.setAssPubbliche(assPubbliche);

			for (ProDAssPubbliche assEntity : assPubblicheEntites) {
				assPubbliche.add(ProdisMappers.ASS_PUBBLICHE.toModel(assEntity));
			}
		}

		ProDRiepilogoNazionale riepilogoNazEntity = caricaRiepilogoNazionalePerPdf(id);
		if (riepilogoNazEntity != null) {
			prospetto.setRiepilogoNazionale(ProdisMappers.RIEPILOGO_NAZIONALE.toModel(riepilogoNazEntity));
		}

		List<ProRProspettoProvincia> proRProspettoProvEntities = caricaProspettoProvinciaPerPdf(id);

		List<ProspettoProvincia> proviancias = new ArrayList<ProspettoProvincia>();
		prospetto.setProspettoProvincias(proviancias);

		for (ProRProspettoProvincia rProspettoProv : proRProspettoProvEntities) {

			ProspettoProvincia proProvincia = ProdisMappers.PROSPETTO_PROVINCIA.toModel(rProspettoProv);
			proviancias.add(proProvincia);

			ProDDatiProvinciali datiProvEntity = caricaDatiProvincialiPerPdf(rProspettoProv.getId());
			if (datiProvEntity != null) {
				DatiProvinciali datiProvinciali = ProdisMappers.DATI_PROVINCIALI.toModel(datiProvEntity);
				proProvincia.setDatiProvinciali(datiProvinciali);

				List<ProDLavoratoriInForza> lavoratoryEntity = caricaLavoratoriInForzaPerPdf(
						rProspettoProv.getIdProspettoProv());
				if (lavoratoryEntity != null) {
					datiProvinciali.setLavoratoriInForzas(ProdisMappers.LAVORATORI_IN_FORZA.toModels(lavoratoryEntity));
				}

				List<ProDCategorieEscluse> categorieEsclEntities = caricaCategorieEsclusePerPdf(
						rProspettoProv.getIdProspettoProv());
				if (categorieEsclEntities != null && categorieEsclEntities.size() > 0) {
					datiProvinciali
							.setCategorieEscluses(ProdisMappers.CATEGORIE_ESCLUSE.toModels(categorieEsclEntities));
				}

				List<ProDProvIntermittenti> intermittentiEntities = caricaIntermittentiPerPdf(
						rProspettoProv.getIdProspettoProv());
				if (intermittentiEntities != null && intermittentiEntities.size() > 0) {
					datiProvinciali
							.setProvIntermittentis(ProdisMappers.PROV_INTERMITTENTI.toModels(intermittentiEntities));
				}

				List<ProDProvCompensazioni> provCompensazioni = caricaCompensazioniPerPdf(
						rProspettoProv.getIdProspettoProv());
				if (provCompensazioni != null && provCompensazioni.size() > 0) {
					datiProvinciali.setProvCompensazionis(ProdisMappers.PROV_COMPENSAZIONI.toModels(provCompensazioni));
				}

				List<ProDPartTime> partTimeEntities = caricaPartTimePerPdf(rProspettoProv.getIdProspettoProv());
				if (partTimeEntities != null && partTimeEntities.size() > 0) {
					datiProvinciali.setPartTimes(ProdisMappers.PART_TIME.toModels(partTimeEntities));
				}

				List<ProDPostiLavoroDisp> postiLavEntities = caricaPostiLavoroDispPerPdf(
						rProspettoProv.getIdProspettoProv());
				if (postiLavEntities != null && postiLavEntities.size() > 0) {
					datiProvinciali.setPostiLavoroDisps(ProdisMappers.POSTI_LAVORO_DISP.toModels(postiLavEntities));
				}

				ProDRiepilogoProvinciale riepilogoProvEntity = caricaRiepilogoProvincialePerPdf(
						rProspettoProv.getIdProspettoProv());
				if (riepilogoProvEntity != null) {
					proProvincia
							.setRiepilogoProvinciale(ProdisMappers.RIEPILOGO_PROVINCIALE.toModel(riepilogoProvEntity));
				}

			}
		}

		return prospetto;
	}

	public StatoProspetto getStatoProspetto(Long idStato) {

		Optional<ProTStatoProspetto> statoEnt = proTStatoProspettoDao.findOne(idStato);

		if (statoEnt.isEmpty()) {
			return null;
		}

		return ProdisMappers.STATO_PROSPETTO.toModel(statoEnt.get());

	}

	public EsitoStoreProcedure storeProcedureEseguiCalcoli(Long idProspetto, String cfOperatore,
			Boolean soloScoperture) {
		EsitoStoreProcedure loEsito = proDProspettoDao.storeProcedureEseguiCalcoli(idProspetto, cfOperatore,
				soloScoperture);
		return loEsito;

	}

	public Optional<ProDDatiProvinciali> getDatiProvinciali(long idProspettoProv) {
		return proDDatiProvincialiDao.findOne(idProspettoProv);
	}

}
