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
import java.util.Date;
import java.util.List;

import it.csi.prodis.prodissrv.lib.dto.BaseDto;

/**
 * The Class Cpi.
 */
public class Cpi extends BaseDto<Long> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String cUtente;
	private String codCpi;
	private String dsProTCpi;
	private Date dtFine;
	private Date dtInizio;
	private List<Comune> comunes;

	/**
	 * @return the cUtente
	 */
	public String getCUtente() {
		return cUtente;
	}
	
	/**
	 * @param cUtente the cUtente to set
	 */
	public void setCUtente(String cUtente) {
		this.cUtente = cUtente;
	}

	/**
	 * @return the codCpi
	 */
	public String getCodCpi() {
		return codCpi;
	}
	
	/**
	 * @param codCpi the codCpi to set
	 */
	public void setCodCpi(String codCpi) {
		this.codCpi = codCpi;
	}

	/**
	 * @return the dsProTCpi
	 */
	public String getDsProTCpi() {
		return dsProTCpi;
	}
	
	/**
	 * @param dsProTCpi the dsProTCpi to set
	 */
	public void setDsProTCpi(String dsProTCpi) {
		this.dsProTCpi = dsProTCpi;
	}

	/**
	 * @return the dtFine
	 */
	public Date getDtFine() {
		return dtFine;
	}
	
	/**
	 * @param dtFine the dtFine to set
	 */
	public void setDtFine(Date dtFine) {
		this.dtFine = dtFine;
	}

	/**
	 * @return the dtInizio
	 */
	public Date getDtInizio() {
		return dtInizio;
	}
	
	/**
	 * @param dtInizio the dtInizio to set
	 */
	public void setDtInizio(Date dtInizio) {
		this.dtInizio = dtInizio;
	}

	/**
	 * @return the comunes
	 */
	public List<Comune> getComunes() {
		return comunes;
	}
	
	/**
	 * @param comunes the comunes to set
	 */
	public void setComunes(List<Comune> comunes) {
		this.comunes = comunes;
	}

}
