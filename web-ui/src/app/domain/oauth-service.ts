import { AuthConfig } from 'angular-oauth2-oidc';
import { auth0Config } from '../../assets/config/oauth-config';



export const authConfig: AuthConfig = {
    // Url of the Identity Provider
    issuer: auth0Config.issuer,
    // The SPA's id. The SPA is registered with this id at the auth-server
    clientId: auth0Config.clientId,

    // URL of the SPA to redirect the user to after login
    redirectUri: `${window.location.origin}/index.html`,

    logoutUrl: auth0Config.issuer + auth0Config.logoutPath + '?client_id='
        + auth0Config.clientId
        + `&returnTo=${window.location.origin}/index.html`,

    requireHttps: false,

    responseType: 'code',


    // set the scope for the permissions the client should request
    // The first three are defined by OIDC. The 4th is a usecase-specific one
    scope: 'openid email',
};