/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
export interface CategoriaCompensazioneInterface {
    codice: string;
    descrizione: string;
}

export class CategoriaCompensazione {
    private static readonly data: CategoriaCompensazioneInterface[] = [
        {'codice': 'E', 'descrizione': 'Eccedenza'},
        {'codice': 'R', 'descrizione': 'Riduzione'}
    ];

    static get(): CategoriaCompensazioneInterface[] {
        return CategoriaCompensazione.data;
    }

    static getDescrizioneByCodice(codice: string) {
        const r = CategoriaCompensazione.data.find(v => v.codice === codice.toUpperCase());
        return r === undefined ? null : r.descrizione;
    }

    static getCodiceByDescrizione(descrizione: string) {
        const r = CategoriaCompensazione.data.find(v => v.descrizione === descrizione.toLowerCase());
        return r === undefined ? null : r.codice;
    }
}
