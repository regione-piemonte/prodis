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

import java.math.BigDecimal;
import java.util.List;

import it.csi.prodis.prodisweb.ejb.business.be.dad.CompensazioniDad;
import it.csi.prodis.prodisweb.ejb.entity.ProDProvCompensazioni;
import it.csi.prodis.prodisweb.ejb.entity.ProRProspettoProvincia;
import it.csi.prodis.prodisweb.lib.dto.ApiError;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Provincia;
import it.csi.prodis.prodisweb.lib.dto.error.MsgProdis;
import it.csi.prodis.prodisweb.lib.dto.prospetto.DatiAzienda;
import it.csi.prodis.prodisweb.lib.dto.prospetto.Prospetto;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProspettoProvincia;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProvCompensazioni;
import it.csi.prodis.prodisweb.lib.dto.prospetto.RiepilogoProvinciale;

public class ValidatorCompensazioni extends ValidatorUtil {

	Prospetto prospettoQuadro1 = new Prospetto();
	ProspettoProvincia prospettoProvincia = new ProspettoProvincia();
	CompensazioniDad compensazioniDad;

	public ValidatorCompensazioni(CompensazioniDad compensazioniDad) {
		super();
		this.compensazioniDad = compensazioniDad;
	}

	public void valida(ProvCompensazioni compensazioni, List<ApiError> apiErrors) {
		prospettoProvincia = compensazioniDad.getProspettoProvinciaById(compensazioni.getIdProspettoProv());
		prospettoQuadro1 = compensazioniDad.getProspetto(prospettoProvincia.getIdProspetto());

		DatiAzienda datiAziendaProspetto = null;
		if (prospettoQuadro1 != null && prospettoQuadro1.getDatiAzienda() != null) {
			datiAziendaProspetto = prospettoQuadro1.getDatiAzienda();
		}
		int tipoDichiarante = 0;
		if (datiAziendaProspetto != null && prospettoQuadro1.getDatiAzienda().getDichiarante() != null) {
			tipoDichiarante = prospettoQuadro1.getDatiAzienda().getDichiarante().getId().intValue();
		}

		if (compensazioni.getCategoriaCompensazione() == null
				|| "".equalsIgnoreCase(compensazioni.getCategoriaCompensazione())) {
			apiErrors.add(MsgProdis.PROPCTE0001.getError());
		}
		if (compensazioni.getnLavoratori() == null || compensazioni.getnLavoratori().equals(new BigDecimal(0))) {
			apiErrors.add(MsgProdis.PROPCTE0002.getError());
		}
		if (compensazioni.getCategoriaSoggetto() == null || "".equalsIgnoreCase(compensazioni.getCategoriaSoggetto())) {
			apiErrors.add(MsgProdis.PROPCTE0003.getError());
		}
		if (compensazioni.getIdProspettoProv() == null || compensazioni.getIdProspettoProv().equals(0L)) {
			apiErrors.add(MsgProdis.PROPCTE0005.getError());
		}
		if (compensazioni.getProvincia() == null || compensazioni.getProvincia().getId() == null) {
			apiErrors.add(MsgProdis.PROPCTE0004.getError());
		}

		if (ProdisSrvUtil.isNotVoid(compensazioni.getCfAziendaAppartenAlGruppo())) {
			if (tipoDichiarante != ConstantsProdis.COD_DICHIARANTE_D
					|| (datiAziendaProspetto != null && ProdisSrvUtil.isVoid(datiAziendaProspetto.getCfCapogruppo()))) {
				apiErrors.add(MsgProdis.PROPCTE0006.getError());
			}
			String regexCfAziendaAlfanumerico = compensazioniDad
					.getParametroByNome(ConstantsProdis.PARAMETRO_REGEX_AZIENDA_CODICEFISCALE_ALFANUMERICO);
			String regexCfAziendaNumerico = compensazioniDad
					.getParametroByNome(ConstantsProdis.PARAMETRO_REGEX_AZIENDA_CODICEFISCALE_NUMERICO);
			if (!ValidatorUtil.checkFormatoCfoPiva(compensazioni.getCfAziendaAppartenAlGruppo(),
					regexCfAziendaAlfanumerico, regexCfAziendaNumerico)) {
				apiErrors.add(MsgProdis.PROPCTE0007.getError());
			}
		}

		if (apiErrors != null && apiErrors.size() > 0) {
			return;
		}
		List<ProRProspettoProvincia> elencoProspettiProvincia = compensazioniDad
				.getElencoProspettiProvinciaByIdProspetto(prospettoQuadro1.getId());
		if (ProdisSrvUtil.isVoid(compensazioni.getCfAziendaAppartenAlGruppo())) {

			boolean flgTrovata = false;

			for (ProRProspettoProvincia datiProv : elencoProspettiProvincia) {
				if (datiProv.getProTProvincia().getIdTProvincia() == compensazioni.getProvincia().getId().longValue()) {
					if (datiProv.getIdProspettoProv() == compensazioni.getIdProspettoProv().longValue()) {
						apiErrors.add(MsgProdis.PROPCTE0008.getError());
					}
					flgTrovata = true;
					break;
				}
			}

			if (!flgTrovata) {
				apiErrors.add(MsgProdis.PROPCTE0009.getError());
			}
		}

		if (tipoDichiarante == ConstantsProdis.COD_DICHIARANTE_C) {
			Provincia provincia = compensazioniDad.getProvinciaById(compensazioni.getProvincia().getId());
			if (provincia != null) {
				Long idRegione = provincia.getRegione().getId();
				Long idRegionePerCuiCompensare = null;
				Long idProvinciaDaElencoProspettoProvincia = null;
				for (ProRProspettoProvincia datiProv : elencoProspettiProvincia) {
					if (datiProv.getIdProspettoProv() == compensazioni.getIdProspettoProv().longValue()) {
						idProvinciaDaElencoProspettoProvincia = datiProv.getProTProvincia().getIdTProvincia();
						break;
					}

				}
				if (idProvinciaDaElencoProspettoProvincia != null) {
					Provincia provinciaCompensazione = compensazioniDad
							.getProvinciaById(idProvinciaDaElencoProspettoProvincia);
					if (provinciaCompensazione != null) {
						idRegionePerCuiCompensare = provinciaCompensazione.getRegione().getId();
						if (idRegione != null && idRegionePerCuiCompensare != null
								&& !idRegione.equals(idRegionePerCuiCompensare)) {
							apiErrors.add(MsgProdis.PROPCTE0010.getError());
						}
					}
				}

			}
		}

		if (compensazioni.getCategoriaCompensazione().equalsIgnoreCase(ConstantsProdis.CATEGORIA_COMPENSAZIONE_E)) {
			if (compensazioni.getCategoriaSoggetto().equalsIgnoreCase(ConstantsProdis.CATEGORIA_SOGGETTO_D)) {
				for (ProRProspettoProvincia datiProv : elencoProspettiProvincia) {
					if (datiProv.getIdProspettoProv() == compensazioni.getIdProspettoProv().longValue()) {
						RiepilogoProvinciale rp = compensazioniDad
								.getRiepilogoProvincialeByidProspettoProv(compensazioni.getIdProspettoProv());
						if (rp != null) {
							if (rp.getNumPosizioniEsonerate().compareTo(new BigDecimal(0)) == 1) {
								apiErrors.add(MsgProdis.PROPCTE0011.getError());
							}

						}
					}
				}
			}
		}

		//
		if (apiErrors != null && apiErrors.size() > 0) {
			return;
		}

		List<ProDProvCompensazioni> elencoCompensazioni = compensazioniDad
				.getElencoCompensazioniByIdProspettoProv(compensazioni.getIdProspettoProv());
		if (elencoCompensazioni != null && elencoCompensazioni.size() > 0) {
			for (ProDProvCompensazioni altraCompensazione : elencoCompensazioni) {
				if (compensazioni.getId() == null
						|| compensazioni.getId().longValue() != altraCompensazione.getIdCompensazione()) {
					if (compensazioni.getIdProspettoProv().equals(altraCompensazione.getIdProspettoProv().longValue())
							&& compensazioni.getCategoriaSoggetto().equals(altraCompensazione.getCategoriaSoggetto())) {

						if (!compensazioni.getCategoriaCompensazione()
								.equals(altraCompensazione.getCategoriaCompensazione())) {
							apiErrors.add(MsgProdis.PROPCTE0012.getError());
							break;
						}
						if (ProdisSrvUtil.isNotVoid(compensazioni.getCfAziendaAppartenAlGruppo())
								&& ProdisSrvUtil.isNotVoid(altraCompensazione.getCfAziendaAppartenAlGruppo())
								&& compensazioni.getCfAziendaAppartenAlGruppo()
										.equalsIgnoreCase(altraCompensazione.getCfAziendaAppartenAlGruppo())
								&& compensazioni.getProvincia().getCodProvinciaMin()
										.equals(altraCompensazione.getProTProvincia().getCodProvinciaMin())) {
							apiErrors.add(MsgProdis.PROPCTE0013.getError());
							break;
						}
						if (ProdisSrvUtil.isVoid(compensazioni.getCfAziendaAppartenAlGruppo())
								&& ProdisSrvUtil.isVoid(altraCompensazione.getCfAziendaAppartenAlGruppo())
								&& compensazioni.getProvincia().getCodProvinciaMin()
										.equals(altraCompensazione.getProTProvincia().getCodProvinciaMin())) {
							apiErrors.add(MsgProdis.PROPCTE0013.getError());
							break;
						}
					}
					if (ProdisSrvUtil.isNotVoid(compensazioni.getCfAziendaAppartenAlGruppo())
							&& ProdisSrvUtil.isNotVoid(altraCompensazione.getCfAziendaAppartenAlGruppo())) {
						if (!compensazioni.getCategoriaCompensazione()
								.equals(altraCompensazione.getCategoriaCompensazione())
								&& compensazioni.getCategoriaSoggetto()
										.equals(altraCompensazione.getCategoriaSoggetto())) {
							apiErrors.add(MsgProdis.PROPCTE0014.getError());
							break;
						}

					}
				}
			}
		}
	}

	public void validaConsistenzaDati(Long idProspetto, List<ApiError> apiErrors) {
		int totaleD = 0;
		int totaleC = 0;

		List<ProRProspettoProvincia> elencoProspettoProvincia = compensazioniDad
				.getElencoProspettiProvinciaByIdProspetto(idProspetto);

		if (elencoProspettoProvincia != null && elencoProspettoProvincia.size() > 0) {

			for (ProRProspettoProvincia prosProv : elencoProspettoProvincia) {
				List<ProDProvCompensazioni> elencoCompensazioni = compensazioniDad
						.getElencoCompensazioniByIdProspettoProv(prosProv.getIdProspettoProv());

				for (ProDProvCompensazioni comp : elencoCompensazioni) {
					if (ProdisSrvUtil.isVoid(comp.getCfAziendaAppartenAlGruppo())) {

						Integer eccedenze = null;
						Integer riduzioni = null;

						if (comp.getCategoriaCompensazione() != null
								&& comp.getCategoriaCompensazione().equals(ConstantsProdis.CATEGORIA_COMPENSAZIONE_E)) {
							eccedenze = comp.getnLavoratori().intValue();
							riduzioni = null;

						} else if (comp.getCategoriaCompensazione() != null
								&& comp.getCategoriaCompensazione().equals(ConstantsProdis.CATEGORIA_COMPENSAZIONE_R)) {
							eccedenze = null;
							riduzioni = comp.getnLavoratori().intValue();
						}
						if (comp.getCategoriaSoggetto().equals(ConstantsProdis.CATEGORIA_SOGGETTO_D)) {
							totaleD += (nvl(eccedenze) - nvl(riduzioni));
						} else if (comp.getCategoriaSoggetto().equals(ConstantsProdis.CATEGORIA_SOGGETTO_C)) {
							totaleC += (nvl(eccedenze) - nvl(riduzioni));
						}
					}
				}
			}
		}

		String soggetto = "";

		if (totaleD != 0) {
			soggetto = ConstantsProdis.DES_CATEGORIA_SOGGETTO_D;
		}

		if (totaleC != 0) {
			soggetto = ConstantsProdis.DES_CATEGORIA_SOGGETTO_C;
		}
		String messaggio = "Le eccedenze relative alla categoria soggetto " + soggetto
				+ " non sono compensate dalle riduzioni.";
		if (!"".equals(soggetto)) {
			apiErrors.add(new ApiError("PRO-PCT-E-00015", messaggio));
		}

	}
}
