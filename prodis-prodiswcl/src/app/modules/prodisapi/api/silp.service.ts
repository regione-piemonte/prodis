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
import { DatiAzienda } from '../model/datiAzienda';
import { LavoratoriSilp } from '../model/lavoratoriSilp';
import { SedeLegale } from '../model/sedeLegale';

import { BASE_PATH, COLLECTION_FORMATS }                     from '../variables';
import { Configuration, FormParams }                         from '../configuration';
import { SilpServiceInterface }                            from './silp.serviceInterface';


@Injectable()
export class SilpService implements SilpServiceInterface {

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
     * Restituisce dati registrati su sistema.
     * @param codiceFiscale codiceFiscale
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getAzienda(codiceFiscale: string, observe?: 'body', reportProgress?: boolean): Observable<DatiAzienda>;
    public getAzienda(codiceFiscale: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<DatiAzienda>>;
    public getAzienda(codiceFiscale: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<DatiAzienda>>;
    public getAzienda(codiceFiscale: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (codiceFiscale === null || codiceFiscale === undefined) {
            throw new Error('Required parameter codiceFiscale was null or undefined when calling getAzienda.');
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

        return this.httpClient.get<DatiAzienda>(`${this.basePath}/silp/azienda/${encodeURIComponent(String(codiceFiscale))}`,
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
     * @param codiceFiscale codiceFiscale
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getLavoratore(codiceFiscale: string, observe?: 'body', reportProgress?: boolean): Observable<LavoratoriSilp>;
    public getLavoratore(codiceFiscale: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<LavoratoriSilp>>;
    public getLavoratore(codiceFiscale: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<LavoratoriSilp>>;
    public getLavoratore(codiceFiscale: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (codiceFiscale === null || codiceFiscale === undefined) {
            throw new Error('Required parameter codiceFiscale was null or undefined when calling getLavoratore.');
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

        return this.httpClient.get<LavoratoriSilp>(`${this.basePath}/silp/lavoratore/${encodeURIComponent(String(codiceFiscale))}`,
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
     * @param idAzienda idAzienda
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getSedi(idAzienda: string, observe?: 'body', reportProgress?: boolean): Observable<SedeLegale>;
    public getSedi(idAzienda: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<SedeLegale>>;
    public getSedi(idAzienda: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<SedeLegale>>;
    public getSedi(idAzienda: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (idAzienda === null || idAzienda === undefined) {
            throw new Error('Required parameter idAzienda was null or undefined when calling getSedi.');
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

        return this.httpClient.get<SedeLegale>(`${this.basePath}/silp/sedi/${encodeURIComponent(String(idAzienda))}`,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

}
