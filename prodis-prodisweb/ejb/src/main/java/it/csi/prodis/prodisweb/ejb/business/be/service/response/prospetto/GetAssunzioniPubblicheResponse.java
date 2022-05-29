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
package it.csi.prodis.prodisweb.ejb.business.be.service.response.prospetto;

import java.util.List;

import it.csi.prodis.prodisweb.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.prodis.prodisweb.lib.dto.prospetto.AssPubbliche;

/**
 * Response for reading AssunzioniPubbliche by its id.
 */
public class GetAssunzioniPubblicheResponse extends BaseGetResponse<List<AssPubbliche>> {

	private List<AssPubbliche> assPubbliche;

	/**
	 * @return the AssPubbliche
	 */
	public List<AssPubbliche> getProspetto() {
		return assPubbliche;
	}

	/**
	 * @param prospetto the AssPubbliche to set
	 */
	public void setProspetto(List<AssPubbliche> assPubbliche) {
		this.assPubbliche = assPubbliche;
	}

	@Override
	protected List<AssPubbliche> getEntity() {
		return assPubbliche;
	}

}
