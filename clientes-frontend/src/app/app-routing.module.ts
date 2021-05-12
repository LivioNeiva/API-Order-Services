/*
AppRoutingModule
module de router da aplicação - todas as rotas q nao são de funcionaliade, nosso caso, a rota 
de cliente que é uma funcionalidade, elas irão ficar aki nesse module.
Uma rota que não é de funcionalidade é a rota para tela home(component).
resumo: app.routing.modules é para definir os  router que são da aplicação  e nao de uma 
funcionalidade, podemos declarar todas as rotas dos compoents de uma aplicação.
Obs. Nossa aplicação é organiada toda por modules, então vamos deixar cada module defina suas 
routes module
*/
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';

//vamos definir a router home, propreties path define uma url
const routes: Routes = [
  {path : 'home', component : HomeComponent}//faz rota para component home, temos importar home
];

@NgModule({
  imports: [RouterModule.forRoot(routes)], //<router-outlet></router-outlet> so funciona se RouterModule esiver instalado nesse arquivo
  exports: [RouterModule]
})
export class AppRoutingModule { }
