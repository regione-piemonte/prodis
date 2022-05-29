/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ERROR_DOWNLOAD_1000_LAVORATORI, STATO_PROSPETTO_BOZZA, TYPE_ALERT_DANGER } from 'src/app/constants';
import { DatiProvincialiService, DecodificaService, Prospetto, ProspettoProvincia, Provincia, VistaElencoProvQ2 } from 'src/app/modules/prodisapi';
import { PromptModalService } from 'src/app/modules/prodiscommon/services';
import { UtilitiesService } from 'src/app/services';
import { SidebarService } from 'src/app/services/sidebar.service';
import { ProdisStorageService } from 'src/app/services/storage/prodis-storage.service';
import { Utils } from 'src/app/utils';

@Component({
  selector: 'prodis-province-lavoratori-computabili',
  templateUrl: './province-lavoratori-computabili.component.html',
  styleUrls: ['./province-lavoratori-computabili.component.scss']
})
export class ProvinceLavoratoriComputabiliComponent implements OnInit {
  private static readonly SCROLL_TARGET = 'em[data-scroll-marker="dettaglioProvincia"]';
  private static readonly SCROLL_TARGET_DOWNLOAD = 'em[data-scroll-marker="notifiche"]';

  display = false;
  prospetto: Prospetto;
  idProspettoProvSelected: number;
  idProvinciaSelected: number;
  READ_MODE = true;
  provinceTutte: Provincia[];
  province: Provincia[] = [];
  elencoProvQ2: VistaElencoProvQ2[] = [];
  editMode: boolean;
  @Input() set inputEditMode(editMode: boolean) {
    this.editMode = editMode;
  }
  form: FormGroup;
  dsProvincia: string;
  typeMsg: string;
  hide = false;
  listMsg: string[] = [];

  get f() {return this.form.controls as any; }

  constructor(
    private sidebarService: SidebarService,
    private prodisStorageService: ProdisStorageService,
    private datiProvincialiService: DatiProvincialiService,
    private utilitiesService: UtilitiesService,
    private decodificaService: DecodificaService,
    private promptModalService: PromptModalService
  ) { }

  async ngOnInit() {
    this.utilitiesService.showSpinner();
    this.sidebarService.setShowSideBar(false);
    try {
      this.provinceTutte = await this.decodificaService.getProvincia().toPromise();
      this.prodisStorageService.prospetto$.subscribe(async item => {
        this.prospetto = item;
        this.READ_MODE = this.prospetto.statoProspetto.descrizione !== STATO_PROSPETTO_BOZZA;
        if (this.prospetto && this.prospetto.id) {
          this.elencoProvQ2 = await this.datiProvincialiService.getElencoProvinceQ2ByIdProspetto(this.prospetto.id).toPromise();
        }
        this.filtraProvince();
      });
      this.initForm();
    } catch (e) {
      console.log('ERROREEEEEEEEEEEEEEEEEEEEEE : ' + e);
    } finally {
      this.utilitiesService.hideSpinner();
    }

  }

  private filtraProvince() {
    if (this.elencoProvQ2.length > 0) {

      this.province = [];
      for (const element of this.provinceTutte) {
        if (!this.elencoProvQ2.find(item => item.dsTarga === element.dsTarga)) {
          this.province.push(element);
        }
      }
    } else {
      this.province = this.provinceTutte;
    }
  }



  private initForm() {
    this.form = new FormGroup({
      provincia: new FormControl()
    });
  }

  setDisplay() {
    console.log(this.display);
  }


  visualizzaTabDettaglioProvince(idProspettoProv: number, idProvincia: number, dsProvincia: string) {
    setTimeout(() => this.display = false, 200) //prima soluzione
    this.idProspettoProvSelected = idProspettoProv;
    this.idProvinciaSelected = idProvincia;
    this.display = true;
    this.dsProvincia = dsProvincia;
    setTimeout(() => this.display = true, 200) //prima soluzione
    this.utilitiesService.scrollTo(ProvinceLavoratoriComputabiliComponent.SCROLL_TARGET);


  }

  async onSubmit() {
    this.utilitiesService.showSpinner();
    this.pulisciListaErrori();
    const provincia: Provincia = this.f.provincia.value;
    const prospetto = this.prospetto;
    try {
      if (provincia && prospetto) {

        const prospettoProvToSave: ProspettoProvincia = {
          idProspetto: prospetto.id,
          provincia: provincia
        };
        if (!this.elencoProvQ2.find(item => item.dsTarga === provincia.dsTarga)) {
          const res = await this.datiProvincialiService.postDatiProvinciali(prospettoProvToSave).toPromise();
          if (res) {
            this.getElencoProvinceQ2();
            this.visualizzaTabDettaglioProvince(res.id, res.provincia.id, res.provincia.dsProTProvincia);
            this.form.reset();
          }
        }
      }
    } catch (e) {
      if (e.error && e.error.length > 0) {
        this.typeMsg = TYPE_ALERT_DANGER;
        this.hide = true;
        this.listMsg.push(e.error[0].errorMessage);
      }
    } finally {
      this.utilitiesService.hideSpinner();
      //
    }
  }


  async eliminaProspettoProv(idProspettoProv: number, dsProvincia: string) {
      this.pulisciListaErrori();
      const result = await this.promptModalService.openPrompt(
        'ELIMINAZIONE DETTAGLIO PROVINCIALE',
        'Confermare la cancellazione del dettaglio provinciale per la provincia di ' + dsProvincia + '?',
        'Conferma', 'Annulla',
        null);
      if (result) {
          this.utilitiesService.showSpinner();
          try {
            const response = await this.datiProvincialiService.deleteDatiProvinciali(idProspettoProv).toPromise();
            if (response) {
              this.display = false;
              this.elencoProvQ2 = await this.datiProvincialiService.getElencoProvinceQ2ByIdProspetto(this.prospetto.id).toPromise();
              this.filtraProvince();
            }

          } catch (e) {
            console.log('errore stato prospetto: ' + e.error);
            if (e.error && e.error.length > 0) {
              this.typeMsg = TYPE_ALERT_DANGER;
              this.hide = true;
              this.listMsg.push(e.error[0].errorMessage);
            }
          } finally {
            this.utilitiesService.hideSpinner();
          }
        }
  }

  async openModalUploadLav(){
    const resUpload = await this.promptModalService.openUploadLavPrompt(this.prospetto.id,this.idProspettoProvSelected,this.dsProvincia);
    if(resUpload){
      this.getElencoProvinceQ2();
      this.prodisStorageService.setEsitoUploadLavoratori(true);
    }
  }

  private pulisciListaErrori() {
    this.typeMsg = '';
    this.hide = false;
    this.listMsg = [];
  }


  async downLoadLavoratori(){
    this.utilitiesService.showSpinner();
    this.listMsg = [];
    let res;
    try{
      if(this.idProspettoProvSelected){
        res = await this.datiProvincialiService.downloadProvinciaLavoratoriInForza(this.idProspettoProvSelected, 'response').toPromise();
        
      }else{
        res = await this.datiProvincialiService.downloadProspettoLavoratoriInForza(this.prospetto.id, 'response').toPromise();
      }

      if(res){
        //const apiErrors = res.body.apiErrors;
        // if(apiErrors && apiErrors.length > 0){
          //   apiErrors.forEach(element => {
            //     this.listMsg.push(element.errorMessage);
            //   });
            //   this.typeMsg = TYPE_ALERT_DANGER;
            //   this.hide = true;
            //   this.utilitiesService.scrollTo(ProvinceLavoratoriComputabiliComponent.SCROLL_TARGET_DOWNLOAD);
            // }else{
          const fileName = Utils.extractFileNameFromContentDisposition(res.headers.get('Content-Disposition'));
          this.utilitiesService.downloadBlobFile(fileName, res.body);
        //}
      }

    }catch(e){
      if (e.error && e.error.length > 0) {
        e.error.forEach(element => {
          if(element.errorMessage){
            this.listMsg.push(element.errorMessage);
          }
        });
       const firstError = e.error[0];
       if(firstError.code === "SYS-SYS-E-0001" && this.prospetto.id && !this.idProspettoProvSelected){
        this.listMsg.push(ERROR_DOWNLOAD_1000_LAVORATORI.errorMessage);
       }
       if(this.listMsg.length > 0){
        this.typeMsg = TYPE_ALERT_DANGER;
        this.hide = true;
        this.utilitiesService.scrollTo(ProvinceLavoratoriComputabiliComponent.SCROLL_TARGET_DOWNLOAD);
       }

      }
    }finally{
      this.utilitiesService.hideSpinner();
    }
  }

  async getElencoProvinceQ2() {
    try {
      this.elencoProvQ2 = await this.datiProvincialiService.getElencoProvinceQ2ByIdProspetto(this.prospetto.id).toPromise();
      this.filtraProvince();
    } catch (e) {

    } finally {

    }
  }

}
