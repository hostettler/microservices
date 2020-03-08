/**
 * @license
 * Copyright Akveo. All Rights Reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */
export const environment = {
  production: true,
  apiUrl: 'URL_DE_API',
  keycloak: {
        url: 'https://localhost/auth',
        realm: 'apigw',
        clientId: 'web-sso',
        checkLoginIframe: true,
        onLoad: 'login-required',
        responseMode: 'fragment',
    },
    counterpartyService: {
        url: 'https://localhost/api/v1/counterparty',
    },
    valuationService: {
        url: 'https://localhost/api/v1/valuation',
    },
};
