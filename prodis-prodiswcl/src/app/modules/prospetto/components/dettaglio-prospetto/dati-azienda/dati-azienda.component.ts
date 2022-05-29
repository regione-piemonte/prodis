/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, OnInit } from '@angular/core';
import { STATO_PROSPETTO_BOZZA, TYPE_ALERT_DANGER, TYPE_ALERT_SUCCESS } from 'src/app/constants';
import { AssPubbliche, DecodificaGenerica, DecodificaService, Prospetto, ProspettoService, StatiEsteri } from 'src/app/modules/prodisapi';
import { UtilitiesService } from 'src/app/services';
import { SidebarService } from 'src/app/services/sidebar.service';
import { ProdisStorageService } from 'src/app/services/storage/prodis-storage.service';
import { Utils } from 'src/app/utils';


@Component({
  selector: 'prodis-dati-azienda',
  templateUrl: './dati-azienda.component.html',
  styleUrls: ['./dati-azienda.component.scss']
})
export class DatiAziendaComponent implements OnInit {

  prospetto: Prospetto;
  idProspetto: number;
  newProspetto: Prospetto;
  datiProspetto: Prospetto;
  READ_MODE: boolean;
  elencoAssunzioni: AssPubbliche[];
  elencoStatiEsteri: StatiEsteri[];

  typeMsg: string;
  hide = false;
  listMsg: string[] = [];


  constructor(
    private sidebarService: SidebarService,
    private prodisStorageService: ProdisStorageService,
    private prospettoService: ProspettoService,
    private utilitiesService: UtilitiesService,
    private decodificaService: DecodificaService
  ) { }

  async ngOnInit() {
    this.sidebarService.setShowSideBar(false);

    this.prodisStorageService.prospetto$.subscribe(item => {
      this.prospetto = item;
      this.idProspetto = this.prospetto.id;
      this.READ_MODE = this.prospetto.statoProspetto.descrizione !== STATO_PROSPETTO_BOZZA;
      this.newProspetto = Utils.clone(this.prospetto);
    });
    if (this.prospetto.id) {
      this.elencoAssunzioni = await this.prospettoService.getAssunzioniPubblicheByIdProspetto(this.prospetto.id).toPromise();
    }
  }

  onNewDatiGenerali(datiGenProspetto: Prospetto) {
    this.newProspetto.id = datiGenProspetto.id;
    this.newProspetto.dAggiorn = datiGenProspetto.dAggiorn;
    this.newProspetto.datiAzienda = datiGenProspetto.datiAzienda;
    this.prodisStorageService.setProspettoToSave(this.newProspetto);
  }


  onNewDatiProspetto(datiProspetto: Prospetto) {
    this.newProspetto.dataRiferimentoProspetto = datiProspetto.dataRiferimentoProspetto;
    this.newProspetto.flgAssunzioniPubbSelezione = datiProspetto.flgAssunzioniPubbSelezione;
    this.newProspetto.prospettoGradualita = Utils.setProspettoGradualita(datiProspetto.prospettoGradualita);
    this.newProspetto.dFineSospensioneQ1 = datiProspetto.dFineSospensioneQ1
    this.prodisStorageService.setProspettoToSave(this.newProspetto);
  }

  onNewElencoAssunzioni(elenco: AssPubbliche[]) {
    this.newProspetto.assPubbliche = elenco;
    this.elencoAssunzioni = this.newProspetto.assPubbliche;
    this.prodisStorageService.setProspettoToSave(this.newProspetto);
  }

  get disableTabDatiProspetto() {
    if (!this.READ_MODE) {
      return false;
    }
  }

  async onRicaricaErrori(erroriParams: any) {
    this.typeMsg = erroriParams.typeMsg;
    this.hide = erroriParams.hide;
    this.listMsg = erroriParams.listMsg;
  }

  async onSalvaInBozzaDatiProspetto(datiProspetto: Prospetto) {
    this.pulisciListaErrori();
    // dati del secondo tab
    this.newProspetto.dataRiferimentoProspetto = datiProspetto.dataRiferimentoProspetto;
    this.newProspetto.flgAssunzioniPubbSelezione = datiProspetto.flgAssunzioniPubbSelezione;
    this.newProspetto.prospettoGradualita = datiProspetto.prospettoGradualita;
    this.newProspetto.dFineSospensioneQ1 = datiProspetto.dFineSospensioneQ1
    // dati del secondo tab
    await this.chiamoServizioPerSalvataggioInBozza();
  }


  async onSalvaInBozzaAssunzioniPubbliche(assunzioniPubbliche: AssPubbliche[]) {
    this.pulisciListaErrori();
    // dati del terzo tab
    this.newProspetto.assPubbliche = assunzioniPubbliche;
    // dati del terzo tab
    await this.chiamoServizioPerSalvataggioInBozza();
  }

  async onSalvaInBozzaDatiGenerali(datiGenProspetto: Prospetto) {
    this.pulisciListaErrori();
    // dati del primo tab
    this.newProspetto.id = datiGenProspetto.id;
    this.newProspetto.dAggiorn = datiGenProspetto.dAggiorn;
    this.newProspetto.datiAzienda = datiGenProspetto.datiAzienda;
    // dati del primo tab
    await this.chiamoServizioPerSalvataggioInBozza();
  }

  private async chiamoServizioPerSalvataggioInBozza() {
    this.utilitiesService.showSpinner();
    try {
      let esito = null;

      if (this.newProspetto.assPubbliche && this.newProspetto.assPubbliche.length > 0) {
          this.newProspetto.flgAssunzioniPubbSelezione = 'S';
      } else {
        this.newProspetto.flgAssunzioniPubbSelezione = 'N';
      }
      this.newProspetto = Utils.textTransformUpperCaseCfQ1(this.newProspetto);
      if (this.newProspetto.id) {
        /* SONO IN MODIFICA DELLA BOZZA */
        esito = await this.prospettoService.putProspettoUpdate(this.newProspetto.id, true, this.newProspetto).toPromise();
        // if (esito) {
        //   this.prodisStorageService.setProspetto(esito);
        //   this.prodisStorageService.setProspettoToSave(esito);
        // }
      } else {
        /* SONO IN INSERIMENTO DELLA BOZZA DI UN PROSPETTO NUOVO */
         
        esito = await this.prospettoService.postProspetto(true, this.newProspetto).toPromise();
      }
      if (esito) {
        const prospettoRes:Prospetto = esito.prospetto;
        this.elencoAssunzioni = prospettoRes.assPubbliche;
        await this.setStatiEsteri(prospettoRes);
        this.prodisStorageService.setProspetto(prospettoRes);
        this.prodisStorageService.setProspettoToSave(prospettoRes);
        //this.newProspetto = esito;
      }

      this.typeMsg = TYPE_ALERT_SUCCESS;
      this.hide = true;
      this.listMsg.push('Salvataggio del prospetto in bozza andato a buon fine');
    } catch (e) {
      if (e.error && e.error.length > 0) {
        this.typeMsg = TYPE_ALERT_DANGER;
        this.hide = true;
        this.listMsg.push(e.error[0].errorMessage);
      }
      console.log(e.error);
    }
    this.utilitiesService.hideSpinner();
    this.prodisStorageService.setProspettoToSave(this.newProspetto);
  }

  private pulisciListaErrori() {
    this.typeMsg = '';
    this.hide = false;
    this.listMsg = [];
  }

  private async setStatiEsteri(prospetto: Prospetto){
    /* settare i dati esteri */
    const elencoDecodStatiEsteri: DecodificaGenerica[] = await this.decodificaService.postStatiEsteriDecodifica({flgStatiUe: 'S'} as DecodificaGenerica
    ).toPromise();
    this.elencoStatiEsteri = [];
    elencoDecodStatiEsteri.forEach(item => {
      this.elencoStatiEsteri.push({
        id: item.idDecodifica,
        codNazioneMin: item.codDecodifica,
        dsStatiEsteri: item.dsDecodifica,
        siglaNazione : item.siglaNazione
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
        prospetto.datiAzienda.cfCapogruppo = cf.substring(2,cf.length);
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
      prospetto.datiAzienda.statiEsteri =  statoEsteroCorrente;
    }
 }

}
