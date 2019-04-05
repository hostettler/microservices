/**
 * @license
 * Copyright Akveo. All Rights Reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */
// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

export const environment = {
    production: false,
    apiUrl: 'URL_DE_API',
    keycloak: {
        url: 'http://iam:8080/auth',
        realm: 'apigw',
        clientId: 'api-gateway',
        checkLoginIframe: true,
        onLoad: 'login-required',
        responseMode: "fragment",
    },
    counterpartyService: {
        url: 'http://localhost:10080',
    },
    valuationService: {
        url: 'http://localhost:12080',
    },
};
