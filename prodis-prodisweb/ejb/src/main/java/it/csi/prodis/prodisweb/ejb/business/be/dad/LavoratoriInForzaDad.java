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
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDDatiAziendaDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDDatiProvincialiDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDLavoratoriInForzaDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDParametriDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDProspettoDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDProspettoGradualitaDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProRProspettoProvinciaDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProTAssunzioneProtettaDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProTComuneDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProTContrattiDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProTIstat2001livello5Dao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProTStatiEsteriDao;
import it.csi.prodis.prodisweb.ejb.entity.EsitoLavoratore;
import it.csi.prodis.prodisweb.ejb.entity.ProDAssPubbliche;
import it.csi.prodis.prodisweb.ejb.entity.ProDDatiProvinciali;
import it.csi.prodis.prodisweb.ejb.entity.ProDLavoratoriInForza;
import it.csi.prodis.prodisweb.ejb.entity.ProDParametri;
import it.csi.prodis.prodisweb.ejb.entity.ProDProspetto;
import it.csi.prodis.prodisweb.ejb.entity.ProRProspettoProvincia;
import it.csi.prodis.prodisweb.ejb.entity.ProTAssunzioneProtetta;
import it.csi.prodis.prodisweb.ejb.entity.ProTComune;
import it.csi.prodis.prodisweb.ejb.entity.ProTContratti;
import it.csi.prodis.prodisweb.ejb.entity.ProTIstat2001livello5;
import it.csi.prodis.prodisweb.ejb.entity.ProTStatiEsteri;
import it.csi.prodis.prodisweb.ejb.exception.NotFoundException;
import it.csi.prodis.prodisweb.ejb.mapper.ProdisMappers;
import it.csi.prodis.prodisweb.ejb.util.ProdisSrvUtil;
import it.csi.prodis.prodisweb.lib.dto.common.DecodificaGenerica;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.AssunzioneProtetta;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Contratti;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Istat2001livello5;
import it.csi.prodis.prodisweb.lib.dto.prospetto.AssPubbliche;
import it.csi.prodis.prodisweb.lib.dto.prospetto.DatiAzienda;
import it.csi.prodis.prodisweb.lib.dto.prospetto.LavoratoriInForza;
import it.csi.prodis.prodisweb.lib.dto.prospetto.Prospetto;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProspettoGradualita;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProspettoProvincia;

@ApplicationScoped
public class LavoratoriInForzaDad extends BaseDad {

	@Inject
	private ProDLavoratoriInForzaDao proDLavoratoriInForzaDao;

	@Inject
	private ProDDatiProvincialiDao proDDatiProvincialiDao;

	@Inject
	private ProDParametriDao proDParamentriDao;

	@Inject
	private ProTComuneDao proTComuneDao;

	@Inject
	private ProTStatiEsteriDao proTStatiEsteriDao;

	@Inject
	private ProRProspettoProvinciaDao proRProspettoProvinciaDao;

	@Inject
	private ProDProspettoDao proDProspettoDao;

	@Inject
	private ProDDatiAziendaDao proDDatiAziendaDao;

	@Inject
	private ProDAssPubblicheDao proDAssPubblicheDao;

	@Inject
	private ProDProspettoGradualitaDao proDProspettoGradualitaDao;

	@Inject
	private ProTAssunzioneProtettaDao proTAssunzioneProtettaDao;

	@Inject
	private ProTContrattiDao proTContrattiDao;

	@Inject
	private ProTIstat2001livello5Dao proTIstat2001livello5Dao;

	@Inject
	private ProDParametriDao proDParametriDao;

	public LavoratoriInForza insertSingleLavoratoriInForza(LavoratoriInForza lif, Integer idDatiProvinciali,
			String flagCompletato) {

		Date dataAttuale = new Date();

		// Questo flag va settato a N se ci sono problemi con i dati del lavoratore
		lif.setFlgCompletato(flagCompletato);

		if (lif.getStatiEsteri() != null && lif.getStatiEsteri().getId() == null) {
			lif.setStatiEsteri(null);
		}

		if (lif.getContratti() != null && lif.getContratti().getId() == null) {
			lif.setContratti(null);
		}

		if (lif.getComune() != null && lif.getComune().getId() == null) {
			lif.setComune(null);
		}

		if (lif.getAssunzioneProtetta() != null && lif.getAssunzioneProtetta().getCodAssunzioneProtetta() != null
				&& (lif.getAssunzioneProtetta().getCodAssunzioneProtetta().equals("M")
						|| lif.getAssunzioneProtetta().getCodAssunzioneProtetta().equals("N"))) {
			lif.setContratti(null);
		} else if (lif.getAssunzioneProtetta() != null && lif.getAssunzioneProtetta().getId() == null) {
			lif.setAssunzioneProtetta(null);
		}

		Optional<ProDDatiProvinciali> datiProvincialiFinded = proDDatiProvincialiDao
				.findOne(idDatiProvinciali.longValue());

		ProDLavoratoriInForza newLav = null;
		if (!datiProvincialiFinded.isPresent()) {
			throw new NotFoundException("DatiProvinciali");
		} else {

			ProDLavoratoriInForza lifEntity = ProdisMappers.LAVORATORI_IN_FORZA.toEntity(lif);

			lifEntity.setdAggiorn(dataAttuale);
			lifEntity.setdInserim(dataAttuale);

			newLav = proDLavoratoriInForzaDao.insert(lifEntity);
		}

		return ProdisMappers.LAVORATORI_IN_FORZA.toModel(newLav);

	}

	public LavoratoriInForza updateSingleLavoratoriInForza(LavoratoriInForza lavoratoriInForza,
			Integer idLavoratoriInForza, String flagCompletato) {

		Date dataAttuale = new Date();

		// Questo flag va settato a N se ci sono problemi con i dati del lavoratore
		lavoratoriInForza.setFlgCompletato(flagCompletato);

		if (lavoratoriInForza.getStatiEsteri() != null && lavoratoriInForza.getStatiEsteri().getId() == null) {
			lavoratoriInForza.setStatiEsteri(null);
		}

		if (lavoratoriInForza.getContratti() != null && lavoratoriInForza.getContratti().getId() == null) {
			lavoratoriInForza.setContratti(null);
		}

		if (lavoratoriInForza.getComune() != null && lavoratoriInForza.getComune().getId() == null) {
			lavoratoriInForza.setComune(null);
		}

		if (lavoratoriInForza.getAssunzioneProtetta() != null
				&& lavoratoriInForza.getAssunzioneProtetta().getCodAssunzioneProtetta() != null
				&& (lavoratoriInForza.getAssunzioneProtetta().getCodAssunzioneProtetta().equals("M")
						|| lavoratoriInForza.getAssunzioneProtetta().getCodAssunzioneProtetta().equals("N"))) {
			lavoratoriInForza.setContratti(null);
		} else if (lavoratoriInForza.getAssunzioneProtetta() != null
				&& lavoratoriInForza.getAssunzioneProtetta().getId() == null) {
			lavoratoriInForza.setAssunzioneProtetta(null);
		}

		Optional<ProDLavoratoriInForza> lavoratoriInForzaFinded = proDLavoratoriInForzaDao
				.findOne(idLavoratoriInForza.longValue());

		ProDLavoratoriInForza newLav = null;
		if (!lavoratoriInForzaFinded.isPresent()) {
			throw new NotFoundException("LavoratoriInForza");
		} else {

			ProDLavoratoriInForza newLavoratori = ProdisMappers.LAVORATORI_IN_FORZA.toEntity(lavoratoriInForza);

			newLavoratori.setdAggiorn(dataAttuale);
			newLavoratori.setdInserim(lavoratoriInForzaFinded.get().getdInserim());
			newLavoratori.setCodUserInserim(lavoratoriInForzaFinded.get().getCodUserInserim());

			newLav = proDLavoratoriInForzaDao.update(newLavoratori);

		}

		return ProdisMappers.LAVORATORI_IN_FORZA.toModel(newLav);
	}

	public LavoratoriInForza deleteSingleLavoratoriInForza(Long idlavoratoriInForza) {

		Optional<ProDLavoratoriInForza> lavoratoriInForzaFinded = proDLavoratoriInForzaDao.findOne(idlavoratoriInForza);

		if (lavoratoriInForzaFinded.isPresent()) {
			proDLavoratoriInForzaDao.delete(idlavoratoriInForza);
		} else {
			throw new NotFoundException("LavoratoriInForza");
		}

		return ProdisMappers.LAVORATORI_IN_FORZA.toModel(lavoratoriInForzaFinded.get());

	}

	// raffy controlli lavoratori
	public String getParametroByNome(String parametro) {
		DecodificaGenerica valore = proDParamentriDao.findByNome(parametro);
		if (ProdisSrvUtil.isVoid(valore)) {
			throw new NotFoundException("parametro");
		}
		return valore.getCodDecodifica();
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
		return proDLavoratoriInForzaDao.checkCongruenzaCodiceFiscale(cf, cognome, nome, sesso, dataNascita,
				codiceComuneStatoEsteroNascita);
	}

	public List<DecodificaGenerica> getComuneValido(String codComuneMin, String dsProTComune) {
		DecodificaGenerica filtro = new DecodificaGenerica();
		filtro.setCodDecodifica(codComuneMin);
		filtro.setDsDecodifica(dsProTComune);
		return proTComuneDao.findByFilter(filtro);
	}

	public List<DecodificaGenerica> getStatoEsteroValido(String codMin, String descrizione) {
		DecodificaGenerica filtro = new DecodificaGenerica();
		filtro.setCodDecodifica(codMin);
		filtro.setDsDecodifica(descrizione);
		return proTStatiEsteriDao.findByFilter(filtro);
	}

	public ProTComune getComuneById(Long id) {
		Optional<ProTComune> optional = proTComuneDao.findOne(id);
		if (!optional.isPresent()) {
			throw new NotFoundException("Comune");
		}
		return optional.get();
	}

	public List<ProTComune> getComuni(Long idProvincia, String codComuneMin, String dsProTComune, Date data) {
		return proTComuneDao.find(idProvincia, codComuneMin, dsProTComune, data);
	}

	public List<ProTStatiEsteri> getStati(String codNazioneMin, String dsStatiEsteri, Date data) {
		return proTStatiEsteriDao.find(codNazioneMin, dsStatiEsteri, data);
	}

	public ProspettoProvincia getProspettoProvinciaById(Long id) {
		Optional<ProspettoProvincia> optional = proRProspettoProvinciaDao.findOne(id.longValue())
				.map(ProdisMappers.PROSPETTO_PROVINCIA::toModel);
		if (!optional.isPresent()) {
			throw new NotFoundException("ProspettoProvincia");
		}
		return optional.get();
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

	public AssunzioneProtetta getAssunzioneProtettaById(Long id) {
		Optional<AssunzioneProtetta> optional = proTAssunzioneProtettaDao.findOne(id.longValue())
				.map(ProdisMappers.ASSUNZIONE_PROTETTA::toModel);
		if (!optional.isPresent()) {
			throw new NotFoundException("AssunzioneProtetta");
		}
		return optional.get();
	}

	public Contratti getTipologiaContrattualeById(Long id) {
		Optional<Contratti> optional = proTContrattiDao.findOne(id.longValue()).map(ProdisMappers.CONTRATTI::toModel);
		if (!optional.isPresent()) {
			throw new NotFoundException("AssunzioneProtetta");
		}
		return optional.get();
	}

	public List<DecodificaGenerica> getIstat(String codiceMinisteriale, String descrizioneMin) {
		DecodificaGenerica filtro = new DecodificaGenerica();
		filtro.setCodDecodifica(codiceMinisteriale);
		filtro.setDsDecodifica(descrizioneMin);
		filtro.setFlgAncheNonValidi(true);
		List<DecodificaGenerica> lista = proTIstat2001livello5Dao.findByFilter(filtro);
		return lista;
	}

	public List<DecodificaGenerica> getIstatValidi(String codiceMinisteriale, String descrizioneMin) {
		DecodificaGenerica filtro = new DecodificaGenerica();
		filtro.setCodDecodifica(codiceMinisteriale);
		filtro.setDsDecodifica(descrizioneMin);
		filtro.setFlgAncheNonValidi(false);
		List<DecodificaGenerica> lista = proTIstat2001livello5Dao.findByFilter(filtro);
		return lista;
	}

	public Istat2001livello5 getIstatById(Long id) {
		Optional<Istat2001livello5> optional = proTIstat2001livello5Dao.findOne(id.longValue())
				.map(ProdisMappers.ISTAT2001LIVELLO5::toModel);
		if (!optional.isPresent()) {
			throw new NotFoundException("AssunzioneProtetta");
		}
		return optional.get();
	}

	public List<ProRProspettoProvincia> getProspettoProvinciaByIdProspetto(Long idProspetto) {
		return proRProspettoProvinciaDao.findByIdProspetto(idProspetto);
	}

	public List<ProDLavoratoriInForza> getListaLavoratoriByIdProspettoProv(Long idProspettoProv) {
		return proDLavoratoriInForzaDao.findByIdProspettoProv(new BigDecimal(idProspettoProv));
	}

	public List<EsitoLavoratore> uploadLavoratoriInForza(List<ProDLavoratoriInForza> lavoratoriCaricati,
			Long idProspettoProv) {

		Optional<ProDDatiProvinciali> datiProvincialiFinded = proDDatiProvincialiDao.findOne(idProspettoProv);

		if (!datiProvincialiFinded.isPresent()) {
			throw new NotFoundException("DatiProvinciali");
		}

		if (lavoratoriCaricati.isEmpty() || lavoratoriCaricati == null) {
			throw new NotFoundException("LavoratoriInForza");
		}

		List<EsitoLavoratore> esiti = new ArrayList<EsitoLavoratore>();

		for (ProDLavoratoriInForza lav : lavoratoriCaricati) {

			Optional<ProDLavoratoriInForza> oldLav = proDLavoratoriInForzaDao.findOne(lav.getId());

			if (!oldLav.isPresent()) {
				esiti.add(new EsitoLavoratore(lav.getCodiceFiscale(), "Ha subito modifiche"));
				proDLavoratoriInForzaDao.insert(lav);
			} else {
				if (oldLav.get().equals(lav)) {
					esiti.add(new EsitoLavoratore(lav.getCodiceFiscale(), "Non ha subito modifiche"));
				} else {
					esiti.add(new EsitoLavoratore(lav.getCodiceFiscale(), "Ha subito modifiche"));
				}
				proDLavoratoriInForzaDao.update(lav);
			}

		}

		return esiti;

	}

	public List<ProDLavoratoriInForza> downloadLavoratoriInForza(Long idProspettoProv) {

		Optional<ProDDatiProvinciali> datiProvincialiFinded = proDDatiProvincialiDao.findOne(idProspettoProv);

		if (!datiProvincialiFinded.isPresent()) {
			throw new NotFoundException("DatiProvinciali");
		}

		List<ProDLavoratoriInForza> lavoratoriFinded = proDLavoratoriInForzaDao
				.findByIdProspettoProv(new BigDecimal(idProspettoProv));

		if (lavoratoriFinded.isEmpty() || lavoratoriFinded == null) {
			throw new NotFoundException("LavoratoriInForza");
		}

		return lavoratoriFinded;

	}

	public String getSiglaProvincia(Long idProspettoProv) {

		Optional<ProRProspettoProvincia> ppFinded = proRProspettoProvinciaDao.findOne(idProspettoProv);

		if (!ppFinded.isPresent()) {
			throw new NotFoundException("ProspettoProvincia");
		}

		if (ppFinded.get().getProTProvincia() != null && ppFinded.get().getProTProvincia().getDsTarga() != null) {
			return ppFinded.get().getProTProvincia().getDsTarga();
		} else {
			return null;
		}

	}

	public ProDParametri getParametro(String parametroFirma) {

		Optional<ProDParametri> parametriFinded = proDParametriDao.getOneParametro(parametroFirma);

		if (parametriFinded.isPresent()) {
			return parametriFinded.get();
		} else {
			throw new NotFoundException("Parametri");
		}

	}

	public List<ProDParametri> getParametri(String parametroFirmaLike) {

		List<ProDParametri> listParametriFinded = proDParametriDao.findAll();

		if (listParametriFinded == null || listParametriFinded.isEmpty()) {
			throw new NotFoundException("Parametri");
		}

		List<ProDParametri> listParametriFilter = new ArrayList<ProDParametri>();
		for (ProDParametri p : listParametriFinded) {
			if (p.getDsNome().contains(parametroFirmaLike)) {
				listParametriFilter.add(p);
			}
		}

		if (listParametriFilter == null || listParametriFilter.isEmpty()) {
			throw new NotFoundException("Parametri");
		}

		return listParametriFilter;

	}

	public Prospetto getProspettoByProvincia(Long idProspettoProv) {

		Optional<ProRProspettoProvincia> ppFinded = proRProspettoProvinciaDao.findOne(idProspettoProv);

		if (!ppFinded.isPresent()) {
			throw new NotFoundException("Prospetto Provincia");
		} else {

			Optional<ProDProspetto> prospettoFinded = proDProspettoDao
					.findById(ppFinded.get().getIdProspetto().longValue());

			if (!prospettoFinded.isPresent()) {
				throw new NotFoundException("Prospetto");
			} else {
				return ProdisMappers.PROSPETTO.toModel(prospettoFinded.get());
			}

		}

	}

	public ProTComune cercaComune(String codiceComune) {

		Optional<ProTComune> comuneFinded = proTComuneDao.findByCodice(codiceComune);

		if (comuneFinded!=null && comuneFinded.isPresent()) {

			return comuneFinded.get();

		}

		return null;

	}

	public ProTStatiEsteri cercaStatiEsteri(String codiceStatoEstero) {

		Optional<ProTStatiEsteri> statiEsteriFinded = proTStatiEsteriDao.findByCodice(codiceStatoEstero);

		if (statiEsteriFinded!=null && statiEsteriFinded.isPresent()) {

			return statiEsteriFinded.get();

		}

		return null;

	}

	public ProTAssunzioneProtetta cercaAssunzioneProtetta(String codiceTipoAssunzioneProtetta) {

		Optional<ProTAssunzioneProtetta> assFinded = proTAssunzioneProtettaDao
				.findByCodice(codiceTipoAssunzioneProtetta);

		if (assFinded!=null && assFinded.isPresent()) {

			return assFinded.get();

		}

		return null;
	}

	public ProTContratti cercaContratti(String codiceTipologiaContrattuale) {

		Optional<ProTContratti> contrattiFinded = proTContrattiDao.findByCodice(codiceTipologiaContrattuale);

		if (contrattiFinded!=null && contrattiFinded.isPresent()) {

			return contrattiFinded.get();

		}

		return null;
	}

	public ProTIstat2001livello5 cercaIstat(String codiceQualificaIstat) {

		Optional<ProTIstat2001livello5> istatFinded = proTIstat2001livello5Dao.findByCodice(codiceQualificaIstat);

		if (istatFinded!=null && istatFinded.isPresent()) {

			return istatFinded.get();

		}

		return null;
	}

	public Long contaLavoratoriProspetto(Long idProspetto) {

		List<ProRProspettoProvincia> ppFinded = proRProspettoProvinciaDao.findByIdProspetto(idProspetto);

		if (ppFinded.isEmpty()) {
			return 0L;
		} else {

			Long sommaLav = 0L;

			for (ProRProspettoProvincia p : ppFinded) {
				sommaLav += proDLavoratoriInForzaDao.contaLavoratoriProspettoProv(p.getIdProspettoProv());
			}

			return sommaLav;

		}

	}

	public ProspettoProvincia cercaProvinciaInProspetto(String targaProvinciaQuestoLav, Long idProspetto) {

		List<ProRProspettoProvincia> ppFinded = proRProspettoProvinciaDao.findByIdProspetto(idProspetto);
		
		if (ppFinded==null || ppFinded.isEmpty()) {
			return null;
		}
		
		for (ProRProspettoProvincia pp : ppFinded) {
			if (pp.getProTProvincia()!=null && pp.getProTProvincia().getDsTarga().equals(targaProvinciaQuestoLav)) {
				return ProdisMappers.PROSPETTO_PROVINCIA.toModel(pp);
			}
		}
		
		return null;
		
	}

}
