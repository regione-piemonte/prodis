/*-
 * ========================LICENSE_START=================================
 * PRODIS Servizi - LIB submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodissrv.lib.dto.common;

import java.io.Serializable;

import it.csi.prodis.prodissrv.lib.dto.BaseDto;
import it.csi.prodis.prodissrv.lib.util.ProdisStringUtils;

/**
 * The Class Comune.
 */
public class DecodificaGenerica extends BaseDto<Long> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String codDecodifica;
	private String dsDecodifica;
	private Long idDecodifica;
	private Long idFiltroFacoltativo;

	/**
	 * ambitoDecodifica -- utilizzato per categoriaEscluse decodifica
	 */
	private String ambitoDecodifica;

	/**
	 * flgAncheNonValidi -- se impostato a true non esegue il filtro su data inizio
	 * e fine
	 */
	private boolean flgAncheNonValidi = false;

	/**
	 * @return the codDecodifica
	 */
	public String getCodDecodifica() {
		return codDecodifica;
	}

	/**
	 * @param codDecodifica the codDecodifica to set
	 */
	public void setCodDecodifica(String codDecodifica) {
		this.codDecodifica = codDecodifica;
	}

	/**
	 * @return the dsDecodifica
	 */
	public String getDsDecodifica() {
		return ProdisStringUtils.escapeSql(dsDecodifica);
	}

	/**
	 * @param dsDecodifica the dsDecodifica to set
	 */
	public void setDsDecodifica(String dsDecodifica) {
		this.dsDecodifica = dsDecodifica;
	}

	/**
	 * @return the idDecodifica
	 */
	public Long getIdDecodifica() {
		return idDecodifica;
	}

	/**
	 * @param idDecodifica the idDecodifica to set
	 */
	public void setIdDecodifica(Long idDecodifica) {
		this.idDecodifica = idDecodifica;
	}

	/**
	 * @return the ambitoDecodifica
	 */
	public String getAmbitoDecodifica() {
		return ambitoDecodifica;
	}

	/**
	 * @param ambitoDecodifica the ambitoDecodifica to set
	 */
	public void setAmbitoDecodifica(String ambitoDecodifica) {
		this.ambitoDecodifica = ambitoDecodifica;
	}

	/**
	 * @return the flgAncheNonValidi
	 */
	public boolean isFlgAncheNonValidi() {
		return flgAncheNonValidi;
	}

	/**
	 * @param flgAncheNonValidi the flgAncheNonValidi to set
	 */
	public void setFlgAncheNonValidi(boolean flgAncheNonValidi) {
		this.flgAncheNonValidi = flgAncheNonValidi;
	}

	/**
	 * @return the idFiltroFacoltativo
	 */
	public Long getIdFiltroFacoltativo() {
		return idFiltroFacoltativo;
	}

	/**
	 * @param idFiltroFacoltativo the id optional to set
	 */
	public void setIdFiltroFacoltativo(Long idFiltroFacoltativo) {
		this.idFiltroFacoltativo = idFiltroFacoltativo;
	}

}
