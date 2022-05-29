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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.csi.prodis.prodissrv.ejb.entity.base.BaseAuditedEntity;


/**
 * The persistent class for the PRO_D_PART_TIME database table.
 * 
 */
@Entity
@Table(name="PRO_D_PART_TIME")
@NamedQuery(name="ProDPartTime.findAll", query="SELECT p FROM ProDPartTime p")
@SequenceGenerator(name = "PART_TIME_SEQUENCE", sequenceName = "SEQ_D_PART_TIME", initialValue = 1, allocationSize = 1)
public class ProDPartTime extends BaseAuditedEntity<Long> implements Serializable {

	@Override
	public Long getId() {
		return idPartTime;
	}

	@Override
	public void setId(Long id) {
		idPartTime = id;
	}


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_PART_TIME", unique=true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PART_TIME_SEQUENCE")
	private long idPartTime;

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

	@Column(name="N_PART_TIME")
	private BigDecimal nPartTime;

	@Column(name="ORARIO_SETT_CONTRATTUALE_MIN")
	private BigDecimal orarioSettContrattualeMin;

	@Column(name="ORARIO_SETT_PART_TIME_MIN")
	private BigDecimal orarioSettPartTimeMin;

	@Column(name="ID_PROSPETTO_PROV")
	private Long idProspettoProv;

	//bi-directional many-to-one association to ProTTipoRipropPt
	@ManyToOne
	@JoinColumn(name="ID_TIPO_RIPROP_PT")
	private ProTTipoRipropPt proTTipoRipropPt;

	public ProDPartTime() {
	}

	public long getIdPartTime() {
		return this.idPartTime;
	}

	public void setIdPartTime(long idPartTime) {
		this.idPartTime = idPartTime;
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

	public BigDecimal getnPartTime() {
		return this.nPartTime;
	}

	public void setnPartTime(BigDecimal nPartTime) {
		this.nPartTime = nPartTime;
	}

	public BigDecimal getOrarioSettContrattualeMin() {
		return this.orarioSettContrattualeMin;
	}

	public void setOrarioSettContrattualeMin(BigDecimal orarioSettContrattualeMin) {
		this.orarioSettContrattualeMin = orarioSettContrattualeMin;
	}

	public BigDecimal getOrarioSettPartTimeMin() {
		return this.orarioSettPartTimeMin;
	}

	public void setOrarioSettPartTimeMin(BigDecimal orarioSettPartTimeMin) {
		this.orarioSettPartTimeMin = orarioSettPartTimeMin;
	}



	public Long getIdProspettoProv() {
		return idProspettoProv;
	}

	public void setIdProspettoProv(Long idProspettoProv) {
		this.idProspettoProv = idProspettoProv;
	}

	public ProTTipoRipropPt getProTTipoRipropPt() {
		return this.proTTipoRipropPt;
	}

	public void setProTTipoRipropPt(ProTTipoRipropPt proTTipoRipropPt) {
		this.proTTipoRipropPt = proTTipoRipropPt;
	}

}
