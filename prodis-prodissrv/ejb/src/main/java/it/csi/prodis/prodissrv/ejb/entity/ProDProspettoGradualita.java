/*-
 * ========================LICENSE_START=================================
 * PRODIS Servizi - EJB submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodissrv.ejb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.csi.prodis.prodissrv.ejb.entity.base.BaseAuditedEntity;


/**
 * The persistent class for the PRO_D_PROSPETTO_GRADUALITA database table.
 * 
 */
@Entity
@Table(name="PRO_D_PROSPETTO_GRADUALITA")
@NamedQuery(name="ProDProspettoGradualita.findAll", query="SELECT p FROM ProDProspettoGradualita p")
public class ProDProspettoGradualita extends BaseAuditedEntity<Long> implements Serializable {

	@Override
	public Long getId() {
		return idProspetto;
	}

	@Override
	public void setId(Long id) {
		idProspetto = id;
	}


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_PROSPETTO")
	private long idProspetto;

//	@Column(name="COD_USER_AGGIORN")
//	private String codUserAggiorn;
//
//	@Column(name="COD_USER_INSERIM")
//	private String codUserInserim;
//
//	@Temporal(TemporalType.DATE)
//	@Column(name="D_AGGIORN")
//	private Date dAggiorn;
//
//	@Temporal(TemporalType.DATE)
//	@Column(name="D_INSERIM")
//	private Date dInserim;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_ATTO")
	private Date dataAtto;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_TRASFORMAZIONE")
	private Date dataTrasformazione;

	@Column(name="ESTREMI_ATTO")
	private String estremiAtto;

	@Column(name="N_ASSUNZIONI_LAV_PRE_TRASF")
	private BigDecimal nAssunzioniLavPreTrasf;

	private BigDecimal percentuale;

	//bi-directional one-to-one association to ProDProspetto
	@OneToOne
	@JoinColumn(name="ID_PROSPETTO")
	private ProDProspetto proDProspetto;

	public ProDProspettoGradualita() {
	}

	public long getIdProspetto() {
		return this.idProspetto;
	}

	public void setIdProspetto(long idProspetto) {
		this.idProspetto = idProspetto;
	}

//	public String getCodUserAggiorn() {
//		return this.codUserAggiorn;
//	}
//
//	public void setCodUserAggiorn(String codUserAggiorn) {
//		this.codUserAggiorn = codUserAggiorn;
//	}
//
//	public String getCodUserInserim() {
//		return this.codUserInserim;
//	}
//
//	public void setCodUserInserim(String codUserInserim) {
//		this.codUserInserim = codUserInserim;
//	}
//
//	public Date getDAggiorn() {
//		return this.dAggiorn;
//	}
//
//	public void setDAggiorn(Date dAggiorn) {
//		this.dAggiorn = dAggiorn;
//	}
//
//	public Date getDInserim() {
//		return this.dInserim;
//	}
//
//	public void setDInserim(Date dInserim) {
//		this.dInserim = dInserim;
//	}

	public Date getDataAtto() {
		return this.dataAtto;
	}

	public void setDataAtto(Date dataAtto) {
		this.dataAtto = dataAtto;
	}

	public Date getDataTrasformazione() {
		return this.dataTrasformazione;
	}

	public void setDataTrasformazione(Date dataTrasformazione) {
		this.dataTrasformazione = dataTrasformazione;
	}

	public String getEstremiAtto() {
		return this.estremiAtto;
	}

	public void setEstremiAtto(String estremiAtto) {
		this.estremiAtto = estremiAtto;
	}

	public BigDecimal getnAssunzioniLavPreTrasf() {
		return this.nAssunzioniLavPreTrasf;
	}

	public void setnAssunzioniLavPreTrasf(BigDecimal nAssunzioniLavPreTrasf) {
		this.nAssunzioniLavPreTrasf = nAssunzioniLavPreTrasf;
	}

	public BigDecimal getPercentuale() {
		return this.percentuale;
	}

	public void setPercentuale(BigDecimal percentuale) {
		this.percentuale = percentuale;
	}

	public ProDProspetto getProDProspetto() {
		return this.proDProspetto;
	}

	public void setProDProspetto(ProDProspetto proDProspetto) {
		this.proDProspetto = proDProspetto;
	}

}
