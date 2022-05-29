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
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDDatiProvincialiDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDPostiLavoroDispDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProTComuneDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProTIstat2001livello5Dao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProTStatiEsteriDao;
import it.csi.prodis.prodisweb.ejb.entity.ProDDatiProvinciali;
import it.csi.prodis.prodisweb.ejb.entity.ProDPostiLavoroDisp;
import it.csi.prodis.prodisweb.ejb.entity.ProTStatiEsteri;
import it.csi.prodis.prodisweb.ejb.exception.NotFoundException;
import it.csi.prodis.prodisweb.ejb.mapper.ProdisMappers;
import it.csi.prodis.prodisweb.lib.dto.common.DecodificaGenerica;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Comune;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Istat2001livello5;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.StatiEsteri;
import it.csi.prodis.prodisweb.lib.dto.prospetto.PostiLavoroDisp;

@ApplicationScoped
public class PostiLavoroDispDad {

	@Inject
	private ProDPostiLavoroDispDao proDPostiLavoroDispDao;

	@Inject
	private ProDDatiProvincialiDao proDDatiProvincialiDao;

	@Inject
	private ProTIstat2001livello5Dao proTIstat2001livello5Dao;

	@Inject
	private ProTComuneDao proTComuneDao;

	@Inject
	private ProTStatiEsteriDao proTStatiEsteriDao;

	public PostiLavoroDisp updatePostiLavoroDisp(PostiLavoroDisp postiLavoroDisp, Long id) {
		
		Date dataAttuale = new Date();

		Optional<ProDPostiLavoroDisp> postiLavoroDispFinded = proDPostiLavoroDispDao.findOne(id);

		Optional<ProDDatiProvinciali> datiProvincialiFinded = proDDatiProvincialiDao
				.findOne(postiLavoroDisp.getIdProspettoProv());

		if (!datiProvincialiFinded.isPresent()) {
			throw new NotFoundException("DatiProvinciali");
		}

		ProDPostiLavoroDisp newPostiLavoroDisp = null;

		if (!postiLavoroDispFinded.isPresent()) {
			throw new NotFoundException("PostiLavoroDisp");
		} else {
			newPostiLavoroDisp = ProdisMappers.POSTI_LAVORO_DISP.toEntity(postiLavoroDisp);
			newPostiLavoroDisp.setIdProspettoProv(new BigDecimal(postiLavoroDisp.getIdProspettoProv()));
			newPostiLavoroDisp.setId(id);
			if (newPostiLavoroDisp.getProTComune() != null
					&& newPostiLavoroDisp.getProTComune().getIdTComune() == null) {
				if (newPostiLavoroDisp.getProTComune().getCodComuneMin() == null
						&& newPostiLavoroDisp.getProTComune().getDsProTComune() == null) {
					newPostiLavoroDisp.setProTComune(null);
				}
			}
			
			newPostiLavoroDisp.setdAggiorn(dataAttuale);
			newPostiLavoroDisp.setdInserim(postiLavoroDispFinded.get().getdInserim());
			newPostiLavoroDisp.setCodUserInserim(postiLavoroDispFinded.get().getCodUserInserim());
			
			newPostiLavoroDisp = proDPostiLavoroDispDao.update(newPostiLavoroDisp);
			
		}

		return ProdisMappers.POSTI_LAVORO_DISP.toModel(newPostiLavoroDisp);

	}

	public PostiLavoroDisp insertPostiLavoroDisp(PostiLavoroDisp postiLavoroDisp, Long id) {
		
		Date dataAttuale = new Date();

		Optional<ProDDatiProvinciali> datiProvincialiFinded = proDDatiProvincialiDao.findOne(id);

		ProDPostiLavoroDisp newPostiLavoroDisp = null;

		if (!datiProvincialiFinded.isPresent()) {
			throw new NotFoundException("DatiProvinciali");
		} else {
			newPostiLavoroDisp = ProdisMappers.POSTI_LAVORO_DISP.toEntity(postiLavoroDisp);
			newPostiLavoroDisp.setIdProspettoProv(new BigDecimal(id));
			if (newPostiLavoroDisp.getProTComune() != null
					&& newPostiLavoroDisp.getProTComune().getIdTComune() == null) {
				if (newPostiLavoroDisp.getProTComune().getCodComuneMin() == null
						&& newPostiLavoroDisp.getProTComune().getDsProTComune() == null) {
					newPostiLavoroDisp.setProTComune(null);
				}
			}
			
			newPostiLavoroDisp.setdAggiorn(dataAttuale);
			newPostiLavoroDisp.setdInserim(dataAttuale);
			
			newPostiLavoroDisp = proDPostiLavoroDispDao.insert(newPostiLavoroDisp);
		}

		return ProdisMappers.POSTI_LAVORO_DISP.toModel(newPostiLavoroDisp);
	}

	public ProDPostiLavoroDisp deletePostiLavoroDisp(Long idPostiLavoroDisp) {

		Optional<ProDPostiLavoroDisp> postiLavoroDispFinded = proDPostiLavoroDispDao.findOne(idPostiLavoroDisp);

		if (postiLavoroDispFinded.isPresent()) {
			proDPostiLavoroDispDao.delete(idPostiLavoroDisp);
		} else {
			throw new NotFoundException("PostiLavoroDisp");
		}

		return postiLavoroDispFinded.get();

	}

	public List<DecodificaGenerica> getIstat(String codiceMinisteriale, String descrizioneMin) {
		DecodificaGenerica filtro = new DecodificaGenerica();
		filtro.setCodDecodifica(codiceMinisteriale);
		filtro.setDsDecodifica(descrizioneMin);
		filtro.setFlgAncheNonValidi(true);
		List<DecodificaGenerica> lista = proTIstat2001livello5Dao.findByFilter(filtro);
		return lista;
	}

	public Istat2001livello5 getIstatById(Long id) {
		Optional<Istat2001livello5> optional = proTIstat2001livello5Dao.findOne(id.longValue())
				.map(ProdisMappers.ISTAT2001LIVELLO5::toModel);
		if (!optional.isPresent()) {
			throw new NotFoundException("QualificaIstat");
		}
		return optional.get();
	}

	public List<DecodificaGenerica> getComune(String codiceMinisteriale, String descrizioneMin) {
		DecodificaGenerica filtro = new DecodificaGenerica();
		filtro.setCodDecodifica(codiceMinisteriale);
		filtro.setDsDecodifica(descrizioneMin);
		filtro.setFlgAncheNonValidi(true);
		List<DecodificaGenerica> lista = proTComuneDao.findByFilter(filtro);
		return lista;
	}

	public List<ProTStatiEsteri> getNazione(String codiceMinisteriale, String descrizioneMin) {

		List<ProTStatiEsteri> lista = proTStatiEsteriDao.find(codiceMinisteriale, descrizioneMin, null);
		return lista;
	}

	public Comune getComuneById(Long id) {
		Optional<Comune> optional = proTComuneDao.findOne(id.longValue()).map(ProdisMappers.COMUNE::toModel);
		if (!optional.isPresent()) {
			throw new NotFoundException("Comune");
		}
		return optional.get();
	}

	public StatiEsteri getStatoEsteroById(Long id) {
		Optional<StatiEsteri> optional = proTStatiEsteriDao.findOne(id.longValue())
				.map(ProdisMappers.STATI_ESTERI::toModel);
		if (!optional.isPresent()) {
			throw new NotFoundException("StatiEsteri");
		}
		return optional.get();
	}

	public List<DecodificaGenerica> getIstatValidi(String codiceMinisteriale, String descrizioneMin) {
		DecodificaGenerica filtro = new DecodificaGenerica();
		filtro.setCodDecodifica(codiceMinisteriale);
		filtro.setDsDecodifica(descrizioneMin);
		filtro.setFlgAncheNonValidi(false);
		List<DecodificaGenerica> lista = proTIstat2001livello5Dao.findByFilter(filtro);
		return lista;
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

	public List<ProDPostiLavoroDisp> getElencoPostiLavoroDispByIdProspettoProv(Long idProspettoProv) {
		List<ProDPostiLavoroDisp> elencoPostiLavoroDisp = proDPostiLavoroDispDao.findByField(idProspettoProv);
		return elencoPostiLavoroDisp;
	}

}
