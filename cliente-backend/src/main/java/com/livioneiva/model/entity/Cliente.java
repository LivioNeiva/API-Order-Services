package com.livioneiva.model.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //informa q uma classe representara uma entidade e seu objetos devem ser persistidos no banco de dados
@Data //completo gets, sets, construtores(vazio) e toString
@NoArgsConstructor //gera um construtor sem argumentos
@AllArgsConstructor// gera um construtor com argumentos
@Builder //vai gerar builder do cliente
public class Cliente {

	@Id //id da tabela, chave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY)//VAMOS DEIXAR PARA BANCO DE DADOS FAZER O AUTO INCREMENTO
	private Integer id;
	
	@Column(nullable = false, length = 150)
	@NotEmpty(message = "{campo.nome.obrigatorio}") //aplica uma regra de validação na variavel cliente, nao aceita null ou campos vazios
	private String nome;
	
	@Column(nullable = false, length = 11)
	@NotNull(message = "{campo.cpf.obrigatorio}")
	@CPF(message = "{campo.cpf.invalido}" )
	private String cpf;
	
	
	@Column(name = "data_cadastro", updatable = false) //name =nome da tabela no bda, updatable nao atualiza a data ao modificar o campo
	@JsonFormat(pattern = "dd/MM/yyyy") //formato da data no campo dataCadastro
	private LocalDate dataCadastro;
	
	@PrePersist() //quando a entidade cliente for persistir no banco de dados, será executado o metodo abaixo
	public void persistir() {//os comandos abaixo serão persistidos no banco de dados
		setDataCadastro(LocalDate.now());//seta a data do momento no banco de dados
	}
	
}
