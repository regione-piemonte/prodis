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
package it.csi.prodis.prodisweb.ejb.business.be.service.response.datiprovinciali;


import it.csi.prodis.prodisweb.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.prodis.prodisweb.lib.dto.prospetto.DatiProvinciali;


/**
 * Response for reading Prospetto by its id.
 */
public class GetDatiProvincialiByIdProspettoProvResponse extends BaseGetResponse<DatiProvinciali> {

	private DatiProvinciali datiProvinciali;

	

	public DatiProvinciali getDatiProvinciali() {
		return datiProvinciali;
	}



	public void setDatiProvinciali(DatiProvinciali datiProvinciali) {
		this.datiProvinciali = datiProvinciali;
	}



	@Override
	protected DatiProvinciali getEntity() {
		return datiProvinciali;
	}

}
