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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.GregorianCalendar;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;


public class Varie {

	public static String getClassName(String className) {
    String name = "";
    if (className.lastIndexOf(".") >= 0) {
      name = className.substring(className.lastIndexOf(".") + 1);
    }
    return name;
  }
	
	public static String createMessageError(Object[] elencoErrori) {
	  StringBuffer messaggioCompleto = null;
	  try {
	    String messaggio = elencoErrori[0].toString();
	    String parametro = "";
	
	    messaggioCompleto = new StringBuffer(messaggio);
	    int pos = messaggioCompleto.toString().indexOf("@");
	    int i = 1;
	
	    while (pos != -1 && i <= 3) {
	//	if(elencoErrori[i] != null) {
		parametro = elencoErrori[i].toString();
		//System.out.println("..... Varie.createMessageError, parametro = " + parametro);
		messaggioCompleto.replace(pos, pos + 1, parametro);
		pos = messaggioCompleto.toString().indexOf("@");
	//	}
		i++;
	    }
	  }
	  catch (Exception ex) {
	    //System.out.println("........ ERRORE: varie.createMessageError: elencoErrori ha dei problemi, allora by-passo, " + ex.toString());
	  }
	   return messaggioCompleto != null ? messaggioCompleto.toString() : null;
	 }
	

	public static boolean writeFileToServerFileSystem(String path,byte[] pdf, String nomeFile)
 {
     //****************************************** DA ELIMINARE PER LA VERSIONE IN ESERCIZIO *************************
      try {

          File tempFile = new File(
                  path + nomeFile);

          tempFile.delete();

          RandomAccessFile rf = new RandomAccessFile(tempFile, "rw");

          rf.write(pdf);

          rf.close();
      } catch (FileNotFoundException ex10) {
          ex10.printStackTrace();

      } catch (IOException ex20) {
          ex20.printStackTrace();
      }
      return true;
 }


	public static boolean writeFileToServerFileSystem(String path,String pdf, String nomeFile)
{
    //****************************************** DA ELIMINARE PER LA VERSIONE IN ESERCIZIO *************************
     try {

         File tempFile = new File(
                 path + nomeFile);

         tempFile.delete();

         RandomAccessFile rf = new RandomAccessFile(tempFile, "rw");

         rf.writeBytes(pdf);

         rf.close();
     } catch (FileNotFoundException ex10) {
         ex10.printStackTrace();

     } catch (IOException ex20) {
         ex20.printStackTrace();
     }
     return true;
}

 /***
  *  ritorna true se
  *  - l'oggetto in ingresso � nullo
  *  - l'oggetto in ingresso � una stringa ed � vuota
  *  - l'oggetto in ingresso � una collection ed � nulla o a dimensione 0
  */
 
	public static boolean isIDVoid(Long id){
		 return (id == null || id.longValue() == 0);
	 }
	 
	public static boolean isIDNotVoid(Long id){
		 return !isIDVoid(id);
	 }
	
	public static boolean isNullLong(Long id) {
		if(id == null) {
			return true;
		}
		return false;
	}
	
	public static boolean isVoidLong(Long objIn) {
		if(objIn == null) {
			return true;
		}
		else if(objIn.longValue() == 0) {
			// lo zero viene considerato come valore non nullo
			return false;
		}
		return false;
	}
	
    public static boolean isVoidInteger(Integer objIn) {
        if(objIn == null) {
            return true;
        }
        else if(objIn.intValue() == 0) {
            // lo zero viene considerato come valore non nullo
            return false;
        }
        return false;
    }
    
	@SuppressWarnings({ "rawtypes" })
	public static boolean isVoid(Object objIn) {
		try {
			// oggetto nullo 
			 if(objIn == null) {
				 return true;
			 }	 
			 // stringa vuota
			 else if(objIn instanceof String && ((String)objIn).trim().equals("")) {
				 return true;
			 }
			 // Long
			 else if(objIn instanceof Long && ((Long) objIn).longValue() == 0) {
				 return true;
			 }
			 else if(objIn instanceof Integer && ((Integer) objIn).intValue() == 0) {
			     return true;
			 }
			 // Boolean: se non � nullo,
			 else if(objIn instanceof Boolean) {
				 try {
					 @SuppressWarnings("unused")
					boolean b = ((Boolean) objIn).booleanValue();
					 return false;
				 }
				 catch(Exception e) {
					 return true;
				 }
			 }
			 //collection nulla o vuota
			 else if(objIn instanceof Collection && (objIn == null || ((Collection) objIn).size() == 0)) {
				 return true;
			 }
			 else if(objIn instanceof ArrayList && (objIn == null || ((ArrayList) objIn).size() == 0)) {
				 return true;
			 }
			 // java.util.date
			 else if(objIn instanceof java.util.Date && ((java.util.Date) objIn).getTime() == 0) {
				 return true;
			 }
			 else if(objIn instanceof java.sql.Date && ((java.sql.Date) objIn).getTime() == 0) {
				 return true;
			 }
			 // gregoriaCal
			 else if(objIn instanceof GregorianCalendar && ((GregorianCalendar) objIn).getTime() == null) {
				 return true;
			 }
	//		 // decodifica
	//		 else if(objIn instanceof Decodifica &&
	//				 (((Decodifica) objIn).getCodice() == null || ((Decodifica) objIn).getCodice().equals(""))) {
	//			 return true;
	//		 }
	//		 // ComuneDRISBO, in caso di comune sede legale/operativa
	//		 else if(objIn instanceof ComuneDRISBO &&
	//				 (((ComuneDRISBO) objIn).getCodice() == null || ((ComuneDRISBO) objIn).getCodice().equals("") ||
	//				 ((ComuneDRISBO) objIn).getCodice().equals("0") || ((ComuneDRISBO) objIn).getCodice().equals("-1"))) {
	//			 return true;
	//		 }
	//		 // ComuneSiltoBO, in caso di comune dom/res del lavoratore
	//		 else if(objIn instanceof ComuneSiltoBO &&
	//				 (((ComuneSiltoBO) objIn).getCodice() == null || ((ComuneSiltoBO) objIn).getCodice().equals(""))) {
	//			 return true;
	//		 }
	//		 // TipoRapportoBO, per l'oggetto tipo rapporto di lavoro della comunicazione
	//		 else if(objIn instanceof TipoRapportoBO &&
	//				 (((TipoRapportoBO) objIn).getCodice() == null || ((TipoRapportoBO) objIn).getCodice().equals("") ||
	//				 ((TipoRapportoBO) objIn).getForma() == null || ((TipoRapportoBO) objIn).getForma().equals(""))) {
	//			 return true;
	//		 }
	//		 // in caso di objIn utente
	//		 else if(objIn instanceof ATUserLogin && ((ATUserLogin) objIn).getInfoPersona() == null) {
	//			 return true;
	//		 }
			 else{
				 return false;
			 }
		}
		catch(Exception e) {
			return true;
		}
	 }

	public static boolean isVoidCombo(Object objIn) {
		 // oggetto nullo 
		 if(objIn == null) {
			 return true;
		 }	 
		 // stringa vuota
		 else if(objIn instanceof String && (objIn.equals("") || objIn.equals("-1") || objIn.equals("0"))) {
			 return true;
		 }
		 else {
			 return false;
		 }
	}
	
	public static boolean isNotVoid(Object objIn) {
		 return !isVoid(objIn);
	 }

	public static boolean isNotVoidCombo(Object objIn) {
		 return !isVoidCombo(objIn);
	 }

	public static boolean isNotVoidAndEqual(int s, int conf) {
		if(s == 0) {
			return false;
		}
//		else if(s.equals("") || s.trim().equals("")) {
//			return false;
//		}
		else if(s == conf) {
			return true;
		}
		return false;
	}

	public static boolean isNotVoidAndEqual(String s, String conf) {
		if(s == null) {
			return false;
		}
		else if(s.equals("") || s.trim().equals("")) {
			return false;
		}
		else if(s.equals(conf)) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unlikely-arg-type")
	public static boolean isNotVoidAndEqual(String s, int conf) {
		if(s == null) {
			return false;
		}
		else if(s.equals("") || s.trim().equals("")) {
			return false;
		}
		else if(s.equals(conf)) {
			return true;
		}
		return false;
	}

	public static String trim(String sIn){
		 if(sIn!=null){
			 return sIn.trim();
		 }
		 else{
			 return null;
		 }	 
	 }
	 
	 
	public static String tagliaParteFinaleStringa(String s, String parteDaTagliare) {
		 String ris = "";
		 ris = s.replaceFirst(parteDaTagliare, "");
		 return ris;
	 }
	
	public static void myLog(String s){
		//System.out.println("#@#----- " + s);
	 }
		 
	public static boolean isProvinciaInPiemonte (String targaProv) {
		  return (targaProv.equalsIgnoreCase("NO") || targaProv.equalsIgnoreCase("VC") || 
		  		 targaProv.equalsIgnoreCase("TO") || targaProv.equalsIgnoreCase("AL") || 
		  		 targaProv.equalsIgnoreCase("CN") || targaProv.equalsIgnoreCase("AT") ||
		  		 targaProv.equalsIgnoreCase("BI") || targaProv.equalsIgnoreCase("VB")) ? true : false;
	 }
	 
	 // per determinare la dicitura nell'e-mail dopo la protocollazione
	public static String determinaTipoDataProtocollazioneSeProvincialeORegionale(String siglaProv) {
		  String tipo = "";
		  
		  // la prov di pertinenza per la comunicazione � in Piemonte, quindi la data � di "protocollazione";
		  // diversamente � di "invio" poich� presente solo il prot regionale.
		  if(Varie.isProvinciaInPiemonte(siglaProv)){
			  tipo = "Data di Protocollazione: \n";
		  }
		  else {
			  tipo = "Data di invio: \n";
		  }
	//	  try {
	//		  GestioneListe gestioneListeUtility = GestioneListe.getInstance();
	//		  LinkedList linkList = (LinkedList) gestioneListeUtility.getELencoPvPiemonte();
	//
	//		  Iterator linkListIterator = linkList.iterator();
	//	      while (linkListIterator.hasNext()) {
	//	    	  Decodifica linkListNew = new Decodifica();
	//	    	  linkListNew = (Decodifica) linkListIterator.next();
	//	    	  if(linkListNew.getDescrizione().equalsIgnoreCase(siglaProv)) {
	//	    		  // la prov di pertinenza per la comunicazione � in Piemonte, quindi la data � di "protocollazione";
	//	    		  // diversamente � di "invio" poich� presente solo il prot regionale.
	//	    		  tipo = "Data di Protocollazione: \n";
	//	    	  }
	//	      }
	//	  }
	//	  catch (Exception e) {
	//		  //System.out.println("..... determinaTipoDataProtocollazioneSeProvincialeORegionale, errore, " + e.toString());
	//	  }
		  return tipo;
	 }
	
	
	   //*************************************************************************************************************
	
	public static boolean checkLongNonNullNonZero(Long num) {
		 if(num == null) {
			 return false;
		 }
		 else if(num.longValue() == 0) {
			 return false;
		 }
		 return true;
	 }
	
	public static boolean checkStringNonNullNonZeroNonStringaVuota(String stringa) {
		 if(stringa == null) {
			return false; 
		 }
		 else if(stringa.equals("")) {
			 return false;
		 }
		 else if(stringa.equals("0")) {
			 return false;
		 }
		 return true;
	 }
	
	public static boolean checkValoriAmmissibili(String s1, String s2 ) {
		
		return (Varie.isVoid(s1) || s1.equals("0")) && !s2.equals("0");

	 }
 
	public static String[] estraiDaStringaCompostaDaElementiSeparatiDaVirgolaUnArrayDiStringhe(String input) {
		String ris = "";
		int quanti = StringUtils.countMatches(input, ",");
		String[] lista = new String[quanti + 1];
		
		try {
			for (int i = 0; i <= quanti; i++) {
				if(i == quanti) {
					ris = input.trim();
//					//System.out.println("..... ris = " + ris);
//				   listaStringata = StringUtils.remove(listaStringata, ris + ",");
					if(ris.equals("")) {
//						//System.out.println("..... fine");
						break;
					}
					lista[i] = ris;
				}
				else {
					ris = StringUtils.substringBetween(input, "", ",").trim();
//					//System.out.println("..... ris = " + ris);
					input = StringUtils.remove(input, ris + ",");
					if(ris.equals("")) {
//						//System.out.println("..... fine2");
						break;
					}
					lista[i] = ris;
				}
			}
		}
		catch(Exception e) {
			//System.out.println("..... estraiDaStringaCompostaDaElementiSeparatiDaVirgolaUnArrayDiStringhe, err");
		}
//		//System.out.println("..... fine fine");
		return lista;
	}

    public static ArrayList<String> estraiDaStringaCompostaDaElementiSeparatiDaCarUnArrayDiStringhe(String input, String car) {
//        System.out.println("..... estraiDaStringaCompostaDaElementiSeparatiDaPileUnArrayDiStringhe");
        String ris = "";
        int quanti = StringUtils.countMatches(input, car);
//        System.out.println("..... quanti pipe = " + quanti);
//        String[] lista = new String[quanti + 1];
        ArrayList<String> alLista = new ArrayList<String>();
        
        try {
            for (int i = 0; i <= quanti; i++) {
//                System.out.println("..... input " + " " + i + " " + input);
                if(i == quanti) {
                    ris = input.trim();
                    if(ris.equals("")) {
                        break;
                    }
//                    lista[i] = ris;
//                    System.out.println("..... finale, ris= " + ris);
                    alLista.add(ris);
                }
                else {
                    ris = StringUtils.substringBetween(input, "", car).trim();
                    input = StringUtils.remove(input, ris + car);
                    if(ris.equals("")) {
                        // posso essere all'ultima occorrenza dopo l'ultimo marcatore
                        ris = input;
                        if(ris.equals("")) {
                            break;
                        }
                    }
//                    lista[i] = ris;
//                    System.out.println("..... ris= " + ris);
                    alLista.add(ris);
                }
            }
        }
        catch(Exception e) {
        }
//        return lista;
        return alLista;
    }


	public static String convertiCaratteri(String oggetto) {
		  if (oggetto == null || oggetto.equals(""))
			  return "";
		  
		  StringBuffer s = new StringBuffer(oggetto);
		  for (int i = 0; i < s.length(); i++) {
			  switch (s.charAt(i)) {
			  case '&':
				  s.replace(i, i + 1, "&amp;");
				  break;
//			  case '\'':
//				  s.replace(i, i + 1, "&apos;");
//		          break;
			  case '<':
		          s.replace(i, i + 1, "&lt;");
		          break;
			  case '>':
		          s.replace(i, i + 1, "&gt;");
		          break;
			  case '"':
		          s.replace(i, i + 1, "&quot;");
		          break;
		          // codici per encoding="Cp1252",
		          // altrimenti compilando sul server con encoding="UTF-8", si spacca
			  case '\u00E0':     //�
				  s.replace(i, i + 1, "&agrave;");
				  break;
			  case '\u00e8':     //�
				  s.replace(i, i + 1, "&egrave;");
				  break;
			  case '\u00e9':     //�
				  s.replace(i, i + 1, "&eacute;");
				  break;
			  case '\u00EC':     //�
				  s.replace(i, i + 1, "&igrave;");
				  break;
			  case '\u00F2':     //�
				  s.replace(i, i + 1, "&ograve;");
				  break;
			  case '\u00F9':     //�
				  s.replace(i, i + 1, "&ugrave;");
				  break;
		      }
		    }
		    return s.toString();
	  }


	@SuppressWarnings({ "unused", "rawtypes" })
	private static void stampaContenuteSession(HttpSession session) {

//          HttpSession session = req.getSession(false);
          //System.out.println("..... session=" + session);
          //System.out.println("..... session.isNew()=" + session.isNew());
          //System.out.println("..... session.getCreationTime()=" + session.getCreationTime());
          //System.out.println("..... session.getServletContext()=" + session.getServletContext());
          //System.out.println("..... session.getId()=" + session.getId());
          long sessionSize = 0L;
          for (Enumeration e = session.getAttributeNames(); e.hasMoreElements(); )
          {
              String name = (String) e.nextElement();
              Object value = session.getAttribute(name);
              //System.out.println("..... - session attribute " + name + "=" + value);

              try
              {
                  ByteArrayOutputStream baos = new ByteArrayOutputStream();
                  ObjectOutputStream oos = new ObjectOutputStream(baos);

                  oos.writeObject(session.getAttribute(name));
                  sessionSize += baos.size();
                  //System.out.println("..... session attribute Data size=" + baos.size() + " bytes");
              }
              catch (NotSerializableException ex)
              {
//                  //System.out.println("..... MiscUtils.sprintf("!!! UNSERIALIZABLE !!! %s - %s", name, ex.toString()));
                  //System.out.println("..... ex 0");
              }
              catch (IOException ex)
              {
//                  //System.out.println("..... MiscUtils.sprintf("!!! UNSERIALIZABLE !!! %s - %s", name, ex.toString()));
                  //System.out.println("..... ex 1");
              }
              //if (name.equals("oracle.portal.provider.http.v2.ServletProviderSession")) {
              //    ServletProviderSession sps = (ServletProviderSession) value;
              //    //System.out.println("..... sps Marcatore=" + sps.getMarcatore());
              //}
              //if (name.equals("oracle.portal.provider.http.v2.ServletProviderSession2")) {
              //ServletProviderSession2 sps2 = (ServletProviderSession2) value;
              //    //System.out.println("..... sps2 Marcatore=" + sps2.getMarcatore());
              //}

          }
          //System.out.println("..... sessionSize=" + sessionSize);
          ServletContext context = session.getServletContext();
          //System.out.println("..... context.getMajorVersion()=" + context.getMajorVersion());
          //System.out.println("..... context.getMinorVersion()=" + context.getMinorVersion());
          //System.out.println("..... context.getServerInfo()=" + context.getServerInfo());
          //System.out.println("..... context.getServletContextName()=" + context.getServletContextName());

          for (Enumeration e = context.getAttributeNames(); e.hasMoreElements(); )
          {
              String name = (String) e.nextElement();
              Object value = context.getAttribute(name);
              //System.out.println("..... - context attribute " + name + "=" + value);
          }

          for (Enumeration e = context.getInitParameterNames(); e.hasMoreElements(); )
          {
              String name = (String) e.nextElement();
              Object value = context.getAttribute(name);
              //System.out.println("..... - context init parameter " + name + "=" + value);
          }
	  }
	  
	
	public static String mettiInLikePerQuery(String testo) {
		return "%" + testo.toUpperCase() + "%";
	}
	
	public static String toUpper(String stringa) {
		if(stringa != null) {
			return stringa.toUpperCase();
		}
		return "";
	}
	
}
