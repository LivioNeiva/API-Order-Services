package com.livioneiva.rest.exception;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

/*
 * criaremos a classe api erros que vai gerar um obj padronizado, que vai gerar o retorno quando ocorrer
 * exceptions na api, quando acontecer o tratamento de errors na api,sempre mandaremos um json padronisado
 * independente do erro q ocorra
 */

public class ApiErrors {
	
	@Getter
	private List<String> errors;
	
	
	public ApiErrors(List<String> errors) {
		this.errors=errors;
	}
	
	public ApiErrors(String messages) {
		this.errors = Arrays.asList(messages);
	}

}
