/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
export * from './categorieEscluse.service';
import { CategorieEscluseService } from './categorieEscluse.service';
export * from './categorieEscluse.serviceInterface'
export * from './common.service';
import { CommonService } from './common.service';
export * from './common.serviceInterface'
export * from './datiProvinciali.service';
import { DatiProvincialiService } from './datiProvinciali.service';
export * from './datiProvinciali.serviceInterface'
export * from './decodifica.service';
import { DecodificaService } from './decodifica.service';
export * from './decodifica.serviceInterface'
export * from './prospetto.service';
import { ProspettoService } from './prospetto.service';
export * from './prospetto.serviceInterface'
export * from './riepilogo.service';
import { RiepilogoService } from './riepilogo.service';
export * from './riepilogo.serviceInterface'
export * from './silp.service';
import { SilpService } from './silp.service';
export * from './silp.serviceInterface'
export * from './system.service';
import { SystemService } from './system.service';
export * from './system.serviceInterface'
export * from './utente.service';
import { UtenteService } from './utente.service';
export * from './utente.serviceInterface'
export const APIS = [CategorieEscluseService, CommonService, DatiProvincialiService, DecodificaService, ProspettoService, RiepilogoService, SilpService, SystemService, UtenteService];
