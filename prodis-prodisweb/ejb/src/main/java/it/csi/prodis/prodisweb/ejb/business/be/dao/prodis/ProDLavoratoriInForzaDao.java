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
package it.csi.prodis.prodisweb.ejb.business.be.dao.prodis;

import java.math.BigDecimal;
import java.util.List;

import it.csi.prodis.prodisweb.ejb.business.be.dao.BaseAuditedEntityDao;
import it.csi.prodis.prodisweb.ejb.entity.ProDLavoratoriInForza;

/**
 * Data Access Object interface for the entity ProDLavoratoriInForza
 */
public interface ProDLavoratoriInForzaDao extends BaseAuditedEntityDao<Long, ProDLavoratoriInForza> {

	List<ProDLavoratoriInForza> findByField(String field);
	
	List<ProDLavoratoriInForza> findByIdProspettoProv(BigDecimal idProspettoProv);
	
	public String checkCongruenzaCodiceFiscale(String cf, String cognome, String nome, String sesso, String dataNascita, String codiceComuneStatoEsteroNascita);

	Long contaLavoratoriProspettoProv(Long idProspettoProv);
}
