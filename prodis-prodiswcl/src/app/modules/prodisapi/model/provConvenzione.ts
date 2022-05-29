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
import { AssunzioneProtetta } from './assunzioneProtetta';
import { DatiProvinciali } from './datiProvinciali';
import { StatoConcessione } from './statoConcessione';


export interface ProvConvenzione { 
    assunzioneProtetta?: AssunzioneProtetta;
    codUserAggiorn?: string;
    codUserInserim?: string;
    dAggiorn?: Date;
    dInserim?: Date;
    dataAtto?: Date;
    dataScadenza?: Date;
    dataStipula?: Date;
    datiProvinciali?: DatiProvinciali;
    estremiAtto?: string;
    id?: number;
    numLavPrevConvQ2?: number;
    statoConcessione?: StatoConcessione;
}