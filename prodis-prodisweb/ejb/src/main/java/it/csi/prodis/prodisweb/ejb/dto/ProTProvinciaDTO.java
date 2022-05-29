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
//classe generata automaticamente da generator.strategy.DTOSpringNamedJdbcStrategy
package it.csi.prodis.prodisweb.ejb.dto;
import java.util.Date;
import java.io.Serializable;

public class ProTProvinciaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codProvinciaMin =  null;
	private String dsProTProvincia =  null;
	private Date dtFine =  null;
	//campo chiave primaria
	private Long idTProvincia =  null;
	private Date dtInizio =  null;
	//campo chiave esterna verso la colonna ID_T_REGIONE della tabella PRO_T_REGIONE
	private Long idTRegione =  null;
	private Date dtTmst =  null;
	private String dsTarga =  null;

	public String getCodProvinciaMin() {
			return codProvinciaMin;
	}

	public String getDsProTProvincia() {
			return dsProTProvincia;
	}

	public Date getDtFine() {
			return dtFine;
	}

	public java.sql.Date getDtFineSql() {
		if(dtFine!=null) {
			return new java.sql.Date(dtFine.getTime());
		}
		return null;
	}

	public Long getIdTProvincia() {
			return idTProvincia;
	}

	public Date getDtInizio() {
			return dtInizio;
	}

	public java.sql.Date getDtInizioSql() {
		if(dtInizio!=null) {
			return new java.sql.Date(dtInizio.getTime());
		}
		return null;
	}

	public Long getIdTRegione() {
			return idTRegione;
	}

	public Date getDtTmst() {
			return dtTmst;
	}

	public java.sql.Date getDtTmstSql() {
		if(dtTmst!=null) {
			return new java.sql.Date(dtTmst.getTime());
		}
		return null;
	}

	public String getDsTarga() {
			return dsTarga;
	}

	public void setCodProvinciaMin(String codProvinciaMin) {
		this.codProvinciaMin = codProvinciaMin;
	}

	public void setDsProTProvincia(String dsProTProvincia) {
		this.dsProTProvincia = dsProTProvincia;
	}

	public void setDtFine(Date dtFine) {
		this.dtFine = dtFine;
	}

	public void setIdTProvincia(Long idTProvincia) {
		this.idTProvincia = idTProvincia;
	}

	public void setDtInizio(Date dtInizio) {
		this.dtInizio = dtInizio;
	}

	public void setIdTRegione(Long idTRegione) {
		this.idTRegione = idTRegione;
	}

	public void setDtTmst(Date dtTmst) {
		this.dtTmst = dtTmst;
	}

	public void setDsTarga(String dsTarga) {
		this.dsTarga = dsTarga;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProTProvinciaDTO [");
		if (codProvinciaMin != null)
			builder.append("codProvinciaMin=").append(codProvinciaMin).append("; ");
		if (dsProTProvincia != null)
			builder.append("dsProTProvincia=").append(dsProTProvincia).append("; ");
		if (dtFine != null)
			builder.append("dtFine=").append(dtFine).append("; ");
		if (idTProvincia != null)
			builder.append("idTProvincia=").append(idTProvincia).append("; ");
		if (dtInizio != null)
			builder.append("dtInizio=").append(dtInizio).append("; ");
		if (idTRegione != null)
			builder.append("idTRegione=").append(idTRegione).append("; ");
		if (dtTmst != null)
			builder.append("dtTmst=").append(dtTmst).append("; ");
		if (dsTarga != null)
			builder.append("dsTarga=").append(dsTarga).append("; ");
		builder.append("]");
	return builder.toString();
	}
} //fine classe

