/*-
 * ========================LICENSE_START=================================
 * PRODIS BackEnd - INTEGRATION AAEP, IRIDE, COMONL submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodisweb.profilazione.helper;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;

import it.csi.comonl.comprof.cxfclient.ComprofsvSrv;
import it.csi.prodis.prodisweb.lib.util.log.LogUtil;

/**
 * Reperimento di una reference a COMONL.comprof (CXF)
 */
public class AdapterComonlWSFactory {

//	private static final Logger logger = Logger.getLogger(Constants.LOGGER);
	protected final LogUtil logger = new LogUtil(getClass());

	private Service s = null;

	private String endpoint;

	public ComprofsvSrv getService() throws Exception {
		if (s == null) {
			logger.info("[AdapterComonlWSFactory::getService] Look up to COMONL.comprof....", "");
			s = Service.create(getClass().getResource("/comonl.comprof.wsdl"),
					new QName("urn:comprofsv", "ComprofsvSrvService"));
		}
		ComprofsvSrv service = s.getPort(ComprofsvSrv.class);
		BindingProvider bp = (BindingProvider) service;
		Map<String, Object> context = bp.getRequestContext();
		context.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, getEndpoint());

//		org.apache.cxf.endpoint.Client client = ClientProxy.getClient(service);
//		HTTPConduit conduit = (HTTPConduit) client.getConduit();
//		HTTPClientPolicy policy = conduit.getClient();
//		policy.setConnectionTimeout(30000);
//		policy.setReceiveTimeout(30000);
		logger.info("[AdapterComonlWSFactory::getService] Look up to COMPROF.comprofsv completed to endpoint "
				+ getEndpoint(), "");

		return service;
	}

	public String getEndpoint() throws Exception {
		final String COMONL_RESOURCE = "/comonl.properties";
		InputStream is = this.getClass().getResourceAsStream(COMONL_RESOURCE);
		Properties properties = new Properties();
		properties.load(is);
		endpoint = properties.getProperty("url");
		return endpoint;

		// return
		// "http://tst-applogic.reteunitaria.piemonte.it/comprofsvWsfad/services/comprofsv";
	}

}
