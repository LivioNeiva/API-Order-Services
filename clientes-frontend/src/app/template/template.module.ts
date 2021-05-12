import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './navbar/navbar.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { RouterModule } from '@angular/router';



@NgModule({
  declarations: [
    NavbarComponent, //importando componentes navBar, menu horizontal 
    SidebarComponent // importando sideBar, barra de menu vertical
  ],
  imports: [
    CommonModule,
    //quando precisamos usar alguma diretiva, algum componente, qalquer coisa faça parte de um module, temos imports  RouteModule p/utilizar recurso
    RouterModule //para que posssamos exportar o link do Angula, precisamos imports RouterModule
  ],
  exports:[
    NavbarComponent, //temos exportar os componentes para q o mesmos possam ser vistos
    SidebarComponent
  ]
})
export class TemplateModule { }
