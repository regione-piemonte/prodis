/*-
 * ========================LICENSE_START=================================
 * PRODIS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodisweb.ejb.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.csi.prodis.prodisweb.ejb.entity.base.BaseAuditedEntity;


/**
 * The persistent class for the PRO_D_PROV_INTERMITTENTI database table.
 * 
 */
@Entity
@Table(name="PRO_D_PROV_INTERMITTENTI")
@NamedQuery(name="ProDProvIntermittenti.findAll", query="SELECT p FROM ProDProvIntermittenti p")
@SequenceGenerator(name = "INTER_SEQUENCE", sequenceName = "SEQ_D_PROV_INTERMITTENTI", initialValue = 1, allocationSize = 1)
public class ProDProvIntermittenti extends BaseAuditedEntity<Long> implements Serializable {

	@Override
	public Long getId() {
		return idIntermittenti;
	}

	@Override
	public void setId(Long id) {
		idIntermittenti = id;
	}


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_INTERMITTENTI", unique=true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INTER_SEQUENCE")
	private long idIntermittenti;

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

	@Column(name="N_INTERMITTENTI")
	private BigDecimal nIntermittenti;

	@Column(name="ORARIO_SETTIMANALE_CONTRATTUAL")
	private BigDecimal orarioSettimanaleContrattual;

	@Column(name="ORARIO_SETTIMANALE_SVOLTO")
	private BigDecimal orarioSettimanaleSvolto;

	//bi-directional many-to-one association to ProDDatiProvinciali
	//@ManyToOne
	//@JoinColumn(name="ID_PROSPETTO_PROV")
	@Column(name="ID_PROSPETTO_PROV")
	private BigDecimal idProspettoProv;

	public BigDecimal getIdProspettoProv() {
		return idProspettoProv;
	}

	public void setIdProspettoProv(BigDecimal idProspettoProv) {
		this.idProspettoProv = idProspettoProv;
	}

	public ProDProvIntermittenti() {
	}

	public long getIdIntermittenti() {
		return this.idIntermittenti;
	}

	public void setIdIntermittenti(long idIntermittenti) {
		this.idIntermittenti = idIntermittenti;
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
//	public Date getdAggiorn() {
//		return this.dAggiorn;
//	}
//
//	public void setdAggiorn(Date dAggiorn) {
//		this.dAggiorn = dAggiorn;
//	}
//
//	public Date getdInserim() {
//		return this.dInserim;
//	}
//
//	public void setdInserim(Date dInserim) {
//		this.dInserim = dInserim;
//	}

	public BigDecimal getnIntermittenti() {
		return this.nIntermittenti;
	}

	public void setnIntermittenti(BigDecimal nIntermittenti) {
		this.nIntermittenti = nIntermittenti;
	}

	public BigDecimal getOrarioSettimanaleContrattual() {
		return this.orarioSettimanaleContrattual;
	}

	public void setOrarioSettimanaleContrattual(BigDecimal orarioSettimanaleContrattual) {
		this.orarioSettimanaleContrattual = orarioSettimanaleContrattual;
	}

	public BigDecimal getOrarioSettimanaleSvolto() {
		return this.orarioSettimanaleSvolto;
	}

	public void setOrarioSettimanaleSvolto(BigDecimal orarioSettimanaleSvolto) {
		this.orarioSettimanaleSvolto = orarioSettimanaleSvolto;
	}

}
