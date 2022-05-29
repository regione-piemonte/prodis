/*-
 * ========================LICENSE_START=================================
 * PRODIS Servizi - EJB submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodissrv.ejb.business.be.dao.prodis;

import java.util.List;

import it.csi.prodis.prodissrv.ejb.business.be.dao.BaseEntityDao;
import it.csi.prodis.prodissrv.ejb.entity.ProDRiepilogoNazionale;

/**
 * Data Access Object interface for the entity ProDRiepilogoNazionale
 */
public interface ProDRiepilogoNazionaleDao extends BaseEntityDao<Long, ProDRiepilogoNazionale> {

	List<ProDRiepilogoNazionale> findByField(String field);
}
