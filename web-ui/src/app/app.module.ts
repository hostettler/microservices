/**
 * @license
 * Copyright Akveo. All Rights Reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */
import { NgModule, APP_INITIALIZER  } from '@angular/core';
import { NbOAuth2AuthStrategy, NbAuthModule,NbOAuth2ResponseType} from '@nebular/auth';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { CoreModule } from './@core/core.module';
import { ThemeModule } from './@theme/theme.module';
import { CounterpartyService } from './domain/counterpartyService';
import { InstrumentStatisticsService } from './domain/instrumentStatisticsService';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import {
  NbChatModule,
  NbDatepickerModule,
  NbDialogModule,
  NbMenuModule,
  NbSidebarModule,
  NbToastrModule,
  NbWindowModule,
} from '@nebular/theme';
import { AppInitService } from './app.init';


declare var window: any;

export function init_config(appLoadService: AppInitService) {
  return () =>  appLoadService.init().then( () => {
     console.info(window.config);
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
    NbSidebarModule.forRoot(),
    NbMenuModule.forRoot(),
    NbDatepickerModule.forRoot(),
    NbDialogModule.forRoot(),
    NbWindowModule.forRoot(),
    NbToastrModule.forRoot(),
    CoreModule.forRoot(),
    ThemeModule.forRoot(),
    NbAuthModule.forRoot({
      strategies: [
        NbOAuth2AuthStrategy.setup({
          name: 'oauth0',
          clientId: 'dLkjEswclwactvw6l9rAmPUcFgNXM2PL',
          clientSecret: '',
          authorize: {
            endpoint: 'pinfo.us.auth0.com/oauth2/v2/auth',
            responseType: NbOAuth2ResponseType.TOKEN,
            scope:  'openid profile email',
            redirectUri: 'https://pinfo.westeurope.cloudapp.azure.com/',
          },
        }),
      ],
    }),
  ],
  providers: [
    CounterpartyService,
    InstrumentStatisticsService,
    AppInitService,
    {
      provide: APP_INITIALIZER,
      useFactory: init_config,
      deps: [AppInitService],
      multi: true,
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {
}
