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
package it.csi.prodis.prodisweb.ejb.mapper;

import org.mapstruct.factory.Mappers;

/**
 * Mappers for Prodis
 */
public final class ProdisMappers {

	/** Private constructor */
	private ProdisMappers() {
		// Prevent instantiation
	}

	public static final ProvinciaMapper PROVINCIA = Mappers.getMapper(ProvinciaMapper.class);
	public static final ComuneMapper COMUNE = Mappers.getMapper(ComuneMapper.class);
	public static final RegioneMapper REGIONE = Mappers.getMapper(RegioneMapper.class);
	
	public static final ProspettoMapper PROSPETTO = Mappers.getMapper(ProspettoMapper.class);
	public static final AssPubblicheMapper ASS_PUBBLICHE = Mappers.getMapper(AssPubblicheMapper.class);
	public static final AssPubblichePKMapper ASS_PUBBLICHE_PK = Mappers.getMapper(AssPubblichePKMapper.class);
	public static final CategorieEscluseMapper CATEGORIE_ESCLUSE = Mappers.getMapper(CategorieEscluseMapper.class);
	public static final DatiAziendaMapper DATI_AZIENDA = Mappers.getMapper(DatiAziendaMapper.class);
	public static final DatiProvincialiMapper DATI_PROVINCIALI = Mappers.getMapper(DatiProvincialiMapper.class);
	public static final ImportErroriMapper IMPORT_ERRORI = Mappers.getMapper(ImportErroriMapper.class);
	public static final LavoratoriInForzaMapper LAVORATORI_IN_FORZA = Mappers.getMapper(LavoratoriInForzaMapper.class);
	public static final LavoratoriSilpMapper LAVORATORI_SILP = Mappers.getMapper(LavoratoriSilpMapper.class);
	public static final LavoratoriSilpPKMapper LAVORATORI_SILP_PK = Mappers.getMapper(LavoratoriSilpPKMapper.class);
	public static final ParametriMapper PARAMETRI = Mappers.getMapper(ParametriMapper.class);
	public static final PartTimeMapper PART_TIME = Mappers.getMapper(PartTimeMapper.class);
	public static final PdfProspettoMapper PDF_PROSPETTO = Mappers.getMapper(PdfProspettoMapper.class);
	public static final PdfSilpMapper PDF_SILP = Mappers.getMapper(PdfSilpMapper.class);
	public static final PostiLavoroDispMapper POSTI_LAVORO_DISP = Mappers.getMapper(PostiLavoroDispMapper.class);
	public static final ProspettoGradualitaMapper PROSPETTO_GRADUALITA = Mappers.getMapper(ProspettoGradualitaMapper.class);
	public static final ProspettoProvSedeMapper PROSPETTO_PROV_SEDE = Mappers.getMapper(ProspettoProvSedeMapper.class);
	public static final ProvCompensazioniMapper PROV_COMPENSAZIONI = Mappers.getMapper(ProvCompensazioniMapper.class);
	public static final ProvConvenzioneMapper PROV_CONVENZIONE = Mappers.getMapper(ProvConvenzioneMapper.class);
	public static final ProvEsoneroMapper PROV_ESONERO = Mappers.getMapper(ProvEsoneroMapper.class);
	public static final ProvEsoneroAutocertMapper PROV_ESONERO_AUTOCERT = Mappers.getMapper(ProvEsoneroAutocertMapper.class);
	public static final ProvGradualitaMapper PROV_GRADUALITA = Mappers.getMapper(ProvGradualitaMapper.class);
	public static final ProvIntermittentiMapper PROV_INTERMITTENTI = Mappers.getMapper(ProvIntermittentiMapper.class);
	public static final ProvSospensioneMapper PROV_SOSPENSIONE = Mappers.getMapper(ProvSospensioneMapper.class);
	public static final RiepilogoNazionaleMapper RIEPILOGO_NAZIONALE = Mappers.getMapper(RiepilogoNazionaleMapper.class);
	public static final RiepilogoProvincialeMapper RIEPILOGO_PROVINCIALE = Mappers.getMapper(RiepilogoProvincialeMapper.class);
	public static final SedeLegaleMapper SEDE_LEGALE = Mappers.getMapper(SedeLegaleMapper.class);
	public static final UserAccessLogMapper USER_ACCESS_LOG = Mappers.getMapper(UserAccessLogMapper.class);
	public static final ProspettoProvinciaMapper PROSPETTO_PROVINCIA = Mappers.getMapper(ProspettoProvinciaMapper.class);
	public static final AssunzioneProtettaMapper ASSUNZIONE_PROTETTA = Mappers.getMapper(AssunzioneProtettaMapper.class);
	public static final AtecofinMapper ATECOFIN = Mappers.getMapper(AtecofinMapper.class);
	public static final CategoriaAziendaMapper CATEGORIA_AZIENDA = Mappers.getMapper(CategoriaAziendaMapper.class);
	public static final CategoriaEscluseMapper CATEGORIA_ESCLUSE = Mappers.getMapper(CategoriaEscluseMapper.class);
	public static final CausaSospensioneMapper CAUSA_SOSPENSIONE = Mappers.getMapper(CausaSospensioneMapper.class);
	public static final CcnlMapper CCNL = Mappers.getMapper(CcnlMapper.class);
	public static final ComunicazioneMapper COMUNICAZIONE = Mappers.getMapper(ComunicazioneMapper.class);
	public static final ContrattiMapper CONTRATTI = Mappers.getMapper(ContrattiMapper.class);
	public static final CpiMapper CPI = Mappers.getMapper(CpiMapper.class);
	public static final DichiaranteMapper DICHIARANTE = Mappers.getMapper(DichiaranteMapper.class);
	public static final ImportErroriSpicomMapper IMPORT_ERRORI_SPICOM = Mappers.getMapper(ImportErroriSpicomMapper.class);
	public static final Istat2001livello5Mapper ISTAT2001LIVELLO5 = Mappers.getMapper(Istat2001livello5Mapper.class);
	public static final SoggettiMapper SOGGETTI = Mappers.getMapper(SoggettiMapper.class);
	public static final StatiEsteriMapper STATI_ESTERI = Mappers.getMapper(StatiEsteriMapper.class);
	public static final StatoConcessioneMapper STATO_CONCESSIONE = Mappers.getMapper(StatoConcessioneMapper.class);
	public static final StatoProspettoMapper STATO_PROSPETTO = Mappers.getMapper(StatoProspettoMapper.class);
	public static final StatoVerificaMapper STATO_VERIFICA = Mappers.getMapper(StatoVerificaMapper.class);
	public static final TipoRipropPtMapper TIPO_RIPROP_PT = Mappers.getMapper(TipoRipropPtMapper.class);
	public static final VistaElencoProvQ2Mapper VISTA_ELENCO_PROV_Q2 = Mappers.getMapper(VistaElencoProvQ2Mapper.class);
}
