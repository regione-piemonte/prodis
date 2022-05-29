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
 * The primary key class for the PRO_D_ASS_PUBBLICHE database table.
 * 
 */
@Embeddable
public class ProDAssPubblichePK implements Serializable {

	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "ID_PROSPETTO", insertable = false, updatable = false)
	private long idProspetto;

	@Column(name = "ID_T_REGIONE", insertable = false, updatable = false)
	private long idTRegione;

	public ProDAssPubblichePK() {
	}

	public long getIdProspetto() {
		return this.idProspetto;
	}

	public void setIdProspetto(long idProspetto) {
		this.idProspetto = idProspetto;
	}

	public long getIdTRegione() {
		return this.idTRegione;
	}

	public void setIdTRegione(long idTRegione) {
		this.idTRegione = idTRegione;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProDAssPubblichePK)) {
			return false;
		}
		ProDAssPubblichePK castOther = (ProDAssPubblichePK) other;
		return (this.idProspetto == castOther.idProspetto) && (this.idTRegione == castOther.idTRegione);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.idProspetto ^ (this.idProspetto >>> 32)));
		hash = hash * prime + ((int) (this.idTRegione ^ (this.idTRegione >>> 32)));

		return hash;
	}
}
