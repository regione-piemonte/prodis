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

import it.csi.prodis.prodisweb.ejb.business.be.dad.sort.ProspettoSort;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDCategorieEscluseDao;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDDatiProvincialiDao;
import it.csi.prodis.prodisweb.ejb.entity.ProDCategorieEscluse;
import it.csi.prodis.prodisweb.ejb.entity.ProDDatiProvinciali;
import it.csi.prodis.prodisweb.ejb.exception.NotFoundException;
import it.csi.prodis.prodisweb.ejb.mapper.ProdisMappers;
import it.csi.prodis.prodisweb.ejb.util.jpa.Page;
import it.csi.prodis.prodisweb.lib.dto.prospetto.CategorieEscluse;
import it.csi.prodis.prodisweb.lib.util.pagination.PagedList;
import it.csi.prodis.prodisweb.lib.util.pagination.Sort;

/**
 * Data Access Delegate for categorieEscluse
 */
@ApplicationScoped
public class CategorieEscluseDad extends BaseDad {

	@Inject
	private ProDCategorieEscluseDao proDCategorieEscluseDao;
	
	@Inject
	private ProDDatiProvincialiDao proDDatiProvincialiDao;

	public PagedList<CategorieEscluse> getCategorieEsclusePagByIdProspettoProv(int page, int size, Sort sort, Long idProspettoProv) {
		String sortField = null;
		String sortDirection = null;
		if (sort != null) {
			if (ProspettoSort.byModelName(sort.getField()) != null) {
				sortField = ProspettoSort.byModelName(sort.getField()).getQueryName();
			}
			sortDirection = sort.getOrder().getSortDirection();
		}

		Page<ProDCategorieEscluse> prodisDProspettos = proDCategorieEscluseDao.findPaginatedByIdProspettoProv(page, size, sortField, sortDirection,
				new BigDecimal(idProspettoProv));

		PagedList<CategorieEscluse> pagedList = toPagedList(prodisDProspettos, page, size, ProdisMappers.CATEGORIE_ESCLUSE::toModel);
		return pagedList;
	}
	
	public CategorieEscluse insertSingleCategorieEscluse (CategorieEscluse ce, Integer idDatiProvinciali) {
		
		Date dataAttuale = new Date();
		
		Optional<ProDDatiProvinciali> datiProvincialiFinded = proDDatiProvincialiDao.findOne(idDatiProvinciali.longValue());
		
		ProDCategorieEscluse newCe = null;
		if (!datiProvincialiFinded.isPresent()) {
			throw new NotFoundException("DatiProvinciali");
		} else {
			
			ProDCategorieEscluse ceEntity = ProdisMappers.CATEGORIE_ESCLUSE.toEntity(ce);
			
			ceEntity.setdAggiorn(dataAttuale);
			ceEntity.setdInserim(dataAttuale);
			
			newCe = proDCategorieEscluseDao.insert(ceEntity);
		}
		
		return ProdisMappers.CATEGORIE_ESCLUSE.toModel(newCe);
		
	}

	public CategorieEscluse updateSingleCategorieEscluse(CategorieEscluse categorieEscluse, Integer idCategorieEscluse) {
		
		Date dataAttuale = new Date();
		
		Optional<ProDCategorieEscluse> categorieEscluseFinded = proDCategorieEscluseDao.findOne(idCategorieEscluse.longValue());
		
		ProDCategorieEscluse newCe = null;
		if (!categorieEscluseFinded.isPresent()) {
			throw new NotFoundException("CategorieEscluse");
		} else {
			
			ProDCategorieEscluse newCat = ProdisMappers.CATEGORIE_ESCLUSE.toEntity(categorieEscluse);
			
			newCat.setdAggiorn(dataAttuale);
			newCat.setCodUserInserim(categorieEscluseFinded.get().getCodUserInserim());
			newCat.setdInserim(categorieEscluseFinded.get().getdInserim());
			
			newCe = proDCategorieEscluseDao.update(newCat);
			
		}
		
		return ProdisMappers.CATEGORIE_ESCLUSE.toModel(newCe);
	}

	public CategorieEscluse deleteSingleCategorieEscluse(Long idCategorieEscluse) {

		Optional<ProDCategorieEscluse> categorieEscluseFinded = proDCategorieEscluseDao.findOne(idCategorieEscluse);
		
		if (categorieEscluseFinded.isPresent()) {
			proDCategorieEscluseDao.delete(idCategorieEscluse);	
		} else {
			throw new NotFoundException("CategorieEscluse");
		}
		
		return ProdisMappers.CATEGORIE_ESCLUSE.toModel(categorieEscluseFinded.get());

	}
	public List<ProDCategorieEscluse> getListaCaterieEscluseByIdProspettoProvincia(Long idProspettoProvincia) {
		return proDCategorieEscluseDao.findByIdProspettoProv(new BigDecimal(idProspettoProvincia));
	}
}
