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
package it.csi.prodis.prodissrv.ejb.business.be.service.response.prospetto;

import java.util.List;

import it.csi.prodis.prodissrv.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.prodis.prodissrv.ejb.entity.ProDImportErrori;
import it.csi.prodis.prodissrv.lib.dto.prospetto.Prospetto;

public class RiceviProspettoDaSpicomResponse extends BaseGetResponse<Prospetto> {
	
	private List<ProDImportErrori> errors;
	private String tipoComunicazione;

	private Prospetto prospetto;

	public Prospetto getProspetto() {
		return prospetto;
	}

	public void setProspetto(Prospetto prospetto) {
		this.prospetto = prospetto;
	}

	@Override
	protected Prospetto getEntity() {
		return prospetto;
	}

	public List<ProDImportErrori> getErrors() {
		return errors;
	}

	public void setErrors(List<ProDImportErrori> errors) {
		this.errors = errors;
	}

	public String getTipoComunicazione() {
		return tipoComunicazione;
	}

	public void setTipoComunicazione(String tipoComunicazione) {
		this.tipoComunicazione = tipoComunicazione;
	}

}
