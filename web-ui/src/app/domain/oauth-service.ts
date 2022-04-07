import { AuthConfig } from 'angular-oauth2-oidc';
import { environment } from '../../environments/environment';

export const authConfig: AuthConfig = {
    // Url of the Identity Provider
    get issuer() {
        return environment.issuer;
    },
    // The SPA's id. The SPA is registered with this id at the auth-server
    get clientId() {
        return environment.clientId;
    },

    // URL of the SPA to redirect the user to after login
    get redirectUri() {
        return `${window.location.origin}/index.html`;
    },

    get logoutUrl() {
        return environment.issuer + environment.logoutPath + '?client_id='
            + environment.clientId
            + `&returnTo=${window.location.origin}/index.html`;
    },

    get requireHttps() {
        return false
    },

    get responseType() {
        return 'code'
    },

    // set the scope for the permissions the client should request
    // The first three are defined by OIDC. The 4th is a usecase-specific one
    get scope() {
        return 'openid email'
    },
};