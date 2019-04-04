import { AfterViewInit, Component, OnDestroy, AfterViewChecked, OnInit } from '@angular/core';
import { NbThemeService } from '@nebular/theme';
import { InstrumentStatisticsService } from '../../../domain/instrumentStatisticsService' ;
import { Valuation } from '../../../domain/Valuation' ;

@Component({
  selector: 'ngx-breakdown-instrument',
  template: `
    <div echarts [options]="options" class="echart"></div>
  `,
})
export class ValuationBreakdownInstrumentPieComponent implements AfterViewInit, OnDestroy, OnInit {
  options: any = {};
  themeSubscription: any;

  private legend: String[];
  private datapoints: any[] = [];
  private currencies: string[] = [];

  constructor(private theme: NbThemeService,  public instrumentStatisticsService: InstrumentStatisticsService) {
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
          formatter:   function(params) {
            const serie: string = params.seriesName;
            const type: string = params.name;
            const amount: number = params.value;
            const percentage: number = params.percent;
            const formattedAmount: string = amount.toLocaleString();
            const output: string =  '' + serie + ' <br/>' + type + ' : ' + formattedAmount + ' (' + percentage + '%)';
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

      private valuation: Valuation;
      ngOnInit(): void {
        console.info('OnInit');
        this.currencies = [];
        this.datapoints = [];
        this.instrumentStatisticsService.getValuation().subscribe((data: Valuation) => {
             console.info(data);
             this.valuation = data;
             for (const prop in data.breakdownByInstrumentType) {
                if (data.breakdownByInstrumentType.hasOwnProperty(prop)) {
                    console.info(prop);
                    console.info(data.breakdownByInstrumentType[prop]);
                    this.currencies.push(prop);
                    this.datapoints.push({value: data.breakdownByInstrumentType[prop], name : prop});
                }
             }
             console.info(this.currencies);
             console.info(this.datapoints);
            this.options.legend.data = this.currencies;
            this.options.series.data = this.datapoints;
        });
    }

  ngOnDestroy(): void {
    this.themeSubscription.unsubscribe();
  }
}
