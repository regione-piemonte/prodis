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
 * The persistent class for the PRO_T_CATEGORIA_ESCLUSE database table.
 * 
 */
@Entity
@Table(name="PRO_T_CATEGORIA_ESCLUSE")
@NamedQuery(name="ProTCategoriaEscluse.findAll", query="SELECT p FROM ProTCategoriaEscluse p")
public class ProTCategoriaEscluse implements Serializable, BaseEntity<Long> {

	@Override
	public Long getId() {
		return idTCategoriaEscluse;
	}

	@Override
	public void setId(Long id) {
		idTCategoriaEscluse = id;
	}


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_T_CATEGORIA_ESCLUSE")
	private long idTCategoriaEscluse;

	@Column(name="AMBITO_CATEGORIA")
	private String ambitoCategoria;

	@Column(name="COD_CATEGORIA_ESCLUSE")
	private String codCategoriaEscluse;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_FINE")
	private Date dataFine;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_INIZIO")
	private Date dataInizio;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_TMST")
	private Date dataTmst;

	@Column(name="DES_CATEGORIA_ESCLUSE")
	private String desCategoriaEscluse;

	//bi-directional many-to-one association to ProDCategorieEscluse
	@OneToMany(mappedBy="proTCategoriaEscluse")
	private List<ProDCategorieEscluse> proDCategorieEscluses;

	public ProTCategoriaEscluse() {
	}

	public long getIdTCategoriaEscluse() {
		return this.idTCategoriaEscluse;
	}

	public void setIdTCategoriaEscluse(long idTCategoriaEscluse) {
		this.idTCategoriaEscluse = idTCategoriaEscluse;
	}

	public String getAmbitoCategoria() {
		return this.ambitoCategoria;
	}

	public void setAmbitoCategoria(String ambitoCategoria) {
		this.ambitoCategoria = ambitoCategoria;
	}

	public String getCodCategoriaEscluse() {
		return this.codCategoriaEscluse;
	}

	public void setCodCategoriaEscluse(String codCategoriaEscluse) {
		this.codCategoriaEscluse = codCategoriaEscluse;
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

	public String getDesCategoriaEscluse() {
		return this.desCategoriaEscluse;
	}

	public void setDesCategoriaEscluse(String desCategoriaEscluse) {
		this.desCategoriaEscluse = desCategoriaEscluse;
	}

	public List<ProDCategorieEscluse> getProDCategorieEscluses() {
		return this.proDCategorieEscluses;
	}

	public void setProDCategorieEscluses(List<ProDCategorieEscluse> proDCategorieEscluses) {
		this.proDCategorieEscluses = proDCategorieEscluses;
	}

	public ProDCategorieEscluse addProDCategorieEsclus(ProDCategorieEscluse proDCategorieEsclus) {
		getProDCategorieEscluses().add(proDCategorieEsclus);
		proDCategorieEsclus.setProTCategoriaEscluse(this);

		return proDCategorieEsclus;
	}

	public ProDCategorieEscluse removeProDCategorieEsclus(ProDCategorieEscluse proDCategorieEsclus) {
		getProDCategorieEscluses().remove(proDCategorieEsclus);
		proDCategorieEsclus.setProTCategoriaEscluse(null);

		return proDCategorieEsclus;
	}

}
