/*-
 * ========================LICENSE_START=================================
 * PRODIS BackEnd - INTEGRATION AAEP, IRIDE, COMONL submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodisweb.lib.external.impl.integ;

import java.util.ArrayList;
import java.util.List;

import it.csi.prodis.prodisweb.lib.dto.common.Ruolo;

public class RuoliHelperImpl {

	public List<Ruolo> getRuoli() {
		Ruolo ruoloTest1 = new Ruolo();
		ruoloTest1.setDenominazioneAzienda("AAA S.P.A.");
		ruoloTest1.setCodiceFiscale("08101160151");
		ruoloTest1.setRuolo("Legale rappresentante");

		Ruolo ruoloTest2 = new Ruolo();
		ruoloTest2.setDenominazioneAzienda("BBB S.r.l.");
		ruoloTest2.setCodiceFiscale("02062800046");
		ruoloTest2.setRuolo("Legale rappresentante");

		List<Ruolo> ruolos = new ArrayList<>();
		ruolos.add(ruoloTest1);
		ruolos.add(ruoloTest2);
		return ruolos;
	}

}
