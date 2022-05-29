/*-
 * ========================LICENSE_START=================================
 * PRODIS Servizi - LIB submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodissrv.lib.dto.decodifiche;

import java.io.Serializable;
import java.util.List;

import it.csi.prodis.prodissrv.lib.dto.BaseDto;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ImportErrori;

/**
 * The Class ImportErroriSpicom.
 */
public class ImportErroriSpicom extends BaseDto<Long> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String descErrore;
	private List<ImportErrori> importErroris;

	/**
	 * @return the descErrore
	 */
	public String getDescErrore() {
		return descErrore;
	}
	
	/**
	 * @param descErrore the descErrore to set
	 */
	public void setDescErrore(String descErrore) {
		this.descErrore = descErrore;
	}

	/**
	 * @return the importErroris
	 */
	public List<ImportErrori> getImportErroris() {
		return importErroris;
	}
	
	/**
	 * @param importErroris the importErroris to set
	 */
	public void setImportErroris(List<ImportErrori> importErroris) {
		this.importErroris = importErroris;
	}

}
