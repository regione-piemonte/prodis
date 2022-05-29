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
package it.csi.prodis.prodisweb.ejb.business.be.service.impl.prospetto.generapdf;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import com.ibm.icu.util.Calendar;

public class GeneraPdfUtilities {
	
	public static String createRow(String nomeLabel, String valore) {

		return 	"\t<tr class=\"field\">\n"
						+ "\t\t<td class=\"nome\"><label>"+nomeLabel+"</label></td>\n"
						+ "\t\t<td class=\"valore\"><input>"+valore+"</input></td>\n"
						+ "\t</tr>\n";
		
	}
	
	public static String createCapitolo(String nomeCapitolo) {
		
		return "<br></br><div class=\"capitolo\">"+nomeCapitolo+"</div><br></br>\n";
		
	}
	
	public static String createParagrafo(String nomeParagrafo) {
		
		return "<br></br><div class=\"paragrafo\">"+nomeParagrafo+"</div><br></br>\n"
				+ "<table class=\"tabellaDati\">\n";
		
	}
	
	public static String createParagrafoDati(String nomeParagrafo) {
		
		return "<br></br><div class=\"paragrafoDati\">"+nomeParagrafo+"</div><br></br>\n"
				+ "<table class=\"tabellaDati\">\n";
		
	}
	
	public static String chiudiParagrafo() {
		
		return "</table>\n";
		
	}

	public static String getHtmlString() {
		return "<html>\r\n" + 
        		"<head>\r\n" + 
        		"    <style>\r\n" + 
        		"        .field{\r\n" + 
        		"            width: 100%;\r\n" + 
        		"        }\r\n" + 
         		"        .nome{\r\n" + 
        		"            font-size: 10pt;\r\n" + 
        		"            color: black;\r\n" + 
        		"            font-family: Arial, Helvetica, sans-serif;\r\n" + 
        		"            text-align: left;\r\n" + 
        		"            width: 50%;\r\n" + 
        		"        }\r\n" + 
        		"        .valore{\r\n" + 
        		"            font-size: 12pt;\r\n" + 
        		"            color: black;\r\n" + 
        		"            font-family: Arial, Helvetica, sans-serif;\r\n" + 
        		"            border: black;\r\n" + 
        		"            text-align: left;\r\n" + 
        		"            border: 2px solid black;\r\n" + 
        		"            width: 100%;\r\n" + 
        		"        }\r\n" + 
        		"        .capitolo{\r\n" + 
        		"            font-size: 20pt;\r\n" + 
        		"            color: black;\r\n" + 
        		"            font-family: Arial, Helvetica, sans-serif;\r\n" + 
        		"            border: 2px solid black;\r\n" + 
        		"            text-align: center;\r\n" + 
        		"            background-color: darkgray;\r\n" + 
        		"        }\r\n" + 
        		"        .paragrafo{\r\n" + 
        		"            font-size: 12pt;\r\n" + 
        		"            color: black;\r\n" + 
        		"            font-family: Arial, Helvetica, sans-serif;\r\n" + 
        		"            border: 1px solid black;\r\n" + 
        		"            text-align: center;\r\n" + 
        		"        }\r\n" + 
        		"        .paragrafoDati{\r\n" + 
        		"            font-size: 12pt;\r\n" + 
        		"            color: black;\r\n" + 
        		"            font-family: Arial, Helvetica, sans-serif;\r\n" + 
        		"            border: 1px solid black;\r\n" + 
        		"            text-align: center;\r\n" + 
        		"            background-color: gray;\r\n" + 
        		"        }\r\n" + 
        		"        .regione{\r\n" + 
        		"            font-size: 20pt;\r\n" + 
        		"            color: black;\r\n" + 
        		"            font-family: Arial, Helvetica, sans-serif;\r\n" + 
        		"            text-align: left;\r\n" + 
        		"            font-weight: bold;\r\n" + 
        		"			 padding-bottom: 10px;" +	
        		"        }\r\n" + 
        		"        .prospetto{\r\n" + 
        		"            font-size: 20pt;\r\n" + 
        		"            color: black;\r\n" + 
        		"            font-family: Arial, Helvetica, sans-serif;\r\n" + 
        		"            text-align: right;\r\n" + 
        		"            font-weight: bold;\r\n" + 
        		"            text-decoration: underline;\r\n" +
        		"			 padding-bottom: 10px;" +	
        		"        }\r\n" + 
         		"        .ptIntest{\r\n" + 
        		"            width: 100%;\r\n" + 
        		"            font-size: 12pt;\r\n" + 
        		"            color: black;\r\n" + 
        		"            font-family: Arial, Helvetica, sans-serif;\r\n" + 
        		"            text-align: center;\r\n" + 
        		"        }\r\n" +
         		"        .ptCol{\r\n" + 
        		"            width: 25%;\r\n" + 
        		"            font-size: 12pt;\r\n" + 
        		"            color: black;\r\n" + 
        		"            font-family: Arial, Helvetica, sans-serif;\r\n" + 
        		"            text-align: center;\r\n" + 
        		"        }\r\n" +
         		"        .ptRow{\r\n" + 
        		"            width: 100%;\r\n" + 
        		"            font-size: 12pt;\r\n" + 
        		"            color: black;\r\n" + 
        		"            font-family: Arial, Helvetica, sans-serif;\r\n" + 
        		"            text-align: center;\r\n" + 
        		"        }\r\n" +
         		"        .ptCell{\r\n" + 
        		"            width: 100%;\r\n" + 
        		"            font-size: 12pt;\r\n" + 
        		"            color: black;\r\n" + 
        		"            font-family: Arial, Helvetica, sans-serif;\r\n" + 
        		"            text-align: center;\r\n" + 
        		"            border: 1px solid black;\r\n" + 
        		"        }\r\n" +
         		"        .tabellaDati{\r\n" + 
        		"            width: 100%;\r\n" + 
        		"        }\r\n" + 
      			"    </style>\r\n" + 
       			"</head>\r\n" + 
        		"<body>\r\n" + 
        		"    <div class=\"regione\">Regione Piemonte</div>\r\n" + 
        		"    <br></br><br></br><div class=\"prospetto\">Prospetto informativo on-line</div><br></br><br></br>\r\n" + 
        		"{{quadro1}}\r\n" + 
        		"{{quadro2}}\r\n" + 
        		"{{quadro3}}\r\n" + 
        		"</body>\r\n" + 
        		"</html>";
	}
	
	public static String removeTime(Date date) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		int anno = calendar.get(Calendar.YEAR);
		String mese = calendar.get(Calendar.MONTH)+1<10 ? 
				"0"+Integer.toString(calendar.get(Calendar.MONTH)+1) : Integer.toString(calendar.get(Calendar.MONTH)+1);
		String giorno = calendar.get(Calendar.DAY_OF_MONTH)<10 ? 
				"0"+Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)) : Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
		
        return giorno+"/"+mese+"/"+anno;
    }
	
	public static String fromMinutesToHHmm(int minutes) {
	    long hours = TimeUnit.MINUTES.toHours(Long.valueOf(minutes));
	    long remainMinutes = minutes - TimeUnit.HOURS.toMinutes(hours);
	    return String.format("%02d:%02d", hours, remainMinutes);
	}
	
	public static String escapeHtml(String s) {
		String myString = StringEscapeUtils.escapeHtml(s);
		return myString;
	}
	
	public static String replaceSpecialChar(String s) {
		
		Map<String,String> tokens = new HashMap<String,String>();
		
		tokens.put("&agrave;","&#224;");
		tokens.put("&aacute;","&#225;");
		tokens.put("&pound;","&#163;");
		tokens.put("&sect;","&#167;");
		tokens.put("&deg;","&#176;");
		tokens.put("&ccedil;","&#231;");
		tokens.put("&egrave;","&#232;");
		tokens.put("&eacute;","&#233;");
		tokens.put("&igrave;","&#236;");
		tokens.put("&iacute;","&#237;");
		tokens.put("&ograve;","&#242;");
		tokens.put("&oacute;","&#243;");
		tokens.put("&ugrave;","&#249;");
		tokens.put("&uacute;","&#250;");
		
        tokens.put("&nbsp;","&#160;");
		tokens.put("&iexcl;","&#161;");
		tokens.put("&cent;","&#162;");
		tokens.put("&curren;","&#164;");
		tokens.put("&yen;","&#165;");
		tokens.put("&brvbar;","&#166;");
		tokens.put("&uml;","&#168;");
		tokens.put("&copy;","&#169;");
		tokens.put("&ordf;","&#170;");
		tokens.put("&laquo;","&#171;");
		tokens.put("&not;","&#172;");
		tokens.put("&shy;","&#173;");
		tokens.put("&reg;","&#174;");
		tokens.put("&macr;","&#175;");
		tokens.put("&plusmn;","&#177;");
		tokens.put("&sup2;","&#178;");
		tokens.put("&sup3;","&#179;");
		tokens.put("&acute;","&#180;");
		tokens.put("&micro;","&#181;");
		tokens.put("&para;","&#182;");
		tokens.put("&middot;","&#183;");
		tokens.put("&cedil;","&#184;");
		tokens.put("&sup1;","&#185;");
		tokens.put("&ordm;","&#186;");
		tokens.put("&raquo;","&#187;");
		tokens.put("&frac14;","&#188;");
		tokens.put("&frac12;","&#189;");
		tokens.put("&frac34;","&#190;");
		tokens.put("&iquest;","&#191;");
		tokens.put("&Agrave;","&#192;");
		tokens.put("&Aacute;","&#193;");
		tokens.put("&Acirc;","&#194;");
		tokens.put("&Atilde;","&#195;");
		tokens.put("&Auml;","&#196;");
		tokens.put("&Aring;","&#197;");
		tokens.put("&AElig;","&#198;");
		tokens.put("&Ccedil;","&#199;");
		tokens.put("&Egrave;","&#200;");
		tokens.put("&Eacute;","&#201;");
		tokens.put("&Ecirc;","&#202;");
		tokens.put("&Euml;","&#203;");
		tokens.put("&Igrave;","&#204;");
		tokens.put("&Iacute;","&#205;");
		tokens.put("&Icirc;","&#206;");
		tokens.put("&Iuml;","&#207;");
		tokens.put("&ETH;","&#208;");
		tokens.put("&Ntilde;","&#209;");
		tokens.put("&Ograve;","&#210;");
		tokens.put("&Oacute;","&#211;");
		tokens.put("&Ocirc;","&#212;");
		tokens.put("&Otilde;","&#213;");
		tokens.put("&Ouml;","&#214;");
		tokens.put("&times;","&#215;");
		tokens.put("&Oslash;","&#216;");
		tokens.put("&Ugrave;","&#217;");
		tokens.put("&Uacute;","&#218;");
		tokens.put("&Ucirc;","&#219;");
		tokens.put("&Uuml;","&#220;");
		tokens.put("&Yacute;","&#221;");
		tokens.put("&THORN;","&#222;");
		tokens.put("&szlig;","&#223;");
		tokens.put("&acirc;","&#226;");
		tokens.put("&atilde;","&#227;");
		tokens.put("&auml;","&#228;");
		tokens.put("&aring;","&#229;");
		tokens.put("&aelig;","&#230;");
		tokens.put("&ecirc;","&#234;");
		tokens.put("&euml;","&#235;");
		tokens.put("&icirc;","&#238;");
		tokens.put("&iuml;","&#239;");
		tokens.put("&eth;","&#240;");
		tokens.put("&ntilde;","&#241;");
		tokens.put("&ocirc;","&#244;");
		tokens.put("&otilde;","&#245;");
		tokens.put("&ouml;","&#246;");
		tokens.put("&divide;","&#247;");
		tokens.put("&oslash;","&#248;");
		tokens.put("&ucirc;","&#251;");
		tokens.put("&uuml;","&#252;");
		tokens.put("&yacute;","&#253;");
		tokens.put("&thorn;","&#254;");
		tokens.put("&yuml;","&#255;");
		tokens.put("&OElig;","&#338;");
		tokens.put("&oelig;","&#339;");
		tokens.put("&Scaron;","&#352;");
		tokens.put("&scaron;","&#353;");
		tokens.put("&Yuml;","&#376;");
		tokens.put("&circ;","&#710;");
		tokens.put("&tilde;","&#732;");
		tokens.put("&ensp;","&#8194;");
		tokens.put("&emsp;","&#8195;");
		tokens.put("&thinsp;","&#8201;");
		tokens.put("&zwnj;","&#8204;");
		tokens.put("&zwj;","&#8205;");
		tokens.put("&lrm;","&#8206;");
		tokens.put("&rlm;","&#8207;");
		tokens.put("&ndash;","&#8211;");
		tokens.put("&mdash;","&#8212;");
		tokens.put("&lsquo;","&#8216;");
		tokens.put("&rsquo;","&#8217;");
		tokens.put("&sbquo;","&#8218;");
		tokens.put("&ldquo;","&#8220;");
		tokens.put("&rdquo;","&#8221;");
		tokens.put("&bdquo;","&#8222;");
		tokens.put("&dagger;","&#8224;");
		tokens.put("&Dagger;","&#8225;");
		tokens.put("&permil;","&#8240;");
		tokens.put("&lsaquo;","&#8249;");
		tokens.put("&rsaquo;","&#8250;");
        tokens.put("&euro;","&#8364;");

		// Create pattern of the format "%(cat|beverage)%"
		String patternString = "(" + StringUtils.join(tokens.keySet(), "|") + ")";
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(s);

		StringBuffer sb = new StringBuffer();
		while(matcher.find()) {
		    matcher.appendReplacement(sb, tokens.get(matcher.group(1)));
		}
		matcher.appendTail(sb);

		//System.out.println(sb.toString());
		
		return sb.toString();
		
	}

}
