/*-
 * ========================LICENSE_START=================================
 * PRODIS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodisweb.ejb.business.be.service.impl.lavoratoriinforza.excel;

import it.csi.prodis.prodisweb.ejb.business.be.dad.LavoratoriInForzaDad;
import it.csi.prodis.prodisweb.ejb.entity.ProDLavoratoriInForza;
import it.csi.prodis.prodisweb.ejb.entity.ProDParametri;
import it.csi.prodis.prodisweb.ejb.entity.ProRProspettoProvincia;
import it.csi.prodis.prodisweb.ejb.entity.ProTAssunzioneProtetta;
import it.csi.prodis.prodisweb.ejb.entity.ProTComune;
import it.csi.prodis.prodisweb.ejb.entity.ProTContratti;
import it.csi.prodis.prodisweb.ejb.entity.ProTIstat2001livello5;
import it.csi.prodis.prodisweb.ejb.entity.ProTStatiEsteri;
import it.csi.prodis.prodisweb.ejb.exception.NotFoundException;
import it.csi.prodis.prodisweb.ejb.mapper.ProdisMappers;
import it.csi.prodis.prodisweb.ejb.util.ValidatorLavoratore;
import it.csi.prodis.prodisweb.lib.dto.ApiError;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.AssunzioneProtetta;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Comune;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Contratti;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Istat2001livello5;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.StatiEsteri;
import it.csi.prodis.prodisweb.lib.dto.error.MsgProdis;
import it.csi.prodis.prodisweb.lib.dto.prospetto.LavoratoriInForza;
import it.csi.prodis.prodisweb.lib.dto.prospetto.Prospetto;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProspettoProvincia;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import jxl.Cell;
import jxl.CellView;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import jxl.write.DateFormat;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class MassivoLavoratoriManager {

	private DownloadWorkSheet down;
	
	private UploadWorkSheet up;
	
	private Helper helper;

	private LavoratoriInForzaDad lavoratoriInForzaDad;

	private LinkedHashMap<String, CampoCella> mappaCampi;

	// COSTRUTTORE
	
	public MassivoLavoratoriManager() {}
	
	public MassivoLavoratoriManager(LavoratoriInForzaDad lavoratoriInForzaDad) {
		
		this.lavoratoriInForzaDad = lavoratoriInForzaDad;
		
		down = new DownloadWorkSheet();
		
		up = new UploadWorkSheet();
		
		helper = new Helper();
		
		mappaCampi = CampoCellaHelper.getMappaCampiCella(this.lavoratoriInForzaDad);
		
	}

	// CONTROLLO DEL FILE
	
	public MassivoLavoratoriEsito checkFile(String contentType, String fileName) {
		MassivoLavoratoriEsito esito = new MassivoLavoratoriEsito();
		esito.setEsitoPositivo(Boolean.TRUE);

		try {

			if (contentType == null) {

				esito.setEsitoPositivo(false);
				esito.addErrore(MsgProdis.PROUPDE0001.getError());

			} else if (fileName != null && !(fileName.toLowerCase().endsWith(".csv")
					|| fileName.toLowerCase().endsWith(".xls") || fileName.toLowerCase().endsWith(".xlsx"))) {

				esito.setEsitoPositivo(false);
				esito.addErrore(MsgProdis.PROUPDE0002.getError());

			}

		} catch (Exception ex) {

			esito.setEsitoPositivo(false);
			esito.addErrore(MsgProdis.PROUPDE0002.getError());

		} 

		return esito;

	}
	
	// DOWNLOAD DI TUTTI I LAVORATORI DI UNA PROVINCIA
	
	public MassivoLavoratoriEsito downloadProvinciaLavoratori(Long idProspettoProvincialeSelezionato) {

		String siglaProvincia = "ALL";
			
		List<LavoratoriInForzaMassivo> lavoratori = new ArrayList<LavoratoriInForzaMassivo>();
			
		ProspettoProvincia prospettoProvincia = 
				lavoratoriInForzaDad.getProspettoProvinciaById(idProspettoProvincialeSelezionato);
			
				
		if (idProspettoProvincialeSelezionato == null || prospettoProvincia==null) {
			throw new NotFoundException("ProspettoProvincia");
		}

		siglaProvincia = prospettoProvincia.getProvincia().getDsTarga();
					
		List<LavoratoriInForza> lavoratoriSintesi = 
			ProdisMappers.LAVORATORI_IN_FORZA.toModels(
				lavoratoriInForzaDad.getListaLavoratoriByIdProspettoProv(idProspettoProvincialeSelezionato));
					
		if (lavoratoriSintesi!=null) {
						
			for (LavoratoriInForza lavoratore : lavoratoriSintesi) {
				
				LavoratoriInForzaMassivo lavoratoreMassivo = new LavoratoriInForzaMassivo(lavoratore);
				
				lavoratoreMassivo.setSiglaProvincia(prospettoProvincia.getProvincia().getDsTarga());
				
				lavoratori.add(lavoratoreMassivo);
				
			}
						
		}

		MassivoLavoratoriEsito esito = down.execute(
				lavoratori, 
				lavoratoriInForzaDad.getProspettoByProvincia(
						idProspettoProvincialeSelezionato).getDatiAzienda().getCfAzienda(),
				siglaProvincia
		);

		return esito;

	}

	// DOWNLOAD DI TUTTI I LAVORATORI DI UN PROSPETTO
	
	public MassivoLavoratoriEsito downloadProspettoLavoratori(Long idProspetto) {

		MassivoLavoratoriEsito esito = new MassivoLavoratoriEsito();
		
		if (lavoratoriInForzaDad.contaLavoratoriProspetto(idProspetto)>
			Long.parseLong(lavoratoriInForzaDad.getParametroByNome(Parametro.PARAMETRO_MASSIVOLAVORATORI_NUMERO_LAVORATORI_PROSPETTO))) {
		
			esito.setEsitoPositivo(false);
		
			esito.setFileLavoratori(null);
			
			esito.setLavoratoriBuoni(null);
			esito.setLavoratoriCattivi(null);
		
			esito.addErrore(MsgProdis.PROUPDE0008.getError());
		
			return esito;
		
		}

		Prospetto prospetto = lavoratoriInForzaDad.getProspetto(idProspetto);
			
		if (prospetto==null) {
			throw new NotFoundException("Prospetto");
		}
			
		List<LavoratoriInForzaMassivo> lavoratori = new ArrayList<LavoratoriInForzaMassivo>();
			
		List<ProspettoProvincia> prospettoProvinciaList = 
			ProdisMappers.PROSPETTO_PROVINCIA.toModels(
				lavoratoriInForzaDad.getProspettoProvinciaByIdProspetto(prospetto.getId()));
			

		for (ProspettoProvincia pp : prospettoProvinciaList) {
				
			if (pp != null) {
					
				List<LavoratoriInForza> lavoratoriSintesi = null;
					
				List<ProDLavoratoriInForza> lavoratoriEnt = 
						lavoratoriInForzaDad.getListaLavoratoriByIdProspettoProv(pp.getId().longValue());
					
				if(lavoratoriEnt != null && lavoratoriEnt.size() > 0) {
					lavoratoriSintesi = ProdisMappers.LAVORATORI_IN_FORZA.toModels(lavoratoriEnt);
						
					for (LavoratoriInForza lavoratore : lavoratoriSintesi) {
						
						LavoratoriInForzaMassivo lavoratoreMassivo = 
								new LavoratoriInForzaMassivo(lavoratore);
						
						lavoratoreMassivo.setSiglaProvincia(pp.getProvincia().getDsTarga());
						
						lavoratori.add(lavoratoreMassivo);
						
					}
					
				}
				
			}
			
		}
		
		esito = down.execute(
			lavoratori, 
			prospetto.getDatiAzienda().getCfAzienda(),
			null
		);

		return esito;

	}
	
	// UPLOAD DI TUTTI I LAVORATORI DI UN PROSPETTO

	public MassivoLavoratoriEsito uploadProspettoLavoratori(Prospetto prospetto, 
			byte[] fileInByte, String cfOperatoreConnesso) {

		MassivoLavoratoriEsito esitoGenerale = new MassivoLavoratoriEsito();
		
		esitoGenerale.setEsitoPositivo(true);

		try {
			esitoGenerale = up.execute(
				prospetto, 
				null, 
				cfOperatoreConnesso,
				fileInByte
			);
		} catch (BiffException e) {
			esitoGenerale.setEsitoPositivo(false);
			esitoGenerale.addErrore(MsgProdis.PROUPDE0003.getError());
		}

		return esitoGenerale;

	}
	
	// UPLOAD DI TUTTI I LAVORATORI DI UNA PROVINCIA
	
	public MassivoLavoratoriEsito uploadProvinciaLavoratori(Prospetto prospetto, 
			Long idProspettoProvinciale, byte[] fileInByte, String cfOperatoreConnesso) {

		MassivoLavoratoriEsito esitoGenerale = new MassivoLavoratoriEsito();
		
		esitoGenerale.setEsitoPositivo(true);

		try {
			esitoGenerale = up.execute(
				prospetto, 
				idProspettoProvinciale, 
				cfOperatoreConnesso,
				fileInByte
			);
		} catch (BiffException e) {
			esitoGenerale.setEsitoPositivo(false);
			esitoGenerale.addErrore(MsgProdis.PROUPDE0003.getError());
		}

		return esitoGenerale;

	}

	class UploadWorkSheet {
		
		// ESEGUI UPLOAD

		public MassivoLavoratoriEsito execute(Prospetto prospetto, Long idProspettoProvincialeSelezionato,
				String cfOperatoreConnesso, byte[] fileInByte) throws BiffException {

			MassivoLavoratoriEsito esitoGenerale = new MassivoLavoratoriEsito();
			
			esitoGenerale.setEsitoPositivo(Boolean.TRUE);

			// CONVERSIONE DELL'ARRAY DI BYTE IN FILE dummy
				
			InputStream inputStream = new ByteArrayInputStream(fileInByte);

		    File file = new File("dummy.xls");
		    try {
		    	Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
				
			// CREAZIONE WORKBOOK

			Workbook workbook = null;
			
			try {
				workbook = Workbook.getWorkbook(file);
			} catch (BiffException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
				
			Sheet sheet = workbook.getSheet(0);

			ArrayList<ApiError> erroriControlliGeneraliPreEstrazione = 
					new ArrayList<ApiError>();
				
			// CONTROLLI PRE-ESTRAZIONE
			
			boolean controlliGeneraliPreEstrazione = controlliGeneraliPreEstrazione(
					sheet,
					erroriControlliGeneraliPreEstrazione
			);
				
			if (!controlliGeneraliPreEstrazione) {

				esitoGenerale.setEsitoPositivo(false);
					
				esitoGenerale.addErrori(erroriControlliGeneraliPreEstrazione);
					
				return esitoGenerale;

			}
			
			if ((sheet.getRows()-1)>
				Integer.parseInt(lavoratoriInForzaDad.getParametroByNome(Parametro.PARAMETRO_MASSIVOLAVORATORI_NUMERO_LAVORATORI_PROSPETTO_UPLOAD))) {
			
				esitoGenerale.setEsitoPositivo(false);
			
				esitoGenerale.setFileLavoratori(null);
			
				esitoGenerale.setLavoratoriBuoni(null);
				esitoGenerale.setLavoratoriCattivi(null);
			
				esitoGenerale.addErrore(MsgProdis.PROUPDE0009.getError());
			
				return esitoGenerale;
			
			}
			
			List<ProRProspettoProvincia> ppList = 
					lavoratoriInForzaDad.getProspettoProvinciaByIdProspetto(prospetto.getId());
			
			Map<Long, List<LavoratoriInForza>> mappaLavoratori = new HashMap<Long, List<LavoratoriInForza>>();

			ProRProspettoProvincia prospettoProvincialeSelezionato = null;
			if (idProspettoProvincialeSelezionato!=null) {
				for (ProRProspettoProvincia p : ppList) {
					if (p.getIdProspettoProv()==idProspettoProvincialeSelezionato) {
						prospettoProvincialeSelezionato = p;
					}
				}
			}
			
			// CICLO PRINCIPALE PER OGNI LAVORATORE
			
			for (int row = 1; row < sheet.getRows(); row++) {

				// ESTRAZIONE LAVORATORE
				
				ArrayList<ApiError> erroriEstrazioneLavoratore = new ArrayList<ApiError>();
					
				LavoratoriInForzaMassivo lavoratore = estrazioneLavoratore(
					sheet, 
					row, 
					erroriEstrazioneLavoratore,
					prospettoProvincialeSelezionato
				);

				if (!erroriEstrazioneLavoratore.isEmpty()) {
					
					esitoGenerale.addLavoratoriCattivi(lavoratore);

					esitoGenerale.setEsitoPositivo(false);

					esitoGenerale.addErrore(erroriEstrazioneLavoratore.get(0));

					continue;

				}
				
				// CHECK PER LA TARGA PROVINCIALE
				
				String targaProvinciaQuestoLav = lavoratore.getSiglaProvincia();
				
				ProspettoProvincia sintesiProvincialeSelezionata = null;
				if (idProspettoProvincialeSelezionato!=null) {
					
					Boolean sintesiProvincialeSelezionataFinded = false;
					
					if (prospetto.getProspettoProvincias()!=null) {
						for (ProspettoProvincia pp : prospetto.getProspettoProvincias()) {
							if (pp.getId().longValue()==idProspettoProvincialeSelezionato) {
								sintesiProvincialeSelezionata = pp;
								sintesiProvincialeSelezionataFinded = true;
								break;
							}
						}
					}

					if (!sintesiProvincialeSelezionataFinded) {
						sintesiProvincialeSelezionata = 
								lavoratoriInForzaDad.getProspettoProvinciaById(idProspettoProvincialeSelezionato);
						if (prospetto.getProspettoProvincias()==null) {
							prospetto.setProspettoProvincias(new ArrayList<ProspettoProvincia>());
						}
						prospetto.getProspettoProvincias().add(sintesiProvincialeSelezionata);
					}
					
					if (!sintesiProvincialeSelezionata.getProvincia().getDsTarga()
							.equals(targaProvinciaQuestoLav)) {
						
						String msg = lavoratore.getCodiceFiscale() + " - Sigla provincia errata.";
						
						esitoGenerale.setEsitoPositivo(Boolean.FALSE);
						
						esitoGenerale.addErrore(new ApiError("PRO-UPD-E-0008",msg));
						
						esitoGenerale.addLavoratoriCattivi(lavoratore);
						
						continue;
						
					}
		
				} else {
					
					Boolean provinciaNelProspetto = false;
					
					if (prospetto.getProspettoProvincias()!=null) {
						for (ProspettoProvincia pp : prospetto.getProspettoProvincias()) {
							if (pp.getProvincia().getDsTarga().equals(targaProvinciaQuestoLav)) {
								provinciaNelProspetto = true;
							}
						}	
					}
					
					if (!provinciaNelProspetto) {
						
						ProspettoProvincia pp = lavoratoriInForzaDad.cercaProvinciaInProspetto(targaProvinciaQuestoLav, prospetto.getId());
						
						if (pp==null){
							
							String msg = lavoratore.getCodiceFiscale()
									+ " - Sigla provincia errata, non e' coerente con nessuno dei prospetti provinciali che compongono il prospetto.";
							
							esitoGenerale.setEsitoPositivo(Boolean.FALSE);
							
							esitoGenerale.addLavoratoriCattivi(lavoratore);
							
							esitoGenerale.addErrore(new ApiError("PRO-UPD-E-0009",msg));
							
							continue;
							
						} else {
							if (prospetto.getProspettoProvincias()==null) {
								prospetto.setProspettoProvincias(new ArrayList<ProspettoProvincia>());
							}
							prospetto.getProspettoProvincias().add(pp);
						}
						
					}
					
				}
				
				// REMAPPING SU ENTITY
				
				// set id prospetto prov sul lavoratore
				if (idProspettoProvincialeSelezionato!=null) {
					
					lavoratore.setIdProspettoProv(idProspettoProvincialeSelezionato);
					
				} else {
					
					for (ProRProspettoProvincia pp : ppList) {
						
						if (pp.getProTProvincia().getDsTarga().equals(lavoratore.getSiglaProvincia())) {
							lavoratore.setIdProspettoProv(pp.getIdProspettoProv());
							break;
						}
						
					}
					
				}
				
				// se non ho ancora la lista dei lavoratori per questa provincia, la carico
				if (lavoratore.getIdProspettoProv()!=null && !mappaLavoratori.containsKey(lavoratore.getIdProspettoProv())) {
					mappaLavoratori.put(
							lavoratore.getIdProspettoProv(), 
							ProdisMappers.LAVORATORI_IN_FORZA.toModels(
									lavoratoriInForzaDad.getListaLavoratoriByIdProspettoProv(lavoratore.getIdProspettoProv())
							)
					);
				}
				
				// remapping orario settimanale
				if (lavoratore.getOrarioSettimanale()!=null && !lavoratore.getOrarioSettimanale().equals("")) {
					
					Integer ore = Integer.parseInt(lavoratore.getOrarioSettimanale().substring(0, 2))*60;
					
					Integer min = Integer.parseInt(lavoratore.getOrarioSettimanale().substring(3, 5));
					
					lavoratore.setOrarioSettContrattualeMin(new BigDecimal(ore+min));
					
				} else {
					
					esitoGenerale.setEsitoPositivo(false);
					
					esitoGenerale.addLavoratoriCattivi(lavoratore);
					
					esitoGenerale.addErrore(MsgProdis.PROLAVE0030.getError());
					
					continue;
					
				}
				
				// remapping orario part time
				if (lavoratore.getOrarioSettimanalePartTime()!=null && !lavoratore.getOrarioSettimanalePartTime().equals("")) {
					
					Integer ore = Integer.parseInt(lavoratore.getOrarioSettimanalePartTime().substring(0, 2))*60;
					
					Integer min = Integer.parseInt(lavoratore.getOrarioSettimanalePartTime().substring(3, 5));
					
					lavoratore.setOrarioSettPartTimeMin(new BigDecimal(ore+min));
					
				} else {
					
					esitoGenerale.setEsitoPositivo(false);
					
					esitoGenerale.addLavoratoriCattivi(lavoratore);
					
					esitoGenerale.addErrore(MsgProdis.PROLAVE0032.getError());
					
					continue;
					
				}
				
				// remapping codice stato estero
				if (lavoratore.getCodiceStatoEstero().equals("")) {
					lavoratore.setCodiceStatoEstero(null);
				}
				if (lavoratore.getCodiceStatoEstero()!=null) {
					
					ProTStatiEsteri statiEsteri = lavoratoriInForzaDad.cercaStatiEsteri(lavoratore.getCodiceStatoEstero());
					
					if (statiEsteri!=null) {
						lavoratore.setStatiEsteri(ProdisMappers.STATI_ESTERI.toModel(statiEsteri));
					}
					
					
				}
				
				// remapping codice comune
				if (lavoratore.getCodiceComune().equals("")) {
					lavoratore.setCodiceComune(null);
				}
				if (lavoratore.getCodiceComune()!=null) {
					
					ProTComune comune = lavoratoriInForzaDad.cercaComune(lavoratore.getCodiceComune());
					
					if (comune!=null) {
						lavoratore.setComune(ProdisMappers.COMUNE.toModel(comune));
					}
					
					
				}
				
				// remapping codice tipo assunzione protetta
				if (lavoratore.getCodiceTipoAssunzioneProtetta().equals("")) {
					lavoratore.setCodiceTipoAssunzioneProtetta(null);
				}
				if (lavoratore.getCodiceTipoAssunzioneProtetta()!=null) {
					
					ProTAssunzioneProtetta ass = 
							lavoratoriInForzaDad.cercaAssunzioneProtetta(lavoratore.getCodiceTipoAssunzioneProtetta());
					
					if (ass!=null) {
						lavoratore.setAssunzioneProtetta(ProdisMappers.ASSUNZIONE_PROTETTA.toModel(ass));
					}
					
				}
				
				// remapping codice tipologia contrattuale
				if (lavoratore.getCodiceTipologiaContrattuale().equals("")) {
					lavoratore.setCodiceTipologiaContrattuale(null);
				}
				if (lavoratore.getCodiceTipologiaContrattuale()!=null) {
					
					ProTContratti cont = 
							lavoratoriInForzaDad.cercaContratti(lavoratore.getCodiceTipologiaContrattuale());
					
					if (cont!=null) {
						lavoratore.setContratti(ProdisMappers.CONTRATTI.toModel(cont));
					}
					
				}
				
				// controllo flag forma
				if (lavoratore.getFlgForma()==null || lavoratore.getFlgForma().equals("")) {

					esitoGenerale.setEsitoPositivo(Boolean.FALSE);
					
					esitoGenerale.addLavoratoriCattivi(lavoratore);
					
					esitoGenerale.addErrore(MsgProdis.PROUPDE0020.getError());

					continue;

				} else if (lavoratore.getContratti()!=null){
					
					if (!lavoratore.getContratti().getFlgForma().equals(lavoratore.getFlgForma())) {
						
						esitoGenerale.setEsitoPositivo(Boolean.FALSE);
						
						esitoGenerale.addLavoratoriCattivi(lavoratore);
						
						esitoGenerale.addErrore(MsgProdis.PROUPDE0021.getError());

						continue;
						
					}
					
				}
				
				// remapping codice qualifica istat
				if (lavoratore.getCodiceQualificaIstat().equals("")) {
					lavoratore.setCodiceQualificaIstat(null);
				}
				if (lavoratore.getCodiceQualificaIstat()!=null) {
					
					ProTIstat2001livello5 ist = 
							lavoratoriInForzaDad.cercaIstat(lavoratore.getCodiceQualificaIstat());
					
					if (ist!=null) {
						lavoratore.setIstat2001livello5(ProdisMappers.ISTAT2001LIVELLO5.toModel(ist));
					}
					
				}
				
				// controllo percentuale disabilità
				if (lavoratore.getPercentualeDisabilita()!=null && lavoratore.getPercentualeDisabilita().intValue()>100) {

					esitoGenerale.setEsitoPositivo(Boolean.FALSE);
					
					esitoGenerale.addLavoratoriCattivi(lavoratore);
					
					esitoGenerale.addErrore(MsgProdis.PROLAVE0035.getError());

					continue;

				}
				
				// check data fine rapporto + contratti indeterminati
				if (lavoratore.getDataFineRapporto()!=null
						&& lavoratore.getContratti()!=null && lavoratore.getContratti().getId()==1) {
					
					esitoGenerale.setEsitoPositivo(Boolean.FALSE);
					
					esitoGenerale.addLavoratoriCattivi(lavoratore);
					
					esitoGenerale.addErrore(MsgProdis.PROUPDE0022.getError());

					continue;
					
				}
				
				// ---
				
				// controlli post estrazione - se non validi ritorno
				boolean controlliPostEstrazioneLavoratore = controlliPostEstrazioneLavoratore(prospetto,
						idProspettoProvincialeSelezionato, lavoratore, esitoGenerale, 
						ppList, mappaLavoratori, sintesiProvincialeSelezionata);
					
				if (!controlliPostEstrazioneLavoratore) {

					continue;

				}
				
				ApiError erroreValidazioneLavoratore = 
						validazioneLavoratore(prospetto, ProdisMappers.PROSPETTO_PROVINCIA.toModels(ppList), 
								lavoratore, mappaLavoratori.get(lavoratore.getIdProspettoProv()));

				// check errori validazione
				if (erroreValidazioneLavoratore != null) {

					esitoGenerale.setEsitoPositivo(Boolean.FALSE);
					
					esitoGenerale.addLavoratoriCattivi(lavoratore);
					
					esitoGenerale.addErrore(erroreValidazioneLavoratore);

					continue;

				}

				// eseguo il merge (inserimento/modifica) per lavoratore
				MassivoLavoratoriEsito esitoMergeLavoratori = mergeLavoratori(
						idProspettoProvincialeSelezionato,
						prospetto, 
						lavoratore, 
						cfOperatoreConnesso,
						ppList,
						mappaLavoratori
				);
				
				// check esito merge
				if (!esitoMergeLavoratori.isEsitoPositivo()) {

					esitoGenerale.setEsitoPositivo(Boolean.FALSE);
					
					esitoGenerale.addLavoratoriCattivi(lavoratore);
						
					esitoGenerale.addErrore(esitoMergeLavoratori.getErrori().get(0));
					
					continue;

				} else {
					
					esitoGenerale.setEsitoPositivo(Boolean.TRUE);

					esitoGenerale.addMessaggio(esitoMergeLavoratori.getMessaggi().get(0));
					
					esitoGenerale.addLavoratoriBuoni(lavoratore);
					
					continue;

				}

			}

			return esitoGenerale;

		}
		
		// CONTROLLA PRE ESTRAZIONE UPLOAD
			
		protected final boolean controlliGeneraliPreEstrazione(Sheet sheet,
				ArrayList<ApiError> erroriControlliGeneraliPreEstrazione) {

			ProDParametri numeroMassimoLavoratoriUpload = lavoratoriInForzaDad.getParametro(
					Parametro.PARAMETRO_MASSIVOLAVORATORI_NUMERO_LAVORATORI_PROSPETTO_UPLOAD);

			int numeroLavoratoriUpload = sheet.getRows() - 1;
			
			if (numeroLavoratoriUpload > Integer.valueOf(numeroMassimoLavoratoriUpload.getDsValore())) {
				
				StringBuffer msg = new StringBuffer();
				
				msg.append("Il numero di lavoratori presenti nel file (")
						.append(numeroLavoratoriUpload)
						.append(") e' superiore al massimo consentito (")
						.append(numeroMassimoLavoratoriUpload.getDsValore())
						.append("). Si consiglia di sottoporre piu' file di minori dimensioni. ");
				
				erroriControlliGeneraliPreEstrazione.add(new ApiError("PRO-UPD-E-0006",msg.toString()));
				
				return false;
				
			}

			ProDParametri controlliGeneraliPreEstrazioneAbilitati = lavoratoriInForzaDad.getParametro(
					Parametro.PARAMETRO_MASSIVOLAVORATORI_CONTROLLI_GENERALI_PREESTRAZIONE_ABILITATI);

			if (Boolean.TRUE.equals(Boolean.valueOf(
					controlliGeneraliPreEstrazioneAbilitati.getDsValore()))) {
				
				Cell[] cell = sheet.getRow(0);
				
				int colonna = 0;
				
				for (String intestazione : mappaCampi.keySet()) {

					String contents = cell[colonna].getContents();

					if (!intestazione.equalsIgnoreCase(contents)) {
						
						erroriControlliGeneraliPreEstrazione.add(MsgProdis.PROUPDE0007.getError());
						
						return false;
						
					}

					colonna++;

				}
				
			}

			return true;
			
		}
		
		// VALIDA LAVORATORE

		protected ApiError validazioneLavoratore(Prospetto prospetto, List<ProspettoProvincia> prospettoProvincias,
				LavoratoriInForzaMassivo lavoratore, List<LavoratoriInForza> vecchiLavoratori) {
			
			if (lavoratore.getCodiceComune()==null || lavoratore.getCodiceComune().equals("")) {
				lavoratore.setCodiceComune(null);
				lavoratore.setComune(null);
			}
			if (lavoratore.getCodiceStatoEstero()==null || lavoratore.getCodiceStatoEstero().equals("")) {
				lavoratore.setCodiceStatoEstero(null);
				lavoratore.setStatiEsteri(null);
			}
			
			List<ApiError> apiErrors = new ArrayList<ApiError>();
			
			ValidatorLavoratore val = new ValidatorLavoratore(lavoratoriInForzaDad, prospetto,
			prospettoProvincias, vecchiLavoratori);
			
			val.validaLavoratore(lavoratore, apiErrors);
			
			if (apiErrors!=null && !apiErrors.isEmpty()) {
				return apiErrors.get(0);
			} else {
				return null;
			}
			
			
		}
		
		// CONTROLLA POST ESTRAZIONE LAVORATORE UPLOAD

		protected final boolean controlliPostEstrazioneLavoratore(
				Prospetto prospetto,
				Long idProspettoProvincialeSelezionato, 
				LavoratoriInForzaMassivo lavoratore,
				MassivoLavoratoriEsito esito, 
				List<ProRProspettoProvincia> ppFinded,
				Map<Long, List<LavoratoriInForza>> mappaLavoratori, 
				ProspettoProvincia sintesiProvincialeSelezionata) {
			
			ProspettoProvincia sintesiCorrente = null;

			// 1) controllo che la sigla provincia del lavoratore sia coerente con il prospetto provinciale che ho selezionato

			if (idProspettoProvincialeSelezionato != null) {

				sintesiCorrente = sintesiProvincialeSelezionata;

				// è stata selezionata una provincia, procedo con le verifiche che la sigla provincia indicata nella riga sia coerente
				
				if (sintesiProvincialeSelezionata != null && !sintesiProvincialeSelezionata.getProvincia().getDsTarga()
						.equalsIgnoreCase(lavoratore.getSiglaProvincia())) {
					
					String msg = lavoratore.getCodiceFiscale() + " - Sigla provincia errata.";
					
					esito.setEsitoPositivo(Boolean.FALSE);
					
					esito.addErrore(new ApiError("PRO-UPD-E-0008",msg));
					
					esito.addLavoratoriCattivi(lavoratore);
					
					return false;
					
				}

			} else {

				/*
				 * non e' stata selezionata alcuna provincia, sto lavorando su tutto il
				 * prospetto... devo pertanto verificare che la sigla provinciale inserita nella
				 * riga del file excel sia coerente con almeno una delle sintesi provinciali che
				 * compongono il prospetto
				 */
				
				boolean trovata = false;
				
				if (!ppFinded.isEmpty()) {
					
					for (ProRProspettoProvincia sintesiTmp : ppFinded) {
						
						if (sintesiTmp.getProTProvincia().getDsTarga().equalsIgnoreCase(lavoratore.getSiglaProvincia())) {
							
							trovata = true;
							
							sintesiCorrente = ProdisMappers.PROSPETTO_PROVINCIA.toModel(sintesiTmp);
							
							lavoratore.setIdProspettoProv(sintesiCorrente.getId().longValue());
							
							break;
							
						}
						
					}
					
				}

				if (!trovata) {
					
					String msg = lavoratore.getCodiceFiscale()
							+ " - Sigla provincia errata, non e' coerente con nessuno dei prospetti provinciali che compongono il prospetto.";
					
					esito.setEsitoPositivo(Boolean.FALSE);
					
					esito.addLavoratoriCattivi(lavoratore);
					
					esito.addErrore(new ApiError("PRO-UPD-E-0009",msg));
					
					return false;
					
				}

			}

			/*
			 * A questo punto sono sicuro che sintesiCorrente sia stato valorizzato
			 */
			
			//Controlli di congruenza...
			
			ProDParametri controlliCongruenzaAbilitati = lavoratoriInForzaDad.getParametro(Parametro.PARAMETRO_MASSIVOLAVORATORI_CONTROLLI_CONGRUENZA_ABILITATI);

			if (Boolean.TRUE.equals(Boolean.valueOf(controlliCongruenzaAbilitati.getDsValore()))) {

				// 2) Controllo la congruenza tra i campi Percentuale Disabilita' e Categoria Soggetto

				if (Constants.CATEGORIA_SOGGETTO_C.equalsIgnoreCase(lavoratore.getCategoriaSoggetto())
						&& lavoratore.getPercentualeDisabilita()!=null) {

					String msg = lavoratore.getCodiceFiscale()
							+ " - In caso di lavoratore Categoria protetta non deve essere valorizzata la Percentuale disabilita'.";

					esito.setEsitoPositivo(false);
					
					esito.addErrore(new ApiError("PRO-UPD-E-0010",msg));
					
					esito.addLavoratoriCattivi(lavoratore);
					
					return false;

				} else if (Constants.CATEGORIA_SOGGETTO_D.equalsIgnoreCase(lavoratore.getCategoriaSoggetto())
						&& lavoratore.getPercentualeDisabilita() == null) {

					String msg = lavoratore.getCodiceFiscale()
							+ " - In caso di lavoratore Disabile deve essere valorizzata la Percentuale disabilita'.";

					esito.setEsitoPositivo(false);
					
					esito.addErrore(new ApiError("PRO-UPD-E-0011",msg));
					
					esito.addLavoratoriCattivi(lavoratore);
					
					return false;

				}
				
			}

			// 3) controllo se i dati del lavoratori sul file excel sono stati modificati

			LavoratoriInForza lavoratoreSintesi = null;

			/*
			 * anche in questo caso devo ragionare nelle due modalita'... Nel caso in cui
			 * sia stata selezionata una provincia cerco la corrispondenza del lavoratore
			 * nell'elenco dei lavoratori della provincia
			 */
			List<LavoratoriInForza> lavoratoriFinded = mappaLavoratori.get(sintesiCorrente.getId().longValue());
			if (lavoratoriFinded==null || lavoratoriFinded.isEmpty()) {
				lavoratoriFinded = ProdisMappers.LAVORATORI_IN_FORZA.toModels(
						lavoratoriInForzaDad.getListaLavoratoriByIdProspettoProv(sintesiCorrente.getId().longValue())
				);
				mappaLavoratori.put(sintesiCorrente.getId().longValue(), lavoratoriFinded);
			}
			
			for (LavoratoriInForza lavoratoreSintesiTmp : lavoratoriFinded) {
				
				if (lavoratoreSintesiTmp.getCodiceFiscale().equalsIgnoreCase(lavoratore.getCodiceFiscale())) {
					
					lavoratoreSintesi = lavoratoreSintesiTmp;
					
					break;
					
				}
				
			}
			
			if (lavoratoreSintesi!=null && lavoratore.equals4Massivo(lavoratoreSintesi, mappaCampi)) {

				String msg = lavoratore.getCodiceFiscale() + " - Non ha subito modifiche.";

				esito.addMessaggio(msg);
				
				esito.addLavoratoriBuoni(lavoratore);
				
				esito.setEsitoPositivo(true);
				
				return false;

			}

			return true;
			
		}
		
		// MERGE LAVORATORI

		protected final MassivoLavoratoriEsito mergeLavoratori(
				Long idProspettoProvincialeSelezionato, 
				Prospetto prospetto, 
				LavoratoriInForzaMassivo lavoratoreMassivo, 
				String cfOperatoreConnesso, 
				List<ProRProspettoProvincia> ppList,
				Map<Long, List<LavoratoriInForza>> mappaLavoratori
		) {
			
			MassivoLavoratoriEsito esito = new MassivoLavoratoriEsito();
			
			try {

				lavoratoreMassivo.setFlgCompletato("S");

				boolean sintesiTrovata = false;

				for (ProRProspettoProvincia sintesiProvinciale : ppList) {

					if (lavoratoreMassivo.getSiglaProvincia()
							.equalsIgnoreCase(sintesiProvinciale.getProTProvincia().getDsTarga())) {

						sintesiTrovata = true;
						
						boolean trovato = false;
						
						List<LavoratoriInForza> lavoratoriList = mappaLavoratori.get(sintesiProvinciale.getIdProspettoProv());

						for (LavoratoriInForza lavoratore : lavoratoriList) {
							
							if (lavoratore.getCodiceFiscale().equalsIgnoreCase(lavoratoreMassivo.getCodiceFiscale())) {
								
								LavoratoriInForza lif = formMassivoToSingle(lavoratoreMassivo);

								lif.setId(lavoratore.getId());
								
								lavoratoriInForzaDad.updateSingleLavoratoriInForza(
										lif, 
										lavoratore.getId().intValue(), 
										"S"
								);
								
								trovato = true;
								
								esito.addMessaggio(lavoratore.getCodiceFiscale() + " - modificato correttamente.");
								esito.addLavoratoriBuoni(lavoratoreMassivo);
								esito.setEsitoPositivo(true);
								
								return esito;
								
							}
							
						}

						if (!trovato) {
							
							LavoratoriInForza lavoratore = formMassivoToSingle(lavoratoreMassivo);
							
							helper.gestioneDecodifiche(lavoratore);

							lavoratore.setIdProspettoProv(sintesiProvinciale.getId().longValue());
							
							lavoratoriInForzaDad.insertSingleLavoratoriInForza(
									lavoratore, sintesiProvinciale.getId().intValue(), "S");
							
							esito.addMessaggio(lavoratore.getCodiceFiscale() + " - inserito correttamente.");
							esito.addLavoratoriBuoni(lavoratoreMassivo);
							esito.setEsitoPositivo(true);
							
							return esito;
							
						}

					}
					
				}

				if (!sintesiTrovata) {
					
					esito.setEsitoPositivo(false);
					
					esito.addLavoratoriCattivi(lavoratoreMassivo);
				
					esito.addErrore(new ApiError("PRO-UPD-E-0010",
							lavoratoreMassivo.getCodiceFiscale()+" - Sigla provincia errata, non e' coerente con nessuno dei prospetti provinciali."));
				
					return esito;
					
				}

			} catch (Exception ex) {

				esito.setEsitoPositivo(false);
				
				return esito;

			} 

			return esito;
			
		}
		
		// ESTRAI LAVORATORE DA EXCEL

		protected final LavoratoriInForzaMassivo estrazioneLavoratore(Sheet sheet, int row,
				ArrayList<ApiError> erroriEstrazioneLavoratore, ProRProspettoProvincia pp) {

			LavoratoriInForzaMassivo lavoratore = new LavoratoriInForzaMassivo();

			Cell[] cell = sheet.getRow(row);
			
			Boolean flagFirst = true;
			
			Boolean flagFound = false;
			
			int colonna = 0;
			
			for (String intestazione : mappaCampi.keySet()) {

				String cella = null;

				CampoCella campoCella = mappaCampi.get(intestazione);

				try {

					if (campoCella.getObbligatorio().isObbligatorio() 
							&& cell[colonna].getContents()==null) {

						erroriEstrazioneLavoratore.add(
								new ApiError("PRO-UPD-E-0012",campoCella.getObbligatorio().getErrore()));

						
					} else {

						String contents = cell[colonna].getContents();

						if (TipoCella.date.equals(campoCella.getTipo()) 
								&& cell[colonna] instanceof DateCell) {

							try {
								
								DateCell dateCell = (DateCell) cell[colonna];
								
								if (dateCell != null) {
									
									cella = new SimpleDateFormat("dd/MM/yyyy").format(dateCell.getDate());
									
								}
								
							} catch (Exception ex) {
								
								erroriEstrazioneLavoratore.add(new ApiError("PRO-UPD-E-0013",campoCella.getValidabile().getErrore()));
							
							}

						} else if (TipoCella.number.equals(campoCella.getTipo())
								|| TipoCella.string.equals(campoCella.getTipo())) {
							
							if (flagFirst && pp!=null && !contents.toUpperCase().equals(pp.getProTProvincia().getDsTarga())) {
								
								erroriEstrazioneLavoratore.add(
										new ApiError("PRO-UPD-E-0014","Sigla provincia errata."));
								
								flagFound = true;
								
							}
							
							if (!flagFirst && flagFound && contents.length()>2) {
								lavoratore.setCodiceFiscale(contents);
								return lavoratore;
							}
							
							flagFirst = false;

							if (campoCella.getObbligatorio().isObbligatorio() 
									&& !contents.isEmpty()
									&& campoCella.getValidabile().isValidabile()
									&& !contents.matches(campoCella.getValidabile().getRegularExpression())) {
								
								erroriEstrazioneLavoratore.add(
										new ApiError("PRO-UPD-E-0014",campoCella.getValidabile().getErrore()));

								if (campoCella.getValidabile().getErrore().equals("CodiceFiscale non corretto")) {
									lavoratore.setCodiceFiscale(contents);
									return lavoratore;
								}
								
							} else if (!campoCella.getObbligatorio().isObbligatorio() 
									&& !contents.isEmpty()
									&& campoCella.getValidabile().isValidabile()
									&& !contents.matches(campoCella.getValidabile().getRegularExpression())) {

								erroriEstrazioneLavoratore.add(
										new ApiError("PRO-UPD-E-0015",campoCella.getValidabile().getErrore()));

							} else {

								cella = contents!=null ? contents.toUpperCase() : null;

							}

						}

					}

				} catch (ArrayIndexOutOfBoundsException arrEx) {
					
					arrEx.printStackTrace();
					
				} catch (Exception ex) {
					
					ex.printStackTrace();
					
				}

				ReflectionUtils.setProperty(lavoratore, campoCella, cella);
				
				colonna++;
				
			}

			// gestione decodifiche
			helper.gestioneDecodifiche(lavoratore);

			return lavoratore;
			
		}

	}

	class DownloadWorkSheet {
		
		// ESEGUI DOWNLOAD

		public MassivoLavoratoriEsito execute(List<LavoratoriInForzaMassivo> lavoratori,
				String codiceFiscaleAzienda, String siglaProvincia){

			operazioniPreGestioneMassiva(lavoratori);
			
			MassivoLavoratoriEsito esito = gestioneMassiva(
					lavoratori, 
					codiceFiscaleAzienda, 
					siglaProvincia
			);

			return esito;

		}
		
		// OPERAZIONE PRE GESTIONE MASSIVA DOWNLOAD

		protected void operazioniPreGestioneMassiva(List<LavoratoriInForzaMassivo> lavoratori) {

			for (LavoratoriInForzaMassivo lavoratore : lavoratori) {

				// insieme di operazione per tradurre le informazioni da id a codici ministeriali

				// comune
				if (lavoratore.getComune()!=null 
						&& lavoratore.getComune().getId() != null 
						&& lavoratore.getComune().getId() != 0) {
					
					Comune comune = lavoratore.getComune();
					
					if (comune != null) {
						
						lavoratore.setCodiceComune(comune.getCodComuneMin());

					}
					
				}

				// qualifica istat
				if (lavoratore.getIstat2001livello5()!=null 
						&& lavoratore.getIstat2001livello5().getId() != null
						&& lavoratore.getIstat2001livello5().getId() != 0) {
					
					Istat2001livello5 qualifica = lavoratore.getIstat2001livello5();
					
					if (qualifica != null) {
						
						lavoratore.setCodiceQualificaIstat(qualifica.getCodIstat2001livello5Min());
						
					}
					
				}

				// stato estero
				if (lavoratore.getStatiEsteri()!=null 
						&&lavoratore.getStatiEsteri().getId() != null 
						&& lavoratore.getStatiEsteri().getId() != 0) {
					
					StatiEsteri stato = lavoratore.getStatiEsteri();
					
					if (stato != null) {
						
						lavoratore.setCodiceStatoEstero(stato.getCodNazioneMin());
						
					}
					
				}

				// tipo assunzione protetta
				if (lavoratore.getAssunzioneProtetta()!=null 
						&& lavoratore.getAssunzioneProtetta().getId() != null
						&& lavoratore.getAssunzioneProtetta().getId() != 0) {
					
					AssunzioneProtetta tipoAssunzioneProtetta = lavoratore.getAssunzioneProtetta();
					
					if (tipoAssunzioneProtetta != null) {
						
						lavoratore.setCodiceTipoAssunzioneProtetta(
								tipoAssunzioneProtetta.getCodAssunzioneProtetta());
						
					}
					
				}

				// tipologia contrattuale
				if (lavoratore.getContratti()!=null 
						&& lavoratore.getContratti().getTipo() != null) {
					
					Contratti tipologiaContrattuale = lavoratore.getContratti();
					
					if (tipologiaContrattuale != null) {
						
						lavoratore.setCodiceTipologiaContrattuale(
								tipologiaContrattuale.getCodTipoContrattoMin());
						
					}
					
				}
				
				// orario settimanale
				if (lavoratore.getOrarioSettContrattualeMin() != null) {
					
					lavoratore.setOrarioSettimanale(fromMinutesToHHmm(lavoratore.getOrarioSettContrattualeMin().intValue()));	
					
				}
				
				// orario part time
				if (lavoratore.getOrarioSettPartTimeMin() != null) {
					
					lavoratore.setOrarioSettimanalePartTime(fromMinutesToHHmm(lavoratore.getOrarioSettPartTimeMin().intValue()));	
					
				}
				
			}

		}
		
		private String fromMinutesToHHmm(int minutes) {
		    long hours = TimeUnit.MINUTES.toHours(Long.valueOf(minutes));
		    long remainMinutes = minutes - TimeUnit.HOURS.toMinutes(hours);
		    return String.format("%02d:%02d", hours, remainMinutes);
		}
		
		// GESTIONE MASSIVA DOWNLOAD

		private MassivoLavoratoriEsito gestioneMassiva(List<LavoratoriInForzaMassivo> lavoratori,
				String codiceFiscaleAzienda, String siglaProvincia) {

			MassivoLavoratoriEsito esito = new MassivoLavoratoriEsito();
			
			esito.setEsitoPositivo(false);

			WritableWorkbook workbook = null;

			File temp = null;
			
			try {
					
				temp = File.createTempFile(
						System.currentTimeMillis() + "_" + (Math.random() * 1000), null);
				
			} catch (IOException e) {
					
				e.printStackTrace();
					
			}
				
			WorkbookSettings wbSettings = new WorkbookSettings();

			wbSettings.setLocale(new Locale("it", "IT"));

			try {
				
				workbook = Workbook.createWorkbook(temp, wbSettings);
				
			} catch (IOException e1) {
				
				e1.printStackTrace();
				
			}
			
			workbook.createSheet("Massivo Lavoratori", 0);
			
			WritableSheet excelSheet = workbook.getSheet(0);

			WritableCellFormat cellaTitolo = new WritableCellFormat(
					new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false));
			
			try {
				
				cellaTitolo.setWrap(false);
				
			} catch (WriteException e) {
				
				e.printStackTrace();
				
			}

			WritableCellFormat cellaNormale = new WritableCellFormat(
					new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false));
			
			try {
				
				cellaNormale.setWrap(true);
				
			} catch (WriteException e) {
				
				e.printStackTrace();
				
			}

			CellView cv = new CellView();
			cv.setFormat(cellaNormale);
			cv.setFormat(cellaTitolo);
			cv.setAutosize(true);

			// intestazione
			int riga = 0;
			int colonna = 0;
			for (CampoCella campoCella : mappaCampi.values()) {
				
				try {
					
					excelSheet.addCell(
							new Label(colonna++, riga, campoCella.getCampo(), cellaTitolo)
					);
					
				} catch (RowsExceededException e) {
					
					e.printStackTrace();
					
				} catch (WriteException e) {
					
					e.printStackTrace();
					
				}
				
			}
			
			WritableCellFormat EXCEL_DATE_FORMATTER = new WritableCellFormat(
					new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false),
					new DateFormat("dd/MM/yyyy")
			);

			// dati
			
			riga++;
			
			for (LavoratoriInForza lavoratore : lavoratori) {
				
				colonna = 0;
				
				for (CampoCella campoCella : mappaCampi.values()) {

					String property = ReflectionUtils.getProperty(lavoratore, campoCella.getCampo());

					if (property==null || property.isEmpty()) {

						if (NomiCampi.FlgForma.toString().equalsIgnoreCase(campoCella.getCampo())) {

							Contratti tipologiaContrattuale = lavoratore.getContratti();

							if (Constants.CONTRATTO_FORMA_ENTRAMBE
									.equalsIgnoreCase(tipologiaContrattuale.getFlgForma())) {

								if (lavoratore.getDataFineRapporto()!=null) {
									
									try {
										
										excelSheet.addCell(new Label(colonna, riga,
												Constants.CONTRATTO_FORMA_DETERMINATO, cellaNormale));
										
									} catch (RowsExceededException e) {
										
										e.printStackTrace();
										
									} catch (WriteException e) {
										
										e.printStackTrace();
										
									}
									
								} else {
									
									try {
										
										excelSheet.addCell(new Label(colonna, riga,
												Constants.CONTRATTO_FORMA_INDETERMINATO, cellaNormale));
										
									} catch (RowsExceededException e) {
										
										e.printStackTrace();
										
									} catch (WriteException e) {
										
										e.printStackTrace();
										
									}
									
								}

							} else {

								try {
									
									excelSheet.addCell(new Label(
											colonna, 
											riga, 
											tipologiaContrattuale.getFlgForma(),
											cellaNormale
									));
									
								} catch (RowsExceededException e) {
									
									e.printStackTrace();
									
								} catch (WriteException e) {
									
									e.printStackTrace();
									
								}

							}
							
						} else {

							try {
									
								excelSheet.addCell(new jxl.write.Blank(colonna, riga, cellaNormale));
									
							} catch (RowsExceededException e) {
									
								e.printStackTrace();
									
							} catch (WriteException e) {
									
								e.printStackTrace();
									
							}

						}

					} else if (TipoCella.date.equals(campoCella.getTipo())) {

						try {
							
							Date data = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy",
									Locale.US).parse(property);
							
							excelSheet.addCell(new jxl.write.DateTime(
									colonna, riga, data, EXCEL_DATE_FORMATTER));
							
						} catch (RowsExceededException e) {
							
							e.printStackTrace();
							
						} catch (WriteException e) {
							
							e.printStackTrace();
							
						} catch (ParseException e) {
							
							e.printStackTrace();
							
						}

					} else if (TipoCella.number.equals(campoCella.getTipo())) {

						if (NomiCampi.PercentualeDisabilita.toString().equalsIgnoreCase(campoCella.getCampo())
								&& Constants.CATEGORIA_SOGGETTO_C.equalsIgnoreCase(lavoratore.getCategoriaSoggetto())) {

							try {
									
								excelSheet.addCell(new jxl.write.Blank(colonna, riga, cellaNormale));
									
							} catch (RowsExceededException e) {
									
								e.printStackTrace();
									
							} catch (WriteException e) {
									
								e.printStackTrace();
									
							}

						} else {

							try {
								
								excelSheet.addCell(new Label(colonna, riga, property, cellaNormale));
								
							} catch (RowsExceededException e) {
								
								e.printStackTrace();
								
							} catch (WriteException e) {
								
								e.printStackTrace();
								
							}

						}

					} else {

						try {
							
							excelSheet.addCell(new Label(colonna, riga, property, cellaNormale));
							
						} catch (RowsExceededException e) {
							
							e.printStackTrace();
							
						} catch (WriteException e) {
							
							e.printStackTrace();
							
						}

					}
					
					colonna++;
				}
				
				riga++;
				
			}

			try {
				
				workbook.setOutputFile(temp);
				
			} catch (IOException e2) {
				
				e2.printStackTrace();
				
			}

			if (workbook != null) {
				
				try {
					
					workbook.write();
					
				} catch (IOException e1) {
					
					e1.printStackTrace();
					
				}
				
				try {
					
					workbook.close();
					
				} catch (WriteException e) {
					
					e.printStackTrace();
					
				} catch (IOException e) {
					
					e.printStackTrace();
					
				}
				
			}

			esito.setEsitoPositivo(true);
				
			if (siglaProvincia==null) {

				esito.getFileLavoratori().setName(helper.getNomeFile(codiceFiscaleAzienda, ""));
				
			} else {
				
				esito.getFileLavoratori().setName(helper.getNomeFile(
						codiceFiscaleAzienda, siglaProvincia));
				
			}
				
			try {
				
				esito.getFileLavoratori().setContents(Files.readAllBytes(temp.toPath()));
				
			} catch (IOException e) {
				
				e.printStackTrace();
				
			}

			return esito;

		}

	}

	class Helper {

		protected final String getNomeFile(String codiceFiscaleAzienda, String siglaProvincia) {

			String result = new String();

			try {

				result = lavoratoriInForzaDad.getParametro(
						Parametro.PARAMETRO_MASSIVOLAVORATORI_NOMEFILE)
						.getDsValore();
				
				result = result.replaceFirst("\\{0}", codiceFiscaleAzienda);
				
				result = result.replaceFirst("\\{1}", siglaProvincia == null?"" : siglaProvincia);
				
				result = result.replaceFirst("\\{2}", new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
				

			} catch (Exception e) {
				
				e.printStackTrace();
				
			}

			return result;
		}

		protected final void gestioneDecodifiche(LavoratoriInForza lavoratore) {

			// comune
			if (lavoratore.getComune()!=null 
					&& lavoratore.getComune().getCodComuneMin() != null
					&& lavoratore.getComune().getCodComuneMin().length() > 0) {
				
				Comune comune = lavoratore.getComune();
				
				if (comune == null) {
					
					comune = lavoratore.getComune();
					
				}
				
				if (comune != null) {
					
					lavoratore.getComune().setId(comune.getId());
					
					lavoratore.getComune().setDsProTComune(comune.getDsProTComune());
					
				}
				
			}

			// qualifica istat
			if (lavoratore.getIstat2001livello5()!=null 
					&& lavoratore.getIstat2001livello5().getCodIstat2001livello5Min() != null
					&& lavoratore.getIstat2001livello5().getCodIstat2001livello5Min().length() > 0) {
				
				Istat2001livello5 qualifica = lavoratore.getIstat2001livello5();
				
				if (qualifica != null) {
					
					lavoratore.getIstat2001livello5()
							.setCodIstat2001livello5Min(qualifica.getCodIstat2001livello5Min());
					
					lavoratore.getIstat2001livello5().setDsComIstat2001livello5(qualifica.getDsComIstat2001livello5());
					
				}
				
			}

			// stato estero
			if (lavoratore.getStatiEsteri()!=null 
					&& lavoratore.getStatiEsteri().getCodNazioneMin() != null
					&& lavoratore.getStatiEsteri().getCodNazioneMin().length() > 0) {
				
				StatiEsteri statoEstero = lavoratore.getStatiEsteri();
				
				if (statoEstero == null) {
					
					statoEstero = lavoratore.getStatiEsteri();
					
				}
				
				if (statoEstero != null) {
					
					lavoratore.getStatiEsteri().setId(statoEstero.getId());
					
					lavoratore.getStatiEsteri().setDsStatiEsteri(statoEstero.getDsStatiEsteri());
					
				}
				
			}

			// tipo assunzione protetta
			if (lavoratore.getAssunzioneProtetta()!=null 
					&& lavoratore.getAssunzioneProtetta().getCodAssunzioneProtetta() != null
					&& lavoratore.getAssunzioneProtetta().getCodAssunzioneProtetta().length() > 0) {
				
				AssunzioneProtetta assunzioneProtetta = lavoratore.getAssunzioneProtetta();
				
				if (assunzioneProtetta != null) {
					
					lavoratore.getAssunzioneProtetta().setId(assunzioneProtetta.getId());
					
				}
				
			}

			// tipologia contrattuale
			if (lavoratore.getContratti()!=null 
					&& lavoratore.getContratti().getTipo() != null 
					&& lavoratore.getContratti().getTipo().length() > 0) {
				
				Contratti contratto = lavoratore.getContratti();
				
				if (contratto != null) {
					
					lavoratore.getContratti().setId(contratto.getId());
					
				}
				
			}

		}

	}

	public byte[] creaFileEsito(List<String> messaggi, List<ApiError> errori, 
			List<LavoratoriInForzaMassivo> lavB, List<LavoratoriInForzaMassivo> lavC) {

		byte[] result = null;
		
		try {

			WritableWorkbook workbook = null;

			File temp = null;
			
			temp = File.createTempFile(System.currentTimeMillis() + "_" + (Math.random() * 1000), null);
			
			System.out.println(temp.getAbsolutePath());

			WorkbookSettings wbSettings = new WorkbookSettings();

			wbSettings.setLocale(new Locale("it", "IT"));

			workbook = Workbook.createWorkbook(temp, wbSettings);
			
			workbook.createSheet("positivi", 0);
			
			workbook.createSheet("negativi", 1);
			
			WritableSheet positivi = workbook.getSheet(0);
			
			WritableSheet negativi = workbook.getSheet(1);

			WritableCellFormat cellaTitolo = new WritableCellFormat(
					new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false));
			
			cellaTitolo.setWrap(false);

			WritableCellFormat cellaNormale = new WritableCellFormat(
					new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false));
			
			cellaNormale.setWrap(true);

			CellView cv = new CellView();
			
			cv.setFormat(cellaNormale);
			
			cv.setFormat(cellaTitolo);
			
			cv.setAutosize(true);

			// POSITIVI
			
			// intestazione
			
			positivi.addCell(new Label(0, 0, "Lavoratore", cellaTitolo));
			
			positivi.addCell(new Label(1, 0, "Esito", cellaTitolo));
			
			// dati codice fiscale lavoratore
			
			if (lavB!=null && !lavB.isEmpty()) {
				
				int rigaPositiviCodice=1;
				for (LavoratoriInForzaMassivo l : lavB) {
												
					positivi.addCell(new Label(0, rigaPositiviCodice, l.getCodiceFiscale(), cellaNormale));
					
					rigaPositiviCodice++;
											
				}

				// dati messaggio
				int rigaPositiviMessaggio=1;
				for (String messaggio : messaggi) {
					
					String[] tkz = messaggio.split("-");
						
					positivi.addCell(new Label(1, rigaPositiviMessaggio, tkz[1], cellaNormale));
					
					rigaPositiviMessaggio++;
					
				}
				
			}

			// NEGATIVI
			
			// intestazione

			negativi.addCell(new Label(0, 0, "Lavoratore", cellaTitolo));
			
			negativi.addCell(new Label(1, 0, "Esito", cellaTitolo));
			
			if (lavC!=null && !lavC.isEmpty()) {
				
				// dati codice fiscale lavoratore
				int rigaNegativiCodice=1;
				for (LavoratoriInForzaMassivo l : lavC) {
				
					negativi.addCell(new Label(0, rigaNegativiCodice, l.getCodiceFiscale(), cellaNormale));
								
					rigaNegativiCodice++;
								
				}

				// dati errori
				int rigaNegativiErrori=1;
				for (ApiError errore : errori) {
					
					String[] tkz = {"a","b"};
					if (errore.getErrorMessage().contains("-")) {
						tkz = errore.getErrorMessage().split("-");
					} else {
						tkz[1] = errore.getErrorMessage();
					}
					
					negativi.addCell(new Label(1, rigaNegativiErrori, tkz[1], cellaNormale));
					
					rigaNegativiErrori++;
					
				}
				
			}

			// ---

			if (workbook != null) {
				
				workbook.write();
				
				workbook.close();
				
			}
			
			// ---

			result = Files.readAllBytes(temp.toPath());

		} catch (Exception ex) {
			
			ex.printStackTrace();
			
		}

		return result;
		
	}

	public LavoratoriInForza formMassivoToSingle(LavoratoriInForzaMassivo lm) {
		
		LavoratoriInForza lif = new LavoratoriInForza();
		
		if (lm.getId()!=null) {
			lif.setId(lm.getId());
		}
		
		lif.setSesso(lm.getSesso());
		lif.setPercentualeDisabilita(lm.getPercentualeDisabilita());
		lif.setOrarioSettPartTimeMin(lm.getOrarioSettPartTimeMin());
		lif.setOrarioSettContrattualeMin(lm.getOrarioSettContrattualeMin());
		lif.setNome(lm.getNome());
		lif.setIstat2001livello5(lm.getIstat2001livello5());
		lif.setIdProspettoProv(lm.getIdProspettoProv());
		lif.setFlgCompletato(lm.getFlgCompletato());
		lif.setAssunzioneProtetta(lm.getAssunzioneProtetta());
		lif.setCategoriaAssunzione(lm.getCategoriaAssunzione());
		lif.setCategoriaSoggetto(lm.getCategoriaSoggetto());
		lif.setCodiceFiscale(lm.getCodiceFiscale());
		lif.setCognome(lm.getCognome());
		
		if (lm.getComune()!=null) {
			lif.setComune(lm.getComune());
		} else if (lm.getCodiceComune()!=null) {
			lif.setComune(ProdisMappers.COMUNE.toModel(lavoratoriInForzaDad.cercaComune(lm.getCodiceComune())));
		} else {
			lif.setComune(null);
		}
		
		if (lm.getStatiEsteri()!=null) {
			lif.setStatiEsteri(lm.getStatiEsteri());
		} else if (lm.getCodiceStatoEstero()!=null) {
			lif.setStatiEsteri(ProdisMappers.STATI_ESTERI.toModel(lavoratoriInForzaDad.cercaStatiEsteri(lm.getCodiceStatoEstero())));
		} else {
			lif.setStatiEsteri(null);
		}
		
		lif.setContratti(lm.getContratti());
		lif.setDataInizioRapporto(lm.getDataInizioRapporto());
		lif.setDataFineRapporto(lm.getDataFineRapporto());
		lif.setDataNascita(lm.getDataNascita());
		
		return lif;
	}

}
