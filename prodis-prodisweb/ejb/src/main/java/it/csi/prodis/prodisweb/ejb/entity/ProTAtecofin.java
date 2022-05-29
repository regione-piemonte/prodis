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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.csi.prodis.prodisweb.ejb.entity.base.BaseEntity;

/**
 * The persistent class for the PRO_T_ATECOFIN database table.
 * 
 */
@Entity
@Table(name = "PRO_T_ATECOFIN")
@NamedQuery(name = "ProTAtecofin.findAll", query = "SELECT p FROM ProTAtecofin p")
public class ProTAtecofin implements Serializable, BaseEntity<Long> {

	@Override
	public Long getId() {
		return idTAtecofin;
	}

	@Override
	public void setId(Long id) {
		idTAtecofin = id;
	}

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_T_ATECOFIN")
	private Long idTAtecofin;

	@Column(name = "COD_ATECOFIN_MIN")
	private String codAtecofinMin;

	@Column(name = "DS_PRO_T_ATECOFIN")
	private String dsProTAtecofin;

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_FINE")
	private Date dtFine;

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_INIZIO")
	private Date dtInizio;

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_TMST")
	private Date dtTmst;

	@Column(name = "ID_NEW_ATECOFIN")
	private BigDecimal idNewAtecofin;

	// bi-directional many-to-one association to ProDDatiAzienda
//	@OneToMany(mappedBy = "proTAtecofin")
//	private List<ProDDatiAzienda> proDDatiAziendas;

	public ProTAtecofin() {
	}

	public Long getIdTAtecofin() {
		return this.idTAtecofin;
	}

	public void setIdTAtecofin(Long idTAtecofin) {
		this.idTAtecofin = idTAtecofin;
	}

	public String getCodAtecofinMin() {
		return this.codAtecofinMin;
	}

	public void setCodAtecofinMin(String codAtecofinMin) {
		this.codAtecofinMin = codAtecofinMin;
	}

	public String getDsProTAtecofin() {
		return this.dsProTAtecofin;
	}

	public void setDsProTAtecofin(String dsProTAtecofin) {
		this.dsProTAtecofin = dsProTAtecofin;
	}

	public Date getDtFine() {
		return this.dtFine;
	}

	public void setDtFine(Date dtFine) {
		this.dtFine = dtFine;
	}

	public Date getDtInizio() {
		return this.dtInizio;
	}

	public void setDtInizio(Date dtInizio) {
		this.dtInizio = dtInizio;
	}

	public Date getDtTmst() {
		return this.dtTmst;
	}

	public void setDtTmst(Date dtTmst) {
		this.dtTmst = dtTmst;
	}

	public BigDecimal getIdNewAtecofin() {
		return this.idNewAtecofin;
	}

	public void setIdNewAtecofin(BigDecimal idNewAtecofin) {
		this.idNewAtecofin = idNewAtecofin;
	}

//	public List<ProDDatiAzienda> getProDDatiAziendas() {
//		return this.proDDatiAziendas;
//	}
//
//	public void setProDDatiAziendas(List<ProDDatiAzienda> proDDatiAziendas) {
//		this.proDDatiAziendas = proDDatiAziendas;
//	}

//	public ProDDatiAzienda addProDDatiAzienda(ProDDatiAzienda proDDatiAzienda) {
//		getProDDatiAziendas().add(proDDatiAzienda);
//		proDDatiAzienda.setProTAtecofin(this);
//
//		return proDDatiAzienda;
//	}
//
//	public ProDDatiAzienda removeProDDatiAzienda(ProDDatiAzienda proDDatiAzienda) {
//		getProDDatiAziendas().remove(proDDatiAzienda);
//		proDDatiAzienda.setProTAtecofin(null);
//
//		return proDDatiAzienda;
//	}

}
