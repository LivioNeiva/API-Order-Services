package com.livioneiva.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.livioneiva.model.entity.Cliente;
import com.livioneiva.model.repository.ClienteRepository;

@RestController //retorna o objeto e os dados do objeto q sao grvados diretamente na resposta HTTP como JSON ou xml
@RequestMapping("/api/clientes")//vai mapear qual é a URL base q vai ser tratada dentro desse controller
public class ClienteController {
	
	//umatributo final de uma classe pode ter seu valor atribuido uma unica vez
	private final ClienteRepository repository;
	
	@Autowired //dependencia dentro do construtor
	public ClienteController(ClienteRepository repository) {
		this.repository=repository;
	}
	
	//mapear a url para que seja feito a requisição do cliente
	@PostMapping //mapear essa url para q seja feito uma requisição
	@ResponseStatus(HttpStatus.CREATED)//mapea o metodo para corpo da resposta, define o codigo de status, desse metodo é de criação. "CREATED"
	public Cliente salvar(@RequestBody @Valid Cliente cliente) {//RequestBody = temos que informar o objeto cliente vai ser um json que va vim pelo corpo da requisição, conversao do objeto json para obj cliente
		//esse metodo vai criar um recurso no servidor
		//@Valid vai validar a mensgem, @Empty,@CPF, @NotNull
		return repository.save(cliente);
	}
	/*
	//Optional<T> pode exixtir um objeto ou nao, retorna um obj optinal
	@GetMapping("{id}")
	public Optional<Cliente> acharPorId(@PathVariable Integer id) {
		return repository.findById(id);
	}
	*/
	@GetMapping("{id}")
	public Cliente acharPorId(@PathVariable Integer id) {
		return repository
				.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "cliente nao encontrado"));
	}
    /*
    () -> supply, se conseguir obter o id cliente, retorna o cliente, caso contrario retorna Exception
    q foi disponibilizada pelo pacote org.springframework.web.server.ResponseStatusException,
    ela serve para mapeare lançar um erro de status, isso quer dizer q, esse codigo de status
    q estou mandando NOT_FOUND, o mesmo vai ser lançado como erro q vai receber esse
    codigo de estatus(HttpStatus.NOT_FOUND) como retorno da requisição, nessa forma vai ficar todo metodo
    mapeado para achar pelo id.
    Se o cliente nao exisir eu recebo codigo de status NOT_FOUND

     */
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)//https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status/204
	public void deletar(@PathVariable Integer id) {//NO_CONTENT -> O código de resposta HTTP de status de sucesso 204 No Content indica que a solicitação foi bem sucedida e o cliente não precisa sair da página atual. Uma resposta 204 é armazenada em cache por padrão. Um cabeçalho ETag está incluso na resposta
		//repository.delete(id); //esse metodo tb resolveria
		repository.findById(id)
					.map(cliente -> {//usando o map possibilita eu saber se meu cliente existe na base de dados
						repository.delete(cliente);//vamos manibular o obj com map usando uma expressao
						return cliente.getNome(); //vai retornar null, eu posso retornar o proprio cliente, return cliente, se colocamos o void.TYPE. para nao fiar sem return
					})//caso nao encontre cliente eu lanço exception abaixo NOT_FOUND = STATUS 404 - NOT_FUND
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "cliente nao encontrado"));
		/*
        return Void.TYPE; Seu propósito é simplesmente representar o tipo de retorno void como uma classe e conter um valor público Class <Void>.
        Não é instanciado porque seu único construtor é privado. Portanto, o único valor que podemos atribuir a uma variável Void é nulo.
         */
	}
	
	//obs temos passar id quando preisamos fazer alguma ação no registro. ex atualizar ou deletar
	
	@PutMapping("{id}")//é usado para atualiazar completamente um recurso, ("{id}") paramero de url,vamos passar id na url
	@ResponseStatus(HttpStatus.NO_CONTENT)//NO_CONTENT -> O código de resposta HTTP de status de sucesso 204 No Content indica que a solicitação foi bem sucedida e o cliente não precisa sair da página atual. Uma resposta 204 é armazenada em cache por padrão. Um cabeçalho ETag está incluso na resposta
	public void atualizar(@PathVariable Integer id, @RequestBody @Valid Cliente clienteAtualizado ) { //vamos passar os dados do cliente atualizado via @RequestBody
		repository
				.findById(id)//localizamos o id para termos certeza de sua existencia
				.map(cliente -> { //no map faremos o processo de atualização, o obj cliente-> e as informaçoes do cliente que foi encontrado no dba
					cliente.setNome(clienteAtualizado.getNome());//atualizaremos o nome
					cliente.setCpf(clienteAtualizado.getCpf());//atualizaremos o cpf
					return repository.save(cliente);
				})//caso nao encontremos o cliente lançaremos a exception abaixo NOT_FOUND = STATUS 404 - NOT_FUND
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "cliente nao encontrado para ser atualizado"));
	}
	
	@GetMapping //obrigador a por para mapear metodo
	public List<Cliente> listarClientes(){
		return repository.findAll();
	}
}
/*
a deéndencia pode ser feita na variavel, no contrutor ou nos meodos sets
ex
public void setRepository(ClienteRepository repository){
    this.repository = repository;
}
 */
/*
Um atributo final de uma classe pode ter seu valor atribuído uma única vez, seja na
própria declaração ou no construtor. Use isso para garantir que um valor ou
referência de objeto não vai mudar. ... Se você tem um algoritmo que usa esse
variável, você pode armazenar valores calculados sem a preocupação do valor mudar
 */
