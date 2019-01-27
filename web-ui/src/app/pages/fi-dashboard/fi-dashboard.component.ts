import {Component, OnDestroy} from '@angular/core';
import { NbThemeService } from '@nebular/theme';
import { takeWhile } from 'rxjs/operators' ;


interface CardSettings {
  title: string;
  iconClass: string;
  type: string;
  value: string;
}

@Component({
  selector: 'ngx-dashboard',
  styleUrls: ['./fi-dashboard.component.scss'],
  templateUrl: './fi-dashboard.component.html',
})
export class FIDashboardComponent implements OnDestroy {

  private alive = true;

  portfolioValuation: CardSettings = {
    title: 'Portfolio Valuation',
    iconClass: 'logo-usd',
    type: 'primary',
    value: ''
  };
  activeCpty: CardSettings = {
    title: '# active counterparties',
    iconClass: 'nb-person',
    type: 'success',
    value: ''
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
  } = {
    default: this.commonStatusCardsSet,
    cosmic: this.commonStatusCardsSet,
    corporate: [
      {
        ...this.portfolioValuation,
        type: 'warning',
        value : '$ 100\'000\'000'
      },
      {
        ...this.activeCpty,
        type: 'primary',
        value : '2\'543\'434'
      }
    ],
  };

  constructor(private themeService: NbThemeService) {
    this.themeService.getJsTheme()
      .pipe(takeWhile(() => this.alive))
      .subscribe(theme => {
        this.statusCards = this.statusCardsByThemes[theme.name];
    });

  }

  ngOnDestroy() {
    this.alive = false;
  }
}
