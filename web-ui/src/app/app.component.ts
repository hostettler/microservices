/**
 * @license
 * Copyright Akveo. All Rights Reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */
import { Component, OnInit } from '@angular/core';
import { AnalyticsService } from './@core/utils/analytics.service';
import { SeoService } from './@core/utils/seo.service';
import { JwksValidationHandler, OAuthService } from 'angular-oauth2-oidc';
import { authConfig } from './domain/oauth-service';

@Component({
  selector: 'ngx-app',
  template: '<router-outlet></router-outlet>',
})
export class AppComponent implements OnInit {

  constructor(private analytics: AnalyticsService,
    private oauthService: OAuthService,
    private seoService: SeoService) {
    this.configure();
  }

  private configure() {
    this.oauthService.configure(authConfig);
    this.oauthService.setStorage(localStorage);
    this.oauthService.tokenValidationHandler = new JwksValidationHandler();
  }

  ngOnInit(): void {
    this.analytics.trackPageViews();
    this.seoService.trackCanonicalChanges();
    this.oauthService.loadDiscoveryDocumentAndLogin().then(
      (isLoggedIn) => {
        if (isLoggedIn) {
          this.oauthService.setupAutomaticSilentRefresh();
        } else {
          this.oauthService.initImplicitFlow();
        }
      },
      (error) => {
        console.error({ error });
        if (error.status === 400) {
          location.reload();
        }
      },
    );

  }
}
