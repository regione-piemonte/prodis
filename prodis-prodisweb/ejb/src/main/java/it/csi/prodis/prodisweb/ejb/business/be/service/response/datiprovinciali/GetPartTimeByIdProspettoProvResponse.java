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

import java.util.List;

import it.csi.prodis.prodisweb.ejb.business.be.service.response.base.BaseGetResponse;
import it.csi.prodis.prodisweb.lib.dto.prospetto.PartTime;

/**
 * Response for reading partTimes by its id Prospetto provinciale.
 */
public class GetPartTimeByIdProspettoProvResponse extends BaseGetResponse<List<PartTime>> {

	private List<PartTime> partTimes;

	@Override
	protected List<PartTime> getEntity() {
		return partTimes;
	}

	public List<PartTime> getPartTimes() {
		return partTimes;
	}

	public void setPartTimes(List<PartTime> partTimes) {
		this.partTimes = partTimes;
	}

}
