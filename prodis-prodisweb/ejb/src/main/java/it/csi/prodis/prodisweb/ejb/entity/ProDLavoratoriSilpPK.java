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
package it.csi.prodis.prodisweb.ejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the PRO_D_LAVORATORI_SILP database table.
 * 
 */
@Embeddable
public class ProDLavoratoriSilpPK implements Serializable   {

	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="CF_IMPRESA")
	private String cfImpresa;

	@Column(name="ID_T_PROVINCIA", insertable=false, updatable=false)
	private long idTProvincia;

	@Column(name="CODICE_FISCALE")
	private String codiceFiscale;

	public ProDLavoratoriSilpPK() {
	}
	public String getCfImpresa() {
		return this.cfImpresa;
	}
	public void setCfImpresa(String cfImpresa) {
		this.cfImpresa = cfImpresa;
	}
	public long getIdTProvincia() {
		return this.idTProvincia;
	}
	public void setIdTProvincia(long idTProvincia) {
		this.idTProvincia = idTProvincia;
	}
	public String getCodiceFiscale() {
		return this.codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProDLavoratoriSilpPK)) {
			return false;
		}
		ProDLavoratoriSilpPK castOther = (ProDLavoratoriSilpPK)other;
		return 
			this.cfImpresa.equals(castOther.cfImpresa)
			&& (this.idTProvincia == castOther.idTProvincia)
			&& this.codiceFiscale.equals(castOther.codiceFiscale);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cfImpresa.hashCode();
		hash = hash * prime + ((int) (this.idTProvincia ^ (this.idTProvincia >>> 32)));
		hash = hash * prime + this.codiceFiscale.hashCode();
		
		return hash;
	}
}
