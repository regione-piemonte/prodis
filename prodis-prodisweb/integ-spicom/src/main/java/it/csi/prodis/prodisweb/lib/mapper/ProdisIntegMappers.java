/*-
 * ========================LICENSE_START=================================
 * PRODIS BackEnd - INTEGRATION SPICOM submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodisweb.lib.mapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import it.csi.prodis.prodisweb.lib.dto.common.Ruolo;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Regione;
import it.csi.prodis.prodisweb.lib.dto.prospetto.AssPubbliche;
import it.csi.prodis.prodisweb.lib.dto.prospetto.CategorieEscluse;
import it.csi.prodis.prodisweb.lib.dto.prospetto.DatiProvinciali;
import it.csi.prodis.prodisweb.lib.dto.prospetto.LavoratoriInForza;
import it.csi.prodis.prodisweb.lib.dto.prospetto.PartTime;
import it.csi.prodis.prodisweb.lib.dto.prospetto.PostiLavoroDisp;
import it.csi.prodis.prodisweb.lib.dto.prospetto.Prospetto;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProspettoProvincia;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProvCompensazioni;
import it.csi.prodis.prodisweb.lib.dto.prospetto.ProvIntermittenti;
import it.csi.prodis.prodisweb.lib.utils.Format;
import it.csi.prodis.prodisweb.spicom.AppConstants;
import it.csi.prodis.prodisweb.spicom.ProfConstants;
import it.csi.spicom.dto.Assunzionipubblicaselezione;
import it.csi.spicom.dto.Categorieescluse;
import it.csi.spicom.dto.Compensazioni;
import it.csi.spicom.dto.ComunicazioneProspettoDisabiliDTO;
import it.csi.spicom.dto.Convenzione;
import it.csi.spicom.dto.DatiAggiuntiviComunicazioneDTO;
import it.csi.spicom.dto.DatiAggiuntiviTracciatoDTO;
import it.csi.spicom.dto.DatiInvioDTO;
import it.csi.spicom.dto.Datiaziendali;
import it.csi.spicom.dto.Datiprospetto;
import it.csi.spicom.dto.Datiprovinciali;
import it.csi.spicom.dto.Dettagliointermittenti;
import it.csi.spicom.dto.Dettaglioparttime;
import it.csi.spicom.dto.Dichiarante;
import it.csi.spicom.dto.Elencoriepilogativo;
import it.csi.spicom.dto.Esoneri;
import it.csi.spicom.dto.Esoneri60PerMille;
import it.csi.spicom.dto.Gradualita;
import it.csi.spicom.dto.Gradualitaprov;
import it.csi.spicom.dto.Lavoratore;
import it.csi.spicom.dto.Lavoratoricomputabili;
import it.csi.spicom.dto.Lavoratoritempopienoparttime;
import it.csi.spicom.dto.Personale;
import it.csi.spicom.dto.PersonaleNonDipendente;
import it.csi.spicom.dto.Postilavoro;
import it.csi.spicom.dto.ProspettoGenerale;
import it.csi.spicom.dto.Quadro1;
import it.csi.spicom.dto.Quadro2;
import it.csi.spicom.dto.Quadro3;
import it.csi.spicom.dto.Rapportolavoro;
import it.csi.spicom.dto.Referente;
import it.csi.spicom.dto.Referenteminimo;
import it.csi.spicom.dto.Riepilogonazionale;
import it.csi.spicom.dto.Sedelegale;
import it.csi.spicom.dto.Sederiferimentominimo;
import it.csi.spicom.dto.Sospensione;
import it.csi.spicom.dto.Sospensionemobilita;
import it.csi.spicom.util.TUConstants;

/**
 * Mappers for Spicom integration
 */
public final class ProdisIntegMappers {

	public static final ComunicazioneProspettoDisabiliDTO toComunicazioneSpicom(Prospetto prospetto, Ruolo ruolo) {

		ComunicazioneProspettoDisabiliDTO comProspetto = new ComunicazioneProspettoDisabiliDTO();

		ProspettoGenerale prospettoGeneraleDst = new ProspettoGenerale();

		prospettoGeneraleDst.setCodicecomunicazioneprecedente(prospetto.getCodiceComunicazionePreced());

		prospettoGeneraleDst.setDataInvio(prospetto.getDataInvio());
		String cfStudioProfessionale = prospetto.getCfStudioProfessionale();

		prospettoGeneraleDst.setEmailDelegato(prospetto.getEmailSoggettoComunicazione());

		StringBuffer numeroProtocolloReversed = new StringBuffer(prospetto.getNumeroProtocollo().toString()).reverse();
		while (numeroProtocolloReversed != null && numeroProtocolloReversed.length() < 8) {
			numeroProtocolloReversed.append("0");
		}

		prospettoGeneraleDst.setProtocollo(numeroProtocolloReversed.reverse().toString());
		prospettoGeneraleDst.setTipocomunicazione(prospetto.getComunicazione().getCodComunicazione());

		boolean isComunicazioneDatoreLavoro = true;
		if (prospetto.getSoggetti() != null && prospetto.getSoggetti().getCodSoggetti() != null) {
			isComunicazioneDatoreLavoro = false;
		}

		if (ruolo.getRuolo().toUpperCase().startsWith(ProfConstants.CONSULENTE_RESPONSABILE_COMONL_DESC.toUpperCase())
				|| (ruolo.getRuolo().toUpperCase().startsWith(ProfConstants.AMMINISTRATORE_PRODIS_DESC.toUpperCase())
						&& !isComunicazioneDatoreLavoro)) {

			if (cfStudioProfessionale != null && !"".equals(cfStudioProfessionale)) {
				prospettoGeneraleDst.setDelegato(cfStudioProfessionale.toUpperCase());
				prospettoGeneraleDst.setTipoDelegato(prospetto.getSoggetti().getCodSoggetti());
			}
			
		}
		
		if (ruolo.getRuolo().toUpperCase()
				.startsWith(ProfConstants.CONSULENTE_RESPONSABILE_COMONL_DESC.toUpperCase())) {
			prospettoGeneraleDst.setTipoDelegato(prospetto.getSoggetti().getCodSoggetti());
		}

		prospettoGeneraleDst.setVersione(TUConstants.VERSIONE_PROSPETTO);
		Quadro1 quadro1Dst = new Quadro1();
		prospettoGeneraleDst.setQuadro1(quadro1Dst);

		Datiprospetto datiprospettoDst = new Datiprospetto();
		quadro1Dst.setDatiprospetto(datiprospettoDst);

		if (prospetto.getCategoriaAzienda() != null) {
			datiprospettoDst.setCategoriaAzienda(prospetto.getCategoriaAzienda().getCodCategoriaAzienda());
		}

		datiprospettoDst.setCapogruppo(prospetto.getDatiAzienda().getFlgProspettoDaCapogruppo());
		datiprospettoDst.setCfcapogruppo(prospetto.getDatiAzienda().getCfCapogruppo());
		datiprospettoDst.setFlgCapogruppoEstero(prospetto.getDatiAzienda().getFlgCapogruppoEstero() != null
				? prospetto.getDatiAzienda().getFlgCapogruppoEstero()
				: "N");
		datiprospettoDst.setDataRiferimento(prospetto.getDataRiferimentoProspetto());
		datiprospettoDst.setNlavoratoriNazionali(Format.toStrInt(prospetto.getNumLavorInForzaNazionale()));
		datiprospettoDst.setNessunaAssunzioneAggiuntiva(prospetto.getFlgNessunaAssunzioneAggiun());

		if ("N".equalsIgnoreCase(prospetto.getFlgNessunaAssunzioneAggiun())) {

			datiprospettoDst.setDataPrimaAssunzione(prospetto.getDataPrimaAssunzione());

			if (prospetto.getDataSecondaAssunzione() != null) {
				datiprospettoDst.setDataSecondaAssunzione(prospetto.getDataSecondaAssunzione());
			}
		}

		Datiaziendali datiaziendaliDst = new Datiaziendali();
		quadro1Dst.setDatiaziendali(datiaziendaliDst);

		Dichiarante dichiaranteDst = new Dichiarante();
		datiaziendaliDst.setDichiarante(dichiaranteDst);

		dichiaranteDst.setCcnl(prospetto.getDatiAzienda().getCcnl().getCodCcnlMin());
		dichiaranteDst.setCodicefiscale(prospetto.getDatiAzienda().getCfAzienda());
		dichiaranteDst.setDenominazione(prospetto.getDatiAzienda().getDenominazioneDatoreLavoro());
		dichiaranteDst.setSettore(prospetto.getDatiAzienda().getAtecofin().getCodAtecofinMin());
		dichiaranteDst.setTipologiadichiarante(prospetto.getDatiAzienda().getDichiarante().getCodDichiarante());

		Referente referenteDst = new Referente();
		datiaziendaliDst.setReferente(referenteDst);

		referenteDst.setCap(prospetto.getDatiAzienda().getCapReferente());
		referenteDst.setCodicefiscale(prospetto.getDatiAzienda().getCfReferente());
		referenteDst.setCognome(prospetto.getDatiAzienda().getCognomeReferente());
		referenteDst.setNome(prospetto.getDatiAzienda().getNomeReferente());
		referenteDst.setComune(prospetto.getDatiAzienda().getComune().getCodComuneMin());
		referenteDst.setEmail(prospetto.getDatiAzienda().getEmailReferente());
		referenteDst.setFax(prospetto.getDatiAzienda().getFaxReferente());
		referenteDst.setIndirizzo(prospetto.getDatiAzienda().getIndirizzoReferente());
		referenteDst.setTelefono(prospetto.getDatiAzienda().getTelefonoReferente());

		Sedelegale sedelegaleDst = new Sedelegale();
		datiaziendaliDst.setSedelegale(sedelegaleDst);

		sedelegaleDst.setCapsedelegale(prospetto.getDatiAzienda().getSedeLegale().getCapSede());
		sedelegaleDst.setComunesedelegale(prospetto.getDatiAzienda().getSedeLegale().getComune().getCodComuneMin());
		sedelegaleDst.setEmail(prospetto.getDatiAzienda().getSedeLegale().getEmail());
		sedelegaleDst.setFax(prospetto.getDatiAzienda().getSedeLegale().getFax());
		sedelegaleDst.setIndirizzosedelegale(prospetto.getDatiAzienda().getSedeLegale().getIndirizzo());
		sedelegaleDst.setTelefono(prospetto.getDatiAzienda().getSedeLegale().getTelefono());

		if (prospetto.getProspettoGradualita() != null) {
			Gradualita gradualitaDst = new Gradualita();
			gradualitaDst.setAssunzioninondisabiliprimaditrasformazione(
					Format.toStrInt(prospetto.getProspettoGradualita().getnAssunzioniLavPreTrasf()));
			gradualitaDst.setDataatto(prospetto.getProspettoGradualita().getDataAtto());
			gradualitaDst.setDatatrasformazione(prospetto.getProspettoGradualita().getDataTrasformazione());
			gradualitaDst.setEstremiatto(prospetto.getProspettoGradualita().getEstremiAtto());
			gradualitaDst.setPercentuale(Format.toStrInt(prospetto.getProspettoGradualita().getPercentuale()));
			quadro1Dst.setGradualita(gradualitaDst);
		}

		if (prospetto.getAssPubbliche() != null && prospetto.getAssPubbliche().size() > 0) {
			Assunzionipubblicaselezione[] apsArrayDst = new Assunzionipubblicaselezione[prospetto.getAssPubbliche()
					.size()];

			int i = 0;
			for (AssPubbliche ass : prospetto.getAssPubbliche()) {
				Assunzionipubblicaselezione assunzionipubblicaselezioneDst = new Assunzionipubblicaselezione();
				Regione regione = ass.getRegione();
				if (regione != null) {
					assunzionipubblicaselezioneDst.setRegione(regione.getCodRegioneMin());
				}
				assunzionipubblicaselezioneDst.setSaldodisabili(Format.toStrInt(ass.getSaldoDisabili()));
				assunzionipubblicaselezioneDst.setSaldoexart18(Format.toStrInt(ass.getSaldoExArt18()));
				assunzionipubblicaselezioneDst.setNote(ass.getDsNote());

				apsArrayDst[i] = assunzionipubblicaselezioneDst;
				i++;
			}

			quadro1Dst.setAssunzionipubblicaselezione(apsArrayDst);
		}

		Sospensionemobilita sospensionemobilitaDst = new Sospensionemobilita();
		quadro1Dst.setSospensionemobilita(sospensionemobilitaDst);

		sospensionemobilitaDst.setSospensionepermobilita(prospetto.getFlgSospensionePerMobilita());
		sospensionemobilitaDst.setDataFineSospensione(prospetto.getdFineSospensioneQ1());

		ArrayList<Quadro2> quadri2 = new ArrayList<Quadro2>();

		for (ProspettoProvincia proPorv : prospetto.getProspettoProvincias()) {
			Quadro2 quadro2Dst = new Quadro2();
			quadri2.add(quadro2Dst);

			DatiProvinciali datiProv = proPorv.getDatiProvinciali();

			if (datiProv.getProvCompensazionis() != null && datiProv.getProvCompensazionis().size() > 0) {
				ArrayList<Compensazioni> compensazioniArrayDst = new ArrayList<Compensazioni>();

				for (ProvCompensazioni provComp : datiProv.getProvCompensazionis()) {
					Compensazioni compensazioniDst = new Compensazioni();
					compensazioniArrayDst.add(compensazioniDst);

					compensazioniDst.setCodicefiscaleaziendagruppo(provComp.getCfAziendaAppartenAlGruppo());
					compensazioniDst.setCategoriacomp(provComp.getCategoriaCompensazione());
					compensazioniDst.setCategoriasogg(provComp.getCategoriaSoggetto());
					compensazioniDst.setDataatto(provComp.getDataAtto());
					compensazioniDst.setEstremiatto(provComp.getEstremiAtto());
					compensazioniDst.setNumero(Format.toStrInt(provComp.getnLavoratori()));
					compensazioniDst.setProvincia(provComp.getProvincia().getCodProvinciaMin());
					if(provComp.getStatoConcessione() != null) {
						compensazioniDst.setStato(provComp.getStatoConcessione().getCodStatoConcessione());
					}
				}

				quadro2Dst.setCompensazioni(compensazioniArrayDst.toArray(new Compensazioni[0]));
			}

			if (datiProv.getProvConvenzione() != null) {
				Convenzione convenzioneDst = new Convenzione();
				quadro2Dst.setConvenzione(convenzioneDst);

				convenzioneDst.setDataatto(datiProv.getProvConvenzione().getDataAtto());
				convenzioneDst.setDatastipula(datiProv.getProvConvenzione().getDataStipula());
				convenzioneDst.setDataconcessione(datiProv.getProvConvenzione().getDataScadenza());
				convenzioneDst.setEstremiatto(datiProv.getProvConvenzione().getEstremiAtto());
				convenzioneDst.setStato(datiProv.getProvConvenzione().getStatoConcessione().getCodStatoConcessione());

				if (datiProv.getProvConvenzione().getAssunzioneProtetta() != null) {
					convenzioneDst.setTipoconvenzione(
							datiProv.getProvConvenzione().getAssunzioneProtetta().getCodAssunzioneProtetta());
				}

				if (datiProv.getProvConvenzione().getNumLavPrevConvQ2() == null) {
					convenzioneDst.setNumLavoratoriPrevisti("0");
				} else {
					convenzioneDst.setNumLavoratoriPrevisti(
							Format.toStrInt(datiProv.getProvConvenzione().getNumLavPrevConvQ2()));
				}
			}

			Datiprovinciali datiprovincialiDst = new Datiprovinciali();
			quadro2Dst.setDatiprovinciali(datiprovincialiDst);

			datiprovincialiDst.setNposticentralinisti(Format.toStrInt(datiProv.getnPostiPrevCentraliNonved()));
			datiprovincialiDst.setNpostimassofisioterapisti(Format.toStrInt(datiProv.getnPostiPrevMassofisNonved()));
			datiprovincialiDst.setProvincia(proPorv.getProvincia().getCodProvinciaMin());

			Referenteminimo referenteminimoDst = new Referenteminimo();
			datiprovincialiDst.setReferente(referenteminimoDst);

			referenteminimoDst.setCognome(datiProv.getProspettoProvSede().getCognomeReferente());
			referenteminimoDst.setNome(datiProv.getProspettoProvSede().getNomeReferente());

			Sederiferimentominimo sederiferimentominimoDst = new Sederiferimentominimo();
			datiprovincialiDst.setSederiferimento(sederiferimentominimoDst);

			sederiferimentominimoDst.setCap(datiProv.getProspettoProvSede().getCap());
			sederiferimentominimoDst.setComune(datiProv.getProspettoProvSede().getComune().getCodComuneMin());
			sederiferimentominimoDst.setEmail(datiProv.getProspettoProvSede().getEmail());
			sederiferimentominimoDst.setFax(datiProv.getProspettoProvSede().getFax());
			sederiferimentominimoDst.setIndirizzo(datiProv.getProspettoProvSede().getIndirizzo());
			sederiferimentominimoDst.setTelefono(datiProv.getProspettoProvSede().getTelefono());

			if (datiProv.getProvEsonero() != null) {
				Esoneri esoneriDst = new Esoneri();
				esoneriDst.setDataatto(datiProv.getProvEsonero().getDataAtto());
				esoneriDst.setEstremiatto(datiProv.getProvEsonero().getEstremiAtto());
				esoneriDst.setFinoal(datiProv.getProvEsonero().getDataAttoFinoAl());
				esoneriDst.setNumerolavoratori(Format.toStrInt(datiProv.getProvEsonero().getnLavoratoriEsonero()));
				esoneriDst.setPercentuale(Format.toStrInt(datiProv.getProvEsonero().getPercentuale()));
				esoneriDst.setStato(datiProv.getProvEsonero().getStatoConcessione().getCodStatoConcessione());

				quadro2Dst.setEsoneri(esoneriDst);
			}

			if (datiProv.getProvEsoneroAutocert() != null) {
				Esoneri60PerMille esoneriDst60PerMille = new Esoneri60PerMille();
				esoneriDst60PerMille.setDataAutocertificazione(datiProv.getProvEsoneroAutocert().getDataAutocert());
				esoneriDst60PerMille.setNumeroLavoratori60PerMille(
						Format.toStrInt(datiProv.getProvEsoneroAutocert().getnLav60x1000()));
				esoneriDst60PerMille.setNumeroLavoratoriEsonero(
						Format.toStrInt(datiProv.getProvEsoneroAutocert().getnLavEsoneroAutocert()));
				esoneriDst60PerMille
						.setPercentuale(Format.toStrInt(datiProv.getProvEsoneroAutocert().getPercentualeEsAutocert()));
				quadro2Dst.setEsoneri60PerMille(esoneriDst60PerMille);
			}

			if (datiProv.getProvGradualita() != null) {
				Gradualitaprov gradualitaprovDst = new Gradualitaprov();
				gradualitaprovDst
						.setNassunzioni(Format.toStrInt(datiProv.getProvGradualita().getnAssunzioniEffDopoTrasf()));
				quadro2Dst.setGradualita(gradualitaprovDst);
			}

			if (datiProv.getLavoratoriInForzas() != null) {

				ArrayList<Lavoratoricomputabili> lavoratoricomputabiliArrayDst = new ArrayList<Lavoratoricomputabili>();

				for (LavoratoriInForza lavoratore : datiProv.getLavoratoriInForzas()) {
					Lavoratoricomputabili lavComp = new Lavoratoricomputabili();
					lavoratoricomputabiliArrayDst.add(lavComp);

					Lavoratore lavoratoreDst = new Lavoratore();
					lavComp.setLavoratore(lavoratoreDst);

					lavoratoreDst.setCodicefiscale(lavoratore.getCodiceFiscale());
					lavoratoreDst.setCognome(lavoratore.getCognome());

					if (lavoratore.getComune() != null && lavoratore.getComune().getId() != null) {
						lavoratoreDst.setComunenascita(lavoratore.getComune().getCodComuneMin());

					} else if (lavoratore.getStatiEsteri() != null && lavoratore.getStatiEsteri().getId() != null) {
						lavoratoreDst.setComunenascita(lavoratore.getStatiEsteri().getCodNazioneMin());

					}
					lavoratoreDst.setDatanascita(lavoratore.getDataNascita());
					lavoratoreDst.setNome(lavoratore.getNome());
					lavoratoreDst.setSesso(lavoratore.getSesso());
					lavoratoreDst.setPercentualeDisabilita(Format.toStrInt(lavoratore.getPercentualeDisabilita()));

					Rapportolavoro rapportolavoroDst = new Rapportolavoro();
					lavComp.setRapportolavoro(rapportolavoroDst);

					rapportolavoroDst.setCategoriaass(lavoratore.getCategoriaAssunzione());
					rapportolavoroDst.setCategoriasogg(lavoratore.getCategoriaSoggetto());
					rapportolavoroDst.setDatafine(lavoratore.getDataFineRapporto());
					rapportolavoroDst.setDatainizio(lavoratore.getDataInizioRapporto());
					rapportolavoroDst
							.setOrariosettimanale(Format.minutiToOreMinuti(lavoratore.getOrarioSettContrattualeMin()));
					rapportolavoroDst.setOrariosettimanaleparttime(
							Format.minutiToOreMinuti(lavoratore.getOrarioSettPartTimeMin()));

					if (lavoratore.getIstat2001livello5() != null) {
						rapportolavoroDst.setQualifica(lavoratore.getIstat2001livello5().getCodIstat2001livello5Min());
					}
					if (lavoratore.getAssunzioneProtetta() != null) {
						rapportolavoroDst
								.setTipoassunzione(lavoratore.getAssunzioneProtetta().getCodAssunzioneProtetta());
					}
					if (lavoratore.getContratti() != null) {
						rapportolavoroDst.setTipologiacontr(lavoratore.getContratti().getCodTipoContrattoMin());
					}
				}

				quadro2Dst
						.setLavoratoricomputabili(lavoratoricomputabiliArrayDst.toArray(new Lavoratoricomputabili[0]));
			}

			quadro2Dst.setNote(datiProv.getNote());

			Personale personaleDst = new Personale();

			if (datiProv.getCategorieEscluses() != null && datiProv.getCategorieEscluses().size() > 0) {
				ArrayList<Categorieescluse> comCatEscluse = new ArrayList<Categorieescluse>();
				for (CategorieEscluse catEscl : datiProv.getCategorieEscluses()) {
					Categorieescluse comCat = new Categorieescluse();
					comCatEscluse.add(comCat);
					comCat.setCategoria(catEscl.getCategoriaEscluse().getCodCategoriaEscluse());
					comCat.setNumero(Format.toStrInt(catEscl.getnLavAppartartCategoria()));
				}
				personaleDst.setCategorieescluse(comCatEscluse.toArray(new Categorieescluse[0]));
			}

			personaleDst.setDicuiinforzaal17012000(Format.toStrInt(datiProv.getnCateProtForzaA17012000()));
			personaleDst.setCategorieprotette(Format.toStrInt(datiProv.getnCateProtForza()));
			personaleDst.setNlavoratori(Format.toStrInt(datiProv.getnTotaleLavoratDipendenti()));

			PersonaleNonDipendente personaleNoDip = new PersonaleNonDipendente();
			quadro2Dst.setPersonaleNonDipendente(personaleNoDip);

			Lavoratoritempopienoparttime numCentralTelefoNonvedenti = new Lavoratoritempopienoparttime();
			numCentralTelefoNonvedenti
					.setNlavoratoritempopieno(Format.toStrInt(datiProv.getnCentralTelefoNonvedenti()));
			personaleDst.setCentralinisti(numCentralTelefoNonvedenti);

			Lavoratoritempopienoparttime numDisabili = new Lavoratoritempopienoparttime();
			numDisabili.setNlavoratoritempopieno(Format.toStrInt(datiProv.getnDisabiliInForza()));
			personaleDst.setDisabili(numDisabili);

			Lavoratoritempopienoparttime numFisiot = new Lavoratoritempopienoparttime();
			numFisiot.setNlavoratoritempopieno(Format.toStrInt(datiProv.getnTerariabMassofisNonved()));
			personaleDst.setMassofisioterapisti(numFisiot);

			Lavoratoritempopienoparttime numTelelavoro = new Lavoratoritempopienoparttime();
			numTelelavoro.setNlavoratoritempopieno(Format.toStrInt(datiProv.getnTelelavoroFt()));
			personaleDst.setTelelavoro(numTelelavoro);

			Lavoratoritempopienoparttime numSomministrati = new Lavoratoritempopienoparttime();
			numSomministrati.setNlavoratoritempopieno(Format.toStrInt(datiProv.getnSomministratiFt()));
			personaleNoDip.setLavoratoriDisabiliSomministrati(numSomministrati);

			Lavoratoritempopienoparttime numDisabiliConv = new Lavoratoritempopienoparttime();
			numDisabiliConv.setNlavoratoritempopieno(Format.toStrInt(datiProv.getnConvenzioni12bis14Ft()));
			personaleNoDip.setLavoratoriDisabiliConv12bis14(numDisabiliConv);

			if (datiProv.getPartTimes() != null && datiProv.getPartTimes().size() > 0) {
				ArrayList<Dettaglioparttime> comParttime = new ArrayList<Dettaglioparttime>();

				for (PartTime parttime : datiProv.getPartTimes()) {

					Dettaglioparttime pt = new Dettaglioparttime();
					pt.setNumero(Format.toStrInt(parttime.getnPartTime()));
					pt.setOrariosettimanalecontrattuale(
							Format.minutiToOreMinuti(parttime.getOrarioSettContrattualeMin()));
					pt.setOrariosettimanaleparttime(Format.minutiToOreMinuti(parttime.getOrarioSettPartTimeMin()));

					if (parttime.getTipoRipropPt().getId() == AppConstants.PT_CENTRALINISTI) {
						if (personaleDst.getCentralinisti().getParttime() == null) {
							personaleDst.getCentralinisti().setParttime(new ArrayList());
						}

						personaleDst.getCentralinisti().getParttime().add(pt);

					} else if (parttime.getTipoRipropPt().getId() == AppConstants.PT_DISABILI) {
						if (personaleDst.getDisabili().getParttime() == null) {
							personaleDst.getDisabili().setParttime(new ArrayList());
						}
						personaleDst.getDisabili().getParttime().add(pt);

					} else if (parttime.getTipoRipropPt().getId() == AppConstants.PT_TERAPISTI_MASSOF_NONVEDENTI) {
						if (personaleDst.getMassofisioterapisti().getParttime() == null) {
							personaleDst.getMassofisioterapisti().setParttime(new ArrayList());
						}
						personaleDst.getMassofisioterapisti().getParttime().add(pt);

					} else if (parttime.getTipoRipropPt().getId() == AppConstants.PT_TELELAVORO) {
						if (personaleDst.getTelelavoro().getParttime() == null) {
							personaleDst.getTelelavoro().setParttime(new ArrayList());
						}
						personaleDst.getTelelavoro().getParttime().add(pt);

					} else if (parttime.getTipoRipropPt().getId() == AppConstants.PT_DISABILI_SOMM) {
						if (personaleNoDip.getLavoratoriDisabiliSomministrati().getParttime() == null) {
							personaleNoDip.getLavoratoriDisabiliSomministrati().setParttime(new ArrayList());
						}
						personaleNoDip.getLavoratoriDisabiliSomministrati().getParttime().add(pt);

					} else if (parttime.getTipoRipropPt().getId() == AppConstants.PT_DISABILI_CONVENZIONE) {
						if (personaleNoDip.getLavoratoriDisabiliConv12bis14().getParttime() == null) {
							personaleNoDip.getLavoratoriDisabiliConv12bis14().setParttime(new ArrayList());
						}
						personaleNoDip.getLavoratoriDisabiliConv12bis14().getParttime().add(pt);

					} else if (parttime.getTipoRipropPt().getId() == AppConstants.PT_ALTRE_CATEGORIA) {
						if (comParttime == null) {
							comParttime = new ArrayList();
						}
						comParttime.add(pt);
					}
				}
				
				/* inizio patch per comunicazione pd soap
				if(personaleDst.getCentralinisti().getParttime() != null) {
					personaleDst.getCentralinisti().setParttimeArray((Dettaglioparttime[])(personaleDst.getCentralinisti().getParttime().toArray(new Dettaglioparttime[0])));
					personaleDst.getCentralinisti().setParttime(null);
				}
				if(personaleDst.getDisabili().getParttime() != null) {
					personaleDst.getDisabili().setParttimeArray((Dettaglioparttime[])(personaleDst.getDisabili().getParttime().toArray(new Dettaglioparttime[0])));
					personaleDst.getDisabili().setParttime(null);
				}				
				if(personaleDst.getMassofisioterapisti().getParttime() != null) {
					personaleDst.getMassofisioterapisti().setParttimeArray((Dettaglioparttime[])(personaleDst.getMassofisioterapisti().getParttime().toArray(new Dettaglioparttime[0])));
					personaleDst.getMassofisioterapisti().setParttime(null);
				}				
				if(personaleDst.getTelelavoro().getParttime() != null) {
					personaleDst.getTelelavoro().setParttimeArray((Dettaglioparttime[])(personaleDst.getTelelavoro().getParttime().toArray(new Dettaglioparttime[0])));
					personaleDst.getTelelavoro().setParttime(null);
				}				
				if(personaleNoDip.getLavoratoriDisabiliSomministrati().getParttime() != null) {
					personaleNoDip.getLavoratoriDisabiliSomministrati().setParttimeArray((Dettaglioparttime[])(personaleNoDip.getLavoratoriDisabiliSomministrati().getParttime().toArray(new Dettaglioparttime[0])));
					personaleNoDip.getLavoratoriDisabiliSomministrati().setParttime(null);
				}
				if(personaleNoDip.getLavoratoriDisabiliConv12bis14().getParttime() != null) {
					personaleNoDip.getLavoratoriDisabiliConv12bis14().setParttimeArray((Dettaglioparttime[])(personaleNoDip.getLavoratoriDisabiliConv12bis14().getParttime().toArray(new Dettaglioparttime[0])));
					personaleNoDip.getLavoratoriDisabiliConv12bis14().setParttime(null);
				}
				*/ //fine patch
				
				if (comParttime != null) {
					personaleDst.setDettaglioparttime(
							(Dettaglioparttime[]) (comParttime.toArray(new Dettaglioparttime[0])));
					
				}
			}

			if (datiProv.getProvIntermittentis() != null && datiProv.getProvIntermittentis().size() > 0) {
				ArrayList<Dettagliointermittenti> comIntermittenti = new ArrayList<Dettagliointermittenti>();

				for (ProvIntermittenti intermittenti : datiProv.getProvIntermittentis()) {
					Dettagliointermittenti comIntermittente = new Dettagliointermittenti();
					comIntermittenti.add(comIntermittente);
					comIntermittente.setNumero(Format.toStrInt(intermittenti.getnIntermittenti()));
					comIntermittente.setOrariosettimanalecontrattuale(
							Format.minutiToOreMinuti(intermittenti.getOrarioSettimanaleContrattual()));
					comIntermittente.setOrariosettimanalesvolto(
							Format.minutiToOreMinuti(intermittenti.getOrarioSettimanaleSvolto()));
				}

				personaleDst.setDettagliointermittenti(comIntermittenti.toArray(new Dettagliointermittenti[0]));
			}

			quadro2Dst.setPersonaledipendente(personaleDst);

			if (datiProv.getPostiLavoroDisps() != null && datiProv.getPostiLavoroDisps().size() > 0) {

				ArrayList<Postilavoro> lavArray = new ArrayList<Postilavoro>();

				for (PostiLavoroDisp postoDisp : datiProv.getPostiLavoroDisps()) {
					Postilavoro posto = new Postilavoro();
					lavArray.add(posto);

					posto.setBarrierearchitettoniche(postoDisp.getFlgPresenzaBarriereArchite());
					posto.setCapacitacontroindicazioni(postoDisp.getDescCapacitaRichiesteContr());
					posto.setCategoriaass(postoDisp.getCategoriaAssunzione());
					posto.setCategoriasogg(postoDisp.getCategoriaSoggetto());

					if (postoDisp.getComune() != null && postoDisp.getComune().getId() > 0) {
						posto.setComuneass(postoDisp.getComune().getCodComuneMin());

					} else if (postoDisp.getStatiEsteri() != null && postoDisp.getStatiEsteri().getId() > 0) {
						posto.setComuneass(postoDisp.getStatiEsteri().getCodNazioneMin());
					}
					posto.setMansione(postoDisp.getDescMansione());
					posto.setMezzipubblici(postoDisp.getFlgRaggiungibMezziPubblici());
					posto.setNposti(Format.toStrInt(postoDisp.getnPosti()));
					if (postoDisp.getIstat2001livello5() != null && postoDisp.getIstat2001livello5().getId() > 0) {
						posto.setQualificaprof(postoDisp.getIstat2001livello5().getCodIstat2001livello5Min());
					}
					posto.setTurninotturni(postoDisp.getFlgTurniNotturni());

				}

				quadro2Dst.setPostilavoro(lavArray.toArray(new Postilavoro[0]));
			}

			if (datiProv.getProvSospensione() != null) {
				Sospensione s = new Sospensione();
				quadro2Dst.setSospensioni(s);
				if (datiProv.getProvSospensione().getCausaSospensione() != null
						&& datiProv.getProvSospensione().getCausaSospensione().getId() > 0) {
					s.setCausale(datiProv.getProvSospensione().getCausaSospensione().getCodCausaSospensione());
				}
				s.setNumerolavoratori(Format.toStrInt(datiProv.getProvSospensione().getnLavoratori()));

				if (datiProv.getProvSospensione().getStatoConcessione() != null
						&& datiProv.getProvSospensione().getStatoConcessione().getId() > 0) {
					s.setStato(datiProv.getProvSospensione().getStatoConcessione().getCodStatoConcessione());
				}

				s.setDataFineSospensione(datiProv.getProvSospensione().getDataFineSospensioneQ2());// Adeguamenti 2014
			}

		} // fine quadri prov

		prospettoGeneraleDst.setQuadro2(quadri2.toArray(new Quadro2[0]));

		/*************************** Quadro 3 *************************************/

		Quadro3 quadro3Dst = new Quadro3();

		ArrayList<Elencoriepilogativo> elRiepArray = new ArrayList<Elencoriepilogativo>();

		for (ProspettoProvincia prospProv : prospetto.getProspettoProvincias()) {
			Elencoriepilogativo elRiep = new Elencoriepilogativo();
			elRiepArray.add(elRiep);

			elRiep.setNumLavBaseComputoArt3(Format.toStrInt(prospProv.getRiepilogoProvinciale().getBaseComputoArt3()));
			elRiep.setNumLavBaseComputoArt18(
					Format.toStrInt(prospProv.getRiepilogoProvinciale().getBaseComputoArt18()));
			elRiep.setSospensioniincorso(prospProv.getRiepilogoProvinciale().getFlgSospensioniInCorso());
			elRiep.setCategoriacompcatprotette(prospProv.getRiepilogoProvinciale().getCatCompensazioneCateProt());
			elRiep.setCategoriacompdisabili(prospProv.getRiepilogoProvinciale().getCatCompensazioneDisabili());
			elRiep.setCatprotette(Format.toStrInt(prospProv.getRiepilogoProvinciale().getNumCatProtInForza()));
			elRiep.setDisabili(Format.toStrInt(prospProv.getRiepilogoProvinciale().getNumDisabiliInForza()));
			elRiep.setEsonerati(Format.toStrInt(prospProv.getRiepilogoProvinciale().getNumPosizioniEsonerate()));

			if (prospProv.getRiepilogoProvinciale().getNumCompensazioniCateProt() != null && prospProv
					.getRiepilogoProvinciale().getNumCompensazioniCateProt().compareTo(BigDecimal.ZERO) != 0) {
				elRiep.setNumerocompcatprotette(
						Format.toStrInt(prospProv.getRiepilogoProvinciale().getNumCompensazioniCateProt()));
			}
			if (prospProv.getRiepilogoProvinciale().getNumCompensazioneDisabili() != null && prospProv
					.getRiepilogoProvinciale().getNumCompensazioneDisabili().compareTo(BigDecimal.ZERO) != 0) {
				elRiep.setNumerocompdisabili(
						Format.toStrInt(prospProv.getRiepilogoProvinciale().getNumCompensazioneDisabili()));
			}

			elRiep.setProvincia(prospProv.getProvincia().getCodProvinciaMin());
			elRiep.setQuotariservacatprotette(
					Format.toStrInt(prospProv.getRiepilogoProvinciale().getQuotaRiservaArt18()));
			elRiep.setQuotariservadisabili(
					Format.toStrInt(prospProv.getRiepilogoProvinciale().getQuotaRiservaDisabili()));
			elRiep.setScoperturecatprotette(
					Format.toStrInt(prospProv.getRiepilogoProvinciale().getNumScopertureCatProt()));
			elRiep.setScoperturedisabili(
					Format.toStrInt(prospProv.getRiepilogoProvinciale().getNumScopertureDisabili()));
		}

		quadro3Dst.setElencoriepilogativoprovinciale(elRiepArray.toArray(new Elencoriepilogativo[0]));
		quadro3Dst.setNote(prospetto.getNote());

		Riepilogonazionale riepilogonazionaleDst = new Riepilogonazionale();

		riepilogonazionaleDst
				.setNumLavBaseComputoArt3(Format.toStrInt(prospetto.getRiepilogoNazionale().getBaseComputoArt3()));
		riepilogonazionaleDst
				.setNumLavBaseComputoArt18(Format.toStrInt(prospetto.getRiepilogoNazionale().getBaseComputoArt18()));
		riepilogonazionaleDst.setSospensioniincorso(prospetto.getRiepilogoNazionale().getFlgSospensioniInCorso());
		riepilogonazionaleDst.setCatprotette(Format.toStrInt(prospetto.getRiepilogoNazionale().getNumCatProtInForza()));
		riepilogonazionaleDst.setDisabili(Format.toStrInt(prospetto.getRiepilogoNazionale().getNumDisabiliInForza()));
		riepilogonazionaleDst.setEsoneri(Format.toStrInt(prospetto.getRiepilogoNazionale().getNumPosizioniEsonerate()));
		riepilogonazionaleDst
				.setQuotariservacatprotette(Format.toStrInt(prospetto.getRiepilogoNazionale().getQuotaRiservaArt18()));
		riepilogonazionaleDst
				.setQuotariservadisabili(Format.toStrInt(prospetto.getRiepilogoNazionale().getQuotaRiservaDisabili()));
		riepilogonazionaleDst.setScoperturecatprotette(
				Format.toStrInt(prospetto.getRiepilogoNazionale().getNumScopertCategorieProtette()));
		riepilogonazionaleDst
				.setScoperturedisabili(Format.toStrInt(prospetto.getRiepilogoNazionale().getNumScopertDisabili()));
		riepilogonazionaleDst
				.setQuotaEsuberiArt18(Format.toStrInt(prospetto.getRiepilogoNazionale().getQuotaEsuberiArt18()));

		quadro3Dst.setRiepilogonazionale(riepilogonazionaleDst);
		prospettoGeneraleDst.setQuadro3(quadro3Dst);

		// DATI Comunicazione aggiuntivi
		DatiInvioDTO datiInvioDst = new DatiInvioDTO();

		if (ruolo.getRuolo() == null) {
			// FIXME
		} else if (ruolo.getRuolo().toUpperCase()
				.startsWith(ProfConstants.CONSULENTE_RESPONSABILE_COMONL_DESC.toUpperCase())
				|| (ruolo.getRuolo().toUpperCase().startsWith(ProfConstants.AMMINISTRATORE_PRODIS_DESC.toUpperCase())
						&& !isComunicazioneDatoreLavoro)) {

			datiInvioDst.setSoggettoComunicante(
					prospetto.getSoggetti() != null ? prospetto.getSoggetti().getCodSoggetti() : "");
			datiInvioDst.setCodFiscaleSoggettoComunicante(
					cfStudioProfessionale == null ? "" : cfStudioProfessionale.toUpperCase());
		}

		datiInvioDst.setCodiceComunicazionePrecedente(prospetto.getCodiceComunicazionePreced());
		if (prospetto.getDataTimbroPostale() != null) {
			datiInvioDst.setDataInvio(prospetto.getDataTimbroPostale());
		} else {
			datiInvioDst.setDataInvio(new Date());
		}
		datiInvioDst.setEmailDelegato(prospetto.getEmailSoggettoComunicazione());
		if (prospetto.getNumeroProtocollo() != null) {
			datiInvioDst.setProtocolloSistema(Format.toStrInt(prospetto.getNumeroProtocollo()));
		}
		datiInvioDst.setTipoComunicazione(prospetto.getComunicazione().getCodComunicazione());

		DatiAggiuntiviTracciatoDTO datiAggiuntiviTracciatoDst = new DatiAggiuntiviTracciatoDTO();
		datiAggiuntiviTracciatoDst.setIdAppMittente(TUConstants.PRODIS);
		datiAggiuntiviTracciatoDst.setNumVersioneXML(TUConstants.TIPO_TRACCIATO_XML_NEW);
		datiAggiuntiviTracciatoDst.setRichiestaValidazioneMinisteriale(true);
		datiAggiuntiviTracciatoDst.setTipoTracciatoXML(TUConstants.TIPO_TRACCIATO_UNIPI);

		DatiAggiuntiviComunicazioneDTO DatiAggiuntiviComunicazioneDst = new DatiAggiuntiviComunicazioneDTO();
		datiAggiuntiviTracciatoDst.setDatiAggiuntiviComunicazione(DatiAggiuntiviComunicazioneDst);

		if (prospetto.getDatiAzienda().getSedeLegale() != null) {
			DatiAggiuntiviComunicazioneDst
					.setSiglaProvincia(prospetto.getDatiAzienda().getComune().getProvincia().getDsTarga());
		}

		datiAggiuntiviTracciatoDst.setChiaveEsterna(prospetto.getId().longValue());
		datiAggiuntiviTracciatoDst.setIdAppMittente(AppConstants.PROSPETTO_DISABILI);

		comProspetto.setProspetto(prospettoGeneraleDst);
		comProspetto.setDatiInvio(datiInvioDst);
		comProspetto.setDatiAggiuntivi(datiAggiuntiviTracciatoDst);

		return comProspetto;
	}

	/** Private constructor */
	private ProdisIntegMappers() {
		// Prevent instantiation
	}
}
