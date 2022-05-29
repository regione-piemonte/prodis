/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { TYPE_ALERT_DANGER, TYPE_ALERT_WARNING } from 'src/app/constants';
import { DatiProvincialiService, VistaElencoProvQ2 } from 'src/app/modules/prodisapi';
import { UtilitiesService } from 'src/app/services';
import { ProdisStorageService } from 'src/app/services/storage/prodis-storage.service';
import { Utils } from 'src/app/utils';

@Component({
  selector: 'prodis-upload-lavoratori-modal',
  templateUrl: './upload-lavoratori-modal.component.html',
  styleUrls: ['./upload-lavoratori-modal.component.scss']
})
export class UploadLavoratoriModalComponent implements OnInit {

  @Input() callback;
  @Input() modal;
  @Input() idProspetto: number; /*Da utilizzare se idProspettoProv non è valorizzato, 
                                  si effettua la chiamata che vale per tutte le provice del prospetto con idProspetto*/
  @Input() idProspettoProv: number; //se valorizzato prevale su idProspetto quindi si effettua la chiamata per lo specifico idProspettoProv
  @Input() dsProvincia: string; //valorizzato se e solo se anche idProspettoProv è valorizzato
  @ViewChild('validatedXlsFile', { static: false }) xlsFile: ElementRef;


  formUploadLav: FormGroup;

  public fileLav: File;

  typeMsg: string;
  hide = false;
  listMsg: string[] = [];
  title: string = "Upload lavoratori ";

  get f() {return this.formUploadLav.controls as any};
  constructor(
    private utilitiesService: UtilitiesService,
    private datiProvincialiService: DatiProvincialiService,
  ) { }

  ngOnInit() {
   if(this.idProspettoProv){
     this.title = this.title + this.dsProvincia;
   }
   this.initAlertMasg();
  }

  emptyFile() {
    this.initAlertMasg();
    this.fileLav = undefined;
    this.xlsFile.nativeElement = undefined;
  }

  chooseFile(event) {
    this.fileLav = event.target.files[0];
  }

  validateFile(nomeFile: string) {
    if (nomeFile === 'fileXls' && this.fileLav) {
      if (this.fileLav.name.toLowerCase().endsWith('.xls')) {
        return false;
      } else {
        return true;
      }
    }
  }

  async onClickConferma(modal){
    this.utilitiesService.showSpinner();
    this.initAlertMasg();
    let res
    try{
      if(this.idProspettoProv){
        //chiamata per una specifica provincia
        res = await this.datiProvincialiService.uploadProvinciaLavoratoriInForza(""+this.idProspettoProv,this.fileLav, 'response').toPromise();
        const fileName = Utils.extractFileNameFromContentDisposition(res.headers.get('Content-Disposition'));
        this.utilitiesService.downloadBlobFile(fileName, res.body);
        this.callback(modal,true);
      }else if(this.idProspetto){
        res = await this.datiProvincialiService.uploadProspettoLavoratoriInForza(""+this.idProspetto,this.fileLav, 'response').toPromise();
        const fileName = Utils.extractFileNameFromContentDisposition(res.headers.get('Content-Disposition'));
        this.utilitiesService.downloadBlobFile(fileName, res.body);
        this.callback(modal,true);
      }
    }catch(e){
      if (e.error && e.error.length > 0) {
        const errorList = e.error;
        errorList.forEach(element => {
          this.listMsg.push(element.errorMessage);
        });
      }
      this.setAlert(TYPE_ALERT_DANGER);
    }finally{
      this.utilitiesService.hideSpinner();
    }
  }

  private setAlert(type: string){
    this.typeMsg = TYPE_ALERT_DANGER;
    this.hide = true;
  }

  private initAlertMasg(){
    this.typeMsg = "";
    this.hide = false;
    this.listMsg = [];
  }

}
