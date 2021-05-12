import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { from } from 'rxjs';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import {TemplateModule } from './template/template.module';
import {ClientesModule } from './clientes/clientes.module';

import { HomeComponent } from './home/home.component';
import { RodapeComponent } from './rodape/rodape.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

//define q essa classe é modulo Angular
@NgModule({//aqui declararamos os components que faça parte desse modules
  declarations: [
    AppComponent, //componente inicial da aplicação
    HomeComponent,
    RodapeComponent
  ],
  //aqui declaramos outros modules q vai agregar o module projeto
  imports: [
    BrowserModule, //esse modules é nescessario para a app rodar no browse  ele vem dessa biblioteca '@angular/common'
    AppRoutingModule, // navegar entre os components nas telas
    TemplateModule,
    BrowserAnimationsModule,
    ClientesModule //temos q importar todos os modules forem criados, clienteModule faz as telas de clente aparecer no browse
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

/*
o unico modules q deve ter o bootStrap na aplicação é appModule.ts, é modules raiz, modules base
module principal, so ele é q tem ter a propriedade bootStrap, para dizer qual é a classe, qual é
o component q vai ser mostrado assim q inicializar a aplicação
*/