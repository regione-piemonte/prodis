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
package it.csi.prodis.prodisweb.ejb.business.be.facade;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import it.csi.prodis.prodisweb.ejb.BaseTest;
import it.csi.prodis.prodisweb.ejb.util.conf.ConfigurationHelper;

/**
 * Test class for DecodificaFacade.
 */
@RunWith(Arquillian.class)
public class DecodificaFacadeTest extends BaseTest {

	/**
	 * Arquillian entry point
	 * 
	 * @return the Archive
	 */
	@Deployment
	public static EnterpriseArchive createDeployment() {
		return BaseTest.createDeployment();
	}

	@Inject
	private ConfigurationHelper configurationHelper;

	/**
	 * Test
	 */
	@Test
	public void getModalitaAffidamento() {
	}

}