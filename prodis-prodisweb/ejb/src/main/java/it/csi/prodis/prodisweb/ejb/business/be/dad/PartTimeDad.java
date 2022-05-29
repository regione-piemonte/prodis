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
import it.csi.prodis.prodisweb.lib.dto.prospetto.PartTime;

/**
 * Data Access Delegate for partTime
 */
@ApplicationScoped
public class PartTimeDad extends BaseDad {

	@Inject
	private ProDPartTimeDao proDPartTimeDao;
	
	@Inject
	private ProDDatiProvincialiDao proDDatiProvincialiDao;
	
	@Inject
	private ProDProvIntermittentiDao proDProvIntermittentiDao;


	public PartTime insertSinglePartTime (PartTime pt, Integer idDatiProvinciali, Long idIntermittenti) {
		
		Date dataAttuale = new Date();
		
		if (idIntermittenti!=null) {
			
			Optional<ProDProvIntermittenti> intermittentiFinded = proDProvIntermittentiDao.findOne(idIntermittenti);
			
			if (intermittentiFinded.isPresent()) {
				if (intermittentiFinded.get().getIdProspettoProv().intValue()==idDatiProvinciali) {
					proDProvIntermittentiDao.delete(idIntermittenti);
				} else {
					throw new NotFoundException("Intermittenti");
				}
			} else {
				throw new NotFoundException("Intermittenti");
			}
			
		}
		
		Optional<ProDDatiProvinciali> datiProvincialiFinded = proDDatiProvincialiDao.findOne(idDatiProvinciali.longValue());

		ProDPartTime newPartTime = null;
		if (!datiProvincialiFinded.isPresent()) {
			throw new NotFoundException("DatiProvinciali");
		} else {
			
			ProDPartTime ptEntity = ProdisMappers.PART_TIME.toEntity(pt);
			
			ptEntity.setdAggiorn(dataAttuale);
			ptEntity.setdInserim(dataAttuale);
			
			newPartTime = proDPartTimeDao.insert(ptEntity);
		}
		
		return ProdisMappers.PART_TIME.toModel(newPartTime);
		
	}

	public PartTime updateSinglePartTime(PartTime partTime, Integer idPartTime) {
		
		Date dataAttuale = new Date();
		
		Optional<ProDPartTime> partTimeFinded = proDPartTimeDao.findOne(idPartTime.longValue());
		
		ProDPartTime newPt = null;
		if (!partTimeFinded.isPresent()) {
			throw new NotFoundException("PartTime");
		} else {
			
			ProDPartTime newPartTime = ProdisMappers.PART_TIME.toEntity(partTime);
			
			newPartTime.setdAggiorn(dataAttuale);
			newPartTime.setdInserim(partTimeFinded.get().getdInserim());
			newPartTime.setCodUserInserim(partTimeFinded.get().getCodUserInserim());
			
			newPt = proDPartTimeDao.update(newPartTime);
			
		}
		
		return ProdisMappers.PART_TIME.toModel(newPt);
		
	}
	
	public PartTime deleteSinglePartTime(Long idPartTime) {

		Optional<ProDPartTime> partTimeFinded = proDPartTimeDao.findOne(idPartTime);
		
		if (partTimeFinded.isPresent()) {
			proDPartTimeDao.delete(idPartTime);	
		} else {
			throw new NotFoundException("PartTime");
		}
		
		return ProdisMappers.PART_TIME.toModel(partTimeFinded.get());

	}
	public List<ProDPartTime> getListaPartTimeByIdProspettoProvincia(Long idProspettoProvincia) {
		return proDPartTimeDao.findByIdProspettoProv(new BigDecimal(idProspettoProvincia));
	}
}

