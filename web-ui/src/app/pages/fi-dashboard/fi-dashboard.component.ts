import { Component, OnDestroy, OnInit } from '@angular/core';
import { NbThemeService } from '@nebular/theme';
import { takeWhile } from 'rxjs/operators';
import { InstrumentStatisticsService } from '../../domain/instrumentStatisticsService';
import { CounterpartyService } from '../../domain/counterpartyService';
import { Valuation } from '../../domain/valuation';
import { KeycloakService } from '../../services/keycloak/keycloak.service';
import { KeycloakInstance } from 'keycloak-js';

interface CardSettings {
    title: string;
    iconClass: string;
    type: string;
    value: string;
}

@Component({
    // tslint:disable-next-line:component-selector
    selector: 'ngx-dashboard',
    styleUrls: ['./fi-dashboard.component.scss'],
    templateUrl: './fi-dashboard.component.html',
})
export class FIDashboardComponent implements OnDestroy, OnInit {

    private alive = true;

    portfolioValuation: CardSettings = {
        title: 'Portfolio Valuation',
        iconClass: 'ion-social-usd',
        type: 'primary',
        value: '',
    };
    activeCpty: CardSettings = {
        title: '# active counterparties',
        iconClass: 'nb-person',
        type: 'success',
        value: '',
    };

    statusCards: string;
    public keycloakAuth: KeycloakInstance;
    commonStatusCardsSet: CardSettings[] = [
        this.portfolioValuation,
        this.activeCpty,
    ];

    statusCardsByThemes: {
        default: CardSettings[];
        cosmic: CardSettings[];
        corporate: CardSettings[];
    } = {
            default: this.commonStatusCardsSet,
            cosmic: this.commonStatusCardsSet,
            corporate: [
                {
                    ...this.portfolioValuation,
                    type: 'warning',
                    value: '$ 100\'000\'000',
                },
                {
                    ...this.activeCpty,
                    type: 'primary',
                    value: '2\'543\'434',
                },
            ],
        };

    constructor(private themeService: NbThemeService, public instrumentStatisticsService: InstrumentStatisticsService,
        public counterpartyService: CounterpartyService, public keycloak: KeycloakService) {
        this.themeService.getJsTheme()
            .pipe(takeWhile(() => this.alive))
            .subscribe(theme => {
                this.statusCards = this.statusCardsByThemes[theme.name];
            });

    }

    ngOnInit() {
        this.keycloakAuth = this.keycloak.getKeycloakAuth();
        if (this.keycloak.isLoggedIn() === false) {
            this.keycloak.login();
        }
        this.instrumentStatisticsService.getValuation().subscribe((data: Valuation) => {
            console.info(data);
            console.info(data.currentValue.toLocaleString());
            let value: string = '';
            if (data.reportingCurrency === 'USD') {
                value = '$';
            } else if (data.reportingCurrency === 'EUR') {
                value = 'E';
            }
            value = value + data.currentValue.toLocaleString();
            this.statusCardsByThemes.corporate[0].value = value;
        });
        this.counterpartyService.getCount().subscribe((data: Number) => {
            console.info(data);
            const value: string = data.toLocaleString();
            this.statusCardsByThemes.corporate[1].value = value;
        });

    }

    ngOnDestroy() {
        this.alive = false;
    }
}
