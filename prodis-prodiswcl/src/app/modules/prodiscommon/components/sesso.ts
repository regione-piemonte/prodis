/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
export interface SessoInterface {
    codice: string;
    descrizione: string;
}

export class Sesso {
    private static readonly data: SessoInterface[] = [
        {'codice': 'M', 'descrizione': 'maschio'},
        {'codice': 'F', 'descrizione': 'femmina'}
    ];

    static get(): SessoInterface[] {
        return Sesso.data;
    }

    static getDescrizioneByCodice(codice: string) {
        const r = Sesso.data.find(v => v.codice === codice.toUpperCase());
        return r === undefined ? null : r.descrizione;
    }

    static getCodiceByDescrizione(descrizione: string) {
        const r = Sesso.data.find(v => v.descrizione === descrizione.toLowerCase());
        return r === undefined ? null : r.codice;
    }
}
