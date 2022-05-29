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
 * The persistent class for the PRO_T_CATEGORIA_AZIENDA database table.
 * 
 */
@Entity
@Table(name="PRO_T_CATEGORIA_AZIENDA")
@NamedQuery(name="ProTCategoriaAzienda.findAll", query="SELECT p FROM ProTCategoriaAzienda p")
public class ProTCategoriaAzienda implements Serializable, BaseEntity<Long> {

	@Override
	public Long getId() {
		return idTCategoriaAzienda;
	}

	@Override
	public void setId(Long id) {
		idTCategoriaAzienda = id;
	}


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_T_CATEGORIA_AZIENDA")
	private long idTCategoriaAzienda;

	@Column(name="COD_CATEGORIA_AZIENDA")
	private String codCategoriaAzienda;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_FINE")
	private Date dataFine;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_INIZIO")
	private Date dataInizio;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_TMST")
	private Date dataTmst;

	@Column(name="DES_CATEGORIA_AZIENDA")
	private String desCategoriaAzienda;

	//bi-directional many-to-one association to ProDProspetto
	@OneToMany(mappedBy="proTCategoriaAzienda")
	private List<ProDProspetto> proDProspettos;

	public ProTCategoriaAzienda() {
	}

	public long getIdTCategoriaAzienda() {
		return this.idTCategoriaAzienda;
	}

	public void setIdTCategoriaAzienda(long idTCategoriaAzienda) {
		this.idTCategoriaAzienda = idTCategoriaAzienda;
	}

	public String getCodCategoriaAzienda() {
		return this.codCategoriaAzienda;
	}

	public void setCodCategoriaAzienda(String codCategoriaAzienda) {
		this.codCategoriaAzienda = codCategoriaAzienda;
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

	public String getDesCategoriaAzienda() {
		return this.desCategoriaAzienda;
	}

	public void setDesCategoriaAzienda(String desCategoriaAzienda) {
		this.desCategoriaAzienda = desCategoriaAzienda;
	}

	public List<ProDProspetto> getProDProspettos() {
		return this.proDProspettos;
	}

	public void setProDProspettos(List<ProDProspetto> proDProspettos) {
		this.proDProspettos = proDProspettos;
	}

	public ProDProspetto addProDProspetto(ProDProspetto proDProspetto) {
		getProDProspettos().add(proDProspetto);
		proDProspetto.setProTCategoriaAzienda(this);

		return proDProspetto;
	}

	public ProDProspetto removeProDProspetto(ProDProspetto proDProspetto) {
		getProDProspettos().remove(proDProspetto);
		proDProspetto.setProTCategoriaAzienda(null);

		return proDProspetto;
	}

}
