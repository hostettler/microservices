import { AfterViewInit, Component, OnDestroy } from '@angular/core';
import { NbThemeService } from '@nebular/theme';

@Component({
  selector: 'ngx-breakdown-instrument',
  template: `
    <div echarts [options]="options" class="echart"></div>
  `,
})
export class ValuationBreakdownInstrumentPieComponent implements AfterViewInit, OnDestroy {
  options: any = {};
  themeSubscription: any;

  constructor(private theme: NbThemeService) {
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
            let serie:string = params.seriesName;
            let type:string = params.name;
            let amount:number = params.value;
            let percentage:number = params.percent;
            let formattedAmount:string = amount.toLocaleString();
            let output:string =  '' + serie + ' <br/>' + type + ' : ' + formattedAmount + ' ('+ percentage + '%)';
            return output;
          }
        },
        legend: {
          orient: 'vertical',
          left: 'left',
          data: ['CHF', 'SGD', 'GBP', 'USD'],
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
            data: [
              { value: 70073308, name: 'CHF' },
              { value: 66540948, name: 'SGD' },
              { value: 913601713, name: 'EUR' },
              { value: 102726326, name: 'GBP' },
              { value: 85005746, name: 'USD' },
            ],
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

  ngOnDestroy(): void {
    this.themeSubscription.unsubscribe();
  }
}
