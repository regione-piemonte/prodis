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
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDProspettoDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDRiepilogoNazionaleDao;
import it.csi.prodis.prodisweb.ejb.entity.ProDProspetto;
import it.csi.prodis.prodisweb.ejb.entity.ProDRiepilogoNazionale;
import it.csi.prodis.prodisweb.ejb.exception.NotFoundException;
import it.csi.prodis.prodisweb.ejb.mapper.ProdisMappers;
import it.csi.prodis.prodisweb.lib.dto.prospetto.RiepilogoNazionale;

@ApplicationScoped
public class RiepilogoNazionaleDad extends BaseDad {
	
	@Inject
	private ProDProspettoDao proDProspettoDao;
	
	@Inject
	private ProDRiepilogoNazionaleDao proDRiepilogoNazionaleDao;

	public RiepilogoNazionale insertRiepilogoNazionale(RiepilogoNazionale riepilogoNazionale, Long idProspetto) {
		
		Date dataAttuale = new Date();
		
		Optional<ProDProspetto> prospettoFinded = proDProspettoDao.findOne(idProspetto);
		
		ProDRiepilogoNazionale newRiepilogoNazionale = null;
		
		if (!prospettoFinded.isPresent()) {
			throw new NotFoundException("Prospetto");
		} else {
			newRiepilogoNazionale = ProdisMappers.RIEPILOGO_NAZIONALE.toEntity(riepilogoNazionale);
			newRiepilogoNazionale.setIdProspetto(idProspetto);
			newRiepilogoNazionale.setDInserim(dataAttuale);
			newRiepilogoNazionale.setDAggiorn(dataAttuale);
			newRiepilogoNazionale.setdInserim(dataAttuale);
			newRiepilogoNazionale.setdAggiorn(dataAttuale);
			newRiepilogoNazionale = proDRiepilogoNazionaleDao.insert(newRiepilogoNazionale);
		}
		
		return ProdisMappers.RIEPILOGO_NAZIONALE.toModel(newRiepilogoNazionale);
		
	}

	public RiepilogoNazionale updateRiepilogoNazionale(RiepilogoNazionale riepilogoNazionale, Long idProspetto, Long idRiepilogoNazionale) {
		
		Date dataAttuale = new Date();
		
		Optional<ProDProspetto> prospettoFinded = proDProspettoDao.findOne(idProspetto);
		
		Optional<ProDRiepilogoNazionale> riepilogoNazionaleFinded = proDRiepilogoNazionaleDao.findOne(idRiepilogoNazionale);
		
		if (!riepilogoNazionaleFinded.isPresent()) {
			throw new NotFoundException("RiepilogoNazionale");
		}
		
		ProDRiepilogoNazionale newRiepilogoNazionale = null;
		
		if (!prospettoFinded.isPresent()) {
			throw new NotFoundException("Prospetto");
		} else {
			newRiepilogoNazionale = ProdisMappers.RIEPILOGO_NAZIONALE.toEntity(riepilogoNazionale);
			newRiepilogoNazionale.setIdProspetto(idProspetto);
			newRiepilogoNazionale.setId(idRiepilogoNazionale);
			newRiepilogoNazionale.setDAggiorn(dataAttuale);
			newRiepilogoNazionale.setdAggiorn(dataAttuale);
			newRiepilogoNazionale.setDInserim(riepilogoNazionaleFinded.get().getDInserim());
			newRiepilogoNazionale.setdInserim(riepilogoNazionaleFinded.get().getdInserim());
			newRiepilogoNazionale.setCodUserInserim(riepilogoNazionaleFinded.get().getCodUserInserim());
			newRiepilogoNazionale = proDRiepilogoNazionaleDao.update(newRiepilogoNazionale);
		}
		
		return ProdisMappers.RIEPILOGO_NAZIONALE.toModel(newRiepilogoNazionale);
	}
	
	public RiepilogoNazionale getRiepilogoNazionaleByIdProspetto(Long idProspetto) {
		Optional<ProDRiepilogoNazionale> riepilogoNazionale = proDRiepilogoNazionaleDao.findOne(idProspetto);
		
		if(riepilogoNazionale.isPresent()) {
			return ProdisMappers.RIEPILOGO_NAZIONALE.toModel(riepilogoNazionale.get());
		}
		return null;
		
		
	}

}
