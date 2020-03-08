import { AfterViewInit, Component, OnDestroy, OnInit } from '@angular/core';
import { NbThemeService } from '@nebular/theme';
import { InstrumentStatisticsService } from '../../../domain/instrumentStatisticsService';
import { Valuation } from '../../../domain/valuation';

@Component({
    selector: 'fi-breakdown-instrument',
    template: `
    <div echarts [options]="optionsIntruments" class="echart" (chartInit)="onChartInit($event)"></div>
  `,
})
export class ValuationBreakdownInstrumentPieComponent implements AfterViewInit, OnDestroy, OnInit {
    optionsIntruments: any = {};
    themeSubscription: any;
    private datapoints: any[] = [
        { name: 'AAA_INS', value: '11' },
        { name: 'BBB_INS', value: '333' },
        { name: 'CCC_INS', value: '333' },
    ];
    private instruments: string[] = ['AAA_INS', 'BBB_INS', 'CCC_INS'];
    echartsInstance: any;
    constructor(private theme: NbThemeService, public instrumentStatisticsService: InstrumentStatisticsService) {
    }


    onChartInit(e: any) {
        this.echartsInstance = e;
        this.instrumentStatisticsService.getValuation().subscribe((data: Valuation) => {
            this.instruments.length = 0;
            this.datapoints.length = 0;
            if (data && data.breakdownByCurrency) {
                for (const prop in data.breakdownByInstrumentType) {
                    if (data.breakdownByInstrumentType.hasOwnProperty(prop)) {
                        this.instruments.push(prop);
                        this.datapoints.push({ value: data.breakdownByInstrumentType[prop], name: prop });
                    }
                }
            }
            this.optionsIntruments.legend.data = this.instruments;
            this.optionsIntruments.series.data = this.datapoints;
            this.echartsInstance.setOption(this.optionsIntruments);
        });
    }


    ngAfterViewInit() {
        this.themeSubscription = this.theme.getJsTheme().subscribe(config => {

            const colors = config.variables;
            const echarts: any = config.variables.echarts;

            this.optionsIntruments = {
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
                        const output: string = '' + serie + ' <br/>' + type
                            + ' : ' + formattedAmount + ' (' + percentage + '%)';
                        return output;
                    },
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: this.instruments,
                    textStyle: {
                        color: echarts.textColor,
                    },
                },
                series: [
                    {
                        name: 'Currency',
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

    ngOnInit(): void {
    }

    ngOnDestroy(): void {
        this.themeSubscription.unsubscribe();
    }
}
