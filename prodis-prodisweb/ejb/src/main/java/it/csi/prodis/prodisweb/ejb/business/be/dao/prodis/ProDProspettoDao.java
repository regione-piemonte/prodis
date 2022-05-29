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

import it.csi.prodis.prodisweb.ejb.business.be.dao.BaseAuditedEntityDao;
import it.csi.prodis.prodisweb.ejb.entity.EsitoStoreProcedure;
import it.csi.prodis.prodisweb.ejb.entity.ProDProspetto;
import it.csi.prodis.prodisweb.ejb.entity.RitornoEseguiOperazione;
import it.csi.prodis.prodisweb.ejb.util.jpa.Page;
import it.csi.prodis.prodisweb.lib.dto.prospetto.RicercaProspetto;

/**
 * Data Access Object interface for the entity ProDProspetto
 */
public interface ProDProspettoDao extends BaseAuditedEntityDao<Long, ProDProspetto> {

	public long countRicerca(RicercaProspetto ricercaProspetto);

	public Page<ProDProspetto> findPaginated(int page, int size, String sortField, String sortDirection,
			RicercaProspetto ricercaProspetto);

	public String checkCodiceFiscale(String cf);

	public String checkCongruenzaCodiceFiscale(String cf, String cognome, String nome, String sesso, String dataNascita,
			String codiceComuneStatoEsteroNascita);

	public RitornoEseguiOperazione rettifica(ProDProspetto proDProspetto);

	public RitornoEseguiOperazione annulla(ProDProspetto proDProspetto);

	public RitornoEseguiOperazione duplica(ProDProspetto proDProspetto);

	public EsitoStoreProcedure storeProcedureEseguiCalcoli(Long idProspetto, String cfOperatore,
			Boolean soloScoperture);

}
