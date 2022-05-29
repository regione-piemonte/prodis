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
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import it.csi.prodis.prodissrv.lib.dto.BaseDto;
import it.csi.prodis.prodissrv.lib.dto.prospetto.LavoratoriInForza;
import it.csi.prodis.prodissrv.lib.dto.prospetto.LavoratoriSilp;
import it.csi.prodis.prodissrv.lib.dto.prospetto.PostiLavoroDisp;

/**
 * The Class Istat2001livello5.
 */
public class Istat2001livello5 extends BaseDto<Long> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String codIstat2001livello5Min;
	private String dsComIstat2001livello5;
	private Date dtFine;
	private Date dtInizio;
	private Date dtTmst;
	private String flgVldUd;
	private BigDecimal idNewIstat;
	private List<LavoratoriInForza> lavoratoriInForzas;
	private List<LavoratoriSilp> lavoratoriSilps;
	private List<PostiLavoroDisp> postiLavoroDisps;

	/**
	 * @return the codIstat2001livello5Min
	 */
	public String getCodIstat2001livello5Min() {
		return codIstat2001livello5Min;
	}
	
	/**
	 * @param codIstat2001livello5Min the codIstat2001livello5Min to set
	 */
	public void setCodIstat2001livello5Min(String codIstat2001livello5Min) {
		this.codIstat2001livello5Min = codIstat2001livello5Min;
	}

	/**
	 * @return the dsComIstat2001livello5
	 */
	public String getDsComIstat2001livello5() {
		return dsComIstat2001livello5;
	}
	
	/**
	 * @param dsComIstat2001livello5 the dsComIstat2001livello5 to set
	 */
	public void setDsComIstat2001livello5(String dsComIstat2001livello5) {
		this.dsComIstat2001livello5 = dsComIstat2001livello5;
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
	 * @return the flgVldUd
	 */
	public String getFlgVldUd() {
		return flgVldUd;
	}
	
	/**
	 * @param flgVldUd the flgVldUd to set
	 */
	public void setFlgVldUd(String flgVldUd) {
		this.flgVldUd = flgVldUd;
	}

	/**
	 * @return the idNewIstat
	 */
	public BigDecimal getIdNewIstat() {
		return idNewIstat;
	}
	
	/**
	 * @param idNewIstat the idNewIstat to set
	 */
	public void setIdNewIstat(BigDecimal idNewIstat) {
		this.idNewIstat = idNewIstat;
	}

	/**
	 * @return the lavoratoriInForzas
	 */
	public List<LavoratoriInForza> getLavoratoriInForzas() {
		return lavoratoriInForzas;
	}
	
	/**
	 * @param lavoratoriInForzas the lavoratoriInForzas to set
	 */
	public void setLavoratoriInForzas(List<LavoratoriInForza> lavoratoriInForzas) {
		this.lavoratoriInForzas = lavoratoriInForzas;
	}

	/**
	 * @return the lavoratoriSilps
	 */
	public List<LavoratoriSilp> getLavoratoriSilps() {
		return lavoratoriSilps;
	}
	
	/**
	 * @param lavoratoriSilps the lavoratoriSilps to set
	 */
	public void setLavoratoriSilps(List<LavoratoriSilp> lavoratoriSilps) {
		this.lavoratoriSilps = lavoratoriSilps;
	}

	/**
	 * @return the postiLavoroDisps
	 */
	public List<PostiLavoroDisp> getPostiLavoroDisps() {
		return postiLavoroDisps;
	}
	
	/**
	 * @param postiLavoroDisps the postiLavoroDisps to set
	 */
	public void setPostiLavoroDisps(List<PostiLavoroDisp> postiLavoroDisps) {
		this.postiLavoroDisps = postiLavoroDisps;
	}

}
