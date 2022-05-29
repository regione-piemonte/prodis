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
import { PagedResponseCategorieEscluse } from '../model/pagedResponseCategorieEscluse';


import { Configuration }                                     from '../configuration';


export interface CategorieEscluseServiceInterface {
    defaultHeaders: HttpHeaders;
    configuration: Configuration;
    

    /**
    * 
    * Restituisce dati registrati su sistema.
    * @param idProspettoProv L&#39;idProspettoProv.
    * @param offset Il numero di record da ignorare prima di iniziare a raccogliere i risultati.
    * @param limit Il numero di record da restituire.
    * @param sort Il campo di sort.
    * @param direction La direzione di sort.
    */
    getCategorieEsclusePagByIdProspettoProv(idProspettoProv: number, offset?: number, limit?: number, sort?: string, direction?: 'asc' | 'desc' | '', extraHttpRequestParams?: any): Observable<PagedResponseCategorieEscluse>;

}
