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
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDParametriDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDProspettoDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDProspettoGradualitaDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDProvCompensazioniDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDRiepilogoProvincialeDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProRProspettoProvinciaDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProTProvinciaDao;
import it.csi.prodis.prodisweb.ejb.entity.ProDAssPubbliche;
import it.csi.prodis.prodisweb.ejb.entity.ProDDatiProvinciali;
import it.csi.prodis.prodisweb.ejb.entity.ProDProvCompensazioni;
import it.csi.prodis.prodisweb.ejb.entity.ProRProspettoProvincia;
import it.csi.prodis.prodisweb.ejb.exception.NotFoundException;
import it.csi.prodis.prodisweb.ejb.mapper.ProdisMappers;
import it.csi.prodis.prodisweb.ejb.util.ProdisSrvUtil;
import it.csi.prodis.prodisweb.lib.dto.common.DecodificaGenerica;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Provincia;
import it.csi.prodis.prodisweb.lib.dto.prospetto.AssPubbliche;
import it.csi.prodis.prodisweb.lib.dto.prospetto.DatiAzienda;
import it.csi.prodis.prodisweb.lib.dto.prospetto.Prospetto;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProspettoGradualita;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProspettoProvincia;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProvCompensazioni;
import it.csi.prodis.prodisweb.lib.dto.prospetto.RiepilogoProvinciale;

@ApplicationScoped
public class CompensazioniDad {
	
	@Inject
	private ProDProvCompensazioniDao proDCompensazioniDao;
	
	@Inject
	private ProDDatiProvincialiDao proDDatiProvincialiDao;
	

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
	private ProDParametriDao proDParamentriDao;
	
	
	@Inject
	private ProTProvinciaDao proTProvinciaDao;
	
	
	@Inject
	private ProDRiepilogoProvincialeDao proDRiepilogoProvincialeDao;
	

	public ProvCompensazioni updateCompensazioni(ProvCompensazioni compensazioni, Long id) {
		
		Date dataAttuale = new Date();
		
		Optional<ProDProvCompensazioni> compensazioniFinded = proDCompensazioniDao.findOne(id);
		
		Optional<ProDDatiProvinciali> datiProvincialiFinded = proDDatiProvincialiDao.findOne(compensazioni.getIdProspettoProv());
		
		if (!datiProvincialiFinded.isPresent()) {
			throw new NotFoundException("DatiProvinciali");
		}
		
		ProDProvCompensazioni newCompensazioni = null;
		
		if (!compensazioniFinded.isPresent()) {
			throw new NotFoundException("Compensazioni");
		} else {
			newCompensazioni = ProdisMappers.PROV_COMPENSAZIONI.toEntity(compensazioni);
			newCompensazioni.setIdProspettoProv(new BigDecimal(compensazioni.getIdProspettoProv()));
			newCompensazioni.setId(id);
			newCompensazioni.setDAggiorn(dataAttuale);
			newCompensazioni.setdAggiorn(dataAttuale);
			newCompensazioni.setCodUserInserim(compensazioniFinded.get().getCodUserInserim());
			newCompensazioni.setDInserim(compensazioniFinded.get().getDInserim());
			newCompensazioni.setdInserim(compensazioniFinded.get().getdInserim());
			newCompensazioni = proDCompensazioniDao.update(newCompensazioni);
		}
		
		return ProdisMappers.PROV_COMPENSAZIONI.toModel(newCompensazioni);
		
	}

	public ProvCompensazioni insertCompensazioni(ProvCompensazioni compensazioni, Long id) {
		
		Date dataAttuale = new Date();
		
		Optional<ProDDatiProvinciali> datiProvincialiFinded = proDDatiProvincialiDao.findOne(id);
		
		ProDProvCompensazioni newCompensazioni = null;
		
		if (!datiProvincialiFinded.isPresent()) {
			throw new NotFoundException("DatiProvinciali");
		} else {
			newCompensazioni = ProdisMappers.PROV_COMPENSAZIONI.toEntity(compensazioni);
			newCompensazioni.setIdProspettoProv(new BigDecimal(id));
			newCompensazioni.setDInserim(dataAttuale);
			newCompensazioni.setDAggiorn(dataAttuale);
			newCompensazioni.setdInserim(dataAttuale);
			newCompensazioni.setdAggiorn(dataAttuale);
			newCompensazioni = proDCompensazioniDao.insert(newCompensazioni);
		}
		
		return ProdisMappers.PROV_COMPENSAZIONI.toModel(newCompensazioni);
	}

	public ProDProvCompensazioni deleteCompensazioni(Long idCompensazioni) {

		Optional<ProDProvCompensazioni> compensazioniFinded = proDCompensazioniDao.findOne(idCompensazioni);
		
		if (compensazioniFinded.isPresent()) {
			proDCompensazioniDao.delete(idCompensazioni);	
		} else {
			throw new NotFoundException("Compensazioni");
		}
		
		return compensazioniFinded.get();
		
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
	public String getParametroByNome(String parametro) {
		DecodificaGenerica valore = proDParamentriDao.findByNome(parametro);
		if (ProdisSrvUtil.isVoid(valore)) {
			throw new NotFoundException("parametro");
		}
		return valore.getCodDecodifica();
	}
	
	public List<ProRProspettoProvincia> getElencoProspettiProvinciaByIdProspetto(Long idProspetto) {
		return  proRProspettoProvinciaDao.findByIdProspetto(idProspetto);
	}
	public Provincia getProvinciaById(Long id) {
		Optional<Provincia> optional = proTProvinciaDao.findOne(id.longValue())
				.map(ProdisMappers.PROVINCIA::toModel);
		if (!optional.isPresent()) {
			throw new NotFoundException("Provincia");
		}
		return optional.get();
	}
	public RiepilogoProvinciale getRiepilogoProvincialeByidProspettoProv(Long id) {
		Optional<RiepilogoProvinciale> optional = proDRiepilogoProvincialeDao.findOne(id.longValue())
				.map(ProdisMappers.RIEPILOGO_PROVINCIALE::toModel);
		if (!optional.isPresent()) {
			throw new NotFoundException("riepilogoProvinciale");
		}
		return optional.get();
	}
	public ProvCompensazioni getCompensazioniByidProspettoProv(Long id) {
		Optional<ProvCompensazioni> optional = proDCompensazioniDao.findOne(id.longValue())
				.map(ProdisMappers.PROV_COMPENSAZIONI::toModel);
		if (!optional.isPresent()) {
			throw new NotFoundException("compensazioni");
		}
		return optional.get();
	}
	
	public List<ProDProvCompensazioni> getElencoCompensazioniByIdProspettoProv (Long idProspettoProv) {
		List<ProDProvCompensazioni> elenco =  proDCompensazioniDao.findByIdProspettoProv(idProspettoProv);
		return elenco;
	}
	
	
}
