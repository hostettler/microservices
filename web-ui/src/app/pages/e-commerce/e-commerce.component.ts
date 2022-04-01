import { AfterViewInit, Component, OnDestroy, OnInit } from '@angular/core';
import { NbThemeService } from '@nebular/theme';
import { InstrumentStatisticsService } from '../../domain/instrumentStatisticsService';
import { CounterpartyService } from '../../domain/counterpartyService';
import { Valuation } from '../../domain/valuation';
import { InstrumentService } from '../../domain/instrumentService';
import { delay, takeWhile } from 'rxjs/operators';

interface CardSettings {
    title: string;
    iconClass: string;
    type: string;
    value: string;
    onclickCallback: Function;
}


@Component({
    selector: 'ngx-ecommerce',
    templateUrl: './e-commerce.component.html',
})
export class ECommerceComponent implements OnDestroy, OnInit {

    private alive = true;

    portfolioValuation: CardSettings = {
        title: 'Portfolio Valuation',
        iconClass: 'ion-social-usd',
        type: 'warning',
        value: '$ 100\'000\'000',
        onclickCallback: null,
    };
    activeCpty: CardSettings = {
        title: '# counterparties',
        iconClass: 'nb-person',
        type: 'primary',
        value: '2\'543\'434',
        onclickCallback: null,
    };
    activeInstrument: CardSettings = {
        title: '# instruments',
        iconClass: 'nb-person',
        type: 'primary',
        value: '1\'000\'434',
        onclickCallback: (event): void => {
            console.info("propagate instruments");
            this.instrumentService.updateValuation();
        },
    };
    statusCards: string;

    commonStatusCardsSet: CardSettings[] = [
        this.portfolioValuation,
        this.activeCpty,
        this.activeInstrument,

    ];

    statusCardsByThemes: {
        default: CardSettings[];
        cosmic: CardSettings[];
        corporate: CardSettings[];
        dark: CardSettings[];
    } = {
            default: [
                { ...this.portfolioValuation },
                { ...this.activeCpty },
                { ...this.activeInstrument },
            ],
            cosmic: [
                { ...this.portfolioValuation },
                { ...this.activeCpty },
                { ...this.activeInstrument },
            ],
            corporate: [
                { ...this.portfolioValuation },
                { ...this.activeCpty },
                { ...this.activeInstrument },
            ],
            dark: [
                { ...this.portfolioValuation },
                { ...this.activeCpty },
                { ...this.activeInstrument },
            ],
        };

    optionsCurrency: any;
    optionsType: any;
    echartsTypeIntance: any;
    echartsCurrencyIntance: any;

    constructor(private themeService: NbThemeService,
        public instrumentService: InstrumentService,
        public instrumentStatisticsService: InstrumentStatisticsService,
        public counterpartyService: CounterpartyService) {
        this.themeService.getJsTheme()
            .pipe(takeWhile(() => this.alive))
            .subscribe(theme => {
                this.statusCards = this.statusCardsByThemes[theme.name];
            });
    }



    ngOnInit() {
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
            this.statusCardsByThemes.default[0].value = value;
            this.statusCardsByThemes.cosmic[0].value = value;
            this.statusCardsByThemes.corporate[0].value = value;
            this.statusCardsByThemes.dark[0].value = value;
        });
        this.counterpartyService.getCount().subscribe((data: Number) => {
            console.info(data);
            const value: string = data.toLocaleString();
            this.statusCardsByThemes.default[1].value = value;
            this.statusCardsByThemes.cosmic[1].value = value;
            this.statusCardsByThemes.corporate[1].value = value;
            this.statusCardsByThemes.dark[1].value = value;
        });
        this.instrumentService.getCount().subscribe((data: Number) => {
            console.info(data);
            const value: string = data.toLocaleString();
            this.statusCardsByThemes.default[2].value = value;
            this.statusCardsByThemes.cosmic[2].value = value;
            this.statusCardsByThemes.corporate[2].value = value;
            this.statusCardsByThemes.dark[2].value = value;
        });


        this.optionsType = {
            legend: {
                data: ['bar', 'bar2'],
                align: 'left',
            },
            tooltip: {},
            series: [
                {
                    name: 'pie',
                    type: 'pie',
                    radius: '80%',
                    center: ['50%', '50%'],
                    data: [
                    ],
                    animationDelay: (idx) => idx * 10,
                },
            ],
            animationEasing: 'elasticOut',
            animationDelayUpdate: (idx) => idx * 5,
        };

        this.optionsCurrency = {
            legend: {
                data: ['bar', 'bar2'],
                align: 'left',
            },
            tooltip: {},
            series: [
                {
                    name: 'pie',
                    type: 'pie',
                    radius: '80%',
                    center: ['50%', '50%'],
                    data: [
                    ],
                    animationDelay: (idx) => idx * 10,
                },
            ],
            animationEasing: 'elasticOut',
            animationDelayUpdate: (idx) => idx * 5,
        };
    }


    onTypeChartInit(echarts) {
        this.echartsTypeIntance = echarts;

        this.instrumentStatisticsService.getValuation().toPromise().then((data: Valuation) => {
            console.info(data);

            var breakdownByInstrumentType: any[] = new Array();

            for (let [key, value] of Object.entries(data.breakdownByInstrumentType)) {
                breakdownByInstrumentType.push({ "name": key, "value": value });
            }

            this.echartsTypeIntance.setOption({
                series: [{
                    data: breakdownByInstrumentType
                }]
            });

        });
    }

    onCurrencyChartInit(echarts) {
        this.echartsCurrencyIntance = echarts;

        this.instrumentStatisticsService.getValuation().toPromise().then((data: Valuation) => {
            console.info(data);

            var breakdownByCurrency: any[] = new Array();

            for (let [key, value] of Object.entries(data.breakdownByCurrency)) {
                breakdownByCurrency.push({ "name": key, "value": value });
            }

            this.echartsCurrencyIntance.setOption({
                series: [{
                    data: breakdownByCurrency
                }]
            });

        });
    }

    ngOnDestroy() {
        this.alive = false;
    }




}
