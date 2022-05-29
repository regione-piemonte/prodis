/*-
 * ========================LICENSE_START=================================
 * PRODIS BackEnd - INTEGRATION SPICOM submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodisweb.lib.exception;

public class SpicomIntegrationException extends RuntimeException {

		private static final long serialVersionUID = 1L;

		public SpicomIntegrationException() {
			super();
		}

		public SpicomIntegrationException(String message, Throwable cause) {
			super(message, cause);
		}

		public SpicomIntegrationException(String message) {
			super(message);
		}

		public SpicomIntegrationException(Throwable cause) {
			super(cause);
		}
}
