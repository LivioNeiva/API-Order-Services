package com.livioneiva.model.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity //informa q a classe repesentara uma entidade e seus objetos deveram ser persistidos no dba
@Data //criaremos gets, sets, contrutores(Vazios) e toString
public class Servico {

	@Id //campo será a chave primaria da tabela
	@GeneratedValue(strategy = GenerationType.IDENTITY) //o banco de dados fará o auto incremento no campo id
	Integer id;
	
	@Column(nullable = false, length=150)
	private String descricao;

    //https://www.devmedia.com.br/hibernate-mapping-mapeando-relacionamentos-entre-entidades/29445
    //relacionamento de 1 para muitos com cliente, o cliente pode ter muitos serviços,
    // mais o serviço so terás um cliente 
	@ManyToOne //informa sao muito clientes para 1 serviços
	@JoinColumn(name="id_cliente")//deifine uma chave estrangeira, tabela de serviço vai ter uma chave estrangeira para tabela clientes
	
	private Cliente cliente;
	
	//utilize sempre Strings no construtor do BigDecimal.
	@Column
	private BigDecimal valor; //bigDecimal = trabalhar com uma maior precisão em valores monetários em Java.
	
}
