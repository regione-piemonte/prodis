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
 * The Class Atecofin.
 */
public class Atecofin extends BaseDto<Long> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String codAtecofinMin;
	private String dsProTAtecofin;
	private Date dtFine;
	private Date dtInizio;
	private Date dtTmst;
	private BigDecimal idNewAtecofin;
//	private List<DatiAzienda> datiAziendas;

	/**
	 * @return the codAtecofinMin
	 */
	public String getCodAtecofinMin() {
		return codAtecofinMin;
	}
	
	/**
	 * @param codAtecofinMin the codAtecofinMin to set
	 */
	public void setCodAtecofinMin(String codAtecofinMin) {
		this.codAtecofinMin = codAtecofinMin;
	}

	/**
	 * @return the dsProTAtecofin
	 */
	public String getDsProTAtecofin() {
		return dsProTAtecofin;
	}
	
	/**
	 * @param dsProTAtecofin the dsProTAtecofin to set
	 */
	public void setDsProTAtecofin(String dsProTAtecofin) {
		this.dsProTAtecofin = dsProTAtecofin;
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
	 * @return the idNewAtecofin
	 */
	public BigDecimal getIdNewAtecofin() {
		return idNewAtecofin;
	}
	
	/**
	 * @param idNewAtecofin the idNewAtecofin to set
	 */
	public void setIdNewAtecofin(BigDecimal idNewAtecofin) {
		this.idNewAtecofin = idNewAtecofin;
	}

	/**
	 * @return the datiAziendas
	 */
//	public List<DatiAzienda> getDatiAziendas() {
//		return datiAziendas;
//	}
//	
//	/**
//	 * @param datiAziendas the datiAziendas to set
//	 */
//	public void setDatiAziendas(List<DatiAzienda> datiAziendas) {
//		this.datiAziendas = datiAziendas;
//	}

}
