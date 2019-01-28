import { AfterViewInit, Component, OnDestroy } from '@angular/core';
import { NbThemeService } from '@nebular/theme';

@Component({
  selector: 'ngx-breakdown-currency',
  template: `
    <div echarts [options]="options" class="echart"></div>
  `,
})
export class ValuationBreakdownCurrencyPieComponent implements AfterViewInit, OnDestroy {
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
          formatter: function(params) {
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
          data: ['STOCK', 'LOAN', 'BOND', 'DEPOSIT', 'WARRANT'],
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
            data: [
              { value: 376127254, name: 'STOCK' },
              { value: 317483580, name: 'LOAN' },
              { value: 468433784, name: 'BOND' },
              { value: 71056222, name: 'DEPOSIT' },
              { value: 4847202, name: 'WARRANT' },
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
