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
import java.util.Date;

import it.csi.prodis.prodisweb.lib.dto.BaseAuditedDto;

/**
 * The Class ProspettoGradualita.
 */
public class ProspettoGradualita extends BaseAuditedDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Date dataAtto;
	private Date dataTrasformazione;
	private String estremiAtto;
	private BigDecimal nAssunzioniLavPreTrasf;
	private BigDecimal percentuale;
	private Prospetto prospetto;

	/**
	 * @param dInserim the dInserim to set
	 */
	public void setDInserim(Date dInserim) {
		this.dInserim = dInserim;
	}

	/**
	 * @return the dataAtto
	 */
	public Date getDataAtto() {
		return dataAtto;
	}
	
	/**
	 * @param dataAtto the dataAtto to set
	 */
	public void setDataAtto(Date dataAtto) {
		this.dataAtto = dataAtto;
	}

	/**
	 * @return the dataTrasformazione
	 */
	public Date getDataTrasformazione() {
		return dataTrasformazione;
	}
	
	/**
	 * @param dataTrasformazione the dataTrasformazione to set
	 */
	public void setDataTrasformazione(Date dataTrasformazione) {
		this.dataTrasformazione = dataTrasformazione;
	}

	/**
	 * @return the estremiAtto
	 */
	public String getEstremiAtto() {
		return estremiAtto;
	}
	
	/**
	 * @param estremiAtto the estremiAtto to set
	 */
	public void setEstremiAtto(String estremiAtto) {
		this.estremiAtto = estremiAtto;
	}

	/**
	 * @return the nAssunzioniLavPreTrasf
	 */
	public BigDecimal getnAssunzioniLavPreTrasf() {
		return nAssunzioniLavPreTrasf;
	}
	
	/**
	 * @param nAssunzioniLavPreTrasf the nAssunzioniLavPreTrasf to set
	 */
	public void setnAssunzioniLavPreTrasf(BigDecimal nAssunzioniLavPreTrasf) {
		this.nAssunzioniLavPreTrasf = nAssunzioniLavPreTrasf;
	}

	/**
	 * @return the percentuale
	 */
	public BigDecimal getPercentuale() {
		return percentuale;
	}
	
	/**
	 * @param percentuale the percentuale to set
	 */
	public void setPercentuale(BigDecimal percentuale) {
		this.percentuale = percentuale;
	}

	/**
	 * @return the prospetto
	 */
	public Prospetto getProspetto() {
		return prospetto;
	}
	
	/**
	 * @param prospetto the prospetto to set
	 */
	public void setProspetto(Prospetto prospetto) {
		this.prospetto = prospetto;
	}

}
