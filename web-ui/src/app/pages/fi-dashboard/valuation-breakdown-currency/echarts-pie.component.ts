import { AfterViewInit, Component, OnDestroy, AfterViewChecked, OnInit } from '@angular/core';
import { NbThemeService } from '@nebular/theme';
import { InstrumentStatisticsService } from '../../../domain/instrumentStatisticsService';
import { Valuation } from '../../../domain/Valuation';
import { KeycloakInstance } from 'keycloak-js';

@Component({
    selector: 'ngx-breakdown-currency',
    template: `
    <div echarts [options]="options" class="echart"></div>
  `,
})
export class ValuationBreakdownCurrencyPieComponent implements AfterViewInit, AfterViewChecked, OnDestroy, OnInit {

    options: any = {};
    themeSubscription: any;

    private legend: String[];
    private datapoints: any[] = [];
    private currencies: string[] = [];

    public keycloakAuth: KeycloakInstance;

    constructor(private theme: NbThemeService, public instrumentStatisticsService: InstrumentStatisticsService) {
    }

    ngAfterViewInit() {
        this.themeSubscription = this.theme.getJsTheme().subscribe(config => {

            const colors = config.variables;
            const echarts: any = config.variables.echarts;


            this.options = {
                backgroundColor: echarts.bg,
                color: [colors.warningLight, colors.infoLight, colors.dangerLight, colors.successLight, colors.primaryLight],
                tooltip: {
                    trigger: 'item',
                    formatter: function (params) {
                        const serie: string = params.seriesName;
                        const type: string = params.name;
                        const amount: number = params.value;
                        const percentage: number = params.percent;
                        const formattedAmount: string = amount.toLocaleString();
                        const output: string = '' + serie + ' <br/>' + type + ' : ' + formattedAmount + ' (' + percentage + '%)';
                        return output;
                    },
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: this.legend,
                    textStyle: {
                        color: echarts.textColor,
                    },
                },

                series: [
                    {
                        name: 'Instrument',
                        type: 'pie',
                        radius: '80%',
                        center: ['50%', '50%'],
                        data: this.datapoints,
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: echarts.itemHoverShadowColor,
                            },
                        },
                        label: {
                            normal: {
                                textStyle: {
                                    color: echarts.textColor,
                                },
                            },
                        },
                        labelLine: {
                            normal: {
                                lineStyle: {
                                    color: echarts.axisLineColor,
                                },
                            },
                        },
                    },
                ],
            };
        });
    }

    private valuation: Valuation;

    ngOnInit(): void {
        console.info('OnInit');
        this.currencies = [];
        this.datapoints = [];
        this.instrumentStatisticsService.getValuation().subscribe((data: Valuation) => {
            console.info(data);
            this.valuation = data;
            for (const prop in data.breakdownByCurrency) {
                if (data.breakdownByCurrency.hasOwnProperty(prop)) {
                    console.info(prop);
                    console.info(data.breakdownByCurrency[prop]);
                    this.currencies.push(prop);
                    this.datapoints.push({ value: data.breakdownByCurrency[prop], name: prop });
                }
            }
            console.info(this.currencies);
            console.info(this.datapoints);
            this.options.legend.data = this.currencies;
            this.options.series.data = this.datapoints;
        });
    }

    ngAfterViewChecked(): void {
        this.options.legend.data = this.currencies;
        this.options.series.data = this.datapoints;
    }

    ngOnDestroy(): void {
        this.themeSubscription.unsubscribe();
    }
}
