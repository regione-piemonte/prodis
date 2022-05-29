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


export interface RiepilogoProvinciale { 
    baseComputoArt18?: number;
    baseComputoArt3?: number;
    catCompensazioneCateProt?: string;
    catCompensazioneDisabili?: string;
    codUserAggiorn?: string;
    codUserInserim?: string;
    dAggiorn?: Date;
    dInserim?: Date;
    flgSospensioniInCorso?: string;
    id?: number;
    idProspettoProv?: number;
    numCatProtInForza?: number;
    numCatProtInForzaContDis?: number;
    numCompensazioneDisabili?: number;
    numCompensazioniCateProt?: number;
    numDisabiliInForza?: number;
    numLavoratoriBaseComputo?: number;
    numLavoratoriSospensione?: number;
    numPosizioniEsonerate?: number;
    numScopertureCatProt?: number;
    numScopertureCatProtReali?: number;
    numScopertureDisabili?: number;
    numScopertureDisabiliReali?: number;
    quotaRiservaArt18?: number;
    quotaRiservaDisabili?: number;
}
