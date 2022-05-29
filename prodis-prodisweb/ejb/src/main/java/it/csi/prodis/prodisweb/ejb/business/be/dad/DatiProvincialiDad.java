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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDAssPubblicheDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDCategorieEscluseDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDDatiAziendaDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDDatiProvincialiDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDLavoratoriInForzaDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDParametriDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDPartTimeDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDPostiLavoroDispDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDProspettoDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDProspettoGradualitaDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDProspettoProvSedeDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDProvCompensazioniDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDProvConvenzioneDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDProvEsoneroAutocertDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDProvEsoneroDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDProvGradualitaDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDProvIntermittentiDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDProvSospensioneDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDRiepilogoNazionaleDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDRiepilogoProvincialeDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDVistaElencoProvQ2Dao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProRProspettoProvinciaDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProTAssunzioneProtettaDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProTComuneDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProTProvinciaDao;
import it.csi.prodis.prodisweb.ejb.entity.ProDAssPubbliche;
import it.csi.prodis.prodisweb.ejb.entity.ProDCategorieEscluse;
import it.csi.prodis.prodisweb.ejb.entity.ProDDatiProvinciali;
import it.csi.prodis.prodisweb.ejb.entity.ProDLavoratoriInForza;
import it.csi.prodis.prodisweb.ejb.entity.ProDPartTime;
import it.csi.prodis.prodisweb.ejb.entity.ProDPostiLavoroDisp;
import it.csi.prodis.prodisweb.ejb.entity.ProDProspetto;
import it.csi.prodis.prodisweb.ejb.entity.ProDProspettoProvSede;
import it.csi.prodis.prodisweb.ejb.entity.ProDProvCompensazioni;
import it.csi.prodis.prodisweb.ejb.entity.ProDProvConvenzione;
import it.csi.prodis.prodisweb.ejb.entity.ProDProvEsonero;
import it.csi.prodis.prodisweb.ejb.entity.ProDProvEsoneroAutocert;
import it.csi.prodis.prodisweb.ejb.entity.ProDProvGradualita;
import it.csi.prodis.prodisweb.ejb.entity.ProDProvIntermittenti;
import it.csi.prodis.prodisweb.ejb.entity.ProDProvSospensione;
import it.csi.prodis.prodisweb.ejb.entity.ProDRiepilogoNazionale;
import it.csi.prodis.prodisweb.ejb.entity.ProDRiepilogoProvinciale;
import it.csi.prodis.prodisweb.ejb.entity.ProDVistaElencoProvQ2;
import it.csi.prodis.prodisweb.ejb.entity.ProRProspettoProvincia;
import it.csi.prodis.prodisweb.ejb.entity.ProTComune;
import it.csi.prodis.prodisweb.ejb.entity.ProTProvincia;
import it.csi.prodis.prodisweb.ejb.entity.RitornoPassaggioQ3;
import it.csi.prodis.prodisweb.ejb.exception.NotFoundException;
import it.csi.prodis.prodisweb.ejb.mapper.ProdisMappers;
import it.csi.prodis.prodisweb.ejb.util.ProdisSrvUtil;
import it.csi.prodis.prodisweb.lib.dto.common.DecodificaGenerica;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.AssunzioneProtetta;
import it.csi.prodis.prodisweb.lib.dto.prospetto.AssPubbliche;
import it.csi.prodis.prodisweb.lib.dto.prospetto.CategorieEscluse;
import it.csi.prodis.prodisweb.lib.dto.prospetto.DatiAzienda;
import it.csi.prodis.prodisweb.lib.dto.prospetto.DatiProvinciali;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ElencoProvScoperture;
import it.csi.prodis.prodisweb.lib.dto.prospetto.LavoratoriInForza;
import it.csi.prodis.prodisweb.lib.dto.prospetto.PartTime;
import it.csi.prodis.prodisweb.lib.dto.prospetto.Prospetto;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProspettoGradualita;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProspettoProvincia;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProvConvenzione;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProvEsonero;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProvEsoneroAutocert;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProvGradualita;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProvIntermittenti;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProvSospensione;
import it.csi.prodis.prodisweb.lib.dto.prospetto.RiepilogoProvinciale;
import it.csi.prodis.prodisweb.lib.dto.prospetto.VistaElencoProvQ2;

/**
 * Data Access Delegate for prospetto
 */
@ApplicationScoped
public class DatiProvincialiDad extends BaseDad {
	
	@Inject
	private ProDPostiLavoroDispDao proDPostiLavoroDispDao;
	
	@Inject
	private ProDRiepilogoNazionaleDao proDRiepilogoNazionaleDao;

	@Inject
	private ProDDatiProvincialiDao proDDatiProvincialiDao;

	@Inject
	private ProDVistaElencoProvQ2Dao proDVistaElencoProvQ2Dao;

	@Inject
	private ProRProspettoProvinciaDao proRProspettoProvinciaDao;

	@Inject
	private ProDProvConvenzioneDao proDProvConvenzioneDao;

	@Inject
	private ProDProvEsoneroDao proDProvEsoneroDao;

	@Inject
	private ProDProvEsoneroAutocertDao proDProvEsoneroAutocertDao;

	@Inject
	private ProDProvSospensioneDao proDProvSospensioneDao;

	@Inject
	private ProDProvGradualitaDao proDProvGradualitaDao;

	@Inject
	private ProDLavoratoriInForzaDao proDLavoratoriInForzaDao;

	@Inject
	private ProDProspettoDao proDProspettoDao;

	@Inject
	private ProTProvinciaDao proTProvinciaDao;

	@Inject
	private ProDPartTimeDao proDPartTimeDao;

	@Inject
	private ProDProspettoProvSedeDao proDProspettoProvSedeDao;

	@Inject
	private ProDCategorieEscluseDao proDCategorieEscluseDao;

	@Inject
	private ProTComuneDao proTComuneDao;

	@Inject
	private ProDProvIntermittentiDao proDProvIntermittentiDao;

	@Inject
	private ProDParametriDao proDParamentriDao;

	@Inject
	private ProDDatiAziendaDao proDDatiAziendaDao;

	@Inject
	private ProDAssPubblicheDao proDAssPubblicheDao;

	@Inject
	private ProTAssunzioneProtettaDao proTAssunzioneProtettaDao;

	@Inject
	private ProDProspettoGradualitaDao proDProspettoGradualitaDao;

	@Inject
	private ProDRiepilogoProvincialeDao proDRiepilogoProvincialeDao;
	
	@Inject
	private ProDProvCompensazioniDao proDProvCompensazioniDao;

	/**
	 * Find by idProspettoProv
	 * 
	 * @param Long the idProspettoProv
	 * @return the model instance
	 */
	public DatiProvinciali getDatiProvincialiByIdProspettoProv(Long idProspettoProv) {
		Optional<DatiProvinciali> optional = proDDatiProvincialiDao.findOne(idProspettoProv)
				.map(ProdisMappers.DATI_PROVINCIALI::toModel);
		if (!optional.isPresent()) {
			throw new NotFoundException("DatiProvinciali");
		}
		return optional.get();
	}

	public List<VistaElencoProvQ2> getElencoProvQ2ByIdProspetto(Long idProspetto) {
		List<ProDVistaElencoProvQ2> list = proDVistaElencoProvQ2Dao.findByIdProspetto(idProspetto);
		return ProdisMappers.VISTA_ELENCO_PROV_Q2.toModels(list);
	}

	public List<ProspettoProvincia> getProspettoProvinciaByIdProspetto(Long idProspetto) {
		List<ProRProspettoProvincia> list = proRProspettoProvinciaDao.findByIdProspetto(idProspetto);
		return ProdisMappers.PROSPETTO_PROVINCIA.toModels(list);
	}

	public List<ElencoProvScoperture> getElencoScoperture(Long idProspetto) {
		List<ElencoProvScoperture> list = proRProspettoProvinciaDao.findElencoScopertureByIdProspetto(idProspetto);
		return list;
	}

	// TAB 0

	public ProRProspettoProvincia insertProspettoProvincia(ProspettoProvincia prospettoProvincia, Long idProspetto,
			Long idProvincia) {
		
		Date nuovaData = new Date();

		boolean checkCoupleUnicity = false;

		List<ProRProspettoProvincia> rProspettoProvinciaByProspetto = proRProspettoProvinciaDao
				.findByIdProspetto(idProspetto);

		if (!rProspettoProvinciaByProspetto.isEmpty()) {
			for (ProRProspettoProvincia prpp : rProspettoProvinciaByProspetto) {
				if (prpp.getProTProvincia().getId() == idProvincia) {
					checkCoupleUnicity = true;
					break;
				}
			}
		}

		if (checkCoupleUnicity == false) {

			ProRProspettoProvincia rProspettoProvincia = ProdisMappers.PROSPETTO_PROVINCIA.toEntity(prospettoProvincia);

			Optional<ProDProspetto> prospettoFinded = proDProspettoDao.findById(idProspetto);

			if (prospettoFinded.isPresent()) {
				rProspettoProvincia.setIdProspetto(new BigDecimal(prospettoFinded.get().getIdProspetto()));
			} else {
				throw new NotFoundException("Prospetto");
			}

			Optional<ProTProvincia> provinciaFinded = proTProvinciaDao.findOne(idProvincia);

			if (provinciaFinded.isPresent()) {
				rProspettoProvincia.setProTProvincia(provinciaFinded.get());
			} else {
				throw new NotFoundException("Provincia");
			}

			rProspettoProvincia.setFlgConfermatoQ2("N");

			rProspettoProvincia.setDInserim(nuovaData);
			rProspettoProvincia.setDAggiorn(nuovaData);
			rProspettoProvincia.setdInserim(nuovaData);
			rProspettoProvincia.setdAggiorn(nuovaData);
			rProspettoProvincia = proRProspettoProvinciaDao.insert(rProspettoProvincia);

			ProDDatiProvinciali datiProvinciali = new ProDDatiProvinciali();

			datiProvinciali.setIdProspettoProv(rProspettoProvincia.getIdProspettoProv());

			rProspettoProvincia.setDInserim(nuovaData);
			rProspettoProvincia.setDAggiorn(nuovaData);
			rProspettoProvincia.setdInserim(nuovaData);
			rProspettoProvincia.setdAggiorn(nuovaData);
			datiProvinciali = proDDatiProvincialiDao.insert(datiProvinciali);

			return rProspettoProvincia;

		} else {
			throw new NotFoundException("ProspettoProvincia");
		}

	}

	public void modificaFlagConfermatoQ2(Long idProspettoProv, boolean flagBozza, boolean flagValidazione) {

		Optional<ProRProspettoProvincia> prospettoProvincia = proRProspettoProvinciaDao
				.findOne(idProspettoProv);

		if (prospettoProvincia.isPresent()) {

			if (!flagBozza && flagValidazione) {

				prospettoProvincia.get().setFlgConfermatoQ2("S");

			} else {

				prospettoProvincia.get().setFlgConfermatoQ2("N");

			}

			proRProspettoProvinciaDao.update(prospettoProvincia.get());

		} else {
			
			throw new NotFoundException("ProspettoProvincia");
			
		}

	}

	public DatiProvinciali insertQuadro2Completo(DatiProvinciali datiProvinciali, boolean flagBozza) {
		
		// DATA UTILIZZABILE PER TUTTE LE DAggiorn/dAggiorn
		Date dataAggiornamento = new Date();
		
		// Per l'inserimento di queste liste, ci sono servizi apposta
		datiProvinciali.setProvCompensazionis(null);
		datiProvinciali.setPostiLavoroDisps(null);

		Long idProspettoProv = datiProvinciali.getId().longValue();

		Optional<ProRProspettoProvincia> refProspettoProvincia = proRProspettoProvinciaDao.findOne(idProspettoProv);

		if (!refProspettoProvincia.isPresent()) {
			throw new NotFoundException("ProspettoProvincia");
		}

		ProDDatiProvinciali newDatiProvinciali = ProdisMappers.DATI_PROVINCIALI.toEntity(datiProvinciali);

		// STACCARE I CAMPI

		// prospetto prov sede 
		if (newDatiProvinciali.getProDProspettoProvSede() != null) {
			newDatiProvinciali.setProDProspettoProvSede(null);
		}

		// gradualita
		if (newDatiProvinciali.getProDProvGradualita() != null) {
			newDatiProvinciali.setProDProvGradualita(null);
		}

		// convenzione
		if (newDatiProvinciali.getProDProvConvenzione() != null) {
			newDatiProvinciali.setProDProvConvenzione(null);
		}

		// esonero
		if (newDatiProvinciali.getProDProvEsonero() != null) {
			newDatiProvinciali.setProDProvEsonero(null);
		}

		// esonero auto cert
		if (newDatiProvinciali.getProDProvEsoneroAutocert() != null) {
			newDatiProvinciali.setProDProvEsoneroAutocert(null);
		}
		
		// sospensione
		if (newDatiProvinciali.getProDProvSospensione()!=null) {
			newDatiProvinciali.setProDProvSospensione(null);
		}

		// UPDATE
		
		Optional<ProDDatiProvinciali> datiProvincialiFinded = proDDatiProvincialiDao.findOne(idProspettoProv);
		if (datiProvincialiFinded.isPresent()) {
			newDatiProvinciali.setdInserim(datiProvincialiFinded.get().getdInserim());
			newDatiProvinciali.setCodUserInserim(datiProvincialiFinded.get().getCodUserInserim());
		}
		newDatiProvinciali.setdAggiorn(dataAggiornamento);
		proDDatiProvincialiDao.update(newDatiProvinciali);

		// solo dopo l'inserimento ho l'ID
		final Long datiProvincialiId = newDatiProvinciali.getId();

		insertQuadro2Tab1(datiProvinciali, datiProvincialiId, dataAggiornamento, 
				newDatiProvinciali.getCodUserAggiorn() );

		datiProvinciali = insertQuadro2Tab6(datiProvinciali, datiProvincialiId, 
				dataAggiornamento, newDatiProvinciali.getCodUserAggiorn());

		// set id del prospetto
		datiProvinciali.setId(datiProvincialiId.intValue());

		return datiProvinciali;

	}

	public DatiProvinciali insertQuadro2Tab6(DatiProvinciali datiProvinciali, Long id,
			Date dataAggiornamento, String codUserAggiorn) {

		// CONVENZIONE
		
		Optional<ProDProvConvenzione> oldConvenzione = proDProvConvenzioneDao.findOne(id);
		if (datiProvinciali.getProvConvenzione() != null && datiProvinciali.getProvConvenzione().getId()!=null) {
			ProDProvConvenzione newConvenzione = ProdisMappers.PROV_CONVENZIONE
					.toEntity(datiProvinciali.getProvConvenzione());
			newConvenzione.setId(id);
			newConvenzione.setCodUserAggiorn(codUserAggiorn);
			newConvenzione.setDAggiorn(dataAggiornamento);
			if (oldConvenzione.isPresent()) {
				newConvenzione.setCodUserInserim(oldConvenzione.get().getCodUserInserim());
				newConvenzione.setDInserim(oldConvenzione.get().getDInserim());
				proDProvConvenzioneDao.update(newConvenzione);
			} else {
				newConvenzione.setCodUserInserim(codUserAggiorn);
				newConvenzione.setDInserim(dataAggiornamento);
				proDProvConvenzioneDao.insert(newConvenzione);
			}
			ProvConvenzione convenzioneModel = ProdisMappers.PROV_CONVENZIONE
					.toModel(newConvenzione);
			convenzioneModel.setId(id.intValue());
			datiProvinciali.setProvConvenzione(convenzioneModel);
		} else {
			datiProvinciali.setProvConvenzione(null);
			if (oldConvenzione.isPresent()) {
				proDProvConvenzioneDao.delete(id);
			}
		}
		
		// ESONERO

		Optional<ProDProvEsonero> oldEsonero = proDProvEsoneroDao.findOne(id);
		if (datiProvinciali.getProvEsonero() != null && datiProvinciali.getProvEsonero().getId()!=null) {
			ProDProvEsonero newEsonero = ProdisMappers.PROV_ESONERO.toEntity(datiProvinciali.getProvEsonero());
			newEsonero.setId(id);
			newEsonero.setCodUserAggiorn(codUserAggiorn);
			newEsonero.setdAggiorn(dataAggiornamento);
			if (oldEsonero.isPresent()) {
				newEsonero.setCodUserInserim(oldEsonero.get().getCodUserInserim());
				newEsonero.setdInserim(oldEsonero.get().getdInserim());
				proDProvEsoneroDao.update(newEsonero);
			} else {
				newEsonero.setCodUserInserim(codUserAggiorn);
				newEsonero.setdInserim(dataAggiornamento);
				proDProvEsoneroDao.insert(newEsonero);
			}
			ProvEsonero esoneroModel = ProdisMappers.PROV_ESONERO
					.toModel(newEsonero);
			esoneroModel.setId(id.intValue());
			datiProvinciali.setProvEsonero(esoneroModel);
		} else {
			datiProvinciali.setProvEsonero(null);
			if (oldEsonero.isPresent()) {
				proDProvEsoneroDao.delete(id);
			}
		}
		
		// ESONERO AUTOCERT

		Optional<ProDProvEsoneroAutocert> oldEA = proDProvEsoneroAutocertDao.findOne(id);
		if (datiProvinciali.getProvEsoneroAutocert() != null && datiProvinciali.getProvEsoneroAutocert()!=null) {
			ProDProvEsoneroAutocert newEsoneroAutocert = ProdisMappers.PROV_ESONERO_AUTOCERT
					.toEntity(datiProvinciali.getProvEsoneroAutocert());
			newEsoneroAutocert.setId(id);
			newEsoneroAutocert.setCodUserAggiorn(codUserAggiorn);
			newEsoneroAutocert.setdAggiorn(dataAggiornamento);
			if (oldEA.isPresent()) {
				newEsoneroAutocert.setCodUserInserim(oldEA.get().getCodUserInserim());
				newEsoneroAutocert.setdInserim(oldEA.get().getdInserim());
				proDProvEsoneroAutocertDao.update(newEsoneroAutocert);
			} else {
				newEsoneroAutocert.setCodUserInserim(codUserAggiorn);
				newEsoneroAutocert.setdInserim(dataAggiornamento);
				proDProvEsoneroAutocertDao.insert(newEsoneroAutocert);
			}
			ProvEsoneroAutocert esoneroAutocertModel = ProdisMappers.PROV_ESONERO_AUTOCERT
					.toModel(newEsoneroAutocert);
			esoneroAutocertModel.setId(id.intValue());
			datiProvinciali.setProvEsoneroAutocert(esoneroAutocertModel);
		} else {
			datiProvinciali.setProvEsoneroAutocert(null);
			if (oldEA.isPresent()) {
				proDProvEsoneroAutocertDao.delete(id);
			}
		}
		
		// SOSPENSIONE

		Optional<ProDProvSospensione> oldSospensione = proDProvSospensioneDao.findOne(id);
		if (datiProvinciali.getProvSospensione() != null && datiProvinciali.getProvSospensione().getId()!=null){
			ProDProvSospensione newSospensione = ProdisMappers.PROV_SOSPENSIONE
					.toEntity(datiProvinciali.getProvSospensione());
			newSospensione.setId(id);
			newSospensione.setCodUserAggiorn(codUserAggiorn);
			newSospensione.setdAggiorn(dataAggiornamento);
			if (oldSospensione.isPresent()) {
				newSospensione.setCodUserInserim(oldSospensione.get().getCodUserInserim());
				newSospensione.setdInserim(oldSospensione.get().getdInserim());
				proDProvSospensioneDao.update(newSospensione);
			} else {
				newSospensione.setCodUserInserim(codUserAggiorn);
				newSospensione.setdInserim(dataAggiornamento);
				proDProvSospensioneDao.insert(newSospensione);
			}
			ProvSospensione sospensioneModel = ProdisMappers.PROV_SOSPENSIONE
					.toModel(newSospensione);
			sospensioneModel.setId(id.intValue());
			datiProvinciali.setProvSospensione(sospensioneModel);
		} else {
			datiProvinciali.setProvSospensione(null);
			if (oldSospensione.isPresent()) {
				proDProvSospensioneDao.delete(id);
			}
		}
		
		// GRADUALITA

		Optional<ProDProvGradualita> oldGradualita = proDProvGradualitaDao.findOne(id);
		if (datiProvinciali.getProvGradualita() != null && datiProvinciali.getProvGradualita().getId()!=null) {
			ProDProvGradualita newGradualita = ProdisMappers.PROV_GRADUALITA
					.toEntity(datiProvinciali.getProvGradualita());
			newGradualita.setId(id);
			newGradualita.setCodUserAggiorn(codUserAggiorn);
			newGradualita.setdAggiorn(dataAggiornamento);
			if (oldGradualita.isPresent()) {
				newGradualita.setCodUserInserim(oldGradualita.get().getCodUserInserim());
				newGradualita.setdInserim(oldGradualita.get().getdInserim());
				proDProvGradualitaDao.update(newGradualita);
			} else {
				newGradualita.setCodUserInserim(codUserAggiorn);
				newGradualita.setdInserim(dataAggiornamento);
				proDProvGradualitaDao.insert(newGradualita);
			}
			ProvGradualita gradualitaModel = ProdisMappers.PROV_GRADUALITA
					.toModel(newGradualita);
			gradualitaModel.setId(id.intValue());
			datiProvinciali.setProvGradualita(gradualitaModel);
		} else {
			datiProvinciali.setProvGradualita(null);
			if (oldGradualita.isPresent()) {
				proDProvGradualitaDao.delete(id);
			}
		}

		return datiProvinciali;

	}

	public void insertQuadro2Tab1(DatiProvinciali datiProvinciali, Long id, 
			Date dataAggiornamento, String codUserAggiorn) {

		ProDProspettoProvSede newProspettoProvSede = ProdisMappers.PROSPETTO_PROV_SEDE
				.toEntity(datiProvinciali.getProspettoProvSede());
		newProspettoProvSede.setId(id);
		/* Pezzo per gestire gli oggetti con id = null o id = 0 */
		if (newProspettoProvSede.getProTComune() != null && newProspettoProvSede.getProTComune().getIdTComune() == null) {
			newProspettoProvSede.setProTComune(null);
		}
		newProspettoProvSede.setDAggiorn(dataAggiornamento);
		newProspettoProvSede.setCodUserAggiorn(codUserAggiorn);
		Optional<ProDProspettoProvSede> oldProspettoSede = proDProspettoProvSedeDao.findOne(id);
		if (oldProspettoSede.isPresent()) {
			newProspettoProvSede.setDInserim(oldProspettoSede.get().getDInserim());
			newProspettoProvSede.setCodUserInserim(oldProspettoSede.get().getCodUserInserim());
			proDProspettoProvSedeDao.update(newProspettoProvSede);
		} else {
			newProspettoProvSede.setDInserim(dataAggiornamento);
			newProspettoProvSede.setCodUserInserim(codUserAggiorn);
			proDProspettoProvSedeDao.insert(newProspettoProvSede);
		}

	}

	public ProTComune getComuneById(Long id) {
		Optional<ProTComune> optional = proTComuneDao.findOne(id);
		if (!optional.isPresent()) {
			throw new NotFoundException("Comune");
		}
		return optional.get();
	}

	public List<CategorieEscluse> getCategorieEscluseByIdProspettoProv(Long idProspettoProv) {
		List<ProDCategorieEscluse> list = proDCategorieEscluseDao
				.findByIdProspettoProv(new BigDecimal(idProspettoProv));
		return ProdisMappers.CATEGORIE_ESCLUSE.toModels(list);
	}

	public List<ProvIntermittenti> getProvIntermittentiByIdProspettoProv(Long idProspettoProv) {
		List<ProDProvIntermittenti> list = proDProvIntermittentiDao
				.findByIdProspettoProv(new BigDecimal(idProspettoProv));
		return ProdisMappers.PROV_INTERMITTENTI.toModels(list);
	}

	public List<PartTime> getPartTimeByIdProspettoProv(Long idProspettoProv) {
		List<ProDPartTime> list = proDPartTimeDao.findByIdProspettoProv(new BigDecimal(idProspettoProv));
		return ProdisMappers.PART_TIME.toModels(list);
	}

	public List<LavoratoriInForza> getLavoratoriInForzaByIdProspettoProv(Long idProspettoProv) {
		List<ProDLavoratoriInForza> list = proDLavoratoriInForzaDao
				.findByIdProspettoProv(new BigDecimal(idProspettoProv));
		return ProdisMappers.LAVORATORI_IN_FORZA.toModels(list);
	}

	public String getParametroByNome(String parametro) {
		DecodificaGenerica valore = proDParamentriDao.findByNome(parametro);
		if (ProdisSrvUtil.isVoid(valore)) {
			throw new NotFoundException("parametro");
		}
		return valore.getCodDecodifica();
	}

	public ProspettoProvincia getProspettoProvinciaById(Integer id) {
		Optional<ProspettoProvincia> optional = proRProspettoProvinciaDao.findOne(id.longValue())
				.map(ProdisMappers.PROSPETTO_PROVINCIA::toModel);
		if (!optional.isPresent()) {
			throw new NotFoundException("ProspettoProvincia");
		}
		return optional.get();
	}

	public List<ProTComune> getComuni(Long idProvincia, String codComuneMin, String dsProTComune, Date data) {
		return proTComuneDao.find(idProvincia, codComuneMin, dsProTComune, data);
	}

	public List<DecodificaGenerica> getComuneValido(String codComuneMin, String dsProTComune) {
		DecodificaGenerica filtro = new DecodificaGenerica();
		filtro.setCodDecodifica(codComuneMin);
		filtro.setDsDecodifica(dsProTComune);
		return proTComuneDao.findByFilter(filtro);
	}

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

	public AssunzioneProtetta getTipoAssunzioneProtettaObjById(Long id) {
		Optional<AssunzioneProtetta> optional = proTAssunzioneProtettaDao.findOne(id)
				.map(ProdisMappers.ASSUNZIONE_PROTETTA::toModel);
		if (!optional.isPresent()) {
			throw new NotFoundException("AssunzioneProtetta");
		}
		return optional.get();
	}

	public RitornoPassaggioQ3 confermaProvince(Long idProspetto) {
		
		RitornoPassaggioQ3 rit = new RitornoPassaggioQ3();

		Optional<ProDProspetto> prospettoFinded = proDProspettoDao.findById(idProspetto);

		if (!prospettoFinded.isPresent()) {
			throw new NotFoundException("Prospetto");
		}

		List<ProRProspettoProvincia> prospettoProvFinded = proRProspettoProvinciaDao.findByIdProspetto(idProspetto);

		if (prospettoProvFinded.isEmpty()) {
			throw new NotFoundException("ProspettoProvincia");
		}

		for (ProRProspettoProvincia relazione : prospettoProvFinded) {

			if (relazione.getFlgConfermatoQ2() == "N") {
				rit.setFlagConferma(false);
				return rit;
			}

		}

		rit = proDDatiProvincialiDao.passaggioQ3(
					idProspetto,
					prospettoFinded.get().getCodUserAggiorn(),
					false);

		return rit;
		
	}

	public RiepilogoProvinciale getRiepilogoProvincialeByIdProspettoProvincia(Long idProspettoProvincia) {
		Optional<RiepilogoProvinciale> optional = proDRiepilogoProvincialeDao.findOne(idProspettoProvincia)
				.map(ProdisMappers.RIEPILOGO_PROVINCIALE::toModel);
		if (!optional.isPresent()) {
			throw new NotFoundException("RiepilogoProvinciale");
		}
		return optional.get();
	}

	public Boolean deleteDatiProvinciali(Long idProspettoProv) {
		
		Optional<ProRProspettoProvincia> prospettoProvinciaFinded = proRProspettoProvinciaDao.findOne(idProspettoProv);
		
		if (!prospettoProvinciaFinded.isPresent()) {
			throw new NotFoundException("ProspettoProvincia");
		}
		
		Optional<ProDDatiProvinciali> datiProvincialiFinded = proDDatiProvincialiDao.findOne(idProspettoProv);
		
		if (datiProvincialiFinded.isPresent()) {
			
			// CANCELLO PROSPETTO PROV SEDE
			
			Optional<ProDProspettoProvSede> prospettoProvSedeFinded = proDProspettoProvSedeDao.findOne(idProspettoProv);
			
			if (prospettoProvSedeFinded.isPresent()) {
				proDProspettoProvSedeDao.delete(idProspettoProv);
			}
			
			// CANCELLO CONVENZIONE
			
			Optional<ProDProvConvenzione> convenzioneFinded = proDProvConvenzioneDao.findOne(idProspettoProv);
			
			if (convenzioneFinded.isPresent()) {
				proDProvConvenzioneDao.delete(idProspettoProv);
			}
			
			// CANCELLO ESONERO
			
			Optional<ProDProvEsonero> esoneroFinded = proDProvEsoneroDao.findOne(idProspettoProv);
			
			if (esoneroFinded.isPresent()) {
				proDProvEsoneroDao.delete(idProspettoProv);
			}
			
			// CANCELLO LAVORATORI
			
			List<ProDLavoratoriInForza> lavoratoriFinded = proDLavoratoriInForzaDao.findByIdProspettoProv(new BigDecimal(idProspettoProv));
			
			if (lavoratoriFinded!=null) {
				
				if (!lavoratoriFinded.isEmpty()) {
					
					for (ProDLavoratoriInForza lav : lavoratoriFinded) {
						
						proDLavoratoriInForzaDao.delete(lav.getId());
						
					}
			
				}
				
			}
			
			// CANCELLO PART TIME
			
			List<ProDPartTime> partTimeFinded = proDPartTimeDao.findByIdProspettoProv(new BigDecimal(idProspettoProv));
			
			if (partTimeFinded!=null) {
				
				if (!partTimeFinded.isEmpty()) {
					
					for (ProDPartTime pt : partTimeFinded) {
						proDPartTimeDao.delete(pt.getId());
					}
					
				}
				
			}
			
			// CANCELLO INTERMITTENTI
			
			List<ProDProvIntermittenti> intermittentiFinded = proDProvIntermittentiDao.findByIdProspettoProv(new BigDecimal(idProspettoProv));
			
			if (intermittentiFinded!=null) {
				
				if (!intermittentiFinded.isEmpty()) {
					
					for (ProDProvIntermittenti interm : intermittentiFinded) {
						proDProvIntermittentiDao.delete(interm.getId());
					}
					
				}
				
			}
			
			// CANCELLO CATEGORIE ESCLUSE
			
			List<ProDCategorieEscluse> categorieEscluseFinded = proDCategorieEscluseDao.findByIdProspettoProv(new BigDecimal(idProspettoProv));
			
			if (categorieEscluseFinded!=null) {
			
				if (!categorieEscluseFinded.isEmpty()) {
				
					for (ProDCategorieEscluse ce : categorieEscluseFinded) {
						
						proDCategorieEscluseDao.delete(ce.getId());
						
					}
					
				}
				
			}
			
			// CANCELLO POSTI DI LAVORO DISP
			
			List<ProDPostiLavoroDisp> postiLavoroDispFinded = proDPostiLavoroDispDao.findByField(idProspettoProv);
			
			if (postiLavoroDispFinded!=null) {
				
				if (!postiLavoroDispFinded.isEmpty()) {
				
					for (ProDPostiLavoroDisp pld : postiLavoroDispFinded) {
						
						proDPostiLavoroDispDao.delete(pld.getId());
						
					}
					
				}
				
			}
			
			// CANCELLO COMPENSAZIONI
			
			List<ProDProvCompensazioni> compensazioniFinded = proDProvCompensazioniDao.findByIdProspettoProv(idProspettoProv);
						
			if (compensazioniFinded!=null) {
							
				if (!compensazioniFinded.isEmpty()) {
							
					for (ProDProvCompensazioni comp : compensazioniFinded) {
									
						proDProvCompensazioniDao.delete(comp.getId());
									
					}
								
				}
							
			}
			
			// CANCELLO SOSOPENSIONE
			
			Optional<ProDProvSospensione> sospensioneFinded = proDProvSospensioneDao.findOne(idProspettoProv);
			
			if (sospensioneFinded.isPresent()) {
				proDProvSospensioneDao.delete(idProspettoProv);
			}
			
			// CANCELLO GRADUALITA
			
			Optional<ProDProvGradualita> gradualitaFinded = proDProvGradualitaDao.findOne(idProspettoProv);
			
			if (gradualitaFinded.isPresent()) {
				proDProvGradualitaDao.delete(idProspettoProv);
			}
			
			// CANCELLO ESONERO AUTOCERT
			
			Optional<ProDProvEsoneroAutocert> esoneroAutocertFinded = proDProvEsoneroAutocertDao.findOne(idProspettoProv);
			
			if (esoneroAutocertFinded.isPresent()) {
				proDProvEsoneroAutocertDao.delete(idProspettoProv);
			}

		}
		
		proDDatiProvincialiDao.delete(idProspettoProv);
		
		Optional<ProDRiepilogoProvinciale> rpFinded = proDRiepilogoProvincialeDao.findOne(idProspettoProv);
		
		if (rpFinded.isPresent()) {
			proDRiepilogoProvincialeDao.delete(idProspettoProv);
		}
		
		proRProspettoProvinciaDao.delete(idProspettoProv);
		
		return true;
		
	}

	public Optional<ProDRiepilogoNazionale> getRiepilogoNazionale(Long id) {
		return proDRiepilogoNazionaleDao.findById(id);
	}

	public Long getIdProvinciaBySigla(String siglaProvincia) {
		Long idProvincia = proTProvinciaDao.findIdBySigla(siglaProvincia);
		return idProvincia;
	}

	
}
