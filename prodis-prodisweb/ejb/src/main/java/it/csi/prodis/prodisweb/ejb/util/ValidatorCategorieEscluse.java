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
package it.csi.prodis.prodisweb.ejb.util;

import java.math.BigDecimal;
import java.util.List;

import it.csi.prodis.prodisweb.ejb.business.be.dad.CategorieEscluseDad;
import it.csi.prodis.prodisweb.ejb.entity.ProDCategorieEscluse;
import it.csi.prodis.prodisweb.lib.dto.ApiError;
import it.csi.prodis.prodisweb.lib.dto.error.MsgProdis;
import it.csi.prodis.prodisweb.lib.dto.prospetto.CategorieEscluse;

public class ValidatorCategorieEscluse  extends ValidatorUtil {

	CategorieEscluseDad categorieEscluseDad;



	public ValidatorCategorieEscluse(CategorieEscluseDad categorieEscluseDad) {
		super();
		this.categorieEscluseDad = categorieEscluseDad;
	}



	public void valida(CategorieEscluse categorieEscluse, List<ApiError> apiErrors) {
		if (categorieEscluse.getnLavAppartartCategoria() == null || categorieEscluse.getnLavAppartartCategoria().equals(new BigDecimal(0))) {
			apiErrors.add(MsgProdis.PROCATE0001.getError());
		}
		if (categorieEscluse.getCategoriaEscluse() == null || categorieEscluse.getCategoriaEscluse().getId() == null) {
			apiErrors.add(MsgProdis.PROCATE0002.getError());
		}

		if (apiErrors == null || apiErrors.size() == 0) {
			List<ProDCategorieEscluse> elencoCategorie = categorieEscluseDad.getListaCaterieEscluseByIdProspettoProvincia(categorieEscluse.getIdProspettoProv());
			
			if (elencoCategorie != null && elencoCategorie.size() > 0) {
				if (categorieEscluse.getId() == null) { //inserimento
					for (ProDCategorieEscluse cat : elencoCategorie) {
						if (cat.getProTCategoriaEscluse().getCodCategoriaEscluse().equalsIgnoreCase(categorieEscluse.getCategoriaEscluse().getCodCategoriaEscluse())) {
							apiErrors.add(MsgProdis.PROCATE0003.getError());
						}
					}
				} else { //modifica
					for (ProDCategorieEscluse cat : elencoCategorie) {
						if (cat.getProTCategoriaEscluse().getCodCategoriaEscluse().equalsIgnoreCase(categorieEscluse.getCategoriaEscluse().getCodCategoriaEscluse())
								&& categorieEscluse.getId().longValue() != cat.getIdCategorieEscluse()) {
							apiErrors.add(MsgProdis.PROCATE0003.getError());
						}
					}
				}
			}
		}
	}
}
