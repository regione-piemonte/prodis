/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { NgModule, ModuleWithProviders, SkipSelf, Optional } from '@angular/core';
import { Configuration } from './configuration';
import { HttpClient } from '@angular/common/http';


import { CategorieEscluseService } from './api/categorieEscluse.service';
import { CommonService } from './api/common.service';
import { DatiProvincialiService } from './api/datiProvinciali.service';
import { DecodificaService } from './api/decodifica.service';
import { ProspettoService } from './api/prospetto.service';
import { RiepilogoService } from './api/riepilogo.service';
import { SilpService } from './api/silp.service';
import { SystemService } from './api/system.service';
import { UtenteService } from './api/utente.service';

@NgModule({
  imports:      [],
  declarations: [],
  exports:      [],
  providers: [
    CategorieEscluseService,
    CommonService,
    DatiProvincialiService,
    DecodificaService,
    ProspettoService,
    RiepilogoService,
    SilpService,
    SystemService,
    UtenteService ]
})
export class ApiModule {
    public static forRoot(configurationFactory: () => Configuration): ModuleWithProviders {
        return {
            ngModule: ApiModule,
            providers: [ { provide: Configuration, useFactory: configurationFactory } ]
        };
    }

    constructor( @Optional() @SkipSelf() parentModule: ApiModule,
                 @Optional() http: HttpClient) {
        if (parentModule) {
            throw new Error('ApiModule is already loaded. Import in your base AppModule only.');
        }
        if (!http) {
            throw new Error('You need to import the HttpClientModule in your AppModule! \n' +
            'See also https://github.com/angular/angular/issues/20575');
        }
    }
}
