import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClientesRoutingModule } from './clientes-routing.module';
import { ClientesFormComponent } from './clientes-form/clientes-form.component';


@NgModule({
  declarations: [
    ClientesFormComponent
  ],
  imports: [
    CommonModule,
    ClientesRoutingModule //ele so reconhece a rota do cliente se tiver essa importação
  ], exports: [ //temos q exportar para q o mesmo fique visivel nos outros modules, e visualizado em outras telas
      ClientesFormComponent
  ]
})
export class ClientesModule { }
