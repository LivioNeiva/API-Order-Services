package com.livioneiva.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.livioneiva.rest.exception.ApiErrors;

/* 
 * vamos fazer um tratamento de erros na api, para mapear a resposta quando a validação ocorrer
 * vamos mapear a resposta de acordo com o sistema, mensagem de erro será tratada pelo front-end
 * e mostrada para usuario final.
 */
/*
 * ControlleAdvice = é uma anotation q a spring tem q identifica um stereotipo de uma classe
 * e que vai receber requisições de quando houver um erro, o erro q vai ser interpretado e
 * pode modifciar o retorno de sua requisição
 */


@RestControllerAdvice //serve para interceptar as exceptions e faz o mesmo serviço @ControllerAdvice
public class ApplicationControllerAdvice {
	/*
	 * vamos criar um metodo para receber a exception, que é lançada quando uma exception
	 * de validação é criada pelo @Valid, a mesma está na classe ClienteController nos metodos
	 * salvar e atualizar. A exception MehodArgumentNotValidException é quem captura a
	 * exception, a mesma está no argumentos do metodo handValidationErrors
	 * MethodArgumentNotValidException = argumento de metodo nao valido exception
	 */

	@ExceptionHandler(MethodArgumentNotValidException.class)//@ExceptionHangler = serve para tratar um erro expecifico, temos q mapear, temos q informar a exception q vou receber
	@ResponseStatus(HttpStatus.BAD_REQUEST)//temos fazer o retorno da resposta correta, temos que mapear a resposta para nao ficar recebendo o codigo de status 200 padrão. temos fazer retornar status 400 com HttpStatus.BAD_REQUEST
	public ApiErrors handleValidationErrors(MethodArgumentNotValidException ex) {//vamos interceptar a exception quando ela ocorrer e quero fazer algo com a exception, pra fazer isso tenho que por como argumentos
		BindingResult bindingResult = ex.getBindingResult();//o obj ex vai me passar msg de erros da validação @Valid que está na classe ClienteController
		List<String> messages = bindingResult.getAllErrors() // getErrors é um array de erros, com as msg de campos q ocorrem erros
								.stream()
								.map(objectError -> objectError.getDefaultMessage())
								.collect(Collectors.toList());//a forma q esta sendo coletada é em forma de uma lista, vai transformas essa stream de String
		return new ApiErrors(messages); // em uma lista de strings para a classe ApiErrors
		
	}
	
	/*
	 * ResponseEntity = representa todas as resposta http: codigo de status, cabeçalho e corpo como resultado
	 * podemos usa-lo para configurar totalmente a resposta http
	 * https://www.devmedia.com.br/http-status-code/41222
	 */
	
	//retorno dinamico, metodo que retorna respota 404-not-found quando o cliente nao for encontrado
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity responseStatusException(ResponseStatusException ex) {
		String messageErrors = ex.getMessage();//coleta a mensagem do cliente nao encontrado: ResponseStatusException(HttpStatus.NOT_FOUND, "cliente nao encontrado"));
		HttpStatus codigoStatus = ex.getStatus();//coleta os codigos de status
		ApiErrors apiErrors = new ApiErrors(messageErrors);//SATATUS 404 - NOT_FOUND
		return new ResponseEntity(apiErrors, codigoStatus); //vai retornar dois parametros o corpo da resposta e status da resposta
		
	}
	
}

/*
 * @ResponseStatus(HttpStatus.BAD_REQUEST) 400 Bad Request
	https://httpstatuses.com/400
 * 400 Bad Request
 * O servidor não pode ou não processará a solicitação devido a algo que é percebido como 
 * um erro do cliente (por exemplo, sintaxe de solicitação malformada, enquadramento de 
 * mensagem de solicitação inválido ou roteamento de solicitação enganoso). 
 */

/*
[BindingResult] é o objeto do Spring que contém o resultado da validação e vinculação e contém
erros que podem ter ocorrido. O BindingResult deve vir logo após o objeto de modelo que é validado
ou então o Spring falhará em validar o objeto e lançará uma exceção.
Quando Spring vê @Valid, ele tenta encontrar o validador para o objeto sendo validado.
O Spring seleciona automaticamente as anotações de validação se você tiver habilitado o “orientado por
anotações”. O Spring então invoca o validador e coloca quaisquer erros no BindingResult e adiciona o
BindingResult ao modelo de visualização.

obs. o @ExceptionHandler(MethodArgumentNotValidException.class) será aplicddo para todos os metodos do sistema q estiver com @Valid,

Estereótipo é o conceito ou imagem preconcebida, padronizada e generalizada estabelecida pelo senso
 comum, sem conhecimento profundo, sobre algo ou alguém

 resumo:
 @RestControllerAdive vai interceptar todas as vezes q houver um erro de validação, ou seja o
 MethodArgumentNotValidException(argumneto de metodo nao valido exception)
  e atraves do @ExceptionHandler ele vai capturar a exception ex e vamos pegar o bindingResultq tem todas
  as msg e erros e vamos retornar como objeto padronizado

@ResponseStatus(HttpStatus.BAD_REQUEST) 400 Bad Request
https://httpstatuses.com/400
O servidor não pode ou não processará a solicitação devido a algo que é percebido como um erro do cliente
(por exemplo, sintaxe de solicitação malformada, enquadramento de mensagem de solicitação inválido ou
roteamento de solicitação enganoso).
 */
