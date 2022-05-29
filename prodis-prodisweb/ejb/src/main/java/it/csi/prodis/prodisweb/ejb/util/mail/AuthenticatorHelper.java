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
package it.csi.prodis.prodisweb.ejb.util.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Authenticator helper with username and password
 */
public class AuthenticatorHelper extends Authenticator {
	private final String username;
	private final String password;

	/**
	 * Constructor
	 * @param username the username
	 * @param password the password
	 */
	public AuthenticatorHelper(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	}
}
