import { NgModule } from '@angular/core';

import { NgxEchartsModule } from 'ngx-echarts';

import { ThemeModule } from '../../@theme/theme.module';
import { FIDashboardComponent } from './fi-dashboard.component';
import { StatusCardComponent } from './status-card/status-card.component';

import { ValuationBreakdownCurrencyPieComponent } from './valuation-breakdown-currency/currency-echarts-pie.component';
import { ValuationBreakdownInstrumentPieComponent } from './valuation-breakdown-instrument/instrument-echarts-pie.component';

@NgModule({
    imports: [
        ThemeModule,
        NgxEchartsModule,
    ],
    declarations: [
        FIDashboardComponent,
        StatusCardComponent,
        ValuationBreakdownCurrencyPieComponent,
        ValuationBreakdownInstrumentPieComponent,
    ],
})
export class FIDashboardModule { }
