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
import { Prospetto } from './prospetto';


export interface Soggetti { 
    codSoggetti?: string;
    dataFine?: Date;
    dataInizio?: Date;
    dataTmst?: Date;
    descSoggetti?: string;
    id?: number;
    prospettos?: Array<Prospetto>;
}
