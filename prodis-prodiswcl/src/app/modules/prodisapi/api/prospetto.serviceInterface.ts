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
import { AssPubbliche } from '../model/assPubbliche';
import { PagedResponseProspetto } from '../model/pagedResponseProspetto';
import { PostPutProspettoResponse } from '../model/postPutProspettoResponse';
import { Prospetto } from '../model/prospetto';
import { ReinviaProspetto } from '../model/reinviaProspetto';
import { RicercaProspetto } from '../model/ricercaProspetto';


import { Configuration }                                     from '../configuration';


export interface ProspettoServiceInterface {
    defaultHeaders: HttpHeaders;
    configuration: Configuration;
    

    /**
    * 
    * Annulla il prospetto registrato su sistema.
    * @param id 
    */
    annullaProspetto(id: number, extraHttpRequestParams?: any): Observable<PostPutProspettoResponse>;

    /**
    * 
    * Restituisce true se le procedure di passaggio dal Q2 al Q3 sono andate a buon fine.
    * @param idProspetto L&#39;id del prospetto.
    */
    checkPassaggioQ3(idProspetto: number, extraHttpRequestParams?: any): Observable<boolean>;

    /**
    * 
    * Elimina dati registrati su sistema.
    * @param id id del Prospetto da eliminare.
    */
    deleteProspetto(id: number, extraHttpRequestParams?: any): Observable<Prospetto>;

    /**
    * 
    * Duplica il prospetto registrato su sistema.
    * @param id 
    */
    duplicaProspetto(id: number, extraHttpRequestParams?: any): Observable<PostPutProspettoResponse>;

    /**
    * 
    * Genera pdf del prospetto registrato su sistema.
    * @param idProspetto 
    */
    generaPdf(idProspetto: number, extraHttpRequestParams?: any): Observable<Blob>;

    /**
    * 
    * Restituisce dati registrati su sistema.
    * @param id L&#39;id del prospetto.
    */
    getAssunzioniPubblicheByIdProspetto(id: number, extraHttpRequestParams?: any): Observable<Array<AssPubbliche>>;

    /**
    * 
    * Restituisce il resultCheck.
    * @param codiceFiscale Codice fiscale.
    */
    getCheckCodiceFiscale(codiceFiscale: string, extraHttpRequestParams?: any): Observable<string>;

    /**
    * 
    * Restituisce il resultCheck.
    * @param idProspetto L&#39;id del prospetto.
    */
    getCheckScopertureDatoriLavoriPubblici(idProspetto: number, extraHttpRequestParams?: any): Observable<string>;

    /**
    * 
    * Restituisce il prospetto per id.
    * @param id L&#39;id del prospetto.
    */
    getProspettoById(id: number, extraHttpRequestParams?: any): Observable<Prospetto>;

    /**
    * 
    * Restituisce dati registrati su sistema.
    * @param ricercaProspetto 
    * @param offset Il numero di record da ignorare prima di iniziare a raccogliere i risultati.
    * @param limit Il numero di record da restituire.
    * @param sort Il campo di sort.
    * @param direction La direzione di sort.
    */
    getRicercaProspetti(ricercaProspetto: RicercaProspetto, offset?: number, limit?: number, sort?: string, direction?: 'asc' | 'desc' | '', extraHttpRequestParams?: any): Observable<PagedResponseProspetto>;

    /**
    * 
    * Restituisce dati registrati su sistema.
    * @param flagBozza flag che indica il tipo di salvataggio.
    * @param prospetto 
    */
    postProspetto(flagBozza: boolean, prospetto: Prospetto, extraHttpRequestParams?: any): Observable<{}>;

    /**
    * 
    * Restituisce dati registrati su sistema.
    * @param id id prospetto da modificare.
    * @param flagBozza 
    * @param prospetto 
    */
    putProspettoUpdate(id: number, flagBozza: boolean, prospetto: Prospetto, extraHttpRequestParams?: any): Observable<PostPutProspettoResponse>;

    /**
    * 
    * Restituisce dati registrati su sistema.
    * @param idStatoProspetto 
    * @param prospetto 
    */
    putStatoProspettoUpdate(idStatoProspetto: number, prospetto: Prospetto, extraHttpRequestParams?: any): Observable<Prospetto>;

    /**
    * 
    * Restituisce l&#39;esito dell&#39;operazione come stringa.
    * @param reinviaProspetto 
    */
    reinviaProspetto(reinviaProspetto: ReinviaProspetto, extraHttpRequestParams?: any): Observable<string>;

    /**
    * 
    * Rettifica il prospetto registrato su sistema.
    * @param id 
    */
    rettificaProspetto(id: number, extraHttpRequestParams?: any): Observable<PostPutProspettoResponse>;

    /**
    * 
    * salva i pdf dei prospetti registrati su sistema.
    * @param idProspettos 
    */
    salvaPdf(idProspettos: Array<string>, extraHttpRequestParams?: any): Observable<string>;

}
