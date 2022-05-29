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

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import it.csi.prodis.prodisweb.ejb.business.be.service.response.base.BasePutResponse;
import it.csi.prodis.prodisweb.lib.dto.ApiError;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ConfermaRiepilogo;
import it.csi.prodis.prodisweb.lib.dto.prospetto.Prospetto;
import it.csi.prodis.prodisweb.lib.util.serialization.JsonUtility;

public class PutProspettoResponse extends BasePutResponse<Long, Prospetto> {

	private Prospetto prospetto = new Prospetto();
	protected List<ApiError> warnings = new ArrayList<>();
	
	public List<ApiError> getWarnings() {
		return warnings;
	}

	public void setWarnings(List<ApiError> warnings) {
		this.warnings = warnings;
	}

	public Prospetto getProspetto() {
		return prospetto;
	}

	public void setProspetto(Prospetto prospetto) {
		this.prospetto = prospetto;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PutProspettoResponse [prospetto=").append(prospetto).append(", apiErrors=")
				.append(getApiErrors()).append("]");
		return builder.toString();
	}

	@Override
	protected Response composeOwnResponse() {
		final String methodName = "composeOwnResponse";
		
		ConfermaRiepilogo confermaRiepilogo = new ConfermaRiepilogo();
		confermaRiepilogo.setProspetto(getProspetto());
		confermaRiepilogo.setWarnings(warnings);
		
		String serialized =  JsonUtility.serialize(confermaRiepilogo);
		logSerialized(methodName, serialized);
		return Response
				.status(Status.CREATED)
				.entity(serialized)
				.location(URI.create(getBaseUri() + "/" + getEntity().getId()))
				.build();
	}

	@Override
	protected Prospetto getEntity() {
		return prospetto;
	}

	@Override
	protected String getBaseUri() {
		return "intervento";
	}

}
