/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
export const environment = {
  production: false,
  ambiente: 'local',
  shibbolethAuthentication: true,
  publicPath: 'http://localhost:4200/',

  appBaseHref: '/prodisweb',

  beServerPrefix: '',
  beService_2_WAR: '/rest/api/v1',
  beService: '/prodisweb/api/v1',

  shibbolethSSOLogoutURL: 'http://localhost:8080/prodisweb/logout',
  onAppExitURL: '',
  userManualURL: 'http://localhost:8080/UserManual/'
};
