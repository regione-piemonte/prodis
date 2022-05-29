/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
export interface CategoriaSoggettoInterface {
    codice: string;
    descrizione: string;
}

export class CategoriaSoggetto {
    private static readonly data: CategoriaSoggettoInterface[] = [
        {'codice': 'C', 'descrizione': 'Categorie protette'},
        {'codice': 'D', 'descrizione': 'Disabili'}
    ];

    static get(): CategoriaSoggettoInterface[] {
        return CategoriaSoggetto.data;
    }

    static getDescrizioneByCodice(codice: string) {
        const r = CategoriaSoggetto.data.find(v => v.codice === codice.toUpperCase());
        return r === undefined ? null : r.descrizione;
    }

    static getCodiceByDescrizione(descrizione: string) {
        const r = CategoriaSoggetto.data.find(v => v.descrizione === descrizione.toLowerCase());
        return r === undefined ? null : r.codice;
    }
}
