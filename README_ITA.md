# Prodotto

PRODIS : Prospetto Disabili - Servizi Lavoro Piemonte

# Descrizione del prodotto
Il prodotto PRODIS è l’applicativo della Regione Piemonte che permette alle aziende e ai loro intermediari di ottemperare agli obblighi inerenti alla comunicazione del prospetto informativo disabili alla Pubblica Amministrazione, indicando la propria situazione occupazionale rispetto agli obblighi di assunzione di personale disabile e/o appartenente alle altre categorie protette come previsto dalla Legge 12 marzo 1999 n. 68.

In coerenza con la vigente normativa l’obbligo di adempimento riguarda i datori di lavoro con più di 15 dipendenti.

L’applicativo permette alle aziende o ai propri delegati l’accesso ai prospetti inviati negli anni precedenti, la possibilità di duplicare un prospetto per rendere più agevole la fase di compilazione ed invio del nuovo nonché la possibilità di caricare da file esterni l’elenco dei lavoratori.

Il prodotto "Prodis" è stato sviluppato negli anni nel completo rispetto dei Modelli e Regole definiti dal Ministero e in coerenza con alcuni principi cardine:
- sono soggette all’obbligo di invio del Prospetto Disabili tutte le aziende che hanno subito cambiamenti nella situazione occupazionale tali da modificare l'obbligo o da incidere sul computo della quota di riserva; quelle che non hanno subito variazioni significative ai fini dell’obbligo non sono tenute ad inviare il prospetto informativo.
- le aziende che hanno anche solo una sede fuori dalla Regione Piemonte, devono trasmettere il Prospetto Disabili presso il sito regionale competente la sede legale.
- il Prospetto Disabili è unico e contiene sia i dati provinciali sia quelli nazionali.
- il prospetto deve essere comunicato entro il 31 gennaio di ogni anno (salvo proroghe della data di scadenza, comunicata dal Ministero), con riferimento alla situazione occupazionale al 31 dicembre dell'anno precedente.
- è facoltà dell’azienda inviare il prospetto disabili durante l’anno ad ogni variazione significativa del personale.


Il prodotto è strutturato nelle seguenti componenti specifiche:
- [prodisdb]( https://github.com/regione-piemonte/prodis/prodis-prodisdb ) : script DDL/DML per la creazione ed il popolamento iniziale del DB (istanza DBMS Oracle), procedure PL/SQL di elaborazione dati;
- [prodiswcl]( https://github.com/regione-piemonte/prodis/prodis-prodiswcl ) : Client Web (Angular8), front-end applicativo;
- [prodisweb]( https://github.com/regione-piemonte/prodis/prodis-prodisweb ) : Componente SPA con servizi REST per prodiswcl;					;
- [prodissrv]( https://github.com/regione-piemonte/prodis/prodis-prodissrv ) : Componente di esposizione servizi (REST API) verso altri applicativi del Sistema Informativo Regionale.				;

A ciascuna componente del prodotto elencata sopra corisponde una sotto-directory denominata prodis-<nome_componente>.\
In ciascuna di queste cartelle di componente si trovano ulteriori informazioni specifiche, incluso il BOM della componente di prodotto.

Nella directory [csi-lib]( https://github.com/regione-piemonte/prodis/prodis-csi-lib ) si trovano le librerie sviluppate da CSI-Piemonte con licenza OSS, come indicato nei BOM delle singole componenti, ed usate trasversalmente nel prodotto.
	

# Prerequisiti di sistema

Una istanza DBMS Oracle (v. 12 o sup. - consigliata la v.19, eventualmente la "Enterprise Edition" per carichi di lavoro elevati) con utenza avente privilegi per la creazione tabelle ed altri oggetti DB (tramite le istruzioni DDL messe a disposizione nella componente prodisdb), ed una ulteriore utenza separata non proprietaria dello schama, per l'esecuzione di istruzioni DML di Create, Readd, Update e Delete sui dati.

Una istanza di application server J2EE, consigliato WildFly 17 ( https://www.wildfly.org/downloads/ ).\
Una istanza di web server, consigliato apache web server ( https://httpd.apache.org/ ).\
Per il build è previsto l'uso di Apache Maven ( https://maven.apache.org/ ).\
Per la compilazione/build delle componenti prodisweb e prodisrv sono rese disponibili nella directory "csi-lib" una serie di librerie predisposte da CSI Piemonte per un uso trasversale nei prodotti realizzati, o per uso specifico in altri prodotti con cui PRODIS si interfaccia. Indicazioni più specifiche sono disponibili nella documentazione di ciascuna componente.

Il prodotto PRODIS è integrato nei servizi del sistema informativo di Regione Piemonte "Lavoro": alcune sue funzionalità sono quindi strettamente legate alla possibilità di accedere a servizi esposti da altre componenti dell'ecosistema "Lavoro" di Regione Piemonte.

Infine, anche per quanto concerne l'autenticazione e la profilazione degli utenti del sistema, PRODIS è integrato con servizi trasversali del sistema informativo regionale ("Shibboleth", "IRIDE"), di conseguenza per un utilizzo in un altro contesto occorre avere a disposizione servizi analoghi o integrare moduli opportuni che svolgano analoghe funzionalità.
 

# Installazione

Creare lo schema del DB e popolarlo, tramite gli script della componente prodisdb. Installare anche i package e le procedure PL/SQL presenti nella stessa directory.
 
Configurare il datasource nell'application server, utilizzato in prodisweb e prodissrv.

Nel caso si vogliano sfruttare le funzionalità di invio mail, occorre anche configurare un mail-server.


# Deployment

Dopo aver seguito le indicazioni del paragrafo relativo all'installazione, si può procedere al build dei pacchetti ed al deploy su application server (WildFly).


# Versioning
Per la gestione del codice sorgente viene utilizzato Git, ma non vi sono vincoli per l'utilizzo di altri strumenti analoghi.\
Per il versionamento del software si usa la tecnica Semantic Versioning (http://semver.org).


# Copyrights
© Copyright Regione Piemonte – 2022\
© Copyright CSI-Piemonte – 2022


# License

SPDX-License-Identifier: EUPL-1.2-or-later .\
Questo software è distribuito con licenza EUPL-1.2 .\
Consultare il file LICENSE.txt per i dettagli sulla licenza.


