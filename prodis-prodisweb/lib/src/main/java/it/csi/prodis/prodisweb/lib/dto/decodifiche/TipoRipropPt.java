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
import it.csi.prodis.prodisweb.lib.dto.prospetto.PartTime;

/**
 * The Class TipoRipropPt.
 */
public class TipoRipropPt extends BaseDto<Long> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String ambitoRiprop;
	private String ambitoRipropBc;
	private String dsMin;
	private String dsTipoRipropPt;
	private Date dtFine;
	private Date dtInizio;
	private List<PartTime> partTimes;

	/**
	 * @return the ambitoRiprop
	 */
	public String getAmbitoRiprop() {
		return ambitoRiprop;
	}
	
	/**
	 * @param ambitoRiprop the ambitoRiprop to set
	 */
	public void setAmbitoRiprop(String ambitoRiprop) {
		this.ambitoRiprop = ambitoRiprop;
	}

	/**
	 * @return the ambitoRipropBc
	 */
	public String getAmbitoRipropBc() {
		return ambitoRipropBc;
	}
	
	/**
	 * @param ambitoRipropBc the ambitoRipropBc to set
	 */
	public void setAmbitoRipropBc(String ambitoRipropBc) {
		this.ambitoRipropBc = ambitoRipropBc;
	}

	/**
	 * @return the dsMin
	 */
	public String getDsMin() {
		return dsMin;
	}
	
	/**
	 * @param dsMin the dsMin to set
	 */
	public void setDsMin(String dsMin) {
		this.dsMin = dsMin;
	}

	/**
	 * @return the dsTipoRipropPt
	 */
	public String getDsTipoRipropPt() {
		return dsTipoRipropPt;
	}
	
	/**
	 * @param dsTipoRipropPt the dsTipoRipropPt to set
	 */
	public void setDsTipoRipropPt(String dsTipoRipropPt) {
		this.dsTipoRipropPt = dsTipoRipropPt;
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
	 * @return the partTimes
	 */
	public List<PartTime> getPartTimes() {
		return partTimes;
	}
	
	/**
	 * @param partTimes the partTimes to set
	 */
	public void setPartTimes(List<PartTime> partTimes) {
		this.partTimes = partTimes;
	}

}
