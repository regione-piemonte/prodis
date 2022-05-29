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

import it.csi.prodis.prodissrv.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.prodis.prodissrv.ejb.entity.EsitoStoreProcedure;

public class StoreProcedureEseguiCalcoliResponse extends BaseGetResponse<EsitoStoreProcedure> {

	private EsitoStoreProcedure esito;

	@Override
	protected EsitoStoreProcedure getEntity() {
		return esito;
	}

	public EsitoStoreProcedure getEsito() {
		return esito;
	}

	public void setEsito(EsitoStoreProcedure esito) {
		this.esito = esito;
	}

}
