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
import { Istat2001livello5 } from './istat2001livello5';
import { StatiEsteri } from './statiEsteri';


export interface PostiLavoroDisp { 
    categoriaAssunzione?: string;
    categoriaSoggetto?: string;
    codUserAggiorn?: string;
    codUserInserim?: string;
    comune?: Comune;
    dAggiorn?: Date;
    dInserim?: Date;
    descCapacitaRichiesteContr?: string;
    descMansione?: string;
    flgPresenzaBarriereArchite?: string;
    flgRaggiungibMezziPubblici?: string;
    flgTurniNotturni?: string;
    id?: number;
    idProspettoProv?: number;
    istat2001livello5?: Istat2001livello5;
    nPosti?: number;
    statiEsteri?: StatiEsteri;
}
