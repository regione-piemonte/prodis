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
package it.csi.prodis.prodissrv.ejb.business.be.service.request.prospetto;

import java.util.List;

import it.csi.prodis.prodissrv.ejb.business.be.service.request.base.BaseRequest;
import it.csi.prodis.prodissrv.ejb.entity.ProDImportErrori;
import it.csi.prodis.prodissrv.lib.dto.prospetto.Prospetto;

public class InserisciProspettoRequest implements BaseRequest {
	
	private List<ProDImportErrori> errors;
	private Prospetto prospetto;
	private String tipoComunicazione;
	
	public InserisciProspettoRequest(Prospetto prospetto, List<ProDImportErrori> errors, String tipoComunicazione) {
		super();
		this.errors = errors;
		this.prospetto = prospetto;
		this.tipoComunicazione = tipoComunicazione;
	}
	public List<ProDImportErrori> getErrors() {
		return errors;
	}
	public void setErrors(List<ProDImportErrori> errors) {
		this.errors = errors;
	}
	public Prospetto getProspetto() {
		return prospetto;
	}
	public void setProspetto(Prospetto prospetto) {
		this.prospetto = prospetto;
	}
	public String getTipoComunicazione() {
		return tipoComunicazione;
	}
	public void setTipoComunicazione(String tipoComunicazione) {
		this.tipoComunicazione = tipoComunicazione;
	}

}
