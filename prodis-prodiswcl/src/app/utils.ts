/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { formatNumber } from '@angular/common';
import { ApiError, DatiProvinciali, Prospetto, ProspettoGradualita, ProvConvenzione } from 'src/app/modules/prodisapi';

// Transversal utilities
export class Utils {

  private static readonly ISO_DATE_FORMAT = /^\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}(?:\.\d*)?(?:\+\d{4})?Z?$/;

  static async wait(ms: number = 50): Promise<void> {
    return new Promise(resolve => setTimeout(() => resolve(), ms));
  }

  static generateRandomString(length: number = 40) {
    const arr = new Uint8Array(length / 2);
    window.crypto.getRandomValues(arr);
    return Array.from(arr, Utils.dec2hex).join('');
  }

  static dec2hex(dec) {
    return ('0' + dec.toString(16)).substr(-2);
  }

  static clone<T>(obj: T): T {
    const str = JSON.stringify(obj);
    return Utils.jsonParse(str) as T;
  }

  static jsonParse(str: string): any {
    const tmp = JSON.parse(str);
    Utils.convertHandlingDate(tmp);
    return tmp;
  }

  static convertHandlingDate(obj: any): any {
    if (obj === null || obj === undefined) {
      return obj;
    }
    if (typeof obj !== 'object') {
      return obj;
    }
    for (const key of Object.keys(obj)) {
      const value = obj[key];
      if (Utils.isIsoDateString(value)) {
        obj[key] = new Date(value);
      } else if (typeof value === 'object') {
        Utils.convertHandlingDate(value);
      }
    }
  }

  static isIsoDateString(value: any): boolean {
    if (value === null || value === undefined) {
      return false;
    }
    if (typeof value === 'string') {
      return Utils.ISO_DATE_FORMAT.test(value);
    }
    return false;
  }

  static extractFileNameFromContentDisposition(contentDisposition: string): string {
    return contentDisposition.substring(contentDisposition.indexOf('filename=') + 9, contentDisposition.length);
  }

  static strToNumber(value: number | string): number {
    // Convert strings to numbers
    if (typeof value === 'string' && !isNaN(Number(value) - parseFloat(value))) {
      return Number(value);
    }
    if (typeof value !== 'number') {
      throw new Error(`${value} is not a number`);
    }
    return value;
  }

  static areApiErrorLike(obj: any[]): obj is ApiError[] {
    return obj.every(el => Utils.isApiErrorLike(el));
  }

  static isApiErrorLike(obj: any): obj is ApiError {
    return obj.code && obj.params;
  }

  static compareById(a: any, b: any) {
    return a && a.id !== null && a.id !== undefined
      && b && b.id !== null && b.id !== undefined
      // tslint:disable-next-line: triple-equals
      && a.id == b.id;
  }
  static groupBy<T>(arr: T[], key: string): {[key: string]: T[]} {
    return arr.reduce((acc, el) => {
      (acc[el[key] || ''] = acc[el[key] || ''] || []).push(el);
      return acc;
    }, {});
  }

  static isNullOrUndefined<T>(
    obj: T | null | undefined
  ): obj is null | undefined {
    return typeof obj === 'undefined' || obj === null;
  }

  static isNullOrUndefinedOrCampoVuoto<T>(
    obj: T | null | undefined | ''
  ): obj is null | undefined | '' {
    return typeof obj === 'undefined' || obj === null || obj === '';
  }

  static timeConvert(num) {
    if (this.isNumber(num)) {
      const hours = Math.floor(num / 60);
      const minutes = num % 60;
      return `${formatNumber(hours, 'it-IT', '2.0')}:${formatNumber(minutes, 'it-IT', '2.0')}`;
    } else {
      return '00:00';
    }
  }

  static getDeepValue(object: any, path: string) {
    if (!path || !object) {
      return object;
    }
    const props = path.split('.');
    let tmp = object;
    let i: number;
    for (i = 0; i < props.length - 1; i++) {
      const prop = props[i];
      const candidate = tmp[prop];
      if (candidate !== undefined) {
        tmp = candidate;
      } else {
        break;
      }
    }
    return tmp[props[i]];
  }

  static setDeepValue<T>(object: T, path: string, value: any): T {
    if (path && object) {
        const props = path.split('.');
        let tmp = object;
        let i: number;
        for (i = 0; i < props.length - 1; i++) {
          const prop = props[i];
          let candidate = tmp[prop];
          if (candidate === undefined) {
            tmp[prop] = candidate = {};
          }
          tmp = candidate;
        }
        tmp[props[i]] = value;
    }
    return object;
  }

  static isString<T>(obj: T): boolean {
    return typeof obj === 'string';
  }

  static isNumber<T>(value: T): boolean {
    return typeof value === 'number';
  }
  static isBoolean<T>(value: T): boolean {
    return typeof value === 'boolean';
  }
  static getTimeFormat(val: number): string {
    if (val && this.isNumber(val) && val > 0) {
      const hours = Math.floor(val / 60);
      const minutes = val % 60;
      return `${formatNumber(hours, 'it-IT', '2.0')}:${formatNumber(minutes, 'it-IT', '2.0')}`;
    } else {
      return '00:00';
    }
  }

  static setCampiAltreConcessioni(datiProvinciali: DatiProvinciali): DatiProvinciali{
    /* START ProvConvenzione */
    let provConvenzione: ProvConvenzione = datiProvinciali.provConvenzione;
    if(provConvenzione){
      if(
        !this.isNullOrUndefinedOrCampoVuoto(provConvenzione.statoConcessione) ||
        !this.isNullOrUndefinedOrCampoVuoto(provConvenzione.dataAtto) ||
        !this.isNullOrUndefinedOrCampoVuoto(provConvenzione.estremiAtto) ||
        !this.isNullOrUndefinedOrCampoVuoto(provConvenzione.assunzioneProtetta) || 
        (!this.isNullOrUndefinedOrCampoVuoto(provConvenzione.numLavPrevConvQ2)) ||
        !this.isNullOrUndefinedOrCampoVuoto(provConvenzione.dataStipula) ||
        !this.isNullOrUndefinedOrCampoVuoto(provConvenzione.dataScadenza)
      ){
        datiProvinciali.provConvenzione.id = datiProvinciali.id;
      }else{
        datiProvinciali.provConvenzione = null;
      }
    }

      /* START ProvEsonero */
      let provEsonero = datiProvinciali.provEsonero;
      if(provEsonero){
        if(
          !this.isNullOrUndefinedOrCampoVuoto(provEsonero.statoConcessione) || 
          !this.isNullOrUndefinedOrCampoVuoto(provEsonero.dataAtto) || 
          !this.isNullOrUndefinedOrCampoVuoto(provEsonero.estremiAtto) || 
          !this.isNullOrUndefinedOrCampoVuoto(provEsonero.dataAttoFinoAl) || 
          (!this.isNullOrUndefinedOrCampoVuoto(provEsonero.percentuale)) || 
          (!this.isNullOrUndefinedOrCampoVuoto(provEsonero.nLavoratoriEsonero)) 
        ){
          datiProvinciali.provEsonero.id = datiProvinciali.id;
        }else{
          datiProvinciali.provEsonero = null;
        }
      }

      /* START ProvEsoneroAutocert */

      let provEsoneroAutocert = datiProvinciali.provEsoneroAutocert;
      if(provEsoneroAutocert){
        if(
          !this.isNullOrUndefinedOrCampoVuoto(provEsoneroAutocert.dataAutocert) ||
          (!this.isNullOrUndefinedOrCampoVuoto(provEsoneroAutocert.nLav60x1000)) ||
          (!this.isNullOrUndefinedOrCampoVuoto(provEsoneroAutocert.percentualeEsAutocert)) ||
          (!this.isNullOrUndefinedOrCampoVuoto(provEsoneroAutocert.nLavEsoneroAutocert))
        ){
          datiProvinciali.provEsoneroAutocert.id = datiProvinciali.id;
        }else{
          datiProvinciali.provEsoneroAutocert = null;
        }
      }

      /* START ProvSospensione */
      let provSospensione = datiProvinciali.provSospensione;
      if(provSospensione){
        if(
          !this.isNullOrUndefinedOrCampoVuoto(provSospensione.statoConcessione) ||
          !this.isNullOrUndefinedOrCampoVuoto(provSospensione.causaSospensione) ||
          !this.isNullOrUndefinedOrCampoVuoto(provSospensione.dataFineSospensioneQ2) ||
          (!this.isNullOrUndefinedOrCampoVuoto(provSospensione.nLavoratori))
        ){
          datiProvinciali.provSospensione.id = datiProvinciali.id;
        }else{  
          datiProvinciali.provSospensione = null;
        }
      }

      /* START ProvGradualita */
      let provGradualita = datiProvinciali.provGradualita;
      if(provGradualita){
        if(
          (!this.isNullOrUndefinedOrCampoVuoto(provGradualita.nAssunzioniEffDopoTrasf))
        ){
          datiProvinciali.provGradualita.id = datiProvinciali.id;
        }else{
          datiProvinciali.provGradualita = null;
        }
      }

      return datiProvinciali;
    }

    static setProspettoGradualita(gradualita: ProspettoGradualita){
      let grP = gradualita;
      if(grP){
        if(
          this.isNullOrUndefinedOrCampoVuoto(grP.dataAtto) &&
          this.isNullOrUndefinedOrCampoVuoto(grP.estremiAtto) &&
          this.isNullOrUndefinedOrCampoVuoto(grP.dataTrasformazione) &&
          this.isNullOrUndefinedOrCampoVuoto(grP.percentuale) &&
          this.isNullOrUndefinedOrCampoVuoto(grP.nAssunzioniLavPreTrasf)
        ){
          grP =  null;
        }
      }
      return grP;
    }

    static textTransformUpperCaseCfQ1(prospetto: Prospetto): Prospetto{
      // let cfComunicazione = prospetto.cfComunicazione
      // if(!this.isNullOrUndefinedOrCampoVuoto(cfComunicazione)){
      //   prospetto.cfComunicazione = cfComunicazione.toUpperCase();
      // }
      const datiAzienda = prospetto.datiAzienda;
      if(datiAzienda){
        let cfAzienda = datiAzienda.cfAzienda;
        if(!this.isNullOrUndefinedOrCampoVuoto(cfAzienda)){
          prospetto.datiAzienda.cfAzienda = cfAzienda.toUpperCase();
        }
        let cfReferente = datiAzienda.cfReferente
        if(!this.isNullOrUndefinedOrCampoVuoto(cfReferente)){
          prospetto.datiAzienda.cfReferente = cfReferente.toUpperCase();
        }
        let cfCapogruppo = datiAzienda.cfCapogruppo;
        if(!this.isNullOrUndefinedOrCampoVuoto(cfCapogruppo)){
          prospetto.datiAzienda.cfCapogruppo = cfCapogruppo.toUpperCase();
        }
      }
      return prospetto;
    }

  static getAbsoluteVale(value: number){
    if(this.isNumber(value)){
      return Math.abs(value);
    }
  }
}
