import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

import { PagesComponent } from './pages.component';
import { FIDashboardComponent } from './fi-dashboard/fi-dashboard.component';


const routes: Routes = [{
  path: '',
  component: PagesComponent,
  children: [
  {
    path: 'fi-dashboard',
    component: FIDashboardComponent,
  }, {
    path: '',
    redirectTo: 'fi-dashboard',
    pathMatch: 'full',
  }],
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PagesRoutingModule {
}
