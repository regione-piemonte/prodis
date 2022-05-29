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

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.apache.log4j.Logger;

import it.csi.prodis.prodisweb.lib.dto.prospetto.LavoratoriInForza;

public class ReflectionUtils {

	private static final Logger log = Logger
			.getLogger("Upload/Download lavoratori");
	
	private static final String GET = "get";
	
	private static final String IS = "is";
	
	private static final String SET = "set";
	
	public static String getClassName(Object aClass) {
		
		return aClass.getClass().getSimpleName();
		
	}

	public static String getProperty( LavoratoriInForza lavoratore, String name )  {
		
		try {
			
			for ( Method method : lavoratore.getClass().getMethods()) {
				
				StringBuffer get = new StringBuffer();
				
				StringBuffer is = new StringBuffer();
				
				if (method.getName().equals(get.append(GET).append(name).toString()) 
						|| method.getName().equals(is.append(IS).append(name).toString())) {
					
					Object obj = method.invoke(lavoratore, new Object[0]);
					
					return obj != null ? obj.toString() : "";
					
				}
				
			}
			
		} catch (Exception ecc) {
			
			log.warn("[ReflectionUtils::getProperty] Exception = " + ecc.getMessage());
			
		}
		
		return null;
		
	}

	public static void setProperty( LavoratoriInForza lavoratore, CampoCella campoCella, String val )  {
		
		try {
			
			for ( Method method : lavoratore.getClass().getMethods()) {
				
				StringBuffer set = new StringBuffer();
				
				if (method.getName().equals(set.append(SET).append(campoCella.getCampo()).toString())) {
					
					if (TipoCella.string.equals(campoCella.getTipo())) {
						
						method.invoke(lavoratore, val);
						
					} else if (TipoCella.date.equals(campoCella.getTipo())) {
						
						try {
							
							method.invoke(lavoratore, val!=null && !"".equals(val) 
									? new SimpleDateFormat("dd/MM/yyyy").parse(val) 
									: null);
							
						} catch (Exception e1) {
							
							method.invoke(lavoratore, val!=null && !"".equals(val) 
									? new SimpleDateFormat("EEE MMM ss HH:mm:ss z yyyy",Locale.US).parse(val) 
									: null);
							
						}
						
						
					} else if (TipoCella.number.equals(campoCella.getTipo())) {
						
						method.invoke(lavoratore, val!=null && !"".equals(val)  
								? new BigDecimal(Integer.parseInt(val)) 
								: null);
						
					}
					
					break;
					
				}
				
			}
			
		} catch (Exception e) {
			
			log.warn("[ReflectionUtils::setProperty] Exception = " + e.getMessage());
		
		}
		
	}
	
	public static void copyProperty(CampoCella campoCella, LavoratoriInForza orig, LavoratoriInForza dest){
		
		try {
			
			setProperty(dest, campoCella, getProperty(orig, campoCella.getCampo()));
			
		} catch (Exception e) {
			
			log.warn("[ReflectionUtils::copyProperty] Exception = " + e.getMessage());
			
		}
		
	}
	
}
