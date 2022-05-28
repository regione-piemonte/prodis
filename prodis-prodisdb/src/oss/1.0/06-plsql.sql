
CREATE OR REPLACE PACKAGE PCK_PRODIS_UTILS AS

    /* calcola riepilogo provinciale */
    PROCEDURE calcola_riepilogo_provinciale (
      prospetto_id IN NUMBER,
      cf_operatore IN VARCHAR2);

    /* calcola riepilogo nazionale */
    PROCEDURE calcola_riepilogo_nazionale (
      prospetto_id IN NUMBER,
      cf_operatore IN VARCHAR2);

    /* */
    PROCEDURE esegui_ricalcolo (
      prospetto_id IN NUMBER,
      cf_operatore IN VARCHAR2);

    /*
    lato java se si usa la stored procedure viene generato in errore oracle ORA-04091
    */
    /*
    FUNCTION get_base_computo (
      prospetto_prov_id IN NUMBER) return NUMBER;
      */

    PROCEDURE get_base_computo(prospetto_prov_id IN NUMBER, base_computo OUT NUMBER);

    /* calcola part time riproporzionati */
    FUNCTION get_part_time_riproporz (
      prospetto_prov_id IN NUMBER) return NUMBER;

    /* calcola intermittenti riproporzionati */
    FUNCTION get_intermit_riproporz (
      prospetto_prov_id IN NUMBER) return NUMBER;

    /* cancella dati provinciali */
    procedure cancella_dati_provinciali (
      prospetto_prov_id IN NUMBER);

    /* duplica un prospetto */
    procedure copia_prospetto(prospetto_id IN NUMBER, nuovo_prospetto_id OUT NUMBER);

    procedure annulla_prospetto(prospetto_id IN NUMBER, nuovo_prospetto_id OUT NUMBER);
    procedure rettifica_prospetto(prospetto_id IN NUMBER, nuovo_prospetto_id OUT NUMBER);

    procedure cancella_prospetto(prospetto_id IN NUMBER);


END PCK_PRODIS_UTILS;
/


CREATE OR REPLACE PACKAGE PCK_PRODIS_2012 AS
  
  log_enable BOOLEAN := false;

  TYPE prospetto_provincia_TYPE IS RECORD(
    idProspettoProv            NUMBER,
    idProvincia                NUMBER,
    nLavDipProv_               NUMBER,
    nDisabiliInForza           NUMBER,
    nCatProtInForza            NUMBER,
    nDisabiliInForzaBC         NUMBER, 
    nCatProtInForza17_         NUMBER,
    catEsclDisabProv_          NUMBER,
    catEsclCatProtProv_        NUMBER,
    nLavPartTimeProv_          NUMBER,
    partTimeRiproporz_         NUMBER,
    intermitRiproporz_         NUMBER,
    diffPartTimeProv_          NUMBER,
    nLavIntermitProv_          NUMBER,
    diffIntermitProv_          NUMBER,
    preBaseComputoDisabiliPV_  NUMBER,
    preBaseComputoCatProtPV_   NUMBER,
    catProtInForza1pcPV_       NUMBER,
    minimoCatProtInForzaPV_    NUMBER,
    nLavBaseComputoArt3        NUMBER,
    nLavBaseComputoArt18       NUMBER,
    quotaRiservaDisabili       NUMBER,
    restoQuotaRiservaDisabili_ NUMBER,
    quotaRiservaDisabiliArrot_ NUMBER,
    quotaRiservaArt18          NUMBER,
    restoQuotaRiservaArt18_    NUMBER,
    quotaRiservaArt18Arrot_    NUMBER,
    flgSospensioniInCorso      CHAR,
    nPosizEsonerate            NUMBER,
    scopertureDisabiliPV_      NUMBER,
    nScopertureDisab           NUMBER,
    nScopertureCatProt         NUMBER,
    dInserim                   DATE,
    dAggiorn                   DATE,
    codUserAggiorn             VARCHAR2(16),
    codUserInserim             VARCHAR2(16),
    diffTmp_                   NUMBER, 
    ordTmp_                    NUMBER, 
    telelavoroRiproporz_       NUMBER, 
    telelavoroFT_              NUMBER, 
    telelavoroPT_              NUMBER, 
    maxSplamabile_             NUMBER, 
    nDisabiliFt                NUMBER  
    );

  TYPE prospetto_provincia_TABLE IS TABLE OF prospetto_provincia_TYPE INDEX BY BINARY_INTEGER;

  procedure setLogEnable(attiva boolean);
  FUNCTION isLogEnable return BOOLEAN;

  FUNCTION order_by(tabellaIN IN prospetto_provincia_TABLE,
                    campo     IN VARCHAR2,
                    ordine    IN VARCHAR2) return prospetto_provincia_TABLE;

  PROCEDURE esegui_calcoli_nazionali(prospetto_id   IN NUMBER,
                                     cf_operatore   IN VARCHAR2,
                                     soloScoperture IN VARCHAR2,
                                     esito          OUT NUMBER);

  PROCEDURE esegui_calcoli_provinciali(prospetto_id   IN NUMBER,
                                       cf_operatore   IN VARCHAR2,
                                       soloScoperture IN VARCHAR2,
                                       esito          OUT NUMBER);

  PROCEDURE esegui_compensazioni(prospetto_id IN NUMBER,
                                 cf_operatore IN VARCHAR2,
                                 esito        OUT NUMBER,
                                 messaggio    OUT VARCHAR2);

  PROCEDURE esegui_calcoli(prospetto_id   IN NUMBER,
                           cf_operatore   IN VARCHAR2,
                           soloScoperture IN VARCHAR2,
                           esito          OUT NUMBER,
                           messaggio      OUT VARCHAR2);

  PROCEDURE esegui_operazione(prospetto_id         IN NUMBER,
                              cf_operatore         IN VARCHAR2,
                              cf_studio_professionale   IN VARCHAR2,
                              id_t_soggetti        IN NUMBER,
                              tipoOperazione       IN VARCHAR2,
                              esito          OUT NUMBER,
                              messaggio      OUT VARCHAR2,
                              newIdProspetto OUT NUMBER);

--FUNCTION order_by(tabella IN prospetto_provincia_TABLE, campo IN VARCHAR2, ordine IN VARCHAR2) return prospetto_provincia_TABLE;

END PCK_PRODIS_2012;
/

CREATE OR REPLACE PACKAGE "PCK_PRODIS_2012_COMP_UTILS" AS

  log_enable BOOLEAN := false;
  procedure setLogEnable(attiva boolean);
  FUNCTION isLogEnable return BOOLEAN;

  FUNCTION get_cat_compens_disab_pv(prospetto_prov_id IN NUMBER) return CHAR;
  FUNCTION get_n_compen_disab_pv(prospetto_prov_id IN NUMBER) return NUMBER;
  FUNCTION get_cat_compens_catprot_pv(prospetto_prov_id IN NUMBER) return CHAR;
  FUNCTION get_n_compen_catprot_pv(prospetto_prov_id IN NUMBER) return NUMBER;

  FUNCTION get_n_scopert_disab_pv(prospetto_prov_id IN NUMBER) return NUMBER;
  FUNCTION get_n_scopert_catprot_pv(prospetto_prov_id IN NUMBER) return NUMBER;


  FUNCTION get_n_ecced_disab_itergruppo(prospetto_id IN NUMBER) return NUMBER;

  FUNCTION get_n_riduz_disab_itergruppo(prospetto_id IN NUMBER) return NUMBER;

  FUNCTION get_n_ecced_catprot_itergruppo(prospetto_id IN NUMBER) return NUMBER;

  FUNCTION get_n_riduz_catprot_itergruppo(prospetto_id IN NUMBER) return NUMBER;

  FUNCTION get_n_scopert_disab_naz(
    prospetto_id IN NUMBER,
    numeccedenzedisabintergruppo IN NUMBER,
    numriduzionidisabintergruppo IN NUMBER) return NUMBER;

  FUNCTION get_n_scopert_catprot_naz(
    prospetto_id IN NUMBER,
    numeccedenzecatprotintergruppo IN NUMBER,
    numriduzionicatprotintergruppo IN NUMBER) return NUMBER;

END PCK_PRODIS_2012_COMP_UTILS;
/

CREATE OR REPLACE PACKAGE PCK_PRODIS_2012_NAZ_UTILS AS
  
  log_enable BOOLEAN := false;
  procedure setLogEnable(attiva boolean);
  FUNCTION isLogEnable return BOOLEAN;

  FUNCTION get_n_lav_in_forza_naz(prospetto_id IN NUMBER) return NUMBER;

  FUNCTION get_n_lav_base_computo_art_3(prospetto_id IN NUMBER) return NUMBER;

  FUNCTION get_n_lav_base_computo_art_18(prospetto_id IN NUMBER)
    return NUMBER;

  FUNCTION get_categoria_azienda(nLavBaseComputo IN NUMBER) return CHAR;

  FUNCTION get_quota_riserva_disabili(prospetto_id     IN NUMBER,
                                      categoriaAzienda IN CHAR) return NUMBER;

  FUNCTION get_quota_riserva_art_18(prospetto_id IN NUMBER) return NUMBER;

  FUNCTION get_n_posizioni_esonerate(prospetto_id IN NUMBER) return NUMBER;

  FUNCTION get_n_disabili_in_forza(prospetto_id IN NUMBER) return NUMBER;

  FUNCTION get_n_disabili_in_forza_bc(prospetto_id IN NUMBER) return NUMBER; 

  FUNCTION get_n_cat_prot_in_forza(prospetto_id IN NUMBER) return NUMBER;

  FUNCTION get_n_cat_prot_in_forz_17_1_00(prospetto_id IN NUMBER)
    return NUMBER;

  FUNCTION get_n_scoperture_disabili(prospetto_id IN NUMBER) return NUMBER;

  FUNCTION get_n_scoperture_cat_prot(prospetto_id IN NUMBER) return NUMBER;

  FUNCTION get_sospensioni_in_corso(prospetto_id IN NUMBER) return CHAR;

  FUNCTION get_diff_parttime_naz(prospetto_id IN NUMBER) return NUMBER;

  FUNCTION get_diff_intermit_naz(prospetto_id IN NUMBER) return NUMBER;

  FUNCTION get_n_lav_part_time(prospetto_id IN NUMBER) return NUMBER;

  FUNCTION get_parttime_riproporzionati(prospetto_id IN NUMBER) return NUMBER;

  FUNCTION get_n_lav_intermittenti(prospetto_id IN NUMBER) return NUMBER;

  FUNCTION get_intermittenti_riproporz(prospetto_id IN NUMBER) return NUMBER;

  FUNCTION get_cat_prot_in_forza_1(prospetto_id IN NUMBER) return NUMBER;

  FUNCTION get_esuberi_art_18(prospetto_id IN NUMBER) return NUMBER;

  FUNCTION get_valore_abbatt_naz(quotaRiservaArt18   IN NUMBER,
                                 esuberoArt18        IN NUMBER,
                                 catProtInForz170100 IN NUMBER) return NUMBER;

  FUNCTION get_abbatt_pc_naz(prospetto_id IN NUMBER) return NUMBER;

  FUNCTION get_n_lav_catescluse(prospetto_id IN NUMBER, flagArt18 IN CHAR)
    return NUMBER;

  FUNCTION get_telelav_riproporzionati(prospetto_id IN NUMBER) RETURN NUMBER;
  FUNCTION get_disabili_riproporz(prospetto_id IN NUMBER) RETURN NUMBER;
  FUNCTION get_n_telelavoro(prospetto_id IN NUMBER) RETURN NUMBER;
  

END PCK_PRODIS_2012_NAZ_UTILS;
/

CREATE OR REPLACE PACKAGE PCK_PRODIS_2012_PROV_UTILS AS
  
  log_enable BOOLEAN := false;
  procedure setLogEnable(attiva boolean);
  FUNCTION isLogEnable return BOOLEAN;

  FUNCTION get_n_lav_dip_prov(prospetto_prov_id IN NUMBER) return NUMBER;

  FUNCTION get_categoria_compens_disab(prospetto_prov_id IN NUMBER)
    return CHAR;

  FUNCTION get_n_compens_disab(prospetto_prov_id IN NUMBER) return NUMBER;

  FUNCTION get_categoria_compens_cat_prot(prospetto_prov_id IN NUMBER)
    return CHAR;

  FUNCTION get_n_compens_cat_prot(prospetto_prov_id IN NUMBER) return NUMBER;

  FUNCTION get_n_disabili_in_forza(prospetto_prov_id IN NUMBER) return NUMBER;

  FUNCTION get_n_cat_prot_in_forza(prospetto_prov_id IN NUMBER) return NUMBER;

  FUNCTION get_n_catprotforza_170100_pv(prospetto_prov_id IN NUMBER)
    return NUMBER;

  FUNCTION get_n_posizioni_esonerate(prospetto_prov_id IN NUMBER)
    return NUMBER;

  FUNCTION get_sospensioni_in_corso(prospetto_prov_id IN NUMBER) return CHAR;

  FUNCTION get_cat_escl_disab_prov(prospetto_prov_id IN NUMBER) return NUMBER;

  FUNCTION get_cat_escl_cat_prot_prov(prospetto_prov_id IN NUMBER)
    return NUMBER;

  FUNCTION get_n_lav_part_time_prov(prospetto_prov_id IN NUMBER)
    return NUMBER;

  FUNCTION get_part_time_riproporz(prospetto_prov_id IN NUMBER) return NUMBER;

  FUNCTION get_diff_part_time_prov(prospetto_prov_id IN NUMBER) return NUMBER;

  FUNCTION get_n_lav_intermit_prov(prospetto_prov_id IN NUMBER) return NUMBER;

  FUNCTION get_intermit_riproporz(prospetto_prov_id IN NUMBER) return NUMBER;

  FUNCTION get_diff_intermit_prov(prospetto_prov_id IN NUMBER) return NUMBER;

  FUNCTION get_scoperture_disabili_pv(quotaRicervaDisabPV IN NUMBER,
                                      nPosizEsoneratePv   IN NUMBER,
                                      nDisabiliInForzaPV  IN NUMBER)
    return NUMBER;

  FUNCTION get_n_disabili_in_forza_bc(prospetto_prov_id IN NUMBER) RETURN NUMBER;
  FUNCTION get_n_lav_telelav_pt_prov(prospetto_prov_id IN NUMBER) RETURN NUMBER;
  FUNCTION get_telelav_riproporz(prospetto_prov_id IN NUMBER) RETURN NUMBER;
  FUNCTION get_n_disabili_pt(prospetto_prov_id IN NUMBER) RETURN NUMBER;
  FUNCTION get_n_lav_telelav_prov(prospetto_prov_id IN NUMBER) RETURN NUMBER;
  FUNCTION get_n_disabili_ft(prospetto_prov_id IN NUMBER) RETURN NUMBER;
  FUNCTION get_disabili_riproporz(prospetto_prov_id IN NUMBER) RETURN NUMBER;
  PROCEDURE get_base_computo(prospetto_prov_id IN NUMBER, base_computo OUT NUMBER);
  PROCEDURE cancella_dati_provinciali(prospetto_prov_id IN NUMBER);
  function get_base_computo_prov(prospetto_prov_id IN NUMBER) return number;

  
END PCK_PRODIS_2012_PROV_UTILS;
/

CREATE OR REPLACE PACKAGE "PCK_PRODIS_2012_UTILS" AS

    FUNCTION minimo(numero1 IN NUMBER, numero2 IN NUMBER) return NUMBER;

    FUNCTION handle_number(numero IN NUMBER) return NUMBER;

    FUNCTION round_number(numero IN NUMBER) return NUMBER;

	FUNCTION controlla_codice_fiscale(
					codice_fiscale IN VARCHAR2,
					cognome IN VARCHAR2,
					nome IN VARCHAR2,
					sesso IN VARCHAR2,
					data_nascita IN VARCHAR2,
					codice_comune_stato IN VARCHAR2) return NUMBER;

END PCK_PRODIS_2012_UTILS;
/

CREATE OR REPLACE FUNCTION 
        CONTROLLO_CODICE_FISCALE(c_flag IN CHAR,
                                 c_cod_fis_iva IN CHAR,
                                 c_sesso IN CHAR,
                                 c_data_nas IN CHAR)
RETURN VARCHAR2 IS
digit NUMBER(2);
SOMMA NUMBER(3);
SOMMA_IVA NUMBER(2);
CONTR NUMBER(3);
decodifica_anno CHAR(2);
decodifica_mese CHAR(2);
decodifica_giorno VARCHAR2(2);
campo_appoggio NUMBER(2);
nAnno NUMBER(2);
messaggio_errore VARCHAR2(70);
/*
Questa procedura consente il controllo formale del codice fiscale per
le persone fisiche (16 bytes alfanumerici), eventualmente anche rispetto al
sesso e alla data di nascita, del codice fiscale per le societa' e
della partita iva (11 bytes numerici contraddistinti per mezzo del
flag impostato a 'F' per il codice fiscale e a 'P' per la partita iva).
*/
--  Serve per indicare la posizione (pari o dispari) del valore digitato.
-- digit (NUMBER);
--  Serve per totalizzare i valori corrispondenti per il codice fiscale.
-- somma (NUMBER);
--  Serve per totalizzare i valori corrispondenti per la partita iva.
-- somma_iva (NUMBER (2));
--  Serve per indicare la posizione di decodifica del carattere di
--  controllo.
-- contr (NUMBER);
--  Serve per la decodifica dell'anno di nascita.
-- decodifica_anno (CHAR (2));
--  Serve per la decodifica del mese di nascita.
-- decodifica_mese (CHAR (2));
--  Serve per la decodifica del giorno di nascita.
-- decodifica_giorno (CHAR (2));
--  Serve nel caso in cui i valori corrispondenti alla posizione pari
--  nella partita iva/codice fiscale per le societa', moltiplicati per 2,
--  diano un risultato di due cifre.
-- campo_appoggio (NUMBER (2));
BEGIN
contr := 26;
IF SUBSTR(c_cod_fis_iva,12,5) IS NULL THEN
   GOTO CONTR_IVA;
END IF;
IF TRANSLATE (SUBSTR(c_cod_fis_iva,1,16),
              '$0123456789QWERTYUIOPASDFGHJKLZXCVBNM','$') IS NULL THEN
   GOTO CONTR_CF;
ELSE
    messaggio_errore := 'CODICE FISCALE FORMALMENTE ERRATO';
    return(messaggio_errore);
END IF;
--
-- CONTROLLO CORRETTEZZA CODICE FISCALE PER LE PERSONE FISICHE (16).
--
<<CONTR_CF>>
digit := 1;
somma := 0;
-- Codifica dei valori digitati corrispondenti alla posizione dispari.
WHILE digit <= 15 LOOP
   IF SUBSTR(c_cod_fis_iva, digit, 1) = 'A' THEN
      somma := somma + 1;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'B' THEN
      somma := somma + 0;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'C' THEN
      somma := somma + 5;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'D' THEN
      somma := somma + 7;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'E' THEN
      somma := somma + 9;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'F' THEN
      somma := somma + 13;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'G' THEN
      somma := somma + 15;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'H' THEN
      somma := somma + 17;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'I' THEN
      somma := somma + 19;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'J' THEN
      somma := somma + 21;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'K' THEN
      somma := somma + 2;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'L' THEN
      somma := somma + 4;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'M' THEN
      somma := somma + 18;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'N' THEN
      somma := somma + 20;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'O' THEN
      somma := somma + 11;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'P' THEN
      somma := somma + 3;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'Q' THEN
      somma := somma + 6;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'R' THEN
      somma := somma + 8;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'S' THEN
      somma := somma + 12;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'T' THEN
      somma := somma + 14;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'U' THEN
      somma := somma + 16;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'V' THEN
      somma := somma + 10;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'W' THEN
      somma := somma + 22;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'X' THEN
      somma := somma + 25;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'Y' THEN
      somma := somma + 24;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'Z' THEN
      somma := somma + 23;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = '0' THEN
      somma := somma + 1;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = '1' THEN
      somma := somma + 0;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = '2' THEN
      somma := somma + 5;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = '3' THEN
      somma := somma + 7;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = '4' THEN
      somma := somma + 9;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = '5' THEN
      somma := somma + 13;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = '6' THEN
      somma := somma + 15;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = '7' THEN
      somma := somma + 17;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = '8' THEN
      somma := somma + 19;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = '9' THEN
      somma := somma + 21;
   END IF;
   digit := digit + 2;
END LOOP;
digit := 2;
-- Codifica dei valori digitati corrispondenti alla posizione pari.
WHILE digit <= 15 LOOP
   IF SUBSTR(c_cod_fis_iva, digit, 1) = 'A' THEN
      somma := somma + 0;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'B' THEN
      somma := somma + 1;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'C' THEN
      somma := somma + 2;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'D' THEN
      somma := somma + 3;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'E' THEN
      somma := somma + 4;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'F' THEN
      somma := somma + 5;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'G' THEN
      somma := somma + 6;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'H' THEN
      somma := somma + 7;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'I' THEN
      somma := somma + 8;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'J' THEN
      somma := somma + 9;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'K' THEN
      somma := somma + 10;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'L' THEN
      somma := somma + 11;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'M' THEN
      somma := somma + 12;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'N' THEN
      somma := somma + 13;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'O' THEN
      somma := somma + 14;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'P' THEN
      somma := somma + 15;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'Q' THEN
      somma := somma + 16;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'R' THEN
      somma := somma + 17;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'S' THEN
      somma := somma + 18;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'T' THEN
      somma := somma + 19;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'U' THEN
      somma := somma + 20;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'V' THEN
      somma := somma + 21;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'W' THEN
      somma := somma + 22;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'X' THEN
      somma := somma + 23;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'Y' THEN
      somma := somma + 24;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = 'Z' THEN
      somma := somma + 25;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = '0' THEN
      somma := somma + 0;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = '1' THEN
      somma := somma + 1;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = '2' THEN
      somma := somma + 2;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = '3' THEN
      somma := somma + 3;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = '4' THEN
      somma := somma + 4;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = '5' THEN
      somma := somma + 5;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = '6' THEN
      somma := somma + 6;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = '7' THEN
      somma := somma + 7;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = '8' THEN
      somma := somma + 8;
   ELSIF SUBSTR(c_cod_fis_iva, digit, 1) = '9' THEN
      somma := somma + 9;
   END IF;
   digit := digit + 2;
END LOOP;
-- Codifica ultimo carattere del codice fiscale (carattere di controllo).
IF SUBSTR(c_cod_fis_iva,16,1) = 'A' THEN
   contr := 0;
ELSIF SUBSTR(c_cod_fis_iva,16,1) = 'B' THEN
   contr := 1;
ELSIF SUBSTR(c_cod_fis_iva,16,1) = 'C' THEN
   contr := 2;
ELSIF SUBSTR(c_cod_fis_iva,16,1) = 'D' THEN
   contr := 3;
ELSIF SUBSTR(c_cod_fis_iva,16,1) = 'E' THEN
   contr := 4;
ELSIF SUBSTR(c_cod_fis_iva,16,1) = 'F' THEN
   contr := 5;
ELSIF SUBSTR(c_cod_fis_iva,16,1) = 'G' THEN
   contr := 6;
ELSIF SUBSTR(c_cod_fis_iva,16,1) = 'H' THEN
   contr := 7;
ELSIF SUBSTR(c_cod_fis_iva,16,1) = 'I' THEN
   contr := 8;
ELSIF SUBSTR(c_cod_fis_iva,16,1) = 'J' THEN
   contr := 9;
ELSIF SUBSTR(c_cod_fis_iva,16,1) = 'K' THEN
   contr := 10;
ELSIF SUBSTR(c_cod_fis_iva,16,1) = 'L' THEN
   contr := 11;
ELSIF SUBSTR(c_cod_fis_iva,16,1) = 'M' THEN
   contr := 12;
ELSIF SUBSTR(c_cod_fis_iva,16,1) = 'N' THEN
   contr := 13;
ELSIF SUBSTR(c_cod_fis_iva,16,1) = 'O' THEN
   contr := 14;
ELSIF SUBSTR(c_cod_fis_iva,16,1) = 'P' THEN
   contr := 15;
ELSIF SUBSTR(c_cod_fis_iva,16,1) = 'Q' THEN
   contr := 16;
ELSIF SUBSTR(c_cod_fis_iva,16,1) = 'R' THEN
   contr := 17;
ELSIF SUBSTR(c_cod_fis_iva,16,1) = 'S' THEN
   contr := 18;
ELSIF SUBSTR(c_cod_fis_iva,16,1) = 'T' THEN
   contr := 19;
ELSIF SUBSTR(c_cod_fis_iva,16,1) = 'U' THEN
   contr := 20;
ELSIF SUBSTR(c_cod_fis_iva,16,1) = 'V' THEN
   contr := 21;
ELSIF SUBSTR(c_cod_fis_iva,16,1) = 'W' THEN
   contr := 22;
ELSIF SUBSTR(c_cod_fis_iva,16,1) = 'X' THEN
   contr := 23;
ELSIF SUBSTR(c_cod_fis_iva,16,1) = 'Y' THEN
   contr := 24;
ELSIF SUBSTR(c_cod_fis_iva,16,1) = 'Z' THEN
   contr := 25;
END IF;
IF contr != MOD(somma,26) THEN
   messaggio_errore := 'CODICE FISCALE FORMALMENTE ERRATO';
   return(messaggio_errore);
ELSE
   GOTO CONTR_SESSO;
END IF;
--
-- CONTROLLO CORRETTEZZA PARTITA IVA/CODICE FISCALE PER LE SOCIETA'(11).
--
<<CONTR_IVA>>
digit := 1;
somma_iva := 0;
campo_appoggio := 0;
IF TRANSLATE (SUBSTR(c_cod_fis_iva,1,11),'$1234567890','$') IS NOT NULL
      OR  LENGTH(c_cod_fis_iva)<11 THEN
   IF c_flag = 'P' THEN
       messaggio_errore := 'PARTITA IVA FORMALMENTE ERRATA';
       return(messaggio_errore);
   ELSE
       messaggio_errore := 'CODICE FISCALE FORMALMENTE ERRATO';
       return(messaggio_errore);
   END IF;
END IF;
-- Loop per i valori corrispondenti alla posizione dispari.
WHILE digit <= 9 LOOP
   somma_iva := TO_NUMBER(SUBSTR(c_cod_fis_iva, digit, 1)) + somma_iva;
   digit := digit + 2;
END LOOP;
digit := 2;
-- Loop per i valori corrispondenti alla posizione pari.
WHILE digit <= 10 LOOP
   campo_appoggio := TO_NUMBER(SUBSTR(c_cod_fis_iva,digit,1))*2;
   IF campo_appoggio > 9 THEN
      somma_iva := TO_NUMBER(SUBSTR(TO_CHAR(campo_appoggio),1,1)) +
                   somma_iva;
      somma_iva := TO_NUMBER(SUBSTR(TO_CHAR(campo_appoggio),2,1)) +
                   somma_iva;
   ELSE
      somma_iva := somma_iva +
                   TO_NUMBER(SUBSTR(TO_CHAR(campo_appoggio),1,2));
   END IF;
   digit := digit + 2;
   campo_appoggio := 0;
END LOOP;
IF TO_CHAR(10-TO_NUMBER(SUBSTR(TO_CHAR(somma_iva),2,1))) != '10' THEN
   IF TO_CHAR(10-TO_NUMBER(SUBSTR(TO_CHAR(somma_iva),2,1)))
                              != SUBSTR(c_cod_fis_iva,11,1) THEN
      IF c_flag = 'P' THEN
         messaggio_errore := 'PARTITA IVA FORMALMENTE ERRATA';
         return(messaggio_errore);
      ELSE
         messaggio_errore := 'CODICE FISCALE FORMALMENTE ERRATO';
         return(messaggio_errore);
      END IF;
   ELSE
      GOTO FINE;
   END IF;
END IF;
IF SUBSTR(c_cod_fis_iva,11,1) != '0' THEN
   IF c_flag = 'P' THEN
       messaggio_errore := 'PARTITA IVA FORMALMENTE ERRATA';
       return(messaggio_errore);
    ELSE
       messaggio_errore := 'CODICE FISCALE FORMALMENTE ERRATO';
       return(messaggio_errore);
   END IF;
ELSE
   GOTO FINE;
END IF;
--
-- CONTROLLO SESSO DIGITATO PER IL CODICE FISCALE DI PERSONE FISICHE.
--
<<CONTR_SESSO>>
IF c_sesso IS NULL THEN
   GOTO CONTR_DATA;
END IF;
IF TO_NUMBER(SUBSTR(c_cod_fis_iva,10,2)) > 40 AND
   c_sesso = 'M'                              THEN
   messaggio_errore := 'CODICE FISCALE INCOMPATIBILE CON IL SESSO';
   return(messaggio_errore);
END IF;
IF TO_NUMBER(SUBSTR(c_cod_fis_iva,10,2)) < 40 AND
   c_sesso = 'F'                              THEN
   messaggio_errore := 'CODICE FISCALE INCOMPATIBILE CON IL SESSO';
   return(messaggio_errore);
END IF;
IF c_data_nas IS NOT NULL THEN
   GOTO CONTR_DATA;
ELSE
   GOTO FINE;
END IF;
--
-- CONTROLLO DATA DI NASCITA PER IL CODICE FISCALE PER LE PERSONE.
--
<<CONTR_DATA>>
IF c_data_nas IS NULL THEN
   GOTO FINE;
END IF;
decodifica_anno := '00';
decodifica_mese := '00';
decodifica_giorno := '00';
-- decodifica_anno := TO_CHAR(TO_NUMBER(SUBSTR(c_cod_fis_iva,7,2),'09'));
nAnno := TO_NUMBER(SUBSTR(c_cod_fis_iva,7,2),'09');
IF nAnno < 10 THEN
   decodifica_anno := '0'|| TO_CHAR(nAnno);
ELSE
   decodifica_anno := TO_CHAR(nAnno);
END IF;
IF SUBSTR(c_cod_fis_iva,9,1) = 'A' THEN
   decodifica_mese := '01';
ELSIF SUBSTR(c_cod_fis_iva,9,1) = 'B' THEN
   decodifica_mese := '02';
ELSIF SUBSTR(c_cod_fis_iva,9,1) = 'C' THEN
   decodifica_mese := '03';
ELSIF SUBSTR(c_cod_fis_iva,9,1) = 'D' THEN
   decodifica_mese := '04';
ELSIF SUBSTR(c_cod_fis_iva,9,1) = 'E' THEN
   decodifica_mese := '05';
ELSIF SUBSTR(c_cod_fis_iva,9,1) = 'H' THEN
   decodifica_mese := '06';
ELSIF SUBSTR(c_cod_fis_iva,9,1) = 'L' THEN
   decodifica_mese := '07';
ELSIF SUBSTR(c_cod_fis_iva,9,1) = 'M' THEN
   decodifica_mese := '08';
ELSIF SUBSTR(c_cod_fis_iva,9,1) = 'P' THEN
   decodifica_mese := '09';
ELSIF SUBSTR(c_cod_fis_iva,9,1) = 'R' THEN
   decodifica_mese := '10';
ELSIF SUBSTR(c_cod_fis_iva,9,1) = 'S' THEN
   decodifica_mese := '11';
ELSIF SUBSTR(c_cod_fis_iva,9,1) = 'T' THEN
   decodifica_mese := '12';
END IF;
IF TO_NUMBER(SUBSTR(c_cod_fis_iva,10,2)) > 40 THEN
   decodifica_giorno := TO_CHAR(TO_NUMBER(SUBSTR(c_cod_fis_iva,10,2),'09') - 40);
   IF SUBSTR(decodifica_giorno, 2, 1) IS NULL THEN
      decodifica_giorno := '0'||SUBSTR (decodifica_giorno, 1, 1);
   END IF;
ELSE
   decodifica_giorno := TO_CHAR(TO_NUMBER(SUBSTR(c_cod_fis_iva,10,2)));
   IF SUBSTR (decodifica_giorno, 2, 1) IS NULL THEN
      decodifica_giorno := '0' || SUBSTR (decodifica_giorno, 1, 1);
   END IF;
END IF;
IF SUBSTR(c_data_nas,1,2) = decodifica_giorno AND
   SUBSTR(c_data_nas,4,2) = decodifica_mese   AND
   SUBSTR(c_data_nas,9,2) = decodifica_anno   THEN
   GOTO FINE;
ELSE
   messaggio_errore := 'CODICE FISCALE INCOMPATIBILE CON DATA NASCITA';
   return(messaggio_errore);
END IF;
<<FINE>>
messaggio_errore := '0';
RETURN messaggio_errore;
END;
/

CREATE OR REPLACE PACKAGE BODY PCK_PRODIS_UTILS AS

  FUNCTION handle_number(numero IN NUMBER) return NUMBER IS
    BEGIN
      if (numero is null) then
        return 0;
      elsif (numero < 0) then
        return 0;
      else
        return numero;
      end if;
    EXCEPTION
      WHEN OTHERS THEN
        dbms_output.put_line('ERRORE : '||SUBSTR(SQLERRM,1,110)||'  SQLCODE='||TO_CHAR(SQLCODE));
      return 0;
  END handle_number;

  FUNCTION round_number(numero IN NUMBER) return NUMBER IS
    BEGIN
        return ROUND(handle_number(numero)-0.001);
    EXCEPTION
      WHEN OTHERS THEN
        dbms_output.put_line('ERRORE : '||SUBSTR(SQLERRM,1,110)||'  SQLCODE='||TO_CHAR(SQLCODE));
      return 0;
  END round_number;

  PROCEDURE cancella_dati_provinciali(prospetto_prov_id IN NUMBER) IS
    BEGIN
    /*
     IMPLEMENTARE LA CANCELLAZIONE DALLE SEGUENTI TAVOLE:
       PRO_R_PROSPETTO_PROVINCIA      : DA CANCELLARE PER ULTIMA
       PRO_D_DATI_PROVINCIALI         : DA CANCELLARE PER PENULTIMA
       PRO_D_CATEGORIE_ESCLUSE
       PRO_D_LAVORATORI_IN_FORZA
       PRO_D_PART_TIME
       PRO_D_POSTI_LAVORO_DISP
       PRO_D_PROSPETTO_PROV_SEDE
       PRO_D_PROV_COMPENSAZIONI
       PRO_D_PROV_CONVENZIONE
       PRO_D_PROV_ESONERO
       PRO_D_PROV_GRADUALITA
       PRO_D_PROV_INTERMITTENTI
       PRO_D_PROV_SOSPENSIONE
       ritornare 1 se tutto è andato bene
    */
       DELETE PRO_D_CATEGORIE_ESCLUSE WHERE ID_PROSPETTO_PROV = prospetto_prov_id;
       DELETE PRO_D_LAVORATORI_IN_FORZA WHERE ID_PROSPETTO_PROV = prospetto_prov_id;
       DELETE PRO_D_PART_TIME WHERE ID_PROSPETTO_PROV = prospetto_prov_id;
       DELETE PRO_D_POSTI_LAVORO_DISP WHERE ID_PROSPETTO_PROV = prospetto_prov_id;
       DELETE PRO_D_PROSPETTO_PROV_SEDE WHERE ID_PROSPETTO_PROV = prospetto_prov_id;
       DELETE PRO_D_PROV_COMPENSAZIONI WHERE ID_PROSPETTO_PROV = prospetto_prov_id;
       DELETE PRO_D_PROV_CONVENZIONE WHERE ID_PROSPETTO_PROV = prospetto_prov_id;
       DELETE PRO_D_PROV_ESONERO WHERE ID_PROSPETTO_PROV = prospetto_prov_id;
       DELETE PRO_D_PROV_GRADUALITA WHERE ID_PROSPETTO_PROV = prospetto_prov_id;
       DELETE PRO_D_PROV_INTERMITTENTI WHERE ID_PROSPETTO_PROV = prospetto_prov_id;
       DELETE PRO_D_PROV_SOSPENSIONE WHERE ID_PROSPETTO_PROV = prospetto_prov_id;
       DELETE PRO_D_DATI_PROVINCIALI WHERE ID_PROSPETTO_PROV = prospetto_prov_id;
       DELETE PRO_D_RIEPILOGO_PROVINCIALE WHERE ID_PROSPETTO_PROV = prospetto_prov_id;
       DELETE PRO_R_PROSPETTO_PROVINCIA WHERE ID_PROSPETTO_PROV = prospetto_prov_id;
    EXCEPTION
    WHEN OTHERS THEN
      raise;
  END cancella_dati_provinciali;

  FUNCTION get_num_lav_base_computo_prosp(prospetto_id IN NUMBER) return NUMBER IS
    nLavBaseComputo NUMBER := 0;
    BEGIN
    select  nvl(sum(pro_d_dati_provinciali.base_computo),0) into nLavBaseComputo
        from    pro_d_dati_provinciali, pro_r_prospetto_provincia
        where   pro_d_dati_provinciali.id_prospetto_prov = pro_r_prospetto_provincia.id_prospetto_prov
              and pro_r_prospetto_provincia.id_prospetto = prospetto_id;
      return handle_number(nLavBaseComputo);
    EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : '||SUBSTR(SQLERRM,1,110)||'  SQLCODE='||TO_CHAR(SQLCODE));
        return 0;
    END get_num_lav_base_computo_prosp;

  /* calcola quota di riserva nazionale disabili */
  FUNCTION get_quota_riserva_dis_prosp(prospetto_id IN NUMBER) return NUMBER IS
      quotaRiservaDisab NUMBER := 0;
      sommaBaseComputo NUMBER := 0;
    BEGIN
      select  nvl(sum(pro_d_dati_provinciali.base_computo),0) into sommaBaseComputo
      from    pro_d_dati_provinciali, pro_r_prospetto_provincia
      where   pro_d_dati_provinciali.id_prospetto_prov = pro_r_prospetto_provincia.id_prospetto_prov
              and pro_r_prospetto_provincia.id_prospetto = prospetto_id;
      sommaBaseComputo := handle_number(sommaBaseComputo);
      /*
        Quota di riserva disabili  ->
        Valore calcolato automaticamente sulla base del numero di lavoratori della base di computo nazionale e cioè:
        1)  Si prende SOMMA(PRO_D_DATI_PROVINCIALI.BASE_COMPUTO):
        2)
          - Se è >= 51 dipendenti:
            il valore da mettere è: 7% di SOMMA(PRO_D_DATI_PROVINCIALI.BASE_COMPUTO;
          - Se il valore è >= 15 e <= 35 dipendenti:
            il valore da mettere nella quota riserva disabili è 1
          - Se è >= 36 e <= 50 dipendenti:
            il valore da mettere nella quota riserva disabili è 2
        (nb nel calcolo percentuale, i decimali vengono arrotondati: Se > 0,50 si aggiunge 1 intero; Se <= 0,50 non si aggiunge niente)
      */
      if (sommaBaseComputo >= 51) then
        quotaRiservaDisab := round_number((sommaBaseComputo*(7/100)));
      elsif (sommaBaseComputo >= 15 and sommaBaseComputo <= 35) then
        quotaRiservaDisab := 1;
      elsif (sommaBaseComputo >= 36 and sommaBaseComputo <= 50) then
        quotaRiservaDisab := 2;
      end if;
      return handle_number(quotaRiservaDisab);
    EXCEPTION
      WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : '||SUBSTR(SQLERRM,1,110)||'  SQLCODE='||TO_CHAR(SQLCODE));
      return 0;
    END get_quota_riserva_dis_prosp;

    FUNCTION get_quota_riserva_a18_prosp(prospetto_id IN NUMBER) return NUMBER IS
        quotaRiservaArt18 NUMBER := 0;
    BEGIN
      /*
        Quota di riserva Art. 18 -> Valore calcolato automaticamente con: 1% di SOMMA(PRO_D_DATI_PROVINCIALI.BASE_COMPUTO)
        (i numeri decimali si arrotondano in qst modo: Se > 0,50 si aggiunge 1 intero; Se <= 0,50 non si aggiunge niente).
      */
      quotaRiservaArt18 := round_number(PCK_PRODIS_UTILS.get_num_lav_base_computo_prosp(prospetto_id)*(1/100));
      return handle_number(quotaRiservaArt18);
    EXCEPTION
    WHEN OTHERS THEN
        dbms_output.put_line('ERRORE : '||SUBSTR(SQLERRM,1,110)||'  SQLCODE='||TO_CHAR(SQLCODE));
        return 0;
    END get_quota_riserva_a18_prosp;

    /* calcola riepilogo provinciale */
    PROCEDURE calcola_riepilogo_provinciale (prospetto_id IN NUMBER, cf_operatore IN VARCHAR2) IS
      res_OK NUMBER := 1;
      res_KO NUMBER := 0;

      sommaBaseComputo                NUMBER := 0;
      numScopertureDisabili           NUMBER := 0;
      numScopertureCategorieProtette  NUMBER := 0;
      conteggioOccorrenze             NUMBER := 0;
      num_lav_sospesi                 NUMBER := 0;
      id_provincia_sl                 NUMBER := 0;
      pos                             NUMBER := 1;
      quota_riserva_dis_prosp         NUMBER := 0;
      quota_riserva_dis_naz           NUMBER := 0;
      quota_riserva_a18_prosp         NUMBER := 0;
      quota_riserva_a18_naz           NUMBER := 0;

      riepilogoProvincialeROW pro_d_riepilogo_provinciale%ROWTYPE;
      datiProvincialiROW pro_d_dati_provinciali%ROWTYPE;
      sedeLegaleROW pro_d_sede_legale%ROWTYPE;
      prospettoProvinciaROW pro_r_prospetto_provincia%ROWTYPE;
      CURSOR prospettoProvinciaCURS (prospetto_id IN NUMBER)
      IS
        SELECT *
        from pro_r_prospetto_provincia
        where pro_r_prospetto_provincia.id_prospetto = prospetto_id
        order by pro_r_prospetto_provincia.id_prospetto_prov;
      CURSOR riepilogoProvincialeCURS (prospetto_id IN NUMBER)
      IS
        select  pro_d_riepilogo_provinciale.*
        from    pro_d_riepilogo_provinciale, pro_r_prospetto_provincia
        where   pro_d_riepilogo_provinciale.id_prospetto_prov = pro_r_prospetto_provincia.id_prospetto_prov
                and pro_r_prospetto_provincia.id_prospetto = prospetto_id
        order by pro_d_riepilogo_provinciale.id_prospetto_prov;

      TYPE quote_prosp_prov_TYPE IS RECORD (
        id_prospetto_prov     NUMBER,
        quota_disabili        NUMBER,
        quota_disabili_arrot  NUMBER,
        quota_art_18          NUMBER,
        quota_art_18_arrot    NUMBER
      );
      TYPE quote_prosp_prov_TABLE IS TABLE OF quote_prosp_prov_TYPE INDEX BY BINARY_INTEGER;
      qppTab quote_prosp_prov_TABLE;
      qppElem quote_prosp_prov_TYPE;

    BEGIN

      /* Recupero l'identificativo della provincia della sede legale */
      dbms_output.put_line('[calcola_riepilogo_provinciale] prospetto_id '||prospetto_id);
      select  pro_t_comune.id_provincia into id_provincia_sl
      from    pro_t_comune,pro_d_sede_legale
      where   pro_t_comune.id_t_comune = pro_d_sede_legale.id_t_comune
              and pro_d_sede_legale.id_prospetto=prospetto_id
              and ROWNUM = 1;
      dbms_output.put_line('[calcola_riepilogo_provinciale] id_provincia_sl '||id_provincia_sl);

      sommaBaseComputo := PCK_PRODIS_UTILS.get_num_lav_base_computo_prosp(prospetto_id);
      dbms_output.put_line('[calcola_riepilogo_provinciale] sommaBaseComputo '||sommaBaseComputo);

      OPEN prospettoProvinciaCURS(prospetto_id);
        LOOP
          FETCH prospettoProvinciaCURS INTO prospettoProvinciaROW;
          EXIT WHEN prospettoProvinciaCURS%NOTFOUND;

          qppElem.id_prospetto_prov := 0;
          qppElem.quota_disabili := 0;
          qppElem.quota_disabili_arrot := 0;
          qppElem.quota_art_18 := 0;
          qppElem.quota_art_18_arrot := 0;

          riepilogoProvincialeROW.id_prospetto_prov := prospettoProvinciaROW.id_prospetto_prov;
          qppElem.id_prospetto_prov := prospettoProvinciaROW.id_prospetto_prov;
          dbms_output.put_line('[calcola_riepilogo_provinciale] id_prospetto_prov '||prospettoProvinciaROW.id_prospetto_prov);

          /*
            Recupero informazioni per il prospetto provincia
          */
          select  pro_d_dati_provinciali.* into datiProvincialiROW
          from    pro_d_dati_provinciali
          where   pro_d_dati_provinciali.id_prospetto_prov = riepilogoProvincialeROW.id_prospetto_prov;

          /*
            Recupero informazioni per il numero di lavoratori sospesi per il prospetto provincia
          */
          select nvl((
            select   pro_d_prov_sospensione.n_lavoratori
            from     pro_d_prov_sospensione
            where    pro_d_prov_sospensione.id_prospetto_prov = riepilogoProvincialeROW.id_prospetto_prov
                  and pro_d_prov_sospensione.id_t_stato_concessione = 1)
          ,0) into num_lav_sospesi from dual;
          num_lav_sospesi:=handle_number(num_lav_sospesi);
          dbms_output.put_line('[calcola_riepilogo_provinciale] num_lav_sospesi '||num_lav_sospesi);

          /*
            pro_d_dati_provinciali -> base computo
            Base computo  PRO_D_DATI_PROVINCIALI.BASE_COMPUTO (uguale a  'base computo' di Elenchi provinciali)
          */
          riepilogoProvincialeROW.NUM_LAVORATORI_BASE_COMPUTO :=
            handle_number(NVL(datiProvincialiROW.base_computo,0));
          dbms_output.put_line('[calcola_riepilogo_provinciale] num_lav_sospesi '||num_lav_sospesi);

          /*
            pro_d_prov_compensazioni -> categoria compensazione disabili
            Categoria compensazione disabili  Si mette E o R, in base alla distinct(PRO_D_PROV_COMPENSAZIONI.CATEGORIA_COMPENSAZIONE)
            per CATEGORIA_SOGGETTO = 'D' e stato concessione 'E'
          */
          select (
            select  distinct(pro_d_prov_compensazioni.categoria_compensazione)
              from  pro_d_prov_compensazioni
            where pro_d_prov_compensazioni.categoria_soggetto = 'D'
              and pro_d_prov_compensazioni.id_t_stato_concessione = 1
              and pro_d_prov_compensazioni.id_prospetto_prov = riepilogoProvincialeROW.id_prospetto_prov
          ) into riepilogoProvincialeROW.CAT_COMPENSAZIONE_DISABILI from dual;--recuperare solo un valore
          dbms_output.put_line('[calcola_riepilogo_provinciale] CAT_COMPENSAZIONE_DISABILI '||riepilogoProvincialeROW.CAT_COMPENSAZIONE_DISABILI);

          /*
            N° Compensazione disabili  valore calcolato automaticamente con la somma delle compensazioni
            con CATEGORIA_SOGGETTO = 'D' per la provincia;
          */
          select  NVL(sum(pro_d_prov_compensazioni.n_lavoratori),0)
                  into riepilogoProvincialeROW.NUM_COMPENSAZIONE_DISABILI
          from    pro_d_prov_compensazioni
          where   pro_d_prov_compensazioni.categoria_soggetto = 'D'
                  and pro_d_prov_compensazioni.id_prospetto_prov = riepilogoProvincialeROW.id_prospetto_prov
                  and pro_d_prov_compensazioni.id_t_stato_concessione = 1;
          riepilogoProvincialeROW.NUM_COMPENSAZIONE_DISABILI:=handle_number(riepilogoProvincialeROW.NUM_COMPENSAZIONE_DISABILI);
          dbms_output.put_line('[calcola_riepilogo_provinciale] NUM_COMPENSAZIONE_DISABILI '||riepilogoProvincialeROW.NUM_COMPENSAZIONE_DISABILI);

          /*
            Categoria compensazione -> categorie protette  Si mette E o R, in base alla
            distinct(PRO_D_PROV_COMPENSAZIONI.CATEGORIA_COMPENSAZIONE)
            per CATEGORIA_SOGGETTO = 'C'
          */
          select (
            select  distinct(pro_d_prov_compensazioni.categoria_compensazione)
            from    pro_d_prov_compensazioni
            where   pro_d_prov_compensazioni.categoria_soggetto = 'C'
                    and pro_d_prov_compensazioni.id_prospetto_prov = riepilogoProvincialeROW.id_prospetto_prov
          ) into riepilogoProvincialeROW.CAT_COMPENSAZIONE_CATE_PROT from dual;--recuperare solo un valore
          dbms_output.put_line('[calcola_riepilogo_provinciale] CAT_COMPENSAZIONE_CATE_PROT '||riepilogoProvincialeROW.CAT_COMPENSAZIONE_CATE_PROT);

          /*
          N° Compensazioni categorie protette  -> valore calcolato automaticamente con la somma delle compensazioni
          con CATEGORIA_SOGGETTO = 'C' per la provincia;
          */
          select  NVL(sum(pro_d_prov_compensazioni.n_lavoratori),0)
                  into riepilogoProvincialeROW.NUM_COMPENSAZIONI_CATE_PROT
          from    pro_d_prov_compensazioni
          where   pro_d_prov_compensazioni.categoria_soggetto = 'C'
                  and pro_d_prov_compensazioni.id_prospetto_prov = riepilogoProvincialeROW.id_prospetto_prov
                  and pro_d_prov_compensazioni.id_t_stato_concessione = 1;
          riepilogoProvincialeROW.NUM_COMPENSAZIONI_CATE_PROT:=handle_number(riepilogoProvincialeROW.NUM_COMPENSAZIONI_CATE_PROT);
          dbms_output.put_line('[calcola_riepilogo_provinciale] NUM_COMPENSAZIONI_CATE_PROT '||riepilogoProvincialeROW.NUM_COMPENSAZIONI_CATE_PROT);

          /*
            N° Disabili in forza (L.68/99 art.1) ->
              (
                PRO_D_DATI_PROVINCIALI.N_DISABILI_IN_FORZA
                +  PRO_D_DATI_PROVINCIALI.N_CENTRAL_TELEFO_NONVEDENTI
                + PRO_D_DATI_PROVINCIALI.N_TERARIAB_MASSOFIS_NONVED
              )
          */
          riepilogoProvincialeROW.NUM_DISABILI_IN_FORZA :=
            handle_number(
              NVL(
                datiProvincialiROW.N_DISABILI_IN_FORZA
                + datiProvincialiROW.N_CENTRAL_TELEFO_NONVEDENTI
                + datiProvincialiROW.N_TERARIAB_MASSOFIS_NONVED,0));
          dbms_output.put_line('[calcola_riepilogo_provinciale] NUM_DISABILI_IN_FORZA '||riepilogoProvincialeROW.NUM_DISABILI_IN_FORZA);

          /*
            N° Categorie protette in forza (L.68/99 art.18) ->  PRO_D_DATI_PROVINCIALI.N_CATE_PROT_FORZA
          */
          riepilogoProvincialeROW.NUM_CAT_PROT_IN_FORZA :=
            handle_number(NVL(datiProvincialiROW.N_CATE_PROT_FORZA,0));
          dbms_output.put_line('[calcola_riepilogo_provinciale] NUM_CAT_PROT_IN_FORZA '||riepilogoProvincialeROW.NUM_CAT_PROT_IN_FORZA);

          /*
            N° Categorie protette in forza (L.68/99 art.18 ) conteggiate come disabili  PRO_D_DATI_PROVINCIALI.N_CATE_PROT_FORZA_CNT_DIS
          */
          riepilogoProvincialeROW.NUM_CAT_PROT_IN_FORZA_CONT_DIS :=
            handle_number(NVL(datiProvincialiROW.N_CATE_PROT_FORZA_CNT_DIS,0));
          dbms_output.put_line('[calcola_riepilogo_provinciale] NUM_CAT_PROT_IN_FORZA_CONT_DIS '||riepilogoProvincialeROW.NUM_CAT_PROT_IN_FORZA_CONT_DIS);

          /*
            Quota di riserva disabili ->
            Valore calcolato automaticamente sulla base del numero di lavoratori della base di computo nazionale e cioè:

        1) Si prende SOMMA(PRO_D_DATI_PROVINCIALI.BASE_COMPUTO):
        2)
          - Se il valore è >= 15 e <= 35 dipendenti:
            il valore da mettere nella quota riserva disabili è 1 sulla provincia della sede legale;
            su tutte le altre si mette 0

          - Se è >= 36 e <= 50 dipendenti:
            il valore da mettere nella quota riserva disabili è 2 sulla provincia della sede legale;
            su tutte le altre si mette 0

          - Se è >= 51 dipendenti:
            il valore da mettere è: 7% di PRO_D_DATI_PROVINCIALI.BASE_COMPUTO;

      (nb nel calcolo percentuale, i decimali vengono arrotondati: Se > 0,50 si aggiunge 1 intero; Se <= 0,50 non si aggiunge niente).
          */
          if (sommaBaseComputo >= 15 and sommaBaseComputo <= 35) then
            if (id_provincia_sl = prospettoProvinciaROW.id_t_provincia) then
              riepilogoProvincialeROW.QUOTA_RISERVA_DISABILI := 1;
            else
              riepilogoProvincialeROW.QUOTA_RISERVA_DISABILI := 0;
            end if;

          elsif sommaBaseComputo >= 36 and sommaBaseComputo <= 50 then
            if (id_provincia_sl = prospettoProvinciaROW.id_t_provincia) then
              riepilogoProvincialeROW.QUOTA_RISERVA_DISABILI := 2;
            else
              riepilogoProvincialeROW.QUOTA_RISERVA_DISABILI := 0;
            end if;

          elsif sommaBaseComputo > 51 then
            qppElem.quota_disabili := NVL(riepilogoProvincialeROW.NUM_LAVORATORI_BASE_COMPUTO*(7/100),0);
            qppElem.quota_disabili_arrot := NVL(round_number(riepilogoProvincialeROW.NUM_LAVORATORI_BASE_COMPUTO*(7/100)),0);
            riepilogoProvincialeROW.QUOTA_RISERVA_DISABILI := handle_number(qppElem.quota_disabili_arrot);

          else
            riepilogoProvincialeROW.QUOTA_RISERVA_DISABILI := 0;

          end if;
          dbms_output.put_line('[calcola_riepilogo_provinciale] QUOTA_RISERVA_DISABILI '||riepilogoProvincialeROW.QUOTA_RISERVA_DISABILI);

          /*
          Quota di riserva Art. 18
          Valore calcolato automaticamente con: 1% di PRO_D_DATI_PROVINCIALI.BASE_COMPUTO

          1) Si prende SOMMA(PRO_D_DATI_PROVINCIALI.BASE_COMPUTO):
          2)
            -  Se il valore è >= 51 e <= 150:
              il valore da mettere nella quota di riserva art. 18 è 1, sulla provincia della sede legale;

            -  Se il valore è > 150,
              il valore viene calcolato automaticamente con: 1% di PRO_D_DATI_PROVINCIALI.BASE_COMPUTO

          (i numeri decimali si arrotondano in qst modo: Se > 0,50 si aggiunge 1 intero; Se <= 0,50 non si aggiunge niente)
          */
          if sommaBaseComputo < 51 then
            riepilogoProvincialeROW.QUOTA_RISERVA_ART_18 := 0;
          elsif (sommaBaseComputo >= 51 and sommaBaseComputo <= 150) then
            if (id_provincia_sl = prospettoProvinciaROW.id_t_provincia) then
              riepilogoProvincialeROW.QUOTA_RISERVA_ART_18 := 1;
            else
              riepilogoProvincialeROW.QUOTA_RISERVA_ART_18 := 0;
            end if;
          elsif (sommaBaseComputo > 150 ) then
            qppElem.quota_art_18 := NVL(riepilogoProvincialeROW.NUM_LAVORATORI_BASE_COMPUTO*(1/100),0);
            qppElem.quota_art_18_arrot := NVL(round_number(riepilogoProvincialeROW.NUM_LAVORATORI_BASE_COMPUTO*(1/100)),0);
            riepilogoProvincialeROW.QUOTA_RISERVA_ART_18 := handle_number(qppElem.quota_art_18_arrot);

          else
            riepilogoProvincialeROW.QUOTA_RISERVA_ART_18 := 0;

          end if;
          dbms_output.put_line('[calcola_riepilogo_provinciale] QUOTA_RISERVA_ART_18 '||riepilogoProvincialeROW.QUOTA_RISERVA_ART_18);

          /*
            N° Lavoratori in sospensione ->  PRO_D_PROV_SOSPENSIONE.N_LAVORATORI (uguale a 'lavoratori in sospensione' di Elenchi provinciali)
          */
          select NVL((
            select  PRO_D_PROV_SOSPENSIONE.n_lavoratori
            from    PRO_D_PROV_SOSPENSIONE
            where   pro_d_prov_sospensione.id_prospetto_prov = riepilogoProvincialeROW.id_prospetto_prov
                and PRO_D_PROV_SOSPENSIONE.id_t_stato_concessione = 1
          ),0) into riepilogoProvincialeROW.NUM_LAVORATORI_SOSPENSIONE from dual;
          riepilogoProvincialeROW.NUM_LAVORATORI_SOSPENSIONE:=handle_number(riepilogoProvincialeROW.NUM_LAVORATORI_SOSPENSIONE);
          dbms_output.put_line('[calcola_riepilogo_provinciale] NUM_LAVORATORI_SOSPENSIONE '||riepilogoProvincialeROW.NUM_LAVORATORI_SOSPENSIONE);

          /*
            N° posizioni esonerate ->  PRO_D_PROV_ESONERO.N_LAVORATORI_ESONERO (come 'lavoratori in esonero' di Elenchi provinciali)
          */
          select NVL((
            select  pro_d_prov_esonero.n_lavoratori_esonero
            from    PRO_D_PROV_ESONERO
            where   pro_d_prov_esonero.id_prospetto_prov = riepilogoProvincialeROW.id_prospetto_prov
                and PRO_D_PROV_ESONERO.id_t_stato_concessione = 1
          ),0) into riepilogoProvincialeROW.NUM_POSIZIONI_ESONERATE from dual;
          riepilogoProvincialeROW.NUM_POSIZIONI_ESONERATE:=handle_number(riepilogoProvincialeROW.NUM_POSIZIONI_ESONERATE);
          dbms_output.put_line('[calcola_riepilogo_provinciale] NUM_POSIZIONI_ESONERATE '||riepilogoProvincialeROW.NUM_POSIZIONI_ESONERATE);

          select  count(1) into conteggioOccorrenze
          from    pro_d_riepilogo_provinciale
          where   pro_d_riepilogo_provinciale.id_prospetto_prov = riepilogoProvincialeROW.id_prospetto_prov;
          dbms_output.put_line('[calcola_riepilogo_provinciale] conteggioOccorrenze '||conteggioOccorrenze);

          riepilogoProvincialeROW.NUM_SCOPERTURE_DISABILI := 0;
          riepilogoProvincialeROW.NUM_SCOPERTURE_CAT_PROT := 0;

          if conteggioOccorrenze > 0 then
            riepilogoProvincialeROW.d_aggiorn := sysdate;
            riepilogoProvincialeROW.cod_user_aggiorn := cf_operatore;
            update  pro_d_riepilogo_provinciale
            set     pro_d_riepilogo_provinciale.cat_compensazione_cate_prot = riepilogoProvincialeROW.cat_compensazione_cate_prot,
                    pro_d_riepilogo_provinciale.cat_compensazione_disabili = riepilogoProvincialeROW.cat_compensazione_disabili,
                    pro_d_riepilogo_provinciale.cod_user_aggiorn = riepilogoProvincialeROW.cod_user_aggiorn,
                    pro_d_riepilogo_provinciale.d_aggiorn = riepilogoProvincialeROW.d_aggiorn,
                    pro_d_riepilogo_provinciale.id_prospetto_prov = riepilogoProvincialeROW.id_prospetto_prov,
                    pro_d_riepilogo_provinciale.num_cat_prot_in_forza = riepilogoProvincialeROW.num_cat_prot_in_forza,
                    pro_d_riepilogo_provinciale.num_cat_prot_in_forza_cont_dis = riepilogoProvincialeROW.num_cat_prot_in_forza_cont_dis,
                    pro_d_riepilogo_provinciale.num_compensazione_disabili = riepilogoProvincialeROW.num_compensazione_disabili,
                    pro_d_riepilogo_provinciale.num_compensazioni_cate_prot = riepilogoProvincialeROW.num_compensazioni_cate_prot,
                    pro_d_riepilogo_provinciale.num_disabili_in_forza = riepilogoProvincialeROW.num_disabili_in_forza,
                    pro_d_riepilogo_provinciale.num_lavoratori_base_computo = riepilogoProvincialeROW.num_lavoratori_base_computo,
                    pro_d_riepilogo_provinciale.num_lavoratori_sospensione = riepilogoProvincialeROW.num_lavoratori_sospensione,
                    pro_d_riepilogo_provinciale.num_posizioni_esonerate = riepilogoProvincialeROW.num_posizioni_esonerate,
                    pro_d_riepilogo_provinciale.num_scoperture_cat_prot = riepilogoProvincialeROW.num_scoperture_cat_prot,
                    pro_d_riepilogo_provinciale.num_scoperture_disabili = riepilogoProvincialeROW.num_scoperture_disabili,
                    pro_d_riepilogo_provinciale.quota_riserva_art_18 = riepilogoProvincialeROW.quota_riserva_art_18,
                    pro_d_riepilogo_provinciale.quota_riserva_disabili = riepilogoProvincialeROW.quota_riserva_disabili
            where   pro_d_riepilogo_provinciale.id_prospetto_prov = riepilogoProvincialeROW.id_prospetto_prov;
          else
            riepilogoProvincialeROW.d_inserim := sysdate;
            riepilogoProvincialeROW.cod_user_inserim := cf_operatore;
            insert into pro_d_riepilogo_provinciale values riepilogoProvincialeROW;
          end if;

          qppTab(pos) := qppElem;
          pos := pos + 1;

        END LOOP;
      CLOSE prospettoProvinciaCURS;

        /*
      Dopo aver applicato la quota del 7% se la somma delle quote di riserva delle province fosse inferiore alla quota di
      riserva nazionale, la quota di riserva in più, che non si saprebbe a quale provincia abbinare è da applicare alla
      provincia della sede legale; invece, se la somma delle quote di riserva delle province fosse superiore alla quota
      di riserva nazionale, la quota di riserva che bisognerebbe togliere, viene detratta dalla provincia che ha un resto
      decimale maggiore.
      Quindi:

        - se PRO_D_RIEPILOGO_NAZIONALE.QUOTA_RISERVA_DISABILI - SOMMA('quote riserva disabili tutte le prov') > 0 allora:
          'quota riserva disabili sede legale' =
            'quota riserva disabili sede legale'
            + (
              PRO_D_RIEPILOGO_NAZIONALE.QUOTA_RISERVA_DISABILI - SOMMA('quote riserva disabili tutte le prov')
              )

        - se PRO_D_RIEPILOGO_NAZIONALE.QUOTA_RISERVA_DISABILI - SOMMA('quote riserva disabili tutte le prov') < 0 allora:
          Si prende la provincia che, a seguito del calcolo del 7%, ha il resto decimale maggiore;
          a tale provincia si ricalcola la sua stessa quota:
          'quota riserva disabili'
          - (
            SOMMA('quote riserva disabili tutte le prov')  -  PRO_D_RIEPILOGO_NAZIONALE.QUOTA_RISERVA_DISABILI
            )
        */

            select pro_d_riepilogo_nazionale.quota_riserva_disabili into quota_riserva_dis_naz
            from pro_d_riepilogo_nazionale
            where pro_d_riepilogo_nazionale.id_prospetto = prospetto_id;
            dbms_output.put_line('[calcola_riepilogo_provinciale] quota_riserva_dis_naz '||quota_riserva_dis_naz);

            select  sum(pro_d_riepilogo_provinciale.quota_riserva_disabili) into quota_riserva_dis_prosp
            from    pro_d_riepilogo_provinciale, pro_r_prospetto_provincia
                    where pro_d_riepilogo_provinciale.id_prospetto_prov = pro_r_prospetto_provincia.id_prospetto_prov
                    and pro_r_prospetto_provincia.id_prospetto = prospetto_id ;
            dbms_output.put_line('[calcola_riepilogo_provinciale] quota_riserva_dis_prosp '||quota_riserva_dis_prosp);

            if ((quota_riserva_dis_naz - quota_riserva_dis_prosp) > 0) then
              update  pro_d_riepilogo_provinciale
              set     pro_d_riepilogo_provinciale.quota_riserva_disabili =
                        pro_d_riepilogo_provinciale.quota_riserva_disabili + (quota_riserva_dis_naz - quota_riserva_dis_prosp)
              where pro_d_riepilogo_provinciale.id_prospetto_prov = (
                  select  pro_r_prospetto_provincia.id_prospetto_prov
                  from    pro_r_prospetto_provincia
                  where   pro_r_prospetto_provincia.id_t_provincia = id_provincia_sl
                    and   pro_r_prospetto_provincia.id_prospetto = prospetto_id);

            elsif ((quota_riserva_dis_naz - quota_riserva_dis_prosp) < 0) then
              qppElem.id_prospetto_prov := 0;
              qppElem.quota_disabili := 0;
              qppElem.quota_art_18 := 0;
              for i in qppTab.FIRST .. qppTab.LAST
              loop
                if ((qppTab(i).quota_disabili - TRUNC(qppTab(i).quota_disabili, 0)) >
                      (qppElem.quota_disabili - TRUNC(qppElem.quota_disabili, 0))) then
                    qppElem.id_prospetto_prov := qppTab(i).id_prospetto_prov;
                    qppElem.quota_disabili := qppTab(i).quota_disabili;
                    qppElem.quota_art_18 := qppTab(i).quota_art_18;
                end if;
              end loop;
              update pro_d_riepilogo_provinciale
                set pro_d_riepilogo_provinciale.quota_riserva_disabili =
                  pro_d_riepilogo_provinciale.quota_riserva_disabili - (quota_riserva_dis_prosp - quota_riserva_dis_naz)
              where pro_d_riepilogo_provinciale.id_prospetto_prov = qppElem.id_prospetto_prov;
            end if;

            /*
      Dopo aver applicato la quota del 1%, se la somma delle quote di riserva delle province fosse inferiore alla quota di
      riserva nazionale, la quota di riserva in più, che non si saprebbe a quale provincia abbinare è da applicare alla
      provincia della sede legale; invece, se la somma delle quote di riserva delle province fosse superiore alla quota
      di riserva nazionale, la quota di riserva che bisognerebbe togliere, viene detratta dalla provincia che ha un resto
      decimale maggiore.
      Quindi:

        - se PRO_D_RIEPILOGO_NAZIONALE.QUOTA_RISERVA_ART_18 - SOMMA('quote riserva art 18 tutte le prov') > 0 allora:
          'quota riserva art 18 sede legale' =
            'quota riserva art 18 sede legale'
            + (PRO_D_RIEPILOGO_NAZIONALE.QUOTA_RISERVA_ART_18I
            - SOMMA('quote riserva art 18 tutte le prov')


        - se PRO_D_RIEPILOGO_NAZIONALE.QUOTA_RISERVA_DISABILI - SOMMA('quote riserva disabili tutte le prov') < 0 allora:
          Si prende la provincia che, a seguito del calcolo del 1%, ha il resto decimale maggiore;
          a tale provincia si ricalcola la sua stessa quota:
          'quota riserva art 18'
          - (
            SOMMA('quote riserva art 18 tutte le prov')  -  PRO_D_RIEPILOGO_NAZIONALE.QUOTA_RISERVA_ART_18
            )
            */

            select pro_d_riepilogo_nazionale.quota_riserva_art_18 into quota_riserva_a18_naz
            from pro_d_riepilogo_nazionale
            where pro_d_riepilogo_nazionale.id_prospetto = prospetto_id;
            dbms_output.put_line('[calcola_riepilogo_provinciale] quota_riserva_a18_naz '||quota_riserva_a18_naz);

            select  sum(pro_d_riepilogo_provinciale.quota_riserva_art_18) into quota_riserva_a18_prosp
            from    pro_d_riepilogo_provinciale, pro_r_prospetto_provincia
                    where pro_d_riepilogo_provinciale.id_prospetto_prov = pro_r_prospetto_provincia.id_prospetto_prov
                    and pro_r_prospetto_provincia.id_prospetto = prospetto_id ;
            dbms_output.put_line('[calcola_riepilogo_provinciale] quota_riserva_a18_prosp '||quota_riserva_a18_prosp);

            if ((quota_riserva_a18_naz - quota_riserva_a18_prosp) > 0) then
              update pro_d_riepilogo_provinciale
                set pro_d_riepilogo_provinciale.quota_riserva_art_18 =
                  pro_d_riepilogo_provinciale.quota_riserva_art_18 + (quota_riserva_a18_naz - quota_riserva_a18_prosp)
              where pro_d_riepilogo_provinciale.id_prospetto_prov = (
                  select  pro_r_prospetto_provincia.id_prospetto_prov
                  from    pro_r_prospetto_provincia
                  where   pro_r_prospetto_provincia.id_t_provincia = id_provincia_sl
                    and   pro_r_prospetto_provincia.id_prospetto = prospetto_id);

            elsif ((quota_riserva_a18_naz - quota_riserva_a18_prosp) < 0) then
              qppElem.id_prospetto_prov := 0;
              qppElem.quota_disabili := 0;
              qppElem.quota_art_18 := 0;
              for i in qppTab.FIRST .. qppTab.LAST
              loop
                if ((qppTab(i).quota_art_18 - TRUNC(qppTab(i).quota_art_18, 0)) >
                    (qppElem.quota_art_18 - TRUNC(qppElem.quota_art_18, 0))) then
                  qppElem.id_prospetto_prov := qppTab(i).id_prospetto_prov;
                  qppElem.quota_disabili := qppTab(i).quota_disabili;
                  qppElem.quota_art_18 := qppTab(i).quota_art_18;
                end if;
              end loop;
              update pro_d_riepilogo_provinciale
                set pro_d_riepilogo_provinciale.quota_riserva_art_18 =
                  pro_d_riepilogo_provinciale.quota_riserva_art_18 - (quota_riserva_dis_prosp - quota_riserva_dis_naz)
              where pro_d_riepilogo_provinciale.id_prospetto_prov = qppElem.id_prospetto_prov;
            end if;

      dbms_output.put_line('[calcola_riepilogo_provinciale] gestione scoperture ');

      OPEN riepilogoProvincialeCURS(prospetto_id);
        LOOP
          FETCH riepilogoProvincialeCURS INTO riepilogoProvincialeROW;
          EXIT WHEN riepilogoProvincialeCURS%NOTFOUND;

          dbms_output.put_line('[calcola_riepilogo_provinciale] gestione scoperture - id_prospetto_prov '||riepilogoProvincialeROW.id_prospetto_prov);

          select  pro_d_dati_provinciali.* into datiProvincialiROW
          from    pro_d_dati_provinciali
          where   pro_d_dati_provinciali.id_prospetto_prov = riepilogoProvincialeROW.id_prospetto_prov;

         /*
            N° Scoperture disabili (L.68/99 art.1)  ->
            Valore calcolato automaticamente con le seguenti formule:

        - Se 'Categoria compensazione disabili' NULL:
          'N° Scoperture disabili (L.68/99 art.1)' =
            'Quota di riserva disabili'
            - ('N° Disabili in forza (L.68/99 art.1)'  + 'N° Categorie protette in forza (L.68/99 art.18 ) conteggiate come disabili')
            - 'N° posizioni esonerate'
            - 'N° lavoratori sospesi'

        - Se 'Categoria compensazione disabili' = 'E':
          'N° Scoperture disabili (L.68/99 art.1)' =
            'Quota di riserva disabili'
            - ('N° Disabili in forza (L.68/99 art.1)'  + 'N° Categorie protette in forza (L.68/99 art.18 ) conteggiate come disabili')
             + somma('N° Compensazione disabili')
             - 'N° posizioni esonerate'
             - 'N° lavoratori sospesi'

        - Se 'Categoria compensazione disabili' = 'R':
          N° Scoperture disabili (L.68/99 art.1) =
            'Quota di riserva disabili'
            - ('N° Disabili in forza (L.68/99 art.1)'  + 'N° Categorie protette in forza (L.68/99 art.18 ) conteggiate come disabili')
             - somma('N° Compensazione disabili')
             - 'N° posizioni esonerate'
             - 'N° lavoratori sospesi'

      (NB il valore di 'n° scoperture disabili' non può essere negativo, pertanto, se il valore
      risultante dai seguenti calcoli è < 0, il valore da inserire sarà sempre 0.)
          */
          dbms_output.put_line('[calcola_riepilogo_provinciale] gestione scoperture - CAT_COMPENSAZIONE_DISABILI '||riepilogoProvincialeROW.CAT_COMPENSAZIONE_DISABILI);
          if riepilogoProvincialeROW.CAT_COMPENSAZIONE_DISABILI is null then
            numScopertureDisabili :=
              riepilogoProvincialeROW.QUOTA_RISERVA_DISABILI
              - ( riepilogoProvincialeROW.NUM_DISABILI_IN_FORZA + riepilogoProvincialeROW.NUM_CAT_PROT_IN_FORZA_CONT_DIS)
              - riepilogoProvincialeROW.NUM_POSIZIONI_ESONERATE
              - riepilogoProvincialeROW.NUM_LAVORATORI_SOSPENSIONE;

          elsif riepilogoProvincialeROW.CAT_COMPENSAZIONE_DISABILI = 'E' then
            numScopertureDisabili :=
              riepilogoProvincialeROW.QUOTA_RISERVA_DISABILI
                - (riepilogoProvincialeROW.NUM_DISABILI_IN_FORZA + riepilogoProvincialeROW.NUM_CAT_PROT_IN_FORZA_CONT_DIS)
                + riepilogoProvincialeROW.NUM_COMPENSAZIONE_DISABILI
                - riepilogoProvincialeROW.NUM_POSIZIONI_ESONERATE
                - riepilogoProvincialeROW.NUM_LAVORATORI_SOSPENSIONE;
          elsif riepilogoProvincialeROW.CAT_COMPENSAZIONE_DISABILI = 'R' then
            numScopertureDisabili :=
              riepilogoProvincialeROW.QUOTA_RISERVA_DISABILI
                - (riepilogoProvincialeROW.NUM_DISABILI_IN_FORZA + riepilogoProvincialeROW.NUM_CAT_PROT_IN_FORZA_CONT_DIS)
                - riepilogoProvincialeROW.NUM_COMPENSAZIONE_DISABILI
                - riepilogoProvincialeROW.NUM_POSIZIONI_ESONERATE
                - riepilogoProvincialeROW.NUM_LAVORATORI_SOSPENSIONE;
          end if;
          riepilogoProvincialeROW.NUM_SCOPERTURE_DISABILI := handle_number(NVL(numScopertureDisabili,0));
          dbms_output.put_line('[calcola_riepilogo_provinciale] gestione scoperture - NUM_SCOPERTURE_DISABILI '||riepilogoProvincialeROW.NUM_SCOPERTURE_DISABILI);

          /*
            N° Scoperture categorie protette (L.68/99 art.18) ->
            Valore calcolato automaticamente con le seguenti formule:

        - Se 'Categoria compensazione cat. protette' NULL:
          N° Scoperture categorie protette (L.68/99 art.18)  =
            'Quota di riserva Art. 18' - 'N° Categorie protette in forza (L.68/99 art.18)'

        - Se 'Categoria compensazione cat. protette' = 'E':
          N° Scoperture categorie protette (L.68/99 art.18)  =
            'Quota di riserva Art. 18'
            - 'N° Categorie protette in forza (L.68/99 art.18)'
            + somma('N° Compensazione categorie Protette')
            - 'N° lavoratori sospesi'

        - Se 'Categoria compensazione cat. Protette' = 'R':
          N° Scoperture categorie protette (L.68/99 art.18)  =
            'Quota di riserva Art. 18'
            - 'N° Categorie protette in forza (L.68/99 art.18) '
            - somma('N° Compensazione categorie Protette')
            - 'N° lavoratori sospesi'

      (NB il valore di 'n° scoperture categorie protette' non può essere negativo, pertanto, se il valore
      risultante dai seguenti calcoli è < 0, il valore da inserire sarà sempre 0.)
          */
          dbms_output.put_line('[calcola_riepilogo_provinciale] gestione scoperture - CAT_COMPENSAZIONE_CATE_PROT '||riepilogoProvincialeROW.CAT_COMPENSAZIONE_CATE_PROT);
          if riepilogoProvincialeROW.CAT_COMPENSAZIONE_CATE_PROT is null then
            dbms_output.put_line('[calcola_riepilogo_provinciale] gestione scoperture - QUOTA_RISERVA_ART_18 '||riepilogoProvincialeROW.QUOTA_RISERVA_ART_18);
            dbms_output.put_line('[calcola_riepilogo_provinciale] gestione scoperture - n_cate_prot_forza '||riepilogoProvincialeROW.NUM_CAT_PROT_IN_FORZA);
            numScopertureCategorieProtette :=
              riepilogoProvincialeROW.QUOTA_RISERVA_ART_18 - handle_number(riepilogoProvincialeROW.NUM_CAT_PROT_IN_FORZA);

          elsif riepilogoProvincialeROW.CAT_COMPENSAZIONE_CATE_PROT = 'E' then
            dbms_output.put_line('[calcola_riepilogo_provinciale] gestione scoperture - QUOTA_RISERVA_ART_18 '||riepilogoProvincialeROW.QUOTA_RISERVA_ART_18);
            dbms_output.put_line('[calcola_riepilogo_provinciale] gestione scoperture - n_cate_prot_forza '||riepilogoProvincialeROW.NUM_CAT_PROT_IN_FORZA);
            dbms_output.put_line('[calcola_riepilogo_provinciale] gestione scoperture - NUM_COMPENSAZIONI_CATE_PROT '||riepilogoProvincialeROW.NUM_COMPENSAZIONI_CATE_PROT);
            dbms_output.put_line('[calcola_riepilogo_provinciale] gestione scoperture - num_lav_sospesi '||riepilogoProvincialeROW.NUM_LAVORATORI_SOSPENSIONE);
            numScopertureCategorieProtette :=
              riepilogoProvincialeROW.QUOTA_RISERVA_ART_18
              - handle_number(riepilogoProvincialeROW.NUM_CAT_PROT_IN_FORZA)
              + riepilogoProvincialeROW.NUM_COMPENSAZIONI_CATE_PROT;

          elsif riepilogoProvincialeROW.CAT_COMPENSAZIONE_CATE_PROT = 'R' then
            dbms_output.put_line('[calcola_riepilogo_provinciale] gestione scoperture - QUOTA_RISERVA_ART_18 '||riepilogoProvincialeROW.QUOTA_RISERVA_ART_18);
            dbms_output.put_line('[calcola_riepilogo_provinciale] gestione scoperture - n_cate_prot_forza '||riepilogoProvincialeROW.NUM_CAT_PROT_IN_FORZA);
            dbms_output.put_line('[calcola_riepilogo_provinciale] gestione scoperture - NUM_COMPENSAZIONI_CATE_PROT '||riepilogoProvincialeROW.NUM_COMPENSAZIONI_CATE_PROT);
            dbms_output.put_line('[calcola_riepilogo_provinciale] gestione scoperture - num_lav_sospesi '||riepilogoProvincialeROW.NUM_LAVORATORI_SOSPENSIONE);
            numScopertureCategorieProtette :=
              riepilogoProvincialeROW.QUOTA_RISERVA_ART_18
              - handle_number(riepilogoProvincialeROW.NUM_CAT_PROT_IN_FORZA)
              - riepilogoProvincialeROW.NUM_COMPENSAZIONI_CATE_PROT;
          end if;
          riepilogoProvincialeROW.NUM_SCOPERTURE_CAT_PROT := handle_number(NVL(numScopertureCategorieProtette,0));
          dbms_output.put_line('[calcola_riepilogo_provinciale] gestione scoperture - NUM_SCOPERTURE_CAT_PROT '||riepilogoProvincialeROW.NUM_SCOPERTURE_CAT_PROT);

            update  pro_d_riepilogo_provinciale
            set     pro_d_riepilogo_provinciale.NUM_SCOPERTURE_DISABILI = riepilogoProvincialeROW.NUM_SCOPERTURE_DISABILI,
                    pro_d_riepilogo_provinciale.NUM_SCOPERTURE_CAT_PROT = riepilogoProvincialeROW.NUM_SCOPERTURE_CAT_PROT
            where   pro_d_riepilogo_provinciale.id_prospetto_prov = riepilogoProvincialeROW.id_prospetto_prov;

        END LOOP;
      CLOSE riepilogoProvincialeCURS;

      --return res_OK;
    EXCEPTION
      WHEN OTHERS THEN
        dbms_output.put_line('ERRORE : '||SUBSTR(SQLERRM,1,110)||'  SQLCODE='||TO_CHAR(SQLCODE));
        raise;
      --return res_KO;
    END calcola_riepilogo_provinciale;


    /* calcola riepilogo nazionale */
    PROCEDURE calcola_riepilogo_nazionale (prospetto_id IN NUMBER,cf_operatore IN VARCHAR2) IS
      res_OK NUMBER := 1;
      res_KO NUMBER := 0;

      riepilogoNazionaleROW pro_d_riepilogo_nazionale%ROWTYPE;

      datiProvincialiROW pro_d_dati_provinciali%ROWTYPE;
      nLavForzaNaz NUMBER := 0;
      nLavBaseComputo NUMBER := 0;
      categoriaAzienda VARCHAR2(1) := '';

      quotaRiservaDisab NUMBER := 0;
      quotaRiservaArt18 NUMBER := 0;
      nLavInSosp NUMBER := 0;
      nPosizEsonerat NUMBER := 0;
      nDisabiliInForza NUMBER := 0;
      nCatProtInForza NUMBER := 0;
      nCatProtInForzaDis NUMBER := 0;
      nScopertDisab NUMBER := 0;
      nScopertCatProt NUMBER := 0;

      conteggioOccorrenze NUMBER := 0;
      num_lav_sospesi NUMBER := 0;
    BEGIN

      riepilogoNazionaleROW.id_prospetto := prospetto_id;
      dbms_output.put_line('[calcola_riepilogo_nazionale] riepilogoNazionaleROW.id_prospetto '||riepilogoNazionaleROW.id_prospetto);

      /*
        Recupero informazioni per il numero di lavoratori sospesi per il prospetto provincia
      */
      select   sum(pro_d_prov_sospensione.n_lavoratori) into num_lav_sospesi
      from     pro_d_prov_sospensione, pro_r_prospetto_provincia
      where    pro_d_prov_sospensione.id_prospetto_prov = pro_r_prospetto_provincia.id_prospetto_prov
              and pro_r_prospetto_provincia.id_prospetto=prospetto_id
              and pro_d_prov_sospensione.id_t_stato_concessione = 1;
      num_lav_sospesi:=handle_number(num_lav_sospesi);
      dbms_output.put_line('[calcola_riepilogo_nazionale] num_lav_sospesi '||num_lav_sospesi);

      /*
        N° Lavoratori in forza nazionale ->
        Valore calcolato automaticamente con: SOMMA(PRO_D_DATI_PROVINCIALI.N_TOTALE_LAVORAT_DIPENDENTI).
      */
      select  sum(pro_d_dati_provinciali.N_TOTALE_LAVORAT_DIPENDENTI)
              into nLavForzaNaz
      from    pro_d_dati_provinciali, pro_r_prospetto_provincia
      where   pro_d_dati_provinciali.id_prospetto_prov = pro_r_prospetto_provincia.id_prospetto_prov
              and pro_r_prospetto_provincia.id_prospetto = prospetto_id;
      nLavForzaNaz:=handle_number(nLavForzaNaz);
      dbms_output.put_line('[calcola_riepilogo_nazionale] nLavForzaNaz '||nLavForzaNaz);

      /*
        N° lavoratori (Base computo) ->
        Valore calcolato automaticamente con: SOMMA(PRO_D_DATI_PROVINCIALI.BASE_COMPUTO).
      */
      select  sum(pro_d_dati_provinciali.BASE_COMPUTO)
              into nLavBaseComputo
      from    pro_d_dati_provinciali, pro_r_prospetto_provincia
      where   pro_d_dati_provinciali.id_prospetto_prov = pro_r_prospetto_provincia.id_prospetto_prov
              and pro_r_prospetto_provincia.id_prospetto = prospetto_id;
      nLavBaseComputo:=handle_number(nLavBaseComputo);
      riepilogoNazionaleROW.NUM_LAVORATORI_BASE_COMPUTO := nLavBaseComputo;
      dbms_output.put_line('[calcola_riepilogo_nazionale] riepilogoNazionaleROW.NUM_LAVORATORI_BASE_COMPUTO '||riepilogoNazionaleROW.NUM_LAVORATORI_BASE_COMPUTO);

      /*
        Categoria Azienda L.68/99 art.3 c.1 ->
        Valore calcolato automaticamente in base al 'N° Lavoratori in forza nazionale (base computo)' nazionale, nel seguente modo:
        Se SOMMA(PRO_D_DATI_PROVINCIALI.BASE_COMPUTO) >= 15 e <= 35: la 'categoria azienda' è C;
        Se SOMMA((PRO_D_DATI_PROVINCIALI.BASE_COMPUTO) >= 36 e <= 50: la 'categoria azienda' è B;
        Se SOMMA(PRO_D_DATI_PROVINCIALI.BASE_COMPUTO) >= 51: la 'categoria azienda' è A.
      */
      if (nLavBaseComputo >= 15 and nLavBaseComputo <= 35) then
        categoriaAzienda := 'C';
      elsif (nLavBaseComputo >= 36 and nLavBaseComputo <= 50) then
        categoriaAzienda := 'B';
      elsif (nLavBaseComputo >= 51) then
        categoriaAzienda := 'A';
      end if;
      dbms_output.put_line('[calcola_riepilogo_nazionale] riepilogoNazionaleROW.NUM_LAVORATORI_BASE_COMPUTO '||riepilogoNazionaleROW.NUM_LAVORATORI_BASE_COMPUTO);

      /*
        Quota di riserva disabili  ->
        Valore calcolato automaticamente sulla base del numero di lavoratori della base di computo nazionale e cioè:

        1)  Si prende SOMMA(PRO_D_DATI_PROVINCIALI.BASE_COMPUTO):
        2)
            - Se è >= 51 dipendenti:
              il valore da mettere è: 7% di SOMMA(PRO_D_DATI_PROVINCIALI.BASE_COMPUTO;

            - Se il valore è >= 15 e <= 35 dipendenti:
              il valore da mettere nella quota riserva disabili è 1

            - Se è >= 36 e <= 50 dipendenti:
              il valore da mettere nella quota riserva disabili è 2

        (nb nel calcolo percentuale, i decimali vengono arrotondati: Se > 0,50 si aggiunge 1 intero; Se <= 0,50 non si aggiunge niente)
      */
      quotaRiservaDisab := PCK_PRODIS_UTILS.get_quota_riserva_dis_prosp(prospetto_id);
      riepilogoNazionaleROW.QUOTA_RISERVA_DISABILI := quotaRiservaDisab;
      dbms_output.put_line('[calcola_riepilogo_nazionale] riepilogoNazionaleROW.QUOTA_RISERVA_DISABILI '||riepilogoNazionaleROW.QUOTA_RISERVA_DISABILI);

      /*
        Quota di riserva Art. 18 ->
        Valore calcolato automaticamente con: 1% di SOMMA(PRO_D_DATI_PROVINCIALI.BASE_COMPUTO)
        (i numeri decimali si arrotondano in qst modo: Se > 0,50 si aggiunge 1 intero; Se <= 0,50 non si aggiunge niente).
      */
      quotaRiservaArt18 := PCK_PRODIS_UTILS.get_quota_riserva_a18_prosp(prospetto_id);
      riepilogoNazionaleROW.QUOTA_RISERVA_ART_18 := quotaRiservaArt18;
      dbms_output.put_line('[calcola_riepilogo_nazionale] riepilogoNazionaleROW.QUOTA_RISERVA_ART_18 '||riepilogoNazionaleROW.QUOTA_RISERVA_ART_18);

      /*
        N° lavoratori in sospensione ->
        SOMMA di PRO_D_PROV_SOSPENSIONE.N_LAVORATORI di tutte le province.
      */
      select  sum(PRO_D_PROV_SOSPENSIONE.N_LAVORATORI)
              into nLavInSosp
      from    PRO_D_PROV_SOSPENSIONE, pro_r_prospetto_provincia
      where   PRO_D_PROV_SOSPENSIONE.id_prospetto_prov = pro_r_prospetto_provincia.id_prospetto_prov
              and pro_r_prospetto_provincia.id_prospetto = prospetto_id
              and PRO_D_PROV_SOSPENSIONE.id_t_stato_concessione = 1;
      nLavInSosp:=handle_number(nLavInSosp);
      riepilogoNazionaleROW.NUM_LAVORATORI_SOSPENSIONE := nLavInSosp;
      dbms_output.put_line('[calcola_riepilogo_nazionale] riepilogoNazionaleROW.NUM_LAVORATORI_SOSPENSIONE '||riepilogoNazionaleROW.NUM_LAVORATORI_SOSPENSIONE);

      /*
        N° posizioni esonerate ->
        SOMMA di PRO_D_PROV_ESONERO.N_LAVORATORI_ESONERO di tutte le province.
      */
      select  sum(PRO_D_PROV_ESONERO.N_LAVORATORI_ESONERO)
              into nPosizEsonerat
      from    PRO_D_PROV_ESONERO, pro_r_prospetto_provincia
      where   PRO_D_PROV_ESONERO.id_prospetto_prov = pro_r_prospetto_provincia.id_prospetto_prov
              and pro_r_prospetto_provincia.id_prospetto = prospetto_id
              and PRO_D_PROV_ESONERO.id_t_stato_concessione = 1;
      nPosizEsonerat:=handle_number(nPosizEsonerat);
      riepilogoNazionaleROW.NUM_POSIZIONI_ESONERATE := nPosizEsonerat;
      dbms_output.put_line('[calcola_riepilogo_nazionale] riepilogoNazionaleROW.NUM_POSIZIONI_ESONERATE '||riepilogoNazionaleROW.NUM_POSIZIONI_ESONERATE);

      /*
        N° Disabili in forza (L.68/99 art.1) ->
        SOMMA di
          (
            PRO_D_DATI_PROVINCIALI.N_DISABILI_IN_FORZA
            +  PRO_D_DATI_PROVINCIALI.N_CENTRAL_TELEFO_NONVEDENTI
            + PRO_D_DATI_PROVINCIALI.N_TERARIAB_MASSOFIS_NONVED
          )
          di tutte le province.
      */
      select  sum(pro_d_dati_provinciali.N_DISABILI_IN_FORZA)
              + sum(pro_d_dati_provinciali.N_CENTRAL_TELEFO_NONVEDENTI)
              + sum(pro_d_dati_provinciali.N_TERARIAB_MASSOFIS_NONVED)
              into nDisabiliInForza
      from    pro_d_dati_provinciali, pro_r_prospetto_provincia
      where   pro_d_dati_provinciali.id_prospetto_prov = pro_r_prospetto_provincia.id_prospetto_prov
              and pro_r_prospetto_provincia.id_prospetto = prospetto_id;
      nDisabiliInForza:=handle_number(nDisabiliInForza);
      riepilogoNazionaleROW.NUM_DISABILI_IN_FORZA := nDisabiliInForza;
      dbms_output.put_line('[calcola_riepilogo_nazionale] riepilogoNazionaleROW.NUM_DISABILI_IN_FORZA '||riepilogoNazionaleROW.NUM_DISABILI_IN_FORZA);

      /*
        N° Categorie protette in forza (L.68/99 art.18) ->
        SOMMA di PRO_D_DATI_PROVINCIALI.N_CATE_PROT_FORZA di tutte le province.
      */
      select  sum(pro_d_dati_provinciali.N_CATE_PROT_FORZA)
              into nCatProtInForza
      from    pro_d_dati_provinciali, pro_r_prospetto_provincia
      where   pro_d_dati_provinciali.id_prospetto_prov = pro_r_prospetto_provincia.id_prospetto_prov
              and pro_r_prospetto_provincia.id_prospetto = prospetto_id;
      nCatProtInForza:=handle_number(nCatProtInForza);
      riepilogoNazionaleROW.NUM_CAT_PROT_IN_FORZA := nCatProtInForza;
      dbms_output.put_line('[calcola_riepilogo_nazionale] riepilogoNazionaleROW.NUM_CAT_PROT_IN_FORZA '||riepilogoNazionaleROW.NUM_CAT_PROT_IN_FORZA);

      /*
        N° Categorie protette in forza (L.68/99 art.18 ) conteggiate come disabili ->
        SOMMA di PRO_D_DATI_PROVINCIALI.N_CATE_PROT_FORZA_CNT_DIS di tutte le province.
      */
      select  sum(pro_d_dati_provinciali.N_CATE_PROT_FORZA_CNT_DIS)
              into nCatProtInForzaDis
      from    pro_d_dati_provinciali, pro_r_prospetto_provincia
      where   pro_d_dati_provinciali.id_prospetto_prov = pro_r_prospetto_provincia.id_prospetto_prov
              and pro_r_prospetto_provincia.id_prospetto = prospetto_id;
      nCatProtInForzaDis:=handle_number(nCatProtInForzaDis);
      riepilogoNazionaleROW.NUM_CAT_PROT_IN_FORZA_CNT_DIS := nCatProtInForzaDis;
      dbms_output.put_line('[calcola_riepilogo_nazionale] riepilogoNazionaleROW.NUM_CAT_PROT_IN_FORZA_CNT_DIS '||riepilogoNazionaleROW.NUM_CAT_PROT_IN_FORZA_CNT_DIS);

      /*
    N° Scoperture disabili (L.68/99 art.1)  ->
    Valore calcolato automaticamente nel seguente modo:

      'quota di riserva disabili' nazionale
      - ('N° Disabili in forza' nazionale + 'N° Categorie protette in forza (L.68/99 art.18 ) conteggiate come disabili'  nazionale)
      - 'N° posizioni esonerate'  nazionale
      - 'N° lavoratori in sospensione'

    (NB il valore di 'n° scoperture disabili' non può essere negativo, pertanto,
    se il valore risultante dai seguenti calcoli è < 0, il valore da inserire sarà sempre 0.)
      */
      nScopertDisab := handle_number( quotaRiservaDisab - (nDisabiliInForza + nCatProtInForzaDis) - nPosizEsonerat - num_lav_sospesi);
      riepilogoNazionaleROW.NUM_SCOPERT_DISABILI := nScopertDisab;
      dbms_output.put_line('[calcola_riepilogo_nazionale] riepilogoNazionaleROW.NUM_SCOPERT_DISABILI '||riepilogoNazionaleROW.NUM_SCOPERT_DISABILI);

      /*
        N° Scoperture categorie protette (L.68/99 art.18) ->
        Valore calcolato automaticamente nel seguente modo:
          'quota di riserva Art. 18'  nazionale
          - 'N° Categorie protette in forza (L.68/99 art.18)'  nazionale

        (NB il valore di 'n° scoperture categorie protette' non può essere negativo,
         pertanto, se il valore risultante dai seguenti calcoli è < 0,
         il valore da inserire sarà sempre 0.)
      */
      nScopertCatProt := handle_number(quotaRiservaArt18 - nCatProtInForza);
      riepilogoNazionaleROW.NUM_SCOPERT_CATEGORIE_PROTETTE := nScopertCatProt;
      dbms_output.put_line('[calcola_riepilogo_nazionale] riepilogoNazionaleROW.NUM_SCOPERT_CATEGORIE_PROTETTE '||riepilogoNazionaleROW.NUM_SCOPERT_CATEGORIE_PROTETTE);

      update pro_d_prospetto
      set
        pro_d_prospetto.id_t_categoria_azienda =
          (
            select  id_t_categoria_azienda
            from    pro_t_categoria_azienda
            where   cod_categoria_azienda = categoriaAzienda
          ),
        pro_d_prospetto.num_lavor_in_forza_nazionale = nLavForzaNaz
      where pro_d_prospetto.id_prospetto = riepilogoNazionaleROW.id_prospetto;

      select  count(*) into conteggioOccorrenze
      from    pro_d_riepilogo_nazionale
      where   pro_d_riepilogo_nazionale.id_prospetto = riepilogoNazionaleROW.id_prospetto;

      if conteggioOccorrenze > 0 then
        riepilogoNazionaleROW.d_aggiorn := sysdate;
        riepilogoNazionaleROW.cod_user_aggiorn := cf_operatore;
        update  pro_d_riepilogo_nazionale
        set     pro_d_riepilogo_nazionale.cod_user_aggiorn = riepilogoNazionaleROW.cod_user_aggiorn,
                pro_d_riepilogo_nazionale.d_aggiorn = riepilogoNazionaleROW.d_aggiorn,
                pro_d_riepilogo_nazionale.id_prospetto = riepilogoNazionaleROW.id_prospetto,
                pro_d_riepilogo_nazionale.num_cat_prot_in_forza = riepilogoNazionaleROW.num_cat_prot_in_forza,
                pro_d_riepilogo_nazionale.num_cat_prot_in_forza_cnt_dis = riepilogoNazionaleROW.num_cat_prot_in_forza_cnt_dis,
                pro_d_riepilogo_nazionale.num_disabili_in_forza = riepilogoNazionaleROW.num_disabili_in_forza,
                pro_d_riepilogo_nazionale.num_lavoratori_base_computo = riepilogoNazionaleROW.num_lavoratori_base_computo,
                pro_d_riepilogo_nazionale.num_lavoratori_sospensione = riepilogoNazionaleROW.num_lavoratori_sospensione,
                pro_d_riepilogo_nazionale.num_posizioni_esonerate = riepilogoNazionaleROW.num_posizioni_esonerate,
                pro_d_riepilogo_nazionale.num_scopert_categorie_protette = riepilogoNazionaleROW.num_scopert_categorie_protette,
                pro_d_riepilogo_nazionale.num_scopert_disabili = riepilogoNazionaleROW.num_scopert_disabili,
                pro_d_riepilogo_nazionale.quota_riserva_art_18 = riepilogoNazionaleROW.quota_riserva_art_18,
                pro_d_riepilogo_nazionale.quota_riserva_disabili = riepilogoNazionaleROW.quota_riserva_disabili
        where   pro_d_riepilogo_nazionale.id_prospetto = riepilogoNazionaleROW.id_prospetto;
      else
        riepilogoNazionaleROW.d_inserim := sysdate;
        riepilogoNazionaleROW.cod_user_inserim := cf_operatore;
        insert into pro_d_riepilogo_nazionale values riepilogoNazionaleROW;
      end if;

      --return res_OK;
    EXCEPTION
        WHEN OTHERS THEN
            dbms_output.put_line('ERRORE : '||SUBSTR(SQLERRM,1,110)||'  SQLCODE='||TO_CHAR(SQLCODE));
        raise;
        --return res_KO;
    END calcola_riepilogo_nazionale;

    /* calcola base computo */
    /*
    lato java se si usa la stored procedure viene generato in errore oracle ORA-04091
    */
    PROCEDURE get_base_computo(prospetto_prov_id IN NUMBER, base_computo OUT NUMBER) IS

    BEGIN
      select
        (
          nvl(
            PRO_D_DATI_PROVINCIALI.N_TOTALE_LAVORAT_DIPENDENTI,0)
          - nvl(PRO_D_DATI_PROVINCIALI.N_DISABILI_IN_FORZA,0)
          - nvl(PRO_D_DATI_PROVINCIALI.N_CENTRAL_TELEFO_NONVEDENTI,0)
          - nvl(PRO_D_DATI_PROVINCIALI.N_TERARIAB_MASSOFIS_NONVED,0)
          - nvl(
            (select sum(PRO_D_CATEGORIE_ESCLUSE.N_LAV_APPARTART_CATEGORIA)
            from PRO_D_CATEGORIE_ESCLUSE
            where PRO_D_CATEGORIE_ESCLUSE.ID_PROSPETTO_PROV = PRO_D_DATI_PROVINCIALI.ID_PROSPETTO_PROV),0)
          - nvl(PRO_D_DATI_PROVINCIALI.N_CATE_PROT_FORZA,0)
          - nvl(PRO_D_DATI_PROVINCIALI.N_CATE_PROT_FORZA_CNT_DIS,0)
          - nvl(
            (select Sum(PRO_D_PART_TIME.N_PART_TIME)
            from PRO_D_PART_TIME
            where PRO_D_PART_TIME.ID_PROSPETTO_PROV = PRO_D_DATI_PROVINCIALI.ID_PROSPETTO_PROV),0)
          + nvl(PRO_D_DATI_PROVINCIALI.N_PARTIME_RIPROPORZIONATI,0)
          - nvl(
            (select Sum(PRO_D_PROV_INTERMITTENTI.N_INTERMITTENTI)
            from PRO_D_PROV_INTERMITTENTI
            where PRO_D_PROV_INTERMITTENTI.ID_PROSPETTO_PROV = PRO_D_DATI_PROVINCIALI.ID_PROSPETTO_PROV),0)
          + nvl(PRO_D_DATI_PROVINCIALI.N_INTERMITTENTI_RIPROPORZIONA,0)
        ) into base_computo

      from
        PRO_D_DATI_PROVINCIALI

      where
        PRO_D_DATI_PROVINCIALI.ID_PROSPETTO_PROV = prospetto_prov_id ;

        IF base_computo < 0 THEN
           base_computo := 0;
        END IF;
    EXCEPTION
        WHEN OTHERS THEN
            dbms_output.put_line('ERRORE : '||SUBSTR(SQLERRM,1,110)||'  SQLCODE='||TO_CHAR(SQLCODE));
        raise;
    END get_base_computo;


    /* calcola part time riproporzionati */
    FUNCTION get_part_time_riproporz (prospetto_prov_id IN NUMBER) return NUMBER IS
      part_time_riproporz NUMBER := 0;
    BEGIN
      --select sum(round((pt.orario_sett_part_time_min/pt.orario_sett_contrattuale_min * pt.n_part_time)-0.001))
      select round(sum((pt.orario_sett_part_time_min/pt.orario_sett_contrattuale_min * pt.n_part_time))-0.001)
              into part_time_riproporz
      from    pro_d_part_time pt
      where   pt.id_prospetto_prov = prospetto_prov_id;
      return part_time_riproporz;
    EXCEPTION
        WHEN OTHERS THEN
            dbms_output.put_line('ERRORE : '||SUBSTR(SQLERRM,1,110)||'  SQLCODE='||TO_CHAR(SQLCODE));
        raise;
    END get_part_time_riproporz;

    /* calcola intermittenti riproporzionati */
    FUNCTION get_intermit_riproporz (prospetto_prov_id IN NUMBER) return NUMBER IS
      intermit_riproporz NUMBER := 0;
    BEGIN
      --select  sum(round((inter.orario_settimanale_svolto/inter.orario_settimanale_contrattual * inter.n_intermittenti)-0.001))
      select  round(sum((inter.orario_settimanale_svolto/inter.orario_settimanale_contrattual * inter.n_intermittenti))-0.001)
              into intermit_riproporz
      from    pro_d_prov_intermittenti inter
      where   inter.id_prospetto_prov = prospetto_prov_id;
      return intermit_riproporz;
    EXCEPTION
        WHEN OTHERS THEN
            dbms_output.put_line('ERRORE : '||SUBSTR(SQLERRM,1,110)||'  SQLCODE='||TO_CHAR(SQLCODE));
        raise;
    END get_intermit_riproporz;

    PROCEDURE esegui_ricalcolo (prospetto_id IN NUMBER, cf_operatore IN VARCHAR2) IS
    BEGIN
      PCK_PRODIS_UTILS.calcola_riepilogo_nazionale(prospetto_id, cf_operatore);
      PCK_PRODIS_UTILS.calcola_riepilogo_provinciale(prospetto_id, cf_operatore);
    EXCEPTION
      WHEN OTHERS THEN
        dbms_output.put_line('ERRORE : '||SUBSTR(SQLERRM,1,110)||'  SQLCODE='||TO_CHAR(SQLCODE));
        raise;
    END esegui_ricalcolo;


    PROCEDURE copia_prospetto(prospetto_id IN NUMBER, nuovo_prospetto_id OUT NUMBER) IS
      prospetto       PRO_D_PROSPETTO%ROWTYPE;
      datiAzienda     PRO_D_DATI_AZIENDA%ROWTYPE;
      sedeLeg         PRO_D_SEDE_LEGALE%ROWTYPE;
      proPdfProspetto PRO_D_PDF_PROSPETTO%ROWTYPE;
      gradualita      PRO_D_PROSPETTO_GRADUALITA%ROWTYPE;
      riepilogoNaz    PRO_D_RIEPILOGO_NAZIONALE%ROWTYPE;
      prospettoProv   PRO_D_DATI_PROVINCIALI%ROWTYPE;
      riepilogoProv   PRO_D_RIEPILOGO_PROVINCIALE%ROWTYPE;
      proProvSede     PRO_D_PROSPETTO_PROV_SEDE%ROWTYPE;
      proProvConv     PRO_D_PROV_CONVENZIONE%ROWTYPE;
      proProvEsonero  PRO_D_PROV_ESONERO%ROWTYPE;
      proProvGradual  PRO_D_PROV_GRADUALITA%ROWTYPE;
      proProvSosp     PRO_D_PROV_SOSPENSIONE%ROWTYPE;
      newIdProspettoProv NUMBER(10);
      oldIdProspettoProv NUMBER(10);

    BEGIN
      SELECT * INTO prospetto FROM PRO_D_PROSPETTO WHERE id_prospetto = prospetto_id;

      SELECT seq_d_prospetto.NEXTVAL INTO nuovo_prospetto_id FROM DUAL;

      prospetto.id_prospetto := nuovo_prospetto_id;

      INSERT INTO PRO_D_PROSPETTO VALUES prospetto;

      BEGIN
        SELECT *
          INTO datiAzienda
          FROM PRO_D_DATI_AZIENDA
         WHERE id_prospetto = prospetto_id;

        datiAzienda.Id_Prospetto := nuovo_prospetto_id;

        INSERT INTO PRO_D_DATI_AZIENDA VALUES datiAzienda;
      EXCEPTION
        WHEN NO_DATA_FOUND THEN
          NULL;
      END;

      BEGIN
        SELECT *
          INTO sedeLeg
          FROM PRO_D_SEDE_LEGALE
         WHERE id_prospetto = prospetto_id;

        sedeLeg.Id_Prospetto := nuovo_prospetto_id;

        INSERT INTO PRO_D_SEDE_LEGALE VALUES sedeLeg;

      EXCEPTION
        WHEN NO_DATA_FOUND THEN
          NULL;
      END;


      FOR assPubb IN (SELECT * FROM PRO_D_ASS_PUBBLICHE WHERE id_prospetto = prospetto_id)
      LOOP

        assPubb.Id_Prospetto := nuovo_prospetto_id;

        INSERT INTO PRO_D_ASS_PUBBLICHE VALUES assPubb;

      END LOOP;

      BEGIN
          SELECT * INTO proPdfProspetto FROM PRO_D_PDF_PROSPETTO WHERE id_prospetto = prospetto_id;
          proPdfProspetto.Id_Prospetto := nuovo_prospetto_id;

          INSERT INTO PRO_D_PDF_PROSPETTO VALUES  proPdfProspetto;

       EXCEPTION
        WHEN NO_DATA_FOUND THEN
          NULL;
      END;

      BEGIN
          SELECT * INTO gradualita FROM PRO_D_PROSPETTO_GRADUALITA g WHERE g.id_prospetto = prospetto_id;
          gradualita.Id_Prospetto := nuovo_prospetto_id;

          INSERT INTO PRO_D_PROSPETTO_GRADUALITA VALUES  gradualita;

       EXCEPTION
        WHEN NO_DATA_FOUND THEN
          NULL;
      END;

      BEGIN
          SELECT * INTO riepilogoNaz FROM PRO_D_RIEPILOGO_NAZIONALE WHERE id_prospetto = prospetto_id;
          riepilogoNaz.Id_Prospetto := nuovo_prospetto_id;

          INSERT INTO PRO_D_RIEPILOGO_NAZIONALE VALUES  riepilogoNaz;

       EXCEPTION
        WHEN NO_DATA_FOUND THEN
          NULL;
      END;

      FOR proRprspProv in (SELECT * FROM PRO_R_PROSPETTO_PROVINCIA WHERE id_prospetto = prospetto_id)
      LOOP

          oldIdProspettoProv := proRprspProv.Id_Prospetto_Prov;

          SELECT SEQ_R_PROSPETTO_PROVINCIA.NEXTVAL INTO newIdProspettoProv FROM DUAL;
          proRprspProv.ID_PROSPETTO := prospetto_id;
          proRprspProv.Id_Prospetto_Prov := newIdProspettoProv;
          INSERT INTO PRO_R_PROSPETTO_PROVINCIA VALUES proRprspProv;

          BEGIN
            SELECT * INTO prospettoProv FROM PRO_D_DATI_PROVINCIALI WHERE ID_PROSPETTO_PROV = oldIdProspettoProv;

            prospettoProv.Id_Prospetto_Prov := newIdProspettoProv;
            INSERT INTO PRO_D_DATI_PROVINCIALI VALUES prospettoProv;

          EXCEPTION
            WHEN NO_DATA_FOUND THEN
              NULL;
          END;

          BEGIN
            SELECT * INTO riepilogoProv FROM PRO_D_RIEPILOGO_PROVINCIALE WHERE ID_PROSPETTO_PROV = oldIdProspettoProv;
            riepilogoProv.Id_Prospetto_Prov := newIdProspettoProv;
            INSERT INTO PRO_D_RIEPILOGO_PROVINCIALE VALUES riepilogoProv;

          EXCEPTION
            WHEN NO_DATA_FOUND THEN
              NULL;
          END;

          BEGIN
            SELECT * INTO proProvSede FROM PRO_D_PROSPETTO_PROV_SEDE WHERE ID_PROSPETTO_PROV = oldIdProspettoProv;
            proProvSede.Id_Prospetto_Prov := newIdProspettoProv;
            INSERT INTO PRO_D_PROSPETTO_PROV_SEDE VALUES proProvSede;
          EXCEPTION
            WHEN NO_DATA_FOUND THEN
              NULL;
          END;

          BEGIN
            SELECT * INTO proProvConv FROM PRO_D_PROV_CONVENZIONE WHERE ID_PROSPETTO_PROV = oldIdProspettoProv;
            proProvConv.Id_Prospetto_Prov := newIdProspettoProv;
            INSERT INTO PRO_D_PROV_CONVENZIONE VALUES proProvConv;

          EXCEPTION
            WHEN NO_DATA_FOUND THEN
              NULL;
          END;

          BEGIN
            SELECT * INTO proProvEsonero FROM PRO_D_PROV_ESONERO WHERE ID_PROSPETTO_PROV = oldIdProspettoProv;
            proProvEsonero.Id_Prospetto_Prov := newIdProspettoProv;
            INSERT INTO PRO_D_PROV_ESONERO VALUES proProvEsonero;

          EXCEPTION
            WHEN NO_DATA_FOUND THEN
              NULL;
          END;

          BEGIN
            SELECT * INTO proProvGradual FROM PRO_D_PROV_GRADUALITA WHERE ID_PROSPETTO_PROV = oldIdProspettoProv;
            proProvGradual.Id_Prospetto_Prov := newIdProspettoProv;
            INSERT INTO PRO_D_PROV_GRADUALITA VALUES proProvGradual;
          EXCEPTION
            WHEN NO_DATA_FOUND THEN
              NULL;
          END;

          BEGIN
            SELECT * INTO proProvSosp FROM PRO_D_PROV_SOSPENSIONE WHERE ID_PROSPETTO_PROV = oldIdProspettoProv;
            proProvSosp.Id_Prospetto_Prov := newIdProspettoProv;
            INSERT INTO PRO_D_PROV_SOSPENSIONE VALUES proProvSosp;
          EXCEPTION
            WHEN NO_DATA_FOUND THEN
              NULL;
          END;

          FOR catEscluse IN (SELECT * FROM PRO_D_CATEGORIE_ESCLUSE WHERE ID_PROSPETTO_PROV = oldIdProspettoProv)
          LOOP
              catEscluse.Id_Prospetto_Prov := newIdProspettoProv;
              SELECT SEQ_D_CATEGORIE_ESCLUSE.NEXTVAL INTO catEscluse.Id_Categorie_Escluse FROM DUAL;
              INSERT INTO PRO_D_CATEGORIE_ESCLUSE VALUES catEscluse;
          END LOOP;

          FOR lavInForza IN (SELECT * FROM PRO_D_LAVORATORI_IN_FORZA WHERE ID_PROSPETTO_PROV = oldIdProspettoProv)
          LOOP
              lavInForza.Id_Prospetto_Prov := newIdProspettoProv;
              SELECT SEQ_D_LAVORATORI_IN_FORZA.NEXTVAL INTO lavInForza.Id_Lavoratori_In_Forza FROM DUAL;
              INSERT INTO PRO_D_LAVORATORI_IN_FORZA VALUES lavInForza;
          END LOOP;

          FOR partTime IN (SELECT * FROM PRO_D_PART_TIME WHERE ID_PROSPETTO_PROV = oldIdProspettoProv)
          LOOP
              partTime.Id_Prospetto_Prov := newIdProspettoProv;
              SELECT SEQ_D_PART_TIME.NEXTVAL INTO partTime.Id_Part_Time FROM DUAL;
              INSERT INTO PRO_D_PART_TIME VALUES partTime;
          END LOOP;

          FOR postiLav IN (SELECT * FROM PRO_D_POSTI_LAVORO_DISP WHERE ID_PROSPETTO_PROV = oldIdProspettoProv)
          LOOP
              postiLav.Id_Prospetto_Prov := newIdProspettoProv;
              SELECT SEQ_D_POSTI_LAVORO_DISP.NEXTVAL INTO postiLav.Id_Posto_Lavoro_Disp FROM DUAL;
              INSERT INTO PRO_D_POSTI_LAVORO_DISP VALUES postiLav;
          END LOOP;

          FOR compensazione IN (SELECT * FROM PRO_D_PROV_COMPENSAZIONI WHERE ID_PROSPETTO_PROV = oldIdProspettoProv)
          LOOP
              compensazione.id_prospetto_prov := newIdProspettoProv;
              SELECT SEQ_D_PROV_COMPENSAZIONI.NEXTVAL INTO compensazione.id_compensazione FROM DUAL;
              INSERT INTO PRO_D_PROV_COMPENSAZIONI VALUES compensazione;
          END LOOP;

          FOR intermittente IN (SELECT * FROM PRO_D_PROV_INTERMITTENTI WHERE ID_PROSPETTO_PROV = oldIdProspettoProv)
          LOOP
              intermittente.id_prospetto_prov := newIdProspettoProv;
              SELECT SEQ_D_PROV_INTERMITTENTI.NEXTVAL INTO intermittente.id_intermittenti FROM DUAL;
              INSERT INTO PRO_D_PROV_INTERMITTENTI VALUES intermittente;
          END LOOP;

      END LOOP;


    EXCEPTION
      WHEN OTHERS THEN
        RAISE;

    END copia_prospetto;

    procedure annulla_prospetto(prospetto_id IN NUMBER, nuovo_prospetto_id OUT NUMBER) IS
    BEGIN
        copia_prospetto(prospetto_id, nuovo_prospetto_id);

        UPDATE PRO_D_PROSPETTO p
           SET p.id_t_stato_prospetto = 2,
               p.id_t_comunicazione = 3,
               p.anno_protocollo = null,
               p.codice_comunicazione_preced = (select pp.id_t_comunicazione from PRO_D_PROSPETTO pp where pp.id_prospetto = prospetto_id),
               p.numero_protocollo = 0,
               p.data_protocollo = null,
               p.data_timbro_postale = null,
               p.data_invio = null
         WHERE p.id_prospetto = nuovo_prospetto_id;

         UPDATE PRO_D_PROSPETTO p
            SET p.id_t_stato_prospetto = 12
          WHERE p.id_prospetto = prospetto_id;


    END annulla_prospetto;

    procedure rettifica_prospetto(prospetto_id IN NUMBER, nuovo_prospetto_id OUT NUMBER) IS
    BEGIN
        copia_prospetto(prospetto_id, nuovo_prospetto_id);

        UPDATE PRO_D_PROSPETTO p
           SET p.id_t_stato_prospetto = 1,
               p.id_t_comunicazione = 2,
               p.anno_protocollo = null,
               p.codice_comunicazione_preced = (select pp.id_t_comunicazione from PRO_D_PROSPETTO pp where pp.id_prospetto = prospetto_id),
               p.numero_protocollo = 0,
               p.data_protocollo = null,
               p.data_timbro_postale = null,
               p.data_invio = null
         WHERE p.id_prospetto = nuovo_prospetto_id;

         UPDATE PRO_D_PROSPETTO p
            SET p.id_t_stato_prospetto = 9
          WHERE p.id_prospetto = prospetto_id;


    END rettifica_prospetto;

    PROCEDURE cancella_prospetto(prospetto_id IN NUMBER) IS
    BEGIN
      FOR proProv IN (SELECT ID_PROSPETTO_PROV FROM PRO_R_PROSPETTO_PROVINCIA WHERE ID_PROSPETTO = prospetto_id)
      LOOP
          cancella_dati_provinciali(proProv.Id_Prospetto_Prov);

      END LOOP;

      DELETE PRO_D_ASS_PUBBLICHE WHERE ID_PROSPETTO = prospetto_id;
      DELETE PRO_D_SEDE_LEGALE WHERE ID_PROSPETTO = prospetto_id;
      DELETE PRO_D_DATI_AZIENDA WHERE ID_PROSPETTO = prospetto_id;
      DELETE PRO_D_PDF_PROSPETTO WHERE ID_PROSPETTO = prospetto_id;
      DELETE PRO_D_PROSPETTO_GRADUALITA WHERE ID_PROSPETTO = prospetto_id;
      DELETE PRO_D_RIEPILOGO_NAZIONALE WHERE ID_PROSPETTO = prospetto_id;

      DELETE PRO_D_PROSPETTO WHERE ID_PROSPETTO = prospetto_id;

    END cancella_prospetto;



END PCK_PRODIS_UTILS;
/

CREATE OR REPLACE PACKAGE BODY PCK_PRODIS_2012 AS
  
  PROCEDURE writeLn(str VARCHAR2) IS
  BEGIN
    IF log_enable = TRUE THEN
      dbms_output.put_line(to_char(sysdate, 'dd/mm/yy hh24:mi:ss') || '-' || str);
    END IF;
  END writeLn;

  PROCEDURE setLogEnable(attiva BOOLEAN) IS
  BEGIN
    log_enable := attiva;
    IF log_enable = TRUE THEN
      dbms_output.enable(999999);
      dbms_output.put_line('ABILITATA SESSIONE DI LOG!');
      dbms_output.put_line('RICORDARSI DI DISABILITARLA AL TERMINE CON PCK_PRODIS_2012_UTILS.setLogEnable(false)');
    END IF;
  END setLogEnable;

  FUNCTION isLogEnable RETURN BOOLEAN IS
  BEGIN
    RETURN log_enable;
  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END isLogEnable;

  /*
  71441 rispristina l'ordine per id provincia
  inizio PRODIS-602
  */
  PROCEDURE ripristina_ordine(tabellaIN IN OUT prospetto_provincia_TABLE) IS

    scambio    BOOLEAN;
    n          NUMBER(10);
    tmp        prospetto_provincia_TYPE;

    indice NUMBER := 0;
  BEGIN
    scambio := TRUE;
    n := tabellaIN.COUNT - 1;

    WHILE scambio LOOP
        scambio := FALSE;
        for i in 1..n-1 LOOP
            if tabellaIN(i).idProvincia > tabellaIN(i + 1).idProvincia then
               tmp := tabellaIN(i);
               tabellaIN(i) := tabellaIN(i + 1);
               tabellaIN(i + 1) := tmp;
               scambio := true;
            end if;
        END LOOP;
        n := n - 1;
    END LOOP;

  END ripristina_ordine;
  -- 71441 fine PRODIS-602

  FUNCTION order_by(tabellaIN IN prospetto_provincia_TABLE,
                    campo     IN VARCHAR2,
                    ordine    IN VARCHAR2) RETURN prospetto_provincia_TABLE IS
    tabellaTMP prospetto_provincia_TABLE;
    tabellaOUT prospetto_provincia_TABLE;
    tmp        prospetto_provincia_TYPE;

    indice NUMBER := 0;
  BEGIN
    tabellaTMP := tabellaIN;

    FOR pos IN tabellaTMP.FIRST .. tabellaTMP.LAST LOOP

      writeLn('[order_by] giro ' || pos);

      IF 'ordTmp_' = campo THEN
        IF ('DESC' = ordine) THEN
          FOR i IN tabellaTMP.FIRST .. tabellaTMP.LAST LOOP
            IF (tmp.ordTmp_ IS NULL) THEN
              tmp    := tabellaTMP(i);
              indice := i;
            ELSIF (tmp.ordTmp_ IS NOT NULL AND
                  tmp.ordTmp_ < tabellaTMP(i).ordTmp_) THEN
              tmp    := tabellaTMP(i);
              indice := i;
            ELSE
              tmp := tmp;
            END IF;
          END LOOP;
        ELSE
          FOR i IN tabellaTMP.FIRST .. tabellaTMP.LAST LOOP
            IF (tmp.ordTmp_ IS NULL) THEN
              tmp    := tabellaTMP(i);
              indice := i;
            ELSIF (tmp.ordTmp_ IS NOT NULL AND
                  tmp.ordTmp_ > tabellaTMP(i).ordTmp_) THEN
              tmp    := tabellaTMP(i);
              indice := i;
            ELSE
              tmp := tmp;
            END IF;
          END LOOP;
        END IF;
      END IF;

      IF ('diffPartTimeProv_' = campo) THEN
        IF ('DESC' = ordine) THEN
          FOR i IN tabellaTMP.FIRST .. tabellaTMP.LAST LOOP
            IF (tmp.diffPartTimeProv_ IS NULL) THEN
              tmp    := tabellaTMP(i);
              indice := i;
            ELSIF (tmp.diffPartTimeProv_ IS NOT NULL AND
                  tmp.diffPartTimeProv_ < tabellaTMP(i).diffPartTimeProv_) THEN
              tmp    := tabellaTMP(i);
              indice := i;
            ELSE
              tmp := tmp;
            END IF;
          END LOOP;
        ELSE
          FOR i IN tabellaTMP.FIRST .. tabellaTMP.LAST LOOP
            IF (tmp.diffPartTimeProv_ IS NULL) THEN
              tmp    := tabellaTMP(i);
              indice := i;
            ELSIF (tmp.diffPartTimeProv_ IS NOT NULL AND
                  tmp.diffPartTimeProv_ > tabellaTMP(i).diffPartTimeProv_) THEN
              tmp    := tabellaTMP(i);
              indice := i;
            ELSE
              tmp := tmp;
            END IF;
          END LOOP;
        END IF;
      END IF;

      IF ('diffIntermitProv_' = campo) THEN
        IF ('DESC' = ordine) THEN
          FOR i IN tabellaTMP.FIRST .. tabellaTMP.LAST LOOP
            IF (tmp.diffIntermitProv_ IS NULL) THEN
              tmp    := tabellaTMP(i);
              indice := i;
            ELSIF (tmp.diffIntermitProv_ IS NOT NULL AND
                  tmp.diffIntermitProv_ < tabellaTMP(i).diffIntermitProv_) THEN
              tmp    := tabellaTMP(i);
              indice := i;
            ELSE
              tmp := tmp;
            END IF;
          END LOOP;
        ELSE
          FOR i IN tabellaTMP.FIRST .. tabellaTMP.LAST LOOP
            IF (tmp.diffIntermitProv_ IS NULL) THEN
              tmp    := tabellaTMP(i);
              indice := i;
            ELSIF (tmp.diffIntermitProv_ IS NOT NULL AND
                  tmp.diffIntermitProv_ > tabellaTMP(i).diffIntermitProv_) THEN
              tmp    := tabellaTMP(i);
              indice := i;
            ELSE
              tmp := tmp;
            END IF;
          END LOOP;
        END IF;
      END IF;

      IF ('minimoCatProtInForzaPV_' = campo) THEN
        IF ('DESC' = ordine) THEN
          FOR i IN tabellaTMP.FIRST .. tabellaTMP.LAST LOOP
            IF (tmp.minimoCatProtInForzaPV_ IS NULL) THEN
              tmp    := tabellaTMP(i);
              indice := i;
            ELSIF (tmp.minimoCatProtInForzaPV_ IS NOT NULL AND
                  tmp.minimoCatProtInForzaPV_ < tabellaTMP(i)
                  .minimoCatProtInForzaPV_) THEN
              tmp    := tabellaTMP(i);
              indice := i;

              --PRODIS-571
            ELSIF (tmp.minimoCatProtInForzaPV_ IS NOT NULL AND
                  tmp.minimoCatProtInForzaPV_ = tabellaTMP(i)
                  .minimoCatProtInForzaPV_) THEN
              IF (tmp.nLavDipProv_ IS NOT NULL AND
                 tmp.nLavDipProv_ < tabellaTMP(i).nLavDipProv_) THEN
                tmp    := tabellaTMP(i);
                indice := i;
              END IF;
              --PRODIS-571

            ELSE
              tmp := tmp;
            END IF;
          END LOOP;
        ELSE
          FOR i IN tabellaTMP.FIRST .. tabellaTMP.LAST LOOP
            IF (tmp.minimoCatProtInForzaPV_ IS NULL) THEN
              tmp    := tabellaTMP(i);
              indice := i;
            ELSIF (tmp.minimoCatProtInForzaPV_ IS NOT NULL AND
                  tmp.minimoCatProtInForzaPV_ > tabellaTMP(i)
                  .minimoCatProtInForzaPV_) THEN
              tmp    := tabellaTMP(i);
              indice := i;

              --PRODIS-571
            ELSIF (tmp.minimoCatProtInForzaPV_ IS NOT NULL AND
                  tmp.minimoCatProtInForzaPV_ = tabellaTMP(i)
                  .minimoCatProtInForzaPV_) THEN
              IF (tmp.nLavDipProv_ IS NOT NULL AND
                 tmp.nLavDipProv_ < tabellaTMP(i).nLavDipProv_) THEN
                tmp    := tabellaTMP(i);
                indice := i;
              END IF;
              --PRODIS-571

            ELSE
              tmp := tmp;
            END IF;
          END LOOP;
        END IF;
      END IF;

      IF ('restoQuotaRiservaDisabili_' = campo) THEN
        IF ('DESC' = ordine) THEN
          FOR i IN tabellaTMP.FIRST .. tabellaTMP.LAST LOOP
            /*
            writeLn('[order_by] i '||i);
            writeLn('[order_by] tabellaTMP(i).restoQuotaRiservaDisabili_ '||tabellaTMP(i).restoQuotaRiservaDisabili_);
            if (tmp.restoQuotaRiservaDisabili_ is not null) then
            writeLn('[order_by] tmp.restoQuotaRiservaDisabili_ '||tmp.restoQuotaRiservaDisabili_);
            end if;
            */
            IF (tmp.restoQuotaRiservaDisabili_ IS NULL) THEN
              tmp    := tabellaTMP(i);
              indice := i;
            ELSIF (tmp.restoQuotaRiservaDisabili_ IS NOT NULL AND
                  tmp.restoQuotaRiservaDisabili_ < tabellaTMP(i)
                  .restoQuotaRiservaDisabili_) THEN
              tmp    := tabellaTMP(i);
              indice := i;
            ELSE
              tmp := tmp;
            END IF;
          END LOOP;
        ELSE
          FOR i IN tabellaTMP.FIRST .. tabellaTMP.LAST LOOP
            IF (tmp.restoQuotaRiservaDisabili_ IS NULL) THEN
              tmp    := tabellaTMP(i);
              indice := i;
            ELSIF (tmp.restoQuotaRiservaDisabili_ IS NOT NULL AND
                  tmp.restoQuotaRiservaDisabili_ > tabellaTMP(i)
                  .restoQuotaRiservaDisabili_) THEN
              tmp    := tabellaTMP(i);
              indice := i;
            ELSE
              tmp := tmp;
            END IF;
          END LOOP;
        END IF;
      END IF;

      IF ('restoQuotaRiservaArt18_' = campo) THEN
        IF ('DESC' = ordine) THEN
          FOR i IN tabellaTMP.FIRST .. tabellaTMP.LAST LOOP
            IF (tmp.restoQuotaRiservaArt18_ IS NULL) THEN
              tmp    := tabellaTMP(i);
              indice := i;
            ELSIF (tmp.restoQuotaRiservaArt18_ IS NOT NULL AND
                  tmp.restoQuotaRiservaArt18_ < tabellaTMP(i)
                  .restoQuotaRiservaArt18_) THEN
              tmp    := tabellaTMP(i);
              indice := i;
            ELSE
              tmp := tmp;
            END IF;
          END LOOP;
        ELSE
          FOR i IN tabellaTMP.FIRST .. tabellaTMP.LAST LOOP
            IF (tmp.restoQuotaRiservaArt18_ IS NULL) THEN
              tmp    := tabellaTMP(i);
              indice := i;
            ELSIF (tmp.restoQuotaRiservaArt18_ IS NOT NULL AND
                  tmp.restoQuotaRiservaArt18_ > tabellaTMP(i)
                  .restoQuotaRiservaArt18_) THEN
              tmp    := tabellaTMP(i);
              indice := i;
            ELSE
              tmp := tmp;
            END IF;
          END LOOP;
        END IF;
      END IF;

      IF ('scopertureDisabiliPV_' = campo) THEN
        IF ('DESC' = ordine) THEN
          FOR i IN tabellaTMP.FIRST .. tabellaTMP.LAST LOOP
            IF (tmp.scopertureDisabiliPV_ IS NULL) THEN
              tmp    := tabellaTMP(i);
              indice := i;
            ELSIF (tmp.scopertureDisabiliPV_ IS NOT NULL AND
                  tmp.scopertureDisabiliPV_ < tabellaTMP(i)
                  .scopertureDisabiliPV_) THEN
              tmp    := tabellaTMP(i);
              indice := i;
            ELSE
              tmp := tmp;
            END IF;
          END LOOP;
        ELSE
          FOR i IN tabellaTMP.FIRST .. tabellaTMP.LAST LOOP
            IF (tmp.scopertureDisabiliPV_ IS NULL) THEN
              tmp    := tabellaTMP(i);
              indice := i;
            ELSIF (tmp.scopertureDisabiliPV_ IS NOT NULL AND
                  tmp.scopertureDisabiliPV_ > tabellaTMP(i)
                  .scopertureDisabiliPV_) THEN
              tmp    := tabellaTMP(i);
              indice := i;
            ELSE
              tmp := tmp;
            END IF;
          END LOOP;
        END IF;
      END IF;

      tabellaTMP(indice) := NULL;
      tabellaOUT(pos) := tmp;
      tmp := NULL;
    END LOOP;

    RETURN tabellaOUT;

  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN tabellaOUT;
  END order_by;

  /* spalma i residui sul campo temporaneo per i partime
   inizio */
  PROCEDURE spalmaBC(nLavPtRipNaz IN NUMBER,
                   nLavPtRipPv  IN NUMBER,
                   campo        VARCHAR2,
                   prospProvTab IN OUT prospetto_provincia_TABLE) IS
    partTimeRiproporzTmp NUMBER;
    prospProvTabOrder    prospetto_provincia_TABLE;
    spalmabile           NUMBER;
  BEGIN

    spalmabile := nLavPtRipPv - nLavPtRipNaz;
    writeLn('[esegui_calcoli_provinciali] spalmabile ' || spalmabile);

    IF spalmabile > 0 THEN

      prospProvTabOrder := order_by(prospProvTab, campo, 'ASC');
      FOR i IN prospProvTabOrder.FIRST .. prospProvTabOrder.LAST LOOP
        IF (prospProvTabOrder(i).nLavPartTimeProv_ > 0 AND
            prospProvTabOrder(i).nLavDipProv_ >= (prospProvTabOrder(i).diffTmp_ +
                                              spalmabile +
                                              prospProvTabOrder(i).nDisabiliInForzaBC +
                                              prospProvTabOrder(i).catEsclDisabProv_)) THEN

          prospProvTabOrder(i).diffTmp_ := prospProvTabOrder(i).diffTmp_ + spalmabile;
          EXIT;
        END IF;
      END LOOP;

    ELSIF spalmabile < 0 THEN
      spalmabile := abs(spalmabile);

      prospProvTabOrder := order_by(prospProvTab, campo, 'DESC');
      FOR i IN prospProvTabOrder.FIRST .. prospProvTabOrder.LAST LOOP
        IF (spalmabile > 0) THEN
          IF (prospProvTabOrder(i).diffTmp_ >= spalmabile) THEN
            partTimeRiproporzTmp := prospProvTabOrder(i).diffTmp_;
            prospProvTabOrder(i).diffTmp_ := prospProvTabOrder(i).diffTmp_ - spalmabile;
            spalmabile := spalmabile - partTimeRiproporzTmp;

          ELSIF (prospProvTabOrder(i).diffTmp_ < spalmabile) THEN
            spalmabile := spalmabile - prospProvTabOrder(i).diffTmp_;
            prospProvTabOrder(i).diffTmp_ := 0;
          ELSE
            EXIT;
          END IF;
        END IF;

      END LOOP;

    END IF;

    IF (prospProvTabOrder.COUNT > 0) THEN
      prospProvTab := prospProvTabOrder;
    END IF;

    ripristina_ordine(prospProvTab); -- 71441 PRODIS-602

  END spalmaBC;

  PROCEDURE spalma(nNaz         IN NUMBER,
                   nPv          IN NUMBER,
                   campo        IN VARCHAR2,
                   prospProvTab IN OUT prospetto_provincia_TABLE,
                   ordine       IN VARCHAR2  DEFAULT 'A'
                   ) IS
    partTimeRiproporzTmp NUMBER;
    prospProvTabOrder    prospetto_provincia_TABLE;
    spalmabile           NUMBER;

    ordine1              VARCHAR(4);
    ordine2              VARCHAR(4);

  BEGIN

    IF ordine = 'A' THEN -- spalma aggiungendo a chi ne ha meno e togliendo a chi ne ha di piu
       ordine1 := 'DESC';
       ordine2 := 'ASC';
    ELSE                 -- spalma aggiungendo a chi ne ha di piu e togliendo a chi ne ha di memo
       ordine1 := 'ASC';
       ordine2 := 'DESC';
    END IF;

    spalmabile := nPv - nNaz;
    writeLn('[esegui_calcoli_provinciali] spalmabile ' || spalmabile);

    IF spalmabile > 0 THEN -- somma provinciale > del nazionale
      prospProvTabOrder := order_by(prospProvTab, campo, ordine1);

      FOR i IN prospProvTabOrder.FIRST .. prospProvTabOrder.LAST LOOP
        writeLn('spalmabile > 0--->' || spalmabile || ' pv ' ||  prospProvTabOrder(i).idProvincia);

        IF (spalmabile > 0) THEN

          IF (prospProvTabOrder(i).diffTmp_ >= spalmabile) THEN
            partTimeRiproporzTmp := prospProvTabOrder(i).diffTmp_;
            prospProvTabOrder(i).diffTmp_ := prospProvTabOrder(i).diffTmp_ - spalmabile;
            spalmabile := spalmabile - partTimeRiproporzTmp;

          ELSIF (prospProvTabOrder(i).diffTmp_ < spalmabile) THEN
            spalmabile := spalmabile - prospProvTabOrder(i).diffTmp_;
            prospProvTabOrder(i).diffTmp_ := 0;
          END IF;
        ELSE
          EXIT;
        END IF;

      END LOOP;

    ELSIF spalmabile < 0 THEN -- somma provinciale < del nazionale
      spalmabile := abs(spalmabile);

      prospProvTabOrder := order_by(prospProvTab, campo, ordine2);
      FOR i IN prospProvTabOrder.FIRST .. prospProvTabOrder.LAST LOOP
           writeLn('spalmabile < 0 --->' || spalmabile || ' pv ' ||  prospProvTabOrder(i).idProvincia
           || ' _ '||  prospProvTabOrder(i).maxSplamabile_ );

          IF (spalmabile > 0) THEN
            IF (prospProvTabOrder(i).diffTmp_ < prospProvTabOrder(i).maxSplamabile_) THEN

               IF(prospProvTabOrder(i).diffTmp_ + spalmabile < prospProvTabOrder(i).maxSplamabile_) THEN
                  prospProvTabOrder(i).diffTmp_ := prospProvTabOrder(i).diffTmp_ + spalmabile;
                  spalmabile := 0;

               ELSE
                  spalmabile := spalmabile - (prospProvTabOrder(i).maxSplamabile_ - prospProvTabOrder(i).diffTmp_);
                  prospProvTabOrder(i).diffTmp_ := prospProvTabOrder(i).maxSplamabile_;
               END IF;

            END IF;

          ELSE
            EXIT;
          END IF;
      END LOOP;

    END IF;

    IF (prospProvTabOrder.COUNT > 0) THEN
      prospProvTab := prospProvTabOrder;
    END IF;

    ripristina_ordine(prospProvTab); -- 71441 PRODIS-602

  END spalma;
  

  PROCEDURE esegui_calcoli_nazionali(prospetto_id   IN NUMBER,
                                     cf_operatore   IN VARCHAR2,
                                     soloScoperture IN VARCHAR2,
                                     esito          OUT NUMBER) IS

    riepilogoNazionaleROW pro_d_riepilogo_nazionale%ROWTYPE;

    nLavInForzaNaz       NUMBER := 0;
    nLavBaseComputoArt3  NUMBER := 0;
    nLavBaseComputoArt18 NUMBER := 0;
    categoriaAzienda     CHAR := '';
    quotaRiservaDisabili NUMBER := 0;
    quotaRiservaArt18    NUMBER := 0;

    nPosizEson             NUMBER := 0;
    nDisabiliInForza       NUMBER := 0;
    nCatProtInForza        NUMBER := 0;
    nScopertureDisabili    NUMBER := 0;
    nScopertureCatProt     NUMBER := 0;
    sospensioniInCorso     CHAR := '';
    conteggioOccorrenze    NUMBER := 0;
    codiceCategoriaAzienda CHAR := '';
    e_categoria_azienda EXCEPTION;

  BEGIN

    riepilogoNazionaleROW.ID_PROSPETTO := prospetto_id;
    writeLn('[esegui_calcoli_nazionali] riepilogoNazionaleROW.id_prospetto ' ||
            riepilogoNazionaleROW.id_prospetto);

    /* N? lavoratori in forza nazionale */
    nLavInForzaNaz := pck_prodis_2012_naz_utils.get_n_lav_in_forza_naz(prospetto_id);
    --riepilogoNazionaleROW.XXX := nLavInForzaNaz;
    writeLn('[esegui_calcoli_nazionali] nLavInForzaNaz ' || nLavInForzaNaz);

    /* N? lavoratori (Base computo art. 3) * */
    nLavBaseComputoArt3                      := pck_prodis_2012_naz_utils.get_n_lav_base_computo_art_3(prospetto_id);
    riepilogoNazionaleROW.BASE_COMPUTO_ART_3 := nLavBaseComputoArt3;
    writeLn('[esegui_calcoli_nazionali] nLavBaseComputoArt3 ' ||
            nLavBaseComputoArt3);

    /* N? lavoratori (Base computo art. 18) * */
    nLavBaseComputoArt18                      := pck_prodis_2012_naz_utils.get_n_lav_base_computo_art_18(prospetto_id);
    riepilogoNazionaleROW.BASE_COMPUTO_ART_18 := nLavBaseComputoArt18;
    writeLn('[esegui_calcoli_nazionali] nLavBaseComputoArt18 ' ||
            nLavBaseComputoArt18);

    /* Categoria Azienda L.68/99 art.3 c.1 */
    categoriaAzienda := pck_prodis_2012_naz_utils.get_categoria_azienda(nLavBaseComputoArt3);
    writeLn('[esegui_calcoli_nazionali] categoriaAzienda ' ||
            categoriaAzienda);

    IF (categoriaAzienda = 'Z' AND nLavBaseComputoArt18 < 51) THEN
      RAISE e_categoria_azienda;
    END IF;

    /* Quota di riserva disabili */
    quotaRiservaDisabili                         := pck_prodis_2012_naz_utils.get_quota_riserva_disabili(prospetto_id,
                                                                                                         categoriaAzienda);
    riepilogoNazionaleROW.QUOTA_RISERVA_DISABILI := quotaRiservaDisabili;
    writeLn('[esegui_calcoli_nazionali] quotaRiservaDisabili ' ||
            quotaRiservaDisabili);

    /* Quota di riserva Art. 18 */
    quotaRiservaArt18                          := pck_prodis_2012_naz_utils.get_quota_riserva_art_18(prospetto_id);
    riepilogoNazionaleROW.QUOTA_RISERVA_ART_18 := quotaRiservaArt18;
    writeLn('[esegui_calcoli_nazionali] quotaRiservaArt18 ' ||
            quotaRiservaArt18);

    /* Data prima assunzione (dpr. 333/2000) */
    /* trattato dalla web app */

    /* Data seconda assunzione (dpr. 333/2000) */
    /* trattato dalla web app */

    /* Nessuna assunzione aggiuntiva */
    /* trattato dalla web app */

    /* N? posizioni esonerate */
    nPosizEson                                    := pck_prodis_2012_naz_utils.get_n_posizioni_esonerate(prospetto_id);
    riepilogoNazionaleROW.NUM_POSIZIONI_ESONERATE := nPosizEson;
    writeLn('[esegui_calcoli_nazionali] nPosizEson ' || nPosizEson);

    /* N? Disabili in forza (L.68/99 art.1) */
    nDisabiliInForza                            := pck_prodis_2012_naz_utils.get_n_disabili_in_forza(prospetto_id);
    riepilogoNazionaleROW.NUM_DISABILI_IN_FORZA := nDisabiliInForza;
    writeLn('[esegui_calcoli_nazionali] nDisabiliInForza ' ||
            nDisabiliInForza);

    /* N? Categorie protette in forza (L.68/99 art.18) */
    nCatProtInForza                             := pck_prodis_2012_naz_utils.get_n_cat_prot_in_forza(prospetto_id);
    riepilogoNazionaleROW.NUM_CAT_PROT_IN_FORZA := nCatProtInForza;
    writeLn('[esegui_calcoli_nazionali] nCatProtInForza ' ||
            nCatProtInForza);

    /* N? Scoperture disabili (L.68/99 art.1) */
    nScopertureDisabili                              := pck_prodis_2012_naz_utils.get_n_scoperture_disabili(prospetto_id);
    riepilogoNazionaleROW.NUM_SCOPERT_DISABILI_REALI := nScopertureDisabili;
    riepilogoNazionaleROW.NUM_SCOPERT_DISABILI       := PCK_PRODIS_2012_UTILS.handle_number(nScopertureDisabili);
    writeLn('[esegui_calcoli_nazionali] nScopertureDisabili ' ||
            nScopertureDisabili);

    /* N? Scoperture categorie protette (L.68/99 art.18) */
    nScopertureCatProt                                   := pck_prodis_2012_naz_utils.get_n_scoperture_cat_prot(prospetto_id);
    riepilogoNazionaleROW.NUM_SCOPERT_CAT_PROT_REALI     := nScopertureCatProt;
    riepilogoNazionaleROW.NUM_SCOPERT_CATEGORIE_PROTETTE := PCK_PRODIS_2012_UTILS.handle_number(nScopertureCatProt);
    writeLn('[esegui_calcoli_nazionali] nScopertureCatProt ' ||
            nScopertureCatProt);

    /* Sospensioni in corso */
    sospensioniInCorso                             := pck_prodis_2012_naz_utils.get_sospensioni_in_corso(prospetto_id);
    riepilogoNazionaleROW.FLG_SOSPENSIONI_IN_CORSO := sospensioniInCorso;
    writeLn('[esegui_calcoli_nazionali] sospensioniInCorso ' ||
            sospensioniInCorso);

    /* Numero quota esubero Art.18 */
    riepilogoNazionaleROW.quota_esuberi_art_18 := PCK_PRODIS_2012_NAZ_UTILS.get_valore_abbatt_naz(PCK_PRODIS_2012_NAZ_UTILS.get_quota_riserva_art_18(prospetto_id),
                                                                                                  PCK_PRODIS_2012_NAZ_UTILS.get_esuberi_art_18(prospetto_id),
                                                                                                  PCK_PRODIS_2012_NAZ_UTILS.get_n_cat_prot_in_forz_17_1_00(prospetto_id));
    writeLn('[esegui_calcoli_nazionali] sospensioniInCorso ' ||
            sospensioniInCorso);

    IF (soloScoperture IS NOT NULL AND soloScoperture = '1') THEN
      SELECT count(*)
        INTO conteggioOccorrenze
        FROM pro_d_riepilogo_nazionale
       WHERE pro_d_riepilogo_nazionale.id_prospetto =
             riepilogoNazionaleROW.id_prospetto;
      writeLn('[esegui_calcoli_nazionali] conteggioOccorrenze ' ||
              conteggioOccorrenze);

      IF conteggioOccorrenze > 0 THEN
        riepilogoNazionaleROW.d_aggiorn        := sysdate;
        riepilogoNazionaleROW.cod_user_aggiorn := cf_operatore;
        UPDATE pro_d_riepilogo_nazionale
           SET pro_d_riepilogo_nazionale.cod_user_aggiorn           = riepilogoNazionaleROW.cod_user_aggiorn,
               pro_d_riepilogo_nazionale.d_aggiorn                  = riepilogoNazionaleROW.d_aggiorn,
               pro_d_riepilogo_nazionale.NUM_SCOPERT_CAT_PROT_REALI = riepilogoNazionaleROW.NUM_SCOPERT_CAT_PROT_REALI,
               pro_d_riepilogo_nazionale.NUM_SCOPERT_DISABILI_REALI = riepilogoNazionaleROW.NUM_SCOPERT_DISABILI_REALI
         WHERE pro_d_riepilogo_nazionale.id_prospetto =
               riepilogoNazionaleROW.id_prospetto;
      ELSE
        riepilogoNazionaleROW.d_inserim        := sysdate;
        riepilogoNazionaleROW.cod_user_inserim := cf_operatore;
        INSERT INTO pro_d_riepilogo_nazionale
          (pro_d_riepilogo_nazionale.NUM_SCOPERT_CAT_PROT_REALI,
           pro_d_riepilogo_nazionale.NUM_SCOPERT_DISABILI_REALI,
           pro_d_riepilogo_nazionale.cod_user_inserim,
           pro_d_riepilogo_nazionale.d_inserim)
        VALUES
          (riepilogoNazionaleROW.NUM_SCOPERT_CAT_PROT_REALI,
           riepilogoNazionaleROW.NUM_SCOPERT_DISABILI_REALI,
           riepilogoNazionaleROW.cod_user_inserim,
           riepilogoNazionaleROW.d_inserim);
      END IF;
    ELSE
      codiceCategoriaAzienda := NULL;
      IF nLavBaseComputoArt18 <= 50 THEN
        IF categoriaAzienda = 'Z' THEN
          codiceCategoriaAzienda := 'A';
        ELSE
          codiceCategoriaAzienda := categoriaAzienda;
        END IF;
      ELSE
        codiceCategoriaAzienda := categoriaAzienda;
      END IF;
      UPDATE pro_d_prospetto
         SET pro_d_prospetto.id_t_categoria_azienda       = (SELECT id_t_categoria_azienda
                                                               FROM pro_t_categoria_azienda
                                                              WHERE cod_categoria_azienda =
                                                                    codiceCategoriaAzienda),
             pro_d_prospetto.num_lavor_in_forza_nazionale = nLavInForzaNaz
       WHERE pro_d_prospetto.id_prospetto =
             riepilogoNazionaleROW.id_prospetto;

      SELECT count(*)
        INTO conteggioOccorrenze
        FROM pro_d_riepilogo_nazionale
       WHERE pro_d_riepilogo_nazionale.id_prospetto =
             riepilogoNazionaleROW.id_prospetto;
      writeLn('[esegui_calcoli_nazionali] conteggioOccorrenze ' ||
              conteggioOccorrenze);

      IF conteggioOccorrenze > 0 THEN
        riepilogoNazionaleROW.d_aggiorn        := sysdate;
        riepilogoNazionaleROW.cod_user_aggiorn := cf_operatore;
        UPDATE pro_d_riepilogo_nazionale
           SET pro_d_riepilogo_nazionale.cod_user_aggiorn               = riepilogoNazionaleROW.cod_user_aggiorn,
               pro_d_riepilogo_nazionale.d_aggiorn                      = riepilogoNazionaleROW.d_aggiorn,
               pro_d_riepilogo_nazionale.id_prospetto                   = riepilogoNazionaleROW.id_prospetto,
               pro_d_riepilogo_nazionale.num_cat_prot_in_forza          = riepilogoNazionaleROW.num_cat_prot_in_forza,
               pro_d_riepilogo_nazionale.num_cat_prot_in_forza_cnt_dis  = riepilogoNazionaleROW.num_cat_prot_in_forza_cnt_dis,
               pro_d_riepilogo_nazionale.num_disabili_in_forza          = riepilogoNazionaleROW.num_disabili_in_forza,
               pro_d_riepilogo_nazionale.num_lavoratori_base_computo    = riepilogoNazionaleROW.num_lavoratori_base_computo,
               pro_d_riepilogo_nazionale.num_lavoratori_sospensione     = riepilogoNazionaleROW.num_lavoratori_sospensione,
               pro_d_riepilogo_nazionale.num_posizioni_esonerate        = riepilogoNazionaleROW.num_posizioni_esonerate,
               pro_d_riepilogo_nazionale.NUM_SCOPERT_CAT_PROT_REALI     = riepilogoNazionaleROW.NUM_SCOPERT_CAT_PROT_REALI,
               pro_d_riepilogo_nazionale.NUM_SCOPERT_DISABILI_REALI     = riepilogoNazionaleROW.NUM_SCOPERT_DISABILI_REALI,
               pro_d_riepilogo_nazionale.NUM_SCOPERT_CATEGORIE_PROTETTE = riepilogoNazionaleROW.NUM_SCOPERT_CATEGORIE_PROTETTE,
               pro_d_riepilogo_nazionale.NUM_SCOPERT_DISABILI           = riepilogoNazionaleROW.NUM_SCOPERT_DISABILI,
               pro_d_riepilogo_nazionale.quota_riserva_art_18           = riepilogoNazionaleROW.quota_riserva_art_18,
               pro_d_riepilogo_nazionale.quota_riserva_disabili         = riepilogoNazionaleROW.quota_riserva_disabili,
               pro_d_riepilogo_nazionale.base_computo_art_3             = riepilogoNazionaleROW.BASE_COMPUTO_ART_3,
               pro_d_riepilogo_nazionale.base_computo_art_18            = riepilogoNazionaleROW.BASE_COMPUTO_ART_18,
               pro_d_riepilogo_nazionale.FLG_SOSPENSIONI_IN_CORSO       = riepilogoNazionaleROW.FLG_SOSPENSIONI_IN_CORSO, --PRODIS-524
               pro_d_riepilogo_nazionale.QUOTA_ESUBERI_ART_18           = riepilogoNazionaleROW.QUOTA_ESUBERI_ART_18
         WHERE pro_d_riepilogo_nazionale.id_prospetto =
               riepilogoNazionaleROW.id_prospetto;
      ELSE
        riepilogoNazionaleROW.d_inserim        := sysdate;
        riepilogoNazionaleROW.cod_user_inserim := cf_operatore;
        INSERT INTO pro_d_riepilogo_nazionale VALUES riepilogoNazionaleROW;
      END IF;
    END IF;

    esito := 1;

  EXCEPTION
    WHEN e_categoria_azienda THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      esito := -101;

    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      esito := -100;

  END esegui_calcoli_nazionali;

  PROCEDURE esegui_calcoli_provinciali(prospetto_id   IN NUMBER,
                                       cf_operatore   IN VARCHAR2,
                                       soloScoperture IN VARCHAR2,
                                       esito          OUT NUMBER) IS

    prospettoProvinciaROW pro_r_prospetto_provincia%ROWTYPE;
    CURSOR prospettoProvinciaCURS(prospetto_id IN NUMBER) IS
      SELECT pro_r_prospetto_provincia.*
        FROM pro_r_prospetto_provincia
       WHERE pro_r_prospetto_provincia.id_prospetto = prospetto_id
       ORDER BY pro_r_prospetto_provincia.id_t_provincia; 

    prospProvTab prospetto_provincia_TABLE;
    prospProvRow prospetto_provincia_TYPE;

    prospProvTabOrder prospetto_provincia_TABLE;

    nDisabiliInForza   NUMBER := 0;
    nCatProtInForza    NUMBER := 0;
    nCatProtInForza17  NUMBER := 0;
    catEsclDisabProv   NUMBER := 0;
    catEsclCatProtProv NUMBER := 0;

    nLavPartTimeProv     NUMBER := 0;
    partTimeRiproporz    NUMBER := 0;
    diffPartTimeProv     NUMBER := 0;
    nLavPartTimeNaz      NUMBER := 0;
    partTimeRiproporzNaz NUMBER := 0;
    partTimeRiproporzPv  NUMBER := 0;
    disabiliRiproporzNaz NUMBER := 0;
    disabiliRiproporzPv  NUMBER := 0;   
    telelavoroRiproporzNaz NUMBER := 0; 
    telelavoroRiproporzPv NUMBER := 0;  

    nLavIntermitProv     NUMBER := 0;
    intermitRiproporz    NUMBER := 0;
    diffIntermitProv     NUMBER := 0;
    nLavIntermitNaz      NUMBER := 0;
    intermitRiproporzNaz NUMBER := 0;
    intermitRiproporzPv  NUMBER := 0;

    pos NUMBER := 0;

    spalmabile           NUMBER := 0;
    intermitRiproporzTmp NUMBER := 0;

    sommaMinimoCatProtInForzaPV NUMBER := 0;
    minimoNaz                   NUMBER := 0;

    id_provincia_sl NUMBER := 0;

    nLavBaseComputoArt3Naz  NUMBER := 0;
    nLavBaseComputoArt18Naz NUMBER := 0;

    sommaQuotaRiservaDisabiliPV NUMBER := 0;
    sommaQuotaRiservaArt18PV    NUMBER := 0;

    quotaRiservaDisabNaz NUMBER := 0;
    quotaRiservaArt18Naz NUMBER := 0;

    restoQRDAccumulato   NUMBER := 0;
    sommaQRDisab         NUMBER := 0;
    restoQRA18Accumulato NUMBER := 0;
    sommaQRArt18         NUMBER := 0;

    calcolato NUMBER := 0;

    nScopertureDisabili    NUMBER := 0;
    abbattimentoNaz        NUMBER := 0;
    abbattPercNaz          NUMBER := 0;
    esuberiArt18Naz        NUMBER := 0;
    nCatProtInForz17100Naz NUMBER := 0;

    cicloAbbattibile   NUMBER := 0;
    abbattimentoNazTmp NUMBER := 0;
    abbattibile        NUMBER := 0;

    conteggioOccorrenze NUMBER := 0;

    riepilogoProvincialeROW pro_d_riepilogo_provinciale%ROWTYPE;

    e_spalmabile EXCEPTION;
    e_categoria_azienda EXCEPTION;
    e_base_computo_negativa EXCEPTION;

    condizioneUscita NUMBER := 0;
  BEGIN

    /* Recupero l'identificativo della provincia della sede legale */
    writeLn('[esegui_calcoli_provinciali] prospetto_id ' || prospetto_id);
    SELECT pro_t_comune.id_provincia
      INTO id_provincia_sl
      FROM pro_t_comune, pro_d_sede_legale
     WHERE pro_t_comune.id_t_comune = pro_d_sede_legale.id_t_comune
       AND pro_d_sede_legale.id_prospetto = prospetto_id
       AND ROWNUM = 1;
    writeLn('[esegui_calcoli_provinciali] id_provincia_sl ' ||
            id_provincia_sl);

    OPEN prospettoProvinciaCURS(prospetto_id);
    LOOP
      FETCH prospettoProvinciaCURS
        INTO prospettoProvinciaROW;
      EXIT WHEN prospettoProvinciaCURS%NOTFOUND;

      prospProvRow.idProspettoProv            := 0;
      prospProvRow.idProvincia                := 0;
      prospProvRow.nLavDipProv_               := 0;
      prospProvRow.nDisabiliInForza           := 0;
      prospProvRow.nCatProtInForza            := 0;
      prospProvRow.nCatProtInForza17_         := 0;
      prospProvRow.catEsclDisabProv_          := 0;
      prospProvRow.catEsclCatProtProv_        := 0;
      prospProvRow.nLavPartTimeProv_          := 0;
      prospProvRow.partTimeRiproporz_         := 0;
      prospProvRow.intermitRiproporz_         := 0;
      prospProvRow.diffPartTimeProv_          := 0;
      prospProvRow.nLavIntermitProv_          := 0;
      prospProvRow.diffIntermitProv_          := 0;
      prospProvRow.preBaseComputoDisabiliPV_  := 0;
      prospProvRow.preBaseComputoCatProtPV_   := 0;
      prospProvRow.catProtInForza1pcPV_       := 0;
      prospProvRow.minimoCatProtInForzaPV_    := 0;
      prospProvRow.nLavBaseComputoArt3        := 0;
      prospProvRow.nLavBaseComputoArt18       := 0;
      prospProvRow.quotaRiservaDisabili       := 0;
      prospProvRow.quotaRiservaDisabiliArrot_ := 0;
      prospProvRow.quotaRiservaArt18          := 0;
      prospProvRow.restoQuotaRiservaArt18_    := 0;
      prospProvRow.quotaRiservaArt18Arrot_    := 0;
      prospProvRow.flgSospensioniInCorso      := '';
      prospProvRow.nPosizEsonerate            := 0;
      prospProvRow.scopertureDisabiliPV_      := 0;
      prospProvRow.nScopertureCatProt         := 0;
      prospProvRow.nScopertureDisab           := 0;

      prospProvRow.idProspettoProv := prospettoProvinciaROW.id_prospetto_prov;
      writeLn('[esegui_calcoli_provinciali] id_prospetto_prov ' ||
              prospettoProvinciaROW.id_prospetto_prov);

      prospProvRow.idProvincia := prospettoProvinciaROW.id_t_provincia;
      writeLn('[esegui_calcoli_provinciali] id_provincia ' ||
              prospettoProvinciaROW.id_t_provincia);

      prospProvRow.nLavDipProv_ := PCK_PRODIS_2012_PROV_UTILS.get_n_lav_dip_prov(prospettoProvinciaROW.id_prospetto_prov);
      writeLn('[esegui_calcoli_provinciali] prospProvRow.nLavDipProv_ ' ||
              prospProvRow.nLavDipProv_);

      nDisabiliInForza              := PCK_PRODIS_2012_PROV_UTILS.get_n_disabili_in_forza(prospettoProvinciaROW.id_prospetto_prov);
      prospProvRow.nDisabiliInForza := nDisabiliInForza;
      writeLn('[esegui_calcoli_provinciali] nDisabiliInForza ' || nDisabiliInForza);

      prospProvRow.nDisabiliFt      := PCK_PRODIS_2012_PROV_UTILS.get_n_disabili_ft(prospettoProvinciaROW.id_prospetto_prov);
      writeLn('[esegui_calcoli_provinciali] prospProvRow.nDisabiliFt ' || prospProvRow.nDisabiliFt);

      prospProvRow.nDisabiliInForzaBC  := PCK_PRODIS_2012_PROV_UTILS.get_n_disabili_in_forza_bc(prospettoProvinciaROW.id_prospetto_prov);

      writeLn('[esegui_calcoli_provinciali] nDisabiliInForza ' || prospProvRow.nDisabiliInForzaBC);
      
      nCatProtInForza              := PCK_PRODIS_2012_PROV_UTILS.get_n_cat_prot_in_forza(prospettoProvinciaROW.id_prospetto_prov);
      prospProvRow.nCatProtInForza := nCatProtInForza;
      writeLn('[esegui_calcoli_provinciali] nCatProtInForza ' ||
              nCatProtInForza);

      nCatProtInForza17               := PCK_PRODIS_2012_PROV_UTILS.get_n_catprotforza_170100_pv(prospettoProvinciaROW.id_prospetto_prov);
      prospProvRow.nCatProtInForza17_ := nCatProtInForza17;
      writeLn('[esegui_calcoli_provinciali] nCatProtInForza17 ' ||
              nCatProtInForza17);

      catEsclDisabProv               := PCK_PRODIS_2012_PROV_UTILS.get_cat_escl_disab_prov(prospettoProvinciaROW.id_prospetto_prov);
      prospProvRow.catEsclDisabProv_ := catEsclDisabProv;
      writeLn('[esegui_calcoli_provinciali] catEsclDisabProv ' ||
              catEsclDisabProv);

      catEsclCatProtProv               := PCK_PRODIS_2012_PROV_UTILS.get_cat_escl_cat_prot_prov(prospettoProvinciaROW.id_prospetto_prov);
      prospProvRow.catEsclCatProtProv_ := catEsclCatProtProv;
      writeLn('[esegui_calcoli_provinciali] catEsclCatProtProv ' ||
              catEsclCatProtProv);

      nLavPartTimeProv               := PCK_PRODIS_2012_PROV_UTILS.get_n_lav_part_time_prov(prospettoProvinciaROW.id_prospetto_prov);
      prospProvRow.nLavPartTimeProv_ := nLavPartTimeProv;
      writeLn('[esegui_calcoli_provinciali] nLavPartTimeProv ' ||
              nLavPartTimeProv);

      partTimeRiproporz               := PCK_PRODIS_2012_PROV_UTILS.get_part_time_riproporz(prospettoProvinciaROW.id_prospetto_prov);
      prospProvRow.partTimeRiproporz_ := partTimeRiproporz;
      writeLn('[esegui_calcoli_provinciali] partTimeRiproporz ' ||
              partTimeRiproporz);

      diffPartTimeProv               := PCK_PRODIS_2012_PROV_UTILS.get_diff_part_time_prov(prospettoProvinciaROW.id_prospetto_prov);
      prospProvRow.diffPartTimeProv_ := diffPartTimeProv;
      writeLn('[esegui_calcoli_provinciali] diffPartTimeProv ' ||
              diffPartTimeProv);

      nLavIntermitProv               := PCK_PRODIS_2012_PROV_UTILS.get_n_lav_intermit_prov(prospettoProvinciaROW.id_prospetto_prov);
      prospProvRow.nLavIntermitProv_ := nLavIntermitProv;
      writeLn('[esegui_calcoli_provinciali] nLavIntermitProv ' ||
              nLavIntermitProv);

      intermitRiproporz               := PCK_PRODIS_2012_PROV_UTILS.get_intermit_riproporz(prospettoProvinciaROW.id_prospetto_prov);
      prospProvRow.intermitRiproporz_ := intermitRiproporz;
      writeLn('[esegui_calcoli_provinciali] intermitRiproporz ' ||
              intermitRiproporz);

      diffIntermitProv               := PCK_PRODIS_2012_PROV_UTILS.get_diff_intermit_prov(prospettoProvinciaROW.id_prospetto_prov);
      prospProvRow.diffIntermitProv_ := diffIntermitProv;
      writeLn('[esegui_calcoli_provinciali] diffIntermitProv ' ||
              diffIntermitProv);

      prospProvRow.telelavoroRiproporz_ := PCK_PRODIS_2012_PROV_UTILS.get_telelav_riproporz(prospettoProvinciaROW.id_prospetto_prov);
      writeLn('[esegui_calcoli_provinciali] telelavoroRiproporz_ ' || prospProvRow.telelavoroRiproporz_);

      prospProvRow.telelavoroFT_ := PCK_PRODIS_2012_PROV_UTILS.get_n_lav_telelav_prov(prospettoProvinciaROW.id_prospetto_prov);
      writeLn('[esegui_calcoli_provinciali] telelavoroFT_ ' || prospProvRow.telelavoroFT_);

      --prospProvRow.telelavoroPT_ := PCK_PRODIS_2012_PROV_UTILS.get_n_lav_telelav_pt_prov(prospettoProvinciaROW.id_prospetto_prov);
      --writeLn('[esegui_calcoli_provinciali] telelavoroPT_ ' || prospProvRow.telelavoroPT_);
      
      prospProvTab(pos) := prospProvRow;
      pos := pos + 1;

    END LOOP;
    CLOSE prospettoProvinciaCURS;

    /*
    II. Si rideterminano i valori dei parttime riproporzionati e intermittenti riproporzionati,
    confrontando le somme di tutti i provinciali calcolati al passo precedente, con i totali nazionali:

    Relativamente al part-time, per le seguenti variabili:
    'TuttiNumParttimePV' =  'NLavoratoriParttime' nazionali calcolato al punto 1)I.
    'TuttiPartTimeRiproporzionatiPV' = SOMMA('Part-timeRiproporzionati_PV')
    'PartTimeRiproporzionati nazionali' = 'Part-timeRiproporzionatiNazionali ' calcolato al punto 1)I.
    'differenzaPartTimeRiproporzionatiPV' = 'DifferenzaParttime_PV' calcolato al passo precedente, su ogni provincia.

    Se ('TuttiNumParttimePV' - 'PartTimeRiproporzionati nazionali') > ('TuttiNumParttimePV' - 'TuttiPartTimeRiproporzionatiPV') allora si ottiene il caso di Q2 con 'PvminorDifferenza':
    'PvminorDifferenza' = relativamente a tutti i Q2 del prospetto, e la provincia con il minor valore tra  'differenzaPartTimeRiproporzionatiPV'
    'differenzaMinorPartTimeRiproporzionati' = ('TuttiNumParttimePV' - 'PartTimeRiproporzionati nazionali') - ('TuttiNumParttimePV' - 'TuttiPartTimeRiproporzionatiPV')

    Se ('TuttiNumParttimePV' - 'PartTimeRiproporzionati nazionali')  <  ('TuttiNumParttimePV' - 'TuttiPartTimeRiproporzionatiPV') allora si ottiene il caso di Q2 con  'PvmaggiorDifferenzan'
    'PvmaggiorDifferenzan' = relativamente a tutti i Q2 del prospetto, e l'elenco delle province dal maggior valore tra  'differenzaPartTimeRiproporzionatiPV' al piu basso valore,
    fino ad esaurimento dei lavoratori da decurtare e quindi:
    'PvmaggiorDifferenza1' = relativamente a tutti i Q2 del prospetto, e la provincia con il maggior valore tra 'differenzaPartTimeRiproporzionatiPV'
    'differenzaMaggiorPartTimeRiproporzionati' = ('TuttiNumParttimePV' - 'TuttiPartTimeRiproporzionatiPV') - ('TuttiNumParttimePV' - 'PartTimeRiproporzionati nazionali')
    Se 'differenzaMaggiorPartTimeRiproporzionati' > 'differenzaPartTimeRiproporzionatiPV' relativo alla 'PvmaggiorDifferenza1' allora si prosegue con la 'PvmaggiorDifferenza2'
    e a seguire, le sucessive finche ('differenzaMaggiorPartTimeRiproporzionati' - 'differenzaPartTimeRiproporzionatiPV' relativo alla 'PvmaggiorDifferenzan' = 0.

    nb Relativamente agli intermittenti, vedi calcoli dei part-time utilizzando i dati degli intermittenti.
    */

    /* Nuova implementazione spalma parttime */
    partTimeRiproporzNaz := PCK_PRODIS_2012_NAZ_UTILS.get_parttime_riproporzionati(prospetto_id);

    FOR i IN prospProvTab.FIRST .. prospProvTab.LAST LOOP
      partTimeRiproporzPv := partTimeRiproporzPv + prospProvTab(i).partTimeRiproporz_;
      prospProvTab(i).diffTmp_ := prospProvTab(i).diffPartTimeProv_;
    END LOOP;

    IF partTimeRiproporzNaz <> partTimeRiproporzPv THEN
      spalmaBC(partTimeRiproporzNaz,
             partTimeRiproporzPv,
             'diffPartTimeProv_',
             prospProvTab);

      FOR i IN prospProvTab.FIRST .. prospProvTab.LAST LOOP
        prospProvTab(i).diffPartTimeProv_ := prospProvTab(i).diffTmp_;
      END LOOP;
    END IF;

    /* Spalma telelavoro */
    telelavoroRiproporzNaz := PCK_PRODIS_2012_NAZ_UTILS.get_telelav_riproporzionati(prospetto_id);

    FOR i IN prospProvTab.FIRST .. prospProvTab.LAST LOOP
      telelavoroRiproporzPv := telelavoroRiproporzPv + prospProvTab(i).telelavoroRiproporz_;
      prospProvTab(i).ordTmp_ := PCK_PRODIS_2012_PROV_UTILS.get_n_lav_telelav_pt_prov(prospProvTab(i).idProspettoProv) - prospProvTab(i).telelavoroRiproporz_;
      prospProvTab(i).diffTmp_ := prospProvTab(i).telelavoroRiproporz_;
    END LOOP;

    IF telelavoroRiproporzNaz <> telelavoroRiproporzPv THEN
      spalmaBC(telelavoroRiproporzNaz,
             telelavoroRiproporzPv,
             'ordTmp_',
             prospProvTab);

      FOR i IN prospProvTab.FIRST .. prospProvTab.LAST LOOP
        prospProvTab(i).telelavoroRiproporz_ := prospProvTab(i).diffTmp_;
      END LOOP;
    END IF;

     /* spalma intermittenti */
    intermitRiproporzNaz := PCK_PRODIS_2012_NAZ_UTILS.get_intermittenti_riproporz(prospetto_id);

    FOR i IN prospProvTab.FIRST .. prospProvTab.LAST LOOP
      intermitRiproporzPv := intermitRiproporzPv + prospProvTab(i).intermitRiproporz_;
      prospProvTab(i).diffTmp_ := prospProvTab(i).diffIntermitProv_;
    END LOOP;

    IF intermitRiproporzNaz <> intermitRiproporzPv THEN
      spalmaBC(intermitRiproporzNaz,
             intermitRiproporzPv,
             'diffIntermitProv_',
             prospProvTab);

      FOR i IN prospProvTab.FIRST .. prospProvTab.LAST LOOP
        prospProvTab(i).diffIntermitProv_ := prospProvTab(i).diffTmp_;
      END LOOP;
    END IF;


    /* spalma i disabili inizio */
    disabiliRiproporzNaz := PCK_PRODIS_2012_NAZ_UTILS.get_n_disabili_in_forza(prospetto_id);

    FOR i IN prospProvTab.FIRST .. prospProvTab.LAST LOOP
      disabiliRiproporzPv := disabiliRiproporzPv + prospProvTab(i).nDisabiliInForza;
    END LOOP;

    IF disabiliRiproporzNaz <> disabiliRiproporzPv THEN
      FOR i IN prospProvTab.FIRST .. prospProvTab.LAST LOOP
        prospProvTab(i).diffTmp_ := PCK_PRODIS_2012_PROV_UTILS.get_disabili_riproporz(prospProvTab(i).idProspettoProv);
        prospProvTab(i).maxSplamabile_ := PCK_PRODIS_2012_PROV_UTILS.get_n_disabili_pt(prospProvTab(i).idProspettoProv);
        prospProvTab(i).ordTmp_ := prospProvTab(i).maxSplamabile_ - prospProvTab(i).diffTmp_;
      END LOOP;

      spalma(disabiliRiproporzNaz,
             disabiliRiproporzPv,
             'ordTmp_',
             prospProvTab,
             'D');

      FOR i IN prospProvTab.FIRST .. prospProvTab.LAST LOOP
        prospProvTab(i).nDisabiliInForza := prospProvTab(i).nDisabiliFt + prospProvTab(i).diffTmp_;
        prospProvTab(i).diffTmp_ := 0;
        prospProvTab(i).maxSplamabile_ := 0;
      END LOOP;
    END IF;
    
    /*
    III.
    Si determinano dei valori di base di computo provinciale temporanea
    per tutti i 'Quadri 2' provinciali, le cui quote  per il momento non
    includono la decurtazione delle categorie protette in forza
    limitatamente all'1% del numero dei lavoratori in forza, e cioe:
    */
    FOR i IN prospProvTab.FIRST .. prospProvTab.LAST LOOP
      prospProvTab(i).preBaseComputoDisabiliPV_ := prospProvTab(i).nLavDipProv_ -
                                                   prospProvTab(i).nDisabiliInForzaBC -
                                                   prospProvTab(i).catEsclDisabProv_ -
                                                   prospProvTab(i).diffPartTimeProv_ -
                                                   prospProvTab(i).diffIntermitProv_ -
                                                   prospProvTab(i).telelavoroRiproporz_ -
                                                   prospProvTab(i).telelavoroFT_;

      writeLn('[esegui_calcoli_provinciali] prospProvTab(i).preBaseComputoDisabiliPV_ ' ||
              prospProvTab(i).preBaseComputoDisabiliPV_);

      prospProvTab(i).preBaseComputoCatProtPV_ := prospProvTab(i).nLavDipProv_ -
                                                  prospProvTab(i).nDisabiliInForzaBC -
                                                  prospProvTab(i).catEsclCatProtProv_ -
                                                  prospProvTab(i).diffPartTimeProv_ -
                                                  prospProvTab(i).diffIntermitProv_ -
                                                  prospProvTab(i).telelavoroRiproporz_ -
                                                  prospProvTab(i).telelavoroFT_;


      writeLn('[esegui_calcoli_provinciali] prospProvTab(i).preBaseComputoCatProtPV_ ' ||
              prospProvTab(i).preBaseComputoCatProtPV_);

      prospProvTab(i).catProtInForza1pcPV_ := PCK_PRODIS_2012_UTILS.round_number((1 / 100) *
                                                                                 prospProvTab(i)
                                                                                 .nLavDipProv_);
      writeLn('[esegui_calcoli_provinciali] prospProvTab(i).catProtInForza1pcPV_ ' ||
              prospProvTab(i).catProtInForza1pcPV_);

      prospProvTab(i).minimoCatProtInForzaPV_ := PCK_PRODIS_2012_UTILS.minimo(prospProvTab(i)
                                                                              .nCatProtInForza,
                                                                              prospProvTab(i)
                                                                              .catProtInForza1pcPV_);
      writeLn('[esegui_calcoli_provinciali] prospProvTab(i).minimoCatProtInForzaPV_ ' ||
              prospProvTab(i).minimoCatProtInForzaPV_);

    END LOOP;

    FOR i IN prospProvTab.FIRST .. prospProvTab.LAST LOOP
      sommaMinimoCatProtInForzaPV := sommaMinimoCatProtInForzaPV +
                                     prospProvTab(i).minimoCatProtInForzaPV_;
    END LOOP;

    writeLn('[esegui_calcoli_provinciali] sommaMinimoCatProtInForzaPV ' ||
            sommaMinimoCatProtInForzaPV);

    minimoNaz := PCK_PRODIS_2012_UTILS.minimo(pck_prodis_2012_naz_utils.get_n_cat_prot_in_forza(prospetto_id),
                                              pck_prodis_2012_naz_utils.get_cat_prot_in_forza_1(prospetto_id));

    writeLn('[esegui_calcoli_provinciali] minimoNaz ' || minimoNaz);

    IF (minimoNaz <> sommaMinimoCatProtInForzaPV) THEN
      IF (minimoNaz < sommaMinimoCatProtInForzaPV) THEN
        prospprovtaborder := order_by(prospProvTab, 'minimoCatProtInForzaPV_', 'DESC');
        spalmabile        := sommaMinimoCatProtInForzaPV - minimoNaz;
        writeLn('[esegui_calcoli_provinciali] spalmabile ' || spalmabile);
        FOR i IN prospprovtaborder.FIRST .. prospprovtaborder.LAST LOOP
          IF (spalmabile > 0) THEN
            IF (prospProvTabOrder(i).minimoCatProtInForzaPV_ >= spalmabile) THEN
              intermitRiproporzTmp := prospprovtaborder(i)
                                     .minimoCatProtInForzaPV_;
              prospprovtaborder(i).minimoCatProtInForzaPV_ := prospprovtaborder(i)
                                                             .minimoCatProtInForzaPV_ -
                                                              spalmabile;
              spalmabile := spalmabile - intermitRiproporzTmp;
            ELSIF (prospProvTabOrder(i).minimoCatProtInForzaPV_ < spalmabile) THEN
              spalmabile := spalmabile - prospProvTabOrder(i)
                           .minimoCatProtInForzaPV_;
              prospProvTabOrder(i).minimoCatProtInForzaPV_ := 0;
            END IF;
          END IF;
        END LOOP;
      ELSIF (minimoNaz > sommaMinimoCatProtInForzaPV) THEN
        prospprovtaborder := order_by(prospProvTab,
                                      'minimoCatProtInForzaPV_',
                                      'ASC');
        spalmabile        := minimoNaz - sommaMinimoCatProtInForzaPV;
        writeLn('[esegui_calcoli_provinciali] spalmabile ' || spalmabile);
        FOR i IN prospprovtaborder.FIRST .. prospprovtaborder.LAST LOOP
          IF (spalmabile > 0 AND
             (prospprovtaborder(i)
             .preBaseComputoDisabiliPV_ -
              (prospprovtaborder(i).minimoCatProtInForzaPV_ + spalmabile)) > 0) THEN
            prospprovtaborder(i).minimoCatProtInForzaPV_ := prospprovtaborder(i)
                                                           .minimoCatProtInForzaPV_ +
                                                            spalmabile;
            spalmabile := 0;
          END IF;
        END LOOP;
        IF (spalmabile > 0) THEN
          RAISE e_spalmabile;
        END IF;
      END IF;
      IF (prospProvTabOrder.COUNT > 0) THEN
        prospProvTab := prospProvTabOrder;
      END IF;
    END IF;

    ripristina_ordine(prospProvTab); -- 71441 PRODIS-602

    /*
    IV.
    a questo punto si determinano le basi di computo per ciascun 'Quadro 2' provinciale:
    */
    FOR i IN prospProvTab.FIRST .. prospProvTab.LAST LOOP
      prospProvTab(i).nLavBaseComputoArt3 := prospProvTab(i).preBaseComputoDisabiliPV_ -
                                             prospProvTab(i).minimoCatProtInForzaPV_;
      writeLn('[esegui_calcoli_provinciali] prospProvTab(i).nLavBaseComputoArt3 ' ||
              prospProvTab(i).nLavBaseComputoArt3);

      --PRODIS-511
      IF (prospProvTab(i).nLavBaseComputoArt3 < 0) THEN
        RAISE e_base_computo_negativa;
      END IF;
      --PRODIS-511

      prospProvTab(i).nLavBaseComputoArt18 := prospProvTab(i).preBaseComputoCatProtPV_ -
                                              prospProvTab(i).minimoCatProtInForzaPV_;
      writeLn('[esegui_calcoli_provinciali] prospProvTab(i).nLavBaseComputoArt18 ' ||
              prospProvTab(i).nLavBaseComputoArt18);

      --PRODIS-511
      IF (prospProvTab(i).nLavBaseComputoArt18 < 0) THEN
        RAISE e_base_computo_negativa;
      END IF;
      --PRODIS-511
    END LOOP;

    --PRODIS-571
    --xxxxxxxxxxxx
    --PRODIS-571

    /*
    V.
    Alche si determinano le quote di riserva per ogni 'Quadro 2' provinciale:
    */
    /*
    Quota di riserva disabili_PV (PRO_D_RIEPILOGO_PROVINCIALE.QUOTA_RISERVA_DISABILI)

    Valore calcolato automaticamente nel seguente modo:
    valore calcolato automaticamente sulla base del numero di lavoratori della base di computo nazionale e cioe:
    1) Si prende 'N? lavoratori (Base computo art. 3)_PV':
    2) Se il valore e >= 15 e <= 35 dipendenti: il valore da mettere nella quota riserva disabili e 1 sulla provincia della sede legale; su tutte le altre si mette 0;
    Se e >= 36 e <= 50 dipendenti: il valore da mettere nella quota riserva disabili e 2 sulla provincia della sede legale; su tutte le altre si mette 0;
    Se e >= 51 dipendenti: il valore da mettere e: 7% di 'N? lavoratori (Base computo art. 3)_PV';
    (nb nel calcolo percentuale, i decimali vengono arrotondati: Se > 0,50 si aggiunge 1 intero; Se <= 0,50 non si aggiunge niente).
    Dopo aver applicato la quota del 7% se la somma delle quote di riserva delle province fosse inferiore alla quota di riserva nazionale, la quota di riserva in piu,
    che non si saprebbe a quale provincia abbinare e da applicare alla provincia della sede legale;
    invece, se la somma delle quote di riserva delle province fosse superiore alla quota di riserva nazionale, la quota di riserva che bisognerebbe togliere, viene detratta dalla provincia che ha un resto decimale maggiore ed a seguire sulle altre province con resto decimale minore fino all'esaurimento delle unita.
    Quindi:
    a) se  'Quota di riserva disabili' nazionale (calcolato al punto 1)IV.) > 0 allora:
    'Quota di riserva disabili_PV'  della sede legale = 'Quota di riserva disabili_PV'  della sede legale + (PRO_D_RIEPILOGO_NAZIONALE.QUOTA_RISERVA_DISABILI - SOMMA('Quota di riserva disabili_PV' di tutte le prov')
    b) se PRO_D_RIEPILOGO_NAZIONALE.QUOTA_RISERVA_DISABILI - SOMMA('Quota di riserva disabili_PV' di tutte le prov') < 0 allora:
    Si prende la provincia che, a seguito del calcolo del 7%, ha il resto decimale maggiore;
    a tale provincia si ricalcola la sua stessa quota:
    'quota riserva disabili_PV' - (SOMMA('Quota di riserva disabili_PV' di tutte le prov')  -  PRO_D_RIEPILOGO_NAZIONALE.QUOTA_RISERVA_DISABILI).
    */
    nLavBaseComputoArt3Naz := pck_prodis_2012_naz_utils.get_n_lav_base_computo_art_3(prospetto_id);
    writeLn('[esegui_calcoli_provinciali] nLavBaseComputoArt3Naz ' || nLavBaseComputoArt3Naz);

    FOR i IN prospProvTab.FIRST .. prospProvTab.LAST LOOP
      IF (nLavBaseComputoArt3Naz >= 15 AND nLavBaseComputoArt3Naz <= 35) THEN
        IF (id_provincia_sl = prospProvTab(i).idProvincia) THEN
          prospProvTab(i).quotaRiservaDisabili := 1;
          prospProvTab(i).quotaRiservaDisabiliArrot_ := 1;
        ELSE
          prospProvTab(i).quotaRiservaDisabili := 0;
          prospProvTab(i).quotaRiservaDisabiliArrot_ := 0;
        END IF;
      ELSIF (nLavBaseComputoArt3Naz >= 36 AND nLavBaseComputoArt3Naz <= 50) THEN
        IF (id_provincia_sl = prospProvTab(i).idProvincia) THEN
          prospProvTab(i).quotaRiservaDisabili := 2;
          prospProvTab(i).quotaRiservaDisabiliArrot_ := 2;
        ELSE
          prospProvTab(i).quotaRiservaDisabili := 0;
          prospProvTab(i).quotaRiservaDisabiliArrot_ := 0;
        END IF;
      ELSIF (nLavBaseComputoArt3Naz >= 51) THEN
        prospProvTab(i).quotaRiservaDisabili := (7 / 100) * prospProvTab(i).nLavBaseComputoArt3;

        prospProvTab(i).restoQuotaRiservaDisabili_ := prospProvTab(i).quotaRiservaDisabili -
                                                      trunc(prospProvTab(i).quotaRiservaDisabili);
        prospProvTab(i).quotaRiservaDisabiliArrot_ := PCK_PRODIS_2012_UTILS.round_number(
                                                      prospProvTab(i).quotaRiservaDisabili);
      END IF;
      writeLn('[esegui_calcoli_provinciali] prospProvTab(i).quotaRiservaDisabili ' ||
              prospProvTab(i).quotaRiservaDisabili || ' idProv ' ||prospProvTab(i).idProvincia);

    END LOOP;

    IF (nLavBaseComputoArt3Naz >= 51) THEN
      prospprovtaborder := order_by(prospProvTab,
                                    'restoQuotaRiservaDisabili_',
                                    'DESC');

      FOR i IN prospprovtaborder.FIRST .. prospprovtaborder.LAST LOOP
        sommaQuotaRiservaDisabiliPV := sommaQuotaRiservaDisabiliPV +
                                       prospprovtaborder(i).quotaRiservaDisabiliArrot_;
      END LOOP;
      writeLn('[esegui_calcoli_provinciali] sommaQuotaRiservaDisabiliPV ' ||
              sommaQuotaRiservaDisabiliPV);

      quotaRiservaDisabNaz := pck_prodis_2012_naz_utils.get_quota_riserva_disabili(prospetto_id,
           pck_prodis_2012_naz_utils.get_categoria_azienda(
                           pck_prodis_2012_naz_utils.get_n_lav_base_computo_art_3(prospetto_id)));
      writeLn('[esegui_calcoli_provinciali] quotaRiservaDisabNaz ' || quotaRiservaDisabNaz);

      restoQRDAccumulato := sommaQuotaRiservaDisabiliPV - quotaRiservaDisabNaz;

      FOR i IN prospprovtaborder.FIRST .. prospprovtaborder.LAST LOOP
        IF ((quotaRiservaDisabNaz - sommaQuotaRiservaDisabiliPV) > 0) THEN
          IF (id_provincia_sl = prospprovtaborder(i).idProvincia) THEN
            prospprovtaborder(i).quotaRiservaDisabili := prospprovtaborder(i).quotaRiservaDisabiliArrot_ +
                                                         quotaRiservaDisabNaz -
                                                         sommaQuotaRiservaDisabiliPV;
            prospprovtaborder(i).quotaRiservaDisabiliArrot_ :=
                      PCK_PRODIS_2012_UTILS.round_number(prospprovtaborder(i).quotaRiservaDisabili);
          END IF;
        ELSIF ((quotaRiservaDisabNaz - sommaQuotaRiservaDisabiliPV) < 0) THEN
          IF (restoQRDAccumulato > 0) THEN
            sommaQRDisab := prospprovtaborder(i)
                           .quotaRiservaDisabiliArrot_ - restoQRDAccumulato;
            IF (sommaQRDisab >= 0) THEN
              prospprovtaborder(i).quotaRiservaDisabili := sommaQRDisab;
              prospprovtaborder(i).quotaRiservaDisabiliArrot_ :=
                           PCK_PRODIS_2012_UTILS.round_number(prospprovtaborder(i).quotaRiservaDisabili);
              restoQRDAccumulato := 0;
            ELSE
              prospprovtaborder(i).quotaRiservaDisabili := 0;
              prospprovtaborder(i).quotaRiservaDisabiliArrot_ := 0;
              restoQRDAccumulato := abs(sommaQRDisab);
            END IF;
            --calcolato := 1;
          END IF;
        END IF;


        writeLn('[esegui_calcoli_provinciali] -> prospprovtaborder(i).quotaRiservaDisabili ' ||
                prospprovtaborder(i).quotaRiservaDisabili || '  prospprovtaborder(i).idProvincia '||  prospprovtaborder(i).idProvincia);
        writeLn('[esegui_calcoli_provinciali] -> prospprovtaborder(i).quotaRiservaDisabiliArrot_ ' ||
                prospprovtaborder(i).quotaRiservaDisabiliArrot_);
      END LOOP;
      IF (prospProvTabOrder.COUNT > 0) THEN
        prospProvTab := prospProvTabOrder;
      END IF;
    END IF;

    ripristina_ordine(prospProvTab); -- 71441 PRODIS-602

    nLavBaseComputoArt18Naz := pck_prodis_2012_naz_utils.get_n_lav_base_computo_art_18(prospetto_id);
    writeLn('[esegui_calcoli_provinciali] nLavBaseComputoArt18Naz ' || nLavBaseComputoArt18Naz);
    FOR i IN prospProvTab.FIRST .. prospProvTab.LAST LOOP
      IF (nLavBaseComputoArt18Naz >= 51 AND nLavBaseComputoArt18Naz <= 150) THEN
        IF (id_provincia_sl = prospProvTab(i).idProvincia) THEN
          prospProvTab(i).quotaRiservaArt18 := 1;
          prospProvTab(i).quotaRiservaArt18Arrot_ := 1;
        ELSE
          prospProvTab(i).quotaRiservaArt18 := 0;
          prospProvTab(i).quotaRiservaArt18Arrot_ := 0;
        END IF;
      ELSIF (nLavBaseComputoArt18Naz > 150) THEN
        prospProvTab(i).quotaRiservaArt18 := (1 / 100) * prospProvTab(i)
                                            .nLavBaseComputoArt18;
        prospProvTab(i).restoQuotaRiservaArt18_ := prospProvTab(i).quotaRiservaArt18 -
                                                   trunc(prospProvTab(i).quotaRiservaArt18);
        prospProvTab(i).quotaRiservaArt18Arrot_ := PCK_PRODIS_2012_UTILS.round_number(
                                                prospProvTab(i).quotaRiservaArt18);
      END IF;
    END LOOP;
    IF (nLavBaseComputoArt18Naz > 150) THEN
      prospprovtaborder := order_by(prospProvTab,
                                    'restoQuotaRiservaArt18_',
                                    'DESC');

      FOR i IN prospprovtaborder.FIRST .. prospprovtaborder.LAST LOOP
        sommaQuotaRiservaArt18PV := sommaQuotaRiservaArt18PV +
                                    prospprovtaborder(i).quotaRiservaArt18Arrot_;
      END LOOP;
      writeLn('[esegui_calcoli_provinciali] sommaQuotaRiservaArt18PV ' ||
              sommaQuotaRiservaArt18PV);

      quotaRiservaArt18Naz := pck_prodis_2012_naz_utils.get_quota_riserva_art_18(prospetto_id);
      writeLn('[esegui_calcoli_provinciali] quotaRiservaArt18Naz ' ||
              quotaRiservaArt18Naz);

      restoQRA18Accumulato := sommaQuotaRiservaArt18PV -
                              quotaRiservaArt18Naz;
      FOR i IN prospprovtaborder.FIRST .. prospprovtaborder.LAST LOOP
        IF ((quotaRiservaArt18Naz - sommaQuotaRiservaArt18PV) > 0) THEN
          IF (id_provincia_sl = prospprovtaborder(i).idProvincia) THEN
            prospprovtaborder(i).quotaRiservaArt18 := prospprovtaborder(i).quotaRiservaArt18Arrot_ +
                                                      quotaRiservaArt18Naz -
                                                      sommaQuotaRiservaArt18PV;
            prospprovtaborder(i).quotaRiservaArt18Arrot_ := PCK_PRODIS_2012_UTILS.round_number(prospprovtaborder(i)
                                                                                               .quotaRiservaArt18);
          END IF;
        ELSIF ((quotaRiservaArt18Naz - sommaQuotaRiservaArt18PV) < 0) THEN
          IF (restoQRA18Accumulato > 0) THEN
            sommaQRArt18 := prospprovtaborder(i).quotaRiservaArt18Arrot_ - restoQRA18Accumulato;
            IF (sommaQRArt18 >= 0) THEN
              prospprovtaborder(i).quotaRiservaArt18 := sommaQRArt18;
              prospprovtaborder(i).quotaRiservaArt18Arrot_ := PCK_PRODIS_2012_UTILS.round_number(
                                                           prospprovtaborder(i).quotaRiservaArt18);
              restoQRA18Accumulato := 0;
            ELSE
              prospprovtaborder(i).quotaRiservaArt18 := 0;
              prospprovtaborder(i).quotaRiservaArt18Arrot_ := 0;
              restoQRA18Accumulato := abs(sommaQRArt18);
            END IF;
            calcolato := 1;
          END IF;
        END IF;
        writeLn('[esegui_calcoli_provinciali] prospprovtaborder(i).quotaRiservaArt18 ' ||
                prospprovtaborder(i).quotaRiservaArt18);
        writeLn('[esegui_calcoli_provinciali] prospprovtaborder(i).quotaRiservaArt18Arrot_ ' ||
                prospprovtaborder(i).quotaRiservaArt18Arrot_);
      END LOOP;
      IF (prospProvTabOrder.COUNT > 0) THEN
        prospProvTab := prospProvTabOrder;
      END IF;

      ripristina_ordine(prospProvTab); -- 71441 PRODIS-602

    END IF;

    /*
    VI. Si calcolano le scoperture provinciali (senza considerare le compensazioni):
    */
    nScopertureDisabili := pck_prodis_2012_naz_utils.get_quota_riserva_disabili(prospetto_id,
                           pck_prodis_2012_naz_utils.get_categoria_azienda(
                               pck_prodis_2012_naz_utils.get_n_lav_base_computo_art_3(prospetto_id))) -
                           pck_prodis_2012_naz_utils.get_n_posizioni_esonerate(prospetto_id) -
                           pck_prodis_2012_naz_utils.get_n_disabili_in_forza(prospetto_id); -- TODO

    abbattimentoNaz := pck_prodis_2012_naz_utils.get_valore_abbatt_naz(pck_prodis_2012_naz_utils.get_quota_riserva_art_18(prospetto_id),
                                                                       pck_prodis_2012_naz_utils.get_esuberi_art_18(prospetto_id),
                                                                       pck_prodis_2012_naz_utils.get_n_cat_prot_in_forz_17_1_00(prospetto_id));

   writeLn('[esegui_calcoli_provinciali] abbattimentoNaz ' || abbattimentoNaz);

    IF (abbattimentoNaz > nScopertureDisabili) THEN
      abbattimentoNaz := nScopertureDisabili;
    END IF;

    abbattPercNaz := pck_prodis_2012_naz_utils.get_abbatt_pc_naz(prospetto_id);
    writeLn('[esegui_calcoli_provinciali] abbattPercNaz ' || abbattPercNaz);

    esuberiArt18Naz := PCK_PRODIS_2012_NAZ_UTILS.get_esuberi_art_18(prospetto_id);
    writeLn('[esegui_calcoli_provinciali] esuberiArt18Naz ' || esuberiArt18Naz);

    nCatProtInForz17100Naz := PCK_PRODIS_2012_NAZ_UTILS.get_n_cat_prot_in_forz_17_1_00(prospetto_id);
    writeLn('[esegui_calcoli_provinciali] nCatProtInForz17100Naz ' || nCatProtInForz17100Naz);

    FOR i IN prospProvTab.FIRST .. prospProvTab.LAST LOOP
      prospProvTab(i).flgSospensioniInCorso := PCK_PRODIS_2012_PROV_UTILS.get_sospensioni_in_corso(prospProvTab(i)
                                                                                                   .idProspettoProv);
      writeLn('[esegui_calcoli_provinciali] prospProvTab(i).flgSospensioniInCorso ' || prospProvTab(i).flgSospensioniInCorso);

      prospProvTab(i).nPosizEsonerate := PCK_PRODIS_2012_PROV_UTILS.get_n_posizioni_esonerate(prospProvTab(i)
                                                                                              .idProspettoProv);
      writeLn('[esegui_calcoli_provinciali] prospProvTab(i).nPosizEsonerate ' || prospProvTab(i).nPosizEsonerate);

      prospProvTab(i).scopertureDisabiliPV_ := PCK_PRODIS_2012_PROV_UTILS.get_scoperture_disabili_pv(
                                            prospProvTab(i).quotaRiservaDisabiliArrot_,
                                            prospProvTab(i).nPosizEsonerate,
                                            prospProvTab(i).nDisabiliInForza);
      writeLn('[esegui_calcoli_provinciali] prospProvTab(i).scopertureDisabiliPV_ ' ||
              prospProvTab(i).scopertureDisabiliPV_);

      prospProvTab(i).nScopertureCatProt := prospProvTab(i).quotaRiservaArt18Arrot_ -
                                            prospProvTab(i).nCatProtInForza;
      writeLn('[esegui_calcoli_provinciali] prospProvTab(i).nScopertureCatProt ' ||
              prospProvTab(i).nScopertureCatProt);

    END LOOP;

    ripristina_ordine(prospProvTab); -- 71441 PRODIS-602
    prospprovtaborder  := order_by(prospProvTab,
                                   'scopertureDisabiliPV_',
                                   'DESC');
    abbattimentoNazTmp := abbattimentoNaz;

    LOOP
      cicloAbbattibile := abbattimentoNazTmp;
      FOR i IN prospprovtaborder.FIRST .. prospprovtaborder.LAST LOOP
        IF (abbattimentoNazTmp > 0 AND abbattPercNaz > 0) THEN
          IF (prospprovtaborder(i)
             .scopertureDisabiliPV_ > 0 AND nCatProtInForz17100Naz > 0 AND
              esuberiArt18Naz > 0) THEN
            abbattibile := PCK_PRODIS_2012_UTILS.round_number((prospprovtaborder(i)
                                                              .scopertureDisabiliPV_ *
                                                               abbattPercNaz) / 100);
            IF (abbattimentoNazTmp >= abbattibile) THEN
              prospprovtaborder(i).nScopertureDisab := prospprovtaborder(i)
                                                      .scopertureDisabiliPV_ -
                                                       abbattibile;
              prospprovtaborder(i).scopertureDisabiliPV_ := prospprovtaborder(i)
                                                           .nScopertureDisab;
              abbattimentoNazTmp := abbattimentoNazTmp - abbattibile;
            ELSE
              prospprovtaborder(i).nScopertureDisab := prospprovtaborder(i)
                                                      .scopertureDisabiliPV_ -
                                                       abbattimentoNazTmp;
              abbattimentoNazTmp := 0;
            END IF;
          ELSIF (prospprovtaborder(i).scopertureDisabiliPV_ < 0) THEN
            prospprovtaborder(i).nScopertureDisab := prospprovtaborder(i)
                                                    .scopertureDisabiliPV_;
          END IF;
        ELSE
          prospprovtaborder(i).nScopertureDisab := prospprovtaborder(i)
                                                  .scopertureDisabiliPV_;
          abbattimentoNazTmp := 0;
        END IF;
        writeLn('[esegui_calcoli_provinciali] prospprovtaborder(i).nScopertureDisab ' ||
                prospprovtaborder(i).nScopertureDisab);
      END LOOP;
      EXIT WHEN abbattimentoNazTmp = 0 OR(abbattimentoNazTmp > 0 AND
                                          cicloAbbattibile =
                                          abbattimentoNazTmp); --mettere condizione d'uscita che consideri anche il caso in cui l'abbattibile e maggiore di 0 e le scoperture delle province sono <= 0
    END LOOP;

    IF (prospProvTabOrder.COUNT > 0) THEN
      prospProvTab := prospProvTabOrder;
    END IF;

    IF (abbattimentoNazTmp > 0 AND cicloAbbattibile = abbattimentoNazTmp) THEN
      ripristina_ordine(prospProvTab); -- 71441 PRODIS-602
      prospprovtaborder := order_by(prospProvTab,
                                    'scopertureDisabiliPV_',
                                    'DESC');
      FOR i IN prospprovtaborder.FIRST .. prospprovtaborder.LAST LOOP
        IF (abbattimentoNazTmp > 0 AND abbattPercNaz > 0) THEN
          IF (prospprovtaborder(i).scopertureDisabiliPV_ > 0 AND
              nCatProtInForz17100Naz > 0 AND
              esuberiArt18Naz > 0) THEN
            abbattibile := prospprovtaborder(i).scopertureDisabiliPV_;
            IF (abbattimentoNazTmp >= abbattibile) THEN
              prospprovtaborder(i).nScopertureDisab := prospprovtaborder(i).scopertureDisabiliPV_ -
                                                       abbattibile;
              prospprovtaborder(i).scopertureDisabiliPV_ := prospprovtaborder(i)
                                                           .nScopertureDisab;
              abbattimentoNazTmp := abbattimentoNazTmp - abbattibile;
            ELSE
              prospprovtaborder(i).nScopertureDisab := prospprovtaborder(i).scopertureDisabiliPV_ -
                                                       abbattimentoNazTmp;
              abbattimentoNazTmp := 0;
            END IF;
          ELSIF (prospprovtaborder(i).scopertureDisabiliPV_ < 0) THEN
            prospprovtaborder(i).nScopertureDisab := prospprovtaborder(i).scopertureDisabiliPV_;
          END IF;
        ELSE
          prospprovtaborder(i).nScopertureDisab := prospprovtaborder(i).scopertureDisabiliPV_;
          abbattimentoNazTmp := 0;
        END IF;
        writeLn('[esegui_calcoli_provinciali] prospprovtaborder(i).nScopertureDisab ' ||
                prospprovtaborder(i).nScopertureDisab);
      END LOOP;
    END IF;
    IF (prospProvTabOrder.COUNT > 0) THEN
      prospProvTab := prospProvTabOrder;
    END IF;

    /*
    FINE : SALVO
    */
    FOR i IN prospProvTab.FIRST .. prospProvTab.LAST LOOP
      SELECT count(1)
        INTO conteggioOccorrenze
        FROM pro_d_riepilogo_provinciale
       WHERE pro_d_riepilogo_provinciale.id_prospetto_prov =
             prospProvTab(i).idProspettoProv;
      writeLn('[esegui_calcoli_provinciali] conteggioOccorrenze ' ||
              conteggioOccorrenze);

      IF (soloScoperture IS NOT NULL AND soloScoperture = '1') THEN
        IF conteggioOccorrenze > 0 THEN
          prospProvTab(i).dAggiorn := sysdate;
          prospProvTab(i).codUserAggiorn := cf_operatore;
          UPDATE pro_d_riepilogo_provinciale
             SET pro_d_riepilogo_provinciale.cod_user_aggiorn              = prospProvTab(i).codUserAggiorn,
                 pro_d_riepilogo_provinciale.d_aggiorn                     = prospProvTab(i).dAggiorn,
                 pro_d_riepilogo_provinciale.NUM_SCOPERTURE_DISABILI_REALI = prospProvTab(i).nScopertureDisab,
                 pro_d_riepilogo_provinciale.NUM_SCOPERTURE_CAT_PROT_REALI = prospProvTab(i).nScopertureCatProt
           WHERE pro_d_riepilogo_provinciale.id_prospetto_prov = prospProvTab(i).idProspettoProv;
        ELSE
          INSERT INTO pro_d_riepilogo_provinciale
            (id_prospetto_prov,
             cod_user_inserim,
             d_inserim,
             NUM_SCOPERTURE_DISABILI_REALI,
             NUM_SCOPERTURE_CAT_PROT_REALI)
          VALUES
            (prospProvTab(i).idProspettoProv,
             cf_operatore,
             sysdate,
             prospProvTab(i).nScopertureDisab,
             prospProvTab(i).nScopertureCatProt);
        END IF;
      ELSE
        SELECT count(1)
          INTO conteggioOccorrenze
          FROM pro_d_riepilogo_provinciale
         WHERE pro_d_riepilogo_provinciale.id_prospetto_prov =
               prospProvTab(i).idProspettoProv;
        writeLn('[esegui_calcoli_provinciali] conteggioOccorrenze ' ||
                conteggioOccorrenze);

        IF conteggioOccorrenze > 0 THEN
          prospProvTab(i).dAggiorn := sysdate;
          prospProvTab(i).codUserAggiorn := cf_operatore;
          UPDATE pro_d_riepilogo_provinciale
             SET pro_d_riepilogo_provinciale.cod_user_aggiorn              = prospProvTab(i).codUserAggiorn,
                 pro_d_riepilogo_provinciale.d_aggiorn                     = prospProvTab(i).dAggiorn,
                 pro_d_riepilogo_provinciale.NUM_DISABILI_IN_FORZA         = prospProvTab(i).nDisabiliInForza,
                 pro_d_riepilogo_provinciale.num_cat_prot_in_forza         = prospProvTab(i).nCatProtInForza,
                 pro_d_riepilogo_provinciale.BASE_COMPUTO_ART_3            = prospProvTab(i).nLavBaseComputoArt3,
                 pro_d_riepilogo_provinciale.BASE_COMPUTO_ART_18           = prospProvTab(i).nLavBaseComputoArt18,
                 pro_d_riepilogo_provinciale.QUOTA_RISERVA_DISABILI        = pck_prodis_2012_utils.round_number(prospProvTab(i).quotaRiservaDisabili),
                 pro_d_riepilogo_provinciale.QUOTA_RISERVA_ART_18          = pck_prodis_2012_utils.round_number(prospProvTab(i).quotaRiservaArt18),
                 pro_d_riepilogo_provinciale.FLG_SOSPENSIONI_IN_CORSO      = prospProvTab(i).flgSospensioniInCorso,
                 pro_d_riepilogo_provinciale.NUM_POSIZIONI_ESONERATE       = prospProvTab(i).nPosizEsonerate,
                 pro_d_riepilogo_provinciale.NUM_SCOPERTURE_DISABILI_REALI = prospProvTab(i).nScopertureDisab,
                 pro_d_riepilogo_provinciale.NUM_SCOPERTURE_DISABILI       = PCK_PRODIS_2012_UTILS.handle_number(prospProvTab(i)
                                                                                                                 .nScopertureDisab),
                 pro_d_riepilogo_provinciale.NUM_SCOPERTURE_CAT_PROT_REALI = prospProvTab(i).nScopertureCatProt,
                 pro_d_riepilogo_provinciale.NUM_SCOPERTURE_CAT_PROT       = PCK_PRODIS_2012_UTILS.handle_number(prospProvTab(i)
                                                                                                                 .nScopertureCatProt)
           WHERE pro_d_riepilogo_provinciale.id_prospetto_prov = prospProvTab(i).idProspettoProv;
        ELSE
          riepilogoProvincialeROW.id_prospetto_prov             := prospProvTab(i).idProspettoProv;
          riepilogoProvincialeROW.cod_user_inserim              := cf_operatore;
          riepilogoProvincialeROW.d_inserim                     := sysdate;
          riepilogoProvincialeROW.NUM_DISABILI_IN_FORZA         := prospProvTab(i).nDisabiliInForza;
          riepilogoProvincialeROW.num_cat_prot_in_forza         := prospProvTab(i).nCatProtInForza;
          riepilogoProvincialeROW.BASE_COMPUTO_ART_3            := prospProvTab(i).nLavBaseComputoArt3;
          riepilogoProvincialeROW.BASE_COMPUTO_ART_18           := prospProvTab(i).nLavBaseComputoArt18;
          riepilogoProvincialeROW.QUOTA_RISERVA_DISABILI        := prospProvTab(i).quotaRiservaDisabili;
          riepilogoProvincialeROW.QUOTA_RISERVA_ART_18          := prospProvTab(i).quotaRiservaArt18;
          riepilogoProvincialeROW.FLG_SOSPENSIONI_IN_CORSO      := prospProvTab(i).flgSospensioniInCorso;
          riepilogoProvincialeROW.NUM_POSIZIONI_ESONERATE       := prospProvTab(i).nPosizEsonerate;
          riepilogoProvincialeROW.NUM_SCOPERTURE_DISABILI_REALI := prospProvTab(i).nScopertureDisab;
          riepilogoProvincialeROW.NUM_SCOPERTURE_DISABILI       := PCK_PRODIS_2012_UTILS.handle_number(prospProvTab(i)
                                                                                                       .nScopertureDisab);
          riepilogoProvincialeROW.NUM_SCOPERTURE_CAT_PROT_REALI := prospProvTab(i).nScopertureCatProt;
          riepilogoProvincialeROW.NUM_SCOPERTURE_CAT_PROT       := PCK_PRODIS_2012_UTILS.handle_number(prospProvTab(i)
                                                                                                       .nScopertureCatProt);
          INSERT INTO pro_d_riepilogo_provinciale
          VALUES riepilogoProvincialeROW;
        END IF;
      END IF;

    END LOOP;

    esito := 1;

  EXCEPTION

    WHEN e_base_computo_negativa THEN
      dbms_output.put_line('Errore: base computo negativa su almeno una provincia. ERRORE : ' ||
                           SUBSTR(SQLERRM, 1, 110) || '  SQLCODE=' ||
                           TO_CHAR(SQLCODE));
      esito := -202;

    WHEN e_categoria_azienda THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      esito := -101;

    WHEN e_spalmabile THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      esito := -201;

    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      esito := -200;

  END esegui_calcoli_provinciali;

  PROCEDURE esegui_compensazioni(prospetto_id IN NUMBER,
                                 cf_operatore IN VARCHAR2,
                                 esito        OUT NUMBER,
                                 messaggio    OUT VARCHAR2) IS
    prospettoProvinciaROW pro_r_prospetto_provincia%ROWTYPE;
    CURSOR prospettoProvinciaCURS(prospetto_id IN NUMBER) IS
      SELECT pro_r_prospetto_provincia.*
        FROM pro_r_prospetto_provincia -- 71441 PRODIS-602
       WHERE pro_r_prospetto_provincia.id_prospetto = prospetto_id
       ORDER BY pro_r_prospetto_provincia.id_t_provincia;

    idProspettoProv NUMBER := 0;

    catCompensazioneDisabPV   CHAR := '';
    nCompensazDisabPV         NUMBER := 0;
    catCompensazioneCatProtPV CHAR := '';
    nCompensazCatProtPV       NUMBER := 0;
    nScopertureDisabPV        NUMBER := 0;
    nScopertureCatProtPV      NUMBER := 0;

    numEccedenzeDisabInterGruppo   NUMBER := 0;
    numRiduzioniDisabIntergruppo   NUMBER := 0;
    numEccedenzeCatProtIntergruppo NUMBER := 0;
    numRiduzioniCatProtIntergruppo NUMBER := 0;

    nScopertureDisabNaz   NUMBER := 0;
    nScopertureCatProtNaz NUMBER := 0;

    e_categoria_compens_disab EXCEPTION;
    e_categoria_compens_cat_prot EXCEPTION;

  BEGIN
    OPEN prospettoProvinciaCURS(prospetto_id);
    LOOP
      FETCH prospettoProvinciaCURS
        INTO prospettoProvinciaROW;
      EXIT WHEN prospettoProvinciaCURS%NOTFOUND;

      idProspettoProv := prospettoProvinciaROW.id_prospetto_prov;
      writeLn('[esegui_compensazioni] idProspettoProv ' || idProspettoProv);

      /*
      I. si calcolano i totali delle compensazioni provinciali, se presenti e si assegnano a ciascun riepilogo provinciale:
      */

      /*
      Categoria compensazione disabili_PV -> PRO_D_RIEPILOGO_PROVINCIALE.CAT_COMPENSAZIONE_DISABILI
      Si mette E o R, in base alla distinct(PRO_D_PROV_COMPENSAZIONI.CATEGORIA_COMPENSAZIONE) per CATEGORIA_SOGGETTO = 'D'
      */
      BEGIN
        catCompensazioneDisabPV := PCK_PRODIS_2012_COMP_UTILS.get_cat_compens_disab_pv(idProspettoProv);
        writeLn('[esegui_compensazioni] catCompensazioneDisabPV ' ||
                catCompensazioneDisabPV);
      EXCEPTION
        WHEN OTHERS THEN
          RAISE e_categoria_compens_disab;
      END;
      /*
      N? Compensazione disabili_PV -> PRO_D_RIEPILOGO_PROVINCIALE.NUM_COMPENSAZIONE_DISABILI
      valore calcolato automaticamente con la somma delle compensazioni con CATEGORIA_SOGGETTO = 'D' per il 'Quadro 2'.
      */
      nCompensazDisabPV := PCK_PRODIS_2012_COMP_UTILS.get_n_compen_disab_pv(idProspettoProv);
      writeLn('[esegui_compensazioni] nCompensazDisabPV ' || nCompensazDisabPV);

      /*
      Categoria compensazione categorie protette_PV -> PRO_D_RIEPILOGO_PROVINCIALE.CAT_COMPENSAZIONE_CATE_PROT
      Si mette E o R, in base alla distinct(PRO_D_PROV_COMPENSAZIONI.CATEGORIA_COMPENSAZIONE) per CATEGORIA_SOGGETTO = 'C'
      */
      BEGIN
        catCompensazioneCatProtPV := PCK_PRODIS_2012_COMP_UTILS.get_cat_compens_catprot_pv(idProspettoProv);
        writeLn('[esegui_compensazioni] catCompensazioneCatProtPV ' || catCompensazioneCatProtPV);
      EXCEPTION
        WHEN OTHERS THEN
          RAISE e_categoria_compens_cat_prot;
      END;
      /*
      N? Compensazioni categorie protette_PV -> PRO_D_RIEPILOGO_PROVINCIALE.NUM_COMPENSAZIONI_CATE_PROT
      valore calcolato automaticamente con la somma delle compensazioni con CATEGORIA_SOGGETTO = 'C' per il 'Quadro 2'.
      */
      nCompensazCatProtPV := PCK_PRODIS_2012_COMP_UTILS.get_n_compen_catprot_pv(idProspettoProv);
      writeLn('[esegui_compensazioni] nCompensazCatProtPV ' || nCompensazCatProtPV);

      /*
      N? Scoperture disabili (L.68/99 art.1)_PV -> PRO_D_RIEPILOGO_PROVINCIALE.NUM_SCOPERT_DISABILI
      Se 'Categoria compensazione disabili_PV' NOT NULL,
      al valore 'N? Scoperture disabili (L.68/99 art.1)_PV' calcolato precedentemente, al punto 2)VI.,
      si aggiungono o detraggono le compensazioni nel seguente modo:
      a) Se 'Categoria compensazione disabili_PV' = 'E', si somma il valore di 'N? Compensazione disabili_PV';
      b) Se 'Categoria compensazione disabili_PV' = 'R', si detrae il valore di 'N? Compensazione disabili_PV'.
      */
      nScopertureDisabPV := PCK_PRODIS_2012_COMP_UTILS.get_n_scopert_disab_pv(idProspettoProv);
      writeLn('[esegui_compensazioni] nScopertureDisabPV ' || nScopertureDisabPV);

      /*
      N? Scoperture categorie protette (L.68/99 art.18)_PV -> PRO_D_RIEPILOGO_PROVINCIALE.NUM_SCOPERT_CATEGORIE_PROTETTE
      Se 'Categoria compensazione categorie protette_PV' NOT NULL,
      al valore 'N? Scoperture categorie protette (L.68/99 art.18)_PV' calcolato precedentemente, al punto 2)VI.,
      si aggiungono o detraggono le compensazioni nel seguente modo:
      a) Se 'Categoria compensazione categorie protette_PV' = 'E', si somma il valore di 'N? Compensazione categorie protette_PV';
      b) Se 'Categoria compensazione categorie protette_PV' = 'R', si detrae il valore di 'N? Compensazione categorie protette_PV'.
      */
      nScopertureCatProtPV := PCK_PRODIS_2012_COMP_UTILS.get_n_scopert_catprot_pv(idProspettoProv);
      writeLn('[esegui_compensazioni] nScopertureCatProtPV ' || nScopertureCatProtPV);

      UPDATE pro_d_riepilogo_provinciale
         SET pro_d_riepilogo_provinciale.d_aggiorn                   = sysdate,
             pro_d_riepilogo_provinciale.cod_user_aggiorn            = cf_operatore,
             PRO_D_RIEPILOGO_PROVINCIALE.CAT_COMPENSAZIONE_DISABILI  = catCompensazioneDisabPV,
             PRO_D_RIEPILOGO_PROVINCIALE.NUM_COMPENSAZIONE_DISABILI  = nCompensazDisabPV,
             PRO_D_RIEPILOGO_PROVINCIALE.CAT_COMPENSAZIONE_CATE_PROT = catCompensazioneCatProtPV,
             PRO_D_RIEPILOGO_PROVINCIALE.NUM_COMPENSAZIONI_CATE_PROT = nCompensazCatProtPV,
             PRO_D_RIEPILOGO_PROVINCIALE.NUM_SCOPERTURE_DISABILI     = PCK_PRODIS_2012_UTILS.handle_number(nScopertureDisabPV),
             PRO_D_RIEPILOGO_PROVINCIALE.NUM_SCOPERTURE_CAT_PROT     = PCK_PRODIS_2012_UTILS.handle_number(nScopertureCatProtPV)
       WHERE pro_d_riepilogo_provinciale.id_prospetto_prov =
             idProspettoProv;

    END LOOP;
    CLOSE prospettoProvinciaCURS;

    /*
    II. eventuali compensazioni provinciali intergruppo si assegnano al riepilogo nazionale:
    */

    /*
    NumEccedenzeDisabiliIntergruppo
    Si sommano tutte le compensazioni con CATEGORIA_SOGGETTO = 'D' estratte da tutte le compensazioni
    di tipo 'E' (Eccedenza) di tutti i 'Quadri 2', dove il campo CF_AZIENDA_APPARTEN_AL_GRUPPO e valorizzato.
    */
    numEccedenzeDisabInterGruppo := pck_prodis_2012_comp_utils.get_n_ecced_disab_itergruppo(prospetto_id);
    writeLn('[esegui_compensazioni] numEccedenzeDisabInterGruppo ' || numEccedenzeDisabInterGruppo);

    /*
    NumRiduzioniDisabiliIntergruppo
    Si sommano tutte le compensazioni con CATEGORIA_SOGGETTO = 'D' estratte da tutte le compensazioni
    di tipo 'R' (Riduzione) di tutti i 'Quadri 2', dove il campo CF_AZIENDA_APPARTEN_AL_GRUPPO e valorizzato.
    */
    numRiduzioniDisabIntergruppo := pck_prodis_2012_comp_utils.get_n_riduz_disab_itergruppo(prospetto_id);
    writeLn('[esegui_compensazioni] numRiduzioniDisabIntergruppo ' || numRiduzioniDisabIntergruppo);

    /*
    NumEccedenzeCatProtetteIntergruppo
    Si sommano tutte le compensazioni con CATEGORIA_SOGGETTO = 'C' estratte da tutte le compensazioni
    di tipo 'E' (Eccedenza) di tutti i 'Quadri 2', dove il campo CF_AZIENDA_APPARTEN_AL_GRUPPO e valorizzato.
    */
    numEccedenzeCatProtIntergruppo := pck_prodis_2012_comp_utils.get_n_ecced_catprot_itergruppo(prospetto_id);
    writeLn('[esegui_compensazioni] numEccedenzeCatProtIntergruppo ' || numEccedenzeCatProtIntergruppo);

    /*
    NumRiduzioniCatProtetteIntergruppo
    Si sommano tutte le compensazioni con CATEGORIA_SOGGETTO = 'R' estratte da tutte le compensazioni
    di tipo 'R' (Riduzione) di tutti i 'Quadri 2', dove il campo CF_AZIENDA_APPARTEN_AL_GRUPPO e valorizzato.
    */
    numRiduzioniCatProtIntergruppo := pck_prodis_2012_comp_utils.get_n_riduz_catprot_itergruppo(prospetto_id);
    writeLn('[esegui_compensazioni] numRiduzioniCatProtIntergruppo ' || numRiduzioniCatProtIntergruppo);

    /*
    N? Scoperture disabili (L.68/99 art.1) -> PRO_D_RIEPILOGO_NAZIONALE.NUM_SCOPERT_DISABILI
    Al valore 'N? Scoperture disabili (L.68/99 art.1)' calcolato precedentemente, al punto 1)V.,
    si aggiungono  'NumEccedenzeDisabiliIntergruppo'
    e si detraggono 'NumRiduzioniDisabiliIntergruppo'.
    */
    nScopertureDisabNaz := pck_prodis_2012_comp_utils.get_n_scopert_disab_naz(prospetto_id,
                                                                              numEccedenzeDisabInterGruppo,
                                                                              numRiduzioniDisabIntergruppo);
    writeLn('[esegui_compensazioni] nScopertureDisabNaz ' || nScopertureDisabNaz);

    /*
    N? Scoperture categorie protette (L.68/99 art.18) -> PRO_D_RIEPILOGO_NAZIONALE.NUM_SCOPERT_CATEGORIE_PROTETTE
    Al valore 'N? Scoperture categorie protette (L.68/99 art.18)' calcolato precedentemente, al punto 1)V.,
    si aggiungono  'NumEccedenzeCatProtetteIntergruppo'
    e si detraggono 'NumRiduzioniCatProtetteIntergruppo'.
    */
    nScopertureCatProtNaz := pck_prodis_2012_comp_utils.get_n_scopert_catprot_naz(prospetto_id,
                                                                                  numeccedenzecatprotintergruppo,
                                                                                  numriduzionicatprotintergruppo);
    writeLn('[esegui_compensazioni] nScopertureCatProtNaz ' || nScopertureCatProtNaz);

    UPDATE PRO_D_RIEPILOGO_NAZIONALE
       SET pro_d_riepilogo_nazionale.d_aggiorn                      = sysdate,
           pro_d_riepilogo_nazionale.cod_user_aggiorn               = cf_operatore,
           pro_d_riepilogo_nazionale.num_scopert_disabili           = PCK_PRODIS_2012_UTILS.handle_number(nScopertureDisabNaz),
           pro_d_riepilogo_nazionale.num_scopert_categorie_protette = PCK_PRODIS_2012_UTILS.handle_number(nScopertureCatProtNaz)
     WHERE pro_d_riepilogo_nazionale.id_prospetto = prospetto_id;

    esito := 1;

  EXCEPTION
    WHEN e_categoria_compens_disab THEN
      dbms_output.put_line('ERRORE : errore occorso durante il calcolo della categoria compensazione per i disabili.');
      esito     := -301;
      messaggio := 'Errore durante l''esecuzione dei calcoli sulle compensazioni. Non e'' stato possibile determinare la categoria compensazione disabili.';
    WHEN e_categoria_compens_cat_prot THEN
      dbms_output.put_line('ERRORE : errore occorso durante il calcolo della categoria compensazione per le categorie protette.');
      esito     := -302;
      messaggio := 'Errore durante l''esecuzione dei calcoli sulle compensazioni. Non e'' stato possibile determinare la categoria compensazione categorie protette.';
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      esito     := -300;
      messaggio := 'Errore generico durante l''esecuzione dei calcoli sulle compensazioni. ERRORE : ' ||
                   SUBSTR(SQLERRM, 1, 110) || '  SQLCODE=' ||
                   TO_CHAR(SQLCODE);

  END esegui_compensazioni;

  PROCEDURE esegui_calcoli(prospetto_id   IN NUMBER,
                           cf_operatore   IN VARCHAR2,
                           soloScoperture IN VARCHAR2,
                           esito          OUT NUMBER,
                           messaggio      OUT VARCHAR2) IS

    e_calcoli_nazionali EXCEPTION;
    e_calcoli_provinciali EXCEPTION;
    e_calcoli_compensazioni EXCEPTION;

    esito_calcoli_nazionali     NUMBER := -100;
    esito_calcoli_provinciali   NUMBER := -200;
    esito_calcoli_compensazioni NUMBER := -300;
    msg_calcoli_compensazioni   VARCHAR2(200) := '';
  BEGIN

    esegui_calcoli_nazionali(prospetto_id,
                             cf_operatore,
                             soloScoperture,
                             esito_calcoli_nazionali);
    writeLn('[esegui_calcoli] calcoli nazionali eseguiti, esito = ' || esito_calcoli_nazionali);
    IF (esito_calcoli_nazionali < 0) THEN
      RAISE e_calcoli_nazionali;
    END IF;

    esegui_calcoli_provinciali(prospetto_id,
                               cf_operatore,
                               soloScoperture,
                               esito_calcoli_provinciali);
    writeLn('[esegui_calcoli] calcoli provinciali eseguiti, esito = ' || esito_calcoli_provinciali);
    IF (esito_calcoli_provinciali < 0) THEN
      RAISE e_calcoli_provinciali;
    END IF;

    IF (soloScoperture IS NULL OR soloScoperture = '0') THEN
      esegui_compensazioni(prospetto_id,
                           cf_operatore,
                           esito_calcoli_compensazioni,
                           msg_calcoli_compensazioni);
      writeLn('[esegui_calcoli] compensazioni eseguite, esito = ' ||
              esito_calcoli_compensazioni);
      IF (esito_calcoli_compensazioni < 0) THEN
        RAISE e_calcoli_compensazioni;
      END IF;
    END IF;

    esito := 1;

  EXCEPTION
    WHEN e_calcoli_nazionali THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      esito := esito_calcoli_nazionali;
      IF (esito = -100) THEN
        messaggio := 'Errore generico durante l''esecuzione dei calcoli nazionali.';
      ELSIF (esito = -101) THEN
        messaggio := 'Errore. Non e'' stato possibile determinare la categoria azienda, se la base di computo e minore di 15 dipendenti, il prospetto non deve essere presentato.';
      END IF;

    WHEN e_calcoli_provinciali THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      esito := esito_calcoli_provinciali;
      IF (esito = -200) THEN
        messaggio := 'Errore generico durante l''esecuzione dei calcoli provinciali.';
      ELSIF (esito = -201) THEN
        messaggio := 'Errore generico durante l''esecuzione dei calcoli provinciali, impossibile eliminare lo spalmabile durante il calcolo della pre base computo disabili provinciale.';
      ELSIF (esito = -202) THEN
        messaggio := 'Errore: base computo negativa su almeno una provincia';
      END IF;

    WHEN e_calcoli_compensazioni THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      esito := esito_calcoli_compensazioni;
      IF (esito = -300) THEN
        messaggio := 'Errore generico durante l''esecuzione dei calcoli sulle compensazioni.';
      ELSIF (esito = -301) THEN
        messaggio := 'Errore durante l''esecuzione dei calcoli sulle compensazioni. Non e'' stato possibile determinare la categoria compensazione disabili.';
      ELSIF (esito = -302) THEN
        messaggio := 'Errore durante l''esecuzione dei calcoli sulle compensazioni. Non e'' stato possibile determinare la categoria compensazione categorie protette.';
      END IF;

    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      esito     := -1;
      messaggio := 'ERRORE : ' || SUBSTR(SQLERRM, 1, 110) || '  SQLCODE=' ||
                   TO_CHAR(SQLCODE);

  END esegui_calcoli;

  PROCEDURE esegui_operazione(prospetto_id         IN NUMBER,
                              cf_operatore         IN VARCHAR2,
                              cf_studio_professionale   IN VARCHAR2,
                              id_t_soggetti        IN NUMBER,
                              tipoOperazione       IN VARCHAR2,
                              esito                OUT NUMBER,
                              messaggio            OUT VARCHAR2,
                              newIdProspetto       OUT NUMBER) IS

    newIdProspettoProv NUMBER := 0;

    proDProspettoROW       pro_d_prospetto%ROWTYPE;
    proDDatiProvincialiROW pro_d_dati_provinciali%ROWTYPE;

    cfsubstring VARCHAR2(16) := '';
    annoCf      VARCHAR2(2) := '';
    annoNascita VARCHAR2(2) := '';

    idStatoEsteroNascitaLavoratore NUMBER := 0;
    idComuneNascitaLavoratore      NUMBER := 0;

    idtcontratto               NUMBER := 0;
    idtqualificaprofessionalei NUMBER := 0;
    idtassunzioneprotetta      NUMBER := 0;

    e_generic EXCEPTION;
    e_tipo_operazione EXCEPTION;

    controllo_cf          NUMBER;
    v_codice_comune       VARCHAR(4 CHAR);
    dt_nascita_lavoratore VARCHAR(11 CHAR);

    bc_art_3_naa  NUMBER; --PRODIS-561
    bc_art_18_naa NUMBER; --PRODIS-561

    formacontratto VARCHAR2(1) := '';

  BEGIN

    /*
    inserimento in pro_d_prospetto
    */
    SELECT *
      INTO proDProspettoROW
      FROM pro_d_prospetto
     WHERE id_prospetto = prospetto_id;
    SELECT seq_d_prospetto.nextval
      INTO proDProspettoROW.id_prospetto
      FROM dual;
    IF (tipoOperazione = 'RETTIFICA') THEN
      proDProspettoROW.id_t_stato_prospetto         := 1; --BOZZA
      proDProspettoROW.id_t_comunicazione           := 2; --RETTIFICA
      proDProspettoROW.id_prospetto_precedente      := prospetto_id;
      proDProspettoROW.codice_comunicazione_preced  := proDProspettoROW.codice_comunicazione;
      proDProspettoROW.email_soggetto_comunicazione := NULL;
      proDProspettoROW.email_notifica               := NULL;
    ELSIF (tipoOperazione = 'ANNULLAMENTO') THEN
      proDProspettoROW.id_t_stato_prospetto         := 2; --DA FIRMARE
      proDProspettoROW.id_t_comunicazione           := 3; --ANNULLAMENTO
      proDProspettoROW.id_prospetto_precedente      := prospetto_id;
      proDProspettoROW.codice_comunicazione_preced  := proDProspettoROW.codice_comunicazione;
      proDProspettoROW.email_soggetto_comunicazione := proDProspettoROW.email_soggetto_comunicazione;
      proDProspettoROW.email_notifica               := proDProspettoROW.email_notifica;
    ELSIF (tipoOperazione = 'DUPLICAZIONE') THEN
      proDProspettoROW.id_t_stato_prospetto         := 1; --BOZZA
      proDProspettoROW.id_t_comunicazione           := 1; --PROSPETTO INFORMATIVO
      proDProspettoROW.id_prospetto_precedente      := NULL;
      proDProspettoROW.codice_comunicazione_preced  := NULL;
      proDProspettoROW.flg_sospensione_per_mobilita := NULL;
      proDProspettoROW.data_riferimento_prospetto   := NULL;
      proDProspettoROW.flg_confermato_q1            := 'N';
      proDProspettoROW.tipo_provenienza             := 'P';
      proDProspettoROW.email_soggetto_comunicazione := NULL;
      proDProspettoROW.email_notifica               := NULL;
      proDProspettoROW.D_FINE_SOSPENSIONE_Q1        := NULL;
      --PRODIS-600
      proDProspettoROW.CF_STUDIO_PROFESSIONALE      := cf_studio_professionale;
      proDProspettoROW.ID_T_SOGGETTI                := id_t_soggetti;
      proDProspettoROW.DATA_TIMBRO_POSTALE          := NULL;
      proDProspettoROW.FLG_VISITA_ISPETTIVA         := 'N';

      --PRODIS-561
      SELECT base_computo_art_3
        INTO bc_art_3_naa
        FROM PRO_D_RIEPILOGO_NAZIONALE
       WHERE id_prospetto = prospetto_id;
      SELECT base_computo_art_18
        INTO bc_art_18_naa
        FROM PRO_D_RIEPILOGO_NAZIONALE
       WHERE id_prospetto = prospetto_id;
      IF bc_art_3_naa IS NOT NULL AND bc_art_3_naa < 15 AND
         bc_art_18_naa IS NOT NULL AND bc_art_18_naa < 15 THEN
        proDProspettoROW.flg_nessuna_assunzione_aggiun := NULL; --PRODIS-561
        proDProspettoROW.DATA_PRIMA_ASSUNZIONE         := NULL; --71862 - Controlli aggiunti a seguito dei test eseguiti da A.Bianco in data 27/12/2013
        proDProspettoROW.DATA_SECONDA_ASSUNZIONE       := NULL; --71862 - Controlli aggiunti a seguito dei test eseguiti da A.Bianco in data 27/12/2013
      END IF;
      --PRODIS-561

    ELSE
      RAISE e_tipo_operazione;
    END IF;

    proDProspettoROW.anno_protocollo      := NULL;
    proDProspettoROW.numero_protocollo    := NULL;
    proDProspettoROW.data_protocollo      := NULL;
    proDProspettoROW.data_timbro_postale  := NULL;
    proDProspettoROW.data_invio           := NULL;
    proDProspettoROW.cf_comunicazione     := NULL;
    proDProspettoROW.flg_invio_ministero  := NULL;
    proDProspettoROW.codice_comunicazione := NULL;

    proDProspettoROW.cod_user_inserim := cf_operatore;
    proDProspettoROW.d_inserim        := sysdate;

    INSERT INTO pro_d_prospetto VALUES proDProspettoROW;

    IF (tipoOperazione = 'RETTIFICA') THEN
      UPDATE pro_d_prospetto
         SET id_t_stato_prospetto = 9
       WHERE id_prospetto = prospetto_id;
    ELSIF (tipoOperazione = 'ANNULLAMENTO') THEN
      UPDATE pro_d_prospetto
         SET id_t_stato_prospetto = 12
       WHERE id_prospetto = prospetto_id;
    END IF;

    SELECT seq_d_prospetto.currval INTO newIdProspetto FROM dual;
    dbms_output.put_line('new_Prosp: ' || newIdProspetto);

    /*
    inserimento in pro_d_prospetto_gradualita
    */
    INSERT INTO pro_d_prospetto_gradualita
      (id_prospetto,
       data_atto,
       estremi_atto,
       n_assunzioni_lav_pre_trasf,
       data_trasformazione,
       percentuale,
       cod_user_inserim,
       d_inserim,
       cod_user_aggiorn,
       d_aggiorn)
      (SELECT newIdProspetto,
              gr.data_atto,
              gr.estremi_atto,
              gr.n_assunzioni_lav_pre_trasf,
              gr.data_trasformazione,
              gr.percentuale,
              cf_operatore,
              sysdate,
              '',
              ''
         FROM pro_d_prospetto_gradualita gr
        WHERE gr.id_prospetto = prospetto_id);

    /*
    inserimento in pro_d_dati_azienda
    */
    INSERT INTO pro_d_dati_azienda
      (id_prospetto,
       id_t_dichiarante,
       cf_azienda,
       denominazione_datore_lavoro,
       id_t_atecofin,
       id_t_ccnl,
       cf_referente,
       cognome_referente,
       nome_referente,
       indirizzo_referente,
       id_t_comune,
       /*id_t_stati_esteri, 71862 - PRODIS-526*/
       cap_referente,
       telefono_referente,
       fax_referente,
       email_referente,
       cod_user_inserim,
       d_inserim,
       cod_user_aggiorn,
       d_aggiorn,
       flg_prospetto_da_capogruppo,
       cf_capogruppo,
       FLG_CAPOGRUPPO_ESTERO)
      (SELECT newIdProspetto,
              az.id_t_dichiarante,
              trim(az.cf_azienda),
              az.denominazione_datore_lavoro,
              az.id_t_atecofin,
              az.id_t_ccnl,
              trim(az.cf_referente),
              az.cognome_referente,
              az.nome_referente,
              az.indirizzo_referente,
              az.id_t_comune,
              /*az.id_t_stati_esteri, 71862 - PRODIS-526*/
              az.cap_referente,
              az.telefono_referente,
              az.fax_referente,
              az.email_referente,
              cf_operatore,
              sysdate,
              '',
              '',
              az.flg_prospetto_da_capogruppo,
              trim(az.cf_capogruppo),
              az.FLG_CAPOGRUPPO_ESTERO
         FROM pro_d_dati_azienda az
        WHERE az.id_prospetto = prospetto_id);

    /*
    inserimento in pro_d_sede_legale
    */
    INSERT INTO pro_d_sede_legale
      (id_prospetto,
       id_t_comune,
       id_t_stati_esteri,
       cap_sede,
       indirizzo,
       telefono,
       fax,
       email,
       cod_user_inserim,
       d_inserim,
       cod_user_aggiorn,
       d_aggiorn)
      (SELECT newIdProspetto,
              sl.id_t_comune,
              sl.id_t_stati_esteri,
              sl.cap_sede,
              sl.indirizzo,
              sl.telefono,
              sl.fax,
              sl.email,
              cf_operatore,
              sl.d_inserim,
              '',
              ''
         FROM pro_d_sede_legale sl
        WHERE sl.id_prospetto = prospetto_id);

    IF (tipoOperazione = 'ANNULLAMENTO') THEN
      FOR pdRiepilogoNazCurs IN (SELECT *
                                   FROM PRO_D_RIEPILOGO_NAZIONALE
                                  WHERE id_prospetto = prospetto_id) LOOP
        pdRiepilogoNazCurs.id_prospetto     := newIdProspetto;
        pdRiepilogoNazCurs.cod_user_inserim := cf_operatore;
        pdRiepilogoNazCurs.d_inserim        := sysdate;
        INSERT INTO PRO_D_RIEPILOGO_NAZIONALE VALUES pdRiepilogoNazCurs;
      END LOOP;
    END IF;

    FOR pv_prosp IN (SELECT rp.*
                       FROM pro_r_prospetto_provincia rp
                      WHERE rp.id_prospetto = prospetto_id
                   ORDER BY rp.id_t_provincia)  
    LOOP

      /*
      inserimento in pro_r_prospetto_provincia
      */
      IF (tipoOperazione = 'DUPLICAZIONE') THEN
        INSERT INTO pro_r_prospetto_provincia
          (id_prospetto_prov,
           id_prospetto,
           id_t_provincia,
           cod_user_inserim,
           d_inserim,
           cod_user_aggiorn,
           d_aggiorn,
           flg_confermato_q2)
          (SELECT seq_r_prospetto_provincia.nextval,
                  newIdProspetto,
                  rpv.id_t_provincia,
                  cf_operatore,
                  sysdate,
                  '',
                  '',
                  'N'
             FROM pro_r_prospetto_provincia rpv
            WHERE rpv.id_prospetto_prov = pv_prosp.id_prospetto_prov);
      ELSE
        INSERT INTO pro_r_prospetto_provincia
          (id_prospetto_prov,
           id_prospetto,
           id_t_provincia,
           cod_user_inserim,
           d_inserim,
           cod_user_aggiorn,
           d_aggiorn,
           flg_confermato_q2)
          (SELECT seq_r_prospetto_provincia.nextval,
                  newIdProspetto,
                  rpv.id_t_provincia,
                  cf_operatore,
                  sysdate,
                  '',
                  '',
                  rpv.flg_confermato_q2
             FROM pro_r_prospetto_provincia rpv
            WHERE rpv.id_prospetto_prov = pv_prosp.id_prospetto_prov);
      END IF;

      SELECT seq_r_prospetto_provincia.currval
        INTO newIdProspettoProv
        FROM dual;
      dbms_output.put_line('new_PVprosp: ' || newIdProspettoProv);

      IF (tipoOperazione = 'ANNULLAMENTO') THEN
        FOR pdRiepilogoProvCurs IN (SELECT *
                                      FROM PRO_D_RIEPILOGO_PROVINCIALE
                                     WHERE id_prospetto_prov =
                                           pv_prosp.id_prospetto_prov) LOOP
          pdRiepilogoProvCurs.id_prospetto_prov := newIdProspettoProv;
          pdRiepilogoProvCurs.cod_user_inserim  := cf_operatore;
          pdRiepilogoProvCurs.d_inserim         := sysdate;
          INSERT INTO PRO_D_RIEPILOGO_PROVINCIALE
          VALUES pdRiepilogoProvCurs;
        END LOOP;
      END IF;

      /*
      inserimento in pro_d_dati_provinciali
      */
      IF (tipoOperazione = 'DUPLICAZIONE') THEN
        INSERT INTO pro_d_dati_provinciali
          (id_prospetto_prov,
           n_posti_prev_centrali_nonved,
           n_posti_prev_massofis_nonved,
           n_totale_lavorat_dipendenti,
           n_disabili_in_forza,
           n_central_telefo_nonvedenti,
           n_terariab_massofis_nonved,
           flg_categorie_escluse_compute,
           flg_dettaglio_partime,
           n_partime_riproporzionati,
           flg_dettaglio_intermittenti,
           n_intermittenti_riproporziona,
           n_cate_prot_forza,
           n_cate_prot_forza_cnt_dis,
           n_cate_prot_forza_esubero,
           base_computo,
           flg_lavoratori_in_forza,
           flg_posti_lavoro_disponibili,
           flg_compensazioni_territorial,
           flg_azi_sosp,
           flg_azi_gradu,
           flg_azi_esonero_parziale,
           flg_azi_con_convenzione,
           note,
           cod_user_inserim,
           d_inserim,
           cod_user_aggiorn,
           d_aggiorn,
           n_cate_prot_forza_a_17_01_2000,
           n_telelavoro_ft,            
           n_somministrati_ft,         
           n_convenzioni_12bis_14_ft   
           )
          (SELECT newIdProspettoProv,
                  pvd.n_posti_prev_centrali_nonved,
                  pvd.n_posti_prev_massofis_nonved,
                  pvd.n_totale_lavorat_dipendenti,
                  pvd.n_disabili_in_forza,
                  pvd.n_central_telefo_nonvedenti,
                  pvd.n_terariab_massofis_nonved,
                  pvd.flg_categorie_escluse_compute,
                  pvd.flg_dettaglio_partime,
                  '',
                  pvd.flg_dettaglio_intermittenti,
                  '',
                  pvd.n_cate_prot_forza,
                  '',
                  '',
                  '',
                  pvd.flg_lavoratori_in_forza,
                  pvd.flg_posti_lavoro_disponibili,
                  pvd.flg_compensazioni_territorial,
                  pvd.flg_azi_sosp,
                  pvd.flg_azi_gradu,
                  pvd.flg_azi_esonero_parziale,
                  pvd.flg_azi_con_convenzione,
                  pvd.note,
                  cf_operatore,
                  sysdate,
                  '',
                  '',
                  pvd.n_cate_prot_forza_a_17_01_2000,
                  pvd.n_telelavoro_ft,          
                  pvd.n_somministrati_ft,       
                  pvd.n_convenzioni_12bis_14_ft 
             FROM pro_d_dati_provinciali pvd
            WHERE pvd.id_prospetto_prov = pv_prosp.id_prospetto_prov);
      ELSE
        SELECT *
          INTO proDDatiProvincialiROW
          FROM pro_d_dati_provinciali
         WHERE id_prospetto_prov = pv_prosp.id_prospetto_prov;
        proDDatiProvincialiROW.id_prospetto_prov := newIdProspettoProv;
        proDDatiProvincialiROW.cod_user_inserim  := cf_operatore;
        proDDatiProvincialiROW.d_inserim         := sysdate;
        INSERT INTO pro_d_dati_provinciali VALUES proDDatiProvincialiROW;
      END IF;

      /*
      inserimento in pro_d_prospetto_prov_sede
      */
      INSERT INTO pro_d_prospetto_prov_sede
        (id_prospetto_prov,
         id_t_comune,
         cap,
         indirizzo,
         telefono,
         fax,
         email,
         cognome_referente,
         nome_referente,
         cod_user_inserim,
         d_inserim,
         cod_user_aggiorn,
         d_aggiorn)
        (SELECT newIdProspettoProv,
                pvs.id_t_comune,
                pvs.cap,
                pvs.indirizzo,
                pvs.telefono,
                pvs.fax,
                pvs.email,
                pvs.cognome_referente,
                pvs.nome_referente,
                cf_operatore,
                pvs.d_inserim,
                '',
                ''
           FROM pro_d_prospetto_prov_sede pvs
          WHERE pvs.id_prospetto_prov = pv_prosp.id_prospetto_prov);

      /*
      inserimento in pro_d_prov_convenzione
      */
      INSERT INTO pro_d_prov_convenzione
        (id_prospetto_prov,
         id_t_stato_concessione,
         data_atto,
         estremi_atto,
         id_t_assunzione_protetta,
         data_stipula,
         data_scadenza,
         cod_user_inserim,
         d_inserim,
         cod_user_aggiorn,
         d_aggiorn,
         NUM_LAV_PREV_CONV_Q2)
        (SELECT newIdProspettoProv,
                pvc.id_t_stato_concessione,
                pvc.data_atto,
                pvc.estremi_atto,
                pvc.id_t_assunzione_protetta,
                pvc.data_stipula,
                pvc.data_scadenza,
                cf_operatore,
                sysdate,
                '',
                '',
                NUM_LAV_PREV_CONV_Q2
           FROM pro_d_prov_convenzione pvc
          WHERE pvc.id_prospetto_prov = pv_prosp.id_prospetto_prov);

      /*
      inserimento in pro_d_prov_esonero
      */
      INSERT INTO pro_d_prov_esonero
        (id_prospetto_prov,
         id_t_stato_concessione,
         data_atto,
         estremi_atto,
         data_atto_fino_al,
         percentuale,
         n_lavoratori_esonero,
         cod_user_inserim,
         d_inserim,
         d_aggiorn,
         cod_user_aggiorn)
        (SELECT newIdProspettoProv,
                pve.id_t_stato_concessione,
                pve.data_atto,
                pve.estremi_atto,
                pve.data_atto_fino_al,
                pve.percentuale,
                pve.n_lavoratori_esonero,
                cf_operatore,
                sysdate,
                sysdate,
                cf_operatore
           FROM pro_d_prov_esonero pve
          WHERE pve.id_prospetto_prov = pv_prosp.id_prospetto_prov);

     /* 
      inserimento in pro_d_prov_esonero_autocert
      */
      INSERT INTO pro_d_prov_esonero_autocert
        (id_prospetto_prov,
         data_autocert,
         percentuale_es_autocert,
         n_lav_60x1000,
         n_lav_esonero_autocert,
         cod_user_inserim,
         d_inserim,
         d_aggiorn,
         cod_user_aggiorn)
        (SELECT newIdProspettoProv,
                pve.data_autocert,
                pve.percentuale_es_autocert,
                pve.n_lav_60x1000,
                pve.n_lav_esonero_autocert,
                cf_operatore,
                sysdate,
                sysdate,
                cf_operatore
           FROM pro_d_prov_esonero_autocert pve
          WHERE pve.id_prospetto_prov = pv_prosp.id_prospetto_prov);
      
      INSERT INTO pro_d_prov_gradualita
        (id_prospetto_prov,
         n_assunzioni_eff_dopo_trasf,
         cod_user_inserim,
         d_inserim,
         cod_user_aggiorn,
         d_aggiorn)
        (SELECT newIdProspettoProv,
                pvg.n_assunzioni_eff_dopo_trasf,
                cf_operatore,
                sysdate,
                '',
                ''
           FROM pro_d_prov_gradualita pvg
          WHERE pvg.id_prospetto_prov = pv_prosp.id_prospetto_prov);

      /*
      inserimento in pro_d_part_time
      */
      IF (tipoOperazione = 'DUPLICAZIONE') THEN
        /*
        71862 - PRODIS-549
        Q2 - DETTAGLIO PART-TIME / INTERMITTENTI
        - verificare che l'orario settimanale svolto (part-time/intermittente) sia <
        all'orario settimanale contrattuale (e che siano entrambi <> 0)
        - verificare che sia valorizzato il n? lavoratori
        */
        FOR partTimeCurs IN (SELECT *
                               FROM pro_d_part_time
                              WHERE id_prospetto_prov =
                                    pv_prosp.id_prospetto_prov) LOOP
          IF partTimeCurs.n_part_time IS NOT NULL THEN
            IF partTimeCurs.ORARIO_SETT_CONTRATTUALE_MIN IS NOT NULL AND
               partTimeCurs.ORARIO_SETT_CONTRATTUALE_MIN <> 0 AND
               partTimeCurs.ORARIO_SETT_PART_TIME_MIN IS NOT NULL AND
               partTimeCurs.ORARIO_SETT_PART_TIME_MIN <> 0 AND
               partTimeCurs.ORARIO_SETT_PART_TIME_MIN <
               partTimeCurs.ORARIO_SETT_CONTRATTUALE_MIN AND
               partTimeCurs.n_part_time > 0 THEN

              SELECT seq_d_part_time.nextval
                INTO partTimeCurs.ID_PART_TIME
                FROM dual;
              partTimeCurs.id_prospetto_prov := newIdProspettoProv;
              partTimeCurs.cod_user_inserim  := cf_operatore;
              partTimeCurs.d_inserim         := sysdate;

              INSERT INTO pro_d_part_time VALUES partTimeCurs;

            END IF;
          END IF;
        END LOOP;

      ELSE
        INSERT INTO pro_d_part_time
          (id_part_time,
           id_prospetto_prov,
           n_part_time,
           orario_sett_contrattuale_min,
           orario_sett_part_time_min,
           id_tipo_riprop_pt, 
           cod_user_inserim,
           d_inserim,
           cod_user_aggiorn,
           d_aggiorn)
          (SELECT seq_d_part_time.nextval,
                  newIdProspettoProv,
                  pvpt.n_part_time,
                  pvpt.orario_sett_contrattuale_min,
                  pvpt.orario_sett_part_time_min,
                  pvpt.id_tipo_riprop_pt, 
                  cf_operatore,
                  sysdate,
                  '',
                  ''
             FROM pro_d_part_time pvpt
            WHERE pvpt.id_prospetto_prov = pv_prosp.id_prospetto_prov);
      END IF;

      /*
      inserimento in pro_d_prov_intermittenti
      */
      IF (tipoOperazione = 'DUPLICAZIONE') THEN
        /*
        71862 - PRODIS-549
        Q2 - DETTAGLIO PART-TIME / INTERMITTENTI
        - verificare che l'orario settimanale svolto (part-time/intermittente) sia <
        all'orario settimanale contrattuale (e che siano entrambi <> 0)
        - verificare che sia valorizzato il n? lavoratori
        */
        FOR provIntermittentiCurs IN (SELECT *
                                        FROM pro_d_prov_intermittenti
                                       WHERE id_prospetto_prov =
                                             pv_prosp.id_prospetto_prov) LOOP
          IF provIntermittentiCurs.n_intermittenti IS NOT NULL THEN
            IF provIntermittentiCurs.ORARIO_SETTIMANALE_CONTRATTUAL IS NOT NULL AND
               provIntermittentiCurs.ORARIO_SETTIMANALE_CONTRATTUAL <> 0 AND
               provIntermittentiCurs.ORARIO_SETTIMANALE_SVOLTO IS NOT NULL AND
               provIntermittentiCurs.ORARIO_SETTIMANALE_SVOLTO <> 0 AND
               provIntermittentiCurs.ORARIO_SETTIMANALE_SVOLTO <
               provIntermittentiCurs.ORARIO_SETTIMANALE_CONTRATTUAL AND
               provIntermittentiCurs.n_intermittenti > 0 THEN

              SELECT seq_d_prov_intermittenti.nextval
                INTO provIntermittentiCurs.ID_INTERMITTENTI
                FROM dual;
              provIntermittentiCurs.id_prospetto_prov := newIdProspettoProv;
              provIntermittentiCurs.cod_user_inserim  := cf_operatore;
              provIntermittentiCurs.d_inserim         := sysdate;

              INSERT INTO pro_d_prov_intermittenti
              VALUES provIntermittentiCurs;

            END IF;
          END IF;
        END LOOP;

      ELSE
        INSERT INTO pro_d_prov_intermittenti
          (id_intermittenti,
           id_prospetto_prov,
           n_intermittenti,
           orario_settimanale_contrattual,
           orario_settimanale_svolto,
           cod_user_inserim,
           d_inserim,
           cod_user_aggiorn,
           d_aggiorn)
          (SELECT seq_d_prov_intermittenti.nextval,
                  newIdProspettoProv,
                  pvit.n_intermittenti,
                  pvit.orario_settimanale_contrattual,
                  pvit.orario_settimanale_svolto,
                  cf_operatore,
                  sysdate,
                  '',
                  ''
             FROM pro_d_prov_intermittenti pvit
            WHERE pvit.id_prospetto_prov = pv_prosp.id_prospetto_prov);
      END IF;

      /*
      inserimento in pro_d_categorie_escluse
      */
      INSERT INTO pro_d_categorie_escluse
        (id_categorie_escluse,
         id_prospetto_prov,
         id_t_categorie_escluse,
         n_lav_appartart_categoria,
         cod_user_inserim,
         d_inserim,
         cod_user_aggiorn,
         d_aggiorn)
        (SELECT seq_d_categorie_escluse.nextval,
                newIdProspettoProv,
                pves.id_t_categorie_escluse,
                pves.n_lav_appartart_categoria,
                cf_operatore,
                sysdate,
                '',
                ''
           FROM pro_d_categorie_escluse pves, PRO_T_CATEGORIA_ESCLUSE --71862 - JIRA-499
          WHERE pves.id_t_categorie_escluse =
                PRO_T_CATEGORIA_ESCLUSE.id_t_categoria_escluse --71862 - JIRA-499
            AND (PRO_T_CATEGORIA_ESCLUSE.data_fine IS NULL OR
                PRO_T_CATEGORIA_ESCLUSE.data_fine > sysdate) --71862 - JIRA-499
            AND pves.id_prospetto_prov = pv_prosp.id_prospetto_prov);

      /*
      inserimento in pro_d_lavoratori_in_forza
      */
      IF (tipoOperazione = 'DUPLICAZIONE') THEN

        FOR pdLavForzaCurs IN (SELECT *
                                 FROM pro_d_lavoratori_in_forza
                                WHERE id_prospetto_prov =
                                      pv_prosp.id_prospetto_prov) LOOP

          SELECT seq_d_lavoratori_in_forza.nextval
            INTO pdLavForzaCurs.id_lavoratori_in_forza
            FROM dual;

          pdLavForzaCurs.id_prospetto_prov := newIdProspettoProv;
          pdLavForzaCurs.cod_user_inserim  := cf_operatore;
          pdLavForzaCurs.d_inserim         := sysdate;
          pdLavForzaCurs.cod_user_aggiorn  := NULL;
          pdLavForzaCurs.d_aggiorn         := NULL;
          pdLavForzaCurs.flg_completato    := 'S';

          -- manca Qualifica professionale lav - campo obbligatotrio
          -- verificare anche che il valore riportato sia ancora valido (pro_t_istat2001livello5)
          IF pdLavForzaCurs.id_t_qualifica_professionale_i IS NULL OR
             pdLavForzaCurs.id_t_qualifica_professionale_i = '' OR
             pdLavForzaCurs.id_t_qualifica_professionale_i = 0 OR
             -- PRODIS-604 Convenzione art. 12bis o Convenzione art. 14
             -- 71441 aggiunti i disabili somministrati 11 e 12
             pdLavForzaCurs.id_t_assunzione_protetta in (7, 8, 11, 12) THEN
             pdLavForzaCurs.flg_completato := 'N';
          ELSE
            BEGIN
              SELECT ID_T_ISTAT2001LIVELLO5
                INTO idtqualificaprofessionalei
                FROM pro_t_istat2001livello5
               WHERE ID_T_ISTAT2001LIVELLO5 =
                     pdLavForzaCurs.id_t_qualifica_professionale_i;
              IF idtqualificaprofessionalei IS NULL OR
                 idtqualificaprofessionalei = '' OR
                 idtqualificaprofessionalei = 0 THEN
                pdLavForzaCurs.flg_completato := 'N';
              ELSE
                SELECT nvl(ql.id_new_istat, ql.id_t_istat2001livello5)
                  INTO pdLavForzaCurs.id_t_qualifica_professionale_i
                  FROM pro_t_istat2001livello5 ql
                 WHERE ql.id_t_istat2001livello5 =
                       pdLavForzaCurs.id_t_qualifica_professionale_i;
              END IF;
            EXCEPTION
              WHEN OTHERS THEN
                pdLavForzaCurs.flg_completato := 'N';
            END;
          END IF;

          /*
          71862 - PRODIS-549
          Q2 -LAV L68 la procedura deve porre il flg_completato del lavoratore in forza = N se
          */
          -- manca CF lav - campo obbligatotrio
          IF pdLavForzaCurs.codice_fiscale IS NULL OR
             trim(pdLavForzaCurs.codice_fiscale) IS NULL OR
             pdLavForzaCurs.codice_fiscale = '' THEN
            pdLavForzaCurs.flg_completato := 'N';
          ELSE
            BEGIN
              --primo controllo tramite regular expression
              SELECT REGEXP_SUBSTR(pdLavForzaCurs.codice_fiscale,
                                   '[A-Z][A-Z][A-Z][A-Z][A-Z][A-Z][0-9][0-9][A-Z][0-9][0-9][A-Z][0-9][0-9][0-9][A-Z]')
                INTO cfsubstring
                FROM dual;
              IF cfsubstring IS NULL OR cfsubstring = '' OR
                 cfsubstring <> pdLavForzaCurs.codice_fiscale THEN
                pdLavForzaCurs.flg_completato := 'N';
              ELSE

                IF pdLavForzaCurs.id_t_comune_nascita IS NOT NULL THEN
                  SELECT c.cod_comune_min
                    INTO v_codice_comune
                    FROM pro_t_comune c
                   WHERE c.id_t_comune = pdLavForzaCurs.id_t_comune_nascita;
                ELSE
                  SELECT s.cod_nazione_min
                    INTO v_codice_comune
                    FROM pro_t_stati_esteri s
                   WHERE s.id_t_stati_esteri =
                         pdLavForzaCurs.id_t_stato_estero_nascita;
                END IF;

                --secondo controllo tramite stored procedure di controllo codice fiscale
                dt_nascita_lavoratore := to_char(pdLavForzaCurs.data_nascita,
                                                 'dd-mm-yyyy');


                  SELECT PCK_PRODIS_2012_UTILS.controlla_codice_fiscale(pdLavForzaCurs.codice_fiscale,
                                                                        pdLavForzaCurs.cognome,
                                                                        pdLavForzaCurs.nome,
                                                                        pdLavForzaCurs.sesso,
                                                                        dt_nascita_lavoratore,
                                                                        v_codice_comune)
                    INTO controllo_cf
                    FROM dual;

                  IF (controllo_cf = 0) THEN
                    pdLavForzaCurs.flg_completato := 'N';
                  END IF;

              END IF;
            EXCEPTION
              WHEN OTHERS THEN
                pdLavForzaCurs.flg_completato := 'N';
            END;
          END IF;

          -- manca Cognome lav - campo obbligatotrio
          IF pdLavForzaCurs.cognome IS NULL OR
             trim(pdLavForzaCurs.cognome) IS NULL OR
             pdLavForzaCurs.cognome = '' THEN
            pdLavForzaCurs.flg_completato := 'N';
          END IF;

          -- manca Nome lav - campo obbligatotrio
          IF pdLavForzaCurs.nome IS NULL OR
             trim(pdLavForzaCurs.nome) IS NULL OR pdLavForzaCurs.nome = '' THEN
            pdLavForzaCurs.flg_completato := 'N';
          END IF;

          -- manca Sesso lav - campo obbligatotrio
          IF pdLavForzaCurs.sesso IS NULL OR
             trim(pdLavForzaCurs.sesso) IS NULL OR
             pdLavForzaCurs.sesso = '' THEN
            pdLavForzaCurs.flg_completato := 'N';
          END IF;

          -- manca Data nascita lav - campo obbligatotrio
          IF pdLavForzaCurs.data_nascita IS NULL OR
             trim(pdLavForzaCurs.data_nascita) IS NULL OR
             pdLavForzaCurs.data_nascita = '' THEN
            pdLavForzaCurs.flg_completato := 'N';
          ELSIF pdLavForzaCurs.codice_fiscale IS NOT NULL THEN
            SELECT substr(pdLavForzaCurs.codice_fiscale, 7, 2)
              INTO annoCf
              FROM dual;
            SELECT substr(dt_nascita_lavoratore, 9, 2)
              INTO annoNascita
              FROM dual;
            IF annoCf <> annoNascita THEN
              pdLavForzaCurs.flg_completato := 'N';
            END IF;
          END IF;

          -- manca Comune/Stato nascita lav - campo obbligatotrio (cod e descrizione)
          IF (pdLavForzaCurs.id_t_stato_estero_nascita IS NULL OR
             pdLavForzaCurs.id_t_stato_estero_nascita = '' OR
             pdLavForzaCurs.id_t_stato_estero_nascita = 0) AND
             (pdLavForzaCurs.id_t_comune_nascita IS NULL OR
             pdLavForzaCurs.id_t_comune_nascita = '' OR
             pdLavForzaCurs.id_t_comune_nascita = 0) THEN
            --stato e comune non specificato
            pdLavForzaCurs.flg_completato := 'N';
          ELSIF (pdLavForzaCurs.id_t_stato_estero_nascita IS NOT NULL AND
                pdLavForzaCurs.id_t_stato_estero_nascita <> '' AND
                pdLavForzaCurs.id_t_stato_estero_nascita <> 0) AND
                (pdLavForzaCurs.id_t_comune_nascita IS NULL OR
                pdLavForzaCurs.id_t_comune_nascita = '' OR
                pdLavForzaCurs.id_t_comune_nascita = 0) THEN
            --abbiamo stato
            BEGIN
              SELECT id_t_stati_esteri
                INTO idStatoEsteroNascitaLavoratore
                FROM pro_t_stati_esteri
               WHERE id_t_stati_esteri =
                     pdLavForzaCurs.id_t_stato_estero_nascita
                 AND (dt_fine IS NULL OR dt_fine > sysdate);
              IF idStatoEsteroNascitaLavoratore IS NULL OR
                 idStatoEsteroNascitaLavoratore = '' OR
                 idStatoEsteroNascitaLavoratore <>
                 pdLavForzaCurs.id_t_stato_estero_nascita THEN
                pdLavForzaCurs.flg_completato := 'N';
              END IF;
            EXCEPTION
              WHEN OTHERS THEN
                pdLavForzaCurs.flg_completato := 'N';
            END;
          ELSIF (pdLavForzaCurs.id_t_stato_estero_nascita IS NULL AND
                pdLavForzaCurs.id_t_stato_estero_nascita = '' AND
                pdLavForzaCurs.id_t_stato_estero_nascita = 0) AND
                (pdLavForzaCurs.id_t_comune_nascita IS NOT NULL OR
                pdLavForzaCurs.id_t_comune_nascita <> '' OR
                pdLavForzaCurs.id_t_comune_nascita <> 0) THEN
            --abbiamo comune
            BEGIN
              SELECT id_t_comune
                INTO idComuneNascitaLavoratore
                FROM pro_t_comune
               WHERE id_t_comune = pdLavForzaCurs.id_t_comune_nascita
                 AND (dt_fine IS NULL OR dt_fine > sysdate);
              IF idComuneNascitaLavoratore IS NULL OR
                 idComuneNascitaLavoratore = '' OR
                 idComuneNascitaLavoratore <>
                 pdLavForzaCurs.id_t_comune_nascita THEN
                pdLavForzaCurs.flg_completato := 'N';
              END IF;
            EXCEPTION
              WHEN OTHERS THEN
                pdLavForzaCurs.flg_completato := 'N';
            END;
          END IF;

          -- manca Data inizio rapporto lav - campo obbligatotrio
          IF pdLavForzaCurs.data_inizio_rapporto IS NULL OR
             trim(pdLavForzaCurs.data_inizio_rapporto) IS NULL OR
             pdLavForzaCurs.data_inizio_rapporto = '' THEN
            pdLavForzaCurs.flg_completato := 'N';
          END IF;

          --controllo su data_fine_rapporto > data_inizio_rapporto
          IF pdLavForzaCurs.data_inizio_rapporto IS NOT NULL AND
             pdLavForzaCurs.data_fine_rapporto IS NOT NULL AND
             pdLavForzaCurs.data_fine_rapporto <
             pdLavForzaCurs.data_inizio_rapporto THEN
            pdLavForzaCurs.flg_completato := 'N';
          END IF;

          --controllo su data_fine_rapporto > data_riferimento_prospetto
          IF proDProspettoROW.data_riferimento_prospetto IS NOT NULL AND
             pdLavForzaCurs.data_fine_rapporto IS NOT NULL AND
             pdLavForzaCurs.data_fine_rapporto <=
             proDProspettoROW.data_riferimento_prospetto THEN
            pdLavForzaCurs.flg_completato := 'N';
          END IF;

          --controllo su data_nascita <> data_inizio_rapporto
          IF pdLavForzaCurs.data_inizio_rapporto IS NOT NULL AND
             pdLavForzaCurs.data_nascita IS NOT NULL AND
             pdLavForzaCurs.data_inizio_rapporto =
             pdLavForzaCurs.data_nascita THEN
            pdLavForzaCurs.flg_completato := 'N';
          END IF;

          -- manca Tipologia contrattuale lav - campo obbligatotrio
          -- verificare anche che il valore riportato sia ancora valido (pro_t_contratti)
          IF pdLavForzaCurs.id_t_contratto IS NULL OR
             pdLavForzaCurs.id_t_contratto = '' THEN
            pdLavForzaCurs.flg_completato := 'N';
          ELSE
            BEGIN
              SELECT id_t_contratto
                INTO idtcontratto
                FROM pro_t_contratti
               WHERE id_t_contratto = pdLavForzaCurs.id_t_contratto
                 AND (dt_fine IS NULL OR dt_fine > sysdate);
              IF idtcontratto IS NULL THEN
                pdLavForzaCurs.flg_completato := 'N';
              ELSE

                SELECT flg_forma
                  INTO formacontratto
                  FROM pro_t_contratti
                 WHERE id_t_contratto = pdLavForzaCurs.id_t_contratto
                   AND (dt_fine IS NULL OR dt_fine > sysdate);

                -- se contratto e a tempo D DEVE essere presente la data dine attivita
                IF formacontratto = 'D' AND
                   (pdLavForzaCurs.data_fine_rapporto IS NULL OR
                   trim(pdLavForzaCurs.data_fine_rapporto) = '') THEN
                  pdLavForzaCurs.flg_completato := 'N';
                END IF;

              END IF;
            EXCEPTION
              WHEN OTHERS THEN
                pdLavForzaCurs.flg_completato := 'N';
            END;
          END IF;

          -- manca Tipo assunzione protetta lav - campo obbligatotrio
          -- verificare anche che il valore riportato sia ancora valido (pro_t_assunzione_protetta)
          IF pdLavForzaCurs.id_t_assunzione_protetta IS NULL OR
             pdLavForzaCurs.id_t_assunzione_protetta = '' OR
             pdLavForzaCurs.id_t_assunzione_protetta = 0 THEN
            pdLavForzaCurs.flg_completato := 'N';
          ELSE
            BEGIN
              SELECT id_t_assunzione_protetta
                INTO idtassunzioneprotetta
                FROM pro_t_assunzione_protetta
               WHERE id_t_assunzione_protetta =
                     pdLavForzaCurs.id_t_assunzione_protetta
                 AND (data_fine IS NULL OR data_fine > sysdate);

              IF idtassunzioneprotetta IS NULL OR
                 idtassunzioneprotetta = '' OR idtassunzioneprotetta = 0 THEN
                pdLavForzaCurs.flg_completato := 'N';
              END IF;
            EXCEPTION
              WHEN OTHERS THEN
                pdLavForzaCurs.flg_completato := 'N';
            END;
          END IF;

          -- manca Orario settimanale contrattuale lav - campo obbligatotrio
          IF pdLavForzaCurs.orario_sett_contrattuale_min IS NULL OR
             pdLavForzaCurs.orario_sett_contrattuale_min = '' THEN
            pdLavForzaCurs.flg_completato := 'N';
          END IF;

          -- manca Orario settimanale svolto lav - campo obbligatotrio
          IF pdLavForzaCurs.orario_sett_part_time_min IS NULL OR
             pdLavForzaCurs.orario_sett_part_time_min = '' THEN
            pdLavForzaCurs.flg_completato := 'N';
          END IF;

          -- manca Categoria soggetto lav - campo obbligatotrio
          -- verificare anche che il valore riportato sia ancora valido (pro_t_soggetti)
          IF pdLavForzaCurs.categoria_soggetto IS NULL OR
             trim(pdLavForzaCurs.categoria_soggetto) IS NULL OR
             pdLavForzaCurs.categoria_soggetto = '' THEN
            pdLavForzaCurs.flg_completato := 'N';
          ELSIF pdLavForzaCurs.categoria_soggetto = 'D' THEN
            -- manca Percentuale disabilita lav
            -- (da verificare solo se si tratta di categoria soggetto = D disabile - valori 00-100) - campo obbligatotrio
            IF pdLavForzaCurs.PERCENTUALE_DISABILITA IS NULL THEN
              pdLavForzaCurs.flg_completato := 'N';
            ELSIF pdLavForzaCurs.PERCENTUALE_DISABILITA > 100 OR
                  pdLavForzaCurs.PERCENTUALE_DISABILITA < 0 THEN
              pdLavForzaCurs.flg_completato := 'N';
            END IF;
          ELSIF pdLavForzaCurs.categoria_soggetto = 'C' THEN
            --se categoria protetta percentuale disabilita' deve essere nullo o vuoto
            IF pdLavForzaCurs.PERCENTUALE_DISABILITA IS NOT NULL OR
               trim(pdLavForzaCurs.PERCENTUALE_DISABILITA) <> '' THEN
              pdLavForzaCurs.flg_completato := 'N';
            END IF;
          END IF;

          -- manca Categoria assunzione lav - campo obbligatotrio
          IF pdLavForzaCurs.categoria_assunzione IS NULL OR
             trim(pdLavForzaCurs.categoria_assunzione) IS NULL OR
             pdLavForzaCurs.categoria_assunzione = '' THEN
            pdLavForzaCurs.flg_completato := 'N';
          END IF;

          INSERT INTO pro_d_lavoratori_in_forza VALUES pdLavForzaCurs;

        END LOOP;

      ELSE
        FOR pdLavForzaCurs IN (SELECT *
                                 FROM pro_d_lavoratori_in_forza
                                WHERE id_prospetto_prov =
                                      pv_prosp.id_prospetto_prov) LOOP
          SELECT seq_d_lavoratori_in_forza.nextval
            INTO pdLavForzaCurs.id_lavoratori_in_forza
            FROM dual;
          pdLavForzaCurs.id_prospetto_prov := newIdProspettoProv;
          pdLavForzaCurs.cod_user_inserim  := cf_operatore;
          pdLavForzaCurs.d_inserim         := sysdate;
          INSERT INTO pro_d_lavoratori_in_forza VALUES pdLavForzaCurs;
        END LOOP;
      END IF;

      /*
      inserimento in PRO_D_PROV_COMPENSAZIONI
      */
      IF (tipoOperazione = 'ANNULLAMENTO' OR tipoOperazione = 'RETTIFICA') THEN
        FOR pdProvCompCurs IN (SELECT *
                                 FROM PRO_D_PROV_COMPENSAZIONI
                                WHERE id_prospetto_prov =
                                      pv_prosp.id_prospetto_prov) LOOP
          SELECT seq_d_prov_compensazioni.nextval
            INTO pdProvCompCurs.id_compensazione
            FROM dual;
          pdProvCompCurs.id_prospetto_prov := newIdProspettoProv;
          pdProvCompCurs.cod_user_inserim  := cf_operatore;
          pdProvCompCurs.d_inserim         := sysdate;
          INSERT INTO PRO_D_PROV_COMPENSAZIONI VALUES pdProvCompCurs;
        END LOOP;
      END IF;

      /*
      inserimento in PRO_D_POSTI_LAVORO_DISP
      */
      IF (tipoOperazione = 'ANNULLAMENTO' OR tipoOperazione = 'RETTIFICA') THEN
        FOR pdLavDispCurs IN (SELECT *
                                FROM PRO_D_POSTI_LAVORO_DISP
                               WHERE id_prospetto_prov =
                                     pv_prosp.id_prospetto_prov) LOOP
          SELECT SEQ_D_POSTI_LAVORO_DISP.nextval
            INTO pdLavDispCurs.id_posto_lavoro_disp
            FROM dual;
          pdLavDispCurs.id_prospetto_prov := newIdProspettoProv;
          pdLavDispCurs.cod_user_inserim  := cf_operatore;
          pdLavDispCurs.d_inserim         := sysdate;

          SELECT nvl(ql.id_new_istat, ql.id_t_istat2001livello5)
            INTO pdLavDispCurs.ID_T_QUALIFICA_PROFESSI_ISTAT
            FROM pro_t_istat2001livello5 ql
           WHERE ql.id_t_istat2001livello5 =
                 pdLavDispCurs.ID_T_QUALIFICA_PROFESSI_ISTAT;

          INSERT INTO PRO_D_POSTI_LAVORO_DISP VALUES pdLavDispCurs;

        END LOOP;
      END IF;

      /*
      inserimento in PRO_D_PROV_SOSPENSIONE
      */
      IF (tipoOperazione = 'ANNULLAMENTO' OR tipoOperazione = 'RETTIFICA') THEN
        FOR pdProvSospCurs IN (SELECT *
                                 FROM PRO_D_PROV_SOSPENSIONE
                                WHERE id_prospetto_prov =
                                      pv_prosp.id_prospetto_prov) LOOP
          pdProvSospCurs.id_prospetto_prov := newIdProspettoProv;
          pdProvSospCurs.cod_user_inserim  := cf_operatore;
          pdProvSospCurs.d_inserim         := sysdate;
          INSERT INTO PRO_D_PROV_SOSPENSIONE VALUES pdProvSospCurs;
        END LOOP;
      END IF;

    END LOOP;

    esito := 1;

  EXCEPTION

    WHEN e_tipo_operazione THEN
      dbms_output.put_line('ERRORE : tipo operazione ' || tipoOperazione ||
                           ' errato!');
      esito     := -401;
      messaggio := 'Errore generico durante l''esecuzione di un''operazione di annullamento, rettifica o duplicazione. Il tipo operazione specificato e'' errato o inesistente!';

    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      esito     := -400;
      messaggio := 'Errore generico durante l''esecuzione di un''operazione di annullamento, rettifica o duplicazione. ERRORE : ' ||
                   SUBSTR(SQLERRM, 1, 110) || '  SQLCODE=' ||
                   TO_CHAR(SQLCODE);

  END esegui_operazione;

END Pck_Prodis_2012;
/

CREATE OR REPLACE PACKAGE BODY "PCK_PRODIS_2012_COMP_UTILS" AS

	PROCEDURE writeLn(str varchar2) IS
		BEGIN
			if log_enable = true then
				dbms_output.put_line(to_char(sysdate,'dd/mm/yy hh24:mi:ss')||'-'||str);
			end if;
	END writeLn;

	procedure setLogEnable(attiva boolean) is
		begin
			log_enable := attiva;
			if log_enable = true then
				dbms_output.enable(999999);
				dbms_output.put_line('ABILITATA SESSIONE DI LOG!');
				dbms_output.put_line('RICORDARSI DI DISABILITARLA AL TERMINE CON PCK_PRODIS_2012_UTILS.setLogEnable(false)');
			end if;
	end setLogEnable;

	FUNCTION isLogEnable return BOOLEAN IS
		BEGIN
			return log_enable;
		EXCEPTION
			WHEN OTHERS THEN
			dbms_output.put_line('ERRORE : '||SUBSTR(SQLERRM,1,110)||'  SQLCODE='||TO_CHAR(SQLCODE));
			return null;
	END isLogEnable;

  FUNCTION get_cat_compens_disab_pv(prospetto_prov_id IN NUMBER) return CHAR AS
      cat_compens_disab_pv CHAR := '';

      e_categoria_compens EXCEPTION;
    BEGIN

      /*
        Categoria compensazione disabili_PV -> PRO_D_RIEPILOGO_PROVINCIALE.CAT_COMPENSAZIONE_DISABILI
        Si mette E o R, in base alla distinct(PRO_D_PROV_COMPENSAZIONI.CATEGORIA_COMPENSAZIONE) per CATEGORIA_SOGGETTO = 'D'
      */
      cat_compens_disab_pv := PCK_PRODIS_2012_PROV_UTILS.get_categoria_compens_disab(prospetto_prov_id);
      writeLn('[get_cat_compens_disab_pv] cat_compens_disab_pv ' || cat_compens_disab_pv);

      RETURN cat_compens_disab_pv;
    EXCEPTION
      WHEN OTHERS THEN
        dbms_output.put_line('ERRORE : '||SUBSTR(SQLERRM,1,110)||'  SQLCODE='||TO_CHAR(SQLCODE));
        raise e_categoria_compens;
  END get_cat_compens_disab_pv;


  FUNCTION get_n_compen_disab_pv(prospetto_prov_id IN NUMBER) return NUMBER AS
      n_compen_disab_pv NUMBER := 0;
    BEGIN

      /*
        N° Compensazione disabili_PV -> PRO_D_RIEPILOGO_PROVINCIALE.NUM_COMPENSAZIONE_DISABILI
        valore calcolato automaticamente con la somma delle compensazioni con CATEGORIA_SOGGETTO = 'D' per il 'Quadro 2'.
      */
      n_compen_disab_pv := PCK_PRODIS_2012_PROV_UTILS.get_n_compens_disab(prospetto_prov_id);
      writeLn('[get_n_compen_disab_pv] n_compen_disab_pv ' || n_compen_disab_pv);

      RETURN n_compen_disab_pv;
    EXCEPTION
      WHEN OTHERS THEN
        dbms_output.put_line('ERRORE : '||SUBSTR(SQLERRM,1,110)||'  SQLCODE='||TO_CHAR(SQLCODE));
        return null;
  END get_n_compen_disab_pv;


  FUNCTION get_cat_compens_catprot_pv(prospetto_prov_id IN NUMBER) return CHAR AS
      cat_compens_catprot_pv CHAR := '';

      e_categoria_compens EXCEPTION;
    BEGIN

      /*
        Categoria compensazione categorie protette_PV -> PRO_D_RIEPILOGO_PROVINCIALE.CAT_COMPENSAZIONE_CATE_PROT
        Si mette E o R, in base alla distinct(PRO_D_PROV_COMPENSAZIONI.CATEGORIA_COMPENSAZIONE) per CATEGORIA_SOGGETTO = 'C'
      */
      cat_compens_catprot_pv := PCK_PRODIS_2012_PROV_UTILS.get_categoria_compens_cat_prot(prospetto_prov_id);
      writeLn('[get_cat_compens_catprot_pv] cat_compens_catprot_pv ' || cat_compens_catprot_pv);

      RETURN cat_compens_catprot_pv;
    EXCEPTION
      WHEN OTHERS THEN
        dbms_output.put_line('ERRORE : '||SUBSTR(SQLERRM,1,110)||'  SQLCODE='||TO_CHAR(SQLCODE));
        raise e_categoria_compens;
  END get_cat_compens_catprot_pv;

  FUNCTION get_n_compen_catprot_pv(prospetto_prov_id IN NUMBER) return NUMBER AS
      n_compen_catprot_pv NUMBER := 0;
    BEGIN

      /*
        N° Compensazioni categorie protette_PV -> PRO_D_RIEPILOGO_PROVINCIALE.NUM_COMPENSAZIONI_CATE_PROT
        valore calcolato automaticamente con la somma delle compensazioni con CATEGORIA_SOGGETTO = 'C' per il 'Quadro 2'.
      */
      n_compen_catprot_pv := PCK_PRODIS_2012_PROV_UTILS.get_n_compens_cat_prot(prospetto_prov_id);
      writeLn('[get_n_compen_catprot_pv] n_compen_catprot_pv ' || n_compen_catprot_pv);

      RETURN n_compen_catprot_pv;
    EXCEPTION
      WHEN OTHERS THEN
        dbms_output.put_line('ERRORE : '||SUBSTR(SQLERRM,1,110)||'  SQLCODE='||TO_CHAR(SQLCODE));
        return null;
  END get_n_compen_catprot_pv;

  FUNCTION get_n_scopert_disab_pv(prospetto_prov_id IN NUMBER) return NUMBER AS
      n_scopert_disab_pv NUMBER := 0;

      catCompensazioneDisabPV CHAR := '';
      nCompensazDisabPV NUMBER := 0;
    BEGIN

      /*
        N° Scoperture disabili (L.68/99 art.1)_PV -> PRO_D_RIEPILOGO_PROVINCIALE.NUM_SCOPERT_DISABILI
        Se 'Categoria compensazione disabili_PV' NOT NULL,
        al valore 'N° Scoperture disabili (L.68/99 art.1)_PV' calcolato precedentemente, al punto 2)VI.,
        si aggiungono o detraggono le compensazioni nel seguente modo:
        a) Se 'Categoria compensazione disabili_PV' = 'E', si somma il valore di 'N° Compensazione disabili_PV';
        b) Se 'Categoria compensazione disabili_PV' = 'R', si detrae il valore di 'N° Compensazione disabili_PV'.
      */
      catCompensazioneDisabPV := get_cat_compens_disab_pv(prospetto_prov_id);
      writeLn('[n_scopert_disab_pv] catCompensazioneDisabPV ' || catCompensazioneDisabPV);

      nCompensazDisabPV := get_n_compen_disab_pv(prospetto_prov_id);
      writeLn('[n_scopert_disab_pv] nCompensazDisabPV ' || nCompensazDisabPV);

      select  PRO_D_RIEPILOGO_PROVINCIALE.NUM_SCOPERTURE_DISABILI_REALI
              into n_scopert_disab_pv
      from    PRO_D_RIEPILOGO_PROVINCIALE
      where   PRO_D_RIEPILOGO_PROVINCIALE.ID_PROSPETTO_PROV = prospetto_prov_id;
      writeLn('[n_scopert_disab_pv] n_scopert_disab_pv ' || n_scopert_disab_pv);

      if (catCompensazioneDisabPV is not null and catCompensazioneDisabPV = 'E') then
        n_scopert_disab_pv := n_scopert_disab_pv + nCompensazDisabPV;
      elsif (catCompensazioneDisabPV is not null and catCompensazioneDisabPV = 'R') then
        n_scopert_disab_pv := n_scopert_disab_pv - nCompensazDisabPV;
      end if;
      writeLn('[n_scopert_disab_pv] n_scopert_disab_pv ' || n_scopert_disab_pv);

      RETURN n_scopert_disab_pv;
    EXCEPTION
      WHEN OTHERS THEN
        dbms_output.put_line('ERRORE : '||SUBSTR(SQLERRM,1,110)||'  SQLCODE='||TO_CHAR(SQLCODE));
        return null;
  END get_n_scopert_disab_pv;

  FUNCTION get_n_scopert_catprot_pv(prospetto_prov_id IN NUMBER) return NUMBER AS
      n_scopert_catprot_pv NUMBER := 0;

      catCompensazioneCatProtPV CHAR := '';
      nCompensazCatProtPV NUMBER := 0;
    BEGIN

      /*
        N° Scoperture categorie protette (L.68/99 art.18)_PV -> PRO_D_RIEPILOGO_PROVINCIALE.NUM_SCOPERT_CATEGORIE_PROTETTE
        Se 'Categoria compensazione categorie protette_PV' NOT NULL,
        al valore 'N° Scoperture categorie protette (L.68/99 art.18)_PV' calcolato precedentemente, al punto 2)VI.,
        si aggiungono o detraggono le compensazioni nel seguente modo:
        a) Se 'Categoria compensazione categorie protette_PV' = 'E', si somma il valore di 'N° Compensazione categorie protette_PV';
        b) Se 'Categoria compensazione categorie protette_PV' = 'R', si detrae il valore di 'N° Compensazione categorie protette_PV'.
      */
      catCompensazioneCatProtPV := get_cat_compens_catprot_pv(prospetto_prov_id);
      writeLn('[get_n_scopert_catprot_pv] catCompensazioneCatProtPV ' || catCompensazioneCatProtPV);

      nCompensazCatProtPV := get_n_compen_catprot_pv(prospetto_prov_id);
      writeLn('[get_n_scopert_catprot_pv] nCompensazCatProtPV ' || nCompensazCatProtPV);

      select  PRO_D_RIEPILOGO_PROVINCIALE.NUM_SCOPERTURE_CAT_PROT_REALI
              into n_scopert_catprot_pv
      from    PRO_D_RIEPILOGO_PROVINCIALE
      where   PRO_D_RIEPILOGO_PROVINCIALE.id_prospetto_prov = prospetto_prov_id;
      writeLn('[get_n_scopert_catprot_pv] n_scopert_catprot_pv ' || n_scopert_catprot_pv);

      if (catCompensazioneCatProtPV is not null and catCompensazioneCatProtPV = 'E') then
        n_scopert_catprot_pv := n_scopert_catprot_pv + nCompensazCatProtPV;
      elsif (catCompensazioneCatProtPV is not null and catCompensazioneCatProtPV = 'R') then
        n_scopert_catprot_pv := n_scopert_catprot_pv - nCompensazCatProtPV;
      end if;

      RETURN n_scopert_catprot_pv;
    EXCEPTION
      WHEN OTHERS THEN
        dbms_output.put_line('ERRORE : '||SUBSTR(SQLERRM,1,110)||'  SQLCODE='||TO_CHAR(SQLCODE));
        return null;
  END get_n_scopert_catprot_pv;

  FUNCTION get_n_ecced_disab_itergruppo(prospetto_id IN NUMBER) return NUMBER AS
      n_ecced_disab_itergruppo NUMBER := 0;
    BEGIN

	  select nvl((
		  select  sum(pro_d_prov_compensazioni.n_lavoratori)
		  from    pro_d_prov_compensazioni, pro_r_prospetto_provincia
		  where   pro_d_prov_compensazioni.cf_azienda_apparten_al_gruppo is not null
				  and pro_d_prov_compensazioni.categoria_soggetto = 'D'
				  and pro_d_prov_compensazioni.categoria_compensazione = 'E'
				  and pro_r_prospetto_provincia.id_prospetto_prov = pro_d_prov_compensazioni.id_prospetto_prov
				  and pro_r_prospetto_provincia.id_prospetto = prospetto_id), 0)
      into n_ecced_disab_itergruppo
	  from dual;
      writeLn('[get_n_ecced_disab_itergruppo] n_ecced_disab_itergruppo ' || n_ecced_disab_itergruppo);

      RETURN n_ecced_disab_itergruppo;
    EXCEPTION
      WHEN OTHERS THEN
        dbms_output.put_line('ERRORE : '||SUBSTR(SQLERRM,1,110)||'  SQLCODE='||TO_CHAR(SQLCODE));
        return null;
  END get_n_ecced_disab_itergruppo;


  FUNCTION get_n_riduz_disab_itergruppo(prospetto_id IN NUMBER) return NUMBER AS
      n_riduz_disab_itergruppo NUMBER := 0;
    BEGIN

	  select nvl((
		  select  sum(pro_d_prov_compensazioni.n_lavoratori)
		  from    pro_d_prov_compensazioni, pro_r_prospetto_provincia
		  where   pro_d_prov_compensazioni.cf_azienda_apparten_al_gruppo is not null
				  and pro_d_prov_compensazioni.categoria_soggetto = 'D'
				  and pro_d_prov_compensazioni.categoria_compensazione = 'R'
				  and pro_r_prospetto_provincia.id_prospetto_prov = pro_d_prov_compensazioni.id_prospetto_prov
				  and pro_r_prospetto_provincia.id_prospetto = prospetto_id), 0)
	  into n_riduz_disab_itergruppo
	  from dual;
      writeLn('[get_n_riduz_disab_itergruppo] n_riduz_disab_itergruppo ' || n_riduz_disab_itergruppo);

      RETURN n_riduz_disab_itergruppo;
    EXCEPTION
      WHEN OTHERS THEN
        dbms_output.put_line('ERRORE : '||SUBSTR(SQLERRM,1,110)||'  SQLCODE='||TO_CHAR(SQLCODE));
        return null;
  END get_n_riduz_disab_itergruppo;


  FUNCTION get_n_ecced_catprot_itergruppo(prospetto_id IN NUMBER) return NUMBER AS
      n_ecced_catprot_itergruppo NUMBER := 0;
    BEGIN

	  select nvl((
		  select  sum(pro_d_prov_compensazioni.n_lavoratori)
		  from    pro_d_prov_compensazioni, pro_r_prospetto_provincia
		  where   pro_d_prov_compensazioni.cf_azienda_apparten_al_gruppo is not null
				  and pro_d_prov_compensazioni.categoria_soggetto = 'C'
				  and pro_d_prov_compensazioni.categoria_compensazione = 'E'
				  and pro_r_prospetto_provincia.id_prospetto_prov = pro_d_prov_compensazioni.id_prospetto_prov
				  and pro_r_prospetto_provincia.id_prospetto = prospetto_id), 0)
	  into n_ecced_catprot_itergruppo
	  from dual;
      writeLn('[get_n_ecced_catprot_itergruppo] n_ecced_catprot_itergruppo ' || n_ecced_catprot_itergruppo);

      RETURN n_ecced_catprot_itergruppo;
    EXCEPTION
      WHEN OTHERS THEN
        dbms_output.put_line('ERRORE : '||SUBSTR(SQLERRM,1,110)||'  SQLCODE='||TO_CHAR(SQLCODE));
        return null;
  END get_n_ecced_catprot_itergruppo;


  FUNCTION get_n_riduz_catprot_itergruppo(prospetto_id IN NUMBER) return NUMBER AS
      n_riduz_catprot_itergruppo NUMBER := 0;
    BEGIN

	  select nvl((
		  select  sum(pro_d_prov_compensazioni.n_lavoratori)
		  from    pro_d_prov_compensazioni, pro_r_prospetto_provincia
		  where   pro_d_prov_compensazioni.cf_azienda_apparten_al_gruppo is not null
				  and pro_d_prov_compensazioni.categoria_soggetto = 'C'
				  and pro_d_prov_compensazioni.categoria_compensazione = 'R'
				  and pro_r_prospetto_provincia.id_prospetto_prov = pro_d_prov_compensazioni.id_prospetto_prov
				  and pro_r_prospetto_provincia.id_prospetto = prospetto_id), 0)
	  into n_riduz_catprot_itergruppo
	  from dual;
      writeLn('[get_n_riduz_catprot_itergruppo] n_riduz_catprot_itergruppo ' || n_riduz_catprot_itergruppo);

      RETURN n_riduz_catprot_itergruppo;
    EXCEPTION
      WHEN OTHERS THEN
        dbms_output.put_line('ERRORE : '||SUBSTR(SQLERRM,1,110)||'  SQLCODE='||TO_CHAR(SQLCODE));
        return null;
  END get_n_riduz_catprot_itergruppo;

  FUNCTION get_n_scopert_disab_naz(prospetto_id IN NUMBER, numeccedenzedisabintergruppo IN NUMBER, numriduzionidisabintergruppo IN NUMBER) return NUMBER AS
      n_scopert_disab_naz NUMBER := 0;
    BEGIN

      select  pro_d_riepilogo_nazionale.NUM_SCOPERT_DISABILI_REALI
              into n_scopert_disab_naz
      from    PRO_D_RIEPILOGO_NAZIONALE
      where   pro_d_riepilogo_nazionale.id_prospetto = prospetto_id;
      writeLn('[get_n_scopert_disab_naz] n_scopert_disab_naz ' || n_scopert_disab_naz);

      n_scopert_disab_naz := n_scopert_disab_naz + numeccedenzedisabintergruppo - numriduzionidisabintergruppo;
      writeLn('[get_n_scopert_disab_naz] n_scopert_disab_naz ' || n_scopert_disab_naz);

      RETURN n_scopert_disab_naz;

    EXCEPTION
      WHEN OTHERS THEN
        dbms_output.put_line('ERRORE : '||SUBSTR(SQLERRM,1,110)||'  SQLCODE='||TO_CHAR(SQLCODE));
        return null;
  END get_n_scopert_disab_naz;

  FUNCTION get_n_scopert_catprot_naz(prospetto_id IN NUMBER, numeccedenzecatprotintergruppo IN NUMBER, numriduzionicatprotintergruppo IN NUMBER) return NUMBER AS
      n_scopert_catprot_naz NUMBER := 0;
    BEGIN

      select  pro_d_riepilogo_nazionale.NUM_SCOPERT_CAT_PROT_REALI
              into n_scopert_catprot_naz
      from    PRO_D_RIEPILOGO_NAZIONALE
      where   pro_d_riepilogo_nazionale.id_prospetto = prospetto_id;
      writeLn('[get_n_scopert_catprot_naz] n_scopert_catprot_naz ' || n_scopert_catprot_naz);

      n_scopert_catprot_naz := n_scopert_catprot_naz + numeccedenzecatprotintergruppo - numriduzionicatprotintergruppo;
      writeLn('[get_n_scopert_catprot_naz] n_scopert_catprot_naz ' || n_scopert_catprot_naz);

      RETURN n_scopert_catprot_naz;

    EXCEPTION
      WHEN OTHERS THEN
        dbms_output.put_line('ERRORE : '||SUBSTR(SQLERRM,1,110)||'  SQLCODE='||TO_CHAR(SQLCODE));
        return null;
  END get_n_scopert_catprot_naz;

END PCK_PRODIS_2012_COMP_UTILS;
/

CREATE OR REPLACE PACKAGE BODY "PCK_PRODIS_2012_NAZ_UTILS" AS
  
  PROCEDURE writeLn(str VARCHAR2) IS
  BEGIN
    IF log_enable = TRUE THEN
      dbms_output.put_line(TO_CHAR(sysdate, 'dd/mm/yy hh24:mi:ss') || '-' || str);
    END IF;
  END writeLn;
  PROCEDURE setLogEnable(attiva BOOLEAN) IS
  BEGIN
    log_enable := attiva;
    IF log_enable = TRUE THEN
      dbms_output.enable(999999);
      dbms_output.put_line('ABILITATA SESSIONE DI LOG!');
      dbms_output.put_line('RICORDARSI DI DISABILITARLA AL TERMINE CON PCK_PRODIS_2012_UTILS.setLogEnable(false)');
    END IF;
  END setLogEnable;
  FUNCTION isLogEnable RETURN BOOLEAN IS
  BEGIN
    RETURN log_enable;
  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END isLogEnable;
  FUNCTION get_n_lav_in_forza_naz(prospetto_id IN NUMBER) RETURN NUMBER IS
    nLavInForzaNaz NUMBER := 0;
  BEGIN
    SELECT NVL((SELECT SUM(pro_d_dati_provinciali.n_totale_lavorat_dipendenti)
                 FROM pro_d_dati_provinciali, pro_r_prospetto_provincia
                WHERE pro_d_dati_provinciali.id_prospetto_prov =
                      pro_r_prospetto_provincia.id_prospetto_prov
                  AND pro_r_prospetto_provincia.id_prospetto = prospetto_id),
               0)
      INTO nLavInForzaNaz
      FROM dual;
    writeLn('[get_n_lav_in_forza_naz] nLavInForzaNaz : ' || nLavInForzaNaz);
    RETURN PCK_PRODIS_2012_UTILS.handle_number(nLavInForzaNaz);
  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_n_lav_in_forza_naz;
  FUNCTION get_n_central_telef(prospetto_id IN NUMBER) RETURN NUMBER IS
    nCentralTelef NUMBER := 0;
  BEGIN
    SELECT NVL((SELECT SUM(pro_d_dati_provinciali.N_CENTRAL_TELEFO_NONVEDENTI)
                 FROM pro_d_dati_provinciali, pro_r_prospetto_provincia
                WHERE pro_d_dati_provinciali.id_prospetto_prov =
                      pro_r_prospetto_provincia.id_prospetto_prov
                  AND pro_r_prospetto_provincia.id_prospetto = prospetto_id),
               0)
      INTO nCentralTelef
      FROM dual;
    writeLn('[get_n_central_telef] nCentralTelef : ' || nCentralTelef);
    RETURN PCK_PRODIS_2012_UTILS.handle_number(nCentralTelef);
  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_n_central_telef;
  FUNCTION get_n_terriabil_massononved(prospetto_id IN NUMBER) RETURN NUMBER IS
    nTerriabilMassononved NUMBER := 0;
  BEGIN
    SELECT NVL((SELECT SUM(pro_d_dati_provinciali.n_terariab_massofis_nonved)
                 FROM pro_d_dati_provinciali, pro_r_prospetto_provincia
                WHERE pro_d_dati_provinciali.id_prospetto_prov =
                      pro_r_prospetto_provincia.id_prospetto_prov
                  AND pro_r_prospetto_provincia.id_prospetto = prospetto_id),
               0)
      INTO nTerriabilMassononved
      FROM dual;
    writeLn('[get_n_terriabil_massononved] nTerriabilMassononved : ' ||
            nTerriabilMassononved);
    RETURN PCK_PRODIS_2012_UTILS.handle_number(nTerriabilMassononved);
  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_n_terriabil_massononved;

  FUNCTION get_n_lav_catescluse(prospetto_id IN NUMBER, flagArt18 IN CHAR)
    RETURN NUMBER IS
    nLavCatescluse NUMBER := 0;
  BEGIN
    /*
    somma del "N° lavoratori appartenenti a categorie escluse" di ogni provincia,
    considerando le categorie con FLAG ART 18 = 'NO'
    */
    IF (flagArt18 = 'NO') THEN
      SELECT NVL((SELECT SUM(PRO_D_CATEGORIE_ESCLUSE.N_LAV_APPARTART_CATEGORIA)
                   FROM PRO_D_CATEGORIE_ESCLUSE,
                        PRO_T_CATEGORIA_ESCLUSE,
                        PRO_R_PROSPETTO_PROVINCIA
                  WHERE PRO_D_CATEGORIE_ESCLUSE.ID_PROSPETTO_PROV =
                        pro_r_prospetto_provincia.id_prospetto_prov
                    AND pro_t_categoria_escluse.id_t_categoria_escluse =
                        pro_d_categorie_escluse.id_t_categorie_escluse
                    AND pro_t_categoria_escluse.ambito_categoria IN
                        ('E', 'D')
                    AND pro_r_prospetto_provincia.id_prospetto =
                        prospetto_id),
                 0)
        INTO nLavCatescluse
        FROM dual;
    ELSE
      SELECT NVL((SELECT SUM(PRO_D_CATEGORIE_ESCLUSE.N_LAV_APPARTART_CATEGORIA)
                   FROM PRO_D_CATEGORIE_ESCLUSE,
                        PRO_T_CATEGORIA_ESCLUSE,
                        PRO_R_PROSPETTO_PROVINCIA
                  WHERE PRO_D_CATEGORIE_ESCLUSE.ID_PROSPETTO_PROV =
                        pro_r_prospetto_provincia.id_prospetto_prov
                    AND pro_t_categoria_escluse.id_t_categoria_escluse =
                        pro_d_categorie_escluse.id_t_categorie_escluse
                    AND pro_t_categoria_escluse.ambito_categoria IN
                        ('E', 'C')
                    AND pro_r_prospetto_provincia.id_prospetto =
                        prospetto_id),
                 0)
        INTO nLavCatescluse
        FROM dual;
    END IF;
    writeLn('[get_n_lav_catescluse] nLavCatescluse : ' || nLavCatescluse);
    RETURN PCK_PRODIS_2012_UTILS.handle_number(nLavCatescluse);
  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_n_lav_catescluse;

  FUNCTION get_n_cat_prot_in_forza(prospetto_id IN NUMBER) RETURN NUMBER IS
    nCatProtInForza NUMBER := 0;
  BEGIN
    /*
    N° Categorie protette in forza (L.68/99 art.18) ->
    SOMMA di PRO_D_DATI_PROVINCIALI.N_CATE_PROT_FORZA di tutte le province.
    */
    SELECT SUM(pro_d_dati_provinciali.N_CATE_PROT_FORZA)
      INTO nCatProtInForza
      FROM pro_d_dati_provinciali, pro_r_prospetto_provincia
     WHERE pro_d_dati_provinciali.id_prospetto_prov =
           pro_r_prospetto_provincia.id_prospetto_prov
       AND pro_r_prospetto_provincia.id_prospetto = prospetto_id;
    writeLn('[get_n_cat_prot_in_forza] nCatProtInForza : ' ||
            nCatProtInForza);
    RETURN PCK_PRODIS_2012_UTILS.handle_number(nCatProtInForza);
  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_n_cat_prot_in_forza;

  FUNCTION get_cat_prot_in_forza_1(prospetto_id IN NUMBER) RETURN NUMBER IS
    catProtInForza1 NUMBER := 0;
  BEGIN
    catProtInForza1 := PCK_PRODIS_2012_UTILS.round_number((1 / 100) *
                                                          get_n_lav_in_forza_naz(prospetto_id));
    writeLn('[get_cat_prot_in_forza_1] catProtInForza1 : ' ||
            catProtInForza1);
    RETURN PCK_PRODIS_2012_UTILS.handle_number(catProtInForza1);
  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_cat_prot_in_forza_1;

  
  FUNCTION get_n_lav_part_time(prospetto_id IN NUMBER) RETURN NUMBER IS
    nLavPartTime NUMBER := 0;
  BEGIN
    /*
    somma del "N° lavoratori part time" di ogni provincia " "
    si considerano solo i partime
    con AMBITO_RIPROP_BC = 'R', ovvero i dipendenti partime e quelli
    in telelavoro
    */
    SELECT NVL((SELECT SUM(PRO_D_PART_TIME.N_PART_TIME)
                 FROM PRO_D_PART_TIME,
                      PRO_T_TIPO_RIPROP_PT,
                      PRO_R_PROSPETTO_PROVINCIA
                WHERE pro_r_prospetto_provincia.id_prospetto_prov =
                      pro_d_part_time.id_prospetto_prov
                  AND pro_r_prospetto_provincia.id_prospetto = prospetto_id
                  AND PRO_D_PART_TIME.ID_TIPO_RIPROP_PT =
                      PRO_T_TIPO_RIPROP_PT.ID_TIPO_RIPROP_PT
                  AND PRO_T_TIPO_RIPROP_PT.AMBITO_RIPROP_BC = 'R'),
               0)
      INTO nLavPartTime
      FROM dual;
    writeLn('[get_n_lav_part_time] nLavPartTime : ' || nLavPartTime);
    RETURN PCK_PRODIS_2012_UTILS.handle_number(nLavPartTime);
  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_n_lav_part_time;
  
  FUNCTION get_n_lav_intermittenti(prospetto_id IN NUMBER) RETURN NUMBER IS
    nLavIntermittenti NUMBER := 0;
  BEGIN
    /*
    somma del "N° lavoratori intermittenti" di ogni provincia - "
    IntermittentiRiproporzionati nazionali***"
    */
    SELECT NVL((SELECT SUM(PRO_D_PROV_INTERMITTENTI.N_INTERMITTENTI)
                 FROM PRO_D_PROV_INTERMITTENTI, PRO_R_PROSPETTO_PROVINCIA
                WHERE pro_r_prospetto_provincia.id_prospetto_prov =
                      PRO_D_PROV_INTERMITTENTI.id_prospetto_prov
                  AND pro_r_prospetto_provincia.id_prospetto = prospetto_id),
               0)
      INTO nLavIntermittenti
      FROM dual;
    writeLn('[get_n_lav_intermittenti] nLavIntermittenti : ' ||
            nLavIntermittenti);
    RETURN PCK_PRODIS_2012_UTILS.handle_number(nLavIntermittenti);
  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_n_lav_intermittenti;

  FUNCTION get_parttime_riproporzionati(prospetto_id IN NUMBER) RETURN NUMBER IS
    parttimeRiproporzionati NUMBER := 0;
  BEGIN
    /*
    'PartTimeRiproporzionati' si calcolano nel seguente modo:
    arrotondamento della somma di tutte le righe parttime di TUTTI i Q2 x il
    prospetto (Orario settimanale part time / Orario settimanale contrattuale *
    n_part_time)
    NB : per l'arrotondamento si prende la parte intera; alla parte intera si
    aggiunge 1 se la
    parte decimale è > 0,50, altrimenti non si aggiunge nulla.
    Cioè:
    1) si esegue questo calcolo su ogni dettaglio parttime di TUTTI i Q2 per il
    prospetto:
    (Orario settimanale part time / Orario settimanale contrattuale * n_part_time
    )
    2) si sommano tutti i risultati ottenuti al punto 1)
    3) si arrotonda il valore ottenuto al punto 2): per l'arrotondamento si
    prende la parte intera;
    alla parte intera si aggiunge 1 se la parte decimale è > 0,50, altrimenti non
    si aggiunge nulla

    Si considerano solo i partime con tipo AMBITO_RIPROP_BC = 'R'
    ovvero i dipendenti partime e quelli in telelavoro
    */
    SELECT ROUND(SUM((pt.orario_sett_part_time_min /
                     pt.orario_sett_contrattuale_min * pt.n_part_time)) - 0.001)
      INTO parttimeRiproporzionati
      FROM PRO_D_PART_TIME           pt,
           PRO_T_TIPO_RIPROP_PT      t,
           PRO_R_PROSPETTO_PROVINCIA r
     WHERE r.id_prospetto_prov = pt.id_prospetto_prov
       AND r.id_prospetto = prospetto_id
       AND pt.ID_TIPO_RIPROP_PT = t.ID_TIPO_RIPROP_PT
       AND t.AMBITO_RIPROP_BC = 'R';

    writeLn('[get_parttime_riproporzionati] parttimeRiproporzionati : ' ||
            parttimeRiproporzionati);
    RETURN PCK_PRODIS_2012_UTILS.handle_number(parttimeRiproporzionati);
  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_parttime_riproporzionati;

  FUNCTION get_diff_parttime_naz(prospetto_id IN NUMBER) RETURN NUMBER IS
    diffParttimeNaz NUMBER := 0;
  BEGIN
    diffParttimeNaz := get_n_lav_part_time(prospetto_id) -
                       get_parttime_riproporzionati(prospetto_id);
    writeLn('[get_diff_parttime_naz] diffParttimeNaz : ' ||
            diffParttimeNaz);
    RETURN PCK_PRODIS_2012_UTILS.handle_number(diffParttimeNaz);
  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_diff_parttime_naz;

  FUNCTION get_intermittenti_riproporz(prospetto_id IN NUMBER) RETURN NUMBER IS
    intermittentiRiproporzionati NUMBER := 0;
  BEGIN
    /*
    'IntermittentiRiproporzionati' si calcolano nel seguente modo:
    arrotondamento della somma di tutte le righe intermittenti
    di tutti Q2 x il prospetto (Orario settimanale svolto / Orario settimanale
    contrattuale * n_intermittenti)
    NB : per l'arrotondamento si prende la parte intera; alla parte intera
    si aggiunge 1 se la parte decimale è > 0,50, altrimenti non si aggiunge
    nulla.
    Cioè:
    1) si esegue questo calcolo su ogni dettaglio intermittente di TUTTI i Q2 per
    il prospetto:
    (Orario svolto intermittente / Orario settimanale contrattuale *
    n_intermittenti)
    2) si sommano tutti i risultati ottenuti al punto 1)
    3) si arrotonda il valore ottenuto al punto 2): per l'arrotondamento si
    prende
    la parte intera; alla parte intera si aggiunge 1 se
    la parte decimale è > 0,50, altrimenti non si aggiunge nulla
    */
    SELECT ROUND(SUM(inter.orario_settimanale_svolto /
                     inter.orario_settimanale_contrattual *
                     inter.n_intermittenti) - 0.001)
      INTO intermittentiRiproporzionati
      FROM pro_d_prov_intermittenti inter, pro_r_prospetto_provincia pp
     WHERE inter.id_prospetto_prov = pp.id_prospetto_prov
       AND pp.id_prospetto = prospetto_id;
    writeLn('[get_intermittenti_riproporz] intermittentiRiproporzionati : ' ||
            intermittentiRiproporzionati);
    RETURN PCK_PRODIS_2012_UTILS.handle_number(intermittentiRiproporzionati);
  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_intermittenti_riproporz;

  FUNCTION get_diff_intermit_naz(prospetto_id IN NUMBER) RETURN NUMBER IS
    diffIntermitNaz NUMBER := 0;
  BEGIN
    diffIntermitNaz := get_n_lav_intermittenti(prospetto_id) -
                       get_intermittenti_riproporz(prospetto_id);
    writeLn('[get_diff_intermit_naz] diffIntermitNaz : ' ||
            diffIntermitNaz);
    RETURN PCK_PRODIS_2012_UTILS.handle_number(diffIntermitNaz);
  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_diff_intermit_naz;

  FUNCTION get_n_telelavoro(prospetto_id IN NUMBER) RETURN NUMBER IS
    nTelelavoro NUMBER := 0;
  BEGIN

    SELECT NVL(
              (SELECT SUM(pro_d_dati_provinciali.n_telelavoro_ft)
                FROM pro_d_dati_provinciali, pro_r_prospetto_provincia
               WHERE pro_d_dati_provinciali.id_prospetto_prov =
                     pro_r_prospetto_provincia.id_prospetto_prov
                 AND pro_r_prospetto_provincia.id_prospetto = prospetto_id), 0)
      INTO nTelelavoro
      FROM dual;

    RETURN PCK_PRODIS_2012_UTILS.handle_number(nTelelavoro);
  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_n_telelavoro;

  FUNCTION get_telelav_riproporzionati(prospetto_id IN NUMBER) RETURN NUMBER IS
    parttimeRiproporzionati NUMBER := 0;
  BEGIN

    SELECT nvl(
                (SELECT ROUND(SUM(pt.orario_sett_part_time_min /
                                 pt.orario_sett_contrattuale_min * pt.n_part_time) - 0.001)
                  FROM PRO_D_PART_TIME           pt,
                       PRO_T_TIPO_RIPROP_PT      t,
                       PRO_R_PROSPETTO_PROVINCIA r
                 WHERE r.id_prospetto_prov = pt.id_prospetto_prov
                   AND r.id_prospetto = prospetto_id
                   AND pt.ID_TIPO_RIPROP_PT = t.ID_TIPO_RIPROP_PT
                   AND t.AMBITO_RIPROP_BC = 'T'), 0)
      INTO parttimeRiproporzionati
      FROM dual;

    writeLn('[get_parttime_riproporzionati] parttimeRiproporzionati : ' ||
            parttimeRiproporzionati);
    RETURN PCK_PRODIS_2012_UTILS.handle_number(parttimeRiproporzionati);
  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_telelav_riproporzionati;

  
  FUNCTION get_n_lav_base_computo_art_3(prospetto_id IN NUMBER) RETURN NUMBER IS
    nLavBaseComputoArt3 NUMBER := 0;
    nTotDipendenti      NUMBER := 0;
    nDisabiliInForzaBDC NUMBER := 0;
    nLavCatescluse      NUMBER := 0;
    nCatProtetInForza   NUMBER := 0;
    catProtetInForza1   NUMBER := 0;
    diffPartTime        NUMBER := 0;
    diffIntermittenti   NUMBER := 0;
    nTelelavoro         NUMBER := 0; 
    nTelelavoroRip      NUMBER := 0; 
  BEGIN
    /*
    Valore calcolato automaticamente nel seguente modo:
    'N° Lavoratori in forza nazionale' (calcolato al punto 1)I.
    - 'N° Disabili in forza (L.68/99 art.1) '(calcolato al punto 1)I.
    - 'CategorieEscluseDisabiliNazionali  (calcolato al punto 1)I.
    -  (il valore minimo tra 'N° Categorie protette in forza (L.68/99 art.18) ' e
    'CatProtetteInForza1% ')  (calcolati al punto 1)I.
    - 'DifferenzaParttimeNazionali' (calcolato al punto 1)I.
    - 'DifferenzaIntermittentiNazionali'(calcolato al punto 1)I.
    - llavoratori in telelavoro  
    */
    nTotDipendenti   := get_n_lav_in_forza_naz(prospetto_id);
    nDisabiliInForzaBDC := get_n_disabili_in_forza_bc(prospetto_id); 
    nLavCatescluse    := get_n_lav_catescluse(prospetto_id, 'NO');
    nCatProtetInForza := get_n_cat_prot_in_forza(prospetto_id);
    catProtetInForza1 := get_cat_prot_in_forza_1(prospetto_id);
    diffPartTime      := get_diff_parttime_naz(prospetto_id);
    diffIntermittenti := get_diff_intermit_naz(prospetto_id);
    nTelelavoro       := get_n_telelavoro(prospetto_id);             
    nTelelavoroRip    := get_telelav_riproporzionati(prospetto_id);  

    nLavBaseComputoArt3 := nTotDipendenti -
                           nDisabiliInForzaBDC -  
                           nLavCatescluse -
                           PCK_PRODIS_2012_UTILS.minimo(nCatProtetInForza, catProtetInForza1) -
                           diffPartTime -
                           diffIntermittenti -
                           nTelelavoroRip -       
                           nTelelavoro;           

    writeLn('[get_n_lav_base_computo_art_3] nLavBaseComputoArt3 : ' ||
            nLavBaseComputoArt3);

    RETURN PCK_PRODIS_2012_UTILS.handle_number(nLavBaseComputoArt3);
  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_n_lav_base_computo_art_3;

  FUNCTION get_n_lav_base_computo_art_18(prospetto_id IN NUMBER)
    RETURN NUMBER IS
    nLavBaseComputoArt18 NUMBER := 0;
    nTotDipendenti       NUMBER := 0;
    nLavCatescluse       NUMBER := 0;
    nCatProtetInForza    NUMBER := 0;
    catProtetInForza1    NUMBER := 0;
    diffPartTime         NUMBER := 0;
    diffIntermittenti    NUMBER := 0;
    nTelelavoro          NUMBER := 0;
    nDisabiliInForzaBDC  NUMBER := 0; 
    nTelelavoroRip       NUMBER := 0; 
  BEGIN
    /*
    Valore calcolato automaticamente nel seguente modo:
    'N° Lavoratori in forza nazionale' (calcolato al punto 1)I.
    - 'N° Disabili in forza (L.68/99 art.1) '(calcolato al punto 1)I.
    - 'CategorieEscluseDisabiliNazionali  (calcolato al punto 1)I.
    -  (il valore minimo tra 'N° Categorie protette in forza (L.68/99 art.18) ' e
    'CatProtetteInForza1% ')  (calcolati al punto 1)I.
    - 'DifferenzaParttimeNazionali' (calcolato al punto 1)I.
    - 'DifferenzaIntermittentiNazionali'(calcolato al punto 1)I.
    - lavoratori in telelavoro 
    */
    nTotDipendenti    := get_n_lav_in_forza_naz(prospetto_id);
    nDisabiliInForzaBDC := get_n_disabili_in_forza_bc(prospetto_id); 
    nLavCatescluse    := get_n_lav_catescluse(prospetto_id, 'SI');
    nCatProtetInForza := get_n_cat_prot_in_forza(prospetto_id);
    catProtetInForza1 := get_cat_prot_in_forza_1(prospetto_id);
    diffPartTime      := get_diff_parttime_naz(prospetto_id);
    diffIntermittenti := get_diff_intermit_naz(prospetto_id);
    nTelelavoro       := get_n_telelavoro(prospetto_id);             
    nTelelavoroRip    := get_telelav_riproporzionati(prospetto_id);  

    nLavBaseComputoArt18 := nTotDipendenti -
                            nDisabiliInForzaBDC -   
                            nLavCatescluse -
                            PCK_PRODIS_2012_UTILS.minimo(nCatProtetInForza,
                                                         catProtetInForza1) -
                            diffPartTime -
                            diffIntermittenti -
                            nTelelavoroRip -        
                            nTelelavoro;            
    writeLn('[get_n_lav_base_computo_art_18] nLavBaseComputoArt18 : ' ||
            nLavBaseComputoArt18);

    RETURN PCK_PRODIS_2012_UTILS.handle_number(nLavBaseComputoArt18);
  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_n_lav_base_computo_art_18;

  FUNCTION get_categoria_azienda(nLavBaseComputo IN NUMBER) RETURN CHAR IS
    categoriaAzienda CHAR := '';
    e_categoria_azienda EXCEPTION;
  BEGIN
    /*
    Categoria Azienda L.68/99 art.3 c.1 ->
    Valore calcolato automaticamente in base al 'N° Lavoratori in forza nazionale
    (base computo)' nazionale, nel seguente modo:
    Se SOMMA(PRO_D_DATI_PROVINCIALI.BASE_COMPUTO) >= 15 e <= 35: la 'categoria
    azienda' è C;
    Se SOMMA((PRO_D_DATI_PROVINCIALI.BASE_COMPUTO) >= 36 e <= 50: la 'categoria
    azienda' è B;
    Se SOMMA(PRO_D_DATI_PROVINCIALI.BASE_COMPUTO) >= 51: la 'categoria azienda' è
    A.
    Se categoria Azienda è A e tipologia Dichiarante è diverso da C (datore di
    lavoro pubblico) ed è stata
    Indicata una "autocompensazione = S" in almeno un dettaglio provinciale, il
    sistema segnala incongruenza.
    */
    IF (nLavBaseComputo >= 15 AND nLavBaseComputo <= 35) THEN
      categoriaAzienda := 'C';
    ELSIF (nLavBaseComputo >= 36 AND nLavBaseComputo <= 50) THEN
      categoriaAzienda := 'B';
    ELSIF (nLavBaseComputo >= 51) THEN
      categoriaAzienda := 'A';
    ELSE
      --categoriaAzienda := 'Z';
      categoriaAzienda := NULL;
    END IF;
    writeLn('[get_categoria_azienda] categoriaAzienda : ' ||
            categoriaAzienda);
    RETURN categoriaAzienda;
  EXCEPTION
    WHEN e_categoria_azienda THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RAISE e_categoria_azienda;
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_categoria_azienda;

  FUNCTION get_quota_riserva_disabili(prospetto_id     IN NUMBER,
                                      categoriaAzienda IN CHAR) RETURN NUMBER IS
    quotaRiservaDisabili NUMBER := 0;
    nLavBaseComputoArt3  NUMBER := 0;
  BEGIN
    /*
    Quota di riserva disabili ->
    valore calcolato automaticamente sulla base della "Categoria Azienda" (
    calcolata a livello nazionale) corrispondente:
    Se "categoria azienda" = C (Da 15 a 35 dipendenti): il valore da mettere è: 1
    Se "categoria azienda" = B (Da 36 a 50 dipendenti): il valore da mettere è: 2
    Se "categoria azienda" = A (> di 50 dipendenti): il valore da mettere è: 7%
    del "N° lavoratori (Base computo art. 3)
    Nb nel calcolo percentuale, i decimali vengono arrotondati:
    Se > 0,50 si aggiunge 1 intero;
    Se <= 0,50 non si aggiunge niente)
    */
    nLavBaseComputoArt3 := get_n_lav_base_computo_art_3(prospetto_id);
    IF (categoriaAzienda = 'A') THEN
      quotaRiservaDisabili := PCK_PRODIS_2012_UTILS.round_number((nLavBaseComputoArt3 *
                                                                 (7 / 100)));
    ELSIF (categoriaAzienda = 'B') THEN
      quotaRiservaDisabili := 2;
    ELSIF (categoriaAzienda = 'C') THEN
      quotaRiservaDisabili := 1;
    ELSIF (categoriaAzienda = 'Z') THEN
      quotaRiservaDisabili := 0;
    END IF;
    writeLn('[get_quota_riserva_disabili] quotaRiservaDisabili : ' ||
            quotaRiservaDisabili);
    RETURN PCK_PRODIS_2012_UTILS.handle_number(quotaRiservaDisabili);
  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_quota_riserva_disabili;

  FUNCTION get_quota_riserva_art_18(prospetto_id IN NUMBER) RETURN NUMBER IS
    quotaRiservaArt18    NUMBER := 0;
    nLavBaseComputoArt18 NUMBER := 0;
  BEGIN
    /*
    Quota di riserva Art. 18
    valore calcolato automaticamente con: 1% della 'base computo' nazionale nel
    seguente modo:
    1) Si prende 'N° lavoratori (Base computo art. 18)'
    2) se è <= 50: il valore da mettere è 0;
    se è >= 51 dipendenti e <= 150: il valore da mettere è 1;
    Se è >= 151 dipendenti: il valore è 1% di 'N° lavoratori (Base computo art.
    18)'
    NB :
    (i numeri decimali si arrotondano in qst modo: Se > 0,50 si aggiunge 1 intero
    ; Se <= 0,50 non si aggiunge niente)
    */
    nLavBaseComputoArt18 := get_n_lav_base_computo_art_18(prospetto_id);
    IF (nLavBaseComputoArt18 <= 50) THEN
      quotaRiservaArt18 := 0;
    ELSIF (nLavBaseComputoArt18 > 51 AND nLavBaseComputoArt18 <= 150) THEN
      quotaRiservaArt18 := 1;
    ELSE
      quotaRiservaArt18 := (1 / 100) * nLavBaseComputoArt18;
    END IF;
    quotaRiservaArt18 := PCK_PRODIS_2012_UTILS.round_number(quotaRiservaArt18);
    RETURN PCK_PRODIS_2012_UTILS.handle_number(quotaRiservaArt18);
  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_quota_riserva_art_18;

  FUNCTION get_n_posizioni_esonerate(prospetto_id IN NUMBER) RETURN NUMBER IS
    nPosizioniEsonerate NUMBER := 0;
  BEGIN
    /* valore calcolato automaticamente con somma dei lavoratori in esonero
    dichiarati con stato "Approvato" */
    /*
    N° posizioni esonerate -> PRO_D_PROV_ESONERO.N_LAVORATORI_ESONERO (come '
    lavoratori in esonero' di Elenchi provinciali)
    */
    SELECT NVL((SELECT SUM(pro_d_prov_esonero.n_lavoratori_esonero)
                 FROM PRO_D_PROV_ESONERO, pro_r_prospetto_provincia
                WHERE pro_d_prov_esonero.id_prospetto_prov =
                      pro_r_prospetto_provincia.id_prospetto_prov
                  AND pro_r_prospetto_provincia.id_prospetto = prospetto_id
                  AND PRO_D_PROV_ESONERO.id_t_stato_concessione = 1),
               0) + 
           NVL((SELECT SUM(PRO_D_PROV_ESONERO_AUTOCERT.N_LAV_ESONERO_AUTOCERT)
                 FROM PRO_D_PROV_ESONERO_AUTOCERT,
                      pro_r_prospetto_provincia
                WHERE PRO_D_PROV_ESONERO_AUTOCERT.id_prospetto_prov =
                      pro_r_prospetto_provincia.id_prospetto_prov
                  AND pro_r_prospetto_provincia.id_prospetto = prospetto_id),
               0)
      INTO nPosizioniEsonerate
      FROM dual;
    writeLn('[get_n_posizioni_esonerate] nPosizioniEsonerate : ' ||
            nPosizioniEsonerate);
    RETURN PCK_PRODIS_2012_UTILS.handle_number(nPosizioniEsonerate);
  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_n_posizioni_esonerate;

  
  FUNCTION get_n_disabili_in_forza(prospetto_id IN NUMBER) RETURN NUMBER IS
    nDisabiliInForza NUMBER := 0;
    nDisabiliPt      NUMBER := 0;
  BEGIN
    /*
    N° Disabili in forza (L.68/99 art.1) ->
    SOMMA di
    (
    PRO_D_DATI_PROVINCIALI.N_DISABILI_IN_FORZA
    +  PRO_D_DATI_PROVINCIALI.N_CENTRAL_TELEFO_NONVEDENTI
    + PRO_D_DATI_PROVINCIALI.N_TERARIAB_MASSOFIS_NONVED
    + PRO_D_DATI_PROVINCIALI.N_SOMMINISTRATI_FT
    + PRO_D_DATI_PROVINCIALI.N_CONVENZIONI_12BIS_14_FT
    )
    + somma (disabili partime dipendenti e non seconla la regola:
    se l'orario di lavoro è > 50% orario contrattualizzato ==> 1
    altrimenti ==> quota riproporzionata in funzione dell'orario)

    di tutte le province.
    */
    SELECT SUM(nvl(pro_d_dati_provinciali.N_DISABILI_IN_FORZA,0)) +
           SUM(nvl(pro_d_dati_provinciali.N_CENTRAL_TELEFO_NONVEDENTI,0)) +
           SUM(nvl(pro_d_dati_provinciali.N_TERARIAB_MASSOFIS_NONVED,0)) +
           SUM(nvl(pro_d_dati_provinciali.N_SOMMINISTRATI_FT,0)) +
           SUM(nvl(pro_d_dati_provinciali.N_CONVENZIONI_12BIS_14_FT,0))
      INTO nDisabiliInForza
      FROM pro_d_dati_provinciali, pro_r_prospetto_provincia
     WHERE pro_d_dati_provinciali.id_prospetto_prov =
           pro_r_prospetto_provincia.id_prospetto_prov
       AND pro_r_prospetto_provincia.id_prospetto = prospetto_id;

    nDisabiliPt := get_disabili_riproporz(prospetto_id);

    nDisabiliInForza := nDisabiliInForza + nDisabiliPt;

    writeLn('[get_n_disabili_in_forza] nDisabiliInForza : ' ||
            nDisabiliInForza);
    RETURN PCK_PRODIS_2012_UTILS.handle_number(nDisabiliInForza);
  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_n_disabili_in_forza;


  FUNCTION get_disabili_riproporz(prospetto_id IN NUMBER) RETURN NUMBER IS
    nDisabiliPt      NUMBER := 0;
  BEGIN

    SELECT nvl(ROUND(SUM(decode(round((pt.orario_sett_part_time_min /
                                  pt.orario_sett_contrattuale_min) - 0.001),
                            1,
                            1,
                            (pt.orario_sett_part_time_min /
                            pt.orario_sett_contrattuale_min)
                           )* pt.n_part_time
                     ) -0.001
                 ), 0)
      INTO nDisabiliPt
      FROM PRO_D_PART_TIME           pt,
           PRO_T_TIPO_RIPROP_PT      t,
           PRO_R_PROSPETTO_PROVINCIA r
     WHERE pt.ID_TIPO_RIPROP_PT = t.ID_TIPO_RIPROP_PT
       AND r.ID_PROSPETTO_PROV = pt.ID_PROSPETTO_PROV
       AND r.id_prospetto = prospetto_id
       AND t.AMBITO_RIPROP = 'R'; -- tutti i disabili dipendenti e non

    writeLn('[get_n_disabili_in_forza] nDisabiliPt : ' ||  nDisabiliPt);

    RETURN PCK_PRODIS_2012_UTILS.handle_number(nDisabiliPt);
  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_disabili_riproporz;
  
  FUNCTION get_n_disabili_in_forza_bc(prospetto_id IN NUMBER) RETURN NUMBER IS
    nDisabiliInForza NUMBER := 0;
    nDisabiliPt      NUMBER := 0;
  BEGIN
    /*
    N° Disabili in forza (L.68/99 art.1) per base computo ->
    solo disabili dipendenti
    di tutte le province.
    */
    SELECT SUM(pro_d_dati_provinciali.N_DISABILI_IN_FORZA) +
           SUM(pro_d_dati_provinciali.N_CENTRAL_TELEFO_NONVEDENTI) +
           SUM(pro_d_dati_provinciali.N_TERARIAB_MASSOFIS_NONVED)
      INTO nDisabiliInForza
      FROM pro_d_dati_provinciali, pro_r_prospetto_provincia
     WHERE pro_d_dati_provinciali.id_prospetto_prov =
           pro_r_prospetto_provincia.id_prospetto_prov
       AND pro_r_prospetto_provincia.id_prospetto = prospetto_id;

    SELECT nvl(
              (SELECT SUM(pt.n_part_time)

                FROM PRO_D_PART_TIME           pt,
                     PRO_T_TIPO_RIPROP_PT      t,
                     PRO_R_PROSPETTO_PROVINCIA r
               WHERE r.id_prospetto_prov = pt.id_prospetto_prov
                 AND r.id_prospetto = prospetto_id
                 AND pt.ID_TIPO_RIPROP_PT = t.ID_TIPO_RIPROP_PT
                 AND t.AMBITO_RIPROP_BC = 'I'), 0)
      INTO nDisabiliPt
      FROM dual; -- solo i dipendenti contati non riproporzionati

    nDisabiliInForza := nDisabiliInForza + nDisabiliPt;

    writeLn('[get_n_disabili_in_forza_bc] nDisabiliInForza : ' ||
            nDisabiliInForza);
    RETURN PCK_PRODIS_2012_UTILS.handle_number(nDisabiliInForza);
  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_n_disabili_in_forza_bc;
  
  FUNCTION get_n_cat_prot_in_forz_17_1_00(prospetto_id IN NUMBER)
    RETURN NUMBER IS
    nCatProtInForza NUMBER := 0;
  BEGIN
    /*
    N° Categorie protette in forza (L.68/99 art.18) ->
    SOMMA di PRO_D_DATI_PROVINCIALI.N_CATE_PROT_FORZA di tutte le province.
    */
    SELECT SUM(pro_d_dati_provinciali.N_CATE_PROT_FORZA_A_17_01_2000)
      INTO nCatProtInForza
      FROM pro_d_dati_provinciali, pro_r_prospetto_provincia
     WHERE pro_d_dati_provinciali.id_prospetto_prov =
           pro_r_prospetto_provincia.id_prospetto_prov
       AND pro_r_prospetto_provincia.id_prospetto = prospetto_id;
    writeLn('[get_n_cat_prot_in_forz_17_1_00] nCatProtInForza : ' ||
            nCatProtInForza);
    RETURN PCK_PRODIS_2012_UTILS.handle_number(nCatProtInForza);
  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_n_cat_prot_in_forz_17_1_00;

  FUNCTION get_valore_abbatt_naz(quotaRiservaArt18   IN NUMBER,
                                 esuberoArt18        IN NUMBER,
                                 catProtInForz170100 IN NUMBER) RETURN NUMBER IS
    minimo NUMBER := 0;
  BEGIN
    IF (PCK_PRODIS_2012_UTILS.minimo(quotaRiservaArt18, esuberoArt18) =
       quotaRiservaArt18) THEN
      minimo := quotaRiservaArt18;
    ELSE
      minimo := esuberoArt18;
    END IF;
    IF (PCK_PRODIS_2012_UTILS.minimo(minimo, catProtInForz170100) = minimo) THEN
      minimo := minimo;
    ELSE
      minimo := catProtInForz170100;
    END IF;
    writeLn('[get_valore_abbatt_naz] minimo : ' || minimo);
    RETURN minimo;
  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_valore_abbatt_naz;
  FUNCTION get_esuberi_art_18(prospetto_id IN NUMBER) RETURN NUMBER IS
    esuberiArt18 NUMBER := 0;
  BEGIN
    /* "N° Categorie protette in forza (L.68/99 art.18)" nazionali - "Quota di
    riserva art. 18" nazionale > 0) */
    esuberiArt18 := get_n_cat_prot_in_forza(prospetto_id) -
                    get_quota_riserva_art_18(prospetto_id);
    writeLn('[get_esuberi_art_18] esuberiArt18 : ' || esuberiArt18);
    RETURN PCK_PRODIS_2012_UTILS.HANDLE_NUMBER(esuberiArt18);
  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_esuberi_art_18;

  FUNCTION get_scoperture_disabili_naz(prospetto_id IN NUMBER) RETURN NUMBER IS
    scopertureDisabiliNaz NUMBER := 0;
    nLavBaseComputoArt3   NUMBER := 0;
    categoriaAzienda      CHAR := '';
    quotaRiservaDisabili  NUMBER := 0;
  BEGIN
    nLavBaseComputoArt3   := get_n_lav_base_computo_art_3(prospetto_id);
    categoriaAzienda      := get_categoria_azienda(nLavBaseComputoArt3);
    quotaRiservaDisabili  := get_quota_riserva_disabili(prospetto_id,
                                                        categoriaAzienda);
    scopertureDisabiliNaz := quotaRiservaDisabili -
                             get_n_posizioni_esonerate(prospetto_id) -
                             get_n_disabili_in_forza(prospetto_id);
    writeLn('[get_scoperture_disabili_naz] scopertureDisabiliNaz : ' ||
            scopertureDisabiliNaz);
    RETURN PCK_PRODIS_2012_UTILS.HANDLE_NUMBER(scopertureDisabiliNaz);
  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_scoperture_disabili_naz;

  FUNCTION get_n_scoperture_disabili(prospetto_id IN NUMBER) RETURN NUMBER IS
    nScopertureDisabili  NUMBER := 0;
    nLavBaseComputoArt3  NUMBER := 0;
    categoriaAzienda     CHAR := '';
    catProtInForz170100  NUMBER := 0;
    esuberiArt18         NUMBER := 0;
    quotaRiservaArt18    NUMBER := 0;
    quotaRiservaDisabili NUMBER := 0;
    abbatt_naz           NUMBER := 0;
  BEGIN
    nLavBaseComputoArt3  := get_n_lav_base_computo_art_3(prospetto_id);
    categoriaAzienda     := get_categoria_azienda(nLavBaseComputoArt3);
    quotaRiservaDisabili := get_quota_riserva_disabili(prospetto_id,
                                                       categoriaAzienda);
    /*
    Valore calcolato automaticamente nel seguente modo:
    1) innanzitutto si determina se ci sono scoperture disabili nel seguente modo
    :
    "N° Scoperture disabili (L.68/99 art.1)" nazionali =
    ("Quota riserva disabili" nazionale " "N° posizioni esonerate" nazionale - "
    N° Disabili in forza (L.68/99 art.1)" nazionale)
    */
    nScopertureDisabili := quotaRiservaDisabili -
                           get_n_posizioni_esonerate(prospetto_id) -
                           get_n_disabili_in_forza(prospetto_id);
    /*
    Se "N° Scoperture disabili (L.68/99 art.1)" nazionali > 0, cioè ci sono
    scoperture disabili, si prosegue con i passi successivi
    */
    IF (nScopertureDisabili > 0) THEN
      catProtInForz170100 := get_n_cat_prot_in_forz_17_1_00(prospetto_id);
      esuberiArt18        := get_esuberi_art_18(prospetto_id);
      IF (catProtInForz170100 > 0 AND esuberiArt18 > 0) THEN
        /*
        2) si verificano le seguenti condizioni:
        a) cat. Protette in forza al 17/01/2000 > 0 e esuberi di art. 18
        (cioè:  somma del "N° categorie protette in forza al 17/01/2000" di ogni
        provincia è > 0
        E "N° Categorie protette in forza (L.68/99 art.18)" nazionali - "Quota di
        riserva art. 18" nazionale > 0)
        le "scoperture disabili art. 1 nazionali" sono date da:
        "scoperture disabili art. 1 nazionali" calcolate al punto 1) - il valore
        di abbattimento nazionale e cioè:
        "scoperture disabili art. 1 nazionali" = ("Quota riserva disabili"
        nazionale " "N° posizioni esonerate" nazionale - "N° Disabili in forza (
        L.68/99 art.1)" nazionale)
        - (il valore minimo tra:
        La quota di riserva art. 18 (cioè "Quota di riserva art. 18" nazionale)
        e l'esubero art. 18 (cioè "Quota di riserva art. 18" nazionale - N°
        Categorie protette in forza (L.68/99 art.18)" nazionali)
        e le cat. Art.18 in forza al 17012000 (cioè somma del "N° categorie
        protette in forza al 17/01/2000" di ogni provincia)
        */
        quotaRiservaArt18 := get_quota_riserva_art_18(prospetto_id);
        abbatt_naz        := get_valore_abbatt_naz(quotaRiservaArt18,
                                                   esuberiArt18,
                                                   catProtInForz170100);
        IF (abbatt_naz > nScopertureDisabili) THEN
          abbatt_naz := nScopertureDisabili;
        END IF;
        nScopertureDisabili := nScopertureDisabili - abbatt_naz;
      ELSIF (catProtInForz170100 = 0 OR esuberiArt18 <= 0) THEN
        /*
        b) cat. Protette in forza al 17/01/2000 = 0 oppure non ci sono esuberi di
        art. 18:
        le "scoperture disabili art. 1" sono date da:
        ("Quota riserva disabili" nazionale " "N° posizioni esonerate" nazionale
        - "N° Disabili in forza (L.68/99 art.1)" nazionale)
        */
        nScopertureDisabili := quotaRiservaDisabili -
                               get_n_posizioni_esonerate(prospetto_id) -
                               get_n_disabili_in_forza(prospetto_id);
      END IF;
      --      else
      --        /*
      --        NB il valore di "n° scoperture disabili" non può essere negativo,
      -- pertanto, se il valore risultante dai seguenti calcoli è < 0, il valore
      -- da inserire sarà sempre 0.
      --        */
      --        nScopertureDisabili := 0;
      --        --xxxxxxxxxxx
    END IF;
    writeLn('[get_n_scoperture_disabili] nScopertureDisabili : ' ||
            nScopertureDisabili);
    RETURN nScopertureDisabili;
  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_n_scoperture_disabili;

  FUNCTION get_n_scoperture_cat_prot(prospetto_id IN NUMBER) RETURN NUMBER IS
    nScopertureCatProt NUMBER := 0;
    quotaRiservaArt18  NUMBER := 0;
    nCatProtInForza    NUMBER := 0;
  BEGIN
    /*
    N° Scoperture categorie protette (L.68/99 art.18) ->
    Valore calcolato automaticamente nel seguente modo:
    'quota di riserva Art. 18'  nazionale
    - 'N° Categorie protette in forza (L.68/99 art.18)'  nazionale
    (NB il valore di 'n° scoperture categorie protette' non può essere negativo,
    pertanto, se il valore risultante dai seguenti calcoli è < 0,
    il valore da inserire sarà sempre 0.)
    */
    quotaRiservaArt18  := get_quota_riserva_art_18(prospetto_id);
    nCatProtInForza    := get_n_cat_prot_in_forza(prospetto_id);
    nScopertureCatProt := quotaRiservaArt18 - nCatProtInForza;
    writeLn('[get_n_scoperture_cat_prot] nScopertureCatProt : ' ||
            nScopertureCatProt);
    RETURN nScopertureCatProt;
  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_n_scoperture_cat_prot;
  FUNCTION get_sospensioni_in_corso(prospetto_id IN NUMBER) RETURN CHAR IS
    sospensioniInCorso       CHAR := '';
    flgSospensioniInCorsoNaz CHAR := '';
  BEGIN
    /*
    Sospensioni in corso
    Valore calcolato automaticamente nel seguente modo:
    se nel quadro 1 " dati prospetto il flag "Sospensione a carattere nazionale"
    = 'S'
    -> Viene inserito 'S'
    altrimenti
    -> viene inserito 'N'.
    */
    SELECT flg_sospensione_per_mobilita
      INTO flgSospensioniInCorsoNaz
      FROM pro_d_prospetto rn
     WHERE rn.id_prospetto = prospetto_id;
    IF (flgSospensioniInCorsoNaz IS NOT NULL AND
       flgSospensioniInCorsoNaz = 'S') THEN
      sospensioniInCorso := 'S';
    ELSE
      sospensioniInCorso := 'N';
    END IF;
    writeLn('[get_sospensioni_in_corso] sospensioniInCorso : ' ||
            sospensioniInCorso);
    RETURN sospensioniInCorso;
  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_sospensioni_in_corso;

  FUNCTION get_abbatt_pc_naz(prospetto_id IN NUMBER) RETURN NUMBER IS
    abbatt_pc_naz         NUMBER := 0;
    quotaRiservaArt18     NUMBER := 0;
    esuberoArt18          NUMBER := 0;
    catProtInForz170100   NUMBER := 0;
    abbattimentoNaz       NUMBER := 0;
    scopertureDisabiliNaz NUMBER := 0;
  BEGIN
    quotaRiservaArt18     := get_quota_riserva_art_18(prospetto_id);
    esuberoArt18          := get_esuberi_art_18(prospetto_id);
    catProtInForz170100   := get_n_cat_prot_in_forz_17_1_00(prospetto_id);
    abbattimentoNaz       := get_valore_abbatt_naz(quotaRiservaArt18,
                                                   esuberoArt18,
                                                   catProtInForz170100);
    scopertureDisabiliNaz := get_scoperture_disabili_naz(prospetto_id);
    IF (abbattimentoNaz > scopertureDisabiliNaz) THEN
      abbattimentoNaz := scopertureDisabiliNaz;
    END IF;
    IF (scopertureDisabiliNaz IS NOT NULL AND scopertureDisabiliNaz > 0) THEN
      abbatt_pc_naz := (abbattimentoNaz / scopertureDisabiliNaz) * 100;
    ELSE
      abbatt_pc_naz := 0;
    END IF;
    writeLn('[get_abbatt_pc_naz] abbatt_pc_naz : ' || abbatt_pc_naz);
    RETURN PCK_PRODIS_2012_UTILS.handle_number(abbatt_pc_naz);
  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_abbatt_pc_naz;
END PCK_PRODIS_2012_NAZ_UTILS;
/

CREATE OR REPLACE PACKAGE BODY PCK_PRODIS_2012_PROV_UTILS AS

  PROCEDURE writeLn(str VARCHAR2) IS
  BEGIN
    IF log_enable = TRUE THEN
      dbms_output.put_line(to_char(sysdate, 'dd/mm/yy hh24:mi:ss') || '-' || str);
    END IF;
  END writeLn;

  PROCEDURE setLogEnable(attiva BOOLEAN) IS
  BEGIN
    log_enable := attiva;
    IF log_enable = TRUE THEN
      dbms_output.enable(999999);
      dbms_output.put_line('ABILITATA SESSIONE DI LOG!');
      dbms_output.put_line('RICORDARSI DI DISABILITARLA AL TERMINE CON PCK_PRODIS_2012.setLogEnable(false)');
    END IF;
  END setLogEnable;

  FUNCTION isLogEnable RETURN BOOLEAN IS
  BEGIN
    RETURN log_enable;
  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END isLogEnable;

  FUNCTION get_n_lav_dip_prov(prospetto_prov_id IN NUMBER) RETURN NUMBER IS
    nLavDipProv NUMBER := 0;
  BEGIN

    /*
    NLavoratDipendenti_PV
    - PRO_D_DATI_PROVINCIALI.N_TOTALE_LAVORAT_DIPENDENTI per il Quadro 2 relativo.
    */

    SELECT NVL((SELECT N_TOTALE_LAVORAT_DIPENDENTI
                 FROM PRO_D_DATI_PROVINCIALI
                WHERE PRO_D_DATI_PROVINCIALI.ID_PROSPETTO_PROV =
                      prospetto_prov_id),
               0)
      INTO nLavDipProv
      FROM dual;

    writeLn('[get_n_lav_dip_prov] nLavDipProv ' || nLavDipProv);

    RETURN PCK_PRODIS_2012_UTILS.handle_number(nLavDipProv);

  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_n_lav_dip_prov;

  FUNCTION get_n_catprotforza_170100_pv(prospetto_prov_id IN NUMBER)
    RETURN NUMBER IS
    nCatProtInForza170100 NUMBER := 0;
  BEGIN

    /*
    NcatProtetteal17012000_PV
    - PRO_D_DATI_PROVINCIALI.N_CATE_PROT_FORZA_AL_17012000 per il Quadro 2 relativo.
    */
    SELECT NVL(N_CATE_PROT_FORZA_A_17_01_2000, 0)
      INTO nCatProtInForza170100
      FROM pro_d_dati_provinciali
     WHERE id_prospetto_prov = prospetto_prov_id;
    writeLn('[get_n_catprotforza_170100_pv] nCatProtInForza170100 ' ||
            nCatProtInForza170100);

    RETURN PCK_PRODIS_2012_UTILS.handle_number(nCatProtInForza170100);

  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_n_catprotforza_170100_pv;

  FUNCTION get_cat_escl_disab_prov(prospetto_prov_id IN NUMBER) RETURN NUMBER IS
    catEsclDisab NUMBER := 0;
  BEGIN

    /*
    CategorieEscluseDisabili_PV
    - SOMMA(PRO_D_CATEGORIE_ESCLUSE.N_LAV_APPARTART_CATEGORIA)  per il Quadro 2 relativo,
    considerando solo ID_T_CATEGORIE_ESCLUSE tra quelli con AMBITO 'E' o 'D'.
    */

    SELECT nvl((SELECT sum(N_LAV_APPARTART_CATEGORIA)
                 FROM PRO_D_CATEGORIE_ESCLUSE, PRO_T_CATEGORIA_ESCLUSE
                WHERE PRO_T_CATEGORIA_ESCLUSE.ID_T_CATEGORIA_ESCLUSE =
                      PRO_D_CATEGORIE_ESCLUSE.ID_T_CATEGORIE_ESCLUSE
                  AND PRO_T_CATEGORIA_ESCLUSE.AMBITO_CATEGORIA IN
                      ('E', 'D')
                  AND PRO_D_CATEGORIE_ESCLUSE.ID_PROSPETTO_PROV =
                      prospetto_prov_id),
               0)
      INTO catEsclDisab
      FROM dual;
    writeLn('[get_cat_escl_disab_prov] catEsclDisab ' || catEsclDisab);

    RETURN PCK_PRODIS_2012_UTILS.handle_number(catEsclDisab);

  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_cat_escl_disab_prov;

  FUNCTION get_cat_escl_cat_prot_prov(prospetto_prov_id IN NUMBER)
    RETURN NUMBER IS
    catEsclCatProt NUMBER := 0;
  BEGIN

    /*
    CategorieEscluseCatProtette_PV
    - SOMMA(PRO_D_CATEGORIE_ESCLUSE.N_LAV_APPARTART_CATEGORIA)  per il Quadro 2 relativo,
    Considerando ID_T_CATEGORIE_ESCLUSE tra quelli con FLG 'E' o 'C')
    */

    SELECT nvl((SELECT sum(N_LAV_APPARTART_CATEGORIA)
                 FROM PRO_D_CATEGORIE_ESCLUSE, PRO_T_CATEGORIA_ESCLUSE
                WHERE PRO_T_CATEGORIA_ESCLUSE.ID_T_CATEGORIA_ESCLUSE =
                      PRO_D_CATEGORIE_ESCLUSE.ID_T_CATEGORIE_ESCLUSE
                  AND PRO_T_CATEGORIA_ESCLUSE.AMBITO_CATEGORIA IN
                      ('E', 'C')
                  AND PRO_D_CATEGORIE_ESCLUSE.ID_PROSPETTO_PROV =
                      prospetto_prov_id),
               0)
      INTO catEsclCatProt
      FROM dual;
    writeLn('[get_cat_escl_cat_prot_prov] catEsclCatProt ' ||
            catEsclCatProt);

    RETURN PCK_PRODIS_2012_UTILS.handle_number(catEsclCatProt);

  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_cat_escl_cat_prot_prov;

  FUNCTION get_n_lav_part_time_prov(prospetto_prov_id IN NUMBER)
    RETURN NUMBER IS
    nLavPartTime NUMBER := 0;
  BEGIN

    /*
    NLavoratoriParttime_PV
    - (SOMMA(PRO_D_PARTTIME.N_PART_TIME)  per il Quadro 2 relativo.
    */

    SELECT nvl((SELECT sum(pt.N_PART_TIME)
                 FROM PRO_D_PART_TIME pt, PRO_T_TIPO_RIPROP_PT t
                WHERE pt.ID_PROSPETTO_PROV = prospetto_prov_id
                  AND pt.ID_TIPO_RIPROP_PT = t.ID_TIPO_RIPROP_PT
                  AND t.AMBITO_RIPROP_BC = 'R'),
               0)
      INTO nLavPartTime
      FROM dual;
    writeLn('[get_n_lav_part_time_prov] nLavPartTime ' || nLavPartTime);

    RETURN PCK_PRODIS_2012_UTILS.handle_number(nLavPartTime);

  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_n_lav_part_time_prov;

  FUNCTION get_part_time_riproporz(prospetto_prov_id IN NUMBER) RETURN NUMBER IS
    partTimeRiproporz NUMBER := 0;
  BEGIN

    /*
    Part-timeRiproporzionati_PV
    - arrotondamento della somma di tutte le righe parttime per il Quadro 2 relativo.
    (Orario settimanale part time / Orario settimanale contrattuale * n_part_time)

    nb per l'arrotondamento si prende la parte intera; alla parte intera si aggiunge 1
    se la parte decimale è > 0,50, altrimenti non si aggiunge nulla.

    cioè:
    select round(sum(pt1.orario_sett_part_time_min/pt1.orario_sett_contrattuale_min * pt1.n_part_time)-0.001)
    from pro_d_part_time pt1 where pt1.id_prospetto_prov  IN (ID_PROSPETTO_PROV per quel Q2)
    */

    SELECT nvl(
              (SELECT round(sum(nvl(pt.orario_sett_part_time_min /
                               pt.orario_sett_contrattuale_min * pt.n_part_time ,0)
                               ) - 0.001
                          )
                FROM PRO_D_PART_TIME pt, PRO_T_TIPO_RIPROP_PT t
               WHERE pt.ID_PROSPETTO_PROV = prospetto_prov_id
                 AND pt.ID_TIPO_RIPROP_PT = t.ID_TIPO_RIPROP_PT
                 AND t.AMBITO_RIPROP_BC = 'R') ,0)
       INTO partTimeRiproporz
       FROM dual;

    writeLn('[get_part_time_riproporz] partTimeRiproporz ' ||
            partTimeRiproporz);

    RETURN PCK_PRODIS_2012_UTILS.handle_number(partTimeRiproporz);

  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_part_time_riproporz;

  FUNCTION get_diff_part_time_prov(prospetto_prov_id IN NUMBER) RETURN NUMBER IS
    diffPartTimeProv NUMBER := 0;
  BEGIN

    /*
    DifferenzaParttime_PV
    - 'NLavoratoriParttime_PVi'- ParttimeRiproporzionati_PVi')
    */

    diffPartTimeProv := get_n_lav_part_time_prov(prospetto_prov_id) -
                        get_part_time_riproporz(prospetto_prov_id);
    writeLn('[get_diff_part_time_prov] diffPartTimeProv ' ||
            diffPartTimeProv);

    RETURN PCK_PRODIS_2012_UTILS.handle_number(diffPartTimeProv);

  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_diff_part_time_prov;

  FUNCTION get_n_lav_telelav_pt_prov(prospetto_prov_id IN NUMBER)
    RETURN NUMBER IS
    nLavPartTime NUMBER := 0;
  BEGIN

    SELECT nvl((SELECT sum(pt.N_PART_TIME)
                 FROM PRO_D_PART_TIME pt, PRO_T_TIPO_RIPROP_PT t
                WHERE pt.ID_PROSPETTO_PROV = prospetto_prov_id
                  AND pt.ID_TIPO_RIPROP_PT = t.ID_TIPO_RIPROP_PT
                  AND t.AMBITO_RIPROP_BC = 'T'),
               0)
      INTO nLavPartTime
      FROM dual;
    writeLn('[get_n_lav_part_time_prov] nLavPartTime ' || nLavPartTime);

    RETURN PCK_PRODIS_2012_UTILS.handle_number(nLavPartTime);

  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_n_lav_telelav_pt_prov;

  FUNCTION get_n_lav_telelav_prov(prospetto_prov_id IN NUMBER) RETURN NUMBER IS
    nTelelavoro NUMBER := 0;
  BEGIN

    SELECT nvl(
              (SELECT dp.n_telelavoro_ft
                FROM PRO_D_DATI_PROVINCIALI dp
               WHERE dp.ID_PROSPETTO_PROV = prospetto_prov_id) , 0)
     INTO nTelelavoro
     FROM dual;

    writeLn('[get_n_lav_part_time_prov] nTelelavoro ' || nTelelavoro);

    RETURN PCK_PRODIS_2012_UTILS.handle_number(nTelelavoro);

  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_n_lav_telelav_prov;
  
  FUNCTION get_telelav_riproporz(prospetto_prov_id IN NUMBER) RETURN NUMBER IS
    partTimeRiproporz NUMBER := 0;
  BEGIN

    SELECT nvl(
              (SELECT round(sum(pt.orario_sett_part_time_min /
                               pt.orario_sett_contrattuale_min * pt.n_part_time) - 0.001)
                FROM PRO_D_PART_TIME pt, PRO_T_TIPO_RIPROP_PT t
               WHERE pt.ID_PROSPETTO_PROV = prospetto_prov_id
                 AND pt.ID_TIPO_RIPROP_PT = t.ID_TIPO_RIPROP_PT
                 AND t.AMBITO_RIPROP_BC = 'T'), 0)
      INTO partTimeRiproporz
      FROM dual;

    writeLn('[get_part_time_riproporz] partTimeRiproporz ' || partTimeRiproporz);

    RETURN PCK_PRODIS_2012_UTILS.handle_number(partTimeRiproporz);

  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_telelav_riproporz;
  
  FUNCTION get_n_lav_intermit_prov(prospetto_prov_id IN NUMBER) RETURN NUMBER IS
    nLavIntermitProv NUMBER := 0;
  BEGIN

    /*
    NLavoratoriIntermittenti_PV
    - (SOMMA(PRO_D_PARTTIME.N_PART_TIME)  per il Quadro 2 relativo.
    */

    SELECT nvl((SELECT sum(PRO_D_PROV_INTERMITTENTI.N_INTERMITTENTI)
                 FROM PRO_D_PROV_INTERMITTENTI
                WHERE PRO_D_PROV_INTERMITTENTI.ID_PROSPETTO_PROV =
                      prospetto_prov_id),
               0)
      INTO nLavIntermitProv
      FROM dual;
    writeLn('[get_n_lav_intermit_prov] nLavIntermitProv ' ||
            nLavIntermitProv);

    RETURN PCK_PRODIS_2012_UTILS.handle_number(nLavIntermitProv);

  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_n_lav_intermit_prov;

  FUNCTION get_intermit_riproporz(prospetto_prov_id IN NUMBER) RETURN NUMBER IS
    intermitRiproporz NUMBER := 0;
  BEGIN

    /*
    IntermittentiRiproporzionati_PV
    - arrotondamento della somma di tutte le righe intermittenti per il Quadro 2 relativo
    (Orario settimanale svolto / Orario settimanale contrattuale * n_intermittenti)

    nb per l'arrotondamento si prende la parte intera; alla parte intera si aggiunge 1
    se la parte decimale è > 0,50, altrimenti non si aggiunge nulla.

    cioè:
    select round(sum(pt1.orario_settimanale_svolto/pt1.orario_sett_contrattuale_min * pt1.n_intermittenti)-0.001)
    from pro_d_prov_intermittenti pt1 where pt1.id_prospetto_prov  IN (ID_PROSPETTO_PROV per quel Q2)
    */

    SELECT round(sum(PRO_D_PROV_INTERMITTENTI.orario_settimanale_svolto /
                     PRO_D_PROV_INTERMITTENTI.orario_settimanale_contrattual *
                     PRO_D_PROV_INTERMITTENTI.n_intermittenti) - 0.001)
      INTO intermitRiproporz
      FROM PRO_D_PROV_INTERMITTENTI
     WHERE PRO_D_PROV_INTERMITTENTI.ID_PROSPETTO_PROV = prospetto_prov_id;
    writeLn('[get_intermit_riproporz] intermitRiproporz ' ||
            intermitRiproporz);

    RETURN PCK_PRODIS_2012_UTILS.handle_number(intermitRiproporz);

  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_intermit_riproporz;

  FUNCTION get_diff_intermit_prov(prospetto_prov_id IN NUMBER) RETURN NUMBER IS
    diffIntermitProv NUMBER := 0;
  BEGIN

    /*
    DifferenzaIntermittenti_PV
    - 'NLavoratoriIntermittenti_PVi'- IntermittentiRiproporzionati_PVi')
    */

    diffIntermitProv := get_n_lav_intermit_prov(prospetto_prov_id) -
                        get_intermit_riproporz(prospetto_prov_id);
    writeLn('[get_diff_intermit_prov] diffIntermitProv ' ||
            diffIntermitProv);

    RETURN PCK_PRODIS_2012_UTILS.handle_number(diffIntermitProv);

  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_diff_intermit_prov;

  FUNCTION get_categoria_compens_disab(prospetto_prov_id IN NUMBER)
    RETURN CHAR IS
    categoriaCompensDisab CHAR := '';

    e_categoria_compens EXCEPTION;
  BEGIN

    /*
    pro_d_prov_compensazioni -> categoria compensazione disabili
    Categoria compensazione disabili  Si mette E o R, in base alla distinct(PRO_D_PROV_COMPENSAZIONI.CATEGORIA_COMPENSAZIONE)
    per CATEGORIA_SOGGETTO = 'D'
    */
    SELECT (SELECT DISTINCT (pro_d_prov_compensazioni.categoria_compensazione)
              FROM pro_d_prov_compensazioni
             WHERE pro_d_prov_compensazioni.categoria_soggetto = 'D'
               AND pro_d_prov_compensazioni.id_prospetto_prov =
                   prospetto_prov_id)
      INTO categoriaCompensDisab
      FROM dual; --recuperare solo un valore
    writeLn('[get_categoria_compens_disab] categoriaCompensDisab ' ||
            categoriaCompensDisab);

    RETURN categoriaCompensDisab;

  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RAISE e_categoria_compens;
  END get_categoria_compens_disab;

  FUNCTION get_n_compens_disab(prospetto_prov_id IN NUMBER) RETURN NUMBER IS
    nCompensDisab NUMBER := 0;
  BEGIN

    /*
    N° Compensazione disabili valore calcolato automaticamente con la somma delle compensazioni
    con CATEGORIA_SOGGETTO = 'D' per la provincia;
    */
    SELECT NVL(sum(pro_d_prov_compensazioni.n_lavoratori), 0)
      INTO nCompensDisab
      FROM pro_d_prov_compensazioni
     WHERE pro_d_prov_compensazioni.categoria_soggetto = 'D'
       AND pro_d_prov_compensazioni.id_prospetto_prov = prospetto_prov_id;
    writeLn('[get_n_compens_disab] nCompensDisab ' || nCompensDisab);

    RETURN PCK_PRODIS_2012_UTILS.handle_number(nCompensDisab);

  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_n_compens_disab;

  FUNCTION get_categoria_compens_cat_prot(prospetto_prov_id IN NUMBER)
    RETURN CHAR IS
    categoriaCompensCatProt CHAR := '';

    e_categoria_compens EXCEPTION;
  BEGIN

    /*
    Categoria compensazione -> categorie protette Si mette E o R, in base alla
    distinct(PRO_D_PROV_COMPENSAZIONI.CATEGORIA_COMPENSAZIONE)
    per CATEGORIA_SOGGETTO = 'C'
    */
    SELECT (SELECT DISTINCT (pro_d_prov_compensazioni.categoria_compensazione)
              FROM pro_d_prov_compensazioni
             WHERE pro_d_prov_compensazioni.categoria_soggetto = 'C'
               AND pro_d_prov_compensazioni.id_prospetto_prov =
                   prospetto_prov_id)
      INTO categoriaCompensCatProt
      FROM dual; --recuperare solo un valore
    writeLn('[get_categoria_compens_cat_prot] categoriaCompensCatProt ' ||
            categoriaCompensCatProt);

    RETURN categoriaCompensCatProt;

  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RAISE e_categoria_compens;
  END get_categoria_compens_cat_prot;

  FUNCTION get_n_compens_cat_prot(prospetto_prov_id IN NUMBER) RETURN NUMBER IS
    nCompensCatProt NUMBER := 0;
  BEGIN

    /*
    N° Compensazioni categorie protette -> valore calcolato automaticamente con la somma delle compensazioni
    con CATEGORIA_SOGGETTO = 'C' per la provincia;
    */
    SELECT NVL(sum(pro_d_prov_compensazioni.n_lavoratori), 0)
      INTO nCompensCatProt
      FROM pro_d_prov_compensazioni
     WHERE pro_d_prov_compensazioni.categoria_soggetto = 'C'
       AND pro_d_prov_compensazioni.id_prospetto_prov = prospetto_prov_id;
    writeLn('[get_n_compens_cat_prot] nCompensCatProt ' || nCompensCatProt);

    RETURN PCK_PRODIS_2012_UTILS.handle_number(nCompensCatProt);

  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_n_compens_cat_prot;

  FUNCTION get_n_disabili_in_forza(prospetto_prov_id IN NUMBER) RETURN NUMBER IS
    nDisabiliInForza NUMBER := 0;
    nDisabiliPt      NUMBER := 0;
  BEGIN
    /*
    PRO_D_DATI_PROVINCIALI.N_DISABILI_IN_FORZA
    +  PRO_D_DATI_PROVINCIALI.N_CENTRAL_TELEFO_NONVEDENTI
    + PRO_D_DATI_PROVINCIALI.N_TERARIAB_MASSOFIS_NONVED
    + PRO_D_DATI_PROVINCIALI.N_SOMMINISTRATI_FT
    + PRO_D_DATI_PROVINCIALI.N_CONVENZIONI_12BIS_14_FT
    )
    + somma (disabili partime dipendenti e non seconla la regola:
    se l'orario di lavoro è > 50% orario contrattualizzato ==> 1
    altrimenti ==> quota riproporzionata in funzione dell'orario)
    */

    nDisabiliInForza   := get_n_disabili_ft(prospetto_prov_id);
    nDisabiliPt        := get_disabili_riproporz(prospetto_prov_id);

    nDisabiliInForza := nDisabiliInForza + nDisabiliPt;

    writeLn('[get_n_disabili_in_forza] nDisabiliInForza ' ||
            nDisabiliInForza);

    RETURN PCK_PRODIS_2012_UTILS.handle_number(nDisabiliInForza);

  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_n_disabili_in_forza;

  FUNCTION get_n_disabili_ft(prospetto_prov_id IN NUMBER) RETURN NUMBER IS
    nDisabiliFt      NUMBER := 0;
  BEGIN

    SELECT NVL(N_DISABILI_IN_FORZA, 0) +
            NVL(N_CENTRAL_TELEFO_NONVEDENTI, 0) +
            NVL(N_TERARIAB_MASSOFIS_NONVED,0) +
            NVL(N_SOMMINISTRATI_FT, 0) +
            NVL(N_CONVENZIONI_12BIS_14_FT, 0)
      INTO nDisabiliFt
      FROM pro_d_dati_provinciali
     WHERE id_prospetto_prov = prospetto_prov_id;

    writeLn('[get_n_disabili_in_forza] nDisabiliFt ' || nDisabiliFt);

    RETURN PCK_PRODIS_2012_UTILS.handle_number(nDisabiliFt);

  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_n_disabili_ft;

  FUNCTION get_n_disabili_pt(prospetto_prov_id IN NUMBER) RETURN NUMBER IS
    nDisabiliPt      NUMBER := 0;
  BEGIN

    SELECT nvl(
          (SELECT sum(pt.n_part_time)
            FROM PRO_D_PART_TIME pt, PRO_T_TIPO_RIPROP_PT t
           WHERE pt.ID_TIPO_RIPROP_PT = t.ID_TIPO_RIPROP_PT
             AND pt.ID_PROSPETTO_PROV = prospetto_prov_id
             AND t.AMBITO_RIPROP = 'R') , 0)
      INTO nDisabiliPt
      FROM dual; -- tutti i disabili dipendenti e non

    writeLn('[get_n_disabili_in_forza] nDisabiliPt ' || nDisabiliPt);

    RETURN PCK_PRODIS_2012_UTILS.handle_number(nDisabiliPt);

  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_n_disabili_pt;


  FUNCTION get_disabili_riproporz(prospetto_prov_id IN NUMBER) RETURN NUMBER IS
    disabiliPt      NUMBER := 0;
  BEGIN
    /*
    somma (disabili partime dipendenti e non seconla la regola:
    se l'orario di lavoro è > 50% orario contrattualizzato ==> 1
    altrimenti ==> quota riproporzionata in funzione dell'orario)
    */

    SELECT nvl(ROUND(SUM(decode(round((pt.orario_sett_part_time_min /
                                  pt.orario_sett_contrattuale_min) - 0.001),
                            1,
                            1,
                            (pt.orario_sett_part_time_min /
                            pt.orario_sett_contrattuale_min)
                           ) * pt.n_part_time
                     ) - 0.001
                 ), 0)
      INTO disabiliPt
      FROM PRO_D_PART_TIME pt, PRO_T_TIPO_RIPROP_PT t
     WHERE pt.ID_TIPO_RIPROP_PT = t.ID_TIPO_RIPROP_PT
       AND pt.ID_PROSPETTO_PROV = prospetto_prov_id
       AND t.AMBITO_RIPROP = 'R'; -- tutti i disabili dipendenti e non

    writeLn('[get_n_disabili_in_forza] disabiliPt ' || disabiliPt);

    RETURN PCK_PRODIS_2012_UTILS.handle_number(disabiliPt);

  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_disabili_riproporz;

  FUNCTION get_n_disabili_in_forza_bc(prospetto_prov_id IN NUMBER) RETURN NUMBER IS
    nDisabiliInForzaBC NUMBER := 0;
    nDisabiliPt      NUMBER := 0;
  BEGIN
    /*
    PRO_D_DATI_PROVINCIALI.N_DISABILI_IN_FORZA
    +  PRO_D_DATI_PROVINCIALI.N_CENTRAL_TELEFO_NONVEDENTI
    + PRO_D_DATI_PROVINCIALI.N_TERARIAB_MASSOFIS_NONVED
    )
    + somma (disabili partime dipendenti calcolati come FT)
    */

    SELECT NVL(N_DISABILI_IN_FORZA, 0) +
           NVL(N_CENTRAL_TELEFO_NONVEDENTI, 0) +
           NVL(N_TERARIAB_MASSOFIS_NONVED, 0)
      INTO nDisabiliInForzaBC
      FROM pro_d_dati_provinciali
     WHERE id_prospetto_prov = prospetto_prov_id;

    SELECT nvl(
              (SELECT sum(pt.n_part_time)
                FROM PRO_D_PART_TIME pt, PRO_T_TIPO_RIPROP_PT t
               WHERE pt.ID_TIPO_RIPROP_PT = t.ID_TIPO_RIPROP_PT
                 AND pt.ID_PROSPETTO_PROV = prospetto_prov_id
                 AND t.AMBITO_RIPROP_BC = 'I') ,0)
      INTO nDisabiliPt
      FROM dual; -- solo disabili dipendenti

    nDisabiliInForzaBC := nDisabiliInForzaBC + nDisabiliPt;

    writeLn('[get_n_disabili_in_forza] nDisabiliInForzaBC ' || nDisabiliInForzaBC);

    RETURN PCK_PRODIS_2012_UTILS.handle_number(nDisabiliInForzaBC);

  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_n_disabili_in_forza_bc;

  FUNCTION get_n_posizioni_esonerate(prospetto_prov_id IN NUMBER)
    RETURN NUMBER IS
    nPosizioniEsonerate NUMBER := 0;
  BEGIN

    /*
    N° posizioni esonerate
    ->
    PRO_D_PROV_ESONERO.N_LAVORATORI_ESONERO (come 'lavoratori in esonero' di Elenchi provinciali)
    */
    SELECT NVL((SELECT pro_d_prov_esonero.n_lavoratori_esonero
                 FROM PRO_D_PROV_ESONERO
                WHERE pro_d_prov_esonero.id_prospetto_prov =
                      prospetto_prov_id
                  AND PRO_D_PROV_ESONERO.id_t_stato_concessione = 1),
               0) + 
               NVL((SELECT PRO_D_PROV_ESONERO_AUTOCERT.N_LAV_ESONERO_AUTOCERT
                 FROM PRO_D_PROV_ESONERO_AUTOCERT
                WHERE PRO_D_PROV_ESONERO_AUTOCERT.id_prospetto_prov =
                      prospetto_prov_id),
               0)
      INTO nPosizioniEsonerate
      FROM dual;

    writeLn('[get_n_posizioni_esonerate] nPosizioniEsonerate ' ||
            nPosizioniEsonerate);

    RETURN PCK_PRODIS_2012_UTILS.handle_number(nPosizioniEsonerate);

  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_n_posizioni_esonerate;

  FUNCTION get_scoperture_disabili_pv(quotaRicervaDisabPV IN NUMBER,
                                      nPosizEsoneratePv   IN NUMBER,
                                      nDisabiliInForzaPV  IN NUMBER)
    RETURN NUMBER IS
    scopertureDisabiliPv NUMBER := 0;
  BEGIN

    scopertureDisabiliPv := quotaRicervaDisabPV - nPosizEsoneratePv -
                            nDisabiliInForzaPV;
    writeLn('[get_scoperture_disabili_pv] scopertureDisabiliPv ' ||
            scopertureDisabiliPv);

    RETURN scopertureDisabiliPv;

  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_scoperture_disabili_pv;

  FUNCTION get_sospensioni_in_corso(prospetto_prov_id IN NUMBER) RETURN CHAR IS
    sospensioniInCorso       CHAR := '';
    flgSospensioniInCorsoNaz CHAR := '';
    sospensioniPresenti      NUMBER := 0;
  BEGIN

    /*
    Sospensioni in corso
    Valore calcolato automaticamente nel seguente modo:

    se 'sospensioni in corso nazionale' è 'S'
    ->  Viene inserito 'S'

    oppure

    se 'sospensioni in corso nazionale' è 'N' e per la provincia relativa
    a tale riepilogo è stata indicata una sospensione in stato 'approvato';
    ->  Viene inserito 'S'

    In tutti gli altri casi
    ->  Viene inserito 'N'

    */

    SELECT flg_sospensioni_in_corso
      INTO flgSospensioniInCorsoNaz
      FROM pro_d_riepilogo_nazionale rn, pro_r_prospetto_provincia pp
     WHERE rn.id_prospetto = pp.id_prospetto
       AND pp.id_prospetto_prov = prospetto_prov_id;
    writeLn('[get_sospensioni_in_corso] flgSospensioniInCorsoNaz ' ||
            flgSospensioniInCorsoNaz);

    SELECT nvl(count(*), 0)
      INTO sospensioniPresenti
      FROM pro_d_prov_sospensione
     WHERE id_prospetto_prov = prospetto_prov_id
       AND id_t_stato_concessione = 1;
    writeLn('[get_sospensioni_in_corso] sospensioniPresenti ' ||
            sospensioniPresenti);

    IF (flgSospensioniInCorsoNaz IS NOT NULL AND
       flgSospensioniInCorsoNaz = 'S') THEN
      sospensioniInCorso := 'S';
    ELSIF (flgSospensioniInCorsoNaz IS NOT NULL AND
          flgSospensioniInCorsoNaz = 'N' AND sospensioniPresenti > 0) THEN
      sospensioniInCorso := 'S';
    ELSE
      sospensioniInCorso := 'N';
    END IF;
    writeLn('[get_sospensioni_in_corso] sospensioniInCorso ' ||
            sospensioniInCorso);

    RETURN sospensioniInCorso;

  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_sospensioni_in_corso;

  FUNCTION get_n_cat_prot_in_forza(prospetto_prov_id IN NUMBER) RETURN NUMBER IS
    nCatProtInForza NUMBER := 0;
  BEGIN

    /*
    N° Categorie protette in forza (L.68/99 art.18 ) conteggiate come disabili  PRO_D_DATI_PROVINCIALI.N_CATE_PROT_FORZA_CNT_DIS
    */
    SELECT NVL(N_CATE_PROT_FORZA, 0)
      INTO nCatProtInForza
      FROM pro_d_dati_provinciali
     WHERE id_prospetto_prov = prospetto_prov_id;
    writeLn('[get_n_cat_prot_in_forza] nCatProtInForza ' ||
            nCatProtInForza);

    RETURN PCK_PRODIS_2012_UTILS.handle_number(nCatProtInForza);

  EXCEPTION
    WHEN OTHERS THEN
      dbms_output.put_line('ERRORE : ' || SUBSTR(SQLERRM, 1, 110) ||
                           '  SQLCODE=' || TO_CHAR(SQLCODE));
      RETURN NULL;
  END get_n_cat_prot_in_forza;


  PROCEDURE get_base_computo(prospetto_prov_id IN NUMBER, base_computo OUT NUMBER) IS

    BEGIN
      select
        (
          nvl(
            PRO_D_DATI_PROVINCIALI.N_TOTALE_LAVORAT_DIPENDENTI,0)
          - nvl(PRO_D_DATI_PROVINCIALI.N_DISABILI_IN_FORZA,0)
          - nvl(PRO_D_DATI_PROVINCIALI.N_CENTRAL_TELEFO_NONVEDENTI,0)
          - nvl(PRO_D_DATI_PROVINCIALI.N_TERARIAB_MASSOFIS_NONVED,0)
          - nvl(
            (select sum(PRO_D_CATEGORIE_ESCLUSE.N_LAV_APPARTART_CATEGORIA)
            from PRO_D_CATEGORIE_ESCLUSE
            where PRO_D_CATEGORIE_ESCLUSE.ID_PROSPETTO_PROV = PRO_D_DATI_PROVINCIALI.ID_PROSPETTO_PROV),0)
          - nvl(PRO_D_DATI_PROVINCIALI.N_CATE_PROT_FORZA,0)
          - nvl(PRO_D_DATI_PROVINCIALI.N_CATE_PROT_FORZA_CNT_DIS,0)
          - nvl(
            (select Sum(pt.N_PART_TIME)
            from PRO_D_PART_TIME pt , pro_t_tipo_riprop_pt t
            where pt.ID_PROSPETTO_PROV = PRO_D_DATI_PROVINCIALI.ID_PROSPETTO_PROV
              and pt.id_tipo_riprop_pt = t.id_tipo_riprop_pt
              and t.ambito_riprop_bc = 'R'
            ),0)
           - nvl( -- disabili partime
            (select Sum(pt.N_PART_TIME)
            from PRO_D_PART_TIME pt , pro_t_tipo_riprop_pt t
            where pt.ID_PROSPETTO_PROV = PRO_D_DATI_PROVINCIALI.ID_PROSPETTO_PROV
              and pt.id_tipo_riprop_pt = t.id_tipo_riprop_pt
              and t.ambito_riprop_bc = 'I'
            ),0)
          + nvl(PRO_D_DATI_PROVINCIALI.N_PARTIME_RIPROPORZIONATI,0)
          - nvl(
            (select Sum(PRO_D_PROV_INTERMITTENTI.N_INTERMITTENTI)
            from PRO_D_PROV_INTERMITTENTI
            where PRO_D_PROV_INTERMITTENTI.ID_PROSPETTO_PROV = PRO_D_DATI_PROVINCIALI.ID_PROSPETTO_PROV),0)
          + nvl(PRO_D_DATI_PROVINCIALI.N_INTERMITTENTI_RIPROPORZIONA,0)
        ) into base_computo

      from
        PRO_D_DATI_PROVINCIALI
      where
        PRO_D_DATI_PROVINCIALI.ID_PROSPETTO_PROV = prospetto_prov_id ;

        base_computo := base_computo -
                        get_n_lav_telelav_prov(prospetto_prov_id) -
                        get_telelav_riproporz(prospetto_prov_id);

        IF base_computo < 0 THEN
           base_computo := 0;
        END IF;
    EXCEPTION
        WHEN OTHERS THEN
            dbms_output.put_line('ERRORE : '||SUBSTR(SQLERRM,1,110)||'  SQLCODE='||TO_CHAR(SQLCODE));
        raise;
  END get_base_computo;

  function get_base_computo_prov(prospetto_prov_id IN NUMBER) return number IS
           base_computo NUMBER;
    BEGIN
      select
        (
          nvl(
            PRO_D_DATI_PROVINCIALI.N_TOTALE_LAVORAT_DIPENDENTI,0)
          - nvl(PRO_D_DATI_PROVINCIALI.N_DISABILI_IN_FORZA,0)
          - nvl(PRO_D_DATI_PROVINCIALI.N_CENTRAL_TELEFO_NONVEDENTI,0)
          - nvl(PRO_D_DATI_PROVINCIALI.N_TERARIAB_MASSOFIS_NONVED,0)
          - nvl(
            (select sum(PRO_D_CATEGORIE_ESCLUSE.N_LAV_APPARTART_CATEGORIA)
            from PRO_D_CATEGORIE_ESCLUSE
            where PRO_D_CATEGORIE_ESCLUSE.ID_PROSPETTO_PROV = PRO_D_DATI_PROVINCIALI.ID_PROSPETTO_PROV),0)
          - nvl(PRO_D_DATI_PROVINCIALI.N_CATE_PROT_FORZA,0)
          - nvl(PRO_D_DATI_PROVINCIALI.N_CATE_PROT_FORZA_CNT_DIS,0)
          - nvl(
            (select Sum(pt.N_PART_TIME)
            from PRO_D_PART_TIME pt , pro_t_tipo_riprop_pt t
            where pt.ID_PROSPETTO_PROV = PRO_D_DATI_PROVINCIALI.ID_PROSPETTO_PROV
              and pt.id_tipo_riprop_pt = t.id_tipo_riprop_pt
              and t.ambito_riprop_bc = 'R'
            ),0)
           - nvl( -- disabili partime
            (select Sum(pt.N_PART_TIME)
            from PRO_D_PART_TIME pt , pro_t_tipo_riprop_pt t
            where pt.ID_PROSPETTO_PROV = PRO_D_DATI_PROVINCIALI.ID_PROSPETTO_PROV
              and pt.id_tipo_riprop_pt = t.id_tipo_riprop_pt
              and t.ambito_riprop_bc = 'I'
            ),0)
          + nvl(PRO_D_DATI_PROVINCIALI.N_PARTIME_RIPROPORZIONATI,0)
          - nvl(
            (select Sum(PRO_D_PROV_INTERMITTENTI.N_INTERMITTENTI)
            from PRO_D_PROV_INTERMITTENTI
            where PRO_D_PROV_INTERMITTENTI.ID_PROSPETTO_PROV = PRO_D_DATI_PROVINCIALI.ID_PROSPETTO_PROV),0)
          + nvl(PRO_D_DATI_PROVINCIALI.N_INTERMITTENTI_RIPROPORZIONA,0)
        ) into base_computo

      from
        PRO_D_DATI_PROVINCIALI
      where
        PRO_D_DATI_PROVINCIALI.ID_PROSPETTO_PROV = prospetto_prov_id ;

        base_computo := base_computo -
                        get_n_lav_telelav_prov(prospetto_prov_id) -
                        get_telelav_riproporz(prospetto_prov_id);

        IF base_computo < 0 THEN
           base_computo := 0;
        END IF;

        return base_computo;
    EXCEPTION
        WHEN OTHERS THEN
            dbms_output.put_line('ERRORE : '||SUBSTR(SQLERRM,1,110)||'  SQLCODE='||TO_CHAR(SQLCODE));
        raise;
  END get_base_computo_prov;

  PROCEDURE cancella_dati_provinciali(prospetto_prov_id IN NUMBER) IS
    BEGIN
       DELETE PRO_D_CATEGORIE_ESCLUSE WHERE ID_PROSPETTO_PROV = prospetto_prov_id;
       DELETE PRO_D_LAVORATORI_IN_FORZA WHERE ID_PROSPETTO_PROV = prospetto_prov_id;
       DELETE PRO_D_PART_TIME WHERE ID_PROSPETTO_PROV = prospetto_prov_id;
       DELETE PRO_D_POSTI_LAVORO_DISP WHERE ID_PROSPETTO_PROV = prospetto_prov_id;
       DELETE PRO_D_PROSPETTO_PROV_SEDE WHERE ID_PROSPETTO_PROV = prospetto_prov_id;
       DELETE PRO_D_PROV_COMPENSAZIONI WHERE ID_PROSPETTO_PROV = prospetto_prov_id;
       DELETE PRO_D_PROV_CONVENZIONE WHERE ID_PROSPETTO_PROV = prospetto_prov_id;
       DELETE PRO_D_PROV_ESONERO WHERE ID_PROSPETTO_PROV = prospetto_prov_id;
       DELETE PRO_D_PROV_ESONERO_AUTOCERT WHERE ID_PROSPETTO_PROV = prospetto_prov_id;
       DELETE PRO_D_PROV_GRADUALITA WHERE ID_PROSPETTO_PROV = prospetto_prov_id;
       DELETE PRO_D_PROV_INTERMITTENTI WHERE ID_PROSPETTO_PROV = prospetto_prov_id;
       DELETE PRO_D_PROV_SOSPENSIONE WHERE ID_PROSPETTO_PROV = prospetto_prov_id;
       DELETE PRO_D_DATI_PROVINCIALI WHERE ID_PROSPETTO_PROV = prospetto_prov_id;
       DELETE PRO_D_RIEPILOGO_PROVINCIALE WHERE ID_PROSPETTO_PROV = prospetto_prov_id;
       DELETE PRO_R_PROSPETTO_PROVINCIA WHERE ID_PROSPETTO_PROV = prospetto_prov_id;
    EXCEPTION
    WHEN OTHERS THEN
      raise;
  END cancella_dati_provinciali;
  

END PCK_PRODIS_2012_PROV_UTILS;
/

CREATE OR REPLACE PACKAGE BODY "PCK_PRODIS_2012_UTILS" AS

  FUNCTION minimo(numero1 IN NUMBER, numero2 IN NUMBER) return NUMBER IS
    BEGIN
      if (numero1 is null and numero2 is null) then
        return null;
      elsif (numero1 < numero2) then
        return numero1;
      else
        return numero2;
      end if;
    EXCEPTION
      WHEN OTHERS THEN
        dbms_output.put_line('ERRORE : '||SUBSTR(SQLERRM,1,110)||'  SQLCODE='||TO_CHAR(SQLCODE));
      return 0;
  END minimo;

  FUNCTION handle_number(numero IN NUMBER) return NUMBER IS
    BEGIN
      if (numero is null) then
        return 0;
      elsif (numero < 0) then
        return 0;
      else
        return numero;
      end if;
    EXCEPTION
      WHEN OTHERS THEN
        dbms_output.put_line('ERRORE : '||SUBSTR(SQLERRM,1,110)||'  SQLCODE='||TO_CHAR(SQLCODE));
      return 0;
  END handle_number;

  FUNCTION round_number(numero IN NUMBER) return NUMBER IS
    BEGIN
        return ROUND(handle_number(numero)-0.001);
    EXCEPTION
      WHEN OTHERS THEN
        dbms_output.put_line('ERRORE : '||SUBSTR(SQLERRM,1,110)||'  SQLCODE='||TO_CHAR(SQLCODE));
      return 0;
  END round_number;

	FUNCTION controlla_codice_fiscale(
					codice_fiscale IN VARCHAR2,
					cognome IN VARCHAR2,
					nome IN VARCHAR2,
					sesso IN VARCHAR2,
					data_nascita IN VARCHAR2,
					codice_comune_stato IN VARCHAR2) return NUMBER IS

			v_cod_fis VARCHAR2(16);

			--cognome
			i_cg NUMBER;
			y_cg NUMBER;
			z_cg NUMBER;
			n_ascii_cg NUMBER;
			v_cara_cg VARCHAR2(1 char);
			v_vocali_cg VARCHAR2(16);
			v_consonanti_cg VARCHAR2(3 char);
			v_cod_fis_cg VARCHAR2(3);

			--nome
			i_n NUMBER;
			y_n NUMBER;
			n_ascii_n NUMBER;
			v_cara_n VARCHAR2(1 char);
			v_vocali_n VARCHAR2(16);
			v_consonanti_n VARCHAR2(4 char);
			v_cod_fis_n VARCHAR2(3);

			--data nascita + Sex
			TYPE mesi_type IS TABLE OF char INDEX BY BINARY_INTEGER;
			v_mesi mesi_type;
			d_data date;
			c_str varchar2(5 char);

			-- comune/stato nascita
			v_codice_comune varchar2(4 char);

			--check digit
			TYPE caratteri_type IS TABLE OF number INDEX BY varchar2(1 char);
			TYPE controllo_type IS TABLE OF char INDEX BY BINARY_INTEGER;
			v_pari caratteri_type;
			v_dispari caratteri_type;
			v_controllo controllo_type;
			i_c number;
			n_pari number;
			n_dispari number;

			--omocodia
			v_cf_controllato varchar2(16 char);
			v_ctrl_num varchar2(8 char);
			i_o number;
			n_sost  varchar2(1 char);
			v_chr varchar2(1 char);
			TYPE omocodia_type IS TABLE OF char INDEX BY varchar2(1 char);
			v_omocodia omocodia_type;

			v_codice_fiscale varchar2(16 char);
			v_cognome varchar2(50 char);
			v_nome varchar2(50 char);
			v_sesso varchar2(1 char);
			v_data_nascita varchar2(11 char);
			v_codice_comune_stato varchar2(4 char);

		BEGIN

			dbms_output.put_line('codice_fiscale '||codice_fiscale);
			dbms_output.put_line('cognome '||cognome);
			dbms_output.put_line('nome '||nome);
			dbms_output.put_line('sesso '||sesso);
			dbms_output.put_line('data_nascita '||data_nascita);
			dbms_output.put_line('codice_comune_stato '||codice_comune_stato);

			v_codice_fiscale := upper(codice_fiscale);
			v_cognome := upper(cognome);
			v_nome := upper(nome);
			v_sesso := upper(sesso);
			v_data_nascita := upper(data_nascita);
			v_codice_comune_stato := upper(codice_comune_stato);

			v_cod_fis:='';

			--controlli su cognome - START
			if v_cognome is not null then
				dbms_output.put_line('controlli su cognome ');
				i_cg := 0;
				y_cg := 0;
				z_cg := 0;
				v_vocali_cg:='';
				v_consonanti_cg:='';
				v_cara_cg:='';
				v_cod_fis_cg:='';

				while i_cg < length(v_cognome) and y_cg <> 3 loop

					-- estraggo il carattere
					v_cara_cg  := substr(upper(v_cognome), i_cg+1, 1);

					-- estraggo il codice ascii
					n_ascii_cg := ascii(v_cara_cg);

					-- controllo se il codice ascii appartiene al range di valori accettati compreso fra 65 e 90
					if(n_ascii_cg > 64 and n_ascii_cg < 91) then
						-- controllo se il codice ascii Ã¨ una vocale
						if(n_ascii_cg = 65 or n_ascii_cg = 69 or n_ascii_cg = 73 or n_ascii_cg = 79 or n_ascii_cg = 85 ) then
							v_vocali_cg:=v_vocali_cg||v_cara_cg;
						else
							v_consonanti_cg:=v_consonanti_cg||v_cara_cg;
							y_cg := y_cg + 1;
						end if;
					end if;
					i_cg := i_cg + 1;
				end loop;

				--assegno il valore del codice fiscale
				v_cod_fis_cg := substr(v_consonanti_cg|| v_vocali_cg, 1, 3);
				while length(v_cod_fis_cg) < 3  loop
					v_cod_fis_cg := v_cod_fis_cg || 'X';
				end loop;

				v_cod_fis:=v_cod_fis||v_cod_fis_cg;
			else
				dbms_output.put_line('controlli su cognome no');
				v_cod_fis:=v_cod_fis||substr(v_codice_fiscale, 1, 3);
			end if;
			dbms_output.put_line('cf generato '||v_cod_fis);
			--controlli su cognome - END

			--controlli su nome - START
			if v_nome is not null then
				dbms_output.put_line('controlli su nome ');
				i_n := 0;
				y_n := 0;
				v_vocali_n:='';
				v_consonanti_n:='';
				v_cara_n:='';
				v_cod_fis_n:='';

				while i_n < length(v_nome) and y_n <> 4 loop

					-- estraggo il carattere
					v_cara_n  := substr(upper(v_nome), i_n+1, 1);

					-- estraggo il codice ascii
					n_ascii_n := ascii(v_cara_n);

					-- controllo se il codice ascii appartiene al range di valori accettati compreso fra 65 e 90
					if(n_ascii_n > 64 and n_ascii_n < 91) then
						-- controllo se il codice ascii Ã¨ una vocale
						if(n_ascii_n = 65 or n_ascii_n = 69 or n_ascii_n = 73 or n_ascii_n = 79 or n_ascii_n = 85 ) then
							v_vocali_n:=v_vocali_n||v_cara_n;
						else
							v_consonanti_n:=v_consonanti_n||v_cara_n;
							y_n := y_n + 1;
						end if;
					end if;

					i_n := i_n + 1;

				end loop;

				--assegno il valore del codice fiscale
				IF( length(v_consonanti_n)>3 ) THEN
					v_cod_fis_n  := substr(upper(v_consonanti_n), 1, 1);
					v_cod_fis_n  := v_cod_fis_n || substr(upper(v_consonanti_n), 3, 2);
				ELSE
					v_cod_fis_n := substr(v_consonanti_n|| v_vocali_n, 1, 3);
					while length(v_cod_fis_n) < 3  loop
						v_cod_fis_n := v_cod_fis_n || 'X';
					end loop;
				END IF;

				v_cod_fis:=v_cod_fis||v_cod_fis_n;
			else
				dbms_output.put_line('controlli su nome ');
				v_cod_fis:=v_cod_fis||substr(v_codice_fiscale, 4, 3);
			end if;
			dbms_output.put_line('cf generato '||v_cod_fis);
			--controlli su nome - END

			--data_nascita e Sesso - START
			if v_sesso is not null and v_data_nascita is not null then
				dbms_output.put_line('controlli su data_nascita e sesso ');
				-- la data in formato testo gg/mm/aaaa sesso stringa 'F' o 'M'
				-- converto la stringa in ingresso in data
				c_str:='';

				d_data := to_date(v_data_nascita, 'DD/MM/YYYY');

				-- Codifica mesi dell'anno
				v_mesi(1) := 'A';
				v_mesi(2) := 'B';
				v_mesi(3) := 'C';
				v_mesi(4) := 'D';
				v_mesi(5) := 'E';
				v_mesi(6) := 'H';
				v_mesi(7) := 'L';
				v_mesi(8) := 'M';
				v_mesi(9) := 'P';
				v_mesi(10) := 'R';
				v_mesi(11) := 'S';
				v_mesi(12) := 'T';

				-- converto l'anno
				c_str := to_char(d_data, 'YY');

				-- converto il mese
				c_str := c_str || v_mesi(to_number(to_char(d_data, 'MM')));

				--converto il giorno
				if(upper(v_sesso)= 'F') then
					c_str := c_str || to_char(to_number(to_char(d_data, 'DD')) + 40);
				else
					c_str := c_str || to_char(d_data, 'DD');
				end if;

				v_cod_fis:=v_cod_fis||c_str;
			else
				dbms_output.put_line('controlli su data_nascita e sesso no ');
				v_cod_fis:=v_cod_fis||substr(v_codice_fiscale, 7, 5);
			end if;
			dbms_output.put_line('cf generato '||v_cod_fis);
			--data_nascita e Sesso - END

			-- comune_di_nacita - START
			if v_codice_comune_stato is not null then
				dbms_output.put_line('controlli su comune e stato ');
				v_codice_comune := v_codice_comune_stato;
				v_cod_fis:=v_cod_fis||v_codice_comune;
			else
				dbms_output.put_line('controlli su comune e stato no');
				v_cod_fis:=v_cod_fis||substr(v_codice_fiscale, 12, 4);
			end if;
			dbms_output.put_line('cf generato '||v_cod_fis);
			-- comune_di_nacita - END

			--OMOCODIA - START
			/*
			dbms_output.put_line('controlli su omocodia ');
			--verifico se Ã¨ un codice omocodico
			v_cf_controllato:='';
			v_ctrl_num:='';
			v_ctrl_num:=substr(v_codice_fiscale,7,1);
			v_ctrl_num:=v_ctrl_num||substr(v_codice_fiscale,8,1);
			v_ctrl_num:=v_ctrl_num||substr(v_codice_fiscale,10,1);
			v_ctrl_num:=v_ctrl_num||substr(v_codice_fiscale,11,1);
			v_ctrl_num:=v_ctrl_num||substr(v_codice_fiscale,13,1);
			v_ctrl_num:=v_ctrl_num||substr(v_codice_fiscale,14,1);
			v_ctrl_num:=v_ctrl_num||substr(v_codice_fiscale,15,1);

			if (REGEXP_INSTR (v_ctrl_num, '[A-Z]',1))<>0 then

				v_omocodia('L'):=0;
				v_omocodia('M'):=1;
				v_omocodia('N'):=2;
				v_omocodia('P'):=3;
				v_omocodia('Q'):=4;
				v_omocodia('R'):=5;
				v_omocodia('S'):=6;
				v_omocodia('T'):=7;
				v_omocodia('U'):=8;
				v_omocodia('V'):=9;

				i_o := REGEXP_INSTR (v_ctrl_num, '[A-Z]',1);
				v_chr:=substr(v_ctrl_num,i_o,1);
				n_sost:= v_omocodia(v_chr);

				case
					when i_o=1 then i_o:=i_o+6;
					when i_o=2 then i_o:=i_o+6;
					when i_o=3 then i_o:=i_o+7;
					when i_o=4 then i_o:=i_o+7;
					when i_o=5 then i_o:=i_o+8;
					when i_o=6 then i_o:=i_o+8;
					when i_o=7 then i_o:=i_o+8;
				end case;

				v_cf_controllato:=regexp_replace(v_codice_fiscale,v_chr,n_sost,i_o,1);
				v_cf_controllato:=substr(v_cf_controllato,1,15);

			end if;
			dbms_output.put_line('cf generato '||v_cod_fis);
			*/

			--verifico se è un codice omocodico
			v_cf_controllato:='';
			v_ctrl_num:='';
			--estraggo le posizioni numeriche che possono essere sostituite con i caratteri alfabetici
			v_ctrl_num:=substr(v_codice_fiscale,7,1);
			v_ctrl_num:=v_ctrl_num||substr(v_codice_fiscale,8,1);
			v_ctrl_num:=v_ctrl_num||substr(v_codice_fiscale,10,1);
			v_ctrl_num:=v_ctrl_num||substr(v_codice_fiscale,11,1);
			v_ctrl_num:=v_ctrl_num||substr(v_codice_fiscale,13,1);
			v_ctrl_num:=v_ctrl_num||substr(v_codice_fiscale,14,1);
			v_ctrl_num:=v_ctrl_num||substr(v_codice_fiscale,15,1);

			-- ARRAY CARATTERI ALFABETICI pe omocodici
			v_omocodia('L'):=0;
			v_omocodia('M'):=1;
			v_omocodia('N'):=2;
			v_omocodia('P'):=3;
			v_omocodia('Q'):=4;
			v_omocodia('R'):=5;
			v_omocodia('S'):=6;
			v_omocodia('T'):=7;
			v_omocodia('U'):=8;
			v_omocodia('V'):=9;

			i_o :=REGEXP_INSTR (v_ctrl_num, '[A-Z]',1);

    		if i_o <>0 then
    			v_cf_controllato:=v_codice_fiscale;

    			while i_o<>0  loop
      				v_chr:=substr(v_ctrl_num,i_o,1);
      				n_sost:= v_omocodia(v_chr);

      				--sostituisco il carattere nella stringa dei caratteri numerici
      				v_ctrl_num:=regexp_replace(v_ctrl_num,v_chr,n_sost,i_o,1);

      				case
   						when i_o=1 then i_o:=i_o+6;
   						when i_o=2 then i_o:=i_o+6;
   						when i_o=3 then i_o:=i_o+7;
   						when i_o=4 then i_o:=i_o+7;
   						when i_o=5 then i_o:=i_o+8;
   						when i_o=6 then i_o:=i_o+8;
   						when i_o=7 then i_o:=i_o+8;
					end case;

      				--sostituisco il carattere ne cf
      				v_cf_controllato:=regexp_replace(v_cf_controllato,v_chr,n_sost,i_o,1);
      				i_o :=REGEXP_INSTR (v_ctrl_num, '[A-Z]',1);
   				end loop;

  				v_cf_controllato:=substr(v_cf_controllato,1,15);
  			end if;
			--Omocodia - END

			--codice_controllo - START
			dbms_output.put_line('controlli su chkdigit ');
			--ARRAY PARI
			v_pari('A') := 0 ;
			v_pari('B') := 1 ;
			v_pari('C') := 2 ;
			v_pari('D') := 3 ;
			v_pari('E') := 4 ;
			v_pari('F') := 5 ;
			v_pari('G') := 6 ;
			v_pari('H') := 7 ;
			v_pari('I') := 8 ;
			v_pari('J') := 9 ;
			v_pari('K') := 10 ;
			v_pari('L') := 11 ;
			v_pari('M') := 12 ;
			v_pari('N') := 13 ;
			v_pari('O') := 14 ;
			v_pari('P') := 15 ;
			v_pari('Q') := 16 ;
			v_pari('R') := 17 ;
			v_pari('S') := 18 ;
			v_pari('T') := 19 ;
			v_pari('U') := 20 ;
			v_pari('V') := 21 ;
			v_pari('W') := 22 ;
			v_pari('X') := 23 ;
			v_pari('Y') := 24 ;
			v_pari('Z') := 25 ;
			v_pari('1') := 1 ;
			v_pari('2') := 2 ;
			v_pari('3') := 3 ;
			v_pari('4') := 4 ;
			v_pari('5') := 5 ;
			v_pari('6') := 6 ;
			v_pari('7') := 7 ;
			v_pari('8') := 8 ;
			v_pari('9') := 9 ;
			v_pari('0') := 0 ;

			--ARRAY DISPARY
			v_dispari('A') := 1 ;
			v_dispari('B') := 0;
			v_dispari('C') := 5;
			v_dispari('D') := 7;
			v_dispari('E') := 9;
			v_dispari('F') := 13;
			v_dispari('G') := 15;
			v_dispari('H') := 17 ;
			v_dispari('I') := 19 ;
			v_dispari('J') := 21;
			v_dispari('K') := 2;
			v_dispari('L') := 4;
			v_dispari('M') := 18;
			v_dispari('N') := 20;
			v_dispari('O') := 11;
			v_dispari('P') := 3;
			v_dispari('Q') := 6;
			v_dispari('R') := 8;
			v_dispari('S') := 12;
			v_dispari('T') := 14;
			v_dispari('U') := 16;
			v_dispari('V') := 10;
			v_dispari('W') := 22;
			v_dispari('X') := 25;
			v_dispari('Y') := 24;
			v_dispari('Z') := 23;
			v_dispari('1') := 0;
			v_dispari('2') := 5;
			v_dispari('3') := 7;
			v_dispari('4') := 9;
			v_dispari('5') := 13;
			v_dispari('6') := 15;
			v_dispari('7') := 17;
			v_dispari('8') := 19;
			v_dispari('9') := 21;
			v_dispari('0') :=  1;

			--VARIABILI DI CONTROLLO
			v_controllo(0) := 'A';
			v_controllo(1) := 'B';
			v_controllo(2) := 'C';
			v_controllo(3) := 'D';
			v_controllo(4) := 'E';
			v_controllo(5) := 'F';
			v_controllo(6) := 'G';
			v_controllo(7) := 'H';
			v_controllo(8) := 'I';
			v_controllo(9) := 'J';
			v_controllo(10) := 'K';
			v_controllo(11) := 'L';
			v_controllo(12) := 'M';
			v_controllo(13) := 'N';
			v_controllo(14) := 'O';
			v_controllo(15) := 'P';
			v_controllo(16) := 'Q';
			v_controllo(17) := 'R';
			v_controllo(18) := 'S';
			v_controllo(19) := 'T';
			v_controllo(20) := 'U';
			v_controllo(21) := 'V';
			v_controllo(22) := 'W';
			v_controllo(23) := 'X';
			v_controllo(24) := 'Y';
			v_controllo(25) := 'Z';
			i_c := 1;
			n_dispari := 0;

			--cf calcolato
			while i_c < length(v_cod_fis) +1 loop
				n_dispari := n_dispari + v_dispari(substr(v_cod_fis,i_c,1));
				i_c := i_c +2;
			end loop;

			i_c := 2;
			n_pari := 0;
			while i_c < length(v_cod_fis) +1 loop
				n_pari := n_pari + v_pari(substr(v_cod_fis,i_c,1));
				i_c := i_c +2;
			end loop;
			v_cod_fis:=v_cod_fis||(v_controllo(mod(n_dispari + n_pari, 26)));

			--cf calcolato per omocodia
			if v_cf_controllato <> '' or v_cf_controllato is not null then

				i_c := 1;
				n_dispari := 0;
				while i_c < length(v_cf_controllato) +1 loop
					n_dispari := n_dispari + v_dispari(substr(v_cf_controllato,i_c,1));
					i_c := i_c +2;
				end loop;

				i_c := 2;
				n_pari := 0;
				while i_c < length(v_cf_controllato) +1 loop
					n_pari := n_pari + v_pari(substr(v_cf_controllato,i_c,1));
					i_c := i_c +2;
				end loop;

				v_cf_controllato:=v_cf_controllato||(v_controllo(mod(n_dispari + n_pari, 26)));

			end if;
			dbms_output.put_line('cf generato '||v_cod_fis);
			--codice_controllo - END

			--controllo finale del codice fiscale generato con quello in ingresso
			dbms_output.put_line('controllo finale del codice fiscale generato con quello in ingresso ');
			dbms_output.put_line('cf generato '||v_cod_fis);
			dbms_output.put_line('cf in ingresso '||v_codice_fiscale);
			if ((v_cf_controllato = '' or v_cf_controllato is null) and v_codice_fiscale <> v_cod_fis)
				or ((v_cf_controllato <> '' or v_cf_controllato is not null) and v_cf_controllato <> v_cod_fis) then

					dbms_output.put_line('controllo codice fiscale ko');
					return 0;

			end if;

			dbms_output.put_line('controllo codice fiscale ok');
			dbms_output.put_line('end');
			return 1;

		EXCEPTION
			WHEN OTHERS THEN
			dbms_output.put_line('ERRORE : '||SUBSTR(SQLERRM,1,110)||'  SQLCODE='||TO_CHAR(SQLCODE));
			return 0;
	END controlla_codice_fiscale;

END PCK_PRODIS_2012_UTILS;
/
