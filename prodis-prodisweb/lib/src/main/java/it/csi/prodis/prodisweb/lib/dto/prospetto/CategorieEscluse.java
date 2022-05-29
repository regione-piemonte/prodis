/*-
 * ========================LICENSE_START=================================
 * PRODIS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodisweb.lib.dto.prospetto;

import java.io.Serializable;
import java.math.BigDecimal;

import it.csi.prodis.prodisweb.lib.dto.BaseAuditedDto;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.CategoriaEscluse;

/**
 * The Class CategorieEscluse.
 */
public class CategorieEscluse extends BaseAuditedDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private BigDecimal nLavAppartartCategoria;
	private Long idProspettoProv;
	private CategoriaEscluse categoriaEscluse;

	/**
	 * @return the nLavAppartartCategoria
	 */
	public BigDecimal getnLavAppartartCategoria() {
		return nLavAppartartCategoria;
	}
	
	/**
	 * @param nLavAppartartCategoria the nLavAppartartCategoria to set
	 */
	public void setnLavAppartartCategoria(BigDecimal nLavAppartartCategoria) {
		this.nLavAppartartCategoria = nLavAppartartCategoria;
	}

	/**
	 * @return the categoriaEscluse
	 */
	public CategoriaEscluse getCategoriaEscluse() {
		return categoriaEscluse;
	}
	
	/**
	 * @param categoriaEscluse the categoriaEscluse to set
	 */
	public void setCategoriaEscluse(CategoriaEscluse categoriaEscluse) {
		this.categoriaEscluse = categoriaEscluse;
	}

	public Long getIdProspettoProv() {
		return idProspettoProv;
	}

	public void setIdProspettoProv(Long idProspettoProv) {
		this.idProspettoProv = idProspettoProv;
	}

}
