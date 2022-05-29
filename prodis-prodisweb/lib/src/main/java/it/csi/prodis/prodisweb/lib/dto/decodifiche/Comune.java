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
import java.math.BigDecimal;
import java.util.Date;

import it.csi.prodis.prodisweb.lib.dto.BaseDto;

/**
 * The Class Comune.
 */
public class Comune extends BaseDto<Long> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String codCap;
	private String codComuneMin;
	private String codInps;
	private String codIstat;
	private String codPrefisso;
	private String dsProTComune;
	private Date dtFine;
	private Date dtInizio;
	private Date dtTmst;
	private BigDecimal idTCpi;
	private Provincia provincia;

	/**
	 * @return the codCap
	 */
	public String getCodCap() {
		return codCap;
	}
	
	/**
	 * @param codCap the codCap to set
	 */
	public void setCodCap(String codCap) {
		this.codCap = codCap;
	}

	/**
	 * @return the codComuneMin
	 */
	public String getCodComuneMin() {
		return codComuneMin;
	}
	
	/**
	 * @param codComuneMin the codComuneMin to set
	 */
	public void setCodComuneMin(String codComuneMin) {
		this.codComuneMin = codComuneMin;
	}

	/**
	 * @return the codInps
	 */
	public String getCodInps() {
		return codInps;
	}
	
	/**
	 * @param codInps the codInps to set
	 */
	public void setCodInps(String codInps) {
		this.codInps = codInps;
	}

	/**
	 * @return the codIstat
	 */
	public String getCodIstat() {
		return codIstat;
	}
	
	/**
	 * @param codIstat the codIstat to set
	 */
	public void setCodIstat(String codIstat) {
		this.codIstat = codIstat;
	}

	/**
	 * @return the codPrefisso
	 */
	public String getCodPrefisso() {
		return codPrefisso;
	}
	
	/**
	 * @param codPrefisso the codPrefisso to set
	 */
	public void setCodPrefisso(String codPrefisso) {
		this.codPrefisso = codPrefisso;
	}

	/**
	 * @return the dsProTComune
	 */
	public String getDsProTComune() {
		return dsProTComune;
	}
	
	/**
	 * @param dsProTComune the dsProTComune to set
	 */
	public void setDsProTComune(String dsProTComune) {
		this.dsProTComune = dsProTComune;
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
	 * @return the dtTmst
	 */
	public Date getDtTmst() {
		return dtTmst;
	}
	
	/**
	 * @param dtTmst the dtTmst to set
	 */
	public void setDtTmst(Date dtTmst) {
		this.dtTmst = dtTmst;
	}

	/**
	 * @return the idTCpi
	 */
	public BigDecimal getIdTCpi() {
		return idTCpi;
	}
	
	/**
	 * @param idTCpi the idTCpi to set
	 */
	public void setIdTCpi(BigDecimal idTCpi) {
		this.idTCpi = idTCpi;
	}

	/**
	 * @return the provincia
	 */
	public Provincia getProvincia() {
		return provincia;
	}
	
	/**
	 * @param provincia the provincia to set
	 */
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

}
