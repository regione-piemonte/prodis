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
import { HttpHeaders }                                       from '@angular/common/http';

import { Observable }                                        from 'rxjs';

import { ApiError } from '../model/apiError';
import { ConfermaRiepilogo } from '../model/confermaRiepilogo';
import { ConfermaRiepilogoProspetto } from '../model/confermaRiepilogoProspetto';
import { EsitoStoreProcedure } from '../model/esitoStoreProcedure';
import { PostiLavoroDisp } from '../model/postiLavoroDisp';
import { Prospetto } from '../model/prospetto';
import { ProvCompensazioni } from '../model/provCompensazioni';


import { Configuration }                                     from '../configuration';


export interface RiepilogoServiceInterface {
    defaultHeaders: HttpHeaders;
    configuration: Configuration;
    

    /**
    * 
    * Conferma il riepilogo del prospetto registrato su sistema.
    * @param idProspetto 
    * @param confermaRiepilogoProspetto 
    */
    confermaRiepilogo(idProspetto: number, confermaRiepilogoProspetto: ConfermaRiepilogoProspetto, extraHttpRequestParams?: any): Observable<ConfermaRiepilogo>;

    /**
    * 
    * Conferma le compensazioni.
    * @param idProspetto L&#39;idProspetto.
    */
    confermaTornaRiepilogo(idProspetto: number, extraHttpRequestParams?: any): Observable<boolean>;

    /**
    * 
    * Restituisce La ProvCompensazioni eliminata corrispondente a idCompensazioni.
    * @param idCompensazioni id ProvCompensazioni.
    */
    deleteCompensazioni(idCompensazioni: number, extraHttpRequestParams?: any): Observable<ProvCompensazioni>;

    /**
    * 
    * Elimina dati registrati su sistema.
    * @param idPostiLavoroDisp idPostiLavoroDisp del PostiLavoroDisp da eliminare.
    */
    deletePostiLavoroDisp(idPostiLavoroDisp: number, extraHttpRequestParams?: any): Observable<PostiLavoroDisp>;

    /**
    * 
    * Restituisce dati registrati su sistema.
    * @param idDatiProvinciali L&#39;idDatiProvinciali.
    * @param compensazioni 
    */
    postCompensazioni(idDatiProvinciali: number, compensazioni: ProvCompensazioni, extraHttpRequestParams?: any): Observable<{}>;

    /**
    * 
    * Restituisce dati registrati su sistema.
    * @param idDatiProvinciali L&#39;idDatiProvinciali.
    * @param postiLavoroDisp 
    */
    postPostiLavoroDisp(idDatiProvinciali: number, postiLavoroDisp: PostiLavoroDisp, extraHttpRequestParams?: any): Observable<{}>;

    /**
    * 
    * Restituisce dati registrati su sistema.
    * @param idDatiProvinciali id DatiProvinciali.
    * @param idCompensazioni id di ProvCompensazioni da modificare.
    * @param compensazioni 
    */
    putCompensazioni(idDatiProvinciali: number, idCompensazioni: number, compensazioni: ProvCompensazioni, extraHttpRequestParams?: any): Observable<ProvCompensazioni>;

    /**
    * 
    * Restituisce dati registrati su sistema.
    * @param idDatiProvinciali id DatiProvinciali.
    * @param idPostiLavoroDisp id del PostoDiLavoroDisp da modificare.
    * @param postiLavoroDisp 
    */
    putPostiLavoroDisp(idDatiProvinciali: number, idPostiLavoroDisp: number, postiLavoroDisp: PostiLavoroDisp, extraHttpRequestParams?: any): Observable<PostiLavoroDisp>;

    /**
    * 
    * Salva in bozza il riepilogo del prospetto registrato su sistema.
    * @param idProspetto 
    * @param prospetto 
    */
    salvaBozzaRiepilogo(idProspetto: number, prospetto: Prospetto, extraHttpRequestParams?: any): Observable<Prospetto>;

    /**
    * 
    * Restituisce l&#39;esito dei calcoli eseguiti dalla store procedure.
    * @param idProspetto L&#39;id del prospetto.
    * @param cfUenteAggiornamento codice fiscale utente.
    * @param soloScoperture flg solo scoperture.
    */
    storeProcedureEseguiCalcoli(idProspetto: number, cfUenteAggiornamento: string, soloScoperture: boolean, extraHttpRequestParams?: any): Observable<EsitoStoreProcedure>;

}