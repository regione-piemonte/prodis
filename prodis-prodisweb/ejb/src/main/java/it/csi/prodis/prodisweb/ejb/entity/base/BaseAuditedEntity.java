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
package it.csi.prodis.prodisweb.ejb.entity.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Audited entity base implementation
 * 
 * @param <K> the key type
 */
@MappedSuperclass
public abstract class BaseAuditedEntity<K> implements BaseEntity<K> {

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "D_INSERIM")
	private Date dInserim;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "D_AGGIORN")
	private Date dAggiorn;

	@Column(name = "COD_USER_AGGIORN")
	private String codUserAggiorn;

	@Column(name = "COD_USER_INSERIM")
	private String codUserInserim;

	public Date getdInserim() {
		return dInserim;
	}

	public void setdInserim(Date dInserim) {
		this.dInserim = dInserim;
	}

	public Date getdAggiorn() {
		return dAggiorn;
	}

	public void setdAggiorn(Date dAggiorn) {
		this.dAggiorn = dAggiorn;
	}

	public String getCodUserAggiorn() {
		return codUserAggiorn;
	}

	public void setCodUserAggiorn(String codUserAggiorn) {
		this.codUserAggiorn = codUserAggiorn;
	}

	public String getCodUserInserim() {
		return codUserInserim;
	}

	public void setCodUserInserim(String codUserInserim) {
		this.codUserInserim = codUserInserim;
	}

}
