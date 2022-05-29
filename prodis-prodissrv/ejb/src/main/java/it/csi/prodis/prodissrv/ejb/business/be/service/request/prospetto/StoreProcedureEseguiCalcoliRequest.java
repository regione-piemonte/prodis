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

import it.csi.prodis.prodissrv.ejb.business.be.service.request.base.BaseRequest;

public class StoreProcedureEseguiCalcoliRequest implements BaseRequest {

	private Long idProspetto;
	private String cfUtenteAggiornamento;
	private boolean soloScoperture;

	public StoreProcedureEseguiCalcoliRequest(Long idProspetto, String cfUtenteAggiornamento, boolean soloScoperture) {
		this.setIdProspetto(idProspetto);
		this.setCfUtenteAggiornamento(cfUtenteAggiornamento);
		this.setSoloScoperture(soloScoperture);
	}

	public Long getIdProspetto() {
		return idProspetto;
	}

	public void setIdProspetto(Long idProspetto) {
		this.idProspetto = idProspetto;
	}

	public String getCfUtenteAggiornamento() {
		return cfUtenteAggiornamento;
	}

	public void setCfUtenteAggiornamento(String cfUtenteAggiornamento) {
		this.cfUtenteAggiornamento = cfUtenteAggiornamento;
	}

	public boolean isSoloScoperture() {
		return soloScoperture;
	}

	public void setSoloScoperture(boolean soloScoperture) {
		this.soloScoperture = soloScoperture;
	}

}
