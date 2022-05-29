/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
export interface CategoriaAssunzioneInterface {
    codice: string;
    descrizione: string;
}

export class CategoriaAssunzione {
    private static readonly data: CategoriaAssunzioneInterface[] = [
        {'codice': 'NU', 'descrizione': 'Numerica'},
        {'codice': 'NO', 'descrizione': 'Nominativa'}
    ];

    static get(): CategoriaAssunzioneInterface[] {
        return CategoriaAssunzione.data;
    }

    static getDescrizioneByCodice(codice: string) {
      if (codice) {
        const r = CategoriaAssunzione.data.find(v => v.codice === codice.toUpperCase());
        return r === undefined ? null : r.descrizione;
      } else {
        return null;
      }
    }

    static getCodiceByDescrizione(descrizione: string) {
        const r = CategoriaAssunzione.data.find(v => v.descrizione === descrizione.toLowerCase());
        return r === undefined ? null : r.codice;
    }
}
