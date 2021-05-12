//essa importação transforma essa classe em um componente Angular
import { Component, OnInit } from '@angular/core';//a maioria dos projetos temos q importar pacote @angular/core

import { Cliente} from '../cliente';//..-> retorna para pasta raiz

//essa anotations referencia o componente '@angular/core'
@Component({
  selector: 'app-clientes-form', //quando formos referenciar no app.component.html, referenciaremos com nome app-clientes-form
  templateUrl: './clientes-form.component.html',
  styleUrls: ['./clientes-form.component.css']
})
export class ClientesFormComponent implements OnInit {

  cliente?: Cliente; //com a ? nao precisamos inicializar a variavel

  constructor() { }

  ngOnInit(): void {
  }

}

/*
implements OnInit
Constructor: é um método default do TypeScript, ele é utilizado como membro uma class e nós 
possibilita trabalhar com Injeção de Dependência (DI). ... ngOnInit: é carregado na 
inicialização de um componente.

A ngOnInit função é um dos métodos de ciclo de vida de um componente Angular. ... 
Diferente do método construtor, o ngOnInit método vem de uma interface Angular ( OnInit ) que o 
componente precisa implementar para usar esse método. O ngOnInit método é chamado logo após a 
criação do componente.

ngOnInit é um gancho de ciclo de vida chamado por Angular para indicar que Angular terminou de 
criar o componente.
Temos que importar OnInitassim para usá-lo (na verdade, implementar OnInitnão é obrigatório, 
  mas é considerado uma boa prática):

import { Component, OnInit } from '@angular/core';
*/