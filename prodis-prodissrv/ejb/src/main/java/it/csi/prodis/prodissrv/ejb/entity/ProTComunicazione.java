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

import it.csi.prodis.prodissrv.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the PRO_T_COMUNICAZIONE database table.
 * 
 */
@Entity
@Table(name="PRO_T_COMUNICAZIONE")
@NamedQuery(name="ProTComunicazione.findAll", query="SELECT p FROM ProTComunicazione p")
public class ProTComunicazione implements Serializable, BaseEntity<Long> {

	@Override
	public Long getId() {
		return idTComunicazione;
	}

	@Override
	public void setId(Long id) {
		idTComunicazione = id;
	}


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_T_COMUNICAZIONE")
	private long idTComunicazione;

	@Column(name="COD_COMUNICAZIONE")
	private String codComunicazione;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_FINE")
	private Date dataFine;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_INIZIO")
	private Date dataInizio;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_TMST")
	private Date dataTmst;

	@Column(name="DESC_COMUNICAZIONE")
	private String descComunicazione;

	//bi-directional many-to-one association to ProDProspetto
	@OneToMany(mappedBy="proTComunicazione")
	private List<ProDProspetto> proDProspettos;

	public ProTComunicazione() {
	}

	public long getIdTComunicazione() {
		return this.idTComunicazione;
	}

	public void setIdTComunicazione(long idTComunicazione) {
		this.idTComunicazione = idTComunicazione;
	}

	public String getCodComunicazione() {
		return this.codComunicazione;
	}

	public void setCodComunicazione(String codComunicazione) {
		this.codComunicazione = codComunicazione;
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

	public String getDescComunicazione() {
		return this.descComunicazione;
	}

	public void setDescComunicazione(String descComunicazione) {
		this.descComunicazione = descComunicazione;
	}

	public List<ProDProspetto> getProDProspettos() {
		return this.proDProspettos;
	}

	public void setProDProspettos(List<ProDProspetto> proDProspettos) {
		this.proDProspettos = proDProspettos;
	}

	public ProDProspetto addProDProspetto(ProDProspetto proDProspetto) {
		getProDProspettos().add(proDProspetto);
		proDProspetto.setProTComunicazione(this);

		return proDProspetto;
	}

	public ProDProspetto removeProDProspetto(ProDProspetto proDProspetto) {
		getProDProspettos().remove(proDProspetto);
		proDProspetto.setProTComunicazione(null);

		return proDProspetto;
	}

}
