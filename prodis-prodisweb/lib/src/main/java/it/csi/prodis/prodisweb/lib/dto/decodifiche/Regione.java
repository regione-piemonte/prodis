/*-
 * ========================LICENSE_START=================================
 * PRODIS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodisweb.lib.dto.decodifiche;

import java.io.Serializable;
import java.util.Date;

import it.csi.prodis.prodisweb.lib.dto.BaseDto;

/**
 * The Class Regione.
 */
public class Regione extends BaseDto<Long> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String codAmbitoDiffusione;
	private String codMobilitageog;
	private String codRegioneMin;
	private String dsAmbitoDiffusione;
	private String dsProTRegione;
	private Date dtFine;
	private Date dtInizio;
	private Date dtTmst;
//	private List<Provincia> provincias;

	/**
	 * @return the codAmbitoDiffusione
	 */
	public String getCodAmbitoDiffusione() {
		return codAmbitoDiffusione;
	}
	
	/**
	 * @param codAmbitoDiffusione the codAmbitoDiffusione to set
	 */
	public void setCodAmbitoDiffusione(String codAmbitoDiffusione) {
		this.codAmbitoDiffusione = codAmbitoDiffusione;
	}

	/**
	 * @return the codMobilitageog
	 */
	public String getCodMobilitageog() {
		return codMobilitageog;
	}
	
	/**
	 * @param codMobilitageog the codMobilitageog to set
	 */
	public void setCodMobilitageog(String codMobilitageog) {
		this.codMobilitageog = codMobilitageog;
	}

	/**
	 * @return the codRegioneMin
	 */
	public String getCodRegioneMin() {
		return codRegioneMin;
	}
	
	/**
	 * @param codRegioneMin the codRegioneMin to set
	 */
	public void setCodRegioneMin(String codRegioneMin) {
		this.codRegioneMin = codRegioneMin;
	}

	/**
	 * @return the dsAmbitoDiffusione
	 */
	public String getDsAmbitoDiffusione() {
		return dsAmbitoDiffusione;
	}
	
	/**
	 * @param dsAmbitoDiffusione the dsAmbitoDiffusione to set
	 */
	public void setDsAmbitoDiffusione(String dsAmbitoDiffusione) {
		this.dsAmbitoDiffusione = dsAmbitoDiffusione;
	}

	/**
	 * @return the dsProTRegione
	 */
	public String getDsProTRegione() {
		return dsProTRegione;
	}
	
	/**
	 * @param dsProTRegione the dsProTRegione to set
	 */
	public void setDsProTRegione(String dsProTRegione) {
		this.dsProTRegione = dsProTRegione;
	}

	/**
	 * @return the dtFine
	 */
	public Date getDtFine() {
		return dtFine;
	}
	
	/**
	 * @param dtFine the dtFine to set
	 */
	public void setDtFine(Date dtFine) {
		this.dtFine = dtFine;
	}

	/**
	 * @return the dtInizio
	 */
	public Date getDtInizio() {
		return dtInizio;
	}
	
	/**
	 * @param dtInizio the dtInizio to set
	 */
	public void setDtInizio(Date dtInizio) {
		this.dtInizio = dtInizio;
	}

	/**
	 * @return the dtTmst
	 */
	public Date getDtTmst() {
		return dtTmst;
	}
	
	/**
	 * @param dtTmst the dtTmst to set
	 */
	public void setDtTmst(Date dtTmst) {
		this.dtTmst = dtTmst;
	}

	/**
	 * @return the provincias
	 */
//	public List<Provincia> getProvincias() {
//		return provincias;
//	}
//	
//	/**
//	 * @param provincias the provincias to set
//	 */
//	public void setProvincias(List<Provincia> provincias) {
//		this.provincias = provincias;
//	}

}
