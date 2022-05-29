/*-
 * ========================LICENSE_START=================================
 * PRODIS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodisweb.lib.dto;

import java.util.Date;

/**
 * Base model class
 * 
 * @param <K> the key type
 */
public abstract class BaseAuditedDto<K> extends BaseDto<K> {

	protected Date dInserim;
	protected Date dAggiorn;
	protected String codUserInserim;
	protected String codUserAggiorn;

	/** Base JavaBean contructor */
	protected BaseAuditedDto() {
		super(null);
	}

	/**
	 * Constructor
	 * 
	 * @param id the id
	 */
	protected BaseAuditedDto(K id) {
		super(id);
	}

	protected String innerToString() {
		return new StringBuilder().append(", id=").append(id).toString();
	}

	/**
	 * @return the codUserAggiorn
	 */
	public String getCodUserAggiorn() {
		return codUserAggiorn;
	}

	/**
	 * @param codUserAggiorn the codUserAggiorn to set
	 */
	public void setCodUserAggiorn(String codUserAggiorn) {
		this.codUserAggiorn = codUserAggiorn;
	}

	/**
	 * @return the codUserInserim
	 */
	public String getCodUserInserim() {
		return codUserInserim;
	}

	/**
	 * @param codUserInserim the codUserInserim to set
	 */
	public void setCodUserInserim(String codUserInserim) {
		this.codUserInserim = codUserInserim;
	}

	/**
	 * @return the dAggiorn
	 */
	public Date getdAggiorn() {
		return dAggiorn;
	}

	/**
	 * @param dAggiorn the dAggiorn to set
	 */
	public void setdAggiorn(Date dAggiorn) {
		this.dAggiorn = dAggiorn;
	}

	/**
	 * @return the dInserim
	 */
	public Date getdInserim() {
		return dInserim;
	}

	/**
	 * @param dInserim the dInserim to set
	 */
	public void setdInserim(Date dInserim) {
		this.dInserim = dInserim;
	}

}
