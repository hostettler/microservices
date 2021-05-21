import { Component, OnDestroy, OnInit  } from '@angular/core';
import { NbThemeService } from '@nebular/theme';
import { takeWhile } from 'rxjs/operators';
import { InstrumentStatisticsService } from '../../domain/instrumentStatisticsService';
import { CounterpartyService } from '../../domain/counterpartyService';
import { Valuation } from '../../domain/valuation';

interface CardSettings {
  title: string;
  iconClass: string;
  type: string;
  value: string;
}


@Component({
  selector: 'ngx-ecommerce',
  templateUrl: './e-commerce.component.html',
})
export class ECommerceComponent implements OnDestroy, OnInit  {

private alive = true;

portfolioValuation: CardSettings = {
    title: 'Portfolio Valuation',
    iconClass: 'ion-social-usd',
    type: 'warning',
    value: '$ 100\'000\'000',
};
activeCpty: CardSettings = {
    title: '# counterparties',
    iconClass: 'nb-person',
    type: 'primary',
    value: '2\'543\'434',
};

statusCards: string;

commonStatusCardsSet: CardSettings[] = [
    this.portfolioValuation,
    this.activeCpty,
];

statusCardsByThemes: {
    default: CardSettings[];
    cosmic: CardSettings[];
    corporate: CardSettings[];
    dark: CardSettings[];
} = {
        default: [
            { ...this.portfolioValuation},
            { ...this.activeCpty},
        ],
        cosmic: [
            { ...this.portfolioValuation},
            { ...this.activeCpty},
        ],
        corporate: [
            { ...this.portfolioValuation},
            { ...this.activeCpty},
        ],
        dark: [
            { ...this.portfolioValuation},
            { ...this.activeCpty},
        ],
    };


   constructor(private themeService: NbThemeService, public instrumentStatisticsService: InstrumentStatisticsService,
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
  }

  ngOnDestroy() {
    this.alive = false;
  }
}
