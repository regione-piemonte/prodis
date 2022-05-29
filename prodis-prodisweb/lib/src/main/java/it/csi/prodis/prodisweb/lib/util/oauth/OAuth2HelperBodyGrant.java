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
package it.csi.prodis.prodisweb.lib.util.oauth;

import java.net.URI;
import java.net.http.HttpRequest;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import it.csi.prodis.prodisweb.lib.util.http.HttpHelper;

/**
 * Thread-safe implementation of a OAuth2 Helper class via the grant sent in the body
 * @author Marchino Alessandro
 *
 */
public class OAuth2HelperBodyGrant extends OAuth2Helper {
	
	/**
	 * Constructor
	 * @param oAuthURL
	 * @param consumerKey
	 * @param consumerSecret
	 * @param timeout
	 * @param staleThreshold
	 */
	OAuth2HelperBodyGrant(String oAuthURL, String consumerKey, String consumerSecret, long timeout, long staleThreshold) {
		super(oAuthURL, consumerKey, consumerSecret, timeout, staleThreshold);
	}

	@Override
	void initToken() {
		final String methodName = "initToken";
		log.trace(methodName, () -> "OAuth2 URI: " + this.oAuthURL + " - Consumer key: " + this.consumerKey);
		
		Map<Object, Object> params = new HashMap<>();
		params.put("grant_type", "client_credentials");
		params.put("client_id", this.consumerKey);
		params.put("client_secret", this.consumerSecret);
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(this.oAuthURL))
				.timeout(Duration.ofMillis(timeout))
				.header("Accept", "text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2")
				.header("Content-type", "application/x-www-form-urlencoded")
				.POST(HttpHelper.ofFormData(params))
				.build();
		invokeAndParseToken(request);
	}
}
