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
 * The persistent class for the PRO_T_REGIONE database table.
 * 
 */
@Entity
@Table(name="PRO_T_REGIONE")
@NamedQuery(name="ProTRegione.findAll", query="SELECT p FROM ProTRegione p")
public class ProTRegione implements Serializable, BaseEntity<Long> {

	@Override
	public Long getId() {
		return idTRegione;
	}

	@Override
	public void setId(Long id) {
		idTRegione = id;
	}


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_T_REGIONE")
	private long idTRegione;

	@Column(name="COD_AMBITO_DIFFUSIONE")
	private String codAmbitoDiffusione;

	@Column(name="COD_MOBILITAGEOG")
	private String codMobilitageog;

	@Column(name="COD_REGIONE_MIN")
	private String codRegioneMin;

	@Column(name="DS_AMBITO_DIFFUSIONE")
	private String dsAmbitoDiffusione;

	@Column(name="DS_PRO_T_REGIONE")
	private String dsProTRegione;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_FINE")
	private Date dtFine;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_INIZIO")
	private Date dtInizio;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_TMST")
	private Date dtTmst;

	//bi-directional many-to-one association to ProDAssPubbliche
//	@OneToMany(mappedBy="proTRegione")
//	private List<ProDAssPubbliche> proDAssPubbliches;

	//bi-directional many-to-one association to ProTProvincia
	@OneToMany(mappedBy="proTRegione")
	private List<ProTProvincia> proTProvincias;

	public ProTRegione() {
	}

	public long getIdTRegione() {
		return this.idTRegione;
	}

	public void setIdTRegione(long idTRegione) {
		this.idTRegione = idTRegione;
	}

	public String getCodAmbitoDiffusione() {
		return this.codAmbitoDiffusione;
	}

	public void setCodAmbitoDiffusione(String codAmbitoDiffusione) {
		this.codAmbitoDiffusione = codAmbitoDiffusione;
	}

	public String getCodMobilitageog() {
		return this.codMobilitageog;
	}

	public void setCodMobilitageog(String codMobilitageog) {
		this.codMobilitageog = codMobilitageog;
	}

	public String getCodRegioneMin() {
		return this.codRegioneMin;
	}

	public void setCodRegioneMin(String codRegioneMin) {
		this.codRegioneMin = codRegioneMin;
	}

	public String getDsAmbitoDiffusione() {
		return this.dsAmbitoDiffusione;
	}

	public void setDsAmbitoDiffusione(String dsAmbitoDiffusione) {
		this.dsAmbitoDiffusione = dsAmbitoDiffusione;
	}

	public String getDsProTRegione() {
		return this.dsProTRegione;
	}

	public void setDsProTRegione(String dsProTRegione) {
		this.dsProTRegione = dsProTRegione;
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

//	public List<ProDAssPubbliche> getProDAssPubbliches() {
//		return this.proDAssPubbliches;
//	}
//
//	public void setProDAssPubbliches(List<ProDAssPubbliche> proDAssPubbliches) {
//		this.proDAssPubbliches = proDAssPubbliches;
//	}
//
//	public ProDAssPubbliche addProDAssPubblich(ProDAssPubbliche proDAssPubblich) {
//		getProDAssPubbliches().add(proDAssPubblich);
//		proDAssPubblich.setProTRegione(this);
//
//		return proDAssPubblich;
//	}
//
//	public ProDAssPubbliche removeProDAssPubblich(ProDAssPubbliche proDAssPubblich) {
//		getProDAssPubbliches().remove(proDAssPubblich);
//		proDAssPubblich.setProTRegione(null);
//
//		return proDAssPubblich;
//	}

	public List<ProTProvincia> getProTProvincias() {
		return this.proTProvincias;
	}

	public void setProTProvincias(List<ProTProvincia> proTProvincias) {
		this.proTProvincias = proTProvincias;
	}

	public ProTProvincia addProTProvincia(ProTProvincia proTProvincia) {
		getProTProvincias().add(proTProvincia);
		proTProvincia.setProTRegione(this);

		return proTProvincia;
	}

	public ProTProvincia removeProTProvincia(ProTProvincia proTProvincia) {
		getProTProvincias().remove(proTProvincia);
		proTProvincia.setProTRegione(null);

		return proTProvincia;
	}

}
