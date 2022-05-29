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

import java.util.Date;
import java.util.List;

import it.csi.prodis.prodissrv.ejb.business.be.dao.BaseAuditedEntityDao;
import it.csi.prodis.prodissrv.ejb.entity.EsitoStoreProcedure;
import it.csi.prodis.prodissrv.ejb.entity.ProDProspetto;
import it.csi.prodis.prodissrv.ejb.entity.base.BaseEntity;
import it.csi.prodis.prodissrv.ejb.util.jpa.Page;
import it.csi.prodis.prodissrv.lib.dto.prospetto.FilterServiziProdis;
import it.csi.prodis.prodissrv.lib.dto.prospetto.Prospetto;

/**
 * Data Access Object interface for the entity ProDProspetto
 */
public interface ProDProspettoDao extends BaseAuditedEntityDao<Long, ProDProspetto> {

	public long countRicerca(FilterServiziProdis ricercaProspetto);

	public Page<ProDProspetto> findPaginated(int page, int size, String sortField, String sortDirection,
			FilterServiziProdis ricercaProspetto);

	public List<String> findProvinceByIdProspetto(Long idProspetto);

	public Prospetto findDatiNazionaliByIdProspetto(Prospetto ilProspetto);

	public Prospetto findDatiProvincialiByIdProspetto(Prospetto ilProspetto);

	public Prospetto findCompensazioniByIdProspettoProv(Long idProspettoProv);
	
	public <T extends BaseEntity<Long>> BaseEntity<Long> getTfromMin(Class<T> transcodifica, String fieldValue, Date dataRiferimento);
	
	public Long getIdProspettoByCodiceComunicazione(String codiceComunicazione);

	EsitoStoreProcedure storeProcedureEseguiCalcoli(Long idProspetto, String cfOperatore, Boolean soloScoperture);

}
