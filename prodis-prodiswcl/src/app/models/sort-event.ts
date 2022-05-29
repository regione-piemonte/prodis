/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { SortDirection } from 'src/app/models/sort-direction';

export interface SortEvent {
  column: string;
  direction: SortDirection;
}
