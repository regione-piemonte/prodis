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

import it.csi.prodis.prodisweb.lib.dto.ApiError;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ConfermaRiepilogo;
import it.csi.prodis.prodisweb.lib.util.serialization.JsonUtility;

public class PostConferemaRiepilogoProspettoResponse extends PostProspettoResponse{
	
	protected List<ApiError> warnings = new ArrayList<>();
	
	public void addWarnings(ApiError warning){
		warnings.add(warning);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PostConferemaRiepilogoProspettoResponse [prospetto=").append(getProspetto()).append(", apiErrors=")
				.append(getApiErrors()).append(", warnings=").append(apiWarnings).append("]");
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
}
