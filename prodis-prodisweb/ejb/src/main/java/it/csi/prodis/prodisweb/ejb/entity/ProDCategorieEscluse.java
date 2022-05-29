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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import it.csi.prodis.prodisweb.ejb.entity.base.BaseAuditedEntity;


/**
 * The persistent class for the PRO_D_CATEGORIE_ESCLUSE database table.
 * 
 */
@Entity
@Table(name="PRO_D_CATEGORIE_ESCLUSE")
@NamedQuery(name="ProDCategorieEscluse.findAll", query="SELECT p FROM ProDCategorieEscluse p")
@SequenceGenerator(name = "CAT_SEQUENCE", sequenceName = "SEQ_D_CATEGORIE_ESCLUSE", initialValue = 1, allocationSize = 1)
public class ProDCategorieEscluse extends BaseAuditedEntity<Long> implements Serializable {

	@Override
	public Long getId() {
		return idCategorieEscluse;
	}

	@Override
	public void setId(Long id) {
		idCategorieEscluse = id;
	}


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_CATEGORIE_ESCLUSE", unique=true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CAT_SEQUENCE")
	private long idCategorieEscluse;

	@Column(name="N_LAV_APPARTART_CATEGORIA")
	private BigDecimal nLavAppartartCategoria;

	@Column(name="ID_PROSPETTO_PROV")
	private BigDecimal idProspettoProv;

	//bi-directional many-to-one association to ProTCategoriaEscluse
	@ManyToOne
	@JoinColumn(name="ID_T_CATEGORIE_ESCLUSE")
	private ProTCategoriaEscluse proTCategoriaEscluse;

	public BigDecimal getIdProspettoProv() {
		return idProspettoProv;
	}

	public void setIdProspettoProv(BigDecimal idProspettoProv) {
		this.idProspettoProv = idProspettoProv;
	}

	public ProDCategorieEscluse() {
	}

	public long getIdCategorieEscluse() {
		return this.idCategorieEscluse;
	}

	public void setIdCategorieEscluse(long idCategorieEscluse) {
		this.idCategorieEscluse = idCategorieEscluse;
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

	public BigDecimal getnLavAppartartCategoria() {
		return this.nLavAppartartCategoria;
	}

	public void setnLavAppartartCategoria(BigDecimal nLavAppartartCategoria) {
		this.nLavAppartartCategoria = nLavAppartartCategoria;
	}

	public ProTCategoriaEscluse getProTCategoriaEscluse() {
		return this.proTCategoriaEscluse;
	}

	public void setProTCategoriaEscluse(ProTCategoriaEscluse proTCategoriaEscluse) {
		this.proTCategoriaEscluse = proTCategoriaEscluse;
	}

}
