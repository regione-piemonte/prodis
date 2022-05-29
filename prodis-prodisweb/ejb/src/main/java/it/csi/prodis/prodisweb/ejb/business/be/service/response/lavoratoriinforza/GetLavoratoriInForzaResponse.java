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
package it.csi.prodis.prodisweb.ejb.business.be.service.response.lavoratoriinforza;


import it.csi.prodis.prodisweb.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.prodis.prodisweb.lib.dto.prospetto.LavoratoriInForza;


public class GetLavoratoriInForzaResponse extends BaseGetResponse<LavoratoriInForza> {

	private LavoratoriInForza lavoratoriInForza;

	public LavoratoriInForza getLavoratoriInForza() {
		return lavoratoriInForza;
	}

	public void setLavoratoriInForza(LavoratoriInForza lavoratoriInForza) {
		this.lavoratoriInForza = lavoratoriInForza;
	}

	@Override
	protected LavoratoriInForza getEntity() {
		return lavoratoriInForza;
	}

}
