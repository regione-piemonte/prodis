/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { NgbAccordionModule, NgbDatepickerConfig, NgbDatepickerModule, NgbModalModule, NgbNavModule, NgbTabsetModule } from '@ng-bootstrap/ng-bootstrap';
import { ProdiscommonModule } from '../prodiscommon/prodiscommon.module';
import { DatiAziendaComponent } from './components/dettaglio-prospetto/dati-azienda/dati-azienda.component';
import { DettaglioDatiAziendaComponent } from './components/dettaglio-prospetto/dati-azienda/dettaglio-dati-azienda/dettaglio-dati-azienda.component';
import { FormTabAssunzioniPubblSelComponent } from './components/dettaglio-prospetto/dati-azienda/form-tab-assunzioni-pubbl-sel/form-tab-assunzioni-pubbl-sel.component';
import { FormTabDatiGeneraliComponent } from './components/dettaglio-prospetto/dati-azienda/form-tab-dati-generali/form-tab-dati-generali.component';
import { FormTabDatiProspettoComponent } from './components/dettaglio-prospetto/dati-azienda/form-tab-dati-prospetto/form-tab-dati-prospetto.component';
import { TabAssunzioniPubblSelComponent } from './components/dettaglio-prospetto/dati-azienda/tab-assunzioni-pubbl-sel/tab-assunzioni-pubbl-sel.component';
import { TabDatiGeneraliComponent } from './components/dettaglio-prospetto/dati-azienda/tab-dati-generali/tab-dati-generali.component';
import { TabDatiProspettoComponent } from './components/dettaglio-prospetto/dati-azienda/tab-dati-prospetto/tab-dati-prospetto.component';
import { DettaglioProspettoComponent } from './components/dettaglio-prospetto/dettaglio-prospetto.component';
import { NavDettaglioProspettoComponent } from './components/dettaglio-prospetto/nav-dettaglio-prospetto/nav-dettaglio-prospetto.component';
import { DettaglioLavoratoreComponent } from './components/dettaglio-prospetto/province-lavoratori-computabili/dettaglio-lavoratore/dettaglio-lavoratore.component';
import { FormTabDettaglioProvincialeComponent } from './components/dettaglio-prospetto/province-lavoratori-computabili/form-tab-dettaglio-provinciale/form-tab-dettaglio-provinciale.component';
import { FormTabDisabiliCatProtetteComponent } from './components/dettaglio-prospetto/province-lavoratori-computabili/form-tab-disabili-cat-protette/form-tab-disabili-cat-protette.component';
import { FormTabElencoLavoratoriCompComponent } from './components/dettaglio-prospetto/province-lavoratori-computabili/form-tab-elenco-lavoratori-comp/form-tab-elenco-lavoratori-comp.component';
import { FormTabPartTimeIntermComponent } from './components/dettaglio-prospetto/province-lavoratori-computabili/form-tab-part-time-interm/form-tab-part-time-interm.component';
import { FormTabTelelavCatEscluseComponent } from './components/dettaglio-prospetto/province-lavoratori-computabili/form-tab-telelav-cat-escluse/form-tab-telelav-cat-escluse.component';
import { ProvinceLavoratoriComputabiliComponent } from './components/dettaglio-prospetto/province-lavoratori-computabili/province-lavoratori-computabili.component';
import { TabAltreConcessioniComponent } from './components/dettaglio-prospetto/province-lavoratori-computabili/tab-altre-concessioni/tab-altre-concessioni.component';
import { TabDettaglioProvinceComponent } from './components/dettaglio-prospetto/province-lavoratori-computabili/tab-dettaglio-province/tab-dettaglio-province.component';
import { TabDettaglioProvincialeComponent } from './components/dettaglio-prospetto/province-lavoratori-computabili/tab-dettaglio-provinciale/tab-dettaglio-provinciale.component';
import { TabDisabiliCatProtetteComponent } from './components/dettaglio-prospetto/province-lavoratori-computabili/tab-disabili-cat-protette/tab-disabili-cat-protette.component';
import { TabElencoLavoratoriCompComponent } from './components/dettaglio-prospetto/province-lavoratori-computabili/tab-elenco-lavoratori-comp/tab-elenco-lavoratori-comp.component';
import { TabPartTimeIntermComponent } from './components/dettaglio-prospetto/province-lavoratori-computabili/tab-part-time-interm/tab-part-time-interm.component';
import { TabTelelavCatEscluseComponent } from './components/dettaglio-prospetto/province-lavoratori-computabili/tab-telelav-cat-escluse/tab-telelav-cat-escluse.component';
import { FormTabCompensazioniComponent } from './components/dettaglio-prospetto/riepilogo/form-tab-compensazioni/form-tab-compensazioni.component';
import { FormTabPostiDispComponent } from './components/dettaglio-prospetto/riepilogo/form-tab-posti-disp/form-tab-posti-disp.component';
import { FormTabRiepilogoComponent } from './components/dettaglio-prospetto/riepilogo/form-tab-riepilogo/form-tab-riepilogo.component';
import { RiepilogoComponent } from './components/dettaglio-prospetto/riepilogo/riepilogo.component';
import { TabCompensazioniComponent } from './components/dettaglio-prospetto/riepilogo/tab-compensazioni/tab-compensazioni.component';
import { TabPostiDispComponent } from './components/dettaglio-prospetto/riepilogo/tab-posti-disp/tab-posti-disp.component';
import { TableRiepilogoDatiProvincialiComponent } from './components/dettaglio-prospetto/riepilogo/tab-riepilogo/sezioni-tab-riepilogo/table-riepilogo-dati-provinciali/table-riepilogo-dati-provinciali.component';
import { TabRiepilogoComponent } from './components/dettaglio-prospetto/riepilogo/tab-riepilogo/tab-riepilogo.component';
import { FormRicercaProspettiComponent } from './components/ricerca-prospetti/form-ricerca-prospetti/form-ricerca-prospetti.component';
import { RicercaProspettiComponent } from './components/ricerca-prospetti/ricerca-prospetti.component';
import { RisultatiRicercaProspettiComponent } from './components/ricerca-prospetti/risultati-ricerca-prospetti/risultati-ricerca-prospetti.component';
import { SceltaRuoloComponent } from './components/scelta-ruolo/scelta-ruolo.component';
import { ProspettoRoutingModule } from './prospetto-routing.module';
import { SezioneRiepilogoNazionaleUnoComponent } from './components/dettaglio-prospetto/riepilogo/tab-riepilogo/sezioni-tab-riepilogo/sezione-riepilogo-nazionale-uno/sezione-riepilogo-nazionale-uno.component';
import { SezioneRiepilogoNazionaleDueComponent } from './components/dettaglio-prospetto/riepilogo/tab-riepilogo/sezioni-tab-riepilogo/sezione-riepilogo-nazionale-due/sezione-riepilogo-nazionale-due.component';
import { ErrorHandlerComponent } from 'src/app/components/error-handler/error-handler.component';
import { FormTabAltreConcessioniComponent } from './components/dettaglio-prospetto/province-lavoratori-computabili/form-tab-altre-concessioni/form-tab-altre-concessioni.component';
import { RecuperoCodiceRegComponent } from './components/recupero-codice-reg/recupero-codice-reg.component';
import { EsitoInvioProspettoComponent } from './components/dettaglio-prospetto/esito-invio-prospetto/esito-invio-prospetto.component';
@NgModule({
  declarations: [SceltaRuoloComponent, RicercaProspettiComponent, DatiAziendaComponent,
    ProvinceLavoratoriComputabiliComponent, DettaglioLavoratoreComponent, FormRicercaProspettiComponent,
    RisultatiRicercaProspettiComponent, TabDettaglioProvinceComponent,
    DettaglioProspettoComponent, NavDettaglioProspettoComponent, RiepilogoComponent,
    TabAssunzioniPubblSelComponent, TabDatiProspettoComponent, TabDisabiliCatProtetteComponent,
    TabElencoLavoratoriCompComponent, TabAltreConcessioniComponent, DettaglioDatiAziendaComponent,
    TabDatiGeneraliComponent, TabDettaglioProvincialeComponent, TabPartTimeIntermComponent,
    TabTelelavCatEscluseComponent, TabPostiDispComponent, TabCompensazioniComponent,
    TabRiepilogoComponent, FormTabDatiGeneraliComponent, FormTabDatiProspettoComponent,
    FormTabAssunzioniPubblSelComponent, FormTabTelelavCatEscluseComponent,
    FormTabDisabiliCatProtetteComponent, FormTabDettaglioProvincialeComponent,
    FormTabPartTimeIntermComponent, FormTabRiepilogoComponent,
    FormTabPostiDispComponent, FormTabCompensazioniComponent,
    TableRiepilogoDatiProvincialiComponent, FormTabElencoLavoratoriCompComponent,
    SezioneRiepilogoNazionaleUnoComponent, SezioneRiepilogoNazionaleDueComponent,ErrorHandlerComponent, FormTabAltreConcessioniComponent, RecuperoCodiceRegComponent, EsitoInvioProspettoComponent],
  imports: [
    CommonModule,
    ProdiscommonModule,
    ReactiveFormsModule,
    ProspettoRoutingModule,
    NgbAccordionModule,
    NgbDatepickerModule,
    NgbModalModule,
    NgbTabsetModule,
    NgbNavModule,
  ],
  providers: [
    NgbDatepickerConfig
  ],
})
export class ProspettoModule { }
