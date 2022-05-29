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

import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProTAssunzioneProtettaDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProTAtecofinDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProTCategoriaEscluseDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProTCausaSospensioneDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProTCcnlDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProTComuneDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProTContrattiDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProTDichiaranteDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProTIstat2001livello5Dao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProTProvinciaDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProTRegioneDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProTSoggettiDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProTStatiEsteriDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProTStatoConcessioneDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProTStatoProspettoDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProTTipoRipropPtDao;
import it.csi.prodis.prodisweb.ejb.entity.ProTAtecofin;
import it.csi.prodis.prodisweb.ejb.entity.ProTCcnl;
import it.csi.prodis.prodisweb.ejb.entity.ProTComune;
import it.csi.prodis.prodisweb.ejb.entity.ProTDichiarante;
import it.csi.prodis.prodisweb.ejb.entity.ProTProvincia;
import it.csi.prodis.prodisweb.ejb.entity.ProTSoggetti;
import it.csi.prodis.prodisweb.ejb.entity.ProTStatoProspetto;
import it.csi.prodis.prodisweb.ejb.mapper.ProdisMappers;
import it.csi.prodis.prodisweb.lib.dto.common.DecodificaGenerica;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Atecofin;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Ccnl;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Comune;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Dichiarante;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Provincia;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Soggetti;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.StatoProspetto;
import it.csi.prodis.prodisweb.lib.util.ProdisClassUtils;

/**
 * Data Access Delegate for decodificas
 */
@ApplicationScoped
public class DecodificaDad extends BaseDad {

	@Inject
	private ProTComuneDao proTComuneDao;

	@Inject
	private ProTProvinciaDao proTProvinciaDao;
	// private ProdisTProvinciaDao provinciaDao;

	@Inject
	private ProTStatoProspettoDao proTStatoProspettoDao;

	@Inject
	private ProTRegioneDao proTRegioneDao;

	@Inject
	private ProTCcnlDao proTCCNLDao;

	@Inject
	private ProTAtecofinDao proTAtecofinDao;

	@Inject
	private ProTDichiaranteDao proTDichiaranteDao;

	@Inject
	private ProTStatiEsteriDao proTStatiEsteriDao;

	@Inject
	private ProTContrattiDao proTContrattiDao;

	@Inject
	private ProTAssunzioneProtettaDao proTAssunzioneProtettaDao;

	@Inject
	private ProTCategoriaEscluseDao proTCategoriaEscluseDao;

	@Inject
	private ProTCausaSospensioneDao proTCausaSospensioneDao;

	@Inject
	private ProTIstat2001livello5Dao proTIstat2001livello5Dao;

	@Inject
	private ProTTipoRipropPtDao proTTipoRipropPtDao;

	@Inject
	private ProTStatoConcessioneDao proTStatoConcessioneDao;

	@Inject
	private ProTSoggettiDao proTSoggettiDao;

//	@Autowired
//	private ProTProvinciaDAOImpl proTProvinciaDAOImpl;

	public DecodificaDad() {
		try {
			// creo oggetto se non viene inizializzato
//			if (proTProvinciaDAOImpl == null) {
//				Context ctx = new InitialContext();
//				DataSource dataSource = (DataSource) ctx.lookup("java:jboss/datasources/prodiswebDS");
//				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
//
//				proTProvinciaDAOImpl = new ProTProvinciaDAOImpl(dataSource, namedParameterJdbcTemplate);
//			}
		} catch (Exception e) {
			log.error(ProdisClassUtils.truncClassName(getClass()) + " Eccezione !!", e);
		}
	}

	/**
	 * Returns the Comune
	 * 
	 * @return the Comune
	 */
	public List<Comune> getComune(Long idProvincia, String codComuneMin, String dsProTComune, Date data) {
		List<ProTComune> list = proTComuneDao.find(idProvincia, codComuneMin, dsProTComune, data);
		return ProdisMappers.COMUNE.toModels(list);
	}

	/**
	 * Returns the Provincia
	 * 
	 * @return the Provincia
	 */
	public List<Provincia> getProvincia(Long idRegione) {
		List<ProTProvincia> list = proTProvinciaDao.findByRegione(idRegione);
		return ProdisMappers.PROVINCIA.toModels(list);

//		ProTProvinciaDTO proTProvinciaDTOFilter = new ProTProvinciaDTO();
//		proTProvinciaDTOFilter.setIdTRegione(idRegione);
//
//		List<ProTProvinciaDTO> list = proTProvinciaDAOImpl.findByFilter(proTProvinciaDTOFilter);
//		return ProdisMappers.PROVINCIA.toModels(list);
	}

	/**
	 * Returns the state prospect
	 * 
	 * @return the state prospect
	 */
	public List<StatoProspetto> getStatoProspetto() {
		List<ProTStatoProspetto> list = proTStatoProspettoDao.findAll();
		return ProdisMappers.STATO_PROSPETTO.toModels(list);
	}

//	public List<Regione> getRegione() {
//		List<ProTRegione> list = proTRegioneDao.findAll();
//		return ProdisMappers.REGIONE.toModels(list);
//	}

	public List<Ccnl> getCcnl(Long idCcnl) {
		List<ProTCcnl> list = proTCCNLDao.findByField(idCcnl);
		return ProdisMappers.CCNL.toModels(list);
	}

	public List<Atecofin> getAtecofin(Long idAteco) {
		List<ProTAtecofin> list = proTAtecofinDao.findByField(idAteco);
		return ProdisMappers.ATECOFIN.toModels(list);
	}

	public List<Dichiarante> getDichiarante(Long idDichiarante) {
		List<ProTDichiarante> list;

		if (idDichiarante == null) {
			list = proTDichiaranteDao.findAll();
		} else {
			String strId = idDichiarante.toString();
			list = proTDichiaranteDao.findByField(strId);
		}
		return ProdisMappers.DICHIARANTE.toModels(list);
	}

	public List<DecodificaGenerica> getCcnlDecodifica(DecodificaGenerica filtro) {
		return proTCCNLDao.findByFilter(filtro);
	}

	public List<DecodificaGenerica> getAtecofinDecodifica(DecodificaGenerica filtro) {
		return proTAtecofinDao.findByFilter(filtro);
	}

	public List<DecodificaGenerica> getStatiEsteriDecodifica(DecodificaGenerica filtro) {
		return proTStatiEsteriDao.findByFilter(filtro);
	}

	public List<DecodificaGenerica> getContrattiDecodifica(DecodificaGenerica filtro) {
		return proTContrattiDao.findByFilter(filtro);
	}

	public List<DecodificaGenerica> getAssunzioneProtettaDecodifica(DecodificaGenerica filtro) {
		return proTAssunzioneProtettaDao.findByFilter(filtro);
	}

	public List<DecodificaGenerica> getDichiaranteDecodifica(DecodificaGenerica filtro) {
		return proTDichiaranteDao.findByFilter(filtro);
	}

	public List<DecodificaGenerica> getCategoriaEscluseDecodifica(DecodificaGenerica filtro) {
		return proTCategoriaEscluseDao.findByFilter(filtro);
	}

	public List<DecodificaGenerica> getCausaSospensioneDecodifica(DecodificaGenerica filtro) {
		return proTCausaSospensioneDao.findByFilter(filtro);
	}

	public List<DecodificaGenerica> getQualificaDecodifica(DecodificaGenerica filtro) {
		return proTIstat2001livello5Dao.findByFilter(filtro);
	}

	public List<DecodificaGenerica> getTipologiaLavoratoreDecodifica(DecodificaGenerica filtro) {
		return proTTipoRipropPtDao.findByFilter(filtro);
	}

	public List<DecodificaGenerica> getComuneDecodifica(DecodificaGenerica filtro) {
		return proTComuneDao.findByFilter(filtro);
	}

	public List<DecodificaGenerica> getRegioneDecodifica(DecodificaGenerica filtro) {
		return proTRegioneDao.findByFilter(filtro);
	}

	public List<DecodificaGenerica> getSoggettiDecodifica(DecodificaGenerica filtro) {
		return proTSoggettiDao.findByFilter(filtro);
	}

	public List<DecodificaGenerica> getStatoConcessioneDecodifica(DecodificaGenerica filtro) {
		return proTStatoConcessioneDao.findByFilter(filtro);
	}
	
	public Soggetti getSoggettoByCodice(String codSoggetti) {
		ProTSoggetti entity =  proTSoggettiDao.findBycodSoggetti(codSoggetti);
		return ProdisMappers.SOGGETTI.toModel(entity);
	}
	
}
