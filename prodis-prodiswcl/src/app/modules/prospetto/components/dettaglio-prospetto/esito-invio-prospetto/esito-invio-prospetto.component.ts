/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, HostListener, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiError, Prospetto, ProspettoService } from 'src/app/modules/prodisapi';
import { UtilitiesService } from 'src/app/services';
import { ProdisStorageService } from 'src/app/services/storage/prodis-storage.service';

@Component({
  selector: 'prodis-esito-invio-prospetto',
  templateUrl: './esito-invio-prospetto.component.html',
  styleUrls: ['./esito-invio-prospetto.component.scss']
})
export class EsitoInvioProspettoComponent implements OnInit {

  private static readonly SCROLL_TARGET = 'em[data-scroll-marker="top"]';
  prospetto: Prospetto;
  warnings: ApiError[];
  hide:boolean = false;
  listMsg: string[] = [];
  codiceComunicazione: string = 'Non definito'
  constructor(
    private utilitiesService: UtilitiesService,
    private prodisStorageService: ProdisStorageService,
    private prospettoService: ProspettoService,
    private router: Router 
  ) { }

  ngOnInit() {
    this.utilitiesService.showSpinner();
    this.prodisStorageService.esitoInvioProspetto$.subscribe(async item => {
      this.prospetto = item.prospetto;
      if(this.prospetto && this.prospetto.codiceComunicazione){
         this.codiceComunicazione = this.prospetto.codiceComunicazione;
      }
      this.warnings = item.warnings
      if(this.warnings && this.warnings.length > 0){
        this.setWarnings();
      }
      let idList: string[] = [];
      idList.push(this.prospetto.id+"");
      try{
        await this.prospettoService.salvaPdf(idList).toPromise();
      }catch(e){
      }finally{
        this.utilitiesService.scrollTo(EsitoInvioProspettoComponent.SCROLL_TARGET);
        this.utilitiesService.hideSpinner();
      }
    });

  }

  private setWarnings(){
    this.warnings.forEach(war => {
      this.listMsg.push(war.errorMessage +"<br>");
    });
    this.hide = true;
  }
  @HostListener('window:popstate', ['$event'])
  onBrowserBackBtnClose(event: Event) {
    console.log('back button pressed');
    event.preventDefault(); 
    this.router.navigate(['/home'],  {replaceUrl:true});
}

}
