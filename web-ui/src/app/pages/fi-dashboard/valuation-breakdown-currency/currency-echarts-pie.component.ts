import { AfterViewInit, Component, OnDestroy, AfterViewChecked, OnInit } from '@angular/core';
import { NbThemeService } from '@nebular/theme';
import { InstrumentStatisticsService } from '../../../domain/instrumentStatisticsService';
import { Valuation } from '../../../domain/valuation';

@Component({
    // tslint:disable-next-line:component-selector
    selector: 'fi-breakdown-currency',
    template: `
    <div echarts [options]="optionsCurrency" class="echart"  (chartInit)="onChartInit($event)"></div>
  `,
})
export class ValuationBreakdownCurrencyPieComponent implements AfterViewInit, AfterViewChecked, OnDestroy, OnInit {

    optionsCurrency: any = {};
    themeSubscription: any;
    echartsInstance: any;
    private datapoints: any[] = [
        { name: 'AAA_CUR', value: '789' },
        { name: 'BBB_CUR', value: '456' },
        { name: 'CCC_CUR', value: '123' },
    ];
    private currencies: string[] = ['AAA_CUR', 'BBB_CUR', 'CCC_CUR'];

    constructor(private theme: NbThemeService, public instrumentStatisticsService: InstrumentStatisticsService) {
    }

    onChartInit(e: any) {
        this.echartsInstance = e;
        this.instrumentStatisticsService.getValuation().subscribe((data: Valuation) => {
            this.currencies.length = 0;
            this.datapoints.length = 0;
            if (data && data.breakdownByCurrency) {
                for (const prop in data.breakdownByCurrency) {
                    if (data.breakdownByCurrency.hasOwnProperty(prop)) {
                        this.currencies.push(prop);
                        this.datapoints.push({ value: data.breakdownByCurrency[prop], name: prop });
                    }
                }
            }
            this.optionsCurrency.legend.data = this.currencies;
            this.optionsCurrency.series.data = this.datapoints;
            this.echartsInstance.setOption(this.optionsCurrency);
        });
    }

    ngAfterViewInit() {
        console.info('ngAfterViewInit Start');

        this.themeSubscription = this.theme.getJsTheme().subscribe(config => {

            const colors = config.variables;
            const echarts: any = config.variables.echarts;


            this.optionsCurrency = {
                backgroundColor: echarts.bg,
                color: [colors.warningLight, colors.infoLight, colors.dangerLight,
                colors.successLight, colors.primaryLight],
                tooltip: {
                    trigger: 'item',
                    formatter: function (params) {
                        const serie: string = params.seriesName;
                        const type: string = params.name;
                        const amount: number = params.value;
                        const percentage: number = params.percent;
                        const formattedAmount: string = amount.toLocaleString();
                        const output: string = '' + serie + ' <br/>' + type + ' : '
                            + formattedAmount + ' (' + percentage + '%)';
                        return output;
                    },
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: this.currencies,
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
        console.info('ngAfterViewInit End');
    }

    ngOnInit(): void {
    }

    ngAfterViewChecked(): void {

    }

    ngOnDestroy(): void {
        this.themeSubscription.unsubscribe();
    }
}
