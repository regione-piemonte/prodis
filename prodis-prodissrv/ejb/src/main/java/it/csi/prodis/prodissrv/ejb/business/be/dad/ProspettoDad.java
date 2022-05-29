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
package it.csi.prodis.prodissrv.ejb.business.be.dad;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.prodis.prodissrv.ejb.business.be.dad.sort.ProspettoSort;
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProDAssPubblicheDao;
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProDCategorieEscluseDao;
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProDDatiAziendaDao;
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProDDatiProvincialiDao;
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProDImportErroriDao;
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProDLavoratoriInForzaDao;
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProDParametriDao;
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProDPartTimeDao;
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProDPdfProspettoDao;
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProDPostiLavoroDispDao;
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProDProspettoDao;
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProDProspettoGradualitaDao;
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProDProspettoProvSedeDao;
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProDProvCompensazioniDao;
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProDProvConvenzioneDao;
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProDProvEsoneroAutocertDao;
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProDProvEsoneroDao;
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProDProvGradualitaDao;
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProDProvIntermittentiDao;
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProDProvSospensioneDao;
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProDRiepilogoNazionaleDao;
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProDRiepilogoProvincialeDao;
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProDSedeLegaleDao;
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProRProspettoProvinciaDao;
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProTAssunzioneProtettaDao;
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProTCausaSospensioneDao;
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProTSoggettiDao;
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProTStatoConcessioneDao;
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProTStatoProspettoDao;
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProTStatoVerificaDao;
import it.csi.prodis.prodissrv.ejb.entity.EsitoStoreProcedure;
import it.csi.prodis.prodissrv.ejb.entity.ProDAssPubbliche;
import it.csi.prodis.prodissrv.ejb.entity.ProDAssPubblichePK;
import it.csi.prodis.prodissrv.ejb.entity.ProDCategorieEscluse;
import it.csi.prodis.prodissrv.ejb.entity.ProDDatiAzienda;
import it.csi.prodis.prodissrv.ejb.entity.ProDDatiProvinciali;
import it.csi.prodis.prodissrv.ejb.entity.ProDImportErrori;
import it.csi.prodis.prodissrv.ejb.entity.ProDLavoratoriInForza;
import it.csi.prodis.prodissrv.ejb.entity.ProDPartTime;
import it.csi.prodis.prodissrv.ejb.entity.ProDPostiLavoroDisp;
import it.csi.prodis.prodissrv.ejb.entity.ProDProspetto;
import it.csi.prodis.prodissrv.ejb.entity.ProDProspettoGradualita;
import it.csi.prodis.prodissrv.ejb.entity.ProDProspettoProvSede;
import it.csi.prodis.prodissrv.ejb.entity.ProDProvCompensazioni;
import it.csi.prodis.prodissrv.ejb.entity.ProDProvConvenzione;
import it.csi.prodis.prodissrv.ejb.entity.ProDProvEsonero;
import it.csi.prodis.prodissrv.ejb.entity.ProDProvEsoneroAutocert;
import it.csi.prodis.prodissrv.ejb.entity.ProDProvGradualita;
import it.csi.prodis.prodissrv.ejb.entity.ProDProvIntermittenti;
import it.csi.prodis.prodissrv.ejb.entity.ProDProvSospensione;
import it.csi.prodis.prodissrv.ejb.entity.ProDRiepilogoNazionale;
import it.csi.prodis.prodissrv.ejb.entity.ProDRiepilogoProvinciale;
import it.csi.prodis.prodissrv.ejb.entity.ProDSedeLegale;
import it.csi.prodis.prodissrv.ejb.entity.ProRProspettoProvincia;
import it.csi.prodis.prodissrv.ejb.entity.ProTAssunzioneProtetta;
import it.csi.prodis.prodissrv.ejb.entity.ProTAtecofin;
import it.csi.prodis.prodissrv.ejb.entity.ProTCategoriaAzienda;
import it.csi.prodis.prodissrv.ejb.entity.ProTCategoriaEscluse;
import it.csi.prodis.prodissrv.ejb.entity.ProTCausaSospensione;
import it.csi.prodis.prodissrv.ejb.entity.ProTCcnl;
import it.csi.prodis.prodissrv.ejb.entity.ProTComune;
import it.csi.prodis.prodissrv.ejb.entity.ProTComunicazione;
import it.csi.prodis.prodissrv.ejb.entity.ProTContratti;
import it.csi.prodis.prodissrv.ejb.entity.ProTCpi;
import it.csi.prodis.prodissrv.ejb.entity.ProTDichiarante;
import it.csi.prodis.prodissrv.ejb.entity.ProTIstat2001livello5;
import it.csi.prodis.prodissrv.ejb.entity.ProTProvincia;
import it.csi.prodis.prodissrv.ejb.entity.ProTRegione;
import it.csi.prodis.prodissrv.ejb.entity.ProTSoggetti;
import it.csi.prodis.prodissrv.ejb.entity.ProTStatiEsteri;
import it.csi.prodis.prodissrv.ejb.entity.ProTStatoConcessione;
import it.csi.prodis.prodissrv.ejb.entity.ProTStatoProspetto;
import it.csi.prodis.prodissrv.ejb.entity.ProTStatoVerifica;
import it.csi.prodis.prodissrv.ejb.entity.ProTTipoRipropPt;
import it.csi.prodis.prodissrv.ejb.exception.NotFoundException;
import it.csi.prodis.prodissrv.ejb.mapper.ProdisMappers;
import it.csi.prodis.prodissrv.ejb.util.ConstantsProdis;
import it.csi.prodis.prodissrv.ejb.util.jpa.Page;
import it.csi.prodis.prodissrv.lib.dto.BaseDto;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.AssunzioneProtetta;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Atecofin;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.CategoriaAzienda;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.CategoriaEscluse;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.CausaSospensione;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Ccnl;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Comune;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Comunicazione;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Contratti;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Cpi;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Dichiarante;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Istat2001livello5;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Provincia;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Regione;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Soggetti;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.StatiEsteri;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.StatoConcessione;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.TipoRipropPt;
import it.csi.prodis.prodissrv.lib.dto.prospetto.AssPubbliche;
import it.csi.prodis.prodissrv.lib.dto.prospetto.CategorieEscluse;
import it.csi.prodis.prodissrv.lib.dto.prospetto.DatiAzienda;
import it.csi.prodis.prodissrv.lib.dto.prospetto.DatiProvinciali;
import it.csi.prodis.prodissrv.lib.dto.prospetto.FilterServiziProdis;
import it.csi.prodis.prodissrv.lib.dto.prospetto.LavoratoriInForza;
import it.csi.prodis.prodissrv.lib.dto.prospetto.PartTime;
import it.csi.prodis.prodissrv.lib.dto.prospetto.PdfProspetto;
import it.csi.prodis.prodissrv.lib.dto.prospetto.PostiLavoroDisp;
import it.csi.prodis.prodissrv.lib.dto.prospetto.Prospetto;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProspettoGradualita;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProspettoProvSede;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProspettoProvincia;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProvCompensazioni;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProvConvenzione;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProvEsonero;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProvEsoneroAutocert;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProvGradualita;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProvIntermittenti;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProvSospensione;
import it.csi.prodis.prodissrv.lib.dto.prospetto.RiepilogoNazionale;
import it.csi.prodis.prodissrv.lib.dto.prospetto.RiepilogoProvinciale;
import it.csi.prodis.prodissrv.lib.dto.prospetto.SedeLegale;
import it.csi.prodis.prodissrv.lib.util.pagination.PagedList;
import it.csi.prodis.prodissrv.lib.util.pagination.Sort;

/**
 * Data Access Delegate for prospetto
 */
@ApplicationScoped
public class ProspettoDad extends BaseDad {

	@Inject
	private ProDProspettoDao proDProspettoDao;

	@Inject
	private ProDDatiAziendaDao proDDatiAziendaDao;

	@Inject
	private ProDParametriDao proDParamentriDao;

	@Inject
	private ProDPdfProspettoDao proDPdfProspettoDao;
	
	@Inject
	private ProDImportErroriDao proDImportErroriDao;
	
	@Inject
	private ProDAssPubblicheDao proDAssPubblicheDao;
	
	@Inject
	private ProDSedeLegaleDao proDSedeLegaleDao;
	
	@Inject
	private ProTStatoProspettoDao proTStatoProspettoDao;
	
	@Inject
	private ProTAssunzioneProtettaDao proTAssunzioneProtettaDao;
	
	@Inject
	private ProDProspettoGradualitaDao proDProspettoGradualitaDao;
	
	@Inject
	private ProDRiepilogoNazionaleDao proDRiepilogoNazionaleDao;
	
	@Inject
	private ProRProspettoProvinciaDao proRProspettoProvinciaDao;
	
	@Inject
	private ProDRiepilogoProvincialeDao proDRiepilogoProvincialeDao;
	
	@Inject
	private ProDDatiProvincialiDao proDDatiProvincialiDao;
	
	@Inject
	private ProDPostiLavoroDispDao proDPostiLavoroDispDao;
	
	@Inject
	private ProDProvCompensazioniDao proDProvCompensazioniDao;
	
	@Inject
	private ProDProvGradualitaDao proDProvGradualitaDao;
	
	@Inject
	private ProDProvSospensioneDao proDProvSospensioneDao;
	
	@Inject
	private ProDProvConvenzioneDao proDProvConvenzioneDao;
	
	@Inject
	private ProDProvEsoneroDao proDProvEsoneroDao;
	
	@Inject
	private ProDProvEsoneroAutocertDao proDProvEsoneroAutocertDao;
	
	@Inject
	private ProDLavoratoriInForzaDao proDLavoratoriInForzaDao;
	
	@Inject
	private ProDCategorieEscluseDao proDCategorieEscluseDao;
	
	@Inject
	private ProDProspettoProvSedeDao proDProspettoProvSedeDao;
	
	@Inject
	private ProTStatoConcessioneDao proTStatoConcessioneDao;
	
	@Inject
	private ProDPartTimeDao proDPartTimeDao;
	
	@Inject
	private ProTSoggettiDao proTSoggettiDao;
	
	@Inject
	private ProTStatoVerificaDao proTStatoVerificaDao;
	
	@Inject
	private ProTCausaSospensioneDao proTCausaSospensioneDao;
	
	@Inject
	private ProDProvIntermittentiDao proDProvIntermittentiDao;

	public long countRicerca(FilterServiziProdis ricercaProspetto) {
		long count = proDProspettoDao.countRicerca(ricercaProspetto);
		return count;
	}

	public PagedList<Prospetto> getRicerca(int page, int size, Sort sort, FilterServiziProdis ricercaProspetto) {
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

	public String getParametroByNome(String parametro) {
		String valore = proDParamentriDao.findByNome(parametro);
		if (valore.isEmpty()) {
			throw new NotFoundException("parametro");
		}
		return valore;
	}

	/*
	 * Servizio che restituisce l'elenco dei codici ministeriali delle province
	 * legato al prospetto per il servizio di ricerca findByFilterTestataProspetto
	 */
	public List<String> getCodiceMinProvinceByIdProspetto(Long idProspetto) {
		List<String> elencoCodProvince = proDProspettoDao.findProvinceByIdProspetto(idProspetto);
		return elencoCodProvince;
	}

	/**
	 * Find by id
	 * 
	 * @param uuid the uuid
	 * @return the model instance
	 */
	public Prospetto getDatiNazionali(Long idProspetto) {

		Optional<Prospetto> ilProspettoTrovato = proDProspettoDao.findOne(idProspetto)
				.map(ProdisMappers.PROSPETTO::toModel);

		if (!ilProspettoTrovato.isPresent()) {
			throw new NotFoundException("Prospetto");
		} else {

			Optional<DatiAzienda> optionalDatiAziendaModel = proDDatiAziendaDao.findOne(idProspetto)
					.map(ProdisMappers.DATI_AZIENDA::toModel);
			if (optionalDatiAziendaModel.isPresent()) {
				ilProspettoTrovato.get().setDatiAzienda(optionalDatiAziendaModel.get());
			}

			/* qui bisogna caricare i dati Nazionali */
			proDProspettoDao.findDatiNazionaliByIdProspetto(ilProspettoTrovato.get());

			/* qui bisogna caricare i dati Provinciali */
			proDProspettoDao.findDatiProvincialiByIdProspetto(ilProspettoTrovato.get());

		}

		return ilProspettoTrovato.get();
	}

	public PdfProspetto getPdfProspetto(Long idProspetto) {

		Optional<PdfProspetto> prospettoPdf = proDPdfProspettoDao.findOne(idProspetto)
				.map(ProdisMappers.PDF_PROSPETTO::toModel);
		if (prospettoPdf.isPresent()) {
			return prospettoPdf.get();
		}
		return null;
	}
	
	public void deleteImportErrori(Long idComunicazione) {
		proDImportErroriDao.deleteErroriComunicazione(idComunicazione);
	}
	
	public void insertImportErrori(ProDImportErrori error) {
		proDImportErroriDao.insert(error);
	}
	
	public Long insertProspettoCompleto(Prospetto prospetto) {
		
		Date dataOdierna = new Date();
		
		final String userMinistero = ConstantsProdis.UTENTE_IMPORT_PROSPETTO_DA_SPICOM;
		
		// stacco la lista di prospetto provincia
		List<ProspettoProvincia> prospettoProvinciaList = null;
		if (prospetto.getProspettoProvincias()!=null && !prospetto.getProspettoProvincias().isEmpty()) {
			prospettoProvinciaList = prospetto.getProspettoProvincias();
			prospetto.setProspettoProvincias(null);
		}
		
		// stacco i dati azienda (ATTENZIONE! contengono sede legale)
		DatiAzienda datiAzienda = null;
		if (prospetto.getDatiAzienda()!=null) {
			datiAzienda = prospetto.getDatiAzienda();
			prospetto.setDatiAzienda(null);
		}
		
		// stacco la lista di ass pubbliche
		List<AssPubbliche> assPubblicheList = null;
		if (prospetto.getAssPubbliche()!=null && !prospetto.getAssPubbliche().isEmpty()) {
			assPubblicheList = prospetto.getAssPubbliche();
			prospetto.setAssPubbliche(null);
		}
		
		// stacco prospetto gradualita
		ProspettoGradualita prospettoGradualita = null;
		if (prospetto.getProspettoGradualita()!=null) {
			prospettoGradualita = prospetto.getProspettoGradualita();
			prospetto.setProspettoGradualita(null);
		}
		
		// stacco riepilogo nazionale
		RiepilogoNazionale riepilogoNazionale = null;
		if (prospetto.getRiepilogoNazionale()!=null) {
			riepilogoNazionale = prospetto.getRiepilogoNazionale();
			prospetto.setRiepilogoNazionale(null);
		}
		
		// inserisco il prospetto in modo da avere l'ID
		ProDProspetto prospettoEntity = ProdisMappers.PROSPETTO.toEntity(prospetto);
		prospettoEntity.setdAggiorn(dataOdierna);
		prospettoEntity.setdInserim(dataOdierna);
		prospettoEntity.setFlgVisitaIspettiva("N");
		prospettoEntity.setFlgConfermatoQ1("S");
		prospettoEntity.setTipoProvenienza("M");
		//prospettoEntity.setFlgSospensionePerMobilita(null);
		
		if (prospetto.getSoggetti()!=null) {
			Optional<ProTSoggetti> soggettiFinded = proTSoggettiDao.findOne(prospetto.getSoggetti().getId());
			if (soggettiFinded.isPresent()) {
				prospettoEntity.setProTSoggetti(soggettiFinded.get());
			}
		}
		
		if (prospetto.getStatoVerifica()!=null) {
			Optional<ProTStatoVerifica> statoVerificaFinded = proTStatoVerificaDao.findOne(prospetto.getStatoVerifica().getId());
			if (statoVerificaFinded.isPresent()) {
				prospettoEntity.setProTStatoVerifica(statoVerificaFinded.get());
			}
		}

		Optional<ProTStatoProspetto> stato = proTStatoProspettoDao.findOne(3L);
		if (stato.isPresent()) {
			prospettoEntity.setProTStatoProspetto(stato.get());
		}

		prospettoEntity.setCodUserInserim(userMinistero);
		prospettoEntity.setCodUserAggiorn(userMinistero);
		
		ProDProspetto prospettoInserito = proDProspettoDao.insert(prospettoEntity);
		Long idProspetto = prospettoInserito.getId();
		
		// inserisco le ass pubbliche
		if(assPubblicheList != null) {
			for(AssPubbliche ap: assPubblicheList) {
				ProDAssPubbliche apEntity = ProdisMappers.ASS_PUBBLICHE.toEntity(ap);
				ProDAssPubblichePK pk = new ProDAssPubblichePK();
				pk.setIdTRegione(ap.getRegione().getId());
				pk.setIdProspetto(idProspetto);
				apEntity.setId(pk);
				apEntity.setDAggiorn(dataOdierna);
				apEntity.setDInserim(dataOdierna);
				apEntity.setCodUserInserim(userMinistero);
				apEntity.setCodUserAggiorn(userMinistero);
				proDAssPubblicheDao.insert(apEntity);
			}
		}
		
		// inserisco i dati azienda non prima di aver gestito la sede legale
		if (datiAzienda!=null) {
			
			// stacco la sede legale
			SedeLegale sedeLegale = null;
			if (datiAzienda.getSedeLegale()!=null) {
				sedeLegale = datiAzienda.getSedeLegale();
				datiAzienda.setSedeLegale(null);
			}
			
			// inserisco i dati azienda
			ProDDatiAzienda datiAziendaEntity = ProdisMappers.DATI_AZIENDA.toEntity(datiAzienda);
			datiAziendaEntity.setId(idProspetto);
			datiAziendaEntity.setdAggiorn(dataOdierna);
			datiAziendaEntity.setdInserim(dataOdierna);
			datiAziendaEntity.setCodUserInserim(userMinistero);
			datiAziendaEntity.setCodUserAggiorn(userMinistero);
			
			if (datiAziendaEntity.getFlgCapogruppoEstero()==null
					|| datiAziendaEntity.getFlgCapogruppoEstero().equals("0") 
					|| datiAziendaEntity.getFlgCapogruppoEstero().equals("")){
				datiAziendaEntity.setFlgCapogruppoEstero(null);	
			}
			
			proDDatiAziendaDao.insert(datiAziendaEntity);
			
			// inserisco sede legale
			if (sedeLegale!=null) {
				ProDSedeLegale sedeLegaleEntity = ProdisMappers.SEDE_LEGALE.toEntity(sedeLegale);
				sedeLegaleEntity.setId(idProspetto);
				sedeLegaleEntity.setDAggiorn(dataOdierna);
				sedeLegaleEntity.setDInserim(dataOdierna);
				sedeLegaleEntity.setCodUserInserim(userMinistero);
				sedeLegaleEntity.setCodUserAggiorn(userMinistero);
				proDSedeLegaleDao.insert(sedeLegaleEntity);
			}
			
		}
		
		// inserisco prospetto gradualita
		if (prospettoGradualita!=null) {
			ProDProspettoGradualita pgEntity = ProdisMappers.PROSPETTO_GRADUALITA.toEntity(prospettoGradualita);
			pgEntity.setId(idProspetto);
			pgEntity.setdAggiorn(dataOdierna);
			pgEntity.setdInserim(dataOdierna);
			pgEntity.setCodUserInserim(userMinistero);
			pgEntity.setCodUserAggiorn(userMinistero);
			proDProspettoGradualitaDao.insert(pgEntity);
		}
		
		// inserisco riepilogo nazionale
		if (riepilogoNazionale!=null) {
			ProDRiepilogoNazionale rnEntity = ProdisMappers.RIEPILOGO_NAZIONALE.toEntity(riepilogoNazionale);
			rnEntity.setId(idProspetto);
			rnEntity.setDAggiorn(dataOdierna);
			rnEntity.setDInserim(dataOdierna);
			rnEntity.setCodUserInserim(userMinistero);
			rnEntity.setCodUserAggiorn(userMinistero);
			proDRiepilogoNazionaleDao.insert(rnEntity);
		}

		if (prospettoProvinciaList!=null) {
			
			// inizio a lavorare sulle province
			for (ProspettoProvincia pp : prospettoProvinciaList) {
				
				if (pp!=null) {
					
					// stacco dati provinciali
					DatiProvinciali datiProvinciali = null;
					if (pp.getDatiProvinciali()!=null) {
						datiProvinciali = pp.getDatiProvinciali();
						pp.setDatiProvinciali(null);
					}
					
					// stacco riepilogo provinciale
					RiepilogoProvinciale riepilogoProvinciale = null;
					if (pp.getRiepilogoProvinciale()!=null) {
						riepilogoProvinciale = pp.getRiepilogoProvinciale();
						pp.setRiepilogoProvinciale(null);
					}
					
					// inserisco prospetto provincia in modo da ottenere l'id provinciale
					ProRProspettoProvincia ppEntity = ProdisMappers.PROSPETTO_PROVINCIA.toEntity(pp);
					ppEntity.setIdProspetto(idProspetto);
					ppEntity.setFlgConfermatoQ2("S");
					ppEntity.setDAggiorn(dataOdierna);
					ppEntity.setDInserim(dataOdierna);
					ppEntity.setCodUserInserim(userMinistero);
					ppEntity.setCodUserAggiorn(userMinistero);
					ProRProspettoProvincia ppInserito = proRProspettoProvinciaDao.insert(ppEntity);
					Long idProspettoProvincia = ppInserito.getIdProspettoProv();
					
					// inserisco riepilogo provinciale
					if (riepilogoProvinciale!=null) {
						ProDRiepilogoProvinciale rpEntity = ProdisMappers.RIEPILOGO_PROVINCIALE.toEntity(riepilogoProvinciale);
						rpEntity.setId(idProspettoProvincia);
						rpEntity.setDAggiorn(dataOdierna);
						rpEntity.setDInserim(dataOdierna);
						rpEntity.setCodUserInserim(userMinistero);
						rpEntity.setCodUserAggiorn(userMinistero);
						proDRiepilogoProvincialeDao.insert(rpEntity);
					}
					
					// gestisco i dati provinciali
					if (datiProvinciali!=null) {
						
						// stacco gradualita
						ProvGradualita provGradualita = null;
						if (datiProvinciali.getProvGradualita()!=null) {
							provGradualita = datiProvinciali.getProvGradualita();
							datiProvinciali.setProvGradualita(null);
						}
						
						// stacco sospensione
						ProvSospensione provSospensione = null;
						if (datiProvinciali.getProvSospensione()!=null) {
							provSospensione = datiProvinciali.getProvSospensione();
							datiProvinciali.setProvSospensione(null);
						}
						
						// stacco convenzione
						ProvConvenzione provConvenzione = null;
						if (datiProvinciali.getProvConvenzione()!=null) {
							provConvenzione = datiProvinciali.getProvConvenzione();
							datiProvinciali.setProvConvenzione(null);
						}
						
						// stacco esonero
						ProvEsonero provEsonero = null;
						if (datiProvinciali.getProvEsonero()!=null) {
							provEsonero = datiProvinciali.getProvEsonero();
							datiProvinciali.setProvEsonero(null);
						}
						
						// stacco esonero autocert
						ProvEsoneroAutocert provEsoneroAutocert = null;
						if (datiProvinciali.getProvEsoneroAutocert()!=null) {
							provEsoneroAutocert = datiProvinciali.getProvEsoneroAutocert();
							datiProvinciali.setProvEsoneroAutocert(null);
						}
						
						// stacco lavoratori in forza
						List<LavoratoriInForza> lavoratoriInForzaList = null;
						if (datiProvinciali.getLavoratoriInForzas()!=null && !datiProvinciali.getLavoratoriInForzas().isEmpty()) {
							lavoratoriInForzaList = datiProvinciali.getLavoratoriInForzas();
							datiProvinciali.setLavoratoriInForzas(null);
						}
						
						// stacco part time
						List<PartTime> partTimeList = null;
						if (datiProvinciali.getPartTimes()!=null && !datiProvinciali.getPartTimes().isEmpty()) {
							partTimeList = datiProvinciali.getPartTimes();
							datiProvinciali.setPartTimes(null);
						}
						
						// stacco intermittenti
						List<ProvIntermittenti> provIntermittentiList = null;
						if (datiProvinciali.getProvIntermittentis()!=null && !datiProvinciali.getProvIntermittentis().isEmpty()) {
							provIntermittentiList = datiProvinciali.getProvIntermittentis();
							datiProvinciali.setProvIntermittentis(null);
						}
						
						// stacco categorie escluse
						List<CategorieEscluse> categorieEscluseList = null;
						if (datiProvinciali.getCategorieEscluses()!=null && !datiProvinciali.getCategorieEscluses().isEmpty()) {
							categorieEscluseList = datiProvinciali.getCategorieEscluses();
							datiProvinciali.setCategorieEscluses(null);
						}
						
						// stacco prospetto prov sede
						ProspettoProvSede prospettoProvSede = null;
						if (datiProvinciali.getProspettoProvSede()!=null) {
							prospettoProvSede = datiProvinciali.getProspettoProvSede();
							datiProvinciali.setProspettoProvSede(null);
						}
						
						// stacco posti di lavoro disp
						List<PostiLavoroDisp> postiLavoroDispList = null;
						if (datiProvinciali.getPostiLavoroDisps()!=null && !datiProvinciali.getPostiLavoroDisps().isEmpty()) {
							postiLavoroDispList = datiProvinciali.getPostiLavoroDisps();
							datiProvinciali.setPostiLavoroDisps(null);
						}
						
						// stacco compensazioni
						List<ProvCompensazioni> provCompensazioniList = null;
						if (datiProvinciali.getProvCompensazionis()!=null && !datiProvinciali.getProvCompensazionis().isEmpty()) {
							provCompensazioniList = datiProvinciali.getProvCompensazionis();
							datiProvinciali.setProvCompensazionis(null);
						}
						
						// inserisco i dati provinciali
						ProDDatiProvinciali dpEntity = ProdisMappers.DATI_PROVINCIALI.toEntity(datiProvinciali);
						dpEntity.setId(idProspettoProvincia);
						dpEntity.setdAggiorn(dataOdierna);
						dpEntity.setdInserim(dataOdierna);
						dpEntity.setCodUserInserim(userMinistero);
						dpEntity.setCodUserAggiorn(userMinistero);
						proDDatiProvincialiDao.insert(dpEntity);
						
						// inserisco gradualita
						if (provGradualita!=null) {
							ProDProvGradualita pgEntity = ProdisMappers.PROV_GRADUALITA.toEntity(provGradualita);
							pgEntity.setId(idProspettoProvincia);
							pgEntity.setdInserim(dataOdierna);
							pgEntity.setdAggiorn(dataOdierna);
							pgEntity.setCodUserInserim(userMinistero);
							pgEntity.setCodUserAggiorn(userMinistero);
							proDProvGradualitaDao.insert(pgEntity);
						}
						
						// inserisco sospensione
						if (provSospensione!=null) {
							ProDProvSospensione psEntity = ProdisMappers.PROV_SOSPENSIONE.toEntity(provSospensione);
							psEntity.setId(idProspettoProvincia);
							psEntity.setdAggiorn(dataOdierna);
							psEntity.setdInserim(dataOdierna);
							psEntity.setCodUserInserim(userMinistero);
							psEntity.setCodUserAggiorn(userMinistero);
							
							if (datiProvinciali.getProvSospensione()!=null && datiProvinciali.getProvSospensione().getStatoConcessione()!=null) {
								Optional<ProTStatoConcessione> statoFinded = proTStatoConcessioneDao.findOne(
										datiProvinciali.getProvSospensione().getStatoConcessione().getId());
								if (statoFinded.isPresent()) {
									psEntity.setProTStatoConcessione(statoFinded.get());
								}
							}
							
							if (datiProvinciali.getProvSospensione()!=null && datiProvinciali.getProvSospensione().getCausaSospensione()!=null) {
								Optional<ProTCausaSospensione> causaFinded = proTCausaSospensioneDao.findOne(
										datiProvinciali.getProvSospensione().getCausaSospensione().getId());
								if (causaFinded.isPresent()) {
									psEntity.setProTCausaSospensione(causaFinded.get());
								}
							}
							
							proDProvSospensioneDao.insert(psEntity);
						}
						
						// inserisco convenzione
						if (provConvenzione!=null) {
							ProDProvConvenzione pcEntity = ProdisMappers.PROV_CONVENZIONE.toEntity(provConvenzione);
							pcEntity.setId(idProspettoProvincia);
							pcEntity.setDAggiorn(dataOdierna);
							pcEntity.setDInserim(dataOdierna);
							pcEntity.setCodUserInserim(userMinistero);
							pcEntity.setCodUserAggiorn(userMinistero);
							proDProvConvenzioneDao.insert(pcEntity);
						}
						
						// inserisco esonero
						if (provEsonero!=null) {
							ProDProvEsonero peEntity = ProdisMappers.PROV_ESONERO.toEntity(provEsonero);
							peEntity.setId(idProspettoProvincia);
							peEntity.setdAggiorn(dataOdierna);
							peEntity.setdInserim(dataOdierna);
							peEntity.setCodUserInserim(userMinistero);
							peEntity.setCodUserAggiorn(userMinistero);
							
							if (datiProvinciali.getProvEsonero()!=null && datiProvinciali.getProvEsonero().getStatoConcessione()!=null) {
								Optional<ProTStatoConcessione> statoFinded = proTStatoConcessioneDao.findOne(
										datiProvinciali.getProvEsonero().getStatoConcessione().getId());
								if (statoFinded.isPresent()) {
									peEntity.setProTStatoConcessione(statoFinded.get());
								}
							}
							
							proDProvEsoneroDao.insert(peEntity);
						}
						
						// inserisco esonero autocert
						if (provEsoneroAutocert!=null) {
							ProDProvEsoneroAutocert peaEntity = ProdisMappers.PROV_ESONERO_AUTOCERT.toEntity(provEsoneroAutocert);
							peaEntity.setId(idProspettoProvincia);
							peaEntity.setdAggiorn(dataOdierna);
							peaEntity.setdInserim(dataOdierna);
							peaEntity.setCodUserInserim(userMinistero);
							peaEntity.setCodUserAggiorn(userMinistero);
							proDProvEsoneroAutocertDao.insert(peaEntity);
						}
						
						// inserisco prospetto prov sede
						if (prospettoProvSede!=null) {
							ProDProspettoProvSede ppsEntity = ProdisMappers.PROSPETTO_PROV_SEDE.toEntity(prospettoProvSede);
							ppsEntity.setId(idProspettoProvincia);
							ppsEntity.setDAggiorn(dataOdierna);
							ppsEntity.setDInserim(dataOdierna);
							ppsEntity.setCodUserInserim(userMinistero);
							ppsEntity.setCodUserAggiorn(userMinistero);
							proDProspettoProvSedeDao.insert(ppsEntity);
						}
						
						// inserisco lavoratori
						if (lavoratoriInForzaList!=null) {
							for (LavoratoriInForza l : lavoratoriInForzaList) {
								ProDLavoratoriInForza lavEntity = ProdisMappers.LAVORATORI_IN_FORZA.toEntity(l);
								lavEntity.setIdProspettoProv(idProspettoProvincia);
								lavEntity.setdAggiorn(dataOdierna);
								lavEntity.setdInserim(dataOdierna);
								lavEntity.setCodUserInserim(userMinistero);
								lavEntity.setCodUserAggiorn(userMinistero);
								lavEntity.setFlgCompletato("S");
								
								if (l.getAssunzioneProtetta()!=null) {
									Optional<ProTAssunzioneProtetta> assunzioneFinded = proTAssunzioneProtettaDao.findOne(
											l.getAssunzioneProtetta().getId());
									if (assunzioneFinded.isPresent()) {
										lavEntity.setProTAssunzioneProtetta(assunzioneFinded.get());
									}
								}
								
								if (l.getCategoriaSoggetto().equals("C")) {
									l.setPercentualeDisabilita(null);
								}
								
								proDLavoratoriInForzaDao.insert(lavEntity);
								
							}
						}
						
						// inserisco part time
						if (partTimeList!=null) {
							for (PartTime p : partTimeList) {
								ProDPartTime pEntity = ProdisMappers.PART_TIME.toEntity(p);
								pEntity.setIdProspettoProv(idProspettoProvincia);
								pEntity.setdAggiorn(dataOdierna);
								pEntity.setdInserim(dataOdierna);
								pEntity.setCodUserInserim(userMinistero);
								pEntity.setCodUserAggiorn(userMinistero);
								proDPartTimeDao.insert(pEntity);
							}
						}
						
						// inserisco categorie escluse
						if (categorieEscluseList!=null) {
							for (CategorieEscluse c : categorieEscluseList) {
								ProDCategorieEscluse cEntity = ProdisMappers.CATEGORIE_ESCLUSE.toEntity(c);
								cEntity.setIdProspettoProv(idProspettoProvincia);
								cEntity.setdAggiorn(dataOdierna);
								cEntity.setdInserim(dataOdierna);
								cEntity.setCodUserInserim(userMinistero);
								cEntity.setCodUserAggiorn(userMinistero);
								proDCategorieEscluseDao.insert(cEntity);
							}
						}

						// inserisco intermittenti
						if (provIntermittentiList!=null) {
							for (ProvIntermittenti inter : provIntermittentiList) {
								ProDProvIntermittenti intEntity = ProdisMappers.PROV_INTERMITTENTI.toEntity(inter);
								intEntity.setIdProspettoProv(idProspettoProvincia);
								intEntity.setdAggiorn(dataOdierna);
								intEntity.setdInserim(dataOdierna);
								intEntity.setCodUserInserim(userMinistero);
								intEntity.setCodUserAggiorn(userMinistero);
								proDProvIntermittentiDao.insert(intEntity);
							}
						}

						// inserisco posti di lavoro disp
						if (postiLavoroDispList!=null) {
							for (PostiLavoroDisp pld : postiLavoroDispList) {
								ProDPostiLavoroDisp pldEntity = ProdisMappers.POSTI_LAVORO_DISP.toEntity(pld);
								pldEntity.setIdProspettoProv(idProspettoProvincia);
								pldEntity.setdAggiorn(dataOdierna);
								pldEntity.setdInserim(dataOdierna);
								pldEntity.setCodUserInserim(userMinistero);
								pldEntity.setCodUserAggiorn(userMinistero);
								proDPostiLavoroDispDao.insert(pldEntity);
							}
						}
						
						// inserisco compensazioni
						if (provCompensazioniList!=null) {
							for (ProvCompensazioni comp : provCompensazioniList) {
								ProDProvCompensazioni compEntity = ProdisMappers.PROV_COMPENSAZIONI.toEntity(comp);
								compEntity.setIdProspettoProv(idProspettoProvincia);
								compEntity.setDAggiorn(dataOdierna);
								compEntity.setDInserim(dataOdierna);
								compEntity.setCodUserInserim(userMinistero);
								compEntity.setCodUserAggiorn(userMinistero);
								proDProvCompensazioniDao.insert(compEntity);
							}
						}
							
					}
					
				}
				
			}
			
		}
		
		return idProspetto;
				
	}
	
	// restituisce la entity di transcodifica dato il codice ministeriale e la data di riferimento
	public <T extends BaseDto<Long>> BaseDto<Long> getTfromMin(Class<T> transcodifica, String fieldValue, Date dataRiferimento){
		
		if(transcodifica == AssunzioneProtetta.class) {
			ProTAssunzioneProtetta entity = (ProTAssunzioneProtetta)proDProspettoDao.getTfromMin(ProTAssunzioneProtetta.class, fieldValue, dataRiferimento);
			return entity == null? null: ProdisMappers.ASSUNZIONE_PROTETTA.toModel(entity);
			
		} else if (transcodifica == Atecofin.class) {
			ProTAtecofin entity = (ProTAtecofin)proDProspettoDao.getTfromMin(ProTAtecofin.class, fieldValue, dataRiferimento);
			return entity == null? null: ProdisMappers.ATECOFIN.toModel(entity);
			
		} else if (transcodifica == CategoriaAzienda.class) {
			ProTCategoriaAzienda entity = (ProTCategoriaAzienda)proDProspettoDao.getTfromMin(ProTCategoriaAzienda.class, fieldValue, dataRiferimento);
			return entity == null? null: ProdisMappers.CATEGORIA_AZIENDA.toModel(entity);
			
		} else if (transcodifica == CategoriaEscluse.class) {
			ProTCategoriaEscluse entity = (ProTCategoriaEscluse)proDProspettoDao.getTfromMin(ProTCategoriaEscluse.class, fieldValue, dataRiferimento);
			return entity == null? null: ProdisMappers.CATEGORIA_ESCLUSE.toModel(entity);
			
		} else if (transcodifica == CausaSospensione.class) {
			ProTCausaSospensione entity = (ProTCausaSospensione)proDProspettoDao.getTfromMin(ProTCausaSospensione.class, fieldValue, dataRiferimento);
			return entity == null? null: ProdisMappers.CAUSA_SOSPENSIONE.toModel(entity);
			
		} else if (transcodifica == Ccnl.class) {
			ProTCcnl entity = (ProTCcnl)proDProspettoDao.getTfromMin(ProTCcnl.class, fieldValue, dataRiferimento);
			return entity == null? null: ProdisMappers.CCNL.toModel(entity);
			
		} else if (transcodifica == Comune.class) {
			ProTComune entity = (ProTComune)proDProspettoDao.getTfromMin(ProTComune.class, fieldValue, dataRiferimento);
			return entity == null? null: ProdisMappers.COMUNE.toModel(entity);
			
		} else if (transcodifica == Comunicazione.class){
			ProTComunicazione entity = (ProTComunicazione)proDProspettoDao.getTfromMin(ProTComunicazione.class, fieldValue, dataRiferimento);
			return entity == null? null: ProdisMappers.COMUNICAZIONE.toModel(entity);
			
		} else if (transcodifica == Contratti.class) {
			ProTContratti entity = (ProTContratti)proDProspettoDao.getTfromMin(ProTContratti.class, fieldValue, dataRiferimento);
			return entity == null? null: ProdisMappers.CONTRATTI.toModel(entity);
			
		} else if (transcodifica == Cpi.class) {
			ProTCpi entity = (ProTCpi)proDProspettoDao.getTfromMin(ProTCpi.class, fieldValue, dataRiferimento);
			return entity == null? null: ProdisMappers.CPI.toModel(entity);
			
		} else if (transcodifica == Dichiarante.class) {
			ProTDichiarante entity = (ProTDichiarante)proDProspettoDao.getTfromMin(ProTDichiarante.class, fieldValue, dataRiferimento);
			return entity == null? null: ProdisMappers.DICHIARANTE.toModel(entity);
			
		}else if (transcodifica == Istat2001livello5.class) {
			ProTIstat2001livello5 entity = (ProTIstat2001livello5)proDProspettoDao.getTfromMin(ProTIstat2001livello5.class, fieldValue, dataRiferimento);
			return entity == null? null: ProdisMappers.ISTAT2001LIVELLO5.toModel(entity);
			
		}else if (transcodifica == Provincia.class) {
			ProTProvincia entity = (ProTProvincia)proDProspettoDao.getTfromMin(ProTProvincia.class, fieldValue, dataRiferimento);
			return entity == null? null: ProdisMappers.PROVINCIA.toModel(entity);
			
		}else if (transcodifica == Regione.class) {
			ProTRegione entity = (ProTRegione)proDProspettoDao.getTfromMin(ProTRegione.class, fieldValue, dataRiferimento);
			return entity == null? null: ProdisMappers.REGIONE.toModel(entity);
			
		}else if (transcodifica == Soggetti.class) {
			ProTSoggetti entity = (ProTSoggetti)proDProspettoDao.getTfromMin(ProTSoggetti.class, fieldValue, dataRiferimento);
			return entity == null? null: ProdisMappers.SOGGETTI.toModel(entity);
			
		}else if (transcodifica == StatiEsteri.class) {
			ProTStatiEsteri entity = (ProTStatiEsteri)proDProspettoDao.getTfromMin(ProTStatiEsteri.class, fieldValue, dataRiferimento);
			return entity == null? null: ProdisMappers.STATI_ESTERI.toModel(entity);
			
		}else if (transcodifica == StatoConcessione.class)  {
			ProTStatoConcessione entity = (ProTStatoConcessione)proDProspettoDao.getTfromMin(ProTStatoConcessione.class, fieldValue, dataRiferimento);
			return entity == null? null: ProdisMappers.STATO_CONCESSIONE.toModel(entity);
			
		}else if (transcodifica == TipoRipropPt.class) {
			ProTTipoRipropPt entity = (ProTTipoRipropPt)proDProspettoDao.getTfromMin(ProTTipoRipropPt.class, fieldValue, dataRiferimento);
			return entity == null? null: ProdisMappers.TIPO_RIPROP_PT.toModel(entity);
			
		} 
		
		return null;
	}
	
	public Long updateStatoProspettoPrecedente(Prospetto prospetto, String tipoComunicazione) {
		
		if(prospetto.getCodiceComunicazionePreced() == null || prospetto.getCodiceComunicazionePreced().trim().equals("")) {
			return null;
		}
		
		Long idProspettoPrec = proDProspettoDao.getIdProspettoByCodiceComunicazione(prospetto.getCodiceComunicazionePreced());
		
		if(idProspettoPrec == null || idProspettoPrec == 0) {
			return null;
		}
		
		Optional<ProDProspetto> prospettoPrec = proDProspettoDao.findById(idProspettoPrec);
		
		Integer tipoComunicazioneIntero = Integer.parseInt(tipoComunicazione);
		
		// prospetto informativo
		if (tipoComunicazioneIntero == 1) {
			
			// DO NOTHING
			
		// rettifica
		} else if (tipoComunicazioneIntero == 2) {
			
			Optional<ProTStatoProspetto> stato = proTStatoProspettoDao.findOne(10L);
			
			if (stato!=null && prospettoPrec.isPresent()) {
				prospettoPrec.get().setProTStatoProspetto(stato.get());
				proDProspettoDao.update(prospettoPrec.get());
			}
			
		// annullamento
		} else if (tipoComunicazioneIntero == 3) {
			
			Optional<ProTStatoProspetto> stato = proTStatoProspettoDao.findOne(11L);
			
			if (stato!=null && prospettoPrec.isPresent()) {
				prospettoPrec.get().setProTStatoProspetto(stato.get());
				proDProspettoDao.update(prospettoPrec.get());
			}
			
		} else {
			return null;
		}
		
		return idProspettoPrec;
		
	}
	
	public EsitoStoreProcedure storeProcedureEseguiCalcoli(Long idProspetto, String cfOperatore,
			Boolean soloScoperture) {
		EsitoStoreProcedure loEsito = proDProspettoDao.storeProcedureEseguiCalcoli(idProspetto, cfOperatore,
				soloScoperture);
		return loEsito;

	}

}
