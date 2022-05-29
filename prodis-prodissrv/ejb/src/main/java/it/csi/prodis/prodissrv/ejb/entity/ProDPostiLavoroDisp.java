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
 * The persistent class for the PRO_D_POSTI_LAVORO_DISP database table.
 * 
 */
@Entity
@Table(name = "PRO_D_POSTI_LAVORO_DISP")
@NamedQuery(name = "ProDPostiLavoroDisp.findAll", query = "SELECT p FROM ProDPostiLavoroDisp p")
@SequenceGenerator(name = "POSTI_SEQUENCE", sequenceName = "SEQ_D_POSTI_LAVORO_DISP", initialValue = 1, allocationSize = 1)
public class ProDPostiLavoroDisp extends BaseAuditedEntity<Long> implements Serializable {

	@Override
	public Long getId() {
		return idPostoLavoroDisp;
	}

	@Override
	public void setId(Long id) {
		idPostoLavoroDisp = id;
	}

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_POSTO_LAVORO_DISP", unique=true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POSTI_SEQUENCE")
	private long idPostoLavoroDisp;

	@Column(name = "CATEGORIA_ASSUNZIONE")
	private String categoriaAssunzione;

	@Column(name = "CATEGORIA_SOGGETTO")
	private String categoriaSoggetto;

//	@Column(name = "COD_USER_AGGIORN")
//	private String codUserAggiorn;
//
//	@Column(name = "COD_USER_INSERIM")
//	private String codUserInserim;
//
//	@Temporal(TemporalType.DATE)
//	@Column(name = "D_AGGIORN")
//	private Date dAggiorn;
//
//	@Temporal(TemporalType.DATE)
//	@Column(name = "D_INSERIM")
//	private Date dInserim;

	@Column(name = "DESC_CAPACITA_RICHIESTE_CONTR")
	private String descCapacitaRichiesteContr;

	@Column(name = "DESC_MANSIONE")
	private String descMansione;

	@Column(name = "FLG_PRESENZA_BARRIERE_ARCHITE")
	private String flgPresenzaBarriereArchite;

	@Column(name = "FLG_RAGGIUNGIB_MEZZI_PUBBLICI")
	private String flgRaggiungibMezziPubblici;

	@Column(name = "FLG_TURNI_NOTTURNI")
	private String flgTurniNotturni;

	@Column(name = "N_POSTI")
	private BigDecimal nPosti;

	@Column(name="ID_PROSPETTO_PROV")
	private Long idProspettoProv;

	// bi-directional many-to-one association to ProTComune
	@ManyToOne
	@JoinColumn(name = "ID_T_COMUNE_ASSUNZIONE")
	private ProTComune proTComune;

	// bi-directional many-to-one association to ProTIstat2001livello5
	@ManyToOne
	@JoinColumn(name = "ID_T_QUALIFICA_PROFESSI_ISTAT")
	private ProTIstat2001livello5 proTIstat2001livello5;

	// bi-directional many-to-one association to ProTStatiEsteri
	@ManyToOne
	@JoinColumn(name = "ID_T_STATO_ESTERO_ASSUNZIONE")
	private ProTStatiEsteri proTStatiEsteri;

	public ProDPostiLavoroDisp() {
	}

	public long getIdPostoLavoroDisp() {
		return this.idPostoLavoroDisp;
	}

	public void setIdPostoLavoroDisp(long idPostoLavoroDisp) {
		this.idPostoLavoroDisp = idPostoLavoroDisp;
	}

	public String getCategoriaAssunzione() {
		return this.categoriaAssunzione;
	}

	public void setCategoriaAssunzione(String categoriaAssunzione) {
		this.categoriaAssunzione = categoriaAssunzione;
	}

	public String getCategoriaSoggetto() {
		return this.categoriaSoggetto;
	}

	public void setCategoriaSoggetto(String categoriaSoggetto) {
		this.categoriaSoggetto = categoriaSoggetto;
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

	public String getDescCapacitaRichiesteContr() {
		return this.descCapacitaRichiesteContr;
	}

	public void setDescCapacitaRichiesteContr(String descCapacitaRichiesteContr) {
		this.descCapacitaRichiesteContr = descCapacitaRichiesteContr;
	}

	public String getDescMansione() {
		return this.descMansione;
	}

	public void setDescMansione(String descMansione) {
		this.descMansione = descMansione;
	}

	public String getFlgPresenzaBarriereArchite() {
		return this.flgPresenzaBarriereArchite;
	}

	public void setFlgPresenzaBarriereArchite(String flgPresenzaBarriereArchite) {
		this.flgPresenzaBarriereArchite = flgPresenzaBarriereArchite;
	}

	public String getFlgRaggiungibMezziPubblici() {
		return this.flgRaggiungibMezziPubblici;
	}

	public void setFlgRaggiungibMezziPubblici(String flgRaggiungibMezziPubblici) {
		this.flgRaggiungibMezziPubblici = flgRaggiungibMezziPubblici;
	}

	public String getFlgTurniNotturni() {
		return this.flgTurniNotturni;
	}

	public void setFlgTurniNotturni(String flgTurniNotturni) {
		this.flgTurniNotturni = flgTurniNotturni;
	}

	public BigDecimal getnPosti() {
		return this.nPosti;
	}

	public void setnPosti(BigDecimal nPosti) {
		this.nPosti = nPosti;
	}



	public Long getIdProspettoProv() {
		return idProspettoProv;
	}

	public void setIdProspettoProv(Long idProspettoProv) {
		this.idProspettoProv = idProspettoProv;
	}

	public ProTComune getProTComune() {
		return this.proTComune;
	}

	public void setProTComune(ProTComune proTComune) {
		this.proTComune = proTComune;
	}

	public ProTIstat2001livello5 getProTIstat2001livello5() {
		return this.proTIstat2001livello5;
	}

	public void setProTIstat2001livello5(ProTIstat2001livello5 proTIstat2001livello5) {
		this.proTIstat2001livello5 = proTIstat2001livello5;
	}

	public ProTStatiEsteri getProTStatiEsteri() {
		return this.proTStatiEsteri;
	}

	public void setProTStatiEsteri(ProTStatiEsteri proTStatiEsteri) {
		this.proTStatiEsteri = proTStatiEsteri;
	}

}
