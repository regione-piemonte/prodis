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
package it.csi.prodis.prodisweb.lib.dto.prospetto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import it.csi.prodis.prodisweb.lib.dto.decodifiche.Provincia;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.StatoProspetto;
import it.csi.prodis.prodisweb.lib.util.ProdisStringUtils;

public class RicercaProspetto {

	private BigDecimal annoProspetto;
	private BigDecimal numeroProtocollo;
	private Date dataProtocolloDa;
	private Date dataProtocolloA;
	private Date dataRiferimentoDa;
	private Date dataRiferimentoA;
	private String codiceFiscaleAzienda;
	private String denominazioneAzienda;
	private String codiceRegionale;
	private List<StatoProspetto> statoProspettos;
	private Provincia provincia;
	private String codiceFiscaleStudioProf;

	public BigDecimal getAnnoProspetto() {
		return annoProspetto;
	}

	public void setAnnoProspetto(BigDecimal annoProspetto) {
		this.annoProspetto = annoProspetto;
	}

	public BigDecimal getNumeroProtocollo() {
		return numeroProtocollo;
	}

	public void setNumeroProtocollo(BigDecimal numeroProtocollo) {
		this.numeroProtocollo = numeroProtocollo;
	}

	public Date getDataProtocolloDa() {
		return dataProtocolloDa;
	}

	public void setDataProtocolloDa(Date dataProtocolloDa) {
		this.dataProtocolloDa = dataProtocolloDa;
	}

	public Date getDataProtocolloA() {
		return dataProtocolloA;
	}

	public void setDataProtocolloA(Date dataProtocolloA) {
		this.dataProtocolloA = dataProtocolloA;
	}

	public Date getDataRiferimentoDa() {
		return dataRiferimentoDa;
	}

	public void setDataRiferimentoDa(Date dataRiferimentoDa) {
		this.dataRiferimentoDa = dataRiferimentoDa;
	}

	public Date getDataRiferimentoA() {
		return dataRiferimentoA;
	}

	public void setDataRiferimentoA(Date dataRiferimentoA) {
		this.dataRiferimentoA = dataRiferimentoA;
	}

	public String getCodiceFiscaleAzienda() {
		return codiceFiscaleAzienda;
	}

	public void setCodiceFiscaleAzienda(String codiceFiscaleAzienda) {
		this.codiceFiscaleAzienda = codiceFiscaleAzienda;
	}

	public String getDenominazioneAzienda() {
		return denominazioneAzienda;
	}

	public void setDenominazioneAzienda(String denominazioneAzienda) {
		this.denominazioneAzienda = denominazioneAzienda;
	}

	public String getCodiceRegionale() {
		return codiceRegionale;
	}

	public void setCodiceRegionale(String codiceRegionale) {
		this.codiceRegionale = codiceRegionale;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public List<StatoProspetto> getStatoProspettos() {
		return statoProspettos;
	}

	public void setStatoProspettos(List<StatoProspetto> statoProspettos) {
		this.statoProspettos = statoProspettos;
	}

	public String getCodiceFiscaleStudioProf() {
		return codiceFiscaleStudioProf;
	}

	public void setCodiceFiscaleStudioProf(String codiceFiscaleStudioProf) {
		this.codiceFiscaleStudioProf = codiceFiscaleStudioProf;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RicercaProspetto [");
		builder.append("annoProspetto=" + ProdisStringUtils.checkNull(annoProspetto));
		builder.append(",\n");
		builder.append("numeroProtocollo=" + ProdisStringUtils.checkNull(numeroProtocollo));
		builder.append(",\n");
		builder.append("dataProtocolloDa=" + ProdisStringUtils.checkNull(dataProtocolloDa));
		builder.append(",\n ");
		builder.append("dataProtocolloA=" + ProdisStringUtils.checkNull(dataProtocolloA));
		builder.append(",\n ");
		builder.append("dataRiferimentoDa=" + ProdisStringUtils.checkNull(dataRiferimentoDa));
		builder.append(",\n ");
		builder.append("dataRiferimentoA=" + ProdisStringUtils.checkNull(dataRiferimentoA));
		builder.append(",\n ");
		builder.append("codiceFiscaleAzienda=" + ProdisStringUtils.checkNull(codiceFiscaleAzienda));
		builder.append(",\n ");
		builder.append("denominazioneAzienda=" + ProdisStringUtils.checkNull(denominazioneAzienda));
		builder.append(",\n ");
		builder.append("codiceRegionale=" + ProdisStringUtils.checkNull(codiceRegionale));
		builder.append(",\n ");
		if (provincia != null) {
			builder.append("provincia=" + ProdisStringUtils.checkNull(provincia.getId()));
			builder.append(",\n ");
		}
		builder.append("codiceFiscaleStudioProf=" + ProdisStringUtils.checkNull(codiceFiscaleStudioProf));
		builder.append(",\n ");
		if (statoProspettos != null) {
			for (StatoProspetto loStatoProspetto : statoProspettos) {
				builder.append("loStatoProspetto=" + ProdisStringUtils.checkNull(loStatoProspetto.getId()));
				builder.append(", ");
			}
		}
		if (super.toString() != null) {
			builder.append("\n super.toString()=");
			builder.append(super.toString());
		}
		builder.append("]");
		return builder.toString();
	}

}
