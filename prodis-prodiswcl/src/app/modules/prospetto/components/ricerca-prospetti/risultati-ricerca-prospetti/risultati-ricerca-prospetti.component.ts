/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { COMUNICAZIONE_ANNULLAMENTO_ID, ID_STATO_PROSPETTO_BOZZA, ID_STATO_PROSPETTO_CANCELLATA, ID_STATO_PROSPETTO_DA_INVIARE, PARAMETRO_TERMINE_ANNULLAMENTO, PARAMETRO_TERMINE_RETTIFICA, STATO_PROSPETTO_DA_INVIARE, TYPE_ALERT_DANGER } from 'src/app/constants';
import { PaginationDataChange } from 'src/app/models/pagination-data-change';
import { CommonService, ConfermaRiepilogo, ConfermaRiepilogoProspetto, DatiAzienda, DecodificaGenerica, DecodificaService, PagedResponseProspetto, Prospetto, ProspettoService, RicercaProspetto, RiepilogoService, Ruolo, StatiEsteri } from 'src/app/modules/prodisapi';
import { IsInvalidClassDirective } from 'src/app/modules/prodiscommon/directives';
import { PromptModalService } from 'src/app/modules/prodiscommon/services';
import { ProdisStorageService } from 'src/app/services/storage/prodis-storage.service';
import { UtilitiesService } from 'src/app/services/utilities.service';
import { Utils } from 'src/app/utils';

@Component({
  selector: 'prodis-risultati-ricerca-prospetti',
  templateUrl: './risultati-ricerca-prospetti.component.html',
  styleUrls: ['./risultati-ricerca-prospetti.component.scss']
})
export class RisultatiRicercaProspettiComponent implements OnInit {

  @Input() pagedResponse: PagedResponseProspetto;
  @Input() currentPaginationData: PaginationDataChange;
  @Input() filtriRicercaProspetto: RicercaProspetto;

  urlParams: Params;
  dataTimbroPostale: Date;
  listMsg: string[] = [];
  elencoStatiEsteri: StatiEsteri[] = [];

  @Output() readonly changePaginationData = new EventEmitter<PaginationDataChange>();
  @Output() readonly chiamiamoloInAltroModo = new EventEmitter<PaginationDataChange>();
  @Output() readonly erroriInPagina = new EventEmitter<any>();
  ruolo: Ruolo;

  constructor(
    private router: Router,
    private prodisStorageService: ProdisStorageService,
    private util: UtilitiesService,
    private activatedRoute: ActivatedRoute,
    private prospettoService: ProspettoService,
    private promptModalService: PromptModalService,
    private commonService: CommonService,
    private decodificaService: DecodificaService,
    private riepilogoService: RiepilogoService
  ) { }

  ngOnInit() {
    this.pulisciErroriInPagina();
    this.prodisStorageService.ruolo$.subscribe(item => this.ruolo = item);
    this.prodisStorageService.setUrlParams(this.activatedRoute.snapshot.params);
  }

  onChangePaginationData(event: PaginationDataChange) {
    this.currentPaginationData = event;
    this.changePaginationData.emit(event);
    this.urlParams = { page: this.currentPaginationData.page };
    this.prodisStorageService.setUrlParams(this.urlParams);
  }

  async goToDettaglioProspetto(prospetto: Prospetto, flgEdit?: boolean) {
    if (prospetto && prospetto.id) {
      prospetto = await this.prospettoService.getProspettoById(prospetto.id).toPromise();
    }
    /* settare i dati esteri */
    const elencoDecodStatiEsteri: DecodificaGenerica[] = await this.decodificaService.postStatiEsteriDecodifica({ flgStatiUe: 'S' } as DecodificaGenerica
    ).toPromise();
    this.elencoStatiEsteri = [];
    elencoDecodStatiEsteri.forEach(item => {
      this.elencoStatiEsteri.push({
        id: item.idDecodifica,
        codNazioneMin: item.codDecodifica,
        dsStatiEsteri: item.dsDecodifica,
        siglaNazione: item.siglaNazione
      });
    });
    const datiAzienda = prospetto.datiAzienda;
    if (datiAzienda && datiAzienda.cfCapogruppo && datiAzienda.cfCapogruppo !== '') {
      const cf = datiAzienda.cfCapogruppo;
      let statoEsteroCorrente: StatiEsteri;
      if (datiAzienda.flgCapogruppoEstero && datiAzienda.flgCapogruppoEstero === 'S') {
        // sono un cf azienda straniero
        const siglaNazione = cf.substring(0, 2);
        statoEsteroCorrente = this.elencoStatiEsteri.find(item => item.siglaNazione === siglaNazione);
        prospetto.datiAzienda.cfCapogruppo = cf.substring(2, cf.length);
      } else {
        // sono un cf italiano
        statoEsteroCorrente = this.elencoStatiEsteri.find(item => item.siglaNazione === 'IT');
      }
      // if (cf.length === 11 || cf.length === 16) {
      //   statoEsteroCorrente = this.elencoStatiEsteri.find(item => item.siglaNazione === 'IT');
      // } else if (cf.length > 16) {
      //   const siglaNazione = cf.substring(0, 2);
      //   statoEsteroCorrente = this.elencoStatiEsteri.find(item => item.siglaNazione === siglaNazione);
      // }
      prospetto.datiAzienda.statiEsteri = statoEsteroCorrente;
    }



    this.prodisStorageService.setProspetto(prospetto);
    console.log(prospetto);
    if (flgEdit) {
      const prospettoToSave = Utils.clone(prospetto);
      this.prodisStorageService.setProspettoToSave(prospettoToSave);
    }
    this.router.navigateByUrl('/dettaglio-prospetto');
  }

  isBottoneDuplica(prospetto: Prospetto): boolean {
    return this.util.isProspettoPresentato(prospetto);
  }

  isBottoneVisualizza(prospetto: Prospetto): boolean {
    return this.util.isProspettoDaFirmare(prospetto)
      || this.util.isProspettoPresentato(prospetto)
      || this.util.isProspettoInRettifica(prospetto)
      || this.util.isProspettoRettificata(prospetto)
      || this.util.isProspettoAnnullata(prospetto)
      || this.util.isProspettoInAnnullamento(prospetto)
      || this.util.isProspettoCancellata(prospetto);
  }

  isBottoneModifica(prospetto: Prospetto): boolean {
    return this.util.isProspettoStatoBozza(prospetto);
  }

  isBottoneRettifica(prospetto: Prospetto): boolean {
    return this.util.isProspettoPresentato(prospetto);
  }

  isBottoneElimina(prospetto: Prospetto): boolean {
    return this.util.isProspettoStatoBozza(prospetto) || (
      !prospetto.numeroProtocollo && prospetto.statoProspetto.descrizione === STATO_PROSPETTO_DA_INVIARE && prospetto.comunicazione.id === COMUNICAZIONE_ANNULLAMENTO_ID
     );
  }

  isBottoneAnnulla(prospetto: Prospetto): boolean {
    return this.util.isProspettoPresentato(prospetto);
  }

  isBottoneStampa(prospetto: Prospetto) {
    return !this.util.isProspettoCancellata(prospetto);
  }

  async goToStampaProspetto(prospetto: Prospetto) {
    this.util.showSpinner();
    const idProspettoToPrint = prospetto.id;
    this.listMsg = [];
    this.pulisciErroriInPagina();
    try {
      const res = await this.prospettoService.generaPdf(idProspettoToPrint, 'response').toPromise();
      const fileName = Utils.extractFileNameFromContentDisposition(res.headers.get('Content-Disposition'));
      this.util.downloadBlobFile(fileName, res.body);
    } catch (e) {
      if (e.error && e.error.length > 0) {
        const listErrorMsg = e.error;
        listErrorMsg.forEach(element => {
          if (element.code === "SYS-SYS-E-0001") {
            this.listMsg.push("Non e' stato possibile generare il pdf. Contattare l'assistenza.");
          } else {
            this.listMsg.push(element.errorMessage);
          }
        });
        this.settaErroriInPagina();
      }
    } finally {
      this.util.hideSpinner();
    }
  }

  async goToEliminaProspetto(prospetto: Prospetto) {
    this.pulisciErroriInPagina();
    if (
      prospetto &&
      (prospetto.statoProspetto.id === ID_STATO_PROSPETTO_BOZZA ||  prospetto.statoProspetto.id === ID_STATO_PROSPETTO_DA_INVIARE)
    ) {
      const result = await this.promptModalService.openPrompt(
        'ELIMINAZIONE DEL PROSPETTO',
        'Si vuole procedere con l\'eliminazione del prospetto selezionato ? ',
        'Conferma', 'Annulla',
        null);

      if (result) {

        this.util.showSpinner();
        try {
          await this.prospettoService.putStatoProspettoUpdate(ID_STATO_PROSPETTO_CANCELLATA, prospetto).toPromise();

        } catch (e) {
          console.log('errore stato prospetto: ' + e.error);
          if (e.error && e.error.length > 0) {
            const listErrorMsg = e.error;
            listErrorMsg.forEach(element => {
              this.listMsg.push(element.errorMessage);
            });
            this.settaErroriInPagina();
          }
        } finally {
          this.util.hideSpinner();
        }

        this.chiamiamoloInAltroModo.emit(this.currentPaginationData);
      }
    }
  }

  async goToDuplicaProspetto(prospetto: Prospetto, flgEdit?: boolean) {
    this.pulisciErroriInPagina();
    const result = await this.promptModalService.openPrompt(
      'Duplica PROSPETTO',
      'Si vuole procedere con la duplicazione del prospetto selezionato?',
      'Conferma', 'Annulla',
      null);

    if (result) {
      /** se si conferma bisognera' richiamare
       * il servizio per la duplicazione del prospetto
       */
      this.util.showSpinner();
      let ritorno = null;
      try {
        let res = await this.prospettoService.duplicaProspetto(prospetto.id).toPromise();
        ritorno = res.prospetto;
        console.log('prospetto: ' + ritorno);
      } catch (e) {
        console.log('errore duplica prospetto: ' + e.error);
        if (e.error && e.error.length > 0) {
          const listErrorMsg = e.error;
          listErrorMsg.forEach(element => {
            this.listMsg.push(element.errorMessage);
          });
          this.settaErroriInPagina();
        }
      } finally {
        this.util.hideSpinner();
      }
      if (!Utils.isNullOrUndefined(ritorno)) {
        this.goToDettaglioProspetto(ritorno, true);
      }
    }
  }

  async goToRettificaProspetto(prospetto: Prospetto) {
    /** Da gestire e richiamare il metodo di rettifica del prospetto */
    this.listMsg = [];
    const parm = await this.commonService.getParametro(PARAMETRO_TERMINE_RETTIFICA).toPromise();
    const dataCalcolata = await this.commonService.getDataCalcolataConGiorniLavorativi(prospetto.dataTimbroPostale.toString(), Number( parm.codDecodifica)).toPromise();
    const termineRettifica = +parm.codDecodifica;
    this.dataTimbroPostale = new Date();
    let dataTimbroPostaleProspetto = prospetto.dataTimbroPostale;
    if(dataTimbroPostaleProspetto){
      this.dataTimbroPostale.setDate(prospetto.dataTimbroPostale.getDate() + termineRettifica);
    }

    const sysdate = new Date();
    if (!dataTimbroPostaleProspetto || sysdate > new Date(dataCalcolata)) {
      const result = await this.promptModalService.openPrompt(
        'RETTIFICA PROSPETTO',
        'Non si puo\' piu\' procedere con la rettifica del prospetto in quanto sono stati superati i termini previsti dalla normativa vigente.',
        'OK',
        null,
        null
      );
    } else {
      const result = await this.promptModalService.openPrompt(
        'RETTIFICA PROSPETTO',
        "Si vuole procedere con la rettifica del prospetto selezionato?\n\r"+
        "Si ricorda che la rettifica è possibile nei termini previsti dalla normativa vigente.",
        'Conferma', 'Annulla',
        null);
      this.util.showSpinner();
      let ritorno = null;
      try {
        if(result){
          let res = await this.prospettoService.rettificaProspetto(prospetto.id).toPromise();
          ritorno = res.prospetto;
          console.log('prospetto: ' + ritorno);
        }
      } catch (e) {
        console.log('errore rettifica prospetto: ' + e.error);
        if (e.error && e.error.length > 0) {
          const listErrorMsg = e.error;
          listErrorMsg.forEach(element => {
            this.listMsg.push(element.errorMessage);
          });
          this.settaErroriInPagina();
        }
      } finally {
        this.util.hideSpinner();
      }
      if (!Utils.isNullOrUndefined(ritorno)) {
        this.goToDettaglioProspetto(ritorno, true);
      }
      /** invece di riaggiornare la pagina si è deciso di mandarlo direttamente al dettaglio  */
      // this.chiamiamoloInAltroModo.emit(this.currentPaginationData);
    }

  }

  async goToAnnullaProspetto(prospetto: Prospetto) {
    /** Da gestire e richiamare il metodo di annulamento del prospetto */
    this.pulisciErroriInPagina();
    const res = await this.commonService.getParametro(PARAMETRO_TERMINE_ANNULLAMENTO).toPromise();
    const arr = res.codDecodifica.split('/', 3);
    const anno = +arr[2];
    const mese = +arr[1] - 1;
    const giorno = +arr[0];
    const termineAnnullamento = new Date(anno, mese, giorno);
    const sysdate = new Date();
    let flagException = false;
    if (sysdate > termineAnnullamento) {
      const result = await this.promptModalService.openPrompt(
        'ANNULLAMENTO PROSPETTO',
        'Non si puo\' procedere con l\'annullamento perche\' e\' stata superata la data termine per l\'annullamento!',
        'Conferma',
        null,
        null);
    } else {
      const result = await this.promptModalService.openPrompt(
        'ANNULLAMENTO PROSPETTO',
        'Si vuole procedere con l\'annullamento del prospetto selezionato ?',
        'Conferma', 'Annulla',
        null);

      if (result) {
        /** se si conferma bisognera' richiamare
         * il servizio per l'annullamento del prospetto
         */
        this.util.showSpinner();
        try {
          const ritorno = await this.prospettoService.annullaProspetto(prospetto.id).toPromise();
          const prospettoRes = ritorno.prospetto;
          if(ritorno){
            this.invioProspetto(prospettoRes);
          }
        } catch (e) {
          console.log('errore annullamento prospetto: ' + e.error);
          flagException = true;
          if (e.error && e.error.length > 0) {
            const listErrorMsg = e.error;
            listErrorMsg.forEach(element => {
              this.listMsg.push(element.errorMessage);
            });
            this.settaErroriInPagina();
          }
        } finally {
          this.util.hideSpinner();
        }
        if (!flagException) {
          this.chiamiamoloInAltroModo.emit(this.currentPaginationData);
        }
      }

    }

  }


  private async invioProspetto(prospettoToSave: Prospetto){
    const now = new Date();
    prospettoToSave.dataTimbroPostale = now;
    const confermaRiepilogoToSend: ConfermaRiepilogoProspetto = {
      prospetto: prospettoToSave,
      ruolo: this.ruolo
    }
    try{
      const resInvio: ConfermaRiepilogo = await this.riepilogoService.confermaRiepilogo(prospettoToSave.id,confermaRiepilogoToSend).toPromise();
      if(resInvio){
        this.prodisStorageService.setEsitoInvioProspetto(resInvio);
        this.util.hideSpinner();
        this.router.navigateByUrl('/esito-invio-prospetto');
      }
    }catch(e){
      if (e.error && e.error.length > 0) {
        const errorResponseList = e.error;
        let elencoErrori = "";
        errorResponseList.forEach(element => {
            elencoErrori += element.errorMessage + '<br>';
        });
        this.util.showToastrErrorMessage(elencoErrori);
      }
    }finally{
      this.util.hideSpinner();
    }
  }

  private settaErroriInPagina() {
    this.erroriInPagina.emit({
      typeMsg: TYPE_ALERT_DANGER,
      hide: true,
      listMsg: this.listMsg
    });
  }
  private pulisciErroriInPagina() {
    this.listMsg = [];
    this.erroriInPagina.emit({
      typeMsg: '',
      hide: false,
      listMsg: []
    });
  }

}
