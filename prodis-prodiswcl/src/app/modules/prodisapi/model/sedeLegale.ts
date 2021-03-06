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
import { Comune } from './comune';
import { DatiAzienda } from './datiAzienda';
import { StatiEsteri } from './statiEsteri';


export interface SedeLegale { 
    capSede?: string;
    codUserAggiorn?: string;
    codUserInserim?: string;
    comune?: Comune;
    dAggiorn?: Date;
    dInserim?: Date;
    datiAzienda?: DatiAzienda;
    email?: string;
    fax?: string;
    id?: number;
    indirizzo?: string;
    statiEsteri?: StatiEsteri;
    telefono?: string;
}
