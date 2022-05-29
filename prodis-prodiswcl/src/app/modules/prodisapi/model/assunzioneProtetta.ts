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
import { LavoratoriInForza } from './lavoratoriInForza';
import { LavoratoriSilp } from './lavoratoriSilp';
import { ProvConvenzione } from './provConvenzione';


export interface AssunzioneProtetta { 
    codAssunzioneProtetta?: string;
    dataFine?: Date;
    dataInizio?: Date;
    dataTmst?: Date;
    descAssunzioneProtetta?: string;
    flgConvensione?: string;
    id?: number;
    lavoratoriInForzas?: Array<LavoratoriInForza>;
    lavoratoriSilps?: Array<LavoratoriSilp>;
    provConvenziones?: Array<ProvConvenzione>;
}
