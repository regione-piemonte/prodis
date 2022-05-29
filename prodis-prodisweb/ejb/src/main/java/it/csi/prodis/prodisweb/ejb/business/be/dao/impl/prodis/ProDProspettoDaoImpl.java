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
package it.csi.prodis.prodisweb.ejb.business.be.dao.impl.prodis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import org.apache.commons.lang.StringUtils;

import it.csi.prodis.prodisweb.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.prodis.prodisweb.ejb.business.be.dao.prodis.ProDProspettoDao;
import it.csi.prodis.prodisweb.ejb.entity.EsitoStoreProcedure;
import it.csi.prodis.prodisweb.ejb.entity.ProDProspetto;
import it.csi.prodis.prodisweb.ejb.entity.RitornoEseguiOperazione;
import it.csi.prodis.prodisweb.ejb.util.ProdisSrvUtil;
import it.csi.prodis.prodisweb.ejb.util.jpa.Page;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.StatoProspetto;
import it.csi.prodis.prodisweb.lib.dto.prospetto.RicercaProspetto;
import it.csi.prodis.prodisweb.lib.util.ProdisDateUtils;

/**
 * Data Access Object implementor for the entity ProDProspetto
 */
@ApplicationScoped
public class ProDProspettoDaoImpl extends BaseAuditedEntityDaoImpl<Long, ProDProspetto> implements ProDProspettoDao {

	@Override
	public long countRicerca(RicercaProspetto ricercaProspetto) {
		log.error("countRicerca----------->", "Entro nel metodo countRicerca");
		Map<String, Object> params = new HashMap<>();

		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProDProspetto t ");
		jpql.append(" WHERE 1=1 ");

		composeQueryRicerca(ricercaProspetto, params, jpql);
		Query qn = composeQuery(getCountQuery(jpql), params);
		long count = ((Number) qn.getSingleResult()).longValue();
		return count;
	}

	@Override
	public Page<ProDProspetto> findPaginated(int page, int size, String sortField, String sortDirection,
			RicercaProspetto ricercaProspetto) {
		Map<String, Object> params = new HashMap<>();

		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProDProspetto t ");
		jpql.append(" WHERE 1=1 ");

		composeQueryRicerca(ricercaProspetto, params, jpql);

		jpql.append(" ORDER BY ");
		jpql.append("   NVL(t.dataRiferimentoProspetto, TO_DATE('31/12/2999', 'DD/MM/YYYY')) DESC, ");
		jpql.append("   t.cfComunicazione, ");
		// is not sufficient to give a deterministic answer, you do need to add more
		// columns. The most obvious choice would be a primary key column
		jpql.append("   t.idProspetto ");

		return getPagedResult(jpql, params, page, size);
	}

	private void composeQueryRicerca(RicercaProspetto ricercaProspetto, Map<String, Object> params,
			StringBuilder jpql) {
		if (ricercaProspetto != null) {
			if (ricercaProspetto.getAnnoProspetto() != null) {
				jpql.append(" AND t.annoProtocollo = :annoProtocollo ");
				params.put("annoProtocollo", ricercaProspetto.getAnnoProspetto());
			}

			if (ricercaProspetto.getNumeroProtocollo() != null) {
				jpql.append(" AND t.numeroProtocollo = :numeroProtocollo ");
				params.put("numeroProtocollo", ricercaProspetto.getNumeroProtocollo());
			}

			if (ricercaProspetto.getDataProtocolloDa() != null) {
				jpql.append(" AND t.dataProtocollo >= TO_DATE( :dataProtocolloDa , 'dd/MM/yyyy') ");
				params.put("dataProtocolloDa", ProdisDateUtils.formatDate(ricercaProspetto.getDataProtocolloDa()));
			}
			if (ricercaProspetto.getDataProtocolloA() != null) {
				jpql.append(" AND t.dataProtocollo <= TO_DATE( :dataProtocolloA , 'dd/MM/yyyy') ");
				params.put("dataProtocolloA", ProdisDateUtils.formatDate(ricercaProspetto.getDataProtocolloA()));
			}

			if (ricercaProspetto.getDataRiferimentoDa() != null) {
				jpql.append(
						" AND TRUNC(t.dataRiferimentoProspetto) >=  TO_DATE( :dataRiferimentoDa , 'dd/MM/yyyy')   ");
				params.put("dataRiferimentoDa", ProdisDateUtils.formatDate(ricercaProspetto.getDataRiferimentoDa()));
			}
			if (ricercaProspetto.getDataRiferimentoA() != null) {
				jpql.append(" AND TRUNC(t.dataRiferimentoProspetto) <= TO_DATE( :dataRiferimentoA , 'dd/MM/yyyy') ");
				params.put("dataRiferimentoA", ProdisDateUtils.formatDate(ricercaProspetto.getDataRiferimentoA()));
			}

			if (!StringUtils.isEmpty(ricercaProspetto.getCodiceFiscaleAzienda())) {
				jpql.append(" AND UPPER(t.proDDatiAzienda.cfAzienda) ");
				jpql.append(" LIKE UPPER(CONCAT('%', :codiceFiscaleAzienda, '%'))");
				params.put("codiceFiscaleAzienda", ricercaProspetto.getCodiceFiscaleAzienda().trim());
			}
			if (!StringUtils.isEmpty(ricercaProspetto.getDenominazioneAzienda())) {
				jpql.append(" AND UPPER(t.proDDatiAzienda.denominazioneDatoreLavoro) ");
				jpql.append(" LIKE UPPER(CONCAT('%', :denominazioneAzienda, '%'))");
				params.put("denominazioneAzienda", ricercaProspetto.getDenominazioneAzienda().trim());
			}

			if (!StringUtils.isEmpty(ricercaProspetto.getCodiceRegionale())) {
				jpql.append(" AND UPPER(t.codiceComunicazione) ");
				jpql.append(" LIKE UPPER(CONCAT('%', :codiceRegionale, '%'))");
				params.put("codiceRegionale", ricercaProspetto.getCodiceRegionale().trim());
			}

			boolean statoCancellatoSelezionato = false;
			if (ricercaProspetto.getStatoProspettos() != null) {
				List<Long> idStatos = new ArrayList<>();
				for (StatoProspetto statoProspetto : ricercaProspetto.getStatoProspettos()) {
					if (statoProspetto.getId() == 13L) {
						statoCancellatoSelezionato = true;
					}
					idStatos.add(statoProspetto.getId());
				}
				jpql.append(" AND t.proTStatoProspetto.idTStatoProspetto IN :idStatos ");
				params.put("idStatos", idStatos);
			}
			if (!statoCancellatoSelezionato) {
				jpql.append(" AND t.proTStatoProspetto.idTStatoProspetto NOT IN :statoCancellato ");
				params.put("statoCancellato", 13L);
			}

			if (ricercaProspetto.getProvincia() != null) {
				jpql.append(" AND EXISTS ( ");
				jpql.append("   FROM ProRProspettoProvincia rpp ");
				jpql.append("   WHERE rpp.idProspetto = t ");
				jpql.append("   AND rpp.proTProvincia.idTProvincia = :idProvincia ");
				jpql.append(" ) ");
				params.put("idProvincia", ricercaProspetto.getProvincia().getId());
			}

			if (!StringUtils.isEmpty(ricercaProspetto.getCodiceFiscaleStudioProf())) {
				jpql.append(" AND UPPER(t.cfStudioProfessionale) ");
				jpql.append(" LIKE UPPER(CONCAT('%', :codiceFiscaleStudioProf, '%'))");
				params.put("codiceFiscaleStudioProf", ricercaProspetto.getCodiceFiscaleStudioProf());
			}

			log.error("STAMPO I PARAMETRI PASSATI PER LA RICERCA ", "-------->" + ricercaProspetto.toString());
		}
	}

	@Override
	public String checkCodiceFiscale(String cf) {

		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder();

		sql.append(" select PCK_PRODIS_2012_UTILS.controlla_codice_fiscale('");
		sql.append(cf);
		sql.append("', NULL, NULL , NULL , NULL , NULL  ) FROM DUAL ");

		Query query = composeNativeQuery(sql.toString(), params);
		long resultCheck = ((Number) query.getSingleResult()).longValue();

		return Long.toString(resultCheck);
	}

	@Override
	public String checkCongruenzaCodiceFiscale(String cf, String cognome, String nome, String sesso, String dataNascita,
			String codiceComuneStatoEsteroNascita) {

		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder();
		if (ProdisSrvUtil.isVoid(cognome)) {
			cognome = "NULL";
		}
		if (ProdisSrvUtil.isVoid(nome)) {
			nome = "NULL";
		}
		if (ProdisSrvUtil.isVoid(sesso)) {
			sesso = "NULL";
		}
		if (ProdisSrvUtil.isVoid(dataNascita)) {
			dataNascita = "NULL";
		}
		if (ProdisSrvUtil.isVoid(codiceComuneStatoEsteroNascita)) {
			codiceComuneStatoEsteroNascita = "NULL";
		}

		sql.append(" select PCK_PRODIS_2012_UTILS.controlla_codice_fiscale('");
		sql.append(cf);
		sql.append("',");
		sql.append(!cognome.equalsIgnoreCase("NULL") ? "'" : "");
		sql.append(cognome);
		sql.append(!cognome.equalsIgnoreCase("NULL") ? "'" : "");
		sql.append(",");
		sql.append(!nome.equalsIgnoreCase("NULL") ? "'" : "");
		sql.append(nome);
		sql.append(!nome.equalsIgnoreCase("NULL") ? "'" : "");
		sql.append(",");
		sql.append(!sesso.equalsIgnoreCase("NULL") ? "'" : "");
		sql.append(sesso);
		sql.append(!sesso.equalsIgnoreCase("NULL") ? "'" : "");
		sql.append(",");
		sql.append(!dataNascita.equalsIgnoreCase("NULL") ? "'" : "");
		sql.append(dataNascita);
		sql.append(!sesso.equalsIgnoreCase("NULL") ? "'" : "");
		sql.append(",");
		sql.append(!codiceComuneStatoEsteroNascita.equalsIgnoreCase("NULL") ? "'" : "");
		sql.append(codiceComuneStatoEsteroNascita);
		sql.append(!codiceComuneStatoEsteroNascita.equalsIgnoreCase("NULL") ? "'" : "");
		sql.append(" ) FROM DUAL ");
		System.out.println(sql);

		// FIXME usare la chiamata a funzione corretta non via string!!

		Query query = composeNativeQuery(sql.toString(), params);
		long resultCheck = ((Number) query.getSingleResult()).longValue();

		return Long.toString(resultCheck);
	}

	@Override
	public RitornoEseguiOperazione rettifica(ProDProspetto prospetto) {

		RitornoEseguiOperazione rit = new RitornoEseguiOperazione();

		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("PCK_PRODIS_2012.esegui_operazione")
				.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
				.registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
				.registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
				.registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
				.registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
				.registerStoredProcedureParameter(6, Long.class, ParameterMode.OUT)
				.registerStoredProcedureParameter(7, String.class, ParameterMode.OUT)
				.registerStoredProcedureParameter(8, Long.class, ParameterMode.OUT).setParameter(1, prospetto.getId())
				.setParameter(2, prospetto.getCodUserAggiorn()).setParameter(3, prospetto.getCfStudioProfessionale())
				.setParameter(4, prospetto.getProTSoggetti() == null ? null : prospetto.getProTSoggetti().getId())
				.setParameter(5, "RETTIFICA");

		query.execute();

		Long esito = (Long) query.getOutputParameterValue(6);
		String messaggio = (String) query.getOutputParameterValue(7);
		Long newIdProspetto = (Long) query.getOutputParameterValue(8);

		rit.setEsito(esito);
		rit.setMessaggio(messaggio);
		rit.setNewId(newIdProspetto);
		rit.setSuccesso(true);

		return rit;

	}

	@Override
	public RitornoEseguiOperazione annulla(ProDProspetto prospetto) {

		RitornoEseguiOperazione rit = new RitornoEseguiOperazione();

		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("PCK_PRODIS_2012.esegui_operazione")
				.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
				.registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
				.registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
				.registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
				.registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
				.registerStoredProcedureParameter(6, Long.class, ParameterMode.OUT)
				.registerStoredProcedureParameter(7, String.class, ParameterMode.OUT)
				.registerStoredProcedureParameter(8, Long.class, ParameterMode.OUT).setParameter(1, prospetto.getId())
				.setParameter(2, prospetto.getCodUserAggiorn()).setParameter(3, prospetto.getCfStudioProfessionale())
				.setParameter(4, prospetto.getProTSoggetti() == null ? null : prospetto.getProTSoggetti().getId())
				.setParameter(5, "ANNULLAMENTO");

		query.execute();

		Long esito = (Long) query.getOutputParameterValue(6);
		String messaggio = (String) query.getOutputParameterValue(7);
		Long newIdProspetto = (Long) query.getOutputParameterValue(8);

		rit.setEsito(esito);
		rit.setMessaggio(messaggio);
		rit.setNewId(newIdProspetto);
		rit.setSuccesso(true);

		return rit;

	}

	@Override
	public RitornoEseguiOperazione duplica(ProDProspetto prospetto) {

		RitornoEseguiOperazione rit = new RitornoEseguiOperazione();

		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("PCK_PRODIS_2012.esegui_operazione")
				.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
				.registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
				.registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
				.registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
				.registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
				.registerStoredProcedureParameter(6, Long.class, ParameterMode.OUT)
				.registerStoredProcedureParameter(7, String.class, ParameterMode.OUT)
				.registerStoredProcedureParameter(8, Long.class, ParameterMode.OUT).setParameter(1, prospetto.getId())
				.setParameter(2, prospetto.getCodUserAggiorn()).setParameter(3, prospetto.getCfStudioProfessionale())
				.setParameter(4, prospetto.getProTSoggetti() == null ? null : prospetto.getProTSoggetti().getId())
				.setParameter(5, "DUPLICAZIONE");

		query.execute();

		Long esito = (Long) query.getOutputParameterValue(6);
		String messaggio = (String) query.getOutputParameterValue(7);
		Long newIdProspetto = (Long) query.getOutputParameterValue(8);

		rit.setEsito(esito);
		rit.setMessaggio(messaggio);
		rit.setNewId(newIdProspetto);
		rit.setSuccesso(true);

		return rit;

	}

	@Override
	public EsitoStoreProcedure storeProcedureEseguiCalcoli(Long idProspetto, String cfOperatore,
			Boolean soloScoperture) {

		EsitoStoreProcedure rit = new EsitoStoreProcedure();

		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("PCK_PRODIS_2012.esegui_calcoli")
				.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
				.registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
				.registerStoredProcedureParameter(3, Boolean.class, ParameterMode.IN)
				.registerStoredProcedureParameter(4, Long.class, ParameterMode.OUT)
				.registerStoredProcedureParameter(5, String.class, ParameterMode.OUT).setParameter(1, idProspetto)
				.setParameter(2, cfOperatore).setParameter(3, soloScoperture);

		query.execute();

		Long esito = (Long) query.getOutputParameterValue(4);

		String messaggio = (String) query.getOutputParameterValue(5);

		rit.setEsito(esito);
		rit.setMessaggio(messaggio);

		return rit;
	}

}
