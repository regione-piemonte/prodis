/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
// import { PromptModalComponent } from '../components';

import { Injectable } from '@angular/core';
import { ListaDecodificaModalComponent, PromptModalComponent } from '../components';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DecodificaGenerica, SedeLegale, VistaElencoProvQ2 } from '../../prodisapi';
import { ListaSediModalComponent } from '../components/lista-sedi-modal/lista-sedi-modal.component';
import { UploadLavoratoriModalComponent } from '../components/upload-lavoratori-modal/upload-lavoratori-modal.component';

@Injectable()
export class PromptModalService {

    constructor(private modalService: NgbModal) {
    }

    openPrompt(pTitle: string, pMessage: string, pYes: string, pNo: string, type: string) {
        const modalRef = this.modalService.open(PromptModalComponent, {size: 'xl', scrollable: true});

        modalRef.componentInstance.title = pTitle;
        modalRef.componentInstance.message = pMessage;
        modalRef.componentInstance.yesLabel = pYes;
        modalRef.componentInstance.noLabel = pNo;
        modalRef.componentInstance.callback = this.callback;
        modalRef.componentInstance.modal = modalRef;
        modalRef.componentInstance.type = type;
        return modalRef.result;
    }

    openDecodificaPrompt(pTitle: string, list: DecodificaGenerica[], typeSearch: string, filtroDiPartenza: DecodificaGenerica) {
        const modalRef = this.modalService.open(ListaDecodificaModalComponent, {size: 'xl', scrollable: true});

        modalRef.componentInstance.title = pTitle;

        modalRef.componentInstance.list = list;
        modalRef.componentInstance.filtroDiPartenza = filtroDiPartenza;
        modalRef.componentInstance.typeSearch = typeSearch;

        modalRef.componentInstance.callback = this.callbackDecodifica;

        modalRef.componentInstance.modal = modalRef;

        return modalRef.result;
    }

    openListaSediPrompt(pTitle: string, list: SedeLegale[], cfAzienda: string) {
        const modalRef = this.modalService.open(ListaSediModalComponent, {size: 'xl', scrollable: true});

        modalRef.componentInstance.title = pTitle;

        modalRef.componentInstance.list = list;
        modalRef.componentInstance.cfAzienda = cfAzienda;

        modalRef.componentInstance.callback = this.callbackDecodifica;

        modalRef.componentInstance.modal = modalRef;

        return modalRef.result;
    }

    openUploadLavPrompt(idProspetto: number,idProspettoProv: number, dsProvincia: string) {
        const modalRef = this.modalService.open(UploadLavoratoriModalComponent, {size: 'xl', scrollable: true});

        modalRef.componentInstance.idProspetto = idProspetto;
        modalRef.componentInstance.idProspettoProv = idProspettoProv;
        modalRef.componentInstance.dsProvincia = dsProvincia;
        modalRef.componentInstance.callback = this.callback;;
        modalRef.componentInstance.modal = modalRef;

        return modalRef.result;
    }

    callback(modal: NgbModalRef, val: boolean) {
        modal.close(val);
    }

    callbackDecodifica(modal: NgbModalRef, val?: DecodificaGenerica) {
        modal.close(val);
    }

}
