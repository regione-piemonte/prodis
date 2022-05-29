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

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDRiepilogoProvincialeDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProRProspettoProvinciaDao;
import it.csi.prodis.prodisweb.ejb.entity.ProDRiepilogoProvinciale;
import it.csi.prodis.prodisweb.ejb.entity.ProRProspettoProvincia;
import it.csi.prodis.prodisweb.ejb.exception.NotFoundException;
import it.csi.prodis.prodisweb.ejb.mapper.ProdisMappers;
import it.csi.prodis.prodisweb.lib.dto.prospetto.RiepilogoProvinciale;

@ApplicationScoped
public class RiepilogoProvincialeDad {

	@Inject
	private ProRProspettoProvinciaDao proRProspettoProvinciaDao;
	
	@Inject
	private ProDRiepilogoProvincialeDao proDRiepilogoProvincialeDao;

	public RiepilogoProvinciale insertRiepilogoProvinciale(RiepilogoProvinciale riepilogoProvinciale, Long idProspettoProv) {
		
		Optional<ProRProspettoProvincia> prospettoProvinciaFinded = proRProspettoProvinciaDao.findOne(idProspettoProv);
		
		ProDRiepilogoProvinciale newRiepilogoProvinciale = null;
		
		if (!prospettoProvinciaFinded.isPresent()) {
			throw new NotFoundException("ProspettoProvincia");
		} else {
			newRiepilogoProvinciale = ProdisMappers.RIEPILOGO_PROVINCIALE.toEntity(riepilogoProvinciale);
			newRiepilogoProvinciale = proDRiepilogoProvincialeDao.insert(newRiepilogoProvinciale);
		}
		
		return ProdisMappers.RIEPILOGO_PROVINCIALE.toModel(newRiepilogoProvinciale);
		
	}

	public RiepilogoProvinciale updateRiepilogoProvinciale(RiepilogoProvinciale riepilogoProvinciale, Long idProspettoProv, Long idRiepilogoProvinciale) {
		
		Optional<ProRProspettoProvincia> prospettoProvinciaFinded = proRProspettoProvinciaDao.findOne(idProspettoProv);
		
		Optional<ProDRiepilogoProvinciale> riepilogoProvincialeFinded = proDRiepilogoProvincialeDao.findOne(idRiepilogoProvinciale);
		
		if (!riepilogoProvincialeFinded.isPresent()) {
			throw new NotFoundException("RiepilogoProvinciale");
		}
		
		ProDRiepilogoProvinciale newRiepilogoProvinciale = null;
		
		if (!prospettoProvinciaFinded.isPresent()) {
			throw new NotFoundException("Prospetto");
		} else {
			newRiepilogoProvinciale = ProdisMappers.RIEPILOGO_PROVINCIALE.toEntity(riepilogoProvinciale);
			newRiepilogoProvinciale.setId(idRiepilogoProvinciale);
			newRiepilogoProvinciale = proDRiepilogoProvincialeDao.update(newRiepilogoProvinciale);
		}
		
		return ProdisMappers.RIEPILOGO_PROVINCIALE.toModel(newRiepilogoProvinciale);
	}

}
