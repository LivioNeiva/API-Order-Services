/*
nesse RouteModule so iremos colocar a navegaçao q servirar apenas para os components que estão 
dentro dos modules de clientes
*/

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientesFormComponent } from './clientes-form/clientes-form.component';

const routes: Routes = [
  { path : 'clientes-form', component : ClientesFormComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientesRoutingModule { }
