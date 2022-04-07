declare var window: any;

export class DynamicEnvironment {
    public get environment() {
        return window.config && window.config.environment;
    }
    public get production() {
        return window.config && window.config.production;
    }

    public get apiUrl() {
        return window.config && window.config.apiUrl;
    }

    public get counterpartyService() {
        return window.config && window.config.counterpartyService;
    }

    public get instrumentService() {
        return window.config && window.config.instrumentService;
    }

    public get valuationService() {
        return window.config && window.config.valuationService;
    }

    public get issuer() {
        return window.config && window.config.issuer;
    }

    public get clientId() {
        return window.config && window.config.clientId;
    }

    public get logoutPath() {
        return window.config && window.config.logoutPath;
    }

}
