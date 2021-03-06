/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
/**
 * Prodis
 * API per il backend della suite di Prodis.
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
import { DatiAzienda } from './datiAzienda';
import { LavoratoriSilp } from './lavoratoriSilp';
import { PostiLavoroDisp } from './postiLavoroDisp';
import { SedeLegale } from './sedeLegale';


export interface StatiEsteri { 
    codNazioneMin?: string;
    datiAziendas?: Array<DatiAzienda>;
    dsStatiEsteri?: string;
    dtFine?: Date;
    dtInizio?: Date;
    dtTmst?: Date;
    flgUe?: string;
    id?: number;
    lavoratoriSilps?: Array<LavoratoriSilp>;
    postiLavoroDisps?: Array<PostiLavoroDisp>;
    sedeLegales?: Array<SedeLegale>;
    siglaNazione?: string;
}
