/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { NEW_PROSPETTO, STATO_PROSPETTO_BOZZA } from 'src/app/constants';
import { Prospetto, Ruolo, StatoProspetto } from 'src/app/modules/prodisapi';
import { SidebarService, UserService, UtilitiesService } from 'src/app/services';
import { ProdisStorageService } from 'src/app/services/storage/prodis-storage.service';
import { POSSIBLE_SIDEBAR_MODULES, ProdisSidebarModule } from '../../models';

@Component({
  selector: 'prodis-sidebar-left',
  templateUrl: './sidebar-left.component.html',
  styleUrls: ['./sidebar-left.component.scss']
})
export class SidebarLeftComponent implements OnInit, OnDestroy {

  close: boolean = true;
  @Input() set state(state: boolean){
    this.close = state;
    this.sidebarService.setCloseSideBar(this.close);
  }
  currentUrl: string;
  possibleModules = POSSIBLE_SIDEBAR_MODULES.filter(m => !m.ignore);
  private readonly subscriptions: Subscription[] = [];

  ruoloCorrente: Ruolo;

  constructor(
    private utilitiesService: UtilitiesService,
    private userService: UserService,
    private sidebarService: SidebarService,
    private router: Router,
    private prodisStorageService: ProdisStorageService

  ) { }

  ngOnInit() {
    this.subscriptions.push(
      this.userService.currentUrl$.subscribe(currentUrl => this.currentUrl = currentUrl)
    );
    this.sidebarService.setCloseSideBar(this.close);
    this.prodisStorageService.ruolo$.subscribe(el => this.ruoloCorrente = el);
  }

  ngOnDestroy() {
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }

  inSubpath(sm: ProdisSidebarModule): boolean {
    return this.currentUrl && (sm.urlSubpaths.some(url => this.currentUrl.indexOf(url) === 0) || (sm.isHome && this.currentUrl === '/'));
  }

  setClass(){
    this.close = !this.close;
    this.sidebarService.setCloseSideBar(this.close);
  }

  async loadPermessi(sm: ProdisSidebarModule) {
    // this.utilitiesService.showSpinner();
    const code = sm && sm.code || '';
    // Impostazione link per manuale utente
    // this.userService.setUserManualLink(UserLinkMap[modulo && modulo.codice || ''] || UserLinkMap.DEFAULT);
    try {
      // const permessi = await this.utenteService.getPermessiBySettoreAndModulo(this.settore.id, modulo.id).toPromise();
      // this.userService.setPermessi(permessi);
    } catch (e) {
      this.utilitiesService.handleApiErrors(e, '');
    }

  }

  onClickInserisci() {
    let prospetto = NEW_PROSPETTO;
    prospetto.cfStudioProfessionale = this.ruoloCorrente.codiceFiscale;
    if(this.ruoloCorrente.idSoggetti){
      prospetto.soggetti = {id: this.ruoloCorrente.idSoggetti}
    }
    this.prodisStorageService.setProspetto(prospetto);
    this.prodisStorageService.setProspettoToSave(prospetto);
    this.prodisStorageService.setBottoneSalvaProseguiDisabilitato(true);
    //this.router.navigateByUrl('/dettaglio-prospetto');
    this.redirectTo('/dettaglio-prospetto');
  }

  private redirectTo(uri:string){
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(()=>
    this.router.navigate([uri]));
 }
}
