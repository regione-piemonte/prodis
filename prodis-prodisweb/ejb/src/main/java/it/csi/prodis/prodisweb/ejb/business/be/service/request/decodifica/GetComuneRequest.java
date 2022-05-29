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
package it.csi.prodis.prodisweb.ejb.business.be.service.request.decodifica;

import java.util.Date;

import it.csi.prodis.prodisweb.ejb.business.be.service.request.base.BaseRequest;

/**
 * The Class GetComuneRequest.
 */
public class GetComuneRequest implements BaseRequest {

	private Long idProvincia;
	private String codComuneMin;
	private String dsProTComune;
	private Date data;

	/**
	 * Constructor
	 * 
	 * @param idProvincia the idProvincia
	 */
	public GetComuneRequest(Long idProvincia, String codComuneMin, String dsProTComune, Date data) {
		this.idProvincia = idProvincia;
		this.codComuneMin = codComuneMin;
		this.dsProTComune = dsProTComune;
		this.data = data;
	}

	/**
	 * @return the idProvincia
	 */
	public Long getIdProvincia() {
		return idProvincia;
	}

	/**
	 * @return the codComuneMin
	 */
	public String getCodComuneMin() {
		return codComuneMin;
	}

	/**
	 * @param codComuneMin the codComuneMin to set
	 */
	public void setCodComuneMin(String codComuneMin) {
		this.codComuneMin = codComuneMin;
	}

	/**
	 * @return the dsProTComune
	 */
	public String getDsProTComune() {
		return dsProTComune;
	}

	/**
	 * @param dsProTComune the dsProTComune to set
	 */
	public void setDsProTComune(String dsProTComune) {
		this.dsProTComune = dsProTComune;
	}

	/**
	 * @return the data
	 */
	public Date getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Date data) {
		this.data = data;
	}

	/**
	 * @param idProvincia the idProvincia to set
	 */
	public void setIdProvincia(Long idProvincia) {
		this.idProvincia = idProvincia;
	}

}
