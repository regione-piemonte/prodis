# Prodotto - Componente
PRODIS - PRODISDB

# Descrizione della componente
PRODISDB è la componente DB del prodotto PRODIS.\
Il DBMS di riferimento è Oracle.\
Tramite gli script qui presenti viene creato e popolato lo schema dati usato dalle altre componenti.\
Questa componente include:
- script DDL per la creazione dello schema dati;
- script DML per il popolamento iniziale del DB;
- script per la definizione dei vincoli, delle viste e delle sequence;
- il codice PL/SQL.

# Configurazioni iniziali
Definire utente "prodis" su una istanza DBMS Oracle (versione 19) proprietario dello schema, ed un utente "prodis_rw" per accedere ai dati da applicativo (questo utente non ha la possibilità di modificare lo schema dati).

# Getting Started
Una volta prelevata e portata in locale dal repository la componente ("git clone"), predisporsi per poter eseguire gli script nella sequenza indicata nel seguito.

# Prerequisiti di sistema
DBMS Oracle versione 19, utente con permessi adeguati ad eseguire istruzioni di creazione tabelle.

# Installazione
Lanciare tutti gli script nella sequenza indicata dal prefisso del nome del file, lanciando quindi per ultimo lo script 07-ricompila_invalidi.sql :

    01-sequences.sql
    02-tables.sql
    03-dati.sql
    04-views.sql
    05-foreign_keys.sql
    06-plsql.sql
    07-ricompila_invalidi.sql


# Versioning
Per il versionamento del software si usa la tecnica Semantic Versioning (http://semver.org).

# Authors
Fare riferimento a quanto riportato nel file AUTHORS.txt.

# Copyrights

© Copyright Regione Piemonte – 2022 

© Copyright CSI-Piemonte – 2022 

Vedere anche il file Copyrights.txt .

# License
Il prodotto software è sottoposto alla licenza EUPL-1.2 o versioni successive.\
SPDX-License-Identifier: EUPL-1.2-or-later.\
Vedere il file EUPL v1_2 IT-LICENSE.txt per i dettagli.

