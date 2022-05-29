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
/* tslint:disable:no-unused-variable member-ordering */

import { Inject, Injectable, Optional }                      from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams,
         HttpResponse, HttpEvent }                           from '@angular/common/http';
import { CustomHttpUrlEncodingCodec }                        from '../encoder';

import { Observable }                                        from 'rxjs';

import { ApiError } from '../model/apiError';
import { AssPubbliche } from '../model/assPubbliche';
import { PagedResponseProspetto } from '../model/pagedResponseProspetto';
import { PostPutProspettoResponse } from '../model/postPutProspettoResponse';
import { Prospetto } from '../model/prospetto';
import { ReinviaProspetto } from '../model/reinviaProspetto';
import { RicercaProspetto } from '../model/ricercaProspetto';

import { BASE_PATH, COLLECTION_FORMATS }                     from '../variables';
import { Configuration, FormParams }                         from '../configuration';
import { ProspettoServiceInterface }                            from './prospetto.serviceInterface';


@Injectable()
export class ProspettoService implements ProspettoServiceInterface {

    protected basePath = 'http://localhost:8080/prodisweb/api/v1';
    public defaultHeaders = new HttpHeaders();
    public configuration = new Configuration();

    constructor(protected httpClient: HttpClient, @Optional()@Inject(BASE_PATH) basePath: string, @Optional() configuration: Configuration) {
        if (basePath) {
            this.basePath = basePath;
        }
        if (configuration) {
            this.configuration = configuration;
            this.basePath = basePath || configuration.basePath || this.basePath;
        }
    }

    /**
     * @param consumes string[] mime-types
     * @return true: consumes contains 'multipart/form-data', false: otherwise
     */
    private canConsumeForm(consumes: string[]): boolean {
        const form = 'multipart/form-data';
        for (const consume of consumes) {
            if (form === consume) {
                return true;
            }
        }
        return false;
    }


    /**
     * 
     * Annulla il prospetto registrato su sistema.
     * @param id 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public annullaProspetto(id: number, observe?: 'body', reportProgress?: boolean): Observable<PostPutProspettoResponse>;
    public annullaProspetto(id: number, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<PostPutProspettoResponse>>;
    public annullaProspetto(id: number, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<PostPutProspettoResponse>>;
    public annullaProspetto(id: number, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (id === null || id === undefined) {
            throw new Error('Required parameter id was null or undefined when calling annullaProspetto.');
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
        ];

        return this.httpClient.put<PostPutProspettoResponse>(`${this.basePath}/prospetto/annulla/${encodeURIComponent(String(id))}`,
            null,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * 
     * Restituisce true se le procedure di passaggio dal Q2 al Q3 sono andate a buon fine.
     * @param idProspetto L&#39;id del prospetto.
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public checkPassaggioQ3(idProspetto: number, observe?: 'body', reportProgress?: boolean): Observable<boolean>;
    public checkPassaggioQ3(idProspetto: number, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<boolean>>;
    public checkPassaggioQ3(idProspetto: number, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<boolean>>;
    public checkPassaggioQ3(idProspetto: number, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (idProspetto === null || idProspetto === undefined) {
            throw new Error('Required parameter idProspetto was null or undefined when calling checkPassaggioQ3.');
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
        ];

        return this.httpClient.get<boolean>(`${this.basePath}/prospetto/checkPassaggioQ3/${encodeURIComponent(String(idProspetto))}`,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * 
     * Elimina dati registrati su sistema.
     * @param id id del Prospetto da eliminare.
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public deleteProspetto(id: number, observe?: 'body', reportProgress?: boolean): Observable<Prospetto>;
    public deleteProspetto(id: number, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Prospetto>>;
    public deleteProspetto(id: number, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Prospetto>>;
    public deleteProspetto(id: number, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (id === null || id === undefined) {
            throw new Error('Required parameter id was null or undefined when calling deleteProspetto.');
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
        ];

        return this.httpClient.delete<Prospetto>(`${this.basePath}/prospetto/delete/${encodeURIComponent(String(id))}`,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * 
     * Duplica il prospetto registrato su sistema.
     * @param id 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public duplicaProspetto(id: number, observe?: 'body', reportProgress?: boolean): Observable<PostPutProspettoResponse>;
    public duplicaProspetto(id: number, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<PostPutProspettoResponse>>;
    public duplicaProspetto(id: number, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<PostPutProspettoResponse>>;
    public duplicaProspetto(id: number, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (id === null || id === undefined) {
            throw new Error('Required parameter id was null or undefined when calling duplicaProspetto.');
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
        ];

        return this.httpClient.put<PostPutProspettoResponse>(`${this.basePath}/prospetto/duplica/${encodeURIComponent(String(id))}`,
            null,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * 
     * Genera pdf del prospetto registrato su sistema.
     * @param idProspetto 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public generaPdf(idProspetto: number, observe?: 'body', reportProgress?: boolean): Observable<Blob>;
    public generaPdf(idProspetto: number, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Blob>>;
    public generaPdf(idProspetto: number, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Blob>>;
    public generaPdf(idProspetto: number, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (idProspetto === null || idProspetto === undefined) {
            throw new Error('Required parameter idProspetto was null or undefined when calling generaPdf.');
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/pdf'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
        ];

        return this.httpClient.put(`${this.basePath}/prospetto/genera-pdf/${encodeURIComponent(String(idProspetto))}`,
            null,
            {
                responseType: "blob",
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * 
     * Restituisce dati registrati su sistema.
     * @param id L&#39;id del prospetto.
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getAssunzioniPubblicheByIdProspetto(id: number, observe?: 'body', reportProgress?: boolean): Observable<Array<AssPubbliche>>;
    public getAssunzioniPubblicheByIdProspetto(id: number, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<AssPubbliche>>>;
    public getAssunzioniPubblicheByIdProspetto(id: number, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<AssPubbliche>>>;
    public getAssunzioniPubblicheByIdProspetto(id: number, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (id === null || id === undefined) {
            throw new Error('Required parameter id was null or undefined when calling getAssunzioniPubblicheByIdProspetto.');
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
        ];

        return this.httpClient.get<Array<AssPubbliche>>(`${this.basePath}/prospetto/assunzioniPubbliche/${encodeURIComponent(String(id))}`,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * 
     * Restituisce il resultCheck.
     * @param codiceFiscale Codice fiscale.
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getCheckCodiceFiscale(codiceFiscale: string, observe?: 'body', reportProgress?: boolean): Observable<string>;
    public getCheckCodiceFiscale(codiceFiscale: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<string>>;
    public getCheckCodiceFiscale(codiceFiscale: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<string>>;
    public getCheckCodiceFiscale(codiceFiscale: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (codiceFiscale === null || codiceFiscale === undefined) {
            throw new Error('Required parameter codiceFiscale was null or undefined when calling getCheckCodiceFiscale.');
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
        ];

        return this.httpClient.get<string>(`${this.basePath}/prospetto/checkCodiceFiscale/${encodeURIComponent(String(codiceFiscale))}`,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * 
     * Restituisce il resultCheck.
     * @param idProspetto L&#39;id del prospetto.
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getCheckScopertureDatoriLavoriPubblici(idProspetto: number, observe?: 'body', reportProgress?: boolean): Observable<string>;
    public getCheckScopertureDatoriLavoriPubblici(idProspetto: number, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<string>>;
    public getCheckScopertureDatoriLavoriPubblici(idProspetto: number, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<string>>;
    public getCheckScopertureDatoriLavoriPubblici(idProspetto: number, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (idProspetto === null || idProspetto === undefined) {
            throw new Error('Required parameter idProspetto was null or undefined when calling getCheckScopertureDatoriLavoriPubblici.');
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
        ];

        return this.httpClient.get<string>(`${this.basePath}/prospetto/checkScopertureDatoriLavoriPubblici/${encodeURIComponent(String(idProspetto))}`,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * 
     * Restituisce il prospetto per id.
     * @param id L&#39;id del prospetto.
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getProspettoById(id: number, observe?: 'body', reportProgress?: boolean): Observable<Prospetto>;
    public getProspettoById(id: number, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Prospetto>>;
    public getProspettoById(id: number, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Prospetto>>;
    public getProspettoById(id: number, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (id === null || id === undefined) {
            throw new Error('Required parameter id was null or undefined when calling getProspettoById.');
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
        ];

        return this.httpClient.get<Prospetto>(`${this.basePath}/prospetto/${encodeURIComponent(String(id))}`,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * 
     * Restituisce dati registrati su sistema.
     * @param ricercaProspetto 
     * @param offset Il numero di record da ignorare prima di iniziare a raccogliere i risultati.
     * @param limit Il numero di record da restituire.
     * @param sort Il campo di sort.
     * @param direction La direzione di sort.
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getRicercaProspetti(ricercaProspetto: RicercaProspetto, offset?: number, limit?: number, sort?: string, direction?: 'asc' | 'desc' | '', observe?: 'body', reportProgress?: boolean): Observable<PagedResponseProspetto>;
    public getRicercaProspetti(ricercaProspetto: RicercaProspetto, offset?: number, limit?: number, sort?: string, direction?: 'asc' | 'desc' | '', observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<PagedResponseProspetto>>;
    public getRicercaProspetti(ricercaProspetto: RicercaProspetto, offset?: number, limit?: number, sort?: string, direction?: 'asc' | 'desc' | '', observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<PagedResponseProspetto>>;
    public getRicercaProspetti(ricercaProspetto: RicercaProspetto, offset?: number, limit?: number, sort?: string, direction?: 'asc' | 'desc' | '', observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (ricercaProspetto === null || ricercaProspetto === undefined) {
            throw new Error('Required parameter ricercaProspetto was null or undefined when calling getRicercaProspetti.');
        }

        let queryParameters = new HttpParams({encoder: new CustomHttpUrlEncodingCodec()});
        if (offset !== undefined && offset !== null) {
            queryParameters = queryParameters.set('offset', <any>offset);
        }
        if (limit !== undefined && limit !== null) {
            queryParameters = queryParameters.set('limit', <any>limit);
        }
        if (sort !== undefined && sort !== null) {
            queryParameters = queryParameters.set('sort', <any>sort);
        }
        if (direction !== undefined && direction !== null) {
            queryParameters = queryParameters.set('direction', <any>direction);
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
        ];
        const httpContentTypeSelected: string | undefined = this.configuration.selectHeaderContentType(consumes);
        if (httpContentTypeSelected != undefined) {
            headers = headers.set('Content-Type', httpContentTypeSelected);
        }

        return this.httpClient.post<PagedResponseProspetto>(`${this.basePath}/prospetto/ricerca`,
            ricercaProspetto,
            {
                params: queryParameters,
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * 
     * Restituisce dati registrati su sistema.
     * @param flagBozza flag che indica il tipo di salvataggio.
     * @param prospetto 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public postProspetto(flagBozza: boolean, prospetto: Prospetto, observe?: 'body', reportProgress?: boolean): Observable<any>;
    public postProspetto(flagBozza: boolean, prospetto: Prospetto, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<any>>;
    public postProspetto(flagBozza: boolean, prospetto: Prospetto, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<any>>;
    public postProspetto(flagBozza: boolean, prospetto: Prospetto, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (flagBozza === null || flagBozza === undefined) {
            throw new Error('Required parameter flagBozza was null or undefined when calling postProspetto.');
        }
        if (prospetto === null || prospetto === undefined) {
            throw new Error('Required parameter prospetto was null or undefined when calling postProspetto.');
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
        ];
        const httpContentTypeSelected: string | undefined = this.configuration.selectHeaderContentType(consumes);
        if (httpContentTypeSelected != undefined) {
            headers = headers.set('Content-Type', httpContentTypeSelected);
        }

        return this.httpClient.post<any>(`${this.basePath}/prospetto/${encodeURIComponent(String(flagBozza))}`,
            prospetto,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * 
     * Restituisce dati registrati su sistema.
     * @param id id prospetto da modificare.
     * @param flagBozza 
     * @param prospetto 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public putProspettoUpdate(id: number, flagBozza: boolean, prospetto: Prospetto, observe?: 'body', reportProgress?: boolean): Observable<PostPutProspettoResponse>;
    public putProspettoUpdate(id: number, flagBozza: boolean, prospetto: Prospetto, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<PostPutProspettoResponse>>;
    public putProspettoUpdate(id: number, flagBozza: boolean, prospetto: Prospetto, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<PostPutProspettoResponse>>;
    public putProspettoUpdate(id: number, flagBozza: boolean, prospetto: Prospetto, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (id === null || id === undefined) {
            throw new Error('Required parameter id was null or undefined when calling putProspettoUpdate.');
        }
        if (flagBozza === null || flagBozza === undefined) {
            throw new Error('Required parameter flagBozza was null or undefined when calling putProspettoUpdate.');
        }
        if (prospetto === null || prospetto === undefined) {
            throw new Error('Required parameter prospetto was null or undefined when calling putProspettoUpdate.');
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
        ];
        const httpContentTypeSelected: string | undefined = this.configuration.selectHeaderContentType(consumes);
        if (httpContentTypeSelected != undefined) {
            headers = headers.set('Content-Type', httpContentTypeSelected);
        }

        return this.httpClient.put<PostPutProspettoResponse>(`${this.basePath}/prospetto/update/${encodeURIComponent(String(id))}/${encodeURIComponent(String(flagBozza))}`,
            prospetto,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * 
     * Restituisce dati registrati su sistema.
     * @param idStatoProspetto 
     * @param prospetto 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public putStatoProspettoUpdate(idStatoProspetto: number, prospetto: Prospetto, observe?: 'body', reportProgress?: boolean): Observable<Prospetto>;
    public putStatoProspettoUpdate(idStatoProspetto: number, prospetto: Prospetto, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Prospetto>>;
    public putStatoProspettoUpdate(idStatoProspetto: number, prospetto: Prospetto, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Prospetto>>;
    public putStatoProspettoUpdate(idStatoProspetto: number, prospetto: Prospetto, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (idStatoProspetto === null || idStatoProspetto === undefined) {
            throw new Error('Required parameter idStatoProspetto was null or undefined when calling putStatoProspettoUpdate.');
        }
        if (prospetto === null || prospetto === undefined) {
            throw new Error('Required parameter prospetto was null or undefined when calling putStatoProspettoUpdate.');
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
        ];
        const httpContentTypeSelected: string | undefined = this.configuration.selectHeaderContentType(consumes);
        if (httpContentTypeSelected != undefined) {
            headers = headers.set('Content-Type', httpContentTypeSelected);
        }

        return this.httpClient.put<Prospetto>(`${this.basePath}/prospetto/stato-prospetto/${encodeURIComponent(String(idStatoProspetto))}`,
            prospetto,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * 
     * Restituisce l&#39;esito dell&#39;operazione come stringa.
     * @param reinviaProspetto 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public reinviaProspetto(reinviaProspetto: ReinviaProspetto, observe?: 'body', reportProgress?: boolean): Observable<string>;
    public reinviaProspetto(reinviaProspetto: ReinviaProspetto, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<string>>;
    public reinviaProspetto(reinviaProspetto: ReinviaProspetto, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<string>>;
    public reinviaProspetto(reinviaProspetto: ReinviaProspetto, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (reinviaProspetto === null || reinviaProspetto === undefined) {
            throw new Error('Required parameter reinviaProspetto was null or undefined when calling reinviaProspetto.');
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
        ];
        const httpContentTypeSelected: string | undefined = this.configuration.selectHeaderContentType(consumes);
        if (httpContentTypeSelected != undefined) {
            headers = headers.set('Content-Type', httpContentTypeSelected);
        }

        return this.httpClient.put<string>(`${this.basePath}/prospetto/reinvia`,
            reinviaProspetto,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * 
     * Rettifica il prospetto registrato su sistema.
     * @param id 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public rettificaProspetto(id: number, observe?: 'body', reportProgress?: boolean): Observable<PostPutProspettoResponse>;
    public rettificaProspetto(id: number, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<PostPutProspettoResponse>>;
    public rettificaProspetto(id: number, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<PostPutProspettoResponse>>;
    public rettificaProspetto(id: number, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (id === null || id === undefined) {
            throw new Error('Required parameter id was null or undefined when calling rettificaProspetto.');
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
        ];

        return this.httpClient.put<PostPutProspettoResponse>(`${this.basePath}/prospetto/rettifica/${encodeURIComponent(String(id))}`,
            null,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * 
     * salva i pdf dei prospetti registrati su sistema.
     * @param idProspettos 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public salvaPdf(idProspettos: Array<string>, observe?: 'body', reportProgress?: boolean): Observable<string>;
    public salvaPdf(idProspettos: Array<string>, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<string>>;
    public salvaPdf(idProspettos: Array<string>, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<string>>;
    public salvaPdf(idProspettos: Array<string>, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (idProspettos === null || idProspettos === undefined) {
            throw new Error('Required parameter idProspettos was null or undefined when calling salvaPdf.');
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
        ];
        const httpContentTypeSelected: string | undefined = this.configuration.selectHeaderContentType(consumes);
        if (httpContentTypeSelected != undefined) {
            headers = headers.set('Content-Type', httpContentTypeSelected);
        }

        return this.httpClient.put<string>(`${this.basePath}/prospetto/salva-pdf`,
            idProspettos,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

}