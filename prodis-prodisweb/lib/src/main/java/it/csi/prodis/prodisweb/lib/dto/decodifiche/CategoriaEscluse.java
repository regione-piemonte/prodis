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
package it.csi.prodis.prodisweb.lib.dto.decodifiche;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import it.csi.prodis.prodisweb.lib.dto.BaseDto;
import it.csi.prodis.prodisweb.lib.dto.prospetto.CategorieEscluse;

/**
 * The Class CategoriaEscluse.
 */
public class CategoriaEscluse extends BaseDto<Long> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String ambitoCategoria;
	private String codCategoriaEscluse;
	private Date dataFine;
	private Date dataInizio;
	private Date dataTmst;
	private String desCategoriaEscluse;
	private List<CategorieEscluse> categorieEscluses;

	/**
	 * @return the ambitoCategoria
	 */
	public String getAmbitoCategoria() {
		return ambitoCategoria;
	}
	
	/**
	 * @param ambitoCategoria the ambitoCategoria to set
	 */
	public void setAmbitoCategoria(String ambitoCategoria) {
		this.ambitoCategoria = ambitoCategoria;
	}

	/**
	 * @return the codCategoriaEscluse
	 */
	public String getCodCategoriaEscluse() {
		return codCategoriaEscluse;
	}
	
	/**
	 * @param codCategoriaEscluse the codCategoriaEscluse to set
	 */
	public void setCodCategoriaEscluse(String codCategoriaEscluse) {
		this.codCategoriaEscluse = codCategoriaEscluse;
	}

	/**
	 * @return the dataFine
	 */
	public Date getDataFine() {
		return dataFine;
	}
	
	/**
	 * @param dataFine the dataFine to set
	 */
	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	/**
	 * @return the dataInizio
	 */
	public Date getDataInizio() {
		return dataInizio;
	}
	
	/**
	 * @param dataInizio the dataInizio to set
	 */
	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	/**
	 * @return the dataTmst
	 */
	public Date getDataTmst() {
		return dataTmst;
	}
	
	/**
	 * @param dataTmst the dataTmst to set
	 */
	public void setDataTmst(Date dataTmst) {
		this.dataTmst = dataTmst;
	}

	/**
	 * @return the desCategoriaEscluse
	 */
	public String getDesCategoriaEscluse() {
		return desCategoriaEscluse;
	}
	
	/**
	 * @param desCategoriaEscluse the desCategoriaEscluse to set
	 */
	public void setDesCategoriaEscluse(String desCategoriaEscluse) {
		this.desCategoriaEscluse = desCategoriaEscluse;
	}

	/**
	 * @return the categorieEscluses
	 */
	public List<CategorieEscluse> getCategorieEscluses() {
		return categorieEscluses;
	}
	
	/**
	 * @param categorieEscluses the categorieEscluses to set
	 */
	public void setCategorieEscluses(List<CategorieEscluse> categorieEscluses) {
		this.categorieEscluses = categorieEscluses;
	}

}
