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

public class CampoCella {

	private String campo;
	public String getCampo() {return campo;}
	public void setCampo(String campo) {this.campo = campo;}
	
	private TipoCella tipo;
	public TipoCella getTipo() {return tipo;}
	public void setTipo(TipoCella tipo) {this.tipo = tipo;}
	
	private Obbligatorio obbligatorio;
	public Obbligatorio getObbligatorio() {return obbligatorio;}
	public void setObbligatorio(Obbligatorio obbligatorio) {this.obbligatorio = obbligatorio;}

	private Comparabile comparabile;
	public Comparabile getComparabile() {return comparabile;}
	public void setComparabile(Comparabile comparabile) {this.comparabile = comparabile;}

	private Validabile validabile;
	public Validabile getValidabile() {return validabile;}
	public void setValidabile(Validabile validabile) {this.validabile = validabile;}


	public CampoCella(
			String campo, 
			TipoCella tipo, 
			Obbligatorio obbligatorio, 
			Comparabile comparabile, 
			Validabile validabile) {
		setCampo(campo);
		setTipo(tipo);
		setObbligatorio(obbligatorio);
		setComparabile(comparabile);
		setValidabile(validabile);
	}

}
