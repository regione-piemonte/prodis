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
package it.csi.prodis.prodisweb.ejb.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import it.csi.prodis.prodisweb.ejb.business.be.dad.DatiProvincialiDad;
import it.csi.prodis.prodisweb.ejb.entity.ProDRiepilogoNazionale;
import it.csi.prodis.prodisweb.ejb.entity.ProTComune;
import it.csi.prodis.prodisweb.ejb.mapper.ProdisMappers;
import it.csi.prodis.prodisweb.lib.dto.ApiError;
import it.csi.prodis.prodisweb.lib.dto.error.MsgProdis;
import it.csi.prodis.prodisweb.lib.dto.prospetto.Prospetto;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProspettoProvincia;
import it.csi.prodis.prodisweb.lib.dto.prospetto.RiepilogoProvinciale;
import it.csi.prodis.prodisweb.lib.dto.prospetto.VistaElencoProvQ2;

public class ValidatorConfermaDatiProvinciali {

	DatiProvincialiDad datiProvincialiDad;
	Prospetto prospettoQuadro1 = new Prospetto();
	ProspettoProvincia prospettoProvincia = new ProspettoProvincia();

	public ValidatorConfermaDatiProvinciali(DatiProvincialiDad datiProvincialiDad) {
		super();
		this.datiProvincialiDad = datiProvincialiDad;
	}


	public void validaConfermaEProsegui (Long idProspetto, List<ApiError> apiErrors) {

		List<VistaElencoProvQ2> elencoProvince =  datiProvincialiDad.getElencoProvQ2ByIdProspetto(idProspetto);
		if (elencoProvince == null || elencoProvince.size() == 0) {
			apiErrors.add(MsgProdis.PRODPRE0096.getError());
		} else {
			ArrayList<String> pvList = new ArrayList<String>();
			for (VistaElencoProvQ2 vista : elencoProvince) {
				if ("N".equalsIgnoreCase(vista.getFlgConfermatoQ2())) {
					pvList.add(vista.getDsTarga());
				}
			}
			if (pvList != null && pvList.size() > 0) {
				StringBuffer message = new StringBuffer();
				message.append("I dati relativi alle province elencate non sono stati confermati: ");
				for(String s : pvList){
					message.append(s);
					message.append(" ");
				}
				apiErrors.add(new ApiError("PRO-DPR-E-0092", message.toString()));
			}
		}

		Prospetto prospetto = datiProvincialiDad.getProspetto(idProspetto);
		String codComuneSedeLegale = null;
		if (prospetto != null 
				&& prospetto.getDatiAzienda() != null 
				&& prospetto.getDatiAzienda().getSedeLegale() != null 
				&& prospetto.getDatiAzienda().getSedeLegale().getComune() != null
				&& prospetto.getDatiAzienda().getSedeLegale().getComune().getCodComuneMin() != null ) {
			codComuneSedeLegale = prospetto.getDatiAzienda().getSedeLegale().getComune().getCodComuneMin();

			List<ProTComune> comuni = datiProvincialiDad.getComuni(null, codComuneSedeLegale, null, null);
			if(comuni == null || comuni.size() != 1){
				apiErrors.add(MsgProdis.PRODPRE0093.getError());
			} else {
				long idProv = comuni.get(0).getProTProvincia().getIdTProvincia();
				boolean esisteQ2InProv = false;
				for(VistaElencoProvQ2 sintesiQ2 : elencoProvince){
					if(sintesiQ2.getIdTProvincia().equals(idProv)){
						esisteQ2InProv = true;
						break;
					}
				}
				if(!esisteQ2InProv){
					apiErrors.add(MsgProdis.PRODPRE0094.getError());
				}
			}
		} else {
			apiErrors.add(MsgProdis.PRODPRE0093.getError());
		}
		
	}

	public void validaMessaggioAvviso (Prospetto prospetto, List<ApiError> apiErrors, List<VistaElencoProvQ2> elencoProvince) {

		if (prospetto != null 
				&& prospetto.getCategoriaAzienda() != null
				&& prospetto.getCategoriaAzienda().getId() != null) {

			if (prospetto.getCategoriaAzienda().getId().intValue() == ConstantsProdis.COD_CATEGORIA_AZIENDA_C) {
				String msg = "Per questa categoria di azienda (15-35 dipendenti) e' possibile considerare i part time come tempo pieno e conteggiare la persona come 1 unita', " +
						"se il lavoratore disabile ha invalidita' superiore al 50&#37; o ascrivibile alla 5&deg; categoria, indipendentemente dall'orario di lavoro svolto.";

				apiErrors.add(new ApiError("PRO-DPR-W-0095", msg));
			}
		}
	}
	
	public void checkWarningPerScoperture(Prospetto prospetto , List<VistaElencoProvQ2> elencoProvince, List<ApiError> apiErrors) {
		
		Optional<ProDRiepilogoNazionale> riepilogoNazionale = datiProvincialiDad.getRiepilogoNazionale(prospetto.getId());
		
		if (riepilogoNazionale.isPresent()) {
			prospetto.setRiepilogoNazionale(ProdisMappers.RIEPILOGO_NAZIONALE.toModel(riepilogoNazionale.get()));
		}
		
		if (prospetto.getRiepilogoNazionale() != null && elencoProvince != null && elencoProvince.size() > 0) {
			if (isScopertureMaggioriTotaleNumPostiDisponibili(prospetto, elencoProvince)
					&& isNotSospensionePresente(prospetto, elencoProvince)) {
				apiErrors.add(MsgProdis.PRODPRW0097.getError());
				
			} 
			else if (elencoProvince.size() > 1) {
				
				List<ProspettoProvincia> elencoProspettoProvincia = datiProvincialiDad.getProspettoProvinciaByIdProspetto(prospetto.getId());
				if (elencoProspettoProvincia != null && elencoProspettoProvincia.size() > 0) {
					for (ProspettoProvincia pp : elencoProspettoProvincia) {
						RiepilogoProvinciale riepilogoProvinciale = datiProvincialiDad.getRiepilogoProvincialeByIdProspettoProvincia(pp.getId().longValue());
						if (riepilogoProvinciale != null) {
							if (isScopertureProvincialiPresenti(riepilogoProvinciale)) {
								apiErrors.add(MsgProdis.PRODPRW0098.getError());
								break;

							}
						}
					}
				}
			}
		}
	}

	private boolean isScopertureProvincialiPresenti(RiepilogoProvinciale riepilogoProvinciale) {
		return (ProdisSrvUtil.isNotVoid(riepilogoProvinciale.getNumScopertureDisabili()) && riepilogoProvinciale.getNumScopertureDisabili().intValue() > 0)
				|| (ProdisSrvUtil.isNotVoid(riepilogoProvinciale.getNumScopertureCatProt()) && riepilogoProvinciale.getNumScopertureCatProt().intValue() > 0);
	}
	
	private boolean isScopertureMaggioriTotaleNumPostiDisponibili(Prospetto prospetto, List<VistaElencoProvQ2> elencoProvince) {

		long numTotalePostiLavoroDisponibili = 0;

		long numScopertureDisabili = 0;
		long numScopertureCategorieProtette = 0;
		numScopertureDisabili          = prospetto.getRiepilogoNazionale().getNumScopertDisabili().longValue();
		numScopertureCategorieProtette = prospetto.getRiepilogoNazionale().getNumScopertCategorieProtette().longValue();
		for (VistaElencoProvQ2 prov : elencoProvince) {
			numTotalePostiLavoroDisponibili += prov.getNumPostiDisp().longValue();
		}

		if (numScopertureDisabili > numTotalePostiLavoroDisponibili
				|| numScopertureCategorieProtette > numTotalePostiLavoroDisponibili) {
			return true;
		} else
			return false;

	}

	private boolean isNotSospensionePresente(Prospetto prospetto, List<VistaElencoProvQ2> elencoProvince) {
		return !isSospensionePresente(prospetto, elencoProvince);
	}
	private boolean isSospensionePresente(Prospetto prospetto, List<VistaElencoProvQ2> elencoProvince) {

		boolean flagSospensioneInCorsoProspetto = isPresenteFlgSospensioneInCorsoSI(prospetto);
		boolean flagSospensioneInCorsoProvince = isPresenteProvinciaFlgSospensioneInCorsoSI(elencoProvince);

		return (flagSospensioneInCorsoProspetto || flagSospensioneInCorsoProvince);
	}
	private boolean isPresenteFlgSospensioneInCorsoSI(Prospetto prospetto) {

		return (prospetto.getRiepilogoNazionale().getFlgSospensioniInCorso().equalsIgnoreCase("S") ? true : false);
	}
	private boolean isPresenteProvinciaFlgSospensioneInCorsoSI(List<VistaElencoProvQ2> elencoProvince) {

		for ( VistaElencoProvQ2 prov : elencoProvince) {
			if (prov.getSospInCorso().equalsIgnoreCase("S")) {
				return true;
			}
		}
		return false;
	}
}


