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
import java.util.Date;
import java.util.List;

import it.csi.prodis.prodisweb.ejb.business.be.dad.PostiLavoroDispDad;
import it.csi.prodis.prodisweb.ejb.entity.ProTStatiEsteri;
import it.csi.prodis.prodisweb.lib.dto.ApiError;
import it.csi.prodis.prodisweb.lib.dto.common.DecodificaGenerica;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Comune;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Istat2001livello5;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.StatiEsteri;
import it.csi.prodis.prodisweb.lib.dto.error.MsgProdis;
import it.csi.prodis.prodisweb.lib.dto.prospetto.PostiLavoroDisp;

public class ValidatorPostiLavoroDisponibili  extends ValidatorUtil{


	PostiLavoroDispDad postiLavoroDispDad;

	public ValidatorPostiLavoroDisponibili(PostiLavoroDispDad postiLavoroDispDad) {
		super();
		this.postiLavoroDispDad = postiLavoroDispDad;
	}
	private Long recuperaIdQualificaIstatDaDecodifica(String codice, String descrizione) {

		Long id = null;

		List<DecodificaGenerica> decode = postiLavoroDispDad.getIstatValidi(codice, descrizione);
		if (decode !=  null && decode.size() == 1) {
			id = decode.get(0).getIdDecodifica();
		}

		return id;
	}

	private Long recuperaIdComuneDaDecodifica(String codice, String descrizione) {

		Long id = null;

		List<DecodificaGenerica> decode = postiLavoroDispDad.getComuneValido(codice, descrizione);
		if (decode !=  null && decode.size() == 1) {
			id = decode.get(0).getIdDecodifica();
		}

		return id;
	}
	private Long recuperaIdStatoEsteroDaDecodifica(String codice, String descrizione) {

		Long id = null;

		List<DecodificaGenerica> decode = postiLavoroDispDad.getStatoEsteroValido(codice, descrizione);
		if (decode !=  null && decode.size() == 1) {
			id = decode.get(0).getIdDecodifica();
		}

		return id;
	}
	public void valida(PostiLavoroDisp postiLavoroDisp, List<ApiError> apiErrors) {
		if (postiLavoroDisp.getnPosti() == null ) {
			apiErrors.add(MsgProdis.PROPDIE0001.getError());
		} else {
			if (postiLavoroDisp.getnPosti().intValue() == 0 || postiLavoroDisp.getnPosti().intValue() < 0) {
				apiErrors.add(MsgProdis.PROPDIE0002.getError());
			}
		}
		if (postiLavoroDisp.getCategoriaAssunzione() == null || "".equals(postiLavoroDisp.getCategoriaAssunzione())) {
			apiErrors.add(MsgProdis.PROPDIE0003.getError());
		}
		if (postiLavoroDisp.getCategoriaSoggetto() == null || "".equals(postiLavoroDisp.getCategoriaSoggetto())) {
			apiErrors.add(MsgProdis.PROPDIE0004.getError());
		}
		if (postiLavoroDisp.getDescCapacitaRichiesteContr() == null ||  "".equals(postiLavoroDisp.getDescCapacitaRichiesteContr())) {
			apiErrors.add(MsgProdis.PROPDIE0005.getError());
		}
		if (postiLavoroDisp.getDescMansione() == null ||  "".equals(postiLavoroDisp.getDescMansione())) {
			apiErrors.add(MsgProdis.PROPDIE0006.getError());
		}
		if (postiLavoroDisp.getFlgPresenzaBarriereArchite() == null ||  "".equals(postiLavoroDisp.getFlgPresenzaBarriereArchite())) {
			apiErrors.add(MsgProdis.PROPDIE0007.getError());
		}
		if (postiLavoroDisp.getFlgTurniNotturni() == null ||  "".equals(postiLavoroDisp.getFlgTurniNotturni())) {
			apiErrors.add(MsgProdis.PROPDIE0008.getError());
		}
		if (postiLavoroDisp.getFlgRaggiungibMezziPubblici() == null ||  "".equals(postiLavoroDisp.getFlgRaggiungibMezziPubblici())) {
			apiErrors.add(MsgProdis.PROPDIE0009.getError());
		}

		// 
		if (postiLavoroDisp.getIstat2001livello5() == null 
				|| (postiLavoroDisp.getIstat2001livello5().getCodIstat2001livello5Min() == null || "".equals(postiLavoroDisp.getIstat2001livello5().getCodIstat2001livello5Min()))
				|| (postiLavoroDisp.getIstat2001livello5().getDsComIstat2001livello5() == null || "".equals(postiLavoroDisp.getIstat2001livello5().getDsComIstat2001livello5()))
				) {
			apiErrors.add(MsgProdis.PROPDIE0010.getError());
		} else {
			if (postiLavoroDisp.getIstat2001livello5() != null 
					&& ProdisSrvUtil.isNotVoid(postiLavoroDisp.getIstat2001livello5().getCodIstat2001livello5Min()) 
					&& ProdisSrvUtil.isNotVoid(postiLavoroDisp.getIstat2001livello5().getDsComIstat2001livello5())){

				if (postiLavoroDisp.getIstat2001livello5().getId() == null) {
					Long id = recuperaIdQualificaIstatDaDecodifica(postiLavoroDisp.getIstat2001livello5().getCodIstat2001livello5Min(), postiLavoroDisp.getIstat2001livello5().getDsComIstat2001livello5());
					if (id != null) {
						postiLavoroDisp.getIstat2001livello5().setId(id);
					} else {
						apiErrors.add(MsgProdis.PROPDIE0025.getError());
					}
				}

				List<DecodificaGenerica> elencoDecodifica = postiLavoroDispDad.getIstat(postiLavoroDisp.getIstat2001livello5().getCodIstat2001livello5Min(), postiLavoroDisp.getIstat2001livello5().getDsComIstat2001livello5());
				if (elencoDecodifica == null || elencoDecodifica.size() == 0) {
					apiErrors.add(MsgProdis.PROPDIE0011.getError());
				} else {
					ArrayList<Istat2001livello5> elencoIstat = new ArrayList<Istat2001livello5>();
					for (DecodificaGenerica deco : elencoDecodifica) {
						if (deco.getCodDecodifica().equalsIgnoreCase(postiLavoroDisp.getIstat2001livello5().getCodIstat2001livello5Min())) {
							Istat2001livello5 istat = postiLavoroDispDad.getIstatById(deco.getIdDecodifica());
							elencoIstat.add(istat); 
						}
					}
					if (elencoIstat == null || elencoIstat.size() == 0) {
						apiErrors.add(MsgProdis.PROPDIE0011.getError());
					}
					if (elencoIstat != null &&  elencoIstat.size() > 1) {
						apiErrors.add(MsgProdis.PROPDIE0012.getError());
					}
					if (elencoIstat != null && elencoIstat.size() ==1) {
						Istat2001livello5 trovato = elencoIstat.get(0);
						if (!ProdisSrvUtil.isDecodificaValida(new Date(), trovato.getDtInizio(), trovato.getDtFine())) {
							apiErrors.add(MsgProdis.PROPDIE0013.getError());
						}
					}
				}
			}
		}

		if ((postiLavoroDisp.getComune() == null || postiLavoroDisp.getComune().getId() == null 
				|| postiLavoroDisp.getComune().getCodComuneMin() == null 
				|| "".equalsIgnoreCase(postiLavoroDisp.getComune().getCodComuneMin())
				|| postiLavoroDisp.getComune().getDsProTComune() == null 
				|| "".equalsIgnoreCase(postiLavoroDisp.getComune().getDsProTComune()))
				&& 
				(postiLavoroDisp.getStatiEsteri() == null || postiLavoroDisp.getStatiEsteri().getId()==null
				|| postiLavoroDisp.getStatiEsteri().getCodNazioneMin() == null
				|| "".equalsIgnoreCase(postiLavoroDisp.getStatiEsteri().getCodNazioneMin())
				|| postiLavoroDisp.getStatiEsteri().getDsStatiEsteri() == null
				|| "".equalsIgnoreCase(postiLavoroDisp.getStatiEsteri().getDsStatiEsteri()))) {
			apiErrors.add(MsgProdis.PROPDIE0014.getError());
		/*if (postiLavoroDisp.getComune() == null || postiLavoroDisp.getStatiEsteri() == null
				|| (postiLavoroDisp.getComune() != null 
				&& postiLavoroDisp.getStatiEsteri() != null
				&& (postiLavoroDisp.getComune().getCodComuneMin() == null || "".equalsIgnoreCase(postiLavoroDisp.getComune().getCodComuneMin()))
				&& (postiLavoroDisp.getComune().getDsProTComune() == null || "".equalsIgnoreCase(postiLavoroDisp.getComune().getDsProTComune()))
				&& (postiLavoroDisp.getStatiEsteri().getCodNazioneMin() == null ||  "".equalsIgnoreCase(postiLavoroDisp.getStatiEsteri().getCodNazioneMin()))
				&& (postiLavoroDisp.getStatiEsteri().getDsStatiEsteri() == null ||  "".equalsIgnoreCase(postiLavoroDisp.getStatiEsteri().getDsStatiEsteri())))) {
			apiErrors.add(MsgProdis.PROPDIE0014.getError());*/
		} else if (postiLavoroDisp.getComune() != null 
				&& postiLavoroDisp.getStatiEsteri() != null
				&& postiLavoroDisp.getComune().getCodComuneMin() != null && !"".equalsIgnoreCase(postiLavoroDisp.getComune().getCodComuneMin())
				&& postiLavoroDisp.getComune().getDsProTComune() != null && !"".equalsIgnoreCase(postiLavoroDisp.getComune().getDsProTComune())
				&& postiLavoroDisp.getStatiEsteri().getCodNazioneMin() != null && !"".equalsIgnoreCase(postiLavoroDisp.getStatiEsteri().getCodNazioneMin())
				&& postiLavoroDisp.getStatiEsteri().getDsStatiEsteri() != null && !"".equalsIgnoreCase(postiLavoroDisp.getStatiEsteri().getDsStatiEsteri())) {
			apiErrors.add(MsgProdis.PROPDIE0015.getError());
		} else if (postiLavoroDisp.getComune() != null  && postiLavoroDisp.getStatiEsteri() != null){
			String codComune =  postiLavoroDisp.getComune().getCodComuneMin();
			String desComune = postiLavoroDisp.getComune().getDsProTComune();
			String codNazione =  postiLavoroDisp.getStatiEsteri().getCodNazioneMin();
			String desNazione = postiLavoroDisp.getStatiEsteri().getDsStatiEsteri();
			if (postiLavoroDisp.getComune() != null
					&& 
					(ProdisSrvUtil.isVoid(codComune) && ProdisSrvUtil.isNotVoid(desComune))
					|| 
					(ProdisSrvUtil.isNotVoid(codComune) && ProdisSrvUtil.isVoid(desComune))
					|| 
					(ProdisSrvUtil.isVoid(codNazione) && ProdisSrvUtil.isNotVoid(desNazione))
					|| 
					(ProdisSrvUtil.isNotVoid(codNazione) && ProdisSrvUtil.isVoid(desNazione))
					) {
				apiErrors.add(MsgProdis.PROPDIE0016.getError());	
			}

			if(postiLavoroDisp.getComune()!= null && ProdisSrvUtil.isNotVoid(postiLavoroDisp.getComune().getCodComuneMin())
					&& ProdisSrvUtil.isNotVoid(postiLavoroDisp.getComune().getDsProTComune())) {
				if (postiLavoroDisp.getComune().getId() == null 
						&& ProdisSrvUtil.isNotVoid(postiLavoroDisp.getComune().getCodComuneMin())
						&& ProdisSrvUtil.isNotVoid(postiLavoroDisp.getComune().getDsProTComune())) {
					Long id = recuperaIdComuneDaDecodifica(postiLavoroDisp.getComune().getCodComuneMin(), postiLavoroDisp.getComune().getDsProTComune());
					if (id != null) {
						postiLavoroDisp.getComune().setId(id);
					} else {
						apiErrors.add(MsgProdis.PROPDIE0026.getError());
					}
				}
				if (ProdisSrvUtil.isNotVoid(postiLavoroDisp.getComune().getCodComuneMin())
						&& ProdisSrvUtil.isNotVoid(postiLavoroDisp.getComune().getDsProTComune()) ) {

					List<DecodificaGenerica> elencoDecodificaComune = postiLavoroDispDad.getComune(postiLavoroDisp.getComune().getCodComuneMin(), postiLavoroDisp.getComune().getDsProTComune());
					if (elencoDecodificaComune == null || elencoDecodificaComune.size() == 0) {
						apiErrors.add(MsgProdis.PROPDIE0019.getError());
					} else {
						ArrayList<Comune> elencoComuni = new ArrayList<Comune>();
						for (DecodificaGenerica deco : elencoDecodificaComune) {
							if (deco.getCodDecodifica().equalsIgnoreCase(postiLavoroDisp.getComune().getCodComuneMin())) {
								Comune comune = postiLavoroDispDad.getComuneById(deco.getIdDecodifica());
								elencoComuni.add(comune); 
							}
						}
						if (elencoComuni == null || elencoComuni.size() == 0) {
							apiErrors.add(MsgProdis.PROPDIE0019.getError());
						}
						if (elencoComuni != null &&  elencoComuni.size() > 1) {
							apiErrors.add(MsgProdis.PROPDIE0020.getError());
						}
						if (elencoComuni != null && elencoComuni.size() ==1) {
							Comune trovato = elencoComuni.get(0);
							if (!ProdisSrvUtil.isDecodificaValida(new Date(), trovato.getDtInizio(), trovato.getDtFine())) {
								apiErrors.add(MsgProdis.PROPDIE0021.getError());
							}
						}

					}
				}
			} else {
				if (postiLavoroDisp.getStatiEsteri().getId() == null 
						&& ProdisSrvUtil.isNotVoid(postiLavoroDisp.getStatiEsteri().getCodNazioneMin()) 
						&& ProdisSrvUtil.isNotVoid(postiLavoroDisp.getStatiEsteri().getDsStatiEsteri())) {
					Long id = recuperaIdStatoEsteroDaDecodifica(postiLavoroDisp.getStatiEsteri().getCodNazioneMin(), postiLavoroDisp.getStatiEsteri().getDsStatiEsteri());
					if (id != null) {
						postiLavoroDisp.getStatiEsteri().setId(id);
					} else {
						apiErrors.add(MsgProdis.PROPDIE0027.getError());
					}
				}

				if (ProdisSrvUtil.isNotVoid(postiLavoroDisp.getStatiEsteri().getCodNazioneMin()) 
						&& ProdisSrvUtil.isNotVoid(postiLavoroDisp.getStatiEsteri().getDsStatiEsteri())) {


					List<ProTStatiEsteri> elencoDecodificaNazione = postiLavoroDispDad.getNazione(postiLavoroDisp.getStatiEsteri().getCodNazioneMin(), postiLavoroDisp.getStatiEsteri().getDsStatiEsteri());
					if (elencoDecodificaNazione == null || elencoDecodificaNazione.size() == 0) {
						apiErrors.add(MsgProdis.PROPDIE0022.getError());
					} else {
						ArrayList<StatiEsteri> elencoNazioni = new ArrayList<StatiEsteri>();
						for (ProTStatiEsteri deco : elencoDecodificaNazione) {
							if (deco.getCodNazioneMin().equalsIgnoreCase(postiLavoroDisp.getStatiEsteri().getCodNazioneMin())) {
								StatiEsteri nazione = postiLavoroDispDad.getStatoEsteroById(deco.getIdTStatiEsteri());
								elencoNazioni.add(nazione); 
							}
						}
						if (elencoNazioni == null || elencoNazioni.size() == 0) {
							apiErrors.add(MsgProdis.PROPDIE0022.getError());
						}
						if (elencoNazioni != null &&  elencoNazioni.size() > 1) {
							apiErrors.add(MsgProdis.PROPDIE0023.getError());
						}
						if (elencoNazioni != null && elencoNazioni.size() ==1) {
							StatiEsteri trovato = elencoNazioni.get(0);
							if (!ProdisSrvUtil.isDecodificaValida(new Date(), trovato.getDtInizio(), trovato.getDtFine())) {
								apiErrors.add(MsgProdis.PROPDIE0024.getError());
							}
						}
					}
				}
			}
		}

	}
}
