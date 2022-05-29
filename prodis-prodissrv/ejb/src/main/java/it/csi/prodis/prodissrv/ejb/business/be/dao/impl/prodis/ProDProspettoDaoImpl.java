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
package it.csi.prodis.prodissrv.ejb.business.be.dao.impl.prodis;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

import org.apache.commons.lang.StringUtils;

import it.csi.prodis.prodissrv.ejb.business.be.dao.impl.BaseAuditedEntityDaoImpl;
import it.csi.prodis.prodissrv.ejb.business.be.dao.prodis.ProDProspettoDao;
import it.csi.prodis.prodissrv.ejb.entity.EsitoStoreProcedure;
import it.csi.prodis.prodissrv.ejb.entity.ProDProspetto;
import it.csi.prodis.prodissrv.ejb.entity.ProTAssunzioneProtetta;
import it.csi.prodis.prodissrv.ejb.entity.ProTAtecofin;
import it.csi.prodis.prodissrv.ejb.entity.ProTCategoriaAzienda;
import it.csi.prodis.prodissrv.ejb.entity.ProTCategoriaEscluse;
import it.csi.prodis.prodissrv.ejb.entity.ProTCausaSospensione;
import it.csi.prodis.prodissrv.ejb.entity.ProTCcnl;
import it.csi.prodis.prodissrv.ejb.entity.ProTComune;
import it.csi.prodis.prodissrv.ejb.entity.ProTComunicazione;
import it.csi.prodis.prodissrv.ejb.entity.ProTContratti;
import it.csi.prodis.prodissrv.ejb.entity.ProTCpi;
import it.csi.prodis.prodissrv.ejb.entity.ProTDichiarante;
import it.csi.prodis.prodissrv.ejb.entity.ProTIstat2001livello5;
import it.csi.prodis.prodissrv.ejb.entity.ProTProvincia;
import it.csi.prodis.prodissrv.ejb.entity.ProTRegione;
import it.csi.prodis.prodissrv.ejb.entity.ProTSoggetti;
import it.csi.prodis.prodissrv.ejb.entity.ProTStatiEsteri;
import it.csi.prodis.prodissrv.ejb.entity.ProTStatoConcessione;
import it.csi.prodis.prodissrv.ejb.entity.ProTTipoRipropPt;
import it.csi.prodis.prodissrv.ejb.entity.base.BaseEntity;
import it.csi.prodis.prodissrv.ejb.util.ProdisSrvUtil;
import it.csi.prodis.prodissrv.ejb.util.jpa.Page;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.CategoriaAzienda;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Provincia;
import it.csi.prodis.prodissrv.lib.dto.prospetto.DatiProvinciali;
import it.csi.prodis.prodissrv.lib.dto.prospetto.FilterServiziProdis;
import it.csi.prodis.prodissrv.lib.dto.prospetto.Prospetto;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProspettoGradualita;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProspettoProvincia;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProvConvenzione;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProvEsonero;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProvEsoneroAutocert;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProvGradualita;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProvSospensione;
import it.csi.prodis.prodissrv.lib.dto.prospetto.RiepilogoNazionale;
import it.csi.prodis.prodissrv.lib.dto.prospetto.RiepilogoProvinciale;

/**
 * Data Access Object implementor for the entity ProDProspetto
 */
@ApplicationScoped
public class ProDProspettoDaoImpl extends BaseAuditedEntityDaoImpl<Long, ProDProspetto> implements ProDProspettoDao {

	@Override
	public long countRicerca(FilterServiziProdis ricercaProspetto) {
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
			FilterServiziProdis ricercaProspetto) {
		Map<String, Object> params = new HashMap<>();

		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProDProspetto t ");
		jpql.append(" WHERE 1=1 ");

		composeQueryRicerca(ricercaProspetto, params, jpql);

		jpql.append(
				" ORDER BY NVL(t.dataRiferimentoProspetto, TO_DATE('31/12/2999', 'DD/MM/YYYY')) DESC, t.dataTimbroPostale, t.idProspetto  ");

		return getPagedResult(jpql, params, page, size);
	}

	private void composeQueryRicerca(FilterServiziProdis ricercaProspetto, Map<String, Object> params,
			StringBuilder jpql) {
		if (ricercaProspetto != null) {

			if (ricercaProspetto.getListCodiceFiscaleAzienda() != null) {
				jpql.append(" AND t.proDDatiAzienda.cfAzienda IN :listCodiceFiscaleAzienda ");
				params.put("listCodiceFiscaleAzienda", ricercaProspetto.getListCodiceFiscaleAzienda());
			}

			if (!StringUtils.isEmpty(ricercaProspetto.getCodiceRegionale())) {
				jpql.append(" AND UPPER(t.codiceComunicazione) ");
				jpql.append(" LIKE UPPER(CONCAT('%', :codiceRegionale, '%'))");
				params.put("codiceRegionale", ricercaProspetto.getCodiceRegionale());
			}

			if (ricercaProspetto.getDataRiferimentoDa() != null) {
				jpql.append(" AND t.dataRiferimentoProspetto >= :dataRiferimentoDa ");
				params.put("dataRiferimentoDa", ricercaProspetto.getDataRiferimentoDa());
			}
			if (ricercaProspetto.getDataRiferimentoA() != null) {
				jpql.append(" AND t.dataRiferimentoProspetto <= :dataRiferimentoA ");
				params.put("dataRiferimentoA", ricercaProspetto.getDataRiferimentoA());
			}

			if (!StringUtils.isEmpty(ricercaProspetto.getDenominazioneAzienda())) {
				jpql.append(" AND UPPER(t.proDDatiAzienda.denominazioneDatoreLavoro) ");
				jpql.append(" LIKE UPPER(CONCAT('%', :denominazioneAzienda, '%'))");
				params.put("denominazioneAzienda", ricercaProspetto.getDenominazioneAzienda());
			}

			if (ricercaProspetto.getCodProvinciaMin() != null) {
				jpql.append(" AND EXISTS ( ");
				jpql.append("   FROM ProRProspettoProvincia rpp ");
				jpql.append("   WHERE rpp.proDProspetto.idProspetto = t ");
				jpql.append("   AND rpp.proTProvincia.codProvinciaMin = :codProvinciaMin ");
				jpql.append(" ) ");
				params.put("codProvinciaMin", ricercaProspetto.getCodProvinciaMin());
			}

			if (!StringUtils.isEmpty(ricercaProspetto.getTipoComunicazione())) {
				jpql.append(" AND EXISTS ( ");
				jpql.append("   FROM ProTComunicazione ptc ");
				jpql.append("   WHERE ptc.idTComunicazione = t.proTComunicazione.idTComunicazione ");
				jpql.append("   AND ptc.descComunicazione = :tipoComunicazione ");
				jpql.append(" ) ");
				params.put("tipoComunicazione", ricercaProspetto.getTipoComunicazione());
			}

			// bisogna caricare solamente i prospetti in stato --> 3 -->PRESENTATA
			jpql.append(" AND t.proTStatoProspetto.idTStatoProspetto = 3 ");

		}
	}

	@Override
	public List<String> findProvinceByIdProspetto(Long idProspetto) {
		Map<String, Object> params = new HashMap<String, Object>();

		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT ");
		sql.append(" DISTINCT ");
		sql.append("    P.COD_PROVINCIA_MIN ");

		sql.append(" FROM ");
		sql.append("  PRO_R_PROSPETTO_PROVINCIA R, ");
		sql.append("  PRO_T_PROVINCIA P ");
		sql.append(" WHERE 1=1 ");
		sql.append(" AND R.ID_T_PROVINCIA = P.ID_T_PROVINCIA ");
		if (ProdisSrvUtil.isNotVoid(idProspetto)) {
			sql.append(" AND  R.ID_PROSPETTO  =  ");
			sql.append(idProspetto);
			sql.append(" ");
		}

		Query query = composeNativeQuery(sql.toString(), params);
		List<String> elenco = query.getResultList();
		List<String> elencoCodProvince = new ArrayList<String>();

		for (String obj : elenco) {
			elencoCodProvince.add(obj);
		}
		return elencoCodProvince;
	}

	@Override
	public Prospetto findDatiNazionaliByIdProspetto(Prospetto ilProspetto) {
		Map<String, Object> params = new HashMap<String, Object>();

		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT " + "	P.DATA_RIFERIMENTO_PROSPETTO , " + "	CA.DES_CATEGORIA_AZIENDA , "
				+ "	P.NUM_LAVOR_IN_FORZA_NAZIONALE , " + "	RN.NUM_LAVORATORI_BASE_COMPUTO , "
				+ "	RN.BASE_COMPUTO_ART_3 , " + "	RN.BASE_COMPUTO_ART_18 , " + "	RN.NUM_LAVORATORI_SOSPENSIONE , "
				+ "	RN.NUM_POSIZIONI_ESONERATE , " + "	G.N_ASSUNZIONI_LAV_PRE_TRASF, "
				+ "	RN.FLG_SOSPENSIONI_IN_CORSO, " + "	RN.NUM_SCOPERT_DISABILI, "
				+ "	RN.NUM_SCOPERT_CATEGORIE_PROTETTE, " + "	RN.NUM_DISABILI_IN_FORZA, "
				+ "	RN.NUM_CAT_PROT_IN_FORZA, " + "	RN.QUOTA_RISERVA_DISABILI, " + "	RN.QUOTA_RISERVA_ART_18, "
				+ "	RN.NUM_SCOPERT_DISABILI_REALI, " + "	RN.NUM_SCOPERT_CAT_PROT_REALI " + "FROM "
				+ "	PRO_D_PROSPETTO P, " + "	PRO_D_RIEPILOGO_NAZIONALE RN, " + "	PRO_T_CATEGORIA_AZIENDA CA, "
				+ "	PRO_D_PROSPETTO_GRADUALITA G " + "WHERE " + "	P.ID_PROSPETTO = " + ilProspetto.getId()
				+ "	AND P.ID_T_CATEGORIA_AZIENDA = CA.ID_T_CATEGORIA_AZIENDA (+) "
				+ "	AND P.ID_PROSPETTO = RN.ID_PROSPETTO " + "	AND P.ID_PROSPETTO = G.ID_PROSPETTO (+) ");

		Query query = composeNativeQuery(sql.toString(), params);
		List<Object[]> elenco = query.getResultList();
		for (Object[] obj : elenco) {
			RiepilogoNazionale riepNaz = new RiepilogoNazionale();
			if (obj[0] != null) {
//				Data riferimento
				ilProspetto.setDataRiferimentoProspetto((Date) (obj[0]));
			}
			if (obj[1] != null) {
//				Classe di ampiezza
				CategoriaAzienda laCategoria = new CategoriaAzienda();
				laCategoria.setDesCategoriaAzienda((String) obj[1]);
				ilProspetto.setCategoriaAzienda(laCategoria);
			}
			if (obj[2] != null) {
//				Numero lavoratori in forza nazionale
				ilProspetto.setNumLavorInForzaNazionale((BigDecimal) obj[2]);
			}
			if (obj[3] != null) {
//				Numero lavoratori Base di Computo
				riepNaz.setNumLavoratoriBaseComputo((BigDecimal) obj[3]);
			}
			if (obj[4] != null) {
//				Base computo art.3 Disabili
				riepNaz.setBaseComputoArt3((BigDecimal) obj[4]);
			}
			if (obj[5] != null) {
//				Base computo art.18 Categorie protette
				riepNaz.setBaseComputoArt18((BigDecimal) obj[5]);
			}
			if (obj[6] != null) {
//				Numero lavoratori in sospensione
				riepNaz.setNumLavoratoriSospensione((BigDecimal) obj[6]);
			}
			if (obj[7] != null) {
//				Numero posizioni esonerate
				riepNaz.setNumPosizioniEsonerate((BigDecimal) obj[7]);
			}
			if (obj[8] != null) {
				ProspettoGradualita laGradualita = new ProspettoGradualita();
				laGradualita.setnAssunzioniLavPreTrasf((BigDecimal) obj[8]);
//				Numero assunzioni lavoratori in gradualità
				ilProspetto.setProspettoGradualita(laGradualita);
			}
			if (obj[9] != null) {
//				Flag sospensione per mobilità
				riepNaz.setFlgSospensioniInCorso((String) obj[9]);
			}
			if (obj[10] != null) {
//				Numero Scoperture Disabili
				riepNaz.setNumScopertDisabili((BigDecimal) obj[10]);
			}
			if (obj[11] != null) {
//				Numero Scoperture Categorie Protette
				riepNaz.setNumScopertCategorieProtette((BigDecimal) obj[11]);
			}
			if (obj[12] != null) {
//				Numero lavoratori Disabili in forza
				riepNaz.setNumDisabiliInForza((BigDecimal) obj[12]);
			}
			if (obj[13] != null) {
//				Numero lavoratori Categorie protette in forza
				riepNaz.setNumCatProtInForza((BigDecimal) obj[13]);
			}
			if (obj[14] != null) {
//				Quota di riserva Disabii
				riepNaz.setQuotaRiservaDisabili((BigDecimal) obj[14]);
			}
			if (obj[15] != null) {
//				Quota di riserva Categorie Protette
				riepNaz.setQuotaRiservaArt18((BigDecimal) obj[15]);
			}
			if (obj[16] != null) {
//				Numero Scoperture Disabili REALI
				riepNaz.setNumScopertDisabiliReali((BigDecimal) obj[16]);
			}
			if (obj[17] != null) {
//				Numero Scoperture Categorie protette REALI
				riepNaz.setNumScopertCatProtReali((BigDecimal) obj[17]);
			}
			ilProspetto.setRiepilogoNazionale(riepNaz);
		}
		return ilProspetto;
	}

	@Override
	public Prospetto findDatiProvincialiByIdProspetto(Prospetto ilProspetto) {
		Map<String, Object> params = new HashMap<String, Object>();

		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT " + "	PROV.COD_PROVINCIA_MIN, " + "	PROV.DS_PRO_T_PROVINCIA, "
				+ "	DP.N_TOTALE_LAVORAT_DIPENDENTI, " + "	RP.BASE_COMPUTO_ART_3, " + "	RP.BASE_COMPUTO_ART_18, "
				+ "	RP.NUM_POSIZIONI_ESONERATE, " + "	RP.NUM_LAVORATORI_SOSPENSIONE, "
				+ "	RP.NUM_COMPENSAZIONE_DISABILI, "
				+ "	DECODE(( SELECT DISTINCT 1 FROM PRO_D_PROV_COMPENSAZIONI M WHERE M.ID_PROSPETTO_PROV = PP.ID_PROSPETTO_PROV), 1, 'S', 'N') AS FLAG_AUTOCOMPENSAZIONI , "
				+ "	EA.N_LAV_ESONERO_AUTOCERT , " + "	E.N_LAVORATORI_ESONERO , "
				+ "	RP.CAT_COMPENSAZIONE_DISABILI , " + "	RP.NUM_COMPENSAZIONI_CATE_PROT , "
				+ "	RP.CAT_COMPENSAZIONE_CATE_PROT , " + "	C.ID_T_STATO_CONCESSIONE AS STATO_CONVENZIONI, "
				+ "	PG.N_ASSUNZIONI_EFF_DOPO_TRASF , " + "	P.FLG_SOSPENSIONE_PER_MOBILITA , "
				+ "	S.ID_T_STATO_CONCESSIONE AS STATO_SOSPENSIONI, " + "	E.PERCENTUALE , "
				+ "	RP.NUM_SCOPERTURE_DISABILI , " + "	RP.NUM_SCOPERTURE_DISABILI_REALI , "
				+ "	RP.NUM_SCOPERTURE_CAT_PROT , " + "	RP.NUM_SCOPERTURE_CAT_PROT_REALI , "
				+ "	RP.QUOTA_RISERVA_DISABILI , " + "	RP.QUOTA_RISERVA_ART_18 , " + "	RP.NUM_DISABILI_IN_FORZA , "
				+ "	RP.NUM_CAT_PROT_IN_FORZA_CONT_DIS , " + "	RP.NUM_CAT_PROT_IN_FORZA , "
				+ " (SELECT DISTINCT FIRST_VALUE (L.ORARIO_SETT_CONTRATTUALE_MIN) OVER (PARTITION BY "
				+ " L.ID_PROSPETTO_PROV  ORDER BY COUNT(*) DESC) FROM PRO_D_LAVORATORI_IN_FORZA L "
				+ " WHERE L.ID_T_ASSUNZIONE_PROTETTA NOT IN (7, 8, 11, 12) AND L.ID_PROSPETTO_PROV = PP.ID_PROSPETTO_PROV "
				+ " GROUP BY  L.ORARIO_SETT_CONTRATTUALE_MIN, L.ID_PROSPETTO_PROV) AS CALCOLO_ORARIO " + " FROM "
				+ "	PRO_D_PROSPETTO P, " + "	PRO_D_RIEPILOGO_PROVINCIALE RP, " + "	PRO_R_PROSPETTO_PROVINCIA PP, "
				+ "	PRO_D_DATI_PROVINCIALI DP, " + "	PRO_T_PROVINCIA PROV, " + "	PRO_D_PROV_ESONERO_AUTOCERT EA, "
				+ "	PRO_D_PROV_ESONERO E, " + "	PRO_D_PROV_CONVENZIONE C, " + "	PRO_D_PROV_GRADUALITA PG, "
				+ "	PRO_D_PROV_SOSPENSIONE S " + "WHERE " + "	P.ID_PROSPETTO = " + ilProspetto.getId()
				+ "	AND P.ID_PROSPETTO = PP.ID_PROSPETTO " + "	AND PP.ID_T_PROVINCIA = PROV.ID_T_PROVINCIA "
				+ "	AND PP.ID_PROSPETTO_PROV = RP.ID_PROSPETTO_PROV "
				+ "	AND PP.ID_PROSPETTO_PROV = DP.ID_PROSPETTO_PROV (+) "
				+ "	AND PP.ID_PROSPETTO_PROV = EA.ID_PROSPETTO_PROV (+) "
				+ "	AND PP.ID_PROSPETTO_PROV = E.ID_PROSPETTO_PROV (+) "
				+ "	AND PP.ID_PROSPETTO_PROV = C.ID_PROSPETTO_PROV (+) "
				+ "	AND PP.ID_PROSPETTO_PROV = PG.ID_PROSPETTO_PROV (+) "
				+ "	AND PP.ID_PROSPETTO_PROV = S.ID_PROSPETTO_PROV (+) ");

		Query query = composeNativeQuery(sql.toString(), params);
		List<Object[]> elenco = query.getResultList();
		List<ProspettoProvincia> laListaDeiProspettiProvincia = new ArrayList<ProspettoProvincia>();
		for (Object[] obj : elenco) {
			ProspettoProvincia prospettoProv = new ProspettoProvincia();
			Provincia laProv = new Provincia();
			prospettoProv.setProvincia(laProv);
			DatiProvinciali iDatiProv = new DatiProvinciali();
			prospettoProv.setDatiProvinciali(iDatiProv);
			RiepilogoProvinciale ilRiepilogoProv = new RiepilogoProvinciale();
			prospettoProv.setRiepilogoProvinciale(ilRiepilogoProv);
			ProvEsoneroAutocert esoneroAuto = new ProvEsoneroAutocert();
			prospettoProv.getDatiProvinciali().setProvEsoneroAutocert(esoneroAuto);
			ProvEsonero esonero = new ProvEsonero();
			prospettoProv.getDatiProvinciali().setProvEsonero(esonero);
			ProvConvenzione laConve = new ProvConvenzione();
			prospettoProv.getDatiProvinciali().setProvConvenzione(laConve);
			ProvSospensione laSospensione = new ProvSospensione();
			prospettoProv.getDatiProvinciali().setProvSospensione(laSospensione);
			ProvGradualita laGradualita = new ProvGradualita();
			prospettoProv.getDatiProvinciali().setProvGradualita(laGradualita);
			if (obj[0] != null) {
//				cod_provincia
				prospettoProv.getProvincia().setCodProvinciaMin((String) obj[0]);
			}
			if (obj[1] != null) {
//				Descrizione provincia
				prospettoProv.getProvincia().setDsProTProvincia((String) obj[1]);
			}
			if (obj[2] != null) {
//				Numero dipendenti provincia
				prospettoProv.getDatiProvinciali().setnTotaleLavoratDipendenti((BigDecimal) obj[2]);
			}

//			if (obj[3] != null) {
////				Base di computo Provinciale
//				prospettoProv.getRiepilogoProvinciale().setBaseComputoArt3((BigDecimal) obj[3]);
//			}
			if (obj[3] != null) {
//				Base computo Art.3
				prospettoProv.getRiepilogoProvinciale().setBaseComputoArt3((BigDecimal) obj[3]);
			}
			if (obj[4] != null) {
//				Base computo Art.18
				prospettoProv.getRiepilogoProvinciale().setBaseComputoArt18((BigDecimal) obj[4]);
			}
			if (obj[5] != null) {
//				Numero Posizioni esonerate
				prospettoProv.getRiepilogoProvinciale().setNumPosizioniEsonerate((BigDecimal) obj[5]);
			}
			if (obj[6] != null) {
//				Numero Lavoratori in sospensione
				prospettoProv.getRiepilogoProvinciale().setNumLavoratoriSospensione((BigDecimal) obj[6]);
			}
			if (obj[7] != null) {
//				Numero compensazioni disabili
				prospettoProv.getRiepilogoProvinciale().setNumCompensazioneDisabili((BigDecimal) obj[7]);
			}
			if (obj[8] != null) {
//				Flag Autocompensazioni
				prospettoProv.getDatiProvinciali().setFlgAutocompensazioni((String) obj[8]);
			}
			if (obj[9] != null) {
//				Numero esoneri autocertificati
				prospettoProv.getDatiProvinciali().getProvEsoneroAutocert().setnLavEsoneroAutocert((BigDecimal) obj[9]);
			}
			if (obj[10] != null) {
//				Numero esoneri
				prospettoProv.getDatiProvinciali().getProvEsonero().setnLavoratoriEsonero((BigDecimal) obj[10]);
			}
			if (obj[11] != null) {
//				Categoria compensazione Disabili
				prospettoProv.getRiepilogoProvinciale().setCatCompensazioneDisabili((String) obj[11]);
			}
			if (obj[12] != null) {
//				Numero compensazioni Categore protette
				prospettoProv.getRiepilogoProvinciale().setNumCompensazioniCateProt((BigDecimal) obj[12]);
			}
			if (obj[13] != null) {
//				Categoria compensazione Categoria Protetta
				prospettoProv.getRiepilogoProvinciale().setCatCompensazioneCateProt((String) obj[13]);
			}
			if (obj[14] != null) {
//				Convenzione - id stato concessione
				prospettoProv.getDatiProvinciali().getProvConvenzione().setId(((BigDecimal) obj[14]).intValue());
			}
			if (obj[15] != null) {
//				Numero assunti in gradualità
				prospettoProv.getDatiProvinciali().getProvGradualita().setnAssunzioniEffDopoTrasf((BigDecimal) obj[15]);
			}
			if (obj[16] != null) {
//				Flg sospensione per mobilita Q1
				ilProspetto.setFlgSospensionePerMobilita((String) obj[16]);
			}
			if (obj[17] != null) {
//				Sospensione Provinciale – id stato concessione
				prospettoProv.getDatiProvinciali().getProvSospensione().setId(((BigDecimal) obj[17]).intValue());
			}
			if (obj[18] != null) {
//				Esonero - Percentuale
				prospettoProv.getDatiProvinciali().getProvEsonero().setPercentuale((BigDecimal) obj[18]);
			}
			if (obj[19] != null) {
//				Scoperture Disabili
				prospettoProv.getRiepilogoProvinciale().setNumScopertureDisabili((BigDecimal) obj[19]);
			}
			if (obj[20] != null) {
//				Scoperture Disabili Reale
				prospettoProv.getRiepilogoProvinciale().setNumScopertureDisabiliReali((BigDecimal) obj[20]);
			}
			if (obj[21] != null) {
//				Scoperture Categorie Protette
				prospettoProv.getRiepilogoProvinciale().setNumScopertureCatProt((BigDecimal) obj[21]);
			}
			if (obj[22] != null) {
//				Scoperture Categorie Protette Reale
				prospettoProv.getRiepilogoProvinciale().setNumScopertureCatProtReali((BigDecimal) obj[22]);
			}
			if (obj[23] != null) {
//				Quota Riserva Disabili
				prospettoProv.getRiepilogoProvinciale().setQuotaRiservaDisabili((BigDecimal) obj[23]);
			}
			if (obj[24] != null) {
//				Quota Riserva Categorie Protette
				prospettoProv.getRiepilogoProvinciale().setQuotaRiservaArt18((BigDecimal) obj[24]);
			}
			if (obj[25] != null) {
//				Disabili In Forza
				prospettoProv.getRiepilogoProvinciale().setNumDisabiliInForza((BigDecimal) obj[25]);
			}
			if (obj[26] != null) {
//				Categorie Protette In Forza Contati come Disabili
				prospettoProv.getRiepilogoProvinciale().setNumCatProtInForzaContDis((BigDecimal) obj[26]);
			}
			if (obj[27] != null) {
//				Categorie Protette In Forza
				prospettoProv.getRiepilogoProvinciale().setNumCatProtInForza((BigDecimal) obj[27]);
			}
			if (obj[28] != null) {
//				CALCOLO_ORARIO
				prospettoProv.getRiepilogoProvinciale().setNumMinOrarioSettimanaleContrattualeLav((BigDecimal) obj[28]);
			}
			laListaDeiProspettiProvincia.add(prospettoProv);
		}
		ilProspetto.setProspettoProvincias(laListaDeiProspettiProvincia);
		return ilProspetto;
	}

	@Override
	public Prospetto findCompensazioniByIdProspettoProv(Long idProspettoProv) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T extends BaseEntity<Long>> BaseEntity<Long> getTfromMin(Class<T> transcodifica, String fieldValue,
			Date dataRiferimento) {

		if (fieldValue == null || fieldValue.trim().equals("")) {
			return null;
		}

		String minFieldName = null;

//		Table t = transcodifica.getAnnotation(Table.class);
//	    String tableName = t.name();
		StringBuilder sql = new StringBuilder();
//	    sql.append("SELECT * FROM ").append(tableName);
//		sql.append("  WHERE ");
//		sql.append(minFieldName).append(" = :fieldValue ");
//		sql.append(" AND (dt_Inizio IS NULL OR (dt_Inizio IS NOT NULL and dt_Inizio <= :dataRif ) ) ");
//		sql.append(" AND (dt_Fine   IS NULL OR (dt_Fine   IS NOT NULL and dt_Fine   >= :dataRif ) ) ");

		sql.append("FROM ").append(transcodifica.getName());
		sql.append("  WHERE ");
		if (transcodifica == ProTAssunzioneProtetta.class) {
			sql.append(" codAssunzioneProtetta = :fieldValue ");
			sql.append(" AND (dataInizio IS NULL OR (dataInizio IS NOT NULL and dataInizio <= :dataRif ) ) ");
			sql.append(" AND (dataFine   IS NULL OR (dataFine   IS NOT NULL and dataFine   >= :dataRif ) ) ");
		} else if (transcodifica == ProTAtecofin.class) {
			sql.append(" codAtecofinMin = :fieldValue ");
			sql.append(" AND (dtInizio IS NULL OR (dtInizio IS NOT NULL and dtInizio <= :dataRif ) ) ");
			sql.append(" AND (dtFine   IS NULL OR (dtFine   IS NOT NULL and dtFine   >= :dataRif ) ) ");
		} else if (transcodifica == ProTCategoriaAzienda.class) {
			sql.append(" codCategoriaAzienda = :fieldValue ");
			sql.append(" AND (dataInizio IS NULL OR (dataInizio IS NOT NULL and dataInizio <= :dataRif ) ) ");
			sql.append(" AND (dataFine   IS NULL OR (dataFine   IS NOT NULL and dataFine   >= :dataRif ) ) ");
		} else if (transcodifica == ProTCategoriaEscluse.class) {
			sql.append(" codCategoriaEscluse = :fieldValue ");
			sql.append(" AND (dataInizio IS NULL OR (dataInizio IS NOT NULL and dataInizio <= :dataRif ) ) ");
			sql.append(" AND (dataFine   IS NULL OR (dataFine   IS NOT NULL and dataFine   >= :dataRif ) ) ");
		} else if (transcodifica == ProTCausaSospensione.class) {
			sql.append(" codCausaSospensione = :fieldValue ");
			sql.append(" AND (dataInizio IS NULL OR (dataInizio IS NOT NULL and dataInizio <= :dataRif ) ) ");
			sql.append(" AND (dataFine   IS NULL OR (dataFine   IS NOT NULL and dataFine   >= :dataRif ) ) ");
		} else if (transcodifica == ProTCcnl.class) {
			sql.append(" codCcnlMin = :fieldValue ");
			sql.append(" AND (dtInizio IS NULL OR (dtInizio IS NOT NULL and dtInizio <= :dataRif ) ) ");
			sql.append(" AND (dtFine   IS NULL OR (dtFine   IS NOT NULL and dtFine   >= :dataRif ) ) ");
		} else if (transcodifica == ProTComune.class) {
			sql.append(" codComuneMin = :fieldValue ");
			sql.append(" AND (dtInizio IS NULL OR (dtInizio IS NOT NULL and dtInizio <= :dataRif ) ) ");
			sql.append(" AND (dtFine   IS NULL OR (dtFine   IS NOT NULL and dtFine   >= :dataRif ) ) ");
		} else if (transcodifica == ProTComunicazione.class) {
			sql.append(" codComunicazione = :fieldValue ");
			sql.append(" AND (dataInizio IS NULL OR (dataInizio IS NOT NULL and dataInizio <= :dataRif ) ) ");
			sql.append(" AND (dataFine   IS NULL OR (dataFine   IS NOT NULL and dataFine   >= :dataRif ) ) ");
		} else if (transcodifica == ProTContratti.class) {
			sql.append(" codTipoContrattoMin = :fieldValue ");
			sql.append(" AND (dtInizio IS NULL OR (dtInizio IS NOT NULL and dtInizio <= :dataRif ) ) ");
			sql.append(" AND (dtFine   IS NULL OR (dtFine   IS NOT NULL and dtFine   >= :dataRif ) ) ");
		} else if (transcodifica == ProTCpi.class) {
			sql.append(" codCpi = :fieldValue ");
			sql.append(" AND (dtInizio IS NULL OR (dtInizio IS NOT NULL and dtInizio <= :dataRif ) ) ");
			sql.append(" AND (dtFine   IS NULL OR (dtFine   IS NOT NULL and dtFine   >= :dataRif ) ) ");
		} else if (transcodifica == ProTDichiarante.class) {
			sql.append(" codDichiarante = :fieldValue ");
			sql.append(" AND (dataInizio IS NULL OR (dataInizio IS NOT NULL and dataInizio <= :dataRif ) ) ");
			sql.append(" AND (dataFine   IS NULL OR (dataFine   IS NOT NULL and dataFine   >= :dataRif ) ) ");
		} else if (transcodifica == ProTIstat2001livello5.class) {
			sql.append(" codIstat2001livello5Min = :fieldValue ");
			sql.append(" AND (dtInizio IS NULL OR (dtInizio IS NOT NULL and dtInizio <= :dataRif ) ) ");
			sql.append(" AND (dtFine   IS NULL OR (dtFine   IS NOT NULL and dtFine   >= :dataRif ) ) ");
		} else if (transcodifica == ProTProvincia.class) {
			sql.append(" codProvinciaMin = :fieldValue ");
			sql.append(" AND (dtInizio IS NULL OR (dtInizio IS NOT NULL and dtInizio <= :dataRif ) ) ");
			sql.append(" AND (dtFine   IS NULL OR (dtFine   IS NOT NULL and dtFine   >= :dataRif ) ) ");
		} else if (transcodifica == ProTRegione.class) {
			sql.append(" codRegioneMin = :fieldValue ");
			sql.append(" AND (dtInizio IS NULL OR (dtInizio IS NOT NULL and dtInizio <= :dataRif ) ) ");
			sql.append(" AND (dtFine   IS NULL OR (dtFine   IS NOT NULL and dtFine   >= :dataRif ) ) ");
		} else if (transcodifica == ProTSoggetti.class) {
			sql.append(" codSoggetti = :fieldValue ");
			sql.append(" AND (dataInizio IS NULL OR (dataInizio IS NOT NULL and dataInizio <= :dataRif ) ) ");
			sql.append(" AND (dataFine   IS NULL OR (dataFine   IS NOT NULL and dataFine   >= :dataRif ) ) ");
		} else if (transcodifica == ProTStatiEsteri.class) {
			sql.append(" codNazioneMin = :fieldValue ");
			sql.append(" AND (dtInizio IS NULL OR (dtInizio IS NOT NULL and dtInizio <= :dataRif ) ) ");
			sql.append(" AND (dtFine   IS NULL OR (dtFine   IS NOT NULL and dtFine   >= :dataRif ) ) ");
		} else if (transcodifica == ProTStatoConcessione.class) {
			sql.append(" codStatoConcessione = :fieldValue ");
			sql.append(" AND (dataInizio IS NULL OR (dataInizio IS NOT NULL and dataInizio <= :dataRif ) ) ");
			sql.append(" AND (dataFine   IS NULL OR (dataFine   IS NOT NULL and dataFine   >= :dataRif ) ) ");
		} else if (transcodifica == ProTTipoRipropPt.class) {
			sql.append(" dsMin = :fieldValue ");
			sql.append(" AND (dtInizio IS NULL OR (dtInizio IS NOT NULL and dtInizio <= :dataRif ) ) ");
			sql.append(" AND (dtFine   IS NULL OR (dtFine   IS NOT NULL and dtFine   >= :dataRif ) ) ");
		}

		Map<String, Object> params = new HashMap<String, Object>();
		if (dataRiferimento == null) {
			dataRiferimento = new Date();
		}
		params.put("dataRif", dataRiferimento);
		params.put("fieldValue", fieldValue);

		TypedQuery<T> query = composeTypedQuery(sql, params, transcodifica);

		T ret = null;
		try {
			ret = query.getSingleResult();
		} catch (Exception e) {
			log.warn("transcodifica:" + transcodifica.getName() + "fieldValue:" + fieldValue + " dataRif:"
					+ dataRiferimento, e);
		}

		return ret;
	}

	public Long getIdProspettoByCodiceComunicazione(String codiceComunicazione) {

		Map<String, Object> params = new HashMap<>();

		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ProDProspetto t ");
		jpql.append(" WHERE 1=1 ");
		jpql.append(" AND CODICE_COMUNICAZIONE= :codiceComunicazione");

		params.put("codiceComunicazione", codiceComunicazione);

		TypedQuery<ProDProspetto> query = null;
		try {
			query = composeTypedQuery(jpql, params);
		} catch (Exception e) {
			return null;
		}

		List<ProDProspetto> listaRisultati = query.getResultList();

		if (listaRisultati == null || listaRisultati.isEmpty()) {
			return null;
		}

		return query.getResultList().get(0).getIdProspetto();

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
