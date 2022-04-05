import { Component } from '@angular/core';
import { OAuthService } from 'angular-oauth2-oidc';
import { MENU_ITEMS } from './pages-menu';

@Component({
  selector: 'ngx-pages',
  styleUrls: ['pages.component.scss'],
  template: `
    <ngx-one-column-layout>
      <nb-menu [items]="menu"></nb-menu>
      <router-outlet></router-outlet>
    </ngx-one-column-layout>
  `,
})
export class PagesComponent {

  menu = MENU_ITEMS;

  constructor(private oauthService: OAuthService) {
  }

  public ensureLogin(): boolean {
    console.log('isloggedin' + this.oauthService.hasValidAccessToken());
    if (!this.oauthService.hasValidAccessToken()) {
      try {
        this.oauthService.tryLogin();
      } catch (error) {
        console.error(error);
        return false;
      }
    }
    return true;
  }
}
