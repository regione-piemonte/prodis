/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { SceltaRuoloComponent } from './components/scelta-ruolo/scelta-ruolo.component';
import { RicercaProspettiComponent } from './components/ricerca-prospetti/ricerca-prospetti.component';
import { DettaglioProspettoComponent } from './components/dettaglio-prospetto/dettaglio-prospetto.component';
import { DatiAziendaComponent } from './components/dettaglio-prospetto/dati-azienda/dati-azienda.component';
import { ProvinceLavoratoriComputabiliComponent } from './components/dettaglio-prospetto/province-lavoratori-computabili/province-lavoratori-computabili.component';
import { DettaglioLavoratoreComponent } from './components/dettaglio-prospetto/province-lavoratori-computabili/dettaglio-lavoratore/dettaglio-lavoratore.component';
import { CheckRuoloGuard } from 'src/app/guards';
import { RecuperoCodiceRegComponent } from './components/recupero-codice-reg/recupero-codice-reg.component';
import { EsitoInvioProspettoComponent } from './components/dettaglio-prospetto/esito-invio-prospetto/esito-invio-prospetto.component';

const prospettoRoutes: Routes = [
  { path: 'scelta-ruolo', component: SceltaRuoloComponent },
  { path: 'ricerca-prospetti', component: RicercaProspettiComponent, canLoad: [CheckRuoloGuard], canActivate: [CheckRuoloGuard], canActivateChild: [CheckRuoloGuard] },
  { path: 'dati-azienda', component: DatiAziendaComponent, canLoad: [CheckRuoloGuard], canActivate: [CheckRuoloGuard], canActivateChild: [CheckRuoloGuard] },
  { path: 'province-lavoratori-computabili', component: ProvinceLavoratoriComputabiliComponent, canLoad: [CheckRuoloGuard], canActivate: [CheckRuoloGuard], canActivateChild: [CheckRuoloGuard] },
  { path: 'dettaglio-lavoratore', component: DettaglioLavoratoreComponent, canLoad: [CheckRuoloGuard], canActivate: [CheckRuoloGuard], canActivateChild: [CheckRuoloGuard] },
  { path: 'dettaglio-prospetto', component: DettaglioProspettoComponent, canLoad: [CheckRuoloGuard], canActivate: [CheckRuoloGuard], canActivateChild: [CheckRuoloGuard] },
  { path: 'esito-invio-prospetto', component: EsitoInvioProspettoComponent, canLoad: [CheckRuoloGuard], canActivate: [CheckRuoloGuard], canActivateChild: [CheckRuoloGuard] },
  { path: 'recupero-codice-regionale', component: RecuperoCodiceRegComponent, canLoad: [CheckRuoloGuard], canActivate: [CheckRuoloGuard], canActivateChild: [CheckRuoloGuard] },
];

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forChild(prospettoRoutes),
    CommonModule
  ],
  exports: [RouterModule]
})
export class ProspettoRoutingModule { }
