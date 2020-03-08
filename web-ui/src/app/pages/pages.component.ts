import { Component } from '@angular/core';

import { MENU_ITEMS } from './pages-menu';

@Component({
  // tslint:disable-next-line:component-selector
  selector: 'fi-pages',
  styleUrls: ['pages.component.scss'],
  template: `
    <fi-sample-layout>
      <nb-menu [items]="menu"></nb-menu>
      <router-outlet></router-outlet>
    </fi-sample-layout>
  `,
})
export class PagesComponent {

  menu = MENU_ITEMS;
}
