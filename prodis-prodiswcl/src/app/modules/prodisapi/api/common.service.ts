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
import { DecodificaGenerica } from '../model/decodificaGenerica';
import { Ruolo } from '../model/ruolo';
import { UserAccessLog } from '../model/userAccessLog';

import { BASE_PATH, COLLECTION_FORMATS }                     from '../variables';
import { Configuration, FormParams }                         from '../configuration';
import { CommonServiceInterface }                            from './common.serviceInterface';


@Injectable()
export class CommonService implements CommonServiceInterface {

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
     * Restituisce la data calcolata.
     * @param dataInput valore della data.
     * @param numeroGiorniLavorativi numero di giorni lavorativi da aggiungere
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getDataCalcolataConGiorniLavorativi(dataInput: string, numeroGiorniLavorativi: number, observe?: 'body', reportProgress?: boolean): Observable<Date>;
    public getDataCalcolataConGiorniLavorativi(dataInput: string, numeroGiorniLavorativi: number, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Date>>;
    public getDataCalcolataConGiorniLavorativi(dataInput: string, numeroGiorniLavorativi: number, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Date>>;
    public getDataCalcolataConGiorniLavorativi(dataInput: string, numeroGiorniLavorativi: number, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (dataInput === null || dataInput === undefined) {
            throw new Error('Required parameter dataInput was null or undefined when calling getDataCalcolataConGiorniLavorativi.');
        }
        if (numeroGiorniLavorativi === null || numeroGiorniLavorativi === undefined) {
            throw new Error('Required parameter numeroGiorniLavorativi was null or undefined when calling getDataCalcolataConGiorniLavorativi.');
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

        return this.httpClient.get<Date>(`${this.basePath}/common/getDataCalcolataConGiorniLavorativi/${encodeURIComponent(String(dataInput))}/${encodeURIComponent(String(numeroGiorniLavorativi))}`,
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
     * Restituisce il parametro.
     * @param nomeParametro Nome del Parametro.
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getParametro(nomeParametro: string, observe?: 'body', reportProgress?: boolean): Observable<DecodificaGenerica>;
    public getParametro(nomeParametro: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<DecodificaGenerica>>;
    public getParametro(nomeParametro: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<DecodificaGenerica>>;
    public getParametro(nomeParametro: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (nomeParametro === null || nomeParametro === undefined) {
            throw new Error('Required parameter nomeParametro was null or undefined when calling getParametro.');
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

        return this.httpClient.get<DecodificaGenerica>(`${this.basePath}/common/getParametro/${encodeURIComponent(String(nomeParametro))}`,
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
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getRuolo(observe?: 'body', reportProgress?: boolean): Observable<Array<Ruolo>>;
    public getRuolo(observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<Ruolo>>>;
    public getRuolo(observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<Ruolo>>>;
    public getRuolo(observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

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

        return this.httpClient.get<Array<Ruolo>>(`${this.basePath}/common/ruolo`,
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
     * Inserisce info su tabella di log per accesso.
     * @param loUserLog 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public insertUserAccessLog(loUserLog: UserAccessLog, observe?: 'body', reportProgress?: boolean): Observable<UserAccessLog>;
    public insertUserAccessLog(loUserLog: UserAccessLog, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<UserAccessLog>>;
    public insertUserAccessLog(loUserLog: UserAccessLog, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<UserAccessLog>>;
    public insertUserAccessLog(loUserLog: UserAccessLog, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (loUserLog === null || loUserLog === undefined) {
            throw new Error('Required parameter loUserLog was null or undefined when calling insertUserAccessLog.');
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

        return this.httpClient.post<UserAccessLog>(`${this.basePath}/common/userAccessLog`,
            loUserLog,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

}