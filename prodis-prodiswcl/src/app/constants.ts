/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Prospetto } from "./modules/prodisapi/model/prospetto";
import { TipoRipropPt } from "./modules/prodisapi/model/tipoRipropPt";

export const COD_DICHIARANTE_DATORE_LAVORO_PUBBLICO = "C";

export const PARAMETRO_TERMINE_ANNULLAMENTO = "termine.annullamento"
export const PARAMETRO_TERMINE_RETTIFICA = "termine.rettifica"
export const PARAMETRO_REGEX_EMAIL = "prodis.pdweb.regularexpression.email"
export const PARAMETRO_PRODIS_ABILITATO = "prodis.web.abilitato"



export const COMUNICAZIONE_RETTIFICA_ID = 2;
export const COMUNICAZIONE_ANNULLAMENTO_ID = 3;

export const STATO_PROSPETTO_BOZZA ="BOZZA";
export const STATO_PROSPETTO_FIRMARE ="DA FIRMARE";
export const STATO_PROSPETTO_DA_INVIARE ="DA INVIARE";
export const STATO_PROSPETTO_PRESENTATA ="PRESENTATA";
export const STATO_PROSPETTO_IN_RETTIFICA ="IN RETTIFICA";
export const STATO_PROSPETTO_RETTIFICATA ="RETTIFICATA";
export const STATO_PROSPETTO_ANNULLATA ="ANNULLATA";
export const STATO_PROSPETTO_IN_ANNULLAMENTO ="IN_ANNULLAMENTO";
export const STATO_PROSPETTO_CANCELLATA ="CANCELLATA";

export const ID_STATO_PROSPETTO_CANCELLATA = 13;
export const ID_STATO_PROSPETTO_BOZZA = 1;
export const ID_STATO_PROSPETTO_DA_INVIARE = 2;


export const TYPE_ALERT_SUCCESS ="S";
export const TYPE_ALERT_DANGER ="D";
export const TYPE_ALERT_WARNING ="W";
export const TYPE_ALERT_INFO ="I";

export const TYPE_DECODIFICA_GENERICA = {
    ATECO: "ATE",
    CCNL: "CCNL",
    COMUNE: 'COM',
    QUAL_ISTAT: 'QUAL_ISTAT',
    STATI_ESTERI: 'STATI_ES'
}

export const TIPO_LAV_INTERM: TipoRipropPt = {
    id: -1,
    dsTipoRipropPt: "Intermittenti"
  }
  export const NEW_PROSPETTO: Prospetto = {
    statoProspetto : {
      descrizione : STATO_PROSPETTO_BOZZA,
      id : 1
    },
    datiAzienda: {
      cfAzienda: null,
      flgProspettoDaCapogruppo: 'N'
    },
    flgSospensionePerMobilita: null,
    numLavorInForzaNazionale: 0,
    comunicazione: {
      id: 1
    },
    flgVisitaIspettiva: 'N',
    tipoProvenienza: "P"
  } as Prospetto;
  export const MESI = { A: '01', B: '02', C: '03', D: '04', E: '05', H: '06', L: '07', M: '08', P: '09', R: '10', S: '11', T: '12' };

  export const ERROR_DOWNLOAD_1000_LAVORATORI = {
    code: "PRO-UPD-E-0008",
    errorMessage: "Il prospetto presenta un numero di lavoratori tale per cui è necessario procedere selezionando le singole provincie.",
    groupParams: {},
    params: {},
    type: "ERROR"
  }
