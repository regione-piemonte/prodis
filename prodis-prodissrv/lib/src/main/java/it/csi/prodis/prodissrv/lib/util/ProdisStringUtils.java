/*-
 * ========================LICENSE_START=================================
 * PRODIS Servizi - LIB submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodissrv.lib.util;

import java.text.MessageFormat;

import org.apache.commons.lang3.StringUtils;

public abstract class ProdisStringUtils {

	public static String checkNull(String stringa) {
		if (stringa == null)
			stringa = "";
		return stringa;
	}

	public static String checkNull(Object obj) {
		if (obj == null)
			return new String("");
		else
			return obj.toString();
	}

	public static String replace(String text, String repl, String with) {
		StringBuffer buf = new StringBuffer(text.length());
		int start = 0, end = 0;
		while ((end = text.indexOf(repl, start)) != -1) {
			buf.append(text.substring(start, end)).append(with);
			start = end + repl.length();
		}
		buf.append(text.substring(start));
		return buf.toString();
	}

	public static boolean isStringEmpty(String field) {
		if (field == null)
			field = "";

		return field.length() == 0;
	}

	// Questo metodo, copiato dalla classe MessageResource di Struts, e utilizzato
	// per parsificare le stringhe prima di passarle al MessageFormat nel metodo
	// fillParameters, aggiunge l'escape al simbolo di apice singolo (da ' a \').
	public static String escape(String string) {
		if ((string == null) || (string.indexOf('\'') < 0)) {
			return string;
		}

		int n = string.length();
		StringBuffer sb = new StringBuffer(n);

		for (int i = 0; i < n; i++) {
			char ch = string.charAt(i);

			if (ch == '\'') {
				sb.append('\'');
			}

			sb.append(ch);
		}

		return sb.toString();
	}

	public static String fillParameters(String stringa, Object[] argomenti) {
		if (isStringEmpty(stringa))
			return stringa;

		try {
			return MessageFormat.format(escape(stringa), argomenti);
		} catch (IllegalArgumentException iae) {
			return stringa;
		}
	}

	public static Long getLong(String stringa) {
		try {
			return new Long(stringa);
		} catch (Exception exc) {
			return null;
		}
	}

	public static String leftPad(String stringa, int lunghezza, char pad) {
		if (stringa != null) {
			while (stringa.length() < lunghezza) {
				stringa = pad + stringa;
			}
		}
		return stringa;
	}

	public static String escapeSql(String str) {
		if (str == null) {
			return null;
		}
		return StringUtils.replace(str, "'", "''");
	}

}
