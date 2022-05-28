/* ---------------------------------------------------------------------- */
/* Add table "PRO_T_CPI"                                                  */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_T_CPI (
    ID_T_CPI NUMBER(5) NOT NULL,
    DS_PRO_T_CPI VARCHAR2(50),
    COD_CPI VARCHAR2(10),
    C_UTENTE VARCHAR2(20),
    DT_INIZIO DATE,
    DT_FINE DATE,
    DS_MAIL VARCHAR2(200),
    PRIMARY KEY (ID_T_CPI)
);

/* ---------------------------------------------------------------------- */
/* Add table "PRO_T_CATEGORIA_AZIENDA"                                    */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_T_CATEGORIA_AZIENDA (
    ID_T_CATEGORIA_AZIENDA NUMBER(4) NOT NULL,
    COD_CATEGORIA_AZIENDA VARCHAR2(1),
    DES_CATEGORIA_AZIENDA VARCHAR2(100),
    DATA_INIZIO DATE,
    DATA_FINE DATE,
    DATA_TMST DATE,
    PRIMARY KEY (ID_T_CATEGORIA_AZIENDA)
);

/* ---------------------------------------------------------------------- */
/* Add table "PRO_T_STATO_PROSPETTO"                                      */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_T_STATO_PROSPETTO (
    ID_T_STATO_PROSPETTO NUMBER(4) NOT NULL,
    DESCRIZIONE VARCHAR2(100),
    PRIMARY KEY (ID_T_STATO_PROSPETTO)
);

/* ---------------------------------------------------------------------- */
/* Add table "PRO_T_REGIONE"                                              */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_T_REGIONE (
    ID_T_REGIONE NUMBER(5) NOT NULL,
    COD_REGIONE_MIN VARCHAR2(2) NOT NULL,
    DS_PRO_T_REGIONE VARCHAR2(50),
    COD_AMBITO_DIFFUSIONE VARCHAR2(1),
    DS_AMBITO_DIFFUSIONE VARCHAR2(50),
    COD_MOBILITAGEOG VARCHAR2(2),
    DT_INIZIO DATE,
    DT_FINE DATE,
    DT_TMST DATE,
    PRIMARY KEY (ID_T_REGIONE)
);

/* ---------------------------------------------------------------------- */
/* Add table "PRO_T_STATO_CONCESSIONE"                                    */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_T_STATO_CONCESSIONE (
    ID_T_STATO_CONCESSIONE NUMBER(4) NOT NULL,
    COD_STATO_CONCESSIONE VARCHAR2(1),
    DESC_STATO_CONCESSIONE VARCHAR2(100),
    DATA_INIZIO DATE,
    DATA_FINE DATE,
    DATA_TMST DATE,
    PRIMARY KEY (ID_T_STATO_CONCESSIONE)
);

/* ---------------------------------------------------------------------- */
/* Add table "PRO_T_CONTRATTI"                                            */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_T_CONTRATTI (
    ID_T_CONTRATTO NUMBER(5) NOT NULL,
    DS_CONTRATTO VARCHAR2(150),
    STATUS VARCHAR2(1),
    DT_INIZIO DATE,
    DT_FINE DATE,
    DT_TMST DATE,
    COD_TIPO_CONTRATTO_MIN VARCHAR2(7),
    TIPO VARCHAR2(3),
    FLG_FORMA VARCHAR2(1),
    TIPOLOGIA_RAPPORTO VARCHAR2(1),
    PRIMARY KEY (ID_T_CONTRATTO)
);

COMMENT ON COLUMN PRO_T_CONTRATTI.TIPOLOGIA_RAPPORTO IS 'Valori possibili:
''D'' - Avviamento subordinato
''A'' - Lavoro autonomo
''S'' - Rapporto Speciale';

/* ---------------------------------------------------------------------- */
/* Add table "PRO_T_ASSUNZIONE_PROTETTA"                                  */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_T_ASSUNZIONE_PROTETTA (
    ID_T_ASSUNZIONE_PROTETTA NUMBER(4) NOT NULL,
    COD_ASSUNZIONE_PROTETTA VARCHAR2(1),
    DESC_ASSUNZIONE_PROTETTA VARCHAR2(100),
    DATA_INIZIO DATE,
    DATA_FINE DATE,
    DATA_TMST DATE,
    FLG_CONVENSIONE VARCHAR2(1),
    PRIMARY KEY (ID_T_ASSUNZIONE_PROTETTA),
    CHECK (FLG_CONVENSIONE IN ('S','N'))
);

/* ---------------------------------------------------------------------- */
/* Add table "PRO_D_PARAMETRI"                                            */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_D_PARAMETRI (
    DS_NOME VARCHAR2(100) NOT NULL,
    DS_VALORE VARCHAR2(500) NOT NULL,
    DS_DESCRIZIONE VARCHAR2(1000)
);

/* ---------------------------------------------------------------------- */
/* Add table "PRO_T_CCNL"                                                 */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_T_CCNL (
    ID_T_CCNL NUMBER(5) NOT NULL,
    SETTORE VARCHAR2(100),
    DS_CCNL VARCHAR2(1500),
    DT_INIZIO DATE,
    DT_FINE DATE,
    DT_TMST DATE,
    COD_CCNL_MIN VARCHAR2(5),
    ID_NEW_CCNL NUMBER(5),
    ID_T_CCNL_PREV NUMBER(5),
    PRIMARY KEY (ID_T_CCNL)
);

CREATE INDEX IE_PRO_T_CCNL_02 ON PRO_T_CCNL (COD_CCNL_MIN ASC);

/* ---------------------------------------------------------------------- */
/* Add table "PRO_D_USER_ACCESS_LOG"                                      */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_D_USER_ACCESS_LOG (
    ID_PRO_D_USER_ACCESS_LOG NUMBER(7) NOT NULL,
    DS_RUOLO VARCHAR2(50),
    FLG_TROVATO_SU_AAEP VARCHAR2(1),
    DS_NOME VARCHAR2(50),
    DS_COGNOME VARCHAR2(50),
    CF_UTENTE VARCHAR2(16),
    DT_EVENTO DATE,
    PRIMARY KEY (ID_PRO_D_USER_ACCESS_LOG)
);

/* ---------------------------------------------------------------------- */
/* Add table "PRO_T_COMUNICAZIONE"                                        */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_T_COMUNICAZIONE (
    ID_T_COMUNICAZIONE NUMBER(4) NOT NULL,
    COD_COMUNICAZIONE VARCHAR2(2),
    DESC_COMUNICAZIONE VARCHAR2(100),
    DATA_INIZIO DATE,
    DATA_FINE DATE,
    DATA_TMST DATE,
    PRIMARY KEY (ID_T_COMUNICAZIONE)
);

/* ---------------------------------------------------------------------- */
/* Add table "PRO_T_DICHIARANTE"                                          */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_T_DICHIARANTE (
    ID_T_DICHIARANTE NUMBER(4) NOT NULL,
    COD_DICHIARANTE VARCHAR2(1),
    DESC_DICHIARANTE VARCHAR2(100),
    DATA_INIZIO DATE,
    DATA_FINE DATE,
    DATA_TMST DATE,
    PRIMARY KEY (ID_T_DICHIARANTE)
);

/* ---------------------------------------------------------------------- */
/* Add table "PRO_T_STATI_ESTERI"                                         */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_T_STATI_ESTERI (
    ID_T_STATI_ESTERI NUMBER(5) NOT NULL,
    DS_STATI_ESTERI VARCHAR2(50),
    COD_NAZIONE_MIN VARCHAR2(4),
    DT_INIZIO DATE,
    DT_FINE DATE,
    DT_TMST DATE,
    SIGLA_NAZIONE VARCHAR2(3),
    FLG_UE VARCHAR2(1) NOT NULL,
    PRIMARY KEY (ID_T_STATI_ESTERI),
    CHECK (FLG_UE IN ('S', 'N'))
);

/* ---------------------------------------------------------------------- */
/* Add table "PRO_T_PROVINCIA"                                            */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_T_PROVINCIA (
    ID_T_PROVINCIA NUMBER(5) NOT NULL,
    ID_T_REGIONE NUMBER(5),
    DS_PRO_T_PROVINCIA VARCHAR2(50),
    DS_TARGA VARCHAR2(20),
    COD_PROVINCIA_MIN VARCHAR2(3),
    DT_INIZIO DATE,
    DT_FINE DATE,
    DT_TMST DATE,
    PRIMARY KEY (ID_T_PROVINCIA)
);

/* ---------------------------------------------------------------------- */
/* Add table "PRO_T_ATECOFIN"                                             */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_T_ATECOFIN (
    ID_T_ATECOFIN NUMBER(5) NOT NULL,
    DS_PRO_T_ATECOFIN VARCHAR2(250),
    DT_INIZIO DATE,
    DT_FINE DATE,
    COD_ATECOFIN_MIN VARCHAR2(8),
    DT_TMST DATE,
    ID_NEW_ATECOFIN NUMBER(5),
    PRIMARY KEY (ID_T_ATECOFIN)
);

/* ---------------------------------------------------------------------- */
/* Add table "PRO_T_ISTAT2001LIVELLO5"                                    */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_T_ISTAT2001LIVELLO5 (
    ID_T_ISTAT2001LIVELLO5 NUMBER(5) NOT NULL,
    DS_COM_ISTAT2001LIVELLO5 VARCHAR2(300),
    DT_INIZIO DATE,
    DT_FINE DATE,
    DT_TMST DATE,
    COD_ISTAT2001LIVELLO5_MIN VARCHAR2(12),
    ID_NEW_ISTAT NUMBER(5),
    FLG_VLD_UD VARCHAR2(1),
    PRIMARY KEY (ID_T_ISTAT2001LIVELLO5)
);

/* ---------------------------------------------------------------------- */
/* Add table "PRO_T_SOGGETTI"                                             */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_T_SOGGETTI (
    ID_T_SOGGETTI NUMBER(4) NOT NULL,
    COD_SOGGETTI VARCHAR2(3),
    DESC_SOGGETTI VARCHAR2(100),
    DATA_INIZIO DATE,
    DATA_FINE DATE,
    DATA_TMST DATE,
    PRIMARY KEY (ID_T_SOGGETTI)
);

/* ---------------------------------------------------------------------- */
/* Add table "PRO_T_CAUSA_SOSPENSIONE"                                    */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_T_CAUSA_SOSPENSIONE (
    ID_T_CAUSA_SOSPENSIONE NUMBER(4) NOT NULL,
    COD_CAUSA_SOSPENSIONE VARCHAR2(1),
    DES_CAUSA_SOSPENSIONE VARCHAR2(100),
    DATA_INIZIO DATE,
    DATA_FINE DATE,
    DATA_TMST DATE,
    PRIMARY KEY (ID_T_CAUSA_SOSPENSIONE)
);

/* ---------------------------------------------------------------------- */
/* Add table "PRO_T_CATEGORIA_ESCLUSE"                                    */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_T_CATEGORIA_ESCLUSE (
    ID_T_CATEGORIA_ESCLUSE NUMBER(4) NOT NULL,
    COD_CATEGORIA_ESCLUSE VARCHAR2(4),
    DES_CATEGORIA_ESCLUSE VARCHAR2(100),
    DATA_INIZIO DATE,
    DATA_FINE DATE,
    DATA_TMST DATE,
    AMBITO_CATEGORIA VARCHAR2(1),
    PRIMARY KEY (ID_T_CATEGORIA_ESCLUSE),
    CHECK (AMBITO_CATEGORIA IN ('C', 'D', 'E'))
);

COMMENT ON COLUMN PRO_T_CATEGORIA_ESCLUSE.AMBITO_CATEGORIA IS 'Rappresenta l''ambito al quale si quale si può applicare la singola categoria; i possibili valori sono:
- ''C'' - per le Categorie protette
- ''D'' - per i Disabili
- ''E'' - per Entrambe le categorie';

/* ---------------------------------------------------------------------- */
/* Add table "PRO_T_STATO_VERIFICA"                                       */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_T_STATO_VERIFICA (
    ID_T_STATO_VERIFICA NUMBER(4) NOT NULL,
    DESCRIZIONE VARCHAR2(100),
    PRIMARY KEY (ID_T_STATO_VERIFICA)
);

/* ---------------------------------------------------------------------- */
/* Add table "PRO_T_IMPORT_ERRORI_SPICOM"                                 */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_T_IMPORT_ERRORI_SPICOM (
    COD_ERRORE NUMBER NOT NULL,
    DESC_ERRORE VARCHAR2(200),
    PRIMARY KEY (COD_ERRORE)
);

/* ---------------------------------------------------------------------- */
/* Add table "PRO_T_TIPO_RIPROP_PT"                                       */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_T_TIPO_RIPROP_PT (
    ID_TIPO_RIPROP_PT NUMBER(4) NOT NULL,
    DS_TIPO_RIPROP_PT VARCHAR2(100) NOT NULL,
    AMBITO_RIPROP VARCHAR2(1) NOT NULL,
    AMBITO_RIPROP_BC VARCHAR2(1) NOT NULL,
    DS_MIN VARCHAR2(50) NOT NULL,
    DT_INIZIO DATE NOT NULL,
    DT_FINE DATE,
    PRIMARY KEY (ID_TIPO_RIPROP_PT)
);

COMMENT ON TABLE PRO_T_TIPO_RIPROP_PT IS 'Tabella DI decodifica per elencare categorie di assunzione protetta per le quali distinguere  il FT dal PT:';

COMMENT ON COLUMN PRO_T_TIPO_RIPROP_PT.ID_TIPO_RIPROP_PT IS 'Id tabella';

COMMENT ON COLUMN PRO_T_TIPO_RIPROP_PT.DS_TIPO_RIPROP_PT IS 'Descrizione della categoria di assunzione protetta a PT';

COMMENT ON COLUMN PRO_T_TIPO_RIPROP_PT.AMBITO_RIPROP IS 'Flag che identifica il tipo di riproporzionamento richiesto per il calcolo del campo "Disabili in forza".
Vale = R (Riproporzionato) / E (Escluso dal riproporz per Disabili in forza) . In qst caso il riproporz è fatto solo sui Disabili PT con orario lavoro svolto <= 50% dell''orario contrattuale.';

COMMENT ON COLUMN PRO_T_TIPO_RIPROP_PT.AMBITO_RIPROP_BC IS 'Flag che identifica il tipo di riproporzionamento richiesto per il calcolo del campo "Base di computo". Vale = R (Riproporzionato) / E (Escluso dal riproporz per BC / I (Intero, non si riproporziona ma si considerà l''unità)) . In qst caso il riproporz è fatto su tutte le tipologie di PT.';

COMMENT ON COLUMN PRO_T_TIPO_RIPROP_PT.DS_MIN IS 'Descrizione ministeriale del campo su xsd per identificare il tipo di dato da riproporzionare';

COMMENT ON COLUMN PRO_T_TIPO_RIPROP_PT.DT_INIZIO IS 'Data inizio validità';

COMMENT ON COLUMN PRO_T_TIPO_RIPROP_PT.DT_FINE IS 'Data fine validità';

/* ---------------------------------------------------------------------- */
/* Add table "PRO_D_PDF_SILP"                                             */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_D_PDF_SILP (
    ID_PDF_SILP NUMBER(9) NOT NULL,
    ANNO_PROT NUMBER(4),
    NUM_PROT NUMBER(9),
    ID_T_PROVINCIA_PROT NUMBER(5),
    DATA_PROT DATE,
    CF_AZIENDA VARCHAR2(16),
    DENOMINAZIONE_DATORE_LAVORO VARCHAR2(100),
    DATA_RIFERIMENTO_PROSPETTO DATE,
    PROSPETTO_PDF BLOB,
    CODICE_REGIONALE VARCHAR2(16),
    PRIMARY KEY (ID_PDF_SILP)
);

/* ---------------------------------------------------------------------- */
/* Add table "PRO_D_PROSPETTO"                                            */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_D_PROSPETTO (
    ID_PROSPETTO NUMBER(9) NOT NULL,
    ANNO_PROTOCOLLO NUMBER(4),
    NUMERO_PROTOCOLLO NUMBER(9),
    DATA_PROTOCOLLO DATE,
    DATA_TIMBRO_POSTALE DATE,
    ID_T_STATO_PROSPETTO NUMBER(4) NOT NULL,
    CF_STUDIO_PROFESSIONALE VARCHAR2(16),
    ID_PROSPETTO_PRECEDENTE NUMBER,
    DATA_RIFERIMENTO_PROSPETTO DATE,
    NUM_LAVOR_IN_FORZA_NAZIONALE NUMBER(6),
    ID_T_CATEGORIA_AZIENDA NUMBER(4),
    DATA_PRIMA_ASSUNZIONE DATE,
    DATA_SECONDA_ASSUNZIONE DATE,
    FLG_NESSUNA_ASSUNZIONE_AGGIUN VARCHAR2(1),
    FLG_GRADUALITA VARCHAR2(1),
    FLG_SOSPENSIONE_PER_MOBILITA VARCHAR2(1),
    FLG_ASSUNZIONI_PUBB_SELEZIONE VARCHAR2(1),
    NOTE VARCHAR2(2000),
    DATA_INVIO DATE,
    CF_COMUNICAZIONE VARCHAR2(16),
    EMAIL_SOGGETTO_COMUNICAZIONE VARCHAR2(80),
    ID_T_COMUNICAZIONE NUMBER(4) NOT NULL,
    CODICE_COMUNICAZIONE VARCHAR2(16),
    CODICE_COMUNICAZIONE_PRECED VARCHAR2(16),
    ID_T_SOGGETTI NUMBER(4),
    ID_T_STATO_VERIFICA NUMBER(4),
    FLG_INVIO_MINISTERO VARCHAR2(1),
    EMAIL_NOTIFICA VARCHAR2(80),
    COD_USER_INSERIM VARCHAR2(16),
    D_INSERIM DATE,
    COD_USER_AGGIORN VARCHAR2(16),
    D_AGGIORN DATE,
    FLG_VISITA_ISPETTIVA VARCHAR2(1) DEFAULT 'N' NOT NULL,
    FLG_CONFERMATO_Q1 VARCHAR2(1) DEFAULT 'N' NOT NULL,
    TIPO_PROVENIENZA VARCHAR2(1) NOT NULL,
    D_FINE_SOSPENSIONE_Q1 DATE,
    PRIMARY KEY (ID_PROSPETTO),
    CHECK (FLG_ASSUNZIONI_PUBB_SELEZIONE IN ('S','N')),
    CHECK (FLG_INVIO_MINISTERO IN ('S','N')),
    CHECK (FLG_GRADUALITA IN ('S','N')),
    CHECK (FLG_CONFERMATO_Q1 IN ('S','N')),
    CHECK (FLG_SOSPENSIONE_PER_MOBILITA IN ('S','N')),
    CHECK (TIPO_PROVENIENZA IN ('P','M')),
    CHECK (FLG_VISITA_ISPETTIVA IN ('S','N')),
    CHECK (FLG_NESSUNA_ASSUNZIONE_AGGIUN IN ('S','N'))
);

/* ---------------------------------------------------------------------- */
/* Add table "PRO_R_PROSPETTO_PROVINCIA"                                  */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_R_PROSPETTO_PROVINCIA (
    ID_PROSPETTO_PROV NUMBER(9) NOT NULL,
    ID_PROSPETTO NUMBER(9) NOT NULL,
    ID_T_PROVINCIA NUMBER(5) NOT NULL,
    COD_USER_INSERIM VARCHAR2(16),
    D_INSERIM DATE,
    COD_USER_AGGIORN VARCHAR2(16),
    D_AGGIORN DATE,
    FLG_CONFERMATO_Q2 VARCHAR2(1) DEFAULT 'N' NOT NULL,
    PRIMARY KEY (ID_PROSPETTO_PROV),
    CHECK (FLG_CONFERMATO_Q2 IN ('S','N')),
    UNIQUE (ID_PROSPETTO, ID_T_PROVINCIA)
);

CREATE INDEX IE_PRO_R_PROSPETTO_PROV_01 ON PRO_R_PROSPETTO_PROVINCIA (ID_PROSPETTO ASC,ID_PROSPETTO_PROV ASC);

/* ---------------------------------------------------------------------- */
/* Add table "PRO_T_COMUNE"                                               */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_T_COMUNE (
    ID_T_COMUNE NUMBER(5) NOT NULL,
    COD_COMUNE_MIN VARCHAR2(4),
    DS_PRO_T_COMUNE VARCHAR2(100),
    COD_ISTAT VARCHAR2(6),
    ID_PROVINCIA NUMBER(5),
    COD_INPS VARCHAR2(6),
    COD_CAP VARCHAR2(5),
    COD_PREFISSO VARCHAR2(5),
    DT_INIZIO DATE,
    DT_FINE DATE,
    DT_TMST DATE,
    ID_T_CPI NUMBER(5),
    PRIMARY KEY (ID_T_COMUNE)
);

/* ---------------------------------------------------------------------- */
/* Add table "PRO_D_DATI_AZIENDA"                                         */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_D_DATI_AZIENDA (
    ID_PROSPETTO NUMBER(9) NOT NULL,
    ID_T_DICHIARANTE NUMBER(4),
    CF_AZIENDA VARCHAR2(16) NOT NULL,
    DENOMINAZIONE_DATORE_LAVORO VARCHAR2(100) NOT NULL,
    ID_T_ATECOFIN NUMBER(5),
    ID_T_CCNL NUMBER(5),
    CF_REFERENTE VARCHAR2(16),
    COGNOME_REFERENTE VARCHAR2(50),
    NOME_REFERENTE VARCHAR2(50),
    INDIRIZZO_REFERENTE VARCHAR2(100),
    ID_T_COMUNE NUMBER(5),
    ID_T_STATI_ESTERI NUMBER(5),
    CAP_REFERENTE VARCHAR2(5),
    TELEFONO_REFERENTE VARCHAR2(15),
    FAX_REFERENTE VARCHAR2(15),
    EMAIL_REFERENTE VARCHAR2(80),
    COD_USER_INSERIM VARCHAR2(16),
    D_INSERIM DATE,
    COD_USER_AGGIORN VARCHAR2(16),
    D_AGGIORN DATE,
    FLG_PROSPETTO_DA_CAPOGRUPPO VARCHAR2(1) DEFAULT 'N',
    CF_CAPOGRUPPO VARCHAR2(100),
    FLG_CAPOGRUPPO_ESTERO VARCHAR2(1),
    PRIMARY KEY (ID_PROSPETTO),
    CHECK (FLG_PROSPETTO_DA_CAPOGRUPPO IN ('S',
    'N')),
    CHECK (FLG_CAPOGRUPPO_ESTERO IN ('S', 'N'))
);

/* ---------------------------------------------------------------------- */
/* Add table "PRO_D_RIEPILOGO_NAZIONALE"                                  */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_D_RIEPILOGO_NAZIONALE (
    ID_PROSPETTO NUMBER(9) NOT NULL,
    NUM_LAVORATORI_BASE_COMPUTO NUMBER(6),
    QUOTA_RISERVA_DISABILI NUMBER(6) NOT NULL,
    QUOTA_RISERVA_ART_18 NUMBER(6) NOT NULL,
    NUM_LAVORATORI_SOSPENSIONE NUMBER(6),
    NUM_POSIZIONI_ESONERATE NUMBER(6) NOT NULL,
    NUM_DISABILI_IN_FORZA NUMBER(6) NOT NULL,
    NUM_CAT_PROT_IN_FORZA NUMBER(6) NOT NULL,
    NUM_CAT_PROT_IN_FORZA_CNT_DIS NUMBER(6),
    NUM_SCOPERT_DISABILI NUMBER(6),
    NUM_SCOPERT_CATEGORIE_PROTETTE NUMBER(6),
    COD_USER_INSERIM VARCHAR2(16),
    D_INSERIM DATE,
    COD_USER_AGGIORN VARCHAR2(16),
    D_AGGIORN DATE,
    BASE_COMPUTO_ART_3 NUMBER(6),
    BASE_COMPUTO_ART_18 NUMBER(6),
    FLG_SOSPENSIONI_IN_CORSO VARCHAR2(1) DEFAULT 'N' NOT NULL,
    NUM_SCOPERT_DISABILI_REALI NUMBER(6,2),
    NUM_SCOPERT_CAT_PROT_REALI NUMBER(6,2),
    QUOTA_ESUBERI_ART_18 NUMBER(6),
    PRIMARY KEY (ID_PROSPETTO),
    CHECK (FLG_SOSPENSIONI_IN_CORSO IN ('S', 'N'))
);

COMMENT ON COLUMN PRO_D_RIEPILOGO_NAZIONALE.NUM_SCOPERT_DISABILI_REALI IS 'Dato reale e non arrotondato';

COMMENT ON COLUMN PRO_D_RIEPILOGO_NAZIONALE.NUM_SCOPERT_CAT_PROT_REALI IS 'Numero non arrotondato';

/* ---------------------------------------------------------------------- */
/* Add table "PRO_D_ASS_PUBBLICHE"                                        */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_D_ASS_PUBBLICHE (
    ID_PROSPETTO NUMBER(9) NOT NULL,
    ID_T_REGIONE NUMBER(5) NOT NULL,
    SALDO_DISABILI NUMBER(6),
    SALDO_EX_ART_18 NUMBER(6),
    COD_USER_INSERIM VARCHAR2(16),
    D_INSERIM DATE,
    COD_USER_AGGIORN VARCHAR2(16),
    D_AGGIORN DATE,
    DS_NOTE VARCHAR2(2000),
    PRIMARY KEY (ID_PROSPETTO, ID_T_REGIONE)
);

/* ---------------------------------------------------------------------- */
/* Add table "PRO_D_DATI_PROVINCIALI"                                     */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_D_DATI_PROVINCIALI (
    ID_PROSPETTO_PROV NUMBER(9) NOT NULL,
    N_POSTI_PREV_CENTRALI_NONVED NUMBER(2),
    N_POSTI_PREV_MASSOFIS_NONVED NUMBER(2),
    N_TOTALE_LAVORAT_DIPENDENTI NUMBER(6),
    N_DISABILI_IN_FORZA NUMBER(6),
    N_CENTRAL_TELEFO_NONVEDENTI NUMBER(6),
    N_TERARIAB_MASSOFIS_NONVED NUMBER(6),
    FLG_CATEGORIE_ESCLUSE_COMPUTE VARCHAR2(1),
    FLG_DETTAGLIO_PARTIME VARCHAR2(1),
    N_PARTIME_RIPROPORZIONATI NUMBER(6),
    FLG_DETTAGLIO_INTERMITTENTI VARCHAR2(1),
    N_INTERMITTENTI_RIPROPORZIONA NUMBER(6),
    N_CATE_PROT_FORZA NUMBER(6),
    N_CATE_PROT_FORZA_CNT_DIS VARCHAR2(6),
    N_CATE_PROT_FORZA_ESUBERO VARCHAR2(6),
    BASE_COMPUTO NUMBER(6),
    FLG_LAVORATORI_IN_FORZA VARCHAR2(1),
    FLG_POSTI_LAVORO_DISPONIBILI VARCHAR2(1),
    FLG_COMPENSAZIONI_TERRITORIAL VARCHAR2(1),
    FLG_AZI_SOSP VARCHAR2(1),
    FLG_AZI_GRADU VARCHAR2(1),
    FLG_AZI_ESONERO_PARZIALE VARCHAR2(1),
    FLG_AZI_CON_CONVENZIONE VARCHAR2(1),
    NOTE VARCHAR2(2000),
    COD_USER_INSERIM VARCHAR2(16),
    D_INSERIM DATE,
    COD_USER_AGGIORN VARCHAR2(16),
    D_AGGIORN DATE,
    N_CATE_PROT_FORZA_A_17_01_2000 NUMBER(6),
    N_TELELAVORO_FT NUMBER(6),
    N_SOMMINISTRATI_FT NUMBER(6),
    N_CONVENZIONI_12BIS_14_FT NUMBER(6),
    PRIMARY KEY (ID_PROSPETTO_PROV),
    CHECK (FLG_DETTAGLIO_INTERMITTENTI IN ('S','N')),
    CHECK (FLG_LAVORATORI_IN_FORZA IN ('S','N')),
    CHECK (FLG_COMPENSAZIONI_TERRITORIAL IN ('S','N')),
    CHECK (FLG_AZI_SOSP IN ('S','N')),
    CHECK (FLG_AZI_GRADU IN ('S','N')),
    CHECK (FLG_AZI_ESONERO_PARZIALE IN ('S','N')),
    CHECK (FLG_POSTI_LAVORO_DISPONIBILI IN ('S','N')),
    CHECK (FLG_AZI_CON_CONVENZIONE IN ('S','N')),
    CHECK (FLG_CATEGORIE_ESCLUSE_COMPUTE IN ('S','N')),
    CHECK (FLG_DETTAGLIO_PARTIME IN ('S','N'))
);

/* ---------------------------------------------------------------------- */
/* Add table "PRO_D_LAVORATORI_SILP"                                      */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_D_LAVORATORI_SILP (
    CF_IMPRESA VARCHAR2(16) NOT NULL,
    ID_T_PROVINCIA NUMBER(5) NOT NULL,
    CODICE_FISCALE VARCHAR2(16) NOT NULL,
    COGNOME VARCHAR2(50),
    NOME VARCHAR2(50),
    SESSO VARCHAR2(1),
    DATA_NASCITA DATE,
    ID_T_STATO_ESTERO_NASCITA NUMBER(5),
    DATA_INIZIO_RAPPORTO DATE,
    ID_T_CONTRATTO NUMBER(5),
    ID_T_COMUNE_NASCITA NUMBER(5),
    DATA_FINE_RAPPORTO DATE,
    CATEGORIA_SOGGETTO VARCHAR2(1),
    ID_T_QUALIFICA_PROFESSIONALE_I NUMBER(5),
    ID_T_ASSUNZIONE_PROTETTA NUMBER(4),
    ORARIO_SETT_CONTRATTUALE_MIN NUMBER(5),
    ORARIO_SETT_PART_TIME_MIN NUMBER(5),
    CATEGORIA_ASSUNZIONE VARCHAR2(2),
    FLG_IMPORT_ONLINE VARCHAR2(1),
    PRIMARY KEY (CF_IMPRESA, ID_T_PROVINCIA, CODICE_FISCALE)
);

/* ---------------------------------------------------------------------- */
/* Add table "PRO_D_IMPORT_ERRORI"                                        */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_D_IMPORT_ERRORI (
    ID_ERRORE NUMBER NOT NULL,
    ID_SPI_TRASMISSIONE NUMBER NOT NULL,
    DATA_ELAB DATE NOT NULL,
    TABELLA_DESTINAZIONE VARCHAR2(30),
    DATO_INPUT VARCHAR2(30),
    COD_ERRORE NUMBER,
    DS_ERRORE_NON_GESTITO VARCHAR2(2000),
    PRIMARY KEY (ID_ERRORE)
);

/* ---------------------------------------------------------------------- */
/* Add table "PRO_D_PROV_ESONERO_AUTOCERT"                                */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_D_PROV_ESONERO_AUTOCERT (
    ID_PROSPETTO_PROV NUMBER(9) NOT NULL,
    DATA_AUTOCERT DATE NOT NULL,
    PERCENTUALE_ES_AUTOCERT NUMBER(3),
    N_LAV_60X1000 NUMBER(6) NOT NULL,
    N_LAV_ESONERO_AUTOCERT NUMBER(6) NOT NULL,
    COD_USER_INSERIM VARCHAR2(16) NOT NULL,
    D_INSERIM DATE NOT NULL,
    COD_USER_AGGIORN VARCHAR2(16) NOT NULL,
    D_AGGIORN DATE NOT NULL,
    PRIMARY KEY (ID_PROSPETTO_PROV)
);

COMMENT ON TABLE PRO_D_PROV_ESONERO_AUTOCERT IS 'Tabella per nuova tipologia di esonero parziale autocertificato';

COMMENT ON COLUMN PRO_D_PROV_ESONERO_AUTOCERT.ID_PROSPETTO_PROV IS 'Id prospetto provinciale di riferimento';

COMMENT ON COLUMN PRO_D_PROV_ESONERO_AUTOCERT.DATA_AUTOCERT IS 'Data invio dell''autocertificazione attestante l''esonero';

COMMENT ON COLUMN PRO_D_PROV_ESONERO_AUTOCERT.PERCENTUALE_ES_AUTOCERT IS '% esonero rispetto alla quota di riserva (non vengono fatti controlli tra % e N° dichiarati)';

COMMENT ON COLUMN PRO_D_PROV_ESONERO_AUTOCERT.N_LAV_60X1000 IS 'Num lav per i quali si paga premio INAIL >= 60 x1000';

COMMENT ON COLUMN PRO_D_PROV_ESONERO_AUTOCERT.N_LAV_ESONERO_AUTOCERT IS 'Num disabili per i quali l''azi si avvale dell''esonero dall''obbligo di assunzione';

/* ---------------------------------------------------------------------- */
/* Add table "PRO_D_PDF_PROSPETTO"                                        */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_D_PDF_PROSPETTO (
    ID_PROSPETTO NUMBER(9) NOT NULL,
    PDF_PROSPETTO_FIRMATO BLOB,
    PDF_PROSPETTO_DA_FIRMARE BLOB,
    PRIMARY KEY (ID_PROSPETTO)
);

/* ---------------------------------------------------------------------- */
/* Add table "PRO_D_PROSPETTO_GRADUALITA"                                 */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_D_PROSPETTO_GRADUALITA (
    ID_PROSPETTO NUMBER(9) NOT NULL,
    DATA_ATTO DATE,
    ESTREMI_ATTO VARCHAR2(50),
    N_ASSUNZIONI_LAV_PRE_TRASF NUMBER(6),
    DATA_TRASFORMAZIONE DATE,
    PERCENTUALE NUMBER(3),
    COD_USER_INSERIM VARCHAR2(16),
    D_INSERIM DATE,
    COD_USER_AGGIORN VARCHAR2(16),
    D_AGGIORN DATE,
    PRIMARY KEY (ID_PROSPETTO)
);

/* ---------------------------------------------------------------------- */
/* Add table "PRO_D_SEDE_LEGALE"                                          */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_D_SEDE_LEGALE (
    ID_PROSPETTO NUMBER(9) NOT NULL,
    ID_T_COMUNE NUMBER(5),
    ID_T_STATI_ESTERI NUMBER(5),
    CAP_SEDE VARCHAR2(5),
    INDIRIZZO VARCHAR2(100),
    TELEFONO VARCHAR2(15),
    FAX VARCHAR2(15),
    EMAIL VARCHAR2(80),
    COD_USER_INSERIM VARCHAR2(16),
    D_INSERIM DATE,
    COD_USER_AGGIORN VARCHAR2(16),
    D_AGGIORN DATE,
    PRIMARY KEY (ID_PROSPETTO)
);

/* ---------------------------------------------------------------------- */
/* Add table "PRO_D_PROV_SOSPENSIONE"                                     */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_D_PROV_SOSPENSIONE (
    ID_PROSPETTO_PROV NUMBER(9) NOT NULL,
    N_LAVORATORI NUMBER(6),
    ID_T_CAUSA_SOSPENSIONE NUMBER(4),
    ID_T_STATO_CONCESSIONE NUMBER(4),
    COD_USER_INSERIM VARCHAR2(16),
    D_INSERIM DATE,
    COD_USER_AGGIORN VARCHAR2(16),
    D_AGGIORN DATE,
    D_FINE_SOSPENSIONE_Q2 DATE,
    PRIMARY KEY (ID_PROSPETTO_PROV)
);

/* ---------------------------------------------------------------------- */
/* Add table "PRO_D_CATEGORIE_ESCLUSE"                                    */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_D_CATEGORIE_ESCLUSE (
    ID_CATEGORIE_ESCLUSE NUMBER(9) NOT NULL,
    ID_PROSPETTO_PROV NUMBER(9) NOT NULL,
    ID_T_CATEGORIE_ESCLUSE NUMBER(4) NOT NULL,
    N_LAV_APPARTART_CATEGORIA NUMBER(6),
    COD_USER_INSERIM VARCHAR2(16),
    D_INSERIM DATE,
    COD_USER_AGGIORN VARCHAR2(16),
    D_AGGIORN DATE,
    PRIMARY KEY (ID_CATEGORIE_ESCLUSE)
);

/* ---------------------------------------------------------------------- */
/* Add table "PRO_D_PART_TIME"                                            */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_D_PART_TIME (
    ID_PART_TIME NUMBER(9) NOT NULL,
    ID_PROSPETTO_PROV NUMBER(9) NOT NULL,
    N_PART_TIME NUMBER(6) NOT NULL,
    ORARIO_SETT_CONTRATTUALE_MIN NUMBER(5) NOT NULL,
    ORARIO_SETT_PART_TIME_MIN NUMBER(5) NOT NULL,
    COD_USER_INSERIM VARCHAR2(16),
    D_INSERIM DATE,
    COD_USER_AGGIORN VARCHAR2(16),
    D_AGGIORN DATE,
    ID_TIPO_RIPROP_PT NUMBER(4) NOT NULL,
    PRIMARY KEY (ID_PART_TIME)
);

/* ---------------------------------------------------------------------- */
/* Add table "PRO_D_PROV_ESONERO"                                         */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_D_PROV_ESONERO (
    ID_PROSPETTO_PROV NUMBER(9) NOT NULL,
    ID_T_STATO_CONCESSIONE NUMBER(4),
    DATA_ATTO DATE,
    ESTREMI_ATTO VARCHAR2(50),
    DATA_ATTO_FINO_AL DATE,
    PERCENTUALE NUMBER(3),
    N_LAVORATORI_ESONERO NUMBER(6),
    COD_USER_INSERIM VARCHAR2(16),
    D_INSERIM DATE,
    D_AGGIORN DATE,
    COD_USER_AGGIORN VARCHAR2(16),
    PRIMARY KEY (ID_PROSPETTO_PROV)
);

/* ---------------------------------------------------------------------- */
/* Add table "PRO_D_PROV_COMPENSAZIONI"                                   */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_D_PROV_COMPENSAZIONI (
    ID_COMPENSAZIONE NUMBER(9) NOT NULL,
    ID_PROSPETTO_PROV NUMBER(9) NOT NULL,
    ID_T_STATO_CONCESSIONE NUMBER(4),
    DATA_ATTO DATE,
    ESTREMI_ATTO VARCHAR2(50),
    CATEGORIA_COMPENSAZIONE VARCHAR2(1) NOT NULL,
    N_LAVORATORI NUMBER(6),
    CATEGORIA_SOGGETTO VARCHAR2(1) NOT NULL,
    FLG_AUTOCOMPENSAZIONE VARCHAR2(1),
    COD_USER_INSERIM VARCHAR2(16),
    D_INSERIM DATE,
    COD_USER_AGGIORN VARCHAR2(16),
    D_AGGIORN DATE,
    ID_T_PROVINCIA_COMP NUMBER(5),
    CF_AZIENDA_APPARTEN_AL_GRUPPO VARCHAR2(16),
    PRIMARY KEY (ID_COMPENSAZIONE),
    CHECK (CATEGORIA_SOGGETTO IN ('D','C')),
    CHECK (FLG_AUTOCOMPENSAZIONE IN ('S','N')),
    CHECK (CATEGORIA_COMPENSAZIONE IN ('E','R'))
);

/* ---------------------------------------------------------------------- */
/* Add table "PRO_D_RIEPILOGO_PROVINCIALE"                                */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_D_RIEPILOGO_PROVINCIALE (
    ID_PROSPETTO_PROV NUMBER(9) NOT NULL,
    NUM_LAVORATORI_BASE_COMPUTO NUMBER(6),
    NUM_LAVORATORI_SOSPENSIONE NUMBER(6),
    CAT_COMPENSAZIONE_DISABILI VARCHAR2(1),
    NUM_COMPENSAZIONE_DISABILI NUMBER(6),
    CAT_COMPENSAZIONE_CATE_PROT VARCHAR2(1),
    NUM_COMPENSAZIONI_CATE_PROT NUMBER(6),
    NUM_DISABILI_IN_FORZA NUMBER(6) NOT NULL,
    NUM_CAT_PROT_IN_FORZA NUMBER(6) NOT NULL,
    NUM_CAT_PROT_IN_FORZA_CONT_DIS NUMBER(6),
    QUOTA_RISERVA_DISABILI NUMBER(6) NOT NULL,
    QUOTA_RISERVA_ART_18 NUMBER(6) NOT NULL,
    NUM_POSIZIONI_ESONERATE NUMBER(6) NOT NULL,
    NUM_SCOPERTURE_DISABILI NUMBER(6),
    NUM_SCOPERTURE_CAT_PROT NUMBER(6),
    COD_USER_INSERIM VARCHAR2(16),
    D_INSERIM DATE,
    COD_USER_AGGIORN VARCHAR2(16),
    D_AGGIORN DATE,
    BASE_COMPUTO_ART_3 NUMBER(6),
    BASE_COMPUTO_ART_18 NUMBER(6),
    FLG_SOSPENSIONI_IN_CORSO VARCHAR2(1) DEFAULT 'N' NOT NULL,
    NUM_SCOPERTURE_DISABILI_REALI NUMBER(6,2),
    NUM_SCOPERTURE_CAT_PROT_REALI NUMBER(6,2),
    PRIMARY KEY (ID_PROSPETTO_PROV),
    CHECK (FLG_SOSPENSIONI_IN_CORSO IN ('S', 'N'))
);

COMMENT ON COLUMN PRO_D_RIEPILOGO_PROVINCIALE.NUM_SCOPERTURE_DISABILI_REALI IS 'Dato reale e non arrotondato';

COMMENT ON COLUMN PRO_D_RIEPILOGO_PROVINCIALE.NUM_SCOPERTURE_CAT_PROT_REALI IS 'Numero non arrotondato';

/* ---------------------------------------------------------------------- */
/* Add table "PRO_D_PROV_CONVENZIONE"                                     */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_D_PROV_CONVENZIONE (
    ID_PROSPETTO_PROV NUMBER(9) NOT NULL,
    ID_T_STATO_CONCESSIONE NUMBER(4),
    DATA_ATTO DATE,
    ESTREMI_ATTO VARCHAR2(50),
    ID_T_ASSUNZIONE_PROTETTA NUMBER(4),
    DATA_STIPULA DATE,
    DATA_SCADENZA DATE,
    COD_USER_INSERIM VARCHAR2(16),
    D_INSERIM DATE,
    COD_USER_AGGIORN VARCHAR2(16),
    D_AGGIORN DATE,
    NUM_LAV_PREV_CONV_Q2 NUMBER,
    PRIMARY KEY (ID_PROSPETTO_PROV)
);

/* ---------------------------------------------------------------------- */
/* Add table "PRO_D_PROSPETTO_PROV_SEDE"                                  */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_D_PROSPETTO_PROV_SEDE (
    ID_PROSPETTO_PROV NUMBER(9) NOT NULL,
    ID_T_COMUNE NUMBER(5),
    CAP VARCHAR2(5),
    INDIRIZZO VARCHAR2(100),
    TELEFONO VARCHAR2(15),
    FAX VARCHAR2(15),
    EMAIL VARCHAR2(80),
    COGNOME_REFERENTE VARCHAR2(50),
    NOME_REFERENTE VARCHAR2(50),
    COD_USER_INSERIM VARCHAR2(16),
    D_INSERIM DATE,
    COD_USER_AGGIORN VARCHAR2(16),
    D_AGGIORN DATE,
    PRIMARY KEY (ID_PROSPETTO_PROV)
);

/* ---------------------------------------------------------------------- */
/* Add table "PRO_D_PROV_INTERMITTENTI"                                   */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_D_PROV_INTERMITTENTI (
    ID_INTERMITTENTI NUMBER(9) NOT NULL,
    ID_PROSPETTO_PROV NUMBER(9) NOT NULL,
    N_INTERMITTENTI NUMBER(4) NOT NULL,
    ORARIO_SETTIMANALE_CONTRATTUAL NUMBER(4) NOT NULL,
    ORARIO_SETTIMANALE_SVOLTO NUMBER(4) NOT NULL,
    COD_USER_INSERIM VARCHAR2(16),
    D_INSERIM DATE,
    COD_USER_AGGIORN VARCHAR2(16),
    D_AGGIORN DATE,
    PRIMARY KEY (ID_INTERMITTENTI)
);

/* ---------------------------------------------------------------------- */
/* Add table "PRO_D_PROV_GRADUALITA"                                      */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_D_PROV_GRADUALITA (
    ID_PROSPETTO_PROV NUMBER(9) NOT NULL,
    N_ASSUNZIONI_EFF_DOPO_TRASF NUMBER(6) NOT NULL,
    COD_USER_INSERIM VARCHAR2(16),
    D_INSERIM DATE,
    COD_USER_AGGIORN VARCHAR2(16),
    D_AGGIORN DATE,
    PRIMARY KEY (ID_PROSPETTO_PROV)
);

/* ---------------------------------------------------------------------- */
/* Add table "PRO_D_POSTI_LAVORO_DISP"                                    */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_D_POSTI_LAVORO_DISP (
    ID_POSTO_LAVORO_DISP NUMBER(9) NOT NULL,
    ID_PROSPETTO_PROV NUMBER(9) NOT NULL,
    DESC_MANSIONE VARCHAR2(200),
    ID_T_QUALIFICA_PROFESSI_ISTAT NUMBER(5) NOT NULL,
    N_POSTI NUMBER(6),
    CATEGORIA_SOGGETTO VARCHAR2(1),
    DESC_CAPACITA_RICHIESTE_CONTR VARCHAR2(200),
    FLG_PRESENZA_BARRIERE_ARCHITE VARCHAR2(1),
    FLG_TURNI_NOTTURNI VARCHAR2(1),
    ID_T_COMUNE_ASSUNZIONE NUMBER(5),
    FLG_RAGGIUNGIB_MEZZI_PUBBLICI VARCHAR2(1),
    ID_T_STATO_ESTERO_ASSUNZIONE NUMBER(5),
    CATEGORIA_ASSUNZIONE VARCHAR2(2),
    COD_USER_INSERIM VARCHAR2(16),
    D_INSERIM DATE,
    COD_USER_AGGIORN VARCHAR2(16),
    D_AGGIORN DATE,
    PRIMARY KEY (ID_POSTO_LAVORO_DISP),
    CHECK (CATEGORIA_SOGGETTO IN ('D','C')),
    CHECK (FLG_PRESENZA_BARRIERE_ARCHITE IN ('S','N')),
    CHECK (FLG_RAGGIUNGIB_MEZZI_PUBBLICI IN ('S','N')),
    CHECK (FLG_TURNI_NOTTURNI IN ('S','N')),
    CHECK (CATEGORIA_ASSUNZIONE IN ('NU','NO'))
);

/* ---------------------------------------------------------------------- */
/* Add table "PRO_D_LAVORATORI_IN_FORZA"                                  */
/* ---------------------------------------------------------------------- */

CREATE TABLE PRO_D_LAVORATORI_IN_FORZA (
    ID_LAVORATORI_IN_FORZA NUMBER(9) NOT NULL,
    ID_PROSPETTO_PROV NUMBER(9) NOT NULL,
    CODICE_FISCALE VARCHAR2(16) NOT NULL,
    COGNOME VARCHAR2(50) NOT NULL,
    NOME VARCHAR2(50) NOT NULL,
    SESSO VARCHAR2(1) NOT NULL,
    DATA_NASCITA DATE NOT NULL,
    ID_T_STATO_ESTERO_NASCITA NUMBER(5) NOT NULL,
    DATA_INIZIO_RAPPORTO DATE,
    ID_T_CONTRATTO NUMBER(5),
    ID_T_COMUNE_NASCITA NUMBER(5),
    DATA_FINE_RAPPORTO DATE,
    CATEGORIA_SOGGETTO VARCHAR2(1),
    ID_T_QUALIFICA_PROFESSIONALE_I NUMBER(5),
    ID_T_ASSUNZIONE_PROTETTA NUMBER(4),
    ORARIO_SETT_CONTRATTUALE_MIN NUMBER(5),
    ORARIO_SETT_PART_TIME_MIN NUMBER(5),
    CATEGORIA_ASSUNZIONE VARCHAR2(2),
    COD_USER_INSERIM VARCHAR2(16),
    D_INSERIM DATE,
    COD_USER_AGGIORN VARCHAR2(16),
    D_AGGIORN DATE,
    FLG_COMPLETATO VARCHAR2(1),
    PERCENTUALE_DISABILITA NUMBER(3),
    PRIMARY KEY (ID_LAVORATORI_IN_FORZA),
    CHECK (ID_T_ASSUNZIONE_PROTETTA IN ('11', '12') AND ID_T_CONTRATTO IS NULL) OR (ID_T_ASSUNZIONE_PROTETTA NOT IN ('11', '12') AND ID_T_CONTRATTO IS NOT NULL),
    CHECK (FLG_COMPLETATO IN ('S','N')),
    CHECK (SESSO IN ('M','F')),
    CHECK (CATEGORIA_SOGGETTO IN ('D','C')),
    CHECK (CATEGORIA_ASSUNZIONE IN ('NU','NO'))
);

