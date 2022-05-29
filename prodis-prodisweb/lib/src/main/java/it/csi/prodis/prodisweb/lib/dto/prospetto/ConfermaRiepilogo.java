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
package it.csi.prodis.prodisweb.lib.dto.prospetto;

import java.util.List;

import it.csi.prodis.prodisweb.lib.dto.ApiError;

public class ConfermaRiepilogo {

	private Prospetto prospetto;
	private List<ApiError> warnings;

	/**
	 * @return the prospetto
	 */
	public Prospetto getProspetto() {
		return prospetto;
	}

	/**
	 * @param prospetto the prospetto to set
	 */
	public void setProspetto(Prospetto prospetto) {
		this.prospetto = prospetto;
	}

	/**
	 * @return the warnings
	 */
	public List<ApiError> getWarnings() {
		return warnings;
	}

	/**
	 * @param warnings the warnings to set
	 */
	public void setWarnings(List<ApiError> warnings) {
		this.warnings = warnings;
	}

}
