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
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDPartTimeDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDProvIntermittentiDao;
import it.csi.prodis.prodisweb.ejb.entity.ProDDatiProvinciali;
import it.csi.prodis.prodisweb.ejb.entity.ProDPartTime;
import it.csi.prodis.prodisweb.ejb.entity.ProDProvIntermittenti;
import it.csi.prodis.prodisweb.ejb.exception.NotFoundException;
import it.csi.prodis.prodisweb.ejb.mapper.ProdisMappers;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProvIntermittenti;

@ApplicationScoped
public class IntermittentiDad {
	
	@Inject
	private ProDDatiProvincialiDao proDDatiProvincialiDao;
	
	@Inject
	private ProDProvIntermittentiDao proDProvIntermittentiDao;
	
	@Inject
	private ProDPartTimeDao proDPartTimeDao;

	public ProvIntermittenti insertSingleIntermittenti (ProvIntermittenti pi, Long idDatiProvinciali, Long idPartTime) {
		
		Date dataAttuale = new Date();
		
		if (idPartTime!=null) {
			
			Optional<ProDPartTime> partTimeFinded = proDPartTimeDao.findOne(idPartTime);
			
			if (partTimeFinded.isPresent()) {
				if (partTimeFinded.get().getIdProspettoProv().intValue()==idDatiProvinciali) {
					proDPartTimeDao.delete(idPartTime);
				} else {
					throw new NotFoundException("PartTime");
				}
			} else {
				throw new NotFoundException("PartTime");
			}
			
		}
		
		Optional<ProDDatiProvinciali> datiProvincialiFinded = proDDatiProvincialiDao.findOne(idDatiProvinciali);
		
		ProDProvIntermittenti newI = null;
		if (!datiProvincialiFinded.isPresent()) {
			throw new NotFoundException("DatiProvinciali");
		} else {
			ProDProvIntermittenti newIntermittenti = ProdisMappers.PROV_INTERMITTENTI.toEntity(pi);
			newIntermittenti.setIdProspettoProv(new BigDecimal(idDatiProvinciali));
			newIntermittenti.setdAggiorn(dataAttuale);
			newIntermittenti.setdInserim(dataAttuale);
			newI = proDProvIntermittentiDao.insert(newIntermittenti);
		}
		
		return ProdisMappers.PROV_INTERMITTENTI.toModel(newI);
		
	}

	public ProvIntermittenti updateSingleIntermittenti(ProvIntermittenti intermittenti, Integer idIntermittenti) {
		
		Date dataAttuale = new Date();
		
		Optional<ProDProvIntermittenti> intermittentiFinded = proDProvIntermittentiDao.findOne(idIntermittenti.longValue());
		
		ProDProvIntermittenti newI = null;
		if (!intermittentiFinded.isPresent()) {
			throw new NotFoundException("ProvIntermittenti");
		} else {
			
			ProDProvIntermittenti newIntermittenti = ProdisMappers.PROV_INTERMITTENTI.toEntity(intermittenti);
			
			newIntermittenti.setdAggiorn(dataAttuale);
			newIntermittenti.setdInserim(intermittentiFinded.get().getdInserim());
			newIntermittenti.setCodUserInserim(intermittentiFinded.get().getCodUserInserim());
			
			newI = proDProvIntermittentiDao.update(newIntermittenti);
			
		}
		
		return ProdisMappers.PROV_INTERMITTENTI.toModel(newI);
	}
	
	public ProvIntermittenti deleteSingleIntermittenti(Long idIntermittenti) {

		Optional<ProDProvIntermittenti> intermittentiFinded = proDProvIntermittentiDao.findOne(idIntermittenti);
		
		if (intermittentiFinded.isPresent()) {
			proDProvIntermittentiDao.delete(idIntermittenti);	
		} else {
			throw new NotFoundException("Intermittenti");
		}
		
		return ProdisMappers.PROV_INTERMITTENTI.toModel(intermittentiFinded.get());

	}
	public List<ProDProvIntermittenti> getListaIntermittentiByIdProspettoProvincia(Long idProspettoProvincia) {
		return proDProvIntermittentiDao.findByIdProspettoProv(new BigDecimal(idProspettoProvincia));
	}

	public ProvIntermittenti cercaIntermittentiPerControllo(Long idIntermittenti) {
		
		Optional<ProDProvIntermittenti> intermittentiFinded = proDProvIntermittentiDao.findOne(idIntermittenti);
		
		if (!intermittentiFinded.isPresent()) {
			throw new NotFoundException("Intermittenti");
		}
		
		return ProdisMappers.PROV_INTERMITTENTI.toModel(intermittentiFinded.get());
		
	}
}
