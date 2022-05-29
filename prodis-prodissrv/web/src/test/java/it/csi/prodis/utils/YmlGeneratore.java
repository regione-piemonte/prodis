/*-
 * ========================LICENSE_START=================================
 * PRODIS Servizi - WAR submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022  | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Set;
import java.util.UUID;

import it.csi.prodis.prodissrv.lib.dto.Utente;
import it.csi.prodis.prodissrv.lib.dto.common.Ruolo;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.AssunzioneProtetta;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Atecofin;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.CategoriaAzienda;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.CategoriaEscluse;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.CausaSospensione;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Ccnl;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Comune;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Comunicazione;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Contratti;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Cpi;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Dichiarante;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.ImportErroriSpicom;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Istat2001livello5;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Provincia;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Regione;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Soggetti;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.StatiEsteri;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.StatoConcessione;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.StatoProspetto;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.StatoVerifica;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.TipoRipropPt;
import it.csi.prodis.prodissrv.lib.dto.prospetto.AssPubbliche;
import it.csi.prodis.prodissrv.lib.dto.prospetto.AssPubblichePK;
import it.csi.prodis.prodissrv.lib.dto.prospetto.CategorieEscluse;
import it.csi.prodis.prodissrv.lib.dto.prospetto.DatiAzienda;
import it.csi.prodis.prodissrv.lib.dto.prospetto.DatiProvinciali;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ImportErrori;
import it.csi.prodis.prodissrv.lib.dto.prospetto.LavoratoriInForza;
import it.csi.prodis.prodissrv.lib.dto.prospetto.LavoratoriSilp;
import it.csi.prodis.prodissrv.lib.dto.prospetto.LavoratoriSilpPK;
import it.csi.prodis.prodissrv.lib.dto.prospetto.Parametri;
import it.csi.prodis.prodissrv.lib.dto.prospetto.PartTime;
import it.csi.prodis.prodissrv.lib.dto.prospetto.PdfProspetto;
import it.csi.prodis.prodissrv.lib.dto.prospetto.PdfSilp;
import it.csi.prodis.prodissrv.lib.dto.prospetto.PostiLavoroDisp;
import it.csi.prodis.prodissrv.lib.dto.prospetto.Prospetto;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProspettoGradualita;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProspettoProvSede;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProspettoProvincia;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProvCompensazioni;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProvConvenzione;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProvEsonero;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProvEsoneroAutocert;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProvGradualita;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProvIntermittenti;
import it.csi.prodis.prodissrv.lib.dto.prospetto.ProvSospensione;
import it.csi.prodis.prodissrv.lib.dto.prospetto.FilterServiziProdis;
import it.csi.prodis.prodissrv.lib.dto.prospetto.RiepilogoNazionale;
import it.csi.prodis.prodissrv.lib.dto.prospetto.RiepilogoProvinciale;
import it.csi.prodis.prodissrv.lib.dto.prospetto.SedeLegale;
import it.csi.prodis.prodissrv.lib.dto.prospetto.UserAccessLog;

public class YmlGeneratore {
	// final static String PATH_ROOT =
	// "D:\\workspace-prodis\\prodissrv\\docs\\definitions";
	final static String PATH_ROOT = "..\\docs\\definitions";

	final static String PATH_SYSTEM = PATH_ROOT + "\\system";
	final static String PATH_DECODIFICA = PATH_ROOT + "\\decodifica";
	final static String PATH_COMMON = PATH_ROOT + "\\common";
	final static String PATH_PROSPETTO = PATH_ROOT + "\\prospetto";

	final static Class pkClassDefault = Integer.class;
	final static String ACAPO = "\n";

	public static void main(String[] args) {
		try {
			// oggetti system
			go(Utente.class, PATH_SYSTEM);

			// oggetti decodifica
			go(AssunzioneProtetta.class, PATH_DECODIFICA);
			go(Atecofin.class, PATH_DECODIFICA);
			go(CategoriaAzienda.class, PATH_DECODIFICA);
			go(CategoriaEscluse.class, PATH_DECODIFICA);
			go(CausaSospensione.class, PATH_DECODIFICA);
			go(Ccnl.class, PATH_DECODIFICA);
			go(Comune.class, PATH_DECODIFICA);
			go(Comunicazione.class, PATH_DECODIFICA);
			go(Contratti.class, PATH_DECODIFICA);
			go(Cpi.class, PATH_DECODIFICA);
			go(Dichiarante.class, PATH_DECODIFICA);
			go(ImportErroriSpicom.class, PATH_DECODIFICA);
			go(Istat2001livello5.class, PATH_DECODIFICA);
			go(Provincia.class, PATH_DECODIFICA);
			go(Regione.class, PATH_DECODIFICA);
			go(Soggetti.class, PATH_DECODIFICA);
			go(StatiEsteri.class, PATH_DECODIFICA);
			go(StatoConcessione.class, PATH_DECODIFICA);
			go(StatoProspetto.class, PATH_DECODIFICA);
			go(StatoVerifica.class, PATH_DECODIFICA);
			go(TipoRipropPt.class, PATH_DECODIFICA);

			// oggetti common
			go(Ruolo.class, PATH_COMMON);

			// oggetti prospetto
			go(AssPubbliche.class, PATH_PROSPETTO);
			go(AssPubblichePK.class, PATH_PROSPETTO);
			go(CategorieEscluse.class, PATH_PROSPETTO);
			go(DatiAzienda.class, PATH_PROSPETTO);
			go(DatiProvinciali.class, PATH_PROSPETTO);
			go(ImportErrori.class, PATH_PROSPETTO);
			go(LavoratoriInForza.class, PATH_PROSPETTO);
			go(LavoratoriSilp.class, PATH_PROSPETTO);
			go(LavoratoriSilpPK.class, PATH_PROSPETTO);
			go(Parametri.class, PATH_PROSPETTO);
			go(PartTime.class, PATH_PROSPETTO);
			go(PdfProspetto.class, PATH_PROSPETTO);
			go(PdfSilp.class, PATH_PROSPETTO);
			go(PostiLavoroDisp.class, PATH_PROSPETTO);
			go(Prospetto.class, PATH_PROSPETTO);
			go(ProspettoGradualita.class, PATH_PROSPETTO);
			go(ProspettoProvincia.class, PATH_PROSPETTO);
			go(ProspettoProvSede.class, PATH_PROSPETTO);
			go(ProvCompensazioni.class, PATH_PROSPETTO);
			go(ProvConvenzione.class, PATH_PROSPETTO);
			go(ProvEsonero.class, PATH_PROSPETTO);
			go(ProvEsoneroAutocert.class, PATH_PROSPETTO);
			go(ProvGradualita.class, PATH_PROSPETTO);
			go(ProvIntermittenti.class, PATH_PROSPETTO);
			go(ProvSospensione.class, PATH_PROSPETTO);
			go(FilterServiziProdis.class, PATH_PROSPETTO);
			go(RiepilogoNazionale.class, PATH_PROSPETTO);
			go(RiepilogoProvinciale.class, PATH_PROSPETTO);
			go(SedeLegale.class, PATH_PROSPETTO);
			go(UserAccessLog.class, PATH_PROSPETTO);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void go(Class objClass, String path) throws FileNotFoundException, IOException {
		go(objClass, path, pkClassDefault);
	}

	private static void go(Class objClass, String path, Class pkClass) throws FileNotFoundException, IOException {
		StringBuilder sbOut = new StringBuilder();
		sbOut.append(objClass.getSimpleName() + ":" + ACAPO);
		sbOut.append("  type: object" + ACAPO);
		sbOut.append("  properties:" + ACAPO);

		Hashtable<String, String> hstProperies = new Hashtable<String, String>();

		// Get the public methods associated with this class.
		Method[] methods = objClass.getMethods();
		for (Method method : methods) {
			// System.out.println("Public method found: " + method.toString());
			if (method.getName().startsWith("get")) {
				System.out.println(method.getReturnType() + " - " + method.getName());
				String property = method.getName().substring(3, method.getName().length());
				property = property.substring(0, 1).toLowerCase() + property.substring(1);

				StringBuilder sb = new StringBuilder();
				if (method.getReturnType().getCanonicalName().equals("java.util.UUID")) {
					sb.append("    " + property + ":" + ACAPO);
					sb.append("      type: string" + ACAPO);
					sb.append("      format: uuid" + ACAPO);

				} else if (method.getReturnType().getCanonicalName().equals("java.lang.Object")) {
					sb.append("    " + property + ":" + ACAPO);
					if (pkClass != null && pkClass.equals(UUID.class)) {
						sb.append("      type: string" + ACAPO);
						sb.append("      format: uuid" + ACAPO);
					} else {
						sb.append("      type: integer" + ACAPO);
					}

				} else if (method.getReturnType().getCanonicalName().equals("java.lang.String")) {
					sb.append("    " + property + ":" + ACAPO);
					sb.append("      type: string" + ACAPO);

				} else if (method.getReturnType().getCanonicalName().equals("java.util.Date")) {
					sb.append("    " + property + ":" + ACAPO);
					sb.append("      type: string" + ACAPO);
					sb.append("      format: date-time" + ACAPO);

				} else if (method.getReturnType().getCanonicalName().equals("java.math.BigDecimal")) {
					sb.append("    " + property + ":" + ACAPO);
					sb.append("      type: number" + ACAPO);

				} else if (method.getReturnType().getCanonicalName().equals("java.lang.Boolean")) {
					sb.append("    " + property + ":" + ACAPO);
					sb.append("      type: integer" + ACAPO);

				} else if (method.getReturnType().getCanonicalName().equals("java.lang.Integer")) {
					sb.append("    " + property + ":" + ACAPO);
					sb.append("      type: integer" + ACAPO);

				} else if (method.getReturnType().getCanonicalName().equals("java.util.List")) {
					sb.append("    " + property + ":" + ACAPO);
					sb.append("      type: array" + ACAPO);
					sb.append("      items:  " + ACAPO);

					String objName = method.getGenericReturnType().getTypeName();
					if (objName.indexOf("String") != -1) {
						objName = "String";
						sb.append("        type: string" + ACAPO);
					} else {
						int iDot = objName.lastIndexOf(".");
						objName = objName.substring(iDot + 1, objName.length() - 1);
						sb.append("        $ref: '#/definitions/" + objName + "'" + ACAPO);
					}

				} else if (method.getReturnType().getCanonicalName().startsWith("it.csi.prodis.prodissrv.lib.dto.")) {
					int iDot = method.getReturnType().getCanonicalName().lastIndexOf(".");
					String objName = method.getReturnType().getCanonicalName().substring(iDot + 1);
					// objName = objName.substring(0, 1).toLowerCase() + objName.substring(1);

					sb.append("    " + property + ":" + ACAPO);
					sb.append("      type: object" + ACAPO);
					sb.append("      $ref: '#/definitions/" + objName + "'" + ACAPO);

				} else {
					if (!property.equals("class")) {
						System.out.println("DA GESTIRE");
					}
				}

				if (!sb.toString().trim().equals("")) {
					hstProperies.put(property, sb.toString());
				}
			}
		}

		System.out.println("-----------------");
		// ordinamento properties
		Set<String> setProperties = hstProperies.keySet();
		ArrayList<String> listProperties = new ArrayList<String>();
		listProperties.addAll(setProperties);
		Collections.sort(listProperties, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});

		for (String property : listProperties) {
			sbOut.append(hstProperies.get(property));
		}

		System.out.println(sbOut);

		String filename = "";
		String name = objClass.getSimpleName().substring(0, 1).toLowerCase() + objClass.getSimpleName().substring(1);
		for (int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			String sc = "" + c;
			if (c >= 'A' && c <= 'Z') {
				filename += "-" + sc.toLowerCase();
			} else {
				filename += c;
			}
		}

		if (path.startsWith(".")) {
			String current = new java.io.File(".").getCanonicalPath();
			path = current + "\\" + path;
		}

		File file = new File(path + "\\" + filename + ".yml");
		System.out.println("output: " + file.getAbsolutePath());

		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}

		FileOutputStream fos = new FileOutputStream(file);
		fos.write(sbOut.toString().getBytes());
		fos.close();
	}

}
