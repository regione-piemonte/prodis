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

import java.util.List;

import it.csi.prodis.prodisweb.ejb.business.be.dao.BaseEntityDao;
import it.csi.prodis.prodisweb.ejb.entity.ProDRiepilogoProvinciale;

/**
 * Data Access Object interface for the entity ProDRiepilogoProvinciale
 */
public interface ProDRiepilogoProvincialeDao extends BaseEntityDao<Long, ProDRiepilogoProvinciale> {

	public List<ProDRiepilogoProvinciale> findByIdProspetto(Long idProspetto);
}
