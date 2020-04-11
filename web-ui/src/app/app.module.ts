/**
 * @license
 * Copyright Akveo. All Rights Reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */
import { APP_BASE_HREF } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule, APP_INITIALIZER  } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { CoreModule } from './@core/core.module';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { ThemeModule } from './@theme/theme.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { KeycloakService } from './services/keycloak/keycloak.service';
import { KeycloakInterceptorService } from './services/keycloak/keycloak.interceptor.service';
import { CounterpartyService } from './domain/counterpartyService';
import { InstrumentStatisticsService } from './domain/instrumentStatisticsService';
import { AppInitService } from './app.init';
declare var window: any;

export function init_config(appLoadService: AppInitService, keycloak: KeycloakService) {
  return () =>  appLoadService.init().then( () => {
     console.info(window.config);
     keycloak.init();
    },
   );
}

@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    AppRoutingModule,
    NgbModule.forRoot(),
    ThemeModule.forRoot(),
    CoreModule.forRoot(),
  ],
  providers: [
    AppInitService,
    {
      provide: APP_INITIALIZER,
      useFactory: init_config,
      deps: [AppInitService, KeycloakService],
      multi: true,
    },
    { provide: APP_BASE_HREF, useValue: '/' },
    CounterpartyService,
    InstrumentStatisticsService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: KeycloakInterceptorService,
      multi: true,
    },
    KeycloakService,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {
}
