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
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.csi.prodis.prodisweb.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the PRO_T_DICHIARANTE database table.
 * 
 */
@Entity
@Table(name="PRO_T_DICHIARANTE")
@NamedQuery(name="ProTDichiarante.findAll", query="SELECT p FROM ProTDichiarante p")
public class ProTDichiarante implements Serializable, BaseEntity<Long> {

	@Override
	public Long getId() {
		return idTDichiarante;
	}

	@Override
	public void setId(Long id) {
		idTDichiarante = id;
	}


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_T_DICHIARANTE")
	private long idTDichiarante;

	@Column(name="COD_DICHIARANTE")
	private String codDichiarante;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_FINE")
	private Date dataFine;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_INIZIO")
	private Date dataInizio;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_TMST")
	private Date dataTmst;

	@Column(name="DESC_DICHIARANTE")
	private String descDichiarante;

	//bi-directional many-to-one association to ProDDatiAzienda
	@OneToMany(mappedBy="proTDichiarante")
	private List<ProDDatiAzienda> proDDatiAziendas;

	public ProTDichiarante() {
	}

	public long getIdTDichiarante() {
		return this.idTDichiarante;
	}

	public void setIdTDichiarante(long idTDichiarante) {
		this.idTDichiarante = idTDichiarante;
	}

	public String getCodDichiarante() {
		return this.codDichiarante;
	}

	public void setCodDichiarante(String codDichiarante) {
		this.codDichiarante = codDichiarante;
	}

	public Date getDataFine() {
		return this.dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public Date getDataInizio() {
		return this.dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataTmst() {
		return this.dataTmst;
	}

	public void setDataTmst(Date dataTmst) {
		this.dataTmst = dataTmst;
	}

	public String getDescDichiarante() {
		return this.descDichiarante;
	}

	public void setDescDichiarante(String descDichiarante) {
		this.descDichiarante = descDichiarante;
	}

	public List<ProDDatiAzienda> getProDDatiAziendas() {
		return this.proDDatiAziendas;
	}

	public void setProDDatiAziendas(List<ProDDatiAzienda> proDDatiAziendas) {
		this.proDDatiAziendas = proDDatiAziendas;
	}

	public ProDDatiAzienda addProDDatiAzienda(ProDDatiAzienda proDDatiAzienda) {
		getProDDatiAziendas().add(proDDatiAzienda);
		proDDatiAzienda.setProTDichiarante(this);

		return proDDatiAzienda;
	}

	public ProDDatiAzienda removeProDDatiAzienda(ProDDatiAzienda proDDatiAzienda) {
		getProDDatiAziendas().remove(proDDatiAzienda);
		proDDatiAzienda.setProTDichiarante(null);

		return proDDatiAzienda;
	}

}
